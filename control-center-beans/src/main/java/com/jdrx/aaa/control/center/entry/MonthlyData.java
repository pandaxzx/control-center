package com.jdrx.aaa.control.center.entry;

//每月供水量
public class MonthlyData {
	// 月份
	private String tag;
	// 水量
	private Double quantity;

	public MonthlyData(String tag, double quantity) {
		this.tag = tag;
		this.quantity = quantity;
	}

	public Double getQuantity() {
		return quantity;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}