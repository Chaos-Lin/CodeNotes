# python
## 变量
### 类型
```type()```查看数据类型
#### 类型转换
```int(x)```
```float(x)```
```Str(x)``` 
要将字符串转换成数字，必须要求字符串内的内容都是数字
### str
定义
```py
'str'
"str"
"""str"""
```
输出
```py
str = 'Hello World!'

print(str)  
# 输出完整字符串Hello World!
print(str[0] )
# 输出字符串中的第一个字符H
print(str[2:5])
# 输出字符串中第三个至第六个之间的字符串llo
print(str[2:])
# 输出从第三个字符开始的字符串llo World!
print(str * 2)
# 输出字符串两次Hello World!Hello World!
print(str + "TEST")
# 输出连接的字符串Hello World!TEST
```

### List列表
##### 开头，结尾，步数
默认左闭右开
```py
list = []
a='python'
b=a[::-1]
print(b) #nohtyp
c=a[::-2]
print(c) #nhy
#从后往前数的话，最后一个位置为-1
d=a[:-1]  #从位置0到位置-1之前的数
print(d)  #pytho
e=a[:-2]  #从位置0到位置-2之前的数
print(e)  #pyth

b = a[i:j]   # 表示复制a[i]到a[j-1]，以生成新的list对象

a = [0,1,2,3,4,5,6,7,8,9]
b = a[1:3]   # [1,2]

# 当i缺省时，默认为0，即 a[:3]相当于 a[0:3]
# 当j缺省时，默认为len(alist), 即a[1:]相当于a[1:10]
# 当i,j都缺省时，a[:]就相当于完整复制一份a

b = a[i:j:s]    # 表示：i,j与上面的一样，但s表示步进，缺省为1.
# 所以a[i:j:1]相当于a[i:j]

# 当s<0时，i缺省时，默认为-1. j缺省时，默认为-len(a)-1
# 所以a[::-1]相当于 a[-1:-len(a)-1:-1]，也就是从最后一个元素到第一个元素复制一遍，即倒序。
```

##### append
使用 append() 方法，可以方便地将新的结果添加到列表中
```py
model_results.append(result)
```
### tuple元组
元组不能二次赋值，相当于只读列表
```py
tuple = ( 'runoob', 786 , 2.23, 'john', 70.2 )
```
其他和list没差

### dictionary字典
```py
dict = {}
dict['one'] = "This is one"
dict[2] = "This is two"
tinydict = {'name': 'runoob','code':6734, 'dept': 'sales'}
print(dict['one'])# 输出键为'one' 的值
print(dict[2] )# 输出键为 2 的值
print(tinydict)# 输出完整的字典
print(tinydict.keys())# 输出所有键
print(tinydict.values())# 输出所有值
```
##### updata
update() 方法会将作为参数传递的字典的键值对添加到调用该方法的字典中。如果两个字典存在相同的键，那么传递的字典中的键值对会覆盖调用方法的字典中的对应键的值。
```py
dict1.update(dict2)
```
##### easydict
使得字典的值可以像属性一样被调用
```py
from easydict import EasyDict as edict
dict = edict(dict)
```

### f
```py
f"{变量}文字"
```
## 函数
### 函数定义
```py
def f(text:str,max_len:'int>0'=80) ->str:
    # text的类型为str
    # max_len的类型是'int>0'
    # 默认值为80
    # ->str是返回值的类型
```
### pass
1. 占位符：当你暂时不想编写某个代码块的实现时，可以使用pass作为占位符，以便保持语法正确性。这在编写函数、类、循环或条件语句时很常见。
```py
def some_function():
    pass  # 占位符，待实现函数体

if condition:
    pass  # 占位符，待编写条件满足时的逻辑
```
2. 空循环体：有时你可能需要创建一个空的循环体，这种情况下可以使用pass语句。

```py
for item in some_list:
    pass  # 空循环体，不执行任何操作
```

3. 类的占位符：当你定义一个类时，如果没有需要添加的成员或方法，可以使用pass语句作为占位符。


```py
class MyClass:
    pass  # 占位符，待添加类成员或方法
```

使用pass语句可以帮助你避免语法错误，并在需要时提供可扩展性。它允许你保留一个空的代码块，以后再填充具体实现。


## class
### 父类super
```py
class ParentClass:
    def some_method(self):
        # 父类的方法逻辑
        pass

class ChildClass(ParentClass):
    def some_method(self):
        # 扩展父类方法的逻辑
        super().some_method()  # 调用父类的方法
        # 子类的方法逻辑
        pass
```
在Python 3中，可以使用更简洁的方式调用父类构造函数，如super().__init__()。而在Python 2中，则需要显式地传递当前类和实例作为参数，如super(MultiHeadedAttention, self).__init__()所示

### def__ABC__
以下是一些常见的双下划线方法的示例：

```__init__```: 这是类的构造方法，在创建类的新实例时被调用。它用于初始化对象的状态。

```__str__```: 这个方法返回一个对象的字符串表示形式，通常用于打印对象或在字符串上下文中使用对象。

```__len__```: 这个方法返回对象的长度。它通常与内置函数len()一起使用，用于确定对象的大小或元素个数。

```__getitem__```和```__setitem__```: 这些方法用于索引和赋值对象的元素。__getitem__允许通过索引访问对象的元素，__setitem__允许通过索引设置对象的元素。

```__iter__```和```__next__```: 这些方法实现了迭代器协议，允许对象在迭代时按照自定义的方式生成值。

__call__: 这个方法使得一个对象可以像函数一样被调用。当调用对象时，__call__方法会被触发。

### 循环
#### enumerate
```py
seeds=['a','b','c','d']
for i, seed in enumerate(seeds):
    print(i)
    print(seed)
```
结果：
```py
0 a
1 b
2 c
3 d
```

#### name
因此```__name__ == '__main__' ```就表示在当前文件中，
可以在```if __name__ == '__main__':```条件下写入测试代码，如此可以避免测试代码在模块被导入后执行。




## 文件

### path
```py
from pathlib import Path

```
#### with

## 异常
#### try
#### raise
```py
if not config_file.is_file():
    raise FileNotFoundError(errno.ENOENT, os.strerror(errno.ENOENT), config_file)
```

#### assert
如果assert条件为真，代码继续执行。如果assert条件为假，则会引发 AssertionError 异常。
```py
assert(条件)
```
## 模块
### from import

### dir()
dir() 函数一个排好序的字符串列表，内容是一个模块里定义过的名字
```py
import math
content = dir(math)
print content;
```
### globals() 和 locals() 
根据调用地方的不同，```globals()``` 和 ```locals()``` 函数可被用来返回全局和局部命名空间里的名字。

如果在函数内部调用 ```locals()```，返回的是所有能在该函数里访问的命名。

如果在函数内部调用 ```globals()```，返回的是所有在该函数里能访问的全局名字。

两个函数的返回类型都是字典。所以名字们能用 keys() 函数摘取。
### reload()
## IO

## 日志


#### .info()
就是将输出信息填入括号中
```py
logger.info("Running with args:")
logger.info(args)
logger.info(f"Seeds: {seeds}")
```

