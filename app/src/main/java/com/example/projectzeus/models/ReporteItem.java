package com.example.projectzeus.models;

import java.io.Serializable;

public class ReporteItem implements Serializable {
    private String fecha;
    private String hora;
    private String valor;
    public String unidades;

    public ReporteItem(String fecha, String hora, String valor, String unidades) {
        this.fecha = fecha;
        this.hora = hora;
        this.valor = valor;
        this.unidades = unidades;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getDistancia() {
        return valor+" "+unidades;
    }
}
