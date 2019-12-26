package com.unicom.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unicom.core.dao.seller.SellerDao;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.seller.Seller;
import com.unicom.core.pojo.seller.SellerQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService{
	@Autowired
	private SellerDao sellerDao;
	@Override
	public void add(Seller seller) {
		seller.setCreateTime(new Date());
		seller.setStatus("0");
		sellerDao.insertSelective(seller);
	}

	@Override
	public PageResult findPage(Integer page, Integer pageSize, Seller seller) {
		//分页查询
		PageHelper.startPage(page,pageSize);
		//设置查询条件
		SellerQuery sellerQuery = new SellerQuery();
		if (seller != null) {
			SellerQuery.Criteria criteria = sellerQuery.createCriteria();
			if (seller.getStatus() != null && !"".equals(seller.getStatus())) {
				criteria.andStatusEqualTo(seller.getStatus());
			}
			if (seller.getName() != null && !"".equals(seller.getName())){
				criteria.andNameLike("%"+seller.getName()+"%");
			}
			if (seller.getNickName() != null && !"".equals(seller.getNickName())) {
				criteria.andNickNameLike("%"+seller.getNickName()+"%");
			}
		}
		//开始查询
		Page<Seller> sellers = (Page<Seller>) sellerDao.selectByExample(sellerQuery);
		//封装成分页对象并返回
		return new PageResult(sellers.getTotal(),sellers.getResult());
	}

	@Override
	public void updateStatus(Seller seller) {
		//更新数据库操作
		sellerDao.updateByPrimaryKeySelective(seller);
		/*通知用户审核结果*/
		String msg = null;
		if("1".equals(seller.getStatus())){
			msg = "恭喜您，审核通过";
		}else if("2".equals(seller.getStatus())){
			msg ="审核未通过，请联系运营商";
		}else if("3".equals(seller.getStatus())){
			msg ="商家账号被冻结，请联系运营商";
		}
		String licenseNumber = seller.getLicenseNumber();
		/*调用短信接口，发送msg*/
	}

}
