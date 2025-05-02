##深度学习计算
###层和块
定义__init__(self)
定义forword(self,x)
```py
import torch
from torch import nn
from torch.nn import functional as F

net = nn.Sequential(nn.Linear(20, 256), nn.ReLU(), nn.Linear(256, 10))#定义了一个特殊的module

X = torch.rand(2, 20)
#print(net(X))
```


每一个层或者神经网络都是nn.Module的一个子类
每一个Module都有两个重要的函数
__init__(self):定义需要的类和参数
forward(self,X):
```py
import torch
from torch import nn
from torch.nn import functional as F

class MLP(nn.Module):
    # 用模型参数声明层。这里，我们声明两个全连接的层
    def __init__(self):
        # 调用MLP的父类Module的构造函数来执行必要的初始化。
        super().__init__()

        # 需要的层
        self.hidden = nn.Linear(20, 256)  # 隐藏层
        self.out = nn.Linear(256, 10)  # 输出层

    # 定义模型的前向传播，即如何根据输入X返回所需的模型输出
    def forward(self, X):
        # 注意，这里我们使用ReLU的函数版本，其在nn.functional模块中定义。
        return self.out(F.relu(self.hidden(X)))
        # 根据前面定义的层来组织前向运算
```
使用
```py
net = MLP()
# 实例化类
net(X)
# 传入参数
```


顺序块
```py
class MySequential(nn.Module):#定义一个sequential函数
    def __init__(self, *args):#*args就是就是传递一个可变参数列表给函数实参，这个参数列表的数目未知，甚至长度可以为0
        super().__init__()#继承父类
        for idx, module in enumerate(args):#对于每一个传入的层
            # 这里，module是Module子类的一个实例。我们把它保存在'Module'类的成员
            # 变量_modules中。_module的类型是OrderedDict
            self._modules[str(idx)] = module

    def forward(self, X):
        # OrderedDict保证了按照成员添加的顺序遍历它们
        for block in self._modules.values():
            X = block(X)
        return X

#net = MySequential(nn.Linear(20, 256), nn.ReLU(), nn.Linear(256, 10))
#net(X)
```


```py
class FixedHiddenMLP(nn.Module):
    def __init__(self):
        super().__init__()
        # 不计算梯度的随机权重参数。因此其在训练期间保持不变
        self.rand_weight = torch.rand((20, 20),#放进随机的权重 
            requires_grad=False) # 不参与训练
       
        self.linear = nn.Linear(20, 20)

    def forward(self, X):#在forward里放入任何自己想要的
        X = self.linear(X)
        # 使用创建的常量参数以及relu和mm函数
        X = F.relu(torch.mm(X, self.rand_weight) + 1)
        # 复用全连接层。这相当于两个全连接层共享参数
        # 手写了一层，将X与随机权重进行计算
        X = self.linear(X)
        # 控制流
        while X.abs().sum() > 1:
            X /= 2
        return X.sum()


#net = FixedHiddenMLP()
#net(X)
```


```py
class NestMLP(nn.Module):
    def __init__(self):
        super().__init__()
        self.net = nn.Sequential(nn.Linear(20, 64), nn.ReLU(),
                                 nn.Linear(64, 32), nn.ReLU())
        # 嵌套了一层sequential
        self.linear = nn.Linear(32, 16)

    def forward(self, X):
        return self.linear(self.net(X))

chimera = nn.Sequential(NestMLP(), nn.Linear(16, 20), FixedHiddenMLP())#疯狂嵌套
#chimera(X)
```
**可以在定义Module里嵌套nn.Squential()**
**也可以直接在nn.Sequential(放置别的module)**

###参数管理
```py
import torch
from torch import nn

net = nn.Sequential(nn.Linear(4, 8), nn.ReLU(), nn.Linear(8, 1))
X = torch.rand(size=(2, 4))
net(X)

#参数访问net[2].state_dict()
#可以查看某一层参数的值
print(net[2].state_dict())

#目标参数

#可以直接访问参数
print(type(net[2].bias))
# <class 'torch.nn.parameter.Parameter'>
print(net[2].bias)
# tensor([-0.1991], requires_grad=True)
print(net[2].bias.data)
# tensor([-0.1991])
print(net[2].weight.grad == None)
# True


#一次性访问所有参数
print(*[(name, param.shape) for name, param in net[0].named_parameters()])

#打印名字与参数的形状
print(*[(name, param.shape) for name, param in net.named_parameters()])

'''
使用*将列表推导式的结果解包，意味着将列表中的每个元素作为单独的参数传递给print函数。
这样可以使得每个元组(name, param.shape)都被打印出来，而不是打印整个列表。
简而言之，这段代码的目的是打印出net[0]网络模型中每个参数的名称和形状。
'''
#另一种访问方式
net.state_dict()['2.bias'].data

#从嵌套块收集参数
def block1():
    return nn.Sequential(nn.Linear(4, 8), nn.ReLU(),
                         nn.Linear(8, 4), nn.ReLU())

def block2():
    net = nn.Sequential()
    for i in range(4):
        # 在这里嵌套
        net.add_module(f'block {i}', block1())
    return net

rgnet = nn.Sequential(block2(), nn.Linear(4, 1))
rgnet(X)

print(rgnet)#可以通过print大致了解某个网络的样子
```

