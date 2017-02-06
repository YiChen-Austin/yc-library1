<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv=content-type/>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/body.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/system/region_detail.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<title>角色新增页面</title>
</head>
<body>
<div >
<form action="saveRegion" method="post" id="region">
<br/>
<div id="errorMessage">
		<s:fielderror />
		<s:actionerror />
		<s:actionmessage />
</div>
<table class="mainTable">
	<tr>
		<td><s:text name="片区名称" />：</td>
		<td><input type="hidden" name="regionBean.id" id="id" value="${regionBean.id}"/>
		<input type="text" name="regionBean.cnName"
			id="name" value="${regionBean.cnName }" class="validate[required,custom[chinese],length[0,40]]" /><font color="red">*</font></td>
	</tr>
	<tr>
		<td><s:text name="片区代码" />：</td>
		<td><input type="text" name="regionBean.enName"
			id="enName" value="${regionBean.enName }" class="validate[required,custom[englishNumberLine],length[0,40]]" /><font color="red">*</font></td>
	</tr>
	<tr>
		<td><s:text name="备注" />：</td>
		<td><textarea rows="5" cols="19" name="regionBean.remark" id="remark">${regionBean.remark }</textarea></td>
	</tr>
</table>
<table>
<tr>
		<td><input type="submit"
			value="保存" style="margin-right: 5px" id="saveButton"/><input type="reset"
			value="重置" style="margin-right: 5px" id="resetButton"/><input type="button" value="返回"
			 id="backButton"
			style="margin-right: 5px"/></td>
	</tr>
</table>
</form>
</div>
</body>
</html>