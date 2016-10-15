package com.mikhalechko.websockets;


import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/app")
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        session.getBasicRemote().sendText("connected");
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close connection");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        message += " from server";
        session.getBasicRemote().sendText(message);
    }
}
