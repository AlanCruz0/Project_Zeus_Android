package com.example.projectzeus.models.response;
import com.example.projectzeus.models.ReporteItem;

import java.time.OffsetDateTime;
import java.util.List;

public class ReporteResponse {
    private String msg;
    private List<ReporteItem> data;
    private Long status;

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }

    public List<ReporteItem> getData() { return data; }
    public void setData(List<ReporteItem> value) { this.data = value; }

    public Long getStatus() { return status; }
    public void setStatus(long value) { this.status = value; }
}
