package com.example.marchedeviseback.Controllers;
import com.example.marchedeviseback.Entities.RateH;
import com.example.marchedeviseback.Repositories.RateHRepository;
import com.example.marchedeviseback.Services.RateHServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/HRates")
@CrossOrigin(origins = "http://localhost:5173")
public class RateHController {

    @Autowired
    private RateHServiceImp rateHService;
    @Autowired
    private RateHRepository rateHr;

    @GetMapping("/retrieve-all-rateHs")
    public List<RateH> getAllDeviseHs() {
        return rateHService.retrieveAllRateHs();
    }

    // Endpoint to get a specific devise by ID
    @GetMapping("/retrieve-rateH/{id}")
    public RateH getDeviseH(@PathVariable Long id) {
        return rateHService.retrieveRateH(id);
    }


    // Update the stream method to send only the new or updated devises
    @GetMapping("/api/forex-stream")
    public SseEmitter streamForexData() {
        SseEmitter emitter = new SseEmitter();

        try {
            // Track when we started sending data
            AtomicReference<LocalDateTime> lastUpdatedTime = new AtomicReference<>(LocalDateTime.now());  // Use AtomicReference to allow modification

            // Send only new or updated data
            List<RateH> updatedDevises = rateHService.getNewOrUpdatedCurrencies(lastUpdatedTime.get());
            if (!updatedDevises.isEmpty()) {
                emitter.send(updatedDevises);
            }

            // Simulate a real-time stream
            new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep(2000); // Simulate a delay

                        // Check for new or updated data
                        List<RateH> newRates = rateHService.getNewOrUpdatedCurrencies(lastUpdatedTime.get());

                        if (!newRates.isEmpty()) {
                            emitter.send(newRates);
                            lastUpdatedTime.set(LocalDateTime.now());  // Update lastUpdatedTime to the current time
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    emitter.completeWithError(e);
                }
            }).start();

        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

}

    // This endpoint will stream the simulated data to the frontend
//    @GetMapping("/api/forex-stream")
//    public SseEmitter streamForexData() {
//        SseEmitter emitter = new SseEmitter();
//        // Start a new thread to simulate data generation and send it to the client
//            try {
//                List<DeviseH> allDevises = deviseHr.findAll();
//                if (!allDevises.isEmpty()) {
//                    emitter.send(allDevises);
//                }
//            } catch (IOException e) {
//                emitter.completeWithError(e);
//            }
//
//            // Start a new thread to simulate data updates
//            new Thread(() -> {
//                try {
//                    while (true) {
//                        // Wait for new or updated data (replace this with your actual update mechanism)
//                        List<DeviseH> updatedDevises = deviseHr.findAll(); // Get the full list again or filter by updates
//
//                        if (!updatedDevises.isEmpty()) {
//                            emitter.send(updatedDevises);  // Send updates to the client
//                        }
//
//                        Thread.sleep(2000);  // Simulate a short delay before next update
//                    }
//                } catch (IOException | InterruptedException e) {
//                    emitter.completeWithError(e);  // Handle any streaming errors
//                }
//            }).start();
//
//        return emitter;
//    }





/*
    // Endpoint to handle SSE connection
    @GetMapping("/stream-devises")
    public SseEmitter streamDevises() {
        SseEmitter emitter = new SseEmitter();
        deviseHService.addEmitter(emitter); // Register the emitter
        emitter.onCompletion(() -> deviseHService.getEmitters().remove(emitter)); // Cleanup when the client disconnects
        emitter.onTimeout(() -> {
            try {
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
            deviseHService.getEmitters().remove(emitter); // Cleanup on timeout
        });
        return emitter;
    }*/


