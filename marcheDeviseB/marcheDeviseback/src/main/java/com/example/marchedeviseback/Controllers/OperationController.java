package com.example.marchedeviseback.Controllers;


import com.example.marchedeviseback.Entities.Operation;
import com.example.marchedeviseback.Services.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Operations")
@CrossOrigin(origins = "http://localhost:4200")
public class OperationController {


    @Autowired
    private IOperationService operationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // For sending messages to WebSocket clients

    // Endpoint to get all devises
    @GetMapping("/retrieve-all-operations")
    public List<Operation> getAllOperations() {
        return operationService.retrieveAllOperations();
    }

    // Endpoint to get a specific devise by ID
    @GetMapping("/retrieve-devise/{id}")
    public Operation getOperation(@PathVariable Long id) {
        return operationService.retrieveOperation(id);
    }

    // Endpoint to add a new devise
    @PostMapping("/Operation")
    public Operation addOperation(@RequestBody Operation operation) {
        return operationService.addOperation(operation);
    }

    // Endpoint to update a operation (add any required fields to the request body)
    @PutMapping("/modify-operation/{id}")
    public Operation updateOperation(@PathVariable Long id, @RequestBody Operation operation) {
        return operationService.updateOperation(operation, id);
    }

    // Endpoint to delete a operation by ID
    @DeleteMapping("/delete-operation/{id}")
    public void deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
    }


}
