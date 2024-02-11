package com.example.projectzeus.models.response;
import com.example.projectzeus.models.CocheItem;

import java.util.List;

public class CochesResponse {
    private String msg;
    private List<CocheItem> data;
    private String status;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public List<CocheItem> getData() { return data; }
    public void setData(List<CocheItem> value) { this.data = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public class Datum {
        private String descripcion;
        private String alias;

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String value) { this.descripcion = value; }

        public String getAlias() { return alias; }
        public void setAlias(String value) { this.alias = value; }
    }
}
