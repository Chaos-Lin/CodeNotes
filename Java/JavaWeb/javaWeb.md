# Web前端
## HTML
```html
<!-- 文档类型为HTMl -->
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- 字符集为UTF-8 -->
  <meta charset="UTF-8">
  <!-- 设置浏览器兼容性 -->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>文件名文件名</title>
  <!-- 内联样式
  <style>
    h1{
      color:black;
    }
  </style> -->

  <!-- 外联方式 -->
  <link rel="stylesheet" href="./study.css">
</head>

<body>
  <!-- 图片标签为 img
  src:url图片资源路径
  width:percent/px
  height:percent/px -->
  <img src=".\logo.jpg" width="10%" height="10%"> <a href="https://www.bilibili.com/" target="_self">bilibili</a>

  <!-- 行内方式
  <h1 style="color: black;">the whole title</h1> -->
  <h1>the whole title</h1>

  <hr>
  <span class="cls" id="time">2024.1.24 13:14</span> <span class="cls"> <a href="https://www.baidu.com/"
      target="_blank">baidu</a></span>
  <hr>

  <!-- 超链接为 a -->
  <!-- a标签
  href:指定资源访问的url
  target:指定在何处打开资源链接
  _self:默认值,当前页面
  _blank:在空白页面打开 -->



  <!-- 视频标签为 video -->
  <!-- src:资源
    controls:显示播放的控件
    width
    height -->
  <video src="video.mp4" controls width="20%"></video>


  <!-- 音频标签为 audio -->
  <!-- src:资源
    controls:显示播放的控件 -->

  <!-- 段落标签 p -->
  <p>
    这是一个段落
  </p>
  <!-- 文本加粗 b / strong -->

  <!-- 无语义 div
  一行一个 可以设置宽高 -->

  <!-- 无语义 span
  一行多个 不可以设置行高 -->
  <div>
    这是一个盒子
  </div>

  <!-- 表格标签 table
行 tr
单元格 td 
表头 th-->

  <table border="1px" cellspacing="0" width="600px">
    <tr>
      <th>number</th>
      <th>logo</th>
      <th>name</th>
      <th>company</th>
    </tr>
    <tr>
      <td>1</td>
      <td>B</td>
      <td>babel</td>
      <td>Book</td>
    </tr>
  </table>

  <!-- 表单标签 form -->
  <!-- 表单项 
  input 输入
    type
      text
      password
      radio单选按钮
      checkbox复选框
      file文件上传
      date/time/datetime-local
      number
      email
      hidden
      submit/reset/button 按钮
  select 下拉列表 option 定义列表项
  textarea 定义文本
  属性
  action 发送地址
  method 发送方式 -->

  <form action="" method="get">

    name:<input type="text" name="username">
    age:<input type="text" name="age">
    degree:<select name="degree">
      <option value="">---option---</option>
      <option value="1">bachelor</option>
      <option value="2">master</option>

    </select>

    <input type="submit" value="submit">
  </form>

  <!-- 内部脚本 <script> -->
  <!-- <script>
    // 可以在任意位置放置
    alert('Hello');
  </script> -->

  <!-- 外部脚本 -->

  <p class="writer">Lin</p>

  <!-- 事件绑定 -->
  <!-- <input type="button" onlick="on()" value="botton1">
  <script>
    function on() {
      alert("'be clicked'");
    }
  </script> -->
  <input type="button" id="btn" value="botton2">
  <Script src="study.js"></Script>

</body>

</html>

<!-- {{{{content}padding}border}margin} -->
```
## CSS
```css
/* w3school.com.cn/index.html */


h1 {
  color: rgb(80, 80, 80);
}

span {
  color: darkcyan;
  font-size: large;
}

/* 元素 */

.cls {
  color: #968D92;
}

/* .class属性 */

#time {
  color: cornflowerblue;
}

/* #id属性 */


a {
  color: #968D92;
  text-decoration: none;
}

p {
  text-indent: 50px;
  /* 首行缩进 */
}

.writer {
  text-align: right;
  /* 对齐方式 */
}

div {
  width: 200px;
  height: 200px;
  box-sizing: border-box;
  background-color: cornflowerblue;

  padding: 20px 20px 20px 20px;
  border: 10px solid black;
  margin: 30px 30px 30px 30px;
}
```
## JavaScript
```js
//弹出
alert('Hello');
// 警告框

document.write('hello')
// 写入HTML

console.log('Hello')
// 写入浏览器控制台


//变量
var a = 20;
//全局变量

let a = 20;
// 局部变量

const a = 20;
//常量


//数据类型
typeof a
//获取数据类型

//运算符
//===不会进行类型转换
//==会进行类型转换

//类型转换
parseInt()
// string to number

//方法
function NameFunction(pa1,pa2){
  //code
}

//数组
var arr = new Array(1,2,3,4,5);
var arr = [1,2,3,4];
arr[0];
arr.length;
//长度

arr.forEach(fuction(e)){
 console.log(e);
}
//遍历

arr.forEach(element => {
  console.log(element)
});

arr.push();
//加入
arr.splice();
//删除

//字符串
var str = new String("Hello");
var str = "string"
var str = 'string'

str.length
// //长度
str.charAt(0)
// //返回字符
str.indexOf("ing")
// //返回位置
str.trim()
// //去除两边空格
str.substring(2,3)
// //返回中间字符

//自定义
var person ={
  name : "Lin",
  age : 1,
  eat:function(){}
};
json
var jsonfile = {
  "number":1,
  "string":"string",
  "bool":true,
  "array":(1,2,3),
  "object":{
    "a":1,
    "b":2
  },
  "null":null
}

var jsObject = JSON.parse(jsStr)
//将js字符串转换为js对象
var jsonStr = JSON.stringify(jsObject)
//将js转换为js字符串


//BOM Browser Object Model
// window 窗口
// navigator 浏览器
// screen 屏幕
// history 历史记录
// location 地址栏


//窗口
window.history

window.location.href;
//设置或返回完整的url

window.navigator

window.alert()

window.confirm()
// 对话框 确定或取消
// 确定返回 true 取消返回 false

window.setInterval(function(){},2000)
//周期性调用函数

window.setTimeout(function(){},2000)
//调用一次函数


//DOM Document Object Model
// document
// Element
// Attribute
// Text
// Comment


var divs = document.getElementsByTagName('div')
// //标签
var username = document.getElementsByName('username')
// //name属性
var clss = document.getElementsByClassName('cls');
// //class属性
var t = document.getElementById('time');
// //id属性

//事件
document.getElementById('btn').onclick = function () {
  alert('GOD DAMN');
}
// onclick 单击
// onblur 失去焦点
// onfocus 获得焦点
// onload 完成加载
// onsubmit 提交时
// onkeydown 键被摁下
// onmouseover 移动到
// onmouseout 移开
```
## VUE

