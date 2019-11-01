<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
  <head>
    <title>新建购销合同</title>
    <style>
        *{margin:0; padding:0;}
        .btn{background-color:#173e65;color:#ffffff;width:70px;}
        .btn-div{text-align: center;}
        .info{border: 1px solid #CCC}
        .c{
          border-style: solid;width: 200px;height: 300px;
          margin: 4 23 0 23;border-radius:5;display:block;
          background:#fff;
          margin:10% auto;
          text-align: center;
        }
        .c2{
          border-style: solid;width: 400px;height: 320px;
          margin: 4 23 0 23;border-radius:5;display:block;
          background:#fff;
          margin:10% auto;
          text-align: center;
        }
        .retailerMask,.commoditiesMask{
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
        function checkAddContract(){ }
        function changeType(){
            var type = $("#indexType").val();
            $("#type").val(type);
        }
        function cancelEdit(){
     	   $(".retailerMask").css("display","none");
     	   $(".commoditiesMask").css("display","none");
        }
		function SearchRetailer(){
			addRetailer($("#retailerName").val());
        }
        function addRetailer(name){ 
           $("#retailerList").html("");//将原来信息清空
           var message="";
           if(name!=null){
        	   message="{'name':'"+name+"'}";
           }else{
        	   message="{'name':''}";
           }
           $.ajax({  
               type:'post',  
               url:'${pageContext.request.contextPath}/contract/getAllRetailer.action',  
               contentType:'application/json;charset=utf-8',     
               data:message,//数据格式是json
               success:function(data){//返回json结果 
                 for(var i=0;i<data.length;i++){
                	 var oldHtml = $("#retailerList").html();
                	 var info="<p onclick=\"selectRetailer('"+data[i].retailerId+"','"+data[i].name+"','"+data[i].telphone
                    	 +"','"+data[i].address+"')\">"
                    	 +data[i].name+"</p>";
                	 $("#retailerList").html(oldHtml+info);
                 }
                 $(".retailerMask").css("display","block");
               },
               error:function(data){
                  alert("通讯异常！");  
               }   
           });  
        }
        function selectRetailer(retailerId,name,telphone,address){
        	$("#retailerId").val(retailerId);
            $("#retailer_name").html("姓名："+name);
            $("#retailer_telphone").html("联系电话："+telphone);
            $("#retailer_address").html("送货地址："+address);
            $(".retailerMask").css("display","none");//关闭零售商选择框
            $("#retailer_info").css("display","block");//显示零售商信息
        }
        function SearchCommodities(){
        	addFruits($("#commoditiesName").val());
        }
        function addFruits(name){
        	var message="";
            if(name!=null){
         	   message="{'name':'"+name+"'}";
            }else{
         	   message="{'name':''}";
            }
        	$.ajax({  
                type:'post',  
                url:'${pageContext.request.contextPath}/contract/getAllCommodities.action',  
                contentType:'application/json;charset=utf-8',     
                data:message,
                success:function(data){//返回json结果 
                  var tableHead="<tr>"+  
          	      "<td><input type='checkbox' onclick='checkAll(this)'></td>"+
          	      "<td>名称</td><td>价格</td><td>产地</td>"+
          	      "</tr> ";
                  $("#commoditiesList").html(tableHead);//清空列表,添加table标题头
                  for(var i=0;i<data.length;i++){
                 	 var oldHtml = $("#commoditiesList").html();
                 	 var info="<tr>"+  
		          	       "<td><input type='checkbox' name='arrays' value='"+data[i].fruitId+"'></td>"+
		        	       "<td>"+data[i].name+"</td><td>"+data[i].price+"</td>"+
		        	       "<td>"+data[i].locality+"</td>"+
		        	     "</tr>";
                 	 $("#commoditiesList").html(oldHtml+info);
                  }
                  //添加table头和尾
                  $("#commoditiesList").html("<table style='width:375px;text-align:center;' border=1>"
                          +$("#commoditiesList").html()+"</table>");
                  $(".commoditiesMask").css("display","block");
                },
                error:function(data){
                   alert("通讯异常！");  
                }   
            });  
        }

        function checkAll(obj){
            var isCheck=obj.checked;
        	var checkList=document.getElementsByName("arrays");//获取所有check选项
        	for(var i=0;i<checkList.length;i++){
        		checkList[i].checked=isCheck;
        	}      	
        }

        function selectCommodities(){
        	 $("#commodities_info").html("");//将原来信息清空
        	var myArray=new Array();
        	var len=0; 
        	var arrays=document.getElementsByName("arrays");//获取所有check选项
        	for(var i=0;i<arrays.length;i++){
            	if(arrays[i].checked){
            		myArray[len++]=arrays[i].value;
                }
        	}
        	$.ajax({  
                type:'post',  
                url:'${pageContext.request.contextPath}/contract/getCommoditiesAndAccessory.action', 
                data:{"arrays":myArray},//数据为id数组
                traditional: true,
                success:function(data){//成功拼接信息
                	var tableHead="<tr>"+
            	      "<td>名称</td><td>价格</td><td>产地</td><td>附属品</td><td>数量</td>"+
            	      "</tr> ";
                    $("#commodities_info").html(tableHead);//清空列表,添加table标题头
                	for(var i=0;i<data.length;i++){
                        var commodities = data[i].commodities;//获取货物信息
                        var accessory = data[i].accessory;//获取货物附属品数组
                        var accessoryStr="";
                        for(var j=0;j<accessory.length;j++){
                        	accessoryStr+=accessory[j].name+":"+accessory[j].price+"元";
                        	if(j!=accessory.length-1){accessoryStr+="<br/>"}//不是最后一个就换行
                        }
                        accessoryStr=accessoryStr==""?"无":accessoryStr;
                        var oldHtml = $("#commodities_info").html();
                    	var info="<tr>"+
   		        	       "<td>"+commodities.name+"</td><td>"+commodities.price+"元/斤</td>"+
   		        	       "<td>"+commodities.locality+"</td><td>"+accessoryStr+"</td>"+
   		        	       "<td><input type='number' style='width:50px' name='priceArrays'>斤</td>"+
   		        	     "</tr><input type='hidden' name='commoditiesIdArrays' value='"+commodities.fruitId+"'>";
                    	$("#commodities_info").html(oldHtml+info);
                    }
                	//添加table头和尾
                    $("#commodities_info").html("<table style='width:510px;text-align:center;' border=1>"
                            +$("#commodities_info").html()+"</table>");
                    $(".commoditiesMask").css("display","none");//关闭浮出框
                	$("#commodities_info").css("display","block");//显示货物区域
                },
                error:function(data){
                    alert("通讯异常！");  
                }    
            });
        }
    </script>
  </head>
  <body>
     <div class="commoditiesMask">
	   <div class="c2">
	     <div style="background-color:#173e65;height:20px;color:#fff;font-size:12px;padding-left:7px;">
	     	水果列表<font style="float:right;padding-right:10px;" onclick="cancelEdit()">x</font>
	     </div>
	     <input id="commoditiesName" width="width:20%"/>
	     <button class="btn" onclick="SearchCommodities()">查询</button>
	     <div id="commoditiesList" 
	     	style="border:2px solid #CCC;height:230px;overflow-y:scroll;margin:10px;">
	     	<!-- 该区域放置查询到的水果货物信息 -->
	     </div>
	     <button class="btn" onclick="selectCommodities()">确定</button>
	    </div>
     </div>
     <div class="retailerMask">
	   <div class="c">
	     <div style="background-color:#173e65;height:20px;color:#fff;font-size:12px;padding-left:7px;">
	     	零售商信息<font style="float:right;padding-right:10px;" onclick="cancelEdit()">x</font>
	     </div>
	     <input id="retailerName" width="width:20%"/>
	     <button class="btn" onclick="SearchRetailer()">查询</button>
	     <div id="retailerList" 
	     	style="border:5px solid #CCC;height:220px;overflow-y:scroll;margin:10px;">
	     	<!-- 该区域放置查询到的用户信息 -->
	     </div>
	    </div>
     </div>
     <form id="addContractForm" action="add.action" method="post" onsubmit="checkAddContract()">
	        合同编码：<input type="text" name="barcode" style="width:120px;" 
	        			 value="系统自动生成" readonly="readonly"/> <br/>
	        类型：<select id="indexType" onchange="changeType()">
           <option value="1">省外</option>
           <option value="0">省内</option>
        </select>
        <input type="hidden" name="type" id="type" value="0"/><br/>
        <div class="info">
                     零售商信息：
            <div class="btn btn-div" onclick="addRetailer(null)" style="float:right">关联</div><br/>
            <div id="retailer_info" style="display: none">
              <p id="retailer_name"></p>
              <p id="retailer_telphone"></p>
              <p id="retailer_address"></p>
              <input name="retailerId" id="retailerId" type="hidden"/>
            </div>
        </div>
        <div class="info">
           	货物信息：
           	<div class="btn btn-div" onclick="addFruits(null)" style="float:right">添加</div><br/>
           	<div id="commodities_info" style="display: none">
           	    <!-- 展示拼接的货物信息html -->
           	</div>
        </div>
	    <input type="submit" value="提交" class="btn"/>
     </form>
     <!-- 显示后台响应信息 -->  
	 <c:if test="${reaultMessage!=null}">   
	     <br/><font color="red">${reaultMessage}</font>
	 </c:if> 
  </body>
</html>
