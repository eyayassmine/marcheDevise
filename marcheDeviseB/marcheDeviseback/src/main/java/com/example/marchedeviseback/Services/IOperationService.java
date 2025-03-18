package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.Operation;

import java.util.List;

public interface IOperationService {

    Operation addOperation(Operation operation);

    List<Operation> retrieveAllOperations();

    Operation retrieveOperation(Long id);
    Operation updateOperation(Operation operation, Long id);

    void deleteOperation(Long id);


}
