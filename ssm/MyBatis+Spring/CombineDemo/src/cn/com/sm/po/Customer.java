package cn.com.sm.po;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {
    private String cid;
    private String cname;
    private String city;
    private Integer visits_made;
    private Date last_visit_time;

    Customer(){}
    Customer(String cid,String cname,String city,Integer visits_made,Date last_visit_time){
        this.cid = cid;
        this.cname = cname;
        this.city = city;
        this.visits_made = visits_made;
        this.last_visit_time = last_visit_time;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setVisits_made(Integer visits_made) {
        this.visits_made = visits_made;
    }

    public void setLast_visit_time(Date last_visit_time) {
        this.last_visit_time = last_visit_time;
    }

    public String getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public String getCity() {
        return city;
    }

    public Date getLast_visit_time() {
        return last_visit_time;
    }

    public Integer getVisits_made() {
        return visits_made;
    }
}
