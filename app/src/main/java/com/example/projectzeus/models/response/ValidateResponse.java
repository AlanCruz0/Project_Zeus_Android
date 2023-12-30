package com.example.projectzeus.models.response;

public class ValidateResponse {
    private String msg;
    private boolean data;
    private long status;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public boolean getData() { return data; }
    public void setData(boolean value) { this.data = value; }

    public long getStatus() { return status; }
    public void setStatus(long value) { this.status = value; }
}
