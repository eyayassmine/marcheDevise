package com.example.marchedeviseback.Controllers;


import com.example.marchedeviseback.Entities.Devise;
import com.example.marchedeviseback.Services.DeviseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Devises")
@CrossOrigin(origins = "http://localhost:4200")
public class DeviseController {
    @Autowired
    private DeviseServiceImp deviseService;
    //njareb naaytelha bl interface mbaad nchoufha tekhdem wala
    @Autowired
    private SimpMessagingTemplate messagingTemplate; // For sending messages to WebSocket clients


    // Endpoint to get all devises
    @GetMapping("/retrieve-all-devises")
    public List<Devise> getAllDevises() {
        return deviseService.retrieveAllDevises();
    }

    // Endpoint to get a specific devise by ID
    @GetMapping("/retrieve-devise/{id}")
    public Devise getDevise(@PathVariable Long id) {
        return deviseService.retrieveDevise(id);
    }

    // Endpoint to add a new devise
    @PostMapping("/addDevise")
    public Devise addDevise(@RequestBody Devise devise) {
        return deviseService.addDevise(devise);
    }

    // Endpoint to update a devise (add any required fields to the request body)
    @PutMapping("/modify-devise/{id}")
    public Devise updateDevise(@PathVariable Long id, @RequestBody Devise devise) {
        return deviseService.updateDevise(devise, id);
    }

    // Endpoint to delete a devise by ID
    @DeleteMapping("/delete-devise/{id}")
    public void deleteDevise(@PathVariable Long id) {
        deviseService.deleteDevise(id);
    }

    // Method to send updates to WebSocket clients on a topic
    @PostMapping("/simulate")
    public void simulateRates() {
        // Simulate rates and send updates to clients every second
        // Call the simulateRatesInService method from the service
        deviseService.simulateRates();

        // Fetch updated devises and send updates to WebSocket clients
        List<Devise> devises = deviseService.retrieveAllDevises();
        for (Devise devise : devises) {
            messagingTemplate.convertAndSend("/topic/currency", devise);
        }
    }


}
