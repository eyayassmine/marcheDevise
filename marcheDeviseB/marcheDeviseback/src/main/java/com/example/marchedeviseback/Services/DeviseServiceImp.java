package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.Devise;
import com.example.marchedeviseback.Repositories.DeviseRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
@Transactional
@Slf4j
public class DeviseServiceImp implements IDeviseService {

    @Autowired
    DeviseRepository deviser;

    @Override
    public Devise addDevise(Devise devise) {
        float lend = devise.getLend();
        float borrow = devise.getBorrow();

        // Ensure borrow is always less than lend
        if (borrow <= lend) {
            throw new IllegalArgumentException("Lend must be less than Borrow.");
        }

        devise.setIntrestaverage((lend + borrow) / 2);
        devise.setIntrestspread(borrow - lend);

        return deviser.save(devise);
    }

    private float updateValue(float a, float b) {
        return a + b;
    }

    @Scheduled(fixedRate = 10000) // Toutes les secondes
    @Override
    public void simulateRates() {
        List<Devise> devises = deviser.findAll();
        for (Devise devise : devises) {

            float a, b, c, d;

            //float change = (random.nextFloat() * 0.1f) - 0.01f;
            float[] choices = { -0.06f, -0.05f, -0.04f,
                    -0.03f, -0.02f, -0.01f, 0.0f, 0.01f, 0.02f, 0.03f,
                    0.04f, 0.05f, 0.06f, 0.07f, 0.08f, 0.09f, 0.1f};

            Random random = new Random();

            // Randomly select an index from the array
            int randomIndex = random.nextInt(choices.length);

            // Get the random number from the array based on the index
            float randomNumber = choices[randomIndex];

    // Simplify boolean assignment: assign true if conditions are met, false otherwise
            a  = updateValue(devise.getBid(), randomNumber);
            System.out.println("value of a is " +a);
            b = updateValue(devise.getAsk(), randomNumber);
            System.out.println("value of b is " +b);
            c = updateValue(devise.getLend(), randomNumber);
            System.out.println("value of c is " +c);
            d = updateValue(devise.getBorrow(), randomNumber);
            System.out.println("value of d is " +d);

            if (a > 0.25 && a < 4) {
                devise.setBid(a);
            }

            if (b > 0.25 && b < 4) {
                devise.setAsk(b);
            }

            if (c > 0.20 && c < 6 && d > c)  {
                devise.setLend(c);
            }
            if (d > 0.20 && d < 6 && d > c) {
                devise.setBorrow(d);
            }
//            devise.setBid(a);
//            devise.setAsk(b);
//            devise.setLend(c);
//            devise.setBorrow(d);

            devise.setIntrestaverage((devise.getBorrow() + devise.getLend()) / 2);
            devise.setIntrestspread(devise.getBorrow() - devise.getLend());
            devise.setLastUpdated(java.time.LocalDateTime.now());
            deviser.save(devise);

        }

    }

    @Override
    public List<Devise> retrieveAllDevises() {
        return  (List<Devise>) deviser.findAll();
    }

    @Override
    public Devise retrieveDevise(Long id) {
        return deviser.findById(id).orElse(null);
    }

    @Override
    public Devise updateDevise(Devise devise, Long id) {
        if ((!deviser.existsById(id)) ) {
            // If the project doesn't exist, you can handle the situation according to your requirements.
            // For example, you can throw an exception or return null.
            // Here, we are just returning null for simplicity.
            return null;
        }
        Devise existingDevise = deviser.findById(id).orElse(null);
        existingDevise.setLastUpdated(java.time.LocalDateTime.now());
        // Set the ID of the project to ensure it's updated properly
        existingDevise.setId(id);

        // Save the updated project
        return deviser.save(existingDevise);
    }

    @Override
    public void deleteDevise(Long id) {
        deviser.deleteById(id);
    }
}
