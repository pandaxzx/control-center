package com.jdrx.aaa.control.center.websocket;

import com.jdrx.aaa.control.center.service.ControlCenterService;
import com.jdrx.aaa.control.center.service.PipeNetworkService;
import com.jdrx.aaa.control.center.webSocket.WebSocketServer;
import com.jdrx.platform.commons.rest.exception.BizException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@EnableScheduling
public class MonitorPointPushTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ControlCenterService controlCenterService;
	@Autowired
	private PipeNetworkService pipeNetworkService;

	// 监测点实时信息定时推送
	//@Scheduled(initialDelay = 10000, fixedDelay = 20000)
	public void pustMonitoringPoint() throws BizException {
		logger.info("开始推送");
		ConcurrentHashMap<String, WebSocketServer> webSocketMap = WebSocketServer.WebSocketMap;
		// 根据连接数量决定是否需要推送数据
		if (webSocketMap.size() != 0) {
			if (webSocketMap.containsKey("pipenetwork")) {
				//webSocketMap.get("pipenetwork").sendMessage(pipeNetworkService.realTimeData());
			}
			if (webSocketMap.containsKey("bigscreen")) {
				webSocketMap.get("bigscreen").sendMessage(controlCenterService.queryAllPressure());
			}
		} else {
			logger.info("暂时没有websocket建立");
			return;
		}
	}
}