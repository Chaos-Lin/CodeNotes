##循环神经网络
```py
d2l.plot.show()
```
###序列模型
在时间t观察到$x_t$，那么得到T个不独立的随机变量。
$(x_{1}, \ldots, x_T)\sim p(x)$
$x_t \sim P(x_t \mid x_{t-1}, \ldots, x_1).$

$p(X)=p(x_1)p(x_2\mid x_1)p(x_3 \mid x_1,x_2)p(x_T \mid x_1, \dots x_{T-1})$

关键在于：$p(x_T \mid x_1, \dots x_{T-1})$
对其建模：$=p(x_T \mid f(x_1, \dots x_{T-1}))$
对见过的数据回归和建模
####马尔可夫模型
我们使用$x_{t-1}, \ldots, x_{t-\tau}$而不是$x_{t-1}, \ldots, x_1$来估计$x_t$。
即，假设当前数据只跟$\tau$个过去数据点相关 
$p(x_T \mid x_1, \dots x_{T-1})$
$=p(x_T \mid x_{t-\tau}, \dots x_{T-1})$
$=p(x_T \mid f(x_{t-\tau}, \dots x_{T-1}))$

####潜变量模型
引入潜变量$h_t$来表示过去信息$h_t=f(x_1 \dots x_{t-1})$
不断地更新$h_t$,目前的$x_t$只和之前的$x_{t-1}$以及当前的$h_t$相关

###文本预处理

