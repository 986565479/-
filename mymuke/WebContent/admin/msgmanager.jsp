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
	   //显示帖子
	   getMsg(page);
   });
   
   var key="";
   var name="";
   var theme="";
   var page = 1;
   function getMsg(pageNum){
	   
	    $.ajax({
		   url : "${pageContext.request.contextPath}/AdminMessageServlet",
		   type : "post",
		   async : "true",
		   data : {"action" : "serchMsg", "key":key, "name":name, "theme":theme, "pageNum":pageNum},
		   dataType : "json",
		   success:function(data){
			   if (data.res==1){
					$(".list").html("");
					$.each(data.data.data, function(index, msgItem) {
						var msg = $(".template").clone();
						msg.show();
						msg.removeClass("template");
						msg.find(".num").text(index+1);
						msg.find(".tit").text(msgItem.msgtopic);
						msg.find(".tit").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg.find(".author").text(msgItem.realname);
						msg.find(".time").text(msgItem.msgtime);
						msg.find(".delete").attr("onclick", "deleteMsg("+msgItem.msgid+")");
						msg.find(".restore").attr("onclick", "restoreMsg("+msgItem.msgid+")");
						msg.find(".btn-top").attr("onclick", "toTop("+msgItem.msgid+")");
						msg.find(".btn-wonderful").attr("onclick", "wonderful("+msgItem.msgid+")");
						
						if (msgItem.state == 10){
							msg.find(".top").text("置顶");
							msg.find(".top").css("background","red");
						}
						else if (msgItem.state == 5){
							msg.find(".wonderful").text("精华");
							msg.find(".wonderful").css("background","blue");
						}

						if (msgItem.state == 1) {
							msg.find(".delete").show();
							msg.find(".restore").hide();
							msg.find(".btn-top").show();
							msg.find(".btn-wonderful").show();
						}
						else {
							msg.find(".delete").hide();
							msg.find(".restore").show();
							msg.find(".btn-top").hide();
							msg.find(".btn-wonderful").hide();
						}
						
						$(".list").append(msg);
					});
	
					page = setPage(pageNum, data.data.totalPage, "getMsg");
										
				} else if (data.res==-2){
					alert(data.info);
				}

		   }
	   });
   }
   
   function searchMsg(){
		key = $("#key").val();
		name = $("#username").val();
		theme = $("#theme").val();
		
		getMsg(1);
	}

   function deleteMsg(msgid){
	   $.ajax({
	   url : "${pageContext.request.contextPath}/AdminMessageServlet",
	   type : "post",
	   async : "true",
	   data : {"action" : "deleteMsg", "msgid":msgid},
	   dataType : "json",
	   success:function(data){
          if(data.res == 1){
        	  alert ("删除成功");
			  getMsg(page);

          }
	   
	   }
	  
	 });   
 }
   function restoreMsg(msgid){
	   $.ajax({
	   url : "${pageContext.request.contextPath}/AdminMessageServlet",
	   type : "post",
	   async : "true",
	   data : {"action" : "restoreMsg", "msgid":msgid},
	   dataType : "json",
	   success:function(data){
          if(data.res == 1){
        	  alert ("恢复成功");
			  getMsg(page);

          }
	   
	   }
	  
	 });   
 }
   function toTop(msgid){
	   $.ajax({
		   url : "${pageContext.request.contextPath}/AdminMessageServlet",
		   type : "post",
		   async : "true",
		   data : {"action" : "toTop", "msgid":msgid},
		   dataType : "json",
		   success:function(data){
	          if(data.res == 1){
	        	  alert ("置顶成功");
				  getMsg(page);

	          }else if(res==-2){
				   alert(data.info);
			   }
		   
		   }
		  
		 });  
   }
   function wonderful(msgid){
	   $.ajax({
		   url : "${pageContext.request.contextPath}/AdminMessageServlet",
		   type : "post",
		   async : "true",
		   data : {"action" : "wonderful", "msgid":msgid},
		   dataType : "json",
		   success:function(data){
	          if(data.res == 1){
	        	  alert ("加精成功");
				  getMsg(page);

	          }else if(res==-2){
				   alert(data.info);
			   }
		   
		   }
		  
		 });  
   }
