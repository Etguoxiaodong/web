package com.guodong.core.dao;

import com.guodong.core.pojo.House;
import com.guodong.core.pojo.HouseQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseDao {
    int countByExample(HouseQuery example);

    int deleteByExample(HouseQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseQuery example);

    House selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseQuery example);

    int updateByExample(@Param("record") House record, @Param("example") HouseQuery example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
}