package cn.com.sm.po;

import java.io.Serializable;

public class Employee implements Serializable {
    private String eid;
    private String ename;
    private String city;

    Employee(){}
    Employee(String eid,String ename,String city){
        this.eid = eid;
        this.ename = ename;
        this.city = city;
    }
    public void setEid(String eid) {
        this.eid = eid;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEname() {
        return ename;
    }

    public String getCity() {
        return city;
    }

    public String getEid() {
        return eid;
    }

    @Override
    public String toString() {
        return "EID:{" + eid + "}|" +
                "ENAME:{" + ename + "}|" +
                "CITY:{" + city + "}|";
    }
}
