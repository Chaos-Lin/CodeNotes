package org.example.quick_start.Dao;

import org.example.quick_start.pojo.Emp;

import java.util.List;

public interface EmpDao {
    //获取员工列表数据
    public List<Emp> listEmp();
}
