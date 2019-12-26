package com.unicom.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unicom.core.dao.template.TypeTemplateDao;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.template.TypeTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService{
	@Autowired
	private TypeTemplateDao templateDao;
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
		return null;
	}

	@Override
	public List<Map> findBySpecList(Long id) {
		return null;
	}
}
