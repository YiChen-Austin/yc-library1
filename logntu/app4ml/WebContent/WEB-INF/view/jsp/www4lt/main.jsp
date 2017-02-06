<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=projectTitle%></title>
<link rel="icon" href="<%=path%>/img/lt_ico.ico"type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta name="Description" content="龙途" />
<meta name="Keywords" content="漫浪游戏,通行证,密宝,SDO,SNDA,网络游戏,游戏充值，龙途，传奇" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
<link href="<%=path%>/css/idangerous.swiper.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="include/top.jsp" >
	<jsp:param name="tag" value="main" />
</jsp:include>
<!-- Swiper -->
<div class="lt-banner swiper-container ">
     <div class="swiper-wrapper">
        <div class="swiper-slide bg1">
            <a href="<%=project%>/download" class="download"></a>
        </div>
        <div class="swiper-slide bg2">
            <a href="<%=project%>/download" class="download"></a>
        </div>
        <div class="swiper-slide bg3">
            <a href="<%=project%>/download" class="download"></a>
        </div>
        <div class="swiper-slide bg4">
            <a href="<%=project%>/download" class="download"></a>
        </div>
    </div>
    <div class="pagination"></div>
</div>
<jsp:include page="include/index_bottom.jsp" />
<ul class="lfnav">
    <a href="<%=project%>/index">
        <li class="ico1 active"></li>
    </a>
	<a href="<%=project%>/occupation_zs">
        <li class="ico2"></li>
    </a>
    <a href="<%=project%>/activity_list/402881f9589a81ba01589a88dcb50002">
        <li class="ico4"></li>
    </a>
</ul>
<div class="Flag">
	<div class="Flag-top">
		<a href="javascript:void(0);" class="Flag-x" onclick="hide()"></a>
	</div>
	<div class="Flag-con">
		<a href="<%=project%>/download" class="Flag-xz"></a>
		<a href="<%=project%>/game_operation" class="Flag-cj"></a>
		<a href="<%=project%>/game_operation" class="Flag-cz"></a>
	</div>
</div>
<script src="<%=path%>/js/jquery-1.10.1.min.js"></script>
<script src="<%=path%>/js/idangerous.swiper.min.js"></script>
<script>
function hide(){
	$(".Flag").hide();
}
	window.onload=function(){
		$('.Flag-con').css('top','0px');
	}
	var mySwiper = new Swiper('.swiper-container',{
	    pagination: '.pagination',
	    paginationClickable: true,
	    autoplay : 5000,//可选选项，自动滑动
	    mousewheelControl : true,
	    lazyLoading:true,
	    loop:true,
	})

</script>
</html>
