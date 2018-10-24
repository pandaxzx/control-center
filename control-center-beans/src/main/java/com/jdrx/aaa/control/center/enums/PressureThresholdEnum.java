package com.jdrx.aaa.control.center.enums;

public enum PressureThresholdEnum {
	UPPERLIMIT(0.45, "上限值"),
	LOWERLIMIT(0.25, "下限值");
	
	private double value;
	private String desc;
	
	private PressureThresholdEnum(double value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}