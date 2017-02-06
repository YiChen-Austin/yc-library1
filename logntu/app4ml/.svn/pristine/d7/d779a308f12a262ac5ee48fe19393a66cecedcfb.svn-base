<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/tempCss2.css" />
<link
	href="<%=request.getContextPath()%>/css/images/clightbox/clightbox.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/theme/<%=peeling%>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/page/log/log_report.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>
<title><%=projectTitle%></title>
</head>
<body>
<div id=divProcessing
	style="width: 250px; height: 30px; position: absolute; left: 220px; top: 230px; display: none">
<table border=0 cellpadding=0 cellspacing=1 bgcolor="#0072BC"
	width="100%" height="100%">
	<tr>
		<td bgcolor=#0066FF><marquee align="middle" behavior="alternate"
			scrollamount="5"><font color=#FFFFFF>...数据加载中...请等待...</font></marquee></td>
	</tr>
</table>
</div>
<br />
<form id="shareStatistcsForm" style="margin-left: 0px;">
<table>
	<tr>
		<td><label for="beginTime">时间：</label><input type="text"
			class="inputbox" name="logInfoBean.beginTime" id="beginTime"
			readonly="readonly" onclick="WdatePicker()" />-- <input type="text"
			class="inputbox" name="logInfoBean.endTime" id="endTime"
			readonly="readonly" onclick="WdatePicker()" /></td>
		<td><input class="BtnAction" type="button" value="查询"
			id="searchLogInfoButton" /><input class="BtnAction"
			type="button" value="重置" id="resetButton"
			onclick="document.forms[0].reset();" /></td>
	</tr>
</table>
</form>
<table width="90%" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td class="td_title" align="center" rowspan="2">企业</td>
		<td class="td_title" align="center" rowspan="2">用户</td>
		<td class="td_title" align="center" colspan="6">操作类型</td>
	</tr>
	<tr id="title" >
		<td class="td_title" align="center">新增</td>
		<td class="td_title" align="center">修改</td>
		<td class="td_title" align="center">删除</td>
		<td class="td_title" align="center">查看</td>
		<td class="td_title" align="center">登录</td>
		<td class="td_title" align="center">退出</td>
	</tr>
</table>

</body>
</html>