<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>确认密码</title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"
type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/retrieve.css" rel="stylesheet" />
</head>
<body>
	<div class="header">
		<div class="w1100 header-div">
			<a href ="<%=project%>/member/main"><img src="<%=path%>/images/logo.png" class="fl"></a>
			<div class="fr dh">
				<i class="fl"></i>023-63110535
			</div>	
		</div>
	</div>
	<div class =" title">
		<div class="w1100">
			找回密码
		</div>
	</div>
    <div class=" w500 center">
      	<div >
      		<ul class="tab-ul">
	      		<li class="tab form-group"> 
	      			<div class="tit-left fl">输入新密码:</div>
	      			<div class="fl tit-right">
		      			<input type="password" id="newpassword"/>
	      			</div>
	      		</li>
	      		<li class="tab form-group"> 
	      			<div class="tit-left fl">确认新密码:</div>
	      			<div class="fl tit-right">
		      			<input type="password" id="qrpassword"/>
	      			</div>
	      		</li>
	      		<li class="tab form-group">
	      			<div class="tit-left fl"></div>
	      			<div class="fl tit-right">
		      			<a class="xyb" onclick="toPassword()"> 确定</a>
	      			</div>	
	      		</li>
      		</ul>
      	</div>
    </div>
    
	
    <script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=path%>/js/layer.js"></script>
	<script src="<%=path%>/js/reg/validate.js"></script>
	<script>
	$("#newpassword").blur(function(){
        var val = new validate({
            rules:{
                newpassword:"password"
            },
            submitFun:function(){
                var user = {
                    NewPassword:$("#newpassword").val(),
                };
            }
        })
    })
    $("#qrpassword").blur(function(){
        var password = $('#newpassword').val();
        var password1 = $('#qrpassword').val();
        if(password!=password1){
            $("#qrpassword").after('<label class="s" style="color: #b91c21;position: absolute;bottom: 0px;left: 5px;">密码不一致</label>');
            $(".s:gt(0)").remove();
            return false;
        }else{
            $(".s").remove();
        }
    })
    function toPassword() {
        var newpassword = $('#newpassword').val();
        var qrpassword = $('#qrpassword').val();
        if(newpassword!=qrpassword){
            $("#qrpassword").after('<label class="s" style="color: #b91c21;position: absolute;bottom: 0px;left: 5px;">密码不一致</label>');
            $(".s:gt(0)").remove();
            return false;
        }else{
            $(".s").remove();
        }
        var val = new validate({
            rules: {
            	newpassword:"password",
            },
            submitFun: function () {
                toPassword1();
            }
        })
    }
	function toPassword1(){

		$.ajax({
            url:'<%=project%>/member/updatePwd',
            data:{newPw:$('#qrpassword').val()},
            type:'post',
            dataType:'json',
            success:function(result){
            	console.log(result);
            	if(result.msg){
            		 window.location.href ="<%=project%>/member/success";
            	}else{
            		layer.msg('修改失败');
            	}
            }
        })
    }
	</script>
</body>
</html>
