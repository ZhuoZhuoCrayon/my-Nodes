package cn.com.sm.po;

import java.io.Serializable;

public class Product implements Serializable {
    private String pid;
    private String pname;
    private Integer qoh;
    private Integer qoh_threshold;
    private Float original_price;
    private Float discnt_rate;
    private String sid;

    Product(){}
    Product(String pid,String pname,Integer qoh,Integer qoh_threshold,
             Float original_price,Float discnt_rate,String sid){
        this.pid = pid;
        this.pname = pname;
        this.qoh = qoh;
        this.qoh_threshold = qoh_threshold;
        this.original_price = original_price;
        this.discnt_rate = discnt_rate;
        this.sid = sid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setQoh(Integer qoh) {
        this.qoh = qoh;
    }

    public void setQoh_threshold(Integer qoh_threshold) {
        this.qoh_threshold = qoh_threshold;
    }

    public void setOriginal_price(Float original_price) {
        this.original_price = original_price;
    }

    public void setDiscnt_rate(Float discnt_rate) {
        this.discnt_rate = discnt_rate;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public Integer getQoh() {
        return qoh;
    }

    public Integer getQoh_threshold() {
        return qoh_threshold;
    }

    public Float getOriginal_price() {
        return original_price;
    }

    public Float getDiscnt_rate() {
        return discnt_rate;
    }

    public String getSid() {
        return sid;
    }
}
