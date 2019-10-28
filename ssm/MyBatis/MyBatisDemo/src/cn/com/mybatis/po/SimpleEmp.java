package cn.com.mybatis.po;

import java.io.Serializable;

public class SimpleEmp implements Serializable {
    private int empno;
    private String ename;
    private String job;
    public SimpleEmp(){};

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    @Override
    public String toString() {
        return "EMPNO:{" + empno + "}|"+
                "ENAME:{" + ename + "}|"+
                "JOB:{" + job + "}|";
    }
}
