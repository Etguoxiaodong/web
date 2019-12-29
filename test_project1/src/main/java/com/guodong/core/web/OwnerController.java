package com.guodong.core.web;

import com.guodong.core.dao.OwnerDao;
import com.guodong.core.pojo.Owner;
import com.guodong.core.pojo.PageResult;
import com.guodong.core.pojo.ResponseResult;
import com.guodong.core.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/owner")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;
	@RequestMapping("/findPage")
	public ResponseResult findPage(Integer curPage, Integer pageSize, @RequestBody Owner searchowner){
		try {
			PageResult result = ownerService.findPage(curPage,pageSize,searchowner);
			return new ResponseResult(true,result);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误");
		}
	}
	@RequestMapping("/add")
	public ResponseResult addOwner(@RequestBody Owner owner){
		try {
			ownerService.addOwner(owner);
			return new ResponseResult(true,"添加成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"添加失败");
		}
	}
	@RequestMapping("/update")
	public ResponseResult updateOwner(@RequestBody Owner curowner){
		try {
			ownerService.updateOwner(curowner);
			return new ResponseResult(true,"更新成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"更新失败");
		}
	}
	@RequestMapping("deleteOwnerWithId")
	public ResponseResult deleteOwnerWithId(Integer[] ids){
		try {
			ownerService.deleteOwnerWithId(ids);
			return new ResponseResult(true,"删除成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误");
		}
	}
}
