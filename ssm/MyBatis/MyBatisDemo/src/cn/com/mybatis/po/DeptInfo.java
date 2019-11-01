package cn.com.mybatis.po;

public class DeptInfo {
    public Integer empno;
    public SimpleEmp simpleEmp;
    public Dept dept;

    DeptInfo(){}
    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public void setSimpleEmp(SimpleEmp simpleEmp) {
        this.simpleEmp = simpleEmp;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Integer getEmpno() {
        return empno;
    }

    public SimpleEmp getSimpleEmp() {
        return simpleEmp;
    }

    public Dept getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return simpleEmp.toString() + '\n' + dept.toString();
    }
}
