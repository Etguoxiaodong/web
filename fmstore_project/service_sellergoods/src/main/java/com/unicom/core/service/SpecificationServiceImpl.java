package com.unicom.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unicom.core.dao.specification.SpecificationDao;
import com.unicom.core.dao.specification.SpecificationOptionDao;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.entity.SpecEntity;
import com.unicom.core.pojo.specification.Specification;
import com.unicom.core.pojo.specification.SpecificationOption;
import com.unicom.core.pojo.specification.SpecificationOptionQuery;
import com.unicom.core.pojo.specification.SpecificationQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService{
	@Autowired
	private SpecificationDao specDao;
	@Autowired
	private SpecificationOptionDao optionDao;
	@Override
	public PageResult findPage(Integer curPage, Integer pageSize, Specification specSpec) {
		PageHelper.startPage(curPage,pageSize);
		SpecificationQuery query = new SpecificationQuery();
		SpecificationQuery.Criteria criteria = query.createCriteria();
		if (specSpec != null) {
			if (specSpec.getSpecName() != null && !"".equals(specSpec.getSpecName())) {
				criteria.andSpecNameLike("%"+specSpec.getSpecName()+"%");
			}
		}
		Page<Specification> specList = (Page<Specification>)specDao.selectByExample(query);

		return new PageResult(specList.getTotal(), specList.getResult());
	}

	@Override
	public void saveSpecAndSpecOption(SpecEntity specEntity) {
		/*先保存规格*/
		Specification specification = specEntity.getSpecification();
		specDao.insertSelective(specification);
		/*保存规格选项之前要先获取规格id,修改上面的insert方法的sql语句，*/
		/*在语句的属性里加入useGeneratedKeys="true" keyProperty="id"*/
		Long id = specification.getId();
		/*保存规格选项*/
		if(specEntity.getSpecOptionList() != null){
			/*取出每个规格选项，保存到数据库*/
			for (SpecificationOption specificationOption : specEntity.getSpecOptionList()) {
				/*设置规格选项所属规格的id*/
				specificationOption.setSpecId(id);
				optionDao.insertSelective(specificationOption);
			}
		}
	}

	@Override
	public SpecEntity finOneSpec(Long id) {
		/*根据id查询规格*/
		Specification specification = specDao.selectByPrimaryKey(id);
		/*根据规格id查询所有规格选项*/
		SpecificationOptionQuery optionQuery = new SpecificationOptionQuery();
		SpecificationOptionQuery.Criteria criteria = optionQuery.createCriteria();
		criteria.andSpecIdEqualTo(id);
		List<SpecificationOption> optionList = optionDao.selectByExample(optionQuery);

		/*把规格和规格选项封装成SpecEntity,并返回*/

		SpecEntity specEntity = new SpecEntity();
		specEntity.setSpecification(specification);
		specEntity.setSpecOptionList(optionList);
		return specEntity;
	}

	@Override
	public void updateSpec(SpecEntity specEntity) {
		/*更新规格*/
		specDao.updateByPrimaryKeySelective(specEntity.getSpecification());
		/*打破规格选项与规格的关系*/
		SpecificationOptionQuery optionQuery = new SpecificationOptionQuery();
		SpecificationOptionQuery.Criteria criteria = optionQuery.createCriteria();
		/*先删除所有的规格选项*/
		criteria.andSpecIdEqualTo(specEntity.getSpecification().getId());
		optionDao.deleteByExample(optionQuery);
		/*把新的规格选项再添加进去*/
		for (SpecificationOption specificationOption : specEntity.getSpecOptionList()) {
			specificationOption.setSpecId(specEntity.getSpecification().getId());
			optionDao.insertSelective(specificationOption);
		}
	}

	@Override
	public void deleteSpec(Long[] ids) {

		/*删除规格和规格选项*/
		for (Long id : ids) {
			/*先删除规格选项*/
			SpecificationOptionQuery optionQuery = new SpecificationOptionQuery();
			SpecificationOptionQuery.Criteria criteria = optionQuery.createCriteria();
			criteria.andSpecIdEqualTo(id);
			optionDao.deleteByExample(optionQuery);
			/*后删除规格，防止DB中存在约束条件，导致删除失败*/
			specDao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<Map> selectOptionList() {
		return specDao.selectOptionList();
	}
}
