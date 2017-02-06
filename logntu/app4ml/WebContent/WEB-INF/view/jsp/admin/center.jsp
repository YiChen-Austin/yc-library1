<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/shortcut.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/center.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/theme/<%=peeling%>/jquery.easywidgets.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/theme/<%=peeling%>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.easywidgets.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/jquery.tree.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.themeroller.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.checkbox.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/ux/system/sys_shortcut_menu.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/center.js"></script>

<link type="text/css" rel="stylesheet"
	href="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
</head>
<body>
	<input id="contextPath" value="<%=path_ex%>" type="hidden" />
	<c:forEach var="desktopDefendsList" items="${centerMap}"
		varStatus="status">
		<div class="widget-place" id="${desktopDefendsList.key}"
			style="height: 300px; width: 450px;">
			<c:forEach items="${desktopDefendsList.value}" var="desktopDefend"
				varStatus="index">
				<div class="widget movable collapsable removable"
					id="${desktopDefend.id }">
					<div class="widget-header">
						<strong>${desktopDefend.title }</strong>
					</div>
					<div class="widget-content" style="height: 200px; width: 400px;">
						<table id="${desktopDefend.divId }"></table>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
	<div class="widget-place" id="widget-place-shortcut"
		style="height: 400px; width: 300px; float: right; margin-right: 20px">
		<div class="widget collapsable" id="identifierwidget-shortcut">
			<div class="widget-header">
				<strong>快捷菜单显示区</strong>
			</div>
			<div class="widget-content" style="height: 446px; width: 270px;">
				<div id="list" class="list_short"></div>
			</div>
		</div>
	</div>
</body>
</html>
