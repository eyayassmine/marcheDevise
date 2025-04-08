package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.Devise;
import com.example.marchedeviseback.Entities.DeviseH;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DeviseHRepository extends JpaRepository<DeviseH, Long> {

    DeviseH findTopByOrderByLastUpdatedDesc();

        List<DeviseH> findByLastUpdatedAfter(LocalDateTime lastUpdated);

//    Optional<DeviseH> findBySsymbol(String ssymbol);  // Add this method

    //List<DeviseH> findByOriginalDevise(Devise originalDevise);  // Récupérer les versions simulées d’une devise
}
