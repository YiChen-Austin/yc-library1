<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<div class="header">
	<div class="w1100 header-top">
		<%--  --%>
			<a href="http://www.cqlongtu.com">
				<img src="<%=path%>/images/personal-ltlogo.png" class="fl">
				
			</a>
			<div class="fr header-topa">
				<a class="header-a">您好，${session_mem_user.userName}</a> | <a href="<%=project%>/member/exit">退出</a>
			</div>
	</div>
	<div class="header-div">
		<div class="w1100">
			<a href ="<%=project%>/member/main"><img src="<%=path%>/images/logo-1.png" class="fl loginimg"></a> 
			<span class="fl"> 个人中心 </span>
			<ul class="fr header-nav">
				<li>
					<a href="">首页</a>
				</li>
				<li class="${param.tag=='center'?'active':''}">
					<a href="<%=project%>/member/main">个人中心</a>
				</li>
				<li>
					<a href="<%=project%>/pay/pay4l">充值中心</a>
				</li>
				<li class="${param.tag=='client'?'active':''}">
					<a href="<%=project%>/member/client">联系客服</a>
				</li>
			</ul>
		</div>
	</div>
</div>