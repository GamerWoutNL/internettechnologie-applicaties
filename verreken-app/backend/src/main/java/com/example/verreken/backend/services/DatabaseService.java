package com.example.verreken.backend.services;

import com.example.verreken.backend.model.Message;
import com.example.verreken.backend.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final MessageRepository messageRepository;

    public DatabaseService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(Message message) {
        this.messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }
}
