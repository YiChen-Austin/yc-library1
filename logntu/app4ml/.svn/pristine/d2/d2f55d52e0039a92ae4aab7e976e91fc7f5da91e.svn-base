<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>注册</title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"
	type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/layer.css" rel="stylesheet"/>
<link href="<%=path%>/css/reg/style.css" rel="stylesheet" />
</head>

<body>
	<div class="logo">
        <a href ="<%=project%>/member/main"><img src="<%=path%>/images/logo.png"></a>
        <p>
            漫浪游戏
        </p>
    </div>
    <div class="tab">
        <div class="fl sj active">
            <b class="fl"></b>
            <p>
                手机注册
            </p>
            <i></i>
        </div>
        <div class="fl yx">
            <b class="fl"></b>
            <p>
                邮箱注册
            </p>
            <i></i>
        </div>
    </div>
    <div class="tab-div">
        <ul class="lt-logindiv">
    		<form method="post" action="<%=project%>/member/reg4Sm" id="iphonebut" name="payali">
	            <li class="form-group">
	                <input type="text" name="phoneMob" placeholder="请输入手机号码" id="phone"/>
	            </li>
	            <li class="form-group">
	                <input type="text" name="code" placeholder="请输入验证码" id="iphoneyzm"/>
	                <input type="button" id="btnSendCode"  class="hqyzm" value="获取验证码" onclick="dhqyzm()" />
	            </li>
	            <li class="form-group">
	                <input type="password" name="password" placeholder="请输入密码" id="password"/>
	            </li>
	            <li class="mtop30">
	            		实名及防沉迷信息填写
	            </li>
	             <li class="form-group">
	                <input type="text" name="realName" placeholder="请输入真实姓名" id="iphonename"/>
	            </li>
	            <li class="form-group">
	                <input type="text" name="idCard" placeholder="请输入身份证号" id="iphonecard"/>
	            </li>
	            <input name="checkbox" class="ty" type="checkbox" id="tyiphone" checked><a href="">同意漫浪网络用户协议</a>
	            <input type="button" name="login" class="login_tj_btn" value="注册"  onclick="toValidate()">
	       </form>
        </ul>
        <ul class="lt-logindiv">
         	<form method="post" action="<%=project%>/member/reg4Email" id="emlibut" name="payali">
	            <li class="form-group">
	                <input type="text" name="email" placeholder="请输入邮箱" id="email"/>
	            </li>
	            <li class="form-group">
	                <input type="password" name="password" placeholder="请输入密码" id="emailpassword"/>
	            </li>
	            <li class="form-group">
	                <input type="text" name="code" placeholder="请输入验证码" id="emailyzm"/>
	                <img src="<%=project%>/member/vcode" id="emailyzmimg" class="yzmimg emailyzmimg">
	                <a href="javascript:void(0);" onclick="qiehaun()" class="sx"><img  src="<%=path%>/images/reg/sx.png"></a>
	            </li>
	            <li class="mtop30 color0">
	            		实名及防沉迷信息填写
	            </li>
	            <li class="form-group">
	                <input type="text" name="realName" placeholder="请输入真实姓名" id="emailname"/>
	            </li>
	            <li class="form-group">
	                <input type="text" name="idCard" placeholder="请输入身份证号" id="emailcard"/>
	            </li>
	            <input name="checkbox" class="ty" id="tyemail" type="checkbox" checked><a href="">同意漫浪网络用户协议</a>
	            <input type="button" name="login" class="login_tj_btn" value="注册"  onclick="toyoux()">
            </form>
        </ul>
        
    </div>
	<jsp:include page="../include/bottom.jsp" />
</body>
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.cxselect.js"></script>
<script type="text/javascript" src="<%=path%>/js/reg/validate.js"></script>
<script type="text/javascript" src="<%=path%>/js/register.js"></script>
<script type="text/javascript">
//判断手机是否填写，如填写进入是否是机器人验证，如没填写弹出提示
function dhqyzm(){
    if($('#phone').val()==''){
    	layer.msg('手机号不能为空');
    }else{
    	var tcsmrz = layer.open({
            type: 1,
            skin: 'a-gg', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            title: '输入验证码',
            shadeClose: true, //开启遮罩关闭
            content: '<div class="tcdiv "><div class="form-group sjsryzm"><label class="">验证码：</label><input type="text" class="" name="code"id="syzms"/> <a href="javascript:void(0);" onclick="iphoneyzm()"><img src="<%=project%>/member/vcode" class="emailyzmimg" id="iphoneyzmimg"></a></div><div> <div class="inputcenter"><input type="button"  class="call-yzm" value="发送验证码"  onclick="totime()"></div></div></div>'
        });
        $("#syzms").blur(function(){
            var val = new validate({
                rules:{
                    syzms:"null",
                },
                submitFun:function(){
                    var user = {
                        syzms:$("#syzms").val(),
                    };
                }
            })
        });
    }
}
</script>
</html>

