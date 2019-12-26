package com.unicom.core.service;

import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.entity.SpecEntity;
import com.unicom.core.pojo.specification.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {
	PageResult findPage(Integer curPage, Integer pageSize, Specification specSpec);

	void saveSpecAndSpecOption(SpecEntity specEntity);

	SpecEntity finOneSpec(Long id);

	void updateSpec(SpecEntity specEntity);

	void deleteSpec(Long[] ids);

	List<Map> selectOptionList();

}
