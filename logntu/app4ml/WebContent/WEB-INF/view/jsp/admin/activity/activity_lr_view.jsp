<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv=content-type />
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
	src="<%=path%>/js/ux/activity/activity_lr_edit.js"></script>
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
<title>添加页面</title>
</head>
<body>
	<div>
		<form method="post" action="saveActivity" id="activityDetail">
			<br />
			<div id="errorMessage">
				<s:fielderror />
				<s:actionerror />
				<s:actionmessage />
			</div>
			<div id="dateCheck" class="errorMessage"></div>
			<fieldset class="block">
				<legend>
					<b>活动信息</b>
				</legend>
				<table class="info">
					<tr>
						<td class="ftt2"><label for="eventTitle">标题：<font
								color="red">*</font></label></td>
						<td colspan="3">${activityBean.eventTitle}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="imgUrl">封面图片：</label></td>
						<td colspan="3"><img src="${activityBean.imgUrl}"
							alt="图片" style="width: 30px; height: 30px;" /></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="endTime">结束时间：<font
								color="red">*</font></label></td>
						<td colspan="3">${activityBean.endTime}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="eventDesc">内容：</label></td>
						<td colspan="3">${activityBean.eventDesc}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="contacts">增联系人：</label></td>
						<td>${activityBean.contacts}</td>
						<td class="ftt2"><label for="contactWay">联系方式：</label></td>
						<td>${activityBean.contactWay}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="eventAddr">活动地点：</label></td>
						<td colspan="3">${activityBean.eventAddr}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="sponsor">主办：</label></td>
						<td colspan="3">${activityBean.sponsor}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="undertake">承办：</label></td>
						<td colspan="3">${activityBean.undertake}</td>
					</tr>
					<tr>
						<td class="ftt2"><label for="assist">协办：</label></td>
						<td colspan="3">${activityBean.assist}</td>
					</tr>
				</table>
			</fieldset>
			<table>
				<tr>
					<td><input type="button" value="返回" id="backActivityButton"
						style="margin-right: 5px" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>