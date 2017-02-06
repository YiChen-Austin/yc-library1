<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/body.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/system/business_dictionary_modify.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>


<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.ui.draggable.js"></script>
<title>业务字典修改页面</title>
</head>

<body>
<div>
<form id="modifyBusinessDictionary"><br/>
	<div id="errorMsg" class="errorMessage">
	<ul class="errorMessage">
		<li><span>已有该英文名称或中文名称!</span></li>
	</ul>
	</div>
	<table class="mainTable">
		<tr><td>
			<input type="hidden" id="hiddenId" value="${businessDictionaryBean.id }" />
			<label for="cnName">中文名称：</label>
		</td><td>
			<input type="text" id="cnName" value="${businessDictionaryBean.cnName}" class="validate[required,custom[chinese],length[0,40]]" />
			<font color="red">*</font>
		</td><td>
			<label for="name">英文名称：</label>
		</td><td>
			<input type="text" id="enName" value="${businessDictionaryBean.enName}" class="validate[required,custom[englishNumberLine],length[0,40]]" />
			<font color="red">*</font>
		</td></tr>
		<tr>
		<td>
			<label for="flag">可否编辑：</label><input type="hidden" id="flag" name="businessDictionaryBean.flag" value="${businessDictionaryBean.flag}"/>
		</td><td>
			<input type="text" value="${businessDictionaryBean.flag=='1'?'是':'否'}" disabled="disabled"/>
		</td></tr>
		<tr><td>
			<label for="flag">备注：</label>
		</td><td colspan="3">
			<textarea id="remark" rows="4" cols="36">${businessDictionaryBean.remark }</textarea>
		</td></tr>
	</table>
	<table id="childList"></table>
	<div id="pageList"></div>
	<table>
		<tr><td>
			<input type="button" value="保存" id="saveSubmit" style="margin-right: 5px" />
			<input type="button" value="重置" id="resetButton" style="margin-right: 5px" />
			<input type="button" value="返回" id="backButton"  style="margin-right: 5px" />
			</td></tr>
	</table>
</form>
</div>

</body>
</html>