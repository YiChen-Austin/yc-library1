<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jstree/v_0_9_9a/jquery.tree.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.themeroller.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.super_checkbox.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/system/sys_resources_reg.js"></script>
	
<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.ui.draggable.js"></script>
<title>资源注册页面</title>
<style type="text/css">
<!--
#apDiv1 {
	position: absolute;
	width: 405px;
	height: 251px;
	z-index: 1;
	left: 31px;
	top: 17px;
	overflow: auto;
	border: 1px dotted black;
}

#apDiv2 {
	position: absolute;
	width: 496px;
	height: 252px;
	z-index: 2;
	left: 447px;
	top: 17px;
	overflow: auto;
	border: 1px dotted black;
}
#apDiv3 {
	position:absolute;
	width:914px;
	height:165px;
	z-index:3;
	left: 31px;
	top: 276px;
	overflow: auto;
	border: 1px dotted black;
}
#apDiv4 {
	position:absolute;
	width:915px;
	height:98px;
	z-index:4;
	left: 32px;
	top: 451px;
	overflow: auto;
	border: 1px dotted black;
	display:none;
}
#apDiv5 {
	position:absolute;
	width:922px;
	height:91px;
	z-index:5;
	left: 31px;
	top: 451px;
	overflow: auto;
	border: 1px dotted black;
	display:none;
}
#apDiv6 {
	position:absolute;
	width:923px;
	height:257px;
	z-index:6;
	left: 32px;
	top: 451px;
	overflow: auto;
	border: 1px dotted black;
	display:none;
}
-->
</style>



</head>
<body>

<!-- 菜单页面-->
<div id="apDiv1">
<p align="center"><label>请选择菜单</label></p>
<div id="menu_tree" style="margin-top: 10px;"></div>
</div>


<!-- JSP页面-->
<div id="apDiv2">
<input type="hidden" id="jsp_hidden_id" />
<table id="jsp_table_id"></table>
<input type="button" value="新增" id="jsp_add_id"/>
<input type="button" value="修改"  id ="jsp_updata_id"/>
<input type="button" value="删除" id ="jsp_del_id" />
</div>


<!--  组件页面-->
<div id="apDiv3">
<input type="hidden" id="components_hidden_id" />
<table id="components_table_id"></table>
<input type="button" value="新增" id="components_add_id"/>
<input type="button" value="修改" id="components_updata_id"/>
<input type="button" value="删除" id="components_del_id"/>
</div>


<!--  新增/编辑JSP页面-->
<div id="apDiv4">
<p>
<label>JSP页面地址:</label><input type="text" id="jspPage_url_id"/>
<label>描述:</label><input type="text" id="jspPage_description_id"/>
<label>备注:</label><input type="text" id ="jspPage_remark_id"/>
</p>
<p align="left">
<input type="button" value="保存" id="save_jsppage_id"/>
</p>
</div>

<!-- 新增/编辑组件页面 -->
<div id="apDiv5">
<p>
<label>组件名称:</label><input type="text" id="component_name_id"/>
<label>组件ID:</label><input type="text" id="component_name_id_id"/>
<label>描述:</label><input type="text" id="component_description_id"/>
<label>备注:</label><input type="text" id ="component_remark_id"/>
</p>
<input type="button" value="保存" id="save_componente_id"/>
</div>


<!-- 组件的Action页面 -->
<div id="apDiv6">
<table id="actionUrl_table_id"></table>
<input type="hidden" id="actionUrl_hidden_id"/>
<p>
<label>Action地址:</label><input type="text" id="actionUrl_url_id"/>
<label>描述:</label><input type="text" id ="actionUrl_description_id"/>
<label>备注:</label><input type="text" id ="actionUrl_remark_id"/>
</p>
<input type="button" value="保存新增" id="save_actionUrl_id"/>
<input type="button" value="删除" id="del_actionUrl_id"/>
<input type="hidden" id="after_actionUrl_hidden_id"/>
</div>
</body>
</html>