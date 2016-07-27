package com.example.gerardo.gestordeclientes.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gerardo on 26/07/2016.
 */
public class Marca extends RealmObject {

    @PrimaryKey
    private String idMarca;

    private String nombreMarca;
    private RealmList<Modelo> modelos;

    public Marca() {
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public RealmList<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(RealmList<Modelo> modelos) {
        this.modelos = modelos;
    }
}