```html
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
</head>

<body>
  <div id="app">
    <!-- 特殊数据类型——字典和数组的访问方式 -->
    <!-- <h2>{{school.name}}</h2>
    <h3>{{campus[0]}}</h3> -->

    <!-- 本地应用 -->
    <!-- v-text -->
    <h2 v-text="message"></h2>
    <h2> {{message}}</h2>

    <!-- v-html -->
    <p v-html="content"></p>

    <!-- v-on -->
    <input type="button" value="事件绑定1" @click="func">
    <input type="button" value="事件绑定2" v-on:click="func">

    <!-- 事件：click、mouseenter、dbclick -->

    <!-- v-show -->
    <img src="logo.jpg" v-show="isShow">

    <!-- v-if -->
    <p v-if="isShow">switch</p>
    <input type="button" value="switch" @click="stc">

    <!-- v-bind -->
    <!-- 元素一般写在括号内部 -->
    <a v-bind:href="website">website</a>

    <!-- v-model -->
    <!-- 双向数据bind -->
    <input type="text" v-model="url">
    <div>{{url}}</div>

    <!-- v-for -->
    <ul>
      <li v-for="(item,index) in arr">{{index}}:{{item}}</li>
    </ul>

  </div>

  <script>
    new Vue({
      el: "#app",
      data: {
        message: "I like CS",
        content: "<a href='#'>linklink</a>",
        url: "https://www.baidu.com",
        school: {
          name: "lin",
          mobile: "12345"

        },
        campus: ["paking", "shnaghai", "guangzhou"],
        isShow: false,
        website: "www.bilibili.com",
        arr: ["lin", "xuan", "chao"]
      },
      methods: {
        func: function () {
          alert("be clicked")

        },
        stc: function () {
          this.isShow = !this.isShow;
        },

      }
    })
  </script>


</body>

</html>
```
## ajax-axios
## elements
## Nginx
部署只需要在有nginx的文件夹打开一个终端，输入`start nginx.exe`
然后在浏览器-检查-变成手机模式
地址：localhost:8080

