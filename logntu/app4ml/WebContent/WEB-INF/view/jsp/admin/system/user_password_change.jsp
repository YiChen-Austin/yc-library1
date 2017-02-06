<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"  href="<%=path%>/css/body.css" />
<link type="text/css" rel="stylesheet"	href="<%=path%>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link type="text/css" rel="stylesheet"	href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<script type="text/javascript"	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript"	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript"	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript"	src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript"	src="<%=path%>/js/ux/system/user_password_change.js"></script>
<script type="text/javascript"	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>


<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.ui.draggable.js"></script>
<title>修改密码</title>
</head>
<body>
<br />
<form  id="passwordchange">
<div id="passwordCompare" class="errorMessage" style="margin-left: 44px"></div>
<table>
	<tr>
		<td><label>请输入原始密码：</label></td>
		<td><input type="password" id="initPassword" name="initPassword"
			onblur="testoldpassword()"
			class="validate[required,custom[englishNumberLine]]" /></td>
	</tr>
	<tr>
		<td><label>新密码：</label></td>
		<td><input type="password" id="newpassword" name="newpassword" class="validate[required,custom[englishNumberLine]]"/></td>
	</tr>
	<tr>
		<td><label>确认密码：</label></td>
		<td><input type="password" id="confirmPassword" class="validate[required,custom[englishNumberLine]]" /></td>
			
	</tr>
</table>
<table>
	<tr>
		<td><input type="submit" id="save" value="保存"
			style="margin-right: 5px" /><input type="reset" id="resetButton"
			value="重置" style="margin-right: 5px" /></td>
	</tr>
</table>
</form>

</body>
</html>