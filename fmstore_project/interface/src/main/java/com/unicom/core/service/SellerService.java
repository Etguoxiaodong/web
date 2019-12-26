package com.unicom.core.service;

import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.seller.Seller;

public interface SellerService {
	void add(Seller seller);

	PageResult findPage(Integer page, Integer pageSize, Seller seller);

	void updateStatus(Seller seller);

}
