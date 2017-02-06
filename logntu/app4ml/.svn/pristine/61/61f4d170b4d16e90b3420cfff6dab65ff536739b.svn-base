<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mall" uri="http://www.mall.com/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/css/theme/<%=peeling%>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
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
	src="<%=path%>/js/ux/system/sys_designated_date.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/util/v_1_0/error.js"></script>


<link type="text/css" rel="stylesheet"
	href="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/jqueryalert/jquery.ui.draggable.js"></script>
<title>特定日期处理</title>
<style type="text/css">
<!--
#main {
	position: absolute;
	width: 577px;
	height: 327px;
	z-index: 1;
	top: 44px;
}

#date1 {
	position: absolute;
	width: 380px;
	height: 400px;
	z-index: 2;
	left: 620px;
	top: 44px;
	border: 1px dotted black;
}

#date2 {
	position: absolute;
	width: 380px;
	height: 400px;
	z-index: 3;
	left: 620px;
	top: 44px;
	border: 1px dotted black;
}

#date3 {
	position: absolute;
	width: 380px;
	height: 400px;
	z-index: 4;
	left: 620px;
	top: 44px;
	border: 1px dotted black;
}

#apDiv1 {
	position: absolute;
	width: 1002px;
	height: 25px;
	z-index: 4;
}
-->
</style>
</head>









<body>
	<div id="apDiv1" class="mainTable">
		<!-- table接受后台返回的数据 -->
		<s:form id="form" theme="simple">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2"><label for="time4">开始日期：</label> <input
						type="text" name="time4" id="time4" onclick="WdatePicker()"
						readonly="readonly" /> <label for="time5">结束日期：</label> <input
						type="text" name="time5" id="time5" onclick="WdatePicker()"
						readonly="readonly" /> <label for="identifer">状态：</label> <select
						name="identifer" id="identifer">
							<option value="a">所有日期</option>
							<option value="pw">普通工作日</option>
							<option value="ph">普通节假日</option>
							<option value="w">特定工作日</option>
							<option value="h">特定节假日</option>
					</select><input type="button" id="jumpSelect" style="margin-right: 5px"
						value="查询" /> <input type="button" value="重置" id="resetButton"
						style="margin-right: 5px" onclick="document.forms[0].reset();" /></td>
				</tr>
			</table>
		</s:form>
		<br />


	</div>



	<div id="main">
		<table id="sysdesignateddate"></table>
		<div id="pager"></div>


		<input type="button" id="update" value="编辑" style="margin-right: 5px" />
		<input type="button" id="da1" value="统计工作日" style="margin-right: 5px" />
		<input type="button" id="da2" value="计算日期" style="margin-right: 5px" />
		<input type="button" id="da3" value="节假日调整" style="margin-right: 5px" />


	</div>
	<div id="date1">
		<form id="From3">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><label for="tjgzr">&nbsp;统计工作日</label></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="4"
				class="mainTable">
				<tr>
					<td colspan="2">
						<table width="85%" border="0" cellpadding="0" cellspacing="3">
							<tr>
								<td colspan="2"><label for="time1">开始日期：</label><input
									type="text" name="time1" id="time1" onclick="WdatePicker()"
									readonly="readonly" /></td>
							</tr>
							<tr>
								<td colspan="2"><label for="time2">结束日期：</label><input
									type="text" name="time2" id="time2" onclick="WdatePicker()"
									readonly="readonly" /></td>
							</tr>
							<tr>
								<td width="10%"><input type="button" id="statistics"
									value="统计""></input></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>


	</div>
	<div id="date2">
		<form id="From4">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>&nbsp;目标工作日计算</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="4"
				class="mainTable">
				<tr>
					<td colspan="2">
						<table width="85%" border="0" cellpadding="0" cellspacing="3">
							<tr>
								<td colspan="2"><label for="time3">基准日期：</label><input
									type="text" name="time3" id="time3" onclick="WdatePicker()"
									readonly="readonly" /></td>
							</tr>
							<tr>
								<td colspan="2"><label for="days">目标天数：</label><input
									type="text" id="days" name="days" /></td>
							</tr>
							<tr>
								<td width="10%"><input type="button" id="computation"
									value="计算"></input></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="date3">
		<form id="From5">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>&nbsp;节假日调整</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="4"
				class="mainTable">
				<tr>
					<td colspan="2">
						<table width="85%" border="0" cellpadding="0" cellspacing="3">
							<tr>
								<td colspan="2"><label for="time6">开始日期：</label><input
									type="text" name="time6" id="time6" onclick="WdatePicker()"
									readonly="readonly" /></td>
							</tr>
							<tr>
								<td colspan="2"><label for="time7">结束日期：</label><input
									type="text" name="time7" id="time7" onclick="WdatePicker()"
									readonly="readonly" /></td>
							</tr>
							<tr>
								<td colspan="2">
									<p>
										<input type="radio" name="radio" id="radio_ph_id" checked /><label>普通节假日</label>
									</p>
									<p>
										<input type="radio" name="radio" id="radio_pw_id" /><label>普通工作日</label>
									</p>
									<p>
										<input type="radio" name="radio" id="radio_w_id" /><label>特定工作日</label>
									</p>
									<p>
										<input type="radio" name="radio" id="radio_h_id" /><label>特定节假日</label>
									</p>
								</td>
							</tr>
							<tr>
								<td width="10%"><input type="button" id="hwsave" value="保存"></input></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
