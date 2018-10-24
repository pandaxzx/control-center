package com.jdrx.aaa.control.center.service;

import com.jdrx.aaa.control.center.dao.ControlCenterDao;
import com.jdrx.aaa.control.center.entry.MonitorPoint;
import com.jdrx.aaa.control.center.entry.MonthlyData;
import com.jdrx.aaa.control.center.enums.PointTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataStatsService {
	Random random = new Random();
	@Autowired
	private ControlCenterDao controlCenterDao;

	/**
	 * 统计耗电量
	 *
	 * @param
	 * @return List<Map<String, Object>>
	 */
	public void statsPowerConsume() {

	}

	/**
	 * 统计供水量
	 *
	 * @param
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> statsWaterWork() {
		List<Map<String, Object>> list = new ArrayList<>();

		List<MonitorPoint> waterWorks = controlCenterDao.listMonitoringPointByTpye(PointTypeEnum.WATERWORKS.getType());
		waterWorks.forEach(item -> {
			Map<String, Object> map = new HashMap<>();
			map.put("name", item.getName());
			map.put("water", getRandomList());
			list.add(map);
		});
		Map<String, Object> map = new HashMap<>();
		map.put("name", "市一水厂");
		map.put("water", getRandomList());
		list.add(map);
		return list;
	}

	public List<Map<String, Object>> statsPumpHouseAndStation() {
		List<Map<String, Object>> list = new ArrayList<>();
		List<MonitorPoint> pumpHouses = controlCenterDao.listMonitoringPointByTpye(PointTypeEnum.PUMPHOUSE.getType());
		List<MonitorPoint> pumpStations = controlCenterDao.listMonitoringPointByTpye(PointTypeEnum.PUMPSTATION.getType());
		pumpHouses.forEach(item -> {
			Map<String, Object> map = new HashMap<>();
			map.put("name", item.getName());
			map.put("water", getRandomList());
			map.put("power", getRandomList());
			list.add(map);
		});
		pumpStations.forEach(item -> {
			Map<String, Object> map = new HashMap<>();
			map.put("name", item.getName());
			map.put("water", getRandomList());
			map.put("power", getRandomList());
			list.add(map);
		});
		return list;
	}

	/**
	 * 所有水厂的供水量
	 *
	 * @param
	 * @return Map<String,Object>
	 */
	public List<Map<String, Object>> waterWorkValues() {
		List<Map<String, Object>> list = new ArrayList<>();
		List<MonitorPoint> waterWorks = controlCenterDao.listMonitoringPointByTpye(PointTypeEnum.WATERWORKS.getType());
		waterWorks.forEach(item -> {
			Map<String, Object> map = new HashMap<>();
			map.put("name", item.getName());
			map.put("value", random.nextInt(100));
			list.add(map);
		});
		Map<String, Object> map = new HashMap<>();
		map.put("name", "市一水厂");
		map.put("value", random.nextInt(100));
		Map<String, Object> map1 = new HashMap<>();
		map1.put("name", "邻水水厂");
		map1.put("value", random.nextInt(100));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("name", "华蓥蓥城水厂");
		map2.put("value", random.nextInt(100));
		Map<String, Object> map3 = new HashMap<>();
		map3.put("name", "三水厂");
		map3.put("value", random.nextInt(100));
		list.add(map);
		list.add(map1);
		list.add(map2);
		list.add(map3);
		return list;
	}

	/**
	 * 今日供水量和供电量
	 *
	 * @param
	 * @return Map<String , Object>
	 */
	public List<Map<String, Object>> powerAndWaterToday() {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		map1.put("neme", "今日供水量");
		map1.put("value", random.nextInt(100));
		map2.put("neme", "今日耗电量");
		map2.put("value", random.nextInt(100));
		list.add(map1);
		list.add(map2);
		return list;
	}

	public List<MonthlyData> getRandomList() {
		List<MonthlyData> listMonthlyData = new ArrayList<>();
		MonthlyData monthlyData1 = new MonthlyData("Jan", random.nextInt(100));
		MonthlyData monthlyData2 = new MonthlyData("Feb", random.nextInt(100));
		MonthlyData monthlyData3 = new MonthlyData("Mar", random.nextInt(100));
		MonthlyData monthlyData4 = new MonthlyData("Apr", random.nextInt(100));
		MonthlyData monthlyData5 = new MonthlyData("May", random.nextInt(100));
		MonthlyData monthlyData6 = new MonthlyData("Jun", random.nextInt(100));
		MonthlyData monthlyData7 = new MonthlyData("Jul", random.nextInt(100));
		MonthlyData monthlyData8 = new MonthlyData("Aug", random.nextInt(100));
		MonthlyData monthlyData9 = new MonthlyData("Sep", random.nextInt(100));
		MonthlyData monthlyData10 = new MonthlyData("Oct", random.nextInt(100));
		MonthlyData monthlyData11 = new MonthlyData("Nov", random.nextInt(100));
		MonthlyData monthlyData12 = new MonthlyData("Dec", random.nextInt(100));
		listMonthlyData.add(monthlyData1);
		listMonthlyData.add(monthlyData2);
		listMonthlyData.add(monthlyData3);
		listMonthlyData.add(monthlyData4);
		listMonthlyData.add(monthlyData5);
		listMonthlyData.add(monthlyData6);
		listMonthlyData.add(monthlyData7);
		listMonthlyData.add(monthlyData8);
		listMonthlyData.add(monthlyData9);
		listMonthlyData.add(monthlyData10);
		listMonthlyData.add(monthlyData11);
		listMonthlyData.add(monthlyData12);
		return listMonthlyData;
	}
}