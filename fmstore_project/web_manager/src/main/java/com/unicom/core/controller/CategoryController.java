package com.unicom.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.unicom.core.pojo.ad.ContentCategory;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.service.CategoryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contentCategory")
public class CategoryController {

    @Reference
    private CategoryService categoryService;

    @RequestMapping("/search")
    public PageResult search(@RequestBody ContentCategory category, Integer page, Integer rows) {
        PageResult result = categoryService.findPage(category, page, rows);
        return result;
    }


    @RequestMapping("/add")
    public ResponseResult add(@RequestBody ContentCategory category) {
        try {
            categoryService.add(category);
            return new ResponseResult(true, "保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false, "保存失败!");
        }
    }

    @RequestMapping("/findOne")
    public ContentCategory findOne(Long id) {
        ContentCategory one = categoryService.findOne(id);
        return one;
    }

    @RequestMapping("/update")
    public ResponseResult update(@RequestBody ContentCategory category) {
        try {
            categoryService.update(category);
            return new ResponseResult(true, "修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false, "修改失败!");
        }
    }
    @RequestMapping("/delete")
    public ResponseResult delete(Long[] ids) {
        try {
            categoryService.delete(ids);
            return new ResponseResult(true, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false, "删除失败!");
        }
    }

    @RequestMapping("/findAll")
    public List<ContentCategory> findAll(){
        return  this.categoryService.findAll();
    }





}