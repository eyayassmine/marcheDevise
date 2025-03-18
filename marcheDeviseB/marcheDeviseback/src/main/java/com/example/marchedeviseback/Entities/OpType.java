package com.example.marchedeviseback.Entities;

public enum OpType {

    PRET,
    EMPRUNT;
    public SensD toSensD() {
        return SensD.valueOf(this.name()); // Convert OpType -> SensD
    }

}
