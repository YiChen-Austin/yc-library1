<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/tempCss.css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet"  href="<%=path%>/css/body.css" />
<link type="text/css" rel="stylesheet"	href="<%=path%>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link type="text/css" rel="stylesheet"	href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<script type="text/javascript"	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript"	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript"	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript"	src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript"	src="<%=path%>/js/ux/system/user_person_update.js"></script>
<script type="text/javascript"	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.ui.draggable.js"></script>
<title>个人修改页面</title>
</head>
<body>
	<form id="passwordchange" action="" method="post">
		<div id="passwordCompare" class="errorMessage"
			style="margin-left: 44px"></div>
		<fieldset class="block"><legend><B>个人基本信息维护</B></legend>
		<table class="info">
			<tr>
				<td class="ftt2"><label for="realName">姓名：</label><font color="red">*</font></td>
				<td><input type="text" name="sysUserBean.realName"
					id="realName" value="${sysUserBean.realName }"
					class="inputboxlen validate[required,custom[chineseEnglishLine],length[0,40]]" /></td>

			</tr>
			<tr>
				<td class="ftt2"><label for="identityCard">邮箱：</label><font color="red">*</font></td>
				<td><input type="text" name="sysUserBean.identityCard" id="identityCard"
					value="${sysUserBean.identityCard}"
					class="inputboxlen validate[custom[email],length[0,255]]" /></td>
			</tr>
			<tr>
				<td class="ftt2"><label for="mobile">手机：</label><font color="red">*</font></td>
				<td><input type="text" name="sysUserBean.mobile" id="mobile"
					value="${sysUserBean.mobile }"
					class="inputboxlen validate[custom[mobilephone]]" /></td>
			</tr>
		</table>
		</fieldset>
		<table>
			<tr>
				<td><input type="button" id="update" value="保存"
					style="margin-right: 5px" /><input type="reset" id="resetButton"
					value="重置" style="margin-right: 5px" /></td>
			</tr>
		</table>
	</form>

</body>
</html>