package com.example.projectzeus.models;

import java.io.Serializable;

public class ReporteItem implements Serializable {
    private String fecha;
    private String hora;
    private String distancia;

    public ReporteItem(String fecha, String hora, String distancia) {
        this.fecha = fecha;
        this.hora = hora;
        this.distancia = distancia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }
}
