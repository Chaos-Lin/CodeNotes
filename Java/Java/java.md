# basic
```java
public class Demo{
    //主入口
    public static void main(String[] args){
        
    }
}
```
### cmd
```cmd
javac //编译
java
javap //反编译
```
### 数据类型
整数 int short byte long
浮点数 float double 
字符 char
布尔 boolean
### 命名
小驼峰: 方法、变量 (student)
大驼峰: 类名(Student)


### 键盘录入

```java
// 导包
import java.utils.Scanner;

//创建对象
Scanner sc = new Scanner(System.in);

// 输入
int i = sc.nextInt();
```

## IDEA
### 项目结构
project 项目
module 模块
package 包
class 类
 
## 分支
### IF
```java
if (){

}

if (){

}else{

}

if (){

}else if(){

}else{

}
```
### switch
```java
switch(){
    case 值1:
        语句体;
        break;
    default:
        语句体;
        break;

}
```

### for
```java
for(int i -=1; i<10; i++){

}
loop: while(){

    break loop;//
    System.exit(0)//停止虚拟机运行
}

```

## 容器

### 数组
```java
int[] 数组名 = new int[长度] {};
int[] 数组名 = {};
```



##　方法
ctrl alt M 可以抽取方法
```java
public static void 方法名(int a, int b){

}
public static 返回类型 方法名 (参数){
    return 返回值;
}
```
### 重载
方法名相同，参数不同，与返回值无关

## 类
### 包含
属性
方法
构造方法
代码块
内部类
###　格式
对于每个私有化的成员变量都要提供get和set方法。
###　this
用于区分成员变量（堆）和局部变量（栈）
this表示方法调用者的地址值
### 构造方法

```java
修饰符 类名(参数){
    方法体；
}
public Student(){

}
```
无论是否使用，都要手动书写无参数构造方法，和带全部参数的构造方法。
### 快捷
alt + insert
alt + fn + insert

### 分类
#### Javabean类
用于描述事物
#### 测试类
main
#### 工具类
私有化构造方法
方法定义为静态
ArrayUtil


## 字符串
### 定义
```java
String s1 = "abc"
```
使用直接赋值，回检查是否已存在。
### 比较
引用数据类型比较的是地址值
```java
boolean equals(String a)
boolean equalsIgnoreCase(String a)
```

### 遍历
```java
public char charAt(int index);
public int length();
```
### 子串
```java
String substring(int begin, int end)
String substring(int begin)

```

### StringBuilder
String无法再次赋值但是StringBuilder可以。

```java
StringBuilder append();
StringBuilder reverse();
int length();
String toString()
```
### StringJoiner
用于插入中间间隔值
```java
//创建方法
StringJoiner (间隔符号)
StringJoiner (间隔符号，开始符号，结束符号)
//方法
StringJoiner add(添加内容)
int length()
String toString()
```

## 集合
### 创建
```java
ArrayList<String> list = new ArrayList<String>();
// JDK 7
ArrayList<String> list = new ArrayList<String>()
// now
```
### 方法
```java
boolean add (e)
boolean remove(e)
E remove(int index)
E set(int index, E e)
E get(int index)
int size()
```


## static

### 静态类
```java
public class Student(){
    public static String teacherName;
    //所有的Student类都共享一个teacherName
}
```

### 静态方法
用static修饰成员方法多用在测试类和工具类
可以直接用类名调用
静态方法不能访问非静态属性和方法


## 封装
对象代表什么，就得封装对应的数据，并提供数据对应的行为
## 继承
```java
public class Student extends Person{

}
```
java 只支持单继承，不支持多继承，但支持多层继承。
### private
private 的方法不能继承 变量能继承

### super
name 从局部位置开始找
this.name 从本类成员位置开始找
super.name 从父类成员位置开始找

### @Override
重写的父类方法
```java
@Override
public void drink(){

}
```
重写方法的名称、形参必须与父类中的一致
子类重写父类，访问权限子类必须大于等于父类（空着< protected < public 
返回值类型也要小于等于父类
"私有方法不能被重写
子类不能重写父类中的静态方法"->只有被添加到虚方法表中的方法才能被重写

