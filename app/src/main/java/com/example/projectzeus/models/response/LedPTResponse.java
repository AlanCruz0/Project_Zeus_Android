package com.example.projectzeus.models.response;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

public class LedPTResponse implements Serializable {
    private String msg;

    private Data data;

    private Integer status;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static class Data implements Serializable {
        private Integer sensor_id;

        private String updated_at;

        private String unidades;

        private String valor;

        private String created_at;

        private Integer id;

        public Integer getSensor_id() {
            return this.sensor_id;
        }

        public void setSensor_id(Integer sensor_id) {
            this.sensor_id = sensor_id;
        }

        public String getUpdated_at() {
            return this.updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getUnidades() {
            return this.unidades;
        }

        public void setUnidades(String unidades) {
            this.unidades = unidades;
        }

        public String getValor() {
            return this.valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

        public String getCreated_at() {
            return this.created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Integer getId() {
            return this.id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