# Web后端
## maven
```xml
<dependencies>

  <dependency>
    <groupId> </groupId>
    <artifactId> </artifactId>
    <!-- 需要的包的名字 -->
    <version> </version>
    <!-- mvnrepository.com 可以用于搜索依赖 -->

    <scope>test</scope>
    <!-- compile 作用范围 
    test 测试程序
    provided 主程序和测试程序
    runtime 测试程序 打包 -->
  </dependency>

  <!-- 由于依赖的传递性，我们可以主动断开某个依赖附带的包 -->
  <exclusions>
    <exclusion>
      <groupId> </groupId>
      <artifactId> </artifactId>
    </exclusion>
  </exclusions>

</dependencies>
```
## HTTP
### 请求协议

GET /brand HTTP/1.1
**请求行：**请求类型 资源路径 协议

**请求头：**
Host: localhost:8080 主机名
User-Agent: 浏览器
Accept: 能够接受的类型 text/* Image/* */*
Accept-Language:偏好语言
Accept-Encoding:压缩类型 gzip deflate
Content-Type:主体的数据类型
Content-Length:请求主体大小

**请求体：** post请求，存放请求参数

### 响应协议

HTTP/1.1 200 OK
**请求行：**协议 响应状态码 概述
1 临时状态码
2 成功
3 重定向
4 错误：客户端
404 Not Found

5 错误：服务端
500 Internal Server Error

**请求头：**
Content-type
Content-Length
Content-Encoding
Cache-Control
Set-Cookie

**请求体：** 存放响应数据

## Tomcat
开源轻量级Web服务器
JavaSE：标准版
JavaME：小型版
JavaEE：企业版
Spring内嵌TomCat

请求-前端控制器-HttpServeletRequest
响应-前端控制器-HttpServeletResponse
### 请求
```java
    //spingboot方式
   @RequestMapping("/simpleParam")
   public String simpleParam(String name, Integer age){
       //此处参数需要对应成功
       System.out.println(name+age);
       return "OK";
   }

    @RequestMapping("/simpleParam")
    public String simpleParam(@RequestParam(name = "name",required = false)String username, @RequestParam(name = "age")Integer userage){
        //RequestParam可以名字不对应，但也说明这个参数必须传递
        //,required = false说明该参数可选
        System.out.println(username+userage);
        return "simpleParam";
    }
    @RequestMapping("/simplePojo")
    public String simpilePojo(User user){
        System.out.println(user);
        return "simplePojo";
    }

    @RequestMapping("/complexPojo")
    public String complexPojo(User user){
        System.out.println(user);
        return "complexPojo";
    }

    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby){
        System.out.println(Arrays.toString(hobby));
        return "arrayParam";
    }

    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby){
        System.out.println(hobby);
        return "listParam";
    }

    @RequestMapping("/dateParam")
    public String dataParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime){
        //需要指定日期格式
        System.out.println(updateTime);
        return "dateParam";
    }

    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user){
        //json数据的键名需要与形参的对象属性名相同
        System.out.println(user);
        return "jsonParam";
    }

    @RequestMapping("/path/{id}")
    public String pathParam(@PathVariable Integer id){
        //json数据的键名需要与形参的对象属性名相同
        System.out.println(id);
        return "jsonParam";
    }
```
### 响应数据
@RestController = @Controller + @ResponseBody

