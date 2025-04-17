package com.example.marchedeviseback.DTOs;

import com.example.marchedeviseback.Entities.OpType;
import com.example.marchedeviseback.Entities.RateH;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class OperationDTO {

    private Long id;
    @Enumerated(EnumType.STRING)
    private OpType type;
    private BigDecimal amount;//BigDecimal
    private LocalDateTime operationDate;
    private LocalDateTime maturityDate;
    private BigDecimal intrestRate;
    private BigDecimal maturityRate;
    private BigDecimal intestType;
    private BigDecimal maturityAmount;//BigDecimal
    private Long deviseHId;  // ID of DeviseH
    private String deviseHLabel; // Label of DeviseH
    private String deviseHSsymbol; // SSYMBOL of DeviseH


}
