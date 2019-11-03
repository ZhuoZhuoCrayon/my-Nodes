package cn.com.sm.po;
public class Employee {
    public String eid;
    public String ename;
    public String city;

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
