package com.example.marchedeviseback.DTOs;

import com.example.marchedeviseback.Entities.CalculBasis;
import com.example.marchedeviseback.Entities.IntrestType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MaturityCalculationInput {

    private BigDecimal amount;
    private BigDecimal intrestRate;
    private IntrestType intrestType;
//    private CalculBasis calculationBasis;
    private String label;
    private LocalDateTime maturityDate;

}
