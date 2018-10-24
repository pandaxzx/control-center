package com.jdrx.aaa.control.center.api;

import com.jdrx.aaa.control.center.dto.MonitorPointDTO;
import com.jdrx.aaa.control.center.service.BigScreenService;
import com.jdrx.aaa.control.center.service.DataStatsService;
import com.jdrx.platform.commons.rest.beans.vo.ResposeVO;
import com.jdrx.platform.commons.rest.exception.BizException;
import com.jdrx.platform.commons.rest.factory.ResponseFactory;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/0/bigScreen")
@Api(value = "大屏幕展示")
public class BigScreenApi {
	@Autowired
	private BigScreenService bigScreenService;
	@Autowired
	private DataStatsService dataStatsService;

	@ApiOperation(value = "查询单个监控点实时信息", notes = "查询所有监测点信息")
	@RequestMapping(value = "/monitorPointRunTime", method = RequestMethod.POST)
	public ResposeVO getMonitorPointRunTimeById(@RequestBody MonitorPointDTO monitorPointDTO) throws BizException {
		return ResponseFactory.ok(bigScreenService.monitorPointRunTime(monitorPointDTO));
	}

	@ApiOperation(value = "查询全部监控点实时信息", notes = "查询所有监测点信息")
	@RequestMapping(value = "/monitorPointsRunTime", method = RequestMethod.POST)
	public ResposeVO findAllMonitorPointRunTime() throws BizException {
		return ResponseFactory.ok(bigScreenService.queryAllMonitoringRuntime());
	}

	@ApiOperation(value = "查询全部监控点基础信息", notes = "页面基础信息")
	@RequestMapping(value = "/monitorPoints", method = RequestMethod.POST)
	public ResposeVO findAllMonitorPoints() throws BizException {
		return ResponseFactory.ok(bigScreenService.queryMonitoringPoints());
	}

	@ApiOperation(value = "查询全部监控点压力", notes = "页面基础信息")
	@RequestMapping(value = "/observationPoints", method = RequestMethod.POST)
	public ResposeVO findAllObservationPoints() throws BizException {
		return ResponseFactory.ok(bigScreenService.queryAllPressure());
	}

	@ApiOperation(value = "水厂供水量统计", notes = "水厂供水量统计")
	@RequestMapping(value = "/waterWorkStats", method = RequestMethod.POST)
	public ResposeVO findWaterWorkStats() throws BizException {
		return ResponseFactory.ok(dataStatsService.statsWaterWork());
	}

	@ApiOperation(value = "泵房泵站供水量,耗电量", notes = "今日供水供电")
	@RequestMapping(value = "/pumpStats", method = RequestMethod.POST)
	public ResposeVO findPumpHouseAndStationStats() throws BizException {
		return ResponseFactory.ok(dataStatsService.statsPumpHouseAndStation());
	}

	@ApiOperation(value = "所有水厂的供水量", notes = "水厂供水量统计")
	@RequestMapping(value = "/waterWorkValues", method = RequestMethod.POST)
	public ResposeVO findWaterWorkValues() throws BizException {
		return ResponseFactory.ok(dataStatsService.waterWorkValues());
	}

	@ApiOperation(value = "今日供水供电", notes = "今日供水供电")
	@RequestMapping(value = "/dailyData", method = RequestMethod.POST)
	public ResposeVO findWaterAndPower() throws BizException {
		return ResponseFactory.ok(dataStatsService.powerAndWaterToday());
	}
}