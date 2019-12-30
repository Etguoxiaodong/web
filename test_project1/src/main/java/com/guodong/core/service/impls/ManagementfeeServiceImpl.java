package com.guodong.core.service.impls;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guodong.core.dao.HouseDao;
import com.guodong.core.dao.ManagementfeeDao;
import com.guodong.core.dao.OwnerDao;
import com.guodong.core.pojo.*;
import com.guodong.core.service.ManagementfeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagementfeeServiceImpl implements ManagementfeeService {
	@Autowired
	private ManagementfeeDao manaDao;
	@Autowired
	private HouseDao houseDao;
	@Autowired
	private OwnerDao ownerDao;
	@Override
	public PageResult findPage(Integer curPage, Integer pageSize, Managementfee managementfee) {
		PageHelper.startPage(curPage,pageSize);
		ManagementfeeQuery managementfeeQuery = new ManagementfeeQuery();
		ManagementfeeQuery.Criteria criteria = managementfeeQuery.createCriteria();
		if(managementfee != null){
			if(managementfee.getYear() != null && !"".equals(managementfee.getYear())){
				criteria.andYearEqualTo(managementfee.getYear());
			}
			if(managementfee.getStatus() != null ){
				criteria.andStatusEqualTo(managementfee.getStatus());
			}
		}
		Page<Managementfee> list = (Page<Managementfee>) manaDao.selectByExample(managementfeeQuery);
		return new PageResult(list.getTotal(),list.getResult());
	}

	@Override
	public Integer getNotManagefee() {
		ManagementfeeQuery managementfeeQuery = new ManagementfeeQuery();
		ManagementfeeQuery.Criteria criteria = managementfeeQuery.createCriteria();
		criteria.andStatusEqualTo("0");
		return manaDao.countByExample(managementfeeQuery);
	}
}
