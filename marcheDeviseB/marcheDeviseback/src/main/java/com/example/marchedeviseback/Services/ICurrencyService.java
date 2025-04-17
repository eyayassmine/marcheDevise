package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.Currency;

import java.util.List;

public interface ICurrencyService {

    Currency addCurrency(Currency currency);
//    void simulateRates();
    List<Currency> retrieveAllCurrencies();

    Currency retrieveCurrency(Long id);
    Currency updateCurrency(Currency currency, Long id);

    void deleteCurrency(Long id);

    List<Currency> filterCurrenciesbyLabel(String filterText);

}
