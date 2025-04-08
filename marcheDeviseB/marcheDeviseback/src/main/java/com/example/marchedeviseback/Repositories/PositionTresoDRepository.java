package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.Devise;
import com.example.marchedeviseback.Entities.DeviseH;
import com.example.marchedeviseback.Entities.PositionTresoD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionTresoDRepository extends JpaRepository<PositionTresoD, Long> {

    boolean existsByDeviseH(DeviseH deviseH);

    boolean existsByDeviseH_Ssymbol(String ssymbol);
}