```java
    @RequestMapping("/getAddr")
    public Address getAddr(){
        //json数据的键名需要与形参的对象属性名相同
        Address addr = new Address("shanghai","putuo");
        System.out.println(addr);
        return addr;
    }
    @RequestMapping("/listAddr")
    public List<Address> listAddr(){
        List<Address> list = new ArrayList<>();
        Address addr1 = new Address("shanghai","baoshan");
        Address addr2 = new Address("shanghai","putuo");
        list.add(addr1);
        list.add(addr2);
        return list;
    }
```
# database
## mysql
## Mybatis
### 配置信息
用于操作数据库
引入Mybatis的相关依赖，然后再resource包下的application.properties配置连接信息
```
// 配置教据库的连接信息-四要素
// 驱动类名称
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis
spring.datasouree.username=root
spring.datasource.password=1234

//日志输出
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl

```
### JDBC
会有硬编码问题
频繁连接和断开，浪费资源
解析代码繁琐

### 连接池
Druid
Hikari
切换连接池，只需要在依赖中引入就可以了

### lombok
不用繁琐地定义类了
```html
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```
```
@Getter/@Setter 为所有的属性提供get/set方法
@ToString 会给类自动生成易阅读的 toString 方法
@EqualsAndHashCode 根据类所拥有的非静态字段自动重写 equals 方法和 hashCode 方法
@Data 提供了更综合的生成代码功能(@Getter+@Setter+@ToString+@EqualsAndHashCode)
@NoArgsConstructor 为实体类生成无参的构造器方法
@AllArgsConstructor为实体类生成除了static修饰的字段之外带有各参数的构造器方法。
```

### 基础操作
实体名称和数据库名称
实体名称：驼峰命名
数据库：下划线
将下划线之后的第一个字母大写

#### 删除

```java
@Delete("delete from tlias.emp where id = #{id}")
    public void delete(Integer id);
```
预编译可以防止SQL注入问题，并且性能高
#### 新增

```java
//主键返回
@Options(keyProperty = "id", useGeneratedKeys = true)
@Insert("insert into tlias.emp(username, name, gender, image,job, entrydate, dept_id, create_time, update_time) " +
        "values(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
        public void insert(Emp emp);
```
#### 修改
```java
    @Update("update emp set username=#{username}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, entrydate=#{entrydate}, dept_id=#{deptId}, update_time=#{updateTime} where id=#{id}")
    public void update(Emp emp);
```
#### 查询
```java
    // @Results({
    //         @Result(column = "dept_id",property = "deptId"),
    //         @Result(column = "create_time",property = "createTime"),
    //         @Result(column = "update_time",property = "updateTime")
    // })//用于映射数据库与实体名字不一致的情况
@Select("select * from tlias.emp where id=#{id}")
public void getById(Integer id);
```
用于映射数据库与实体名字不一致的情况
```mybatis.configuration.map-underscore-to-camel-case=true```

### XML映射文件
在resource下与mapper接口同包同名的XML

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- 重复片段 -->
<sql id="commonSelect">
    SELECT id, name, age, email
    FROM emp
</sql>


<!-- namespace是与全类型相同的接口 -->
<mapper namespace="org.example.demo.mapper.EmpMapper">
<!-- id是接口中的函数的名字 -->
<!--    返回类型是单条语句的类型-->
    <select id="list" resultType="org.example.demo.pojo.Emp">
        SELECT * from tlias.emp
    </select>
</mapper>


