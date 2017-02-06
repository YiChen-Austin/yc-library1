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
		您当前的位置：首页 > 个人中心 > 绑定银行卡
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="bank" />
		</jsp:include>
		<div class="main-con fl">
			<div class="spread">
            <ul class="bank">
                <li class="bank-li">
                    <div class="fl name">
                        持卡人：
                    </div>
                    <div class="fl form-group">
                        <input type="text" class="inputtext"  id="cardOwner"/>
                    </div>
                </li>
                <li class="bank-li">
                    <div class="fl name ">
                        银行名称：
                    </div>
                    <div class="form-group">
                    <div class="bankname fl " name="nice-select" >
                    		<input type="text" class="input-bank" id="bankRegName" bankCode=""  value="" placeholder="请选择银行"  readonly>
	                        <ul id="select_bank">
	                        </ul>
                        </div>
                    </div>
                </li>
                <li class="bank-li">
                    <div class="fl name">
                        银行卡号：
                    </div>
                    <div class="fl form-group">
                        <input type="text"  class="inputtext"  id="bankCardNo"/>
                    </div>
                </li>
                <li class="bank-li">
                    <div class="fl name">
                        开户支行：
                    </div>
                    <div class="fl form-group">
                        <input type="text" class="inputtext" id="bankRegSub"/>
                        <span class="color999">例：中国工商银行建新北路支行</span>
                    </div>
                </li>
                <li class="bank-li">
                    <div class="fl name">
                        手机号码：
                    </div>
                    <div class="fl form-group">
                        <input type="text" class="inputtext"  id="phone"/>
                    </div>
                </li>
                <li class="bank-li">
                    <div class="fl name">
                        验证码：
                    </div>
                    <div class="fl form-group">
                        <input type="text" class="input-code"  id="code"/>
                        <input type="button" id="btnSendCode" onclick='dhqyzm()'   class="obtain-code" value="获取验证码"></input>
                    </div>
                </li>
                <li class="bank-li">
                    <div class="fl name"></div>
                    <div class="fl">
                       <button onclick="bangding()">确认绑定</button>
                    </div>
                </li>
            </ul>
        </div>
		</div>
	</div>
	
	<div class="tcdiv none" id="upde_phon_ta">
		<div class="form-group sjsryzm">
			<label class="">验证码：</label>
			<input type="text" class="" name="code"id="syzms"/>
			 <a href="javascript:void(0);" onclick="iphoneyzm()">
			 	<img src="<%=project%>/member/vcode" class="emailyzmimg" id="iphoneyzmimg">
			 </a>
	 	</div>
	 	<div> 
		 	<div class="inputcenter">
		 		<input type="button"  class="call-yzm"  value="发送验证码"  onclick="totime()">
		 	</div>
	 	</div>
	 </div>
	<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/reg/validate.js"></script>
<script src="<%=path%>/js/layer.js"></script>
<script>
(function () {
	load_server_data();
})();
/* 加载银行信息 */
function load_server_data() {
        $.ajax({
            url: 'findBanks',
            type: 'post',
            dataType: 'json',
            success: function (json) {
            	console.log(json);
                	var isNext = false;
                    var _html = '';
                    $.each(json, function (i, item) {
                    	_html += '<li  bankCode='+item.bankCode+'>'+item.bankName + '</li>';
                    });
                    $("#select_bank").append(_html); 
                    select()
            }
        })
    }	
function dhqyzm(){
    if($('#phone').val()==''){
    	layer.msg('手机号不能为空');
    }else{
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
}
//验证码点击切换
var yzmimgsrc = $('#iphoneyzmimg').attr("src");
var yzmimgsrcnew = yzmimgsrc+"?"
function iphoneyzm(){
	$("#iphoneyzmimg").attr("src",yzmimgsrcnew+Math.random());
}
//判断是否是机器人，验证码
function totime(){
	var userphone = {
			phone:$("#phone").val(),
			code:$('#syzms').val(),
	    };
    var val = new validate({
             rules:{
            	 syzms:"null",
             },
             submitFun:function(){
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
	/* 绑定银行卡 */
	function bangding(){
	            var val = new validate({
	                rules:{
	                	cardOwner:"name1",
	                	bankRegName:"bankName",
	                	bankCardNo:"bank",
	                	bankRegSub:"null",
	                	phone:"mobile",
	                	code:"null",
	                },
	                submitFun:function(){
	                    toSubmit();
	                }
	            })
	        }
	function  toSubmit(){
		var data = {
			cardOwner:$('#cardOwner').val(),
			bankRegName:$('#bankRegName').val(),
			bankRegCode:$('#bankRegName').attr('bankcode'),
			bankCardNo:$('#bankCardNo').val(),
			bankRegSub:$('#bankRegSub').val(),
			//phone:$('#phone').val(),
			code:$('#code').val()
		};
		 $.ajax({
		        url:'bindcard',
		        type:'POST',
		        dataType:'json',
		        data:data,
		        success:function(msg){
		            if(msg.serviceCode==0){
		            	layer.msg('成功');
		            }else{
		            	layer.msg('失败');
		            }
		        }
		    })
	}
	function select(){
		$('[name="nice-select"]').click(function(e){
		    $('[name="nice-select"]').find('ul').hide();
		    $(this).find('ul').show();
		    e.stopPropagation();
		});
		$('[name="nice-select"] li').hover(function(e){
		    $(this).toggleClass('on');
		    e.stopPropagation();
		});
		$('[name="nice-select"] li').click(function(e){
		    var val = $(this).text();
		    var bankCode = $(this).attr('bankCode');
		    $(this).parents('[name="nice-select"]').find('input').val(val);
		    $(this).parents('[name="nice-select"]').find('input').attr('bankCode',bankCode);
		    $('[name="nice-select"] ul').hide();
		    e.stopPropagation();
		});
		$(document).click(function(){
		    $('[name="nice-select"] ul').hide();
		});
	}
</script>
</html>

