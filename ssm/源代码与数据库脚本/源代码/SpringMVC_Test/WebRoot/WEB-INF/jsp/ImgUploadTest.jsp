<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>  
<html>  
<head>  
	<meta charset="utf-8">  
	<title>上传图片测试</title>  
</head>  
<body>  
	<form action="uploadImg.action" method="post" enctype="multipart/form-data">  
	    <c:if test="${image !=null}">
			<img src="/pic/${image}" width=100 height=100/>
			<br/>
		</c:if>
		<input type="file" name="file" /><br/> 
		<input type="submit" value="上传" />
	</form>  
</body>  
</html>  