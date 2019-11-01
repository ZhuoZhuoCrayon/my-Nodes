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
  <form action="fruitsMapTest.action" method="post">
  <table width="300px;" border=1>  
    <tr>  
      <td>名称</td>  
      <td>价格</td>    
      <td>产地</td> 
   </tr>  
     <tr>  
       <td><input name="fruitMap['name']" value="凤梨"/></td>
	   <td><input name="fruitMap['price']" value="5.7"/></td>
	   <td><input name="fruitMap['producing_area']" value="广州"/></td>
     </tr>  
   </table>  <br/>
   <input type="submit" value="批量测试提交">
   </form> 
</body>  
</html> 