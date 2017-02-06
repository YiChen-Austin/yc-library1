<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/theme/<%=peeling%>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript" src="<%=path%>/page/log/log_list.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>

<link type="text/css" rel="stylesheet"
	href="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/jqueryalert/jquery.ui.draggable.js"></script>
<title><%=projectTitle%></title>
</head>
<body>
<div style="float: left; margin-left: 10px">
<form id="searchForm" style="margin-left: 0px;">
<table class="mainTable">
	<tr>
		<td id="supplyNameId"><label for="belongsCompany">运营商：</label></td>
		<td id="supplyContentId"><input type="hidden" id="belongs" value="${loginUser.organizationName }"/><s:select name="logInfoBean.belongsCompany"
			id="belongsCompany"
			list="#{'':'--选择运营商--','通信管理局':'通信管理局','共建共享办公室':'共建共享办公室','重庆电信':'重庆电信','重庆移动':'重庆移动','重庆联通':'重庆联通'}"
			listKey="key" listValue="value" theme="simple" /></td>
		<td><label for="optype">操作类型：</label></td>
		<td><s:select name="logInfoBean.optype" id="optype"
			list="#{'':'--选择操作类型--','新增':'新增','修改':'修改','删除':'删除','查看':'查看','登录':'登录','退出':'退出'}"
			listKey="key" listValue="value" theme="simple" /></td>
		<td><label for="objectname">操作对象： </label></td>
		<td><input type="text" name="logInfoBean.objectname"
			id="objectname" /></td>
		<td><label for="beginTime">开始时间：</label></td>
		<td><input type="text" name="logInfoBean.beginTime"
			id="beginTime" onclick="WdatePicker()" readonly="readonly" /></td>
		<td><label for="endTime">结束时间：</label></td>
		<td><input type="text" name="logInfoBean.endTime" id="endTime"
			onclick="WdatePicker()" readonly="readonly" /></td>
		<td><input type="button" value="查询" id="searchButton"
			style="margin-right: 5px" /><input type="button" value="重置"
			id="resetButton" style="margin-right: 5px"
			onclick="document.forms[0].reset();" /></td>
	</tr>
</table>
</form>
<table id="list"></table>
<div id="pager"></div>
<table>
	<tr>
		<td><input type="button" id="deleteButton" value="删除"
			style="margin-right: 5px" /></td>
	</tr>
</table>
</div>
</body>
</html>