### 构造方法
构造方法不会被继承
执行时先访问父类中的无参构造，再执行自己
子类构造方法的第一句时super(),如果是有参构造那么就是super(param1,param2)


## 多态
同类型的对象，表现出不同形态。

父类类型 对象名称 = 子类对象

调用成员变量
编译看左边，运行也看左边
因为在继承中，子类对象会把父类的成员变量也继承下来
```java
Animal a = new Dog()
a.name
//Animal
```
调用成员方法
编译看左边，运行看右边
而方法重写则会覆盖
### instanceof
对象 instanceof 类
```java
Person p = new Student()
Student s = (Student)p;
```

## 包
使用同一个包中的类，不需要导包
使用java.lang中的类，不需要导包
如果同时使用两个包中的同名类，需要使用全类名

```java
import 
```

## final
### 方法
不能被重写

### 类
不能被继承

### 变量
常量，只能被赋值一次
全部大写
单词之间用下划线_隔开
final 修饰引用变量，地址值不能发生改变、

## 权限修饰符

### private
同一个类里

### 缺省
同一个包里其他类

### protected
不同包下的子类

### public
都可以

成员变量私有
方法公开

## 代码块

### 局部代码块
一个方法里的代码
{

}


### 构造代码块
```java
class Student{

    {
        //这个代码块里的内容就会在创建对象时调用
    }
    public Student(){

    }

    public Student(param1){

    }
}

```

### 静态代码块
随着类的加载而加载，只执行一次
在类加载时，进行一些数据初始化时用。
```java
class Student{
    static{
        //自动添加一些数据
    }
}

```


## 抽象类
```java
public abstract class Person{
    public abstract void work();
    // 子类必须override
}
```
抽象类不能实例化

## 接口
对某种方法的规定
接口不能实例化
接口和类之间是实现关系
接口的子类要重写接口中所有的抽象方法
```java
public interface Swim{
    public abstract void swim();
}
public class 类名 implements Swim{
    @Override
    public void swim(){
        // 实现
    }
}
```

接口中的成员变量只能是常量
默认：public static final

接口可以单继承也可以多继承

### 接口升级
默认方法
```java
public default void show(){

}
```
可以不被重写，如果重写需要去掉default
如果实现了多个接口，多个接口有相同名字的默认方法，子类必须要重写

## 内部类
内部类可以直接访问外部类的成员
外部类要访问内部类的成员，必须要创建对象

### 成员内部类
类似于成员变量，在**类里**定义
```java 
public class Outer{
    class Inner{

    }
}

out.inner obj = new out().new inner();
//可以直接调用
```

### 静态内部类
特殊的成员内部类
```java 
public class Outer{
    static class Inner{

    }
}

out.inner obj = new out.inner();
//可以直接调用
```
### 局部内部类
类似于局部变量，在**方法内**定义的。
可以直接访问外部类的成员


### 匿名内部类
相当于是一个**对象**。
new 类名或者接口名 是继承类或者接口实现类
是一个没有名字的类
```java
new 类/接口(){
    @override
}
```
当有一个方法需要传入抽象类，就可以直接在括号里new 一个。



# API
## Math
## Ststem
##　Runtime
## Object
## BigInteger
## BigDecima
## 正则表达式
## 爬虫
## 时间
## Arrays

## lambda表达式
本质上就是传一个方法进去，本来需要一个匿名内部类，现在只需要给参数和方法就可以了
```java
Array.sort(arr, (Integer o1, Interger o2)->{
    //方法体
}
);
```

# 集合
单列集合和双列集合

## Collection
```java
add(e)
clear()
remove(e)
contains(obj)
isEmpty()
size()
```
### 遍历方式
**迭代器遍历**
```java
Iterator<String> it = list.iterator()//创建指针

while(it.hasNext()){//判断是否有下一个元素
    String str = it.next()// 获取原酸，并移动指针
}

```
**增强for遍历**
```java
for(String s : list){
    System.out.println(s);
}
```
**lambda表达式**
```java
coll.forEach(
    new Consumer<String>(){
        @Override
        public void accept(String s){
            //todo
        }
    }
);

coll.forEach((s)->{
            //todo
        }

);
```
### List
有序，可重复，有索引
```java
set(index,element);
get(index)
```
列表迭代器遍历
```java
ListIterator<String> it = list.listIterator();
while(it.hasNext()){
    str = it.next();
    it.add();//可以在遍历的过程中添加元素
}
```

