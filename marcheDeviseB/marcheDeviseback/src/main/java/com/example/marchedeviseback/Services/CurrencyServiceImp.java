package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.Currency;
import com.example.marchedeviseback.Repositories.CurrencyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;


@Service
@Transactional
@Slf4j
public class CurrencyServiceImp implements ICurrencyService {

    @Autowired
    CurrencyRepository currencyr;


    @Override
    public Currency addCurrency(Currency currency) {

        BigDecimal lend = currency.getLend();     // BigDecimal
        BigDecimal borrow = currency.getBorrow(); // BigDecimal

        // Ensure borrow is always greater than lend
        if (borrow.compareTo(lend) <= 0) {
            throw new IllegalArgumentException("Lend must be less than Borrow.");
        }

        // (lend + borrow) / 2
        BigDecimal intrestAverage = lend.add(borrow).divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);

        // (borrow - lend)
        BigDecimal intrestSpread = borrow.subtract(lend);

        currency.setIntrestaverage(intrestAverage);
        currency.setIntrestspread(intrestSpread);
        currency.setCreatedDate(java.time.LocalDateTime.now());

        return currencyr.save(currency);



        ////////////kenou float ama radihom bigdecimal
        /*float lend = currency.getLend();
        float borrow = currency.getBorrow();

        // Ensure borrow is always less than lend
        if (borrow <= lend) {
            throw new IllegalArgumentException("Lend must be less than Borrow.");
        }

        currency.setIntrestaverage((lend + borrow) / 2);
        currency.setIntrestspread(borrow - lend);
        currency.setCreatedDate(java.time.LocalDateTime.now());
        return currencyr.save(currency);*/
    }

//    private float updateValue(float a, float b) {
//        return a + b;
//    }
//
//   @Scheduled(fixedRate = 10000) // Toutes les secondes
//    @Override
//    public void simulateRates() {
//        List<Devise> devises = deviser.findAll();
//        for (Devise devise : devises) {
//
//            float a, b, c, d;
//            boolean x, y;
//
//
//            //float change = (random.nextFloat() * 0.1f) - 0.01f;
//            float[] choices = { -0.06f, -0.05f, -0.04f,
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
//            Devise newDevise = new Devise();
//
//    // Simplify boolean assignment: assign true if conditions are met, false otherwise
////            a  = updateValue(devise.getBid(), randomNumber);
////            System.out.println("value of a is " +a);
////            b = updateValue(devise.getAsk(), randomNumber);
////            System.out.println("value of b is " +b);
//
//            c = updateValue(devise.getBorrow(), randomNumber);
//            System.out.println("value of c is " +c);
//            d = updateValue(devise.getLend(), randomNumber);
//            System.out.println("value of d is " +d);
//
////            if (a > 0.25 && a < 4) {
////                devise.setBid(a);
////            }
////
////            if (b > 0.25 && b < 4) {
////                devise.setAsk(b);
////            }
//
////            if (c > 0.20 && c < 6 && d > c)  {
////                devise.setLend(c);
////            }
////            if (d > 0.20 && d < 6 && d > c) {
////                devise.setBorrow(d);
////            }
//            x = (c > 0.20 && c < 6) && (d > 0.20 && d < 6);
//            y = (d>c);
//
////            newDevise.setBid(a);
////            newDevise.setAsk(b);
//            if (c > 0.20 && c < 6 && d > c)  {
//                devise.setLend(c);
//            }
//            if (d > 0.20 && d < 6 && d > c) {
//                devise.setBorrow(d);
//            }
//
//            newDevise.setLend(c);
//            newDevise.setBorrow(d);
//            ///System.out.println("hiiiii" );
//            newDevise.setIntrestaverage((devise.getBorrow() + devise.getLend()) / 2);
//            newDevise.setIntrestspread(devise.getBorrow() - devise.getLend());
//            newDevise.setCreatedDate(devise.getCreatedDate()
//            );
//            newDevise.setLastUpdated(java.time.LocalDateTime.now());
//            System.out.println("hehehe");
//            deviser.save(newDevise);
//
//            System.out.println("houha ");
//
//
//        }
//
//    }

    @Override
    public List<Currency> retrieveAllCurrencies() {
        return  (List<Currency>) currencyr.findAll();
    }

    @Override
    public Currency retrieveCurrency(Long id) {
        return currencyr.findById(id).orElse(null);
    }

    @Override
    public Currency updateCurrency(Currency currency, Long id) {
        if ((!currencyr.existsById(id)) ) {
            // If the project doesn't exist, you can handle the situation according to your requirements.
            // For example, you can throw an exception or return null.
            // Here, we are just returning null for simplicity.
            return null;
        }
        Currency existingCurrency = currencyr.findById(id).orElse(null);
        //existingcurrency.setLastUpdated(java.time.LocalDateTime.now());
        // Set the ID of the project to ensure it's updated properly
        existingCurrency.setId(id);

        // Save the updated project
        return currencyr.save(existingCurrency);
    }

    @Override
    public void deleteCurrency(Long id) {
        currencyr.deleteById(id);
    }

        @Override
    public  List<Currency> filterCurrenciesbyLabel(String filterText) {
        return currencyr.findByLabelContainingIgnoreCase(filterText);

    }

}
