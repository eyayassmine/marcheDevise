package com.example.marchedeviseback.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    //private Long opnum;
    @Enumerated(EnumType.STRING)
    private OpType type;
    private BigDecimal amount;//BigDecimal
    private LocalDateTime operationDate;
    private LocalDateTime maturityDate;
    private BigDecimal intrestRate;
    private BigDecimal maturityRate;
    @Enumerated(EnumType.STRING)
    private IntrestType intrestType;
    private BigDecimal maturityAmount;//BigDecimal
    private BigDecimal givedAmount;
    @ManyToOne(cascade = CascadeType.ALL)
    Currency currency;

    /*
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }*/




    /*    @ManyToOne(cascade = CascadeType.ALL)
    RateH rateH;*/

    /////jdida ken zemen
//    @OneToOne(cascade = CascadeType.ALL)
//    PositionTresoD positionTresoD;
/*
    public RateH getRateH() {
        return rateH;
    }

    public void setRateH(RateH rateH) {
        this.rateH = rateH;
    }

    */
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
