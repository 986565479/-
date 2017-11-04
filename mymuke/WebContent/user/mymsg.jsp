<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.MyMsg" %>
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
<title>慕课答疑平台</title>
<script type="text/javascript">
	var pageNum = 1;
	
	$(function(){
		getMyMsg();
	});

	function getMyMsg(){
		// ajax 异步获取我的问题
		$.ajax({
			url : "${pageContext.request.contextPath}/MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "myMsg","pageNum":pageNum},
			dataType : "json",
			success : function(data) {
				     
				     if(data.res==1){
					 $.each(data.data.data, function(index, MyMsg) {
						// 根据HTML模版创建副本
						
						var msg = $(".template").clone();
						// 设置属性
						msg.attr("display", "block");
						msg.removeClass("template");
						msg.find(".title").text(MyMsg.msgtopic);
						msg.find(".title").attr("href", "message.jsp?msgid="+MyMsg.msgid);
						msg.find(".time").text(MyMsg.msgtime);
						msg.find(".count").text(MyMsg.accessCount+"/"+MyMsg.replyCount);
						// 节点追加
						$(".newList").append(msg);

					}); 
				
					 pageNum++;
			  }
			}
		});
		
		return false;
	}
</script>
</head>
<body>
	<jsp:include flush="fasle" page="../header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-12 msgtitle"><h3>我的问题</h3></div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-xs-8"><h4>标题</h4></div>
			<div class="col-sm-2 col-xs-4 text-center"><h4>时间</h4></div>
			<div class="col-sm-2 hidden-xs text-center"><h4>浏览 • 回复</h4></div>
		</div>
		<div class="newList">
		<div class="row msglist template">
			<div class="col-sm-12">
				<div class="col-sm-8 col-xs-8 text-limit">
					<a class="title">标题标题标题标题标题标题</a>
				</div>
				<div class="col-sm-2  col-xs-4 text-center time">时间</div>
				<div class="col-sm-2 hidden-xs text-center count">浏览/回复</div>
			</div>
		</div>
		</div>
		<div class="row msglist" style="display: none;">
			<div class="col-sm-12">
				<div class="col-sm-8 col-xs-8 text-limit">
					<a class="title" href="message.jsp?msgid=6">网站Bug请在此留下</a>
				</div>
				<div class="col-sm-2  col-xs-4 text-center time">17-08-28</div>
				<div class="col-sm-2 hidden-xs text-center count">4 • 0</div>
			</div>
		</div>
		<!-- <div class="row msglist" style="display: block;">
			<div class="col-sm-12">
				<div class="col-sm-8 col-xs-8 text-limit">
					<a class="title" href="#">如何完全卸载MySQL数据库</a>
				</div>
				<div class="col-sm-2  col-xs-4 text-center time">17-08-28</div>
				<div class="col-sm-2 hidden-xs text-center count">9 • 5</div>
			</div>
		</div>
		<div class="row msglist" style="display: block;">
			<div class="col-sm-12">
				<div class="col-sm-8 col-xs-8 text-limit">
					<a class="title" href="#">盒子模型是怎么回事?</a>
				</div>
				<div class="col-sm-2  col-xs-4 text-center time">17-08-28</div>
				<div class="col-sm-2 hidden-xs text-center count">3 • 1</div>
			</div>
		</div>
		<div class="row msglist" style="display: block;">
			<div class="col-sm-12">
				<div class="col-sm-8 col-xs-8 text-limit">
					<a class="title" href="#">JDK配置环境变量</a>
				</div>
				<div class="col-sm-2  col-xs-4 text-center time">17-08-28</div>
				<div class="col-sm-2 hidden-xs text-center count">8 • 5</div>
			</div>
		</div>
		<div class="row msglist" style="display: block;">
			<div class="col-sm-12">
				<div class="col-sm-8 col-xs-8 text-limit">
					<a class="title" href="#">异步是啥东西</a>
				</div>
				<div class="col-sm-2  col-xs-4 text-center time">17-08-28</div>
				<div class="col-sm-2 hidden-xs text-center count">3 • 0</div>
			</div>
		</div> -->
		<div class="row p">
			<div class="col-sm-12">
				<br/>
				<button id="loadmore" type="button" class="btn btn-default btn-lg btn-block" 
				onclick="javascript:getMyMsg();">加载更多...</button>
			</div>
		</div>
	</div>
	<jsp:include flush="fasle" page="../footer.jsp" />
</body>
</html>