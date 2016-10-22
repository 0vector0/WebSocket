package com.mikhalechko.websockets;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/app")
public class WebSocketServer {

    private static Map<String, Session> users = new HashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
//        session.getBasicRemote().sendText("connected");
//        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close connection");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String[] messageArrary = message.split(":");
        if (messageArrary[0].equals("name")) {
            users.put(messageArrary[1], session);
        } else if (messageArrary[0].equals("list")) {
            String output = "list:";
            for (String name : users.keySet()) {
                output += name + "\n";
            }
            session.getBasicRemote().sendText(output);
        } else {
            String nameFrom = "";
            for (Map.Entry<String, Session> entry : users.entrySet()) {
                if (entry.getValue() == session) {
                    nameFrom = entry.getKey();
                    break;
                }
            }
            String outputMessage = nameFrom + ":" + messageArrary[1];
            users.get(messageArrary[0]).getBasicRemote().sendText(outputMessage);
        }
    }
}