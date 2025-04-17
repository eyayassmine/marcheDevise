package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.DTOs.MaturityCalculationInput;
import com.example.marchedeviseback.DTOs.MaturityCalculationResult;
import com.example.marchedeviseback.Entities.Operation;

import java.util.List;

public interface IOperationService {

    Operation addOperation(Operation operation);

    List<Operation> retrieveAllOperations();
     MaturityCalculationResult calculateMaturityDetails(MaturityCalculationInput maturityCalculationInput);
    Operation retrieveOperation(Long id);
    Operation updateOperation(Operation operation, Long id);

    void deleteOperation(Long id);
  //  List<Operation> SortOperationMaturityDate();


}
