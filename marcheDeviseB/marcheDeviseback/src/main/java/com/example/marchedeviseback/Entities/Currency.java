package com.example.marchedeviseback.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
//@Table(name = "devise")
//@Inheritance(strategy = InheritanceType.JOINED) // Héritage par jointure
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String symbol;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal borrow;
    private BigDecimal lend;
    private BigDecimal intrestaverage;
    private BigDecimal intrestspread;
    @Enumerated(EnumType.STRING)
    private CalculBasis calculationBasis;
    private LocalDateTime createdDate;

//
//    public Long getId() {
//        return id;
//    }
//
//    public String getLibelle() {
//        return libelle;
//    }
//
//
//    public String getSymbol() {
//        return symbol;
//    }
//
//    public float getBid() {
//        return bid;
//    }
//
//    public float getAsk() {
//        return ask;
//    }
//
//    public float getBorrow() {
//        return borrow;
//    }
//
//    public float getLend() {
//        return lend;
//    }
//
//    public float getIntrestaverage() {
//        return intrestaverage;
//    }
//
//    public float getIntrestspread() {
//        return intrestspread;
//    }
//
////    public LocalDateTime getLastUpdated() {
////        return lastUpdated;
////    }
//
//    public LocalDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setLibelle(String libelle) {
//        this.libelle = libelle;
//    }
//
//    public void setSymbol(String symbol) {
//        this.symbol = symbol;
//    }
//
//    public void setBid(float bid) {
//        this.bid = bid;
//    }
//
//    public void setAsk(float ask) {
//        this.ask = ask;
//    }
//
//    public void setBorrow(float borrow) {
//        this.borrow = borrow;
//    }
//
//    public void setLend(float lend) {
//        this.lend = lend;
//    }
//
//    public void setIntrestaverage(float intrestaverage) {
//        this.intrestaverage = intrestaverage;
//    }
//
//    public void setIntrestspread(float intrestspread) {
//        this.intrestspread = intrestspread;
//    }
//
////    public void setLastUpdated(LocalDateTime lastUpdated) {
////        this.lastUpdated = lastUpdated;
////    }
//
//    public void setCreatedDate(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }




}
