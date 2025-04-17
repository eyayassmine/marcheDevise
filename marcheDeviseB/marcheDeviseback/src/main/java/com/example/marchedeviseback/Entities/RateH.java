package com.example.marchedeviseback.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@Table(name = "devisesH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String ssymbol;
    private BigDecimal sborrow;
    private BigDecimal slend;
    private BigDecimal sintrestaverage;
    private BigDecimal sintrestspread;
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;  // Reference to the original Devise entity


//    @ManyToOne
//    @JoinColumn(name = "id_origin",  referencedColumnName = "id")
//    private Devise originalDevise;  // Référence à la devise d'origine

//    public LocalDateTime getLastUpdated() {
//        return lastUpdated;
//    }
//
//    public Devise getOriginalDevise() {
//        return originalDevise;
//    }
//
//    public void setLastUpdated(LocalDateTime lastUpdated) {
//        this.lastUpdated = lastUpdated;
//    }
//
//    public void setOriginalDevise(Devise originalDevise) {
//        this.originalDevise = originalDevise;
//    }

}
