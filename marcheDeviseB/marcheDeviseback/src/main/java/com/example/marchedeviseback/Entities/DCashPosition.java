package com.example.marchedeviseback.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DCashPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OpType sense;
    private BigDecimal amount;
    private LocalDateTime createdDate;

    @ManyToOne (cascade = CascadeType.ALL )
    Operation operation;
    //////jdida
    @ManyToOne(cascade = CascadeType.ALL)
    GCashPosition GCashPosition;

    ///ekdleb l orientation
    /*
    @OneToOne(cascade = CascadeType.ALL)
    Operation operation;*/

    /////diagramme de classe lkdim
    /*@OneToOne(cascade = CascadeType.ALL)
    DeviseH deviseH;*/


//    public Long getId() {
//        return id;
//    }
//
//    public SensD getSens() {
//        return sens;
//    }
//
//    public float getMontant() {
//        return montant;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public Devise getDevise() {
//        return devise;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setSens(SensD sens) {
//        this.sens = sens;
//    }
//
//    public void setMontant(float montant) {
//        this.montant = montant;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public void setDevise(Devise devise) {
//        this.devise = devise;
//    }

}
