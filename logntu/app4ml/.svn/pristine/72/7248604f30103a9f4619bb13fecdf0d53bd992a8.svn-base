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
		您当前的位置：首页 > 个人中心 > 推广收益
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="payrecordCommission" />
		</jsp:include>
		<div class="main-con fl pt50">
			<ul class="pay-top">
			<li class="fl active" >全部</li>
				<li class="fl " id="dqdate"></li>
				<li class="fl"  id="dqdate1"></li>
				<li class="fl" id="dqdate2"></li>
			</ul>
			<div>
			<div class="pay-div pay-s">
					<div>
						<div  class="bd">
							<table class="table table-striped tab-border obtain-table" >
								<thead>
									<tr>
										<th>结算单号</th>
										<th>业务类型</th>
										<th>收益金额</th>
										<th>提交时间</th>
									</tr>
								</thead>
								<tbody id="only">
									
								</tbody>
							</table>
							<div class="jlnull" id="onlytitle">	
								</div>
							</div>
						
							<ul class="page fr">
								<a href="">上一页</a>
								<span>1</span>
								<a href="">下一页</a>
							</ul>
						</div>
					</div>
				<div class="pay-div pay-s">
					<div>
						<div  class="bd">
							<table class="table table-striped tab-border obtain-table" >
								<thead>
									<tr>
										<th>结算单号</th>
										<th>业务类型</th>
										<th>收益金额</th>
										<th>提交时间</th>
									</tr>
								</thead>
								<tbody id="one">
									
								</tbody>
							</table>
							<div class="jlnull" id="onetitle">	
								</div>
							</div>
						
							<ul class="page fr">
								<a href="">上一页</a>
								<span>1</span>
								<a href="">下一页</a>
							</ul>
						</div>
					</div>
				
				<div class="pay-div pay-s">
					<div>
						<div  class="bd">
							<table class="table table-striped tab-border obtain-table" >
								<thead>
									<tr>
										<th>结算单号</th>
										<th>业务类型</th>
										<th>收益金额</th>
										<th>提交时间</th>
									</tr>
								</thead>
								<tbody id="two">
									
								</tbody>
							</table>
								<div class="jlnull" id="twotitle">	
								</div>
							</div>
						<ul class="page fr">
								<a href="">上一页</a>
								<span>1</span>
								<a href="">下一页</a>
							</ul>
					</div>
				</div>
				<div class="pay-div pay-s">
					<div>
						<div  class="bd">
							<table class="table table-striped tab-border obtain-table" >
								<thead>
									<tr>
										<th>结算单号</th>
										<th>业务类型</th>
										<th>收益金额</th>
										<th>提交时间</th>
									</tr>
								</thead>
								<tbody id="three">
								</tbody>
							</table>
							<div class="jlnull" id="threetitle">	
								</div>
							</div>
						<ul class="page fr">
								<a href="">上一页</a>
								<span>1</span>
								<a href="">下一页</a>
							</ul>
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

