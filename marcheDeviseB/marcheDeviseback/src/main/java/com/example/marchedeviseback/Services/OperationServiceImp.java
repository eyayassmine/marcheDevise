package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.DTOs.MaturityCalculationInput;
import com.example.marchedeviseback.DTOs.MaturityCalculationResult;
import com.example.marchedeviseback.Entities.*;
import com.example.marchedeviseback.Repositories.*;
import com.example.marchedeviseback.Repositories.GCashPositionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.time.Duration;

@Service
public class OperationServiceImp implements IOperationService {

    @Autowired
    OperationRepository operationr;
    @Autowired
    DCashPositionRepository dcashPositionr;
    @Autowired
    GCashPositionRepository gcashPositionr;
    @Autowired
    CurrencyRepository currencyr;
//    @Autowired
//    RateHRepository rateHr;

    //private static final Logger logger = LoggerFactory.getLogger(OperationServiceImp.class);



    public MaturityCalculationResult calculateMaturityDetails(MaturityCalculationInput maturityCalculationInput) {
        LocalDateTime operationDate = LocalDateTime.now();
        BigDecimal amount = maturityCalculationInput.getAmount();
        LocalDateTime maturityDate = maturityCalculationInput.getMaturityDate();

        int operationYear = operationDate.getYear();
        int maturityYear = maturityDate.getYear();
        int yearsNb = maturityYear - operationYear;

        BigDecimal intrestRate = maturityCalculationInput.getIntrestRate()
                .divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);

        BigDecimal maturityRate = BigDecimal.ZERO;
        String label = maturityCalculationInput.getLabel();
        Currency currency = (Currency) currencyr.findByLabel(label);
        CalculBasis calculationBasis = currency.getCalculationBasis();
        switch (calculationBasis) {
            case Y360: {
                long days = Duration.between(operationDate, maturityDate).toDays();
                maturityRate = amount.multiply(intrestRate)
                        .multiply(BigDecimal.valueOf(days))
                        .divide(BigDecimal.valueOf(360), MathContext.DECIMAL128);
                break;
            }
            case Y365: {
                long days = Duration.between(operationDate, maturityDate).toDays();
                maturityRate = amount.multiply(intrestRate)
                        .multiply(BigDecimal.valueOf(days))
                        .divide(BigDecimal.valueOf(365), MathContext.DECIMAL128);
                break;
            }
            case Y365_OR_366: {
                if (yearsNb == 0) {
                    int daysInYear = checkIfLeapYearFunction(operationYear);
                    long durationDays = Duration.between(operationDate, maturityDate).toDays();

                    maturityRate = amount.multiply(intrestRate)
                            .multiply(BigDecimal.valueOf(durationDays))
                            .divide(BigDecimal.valueOf(daysInYear), MathContext.DECIMAL128);

                } else {
                    // First year
                    LocalDate endOfOperationYear = LocalDate.of(operationYear, 12, 31);
                    long daysStart = Duration.between(operationDate, endOfOperationYear.atStartOfDay()).toDays();
                    int daysInStartYear = checkIfLeapYearFunction(operationYear);

                    BigDecimal part1 = amount.multiply(intrestRate)
                            .multiply(BigDecimal.valueOf(daysStart))
                            .divide(BigDecimal.valueOf(daysInStartYear), MathContext.DECIMAL128);

                    // Last year
                    LocalDate startOfMaturityYear = LocalDate.of(maturityYear, 1, 1);
                    long daysEnd = Duration.between(startOfMaturityYear.atStartOfDay(), maturityDate).toDays();
                    int daysInEndYear = checkIfLeapYearFunction(maturityYear);

                    BigDecimal part2 = amount.multiply(intrestRate)
                            .multiply(BigDecimal.valueOf(daysEnd))
                            .divide(BigDecimal.valueOf(daysInEndYear), MathContext.DECIMAL128);

                    maturityRate = part1.add(part2);

                    // Full years in between
                    for (int i = operationYear + 1; i < maturityYear; i++) {
                        maturityRate = maturityRate.add(amount.multiply(intrestRate));
                    }
                }
                break;
            }
        }

        BigDecimal maturityAmount;
        BigDecimal givedAmount;

        if (maturityCalculationInput.getIntrestType() == IntrestType.POSTCOUNT) {
            maturityAmount = amount.add(maturityRate);
            givedAmount = amount;
        } else {
            maturityAmount = amount;
            givedAmount = amount.subtract(maturityRate);
        }

