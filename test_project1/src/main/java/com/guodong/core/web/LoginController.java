package com.guodong.core.web;


import com.guodong.core.pojo.ResponseResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
	@RequestMapping("/loginName")
	public ResponseResult loginName(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseResult(true,name);
	}
}