数组：查询快，增删慢，内存连续
链表：查询慢，增删快
#### ArrayList
底层是数组
#### LinkedList
双链表
```java
addFirst(e);
addLast(e);
getFirst();
getLast();
removeFirst();
removeLast();
```
#### 泛型
java的泛型是伪泛型，本质上还是当作Obj来操作，在编译为class文件之后，就会被擦除，他做的只是进行了强转
```java
//泛型类
class 类<E>{
    //E是一个不确定的类型

}
//泛型方法
public static<E> void 方法 (E e){

}
//泛型接口
puclic interface 接口<E>{

}
```
泛型的通配符，可以限制类型的范围
```java
? extends E
? super E
```
### Set
无序，不重复，无索引
```java

```
#### HashSet
哈希表
JDK8前:数组+链表
后：数组+链表+红黑树
```java
.hashCode()
//可以查看哈希值
//没有重写就是用地址值算的，重写的了看情况
```
如果集合中存储的对象是自定义对象，必须要重写hashCode和equals方法。可以直接让IDEA来写
#### TreeSet
可排序，底层用的是红黑树
但是元素需要可排序，所以需要扩展接口
默认排序：
```java
class Student extends Comparable<Student>{
    @Override
    public int compareTo(Student o){
        //指定排序规则
        this.getAge() - o.getAge();
        //如果是负数，说明添加的元素小，放在前面
        //0说明已有，舍弃
    }
}
```
比较器排序：
```java
Tree<String> ts = new TreeSet<>(
    new Comparator<String>(){
        @Override
        public int compare(String o1, String o2){
            //o1表示要添加的
            int i =o1.length()-o2.length();
            i = i == 0 ? o1.compareTo(o2) : i;
            return i ;
        }
    }
);

```
#### LinkedHashSet
有序，每个元素有个双链表机制来记录存储的顺序
## Map
```java
put(key,value)//add element 如果覆盖了原来的键值对，会返回原来的值
get(key)
remove(key)// 返回删除的value
clear()
containsKey() // 是否存在key
containsValue()
isEmpty()
size()

//create
Map<String,String> m = new HashMap<>();
```
### 遍历
键找值
```java
Set<String> keys = map.KeySet();
for(String key : keys){
    String value = map.get(key);
}
```
键值对
ctrl alt v
```java
Set<Map.Entry<String, String>> entries = map.entrySet();
//就是把双列变成单列，但是单列的所有元素都是键值对
for (Map.Entry<String, String>> entry: entries){
    String key = entry.getKey();;
    String value = entry.getValue();
}

for (Map.Entry<String, String>> entry: map.entrySet()){
    String key = entry.getKey();;
    String value = entry.getValue();
}
```
lambda表达式
```java
map.forEach(
    new BiConsumer<String, String>(){
        @Override
        public void accept(String key, String value){
            //todo
        }
    }
);


map.forEach((String key, String value)->{
            //todo
        }
);
map.forEach((key,value)->{
            //todo
        }
);
```
### HashMap
哈希表结构
### TreeMap

### LinkedHashMap
有序，不重复
### 可变参数
在方法的形参中，最多只能写一个可变参数，并且要写在最后
```java
public static int getSum(int...args){

}

```
### Collections
```java
addALL()
shuffle()
sort(List)
sort(List, c)
binarySearch()
copy()
fill()
max/min()
swap()
```
## 不可变集合
```java
.of();
.ofEntries();
```

## Stream 流
1. 获取Stream流
```java
// list
   list.stream();
// map
    hm.keySet().stream();
// array
    Array.stream(arr)
// 零散数据
    Stream.of(数据);
```
2. 中间方法
```java
//全都返回的是Stram流对象
filter()//过滤
    @Override
    test()//返回值表示False去还是True留
limit(int)//获取前几个
skip(int)//跳过前几个
distinct()//去重
concat()//合流
map()//转换类型
map(new Function<String, Object>(){
    @Override
    public Object apply(String s){

    }
});
map(s->Integer.parseInt(s.split("-")[1]))
```
3. 终结方法
```java
forEach()
count()
toArray()
collect(Collection.toList()) //收集起来
Collection.toSet()
Collection.toMap(键的规则，值的规则)
```

