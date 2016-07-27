package com.example.gerardo.gestordeclientes.model;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gerardo on 26/07/2016.
 */
public class Modelo extends RealmObject implements RealmModel {

    @PrimaryKey
    private String idModelo;

    private String nombreModelo;

    public Modelo() {
    }

    public String getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(String idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
}