        return new MaturityCalculationResult(maturityRate, maturityAmount, givedAmount);
    }

    int checkIfLeapYearFunction(int year) {
        return (year % 4 == 0) ? 366 : 365;
    }


    @Transactional
    @Override
    public Operation addOperation(Operation operation) {



//        if (operation.getCurrency() == null || operation.getCurrency().getId() == null) {
//            throw new IllegalArgumentException("Currency or Currency.id is null");
//        }
//
//        Currency currency = currencyr.findById(operation.getCurrency().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Currency not found"));
//
//        operation.setCurrency(currency);
// Check if currency is set
        if (operation.getCurrency() == null) {
            throw new IllegalArgumentException("Currency is required");
        }

        String label = operation.getCurrency().getLabel(); // or .getSymbol()
        if (label == null || label.isEmpty()) {
            throw new IllegalArgumentException("Currency label is required");
        }

// Find the currency by label (or symbol)
        Currency currency = currencyr.findByLabel(label);
        operation.setCurrency(currency);

        operation.setOperationDate(java.time.LocalDateTime.now());

        //////calcul
        BigDecimal amount = operation.getAmount();
        int operationYear = operation.getOperationDate().getYear();
        int maturityYear = operation.getMaturityDate().getYear();
        int yearsNb = maturityYear - operationYear;
        BigDecimal intrestRate = operation.getIntrestRate();
        intrestRate = intrestRate.divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);
        BigDecimal amountSet = operation.getAmount();
        BigDecimal maturityRate = BigDecimal.ZERO;


        switch (operation.getCurrency().getCalculationBasis()) {
            case Y360: {
                Duration duration = Duration.between(operation.getOperationDate(), operation.getMaturityDate());
                System.out.println("duration " +duration);

                long days = duration.toDays();
                maturityRate = amount
                        .multiply(intrestRate)
                        .multiply(BigDecimal.valueOf(days))
                        .divide(BigDecimal.valueOf(360), MathContext.DECIMAL128);
                break;
            }
            case Y365: {
                Duration duration = Duration.between(operation.getOperationDate(), operation.getMaturityDate());

                long days = duration.toDays();
                maturityRate = amount
                        .multiply(intrestRate)
                        .multiply(BigDecimal.valueOf(days))
                        .divide(BigDecimal.valueOf(365), MathContext.DECIMAL128);
                break;
            }
            case Y365_OR_366: { // Assuming you named it like this
                if (yearsNb == 0) {
                    // same year
                    int daysInYear = checkIfLeapYearFunction(operationYear);
                    Duration duration = Duration.between(operation.getOperationDate(), operation.getMaturityDate());
                    long durationDays = duration.toDays();

                    maturityRate = amount
                            .multiply(intrestRate)
                            .multiply(BigDecimal.valueOf(durationDays))
                            .divide(BigDecimal.valueOf(daysInYear), MathContext.DECIMAL128);

                } else {
                    // multiple years
                    // 1. From operationDate to end of operationYear
                    LocalDate endOfOperationYear = LocalDate.of(operationYear, 12, 31);
                    Duration durationStart = Duration.between(operation.getOperationDate(), endOfOperationYear.atStartOfDay());
                    long daysStart = durationStart.toDays();
                    int daysInStartYear = checkIfLeapYearFunction(operationYear);

                    BigDecimal part1 = amount
                            .multiply(intrestRate)
                            .multiply(BigDecimal.valueOf(daysStart))
                            .divide(BigDecimal.valueOf(daysInStartYear), MathContext.DECIMAL128);

                    // 2. From start of maturityYear to maturityDate
                    LocalDate startOfMaturityYear = LocalDate.of(maturityYear, 1, 1);
                    Duration durationEnd = Duration.between(startOfMaturityYear.atStartOfDay(), operation.getMaturityDate());
                    long daysEnd = durationEnd.toDays();
                    int daysInEndYear = checkIfLeapYearFunction(maturityYear);

                    BigDecimal part2 = amount
                            .multiply(intrestRate)
                            .multiply(BigDecimal.valueOf(daysEnd))
                            .divide(BigDecimal.valueOf(daysInEndYear), MathContext.DECIMAL128);

                    maturityRate = part1.add(part2);

                    if (yearsNb > 1) {
                        // Add full years in between
                        int i;
                        for (i = operationYear + 1; i < maturityYear; i++) {
                            maturityRate = maturityRate.add(amount.multiply(intrestRate));
                        }
                    }
                }
                break;
            }

        }
        operation.setMaturityRate(maturityRate);
        //Final maturity amount = initial amount + maturityRate
        operation.setMaturityAmount(amount.add(maturityRate));
        if(operation.getIntrestType()==IntrestType.POSTCOUNT) {
            operation.setMaturityAmount(amount.add(maturityRate));
            operation.setGivedAmount(amount);
        }
        else
        {
            operation.setMaturityAmount(amount);
            operation.setGivedAmount(amount.subtract(maturityRate));        }

        ////fin de calcul

        DCashPosition dCashPosition = new DCashPosition();
        dCashPosition.setSense(operation.getType());
        dCashPosition.setAmount(operation.getGivedAmount());
        dCashPosition.setCreatedDate(operation.getOperationDate());
        dCashPosition.setOperation(operation);

        if (!dcashPositionr.existsByOperation_Currency_Symbol(operation.getCurrency().getSymbol())) {
            // it doesn't so it's the first one
            GCashPosition gCashPosition = new GCashPosition();

            gCashPosition.setVolume(
                    dCashPosition.getSense() == OpType.LEND
                            ? dCashPosition.getAmount().negate()
                            : dCashPosition.getAmount()
            );
            gCashPosition.setValueDate(dCashPosition.getCreatedDate());
            gcashPositionr.save(gCashPosition);
            dCashPosition.setGCashPosition(gCashPosition);
            dcashPositionr.save(dCashPosition);

        }
        else{
            String symbol = dCashPosition.getOperation().getCurrency().getSymbol();
            GCashPosition gCashPosition = dcashPositionr.findGCashPositionByCurrencySymbol(symbol);
            gCashPosition.setVolume(
                    gCashPosition.getVolume().add(
                            dCashPosition.getSense() == OpType.LEND
                                    ? dCashPosition.getAmount().negate()
                                    : dCashPosition.getAmount()
                    )
            );
            gCashPosition.setValueDate(operation.getOperationDate());
            gcashPositionr.save(gCashPosition);
            dCashPosition.setGCashPosition(gCashPosition);
            dcashPositionr.save(dCashPosition);

        }
