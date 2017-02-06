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
		您当前的位置：首页 > 个人中心 > 修改手机号
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="update_phone" />
		</jsp:include>
		<div class="main-con fl">
			<div class="update-div center">
				<img src="<%=path%>/images/update-three.png" >
				<img src="<%=path%>/images/phone-suc.png" >
			</div>
		</div>
	</div>
	<jsp:include page="../include/bottom.jsp" />
	<div class="tcdiv" id="upde_phon_ta">
		<div class="form-group sjsryzm">
			<label class="">验证码：</label>
			<input type="text" class="" name="code"id="syzms"/>
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
<script language="javascript">
    function time() { 
    	window.location.href="<%=project%>"; 
    } 
       window.setTimeout("time();",3000); 
</script>
</html>


