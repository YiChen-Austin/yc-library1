<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>活动中心</title>
<link rel="icon" href="<%=path%>/img/lt_ico.ico"type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta name="Description" content="龙途" />
<meta name="Keywords" content="漫浪游戏,通行证,密宝,SDO,SNDA,网络游戏,游戏充值，龙途，传奇" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="../include/top.jsp" >
	<jsp:param name="tag" value="announ" />
</jsp:include>
<!-- Swiper -->
<div class="activity-banner"></div>
<div class="w1200 activity-con">
    <div class="activity-tit">
       ${articleBean.title}
    </div>
    <div class="activity-date">
        <div class="fl">
            首页 > 活动中心 >${articleBean.title}
        </div>
        <div class="fr">
            发布时间：${articleBean.publishTime}
        </div>
    </div>
    <div class="content">
        ${articleBean.content}
    </div>
</div>
<jsp:include page="../include/bottom.jsp" />

</body>
</html>