//        dcashPositionr.save(dCashPosition);

        return operationr.save(operation);


    }


    //newpositionTresoG.setDeviseH(savedOperation.getDeviseH());

        /*if (operation.getDeviseH() != null && operation.getDeviseH().getId() != null) {
            DeviseH deviseH = deviseHr.findById(operation.getDeviseH().getId())
                    .orElseThrow(() -> new IllegalArgumentException("DeviseH not found"));
            operation.setDeviseH(deviseH);
        } else {
            throw new IllegalArgumentException("DeviseH or DeviseH.id is null");
        }*/

           ////////

//        if (operation.getDeviseH() != null && operation.getDeviseH().getSsymbol() != null) {
//            DeviseH deviseH = deviseHr.findBySsymbol(operation.getDeviseH().getSsymbol())
//                    .orElseThrow(() -> new IllegalArgumentException("DeviseH not found with symbol: " + operation.getDeviseH().getSsymbol()));
//
//            operation.setDeviseH(deviseH);
//        }
        // 2. Check if it's the first operation with that DeviseH.ssymbol





       /* boolean isFirstPosition = !operationr.existsByDeviseH_Ssymbol(operation.getRateH().getSsymbol());
        LocalDateTime twoDaysLater = LocalDateTime.now().plusDays(2);////kenet LocalDate kahaw mn ghir Time
*/




/*

        ////****hedhy ki kenou les dates de type Date taw maadch nesthakha khatr walew LocalDateTime
        //Date dateToSet = Date.from(twoDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        operation.setOperationDate(twoDaysLater);////kenet dateToSet

        // Save the operation
        Operation savedOperation = operationr.save(operation);
        //////////////////////////////*****///////////////////////
        //positionTresoD.setMontant(operation.getMontant());////menghir save ll operation save flekher
      //  DCashPosition.setAmount(savedOperation.getAmount());
        //DCashPosition.setCreatedDate(twoDaysLater);///kenou LdateToSet
        //OpType operationType =savedOperation.getType();///menghir save ll operation save felekher
        //OpType operationType =savedOperation.getType();
        //SensD sensDValue = operationType.toSensD();
        //DCashPosition.setSens(sensDValue);
        /////bidirectionelle
       // DCashPosition.setOperation(savedOperation);
        //com.example.marchedeviseback.Entities.DCashPosition savedDCashPosition = ptresoDr.save(DCashPosition);
        //System.out.println("systemout tekhdem ");

       // savedOperation.setPositionTresoD(DCashPosition);
        //if (isFirstPosition) {
            // If it's the first PositionTresoD for this DeviseH, create a new PositionTresoG
          //  GCashPosition newpositionTresoGCashPosition = new GCashPosition();
            //newpositionTresoG.setDeviseH(savedOperation.getDeviseH());
          //  newpositionTresoGCashPosition.setVolume(savedOperation.getMontant());
            //console()
     //       if (DCashPosition.getSens() == SensD.EMPRUNT) {
      //          newpositionTresoGCashPosition.setSensG(SensG.POSITIVE);
        //    } else {
     //           newpositionTresoGCashPosition.setSensG(SensG.NEGATIVE);
     //       }
            // Set groupementValeur date
    //        newpositionTresoGCashPosition.setGroupementValeur(twoDaysLater);///kenou dateToSet

            // Save the new PositionTresoG
   //         ptresoGr.save(newpositionTresoGCashPosition);
  //          savedDCashPosition.setGCashPosition(newpositionTresoGCashPosition);
 //           savedDCashPosition.setOperation(savedOperation);
