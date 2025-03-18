package com.example.marchedeviseback.Services;

import com.example.marchedeviseback.Entities.Devise;

import java.util.List;

public interface IDeviseService {

    Devise addDevise(Devise devise);
    void simulateRates();
    List<Devise> retrieveAllDevises();

    Devise retrieveDevise(Long id);
    Devise updateDevise(Devise devise, Long id);

    void deleteDevise(Long id);
}
