package com.jdrx.aaa.control.center.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jdrx.aaa.control.center.dao.MonitorPointAttributeDao;
import com.jdrx.aaa.control.center.dao.MonitorPointDao;
import com.jdrx.aaa.control.center.dto.AttributeQueryDto;
import com.jdrx.aaa.control.center.dto.MonitorPointDTO;
import com.jdrx.aaa.control.center.entry.MonitorPoint;
import com.jdrx.aaa.control.center.entry.MonitorPointAttribute;
import com.jdrx.aaa.control.center.enums.PointTypeEnum;

/**
 * 官网监控、工艺服务
 * @author wm
 *
 */
@Service
public class PipeNetworkService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BigScreenService controlCenterService;
	@Autowired
	private MonitorPointAttributeDao attributeDao;
	@Autowired
	private MonitorPointDao pointDao;
	
	public Map<String, Object> list(Integer type) {
		logger.info("查询监测点及其属性");
		// 获取所有监测点
		List<MonitorPoint> pointList = pointDao.list(null);
		
		// 获取所有监测点属性
		AttributeQueryDto dto = new AttributeQueryDto();
		dto.setBizPipe(0);
		List<MonitorPointAttribute> attributeList = attributeDao.list(dto);
		
		// 根据属性tag从redis获取数据
		attributeList.stream().forEach(p -> controlCenterService.setAttributesValue(p));
		
		// 对监测点属性进行分组
		Map<Long, List<MonitorPointAttribute>> attributeMap = 
				attributeList.parallelStream().filter(p -> p.getPointId() != null).collect(Collectors.groupingBy(MonitorPointAttribute::getPointId));
		// 将监测点属性与监测点进行匹配
		pointList.stream().forEach(p -> p.setAttributeList(attributeMap.get(p.getId())));
		
		// 监测点分组统计
		Map<Integer, Long> countPoint = pointList.stream().collect(Collectors.groupingBy(MonitorPoint::getType, Collectors.counting()));
		
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("pointList", pointList);
		resultMap.put("countPoint", countPoint);
		return resultMap;
	}
	
	/**
	 * 水厂、泵站监控
	 * @return
	 */
	public List<Map<String, Object>> monitor() {
		List<Integer> types = Lists.newArrayList();
		types.add(PointTypeEnum.WATERWORKS.getType());// 水厂
		types.add(PointTypeEnum.PUMPSTATION.getType());// 泵站
		
		MonitorPointDTO dto = new MonitorPointDTO();
		dto.setId(1L);// 目前只显示花园水厂的数据
		dto.setTypes(types);
		
		/*获取监测点*/
		List<MonitorPoint> pointList = pointDao.list(dto);
		
		/*获取属性*/
		// 提取监测点id
		List<Long> ids = pointList.stream().map(MonitorPoint::getId).distinct().collect(Collectors.toList());
		AttributeQueryDto attributeQueryDto = new AttributeQueryDto();
		attributeQueryDto.setPointIds(ids);
		attributeQueryDto.setBizMonitor(0);
		List<MonitorPointAttribute> attributeList = attributeDao.list(attributeQueryDto);
		// HYSC_CCS_DQYL_AI、HYSC_CCS_GQYL_AI、HYSC_QSC_SW1_AI
		
		// 根据属性tag从redis获取数据
		attributeList.stream().forEach(p -> controlCenterService.setAttributesValue(p));
		
		// 对监测点属性进行分组
		Map<Long, List<MonitorPointAttribute>> attributeMap = 
				attributeList.parallelStream().filter(p -> p.getPointId() != null).collect(Collectors.groupingBy(MonitorPointAttribute::getPointId));
		// 将监测点属性与监测点进行匹配
		pointList.stream().forEach(p -> p.setAttributeList(attributeMap.get(p.getId())));
		
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> tempMap = null;
		for(MonitorPoint point : pointList) {
			
			tempMap = Maps.newHashMap();
			tempMap.put("name", point.getName());
			
			for(MonitorPointAttribute attribute : point.getAttributeList()) {
				tempMap.put(attribute.getBizMonitor(), attribute.getValue());
			}
			
			list.add(tempMap);
		}
		return list;
	}
}