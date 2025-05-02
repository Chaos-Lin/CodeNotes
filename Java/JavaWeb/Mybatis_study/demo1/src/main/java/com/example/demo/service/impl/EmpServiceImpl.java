package com.example.demo.service.impl;

import com.example.demo.mapper.EmpMapper;
import com.example.demo.pojo.Emp;
import com.example.demo.pojo.PageBean;
import com.example.demo.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;
//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
////        PageBean pageBean = new PageBean();
////        pageBean.setRows(empMapper.page((page-1)*pageSize,pageSize));
////        pageBean.setTotal(empMapper.count());
////        return pageBean;
//
//        // 使用pagehelper插件
//        PageHelper.startPage(page,pageSize);
//        List<Emp> empList = empMapper.list();
//        Page<Emp> p = (Page<Emp>) empList;
//
//        PageBean pageBean = new PageBean();
//        pageBean.setTotal(p.getTotal());
//        pageBean.setRows(p.getResult());
//        return  pageBean;
//    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        return null;
    }
}
