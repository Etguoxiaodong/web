package com.unicom.core.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unicom.core.pojo.entity.GoodsEntity;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.pojo.good.Goods;
import com.unicom.core.pojo.good.GoodsQuery;
import com.unicom.core.service.GoodsService;
import org.opensaml.xml.encryption.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@RequestMapping("/add")
	public ResponseResult add(@RequestBody GoodsEntity goodsEntity) {
		try {
			//获取登录用户名
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			//设置这个商品添加的用户名
			goodsEntity.getGoods().setSellerId(userName);
			goodsService.add(goodsEntity);
			return new ResponseResult(true, "添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "添加失败!");
		}
	}
	@RequestMapping("/search")
	public PageResult search(@RequestBody Goods goods, Integer page , Integer rows) {
		//获取当前登录用户的用户名
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		goods.setSellerId(userName);
		PageResult result = goodsService.findPage(goods, page, rows);
		return result;
	}
	@RequestMapping("/findOne")
	public GoodsEntity findOne(Long id) {
		return goodsService.findOne(id);
	}

	@RequestMapping("/update")
	public ResponseResult update(@RequestBody GoodsEntity goodsEntity) {
		try {
			//获取当前登录用户的用户名
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			//获取这个商品的所有者
			String sellerId = goodsEntity.getGoods().getSellerId();
			if (!userName.equals(sellerId)) {
				return new ResponseResult(false, "您没有权限修改此商品!");
			}
			goodsService.update(goodsEntity);
			return new ResponseResult(true, "修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "修改失败!");
		}
	}
	@RequestMapping("/delete")
	public ResponseResult delete(Long[] ids) {
		try {
			goodsService.delete(ids);
			return  new ResponseResult(true, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResponseResult(false, "删除失败!");
		}
	}
}
