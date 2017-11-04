<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="page/pagetool.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script type="text/javascript">
$(function(){
	   //显示用户
	   getUser(page);
});

     var username="";
     var page = 1;
     function getUser(pageNum){
  	   
 	    $.ajax({
 		   url : "${pageContext.request.contextPath}/AdminUserServlet",
 		   type : "post",
 		   async : "true",
 		   data : {"action" : "getUser",  "username":username,  "pageNum":pageNum},
 		   dataType : "json",
 		   success:function(data){
 			   if (data.res==1){
 					$(".list").html("");
 					$.each(data.data.data, function(index, msgItem) {
 						var msg = $(".template").clone();
 						msg.show();
 						msg.removeClass("template");
 						msg.find(".num").text(index+1);
 						msg.find(".username").text(msgItem.username);
 						msg.find(".userid").attr("href", "message.jsp?msgid="+msgItem.msgid);
 						msg.find(".realname").text(msgItem.realname);
 						msg.find(".sex").text(msgItem.sex);
 						msg.find(".hobbys").text(msgItem.hobbys);
 						msg.find(".birthday").text(msgItem.birthday);
 						msg.find(".city").text(msgItem.city);
 						msg.find(".qq").text(msgItem.qq);
 						msg.find(".email").text(msgItem.email);
 						msg.find(".createtime").text(msgItem.createtime);
 						msg.find(".delete").attr("onclick", "deleteUser("+msgItem.userid+")");
 						msg.find(".restore").attr("onclick", "restoreUser("+msgItem.userid+")");
 						
 	                    if (msgItem.state == 1) {
 							msg.find(".delete").show();
 							msg.find(".restore").hide();
 							
 						}
 						else {
 							msg.find(".delete").hide();
 							msg.find(".restore").show();
 							
 						}
 						
 						$(".list").append(msg);
 					});
 	
 					page = setPage(pageNum, data.data.totalPage, "getUser");
 										
 				} else if (data.res==-2){
 					alert(data.info);
 				}

 		   }
 	   });
    }
     
     function deleteUser(userid){
    	
  	   $.ajax({
  	   url : "${pageContext.request.contextPath}/AdminUserServlet",
  	   type : "post",
  	   async : "true",
  	   data : {"action" : "deleteUser", "userid":userid},
  	   dataType : "json",
  	   success:function(data){
            if(data.res == 1){
          	  alert ("删除成功");
  			  getUser(page);

            }
  	   
  	   }
  	  
  	 });   
   }
     
     function restoreUser(userid){
    	
  	   $.ajax({
  	   url : "${pageContext.request.contextPath}/AdminUserServlet",
  	   type : "post",
  	   async : "true",
  	   data : {"action" : "restoreUser", "userid":userid},
  	   dataType : "json",
  	   success:function(data){
            if(data.res == 1){
          	  alert ("恢复成功");
  			  getUser(page);

            }
  	   
  	   }
  	  
  	 });   
   }
     
     function searchUser(){
 		
 		username = $("#username").val();
 		getUser(1);
 	}
