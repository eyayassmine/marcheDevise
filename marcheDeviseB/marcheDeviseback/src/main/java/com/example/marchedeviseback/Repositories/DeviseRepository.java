package com.example.marchedeviseback.Repositories;

import com.example.marchedeviseback.Entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {

    Devise findByLibelle(String libelle);

//    @Transactional
//    @Modifying
//    @Query("UPDATE Complaint c SET c.priority = :priority, c.status = 1 WHERE c.id = :complaintId")
//    void setPriority(Long complaintId, Integer priority);
//
//
//    // Method for user update (only allowed if status is 0)
//    @Transactional
//    @Modifying
//    @Query("UPDATE Complaint c SET c.title = :title, c.complaintCategory = :complaintCategory, " +
//            "c.description = :description, c.courseId = :courseId, c.updatedDate = CURRENT_TIMESTAMP " +
//            "WHERE c.id = :complaintId AND c.status = 0")
//    void specialUpdate(Long complaintId, String title, ComplaintCategory complaintCategory,
//                       String description, Long courseId);
//

    @Query("SELECT d FROM Devise d ORDER BY d.borrow ASC")
    List<Devise> findAllByBorrowAsc();

    @Query("SELECT d FROM Devise d ORDER BY d.lend ASC")
    List<Devise> findAllByOrderByLendAsc();

//    @Query("SELECT c FROM Devise c WHERE (:status IS NULL OR c.status = :status)")
//    List<Devise> findDevisesByStatus(@Param("status") String status);
//
//    @Query("SELECT c FROM Devise c WHERE (:status IS NULL OR c.deviseCategory = :deviseCategory)")
//    List<Devise> findDevisesByDeviseCategory(@Param("deviseCategory") String deviseCategory);

    List<Devise> findByLibelleContainingIgnoreCase(String libelle);




}
