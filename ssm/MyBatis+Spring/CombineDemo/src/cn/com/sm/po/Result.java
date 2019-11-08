package cn.com.sm.po;

import java.io.Serializable;

public class Result implements Serializable {
    private boolean success;
    private String message;

    public Result(boolean success,String message){
        this.success = success;
        this.message = message;
    }
    public Result(){}

    public boolean isSuccess(){
        return this.success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
