package com.example.marchedeviseback.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GCashPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal volume;
    private LocalDateTime valueDate;
    //private Long groupementCorresp;
    /////nahi couplage fort w boucle infini w hala hlila
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "positionTresoG")
//    List<PositionTresoD> positionTresoDs;

    ///ekdleb l oreintation
    /*
    @OneToMany(cascade = CascadeType.ALL)
    List<PositionTresoD> positionTresoD;
    */


    //////diagramme lkdim
    /*
    @OneToOne
    private DeviseH deviseH;
*/


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
