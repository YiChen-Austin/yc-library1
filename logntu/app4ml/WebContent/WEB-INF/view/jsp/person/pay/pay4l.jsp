<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>龙币账户充值</title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"
type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/pay.css" rel="stylesheet" />
<link href="<%=path%>/css/bootstrap.css" rel="stylesheet" />
<link href="<%=path%>/css/layer.css" rel="stylesheet" />
</head>
<body>
    <div id="src" class='none'><%=project%></div>
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
			<span class="fl"> 充值中心 </span>
			<ul class="fr header-nav">
				<li>
					<a href="">首页</a>
				</li>
				<li class="${param.tag=='center'?'active':''}">
					<a href="<%=project%>/member/main">个人中心</a>
				</li>
				<li>
					<a href="<%=project%>/pay/pay4nl">在线充值</a>
				</li>
				<li  class="active">
					<a href="<%=project%>/pay/pay4l">账户充值</a>
				</li>
			</ul>
		</div>
	</div>
</div>
	<div class="main">
		<div class="w1100 pay-com">
			<div class="pay-comleft fl">
				<h1>龙币简介</h1>
				<span> 10龙币/10元 </span>
				<p>龙币可以用于购买漫浪游戏所有包月服务和游戏道具。
					龙币仅能用于兑换漫浪游戏公司直接运营的产品和服务，不能兑换现金，不能进行转账交易，不能兑换漫浪游戏公司体系外的产品和服务。
				</p>
				
				<div class="pay-bd">
					<input name="checkbox" class="ty" type="checkbox" id="tyiphone" checked>同意 <a href="<%=project%>/member/agreement" target="_blank"class="xy">龙途玩家条例</a>
				</div>
			</div>
			<div class="pay-comright fl">
				<div class="pay-div">
					<div class="pay-bd">
						<span class="fl"> 充值账号：</span> <input type="text"placeholder="输入账号" name="zh" value="${session_mem_user.userName}" id="payid"onchange="payID()" class="fl" /> 
						<label id="payidlab"class="lab"><i></i><span></span></label>
					</div>
					<!-- <div class="pay-bd">
						<span class="fl"> 选择大区：</span>
						<div id="select_zone">
						</div>
						<label id="xzdq"class="lab"><i></i><span></span></label>
					</div> -->
					<div class="pay-bd jediv">
						<span class="fl"> 充值数量：</span> 
							<input type="text"placeholder="其他数量" value="60" onchange="$(this).attr('num',$(this).val())" id="input_num" num="" class="je active czje num " />
						<p>金额10元，获得10龙币</p>
					</div>
					<div class="pay-bd zffs">
						<span class="fl"> 支付方式：</span> <label class="active" restnum="1">支付宝</label>
						<label restnum="2">微信</label> <label restnum="3">银行卡</label>
					</div>
					<ul class="zffs-ul">
						<li class="zffs-li">
							<div class="pay-bd">
								<span class="fl"> 应付金款：</span>
								<div class="fl">
								  <span class="form-amount " id="yf">60</span>元
								</div>
							</div>  
							<div class="pay-bd">
                               <input type="button" class="ljzf"value="立即支付"  onclick="alipay()">
                         	</div>
						</li>
						<li class="zffs-li">
							<div class="pay-bd">
								<span class="fl"> 应付金款：</span>
								<div class="fl">
								  <span class="form-amount " id="yf">60</span>元
								</div>
							</div>  
							<div class="pay-bd">
                               <input type="button" class="ljzf"value="立即支付"  onclick="wxpay()">
                         	</div>
						</li>
						<li class="zffs-li">
							<div class="pay-bd">
								<span class="fl"> 应付金款：</span>
								<div class="fl">
									<span class="form-amount">60</span>元
								</div>
							</div>
							<div class="pay-bd">
								<input type="button" class="ljzf"value="立即支付"  onclick="unionpay()"/>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<form method="post" action="<%=project%>" id="pay_action" name="pay_action" target="_blank">
		<input value="" id="userName" type="hidden" name="userName" />
		<input value="" id="serverCode" type="hidden" name="serverCode" />
		<input value="" id="roleCode" type="hidden" name="roleCode" />
		<input value="" id="goodsAmount" type="hidden" name="goodsAmount" />
		<input value="" id="orderAmount" type="hidden" name="orderAmount" />
	</form> 
	<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/layer.js"></script>
<script src="<%=path%>/js/jquery.cxselect.js"></script>
<script src="<%=path%>/js/pay4l.js"></script>
</html>

