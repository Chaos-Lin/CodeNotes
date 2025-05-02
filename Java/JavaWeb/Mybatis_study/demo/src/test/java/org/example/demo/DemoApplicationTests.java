package org.example.demo;

import org.example.demo.mapper.EmpMapper;
import org.example.demo.pojo.Emp;
//import org.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private EmpMapper empMapper;
    @Test
    public void testListUser(){
        List<Emp> userList = empMapper.list();
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }

}
