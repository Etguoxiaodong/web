package com.guodong.core.web;

import com.guodong.core.pojo.House;
import com.guodong.core.pojo.PageResult;
import com.guodong.core.pojo.ResponseResult;
import com.guodong.core.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
	@Autowired
	private HouseService houseService;
	@RequestMapping("/findPage")
	public ResponseResult findPage(Integer curPage, Integer pageSize, @RequestBody House house){
		try {
			PageResult result = houseService.findPage(curPage,pageSize,house);
			return new ResponseResult(true,result);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误");
		}
	}
	@RequestMapping("/addHouse")
	public ResponseResult addHouse(@RequestBody House house){
		try {
			houseService.addHouse(house);
			return new ResponseResult(true,"添加成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"添加失败");
		}
	}
	@RequestMapping("/updateHouse")
	public ResponseResult updateHouse(@RequestBody House house){
		try {
			houseService.updateHouse(house);
			return new ResponseResult(true,"更新成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"更新失败");
		}
	}
	@RequestMapping("/deleteHouseWithId")
	public ResponseResult deleteHouseWithId(Long[] ids){
		try {
			houseService.deleteHouseWithId(ids);
			return new ResponseResult(true,"删除成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误");
		}
	}
}
