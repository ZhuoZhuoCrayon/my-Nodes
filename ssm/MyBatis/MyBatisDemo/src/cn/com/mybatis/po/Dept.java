package cn.com.mybatis.po;

public class Dept {
    public Integer deptno;
    public String dname;
    public String loc;

    Dept(){}
    Dept(Integer deptno,String dname,String loc){
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }
    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public String getDname() {
        return dname;
    }

    public String getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return "DEPTNO:{" + deptno + "}|" +
                "DNAME:{" + dname + "}|" +
                "LOC:{" + loc + "}}";
    }
}
