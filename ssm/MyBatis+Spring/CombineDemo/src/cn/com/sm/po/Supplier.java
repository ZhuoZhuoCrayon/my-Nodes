package cn.com.sm.po;

import java.io.Serializable;

public class Supplier implements Serializable {
    private String sid;
    private String sname;
    private String city;
    private String  telephone_no;

    Supplier(){}
    Supplier(String sid,String sname,String city,String telephone_no){
        this.sid = sid;
        this.sname = sname;
        this.city = city;
        this.telephone_no = telephone_no;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTelephone_no(String telephone_no) {
        this.telephone_no = telephone_no;
    }

    public String getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public String getCity() {
        return city;
    }

    public String getTelephone_no() {
        return telephone_no;
    }
}
