package com.guodong.core.service.impls;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guodong.core.dao.DepartmentDao;
import com.guodong.core.pojo.Department;
import com.guodong.core.pojo.PageResult;
import com.guodong.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	@Override
	public PageResult findPage(Integer curPage, Integer pageSize) {
		PageHelper.startPage(curPage,pageSize);
		Page<Department> departments = (Page<Department>) departmentDao.selectByExample(null);
		return new PageResult(departments.getTotal(),departments.getResult());
	}

	@Override
	public void addDep(Department department) {
		departmentDao.insertSelective(department);
	}

	@Override
	public void updateDep(Department department) {
		departmentDao.updateByPrimaryKeySelective(department);
	}
}
