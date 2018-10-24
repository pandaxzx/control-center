package com.jdrx.aaa.control.center.entry;

public class MonitorPointAttribute {
	private Long id;
	// 属性名称
	private String name;
	// 所属监测点
	private Long pointId;
	// 属性标签
	private String tag;
	// 分组
	private String group;
	// 单位
	private String unit;
	// 传感器当前数值
	private Double value;
	// 监控值
	private String bizMonitor;
	// 状态是否异常 默认正常
	private Boolean status = true;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getPointId() {
		return pointId;
	}

	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}

	public String getBizMonitor() {
		return bizMonitor;
	}

	public void setBizMonitor(String bizMonitor) {
		this.bizMonitor = bizMonitor;
	}
}