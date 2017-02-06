<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv=content-type />
<link href="/themes/css/images/favicon.ico" rel="SHORTCUT ICON">
	<link href="/themes/css/images/favicon.ico" rel="apple-touch-icon">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/tempCss.css" />
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
	src="<%=path%>/js/vendor/jstree/v_1_0rc2/jquery.jstree.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/util/v_1_0/constant.js"></script>
<!-- easyui需要引入的样式和js -->
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jqueryeasyui/1.2.5/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jqueryeasyui/1.2.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyui_custom.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqueryeasyui/1.2.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqueryeasyui/1.2.5/easyui-lang-zh_CN.js"></script>
<title>${applyCheck.menName}申请详情</title>
</head>
<body>
	<div>
		<fieldset class="block">
			<legend>
				<b>申请信息</b>
			</legend>
			<table class="info">
				<tr>
					<td class="ftt2"><label for="title">申请人：</label></td>
					<td>${applyCheck.menName}</td>
				</tr>
				<tr>
					<td class="ftt2"><label for="title">申请事项：</label></td>
					<td>${applyCheck.appReason}</td>
				</tr>
				<tr>
					<td class="ftt2"><label for="title">申请时间：</label></td>
					<td>${applyCheck.createDate}</td>
				</tr>
			</table>
		</fieldset>
		<c:if test="${not empty member}">
			<fieldset class="block">
				<legend>
					<b>人员基本信息</b>
				</legend>
				<table class="info">
					<tr>
						<td class="ftt2"><label for="title">头像：</label></td>
						<td><img src="${member.portrait}" style="width:150px;height:150px;" title="个人头像"></img></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">手持身份证：</label></td>
						<td><img src="${member.handIdentification}" style="width:150px;height:150px;" title="手持身份证"></img></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">用户名：</label></td>
						<td>${member.userName}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">openId：</label></td>
						<td>${member.openId}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">姓名：</label></td>
						<td>${member.realName}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">联系方式：</label></td>
						<td>${member.mobile}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">昵称：</label></td>
						<td>${member.nickName}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">单位：</label></td>
						<td>${member.orgName}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">身份证：</label></td>
						<td>${member.identification}</td>
					</tr>
				</table>
			</fieldset>
		</c:if>
		<c:if test="${not empty memberTs}">
			<fieldset class="block">
				<legend>
					<b>申请成为天使信息</b>
				</legend>
				<table class="info">
				   <tr>
						<td class="ftt2"><label for="title">手持身份证：</label></td>
						<td><img src="${memberTs.handIdentification}" style="width:150px;height:150px;" title="手持身份证"></img></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">公司名称：</label></td>
						<td>${memberTs.orgName}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">电话：</label></td>
						<td>${memberTs.tel}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">组织类型：</label></td>
						<td>${memberTs.orgType}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">行业：</label></td>
						<td>${memberTs.trade}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">角色：</label></td>
						<td>${memberTs.role}</td>
					</tr>
				</table>
			</fieldset>
		</c:if>
		<c:if test="${not empty memberDs}">
			<fieldset class="block">
				<legend>
					<b>申请成为导师信息</b>
				</legend>
				<table class="info">
				    <tr>
						<td class="ftt2"><label for="title">手持身份证：</label></td>
						<td><img src="${memberDs.handIdentification}" style="width:150px;height:150px;" title="手持身份证"></img></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">公司名称：</label></td>
						<td>${memberDs.orgName}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">电话：</label></td>
						<td>${memberDs.tel}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">组织类型：</label></td>
						<td>${memberDs.orgType}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">行业：</label></td>
						<td>${memberDs.trade}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="title">角色：</label></td>
						<td>${memberDs.role}</td>
					</tr>
				</table>
			</fieldset>
		</c:if>
	</div>
</body>
</html>