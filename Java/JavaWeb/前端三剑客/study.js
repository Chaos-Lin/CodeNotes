//弹出
// alert('Hello');
// 警告框

// document.write('hello')
// 写入HTML

// console.log('Hello')
// 写入浏览器控制台


//变量
// var a = 20;
//全局变量

// let a = 20;
// 局部变量

// const a = 20;
//常量


//数据类型
// typeof a
//获取数据类型

//运算符
//===不会进行类型转换
//==会进行类型转换

//类型转换
//parseInt()
//string to number

//方法
// function NameFunction(pa1,pa2){
//   //code
// }

//数组
// var arr = new Array(1,2,3,4,5);
// var arr = [1,2,3,4];
// arr[0];
// arr.length;
//长度

// arr.forEach(fuction(e)){
//  console.log(e);
// }
//遍历

// arr.forEach(element => {
//   console.log(element)
// });

// arr.push();
//加入
// arr.splice();
//删除

//字符串
// var str = new String("Hello");
// var str = "string"
// var str = 'string'

// str.length
// //长度
// str.charAt(0)
// //返回字符
// str.indexOf("ing")
// //返回位置
// str.trim()
// //去除两边空格
// str.substring(2,3)
// //返回中间字符

//自定义
// var person ={
//   name : "Lin",
//   age : 1,
//   eat:function(){}
// };
//json
// var jsonfile = {
//   "number":1,
//   "string":"string",
//   "bool":true,
//   "array":(1,2,3),
//   "object":{
//     "a":1,
//     "b":2
//   },
//   "null":null
// }

// var jsObject = JSON.parse(jsStr)
//将js字符串转换为js对象
// var jsonStr = JSON.stringify(jsObject)
//将js转换为js字符串


//BOM Browser Object Model
// window 窗口
// navigator 浏览器
// screen 屏幕
// history 历史记录
// location 地址栏


//窗口
// window.history

// window.location.href;
//设置或返回完整的url

// window.navigator

// window.alert()

// window.confirm()
// 对话框 确定或取消
// 确定返回 true 取消返回 false

// window.setInterval(function(){},2000)
//周期性调用函数

// window.setTimeout(function(){},2000)
//调用一次函数


//DOM Document Object Model
// document
// Element
// Attribute
// Text
// Comment


// var divs = document.getElementsByTagName('div')
// //标签
// var username = document.getElementsByName('username')
// //name属性
// var clss = document.getElementsByClassName('cls');
// //class属性
// var t = document.getElementById('time');
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


