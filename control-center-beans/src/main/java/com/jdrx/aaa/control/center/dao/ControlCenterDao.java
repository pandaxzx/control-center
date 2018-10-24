package com.jdrx.aaa.control.center.dao;

import com.jdrx.aaa.control.center.entry.MonitorPoint;
import com.jdrx.aaa.control.center.entry.MonitorPointAttribute;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository@Mapper
public interface ControlCenterDao {
	
	// 获取全部监测点
	List<MonitorPoint> listMonitoringPoint();

	// 获取监测点下所有属性
	List<MonitorPointAttribute> listMonitoringPointAttribute(@Param("id") Long id);

	// 获取监测点的基础信息
	MonitorPoint getMonitoringPointById(Long id);

	// 按类型获取监测点基础信息
	List<MonitorPoint> listMonitoringPointByTpye(Integer type);
	
	// 获取全部监测点带属性
	List<MonitorPoint> listPointAttribute(@Param("type") Integer type);
}