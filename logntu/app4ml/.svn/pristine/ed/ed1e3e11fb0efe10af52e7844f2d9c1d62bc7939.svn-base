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
<script type="text/javascript" src="<%=path%>/js/ux/news/news_tree.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>

<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>

<title>信息栏目管理</title>
</head>
<body>
<div style="float: left; margin-left: 50px"><br />
<form id="menuForm">
<div id="repeatName"  class="errorMessage" style="margin-left: 44px"></div>
<table  cellspacing="0" cellpadding="0" align="center" >
	<tr>
		<td align="left">
			<table width="700"  cellspacing="0" cellpadding="0" align="center" style="border: 1px solid #a6c9e2;">
				<tr>
					<td width="250" height="300" style="border: 1px solid #a6c9e2;">
						<div style="overflow:auto; height: 100%">
							<div id="commonCategory_tree"></div>
						</div>
					</td>
					<td width="450" >
						<table cellspacing="0" cellpadding="0" id="tableObject" style="border: 1px solid #a6c9e2;border-collapse:collapse"  align="center" bgcolor="#ecf0f4" style="width:90%">

							<tr>
								<td align="right" width="120" height="30" bgcolor="#ffffff" style="border: 1px solid #a6c9e2;"><label for="name">信息名称：</label></td>
								<td width="240" align="left" bgcolor="#ffffff" style="border: 1px solid #a6c9e2;">
									&nbsp;<input type="text" name="commonCategoryBean.name" class="validate[required,custom[chineseEnglishLine],length[0,40]]" id="name" /><font color="red">*</font><input
										type="hidden" name="commonCategoryBean.id" id="id" /><input type="hidden"
										name="commonCategoryBean.orderNo" id="orderNo" /><input type="hidden"
										name="commonCategoryBean.parentId" id="parentId" /><input type="hidden"
										name="commonCategoryBean.flag" id="flag" value="discussion"/><input type="hidden"
										name="selectId" id="selectId" />
								</td>
							</tr>

							<tr>			
								<td align="right"  height="130" bgcolor="#ffffff" style="border: 1px solid #a6c9e2;"><label for="remark">备注：</label></td>
								<td align="left"  bgcolor="#ffffff" style="border: 1px solid #a6c9e2;">
									&nbsp;<textarea rows="5" cols="19" name="commonCategoryBean.remark"	id="remark"></textarea>
								</td>
							</tr>
							<tr>
								<td align="right" width="120" height="30" bgcolor="#ffffff" style="border: 1px solid #a6c9e2;"><label for="publisherId">发布人员：</label></td>
								<td width="240" align="left" bgcolor="#ffffff" style="border: 1px solid #a6c9e2;">
									&nbsp;<textarea rows="3" cols="26" name="commonCategoryBean.publisherName" class="validate[required]" id="publisherName" readonly="readonly"></textarea><font color="red">*</font>
									<input type="hidden" name="commonCategoryBean.publisherId"  id="publisherId" />
								</td>
							</tr>
							<tr>
								<td align="right" width="120" height="30" bgcolor="#ffffff" style="border: 1px solid #a6c9e2;"><label for="administratorName">管理人员：</label></td>
								<td width="240" align="left" bgcolor="#ffffff" style="border: 1px solid #a6c9e2;">
									&nbsp;<textarea rows="3" cols="26" name="commonCategoryBean.administratorName" class="validate[required]" id="administratorName" readonly="readonly"></textarea><font color="red">*</font>
									<input type="hidden" name="commonCategoryBean.administratorId" id="administratorId" />
								</td>
							</tr>
						
						</table>
					</td>
				</tr>
			</table>		
		</td>
	</tr>
</table>
</form>
<div id="organizationUserSelect" title="组织机构人员选择" style="display: none;">
	<div style="float: left; margin-left: 20px;"><br />
		<table  cellspacing="0" cellpadding="0" align="center" style="border: 1px solid #a6c9e2;">
			<tr>
				<td align="left">
					<table width="800"  cellspacing="0" cellpadding="0" align="center" style="border: 1px solid #a6c9e2;">
						<tr>
							<td width="250" height="300" style="border: 1px solid #a6c9e2;">
								<div style="overflow:auto; height: 100%">
									<div id="sysOrganization_tree"></div>
								</div>
							</td>
							<td width="350" height="300" style="border: 1px solid #a6c9e2;">
								<div style="margin-left: 10px; display: block"
									id="originaDiv">
									<table id="userOriginaList"></table>
								</div>
							</td>
							<td>
									<input id="remove_btn" type="button" value="&lt;" style="width: 45px;" />
									<br />
									<input id="remove_all_btn" type="button" value="&lt;&lt;" style="width: 45px;" />
								</td>
								<td valign="top" width="120" height="200" style="border: 0.2px solid #a6c9e2;">
									<div style="float: left; margin-left: 2px; display: block"	id="selectDiv">
										<table id="selectDivUser"><tr><td><select id="choicedAccMan" size="17" style="width:180px;height: 250;" multiple="multiple"></select></td></tr></table>
										<table>
										<tr>
											<td><input type="button" value="确定" id="selectBtn"
												style="margin-left: 0px" disabled="disabled" /></td>
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
</div>
<table>
	<tr>
		<td><input type="button" value="同级新增" id="sameLevelCreateButton"
			style="margin-right: 5px" disabled="disabled" /><input type="button" value="次级新增"
			id="secondaryLevelCreateButton" style="margin-right: 5px" disabled="disabled"/><input type="button" value="删除" id="deleteButton"
			style="margin-right: 5px" disabled="disabled"/><input type="button" value="保存" id="saveButton"
			style="margin-right: 10px" disabled="disabled"/></td>
	</tr>
</table>
</div>
</body>
</html>