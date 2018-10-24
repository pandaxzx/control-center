package com.jdrx.aaa.control.center.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "dto", description = "监测点查询dto")
public class PointQueryDto {
	@ApiModelProperty(name = "type", value = "监测点类型：0水厂，1泵房，2泵站，3监测点")
    private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}