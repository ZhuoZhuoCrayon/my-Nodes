package cn.com.sm.po;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private Integer logid;
    private String who;
    private Date time;
    private String table_name;
    private String operation;
    private String key_value;

    public Log(){}
    public Log(Integer logid,String who,Date time,String table_name,
        String operation,String key_value){
        this.logid = logid;
        this.who = who;
        this.time = time;
        this.table_name = table_name;
        this.operation = operation;
        this.key_value = key_value;
    }
    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setKey_value(String key_value) {
        this.key_value = key_value;
    }

    public Integer getLogid() {
        return logid;
    }

    public String getWho() {
        return who;
    }

    public Date getTime() {
        return time;
    }

    public String getKey_value() {
        return key_value;
    }

    public String getOperation() {
        return operation;
    }

    public String getTable_name() {
        return table_name;
    }
}
