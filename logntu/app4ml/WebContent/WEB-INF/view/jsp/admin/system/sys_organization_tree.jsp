<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css"/>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jstree/v_0_9_9a/jquery.tree.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.themeroller.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/system/sys_organization_tree.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>

<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.ui.draggable.js"></script>
<title>组织机构管理</title>
</head>
<body>
<div style="float: left; margin-left: 20px;"><br />
<fieldset class="leftFieldSet" ><legend>组织机构显示区</legend>
<div style="overflow:auto; height: 100%">
<div id="sysOrganization_tree"></div>
</div>
</fieldset>
<table>
	<tr>
		<td><input type="button" value="同级新增" id="sameLevelCreateButton"
			style="margin-right: 5px" disabled="disabled" /><input type="button" value="次级新增"
			id="secondaryLevelCreateButton" style="margin-right: 5px" disabled="disabled"/><input type="button" value="删除" id="deleteButton"
			style="margin-right: 5px" disabled="disabled"/><input type="hidden" name="organizationId" value="${loginUser.organizationId}" id="organizationId"/></td>
	</tr>
</table>
</div>
<div style="float: left; margin-left: 30px"><br />
<fieldset class="rightFieldSet"><legend>组织机构编辑区</legend>
<form id="menuForm">
<div id="repeatName"  class="errorMessage" style="margin-left: 44px"></div>
<table  style="display: none" id="menuTable" align="center" class="mainTable">
	<tr>
		<td><label for="name">机构名称：</label></td>
		<td><input type="text" name="name" class="validate[required,custom[chineseEnglishLine],length[0,40]]" id="name" /><font color="red">*</font><input
			type="hidden" name="id" id="id" /><input type="hidden"
			name="orderNo" id="orderNo" /><input type="hidden"
			name="parentId" id="parentId" /><input type="hidden"
			name="selectId" id="selectId" /></td>
	</tr>
	<tr>
		<td><label for="shortName">机构简称：</label></td>
		<td><input type="text" name="shortName" id="shortName" /></td>
	</tr>
	<tr>
		<td><label for="shortName">机构号：</label></td>
		<td><input type="text" name="organizationNumber" id="organizationNumber" /></td>
	</tr>
	<tr>
		<td><label for="nodeImage">图片名称：</label></td>
		<td><input type="text" name="nodeImage" id="nodeImage" class="validate[custom[nodeImage]]" /></td>
	</tr>
	<tr>
		<td><label for="remark">备注：</label></td>
		<td><textarea rows="5" cols="19" name="remark"
			id="remark"></textarea></td>
	</tr>
</table>
</form>
</fieldset>
<table>
	<tr>
		<td><input type="button" value="保存" id="saveButton"
			style="margin-right: 10px" disabled="disabled"/></td>
	</tr>
</table>
</div>
</body>
</html>