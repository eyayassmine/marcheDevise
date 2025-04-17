package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.GCashPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GCashPositionRepository extends JpaRepository<GCashPosition, Long> {


    //PositionTresoG findByDeviseH_Ssymbol(String ssymbol);
/*
    @Query("SELECT ptd.positionTresoG FROM Operation op " +
            "JOIN op.DCashPosition ptd " +
            "JOIN op.currency currency " +
            "WHERE dh.ssymbol = :ssymbol")
    GCashPosition findPositionTresoGByDeviseHSsymbol(@Param("ssymbol") String ssymbol);
*/


    ///diagramme lkdim
    /*
    PositionTresoG findByDeviseH(DeviseH deviseH);

    PositionTresoG existsByDeviseH_Ssymbol(String ssymbol);*/



}
