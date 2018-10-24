package com.jdrx.aaa.control.center.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jdrx.aaa.control.center.dto.MonitorPointDTO;
import com.jdrx.aaa.control.center.entry.MonitorPoint;

@Repository@Mapper
public interface MonitorPointDao {
	
	List<MonitorPoint> list(MonitorPointDTO dto);
}