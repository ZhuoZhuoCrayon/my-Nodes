package cn.com.mybatis.po;

public class EmpInfo {
    public Integer empno;
    public String ename;
    public String dname;
    public String loc;

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Integer getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getDname() {
        return dname;
    }

    public String getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return "EMPNO:{" + empno + "}|"+
                "ENAME:{" + ename + "}|"+
                "DNAME:{" + dname + "}|" +
                "LOC:{" + loc + "}}";
    }
}
