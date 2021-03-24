package com.example.verreken.backend.api;

import com.example.verreken.backend.model.Message;
import com.example.verreken.backend.services.DatabaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class TestController {

    private final DatabaseService databaseService;

    public TestController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("messages")
    public List<Message> getAllMessages() {
        return this.databaseService.getAllMessages();
    }

}
