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
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/jquery.tree.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.themeroller.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.super_checkbox.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/ux/area/org_area_manager.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>

<title>组织机构城市管理</title>
</head>
<body>
	<div style="float: left; margin-left: 20px;">
		<br />
		<fieldset class="leftFieldSet">
			<legend>组织机构树</legend>
			<div style="overflow: auto; height: 100%;">
				<div id="sysOrganization_tree"></div>
			</div>
		</fieldset>
	</div>
	<!--组织机构原有人员-->
	<div style="float: left; margin-left: 10px; display: block"
		id="originaDiv">
		<table id="userOriginaList"></table>
		<table>
			<tr>
				<td><input type="button" value="添加城市" id="addOrgAreaBtn"
					style="margin-right: 10px" disabled="disabled" /></td>
				<td><input type="button" value="删除城市" id="deleteOrgAreaBtn"
					style="float: left; margin-left: 0px" disabled="disabled" /></td>
			</tr>
		</table>
	</div>
	<!--组织机构新增地区-->
	<div id="all_cities_view" style="display: none" title="城市信息">
		<div>
			<table>
				<tr>
					<td>省份选择：<select name="provinces" id="provinces_sele"></select></td>
				</tr>
			</table>
		</div>
		<table id="areaAddedList"></table>
		<div id="pager"></div>
		<table>
			<tr>
				<td><input type="button" value="提交" id="addedCityBtn"
					style="float: left; margin-left: 0px" /></td>
				<td><input type="button" value="关闭" id="closeCityBtn"
					style="float: left; margin-left: 0px"/></td>
			</tr>
		</table>
	</div>
</body>
</html>