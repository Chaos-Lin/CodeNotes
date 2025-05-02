#深度学习
##逻辑回归
通过X得到预测Y
z=w^T^X+b
y=$\sigma$(z)

对于其中的参数w和b而言，利用loss function和cost function，对其进行梯度下降

$L(y'^i,y^i)=-(ylogy'+(1-y)log(1-y'))$
我们通常希望$L(y'^i,y^i)$尽可能地小
当y=1时，$L(y'^i,y^i)=-logy'$，即希望logy‘更大，即y'更大
当y=0时，$L(y'^i,y^i)=-log(1-y')$即希望-log(1-y')更大，即y'更小

$J(w,b)=\frac{1}{m}\sum_{i=0}^mL(y'^i,y^i)$

利用J(w,b)分别对w和b做偏导
$w=w-\alpha\frac{\partial J(w,b)}{\partial w}$

$b=b-\alpha\frac{\partial J(w,b)}{\partial b}$

```

```
##神经网络
需要用初始化随机不同的W，不然每个隐藏层计算值都是一样的。

####Forward propagation for layer $l$
input a^[l-1]^
output a^[l]^,cache(z^[l]^)

####Backward propagation for layer $l$
input da^[l]^
output da^[l-1]^,dW^[l]^,db^[l]^

推导过程
>$z=w^TX+b$
$a=\sigma(z)=\frac{1}{1+e^-z}$
$L(a,y)=-(yloga+(1-y)log(1-a))$
$da=\frac{d}{da}L(a,y)=-\frac{y}{a}+\frac{1-y}{1-a}$
烦了不想推了

####深度学习的直观
audio$\rightarrow$low level waveform features$\rightarrow$phonemes$\rightarrow$words$\rightarrow$sentence

####Forward and backward fuctions
从a^[0]^出发，经过sigma公式得到a^[1]^,一层一层往前计算。
然后从da^[l]^,经过反向传播得到dw^[l]^和db^[l]^,每一层都利用梯度下降公式计算w^[l]^和b^[l]^一层一层往后计算。完成一个梯度下降循环

####hyperparameters
需要自己设置的参数，来决定wb这两个参数
- learning rate $\alpha$
- hidden layers $L$
- hidden units $n$
- choice of activation fuction $tanh $  $\sigma$

##实操

####Train/dev/test sets

#####data:
- **training set**
- **development set** or **hold-out cross validation - set**简单交叉验证集
- **test**

要确保验证集和测试集来源于统一数据集

####bias and variance偏差与方差

术语：
- high bias —— underfitting欠拟合
- high variance ——overfiting过拟合
- just right

通过训练集和测试集的错误律来判断偏差和方差的大小

####basic recipe for machine learning

high bias(training data problem)$\rightarrow$**bigger network** or **train longer**
$\rightarrow$ not high bias$\rightarrow$
high varaince(drv data problrm)$\rightarrow$**more data** or **regularzation**
$\rightarrow$done


####regularzation
L2正则化：
>正则化，在J函数后面加上这样一个式子：$\frac{\lambda}{2m}|w|^2$~2~
向量参数w的欧几里得范数平方：$w^2$~2~=$\sum_{j=1}^n{w^2}$~j~=$w^Tw$

$\lambda$是正则参数——lambda是python的关键字，所以用lambd表示。

Frobenius norm 佛罗贝尼乌斯范数

没学懂！！！！

####正则化为什么能阻止过拟合
当$\lambda$足够大的时候，w会接近于0
试图消除或减少许多隐藏单元的影响，当w接近0时，z也接近于0，那么activation function的斜率相对于大，接近线性回归函数。

####dropout
会消除一些神经网络中的节点——玄学
#####反向随机失活inverted dropout
完了 已经看不懂了

1. 采用一个较小神经网络好像和使用正则化的效果是一样的
2. Cant rely on any one feature, so have to spread out weights.
如果但内心某些曾过拟合，可以把某些层的**keep-prob**值设置得比其他层更低

dropout得一大缺点是使得J函数不再定义明确（单调）

####其他正则化方法
#####数据增扩
在训练集中加入：翻转图，随意裁剪图。如果是数字还可以强变形。

#####early stopping
迭代到足够得时候停止训练。
缺点在于可能偏差和方差都过高。

####normalizing training sets
1. subtract out 零均值化，移动训练集的点，使得点的均值为零
>$x=x-$$\mu$

2. normalize variance 使得不同维度的方差基本一致
>$x=x/$$\delta$^2^

如果不归一化，那么会导致代价函数不太平均化

####vanishing/explodinng gradients
####深度学习神经单元权重初始化
