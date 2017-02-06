<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信支付-请选择支付方式</title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/pay.css" rel="stylesheet" />
<link href="<%=path%>/css/bootstrap.css" rel="stylesheet" />
<link href="<%=path%>/css/layer.css" rel="stylesheet" />

</head>
<body class="">
	<div class="header">
	    <div class="w1100 header-div">
	        <a href ="<%=project%>/member/main"><img src="<%=path%>/images/logo.png" class="fl"></a>
	        <span class="fl">
	            | 收银台
	        </span>
	
	    </div>
	</div>
	<div class="w900 order">
        <div class="o-left">
            <h3 class="o-title">
               	请您及时付款，以便订单尽快处理！单号：${orderSn}
            </h3>
            <p class="o-tips">
			</p>
        </div>
        <div class="o-right">
            <div class="o-price">
                <em>应付金额</em><strong>${orderAmount}</strong><em>元</em>
            </div>
		</div>
		<p class="mb-tip">
			请您在提交订单后<span class="ftx-04">24小时</span>内完成支付，否则订单会自动取消。
		</p>
	</div> 
	       


	<div class="w900  payment">
	<div class="p-w-hd">微信支付</div>
		<div class="p-w-bd">
		    <div class="p-w-box">
				<div class="pw-box-hd">
					<img src="<%=project%>/findPayQr?code=${orderCode}" width="298">
				</div>
				<div class="pw-box-ft">
					<p>请使用微信扫一扫</p>
					<p>扫描二维码支付</p>
				</div>
			</div>
			<div class="p-w-sidebar"></div>
		</div>
		<!-- payment-change 变更支付方式 -->
		<div class="payment-change">
			<a class="pc-wrap" id="reChooseUrl"
				href="/pay.action?orderNo=${payOrderVo.orderNo}&amount=${payOrderVo.tamount}&key=${key}">
				<i class="pc-w-arrow-left">&lt;</i> <strong>选择其他支付方式</strong>
			</a>
		</div>
	</div>
	<div class="w900 mtop30 w">
		<p>
			完成支付后，您可以：<a id="orderItemUrl" target="_blank" href="javascript:x_;"
				clstag="payment|keycount|paymentlink|details">查看订单详情</a>
		</p>
	</div>
</body>
	<%-- <div class="w main">
		
		<div class="payment">
			<!-- 微信支付 -->
			<div class="pay-weixin">
				
				<div class="p-w-bd">
					
				</div>
			</div>
			<!-- 微信支付 end -->
			<!-- payment-change 变更支付方式 -->
			<div class="payment-change">
				<a class="pc-wrap" id="reChooseUrl"
					href="/pay.action?orderNo=${payOrderVo.orderNo}&amount=${payOrderVo.tamount}&key=${key}">
					<i class="pc-w-arrow-left">&lt;</i> <strong>选择其他支付方式</strong>
				</a>
			</div>
			<!-- payment-change 变更支付方式 end -->
		</div>
	</div> 
	 --%>
	<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
	<%-- <script>
	function load_status(){
	    $.ajax({
           url: '/wxPayStaus?orderNo=${orderNo}',
           type: 'post',
           dataType: 'json',
           success: function (json) {
        	   if(json&&json.status=='2'){
					window.location.href='<%=project%>/pay/payR4Succ?order_id='+json.orderNo;
				}else{
					 setTimeout(load_status,5000);
				}
           }
         });
	 setTimeout(load_status,8000);
	}
	</script> --%>
	<script>
	function load_status(){
	    $.ajax({
           url: '/wxPayStaus?orderNo=${orderNo}',
           type: 'post',
           dataType: 'json',
           success: function (json) {
        	   if(json&&json.status=='2'){
					window.location.href='/pay/payR4Succ?order_id='+json.orderNo;
				}else{
					 setTimeout(load_status,5000);
				}
           }
         });
	 }
	 setTimeout(load_status,8000);
	</script>
	<!-- End Google Tag Manager -->

</body>
</html>

