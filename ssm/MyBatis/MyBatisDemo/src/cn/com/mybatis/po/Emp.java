package cn.com.mybatis.po;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Emp implements Serializable {
    //Mapper文件会调用实体的get和set函数，为了数据置空，一般使用包装类
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgb;
    private Date hiredate;
    private Integer sal;
    private Integer comm;
    private Integer deptno;
    public Emp(){};
    public Emp(Integer empno,String ename,String job,Integer mgb,
               Date hiredate,Integer sal,Integer comm,Integer deptno){
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

    public Integer getMgb() {
        return mgb;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public Integer getSal() {
        return sal;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setMgb(Integer mgb) {
        this.mgb = mgb;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "EMPNO:{" + empno + "}|"+
                "ENAME:{" + ename + "}|"+
                "JOB:{" + job + "}|"+
                "MGB:{" + mgb + "}|"+
                "HIREDATE:{" + simpleDateFormat.format(hiredate) + "}|"+
                "SAL:{" + sal + "}|"+
                "COMM:{" + comm + "}|"+
                "DEPTNO:{" + deptno + "}|";
    }
}
