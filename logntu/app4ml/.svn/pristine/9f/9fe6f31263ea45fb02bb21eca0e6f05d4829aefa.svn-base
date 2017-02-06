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
		<script type="text/javascript" src="<%=path%>/js/commons/form2json.js"></script>
		<title>支付工具数据统计表</title>
</head>
<body>
	<div style="padding: 20px 0; height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" onclick="doSearch()">查询</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-clear" onclick="clearForm()">重置</a>
	</div>
	<form id="search_form" class="easyui-form" method="post"
		data-options="novalidate:true">
		<table cellpadding="5" id="search">
			<tr>
				<tr>
					<td>支付时间:</td>
					<td><input class="easyui-datebox" name="requestTimeS"
						style="width: 100px"></input>-<input class="easyui-datebox"
						name="requestTimeE" style="width: 100px"></input>
					<td><input type="hidden" name="status" id="primary_status"
						value="-1" /></input></td>
				</tr>
		</table>
	</form>
	<!-- tags -->
	<div id="checkup_tags" name="tags" class="tags">
		<ul>
			<li class="selectTag"><a name="primary_all_div"
				id="primary_all_div">全部</a></li>
			<li><a name="primary_unpay_div" id="primary_unpay_div">待支付</a></li>
			<li><a name="primary_payed_div" id="primary_payed_div">支付成功</a></li>
		</ul>
	</div>
	<!-- tags_end -->
	<div id="checkUp" name="checkup_tab_box" class="tagContent">
		<table id="dg" class="easyui-datagrid" title="支付工具数据统计表"
			style="width: 98%; height: 400px"
			data-options="url: '/admin/primary/primaryStat',method: 'post',fitColumns: true,singleSelect: true">
			<thead>
				<tr>
					<th data-options="field:'money4ali',width:100">支付宝(元)</th>
					<th data-options="field:'money4wx',width:100">微信(元)</th>
					<th data-options="field:'money4up',width:100">银联(元)</th>
				</tr>
			</thead>
		</table>
	</div>
	</div>
	<script type="text/javascript">
		function doSearch() {
			var data = $("#search_form").serializeJson(); //自动将form表单封装成json
			//alert(JSON.stringify(data));
			$('#dg').datagrid('load', data);
		}
		function clearForm() {
			$('#search_form').form('clear');
		}
		$(function() {
			//绑定选择
			$("#primary_all_div").bind('click', alterTag);
			$("#primary_unpay_div").bind('click', alterTag);
			$("#primary_payed_div").bind('click', alterTag);
		});
		/**
		 * 选项卡间的切换
		 */
		function alterTag() {
			var tag_no = $(this).attr("name");
			$("#checkup_tags li").attr("class", "");
			$(this).parent().attr("class", "selectTag");
			if (tag_no == 'primary_all_div') {
				$('#primary_status').val('-1');
				$('#isReturn').val('');
			} else if (tag_no == 'primary_unpay_div') {
				$('#primary_status').val('1');
			} else if (tag_no == 'primary_payed_div') {
				$('#primary_status').val('2');
			}
			// 执行查询
			doSearch();
		}
	</script>
</body>
</html>