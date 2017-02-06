<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/ux/system/sys_user_list.js"></script>
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
<title>用户列表</title>
</head>
<body>
	<div>
		<form id="searchForm" style="margin-left: 0px;">
			<table class="mainTable">
				<tr>
					<td><label for="realName">姓名：</label></td>
					<td><input type="hidden" id="currentUserId"
						name="currentUserId" value="${sessionScope.session_mng_user.id }" /><input
						type="text" name="realName" id="realName" /></td>
					<td><label for="identityCard">身份证号：</label></td>
					<td><input type="text" name="identityCard"
						id="identityCard" /></td>
					<td><label for="effectStartDate">生效日期：</label></td>
					<td><input type="text" name="effectStartDate"
						id="effectStartDate" onclick="WdatePicker()" readonly="readonly" /></td>
					<td><label for="effectEndDate">失效日期：</label></td>
					<td><input type="text" name="effectEndDate"
						id="effectEndDate" onclick="WdatePicker()" readonly="readonly" /></td>
				</tr>
				<tr>
					<td><label for="userName">用户名：</label></td>
					<td><input type="text" name="userName"
						id="userName" /> <input type="hidden" value="true"
						name="disabledFlag" id="disabledY" /></td>
					<td><label for="isOperator">操作员：</label></td>
					<td><select name="operatorFlag">
							<option value="-1">---请选择---</option>
							<option value="true">是</option>
							<option value="false">否</option>
					</select></td>
					<td colspan="2"><input type="button" value="查询"
						id="searchButton" style="margin-right: 5px" /><input
						type="button" value="重置" id="resetButton"
						style="margin-right: 5px" onclick="document.forms[0].reset();" /></td>
				</tr>
			</table>
		</form>
		<table id="list"></table>
		<div id="pager"></div>
		<table>
			<tr>
				<td><input type="button" id="createButton" value="新增"
					style="margin-right: 5px" /><input type="button" id="updateButton"
					value="修改" style="margin-right: 5px" /><input type="button"
					id="deleteButton" value="删除" style="margin-right: 5px" /><input
					type="button" id="initButton" value="恢复初始密码"
					style="margin-right: 5px" /></td>
			</tr>
		</table>
	</div>
</body>
</html>