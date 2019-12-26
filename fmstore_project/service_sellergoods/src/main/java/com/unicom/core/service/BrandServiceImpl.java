package com.unicom.core.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unicom.core.dao.good.BrandDao;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.good.Brand;
import com.unicom.core.pojo.good.BrandQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandDao brandDao;
	@Override
	public List<Brand> getAllBrand() {
		/*调用DAO查询全部品牌*/
		List<Brand> brands = brandDao.selectByExample(null);
		return brands;
	}

	@Override
	public PageResult findPage(Integer curPage, Integer pageSize,Brand searchBrand) {
		PageHelper.startPage(curPage,pageSize);//调用分页插件，设置查询数据
		BrandQuery brandQuery = new BrandQuery();
		BrandQuery.Criteria criteria = brandQuery.createCriteria();
		if (searchBrand != null) {
			if (searchBrand.getName() != null && !"".equals(searchBrand.getName())) {
				criteria.andNameLike("%"+searchBrand.getName()+"%");
			}
			if (searchBrand.getFirstChar() != null && !"".equals(searchBrand.getFirstChar())) {
				criteria.andFirstCharLike("%"+searchBrand.getFirstChar()+"%");
			}
		}
		Page<Brand> brandList = (Page<Brand>) brandDao.selectByExample(brandQuery);
		return new PageResult(brandList.getTotal(), brandList.getResult());
	}

	@Override
	public void addBrand(Brand brand) {
		brandDao.insert(brand);
	}

	@Override
	public void deleteBrandWithId(Long[] ids) {
		for (Long id : ids) {
			brandDao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void updateBrand(Brand brand) {
		/*带Seletive的只更新传入的有的字段，传入对象为null的字段
		* 不会更新到数据库*/
		brandDao.updateByPrimaryKeySelective(brand);
	}

	@Override
	public List<Map> selectOptionList() {
		return brandDao.selectOptionList();
	}
}
