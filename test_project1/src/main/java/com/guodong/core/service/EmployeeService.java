package com.guodong.core.service;

import com.guodong.core.pojo.Employee;
import com.guodong.core.pojo.PageResult;

public interface EmployeeService {
	PageResult findPage(Integer curPage, Integer pageSize, Employee employee);

	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployeeWithId(Integer[] ids);
}