```py
import collections
import re
from d2l import torch as d2l

#读取数据集
#@save
d2l.DATA_HUB['time_machine'] = (d2l.DATA_URL + 'timemachine.txt',
                                '090b5e7e70c295757f55df93cb0a180b9691891a')

def read_time_machine():  #@save
    """将时间机器数据集加载到文本行的列表中"""
    with open(d2l.download('time_machine'), 'r') as f:
        lines = f.readlines()
    return [re.sub('[^A-Za-z]+', ' ', line).strip().lower() for line in lines]#非常暴力的预处理，只有小写的26个字母加空格

lines = read_time_machine()
# print(f'# 文本总行数: {len(lines)}')
# print(lines[100])
# print(lines[101])

#词元化
def tokenize(lines, token='word'):  #@save
    """将文本行拆分为单词或字符词元"""
    if token == 'word':
        return [line.split() for line in lines]
    elif token == 'char':
        return [list(line) for line in lines]
    else:
        print('错误：未知词元类型：' + token)

tokens = tokenize(lines)
# for i in range(11):
#     print(tokens[i+100])
#
# 每一行都有很多token在内


# 词表vocabulary
# 根据单词在给的文本中出现的频率来构造字典
class Vocab:  #@save
    """文本词表"""
    #词汇tokens映射到数字索引
    def __init__(self, tokens=None, min_freq=0, reserved_tokens=None):
        """

        :param tokens:
        :param min_freq: 不去训练出现频率较低的token
        :param reserved_tokens: 开头结尾的特殊tokens
        """
        if tokens is None:
            tokens = []
        if reserved_tokens is None:
            reserved_tokens = []
        # 按出现频率排序
        counter = count_corpus(tokens)#这已经是一个tokens的列表了
        self._token_freqs = sorted(counter.items(), key=lambda x: x[1],
                                   reverse=True)
        # 按照出现的频率进行排序
        # items是将列表字典化的操作
        # 未知词元的索引为0
        self.idx_to_token = ['<unk>'] + reserved_tokens
        self.token_to_idx = {token: idx
                             for idx, token in enumerate(self.idx_to_token)}
        for token, freq in self._token_freqs:
            if freq < min_freq:
                break
                # 次数少于要求的就舍去
            if token not in self.token_to_idx:
                self.idx_to_token.append(token)
                self.token_to_idx[token] = len(self.idx_to_token) - 1

    def __len__(self):# 返回个数
        return len(self.idx_to_token)

    def __getitem__(self, tokens):# 返回index
        if not isinstance(tokens, (list, tuple)):
            return self.token_to_idx.get(tokens, self.unk)
        return [self.__getitem__(token) for token in tokens]

    def to_tokens(self, indices):#返回token
        if not isinstance(indices, (list, tuple)):
            return self.idx_to_token[indices]
        return [self.idx_to_token[index] for index in indices]

    @property
    def unk(self):  # 未知词元的索引为0
        return 0

    @property
    def token_freqs(self):
        return self._token_freqs

def count_corpus(tokens):  #@save
    """统计词元的频率"""
    # 这里的tokens是1D列表或2D列表
    if len(tokens) == 0 or isinstance(tokens[0], list):
        # 将词元列表展平成一个列表
        tokens = [token for line in tokens for token in line]
    return collections.Counter(tokens)

vocab = Vocab(tokens)
# tokens里面每个元素是一行
# 每一行里是一个list of token

# print(list(vocab.token_to_idx.items())[100:110])

# for i in range(10):
#     print('文本:', tokens[i])
#     print('索引:', vocab[tokens[i]])
# 输入token给出对应的下标
# 将每一条文本行转换成一个数字索引列表

def load_corpus_time_machine(max_tokens=-1):  #@save
    """返回时光机器数据集的词元索引列表和词表"""
    lines = read_time_machine()
    # 文本读入
    tokens = tokenize(lines, 'word')
    # 词元化
    vocab = Vocab(tokens)
    # 构造字典
    # 因为时光机器数据集中的每个文本行不一定是一个句子或一个段落，
    # 所以将所有文本行展平到一个列表中
    corpus = [vocab[token] for line in tokens for token in line]
    # 将文本转换成对应的下标
    if max_tokens > 0:
        corpus = corpus[:max_tokens]
    return corpus, vocab

corpus, vocab = load_corpus_time_machine()
print(len(corpus), len(vocab))


```
###语言模型和数据集
####使用计数来建模
$p(x,x')=p(x)p(x'\mid x)=\frac{n(x)}{n} \frac{n(x,x')}{n(x)}$n是总次数，$n(x),n(x,x')$是单个单词和连读单词对的出现次数。贝叶斯公式。

####马尔可夫模型
一元语法（unigram）：
$P(x_1, x_2, x_3, x_4) =  P(x_1) P(x_2) P(x_3) P(x_4),$
二元语法（bigram）：
$P(x_1, x_2, x_3, x_4) =  P(x_1) P(x_2  \mid  x_1) P(x_3  \mid  x_2) P(x_4  \mid  x_3),$
三元语法（trigram）：
$P(x_1, x_2, x_3, x_4) =  P(x_1) P(x_2  \mid  x_1) P(x_3  \mid  x_1, x_2) P(x_4  \mid  x_2, x_3).
$
###循环神经网络RNN
$P(x_t \mid x_{t-1}, \ldots, x_1) \approx P(x_t \mid h_{t-1}),$
$h_t = f(x_{t}, h_{t-1}).$

MLP:
$\mathbf{H} = \phi(\mathbf{X} \mathbf{W}_{xh} + \mathbf{b}_h).$
$\mathbf{O} = \mathbf{H} \mathbf{W}_{hq} + \mathbf{b}_q,$

RNN:
$\mathbf{H}_t = \phi(\mathbf{X}_t \mathbf{W}_{xh} + \mathbf{H}_{t-1} \mathbf{W}_{hh}  + \mathbf{b}_h).$
$\mathbf{O}_t = \mathbf{H}_t \mathbf{W}_{hq} + \mathbf{b}_q.$

$\mathbf{H}_{t-1} \mathbf{W}_{hh}$

你的$o_t$是用于match $x_t$的，用这来计算loss function

####困惑度（Perplexity）
其实语言预测也可以视为一种分类问题
平均交叉熵：$\pi=\frac{1}{n} \sum_{t=1}^n -\log P(x_t \mid x_{t-1}, \ldots, x_1),$

困惑度：$exp(\pi)=\exp\left(-\frac{1}{n} \sum_{t=1}^n \log P(x_t \mid x_{t-1}, \ldots, x_1)\right).$

1表示完美，无穷大是最差情况

####梯度裁剪
防止梯度爆炸
$g\leftarrow min (1,\frac{\theta}{||g||})g$
如果梯度长度超过$\theta$，那么拖影射回长度$\theta$
####通过时间反向传播
####代码
```py
import math
import torch
from torch import nn
from torch.nn import functional as F
from d2l import torch as d2l
import os
os.environ["KMP_DUPLICATE_LIB_OK"]  =  "TRUE"

batch_size, num_steps = 32, 35
# 批量大小 时间维度的t（也就是一个序列有多长）
train_iter, vocab = d2l.load_data_time_machine(batch_size, num_steps)

# print(F.one_hot(torch.tensor([0, 2]), len(vocab)))
# 第零位和第二位是1，其向量长度为28（len of vocab）

X = torch.arange(10).reshape((2, 5))
# batch_size, num_steps = 2, 5

# print(F.one_hot(X.T, 28).shape)

# torch.Size([5, 2, 28])
# 每个批量的两个词是连在一起的

# 初始化参数
def get_params(vocab_size, num_hiddens, device):
    num_inputs = num_outputs = vocab_size
    # 输入输出都等于vocab_size,因为都是向量
    # 隐藏单元数num_hiddens是一个可调的超参数。

    def normal(shape):
        return torch.randn(size=shape, device=device) * 0.01

    # 隐藏层参数
    W_xh = normal((num_inputs, num_hiddens))
    W_hh = normal((num_hiddens, num_hiddens))
    b_h = torch.zeros(num_hiddens, device=device)
    # 输出层参数
    W_hq = normal((num_hiddens, num_outputs))
    b_q = torch.zeros(num_outputs, device=device)
    # 附加梯度
    params = [W_xh, W_hh, b_h, W_hq, b_q]
    for param in params:
        param.requires_grad_(True)
    return params

# 初始化隐藏状态
def init_rnn_state(batch_size, num_hiddens, device):
    return (torch.zeros((batch_size, num_hiddens), device=device), )

# 计算
def rnn(inputs, state, params):
    # inputs的形状：(时间步数量，批量大小，词表大小)
    # state 初始隐藏状态
    # params 可学习参数
    W_xh, W_hh, b_h, W_hq, b_q = params
    H, = state
    outputs = []
    # X的形状：(批量大小，词表大小)
    # 按照时间维度进行迭代
    for X in inputs:
        H = torch.tanh(torch.mm(X, W_xh) #输入的
                       + torch.mm(H, W_hh) #前一个时间的隐藏状态
                       + b_h)
        # 当前时刻的隐藏状态，与MLP不同的地方
        Y = torch.mm(H, W_hq) + b_q
        # 当前时刻的预测
        outputs.append(Y)
        # 把所有输出都存在这里
        # Y的形状（批量大小，vocab_size）
        # 循环一次是将输出映射到隐藏层大小，又从隐藏层映射回输出大小
    return torch.cat(outputs, dim=0), (H,)
        # 按照零维拼接，所以列数不变
        # outputs的形状是（批量大小*时间长度，vocab_size）

# 创造一个类来包装函数
class RNNModelScratch: #@save
    """从零开始实现的循环神经网络模型"""
    def __init__(self, vocab_size, num_hiddens, device,
                 get_params, init_state, forward_fn):
        self.vocab_size, self.num_hiddens = vocab_size, num_hiddens
        self.params = get_params(vocab_size, num_hiddens, device)
        self.init_state, self.forward_fn = init_state, forward_fn
        #存初始状态、RNN函数

    def __call__(self, X, state):
        X = F.one_hot(X.T, self.vocab_size).type(torch.float32)
        return self.forward_fn(X, state, self.params)

    # __call__()方法的作用其实是把一个类的实例化对象变成了可调用对象
    # 就是说类对象可以作为函数来用

    def begin_state(self, batch_size, device):
        return self.init_state(batch_size, self.num_hiddens, device)

# 检查形状
num_hiddens = 512
net = RNNModelScratch(len(vocab),
                      num_hiddens,
                      d2l.try_gpu(),
                      get_params,
                      init_rnn_state,
                      rnn)
state = net.begin_state(X.shape[0], d2l.try_gpu())
Y, new_state = net(X.to(d2l.try_gpu()), state)
# print(Y.shape, len(new_state), new_state[0].shape)

# X是（2，5）两个序列，一个序列5个词
# Y是([10==2*5一共有10个词, 28])
# 新的隐藏状是([2, 512])两个序列，每个序列都有512个隐藏状态


#预测函数
def predict_ch8(prefix, num_preds, net, vocab, device):  #@save
    '''

    :param prefix: 字符
    :param num_preds: 预测词数
    :param net: 训练好的模型
    :param vocab: 映射回真实的字符
    :param device:
    :return:
    '''
    """在prefix后面生成新字符"""
    state = net.begin_state(batch_size=1, device=device)
    # 初始化隐藏状态
    outputs = [vocab[prefix[0]]]
    # 拿到整型下标
    get_input = lambda: torch.tensor([outputs[-1]], device=device).reshape((1, 1))
    # 最近预测的值作为输入
    for y in prefix[1:]:  # 预热期
        _, state = net(get_input(), state)
        outputs.append(vocab[y])
    # 已经有的词，就不在意输出了，用的是真实的值。只是用于隐藏层的初始化
    for _ in range(num_preds):  # 预测num_preds步
        y, state = net(get_input(), state)
        # 把前一时刻的输入放进net 进行预测
        outputs.append(int(y.argmax(dim=1).reshape(1)))
        # 分类问题，只要取出最大的那个位置转化为整型存进去就可以了
        # 也就是把独热编码转换为index下标
    return ''.join([vocab.idx_to_token[i] for i in outputs])
    # 最后再把index转换成字符

# print(predict_ch8('time traveller ', 10, net, vocab, d2l.try_gpu()))
# 用于测试


# 梯度剪裁
def grad_clipping(net, theta):  #@save
    """裁剪梯度"""
    if isinstance(net, nn.Module):
        params = [p for p in net.parameters() if p.requires_grad]
    else:
        params = net.params
    norm = torch.sqrt(sum(torch.sum((p.grad ** 2)) for p in params))
    if norm > theta:
        for param in params:
            param.grad[:] *= theta / norm

# 训练
#@save
def train_epoch_ch8(net, train_iter, loss, updater, device,
                    use_random_iter):# 可以导致隐藏层处理不一样，true会有更高的随机性
    """训练网络一个迭代周期（定义见第8章）"""
    state, timer = None, d2l.Timer()
    metric = d2l.Accumulator(2)  # 训练损失之和,词元数量
    for X, Y in train_iter:
        if state is None or use_random_iter:
            # use_random_iter批量之间是否有联系
            # 在第一次迭代或使用随机抽样时初始化state
            state = net.begin_state(batch_size=X.shape[0], device=device)
        else:
            if isinstance(net, nn.Module) and not isinstance(state, tuple):
                # state对于nn.GRU是个张量
                state.detach_()
            else:
                # state对于nn.LSTM或对于我们从零开始实现的模型是个张量
                for s in state:
                    s.detach_()
        y = Y.T.reshape(-1)
        X, y = X.to(device), y.to(device)
        y_hat, state = net(X, state)
        l = loss(y_hat, y.long()).mean()
        # 其实还是一个多分类问题，只是现在的批量大小变成了 批量大小*时间长度
        if isinstance(updater, torch.optim.Optimizer):
            updater.zero_grad()
            l.backward()
            grad_clipping(net, 1)
            updater.step()
        else:
            l.backward()
            grad_clipping(net, 1)
            # 因为已经调用了mean函数
            updater(batch_size=1)
        metric.add(l * y.numel(), y.numel())
    return math.exp(metric[0] / metric[1]), metric[1] / timer.stop()

# 所有循环神经网络的训练
#@save
def train_ch8(net, train_iter, vocab, lr, num_epochs, device,
              use_random_iter=False):
    """训练模型（定义见第8章）"""
    loss = nn.CrossEntropyLoss()
    animator = d2l.Animator(xlabel='epoch', ylabel='perplexity',
                            legend=['train'], xlim=[10, num_epochs])
    # 初始化
    if isinstance(net, nn.Module):
        updater = torch.optim.SGD(net.parameters(), lr)
    else:
        updater = lambda batch_size: d2l.sgd(net.params, lr, batch_size)
    predict = lambda prefix: predict_ch8(prefix, 50, net, vocab, device)
    # 训练和预测
    for epoch in range(num_epochs):
        ppl, speed = train_epoch_ch8(
            net, train_iter, loss, updater, device, use_random_iter)
        if (epoch + 1) % 10 == 0:
            print(predict('time traveller'))
            animator.add(epoch + 1, [ppl])
    print(f'困惑度 {ppl:.1f}, {speed:.1f} 词元/秒 {str(device)}')
    print(predict('time traveller'))
    print(predict('traveller'))

num_epochs, lr = 500, 1
train_ch8(net, train_iter, vocab, lr, num_epochs, d2l.try_gpu())
d2l.plt.show()


```
####简洁实现
```py
import torch
from torch import nn
from torch.nn import functional as F
from d2l import torch as d2l
import os
os.environ["KMP_DUPLICATE_LIB_OK"]  =  "TRUE"

batch_size, num_steps = 32, 35
train_iter, vocab = d2l.load_data_time_machine(batch_size, num_steps)

num_hiddens = 256
rnn_layer = nn.RNN(len(vocab), num_hiddens)
# 输入输出就可以直接作为layer了

state = torch.zeros((1, batch_size, num_hiddens))
# print(state.shape)

X = torch.rand(size=(num_steps, batch_size, len(vocab)))
Y, state_new = rnn_layer(X, state)
# print(Y.shape, state_new.shape)

#@save
class RNNModel(nn.Module):
    """循环神经网络模型"""
    def __init__(self, rnn_layer, vocab_size, **kwargs):
        super(RNNModel, self).__init__(**kwargs)
        self.rnn = rnn_layer
        self.vocab_size = vocab_size
        self.num_hiddens = self.rnn.hidden_size
        # 如果RNN是双向的（之后将介绍），num_directions应该是2，否则应该是1
        if not self.rnn.bidirectional:
            self.num_directions = 1
            self.linear = nn.Linear(self.num_hiddens, self.vocab_size)
        else:
            self.num_directions = 2
            self.linear = nn.Linear(self.num_hiddens * 2, self.vocab_size)

    def forward(self, inputs, state):
        X = F.one_hot(inputs.T.long(), self.vocab_size)
        X = X.to(torch.float32)
        Y, state = self.rnn(X, state)
        # 全连接层首先将Y的形状改为(时间步数*批量大小,隐藏单元数)
        # 它的输出形状是(时间步数*批量大小,词表大小)。
        output = self.linear(Y.reshape((-1, Y.shape[-1])))
        return output, state

    def begin_state(self, device, batch_size=1):
        if not isinstance(self.rnn, nn.LSTM):
            # nn.GRU以张量作为隐状态
            return  torch.zeros((self.num_directions * self.rnn.num_layers,
                                 batch_size, self.num_hiddens),
                                device=device)
        else:
            # nn.LSTM以元组作为隐状态
            return (torch.zeros((
                self.num_directions * self.rnn.num_layers,
                batch_size, self.num_hiddens), device=device),
                    torch.zeros((
                        self.num_directions * self.rnn.num_layers,
                        batch_size, self.num_hiddens), device=device))

device = d2l.try_gpu()
net = RNNModel(rnn_layer, vocab_size=len(vocab))
net = net.to(device)
print(d2l.predict_ch8('time traveller', 10, net, vocab, device))
num_epochs, lr = 500, 1
d2l.train_ch8(net, train_iter, vocab, lr, num_epochs, device)

```