## 方法引用
其实就是将方法作为参数传来传去 非常的python
### 引用静态方法
```java
类名::静态方法
Integer::paeseInt
list.stream().map(Integer::parseInt)
```
### 引用成员方法
```java
对象::成员方法
this::method
super::method
```
### 引用构造方法
```java
//数组构造方法
Integer[] arr = list.stream().toArray(Integer[]::new)
```
# 异常 Exception
编译时异常
RuntimeException
```java
if(age<18>){
    throw new RuntimeException();
}

try{
    arr[10]
}catch(ArrayIndexOutOfBoundsException | ArithmeticException e){
    e.printStackTrace();//会把异常的错误信息输出在控制台
}catch(){

}

System.out.println()
System.err.println()
```
抛出异常
throws 声明异常
throw 结束方法
```java
try {
    max = getMax()
}catch(ArrayIndexOutOfBoundsException e){

}


public static int getMAx() throws ArrayIndexOutOfBoundsException{
    if(){
        throw new ArrayIndexOutOfBoundsException
    }
}

```
# File
```java
//
String str = "";
File f = new File(str);

String parent = "";
String child = "";
File f = new File(parent, child);

File parent = new File(str);
File f = new File(str,child);
```
## 成员方法
### 判断获取
```java
isDirectory()
isFile()
exists()
length()
getAbsolutePath()
getPath()
getName()
lastModified()
```
### 创建删除
```Java
createNewFile()
//成功失败都会返回
//父路径不存在会报IO异常
//没有后缀也能创建
mkdir()
//单级目录
mkdirs()
//多级目录
delete()
//只能删除文件和空文件夹
```
### 获取并遍历
```java
File[] files = f.listFiles();
for(files : file){

}
//如果是文件或者路径不存在或者需要权限都会返回null
//空文件夹则返回空数组
```
# IO流
## 基本流
存储和读取
### 字节流
读数据
```java
// InputStream
//     FileInputStream

//创建对象
FileInputStream fis = new FileInputStream("");
//文件不存在则报错

//读取数据
int a = fis.read();
byte [] bytes = new byte[2]
fis.read(bytes);
String str = new String(bytes,0,len)

while((b = fis.read)!= -1){
    System.out.print((char)b);
}
//一次都一个字节
//文件末尾返回-1

//释放资源
fis.close();
```
写数据
```java
OutputStream
    FileOutputstream
//创建对象
FileOutputstream fos = new FileOutputstream("",boolen append);
//可以输入路径或者file对象
//如果文件不存在会创建文件，但是要保证父级文件夹存在
//如果文件存在，会清空
//如果append是true的话，可以续写

String str = "outputdata";
byte[] arr = str.getBytes();
/*
回车：win：\r\n
linux:\n
mac:\r
*/


//写出数据
fos.write(int b);
fos.write(byte[] b);
fos.write(byte[] b, int off, int len);//起始索引，个数

//释放资源
fos.close(;)
```
文件拷贝
```java
int b;
while((b=fis.read())!=-1){
    fos.wirte(b);
}
fos.close();
fis.close();




int len；
byte[] bytes = new byte[1024];
while((len=fis.read(bytes))!=-1){
    fos.write(bytes,0,len);
}
```
```java
try{

}catch{

}finally{

}

```
### 字符流
1. 字符集默认**GBK** 
两个字节
字母补0 汉字开头必为1

2. 国际**Unicode**
以下是Unicode字符集的编码方式
- UTF-16 UTF-32
- UTF-8 1-4字节
- - ascll 1 byte
0XXXXXXX
- - 简体中文 3 bytes
1110XXXX 10XXXXXX 10XXXXXX

```java
//编码
byte[] bytes=str.getBytes()
byte[] bytes=str.getBytes(String charsetName)

//解码
String str = new String(bytes);
String str = new String(bytes,String charsetName);
```
读数据
```java
// Reader
//     FileReader
FileReader fr = new FileReader("")


int ch;
while((ch = fr.read())!=-1){
    sout((char)ch);
}

int len;
char[] chars = new char[];
while((len =fr.read())!=-1){
    sout(new String(chars,0,len))
}

fr.close()
```
字符流会从内存的缓冲区中读

