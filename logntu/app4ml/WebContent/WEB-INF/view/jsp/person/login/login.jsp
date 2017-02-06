<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登陆</title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"
	type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/layer.css" rel="stylesheet"/>
<link href="<%=path%>/css/login.css" rel="stylesheet" />
</head>
<body>
	 <div class="logo">
         <a href ="<%=project%>/member/main"><img src="<%=path%>/images/logo.png"></a>
        <p>
            漫浪游戏
        </p>
    </div>
    <div class="w500">
    <ul class="lt-logindiv">
        <h1>
            登录
        </h1>
        <form action="<%=project%>/member/login" id="loginbut"  method="post">
	        <li class="form-group">
	            <input type="text" name="userName" placeholder="请输入邮箱、手机号码" id="number"/>
	        </li>
	        <li class="form-group">
	            <input type="password" name="password" placeholder="请输入密码" id="password"/>
	        </li>
	        <font class=""></font>
	        <a class="fr forget" href="<%=project%>/member/retrieve">忘记密码</a>
	        <input type="button" name="login" class="login_tj_btn" value="登录" onclick="toValidate()">
	       
	        <div class="dl mzh">
	            没有账号
	            <a target="_blank" href="<%=project%>/member/reg">立即注册</a>
	        </div>
        </form>
        <div class="dsf">
           <span>第三方登录</span>
            <ul>
                <li class="fl">
                    <a href="" class="wx"></a>
                </li>
                <li class="fl">
                    <a href="" class="wb"></a>
                </li>
                <li class="fl">
                    <a href="" class="qq"></a>
                </li>
            </ul>
        </div>
    </ul>
    </div>
	<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/layer.js"></script>
<script src="<%=path%>/js/jquery.cxselect.js"></script>
<script src="<%=path%>/js/reg/validate.js"></script>
<script>	
//placeholder 兼容性问题
function isPlaceholder(){
    var input = document.createElement('input');
    return 'placeholder' in input;
}

if (!isPlaceholder()) {//不支持placeholder 用jquery来完成
    $(document).ready(function() {
        if(!isPlaceholder()){
            $("input").not("input[type='password']").each(//把input绑定事件 排除password框
                    function(){
                        if($(this).val()=="" && $(this).attr("placeholder")!=""){
                            $(this).val($(this).attr("placeholder"));
                            $(this).focus(function(){
                                if($(this).val()==$(this).attr("placeholder")) $(this).val("");
                            });
                            $(this).blur(function(){
                                if($(this).val()=="") $(this).val($(this).attr("placeholder"));
                            });
                        }
                    });
            //对password框的特殊处理1.创建一个text框 2获取焦点和失去焦点的时候切换
            var pwdField = $("input[type=password]");
            var pwdVal  = pwdField.attr('placeholder');
            pwdField.after('<input name="password" id="pwdPlaceholder" type="text" value='+pwdVal+' autocomplete="off" />');
            var pwdPlaceholder = $('#pwdPlaceholder');
            pwdPlaceholder.show();
            pwdField.hide();

            pwdPlaceholder.focus(function(){
                pwdPlaceholder.hide();
                pwdField.show();
                pwdField.focus();
            });

            pwdField.blur(function(){
                if(pwdField.val() == '') {
                    pwdPlaceholder.show();
                    pwdField.hide();
                }
            });

        }
    });

}
        $("#number").blur(function(){
        	 var val = new validate({
                 rules:{
                     number:"number",
                 },
                 submitFun:function(){
                     var user = {
                         Tel:$("#number").val(),
                     };
                 }
             })
        })
        $("#password").blur(function(){
            var val = new validate({
                rules:{
                    password:"password1",
                },
                submitFun:function(){
                    var user = {
                        Password:$("#password").val(),
                    };
                }
            })
        })
        
        $(document).keydown(function(event){ 
			if(event.keyCode==13){ 
				toValidate();
			}
		})
        
			
		function toValidate(){
	            var val = new validate({
	                rules:{
	                    number:"number",
	                    password:"password1",
	                },
	                submitFun:function(){
	                    toSubmit();
	                }
	            })
	        }
        function toSubmit(){
        	$("#loginbut").submit();
        } 
    </script>
</html>

