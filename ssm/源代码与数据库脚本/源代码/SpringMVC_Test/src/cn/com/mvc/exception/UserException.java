package cn.com.mvc.exception;

import java.io.IOException;

import cn.com.mvcUtil.ExceptionPropertyUtil;

public class UserException extends Exception{  
  //“Ï≥£–≈œ¢  
  private String message;  
  public UserException(String message){  
      super(message);  
      this.message=message;  
  }  
  public String getMessage() {  
      try {
		return new ExceptionPropertyUtil().getExceptionMsg(message);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return message;  
  }  
  public void setMessage(String message) {  
      this.message = message;  
  }  
}  
