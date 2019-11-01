<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
  <title>水果列表</title>  
</head>  
<body>     
  <h3>新鲜水果</h3>  
  <table width="300px;" border=1>  
    <tr>  
      <td>名称</td>  
      <td>价格</td>    
      <td>产地</td> 
   </tr>  
   <c:forEach items="${fruitsList }" var="fruit">  
     <tr>  
       <td>${fruit.name }</td>  
       <td>${fruit.price }</td>    
       <td>${fruit.producing_area }</td>  
     </tr>  
    </c:forEach>  
   </table>
</body>  
</html> 