package com.example.marchedeviseback.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
//@Table(name = "devisesH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviseH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slibelle;

    private String ssymbol;
    private float sborrow;
    private float slend;
    private float sintrestaverage;
    private float sintrestspread;
    @ManyToOne
    @JoinColumn(name = "devise_id", nullable = false)
    private Devise devise;  // Reference to the original Devise entity

    private LocalDateTime lastUpdated;

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