</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-12 msgtitle">
				<h3>
					<span class="title">帖子管理</span>
				</h3>
				<div class="replybtn">
					<button type="button" class="btn btn-success" data-toggle="modal"
							data-target="#search">搜索</button>
					
				</div>
			</div>
		</div>
		
		<div class="row msglist">
			<div class="col-sm-1 col-xs-1 text-center"></div>
			<div class="col-sm-9 col-xs-8">
				<div class="col-sm-8 col-xs-12 title">标题</div>
				<div class="col-sm-2 col-xs-6 author">作者</div>
				<div class="col-sm-2 col-xs-6 time">时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger">操作</button>
			</div>
		</div>
		<!--  -->
		<div class="row msglist template">
			<div class="col-sm-1 col-xs-1 text-center num">1</div>
			<div class="col-sm-8 col-xs-8">
				<div class="col-sm-8 col-xs-12 title">
					<a class="tit" target="_blank">标题</a>&nbsp;&nbsp;
					<span class="badge wonderful"></span>
					<span class="badge top"></span>
				</div>
				<div class="col-sm-2 col-xs-6 author">作者</div>
				<div class="col-sm-2 col-xs-6 time">时间</div>
			</div>
			<div class="col-sm-3  col-sm-offset-0 col-xs-offset-5 col-xs-7 ">
				<button class="btn btn-danger delete">删除</button>
				<button class="btn btn-warning restore">恢复</button>
				<button class="btn btn-success btn-top">置顶</button>
				<button class="btn btn-info btn-wonderful">加精</button>
			</div>
		</div>
		<div class= "list">
		</div>
		<div class="row" style="text-align: center">
		<!--  -->
		
		<!-- <div class="row msglist">
			<div class="col-sm-1 col-xs-1 text-center">2</div>
			<div class="col-sm-9 col-xs-8">
				<div class="col-sm-8 col-xs-12 title">标题</div>
				<div class="col-sm-2 col-xs-6 author">作者</div>
				<div class="col-sm-2 col-xs-6 time">时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<div class="row msglist">
			<div class="col-sm-1 col-xs-1 text-center">3</div>
			<div class="col-sm-9 col-xs-8">
				<div class="col-sm-8 col-xs-12 title">标题</div>
				<div class="col-sm-2 col-xs-6 author">作者</div>
				<div class="col-sm-2 col-xs-6 time">时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<div class="row msglist">
			<div class="col-sm-1 col-xs-1 text-center">4</div>
			<div class="col-sm-9 col-xs-8">
				<div class="col-sm-8 col-xs-12 title">标题</div>
				<div class="col-sm-2 col-xs-6 author">作者</div>
				<div class="col-sm-2 col-xs-6 time">时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<div class="row msglist">
			<div class="col-sm-1 col-xs-1 text-center">5</div>
			<div class="col-sm-9 col-xs-8">
				<div class="col-sm-8 col-xs-12 title">标题</div>
				<div class="col-sm-2 col-xs-6 author">作者</div>
				<div class="col-sm-2 col-xs-6 time">时间</div>
			</div>
			<div class="col-sm-2 col-xs-3 ">
				<button class="btn btn-danger">删除</button>
			</div> 
		</div>-->
		<div class="row" style="text-align: center">
	<jsp:include page="/page/pagetool.jsp"></jsp:include>
</div>
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
							<label for="key">关键字：</label>
							<input type="text" class="form-control" id="key" placeholder="">
						</div>
						<div class="form-group">
							<label for="username">用户名：</label>
							<input type="text" class="form-control" id="username" placeholder="">
						</div>
						<div class="form-group">
							<label for="theme">主题：</label>
							<select class="form-control" id="theme">
								<option>未选择</option>
								<option>Java</option>
								<option>SSH</option>
								<option>MySQL</option>
							</select>
						</div>
					</form>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="searchMsg()">搜索</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
	</div>
</body>
</html>