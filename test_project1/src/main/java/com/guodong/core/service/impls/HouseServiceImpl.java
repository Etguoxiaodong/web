package com.guodong.core.service.impls;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guodong.core.dao.HouseDao;
import com.guodong.core.pojo.House;
import com.guodong.core.pojo.HouseQuery;
import com.guodong.core.pojo.PageResult;
import com.guodong.core.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseDao houseDao;
	@Override
	public PageResult findPage(Integer curPage, Integer pageSize, House house) {
		//调用分页插件，设置查询数据
		PageHelper.startPage(curPage,pageSize);
		HouseQuery houseQuery = new HouseQuery();
		HouseQuery.Criteria criteria = houseQuery.createCriteria();
		if(house != null){
			if(house.getBuilding() != null && !"".equals(house.getBuilding())){
				criteria.andBuildingEqualTo(house.getBuilding());
			}
			if(house.getUnit() != null && !"".equals(house.getUnit())){
				criteria.andUnitEqualTo(house.getUnit());
			}
		}
		Page<House> houseList = (Page<House>) houseDao.selectByExample(houseQuery);
		return new PageResult(houseList.getTotal(),houseList.getResult());
	}

	@Override
	public void addHouse(House house) {
		houseDao.insertSelective(house);
	}

	@Override
	public void updateHouse(House house) {
		houseDao.updateByPrimaryKeySelective(house);
	}

	@Override
	public void deleteHouseWithId(Long[] ids) {
		for (Long id : ids) {
			houseDao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public Integer getAllHouse() {
		return houseDao.countByExample(null);
	}

	@Override
	public PageResult getHouseList() {
		Page<House> houses = (Page<House>) houseDao.selectByExample(null);
		return new PageResult(houses.getTotal(),houses.getResult());
	}
}