$('.pay-s:gt(0)').hide();//隐藏大于0
var stb1=$('.pay-top li');
stb1.click(function(){
    $(this).addClass('active')
            .siblings().removeClass('active');
    var stb_index4=stb1.index(this);//index(this)获取对象参数 获取ct的第几个
    $('.pay-s').eq(stb_index4).show()//如果ct的和li相等就显示
            .siblings().hide();//隐藏掉上一个执行
});
//当前时间
$(function(){ 
	function date(){
	   var mydate = new Date();
	   str = (mydate.getMonth()+1);
	   return str;
	}
	//第一月
	var one = date();
	var two = parseInt(one-1);
	var three = parseInt(one-2);
	var erwes =/^[0-9]{2}$/;
	if(erwes.test(one)){
		one = date();
	}else{
		one = "0"+date();
	}
	if(erwes.test(two)){
		two = parseInt(one-1);
	}else{
		two = "0"+parseInt(one-1);
	}
	if(erwes.test(three)){
		three = parseInt(one-2);
	}else{
		three = "0"+parseInt(one-2);
	}
	$('#dqdate').html(new Date().getFullYear()+"-"+one);
	$('#dqdate1').html(new Date().getFullYear()+"-"+two);
	$('#dqdate2').html(new Date().getFullYear()+"-"+three);
})
//所有收益
$(function(){ 
	var data = {
	        "data":'',
	    };
	$('#onlytitle').html("<div id='jaz0'>加载中...</div>");
	$.ajax({
        url:'payrecordCommission',
        type: 'post',
        dataType: 'json',
        data:data,
        success: function (obj) {
        	var obj = eval("("+obj+")");
        	 $('#only').empty();
        	 if(obj.length == 0){
        			$('#onlytitle').html("当前没有收益");
        			$('#jaz0').css('display','none');
        		}
	        for(var i=0;i<obj.length;i++){
        		$('#only').append(
    	     			"<tr>"+
    	     				"<td>"+obj[i].recordId+"</td>"+
    	     				"<td>"+obj[i].busiDesc+"</td>"+
    	     				"<td>"+obj[i].money+"</td>"+
    	     				"<td>"+obj[i].createTimeStr+"</td>"+
    	    			"</tr>"	
    	        	)
    	        	$('#jaz0').css('display','none');
	        	}  
       	}
    })
})
//当前月份
$(function(){ 
	var data = {
	        "data": $('#dqdate').text(),
	    };
	$('#onetitle').html("<div id='jaz1'>加载中...</div>");
	$.ajax({
        url:'payrecordCommission',
        type: 'post',
        dataType: 'json',
        data:data,
        success: function (obj) {
        	var obj = eval("("+obj+")");
        	 $('#one').empty();
        	 if(obj.length == 0){
        			$('#onetitle').html("当前没有收益");
        			$('#jaz1').css('display','none');
        		}
	        for(var i=0;i<obj.length;i++){
        		$('#one').append(
    	     			"<tr>"+
    	     				"<td>"+obj[i].recordId+"</td>"+
    	     				"<td>"+obj[i].busiDesc+"</td>"+
    	     				"<td>"+obj[i].money+"</td>"+
    	     				"<td>"+obj[i].createTimeStr+"</td>"+
    	    			"</tr>"	
    	        	)
    	        	$('#jaz1').css('display','none');
	        	}  
       	}
    })
})
//当前月份上一月
$(function(){ 
	var data = {
	        "data": $('#dqdate1').text(),
	    };
	$('#twotitle').html("<div id='jaz2'>加载中...</div>");
	$.ajax({
        url:'payrecordCommission',
        type: 'post',
        dataType: 'json',
        data:data,
        success: function (obj) {
        	var obj = eval("("+obj+")");
        	 $('#two').empty();
        	 if(obj.length == 0){
        			$('#twotitle').html("当前没有收益");
        			$('#jaz2').css('display','none');
        		}
	        for(var i=0;i<obj.length;i++){
        		$('#two').append(
    	     			"<tr>"+
    	     				"<td>"+obj[i].recordId+"</td>"+
    	     				"<td>"+obj[i].busiDesc+"</td>"+
    	     				"<td>"+obj[i].money+"</td>"+
    	     				"<td>"+obj[i].createTimeStr+"</td>"+
    	    			"</tr>"	
    	        	)
    	        	$('#jaz2').css('display','none');
	        	}  
       	}
    })
})
//当前月份上二月
$(function(){ 
	var data = {
	        "data": $('#dqdate2').text(),
	    };
	$('#threetitle').html("<div id='jaz3'>加载中...</div>");
	$.ajax({
        url:'payrecordCommission',
        type: 'post',
        dataType: 'json',
        data:data,
        success: function (obj) {
        	var obj = eval("("+obj+")");
        	 $('#three').empty();
        	 if(obj.length == 0){
        			$('#threetitle').html("当前没有收益");
        			$('#jaz3').css('display','none');
        		}
	        for(var i=0;i<obj.length;i++){
        		$('#three').append(
    	     			"<tr>"+
    	     				"<td>"+obj[i].recordId+"</td>"+
    	     				"<td>"+obj[i].busiDesc+"</td>"+
    	     				"<td>"+obj[i].money+"</td>"+
    	     				"<td>"+obj[i].createTimeStr+"</td>"+
    	    			"</tr>"	
    	        	)
    	        	$('#jaz3').css('display','none');
	        	}  
       	}
    })
})
</script>
</html>

