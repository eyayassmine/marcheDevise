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
public class PositionTresoG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SensG sensG;
    private float volume;
    private Date groupementValeur;
    //private Long groupementCorresp;
    @OneToOne
    private DeviseH deviseH;

//    public Long getId() {
//        return id;
//    }
//
//    public SensG getSensG() {
//        return sensG;
//    }
//
//    public float getVolume() {
//        return volume;
//    }
//
//    public Date getGroupementValeur() {
//        return groupementValeur;
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
//    public void setSensG(SensG sensG) {
//        this.sensG = sensG;
//    }
//
//    public void setVolume(float volume) {
//        this.volume = volume;
//    }
//
//    public void setGroupementValeur(Date groupementValeur) {
//        this.groupementValeur = groupementValeur;
//    }
//
//    public void setDevise(Devise devise) {
//        this.devise = devise;
//    }

}
