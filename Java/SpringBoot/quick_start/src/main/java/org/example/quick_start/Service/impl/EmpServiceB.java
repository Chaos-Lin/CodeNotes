package org.example.quick_start.Service.impl;

import org.example.quick_start.Dao.EmpDao;
import org.example.quick_start.Service.EmpService;
import org.example.quick_start.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class EmpServiceB implements EmpService {
    @Autowired
    private EmpDao empDao;
    @Override
    public List<Emp> listEmp() {
        List<Emp> empList = empDao.listEmp();
        empList.stream().forEach(emp -> {
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("male");
            }else if("2".equals(gender)){
                emp.setGender("female");
            }

            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("teacher");
            }else if("2".equals(job)){
                emp.setJob("Student");
            }else if("3".equals(job)){
                emp.setJob("assistant");
            }
        });
        return empList;
    }
}
