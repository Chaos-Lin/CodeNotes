package org.example.demo.mapper;

import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.example.demo.pojo.Emp;

import java.util.List;

@Mapper//在运行时会自动生成该接口的实现类，并且将对象交给IOC容器管理
public interface EmpMapper {
//    @Select("SELECT * from tlias.emp")
    public List<Emp> list();

    @Delete("delete from tlias.emp where id = #{id}")
    public void delete(Integer id);

    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into tlias.emp(username, name, gender, image,job, entrydate, dept_id, create_time, update_time) " +
            "values(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
            public void insert(Emp emp);

    @Update("update emp set username=#{username}, name=#{name}, gender=#{gender}, image=#{image}, job=#{job}, entrydate=#{entrydate}, dept_id=#{deptId}, update_time=#{updateTime} where id=#{id}")
    public void update(Emp emp);

    @Results({
            @Result(column = "dept_id",property = "deptId"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })
    @Select("select * from tlias.emp where id=#{id}")
    public void getById(Integer id);

    public void deleteById(List<Integer> ids);
}
