##卷积神经网络
1.平移不变性（translation invariance）：不管检测对象出现在图像中的哪个**位置**，神经网络的前面几层应该对相同的图像区域具有相似的反应，即为“平移不变性”。
2.局部性（locality）：神经网络的前面几层应该只探索输入图像中的局部区域，而不过度在意图像中相隔较远区域的关系，这就是“局部性”原则。最终，可以**聚合**这些局部特征，以在整个图像级别进行预测。

遍历两个维度做求和。每个x都会和n个w做内积得到n个输出。

###卷积
####概率论中的卷积
$Z=X+Y$，则$Z$的概率密度为$f_Z(z)=\int_{-\infin}^{+\infin}f(x,z-x)dx$


$\int_0^tf(x)g(t-x)dx$从零时刻到t时刻，f(x)随着时间不断吃饭，并以g(x)在消化，整个积分就是所剩下的体内食物。
一个系统，如果输入不稳定，输出稳定，就用卷积求系统存量

某件事的产生受到之前发生的事的影响，但是会随着距离的变化这种影响会改变。
####图像处理
图片——周围的像素点是如何对当前像素点作出影响的
图片与卷积核——先相乘后相加
$f(x,y)*g(m,n)=\sum f(x,y)g(m-x,n-y)$
卷积实际上在提取某种局部特征，图片中与改卷积核越相似的区域，响应越高。

交叉相关和卷积是对称的
一维：文本、语言、时序序列
二维：
三维：视频、医学图像、气象地图
```py
import torch
from torch import nn
from d2l import torch as d2l

def corr2d(X, K):  #@save
    """计算二维互相关运算"""
    h, w = K.shape#行数和列数
    Y = torch.zeros((X.shape[0] - h + 1, X.shape[1] - w + 1))#输出的高度和宽度
    for i in range(Y.shape[0]):
        for j in range(Y.shape[1]):
            Y[i, j] = (X[i:i + h, j:j + w] * K).sum()#做点积求和
    return Y
#验证
X = torch.tensor([[0.0, 1.0, 2.0], [3.0, 4.0, 5.0], [6.0, 7.0, 8.0]])
K = torch.tensor([[0.0, 1.0], [2.0, 3.0]])
print(corr2d(X, K))

#实现二维卷积层
class Conv2D(nn.Module):
    def __init__(self, kernel_size):
        super().__init__()
        self.weight = nn.Parameter(torch.rand(kernel_size))
        self.bias = nn.Parameter(torch.zeros(1))

    def forward(self, x):
        return corr2d(x, self.weight) + self.bias

#例子
X = torch.ones((6, 8))
X[:, 2:6] = 0
K = torch.tensor([[1.0, -1.0]])#只能检测垂直边缘
Y = corr2d(X, K)
#print(Y)

#学习卷积核

# 构造一个二维卷积层，它具有1个输出通道和形状为（1，2）的卷积核
conv2d = nn.Conv2d(1,1, kernel_size=(1, 2), bias=False)

# 这个二维卷积层使用四维输入和输出格式（批量大小、通道、高度、宽度），
# 其中批量大小和通道数都为1
X = X.reshape((1, 1, 6, 8))
Y = Y.reshape((1, 1, 6, 7))
lr = 3e-2  # 学习率

for i in range(10):
    Y_hat = conv2d(X)
    l = (Y_hat - Y) ** 2
    conv2d.zero_grad()
    l.sum().backward()
    # 迭代卷积核
    conv2d.weight.data[:] -= lr * conv2d.weight.grad
    if (i + 1) % 2 == 0:
        print(f'epoch {i+1}, loss {l.sum():.3f}')

print(conv2d.weight.data.reshape((1, 2)))

```
###填充和步幅
填充padding：在原始矩阵最外层加入一圈零。这样的输出会比原来更大
输出形状：**$(n_h-k_h+p_h+1)*(n_w-k_w+p_w+1)$**
步幅stride：宽度和高度的步幅本应为1，但是可以改为别的值，这样的输出会比原来的小。
输出形状：**$\lfloor(n_h-k_h+p_h+s_h)/s_h\rfloor*\lfloor(n_w-k_w+p_w+s_w)/s_w\rfloor$**




```py
import torch
from torch import nn


# 为了方便起见，我们定义了一个计算卷积层的函数。
# 此函数初始化卷积层权重，并对输入和输出提高和缩减相应的维数
def comp_conv2d(conv2d, X):
    # 这里的（1，1）表示批量大小和通道数都是1
    X = X.reshape((1, 1) + X.shape)
    Y = conv2d(X)
    # 省略前两个维度：批量大小和通道
    return Y.reshape(Y.shape[2:])

# 请注意，这里每边都填充了1行或1列，因此总共添加了2行或2列
conv2d = nn.Conv2d(1, 1, kernel_size=3, padding=1)
X = torch.rand(size=(8, 8))
comp_conv2d(conv2d, X).shape

conv2d = nn.Conv2d(1, 1, kernel_size=(5, 3), padding=(2, 1))
comp_conv2d(conv2d, X).shape

conv2d = nn.Conv2d(1, 1, kernel_size=3, padding=1, stride=2)
comp_conv2d(conv2d, X).shape

conv2d = nn.Conv2d(1, 1, kernel_size=3, padding=1, stride=2)
comp_conv2d(conv2d, X).shape
```
###多输入和输出通道
彩色图像可能会有RGB三个通道
多输入通道：每个通道都有一个卷积核，结果是所有通道卷积结果和
多输出通道：每个通道都有一个三维的卷积核。
1*1卷积层，不识别空间模式，只是融合通道。对不同的通道做加权和