写数据
```java
// Writer
//     FileWriter
FileWriter fw = new FileWriter(file/path , boolen append=false);

write(int);
write(String);
write(String, str, int off, int len);
write(char[] cbuf);
write(char[] cbuf, int off, int len);

fw.flush();//可以刷新缓冲区

fw.close();
```
也是先写到缓冲区
## 高级流
### 缓冲流
自带8KB缓冲区
```java
BufferedInputStream(InputStream is, int size=8192);
BufferedOutputStream(OutputStream os, int size=8192);
BufferedReader(Reader r);
BufferedWriter(Writer r);
String readLine();//按行读 无数据会返回null
void newLine();//跨平台换行
```
### 转换流
字节流和字符流之间的桥梁
```java
InputStreamReader isr = new InputStreamReader(new FileInputStream(""), "GBK")
//包装字节流，然后字符流的方式读
//JDK11以后直接用字符流就行了
OutputStreamWriter osw = new OutputStreamWriter(new FileOutputstream(""), "GBK")
```
### 序列化流
可以把对象输入输出
```java
Student stu;
ObjectOutputStream oos = ObjectOutputStream(new FileOutputStream(""));
oos.writeObject(stu);
oos.close();
//但是会报错，所以需要让Student类实现Serializable接口


ObjectInputStream ois = ObjectInputStream(new FileInputStream(""));
Obeject o = ois.readObject();
ois.close();
```
需要固定版本号
```java
private static final long serialVersionUID = 1L;
//不想序列化到本地需要在变量前标注
private transient String address;
```
### 打印流
字节打印流,一行一行的写
```java
PrintStream ps = new PrintStream(new FileOutputStream(""), autoFlush=false, Charset.forName("UTF-8"));
ps.print("");
ps.println("");
ps.printf("%s","myname");
ps.close();
//System类有一个静态的ps类名叫out，指向控制台
System.out.println();
```
字符打印流
```java
PrintWriter pw = new PrintWriter(new FileWriter(""),autoFlush=false, Charset,forName("UTF-8"));
pw.print("");
pw.println("");
pw.printf("%s","myname");
pw.close();
```
### 压缩流
解压
```java
ZipInputStream zip = new ZipInputStream(new FileInputStream(src));
//获取压缩包里的每个entry对象
ZipEntry entry = zip.getNextEntry();
//获取完毕会返回null

public static void unzip(File src, File dest)throws IOException{
    ZipInputStream zip = new ZipInputStream(new FileInputStream(src));
    ZipEntry entry;
    while((entry=zip.getNextEntry();)!=null){
        if(enrty.isDirectory()){
        //文件夹
        File file = new File(dest,entry.toString());
        File.mkdirs();
        }else{
        //文件
        FileOutputStream fos = new FileOutputStream(dest,entry.toString());
        int b;
        while((b=zip.read())!= -1){
            fos.wirte(b);
        }
        fos.close();
        zip.closeEntry();
        }
        
    }
    
}
```

压缩
```java
//压缩单个文件
public static void tozip(File src, File dest){
    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(dest,"a.zip")));
   ZipEntry entry = new ZipEntry("a.txt");
    //相当于创建一个空文件
    zos.putNextEntry(entry);
    //把entry结点放入zos流中
    FileInputStream fis = new FileInputStream(src);
    //把源文件读进来
    int b;
    while((b=fis.read())!=-1){
        zos.wirte(b);
    }
    zos.closeEntry();
    zos.close();
}


//压缩文件夹
```
### Commons-io
```java
copyFile(src,dest);
copyDirectory(src,dest);
copyDirectoryTodirectory(src,dest);
deleteDirectory(directory);
cleanDirectory(directory);
readFileToString(file, Charset);
write(file, data, encoding);

copy(inputStream, outputStream);
coyptlarge(raeder, writer);
readlines(reader);
write(data, outputStream);
```
### Hutool
# 多线程
## 实现方式
### extends Thread
```java
// 定义类
// 重写run
public class MyThread extends Thread{
    @Override
    public void run(){

    }
}


MyThread t = new MyThread();
t.start();

```
### implements Runnable
```java

public class MyThread implements Runnable{
    @Override
    public void run(){

    }
}

MyThread p = new MyThread();
Thread t = new Thread(p);
t.start();
```
### implements Callable, Future
可以获取到返回值
```java
public class MyThread implements Callable<Integer>{
    @Override
    public Integer call(){

        return sum;
    }
}

MyThread mc = new MyThread();
FutureTask<Integer> ft = new FutureTask<>(mc);
Thread t = new Thread(ft);
t.start();
Integer result = ft.get();
```

