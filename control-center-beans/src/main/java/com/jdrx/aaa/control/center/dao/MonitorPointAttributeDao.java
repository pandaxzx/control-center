package com.jdrx.aaa.control.center.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jdrx.aaa.control.center.dto.AttributeQueryDto;
import com.jdrx.aaa.control.center.entry.MonitorPointAttribute;

@Repository@Mapper
public interface MonitorPointAttributeDao {
	
	List<MonitorPointAttribute> list(AttributeQueryDto dto);
}