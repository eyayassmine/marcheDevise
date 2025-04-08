package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.*;
import com.example.marchedeviseback.Repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OperationServiceImp implements IOperationService {

    @Autowired
    OperationRepository operationr;
    @Autowired
    PositionTresoDRepository ptresoDr;
    @Autowired
    PositionTresoGRepository ptresoGr;
    @Autowired
    DeviseRepository deviser;
    @Autowired
    DeviseHRepository deviseHr;

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImp.class);


    @Transactional
    @Override
    public Operation addOperation(Operation operation) {



           if (operation.getDeviseH() != null && operation.getDeviseH().getId() != null) {
               DeviseH deviseH = deviseHr.findById(operation.getDeviseH().getId())
                       .orElseThrow(() -> new IllegalArgumentException("DeviseH not found"));
               operation.setDeviseH(deviseH);
           }
//        if (operation.getDeviseH() != null && operation.getDeviseH().getSsymbol() != null) {
//            DeviseH deviseH = deviseHr.findBySsymbol(operation.getDeviseH().getSsymbol())
//                    .orElseThrow(() -> new IllegalArgumentException("DeviseH not found with symbol: " + operation.getDeviseH().getSsymbol()));
//
//            operation.setDeviseH(deviseH);
//        }
        LocalDate twoDaysLater = LocalDate.now().plusDays(2);
        Date dateToSet = Date.from(twoDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        operation.setDate(dateToSet);

        // Save the operation
        Operation savedOperation = operationr.save(operation);
        boolean isFirstPosition = !ptresoDr.existsByDeviseH_Ssymbol(savedOperation.getDeviseH().getSsymbol());

        // Create and save PositionTresoD
        PositionTresoD positionTresoD = new PositionTresoD();
        positionTresoD.setDeviseH(savedOperation.getDeviseH());
        positionTresoD.setMontant(savedOperation.getMontant());
        positionTresoD.setDate(dateToSet);

        //positionTresoD.setSens(savedOperation.getType().toSensD()); // Assuming conversion of OpType to SensD
        OpType operationType =savedOperation.getType();
        SensD sensDValue = operationType.toSensD();
        positionTresoD.setSens(sensDValue);

        ptresoDr.save(positionTresoD);
        System.out.println("systemout tekhdem ");

        // Check if this is the first PositionTresoD for the given DeviseH
        // Handle PositionTresoG
        if (isFirstPosition) {
            // If it's the first PositionTresoD for this DeviseH, create a new PositionTresoG
            PositionTresoG newpositionTresoG = new PositionTresoG();
            newpositionTresoG.setDeviseH(savedOperation.getDeviseH());
            newpositionTresoG.setVolume(savedOperation.getMontant());
            //console()
            if (positionTresoD.getSens() == SensD.EMPRUNT) {
                newpositionTresoG.setSensG(SensG.POSITIVE);
            } else {
                newpositionTresoG.setSensG(SensG.NEGATIVE);
            }
            // Set groupementValeur date
            newpositionTresoG.setGroupementValeur(dateToSet);

            // Save the new PositionTresoG
            ptresoGr.save(newpositionTresoG);
        } else {
            System.out.println("zzama tekhdem fl else " );
            PositionTresoG positionTresoG = ptresoGr.findByDeviseH_Ssymbol(savedOperation.getDeviseH().getSsymbol());
            System.out.println("This is the positionTresoG that I want to modify. ID: " + positionTresoG.getId());
            logger.info("This is the positionTresoG that i want to modify.", positionTresoG.getId());
            logger.info("This is the positionTresoG volume  i want to modify.", positionTresoG.getVolume());
            System.out.println("This is the volume that I want to modify. Volume: " + positionTresoG.getVolume());


            // If this is not the first PositionTresoD for this DeviseH, modify the existing PositionTresoG
            if (positionTresoD.getSens() == SensD.EMPRUNT) {
                positionTresoG.setVolume(positionTresoG.getVolume() + positionTresoD.getMontant());
                logger.info("This is the positionTresoG sens if it's showed then it worked.", positionTresoG.getSensG());
                System.out.println("This is the positionTresoG sens if it's showed then it worked. " +  positionTresoG.getSensG());
                System.out.println("This is the positionTresoG volume if it's showed then it worked. " +  positionTresoG.getVolume());

            } else if (positionTresoD.getSens() == SensD.PRET) {
                positionTresoG.setVolume(positionTresoG.getVolume() - positionTresoD.getMontant());
                System.out.println("This is the positionTresoG sens if it's showed then it worked. " +  positionTresoG.getSensG());
                System.out.println("This is the positionTresoG volume if it's showed then it worked. " +  positionTresoG.getVolume());

            }
            logger.info("This is the positionTresoG sens if it's showed then it worked.", positionTresoG.getSensG());
            System.out.println("This is the positionTresoG sens if it's showed then it worked. lbara men ifelse" +  positionTresoG.getSensG());
            System.out.println("This is the positionTresoG volume if it's showed then it worked. " +  positionTresoG.getVolume());

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
////////yomken nbadel w nelhdem b dto

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

    @Override
    public List<Operation> SortOperationDateEcheance() {
        return operationr.findByOrderByDateEcheance();
    }
}
