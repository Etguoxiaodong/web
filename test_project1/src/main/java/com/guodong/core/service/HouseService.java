package com.guodong.core.service;

import com.guodong.core.pojo.House;
import com.guodong.core.pojo.PageResult;

public interface HouseService {
	PageResult findPage(Integer curPage, Integer pageSize, House house);

	void addHouse(House house);

	void updateHouse(House house);

	void deleteHouseWithId(Long[] ids);

	Integer getAllHouse();
}
