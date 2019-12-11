package cn.com.sm.po;

import java.io.Serializable;

public class MonthlySale implements Serializable {
    private String pname;
    private String month;
    private String year;
    private Float total;
    private Integer sales;
    private Float avgPrice;

    public MonthlySale(){}

    public MonthlySale(String pname,String month,String year,
                       Float total,Integer sales,Float avgPrice){
        this.pname = pname;
        this.month = month;
        this.year = year;
        this.total = total;
        this.sales = sales;
        this.avgPrice = avgPrice;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public void setAvgPrice(Float avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getPname() {
        return pname;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public Float getAvgPrice() {
        return avgPrice;
    }

    public Float getTotal() {
        return total;
    }

    public Integer getSales() {
        return sales;
    }
}
