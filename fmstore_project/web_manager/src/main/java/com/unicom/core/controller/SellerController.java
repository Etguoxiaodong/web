package com.unicom.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.pojo.seller.Seller;
import com.unicom.core.service.SellerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
	@Reference
	private SellerService sellerService;
	@RequestMapping("/findPage")
	public ResponseResult findPage(Integer page, Integer pageSize, @RequestBody Seller seller){
		PageResult pageResult = sellerService.findPage(page,pageSize,seller);
		return new ResponseResult(true,pageResult);
	}
	@RequestMapping("updateStatus")
	public ResponseResult updateStatus(@RequestBody Seller seller){
		try {
			sellerService.updateStatus(seller);
			return new ResponseResult(true,"审核成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(true,"审核失败");
		}

	}
}
