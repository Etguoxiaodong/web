package com.unicom.core.service;

import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.template.TypeTemplate;

import java.util.List;
import java.util.Map;

public interface TemplateService {
	PageResult findPage(Integer page, Integer pageSize, TypeTemplate template);

	void saveTemplate(TypeTemplate template);

	void deleteTempWithId(Long[] ids);

	TypeTemplate findOneTemplate(Long id);

	List<Map> findBySpecList(Long id);
}
