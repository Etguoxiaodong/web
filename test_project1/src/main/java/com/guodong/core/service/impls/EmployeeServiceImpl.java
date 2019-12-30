package com.guodong.core.service.impls;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guodong.core.dao.EmployeeDao;
import com.guodong.core.pojo.Employee;
import com.guodong.core.pojo.EmployeeQuery;
import com.guodong.core.pojo.PageResult;
import com.guodong.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public PageResult findPage(Integer curPage, Integer pageSize, Employee employee) {
		PageHelper.startPage(curPage,pageSize);
		EmployeeQuery employeeQuery = new EmployeeQuery();
		EmployeeQuery.Criteria criteria = employeeQuery.createCriteria();
		if (employee != null){
			if(employee.getDepartment() != null && !"".equals(employee.getDepartment())){
				criteria.andDepartmentLike("%"+employee.getDepartment()+"%");
			}
			if(employee.getName() != null && !"".equals(employee.getName())){
				criteria.andNameLike("%"+employee.getName()+"%");
			}
		}
		Page<Employee> employees = (Page<Employee>) employeeDao.selectByExample(employeeQuery);
		return new PageResult(employees.getTotal(),employees.getResult());
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeDao.insertSelective(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDao.updateByPrimaryKeySelective(employee);
	}

	@Override
	public void deleteEmployeeWithId(Integer[] ids) {
		for (Integer id : ids) {
			employeeDao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public Integer getAllEmployee() {
		return employeeDao.countByExample(null);
	}
}