二维卷积层：
- 输入X:$c_i*n_h*n_w$有c~i~个输入
- 核W:$c_o*c_i*k_h*k_w$对于每个c~i~都有c~o~个核
- 偏差B:$c_o*c_i$
- 输出Y:$c_o*m_h*m_w$
```py
import torch
from d2l import torch as d2l

def corr2d_multi_in(X, K):
    # 先遍历“X”和“K”的第0个维度（通道维度），再把它们加在一起
    return sum(d2l.corr2d(x, k) for x, k in zip(X, K))#先对其进行卷积运算，再将两个结果进行相加，输出一个2*2的结果

X = torch.tensor([[[0.0, 1.0, 2.0], [3.0, 4.0, 5.0], [6.0, 7.0, 8.0]],
               [[1.0, 2.0, 3.0], [4.0, 5.0, 6.0], [7.0, 8.0, 9.0]]])#2*3*3两个通道，每个通道是3*3的
K = torch.tensor([[[0.0, 1.0], [2.0, 3.0]], [[1.0, 2.0], [3.0, 4.0]]])#2*2*2两个核，每个核是2*2的

corr2d_multi_in(X, K)

def corr2d_multi_in_out(X, K):
    # 迭代“K”的第0个维度，每次都对输入“X”执行互相关运算。
    # 最后将所有结果都叠加在一起
    return torch.stack([corr2d_multi_in(X, k) for k in K], 0)#对于每个k都进行多输入操作，并将每个结果叠加放置

K = torch.stack((K, K + 1, K + 2), 0)#具有三个输出通道的卷积核
print(K.shape)#torch.Size([3, 2, 2, 2])
a=corr2d_multi_in_out(X, K)
print(a.shape)#torch.Size([3, 2, 2])通道为3，每个通道输出一个2*2的结果


def corr2d_multi_in_out_1x1(X, K):
    c_i, h, w = X.shape#输入通道、高、宽
    c_o = K.shape[0]#输出通道
    X = X.reshape((c_i, h * w))
    K = K.reshape((c_o, c_i))
    # 全连接层中的矩阵乘法
    Y = torch.matmul(K, X)
    return Y.reshape((c_o, h, w))

X = torch.normal(0, 1, (3, 3, 3))#三个输入通道
K = torch.normal(0, 1, (2, 3, 1, 1))#两个输出通道

Y1 = corr2d_multi_in_out_1x1(X, K)
Y2 = corr2d_multi_in_out(X, K)
#两个结果是一致的
assert float(torch.abs(Y1 - Y2).sum()) < 1e-6
#Python assert（断言）用于判断一个表达式，在表达式条件为 false 的时候触发异常。
# print(Y1)
# print(Y2)
```
###池化层
积对位置敏感
二维最大池化：滑动但是取的最大值，不是类似核做计算。
池化层和卷积层比较类似。
```py
import torch
from torch import nn
from d2l import torch as d2l

def pool2d(X, pool_size, mode='max'):
    p_h, p_w = pool_size
    Y = torch.zeros((X.shape[0] - p_h + 1, X.shape[1] - p_w + 1))#输出的大小
    for i in range(Y.shape[0]):
        for j in range(Y.shape[1]):
            if mode == 'max':
                Y[i, j] = X[i: i + p_h, j: j + p_w].max()
            elif mode == 'avg':
                Y[i, j] = X[i: i + p_h, j: j + p_w].mean()
    return Y

X = torch.tensor([[0.0, 1.0, 2.0], [3.0, 4.0, 5.0], [6.0, 7.0, 8.0]])
pool2d(X, (2, 2))
pool2d(X, (2, 2), 'avg')

#填充与步幅
X = torch.arange(16, dtype=torch.float32).reshape((1, 1, 4, 4))
pool2d = nn.MaxPool2d(3)
pool2d(X)
#默认情况下，深度学习框架中的步幅与汇聚窗口的大小相同。
#因此，如果我们使用形状为(3, 3)的汇聚窗口，那么默认情况下，我们得到的步幅形状为(3, 3)。
#填充和步幅可以手动设定。
pool2d = nn.MaxPool2d((2, 3), stride=(2, 3), padding=(0, 1))
pool2d(X)

#多个通道
X = torch.cat((X, X + 1), 1)
#torch.cat是将两个张量(tensor) 拼接在一起
#outputs = torch.cat(inputs, dim=?) → Tensor
#dim : 选择的扩维, 必须在0到len(inputs[0])之间，沿着此维连接张量序列。
pool2d = nn.MaxPool2d(3, padding=1, stride=2)
pool2d(X)
#汇聚层的输出通道数与输入通道数相同。
```
###LeNet
```py
import torch
from torch import nn
from d2l import torch as d2l
import os
os.environ["KMP_DUPLICATE_LIB_OK"]  =  "TRUE"


net = nn.Sequential(
    nn.Conv2d(1, 6, kernel_size=5, padding=2), nn.Sigmoid(),#5*5卷积层，通道数为6
    nn.AvgPool2d(kernel_size=2, stride=2),#平均汇聚层
    nn.Conv2d(6, 16, kernel_size=5), nn.Sigmoid(),#5*5卷积层，通道数为16
    nn.AvgPool2d(kernel_size=2, stride=2),#平均汇聚层
    nn.Flatten(),
    nn.Linear(16 * 5 * 5, 120), nn.Sigmoid(),#全连接
    nn.Linear(120, 84), nn.Sigmoid(),#全连接
    nn.Linear(84, 10))#全连接

X = torch.rand(size=(1, 1, 28, 28), dtype=torch.float32)
for layer in net:
    X = layer(X)
    print(layer.__class__.__name__,'output shape: \t',X.shape)

"""
Conv2d output shape: 	 torch.Size([1, 6, 28, 28])#第一个参数是批量大小
Sigmoid output shape: 	 torch.Size([1, 6, 28, 28])
AvgPool2d output shape: 	 torch.Size([1, 6, 14, 14])
Conv2d output shape: 	 torch.Size([1, 16, 10, 10])
Sigmoid output shape: 	 torch.Size([1, 16, 10, 10])
AvgPool2d output shape: 	 torch.Size([1, 16, 5, 5])
通道数变多，但是每个通道的空间信息变小
Flatten output shape: 	 torch.Size([1, 400])
Linear output shape: 	 torch.Size([1, 120])
Sigmoid output shape: 	 torch.Size([1, 120])
Linear output shape: 	 torch.Size([1, 84])
Sigmoid output shape: 	 torch.Size([1, 84])
Linear output shape: 	 torch.Size([1, 10])
最后通过多层感知机模型训练到最后的输出
"""

#模型训练
batch_size = 256
train_iter, test_iter = d2l.load_data_fashion_mnist(batch_size=batch_size)

def evaluate_accuracy_gpu(net, data_iter, device=None): #@save
    """使用GPU计算模型在数据集上的精度"""
    if isinstance(net, nn.Module):
        net.eval()  # 设置为评估模式
        if not device:
            device = next(iter(net.parameters())).device
    # 正确预测的数量，总预测的数量
    metric = d2l.Accumulator(2)
    with torch.no_grad():
        for X, y in data_iter:
            if isinstance(X, list):
                # BERT微调所需的（之后将介绍）
                X = [x.to(device) for x in X]
            else:
                X = X.to(device)
            y = y.to(device)
            metric.add(d2l.accuracy(net(X), y), y.numel())
    return metric[0] / metric[1]

#@save
def train_ch6(net, train_iter, test_iter, num_epochs, lr, device):#只是多了一个device
    """用GPU训练模型(在第六章定义)"""
    def init_weights(m):
        if type(m) == nn.Linear or type(m) == nn.Conv2d:
            nn.init.xavier_uniform_(m.weight)
    net.apply(init_weights)
    print('training on', device)
    net.to(device)
    optimizer = torch.optim.SGD(net.parameters(), lr=lr)
    loss = nn.CrossEntropyLoss()
    animator = d2l.Animator(xlabel='epoch', xlim=[1, num_epochs],
                            legend=['train loss', 'train acc', 'test acc'])#可视化
    timer, num_batches = d2l.Timer(), len(train_iter)
    for epoch in range(num_epochs):
        # 训练损失之和，训练准确率之和，样本数
        metric = d2l.Accumulator(3)
        net.train()
        for i, (X, y) in enumerate(train_iter):
            timer.start()
            optimizer.zero_grad()
            X, y = X.to(device), y.to(device)
            y_hat = net(X)
            l = loss(y_hat, y)
            l.backward()
            optimizer.step()
            with torch.no_grad():
                metric.add(l * X.shape[0], d2l.accuracy(y_hat, y), X.shape[0])
            timer.stop()
            train_l = metric[0] / metric[2]
            train_acc = metric[1] / metric[2]
            if (i + 1) % (num_batches // 5) == 0 or i == num_batches - 1:
                animator.add(epoch + (i + 1) / num_batches,
                             (train_l, train_acc, None))
        test_acc = evaluate_accuracy_gpu(net, test_iter)
        animator.add(epoch + 1, (None, None, test_acc))
    print(f'loss {train_l:.3f}, train acc {train_acc:.3f}, '
          f'test acc {test_acc:.3f}')
    print(f'{metric[2] * num_epochs / timer.sum():.1f} examples/sec '
          f'on {str(device)}')

lr, num_epochs = 0.9, 10
train_ch6(net, train_iter, test_iter, num_epochs, lr, d2l.try_gpu())
```

