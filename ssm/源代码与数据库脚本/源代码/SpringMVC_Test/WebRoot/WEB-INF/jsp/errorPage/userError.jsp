<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>提示</title>  
  </head>  
  <body>  
     <img src="${pageContext.request.contextPath}/image/error.jpg" 
     	width="50px;" height="50px;"/></br>
        抱歉，访问异常，具体信息如下：</br>
    <h2><font color="red">${message}</font></h2></br>  
  </body>  
</html>  