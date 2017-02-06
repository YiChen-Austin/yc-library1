<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>


<ul class="main-left fl main-nav">
	<div>个人信息</div>
	<li class="${param.tag=='main'?'active':''}">
		<a href="<%=project%>/member/main">
			<i class="gezl"></i> 个人资料
		</a>
	</li>
	<li class="${param.tag=='passwd'?'active':''}">
		<a href="<%=project%>/member/passwd">
			<i class="zhaq"></i> 账户安全
		</a>
	</li>
	<li class="${param.tag=='recharge'?'active':''}">
		<a href="<%=project%>/member/recharge">
			<i class='xfcz'></i> 充值消费
		</a>
	</li>
	<li class="${param.tag=='addiction'?'active':''}">
		<a href="<%=project%>/member/addiction">
			<i class="fcm"></i> 防沉迷
		</a>
	</li>
	<div>我的推广</div>
	<li class="${param.tag=='href'?'active':''}">
		<a href="<%=project%>/member/href">
			<i class='tglj'></i>推广链接
		</a>
	</li>
	<li class="${param.tag=='extension'?'active':''}">
		<a href="<%=project%>/member/extension">
			<i class='hytg'></i>会员推广
		</a>
	</li>
	<li class="${param.tag=='payrecordCommission'?'active':''}">
		<a href="<%=project%>/member/payrecordCommission">
			<i class='tgly'></i>推广收益
		</a>
	</li>
	<li  class="${param.tag=='bank'?'active':''}">
		<a href="<%=project%>/member/bank">
			<i class='bdyhk'></i>绑定银行卡
		</a>
	</li>
	<li  class="${param.tag=='update_phone'?'active':''}">
		<a href="<%=project%>/member/update_phone">
			<i class='bgsj'></i>绑定手机
		</a>
	</li>
	<div>客服中心</div>
	<li class="${param.tag=='client'?'active':''}">
		<a href="<%=project%>/member/client">
			<i class='lxkf'></i> 联系客服
		</a>
	</li>
	<li class="${param.tag=='feedback'?'active':''}">
		<a href="<%=project%>/member/feedback">
			<i class='wyfk'></i> 我要反馈
		</a>
	</li>
</ul>

<%-- <div class="main-left fl">
	<div class="main-header">我的账户</div>
	<div class="main-tou">
		<img src="<%=project%>/${session_mem_user.portrait}">
		<p>${session_mem_user.userName}</p>
		<a href="<%=project%>/pay/pay4l">充值</a><a>|</a><a href="<%=project%>/member/recharge">充值查询</a>
		
		
	</div>
	<ul class="main-nav">
		<li class="${param.tag=='main'?'active':''}">
			<a >
				<i></i> 首　　页
			</a>
		</li>
		<li class="${param.tag=='base'?'active':''}">
			<a href="<%=project%>/member/base">
				<i></i> 个人资料
			</a>
		</li>
		<li class="${param.tag=='passwd'?'active':''}">
			<a href="<%=project%>/member/passwd">
				<i></i> 账户修改
			</a>
		</li>
		<li class="${param.tag=='recharge'?'active':''}">
			<a href="<%=project%>/member/recharge">
				<i></i> 充值消费
			</a>
		</li>
		<li class="${param.tag=='client'?'active':''}">
			<a href="<%=project%>/member/client">
				<i></i> 联系客服
			</a>
		</li>
		<li class="${param.tag=='feedback'?'active':''}">
			<a href="<%=project%>/member/feedback">
				<i></i> 我要反馈
			</a>
		</li>
		<li class="${param.tag=='addiction'?'active':''}">
			<a href="<%=project%>/member/addiction">
				<i></i> 防沉迷
			</a>
		</li>
	</ul>
</div> --%>