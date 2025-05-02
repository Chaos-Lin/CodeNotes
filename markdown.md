```py
import os
os.environ["KMP_DUPLICATE_LIB_OK"]  =  "TRUE"
d2l.plt.show()
```

#一级标题
##二级标题
###三级标题etc
>这是一段引用

有序列表：
1. 一
2. 二

无序列表：
- 短横
- 短横

* 星号也可以

待做事项：
- [ ] eat
- [ ] sleep
- [X] check

粗体：
I just love **bold text**.
I just love __bold text__.

斜体：
Italicized text is the *cat's meow*.
Italicized text is the _cat's meow_.

斜粗体
This text is ***really important***.

分隔符：请在单独一行上使用三个或多个星号 (***)、破折号 (---) 或下划线 (___) ，并且不能包含其他内容。
***

这是一个链接 [Markdown语法](https://markdown.com.cn)。

<https://markdown.com.cn>
尖括号也可以

强调 链接, 在链接语法前后增加星号。 要将链接表示为代码，请在方括号中添加反引号。

I love supporting the **[EFF](https://eff.org)**.
This is the *[Markdown Guide](https://www.markdownguide.org)*.
See the section on [`code`](#code).

***
[hobbit-hole][1]


[1]: https://en.wikipedia.org/wiki/Hobbit#Lifestyle
***
代码块：
```c
int main(){
    return 0;
}
```

数学公式：
$$
\frac{\partial f}{\partial x} = 2\sqrt{a}x
$$

表格：
 |姓名|年龄|成绩|
 |:--|--:|:--:|
 |左对齐|右对齐|居中对其|


图片：
![图片alt](图片链接 "图片title")

转义字符：
    \*

<u>underline</u>
==highlight==
:smile:
$\theta=x^2$
H~2~O
x^2^

<font size=1>字体大小size=1</font>
<font size=3>字体大小size=3</font>
<font size=5>字体大小size=5</font>

<font color=red>红色</font>
<font color="blue">蓝色</font>
<font color=Yellow>黄色</font>
<font color=YellowGreen>黄绿色</font>

<font color=#ff0000>红色</font>
<font color=#00ff00>绿色</font>
<font color=#0000ff>蓝色</font>

<font face="黑体">黑体</font>
<font face="宋体">宋体</font>
<font face="仿宋">仿宋</font>
<font face="幼圆">幼圆</font>
<font face="楷书">楷书</font>
<font face="华文行楷">华文行楷</font>
<font face="华文隶书">华文隶书</font>
<font face="华文新魏">华文新魏</font>
<font face="华文彩云">华文彩云</font>
<font face="华文琥珀">华文琥珀</font>

$\lceil x \rceil$
$\lfloor x \rfloor$

$\vec{a}$  向量
$\overline{a}$ 平均值
$\widehat{a}$ (线性回归，直线方程) y尖
$\widetilde{a}$ 颚化符号  等价无穷小
$\dot{a}$   一阶导数
$\ddot{a}$  二阶导数