//            ptresoDr.save(savedDCashPosition);
    //    } else {
    //        System.out.println("zzama tekhdem fl else " );
//            GCashPosition GCashPosition = ptresoGr.findPositionTresoGByDeviseHSsymbol(savedOperation.getRateH().getSsymbol());
//            //PositionTresoG positionTresoG = ptresoGr.findByDeviseH_Ssymbol(savedOperation.getDeviseH().getSsymbol());
//            /////////////bch nwari fl console bch nthabet//////////
//            System.out.println("This is the positionTresoG that I want to modify. ID: " + GCashPosition.getId());
//            logger.info("This is the positionTresoG that i want to modify.", GCashPosition.getId());
//            logger.info("This is the positionTresoG volume  i want to modify.", GCashPosition.getVolume());
//            System.out.println("This is the volume that I want to modify. Volume: " + GCashPosition.getVolume());
//            /////////////////////
//
//            // If this is not the first PositionTresoD for this DeviseH, modify the existing PositionTresoG
//            if (savedDCashPosition.getSens() == SensD.EMPRUNT) {
//                GCashPosition.setVolume(GCashPosition.getVolume() + savedDCashPosition.getMontant());
//                ////////////////bch nwari fl console bch nthabet////////
//                logger.info("This is the positionTresoG sens if it's showed then it worked.", GCashPosition.getSensG());
//                System.out.println("This is the positionTresoG sens if it's showed then it worked. " +  GCashPosition.getSensG());
//                System.out.println("This is the positionTresoG volume if it's showed then it worked. " +  GCashPosition.getVolume());
//                /////////////////////
//
//                }
//            else if (DCashPosition.getSens() == SensD.PRET) {
//                GCashPosition.setVolume(GCashPosition.getVolume() - savedDCashPosition.getMontant());
//                ////////////////bch nwari fl console bch nthabet////////
//                System.out.println("This is the positionTresoG sens if it's showed then it worked. " +  GCashPosition.getSensG());
//                System.out.println("This is the positionTresoG volume if it's showed then it worked. " +  GCashPosition.getVolume());
//                /////////////////////
//
//                }
//            ////////////////bch nwari fl console bch nthabet////////
//            logger.info("This is the positionTresoG sens if it's showed then it worked.", GCashPosition.getSensG());
//            System.out.println("This is the positionTresoG sens if it's showed then it worked. lbara men ifelse" +  GCashPosition.getSensG());
//            System.out.println("This is the positionTresoG volume if it's showed then it worked. " +  GCashPosition.getVolume());
//            /////////////////////
//
//            // Set the SensG based on the volume
//            if (GCashPosition.getVolume() >= 0) {
//                GCashPosition.setSensG(SensG.POSITIVE);
//            } else {
//                GCashPosition.setSensG(SensG.NEGATIVE);
//            }
//
//            // Set groupementValeur date
//            GCashPosition.setGroupementValeur(twoDaysLater);/////kenou dateToSet
//
//            // Save the updated PositionTresoG
//            ptresoGr.save(GCashPosition);
//            savedDCashPosition.setGCashPosition(GCashPosition);
//            savedDCashPosition.setOperation(savedOperation);
//            ptresoDr.save(savedDCashPosition);
//        }




//        operationr.save(savedOperation);
//        OperationDTO operationDTO = new OperationDTO();
//        operationDTO.setId(savedOperation.getId());
//        operationDTO.setMontant(savedOperation.getAmount());
//        operationDTO.setDate(savedOperation.getOperationDate());
//        operationDTO.setDeviseHId(savedOperation.getRateH().getId());
//        operationDTO.setDeviseHLabel(savedOperation.getRateH().getSlibelle());
//        operationDTO.setDeviseHSsymbol(savedOperation.getRateH().getSsymbol());
//
//        return operationDTO;
///////////////////////////////****//////
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

    /*@Override
    public List<Operation> SortOperationMaturityDate() {
        return operationr.findByOrderByMaturityDate();
    }*/
}









