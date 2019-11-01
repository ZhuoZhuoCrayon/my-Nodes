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
  <h3>多选</h3>  
  <form action="fruitsListTest.action" method="post">
  <table width="300px;" border=1>  
    <tr>  
      <td>名称</td>  
      <td>价格</td>    
      <td>产地</td> 
   </tr>  
   <c:forEach items="${fruitsList }" var="fruit" varStatus="status">  
     <tr>  
       <td><input name="fruitList[${status.index}].name" value="${fruit.name }"/></td>
	   <td><input name="fruitList[${status.index}].price" value="${fruit.price }"/></td>
	   <td><input name="fruitList[${status.index}].producing_area" value="${fruit.producing_area }"/></td>
     </tr>  
    </c:forEach>  
   </table>  <br/>
   <input type="submit" value="批量测试提交">
   </form> 
</body>  
</html> 