package com.example.demo.service;

import com.example.demo.pojo.Dept;
import com.example.demo.pojo.Emp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    void update(Dept dept);
}
