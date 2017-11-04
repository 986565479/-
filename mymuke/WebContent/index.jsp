
<%@page import="util.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script language="javascript">

// Ajax异步请求最新五条,
	function getNew() {
		$.ajax({
			url : "${pageContext.request.contextPath}/MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "topNew"},
			dataType : "json",
			success : function(data) {
				if (data.res==1){
					// 遍历数组内容
					$.each(data.data.data, function(index, msgItem) {
						// 根据HTML模版创建副本
						var msg = $(".template").clone();
						// 设置属性
						msg.attr("display", "block");
						msg.removeClass("template");
						msg.find(".msgtile").text(msgItem.msgtopic);
						msg.find(".msgtile").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg.find(".time").text(msgItem.msgtime);
						if (msgItem.state == 10){
							msg.find(".top").text("置顶");
							msg.find(".top").css("background","red");
						}
						if (msgItem.state == 5){
							msg.find(".wonderful").text("精华");
							msg.find(".wonderful").css("background","blue");
						}
						// 节点追加
						$(".newList").append(msg);

					});
				} else if (data.res==-2){
					alert(data.info);
				}
	        }
	});
		return false;
}
	
	function getHot() {
		
		// Ajax异步请求最热五条,就是评论最多的五条
		$.ajax({
			url : "${pageContext.request.contextPath}/MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "topHot"},
			dataType : "json",
			success : function(data) {
				if (data.res==1){
					// 遍历数组内容
					$.each(data.data.data, function(index, msgItem) {
						// 根据HTML模版创建副本
						var msg = $(".template").clone();
						// 设置属性
						msg.attr("display", "block");
						msg.removeClass("template");
						msg.find(".msgtile").text(msgItem.msgtopic);
						msg.find(".msgtile").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg.find(".time").text(msgItem.replyCount);
						if (msgItem.state == 10){
							msg.find(".top").text("置顶");
							msg.find(".top").css("background","red");
						}
						if (msgItem.state == 5){
							msg.find(".wonderful").text("精华");
							msg.find(".wonderful").css("background","blue");
						}
						// 节点追加
						$(".hotList").append(msg);

					});
				} else if (data.res==-2){
					alert(data.info);
				}
	        }
	});
		return false;
	}
	
	function getTheme() {
		
		// Ajax异步请求, 最热的五个主题
		$.ajax({
			url : "${pageContext.request.contextPath}/MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "topTheme"},
			dataType : "json",
			success : function(data) {
				if (data.res==1){
					// 遍历数组内容
					$.each(data.data.data, function(index, msgItem) {
						// 根据HTML模版创建副本
						var msg = $(".template").clone();
						// 设置属性
						msg.attr("display", "block");
						msg.removeClass("template");
						msg.find(".msgtile").text(msgItem.msgtopic);
						msg.find(".msgtile").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg.find(".time").text(msgItem.thename);
						if (msgItem.state == 10){
							msg.find(".top").text("置顶");
							msg.find(".top").css("background","red");
						}
						if (msgItem.state == 5){
							msg.find(".wonderful").text("精华");
							msg.find(".wonderful").css("background","blue");
						}
						// 节点追加
						$(".themeList").append(msg);

					});
				} else if (data.res==-2){
					alert(data.info);
				}
	        }
	});
		return false;
	}
	
	$(function() {
		getNew();
		getHot();
		getTheme();
	});
</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">
		
		<div class="row">
			<div class="col-sm-4">
				<div
					style="overflow: auto; height: 60px; line-height: 40px; padding-top: 20px;">
					<div style="float: left">
						<h3 style="display: inline">最新</h3>
					</div>
					<div style="float: right; vertical-align: bottom;">
						<a href="newmsg.jsp">更多>></a>
					</div>
				</div>
				<div>
					<ul class="list-group newList">
						<li class="list-group-item template">
							<span class="badge time"></span>
							<span class="badge top"></span>
							<span class="badge wonderful"></span>
							<a class="msgtile text-limit"></a>
						</li>
					
					</ul>
				</div>
				
			</div>
			<div class="col-sm-4">
				<div
					style="overflow: auto; height: 60px; line-height: 40px; padding-top: 20px;">
					<div style="float: left">
						<h3 style="display: inline">最热</h3>
					</div>
					<div style="float: right; vertical-align: bottom;">
						<a href="hotmsg.jsp">更多>></a>
					</div>
				</div>
				<div>
				<ul class="list-group hotList">
						<!-- <li class="list-group-item template1" >
							
							<span class="badge top1"></span>
							<span class="badge wonderful1"></span>
							<span class="badge time1"></span>
							<a class="msgtile1 text-limit"></a>
						</li> -->
				</ul>
			</div>
		</div>
			<div class="col-sm-4">
				<div
					style="overflow: auto; height: 60px; line-height: 40px; padding-top: 20px;">
					<div style="float: left">
						<h3 style="display: inline">话题</h3>
					</div>
					<div style="float: right; vertical-align: bottom;">
						<a href="thememsg.jsp">更多>></a>
					</div>
				</div>
				<div>
					<ul class="list-group themeList">
						<!-- <li class="list-group-item template2" style="display:none">
							<span class="badge time2"></span>
							<span class="badge top2"></span>
							<span class="badge wonderful2"></span>
							<a class="msgtile2 text-limit"></a>
						</li>
						<li class="list-group-item" display="block">
							<span class="badge">BUG反馈</span>
							<a class="msgtile text-limit" href="message.jsp?msgid=6">网站Bug请在此留下</a>
						</li>
						<li class="list-group-item" display="block">
							<span class="badge">MySQL</span>
							<a class="msgtile text-limit" href="message.jsp?msgid=5">如何完全卸载MySQL数据库</a>
						</li>
						<li class="list-group-item" display="block">
							<span class="badge">Web前端</span>
							<a class="msgtile text-limit" href="message.jsp?msgid=4">盒子模型是怎么回事?</a>
						</li>
						<li class="list-group-item" display="block">
							<span class="badge">Java</span>
							<a class="msgtile text-limit" href="message.jsp?msgid=3">JDK配置环境变量</a>
						</li> -->
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
	
</body>
</html>