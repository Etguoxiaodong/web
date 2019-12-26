package com.unicom.core.pojo.entity;

import com.unicom.core.pojo.good.Brand;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class PageResult implements Serializable {
	private Long total;
	private List rows;

	public PageResult(Long total, List rows) {
		this.total = total;
		this.rows = rows;
	}
}
