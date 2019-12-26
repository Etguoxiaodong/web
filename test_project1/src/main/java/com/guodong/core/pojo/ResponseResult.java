package com.guodong.core.pojo;

import lombok.Data;

@Data
public class ResponseResult {
	private Boolean success;
	private Object message;

	public ResponseResult(Boolean success, Object message) {
		this.success = success;
		this.message = message;
	}
}
