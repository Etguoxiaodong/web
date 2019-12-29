package com.guodong.core.web;

import com.guodong.core.pojo.Managementfee;
import com.guodong.core.pojo.PageResult;
import com.guodong.core.pojo.ResponseResult;
import com.guodong.core.service.ManagementfeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mana")
public class ManagementfeeController {
	@Autowired
	private ManagementfeeService manaService;
	@RequestMapping("/findPage")
	public ResponseResult findPage(Integer curPage, Integer pageSize, @RequestBody Managementfee managementfee){
		try {
			PageResult result = manaService.findPage(curPage,pageSize,managementfee);
			return new ResponseResult(true,result);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false,"请求错误");
		}
	}
}
