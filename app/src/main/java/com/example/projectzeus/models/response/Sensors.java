package com.example.projectzeus.models.response;

public class Sensors {
    private String descripcion;
    private Long cocheid;
    private Long id;
    private String sku;

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String value) { this.descripcion = value; }

    public Long getCocheid() { return cocheid; }
    public void setCocheid(long value) { this.cocheid = value; }

    public Long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getSku() { return sku; }
    public void setSku(String value) { this.sku = value; }
}
