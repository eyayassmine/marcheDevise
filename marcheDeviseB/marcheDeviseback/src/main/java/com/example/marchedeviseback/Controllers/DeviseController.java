package com.example.marchedeviseback.Controllers;


import com.example.marchedeviseback.Entities.Devise;
//import com.example.marchedeviseback.Services.DeviseHServiceImp;
import com.example.marchedeviseback.Services.DeviseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/Devises")
@CrossOrigin(origins = "http://localhost:5173")
public class DeviseController {
    @Autowired
    private DeviseServiceImp deviseService;
//    @Autowired
//    private DeviseHServiceImp deviseHService;

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
//    @PostMapping("/simulate")
//    public void simulateRates() {
//        // Simulate rates and send updates to clients every second
//        // Call the simulateRatesInService method from the service
//        System.out.println("Simulation started..."); // Debug log
//        deviseHService.simulateRates();
//        System.out.println("Simulation finished.");
//        // Fetch updated devises and send updates to WebSocket clients
//        List<Devise> devises = deviseService.retrieveAllDevises();
//        System.out.println("Sending devises to WebSocket: " + devises);
//
//        for (Devise devise : devises) {
//            messagingTemplate.convertAndSend("/topic/currency", devise);
//        }
//    }
//    @GetMapping(value = "/streamDevises", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public SseEmitter streamDevices() {
//        SseEmitter emitter = new SseEmitter();
//
//        // Correctly retrieve all devises from the deviseService
//        List<Devise> devises = deviseService.retrieveAllDevises();  // Fixed assignment
//
//        // Simulate streaming the devices data
//        new Thread(() -> {
//            try {
//                for (Devise devise : devises) {
//                    // Send device data
//                    emitter.send(devise);
//                    // Simulate a delay before sending the next device
//                    TimeUnit.SECONDS.sleep(1);
//                }
//                // Complete the stream
//                emitter.complete();
//            } catch (Exception e) {
//                emitter.completeWithError(e);
//            }
//        }).start();
//
//        return emitter;
//    }


    // Stream a list of devises every second
//    @GetMapping(value = "/stream-devices", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Devise> streamDevices() {
//        // Simulate streaming devises
//        return Flux.interval(Duration.ofSeconds(1))
//                .map(sequence -> new Devise(sequence, "Devise " + sequence));
//    }

}
