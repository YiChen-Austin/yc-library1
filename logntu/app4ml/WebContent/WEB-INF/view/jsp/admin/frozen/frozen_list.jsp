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
		<title>冻结列表</title>
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
				<td>会员编号:</td>
				<td><input class="easyui-textbox" type="text" name="user_id"></input><input type="hidden" name="status" id="status" value="1"></input></td>
			</tr>
		</table>
		<table cellpadding="5" id="search4tr">
			<tr>
				<td>冻结时间:</td>
				<td><input class="easyui-datebox" name="frozenTimeS"
					style="width: 100px"></input>-<input class="easyui-datebox"
					name="frozenTimeE" style="width: 100px"></input></td>
			</tr>
		</table>
	</form>
	<!-- tags -->
	<div id="checkup_tags" name="tags" class="tags">
		<ul>
			<li class="selectTag"><a name="order_unfrozen_div" id="order_unfrozen_div">待解冻</a></li>
			<li><a name="order_frozen_div" id="order_frozen_div">已解冻</a></li>
		</ul>
	</div>
	<!-- tags_end -->
	<div id="checkUp" name="checkup_tab_box" class="tagContent">
		<table id="dg" class="easyui-datagrid" title="退款订单列表"
			style="width: 98%; height: 400px"
			data-options="singleSelect:true,collapsible:true,pagination:true,rownumbers:true,autoRowHeight:true,url:'/admin/frozen/list',method:'post',pageSize:20">
			<thead>
				<tr>
					<th data-options="field:'frozen_id',width:70,sortable:true">冻结编号</th>
					<th data-options="field:'user_id',width:70,sortable:true">用户编号</th>
					<th data-options="field:'frozen_time',width:74,sortable:true">冻结时间</th>
					<th data-options="field:'frozen',width:74,sortable:true">冻结金额</th>
					<th data-options="field:'status',width:50,align:'center',formatter:formatStatus">状态</th>
					<th data-options="field:'remark',width:200">冻结原因</th>
					<th data-options="field:'bank_reg_name',width:100,sortable:true">开户行</th>
					<th data-options="field:'bank_card_no',width:150,sortable:true">户行卡</th>
					<th data-options="field:'card_owner',width:80,sortable:true">持卡人</th>
					<th data-options="field:'bank_reg_pri',width:100,sortable:true">开户地区</th>
					<th data-options="field:'bank_reg_sub',width:100,sortable:true">开户支行</th>
				</tr>
			</thead>
		</table>
	</div>
	</div>
	<script type="text/javascript">
		function exportData() {
			window.location = "/admin/frozen/export?"+$("#search_form").serialize();
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
			$('#dg').datagrid('load', data);
		}
		function clearForm() {
			$('#search_form').form('clear');
		}
		function formatStatus(val, row) {
			if (val == 1) {
				return '冻结';
			} else if (val == 2) {
				return '解冻';
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
					text : '导出冻结信息',
					iconCls : 'icon-redo',
					handler:exportData
				}]
			});
			//绑定选择
			$("#order_unfrozen_div").bind('click', alterTag);
			$("#order_frozen_div").bind('click', alterTag);
		});
		/**
		 * 选项卡间的切换
		 */
		function alterTag() {
			var tag_no = $(this).attr("name");
			$("#checkup_tags li").attr("class", "");
			$(this).parent().attr("class", "selectTag");
			if (tag_no == 'order_unfrozen_div') {
				$('#status').val('1'); 
			} else if (tag_no == 'order_frozen_div') {
				$('#status').val('2'); 
			}
			// 执行查询
			doSearch();
		}		
	</script>
</body>
</html>