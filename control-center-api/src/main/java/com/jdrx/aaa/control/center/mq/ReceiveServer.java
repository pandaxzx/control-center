package com.jdrx.aaa.control.center.mq;

import com.jdrx.aaa.control.center.service.ControlCenterService;
import com.jdrx.aaa.control.center.webSocket.WebSocketServer;
import com.jdrx.platform.commons.rest.exception.BizException;
import com.jdrx.platform.commons.rest.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RabbitListener(queues="rtu-data",containerFactory = "rabbitListenerContainerFactory")
public class ReceiveServer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private ControlCenterService controlCenterService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //获取MQ队列信息,解析后直接推送;
    @RabbitHandler
    public void process(@Payload Map<String,String> bytes) throws BizException {
        logger.info("接受数据:"+bytes);

        redisTemplate.opsForValue().set("xzx",bytes.get("id"),60000);
        System.out.println(redisTemplate.opsForValue().get("xzx"));
        JsonMapper jsonMapper = new JsonMapper();
        try {
            Object o = jsonMapper.readValue("xzx", Object.class);
            controlCenterService.pustMonitoringPoint(o);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
