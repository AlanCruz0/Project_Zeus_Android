package com.example.projectzeus.models.response;
import java.util.List;

public class CocheSensorsResponse {
    private String msg;
    private List<Sensors> data;
    private String status;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public List<Sensors> getData() { return data; }
    public void setData(List<Sensors> value) { this.data = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }
}