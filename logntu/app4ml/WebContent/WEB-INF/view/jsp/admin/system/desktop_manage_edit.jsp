<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/body.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/system/desktop_manage_edit.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<title>桌面维护编辑页面</title>
</head>

<body>
<div>
<form id="desktopDefend"><br />
<div id="errorMessage">
		<s:fielderror />
		<s:actionerror />
		<s:actionmessage />
</div>
<table class="mainTable">
	<tr>
		<td><label for="title">区域业务名称：</label></td>
		<td><input type="text" name="title"
			id="title" value="${desktopDefendBean.title}"  class="validate[required]"/><font
			color="red">*</font><input id="id"	type="hidden" name="id" value="${desktopDefendBean.id }" /></td>
		<td><label for="name">DIV标签ID：</label></td>
		<td><input type="text" name="divId" id="divId"
			value="${desktopDefendBean.divId}" class="validate[required,custom[englishNumberLine],length[0,40]]"  /><font
			color="red">*</font></td>
	</tr>
	<tr>
		<td><label for="flag">备注： </label></td>
		<td colspan="3"><textarea rows="5" cols="36"
			name="remark">${desktopDefendBean.remark }</textarea></td>
	</tr>
</table>
<table>
   <tr>
		<td><input type="submit" value="保存" id="saveSubmit"
			style="margin-right: 5px" /><input type="reset" value="重置"  id="resetSubmit"
			style="margin-right: 5px" /><input type="button" value="返回"
			id="backButton" style="margin-right: 5px" /></td>
	</tr>
</table>
</form>
</div>

</body>
</html>