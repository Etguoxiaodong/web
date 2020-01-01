package com.guodong.core.web;

import com.guodong.core.pojo.Employee;
import com.guodong.core.pojo.PageResult;
import com.guodong.core.pojo.ResponseResult;
import com.guodong.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
@RequestMapping("/empl")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@RequestMapping("/findPage")
	public ResponseResult findPage(Integer curPage, Integer pageSize, @RequestBody Employee employee){
		try {
			PageResult result = employeeService.findPage(curPage,pageSize,employee);
			return new ResponseResult(true,result);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误");
		}
	}
	@RequestMapping("/addEmployee")
	public ResponseResult addEmployee(@RequestBody Employee employee){
		try {
			employeeService.addEmployee(employee);
			return new ResponseResult(true,"添加成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"添加失败");
		}
	}
	@RequestMapping("/updateEmployee")
	public ResponseResult updateEmployee(@RequestBody Employee employee){
		try {
			employeeService.updateEmployee(employee);
			return new ResponseResult(true,"更新成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"更新失败");
		}
	}
	@RequestMapping("/deleteEmployeeWithId")
	public ResponseResult deleteEmployeeWithId(Long[] ids){
		try {
			employeeService.deleteEmployeeWithId(ids);
			return new ResponseResult(true,"删除成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"删除失败");
		}
	}
}
