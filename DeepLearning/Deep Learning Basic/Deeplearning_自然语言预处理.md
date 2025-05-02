#自然语言预处理
##词嵌入

独热向量不能编码词之间的相似性。

###跳元模型（Skip-Gram）
**根据中心词生成上下文词**
分别用$\mathbf{v}_i\in\mathbb{R}^d$和$\mathbf{u}_i\in\mathbb{R}^d$表示其用作中心词v和上下文词u时的两个向量.给定中心词w~c~（词典中的索引c），生成任何上下文词w~o~（词典中的索引o）的条件概率可以通过对向量点积的softmax操作来建模：
$P(w_o \mid w_c) = \frac{\text{exp}(\mathbf{u}_o^\top \mathbf{v}_c)}{ \sum_{i \in \mathcal{V}} \text{exp}(\mathbf{u}_i^\top \mathbf{v}_c)},$

###连续词袋（CBOW）模型
**基于其在文本序列中的周围上下文词生成中心词**


