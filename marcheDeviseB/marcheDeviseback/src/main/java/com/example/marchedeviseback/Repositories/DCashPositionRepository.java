package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.DCashPosition;
import com.example.marchedeviseback.Entities.GCashPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DCashPositionRepository extends JpaRepository<DCashPosition, Long> {



    boolean existsByOperation_Currency_Symbol(String symbol);
    @Query("SELECT d.GCashPosition FROM DCashPosition d WHERE d.operation.currency.symbol = :symbol")
    GCashPosition findGCashPositionByCurrencySymbol(@Param("symbol") String symbol);

    ///diagramme lkdim
    //boolean existsByDeviseH(DeviseH deviseH);
///diagramme ldkim
    //boolean existsByDeviseH_Ssymbol(String ssymbol);

    ///boolean existsByOperation();



}
