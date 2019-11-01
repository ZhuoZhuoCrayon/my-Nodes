package cn.com.mvcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExceptionPropertyUtil {
	
	private Properties prop;// 属性集合对象  
	private InputStream fis;// 属性文件输入流   
	
	private void init() throws IOException{
		prop = new Properties();// 属性集合对象   
	    fis = this.getClass().getResourceAsStream("/exceptionMapping.properties");// 属性文件输入流   
	    prop.load(fis);// 将属性文件流装载到Properties对象中   
	    fis.close();// 关闭流   
	}
	
	public String getExceptionMsg(String ExceptionCode) throws IOException{
		init();
		String msg = prop.getProperty(ExceptionCode);
		if(msg!=null){
			return msg;
		}else{
			return "未定义异常";
		} 
	}
}
