package com.example.demo.mapper;

import com.example.demo.pojo.Emp;
import com.example.demo.pojo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

    //
    @Select("select count(*) from emp")
    public Long count();

    @Select("select * from emp limit #{start},#{pageSize}")
    public List<Emp> page(Integer start, Integer pageSize);

    @Select("select * from emp")
    public List<Emp> list();


    public List<Emp> pageLimit();
}
