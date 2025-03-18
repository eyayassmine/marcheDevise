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
    Devise devise;
    //Devise;

}
