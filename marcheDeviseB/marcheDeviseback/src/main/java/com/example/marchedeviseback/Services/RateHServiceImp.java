package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.Currency;
import com.example.marchedeviseback.Entities.RateH;
import com.example.marchedeviseback.Repositories.RateHRepository;
import com.example.marchedeviseback.Repositories.CurrencyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
//@Getter
@Transactional
@Slf4j
public class RateHServiceImp implements IRateHService {

    @Autowired
    CurrencyRepository currencyr;
    @Autowired
    RateHRepository rateHr;



    // List to hold active SSE connections
//    private final List<SseEmitter> emitters = new ArrayList<>();
//
//    public List<SseEmitter> getEmitters() {
//        return emitters;
//    }
//
//    // Method to register clients
//    public void addEmitter(SseEmitter emitter) {
//        emitters.add(emitter);
//    }
//
//    public void removeEmitter(SseEmitter emitter) {
//        emitters.remove(emitter);
//    }

    public List<RateH> getNewOrUpdatedCurrencies(LocalDateTime lastTimestamp) {

        return rateHr.findByLastUpdatedAfter(lastTimestamp);
    }
//    private float updateValue(float a, float b) {
//        return a + b;
//    }

    private BigDecimal updateValue(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }


    @Scheduled(fixedRate = 20000) // Toutes les secondes
    @Override
    public void simulateRates() {
        List<Currency> currencies = currencyr.findAll();
        for (Currency currency : currencies) {

            boolean x, y;


            //float change = (random.nextFloat() * 0.1f) - 0.01f;
            BigDecimal[] choices = {
                    new BigDecimal("-0.06"), new BigDecimal("-0.05"), new BigDecimal("-0.04"),
                    new BigDecimal("-0.03"), new BigDecimal("-0.02"), new BigDecimal("-0.01"),
                    new BigDecimal("0.0"), new BigDecimal("0.01"), new BigDecimal("0.02"),
                    new BigDecimal("0.03"), new BigDecimal("0.04"), new BigDecimal("0.05"),
                    new BigDecimal("0.06"), new BigDecimal("0.07"), new BigDecimal("0.08"),
                    new BigDecimal("0.09"), new BigDecimal("0.10")
            };


            Random random = new Random();

            // Randomly select an index from the array
            int randomIndex = random.nextInt(choices.length);
            int randomIndex1 = random.nextInt(choices.length);

            // Get the random number from the array based on the index
            BigDecimal randomNumber = choices[randomIndex];
            BigDecimal randomNumber1 = choices[randomIndex1];

            // Debugging logs
            System.out.println("Random Index Borrow: " + randomIndex + " => " + choices[randomIndex]);
            System.out.println("Random Index Lend: " + randomIndex1 + " => " + choices[randomIndex1]);
            // Simplify boolean assignment: assign true if conditions are met, false otherwise

            // Update Borrow and Lend
            BigDecimal newBorrow = updateValue(currency.getBorrow(), randomNumber);
            System.out.println("value of newBorrow  is " + newBorrow);
            BigDecimal newLend = updateValue(currency.getLend(), randomNumber1);
            System.out.println("value of newLend  is " + newLend);

            x = (newBorrow.compareTo(new BigDecimal("0.20")) > 0 && newBorrow.compareTo(new BigDecimal("20")) < 0)
                    && (newLend.compareTo(new BigDecimal("0.20")) > 0 && newLend.compareTo(new BigDecimal("20")) < 0);

            y = (newBorrow.compareTo(newLend) > 0);

            if (newBorrow.compareTo(newLend) < 0) {
                System.out.println("borrow is inferieur a lend ");
                System.out.println("borrow is less than lend ");
                System.out.println("borrow is akal m lend ");
            }
            if (x && y) {

                RateH rateH = new RateH();
                rateH.setSborrow(newBorrow);
                rateH.setSlend(newLend);
                rateH.setSintrestaverage(newBorrow.add(newLend).divide(new BigDecimal("2")));
                rateH.setSintrestspread(newBorrow.subtract(newLend));
                rateH.setLastUpdated(LocalDateTime.now()); // Date actuelle
                rateH.setLabel(currency.getLabel());
                rateH.setSsymbol(currency.getSymbol());
                rateH.setCurrency(currency); // Lien avec la Devise originale
                System.out.println("hehe ");
                rateHr.save(rateH);
                System.out.println("houha ");

            }
            //log.info("Rules not satisfied for Devise: {}", devise.getLibelle());
        }
    }


//
//    @Scheduled(fixedRate = 20000) // Toutes les secondes
//    @Override
//    public void simulateRates() {
//        List<Currency> currencies = deviser.findAll();
//        for (Currency currency : currencies) {
//
//            boolean x, y;
//
//
//            //float change = (random.nextFloat() * 0.1f) - 0.01f;
//            float[] choices = {-0.06f, -0.05f, -0.04f,
//                    -0.03f, -0.02f, -0.01f, 0.0f, 0.01f, 0.02f, 0.03f,
//                    0.04f, 0.05f, 0.06f, 0.07f, 0.08f, 0.09f, 0.1f};
//
//            Random random = new Random();
//
//            // Randomly select an index from the array
//            int randomIndex = random.nextInt(choices.length);
//            int randomIndex1 = random.nextInt(choices.length);
//
//            // Get the random number from the array based on the index
//            float randomNumber = choices[randomIndex];
//            float randomNumber1 = choices[randomIndex1];
//
//            // Debugging logs
//            System.out.println("Random Index Borrow: " + randomIndex + " => " + choices[randomIndex]);
//            System.out.println("Random Index Lend: " + randomIndex1 + " => " + choices[randomIndex1]);
//            // Simplify boolean assignment: assign true if conditions are met, false otherwise
//
//            float newBorrow = updateValue(currency.getBorrow(), randomNumber);
//            System.out.println("value of newBorrow  is " + newBorrow);
//            float newLend = updateValue(currency.getLend(), randomNumber1);
//            System.out.println("value of newLend  is " + newLend);
//
//            x = (newBorrow > 0.20 && newBorrow < 20) && (newLend > 0.20 && newLend < 20);
//            y = (newBorrow > newLend);
//
//            if(newBorrow < newLend)
//            {
//                System.out.println("borrow is inferieur a lend ");
//                System.out.println("borrow is less than lend ");
//                System.out.println("borrow is akal m lend ");
//            }
//            if (x && y) {
//
//                RateH rateH = new RateH();
//                rateH.setSborrow(newBorrow);
//                rateH.setSlend(newLend);
//                rateH.setSintrestaverage((newBorrow + newLend) / 2);
//                rateH.setSintrestspread(newBorrow - newLend);
//                rateH.setLastUpdated(LocalDateTime.now()); // Date actuelle
//                rateH.setSlibelle(currency.getLibelle());
//                rateH.setSsymbol(currency.getSymbol());
//                rateH.setCurrency(currency); // Lien avec la Devise originale
//                System.out.println("hehe ");
//                deviseHr.save(rateH);
//                System.out.println("houha ");
//
//            }
//            //log.info("Rules not satisfied for Devise: {}", devise.getLibelle());
//        }
//    }

