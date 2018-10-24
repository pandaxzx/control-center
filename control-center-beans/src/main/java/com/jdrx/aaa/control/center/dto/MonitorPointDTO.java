package com.jdrx.aaa.control.center.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "查询的监控点", description = "查询的监控点")
public class MonitorPointDTO {
	@ApiModelProperty(value = "监控点id", notes = "监控点id")
	private Long id;
	@ApiModelProperty(value = "监测点类型", notes = "0水厂,1泵房,2泵站,3监测点")
	private Integer type;
	// 根据类型列表查询
	private List<Integer> types;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Integer> getTypes() {
		return types;
	}

	public void setTypes(List<Integer> types) {
		this.types = types;
	}
}