package com.guodong.core.web;

import com.guodong.core.pojo.Department;
import com.guodong.core.pojo.PageResult;
import com.guodong.core.pojo.ResponseResult;
import com.guodong.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dep")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/findPage")
	public ResponseResult findPage(Integer curPage,Integer pageSize){
		try {
			PageResult result = departmentService.findPage(curPage,pageSize);
			return new ResponseResult(true,result);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误");
		}
	}
	@RequestMapping("/getDepartmentList")
	public ResponseResult getDepartmentList(){
		try {
			List<Department> departmentList = departmentService.getDepartmentList();
			return new ResponseResult(true,departmentList);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"获取部门列表失败");
		}
	}
	@RequestMapping("/addDep")
	public ResponseResult addDep(@RequestBody Department department){
		try {
			departmentService.addDep(department);
			return new ResponseResult(true,"添加成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"添加失败");
		}
	}
	@RequestMapping("/updateDep")
	public ResponseResult updateDep(@RequestBody Department department){
		try {
			departmentService.updateDep(department);
			return new ResponseResult(true,"更新成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"更新失败");
		}
	}
	@RequestMapping("/deleteDepWithId")
	public ResponseResult deleteDepWithId(Long[] ids){
		try {
			departmentService.deleteDepWithId(ids);
			return new ResponseResult(true,"删除成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"删除失败");
		}
	}
}
