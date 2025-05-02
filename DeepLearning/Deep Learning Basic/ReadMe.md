

##Run
```py


```
##Load_data
```py
batch_size = 
train_iter, test_iter = 
```


##Model
```py
def get_params(size,device):

    def normal(shape):
        # 初始化参数函数
        return np.random.normal(scale=0.01, size=shape, ctx=device)
    
    def #可以套娃定义

    #然后就可以按层次初始化参数了
    W_xz = 
    # 附加梯度
    params = [W_xz, W_hz, b_z, W_xr, W_hr, b_r, W_xh, W_hh, b_h, W_hq, b_q]
    for param in params:
        param.attach_grad()


    return params
```
###定义模型
```py
def model(inputs, params):
    参数 = params
    outputs = []
    for X in inputs：

        Y = 
        outputs.append(Y)

    return 
```


#####



##Train
```py
num_epochs #训练次数
for epoch in range(num_epochs):
    for X, y in data_iter(batch_size, features, lables):



```

```py
net.apply(初始化函数)
```

####模型
```py
import torch
from torch import nn

net = nn.Sequential(
    layers, 
    layers,

)
net(X)
```
>参考深度学习计算


#####查看层级
```py
X = torch.randn(1, 1, 224, 224)
# 给个输入
for layer in net:
    X=layer(X)
    print(layer.__class__.__name__,'output shape:\t',X.shape)
```




