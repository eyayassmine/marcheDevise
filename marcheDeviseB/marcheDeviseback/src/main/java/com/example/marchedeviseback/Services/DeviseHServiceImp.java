package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.Devise;
import com.example.marchedeviseback.Entities.DeviseH;
import com.example.marchedeviseback.Repositories.DeviseHRepository;
import com.example.marchedeviseback.Repositories.DeviseRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
//@Getter
@Transactional
@Slf4j
public class DeviseHServiceImp implements IDeviseHService{

    @Autowired
    DeviseRepository deviser;
    @Autowired
    DeviseHRepository deviseHr;



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

    public List<DeviseH> getNewOrUpdatedDevises(LocalDateTime lastTimestamp) {

        return deviseHr.findByLastUpdatedAfter(lastTimestamp);
    }
    private float updateValue(float a, float b) {
        return a + b;
    }


    @Scheduled(fixedRate = 20000) // Toutes les secondes
    @Override
    public void simulateRates() {
        List<Devise> devises = deviser.findAll();
        for (Devise devise : devises) {

            boolean x, y;


            //float change = (random.nextFloat() * 0.1f) - 0.01f;
            float[] choices = {-0.06f, -0.05f, -0.04f,
                    -0.03f, -0.02f, -0.01f, 0.0f, 0.01f, 0.02f, 0.03f,
                    0.04f, 0.05f, 0.06f, 0.07f, 0.08f, 0.09f, 0.1f};

            Random random = new Random();

            // Randomly select an index from the array
            int randomIndex = random.nextInt(choices.length);
            int randomIndex1 = random.nextInt(choices.length);

            // Get the random number from the array based on the index
            float randomNumber = choices[randomIndex];
            float randomNumber1 = choices[randomIndex1];

            // Debugging logs
            System.out.println("Random Index Borrow: " + randomIndex + " => " + choices[randomIndex]);
            System.out.println("Random Index Lend: " + randomIndex1 + " => " + choices[randomIndex1]);
            // Simplify boolean assignment: assign true if conditions are met, false otherwise

            float newBorrow = updateValue(devise.getBorrow(), randomNumber);
            System.out.println("value of newBorrow  is " + newBorrow);
            float newLend = updateValue(devise.getLend(), randomNumber1);
            System.out.println("value of newLend  is " + newLend);

            x = (newBorrow > 0.20 && newBorrow < 20) && (newLend > 0.20 && newLend < 20);
            y = (newBorrow > newLend);

            if(newBorrow < newLend)
            {
                System.out.println("borrow is inferieur a lend ");
                System.out.println("borrow is less than lend ");
                System.out.println("borrow is akal m lend ");
            }
            if (x && y) {

                DeviseH deviseH = new DeviseH();
                deviseH.setSborrow(newBorrow);
                deviseH.setSlend(newLend);
                deviseH.setSintrestaverage((newBorrow + newLend) / 2);
                deviseH.setSintrestspread(newBorrow - newLend);
                deviseH.setLastUpdated(LocalDateTime.now()); // Date actuelle
                deviseH.setSlibelle(devise.getLibelle());
                deviseH.setSsymbol(devise.getSymbol());
                deviseH.setDevise(devise); // Lien avec la Devise originale
                System.out.println("hehe ");
                deviseHr.save(deviseH);
                System.out.println("houha ");

            }
            //log.info("Rules not satisfied for Devise: {}", devise.getLibelle());
        }
    }

    @Override
    public List<DeviseH> retrieveAllDeviseHs() {
        return  (List<DeviseH>) deviseHr.findAll();
    }

    @Override
    public DeviseH retrieveDeviseH(Long id) {
        return deviseHr.findById(id).orElse(null);
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
