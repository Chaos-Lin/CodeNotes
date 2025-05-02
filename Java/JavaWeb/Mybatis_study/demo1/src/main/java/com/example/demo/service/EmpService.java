package com.example.demo.service;

import com.example.demo.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface EmpService {
    PageBean page(Integer page, Integer pageSize,
                  String name,
                  Short gender,
                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);
}
