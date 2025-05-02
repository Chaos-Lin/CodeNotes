package org.example.quick_start.controller;

import org.example.quick_start.Service.EmpService;
import org.example.quick_start.Service.impl.EmpServiceA;
import org.example.quick_start.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping("/listEmp")
    public List<Emp> list(){
        List<Emp> empList = empService.listEmp();
        return empList;
    }
}
