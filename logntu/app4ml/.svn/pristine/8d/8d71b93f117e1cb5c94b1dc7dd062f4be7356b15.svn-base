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
		您当前的位置：首页 > 个人中心 > 推广链接
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="href" />
		</jsp:include>
		<div class="main-con fl">
			<div class="spread">
	            <div class="fl spread-left">
	                <div class="spread-title">我的分享链接：<p id="url"><%=project%>/member/spread/${session_mem_user.mySpread}</p></div>
	                <div class="color999">
	                    分享到：
						<a href="javascript:;"  class="jiathis_button_weixin ico"><i class="wx"></i></a> 
	                    <a href="javascript:;"  onclick="jiathis_sendto('tsina');return false;" class="c1a ico"><i class="wb"></i></a>
	                    <a onclick="jiathis_sendto('cqq');return false;" href="javascript:;"  class="jiatitle ico"><i class="qq"></i></a>
	                    <a href="javascript:;" onclick="jiathis_sendto('qzone');return false;" class="jiatitle ico"><i class="qzone"></i></a>
	                </div>
	                <div class="color999">
	                    复制链接：<a href="javascript:;"  class="ico fza"  id="copyBtn"><i class="fz"></i></a>
	                </div>
	            </div>
	            <div class="fl spread-right">
	                <img src="<%=path%>/images/wbgzh.jpg">
	            </div>
        	</div>
		</div>
	</div>
	<jsp:include page="../include/bottom.jsp" />
</body>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1" charset="utf-8"></script>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/validate.js"></script>
<script src="<%=path%>/js/layer.js"></script>
<script src="<%=path%>/js/jquery.zclip.min.js"></script>
<script type="text/javascript">
	$('#copyBtn').zclip({
	    path: "<%=path%>/js/ZeroClipboard.swf",
	    copy: function(){
	        return $('#url').text();
	}
	});
</script>
</html>

