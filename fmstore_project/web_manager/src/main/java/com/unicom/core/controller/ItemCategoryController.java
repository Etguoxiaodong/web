package com.unicom.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.pojo.item.ItemCat;
import com.unicom.core.service.ItemCatService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class ItemCategoryController {
	@Reference
	private ItemCatService itemCatService;
	@RequestMapping("/findByParentId")
	public List<ItemCat> findByParentId(Long parentId){

		return itemCatService.findByParentId(parentId);
	}
	@RequestMapping("/saveCategory")
	public ResponseResult saveCategory(@RequestBody ItemCat category){
		try {
			itemCatService.saveCategory(category);
			return new ResponseResult(true,"保存成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"保存失败");
		}
	}
	@RequestMapping("/deleteCatWithId")
	public ResponseResult deleteCatWithId (Long[] ids){
		try {
			itemCatService.deleteCatWithId(ids);
			return new ResponseResult(true,"删除成功！");
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseResult(false,"删除失败！");
		}
	}
}
