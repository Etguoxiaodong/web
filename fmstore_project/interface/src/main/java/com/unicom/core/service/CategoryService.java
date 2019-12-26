package com.unicom.core.service;

import com.unicom.core.pojo.ad.ContentCategory;
import com.unicom.core.pojo.entity.PageResult;

import java.util.List;

public interface CategoryService {
    public PageResult findPage(ContentCategory category, Integer page, Integer rows);

    void add(ContentCategory category);

    ContentCategory findOne(Long id);

    void update(ContentCategory category);

    void delete(Long[] ids);

    List<ContentCategory> findAll();

}
