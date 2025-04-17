package com.example.marchedeviseback.DTOs;

import java.math.BigDecimal;

public class MaturityCalculationResult {

    private BigDecimal maturityRate;
    private BigDecimal maturityAmount;
    private BigDecimal givedAmount;

    // Constructor, Getters and Setters
    public MaturityCalculationResult(BigDecimal maturityRate, BigDecimal maturityAmount, BigDecimal givedAmount) {
        this.maturityRate = maturityRate;
        this.maturityAmount = maturityAmount;
        this.givedAmount = givedAmount;
    }

    public BigDecimal getMaturityRate() {
        return maturityRate;
    }

    public BigDecimal getMaturityAmount() {
        return maturityAmount;
    }

    public BigDecimal getGivedAmount() {
        return givedAmount;
    }

}