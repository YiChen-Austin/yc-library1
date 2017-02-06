<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/tempCss.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/theme/<%=peeling%>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/ux/activity/activity_in_list.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/ajaxfileupload/ajaxfileupload.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jquery/ajaxfileupload/ajaxfileupload.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>

<title>活动报名</title>
</head>
<body>
	<div style="margin-left: 20px;">
		<br />
		<form id="searchActivityForm" style="margin-left: 0px;">
			<table>
				<tr>
					<td><label for="title">活动选择：</label><select id="activityId"
						name="activityId">
							<option value="-1">--请选择--</option>
							<c:forEach items="${activityBeans}" var="bean">
								<option value="${bean.id}">${bean.eventTitle}</option>
							</c:forEach>
					</select></td>
					<td><input class="BtnAction" type="submit" value="查询"
						id="searchActivityButton" /><input class="BtnAction"
						type="button" value="重置" id="resetButton"
						onclick="document.forms['searchActivityForm'].reset();" /></td>
				</tr>
			</table>

		</form>
		<table id="activityList"></table>
		<div id="activityPager"></div>
	</div>
</body>
</html>