<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/body.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/jquery-easyui-1.4.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="<%=path%>/js/jquery-easyui-1.4.5/themes/icon.css">
		<script type="text/javascript"
			src="<%=path%>/js/jquery-easyui-1.4.5/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/commons/form2json.js"></script>
		<title>店铺销售数据统计表</title>
</head>
<body>
	<div style="padding: 20px 0; height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" plain="true"
			onclick="doMore()" data-value="1" id="more_search">【更多条件】</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" onclick="doSearch()">查询</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-clear" onclick="clearForm()">重置</a>
	</div>
	<form id="search_form" class="easyui-form" method="post"
		data-options="novalidate:true">
		<table cellpadding="5" id="search">
			<tr>
				<td>订单编号:</td>
				<td><input class="easyui-textbox" type="text" name="order_sn"></input></td>
				<td>商家名称:</td>
				<td><input class="easyui-textbox" type="text" name="seller_name">
				<td>客户姓名:</td>
				<td><input class="easyui-textbox" type="text" name="buyer_name">
				<input type="hidden" name="status" id="order_status" value="-1" /></input>
				<input type="hidden" name="isReturn" id="isReturn" value="" /></input></td>
			</tr>
		</table>
		<table cellpadding="5" id="search4tr">
			<tr>
				<td>提交时间:</td>
				<td><input class="easyui-datebox" name="addTimeS"
					style="width: 100px"></input>-<input class="easyui-datebox"
					name="addTimeE" style="width: 100px"></input></td>
			</tr>
			<tr>
				<td>支付时间:</td>
				<td><input class="easyui-datebox" name="payTimeS"
					style="width: 100px"></input>-<input class="easyui-datebox"
					name="payTimeE" style="width: 100px"></input></td>
			</tr>
			<tr>
				<td>发货时间:</td>
				<td><input class="easyui-datebox" name="shipTimeS"
					style="width: 100px"></input>-<input class="easyui-datebox"
					name="shipTimeE" style="width: 100px"></input></td>
			</tr>
			<tr>
				<td>收货时间:</td>
				<td><input class="easyui-datebox" name="finishedTimeS"
					style="width: 100px"></input>-<input class="easyui-datebox"
					name="finishedTimeE" style="width: 100px"></input></td>
			</tr>
			<tr>
				<td>完成时间:</td>
				<td><input class="easyui-datebox" name="evaluationTimeS"
					style="width: 100px"></input>-<input class="easyui-datebox"
					name="evaluationTimeE" style="width: 100px"></input></td>
			</tr>
		</table>
	</form>
	<!-- tags -->
	<div id="checkup_tags" name="tags" class="tags">
		<ul>
			<li class="selectTag"><a name="order_all_div" id="order_all_div">全部</a></li>
			<li><a name="order_unpay_div" id="order_unpay_div">待支付</a></li>
			<li><a name="order_payed_div" id="order_payed_div">已支付</a></li>
			<li><a name="order_send_div" id="order_send_div">已发货</a></li>
			<li><a name="order_rec_div" id="order_rec_div">已收货</a></li>
			<li><a name="order_over_div" id="order_over_div">已完成</a></li>
			<li><a name="order_cancel_div" id="order_cancel_div">已取消</a></li>
			<li><a name="order_refund_div" id="order_refund_div">退款</a></li>
		</ul>
	</div>
	<!-- tags_end -->
	<div id="checkUp" name="checkup_tab_box" class="tagContent">
		<table id="dg" class="easyui-datagrid" title="店铺销售数据统计表"
			style="width: 98%; height: 400px"
			data-options="url: '/admin/order/orderStat',method: 'post',fitColumns: true,singleSelect: true,rownumbers: true,showFooter: true">
			<thead>
				<tr>
					<th data-options="field:'seller_name',width:100">商家</th>
					<th data-options="field:'order_count',width:100">数量</th>
					<th data-options="field:'order_sum',width:100">金额</th>
				</tr>
			</thead>
		</table>
	</div>
	</div>
	<script type="text/javascript">
		function doMore() {
			var datav = $('#more_search').attr('data-value');
			if (datav == 1) {
				$('#more_search').attr('data-value', '0');
				$('#search4tr').css('display', 'block');
				$('#more_search').text('【隐藏条件】');
			} else if (datav == 0) {
				$('#more_search').attr('data-value', '1');
				$('#search4tr').css('display', 'none');
				$('#more_search').text('【更多条件】');
			}
		}
		function doSearch() {
			var data = $("#search_form").serializeJson(); //自动将form表单封装成json
			//alert(JSON.stringify(data));
			$('#dg').datagrid('load', data);
		}
		function clearForm() {
			$('#search_form').form('clear');
		}
		$(function() {
			//隐藏查询条件
			$('#more_search').attr('data-value', '0');
			doMore();
			//绑定选择
			$("#order_all_div").bind('click', alterTag);
			$("#order_unpay_div").bind('click', alterTag);
			$("#order_payed_div").bind('click', alterTag);
			$("#order_send_div").bind('click', alterTag);
			$("#order_rec_div").bind('click', alterTag);
			$("#order_over_div").bind('click', alterTag);
			$("#order_cancel_div").bind('click', alterTag);
			$("#order_refund_div").bind('click', alterTag);
		});
		/**
		 * 选项卡间的切换
		 */
		function alterTag() {
			var tag_no = $(this).attr("name");
			$("#checkup_tags li").attr("class", "");
			$(this).parent().attr("class", "selectTag");
			if (tag_no == 'order_all_div') {
				$('#order_status').val('-1');
				$('#isReturn').val(''); 
			} else if (tag_no == 'order_unpay_div') {
				$('#order_status').val('11'); 
				$('#isReturn').val(''); 
			} else if (tag_no == 'order_payed_div') {
				$('#order_status').val('20'); 
				$('#isReturn').val(''); 
			} else if (tag_no == 'order_send_div') {
				$('#order_status').val('30'); 
				$('#isReturn').val(''); 
			} else if (tag_no == 'order_rec_div') {
				$('#order_status').val('40'); 
				$('#isReturn').val(''); 
			} else if (tag_no == 'order_over_div') {
				$('#order_status').val('50'); 
				$('#isReturn').val(''); 
			}else if (tag_no == 'order_cancel_div') {
				$('#order_status').val('0'); 
				$('#isReturn').val(''); 
			}else if (tag_no == 'order_refund_div') {
				$('#order_status').val('-1'); 
				$('#isReturn').val('1'); 
			}
			// 执行查询
			doSearch();
		}		
	</script>
</body>
</html>