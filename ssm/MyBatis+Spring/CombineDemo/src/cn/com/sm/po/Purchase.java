package cn.com.sm.po;

import java.io.Serializable;
import java.util.Date;

public class Purchase implements Serializable {
    private Integer purid;
    private String cid;
    private String eid;
    private String pid;
    private Integer qty;
    private Date ptime;
    private Float total_price;

    Purchase(){}
    Purchase(Integer purid,String cid,String eid,String pid,
             Integer qty,Date ptime,Float total_price){
        this.purid = purid;
        this.cid = cid;
        this.eid = eid;
        this.qty = qty;
        this.ptime = ptime;
        this.pid = pid;
        this.total_price = total_price;
    }
    public void setPurid(Integer purid) {
        this.purid = purid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public Integer getPurid() {
        return purid;
    }

    public String getCid() {
        return cid;
    }

    public String getEid() {
        return eid;
    }

    public String getPid() {
        return pid;
    }

    public Date getPtime() {
        return ptime;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public Integer getQty() {
        return qty;
    }
}
