package com.guodong.core.service;

import com.guodong.core.pojo.Department;
import com.guodong.core.pojo.PageResult;

public interface DepartmentService {
	PageResult findPage(Integer curPage, Integer pageSize);

	void addDep(Department department);

	void updateDep(Department department);
}
