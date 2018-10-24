package com.jdrx.aaa.control.center.service;

import com.jdrx.aaa.control.center.dao.ControlCenterDao;
import com.jdrx.aaa.control.center.dto.MonitorPointDTO;
import com.jdrx.aaa.control.center.entry.MonitorPoint;
import com.jdrx.aaa.control.center.entry.MonitorPointAttribute;
import com.jdrx.aaa.control.center.enums.PointTypeEnum;
import com.jdrx.aaa.control.center.enums.PressureThresholdEnum;
import com.jdrx.platform.commons.rest.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BigScreenService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private ControlCenterDao controlCenterDao;

	/**
	 * 查询数据封装
	 * 
	 * @param monitorPointDTO
	 * @return Map<String, Object>
	 */
	public Map<String, Object> monitorPointRunTime(MonitorPointDTO monitorPointDTO) throws BizException {
		Map<String, Object> map = new HashMap<>();
		List<List<MonitorPointAttribute>> list = new ArrayList<>();
		MonitorPoint monitorPointRunTime = getMonitoringPointRunTime(monitorPointDTO);
		List<MonitorPointAttribute> attributeList = monitorPointRunTime.getAttributeList();
		Map<String, List<MonitorPointAttribute>> mapByGroup = attributeList.stream().collect(Collectors.groupingBy(MonitorPointAttribute::getGroup));
		Iterator<Map.Entry<String, List<MonitorPointAttribute>>> it = mapByGroup.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<MonitorPointAttribute>> entry = it.next();
			list.add(entry.getValue());
		}
		map.put("name", monitorPointRunTime.getName());
		map.put("attributes", list);
		return map;
	}

	/**
	 * 获取推送的实时信息(目前只显示监测点下的压力)
	 *
	 * @param
	 * @return Map<String,List<MonitoringPoint>>
	 */
	public Map<String, Object> queryAllPressure() {
		// 总压力
		final double[] pressureTotal = { 0 };
		List<MonitorPointAttribute> allPressure = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		List<MonitorPoint> observationPoints = controlCenterDao.listMonitoringPointByTpye(PointTypeEnum.OBSERVATIONPOINT.getType());
		observationPoints.forEach(item -> {
			List<MonitorPointAttribute> monitorPointAttributes = controlCenterDao.listMonitoringPointAttribute(item.getId());
			monitorPointAttributes.forEach(val -> {
				setAttributesValue(val);
				if (("压力").equals(val.getName())) {
					if (val.getValue() == null) {
						val.setStatus(false);
					} else {
						pressureTotal[0] += val.getValue();
						if (val.getValue() <= PressureThresholdEnum.LOWERLIMIT.getValue() && val.getValue() >= PressureThresholdEnum.UPPERLIMIT.getValue()) {
							val.setStatus(false);
						}
					}
					val.setName(item.getName() + val.getName());
					allPressure.add(val);
				}
			});

		});
		// 平均压力
		map.put("averagePressure", pressureTotal[0] / allPressure.size());
		map.put("ObservationPoints", allPressure);
		return map;
	}

	/**
	 * 获取所有监控点的基础信息
	 *
	 * @param
	 * @return Map<String,List<MonitoringPoint>>
	 */
	public Map<String, List<MonitorPoint>> queryMonitoringPoints() throws BizException {
		try {
			List<MonitorPoint> list = controlCenterDao.listMonitoringPoint();
			// 取出所有监测点
			Map<Integer, List<MonitorPoint>> map = list.stream().collect(Collectors.groupingBy(MonitorPoint::getType));
			List<MonitorPoint> monitorPoints3 = map.get(PointTypeEnum.OBSERVATIONPOINT.getType());

			// 判断监测点的数值是否正常
			monitorPoints3.forEach(item -> {
				List<MonitorPointAttribute> monitorPointAttributes = controlCenterDao.listMonitoringPointAttribute(item.getId());
				monitorPointAttributes.forEach(val -> {
					setAttributesValue(val);
					if (("压力").equals(val.getName())) {
						if (val.getValue() == null) {
							val.setStatus(false);
							item.setStatus(false);
						} else {
							if (val.getValue() <= PressureThresholdEnum.LOWERLIMIT.getValue() && val.getValue() >= PressureThresholdEnum.UPPERLIMIT.getValue()) {
								val.setStatus(false);
								item.setStatus(false);
							}
						}
						item.setAttributeList(monitorPointAttributes);
					}
				});

			});
			Map<String, List<MonitorPoint>> resultMap = new HashMap<>();
			resultMap.put("WaterWorks", map.get(PointTypeEnum.WATERWORKS.getType()));
			resultMap.put("PumpHouse", map.get(PointTypeEnum.PUMPHOUSE.getType()));
			resultMap.put("PumpStation", map.get(PointTypeEnum.PUMPSTATION.getType()));
			resultMap.put("ObservationPoint", monitorPoints3);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取监控点数据失败");
			throw new BizException("获取监控点数据失败");
		}
	}

	/**
	 * 根据Id获取该监控点全部实时信息
	 * 
	 * @param monitorPointDTO
	 * @return List<MonitoringPointAttribute>
	 */
	public MonitorPoint getMonitoringPointRunTime(MonitorPointDTO monitorPointDTO) throws BizException {
		try {
			if (monitorPointDTO.getId() != null) {
				MonitorPoint monitorPoint = controlCenterDao.getMonitoringPointById(monitorPointDTO.getId());
				List<MonitorPointAttribute> monitorPointAttributes = controlCenterDao.listMonitoringPointAttribute(monitorPointDTO.getId());
				monitorPointAttributes.forEach(item -> {
					// 为实时信息赋值
						setAttributesValue(item);
						if (monitorPointDTO.getType() == 3) {
							if ("压力".equals(item.getName())) {
								if (item.getValue() == null) {
									item.setStatus(false);
									monitorPoint.setStatus(false);
								} else {
									if (item.getValue() <= PressureThresholdEnum.LOWERLIMIT.getValue() && item.getValue() >= PressureThresholdEnum.UPPERLIMIT.getValue()) {
										item.setStatus(false);
										monitorPoint.setStatus(false);
									}
								}
							}
						}
					});
				monitorPoint.setAttributeList(monitorPointAttributes);
				return monitorPoint;
			} else {
				throw new Exception("监控点id不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("监控点信息获取失败");
		}
	}

	/**
	 * 返回所有监控点的实时信息
	 * 
	 * @param
	 * @return List<MonitoringPointAttribute>
	 */
	public List<MonitorPoint> queryAllMonitoringRuntime() {
		List<MonitorPoint> monitorPoints = controlCenterDao.listMonitoringPoint();
		List<MonitorPoint> monitorPointsRuntime = new ArrayList<>();
		MonitorPointDTO monitorPointDTO = new MonitorPointDTO();
		monitorPoints.forEach(item -> {
			monitorPointDTO.setId(item.getId());
			monitorPointDTO.setType(item.getType());
			try {
				monitorPointsRuntime.add(getMonitoringPointRunTime(monitorPointDTO));
			} catch (Exception e) {
				logger.error("获取监控点信息失败");
				e.printStackTrace();
			}
		});
		return monitorPointsRuntime;
	}

	// 获取redis数据并赋值
	public void setAttributesValue(@NotNull MonitorPointAttribute item) {
		if (item.getTag() != null) {
			Set<String> keys = redisTemplate.keys("aaa:spa:*:" + item.getTag());
			if (keys.size() > 0) {
				Double value = Double.valueOf(String.valueOf(redisTemplate.opsForHash().get(keys.iterator().next(), "val")));
				item.setValue(value);
			}
		}
	}
}