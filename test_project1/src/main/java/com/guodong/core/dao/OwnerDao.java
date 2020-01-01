package com.guodong.core.dao;

import com.guodong.core.pojo.Owner;
import com.guodong.core.pojo.OwnerQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OwnerDao {
    int countByExample(OwnerQuery example);

    int deleteByExample(OwnerQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Owner record);

    int insertSelective(Owner record);

    List<Owner> selectByExample(OwnerQuery example);

    Owner selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Owner record, @Param("example") OwnerQuery example);

    int updateByExample(@Param("record") Owner record, @Param("example") OwnerQuery example);

    int updateByPrimaryKeySelective(Owner record);

    int updateByPrimaryKey(Owner record);
}