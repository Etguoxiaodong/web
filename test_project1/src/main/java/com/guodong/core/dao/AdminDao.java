package com.guodong.core.dao;

import com.guodong.core.pojo.Admin;
import com.guodong.core.pojo.AdminQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    int countByExample(AdminQuery example);

    int deleteByExample(AdminQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminQuery example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminQuery example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminQuery example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}