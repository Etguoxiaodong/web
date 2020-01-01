package com.guodong.core.dao;

import com.guodong.core.pojo.Department;
import com.guodong.core.pojo.DepartmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentDao {
    int countByExample(DepartmentQuery example);

    int deleteByExample(DepartmentQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentQuery example);

    Department selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentQuery example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentQuery example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}