package com.jdrx.aaa.control.center.websocket;

import com.jdrx.platform.commons.rest.exception.BizException;
import com.jdrx.platform.commons.rest.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/websocket/{biz}")
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //webSocket容器
    public static final ConcurrentHashMap<String, WebSocketServer> WebSocketMap = new ConcurrentHashMap<>();
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("biz")String biz,Session session) throws BizException {
        this.session=session;
        WebSocketMap.put(biz, this);
        try {
        } catch (Exception e) {
            throw new BizException("获取监测点实时信息连接错误");
        }
    }

    @OnMessage
    public void onMessage( String message, Session session) {

    }

    @OnError
    public void onError(Session session, Throwable error) throws BizException {
        throw new BizException("获取监测点实时信息错误");
    }

    @OnClose
    public void onClose(@PathParam("biz") String biz) {
        WebSocketMap.remove(biz);
    }

    public void sendMessage(Object message) throws BizException {
        try {
            this.session.getBasicRemote().sendText(JsonMapper.toJsonString(message));
        } catch (Exception e) {
            logger.error("websocket错误");
            throw new BizException("发送失败,请重试");
        }
    }
}