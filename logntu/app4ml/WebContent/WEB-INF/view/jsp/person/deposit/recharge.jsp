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
		您当前的位置：首页 > 个人中心 > 充值消费
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="recharge" />
		</jsp:include>
		<div class="main-con fl">
			<div class="pay-div tudj">
				龙币：<span class="djs" id="balance"></span>点
				<div class="dj-div">
					温馨提示：
					<p>1、龙币支持大部分游戏内直接使用或兑换游戏专用货币使用；</p>
					<p>2、余额查询结果仅为参考，请以游戏内实际显示余额为准。</p>
				</div>
			</div>
			<ul class="pay-top">
				<li class="fl active">消费记录</li>
				<li class="fl" onclick="czjl()">充值记录</li>
				<li class="fl">提现记录</li>
			</ul>
			<div>
				<div class="pay-div pay-s">
					<div class="jl">
						<label class="active">全部</label>
					</div>
					<div>
						<div  class="bd">
							<table class="table table-striped tab-border" >
								<thead>
									<tr>
										<th>时间</th>
										<th>数量</th>
										<th>金额</th>
										<th>状态</th>
									
									</tr>
								</thead>
								<tbody id='xfjl'>
								</tbody>
							</table>
							<div class="jlnull" id="xfjlnull">	
							</div>
						</div>
					</div>
				</div>
				<div class="pay-div pay-s">
					<div class="jl jl1">
						<label class="active">全部</label> 
					</div>
					<div>
						<div class="bd1">
							<table class="table table-striped tab-border">
								<thead>
									<tr>
										<th>时间</th>
										<th>数量</th>
										<th>金额</th>
										<th>状态</th>
										<th>充值方式</th>
									</tr>
								</thead>
								<tbody id="cz">	
								</tbody>	
							</table>
							<div class="jlnull" id="cznull">		
							</div>
						</div>
					</div>
				</div>
				<div class="pay-div pay-s">
					<div class="jl jl1">
						<label class="active">全部</label> 
					</div>
					<div>
						<div class="bd1">
							<table class="table table-striped tab-border">
								<thead>
									<tr>
										<th>时间</th>
										<th>数量</th>
										<th>金额</th>
										<th>状态</th>
										<th>充值方式</th>
									</tr>
								</thead>
								<tbody id="tx">	
								
								</tbody>	
							</table>
							<div class="jlnull" id="cznull">		
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/validate.js"></script>
<script src="<%=path%>/js/layer.js"></script>
<script>
//充值记录
function czjl(){
	$('#cznull').html("<div id='jaz1'>加载中...</div>");
	$.ajax({
        url:'<%=project%>/order/listConsume',
        type: 'post',
        dataType: 'json',
        success: function (json) {
        	$('#cz').empty();
       		if(json.orderList.length == 0){
       			
       			$('#cznull').html("没有充值记录，赶快去充值吧");
       			$('#jaz1').css('display','none');
       		}else{
       			for (var i = 0; i < json.orderList.length; i++) { 
    	       		if(json.orderList[i].status=='20'){
    	        		var zs = '支付成功';
    	        	}
    	       		if(json.orderList[i].status=='11'){
    	       			var zs = '支付中';
    	        	}
    	       		if(json.orderList[i].status=='10'){
    	       			var zs = '待支付';
    	        		
    	        	}
    	        	$('#cz').append(
    	     			"<tr>"+
    	     				"<td>"+json.orderList[i].addTime+"</td>"+
    	     				"<td>"+json.orderList[i].goodsAmount+"</td>"+
    	     				"<td>"+json.orderList[i].orderAmount+"</td>"+
    		     			"<td class='ztval'>"+zs+"</td>"+
    	     				"<td>"+json.orderList[i].type+"</td>"+
    	    			"</tr>"	
    	        	)
    	        	$('#jaz1').css('display','none');
            	}; 
            	
       		}
       	}
    })
}


//消费记录
$(function(){ 
	$('#xfjlnull').html("<div id='jaz'>加载中...</div>");
    $.ajax({
        url:'<%=project%>/order/listRecharge',
        type: 'post',
        dataType: 'json',
        success: function (json) {
       		console.log(json.orderList.length);
       		
       		if(json.orderList.length==0){
       			layer.close(dengdai1);
       			$('#xfjlnull').html("没有消费记录，赶快去消费吧");
       			$('#jaz').css('display','none');
       		}else{
       			for (var i = 0; i < json.orderList.length; i++) { 
    	       		if(json.orderList[i].status=='20'){
    	        		var zs = '支付成功';
    	        		
    	        	}
    	       		if(json.orderList[i].status=='11'){
    	       			var zs = '支付中';
    	        	}
    	       		if(json.orderList[i].status=='10'){
    	       			var zs = '待支付';
    	        		
    	        	}
    	        	$('#xfjl').append(
    	     			"<tr>"+
    	     				"<td>"+json.orderList[i].addTime+"</td>"+
    	     				"<td>"+json.orderList[i].goodsAmount+"</td>"+
    	     				"<td>"+json.orderList[i].orderAmount+"</td>"+
    		     			"<td class='ztval'>"+zs+"</td>"+
    	    			"</tr>"	
    	        	)
    	       		$('#jaz').css('display','none');
            	}; 
       		}
       	}
    })
})

$(function(){ 
    $.ajax({
        url:'<%=project%>/pay/checkBalance',
        type: 'post',
        dataType: 'json',
        success: function (json) {
        	var balance = json.desposit.balance;
        	$('#balance').append(balance);
        }
    })
})
var stb6=$('.jl label');
stb6.click(function(){
    $(this).addClass('active')
            .siblings().removeClass('active');
})
$('.pay-s:gt(0)').hide();//隐藏大于0
var stb1=$('.pay-top li');
stb1.click(function(){
    $(this).addClass('active')
            .siblings().removeClass('active');
    var stb_index4=stb1.index(this);//index(this)获取对象参数 获取ct的第几个
    $('.pay-s').eq(stb_index4).show()//如果ct的和li相等就显示
            .siblings().hide();//隐藏掉上一个执行
});

$('.bd:gt(0)').hide();//隐藏大于0
var stb6=$('.jl label');
stb6.click(function(){
    $(this).addClass('active')
            .siblings().removeClass('active');
    var stb_index3=stb6.index(this);//index(this)获取对象参数 获取ct的第几个
    $('.bd').eq(stb_index3).show()//如果ct的和li相等就显示
            .siblings().hide();//隐藏掉上一个执行
});
</script>
</html>
