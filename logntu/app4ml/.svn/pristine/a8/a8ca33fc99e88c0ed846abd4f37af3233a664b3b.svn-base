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
		您当前的位置：首页 > 个人中心 > 会员推广
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="extension" />
		</jsp:include>
		<div class="main-con fl pt50">
			<div class="main-con fl">
		        
		        <div class="schedule-tab">
		            <label class="active">我的上级</label>
		            <label >一级分销</label>
		            <label>二级分销</label>
		            <label>三级分销</label>
		        </div>
		        <div class="schedule-tabdiv">
		            <div class="tab-table">
		                <div class="bd1">
		                    <table class="table table-striped tab-border schedule-table">
		                        <thead>
		                        <tr>
		                            <th>用户名</th>
		                            <th>电话号码</th>
		                            <th>微信号码</th>
		                            <th>时间</th>
		                        </tr>
		                        </thead>
		                        <tbody id="sj">
									
		                        </tbody>
		                        <div class="jlnull" id="sjtitle">	
								</div>
		                    </table>
		                </div>
		                <ul class="page fr">
		                    <a href="">上一页</a>
		                    <span>1</span>
		                    <a href="">下一页</a>
		                </ul>
		            </div>
		            <div class="tab-table">
		                <div class="bd1">
		                    <table class="table table-striped tab-border schedule-table">
		                        <thead>
		                        <tr>
		                            <th>用户名</th>
		                            <th>电话号码</th>
		                            <th>微信号码</th>
		                            <th>时间</th>
		                        </tr>
		                        </thead>
		                        <tbody id="one">
		
		                        </tbody>
		                        <div class="jlnull" id="onetitle">	
								</div>
		                    </table>
		                </div>
		                <ul class="page fr">
		                    <a href="">上一页</a>
		                    <span>1</span>
		                    <a href="">下一页</a>
		                </ul>
		            </div>
		            <div class="tab-table">
		                <div class="bd1">
		                    <table class="table table-striped tab-border schedule-table">
		                        <thead>
		                        <tr>
		                            <th>用户名</th>
		                            <th>电话号码</th>
		                            <th>邮箱号码</th>
		                            <th>微信号码</th>
		                        </tr>
		                        </thead>
		                        <tbody id="two">
		
		                        </tbody>
		                        <div class="jlnull" id="twotitle">	
								</div>
		                    </table>
		                </div>
		                <ul class="page fr">
		                    <a href="">上一页</a>
		                    <span>1</span>
		                    <a href="">下一页</a>
		                </ul>
		            </div>
		            <div class="tab-table">
		                <div class="bd1">
		                    <table class="table table-striped tab-border schedule-table">
		                        <thead>
		                        <tr>
		                            <th>用户名</th>
		                            <th>电话号码</th>
		                            <th>邮箱号码</th>
		                            <th>微信号码</th>
		                        </tr>
		                        </thead>
		                        <tbody id="Three">
		
		                        </tbody>
		                    </table>
		                     <div class="jlnull" id="Threetitle">	
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

$('.tab-table:gt(0)').hide();//隐藏大于0
var stb1=$('.schedule-tab label');
stb1.click(function(){
    $(this).addClass('active')
            .siblings().removeClass('active');
    var stb_index1=stb1.index(this);//index(this)获取对象参数 获取ct的第几个
    $('.tab-table').eq(stb_index1).show()//如果ct的和li相等就显示
            .siblings().hide();//隐藏掉上一个执行
});


//上级
$(function(){ 
	$('#sjtitle').html("<div id='jaz1'>加载中...</div>");
	$.ajax({
        url:'findSuperior',
        type: 'post',
        dataType: 'json',
        success: function (obj) {
        	var list = eval("("+obj+")");
        	console.log(list)
        	 if(list.length == 0){
        			$('#sjtitle').html("没有上级");
        			$('#jaz1').css('display','none');
        		}
        	for(var i=0;i<list.length;i++){
        		$('#sj').append(
    	     			"<tr>"+
    	     				"<td>"+list[i].userName+"</td>"+
    	     				"<td>"+list[i].phoneMob+"</td>"+
    	     				"<td>"+list[i].weixin+"</td>"+
    		     			"<td class='ztval'>"+list[i].createTime+"</td>"+
    	    			"</tr>"	
    	        	)
    	        	$('#jaz1').css('display','none');
        	}
       	}
    })
})


//一级
$(function(){ 
	$('#onetitle').html("<div id='jaz2'>加载中...</div>");
	$.ajax({
        url:'findOneDist',
        type: 'post',
        dataType: 'json',
        success: function (obj) {
        	$('#one').empty();
        	var list = eval("("+obj+")");
        	console.log(list)
        	 if(list.length == 0){
        			$('#onetitle').html("没有一级");
        			$('#jaz2').css('display','none');
        		}
        	for(var i=0;i<list.length;i++){
        		$('#one').append(
    	     			"<tr>"+
    	     				"<td>"+list[i].userName+"</td>"+
    	     				"<td>"+list[i].phoneMob+"</td>"+
    	     				"<td>"+list[i].weixin+"</td>"+
    		     			"<td class='ztval'>"+list[i].createTime+"</td>"+
    	    			"</tr>"	
    	        	)
    	        	$('#jaz2').css('display','none');
        	}
       	}
    })
})

//二级
$(function(){ 
	$('#twotitle').html("<div id='jaz3'>加载中...</div>");
	$.ajax({
        url:'findTwoDist',
        type: 'post',
        dataType: 'json',
        success: function (obj) {
        	$('#two').empty();
        	var list = eval("("+obj+")");
        	console.log(list)
        	 if(list.length == 0){
        			$('#twotitle').html("没有二级");
        			$('#jaz3').css('display','none');
        		}
        	for(var i=0;i<list.length;i++){
        		$('#two').append(
    	     			"<tr>"+
    	     				"<td>"+list[i].userName+"</td>"+
    	     				"<td>"+list[i].phoneMob+"</td>"+
    	     				"<td>"+list[i].weixin+"</td>"+
    		     			"<td class='ztval'>"+list[i].createTime+"</td>"+
    	    			"</tr>"	
    	        	)
    	        	$('#jaz3').css('display','none');
        	}
       	}
    })
})
//三级
$(function(){ 
	$('#Threetitle').html("<div id='jaz4'>加载中...</div>");
	$.ajax({
        url:'findThreeDist',
        type: 'post',
        dataType: 'json',
        success: function (obj) {
        	$('#Three').empty();
        	var list = eval("("+obj+")");
        	console.log(list)
        	 if(list.length == 0){
        			$('#Threetitle').html("没有三级");
        			$('#jaz4').css('display','none');
        		}
        	for(var i=0;i<list.length;i++){
        		$('#Three').append(
    	     			"<tr>"+
    	     				"<td>"+list[i].userName+"</td>"+
    	     				"<td>"+list[i].phoneMob+"</td>"+
    	     				"<td>"+list[i].weixin+"</td>"+
    		     			"<td class='ztval'>"+list[i].createTime+"</td>"+
    	    			"</tr>"	
    	        	)
    	        	$('#jaz4').css('display','none');
        	}
       	}
    })
})
</script>
</html>

