package com.unicom.core.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unicom.core.dao.specification.SpecificationOptionDao;
import com.unicom.core.dao.template.TypeTemplateDao;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.specification.SpecificationOption;
import com.unicom.core.pojo.specification.SpecificationOptionQuery;
import com.unicom.core.pojo.template.TypeTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService{
	@Autowired
	private TypeTemplateDao templateDao;
	@Autowired
	private SpecificationOptionDao specOptionDao;
	@Override
	public PageResult findPage(Integer page, Integer pageSize, TypeTemplate template) {
		PageHelper.startPage(page,pageSize);
		Page<TypeTemplate> typeTemplates = (Page<TypeTemplate>) templateDao.selectByExample(null);
		return new PageResult(typeTemplates.getTotal(),typeTemplates.getResult());
	}

	@Override
	public void saveTemplate(TypeTemplate template) {
		templateDao.insertSelective(template);
	}

	@Override
	public void deleteTempWithId(Long[] ids) {
		for (Long id : ids) {
			templateDao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public TypeTemplate findOneTemplate(Long id) {
		TypeTemplate typeTemplate = templateDao.selectByPrimaryKey(id);
		return typeTemplate;
	}

	@Override
	public List<Map> findBySpecList(Long id) {
		TypeTemplate typeTemplate = templateDao.selectByPrimaryKey(id);
		String specIds = typeTemplate.getSpecIds();
		List<Map> mapsList = JSON.parseArray(specIds, Map.class);
		if(mapsList != null){
			for (Map map : mapsList) {
				//获取规格id
				long specId = Long.parseLong(String.valueOf(map.get("id")));
				//查询对应的规格选项
				SpecificationOptionQuery optionQuery = new SpecificationOptionQuery();
				SpecificationOptionQuery.Criteria criteria = optionQuery.createCriteria();
				criteria.andSpecIdEqualTo(specId);
				List<SpecificationOption> options = specOptionDao.selectByExample(optionQuery);
				map.put("options",options);
			}
		}
		return mapsList;
	}
}