## 成员方法
```java
getName();
setName();
static Thread currentThread();
static void sleep(long time);//ms
SetPriority(int);
final int getPriority();
final void setDaemon(boolen on)//设置为守护线程，会在非守护线程结束时，陆续结束
public static void yield();//出让CPU的执行权
public static void join();//插队

```

## 线程池
```java
ExecutorService pool =Executors.newCashedThreadPool();//无上限
ExecutorService pool =Executors.newFixedThreadPool(int nThereads);//有上线

//提交任务
pool.submit(Callable);

//销毁线程池
pool.shoutdown();

ThreadPoolExecutor pool = new ThreadPoolExecutor(
    3,//核心线程数量
    6,//线程池中的最大线程的数量
    60,//空闲时间时长
    TimeUnit.SECONDS,//空闲时间单位
    new ArrayBlockingQueue<>(3),//阻塞队列
    Executors.defaultThreadFactory(),//创建线程的方式
    new ThreadPoolExecutor.AbortPolicy//要执行的任务过多时的解决方案,这里是丢弃任务并抛出异常。
);

```
最大并行数就是4核8线程里的8；

CPU密集型：最大并行数+1；
IO密集型

# 网络编程
## ip
```java
InetAddress address = InetAdderss.getByName("主机名或者ip地址");
address。getHostName();
address.getHostAddress();

```
## 协议
### UDP
#### 单播
自己的ip是127.0.0.1
发送数据
```java
//创建对象
DatagramSocket ds = new DatagramSocket();//可以指定端口或者随机端口

//打包数据
DatagramPacket dp = new DatagramPacket(byte[] data, int offset = 0, int length, InetAddress address, int port);

//发送数据
ds.send(dp);

//释放资源
ds.close();

```
接收数据
```java
//接收端创建
DatagramSocket ds = new DatagramSocket(port);//需要与发送端口一致，手动绑定

//接收数据
byte[] data = new byte[1024]
DatagramPacket dp = new DatagramPacket(data, data.length);
ds.receive(dp);//该方法是阻塞的

//解析数据包
dp.getDates();
dp.getLength();
dp.getAddress();
dp.getPort();

//释放资源
ds.close();
```
#### 组播
224.0.0.0~239.255.255.255
发送端
```java
MultcastSocket ms = new MultcastSocket();
```
接收端
```java
MultcastSocket ms = new MultcastSocket();
ms.joinGroup(address);//需要与发送端相同的组播地址
```
#### 广播
广播地址：255.255.255.255
把单播地址改成广播地址就可以了

## TCP
客户端发送数据
```java
Socket socket = new Socket(host,port);//三次握手
OutputStream os = socket.getOutputStream();
os.write("".getBytes());

os.close();
socket.close();//四次挥手
```
服务端接收数据
```java
ServerSocket ss = new ServerSocket(port);
Socket socket = ss.accept();//监听客户端的链接,这是一个阻塞，三次握手
InputStream is = socket.getInputStream();
//然后字节流读取数据

socket.close();
ss.close(); //四次挥手
```
等待确认-返回响应-再次确认 建立连接
取消请求-响应请求（**此时等待服务器将最后的数据处理完毕**）发送确认请求-连接取消
# 反射
反射允许对成员变量，成员方法和构造方法的信息进行编程访问
```java
Class.forName('全类名');//最常用
类名.class();//当作参数传递
对象.getClass();//已经有了类的对象时才可以用
```

# 动态代理
其实就是类似python中的装饰器，可以在函数外面包装函数

#
## synchronized
synchronized 通过锁机制，确保在任何时刻只有一个线程可以访问共享资源，直到当前线程释放锁，其他线程才能继续访问。