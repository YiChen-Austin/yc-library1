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
		您当前的位置：首页 > 个人中心 > 修改邮箱号
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="passwd" />
		</jsp:include>
		<div class="main-con fl">
			<div class="update-div">
				<img src="<%=path%>/images/update_email1.png" >
				<ul class="update-ul">
					<li>
						您的账号是：
						<span id="old_email">${session_mem_user.userName}</span>
					</li>
					<li >
						输入验证码：<input type="button" id="btnSendCode" class="hqyzm fr" value="获取验证码" onclick="dhqyzm()"><div class="form-group  fr"><input type="text" class="text" id="code"/></div>
					</li>
					<li>
						<a href="javascript:void(0);" class="xyb" onclick="emailzhqr()"> 确定</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../include/bottom.jsp" />
	
	
	<div class="tcdiv none"  id="upde_phon_ta">
		<div class="form-group sjsryzm">
			<label class="">验证码：</label>
			<input type="text" class="" name="code" id="syzms"/>
			 <a href="javascript:void(0);" onclick="iphoneyzm()">
			 	<img src="<%=project%>/member/vcode" class="emailyzmimg" id="iphoneyzmimg">
			 </a>
	 	</div>
	 	<div> 
		 	<div class="inputcenter">
		 		<input type="button"  class="call-yzm" value="发送验证码"  onclick="totime()">
		 	</div>
	 	</div>
	 </div>
</body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/reg/validate.js"></script>
<script src="<%=path%>/js/layer.js"></script>
<script>
//判断手机是否填写，如填写进入是否是机器人验证，如没填写弹出提示
function dhqyzm(){
    	var tcsmrz = layer.open({
            type: 1,
            skin: 'a-gg1', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,
            title: '输入验证码',
            shadeClose: true, //开启遮罩关闭
            content: $('#upde_phon_ta')
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

//验证码点击切换
var yzmimgsrc = $('#iphoneyzmimg').attr("src");
var yzmimgsrcnew = yzmimgsrc+"?"
function iphoneyzm(){
	$("#iphoneyzmimg").attr("src",yzmimgsrcnew+Math.random());
}




//判断是否是机器人，验证码
function totime(){
	var useremail = {
			email:$("#old_email").text(),
			code:$('#syzms').val(),
	    };
	var userphone = {
			phone:$("#old_email").text(),
			code:$('#syzms').val(),
	    };
    var val = new validate({
        rules:{
            syzms:"null",
        },
        submitFun:function(){
        	var  userName= $('#old_email').text();
        	var Email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
        	if(Email.test(userName)){
        		var index = layer.load(0, {
        			  shade: [0.1,'#fff'] //0.1透明度的白色背景
        			});
        		$.ajax({
                    url:'mailSend',
                    data:useremail,
                    type:'post',
                    dataType:'json',
                    success:function(msg){
                    	sendMessage();
                    }
                })
        	}else{
        		var index = layer.load(0, {
        			  shade: [0.1,'#fff'] //0.1透明度的白色背景
        			});
        		$.ajax({
        	           url:'smsSend',
        	           data:userphone,
        	           type:'post',
        	           dataType:'json',
        	           success:function(msg){
        	           	sendMessage();
        	           }
        	       })
        	}   
 		}
    })
    
}
//获得时间js开始
var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
function sendMessage() {
    layer.closeAll();
    curCount = count;
    //设置button效果，开始计时
    $("#btnSendCode").attr("disabled", "true");
    $("#btnSendCode").val("输入验证码" + curCount + "秒内");
    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    //向后台发送处理数据
}
//timer处理函数
function SetRemainTime() {
    layer.closeAll();
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $("#btnSendCode").removeAttr("disabled");//启用按钮
        $("#btnSendCode").val("重新发送验证码");
    }
    else {
        curCount--;
        $("#btnSendCode").val("输入验证码" + curCount + "秒内");
    }
}

//手机找回 确认
function emailzhqr(){
	var val = new validate({
        rules:{
        	code:"null",
        },
        submitFun:function(){
        	qrtoSubmit();
        }
    })
}
function qrtoSubmit(){
	var index = layer.load(0, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
		});
	   var data = {
			userName:$("#old_email").text(),
			code:$("#code").val(),
        }; 
		$.ajax({
            url:'confirmCode',
            data:data,
            type:'post',
            dataType:'json',
            success:function(msg){
            	 if(msg.serviceCode==0){
            		layer.closeAll();
           		 	window.location.href ="<%=project%>/member/new_email" + "?token=" + msg.token;
	           	}else{
	           		layer.closeAll();
	           		layer.msg('输入有误');
	           	}  
	         }
        })
}

</script>
</html>

