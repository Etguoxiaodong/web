package com.guodong.core.service;

import com.guodong.core.pojo.Managementfee;
import com.guodong.core.pojo.PageResult;

public interface ManagementfeeService {
	PageResult findPage(Integer curPage, Integer pageSize, Managementfee managementfee);

	Integer getNotManagefee();
}

