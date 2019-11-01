package cn.com.mybatis.po;

import java.util.List;

public class DeptMember {
    public Integer deptno;
    public String dname;
    public List<Emp> empList;

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setEmpList(List<Emp> empList) {
        this.empList = empList;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public String getDname() {
        return dname;
    }

    public List<Emp> getEmpList() {
        return empList;
    }
}
