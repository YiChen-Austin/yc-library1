<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>龙途在线充值</title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"type="image/x-icon" />
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
				<li>
					<a href="<%=project%>/member/main">个人中心</a>
				</li>
				<li class="active">
					<a href="<%=project%>/pay/pay4nl">在线充值</a>
				</li>
				<li >
					<a href="<%=project%>/pay/pay4l">账户充值</a>
				</li>
			</ul>
		</div>
	</div>
</div>
	<div class="main">
	
		<div class="w1100 pay-com">
			<div class="pay-comleft fl">
				<h1>龙途在线简介</h1>
				<span> 10元宝/1元 </span>
				<p>《龙途》是有漫浪游戏倾力打造，采用 Unity引擎开发的一款大型多人在线角 色扮演游戏（MMORPG)网络游戏。它延
					续了传奇系列的玩法与强PK特性，传承 了战法道的职业体系，保留了经典版本 的原汁原味，让玩家在激情中享受快感， 在热血中重温回忆。</p>
				<div class="pay-bd">
					<input name="checkbox" class="ty" type="checkbox" id="tyiphone" checked>同意 <a href="<%=project%>/member/agreement"  target="_blank"  class="xy">龙途玩家条例</a>
				</div>
			</div>
			<div class="pay-comright fl">
				<div class="pay-div">
					<div class="pay-bd">
						<span class="fl"> 充值账号：</span> <input type="text"placeholder="输入账号" name="zh" value="${session_mem_user.userName}" id="payid"onchange="payID()" class="fl" /> 
						<label id="payidlab"class="lab"><i></i><span></span></label>
					</div>
					<div class="pay-bd">
						<span class="fl"> 选择大区：</span>
						<div id="select_zone">
						</div>
						<label id="xzdq"class="lab"><i></i><span></span></label>
					</div>
					<div class="pay-bd jediv">
						<span class="fl"> 充值数量：</span> <label class="active z czje"
							num="1000" restnum="">1000</label> <label class="czje z"
							num="4000" restnum="4000">4000</label> <label class="czje z"
							num="5000" restnum="5000">5000</label> 
							<input type="text"placeholder="其他数量" onchange="$(this).attr('num',$(this).val())" value="" id="input_num" num="" class="je czje num" />
						<p>金额10元，获得100元宝，至少100元宝</p>
					</div>
					<div class="pay-bd zffs">
						<span class="fl"> 支付方式：</span> 
						<label restnum="0" style="display: none;" id="lb_label_nl" data-b="0">龙币</label>
						<label class="active" restnum="1">支付宝</label>
						<label restnum="2">微信</label> 
						<label restnum="3">银行卡</label>
					</div>
					<ul class="zffs-ul">
					   <li class="zffs-li none">
							<div class="pay-bd">
								<span class="fl"> 应付金款：</span>
								<div class="fl">
									<span class="form-amount" id="lbyf">100</span>元
								</div>
							</div>
							<div class="pay-bd" id="lb-error">
								<span class="fl"></span>
								<div class="fl">
									<label class="lab faerror" style="display:inline-block;"><i></i><span>您的账户余额不足，请先充值龙币，或试试其它支付方式。</span></label>
								</div>
							</div>
							<div class="pay-bd">
								<input type="button" class="ljzf"value="立即支付"  onclick="lbpay()"/>
							</div>
						</li>
						<li class="zffs-li">
							<div class="pay-bd">
								<span class="fl"> 应付金款：</span>
								<div class="fl">
									<span class="form-amount" id="yf">100</span>元
								</div>
							</div>
							<div class="pay-bd">
								<input type="button" class="ljzf"value="立即支付"  onclick="alipay()"/>
							</div>
						</li>
						<li class="zffs-li">
							<div class="pay-bd">
								<span class="fl"> 应付金款：</span>
								<div class="fl">
									<span class="form-amount" id="yf">100</span>元
								</div>
							</div>
							<div class="pay-bd">
								<input type="button" class="ljzf"value="立即支付"  onclick="wxpay()"/>
							</div>
						</li>
						<li class="zffs-li">
							<div class="pay-bd">
								<span class="fl"> 应付金款：</span>
								<div class="fl">
									<span class="form-amount">100</span>元
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
	<!-- 龙币支付from提交 -->
	<form method="post" action="" id="lb-pay" name="lb-pay" target="_blank">
		<input value="" id="lb-userName" type="hidden" name="userName" />
		<input value="" id="lb-serverCode" type="hidden" name="serverCode" />
		<input value="" id="lb-roleCode" type="hidden" name="roleCode" />
		<input value="" id="lb-goodsAmount" type="hidden" name="goodsAmount" />
		<input value="" id="lb-orderAmount" type="hidden" name="orderAmount" />
	</form> 
	<!-- 支付 form提交-->
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
<script src="<%=path%>/js/pay4nl.js"></script>
<script>
//判断是否登录
var name = $('.header-a').text();
if(name==''){
	$('.header-a').html('您好，请登录');
	$('.header-a').attr('href','<%=project%>/member/login');
	$('.tc').css('display','none');
}else{
	$('.header-a').html('您好，${session_mem_user.userName}')
	$('.tc').css('display','inline-block');
}
</script>
</html>

