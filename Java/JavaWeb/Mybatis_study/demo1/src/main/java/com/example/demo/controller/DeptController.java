package com.example.demo.controller;

import com.example.demo.pojo.Dept;
import com.example.demo.pojo.Emp;
import com.example.demo.pojo.Result;
import com.example.demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;



//    private static Logger log = LoggerFactory.getLogger(DeptController.class);
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)//指定请求方式
    @GetMapping
    public Result list(){
        log.info("check all dept data");
        List<Dept> dept = deptService.list();
        return Result.success(dept);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("delete dept data");
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("add dept data");
        deptService.add(dept);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("update dept data");
        deptService.update(dept);
        return Result.success();
    }
}
