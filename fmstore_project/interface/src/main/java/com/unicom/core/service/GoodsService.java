package com.unicom.core.service;


import com.unicom.core.pojo.entity.GoodsEntity;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.good.Goods;

public interface GoodsService {
    public void add(GoodsEntity goodsEntity);

    PageResult findPage(Goods goods, Integer page, Integer rows);

    GoodsEntity findOne(Long id);

    void update(GoodsEntity goodsEntity);

    void delete(Long[] ids);

    void updateStatus(Long[] ids, String status);

}
