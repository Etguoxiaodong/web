package com.unicom.core.pojo.entity;

import com.unicom.core.pojo.specification.Specification;
import com.unicom.core.pojo.specification.SpecificationOption;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SpecEntity implements Serializable {
	private Specification specification;
	private List<SpecificationOption> specOptionList;

}