<!--    返回类型是单条语句的类型-->
<select id="list" resultType="org.example.demo.pojo.Emp">
    SELECT * from tlias.emp
    <where>
        <if test="name != null">
            name like concat('%',#{name},'%')
        </if>
        <if test="gender != null">
            gender = #{gender}
        </if>
    </where>
</select>

<select id="getById" resultType="org.example.demo.pojo.Emp">
    <include refid="commonSelect"></include>
    <!-- 引用重复片段 -->

</select>

<delete id="deleteById">
    delete from emp where id in
--         集合 元素 分割符 遍历开始前拼接词 结束拼接词
    <foreach collection="ids" item="id" separator="," open="(" close=")">
        #{id}
    </foreach>
</delete>
```
注解适合简单的，xml适合复杂的

### 动态sql
#### if
```xml
<where>
  <if test="name != null">
      name like concat('%',#{name},'%')
  </if>
  <if test="gender != null">
      and gender = #{gender}
  </if>
</where>
```
#### forEach
```xml
<delete id="deleteById">
  delete from emp where id in
<!-- 集合 元素 分割符 遍历开始前拼接词 结束拼接词 -->
  <foreach collection="ids" item="id" separator="," open="(" close=")">
      #{id}
  </foreach>
</delete>
```

#### sql include
```xml
<sql id="commonSelect">SELECT id, username, password, name, gender from emp</sql>

<include refid="commonSelect"></include>
```

## MybatisPlus
也就是很多mapper和数据库交互的操作都不需要写了
```java
public interface UserMapper extends BaseMapper<User>{

}
```
就可以直接调用里面的方法，需要添加MP的依赖。

但是需要在service层里
```java
public interface IUserService extends IService<User>
```

```java
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {}

```
`extends IService<User>`、`extends ServiceImpl<UserMapper, User>`需要去继承这样一个mybatisPlus的类

### 注解
- 驼峰转下划线
- id为主键
```java

@TableName("真实表名")
public class User{
  @TableId(value="主键名", type=IdType."AUTO INPUT ASSIGN_ID")//type 可以不填
  private long id;

  @TableField("真实字段名")
  // is开头必须要使用
  // 与数据库冲突的要用转义字符 ''
  @TableField(exist = false)
  //字段名表中不存在
}
```

### 配置

### 条件构造器


### 自定义

# Spring
## Web后端三层架构
三层架构：
Controller：控制层，接收前端发送的请求，对请求进行处理，并响应数据。
Service：业务逻辑层，处理具体的业务逻辑
Dao：数据访问层
### controller
接收前端发送的请求
```java
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping("/listEmp")
    public List<Emp> list(){
        List<Emp> empList = empService.listEmp();
        return empList;
    }

    @DeleteMapping("/depts/{id}")
public Result delete(@PathVariable Integer id){
    log.info("check all dept data");
    deptService.delete(id);
    return Result.success();
}
}


```
### service
具体的业务处理逻辑
```java
public interface EmpService {
    public List<Emp> listEmp();

    void delete(Integer id);
}
```
#### impl
```java
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }
}
```
### mapper
```java
@Mapper
public interface DeptMapper {
    @Select("select * from emp")
    List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);
}
```



## IOC 控制反转
@ComPonent

```java
@ComPonent //将当前类交给IOC容器
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {

        List<Emp> empList = new ArrayList<>();
        //数据的获取
        return empList;
    }
}
@Controller

@Service

@Repository

包扫描，默认扫描当前包及其子包
@ComponentScan //({"包名"})
@SpringBootApplication //具有包扫描作用
```
## DI 依赖注入
@AutoWired
```java
@AutoWired //IOC容器会提供该类型的对象
private EmpDao empDao

//多个相同类型的情况
@Primary
@Component

@AutoWired 
@Qualifier("empServiceA")//默认是类名首字母小写
private EmpDao empDao

@Resource(name = "empServiceA") //JDK提供的注解
private EmpDao empDao
```

## 日志

```java
@Slf4j
//可以直接使用log.info
log.info
```
## AOP
## 事务管理

# SpringMVC
## 请求
## 响应
## 拦截器
```java
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.判断是否需要拦截（ThreadLocal中是否有用户）
        if (UserHolder.getUser() == null) {
            // 没有，需要拦截，设置状态码
            response.setStatus(401);
            // 拦截
            return false;
        }
        // 有用户，则放行
        return true;
    }
}
```
1. 权限验证
在处理用户请求之前，拦截器可以检查用户是否具有访问该资源的权限。例如，对于一些需要登录才能访问的页面或接口，拦截器可以检查用户的会话（Session）或令牌（Token）是否有效。如果用户未登录或权限不足，拦截器可以直接返回错误信息，阻止请求继续传递到目标处理程序。
## 异常处理

# 开发规范
## Restful
通过http动词描述操作
get 查询
post 新增
put 修改
delete 删除

# 登录功能
