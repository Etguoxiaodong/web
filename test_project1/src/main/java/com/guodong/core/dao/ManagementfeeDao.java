package com.guodong.core.dao;

import com.guodong.core.pojo.Managementfee;
import com.guodong.core.pojo.ManagementfeeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagementfeeDao {
    int countByExample(ManagementfeeQuery example);

    int deleteByExample(ManagementfeeQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Managementfee record);

    int insertSelective(Managementfee record);

    List<Managementfee> selectByExample(ManagementfeeQuery example);

    Managementfee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Managementfee record, @Param("example") ManagementfeeQuery example);

    int updateByExample(@Param("record") Managementfee record, @Param("example") ManagementfeeQuery example);

    int updateByPrimaryKeySelective(Managementfee record);

    int updateByPrimaryKey(Managementfee record);
}