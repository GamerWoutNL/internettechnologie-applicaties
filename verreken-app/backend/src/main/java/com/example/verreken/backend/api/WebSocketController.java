package com.example.verreken.backend.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate socket;

    public WebSocketController(SimpMessagingTemplate socket) {
        this.socket = socket;
    }

    @MessageMapping("/data")
    public void receive(String payload) {
        System.out.println("Received: " + payload);
    }

    public void send(String payload) {
        this.socket.convertAndSend("/topic/data", payload);
        System.out.println("Send: " + payload);
    }

}
