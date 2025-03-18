package com.example.marchedeviseback.Controllers;

import com.example.marchedeviseback.Entities.Devise;
import com.example.marchedeviseback.Repositories.DeviseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DeviseWebSocket {

    @Autowired
    private DeviseRepository repository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 1000) // Envoie toutes les secondes
    @SendTo("/topic/devises")
    public void sendUpdatedDevises() {
        List<Devise> devises = repository.findAll();
        messagingTemplate.convertAndSend("/topic/devises", devises);
    }


}
