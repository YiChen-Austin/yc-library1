<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>找不到该网页</title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"
type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
</head>
<body>
	<div class="header">
		<div class="w1100 header-div1">
			<a href ="<%=project%>/member/main"><img src="<%=path%>/images/logo.png" class="fl loginimg"></a>
			<div class="fr dh">
				<i class="fl"></i>023-63110535
			</div>	
		</div>
	</div>
    <div class=" w800 center">
       <img src="<%=path%>/images/404.png">
    </div>
    <script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
    <script language="javascript">
	    function time() { 
	    	window.location.href="<%=project%>"; 
	    } 
        window.setTimeout("time();",5000); 
	</script>
</body>
</html>
