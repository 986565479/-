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
	   getTheme(page);
});
    var key="";
    var thename="";
    var page = 1;
    
    function getTheme(pageNum){
   	   
 	    $.ajax({
 		   url : "${pageContext.request.contextPath}/AdminThemeServlet",
 		   type : "post",
 		   async : "true",
 		   data : {"action" : "searchTheme",  "key":key,  "pageNum":pageNum},
 		   dataType : "json",
 		   success:function(data){
 			   if (data.res==1){
 					$(".list").html("");
 					$.each(data.data.data, function(index, msgItem) {
 						var msg = $(".template").clone();
 						msg.show();
 						msg.removeClass("template");
 						msg.find(".num").text(index+1);
 						msg.find(".title").text(msgItem.thename);
 						
 						msg.find(".delete").attr("onclick", "deleteTheme("+msgItem.theid+")");
 						$(".list").append(msg);
 					});
 	
 					page = setPage(pageNum, data.data.totalPage, "getTheme");
 										
 				} else if (data.res==-2){
 					alert(data.info);
 				}

 		   }
 	   });
    }
    
    function deleteTheme(theid){
    	
   	   $.ajax({
   	   url : "${pageContext.request.contextPath}/AdminThemeServlet",
   	   type : "post",
   	   async : "true",
   	   data : {"action" : "deleteTheme", "theid":theid},
   	   dataType : "json",
   	   success:function(data){
             if(data.res == 1){
           	  alert ("删除成功");
   			  getTheme(page);

             }
   	   
   	   }
   	  
   	 });   
    }
    function addTheme(){
       thename = $("#thename").val();
       $.ajax({
   	   url : "${pageContext.request.contextPath}/AdminThemeServlet",
   	   type : "post",
   	   async : "true",
   	   data : {"action" : "addTheme", "thename":thename},
   	   dataType : "json",
   	   success:function(data){
             if(data.res == 1){
           	  alert ("添加成功");
   			  getTheme(page);

             }
   	   
   	   }
   	  
   	 });   
    }
      
    function searchTheme(){
		key = $("#key").val();
		getTheme(1);
	}
</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8 msgtitle">
				<h3>
					<span class="title">主题管理</span>
				</h3>
				<div class="replybtn">
					<button type="button" class="btn btn-warning" data-toggle="modal"
							data-target="#add">添加</button>
					<button type="button" class="btn btn-success" data-toggle="modal"
							data-target="#search">搜索</button>
					
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8 col-xs-12 msglist template">
				<div class="col-sm-2 col-xs-2 num">1</div>
				<div class="col-sm-8 col-xs-7 title">Java</div>
				<!-- <div class="col-sm-2 col-xs-3 "> -->
					<button class="btn btn-danger delete">删除</button>
				</div>
			</div>
		
		
		<div class= "list"></div>
		
		
		<%-- <div class="row" style="text-align: center">
	        <jsp:include page="/page/pagetool.jsp"></jsp:include>
        </div> --%>
        
		<div class="row" style="text-align: center">
		<ul class="pagination pagination-lg">
				<li class="head"><a href="#">首页</a></li>
				<li class="lastpage"><a href="#">&laquo;</a></li>
				<li class="disabled morehead"><a>...</a></li>
				<li class="page-4"><a></a></li>
				<li class="page-3"><a></a></li>
				<li class="page-2"><a></a></li>
				<li class="page-1"><a></a></li>
				<li class="currentpage active"><a>1</a></li>
				<li class="page_1"><a></a></li>
				<li class="page_2"><a></a></li>
				<li class="page_3"><a></a></li>
				<li class="page_4"><a></a></li>
				<li class="disabled moretail"><a>...</a></li>
				<li class="nextpage"><a href="#">&raquo;</a></li>
				<li class="tail"><a href="#">尾页</a></li>
			</ul>
		</div> 
	</div>
		
<!-- 模态框（Modal） -->
	<div class="modal fade" id="search" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content modalcenter">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title">搜索</h4>
	            </div>
	            <div class="modal-body">
					<form role="form">
						<div class="form-group">
							<label for="thename">主题名：</label>
							<input type="text" class="form-control" id="key" placeholder="">
						</div>
					</form>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="searchTheme()">搜索</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content modalcenter">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title">添加主题</h4>
	            </div>
	            <div class="modal-body">
					<form role="form">
						<div class="form-group">
							<label for="thename">主题名：</label>
							<input type="text" class="form-control" id="thename" placeholder="">
						</div>
					</form>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addTheme()">添加</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>