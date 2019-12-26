package com.unicom.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.pojo.good.Brand;
import com.unicom.core.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BrandController {
	@Reference
	private BrandService brandService;
	@RequestMapping("brand/findAll")
	public ResponseResult findAll(){
		try {
			List<Brand> allBrand = brandService.getAllBrand();
			return new ResponseResult(true,allBrand);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误！");
		}
	}
	@RequestMapping("/brand/findPage")
	public ResponseResult findPage(Integer curPage,Integer pageSize,@RequestBody Brand searchBrand){
		try {
			/*调用业务层，分页获取品牌数据*/
			PageResult result = brandService.findPage(curPage,pageSize,searchBrand);
			return new ResponseResult(true,result);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误！");
		}
	}
	@RequestMapping("/brand/addBrand")
	public ResponseResult addBrand(@RequestBody Brand brand){
		try {
			brandService.addBrand(brand);
			return new ResponseResult(true,"添加成功！");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"添加失败！");
		}
	}
	@RequestMapping("/brand/deleteBrandWithId")
	public ResponseResult deleteBrandWithId (Long[] ids){
		try {
			brandService.deleteBrandWithId(ids);
			return new ResponseResult(true,"删除成功！");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"删除失败！");
		}
	}
	@RequestMapping("/brand/updateBrand")
	public ResponseResult updateBrand (@RequestBody Brand brand){
		try {
			brandService.updateBrand(brand);
			return new ResponseResult(true,"更新成功！");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"更新失败！");
		}
	}
	@RequestMapping("/brand/selectOptionList")
	public  List<Map> selectOptionList(){
		return brandService.selectOptionList();
	}
}
