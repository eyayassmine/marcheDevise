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



}
