<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
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
	src="<%=path%>/js/ux/project/project_tag_list.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/ajaxfileupload/ajaxfileupload.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jquery/ajaxfileupload/ajaxfileupload.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>
<title>项目平台标注</title>
</head>
<body>
	<input type="hidden" id="p_status" value="0"></input>
	<div style="margin-top: 40px;">
		<!-- tags -->
		<div id="checkup_tags" name="tags" class="tags">
			<ul>
			    <li><a name="P_All" id="P_All">所有项目</a></li>
				<li class="selectTag"><a name="P_Un_Concern" id="P_Un_Concern">未关注</a></li>
				<li><a name="P_Concern" id="P_Concern">关注</a></li>
				<li><a name="P_Plan" id="P_Plan">拟投</a></li>
				<li><a name="P_Lead" id="P_Lead">领投</a></li>
				<li><a name="P_Masses" id="P_Masses">参与众筹</a></li>
				<li><a name="P_Already" id="P_Already">已投</a></li>
			</ul>
		</div>
		<!-- tags_end -->
		<div id="checkUp" name="checkup_tab_box" class="tagContent">
			<table id="list"></table>
			<div id="pager"></div>
		</div>
	</div>
	<div style="display: none">
		<a href="#" id="open_member_view" target="_blank"></a>
	</div>
</body>
</html>