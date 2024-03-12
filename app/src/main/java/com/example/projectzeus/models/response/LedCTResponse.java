package com.example.projectzeus.models.response;

public class LedCTResponse {
    private String msg;
    private String data;
    private long status;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public String getData() { return data; }
    public void setData(String value) { this.data = value; }

    public long getStatus() { return status; }
    public void setStatus(long value) { this.status = value; }
}
