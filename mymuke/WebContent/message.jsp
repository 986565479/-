<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<% 
	String msgId = request.getParameter("msgid");
	if (msgId == null || msgId.equals("")){
		msgId = "1";
	}
%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="bootstrapvalidator/css/bootstrapValidator.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 表单验证 -->
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script type="text/javascript">
	var pageNum = 1;
	var msgId = <%=msgId%>;
	
	$(function(){
		getMsg();
	});
	
	$(function(){
		$("#replyform").bootstrapValidator({
		 	message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证*/
            	replycontent: {/*键名username和input name值对应*/
                    message: 'The reply is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '回复内容不能为空'
                        },
                        stringLength: {/*长度提示*/
                            min: 6,
                            max: 1000,
                            message: '回复内容必须在6到1000之间'
                        }/*最后一个没有逗号*/
                        
                    }
                }
            }
        });

	});
	function validateForm(){
		// 验证表单
		validateForm();
	}
	
	function getMsg(){
		// Ajax 获取问题信息
		$.ajax({
			url : "${pageContext.request.contextPath}/MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "getMsg", "msgid":msgId},
			dataType : "json",
			success : function(data) {
				if (data.res==1){
					$(".title").html(data.data.msgtopic);
					$(".badge").html(data.data.thename);
					
					var reply = $(".template").clone();
					reply.show();
					reply.removeClass("template");
					if (data.data.photo != null){
						reply.find(".msgphoto").attr("src", "upload/"+data.data.photo);
					}
					reply.find(".author").text(data.data.realname);
					reply.find(".sex").text(data.data.sex);
					reply.find(".city").text(data.data.city);
					reply.find(".msgcontent").html(data.data.msgcontents);
					reply.find(".order").text("楼主");
					reply.find(".time").text(data.data.msgip + " • " + data.data.msgtime);
					
					$("#msgList").append(reply);
					
					$("#replyLabel").html("回复：" + data.data.msgtopic);

					getReply();

					
				} 
	        }
	});
		return false;
		
	}
	
	function getReply(){
		// Ajax 获取回复信息
		$.ajax({
			url : "${pageContext.request.contextPath}/MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "getReply", "msgid":msgId, "pageNum":pageNum},
			dataType : "json",
			success : function(data) {
				var html = "";
				$.each(data.data.data, function(index, replyItem) {
					var reply = $(".template").clone();
					reply.show();
					reply.removeClass("template");

					if (replyItem.photo != null){
						reply.find(".msgphoto").attr("src", "upload/"+replyItem.photo);
					}
					reply.find(".author").text(replyItem.realname);
					reply.find(".sex").text(replyItem.sex);
					reply.find(".city").text(replyItem.city);
					reply.find(".msgcontent").html(replyItem.replycontents);
					reply.find(".order").text((index+1+data.data.pageNumber*(data.data.curPage-1)) + "楼");
					reply.find(".time").text(replyItem.replyip + " • " + replyItem.replytime);
					
					$("#msgList").append(reply);
				});
				
				pageNum++;
				// 加载更多
				if (parseInt(data.data.totalPage) >= parseInt(pageNum)){
					$("#loadmore").html("加载更多...");
				}
				else{
					$("#loadmore").html("没有更多数据了！");
				}
			}
		});

	}
	
	function replyMsg(){
		// 提交前先主动验证表单 
		
	    var bv = $("#replyform").data('bootstrapValidator');
		bv.validate();
		if (!bv.isValid()){
			return;
		} 
		
		var replyContent = $("#replycontent").val();
		$("#replycontent").val("");

	// Ajax 回复问题
		$.ajax({
			
			url : "${pageContext.request.contextPath}/MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "ReplyMsg", "msgid":msgId, "replycontent":replyContent},
			dataType : "json",
			
			success : function(data) {
				
				if (data.res >= 1){
					alert("回复成功！");
					location.reload();
				}
				else if(data.res == -2){
					alert(data.info);
				}
			}
		});

	}

