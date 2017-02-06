<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mall" uri="http://www.mall.com/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv=content-type />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/theme/<%=peeling%>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
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
	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/jquery.tree.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.themeroller.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/ux/system/sys_user_edit.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<title>用户添加页面</title>
</head>
<body>
	<div>
		<form:form method="post" action="saveSysUser" id="sysUser"
			commandName="sysUserBean">
			<br />
			<div id="errorMessage"></div>
			<div id="dateCheck" class="errorMessage"></div>
			<table class="mainTable">
				<tr>
					<td><label for="realName">姓名：</label></td>
					<td><input type="text" name="realName" id="realName"
						value="${sysUserBean.realName }"
						class="validate[required,custom[chineseEnglishLine],length[0,40]]" /><input
						type="hidden" name="flag" value="${sysUserBean.flag }" /><font
						color="red">*</font></td>
					<td><label for="gender">性别：</label></td>
					<td><form:radiobuttons path="gender" items="${genderTag}"
							itemLabel="name" itemValue="value" /></td>
				</tr>
				<tr>
					<td><label for="birthDay">出生日期：</label></td>
					<td><input type="text" name="birthDay" readonly="readonly"
						id="birthDay" value="${sysUserBean.birthDay }"
						onclick="WdatePicker()" /></td>
					<td><label for="workYears">工作年限：</label></td>
					<td><input type="text" name="workYears" id="workYears"
						value="${sysUserBean.workYears }"
						class="validate[custom[numberFloat]]" /></td>
				</tr>
				<tr>
					<td><label for="mobile">手机：</label></td>
					<td><input type="text" name="mobile" id="mobile"
						value="${sysUserBean.mobile }"
						class="validate[custom[mobilephone]]" /></td>
					<td><label for="staffKind">人员性质：</label></td>
					<td><form:select path="staffKind" items="${staffKindTag}"
							itemLabel="name" itemValue="value"/></td>
				</tr>
				<tr>
					<td><label for="identityCard">身份证号：</label></td>
					<td><input type="text" name="identityCard" id="identityCard"
						value="${sysUserBean.identityCard }" maxlength="18" /></td>
				</tr>
				<tr>
					<td><label for="isOperatorY">操作员：</label></td>
					<td><input type="radio" value="true" name="isOperator"
						id="isOperatorY" checked="checked" onclick="operatorCheck();" /><label
						for="isOperatorY">是 </label><input type="radio" name="isOperator"
						value="false" id="isOperatorN" onclick="operatorCheck();" /><label
						for="isOperatorN">否</label></td>
				</tr>
				<tr>
					<td><label for="userName">用户名：</label></td>
					<td><input type="text" name="userName" id="userName"
						value="${sysUserBean.userName }" /></td>
					<td><label for="password">用户密码：</label></td>
					<td><input type="password" name="password" id="password"
						value="${sysUserBean.password }"
						class="validate[custom[englishNumberLine]]" /></td>
				</tr>
				<tr>
					<td><label for="effectStartDate">生效日期：</label></td>
					<td><input type="text" name="effectStartDate"
						readonly="readonly" id="effectStartDate"
						value="${sysUserBean.effectStartDate }" onclick="WdatePicker()" /></td>
					<td><label for="effectEndDate">失效日期：</label></td>
					<td><input type="text" name="effectEndDate"
						readonly="readonly" id="effectEndDate"
						value="${sysUserBean.effectEndDate }" onclick="WdatePicker()" /></td>
				</tr>
				<tr>
					<td><input type="hidden" value="true" name="disabled"
						id="disabledY" /></td>

				</tr>
				<tr>
					<td align="right">分配组织机构：</td>
					<td align="left" colspan="3"><input type="hidden"
						name="sysOrganizationIDs" id="sysOrganizationIDs" size="80"
						readonly="readonly"></input> <textarea name="orgNames"
							id="orgNames" rows=4 cols=40 readonly="readonly"></textarea></td>
				</tr>
				<tr>
					<td align="right">分配角色：</td>
					<td align="left" colspan="3"><input type="hidden"
						name="sysRoleIDs" id="sysRoleIDs" size="80" readonly="readonly"></input>
						<textarea name="roleName" id="roleName" rows=4 cols=40
							readonly="readonly"></textarea></td>
				</tr>
			</table>
			<table>
				<tr>

					<td><input type="button" value="保存" id="saveSubmit_a"
						style="margin-right: 5px" /><input type="reset" value="重置"
						onclick="document.forms[0].reset();" id="resetSubmit"
						style="margin-right: 5px" /><input type="button" value="返回"
						id="backButton" style="margin-right: 5px" /></td>

				</tr>
			</table>
		</form:form>
		<div id="sysRoleSelect" title="角色选择" style="display: none;">
			<div style="margin-left: 5px;">
				<br />

				<table width="350" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td width="350" height="300" style="border: 1px solid #a6c9e2;"
							valign="top">
							<div
								style="margin-left: 10px; margin-right: 10px; display: block"
								id="originaDiv">
								<table id="sysRoleList"></table>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="organizationSelect" title="组织机构选择" style="display: none;">
			<div style="float: left; margin-left: 20px;">
				<br />
				<table cellspacing="0" cellpadding="0" align="center"
					style="border: 1px solid #a6c9e2;">
					<tr>
						<td align="left">
							<table width="350" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td width="350" height="300"
										style="border: 0.3px solid #a6c9e2;">
										<div style="overflow: auto; height: 100%">
											<div id="sysOrganizationTree"></div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>