package com.guodong.core.web;

import com.guodong.core.pojo.HomeEntity;
import com.guodong.core.pojo.ResponseResult;
import com.guodong.core.service.EmployeeService;
import com.guodong.core.service.HouseService;
import com.guodong.core.service.ManagementfeeService;
import com.guodong.core.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private HouseService houseService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ManagementfeeService managementfeeService;
	@RequestMapping("/getData")
	public ResponseResult getData(){
		try {
			HomeEntity homeEntity = new HomeEntity();
			homeEntity.setTotalowner(ownerService.getAllOwner());
			homeEntity.setTotalhouse(houseService.getAllHouse());
			homeEntity.setTotalemployee(employeeService.getAllEmployee());
			homeEntity.setNotmanagefee(managementfeeService.getNotManagefee());
			return new ResponseResult(true,homeEntity);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误");
		}
	}
}
