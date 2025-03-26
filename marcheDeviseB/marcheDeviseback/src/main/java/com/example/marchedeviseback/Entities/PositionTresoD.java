package com.example.marchedeviseback.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionTresoD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SensD sens;
    private float montant;
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL)
    Devise devise;

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
