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
		<title>订单列表</title>
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
		<table id="dg" class="easyui-datagrid" title="普通（不含退款）订单列表"
			style="width: 98%; height: 400px"
			data-options="singleSelect:true,collapsible:true,pagination:true,rownumbers:true,autoRowHeight:true,url:'/admin/order/list',method:'post',pageSize:20">
			<thead>
				<tr>
					<th data-options="field:'order_sn',width:100,sortable:true">订单编号</th>
					<th data-options="field:'seller_name',width:100,sortable:true">商家</th>
					<th data-options="field:'add_time',width:74,sortable:true">提交时间</th>
					<th data-options="field:'pay_time',width:74,sortable:true">支付时间</th>
					<th data-options="field:'ship_time',width:74,sortable:true">发货时间</th>
					<th data-options="field:'finished_time',width:74,sortable:true">收货时间</th>
					<th data-options="field:'evaluation_time',width:74,sortable:true">完成时间</th>
					<th data-options="field:'status',width:50,align:'center',formatter:formatStatus">状态</th>
					<th data-options="field:'record_time',width:74">退款时间</th>
					<th data-options="field:'refund_type',width:70,sortable:true,formatter:formatType">退款类型</th>
					<th data-options="field:'refund_status',width:50,sortable:true,formatter:formatRefund">退款状态</th>
					<th data-options="field:'goods_amount',width:50,sortable:true">产品金额</th>
					<th data-options="field:'order_amount',width:50,sortable:true">订单金额</th>
					<th data-options="field:'payMoney',width:50,sortable:true">支付金额</th>
					<th data-options="field:'shipping_fee',width:50,sortable:false">物流费</th>
					<th data-options="field:'creduce',width:50,sortable:false">券抵扣</th>
					<th data-options="field:'rreduce',width:50,sortable:true">红包扣</th>
					<th data-options="field:'oreduce',width:50,sortable:true">其他扣</th>
					<th data-options="field:'payIdentity2',width:60,sortable:true">支付工具</th>
					<th data-options="field:'payIdentity',width:80,sortable:true">支付编号</th>
					<th data-options="field:'invoice_no',width:80,sortable:true">物流单号</th>
					<th data-options="field:'buyer_name',width:80,sortable:true">客户</th>
					<th data-options="field:'consignee',width:80,sortable:true">收货人</th>
					<th data-options="field:'phoneMob',width:80,sortable:true">联系电话</th>
				</tr>
			</thead>
		</table>
	</div>
	</div>
	<script type="text/javascript">
		var toolbar = [ {
			text : '导出订单',
			iconCls : 'icon-redo',
			handler : exportData
		} ];
		function exportData() {
			window.location = "/admin/order/export?"+$("#search_form").serialize();
		}
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
		function formatPayTool(val, row) {
			if (val == null || val == '') {
				return '';
			} else if (val.indexOf('up') == 0) {
				return '银联';
			} else if (val.indexOf('ali') == 0) {
				return '支付宝';
			} else if (val.indexOf('20') == 0) {
				return '微信';
			}
			return '';
		}
		function formatType(val, row) {
			if (val == 1) {
				return '仅退款';
			} else if (val == 2) {
				return '退货退款';
			} else {
				return '';
			}
		}
		function formatStatus(val, row) {
			if (val == 0) {
				return '已取消';
			} else if (val == 11) {
				return '待支付';
			} else if (val == 12) {
				return '已发货';
			} else if (val == 20) {
				return '支付成功';
			} else if (val == 30) {
				return '已发货';
			} else if (val == 40) {
				return '已收货';
			} else if (val == 50) {
				return '关闭';
			} else {
				return '异常';
			}
		}
		function formatRefund(val, row) {
			if (val == 0) {
				return '';
			} else if (val == 1) {
				return '待商家处理';
			} else if (val == 2) {
				return '商家拒绝';
			} else if (val == 3) {
				return '商家同意';
			} else if (val == 4) {
				return '商家同意';
			} else if (val == 5) {
				return '退款成功';
			} else if (val == 6) {
				return '退款取消';
			} else {
				return '';
			}
		}
		$(function() {
			//隐藏查询条件
			$('#more_search').attr('data-value', '0');
			doMore();
			//导出
			var pager = $('#dg').datagrid().datagrid('getPager');
			pager.pagination({
				buttons:[{
					text : '导出订单',
					iconCls : 'icon-redo',
					handler:exportData
				}]
			});
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