###参数初始化
```py
#参数初始化
#内置初始化
def init_normal(m):
    if type(m) == nn.Linear:
        nn.init.normal_(m.weight, mean=0, std=0.01)
        nn.init.zeros_(m.bias)
net.apply(init_normal)#遍历一遍
print(net[0].weight.data[0], net[0].bias.data[0])

def init_constant(m):#初始为常数
    if type(m) == nn.Linear:
        nn.init.constant_(m.weight, 1)
        nn.init.zeros_(m.bias)
net.apply(init_constant)
print(net[0].weight.data[0], net[0].bias.data[0])

#对不同的层应用不同的初始化方式
def init_xavier(m):
    if type(m) == nn.Linear:
        nn.init.xavier_uniform_(m.weight)
def init_42(m):
    if type(m) == nn.Linear:
        nn.init.constant_(m.weight, 42)

net[0].apply(init_xavier)
net[2].apply(init_42)
print(net[0].weight.data[0])
print(net[2].weight.data)

#自定义初始化
def my_init(m):
    if type(m) == nn.Linear:
        print("Init", *[(name, param.shape)
                        for name, param in m.named_parameters()][0])
        nn.init.uniform_(m.weight, -10, 10)
        m.weight.data *= m.weight.data.abs() >= 5#保留大于五的权值，不然为0

net.apply(my_init)
net[0].weight[:2]

#还可以直接设参数
net[0].weight.data[:] += 1
net[0].weight.data[0, 0] = 42
net[0].weight.data[0]

#参数绑定
# 我们需要给共享层一个名称，以便可以引用它的参数
shared = nn.Linear(8, 8)
net = nn.Sequential(nn.Linear(4, 8), nn.ReLU(),
                    shared, nn.ReLU(),#相同
                    shared, nn.ReLU(),#相同
                    nn.Linear(8, 1))
net(X)
# 检查参数是否相同
print(net[2].weight.data[0] == net[4].weight.data[0])
net[2].weight.data[0, 0] = 100
# 确保它们实际上是同一个对象，而不只是有相同的值
print(net[2].weight.data[0] == net[4].weight.data[0])

```

###自定义层

```py
import torch
import torch.nn.functional as F
from torch import nn


class CenteredLayer(nn.Module):#层也是一个nn.Module的子类
    def __init__(self):
        super().__init__()

    def forward(self, X):
        return X - X.mean()

layer = CenteredLayer()
layer(torch.FloatTensor([1, 2, 3, 4, 5]))

net = nn.Sequential(nn.Linear(8, 128), CenteredLayer())
Y = net(torch.rand(4, 8))
print(Y.mean())

#带参数的层
class MyLinear(nn.Module):
    def __init__(self, in_units, units):#输入维度，输出维度
        super().__init__()
        self.weight = nn.Parameter(torch.randn(in_units, units))
        self.bias = nn.Parameter(torch.randn(units,))
    def forward(self, X):
        linear = torch.matmul(X, self.weight.data) + self.bias.data#需要weight.data作为参数
        return F.relu(linear)

linear = MyLinear(5, 3)
print(linear.weight)
```

###读写文件
```py
import torch
from torch import nn
from torch.nn import functional as F

x = torch.arange(4)
torch.save(x, 'x-file')#存储一个向量
x2 = torch.load('x-file')#读取
print(x2)

#存一个张量列表
y = torch.zeros(4)
torch.save([x, y],'x-files')
x2, y2 = torch.load('x-files')
print((x2, y2))

#存一个字典
mydict = {'x': x, 'y': y}
torch.save(mydict, 'mydict')
mydict2 = torch.load('mydict')

#加载和保存模型参数
class MLP(nn.Module):
    def __init__(self):
        super().__init__()
        self.hidden = nn.Linear(20, 256)
        self.output = nn.Linear(256, 10)

    def forward(self, x):
        return self.output(F.relu(self.hidden(x)))

net = MLP()
X = torch.randn(size=(2, 20))
Y = net(X)
#将模型的参数存储在一个叫做“mlp.params”的文件中
torch.save(net.state_dict(), 'mlp.params')


clone = MLP()
clone.load_state_dict(torch.load('mlp.params'))#读取参数
clone.eval()

Y_clone = clone(X)
Y_clone == Y
```