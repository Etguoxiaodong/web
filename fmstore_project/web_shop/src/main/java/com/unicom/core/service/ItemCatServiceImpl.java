package com.unicom.core.service;

import com.unicom.core.dao.item.ItemCatDao;
import com.unicom.core.pojo.item.ItemCat;
import com.unicom.core.pojo.item.ItemCatQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private ItemCatDao itemCatDao;

	@Override
	public List<ItemCat> findByParentId(Long parentId) {
		/*条件查询*/
		ItemCatQuery itemCatQuery = new ItemCatQuery();
		ItemCatQuery.Criteria criteria = itemCatQuery.createCriteria();
		/*根据parentId查询item*/
		criteria.andParentIdEqualTo(parentId);
		List<ItemCat> itemCats = itemCatDao.selectByExample(itemCatQuery);
		return itemCats;
	}

	@Override
	public void saveCategory(ItemCat category) {
		itemCatDao.insertSelective(category);
	}

	@Override
	public void deleteCatWithId(Long[] ids) {
		for (Long id : ids) {
			ItemCatQuery itemCatQuery = new ItemCatQuery();
			ItemCatQuery.Criteria criteria = itemCatQuery.createCriteria();
			criteria.andParentIdEqualTo(id);
			List<ItemCat> itemCats = itemCatDao.selectByExample(itemCatQuery);
			if (itemCats != null) {
				//itemCatDao.deleteByExample(itemCatQuery);
				for (ItemCat itemCat : itemCats) {
					itemCatDao.deleteByPrimaryKey(itemCat.getId());
				}
				itemCatDao.deleteByPrimaryKey(id);
			} else {
				itemCatDao.deleteByPrimaryKey(id);
			}
		}
	}

	@Override
	public ItemCat findOneCategory(Long id) {
		ItemCat itemCat = itemCatDao.selectByPrimaryKey(id);
		return itemCat;
	}

	@Override
	public List<ItemCat> findAll() {
		List<ItemCat> itemCats = itemCatDao.selectByExample(null);
		return itemCats;
	}

	/*public void deleteCatWithId(Long[] ids) {
		ItemCatQuery itemCatQuery = new ItemCatQuery();
		ItemCatQuery.Criteria criteria = itemCatQuery.createCriteria();
		for (Long id : ids) {
			criteria.andParentIdEqualTo(id);
			List<ItemCat> itemCats = itemCatDao.selectByExample(itemCatQuery);
			if (itemCats != null) {
				for (ItemCat itemCat : itemCats) {
					itemCat.setParentId(itemCatDao.selectByPrimaryKey(id).getParentId());
					itemCatDao.updateByPrimaryKeySelective(itemCat);
				}
				itemCatDao.deleteByPrimaryKey(id);
			} else {
				itemCatDao.deleteByPrimaryKey(id);
			}
		}
	}*/
}