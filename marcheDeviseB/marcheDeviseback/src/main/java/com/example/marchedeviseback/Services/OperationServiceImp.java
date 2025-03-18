package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.*;
import com.example.marchedeviseback.Repositories.OperationRepository;
import com.example.marchedeviseback.Repositories.PositionTresoDRepository;
import com.example.marchedeviseback.Repositories.PositionTresoGRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@Service
public class OperationServiceImp implements IOperationService {

    @Autowired
    OperationRepository operationr;
    @Autowired
    PositionTresoDRepository ptresoDr;
    @Autowired
    PositionTresoGRepository ptresoGr;

    @Transactional
    @Override
    public Operation addOperation(Operation operation) {

        LocalDate twoDaysLater = LocalDate.now().plusDays(2);
        Date dateToSet = Date.from(twoDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        operation.setDate(dateToSet);

        // Save the operation
        Operation savedOperation = operationr.save(operation);

        // Create and save PositionTresoD
        PositionTresoD positionTresoD = new PositionTresoD();
        positionTresoD.setDevise(savedOperation.getDevise());
        positionTresoD.setMontant(savedOperation.getMontant());
        //positionTresoD.setSens(savedOperation.getType().toSensD()); // Assuming conversion of OpType to SensD
        OpType operationType =savedOperation.getType();
        SensD sensDValue = operationType.toSensD();
        positionTresoD.setSens(sensDValue);

        ptresoDr.save(positionTresoD);

        // Check if this is the first PositionTresoD for the given Devise
        boolean isFirstPosition = !ptresoDr.findByDevise(savedOperation.getDevise());

        // Handle PositionTresoG
        PositionTresoG positionTresoG = ptresoGr.findByDevise(savedOperation.getDevise());
        if (isFirstPosition) {
            // If it's the first PositionTresoD for this Devise, create a new PositionTresoG
            positionTresoG = new PositionTresoG();
            positionTresoG.setDevise(savedOperation.getDevise());
            positionTresoG.setVolume(positionTresoD.getMontant());

            if ("EMPRUNT".equals(positionTresoD.getSens())) {
                positionTresoG.setSensG(SensG.POSITIVE);
            } else {
                positionTresoG.setSensG(SensG.NEGATIVE);
            }

            // Set groupementValeur date
            positionTresoG.setGroupementValeur(dateToSet);

            // Save the new PositionTresoG
            ptresoGr.save(positionTresoG);
        } else {
            // If this is not the first PositionTresoD for this Devise, modify the existing PositionTresoG
            if ("EMPRUNT".equals(positionTresoD.getSens())) {
                positionTresoG.setVolume(positionTresoG.getVolume() + positionTresoD.getMontant());
            } else if ("PRET".equals(positionTresoD.getSens())) {
                positionTresoG.setVolume(positionTresoG.getVolume() - positionTresoD.getMontant());
            }

            // Set the SensG based on the volume
            if (positionTresoG.getVolume() >= 0) {
                positionTresoG.setSensG(SensG.POSITIVE);
            } else {
                positionTresoG.setSensG(SensG.NEGATIVE);
            }

            // Set groupementValeur date
            positionTresoG.setGroupementValeur(dateToSet);

            // Save the updated PositionTresoG
            ptresoGr.save(positionTresoG);
        }

        return savedOperation;
    }

    @Override
    public List<Operation> retrieveAllOperations() {
        return operationr.findAll();
    }

    @Override
    public Operation retrieveOperation(Long id) {
        return operationr.findById(id).orElse(null);
    }

    @Override
    public Operation updateOperation(Operation operation, Long id) {
        if ((!operationr.existsById(id)) ) {
            throw new EntityNotFoundException("Operation with ID " + id + " not found.");
        }
        Operation existingOperation = operationr.findById(id).orElse(null);
        // Set the ID of the project to ensure it's updated properly
        existingOperation.setId(id);
        ////badel num
        // Save the updated project
        return operationr.save(existingOperation);
    }

    @Override
    public void deleteOperation(Long id) {
        operationr.deleteById(id);
    }
}
