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
<link rel="stylesheet" href="bootstrapvalidator/css/bootstrapValidator.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 表单验证 -->
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script type="text/javascript">
	$(function(){

		 validateForm();
	});
	
	function validateForm(){
		// 验证表单
		$("#modifyform").bootstrapValidator({
		 	message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields:{//验证  
            	oldpassword: {
		                    message:'密码无效',
		                    validators: {
		                        notEmpty: {
		                            message: '密码不能为空'
		                        },
		                        regexp: {
		                        	regexp: /^[a-zA-Z0-9_\.]+$/,
		                        	message: '密码不合法, 请重新输入'
		                        },
		                        stringLength: {
		                            min: 6,
		                            max: 30,
		                            message: '密码长度必须在6到30之间'
		                        }
		                    }
		                },
		          newpassword: { 
		                message:'密码无效',
		                validators: {
		                    notEmpty: {
		                        message: '密码不能为空'
		                    },
		                    regexp: {
		                    	regexp: /^[a-zA-Z0-9_\.]+$/,
		                    	message: '密码不合法, 请重新输入'
		                    },
		                    stringLength: {
		                        min: 6,
		                        max: 30,
		                        message: '密码长度必须在6到30之间'
		                    },
		                    identical:{//同一的
		                    	field:'newpassword2',
		                    	message:'两次密码必须一致'
		                    }
		                }
		            },
		      newpassword2:{//确认密码
		        	   message:'密码不相同',
		                validators: {
		                    notEmpty: {
		                        message: '确认密码不能为空'
		                    },
		                   identical:{
		                	   field:'newpassword',
		                	   message:'两次密码必须一致'
		                   }
		                }
		           }
            
           }  
		});
            
	}
	
	function updatePW(){
		// ajax 异步请求修改密码
		var oldpassword = $("input[name='oldpassword']").val();//获取旧密码
		var newpassword = $("input[name='newpassword']").val();//获取新密码
		
		$.ajax({
			url : "${pageContext.request.contextPath}/UserServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "updatePW", 
				"oldpassword": oldpassword, 
				"newpassword": newpassword},
			dataType : "json",
			success : function(data) {
				if (data.res == 1){
					alert("修改成功");
					window.location.replace("");
				}
				else {
					$(".text-warning").text(data.info);
					$("input[name='oldpassword']").val("");
					//$("input[name='password']").val("");
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
			<div class="col-sm-offset-3 col-sm-6 text-center">
				<h3>修改密码</h3>
			</div>
		</div>
		<form class="form-horizontal col-sm-offset-3" id="modifyform" method="post">
			
			<div class="form-group">
				<label for="oldpassword" class="col-sm-2 control-label">旧密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="oldpassword" placeholder="请输入旧密码">
				</div>
			</div>
			<div class="form-group">
				<label for="newpassword" class="col-sm-2 control-label">新密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="newpassword" placeholder="请输入新密码">
				</div>
			</div>
			<div class="form-group">
				<label for="newpassword2" class="col-sm-2 control-label">确认密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="newpassword2" placeholder="请确认新密码">
				</div>
			</div>
			<div class="form-group has-error">
				<div class="col-sm-offset-2 col-sm-4 col-xs-6 ">
					<span class="text-warning"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4 col-xs-12">
					<button type="button" class="btn btn-success btn-block" onclick="updatePW();">提交</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include flush="fasle" page="../footer.jsp" />
</body>
</html>