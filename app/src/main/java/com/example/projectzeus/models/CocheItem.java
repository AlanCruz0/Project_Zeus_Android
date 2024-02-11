package com.example.projectzeus.models;

import java.io.Serializable;

public class CocheItem implements Serializable {
    private String alias;
    private String descripcion;

    public CocheItem(String alias, String descripcion) {
        this.alias = alias;
        this.descripcion = descripcion;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
