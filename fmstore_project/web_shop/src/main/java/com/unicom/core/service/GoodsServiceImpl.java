package com.unicom.core.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unicom.core.dao.good.BrandDao;
import com.unicom.core.dao.good.GoodsDao;
import com.unicom.core.dao.good.GoodsDescDao;
import com.unicom.core.dao.item.ItemCatDao;
import com.unicom.core.dao.item.ItemDao;
import com.unicom.core.dao.seller.SellerDao;
import com.unicom.core.pojo.entity.GoodsEntity;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.pojo.good.Brand;
import com.unicom.core.pojo.good.Goods;
import com.unicom.core.pojo.good.GoodsDesc;
import com.unicom.core.pojo.good.GoodsQuery;
import com.unicom.core.pojo.item.Item;
import com.unicom.core.pojo.item.ItemCat;
import com.unicom.core.pojo.item.ItemQuery;
import com.unicom.core.pojo.seller.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private GoodsDescDao descDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ItemCatDao catDao;
	@Autowired
	private BrandDao brandDao;
	@Autowired
	private SellerDao sellerDao;
	@Override
	public void add(GoodsEntity goodsEntity) {
		//1. 保存商品对象
		//刚添加的商品状态默认为0 未审核
		goodsEntity.getGoods().setAuditStatus("0");
		//添加商品
		goodsDao.insertSelective(goodsEntity.getGoods());
		//2. 保存商品详情对象
		//商品主键作为商品详情的主键
		goodsEntity.getGoodsDesc().setGoodsId(goodsEntity.getGoods().getId());
		//添加商品详情
		descDao.insertSelective(goodsEntity.getGoodsDesc());
		//3.添加库存,商品规格
		insertItem(goodsEntity);
	}

	@Override
	public PageResult findPage(Goods goods, Integer page, Integer rows) {

			PageHelper.startPage(page, rows);
			GoodsQuery query = new GoodsQuery();
			GoodsQuery.Criteria criteria = query.createCriteria();
			if (goods != null) {
				if (goods.getGoodsName() != null && !"".equals(goods.getGoodsName())) {
					criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
				}
				if (goods.getAuditStatus() != null && !"".equals(goods.getAuditStatus())) {
					criteria.andAuditStatusEqualTo(goods.getAuditStatus());
				}
				if (goods.getSellerId() != null && !"".equals(goods.getSellerId())
						&& !"admin".equals(goods.getSellerId()) && !"wc".equals(goods.getSellerId())) {
					criteria.andSellerIdEqualTo(goods.getSellerId());
				}
			}
			Page<Goods> goodsList = (Page<Goods>)goodsDao.selectByExample(query);
			return new PageResult(goodsList.getTotal(), goodsList.getResult());

	}

	@Override
	public GoodsEntity findOne(Long id) {
		//1. 根据商品id查询商品对象
		Goods goods = goodsDao.selectByPrimaryKey(id);
		//2. 根据商品id查询商品详情对象
		GoodsDesc goodsDesc = descDao.selectByPrimaryKey(id);

		//3. 根据商品id查询库存集合对象
		ItemQuery query = new ItemQuery();
		ItemQuery.Criteria criteria = query.createCriteria();
		criteria.andGoodsIdEqualTo(id);
		List<Item> items = itemDao.selectByExample(query);

		//4. 将以上查询到的对象封装到GoodsEntity中返回
		GoodsEntity goodsEntity = new GoodsEntity();
		goodsEntity.setGoods(goods);
		goodsEntity.setGoodsDesc(goodsDesc);
		goodsEntity.setItemList(items);
		return goodsEntity;
	}

	@Override
	public void update(GoodsEntity goodsEntity) {
		//1. 修改商品对象
		goodsDao.updateByPrimaryKeySelective(goodsEntity.getGoods());
		//2. 修改商品详情对象
		descDao.updateByPrimaryKeySelective(goodsEntity.getGoodsDesc());
		//3. 根据商品id删除对应的库存集合数据
		ItemQuery query = new ItemQuery();
		ItemQuery.Criteria criteria = query.createCriteria();
		criteria.andGoodsIdEqualTo(goodsEntity.getGoods().getId());
		itemDao.deleteByExample(query);
		//4. 添加库存集合数据
		insertItem(goodsEntity);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			/*删除库存合集*/
			ItemQuery query = new ItemQuery();
			ItemQuery.Criteria criteria = query.createCriteria();
			criteria.andGoodsIdEqualTo(id);
			itemDao.deleteByExample(query);
			/*删除商品详情信息*/
			descDao.deleteByPrimaryKey(id);
			/*删除商品基本信息*/
			goodsDao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void updateStatus(Long[] ids, String status) {

	}

	public void insertItem(GoodsEntity goodsEntity) {
		if ("1".equals(goodsEntity.getGoods().getIsEnableSpec())) {//判断是否有库存规格
			//有勾选规格复选框
			if (goodsEntity.getItemList() != null) {
				for (Item item : goodsEntity.getItemList()) {
					//库存标题, 由商品名 + 规格组成具体的库存标题, 供消费者搜索使用, 可以搜索的更精确
					String title = goodsEntity.getGoods().getGoodsName();
					//从库存对象中获取前端传入的json格式规格字符串, 例如: {"机身内存":"16G","网络":"联通3G"}
					String specJsonStr = item.getSpec();
					//将json格式字符串转换成对象
					Map speMap = JSON.parseObject(specJsonStr, Map.class);
					//获取map中的value集合
					Collection<String> values = speMap.values();
					for (String value : values) {
						title += " " + value;
					}
					//设置标题
					item.setTitle(title);
					//设置库存对象的属性值
					setItemValue(goodsEntity, item);
					itemDao.insertSelective(item);
				}
			}
		} else {
			//没有勾选复选框, 没有库存数据, 但是我们需要初始化一条, 不然前端有可能报错
			Item item = new Item();
			//价格
			item.setPrice(new BigDecimal("99999999999"));
			//库存量
			item.setNum(0);
			//初始化规格
			item.setSpec("{}");
			//标题
			item.setTitle(goodsEntity.getGoods().getGoodsName());
			//设置库存对象的属性值
			setItemValue(goodsEntity, item);
			itemDao.insertSelective(item);
		}
	}

	private Item setItemValue(GoodsEntity goodsEntity, Item item) {
		//商品id
		item.setGoodsId(goodsEntity.getGoods().getId());
		//创建时间
		item.setCreateTime(new Date());
		//更新时间
		item.setUpdateTime(new Date());
		//库存状态, 默认为0, 未审核
		item.setStatus("0");
		//分类id, 库存使用商品的第三级分类最为库存分类
		item.setCategoryid(goodsEntity.getGoods().getCategory3Id());
		//分类名称
		ItemCat itemCat = catDao.selectByPrimaryKey(goodsEntity.getGoods().getCategory3Id());
		item.setCategory(itemCat.getName());
		//品牌名称
		Brand brand = brandDao.selectByPrimaryKey(goodsEntity.getGoods().getBrandId());
		item.setBrand(brand.getName());
		//卖家名称
		Seller seller = sellerDao.selectByPrimaryKey(goodsEntity.getGoods().getSellerId());
		item.setSeller(seller.getName());
		//示例图片
		String itemImages = goodsEntity.getGoodsDesc().getItemImages();
		List<Map> maps = JSON.parseArray(itemImages, Map.class);
		if (maps != null && maps.size() > 0) {
			String url = String.valueOf(maps.get(0).get("url"));
			item.setImage(url);
		}
		return item;
	}

}
