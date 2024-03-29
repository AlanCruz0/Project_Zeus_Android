package com.example.projectzeus.models.response;
import java.time.OffsetDateTime;

public class AgregarResponse {
    private String msg;
    private Info data;
    private String status;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public Info getData() { return data; }
    public void setData(Info value) { this.data = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }
}

class Info {
    private String descripcion;
    private Long codigo;
    private OffsetDateTime updatedAt;
    private Long userid;
    private String alias;
    private OffsetDateTime createdAt;
    private Long id;

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String value) { this.descripcion = value; }

    public Long getCodigo() { return codigo; }
    public void setCodigo(long value) { this.codigo = value; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime value) { this.updatedAt = value; }

    public Long getUserid() { return userid; }
    public void setUserid(long value) { this.userid = value; }

    public String getAlias() { return alias; }
    public void setAlias(String value) { this.alias = value; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime value) { this.createdAt = value; }

    public Long getid() { return id; }
    public void setid(long value) { this.id = value; }
}
