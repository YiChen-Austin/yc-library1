<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	href="<%=path%>/js/jquery-easyui-1.4.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/jquery-easyui-1.4.5/themes/icon.css" />
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/easyloader.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/artDialog/jquery.artDialog.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/artDialog/plugins/iframeTools.js"></script>
<link href="<%=path%>/js/artDialog/skins/idialog.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/umeditor1_2_2/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
	<script type="text/javascript"
		src="<%=path%>/umeditor1_2_2/third-party/jquery.min.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=path%>/umeditor1_2_2/umeditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=path%>/umeditor1_2_2/umeditor.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/umeditor1_2_2/lang/zh-cn/zh-cn.js"></script>
	<title>添加页面</title>
</head>
<body>
	<div>
		<form method="post" action="" id="activityDetail"
			enctype="multipart/form-data">
			<br />
			<div id="errorMessage">
				<s:fielderror />
				<s:actionerror />
				<s:actionmessage />
			</div>
			<div id="dateCheck" class="errorMessage"></div>
			<fieldset class="block">
				<legend>
					<b>活动内容信息</b>
				</legend>
				<table class="info">
					<tr>
						<td class="ftt2"><label for="eventTitle">活动主题：<font
								color="red">*</font></label></td>
						<td colspan="3"><input type="text" name="eventTitle"
							value="${activityBean.eventTitle}" id="eventTitle"
							class="inputboxlen validate[required,length[0,255]]" /> <input
							type="hidden" name="id" value="${activityBean.id}" id="id" /></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="imgUrl">封面图片：</label></td>
						<td colspan="3"><input type="file" name="dataFile"
							id="imgUrl" /> <c:if test="${not empty activityBean.imgUrl}">
								<img src="${activityBean.imgUrl}" alt="图片"
									style="width: 30px; height: 30px;" />
							</c:if></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="endTime"> 结束时间：<font
								color="red">*</font></label></td>
						<td colspan="3"><input type="text" name="endTime"
							id="endTime" value="${activityBean.endTime }"
							onclick="WdatePicker()" readonly="readonly" class="Wdate" /></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="eventDesc">活动内容：</label></td>
						<td colspan="3"><textarea name="eventDesc"
								style="width: 512px; height: 100px;" rows="5" cols="40"
								id="eventDesc">${activityBean.eventDesc}</textarea> </textarea></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="contacts">增联系人：</label></td>
						<td><input type="text" name="contacts"
							value="${activityBean.contacts}" id="contacts" class="inputbox" /></td>
						<td class="ftt2"><label for="contactWay">联系方式：</label></td>
						<td><input type="text" name="contactWay"
							value="${activityBean.contactWay}" id="contactWay"
							class="inputbox" /></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="eventAddr">活动地点：</label></td>
						<td colspan="3"><input type="text" name="eventAddr"
							value="${activityBean.eventAddr}" id="eventAddr"
							class="inputboxlen" /><input type="hidden" name="lng" id="lngx"
							value="${activityBean.lng}" /><input type="hidden" name="lat"
							id="latx" value="${activityBean.lat}" /><a href="javascrip:;"
							id="mapid">导入百度坐标</a><span id="lanlng">(${activityBean.lng},${activityBean.lat})</span></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="sponsor">主办：</label></td>
						<td colspan="3"><input type="text" name="sponsor"
							value="${activityBean.sponsor}" id="sponsor" class="inputboxlen" /></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="undertake">承办：</label></td>
						<td colspan="3"><input type="text" name="undertake"
							value="${activityBean.undertake}" id="undertake"
							class="inputboxlen" /></td>
					</tr>
					<tr>
						<td class="ftt2"><label for="assist">协办：</label></td>
						<td colspan="3"><input type="text" name="assist"
							value="${activityBean.assist}" id="assist" class="inputboxlen" /></td>
					</tr>
				</table>
			</fieldset>
			<table>
				<tr>
					<td><input type="button" value="保存" id="saveActivitySubmit"
						style="margin-right: 5px" /><input type="reset" value="重置"
						onclick="document.forms[0].reset();" id="resetActivitySubmit"
						style="margin-right: 5px" /> <input type="button" value="返回"
						id="backActivityButton" style="margin-right: 5px" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>