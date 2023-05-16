package com.server.integratedserver.webSocket;

import jakarta.websocket.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;


@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    private final static Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);
    private static WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        MyWebSocketHandler.session = session;
        // 在此处可以处理新连接建立时的逻辑，比如将该 Session 添加到在线列表中等。
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        // 在此处处理接收到消息的逻辑，比如将消息广播给其他在线用户等。
        logger.info("Received message: " + message.getPayload());
    }
    public static void sendInfo(String message) throws IOException {
        logger.info(message);
        //session.getBasicRemote().sendText(message);
    }

}
