package com.unicom.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.pojo.entity.SpecEntity;
import com.unicom.core.pojo.good.Brand;
import com.unicom.core.pojo.specification.Specification;
import com.unicom.core.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spec")
public class SpecController {
	@Reference
	private SpecificationService specificationService;
	@RequestMapping("/findPage")
	public ResponseResult findPage(Integer page, Integer pageSize, @RequestBody Specification specSpec){
		try {
			/*调用业务层，分页获取品牌数据*/
			PageResult result = specificationService.findPage(page,pageSize,specSpec);
			return new ResponseResult(true,result);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误！");
		}
	}
	@RequestMapping("/add")
	public ResponseResult add(@RequestBody SpecEntity specEntity){
		try {
			specificationService.saveSpecAndSpecOption(specEntity);
			return new ResponseResult(true,"保存成功！");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"请求错误！");
		}
	}
	@RequestMapping("/findOne")
	public SpecEntity findOne(Long id){
		SpecEntity specEntity = specificationService.finOneSpec(id);
		return specEntity;
	}
	@RequestMapping("/update")
	public ResponseResult update(@RequestBody SpecEntity specEntity){
		try {
			specificationService.updateSpec(specEntity);
			return new ResponseResult(true,"更新成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"更新失败！");
		}
	}
	@RequestMapping("/delete")
	public  ResponseResult delete(Long[] ids){
		try {
			specificationService.deleteSpec(ids);
			return new ResponseResult(true,"删除成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"删除失败");
		}
	}
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList(){
		return specificationService.selectOptionList();
	}
}
