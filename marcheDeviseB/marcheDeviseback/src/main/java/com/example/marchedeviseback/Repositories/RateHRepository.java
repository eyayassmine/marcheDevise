package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.RateH;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RateHRepository extends JpaRepository<RateH, Long> {

    RateH findTopByOrderByLastUpdatedDesc();

        List<RateH> findByLastUpdatedAfter(LocalDateTime lastUpdated);

//    Optional<DeviseH> findBySsymbol(String ssymbol);  // Add this method

    //List<DeviseH> findByOriginalDevise(Devise originalDevise);  // Récupérer les versions simulées d’une devise
}
