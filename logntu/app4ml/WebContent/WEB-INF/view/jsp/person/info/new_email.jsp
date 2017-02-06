<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=projectTitle%></title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"
	type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
<link href="<%=path%>/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="../include/top.jsp" >
		<jsp:param name="tag" value="center" />
	</jsp:include>
	<div class="w1100 mtb20 color999">
		您当前的位置：首页 > 个人中心 > 确认邮箱号
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="passwd" />
		</jsp:include>
		<div class="main-con fl">
			<div class="update-div">
				<img src="<%=path%>/images/update_email2.png" >
				<ul class="update-ul">
					<li >
						输入新邮箱号：
						<div class="form-group fr mr40">
							<input type="text" class="phone" id="emali"/>
						</div>
					</li>
					<li >
						确认新邮箱号：
						<div class="form-group fr mr40" id="cwts">
							<input type="text" class="phone" id="newemali"/>
						</div>
					</li>
					<li>
						<a href="javascript:void(0);" class="xyb" onclick="emalizhqr()"> 确定</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/reg/validate.js"></script>
<script src="<%=path%>/js/layer.js"></script>
<script>
$("#emali").blur(function() {
	var val = new validate({
		rules : {
			email:"email",
		},
	})
})
$("#newemali").blur(function() {
		var phone = $('#emali').val();
		var newphone = $('#newemali').val();
		if (phone != newphone) {
			$("#newemali").css('border','1px solid #b91c21')
			$("#cwts").append('<label class="s" style="position: absolute;bottom: 0;color:#b91c21;">邮箱号不一致</label>');
			$(".s:gt(0)").remove();
			return false;
		} else {
			$(".s").remove();
			$("#newemali").css('border','1px solid #eeeeee')
		}
})
//邮箱找回 确认
function emalizhqr(){
	var phone = $('#emali').val();
	var newphone = $('#newemali').val();
	if (phone != newphone) {
		$("#newphone").css('border','1px solid #b91c21')
		$("#cwts").append('<label class="s" style="position: absolute;bottom: 0;color:#b91c21;">邮箱号不一致</label>');
		$(".s:gt(0)").remove();
		return false;
	} else {
		$(".s").remove();
		$("#newemali").css('border','1px solid #eeeeee')
	}
	var val = new validate({
        rules:{
        	emali:"email",
        	newemali:"email",
        },
        submitFun:function(){
        	qrtoSubmit();
        }
    })
}

function qrtoSubmit(){
	var user = {
			email:$("#newemali").val(),
    };
    $.ajax({
        url:'updateEmail',
        data:user,
        type:'post',
        dataType:'json',
        success:function(msg){
        	if(msg.serviceCode==0){
        		 window.location.href ="<%=project%>/member/email_suc";
        	}else{
        		layer.msg('修改失败');
        	} 

        }
    })
}
</script>
</html>

