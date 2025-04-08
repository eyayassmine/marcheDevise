package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.Devise;
import com.example.marchedeviseback.Entities.DeviseH;
import com.example.marchedeviseback.Entities.PositionTresoG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionTresoGRepository extends JpaRepository<PositionTresoG, Long> {

    PositionTresoG findByDeviseH(DeviseH deviseH);

    PositionTresoG existsByDeviseH_Ssymbol(String ssymbol);
    PositionTresoG findByDeviseH_Ssymbol(String ssymbol);

}