////fl add operation ll diagramme lkdim


    ///////diagramme de class lkdim
//    boolean isFirstPosition = !ptresoDr.existsByDeviseH_Ssymbol(savedOperation.getDeviseH().getSsymbol());
//
//    // Create and save PositionTresoD
//    PositionTresoD positionTresoD = new PositionTresoD();
//        positionTresoD.setDeviseH(savedOperation.getDeviseH());
//                positionTresoD.setMontant(savedOperation.getMontant());
//                positionTresoD.setDate(dateToSet);
//
//                //positionTresoD.setSens(savedOperation.getType().toSensD()); // Assuming conversion of OpType to SensD
//                OpType operationType =savedOperation.getType();
//                SensD sensDValue = operationType.toSensD();
//                positionTresoD.setSens(sensDValue);
//
//                ptresoDr.save(positionTresoD);
//                System.out.println("systemout tekhdem ");
//
//                // Check if this is the first PositionTresoD for the given DeviseH
//                // Handle PositionTresoG
//                if (isFirstPosition) {
//                // If it's the first PositionTresoD for this DeviseH, create a new PositionTresoG
//                PositionTresoG newpositionTresoG = new PositionTresoG();
//                newpositionTresoG.setDeviseH(savedOperation.getDeviseH());
//                newpositionTresoG.setVolume(savedOperation.getMontant());
//                //console()
//                if (positionTresoD.getSens() == SensD.EMPRUNT) {
//                newpositionTresoG.setSensG(SensG.POSITIVE);
//                } else {
//                newpositionTresoG.setSensG(SensG.NEGATIVE);
//                }
//                // Set groupementValeur date
//                newpositionTresoG.setGroupementValeur(dateToSet);
//
//                // Save the new PositionTresoG
//                ptresoGr.save(newpositionTresoG);
//                } else {
//                System.out.println("zzama tekhdem fl else " );
//                PositionTresoG positionTresoG = ptresoGr.findByDeviseH_Ssymbol(savedOperation.getDeviseH().getSsymbol());
//                System.out.println("This is the positionTresoG that I want to modify. ID: " + positionTresoG.getId());
//                logger.info("This is the positionTresoG that i want to modify.", positionTresoG.getId());
//                logger.info("This is the positionTresoG volume  i want to modify.", positionTresoG.getVolume());
//                System.out.println("This is the volume that I want to modify. Volume: " + positionTresoG.getVolume());
//
//
//                // If this is not the first PositionTresoD for this DeviseH, modify the existing PositionTresoG
//                if (positionTresoD.getSens() == SensD.EMPRUNT) {
//                positionTresoG.setVolume(positionTresoG.getVolume() + positionTresoD.getMontant());
//                logger.info("This is the positionTresoG sens if it's showed then it worked.", positionTresoG.getSensG());
//                System.out.println("This is the positionTresoG sens if it's showed then it worked. " +  positionTresoG.getSensG());
//                System.out.println("This is the positionTresoG volume if it's showed then it worked. " +  positionTresoG.getVolume());
//
//                } else if (positionTresoD.getSens() == SensD.PRET) {
//                positionTresoG.setVolume(positionTresoG.getVolume() - positionTresoD.getMontant());
//                System.out.println("This is the positionTresoG sens if it's showed then it worked. " +  positionTresoG.getSensG());
//                System.out.println("This is the positionTresoG volume if it's showed then it worked. " +  positionTresoG.getVolume());
//
//                }
//                logger.info("This is the positionTresoG sens if it's showed then it worked.", positionTresoG.getSensG());
//                System.out.println("This is the positionTresoG sens if it's showed then it worked. lbara men ifelse" +  positionTresoG.getSensG());
//                System.out.println("This is the positionTresoG volume if it's showed then it worked. " +  positionTresoG.getVolume());
//
//                // Set the SensG based on the volume
//                if (positionTresoG.getVolume() >= 0) {
//                positionTresoG.setSensG(SensG.POSITIVE);
//                } else {
//                positionTresoG.setSensG(SensG.NEGATIVE);
//                }
//
//                // Set groupementValeur date
//                positionTresoG.setGroupementValeur(dateToSet);
//
//                // Save the updated PositionTresoG
//                ptresoGr.save(positionTresoG);
//                }
//
//                return savedOperation;
