package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {

    Devise findByLibelle(String libelle);

}
