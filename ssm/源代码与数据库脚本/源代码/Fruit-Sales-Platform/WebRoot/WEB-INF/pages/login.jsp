<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录</title>
	<link href="${pageContext.request.contextPath}/css/regcss.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript">
	 function  validate(){   
          if(document.myform.username.value==""){   
                  alert("用户名不能为空");   
                  document.myform.username.focus();   
                  return false;   
          }else 
          if(document.myform.password.value==""){   
                  alert("密码不能为空");   
                  document.myform.password.focus();   
                  return false;   
          }   
         return true;   
     }   
	</script> 
  </head>
<body>
	<div id="content">
		<div id="form">
		  <h1>用户登录</h1><br/>
		  <form action="login.action" method="post" id="myform"  onsubmit="return validate()">
		         用户名<input type="text" id="username" name="userName" style="width:190px; height:26px; margin-left:39px;"/><br/>
		  	  密码<input type="password" id="password" name="password" style="width:190px; height:26px; margin-top:8px;margin-left:54px;"/><br/>
			  <input type="submit" value="登录" style="width:50px; height:30px; margin-top:8px;"/>
			  <a href="registerPage.action">注册</a>
		  </form>
		  <!-- 显示错误信息 -->
		  <c:if test="${errorMsg!=null}"> 
		      <font color="red">${errorMsg}</font>    
		  </c:if>
		  <!-- 显示提示 -->
		  <c:if test="${noticeMsg!=null}"> 
		      <font color="green">${noticeMsg}</font>    
		  </c:if>
	  </div>
	</div>
</body>
</html>
