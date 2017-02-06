<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
<title>修改特定日期</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/system/sys_designated_date_edit.js"></script>


<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.ui.draggable.js"></script>
<style type="text/css">
<!--
#apDiv1 {
	position: absolute;
	width: 464px;
	height: 400px;
	z-index: 1;
	left: 381px;
	top: 37px;
	border: 1px dotted black;
}

#apDiv2 {
	position: absolute;
	width: 342px;
	height: 400px;
	z-index: 2;
	left: 31px;
	top: 37px;
	border: 1px dotted black;
}
-->
</style>
</head>
<body>

<div id="apDiv2">
<div id="timeDiv"></div>
</div>
<div id="apDiv1" class="mainTable">
<p><label>名称：</label><input type="hidden" id="id_id"  value="${sysdesignteddatebean.id}"/><input
	type="hidden" id="date_id"  value="${sysdesignteddatebean.date}"/><input type="text" id="name_id" value="${empty sysdesignteddatebean.name?sysdesignteddatebean.date:sysdesignteddatebean.name}"/></p>
<p><input type="radio" name="radio" id="radio_ph_id" ${sysdesignteddatebean.identifer=='ph'?'checked="true"':''}/><label>普通节假日</label></p>
<p><input type="radio" name="radio" id="radio_pw_id" ${sysdesignteddatebean.identifer=='pw'?'checked="true"':''} /><label>普通工作日</label></p>
<p><input type="radio" name="radio" id="radio_w_id" ${sysdesignteddatebean.identifer=='w'?'checked="true"':''} /><label>特定工作日</label></p>
<p><input type="radio" name="radio" id="radio_h_id" ${sysdesignteddatebean.identifer=='h'?'checked="true"':''} /><label>特定节假日</label></p>
<label>备注：</label>
<p><textarea name="remark_id" cols="50" rows="6" id="remark_id">${sysdesignteddatebean.remark}</textarea>
</p>
<p><input type="button" id="submit_id" value="保存" />
<input type="button"  value="返回"  onclick="window.location='<%=path_ex %>/page/system/sys_designated_date'"/>

</p>
</div>
</body>
</html>