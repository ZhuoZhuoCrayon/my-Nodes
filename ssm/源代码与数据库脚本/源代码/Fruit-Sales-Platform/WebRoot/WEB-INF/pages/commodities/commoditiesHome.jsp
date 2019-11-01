<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
  <head>
    <title>货物管理</title>
    <style>
        *{margin:0; padding:0;} #menuContent a{text-decoration:none;color:#ffffff}
        .c{
          border-style: solid;width: 200px;height: 130px;
          margin: 4 23 0 23;border-radius:5;display:block;
          background:#fff;
          margin:10% auto;
        }
        .mask,.addMask,{
        	width:100%;
        	height:100%;
        	position: absolute;
        	background:rgba(0,0,0,.3);
        	display: none;
        }
    </style>
        
    <script type="text/javascript" 
  		src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>  
    <script type="text/javascript">
       function init(){
           var countNumber = document.getElementById("countNumber").value;
           var sumPage = document.getElementById("sumPageNumber").value;
           var currentPage = document.getElementById("currentPage").value;
           var info = "一共<font color='blue'>"+countNumber+"</font>条数据，"+
                      "共<font color='blue'>"+sumPage+"</font>页，"+
                      "当前第<font color='blue'>"+currentPage+"</font>页";
           document.getElementById("pageInfo").innerHTML=info;
       }
    
       function changeStatus(){
           var status = document.getElementById("indexStatus").value;
           document.getElementById("status").value=status;
       }
       
       function toPrePage(){
           var currentPageObject = document.getElementById("currentPage");
           var currentPage = parseInt(currentPageObject.value);
           if(currentPage==1){
               alert("数据已到顶！");
           }else{
               currentPageObject.value = currentPage-1;
               var pageSize = parseInt(document.getElementById("pageSize").value);
               var startPageObject =document.getElementById("startPage");
               startPageObject.value = parseInt(startPageObject.value)-pageSize;
               document.getElementById("listForm").submit();
           }
       }
       
       function toNextPage(){
           var currentPageObject = document.getElementById("currentPage");
           var currentPage = parseInt(currentPageObject.value);
           var sumPage = parseInt(document.getElementById("sumPageNumber").value);
           if(currentPage>=sumPage){
               alert("数据已到底！");
           }else{
               currentPageObject.value = currentPage+1;
               var pageSize = parseInt(document.getElementById("pageSize").value);
               var startPageObject =document.getElementById("startPage");
               startPageObject.value = parseInt(startPageObject.value)+pageSize;
               document.getElementById("listForm").submit();
           }
       }
       
       function toLocationPage(){
           var pageNumber = document.getElementById("pageNumber").value;
           var currentPageObject = document.getElementById("currentPage");
           var currentPage = currentPageObject.value;
           if(pageNumber==null||pageNumber==""){
               alert("请输入要跳转的页数！");
           }else{
        	   pageNumber = parseInt(pageNumber);
               var sumPage = parseInt(document.getElementById("sumPageNumber").value);
	           if(pageNumber<1){
	               alert("数据已到顶！");
	           }else if(pageNumber>sumPage){
	               alert("数据已到底！");
	           }else{
	               currentPageObject.value = pageNumber;
	               var pageSize = parseInt(document.getElementById("pageSize").value);
                   var startPageObject =document.getElementById("startPage");
                   if(pageNumber>currentPage){
                       startPageObject.value = parseInt(startPageObject.value)+pageSize;
                   }else if(pageNumber<currentPage){
                       startPageObject.value = parseInt(startPageObject.value)-pageSize;
                   }
	               document.getElementById("listForm").submit();
	           }
           }
       }
       
       /* function editRetailer(id){
             var message="{'id':'"+id+"'}";
       		 $.ajax({  
                type:'post',  
                url:'${pageContext.request.contextPath}/retailer/editRetailer.action',  
                contentType:'application/json;charset=utf-8',     
                data:message,//数据格式是json串  
                success:function(data){//返回json结果 
                    $("#editName").val(data["name"]);
                    $("#editTelphone").val(data["telphone"]);
                    $("#editAddress").val(data["address"]);
                    $("#retailerId").val(data["retailerId"]);
                    $("#editStatus").val(data["status"]);
                    $("#eStatus").val(data["status"]);
                    //显示弹出框
                    $(".mask").css("display","block");
                    //引入分页信息至该form表单
                    $("#eStartPage").val($("#startPage").val());
                    $("#eCurrentPage").val($("#currentPage").val());
                    $("#ePageSize").val($("#pageSize").val());
                }   
            });  
       } */

       function cancelEdit(){
    	   $(".mask").css("display","none");
       }

      /*  function changeEditStatus(){
    	   var status = document.getElementById("editStatus").value;
           document.getElementById("eStatus").value=status;
       } */

       function deleteCommodities(id,name){
    	   if(window.confirm("你确定要删除附属品"+name+"吗？")){
    		   $("#dFruitId").val(id);//向form中引入id
        	   //引入分页信息至该form表单
               $("#dStartPage").val($("#startPage").val());
               $("#dCurrentPage").val($("#currentPage").val());
               $("#dPageSize").val($("#pageSize").val());
               $("#deleteForm").submit();//提交表单
            }
       }

	   function showAddMask(flag){
		    if(flag=="true"){
		 	   $(".addMask").css("display","block");
		    }else{
		 	   $(".addMask").css("display","none");
		    }
	   }

	   /* function checkAddRetailer(){
           if($("#addName").val()==null||$("#addName").val()==""){
               alert("用户名不能为空！");
               return false;
           }
           if($("#addTelphone").val()==null||$("#addTelphone").val()==""){
               alert("手机号不能为空！");
               return false;
           }
           var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
           if(!myreg.test($("#addTelphone").val())) 
           { 
               alert("请输入有效的手机号码！"); 
               return false; 
           } 
           if($("#addAddress").val()==null||$("#addAddress").val()==""){
               alert("地址不能为空！");
               return false;
           }
           return true;
	   } */

	   function openwin(id) { 
		   var url="${pageContext.request.contextPath}/accessory/list.action?fruitId="+id;
	       window.open (url,"附属品","height=400,width=700,scrollbars=yes"); 
	   } 
    </script>
  </head>
  <body onload="init()">
      <%@ include file="../menu.jsp" %><br/>
   <div class="addMask">
	   <!-- <div class="c">
	     <div style="background-color:#173e65;height:20px;color:#fff;font-size:12px;padding-left:7px;">
	     	添加信息<font style="float:right;padding-right: 10px;" onclick="showAddMask('false')">x</font>
	     </div>
	     <form id="addForm" action="add.action" method="post" onsubmit="checkAddRetailer()">
		        姓名：<input type="text" id="addName" name="name" style="width:120px"/> <br/>
		        手机：<input type="text" id="addTelphone" name="telphone" style="width:120px"/><br/>
		        地址：<input type="text" id="addAddress" name="address" style="width:120px"/><br/>
		     <input type="hidden" name="status" value="1"/>
		     <input type="submit" value="添加" style="background-color:#173e65;color:#ffffff;width:70px;"/>
	     </form>
	    </div> -->
   </div>
   <div class="mask">
	   <!-- <div class="c">
	     <div style="background-color:#173e65;height:20px;color:#fff;font-size:12px;padding-left:7px;">
	     	修改信息<font style="float:right;padding-right: 10px;" onclick="cancelEdit()">x</font>
	     </div>
	     <form id="editForm" action="edit.action" method="post">
		        姓名：<input type="text" id="editName" name="name" style="width:120px"/> <br/>
		        手机：<input type="text" id="editTelphone" name="telphone" style="width:120px"/><br/>
		        地址：<input type="text" id="editAddress" name="address" style="width:120px"/><br/>
		        状态：<select id="eStatus" onchange="changeEditStatus()">
		        <option value="1">启用</option>
		        <option value="0">停用</option>
		     </select><br/>
		     <input type="hidden" name="retailerId" id="retailerId"/>
		     <input type="hidden" name="status" id="editStatus"/>
		     <input type="hidden" name="startPage" id="eStartPage"/>
			 <input type="hidden" name="currentPage" id="eCurrentPage"/>
			 <input type="hidden" name="pageSize" id="ePageSize"/>
		     <input type="submit" value="提交" style="background-color:#173e65;color:#ffffff;width:70px;"/>
	     </form>
	    </div> -->
  </div>
  <form id="listForm" action="list.action" method="post">
        名称：<input type="text" name="name" style="width:120px" value="${commodities.name}"/> 
        产地：<input type="text" name="locality" style="width:120px" value="${commodities.locality}"/>
        价格：<input id="price1" name="startPrice" type="number" min="0.0" step="0.1" style="width:60px" value="${startPrice}"/> 
        - <input id="price2" name="endPrice" type="number" min="0.0" step="0.1" style="width:60px" value="${endPrice}"/><br/><br/>
        创建日期：<input type="datetime-local" name="startTime" value="${startTime}"/> - <input type="datetime-local" name="endTime" value="${endTime}"/>
     <input type="submit" value="搜索" style="background-color:#173e65;color:#ffffff;width:70px;"/> <br/>
     <!-- 显示错误信息 -->  
	 <c:if test="${errorMsg}">   
	     <font color="red">${errorMsg}</font><br/>
	 </c:if> 
	 <input type="hidden" name="startPage" id="startPage" value="${startPage}"/>
	 <input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"/>
	 <input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/>
	 <input type="hidden" name="sumPageNumber" id="sumPageNumber" value="${sumPageNumber}"/>
	 <input type="hidden" name="countNumber" id="countNumber" value="${countNumber}"/>
  </form>
  <hr style="margin-top: 10px;"/> 
  <button onclick="showAddMask('true')" style="background-color:#173e65;color:#ffffff;width:70px;">添加</button>
  <c:if test="${list!=null}">
	  <table style="margin-top: 10px;width:700px;text-align:center;" border=1>  
	    <tr>  
	      <td>序号</td><td>名称</td><td>价格</td><td>产地</td>
	      <td>创建日期</td><td>操作</td>
	   </tr>  
      <c:forEach items="${list}" var="item" varStatus="status">  
	     <tr>  
	       <td>${status.index+1}</td><td>${item.name }</td>
	       <td>${item.price}</td><td>${item.locality }</td>  
	       <td>${item.createTime}</td>
	       <td>
	       		<a onclick="editCommodities('${item.fruitId}')">编辑</a>|
	       		<a onclick="deleteCommodities('${item.fruitId}','${item.name }')">删除</a>|
	       		<a onclick="openwin('${item.fruitId}')">附属品</a>
	       		<form id="deleteForm" action="delete.action" method="post">
	       		   <input type="hidden" name="fruitId" id="dFruitId"/>
	       		   <input type="hidden" name="startPage" id="dStartPage"/>
			       <input type="hidden" name="currentPage" id="dCurrentPage"/>
			       <input type="hidden" name="pageSize" id="dPageSize"/>
	       		</form>
	       </td>
	     </tr>  
	    </c:forEach>  
	    </table> 
   </c:if>
   <c:if test="${list==null}">
       <b>搜索结果为空！</b>
   </c:if>
   <div style="margin-top: 10px;">
       <a onclick="toPrePage()">上一页</a><a onclick="toNextPage()">下一页</a>
       <input type="text" id="pageNumber" style="width:50px">
       <button onclick="toLocationPage()">go</button>
       <div id="pageInfo"></div>
   </div>
  </body>
</html>
