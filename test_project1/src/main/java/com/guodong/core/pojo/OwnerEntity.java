package com.guodong.core.pojo;

import lombok.Data;

@Data
public class OwnerEntity {
	private Owner owner;
	private String address;

	public OwnerEntity(Owner owner, String address) {
		this.owner = owner;
		this.address = address;
	}
}
