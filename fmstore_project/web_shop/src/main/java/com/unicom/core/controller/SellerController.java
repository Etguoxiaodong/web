package com.unicom.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.pojo.seller.Seller;
import com.unicom.core.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
	@Reference
	private SellerService sellerService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@RequestMapping("/add")
	public ResponseResult add(@RequestBody Seller seller){
		try {
			String password = seller.getPassword();
			String encodePassword = passwordEncoder.encode(password);
			seller.setPassword(encodePassword);
			sellerService.add(seller);
			return new ResponseResult(true,"注册成功,请等待审核");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"注册失败，请重试");
		}
	}
}
