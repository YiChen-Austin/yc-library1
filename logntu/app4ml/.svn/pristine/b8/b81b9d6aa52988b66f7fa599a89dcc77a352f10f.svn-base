<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css"/>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script	type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"	></script>
<script	type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jstree/v_0_9_9a/jquery.tree.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.themeroller.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.super_checkbox.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jstree/v_1_0rc2/jquery.jstree.js"></script>

<script type="text/javascript" src="<%=path%>/js/ux/news/bbs_index.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>

<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<title>公共论坛</title>
</head>
<body>
<div style="float: left; margin-left: 50px"><br />
<div id="repeatName"  class="errorMessage" style="margin-left: 44px"></div>
<table cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="left">
			<table width="900" cellspacing="0" cellpadding="0" align="left" >
				<tr>
					<td width="250" height="450" style="border: 1px solid #a6c9e2;">
						<div style="overflow:auto; height: 100%">
							<div id="commonCategory_tree"></div>
						</div>
					</td>
					<td width="650" style="border: 1px solid #a6c9e2;">
						<div style="float: left; margin-left: 10px; display: block;">
						<form id="searchCondition">
							<table class="mainTable" >
								<tr>
									<td><label for="title">关键字：</label></td>
									<td><input type="text" name="commonArticleBean.title" id="title" />
									<input type="hidden" name="commonArticleBean.commonCategoryId" value="${commonCategoryId }" id="commonCategoryId"/></td>
									<td><label for="startTime">从</label></td>
									<td><input type="text" name="commonArticleBean.startTime"
										id="startTime"  onclick="WdatePicker()" readonly="readonly"/></td>
									<td><label for="endTime">到</label></td>
									<td><input type="text" name="commonArticleBean.endTime"
										id="endTime"  onclick="WdatePicker()"  readonly="readonly"/></td>
									<td><input type="button" value="查询" id="searchButton" style="margin-right:5px "/><input type="button" value="重置" id="resetButton"
											style="margin-right: 5px" onclick="document.forms[0].reset();"/></td>
								</tr>
							</table>
							</form>
							<table id="bbs_list"></table>
							<div id="pager"></div>
							<table>
								<tr>
									<td><input type="button" value="发布新帖" id="sendButton"
										style="float: left; margin-left: 0px"/></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>		
		</td>
	</tr>
</table>
</div>
</body>
</html>