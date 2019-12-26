package com.unicom.core.service;

import com.unicom.core.pojo.item.ItemCat;

import java.util.List;

public interface ItemCatService {
	List<ItemCat> findByParentId(Long parentId);

	void saveCategory(ItemCat category);

	void deleteCatWithId(Long[] ids);

	ItemCat findOneCategory(Long id);

	List<ItemCat> findAll();

}
