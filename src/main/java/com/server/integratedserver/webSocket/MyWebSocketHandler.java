package com.server.integratedserver.webSocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

public class MyWebSocketHandler extends TextWebSocketHandler {
    private WebSocketSession session;

    private static final List<WebSocketSession> clients = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.session = session;
        clients.add(session);
        // 在建立WebSocket连接时，启动一个线程来处理客户端的消息
        new Thread(new WebSocketThread()).start();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理收到的消息
    }

    private class WebSocketThread implements Runnable {
        @Override
        public void run() {
            /**try {
                // 在独立的线程中处理WebSocket连接
                ServerSocket serverSocket = new ServerSocket(new InetSocketAddress(9999));
                ClientSoc clientSocket = serverSocket.accept();
                // 处理服务器间的通信
            } catch (IOException e) {
                e.printStackTrace();
            }**/
        }
    }
}
