package com.unicom.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.pojo.template.TypeTemplate;
import com.unicom.core.service.TemplateService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TemplateController {
	@Reference
	private TemplateService templateService;
	@RequestMapping("/search")
	public ResponseResult search(Integer page, Integer pageSize, @RequestBody TypeTemplate template){
		PageResult pageResult = templateService.findPage(page,pageSize,template);
		return new ResponseResult(true,pageResult);
	}
	@RequestMapping("/add")
	public ResponseResult add(@RequestBody TypeTemplate template) {
		try {
			templateService.saveTemplate(template);
			return new ResponseResult(true, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "添加失败");
		}
	}
	@RequestMapping("deleteTempWithId")
	public ResponseResult deleteTempWithId(Long[] ids){
		try {
			templateService.deleteTempWithId(ids);
			return new ResponseResult(true, "删除成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false, "删除失败");
		}

	}
}
