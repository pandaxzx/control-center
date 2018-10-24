package com.jdrx.aaa.control.center.entry;

import java.util.List;

import com.google.common.collect.Lists;

//监测点
public class MonitorPoint {
	private Long id;
	// 监测点名称
	private String name;
	// 监测点类型 0水厂,1泵房,2泵站
	private Integer type;
	// 经度
	private Double lon;
	// 纬度
	private Double lat;
	// 包含的所有设备是否含有异常 默认正常
	private Boolean status = true;
	// 传感器实时信息
	private List<MonitorPointAttribute> attributeList;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public List<MonitorPointAttribute> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<MonitorPointAttribute> attributeList) {
		if(attributeList == null) {
			this.attributeList = Lists.newArrayList();
		} else {
			this.attributeList = attributeList;
		}
	}
}
