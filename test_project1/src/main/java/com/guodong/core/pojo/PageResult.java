package com.guodong.core.pojo;

import lombok.Data;

import java.util.List;
@Data
public class PageResult {
	private Long total;
	private List rows;

	public PageResult(Long total, List rows) {
		this.total = total;
		this.rows = rows;
	}
}
