package com.example.projectzeus.models.request;

public class AgregarRequest {
    private String alias, descripcion;
    private Long user_id;

    public AgregarRequest(String alias, String descripcion, Long user_id) {
        this.alias = alias;
        this.descripcion = descripcion;
        this.user_id = user_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
