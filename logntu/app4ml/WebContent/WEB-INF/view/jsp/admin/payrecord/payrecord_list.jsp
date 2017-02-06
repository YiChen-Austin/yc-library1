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
		<title>会员资金变化列表</title>
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
				<td>产生时间:</td>
				<td><input class="easyui-datebox" name="requestTimeS"
					style="width: 100px"></input>-<input class="easyui-datebox"
					name="requestTimeE" style="width: 100px"></input></td>
				<td>会员编号:</td>
				<td><input class="easyui-textbox" type="text" name="in_user_id"></td>
			</tr>
		</table>
	</form>
	<table id="dg" class="easyui-datagrid" title="会员资金变化列表"
		style="width: 98%; height: 400px"
		data-options="singleSelect:true,collapsible:true,pagination:true,rownumbers:true,autoRowHeight:true,url:'/admin/payrecord/list',method:'post',pageSize:20">
		<thead>
			<tr>
				<th data-options="field:'in_user_id',width:'25%',sortable:true">会员编号</th>
				<th data-options="field:'money',width:'25%',sortable:true">变化金额</th>
				<th data-options="field:'create_time',width:'25%',sortable:true">创建时间</th>
				<th data-options="field:'description',width:'25%',sortable:true">描述</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		function doSearch() {
			var data = $("#search_form").serializeJson(); //自动将form表单封装成json
			//alert(JSON.stringify(data));
			$('#dg').datagrid('load', data);
		}
		function clearForm() {
			$('#search_form').form('clear');
		}
	</script>
</body>
</html>