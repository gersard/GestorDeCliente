package com.example.gerardo.gestordeclientes.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gerardo on 23/07/2016.
 */
public class Moto extends RealmObject {

    @PrimaryKey
    private String idMoto;

    private Marca marca;
    private Modelo modelo;
    private int año;

    public Moto() {
    }

    public String getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(String idMoto) {
        this.idMoto = idMoto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
}
