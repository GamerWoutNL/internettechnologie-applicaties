package com.example.verreken.backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class TestController {

    private final WebSocketController webSocketController;

    public TestController(WebSocketController webSocketController) {
        this.webSocketController = webSocketController;
    }

    @GetMapping("test")
    public void test() {
        this.webSocketController.send("test");
    }

}
