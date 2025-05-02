package org.example.quick_start.Dao.impl;

import org.example.quick_start.Dao.EmpDao;
import org.example.quick_start.pojo.Emp;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {

        List<Emp> empList = new ArrayList<>();
        Emp emp1 = new Emp("1","3");
        Emp emp2 = new Emp("2","1");
        Emp emp3 = new Emp("1","2");
        Emp emp4 = new Emp("2","2");
        Emp emp5 = new Emp("1","3");
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);
        empList.add(emp4);
        empList.add(emp5);
        return empList;
    }
    // alt + enter


}
