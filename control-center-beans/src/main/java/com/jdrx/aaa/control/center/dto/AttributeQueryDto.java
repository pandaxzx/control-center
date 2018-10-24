package com.jdrx.aaa.control.center.dto;

import java.util.List;

public class AttributeQueryDto {
	private Long pointId;
	private Integer bizPipe;
	private Integer bizScreen;
	private Integer bizMonitor;
	private List<Long> pointIds;
	
	public Long getPointId() {
		return pointId;
	}
	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}
	public Integer getBizPipe() {
		return bizPipe;
	}
	public void setBizPipe(Integer bizPipe) {
		this.bizPipe = bizPipe;
	}
	public Integer getBizScreen() {
		return bizScreen;
	}
	public void setBizScreen(Integer bizScreen) {
		this.bizScreen = bizScreen;
	}
	public List<Long> getPointIds() {
		return pointIds;
	}
	public void setPointIds(List<Long> pointIds) {
		this.pointIds = pointIds;
	}
	public Integer getBizMonitor() {
		return bizMonitor;
	}
	public void setBizMonitor(Integer bizMonitor) {
		this.bizMonitor = bizMonitor;
	}
}