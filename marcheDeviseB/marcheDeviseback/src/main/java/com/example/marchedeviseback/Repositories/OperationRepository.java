package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {


    @Query("SELECT o FROM Operation o ORDER BY o.dateEcheance ASC")
    List<Operation> findByOrderByDateEcheance();

}
