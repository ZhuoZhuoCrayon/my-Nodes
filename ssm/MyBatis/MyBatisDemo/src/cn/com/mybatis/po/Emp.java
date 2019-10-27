package cn.com.mybatis.po;

import java.io.Serializable;
import java.util.Date;

public class Emp implements Serializable {
    private int empno;
    private String ename;
    private String job;
    private int mgb;
    private Date hiredate;
    private int sal;
    private int comm;
    private int deptno;
    public Emp(){};
    public Emp(int empno,String ename,String job,int mgb,
               Date hiredate,int sal,int comm,int deptno){
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgb = mgb;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public int getMgb() {
        return mgb;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public int getSal() {
        return sal;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setMgb(int mgb) {
        this.mgb = mgb;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public void setComm(int comm) {
        this.comm = comm;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "EMPNO:{" + empno + "}|"+
                "ENAME:{" + ename + "}|"+
                "JOB:{" + job + "}|"+
                "MGB:{" + mgb + "}|"+
                "HIREDATE:{" + hiredate.toString() + "}|"+
                "SAL:{" + sal + "}|"+
                "COMM:{" + comm + "}|"+
                "DEPTNO:{" + deptno + "}|";
    }
}
