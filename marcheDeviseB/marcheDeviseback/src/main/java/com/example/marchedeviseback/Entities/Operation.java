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
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long opnum;
    @Enumerated(EnumType.STRING)
    private OpType type;
    private float montant;
    private Date date;
    private Date dateEcheance;
    @ManyToOne(cascade = CascadeType.ALL)
    DeviseH deviseH;
    //Devise;
//
//    public Long getId() {
//        return id;
//    }
//
//    public Long getOpnum() {
//        return opnum;
//    }
//
//    public OpType getType() {
//        return type;
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
//    public Date getDateEcheance() {
//        return dateEcheance;
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
//    public void setOpnum(Long opnum) {
//        this.opnum = opnum;
//    }
//
//    public void setType(OpType type) {
//        this.type = type;
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
//    public void setDateEcheance(Date dateEcheance) {
//        this.dateEcheance = dateEcheance;
//    }
//
//    public void setDevise(Devise devise) {
//        this.devise = devise;
//    }

}
