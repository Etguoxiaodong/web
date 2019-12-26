package com.unicom.core.service;

import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.good.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
	List<Brand> getAllBrand();

	/**
	 * 分页查询品牌数据
	 * @param page 当前页
	 * @param rows 每页记录数
	 * @return
	 */
	PageResult findPage(Integer page,Integer rows,Brand searchBrand);

	void addBrand(Brand brand);

	void deleteBrandWithId(Long[] ids);

	void updateBrand(Brand brand);

	List<Map> selectOptionList();
}
