package com.example.marchedeviseback.Controllers;


import com.example.marchedeviseback.DTOs.MaturityCalculationInput;
import com.example.marchedeviseback.DTOs.MaturityCalculationResult;
import com.example.marchedeviseback.Entities.Operation;
import com.example.marchedeviseback.Services.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Operations")
@CrossOrigin(origins = "http://localhost:5173")
public class OperationController {


    @Autowired
    private IOperationService operationService;

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate; // For sending messages to WebSocket clients

    // Endpoint to get all devises
    @GetMapping("/retrieve-all-operations")
    public List<Operation> getAllOperations() {
        return operationService.retrieveAllOperations();
    }

    @PostMapping("/calculate-maturity")
    public MaturityCalculationResult calculateMaturity(@RequestBody MaturityCalculationInput input) {
        // Calculate maturity details based on the provided inputs
        return operationService.calculateMaturityDetails(input);
    }

   /* @PostMapping("/calculate-maturity")
    public MaturityCalculationResult calculateMaturity(@RequestBody Operation operation) {
        return operationService.calculateMaturityDetails(operation);
    }*/



    // Endpoint to get a specific devise by ID
    @GetMapping("/retrieve-operation/{id}")
    public Operation getOperation(@PathVariable Long id) {
        return operationService.retrieveOperation(id);
    }

//     Endpoint to add a new devise
    @PostMapping("/addOperation")
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
