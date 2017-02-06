<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv=content-type />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/body.css" />
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
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/jquery.tree.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.themeroller.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_0_9_9a/plugins/jquery.tree.super_checkbox.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/news/news_detail.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<title>发布信息</title>
</head>
<body>
<div style="float: left; margin-left: 50px"><br />
<form method="post" id="commonArticle" action="saveNewsCommonArticle"><br />
<div id="errorMessage"><s:fielderror /> <s:actionerror /> <s:actionmessage />
</div>
<table width="650" border="0" cellspacing="0" cellpadding="0"
		align="center" style="border-collapse: collapse"
	style="border: 0.2px solid #a6c9e2;">
	<tr>
		<td align="center">
		<table cellspacing="0" cellpadding="0" class="tableStyle"
			id="tableObject" style="padding-left: 3" border="1" align="center"
			 width="100%" >
			<tr>
				<td align="left" height="30"
					style="border: 1px solid #a6c9e2; width: 80px;"><label
					for="title">标题：</label></td>
				<td align="left" style="border: 1px solid #a6c9e2;" colspan="2">${commonArticleBean.title}</td>
			</tr>
			<tr>
				<td align="left" height="30"
					style="border: 1px solid #a6c9e2; width: 80px;"><label
					for="publishDepartmentName">发布部门：</label></td>
				<td align="left" style="border: 1px solid #a6c9e2;"
					colspan="2">${commonArticleBean.publishDepartmentName}</td>
			</tr>
			<tr>
				<td align="left" height="30"
					style="border: 1px solid #a6c9e2; width: 80px;"><label
					for="publishAuthorName">发布人：</label></td>
				<td align="left" style="border: 1px solid #a6c9e2;"
					colspan="2">${commonArticleBean.publishAuthorName}</td>
			</tr>
			<tr>
				<td align="left" height="30"
					style="border: 1px solid #a6c9e2; width: 80px;"><label
					for="publishAuthorName">发布时间：</label></td>
				<td align="left" style="border: 1px solid #a6c9e2;"
					colspan="2">${commonArticleBean.publishTime}</td>
			</tr>
			<s:set value="commonArticleBean.receiveDeparts" id="departJudge"></s:set>
			<s:set value="commonArticleBean.receiveEmps" id="empJudge"></s:set>
			<s:set value="commonArticleBean.affixName" id="affixName"></s:set>
		</table>
		<table cellspacing="0" cellpadding="0" class="tableStyle"
			id="tableObject" style="padding-left: 3" align="center"
			width="100%" style="border: 1px solid #a6c9e2;">
			<tr>
				<td colspan="3" style="border: 1px solid #a6c9e2;">
				<div id="strcontent" style="height: 100%; text-align: left;">${commonArticleBean.content}</div>
				</td>
			</tr>
		</table>
		<table cellspacing="0" cellpadding="0" class="tableStyle"
			id="tableObject" style="padding-left: 3" align="center"
			width="100%" style="border: 1px solid #a6c9e2;">
			<s:if
				test="#affixName==null||#affixName=='commonArticleBean.affixName'"></s:if>
			<s:else>
				<tr>
					<td height="30"
						style="border: 0.2px solid #ecf0f4; width: 80px;">附件：</td>
					<td colspan='2' align="left" style="border: 1px solid #a6c9e2;"><a
						href='downImageOrAffix?commonArticleBean.id=${commonArticleBean.id}'>${
					commonArticleBean.affixName}</a></td>
				</tr>
			</s:else>

		</table>

		</td>
	</tr>
</table>
<table>
	<tr>

		<td><input type="button" value="返回" id="backButton"
			style="margin-right: 5px" onclick="javascript: history.back();" /></td>

	</tr>
</table>
</form>


</div>
</body>
</html>