package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.Devise;
import com.example.marchedeviseback.Entities.PositionTresoG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionTresoGRepository extends JpaRepository<PositionTresoG, Long> {

    PositionTresoG findByDevise(Devise devise);

}
