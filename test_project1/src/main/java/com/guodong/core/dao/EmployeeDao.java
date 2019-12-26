package com.guodong.core.dao;

import com.guodong.core.pojo.Employee;
import com.guodong.core.pojo.EmployeeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {
    int countByExample(EmployeeQuery example);

    int deleteByExample(EmployeeQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeQuery example);

    Employee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeQuery example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeQuery example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}