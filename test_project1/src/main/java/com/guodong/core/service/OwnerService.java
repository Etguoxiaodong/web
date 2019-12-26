package com.guodong.core.service;

import com.guodong.core.pojo.Owner;
import com.guodong.core.pojo.PageResult;

public interface OwnerService {
	PageResult findPage(Integer curPage, Integer pageSize, Owner searchowner);

	void addOwner(Owner owner);

	void updateOwner(Owner owner);

	void deleteOwnerWithId(Integer[] ids);
}
