<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/body.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/system/sys_dictionary_edit.js"></script>	
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<title>系统参数新增页面</title>
</head>
<body>
<div>
<form action="modifySysDictionary" method="post"
	id="sysDictionary"><br />
<div id="errorMessage">
		<s:fielderror />
		<s:actionerror />
		<s:actionmessage />
</div>
<table class="mainTable">
	<tr>
		<td><label for="cnName">中文名称：</label></td>
		<td><input type="text" name="cnName"
			id="cnName" value="${sysDictionaryBean.cnName}"  class="validate[required,custom[chinese],length[0,40]]"/><font
			color="red">*</font><input
			type="hidden" name="id" value="${sysDictionaryBean.id }" /></td>
		<td><label for="name">英文名称：</label></td>
		<td><input type="text" name="name" id="name"
			value="${sysDictionaryBean.name}" class="validate[required,custom[englishNumberLine],length[0,40]]"  /><font
			color="red">*</font></td>
	</tr>
	<tr>
		<td><label for="value">参数值：</label></td>
		<td><input type="text" name="value" id="value"
			value="${sysDictionaryBean.value}" class="validate[required]" /><font
			color="red">*</font></td>
		<td><label for="flag">可否编辑：</label><input type="hidden" name="flag" value="${sysDictionaryBean.flag}"/></td>
		<td><input type="text" value="${sysDictionaryBean.flag=='1'?'是':'否'}" disabled="disabled"/></td>
	</tr>
	<tr>
		<td><label for="flag">备注： </label></td>
		<td colspan="3"><textarea rows="5" cols="36"
			name="remark">${sysDictionaryBean.remark }</textarea></td>
	</tr>
</table>
<table>
<tr>
		<td><input type="submit" value="保存" id="saveSubmit"
			style="margin-right: 5px" /><input type="reset" value="重置"
			onclick="resetSysDictionary();" id="resetSubmit"
			style="margin-right: 5px" /><input type="button" value="返回"
			id="backButton" style="margin-right: 5px" /></td>
	</tr>
</table>
</form>
</div>
</body>
</html>