</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-12 msgtitle">
				<h3>
					<span class="title">用户管理</span>
				</h3>
				<div class="replybtn">
					<button type="button" class="btn btn-success" data-toggle="modal"
							data-target="#search">搜索</button>
					
				</div>
			</div>
		</div>
		<div class="row msglist template">
			<div class="col-sm-10 col-xs-8">
				<div class="col-sm-1 col-xs-2  num"></div>
				<div class="col-sm-1 col-xs-10  username" ></div>
				<div class="col-sm-1 col-xs-4 realname">昵称</div>
				<div class="col-sm-1 col-xs-4 sex">性别</div>
				<div class="col-sm-1 col-xs-4 hobbys">爱好</div>
				<div class="col-sm-1 col-xs-4 birthday">生日</div>
				<div class="col-sm-1 col-xs-4 city">城市</div>
				<div class="col-sm-1 col-xs-4 qq">QQ</div>
				<div class="col-sm-2 col-xs-6 email" data-toggle="tooltip"
        title="zhuangzhuangzhuang@foxmail.com">zhuangzhuangzhuang@foxmail.com</div>
				<div class="col-sm-2 col-xs-6 createtime">注册时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger delete">删除</button>
				<button class="btn btn-warning restore">恢复</button>
			</div>
		</div>
		<div class= "list">
		</div>
		
		<div class="row" style="text-align: center">
	     <jsp:include page="/page/pagetool.jsp"></jsp:include>
        </div>
		<!-- <div class="row msglist">
			<div class="col-sm-10 col-xs-8">
				<div class="col-sm-1 col-xs-2 time">2</div>
				<div class="col-sm-1 col-xs-10 title">用户名</div>
				<div class="col-sm-1 col-xs-4 time">昵称</div>
				<div class="col-sm-1 col-xs-4 time">性别</div>
				<div class="col-sm-1 col-xs-4 time">爱好</div>
				<div class="col-sm-1 col-xs-4 time">生日</div>
				<div class="col-sm-1 col-xs-4 time">城市</div>
				<div class="col-sm-1 col-xs-4 time">QQ</div>
				<div class="col-sm-2 col-xs-6 time">邮箱</div>
				<div class="col-sm-2 col-xs-6 time">注册时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		
		<div class="row msglist">
			<div class="col-sm-10 col-xs-8">
				<div class="col-sm-1 col-xs-2 time">3</div>
				<div class="col-sm-1 col-xs-10 title">用户名</div>
				<div class="col-sm-1 col-xs-4 time">昵称</div>
				<div class="col-sm-1 col-xs-4 time">性别</div>
				<div class="col-sm-1 col-xs-4 time">爱好</div>
				<div class="col-sm-1 col-xs-4 time">生日</div>
				<div class="col-sm-1 col-xs-4 time">城市</div>
				<div class="col-sm-1 col-xs-4 time">QQ</div>
				<div class="col-sm-2 col-xs-6 time">邮箱</div>
				<div class="col-sm-2 col-xs-6 time">注册时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		
		<div class="row msglist">
			<div class="col-sm-10 col-xs-8">
				<div class="col-sm-1 col-xs-2 time">4</div>
				<div class="col-sm-1 col-xs-10 title">用户名</div>
				<div class="col-sm-1 col-xs-4 time">昵称</div>
				<div class="col-sm-1 col-xs-4 time">性别</div>
				<div class="col-sm-1 col-xs-4 time">爱好</div>
				<div class="col-sm-1 col-xs-4 time">生日</div>
				<div class="col-sm-1 col-xs-4 time">城市</div>
				<div class="col-sm-1 col-xs-4 time">QQ</div>
				<div class="col-sm-2 col-xs-6 time">邮箱</div>
				<div class="col-sm-2 col-xs-6 time">注册时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		
		<div class="row msglist">
			<div class="col-sm-10 col-xs-8">
				<div class="col-sm-1 col-xs-2 time">5</div>
				<div class="col-sm-1 col-xs-10 title">用户名</div>
				<div class="col-sm-1 col-xs-4 time">昵称</div>
				<div class="col-sm-1 col-xs-4 time">性别</div>
				<div class="col-sm-1 col-xs-4 time">爱好</div>
				<div class="col-sm-1 col-xs-4 time">生日</div>
				<div class="col-sm-1 col-xs-4 time">城市</div>
				<div class="col-sm-1 col-xs-4 time">QQ</div>
				<div class="col-sm-2 col-xs-6 time">邮箱</div>
				<div class="col-sm-2 col-xs-6 time">注册时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger">删除</button>
			</div>
		</div> -->
		<!-- <div class="row" style="text-align: center">
			<ul class="pagination pagination-lg">
			    <li class="disabled"><a href="#">首页</a></li>
			    <li class="disabled"><a href="#">&laquo;</a></li>
			    <li class="active"><a href="#">1</a></li>
			    <li><a href="#">2</a></li>
			    <li><a href="#">3</a></li>
			    <li><a href="#">4</a></li>
			    <li><a href="#">5</a></li>
			    <li><a href="#">&raquo;</a></li>
			    <li><a href="#">尾页</a></li>
			</ul>
		</div> -->

	</div>
	
		<!-- 模态框（Modal） -->
	<div class="modal fade" id="search" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content modalcenter">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="replyLabel">搜索</h4>
	            </div>
	            <div class="modal-body">
					<form role="form">
						<div class="form-group">
							<label for="username">用户名：</label>
							<input type="text" class="form-control" id="username" placeholder="">
						</div>
					</form>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="searchUser()">搜索</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>