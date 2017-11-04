<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   $(function(){
	   getMsgCount();
   });
   function getMsgCount(){
	   $.ajax({
	   url : "/AdminMessageServlet",
	   type : "post",
	   async : "true",
	   data : {"action" : "getMsgCount"},
	   dataType : "json",
	   success : function(data){
	   if (data.res == 1){
		   
	   $("#todayMsg").text(data.data[0]);
	   $("#weekMsg").text(data.data[1]);
	   $("#monthMsg").text(data.data[2]); 
	   }
	   else {
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
			<div class="col-sm-offset-2 col-sm-8 text-center">
				<h3>数据统计</h3>
				<table class="table table-striped">
				  <caption>发帖统计</caption>
				  <thead>
				    <tr>
				      <th>项目</th>
				      <th>数量</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>今日发帖</td>
				      <td id="todayMsg">5</td>
				    </tr>
				    <tr>
				      <td>本周发帖</td>
				      <td id="weekMsg">34</td>
				    </tr>
				    <tr>
				      <td>本月发帖</td>
				      <td id="monthMsg">34</td>
				    </tr>
				  </tbody>
				</table>
				<table class="table table-striped">
				  <caption>回复统计</caption>
				  <thead>
				    <tr>
				      <th>项目</th>
				      <th>数量</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>今日回复</td>
				      <td>2</td>
				    </tr>
				    <tr>
				      <td>本周回复</td>
				      <td>13</td>
				    </tr>
				    <tr>
				      <td>本月回复</td>
				      <td>13</td>
				    </tr>
				  </tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>