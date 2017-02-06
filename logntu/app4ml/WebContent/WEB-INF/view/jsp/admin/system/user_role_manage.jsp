<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/system/user_role_manage.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>

<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.ui.draggable.js"></script>
<title>用户角色管理</title>
</head>
<body>
<form id="searchCondition">
<table class="mainTable">
		<tr>
			<td><label for="realName">姓名：</label></td>
			<td><input type="text" name="realName" id="realName"/></td>
			<td><label for="roleName">角色名：</label></td>
			<td><input type="text" name="roleName" id="roleName"/></td>
			<td><label for="idcard">身份证号：</label></td>
			<td><input type="text" name="identityCard" id="idcard"/></td>
			<td><input type="button" value="查询" id="searchButton" style="margin-right:5px "/><input type="button" value="重置" id="resetButton"
				style="margin-right: 5px" onclick="document.forms[0].reset();"/></td>
		</tr>
	</table>
</form>
<div style="float:left;margin-left: 0px">
	<table id="userInfoList"></table>
	<div id="pager"></div>
	<input type="hidden" name="loginUserId" value="${loginUser.id}" id="loginUserId"/>"
</div>
<div style="float:left;margin-left: 10px">
	<table id="userRoleInfo"></table>
	<table>
		<tr>
			<td><input type="button" value="提交" id="submitBtn" style="margin-left:0px "/></td>
		</tr>
	</table>
</div>
</body>
</html>