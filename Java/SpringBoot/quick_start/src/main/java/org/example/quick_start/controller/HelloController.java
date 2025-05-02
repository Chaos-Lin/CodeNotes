package org.example.quick_start.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.quick_start.pojo.Address;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.example.quick_start.pojo.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello,world");
        return "hello,world";
    }
//    @RequestMapping("/simpleParam")
//    public String simpleParam(HttpServletRequest requst){
//        String name = requst.getParameter("name");
//        String age = requst.getParameter("age");
//        System.out.println(name+age);
//        return "OK";
//    }

    //spingboot方式
//    @RequestMapping("/simpleParam")
//    public String simpleParam(String name, Integer age){
//        //此处参数需要对应成功
//        System.out.println(name+age);
//        return "OK";
//    }

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
    //
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

}