</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container" id="msgList">
		<div class="row">
			<div class="col-sm-12 msgtitle">
				<h3>
					<span class="title"></span>&nbsp;&nbsp;<span class="badge"></span>
				</h3>
				<div class="replybtn">
					<c:if test="${sessionScope.user == null}">
				  		<button type="button" class="btn btn-success" data-toggle="modal"
							onclick="alert('请先登录！')">回复</button>
					</c:if>
					<c:if test="${sessionScope.user != null}">
						<button type="button" class="btn btn-success" data-toggle="modal"
							data-target="#reply" >回复</button>
					</c:if>
					
				</div>
			</div>
		</div>
		<div class="row reply template">
			<div class="col-sm-12" style="overflow: hidden;">
				<div class="rightinfo order">0楼</div>
			</div>
			<div class="col-sm-2 col-xs-2">
				<div class="author"></div>
				<div class="sex"></div>
				<div class="city"></div>
			</div>
			<div class="col-sm-10 col-xs-10">
				<div class="msgcontent"></div>
			</div>
			<div class="col-sm-12" style="overflow: hidden;">
				<div class="rightinfo time"></div>
			</div>
		</div>
		<!-- <div class="row reply" style="display: block;">
			<div class="col-sm-12" style="overflow: hidden;">
				<div class="rightinfo order">楼主</div>
			</div>
			<div class="col-sm-2 col-xs-2">
				<div class="author">冰城小象</div>
				<div class="sex">男</div>
				<div class="city">济宁</div>
			</div>
			<div class="col-sm-10 col-xs-10">
				<div class="msgcontent">使用循环打印以下圣诞树：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//&nbsp;空格（高度-1），*&nbsp;1（2*行数-1），<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;***&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//&nbsp;空格（高度-2），*3<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*****&nbsp;&nbsp;&nbsp;&nbsp;//&nbsp;空格（高度-3），*5<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*******<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*********<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;***********&nbsp;&nbsp;<br>n要求输入树的高度，打印圣诞树。</div>
			</div>
			<div class="col-sm-12" style="overflow: hidden;">
				<div class="rightinfo time">172.17.111.30 • 2017-08-28 15:37:21</div>
			</div>
		</div>
		<div class="row reply" style="display: block;">
			<div class="col-sm-12" style="overflow: hidden;">
				<div class="rightinfo order">1楼</div>
			</div>
			<div class="col-sm-2 col-xs-2">
				<div class="author">冰城小象</div>
				<div class="sex">男</div>
				<div class="city">济宁</div>
			</div>
			<div class="col-sm-10 col-xs-10">
				<div class="msgcontent">import&nbsp;java.util.Scanner;<br><br>public&nbsp;class&nbsp;test&nbsp;{<br><br>&nbsp;&nbsp;&nbsp;&nbsp;public&nbsp;static&nbsp;void&nbsp;main(String[]&nbsp;args)&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Scanner&nbsp;scanner&nbsp;=&nbsp;new&nbsp;Scanner(System.in);<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;int&nbsp;count&nbsp;=&nbsp;scanner.nextInt();<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for&nbsp;(int&nbsp;i=0;&nbsp;i&gt;count;&nbsp;i++){<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for&nbsp;(int&nbsp;j=0;&nbsp;j&gt;count*2;&nbsp;j++){<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if&nbsp;(&nbsp;i+j&gt;count&nbsp;||&nbsp;j-count&lt;i&nbsp;){<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.print("&nbsp;");<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;else&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.print("*");<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println();<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>&nbsp;&nbsp;&nbsp;&nbsp;}<br>}<br></div>
			</div>
			<div class="col-sm-12" style="overflow: hidden;">
				<div class="rightinfo time">172.17.111.30 • 2017-08-28 16:34:20</div>
			</div>
		</div>-->
	</div> 
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<br/>
				<button id="loadmore" type="button" class="btn btn-default btn-lg btn-block"
				onclick="javascript:getReply();">加载更多...</button>
			</div>
		</div>
	</div>

					
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="reply" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content modalcenter">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="replyLabel">回复：</h4>
	            </div>
	            <div class="modal-body">
	    			<!-- 
	    			<textarea style="width:100%" rows="5" cols="" id="replycontent"></textarea>
	    			 -->
	    			 <form id="replyform">
	    			 	<div class="form-group">
	    			 		<textarea style="width:100%" rows="5" cols="" name="replycontent" id="replycontent"></textarea>
	    			 	</div>
	    			 	<div class="text-right">
	    			 		<span id="returnMessage" class="glyhicon"></span>
	    			 		<p></p>
			                <button class="btn btn-default" data-dismiss="modal" >关闭</button>
	    			 		<button class="btn btn-primary" data-dismiss="modal"  onclick="replyMsg();">提交</button>
	    			 	</div>
	    			 </form>
	            </div>
	            <div class="modal-footer">
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>