    @Override
    public List<RateH> retrieveAllRateHs() {
        return  (List<RateH>) rateHr.findAll();
    }

    @Override
    public RateH retrieveRateH(Long id) {
        return rateHr.findById(id).orElse(null);
    }


//    @Scheduled(fixedRate = 10000) // Toutes les secondes
//    @Override
//    public void simulateRates() {
//        List<Devise> devises = deviser.findAll();
//        for (Devise devise : devises) {
//
//            boolean x, y;
//
//
//            //float change = (random.nextFloat() * 0.1f) - 0.01f;
//            float[] choices = {-0.06f, -0.05f, -0.04f,
//                    -0.03f, -0.02f, -0.01f, 0.0f, 0.01f, 0.02f, 0.03f,
//                    0.04f, 0.05f, 0.06f, 0.07f, 0.08f, 0.09f, 0.1f};
//
//            Random random = new Random();
//
//            // Randomly select an index from the array
//            int randomIndex = random.nextInt(choices.length);
//
//            // Get the random number from the array based on the index
//            float randomNumber = choices[randomIndex];
//
//            // Simplify boolean assignment: assign true if conditions are met, false otherwise
//
//            float newBorrow = updateValue(devise.getBorrow(), randomNumber);
//            System.out.println("value of newBorrow  is " + newBorrow);
//            float newLend = updateValue(devise.getLend(), randomNumber);
//            System.out.println("value of newLend  is " + newLend);
//
//            x = (newBorrow > 0.20 && newBorrow < 20) && (newLend > 0.20 && newLend < 20);
//            y = (newBorrow > newLend);
//
//            if(newBorrow < newLend)
//            {
//                System.out.println("borrow is inferieur a lend ");
//                System.out.println("borrow is less than lend ");
//                System.out.println("borrow is akal a lend ");
//            }
//            if (x && y) {
//
//                DeviseH deviseH = new DeviseH();
//                deviseH.setBorrow(newBorrow);
//                deviseH.setLend(newLend);
//                deviseH.setIntrestaverage((newBorrow + newLend) / 2);
//                deviseH.setIntrestspread(newBorrow - newLend);
//                deviseH.setCreatedDate(devise.getCreatedDate()); // Même date que Devise
//                deviseH.setLastUpdated(LocalDateTime.now()); // Date actuelle
//                deviseH.setLibelle(devise.getLibelle());
//                deviseH.setSymbol(devise.getSymbol());
//                deviseH.setOriginalDevise(devise); // Lien avec la Devise originale
//                            System.out.println("hehe ");
//                deviseHr.save(deviseH);
//                            System.out.println("houha ");
//
//            }
//            //log.info("Rules not satisfied for Devise: {}", devise.getLibelle());
//        }
//    }



    // Notify all registered clients about the new DeviseH update
//    public void notifyClients(DeviseH deviseH) {
//        for (SseEmitter emitter : emitters) {
//            try {
//                emitter.send(deviseH); // Send the updated DeviseH to the client
//            } catch (Exception e) {
//                //log.error("Error sending SSE event", e);
//                emitters.remove(emitter); // Remove the emitter if an error occurs
//            }
//        }
//    }


}
