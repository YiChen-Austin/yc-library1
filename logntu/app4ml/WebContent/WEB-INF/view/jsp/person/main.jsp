<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/head.jsp"%>
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

	<jsp:include page="include/top.jsp" >
		<jsp:param name="tag" value="center" />
	</jsp:include>
	<div class="w1100 mtb20 color999">
		您当前的位置：首页 > 个人中心 > 个人资料
	</div>
	<div class="main w1100">
		<jsp:include page="include/left.jsp">
			<jsp:param name="tag" value="main" />
		</jsp:include>
		<div class="main-center fl">
			
			<%-- <ul class="main-title">
				<div class="tx fl">
					<img src="<%=project%>/${session_mem_user.portrait}" id="touxiang">
					<p>
						<a href="javascript:void(0)" onclick="xgtx()">修改头像</a>
					</p>
				</div>
				<div>
					<span>账号：${session_mem_user.userName}</span> <a href="javascript:void(0)"
						onclick="xgPassword()">修改密码</a>
				</div>
				<li><span id="balance">龙途币余额：</spn></strong></span> <a href="<%=project%>/pay4l">充值</a><a>|</a><a href="<%=project%>/member/recharge">充值查询</a>
				</li>
				<li><span id="accumulated_point">当前可用积分：</span></li>
			</ul> --%>
			
			
			<div class="main-header"></div>
			<div class="tx fl">
			<img src="<%=project%>/${session_mem_user.portrait}" id="touxiang">
					<p>
						<a href="javascript:void(0)" onclick="xgtx()">修改头像</a>
					</p>
			</div>
			<div class="zl fl">
				<div class="zl-tit">
					<h1 class="fl">基础资料</h1>
					<!-- <a class="fr" href="javascript:void(0)" onclick="bj()">编辑</a> -->
				</div>
				<ul>
					<li>账户：<span>${session_mem_user.userName}</span></li>
					<li>性别：<span id="gender">${session_mem_user.gender}</span></li>
					<li>生日：<span id="sf">${session_mem_user.birthday}</span></li>
					
				</ul>
				<div class="zl-tit">
					<h1 class="fl">高级资料</h1>
				</div>
				<ul>
					<li>真实姓名：<span id="realName">${session_mem_user.realNameEX}</span></li>
					<li>身份证号：<span id="">${session_mem_user.idCardEx}</span></li>
				</ul>
			</div>
		</div>
		<div class="main-right fl">
			<ul class="qd-header">
				<li class="fl jr borltnone"><span>今日积分</span>
					<p id="todaySign">0</p></li>
				<li class="fl lj"><span>累计积分</span>
					<p id="todaySigns">0</p></li>
			</ul>
			<div class="qd-button qd-div">
				<a href="javascript:void(0);" id="qd" onclick="qd()"><i></i>签到领积分</a>
			</div>
			<div class="qd-div">
				<ul class="qdxq">
					<li><i></i> <span>签到领积分</span></li>
					<a class="fr" href="">查看积分明细 +</a>
				</ul>
			</div>
		</div>
	</div>
	<ul class="form-horizontal register-con sj active" style="display:none;" id="formImg"> 
			<li class="form-group1">
				<span id="spantext"></span> 
				<div id="upfileResult"></div>
			</li>  
			<li class="form-group">
				<form id="uploadForm" enctype="multipart/form-data">
					<input type="file" name="upfile" id="uploadImage"class="fileinput fileinputs" style="top:249px; height: 42px"/>
				</form>
				<label class="feedback-sca feedback-scas">选择上传图片</label> 
				<input type="button" class="btn tj fr dl qr" onclick="qr()" id="ss" value="确认">
			</li> 
		</ul>
	<jsp:include page="include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
<script src="<%=path%>/js/validate.js"></script>
<script src="<%=path%>/js/layer.js"></script>
<script>

/*修改头像*/
function xgtx() {
	layer
		.open({
			type : 1,
			skin : 'a-gg', //样式类名
			closeBtn : 1, //不显示关闭按钮
			shift : 2,
			title : '修改头像',
			shadeClose : true, //开启遮罩关闭
			content : $('#formImg')
		});
	if (typeof FileReader === 'undefined') {
		$('#upfileResult').css('display', 'none')
		$("#uploadImage").on("change", function() {
			var s = $("#uploadImage").val();
			$("#spantext").text(s);
		})
	} else {
		$("#uploadImage").on(
				"change",
				function() {
					var files = !!this.files ? this.files : [];
					if (!files.length || !window.FileReader)
						return;
					if (/^image/.test(files[0].type)) {
						var reader = new FileReader();
						reader.readAsDataURL(files[0]);
						reader.onloadend = function() {
							$("#upfileResult").css("background-image",
									"url(" + this.result + ")");
						}
					}
				});
	}
}
function qr() {
	$("#uploadForm").ajaxSubmit({
		type : "POST",
		datatype : "json",
		async: false,  
        cache: false,  
        contentType: false,  
        processData: false, 
		url : '<%=project%>/member/uUportrait',
		success : function(returndata) {
			layer.closeAll();
			var a = jQuery.parseJSON(returndata);
			console.log(a);
			$('#touxiang').attr('src','<%=project%>'+a.url);
		},
	 	error: function (returndata) {  
	 		layer.closeAll();
            alert(returndata);  
        }
	});	
}

/*签到*/
 $(function(){ 
    $.ajax({
        url:'<%=project%>/member/getSign',
        type: 'post',
        dataType: 'json',
        success: function (msg) {
        	$("#todaySign").html((msg.sign.continuous)*10);
        	$("#todaySigns").html((msg.sign.signs)*10);
        }
    })
})
 //点击签到
function qd(){
	 $.ajax({
	        url:'<%=project%>/member/signin',
	        type: 'post',
	        dataType: 'json',
	        success: function (msg) {
	        	console.log(msg); 
	        	if(msg.sign==null){
	        		layer.msg('已经签到过了');
	        	}else{
	        		layer.msg('签到成功');
	        		$("#todaySign").html((msg.sign.continuous)*10);
	            	$("#todaySigns").html((msg.sign.signs)*10);
	        	}
	        }
	    })
}
//查询余额
$(function(){ 
    $.ajax({
        url:'<%=project%>/pay/checkBalance',
        type: 'post',
        dataType: 'json',
        success: function (json) {
        	$("#balance").append(json.desposit.balance);
        	$("#accumulated_point").append(json.desposit.accumulatedPoint);
        	
        }
    })
})

//性别
var gender = $('#gender').text();
if(gender==1){
	$("#gender").html('女');
}else{
	$("#gender").html('男');
}

</script>
</html>

