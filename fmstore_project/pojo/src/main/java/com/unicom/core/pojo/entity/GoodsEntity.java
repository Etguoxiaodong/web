package com.unicom.core.pojo.entity;

import com.unicom.core.pojo.good.Goods;
import com.unicom.core.pojo.good.GoodsDesc;
import com.unicom.core.pojo.item.Item;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GoodsEntity implements Serializable {
    private Goods goods;
    private GoodsDesc goodsDesc;
    private List<Item> itemList;
}
