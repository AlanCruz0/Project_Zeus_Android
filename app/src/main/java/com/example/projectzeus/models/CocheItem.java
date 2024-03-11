package com.example.projectzeus.models;

import java.io.Serializable;

public class CocheItem implements Serializable {
    private String alias;
    private String descripcion;
    private Long id;

    public CocheItem(String alias, String descripcion, Long id) {
        this.alias = alias;
        this.descripcion = descripcion;
        this.id = id;
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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
