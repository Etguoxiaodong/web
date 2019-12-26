package com.unicom.core.controller;

import com.unicom.core.pojo.ad.Content;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    @RequestMapping("/findByCategoryId")
    public List<Content> findByCategoryId(Long categoryId){

        List<Content> byCategoryIdFromRedis = contentService.findByCategoryIdFromRedis(categoryId);
        return byCategoryIdFromRedis;
    }




    @RequestMapping("/search")
    public PageResult search(@RequestBody Content content, Integer page, Integer rows) {
        PageResult result = contentService.findPage(content, page, rows);
        return result;
    }

    @RequestMapping("/add")
    public ResponseResult add(@RequestBody Content content) {
        try {
            contentService.add(content);
            return new ResponseResult(true, "保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false, "保存失败!");
        }
    }

    @RequestMapping("/findOne")
    public Content findOne(Long id) {
        Content one = contentService.findOne(id);
        return one;
    }

    @RequestMapping("/update")
    public ResponseResult update(@RequestBody Content content) {
        try {
            contentService.update(content);
            return new ResponseResult(true, "修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false, "修改失败!");
        }
    }
    @RequestMapping("/delete")
    public ResponseResult delete(Long[] ids) {
        try {
            contentService.delete(ids);
            return new ResponseResult(true, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false, "删除失败!");
        }
    }


}
