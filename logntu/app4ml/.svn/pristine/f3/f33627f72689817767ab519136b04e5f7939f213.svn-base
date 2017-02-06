<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>找回密码</title>
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
      	<div class="tab">
      		<div class="tit-left fl">找回方式:</div>
      		<div class="fl tit-right">
      			<span class="active">手机找回</span>
      			<span>邮箱找回</span>
      		</div>
      	</div>
      	<div >
      		<ul class="tab-ul">
	      		<li class="tab form-group"> 
	      			<div class="tit-left fl">漫浪账号:</div>
	      			<div class="fl tit-right">
		      			<input type="text" id="phone"/>
	      			</div>
	      		</li>
	      		<li class="tab form-group">
	      			<div class="tit-left fl">获取验证码:</div>
	      			<div class="fl tit-right">
		      			<input type="text" class ="yzm" id="phoneyzm"/>
		      			<input type="button" value="获取验证码" id="btnSendCode" onclick="phoneyzm()" />
	      			</div>
	      		</li>
	      		<li class="tab form-group">
	      			<div class="tit-left fl"></div>
	      			<div class="fl tit-right">
		      			<a href="javascript:void(0);" class="xyb" onclick="phonezhqr()"> 确定</a>
	      			</div>
	      			
	      		</li>
      		</ul>
      		<ul class="tab-ul">
	      		<li class="tab form-group"> 
	      			<div class="tit-left fl">漫浪账号:</div>
	      			<div class="fl tit-right">
		      			<input type="text" id="email" />
	      			</div>
	      		</li>
	      		<li class="tab form-group">
	      			<div class="tit-left fl">获取验证码:</div>
	      			<div class="fl tit-right">
		      			<input type="text" class ="yzm" id="emaildtyzm"/>
		      			<input type="button" id="btnSendCode1" value="获取验证码"  onclick="emailyzm()"/>
	      			</div>
	      		</li>
	      		<li class="tab form-group">
	      			<div class="tit-left fl"></div>
	      			<div class="fl tit-right">
		      			<a href="javascript:void(0);" class="xyb" onclick="emailzhqr()"> 确定</a>
	      			</div>	
	      		</li>
      		</ul>
      	</div>
    </div>
    
	<div id="phone-tc"  class="none">
    	<div class="tab">
	    	<div class="tit-right">
	    		<input type="text" class="yzm fl" name="code" id="syzms"/> 
	    		<a href="javascript:void(0);"style="height: 40px;"  class="fl" onclick="iphoneyzm()">
	    			<img src="<%=project%>/member/vcode" class="emailyzmimg" style="margin-top: 7px;" id="iphoneyzmimg">
	    		</a>
	    	</div>
    	</div>
    	<div class="tab"> 
	    	<div class="tit-right ">
	    		<a href="javascript:void(0);"  class="xyb" onclick="totime()">发送验证码</a>
	    	</div>
    	</div>
   	</div>
   	<div id="email-tc"  class="none">
    	<div class="tab">
	    	<div class="tit-right">
	    		<input type="text" class="yzm fl" name="code" id="emailyam"/> 
	    		<a href="javascript:void(0);"style="height: 40px;"  class="fl" onclick="qiehaun()">
	    			<img src="<%=project%>/member/vcode" class="emailyzmimg" style="margin-top: 7px;" id="emailyzmimg">
	    		</a>
	    	</div>
    	</div>
    	<div class="tab"> 
	    	<div class="tit-right ">
	    		<a href="javascript:void(0);"  class="xyb" onclick="toemailtime()">发送验证码</a>
	    	</div>
    	</div>
   	</div>
    <script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=path%>/js/layer.js"></script>
	<script type="text/javascript" src="<%=path%>/js/reg/validate.js"></script>
	<script>
		$('.tab-ul:gt(0)').hide(); //隐藏大于0
		var stb6 = $('.tit-right span');
		stb6.click(function () {
		    $(this).addClass('active').siblings().removeClass('active');
		    var stb_index3 = stb6.index(this); //index(this)获取对象参数 获取ct的第几个
		    $('.tab-ul').eq(stb_index3).show() //如果ct的和li相等就显示
		        .siblings().hide(); //隐藏掉上一个执行
		});
		// 手机号格式验证
		$("#phone").blur(function(){
            var val = new validate({
                rules:{
                	phone:"mobile",
                },
                submitFun:function(){
                    var user = {
                        Tel:$("#phone").val(),
                    };
                }
            })
        })
        //获取验证码
		function phoneyzm(){
			var val = new validate({
                rules:{
                	phone:"mobile",
                },
                submitFun:function(){
                	toSubmit();
                }
            })
		}
		function toSubmit(){
			layer.open({
	            type: 1,
	            skin: 'phoneyzm', //样式类名
	            closeBtn: 1, //不显示关闭按钮
	            shift: 2,
	            title: '输入验证码',
	            shadeClose: true, //开启遮罩关闭
	            content: $('#phone-tc')
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
		var yzmimgsrc = $('.emailyzmimg').attr("src");
		var yzmimgsrcnew = yzmimgsrc+"?"
		function qiehaun(){
			$("#emailyzmimg").attr("src",yzmimgsrcnew+Math.random());
		}
		function iphoneyzm(){
			$("#iphoneyzmimg").attr("src",yzmimgsrcnew+Math.random());
		}
		
		//判断是否是机器人，验证码
		function totime(){
			var user = {
					phone:$("#phone").val(),
					code:$('#syzms').val(),
			    };
		    var val = new validate({
		        rules:{
		            syzms:"null",
		        },
		        submitFun:function(){
		        	$.ajax({
		                url:'smsSend',
		                data:user,
		                type:'post',
		                dataType:'json',
		                success:function(msg){
		                	console.log(msg.serviceCode);
		                	if(msg.serviceCode==0){
		                		sendMessage();
		                	}else{
		                		layer.closeAll();
		                		layer.msg('输入有误');
		                	} 
		                }
		            })
		            
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
		function phonezhqr(){
			var val = new validate({
                rules:{
                	phone:"mobile",
                	phoneyzm:"null",
                },
                submitFun:function(){
                	qrtoSubmit();
                }
            })
		}
		
		function qrtoSubmit(){
			   var data = {
					userName:$("#phone").val(),
					code:$("#phoneyzm").val(),
		        }; 
				$.ajax({
		            url:'confirmCode',
		            data:data,
		            type:'get',
		            dataType:'json',
		            success:function(msg){
		            	if(msg.serviceCode==0){
		            		 window.location.href ="<%=project%>/member/updatePwd";
	                	}else{
	                		layer.msg('输入有误');
	                	} 
		            }
		        })
		}
		
		
// 邮箱找回
		// 邮箱格式验证
		$("#email").blur(function(){
            var val = new validate({
                rules:{
                	email:"email",
                },
            })
        })	
        //获取验证码
		function emailyzm(){
			var val = new validate({
                rules:{
                	email:"email",
                },
                submitFun:function(){
                	emailtoSubmit();
                }
            })
		}
		function emailtoSubmit(){
			layer.open({
	            type: 1,
	            skin: 'phoneyzm', //样式类名
	            closeBtn: 1, //不显示关闭按钮
	            shift: 2,
	            title: '输入验证码',
	            shadeClose: true, //开启遮罩关闭
	            content: $('#email-tc')
	        });
	        $("#emailyzm").blur(function(){
	            var val = new validate({
	                rules:{
	                	emailyzm:"null",
	                },
	            })
	        });
		}
		//判断是否是机器人，验证码
		function toemailtime(){
			var user = {
					email:$("#email").val(),
					code:$('#emailyam').val(),
			    };
		    var val = new validate({
		        rules:{
		        	emailyam:"null",
		        },
		        submitFun:function(){
		        	$.ajax({
		                url:'mailSend',
		                data:user,
		                type:'post',
		                dataType:'json',
		                success:function(msg){
		                	if(msg.serviceCode==0){
		                		sendemailMessage();
		                	}else{
		                		layer.closeAll();
		                		layer.msg('输入有误');
		                	} 
		                }
		            })
		            
		        }
		    })
		}
		//获得时间js开始
		var InterValObj; //timer变量，控制时间
		var count = 60; //间隔函数，1秒执行
		var curCount;//当前剩余秒数
		function sendemailMessage() {
		    layer.closeAll();
		    curCount = count;
		    //设置button效果，开始计时
		    $("#btnSendCode1").attr("disabled", "true");
		    $("#btnSendCode1").val("输入验证码" + curCount + "秒内");
		    InterValObj = window.setInterval(SetRemainTime1, 1000); //启动计时器，1秒执行一次
		    //向后台发送处理数据
		}
		//timer处理函数
		function SetRemainTime1() {
			 
		    if (curCount == 0) {
		        window.clearInterval(InterValObj);//停止计时器
		        $("#btnSendCode1").removeAttr("disabled");//启用按钮
		        $("#btnSendCode1").val("重新发送验证码");
		    }
		    else {
		        curCount--;
		        $("#btnSendCode1").val("输入验证码" + curCount + "秒内");
		    }
		}
		//邮箱找回 确认
		function emailzhqr(){
			var val = new validate({
                rules:{
                	email:"email",
                	emaildtyzm:"null",
                },
                submitFun:function(){
                	emalitoSubmit();
                }
            })
		}
		
		function emalitoSubmit(){
			var user = {
				userName:$("#email").val(),
				code:$("#emaildtyzm").val(),
	        }; 
			$.ajax({
	            url:'confirmCode',
	            data:user,
	            type:'get',
	            dataType:'json',
	            success:function(msg){
	            	console.log(msg.serviceCode);
	            	if(msg.serviceCode==0){
	            		 window.location.href ="<%=project%>/member/updatePwd"; 
                	}else{
                		layer.msg('输入有误');
                	} 
	            }
	        })
		}
	</script>
</body>
</html>

