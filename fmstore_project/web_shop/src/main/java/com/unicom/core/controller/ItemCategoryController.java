package com.unicom.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.pojo.item.ItemCat;
import com.unicom.core.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class ItemCategoryController {
	@Autowired
	private ItemCatService itemCatService;
	@RequestMapping("/findByParentId")
	public List<ItemCat> findByParentId(Long parentId){

		return itemCatService.findByParentId(parentId);
	}
	@RequestMapping("/findOneCategory")
	public ItemCat findOneCategory(Long id){
		ItemCat itemCat = itemCatService.findOneCategory(id);
		return itemCat;
	}
	@RequestMapping("findAll")
	public List<ItemCat> findAll(){
		return itemCatService.findAll();
	}
}
