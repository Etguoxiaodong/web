package com.guodong.core.service.impls;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guodong.core.dao.OwnerDao;
import com.guodong.core.pojo.Owner;
import com.guodong.core.pojo.OwnerQuery;
import com.guodong.core.pojo.PageResult;
import com.guodong.core.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
	@Autowired
	private OwnerDao ownerDao;

	/**
	 * 分页查询业主信息
	 * @param curPage 当前页
	 * @param pageSize 每页显示数
	 * @param searchowner 是否查询特定业主
	 * @return 分页查询结果
	 */
	@Override
	public PageResult findPage(Integer curPage, Integer pageSize, Owner searchowner) {
		//调用分页插件，设置查询数据
		PageHelper.startPage(curPage,pageSize);
		//创建条件查询语句
		OwnerQuery ownerQuery = new OwnerQuery();
		OwnerQuery.Criteria criteria = ownerQuery.createCriteria();
		//检查查询内容是否为空
		if(searchowner != null){
			//添加按姓名查询
			if(searchowner.getName() != null && !"".equals(searchowner.getName())){
				criteria.andNameLike("%"+searchowner.getName()+"%");
			}
			//添加按手机号查询
			if(searchowner.getPhone() != null && !"".equals(searchowner.getPhone())){
				criteria.andPhoneEqualTo(searchowner.getPhone());
			}
		}
		Page<Owner> owners = (Page<Owner>) ownerDao.selectByExample(ownerQuery);
		return new PageResult(owners.getTotal(),owners.getResult());
	}

	/**
	 * 添加业主
	 * @param owner 业主信息
	 */
	@Override
	public void addOwner(Owner owner) {
		ownerDao.insertSelective(owner);
	}

	/**
	 * 更新业主信息
	 * @param owner 业主信息
	 */
	@Override
	public void updateOwner(Owner owner) {
		ownerDao.updateByPrimaryKeySelective(owner);
	}

	/**
	 * 批量删除业主
	 * @param ids 要删除的业主id
	 */
	@Override
	public void deleteOwnerWithId(Integer[] ids) {
		for (Integer id : ids) {
			ownerDao.deleteByPrimaryKey(id);
		}
	}
}
