package com.jdrx.aaa.control.center.enums;

public enum PointTypeEnum {
	WATERWORKS(0, "水厂"),
	PUMPHOUSE(1, "泵房"),
	PUMPSTATION(2, "泵站"),
	OBSERVATIONPOINT(3, "监测点");

	private int type;
	private String desc;

	private PointTypeEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}