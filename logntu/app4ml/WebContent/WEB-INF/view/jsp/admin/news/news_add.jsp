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
<script type="text/javascript"
	src="<%=path%>/js/vendor/jstree/v_1_0rc2/jquery.jstree.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/news/news_tree.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>

<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script>

<script type="text/javascript" src="<%=path%>/page/news/news_add.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>

<link type="text/css" rel="stylesheet"
	href="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
<title>发布信息</title>
</head>
<body>
	<div style="float: left; margin-left: 50px">
		<br />
		<form method="post" id="commonArticle" action="saveNewsCommonArticle"
			enctype="multipart/form-data">
			<br />
			<div id="errorMessage">
				<s:fielderror />
				<s:actionerror />
				<s:actionmessage />
			</div>
			<table border="0" width="584px" cellspacing="0" cellpadding="0"
				align="center" style="border-collapse: collapse"
				style="border: 0.2px solid #a6c9e2;">
				<tr>
					<td align="center">
						<table cellspacing="0" cellpadding="0" class="tableStyle"
							id="tableObject" style="padding-left: 3" border="1"
							align="center" style="background-color:#ecf0f4" width="100%">
							<tr style="display: block">
								<td width="60" align="center" height="30"
									style="border: 0.2px solid #a6c9e2;"><label for="title">标题：</label></td>
								<td align="left" style="border: 0.2px solid #a6c9e2;"><input
									type="text" name="commonArticleBean.title"
									class="validate[required,length[0,120]]" id="title" size="55"
									maxlength="240" /><font color="red">*</font> &nbsp;<label
									for="scope">&nbsp;&nbsp;发布范围：</label><input type="checkbox"
									name="commonArticleBean.scope" value="yes" align="middle"
									id="scope" onclick="checkScope();" />&nbsp;&nbsp;</td>
							</tr>
							<tr id="depertTr" style="display: none">
								<td align="right">发送部门：</td>
								<td align="left"><input type="hidden"
									name="commonArticleBean.receiveDepartIDS" id="receiveDepartIDS"
									size="80" readonly="readonly"></input> <textarea
										name="commonArticleBean.receiveDeparts" id="receiveDeparts"
										style="width: 512px; height: 70px; overflow: auto;"
										readonly="readonly"></textarea></td>
							</tr>
							<tr id="empTr" style="display: none">
								<td align="right">发送员工：</td>
								<td align="left"><input type="hidden"
									name="commonArticleBean.receiveEmpIDS" id="receiveEmpIDS"
									size="80" readonly="readonly"></input> <textarea
										name="commonArticleBean.receiveEmps" id="receiveEmps"
										style="width: 512px; height: 70px; overflow: auto;"
										readonly="readonly"> </textarea></td>
							</tr>
							<tr style="display: block">
								<td colspan="2" align="center"
									style="border: 0.2px solid #a6c9e2;"><input type="hidden"
									name="commonArticleBean.id" id="id"
									value="${commonArticleBean.id }" /> <input type="hidden"
									name="commonArticleBean.commonCategoryId" id="commonCategoryId"
									value="${commonArticleBean.commonCategoryId }" />
									<div id="IEStyle" style="display: none;">
										<textarea class="ckeditor" id="editorcontent"
											style="width: 512px; height: 250px; overflow: auto;"
											name="commonArticleBean.content" rows="10"></textarea>
									</div>
									<div id="IE6">
										<input type="hidden" name="commonArticleBean.content"
											id="content" />
										<iframe id='editorcontent'
											src='<%=path%>/eWebEditor/eWebEditor.jsp' frameborder=0
											scrolling=no width='100%' height='350'></iframe>
									</div></td>
							</tr>
							<tr style="display: block">
								<td colspan="2" align="left"
									style="border: 0.2px solid #a6c9e2;" height="30">
									<table width="100%">
										<tr>
											<td><label>是否上传附件：</label><input type="checkbox"
												name="upload" value="yes" align="middle" id="upload"
												onclick="uploadNew();" /></td>
											<td><input type="file" name="myFile" id="myFile"
												style="float: right; display: none"></input></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

					</td>
				</tr>
			</table>
			<div id=divProcessing
				style="width: 250px; height: 30px; position: absolute; left: 220px; top: 230px; display: none">
				<table border=0 cellpadding=0 cellspacing=1 bgcolor="#0072BC"
					width="100%" height="100%">
					<tr>
						<td bgcolor=#3A6EA5><marquee align="middle"
								behavior="alternate" scrollamount="5"> <font
								color=#FFFFFF>...文件上传中...请等待...</font></marquee></td>
					</tr>
				</table>
			</div>
			<table>
				<tr>

					<td><input type="submit" value="保存" id="saveSubmit"
						style="margin-right: 5px" /><input type="button" value="重置"
						id="resetButton" style="margin-right: 5px" /><input type="button"
						value="返回" id="backButton" style="margin-right: 5px" /></td>

				</tr>
			</table>
		</form>
		<div id="organizationUserSelect" title="组织机构人员选择"
			style="display: none;">
			<div style="margin-left: 20px;">
				<br />
				<table cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td align="left">
							<table width="700" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td width="450" height="300" style="border: 1px solid #a6c9e2;">
										<div style="height: 100%">
											<div id="sysOrganization_tree"></div>
										</div>
									</td>
									<td width="300" height="300" style="border: 1px solid #a6c9e2;"
										valign="top">
										<div
											style="margin-left: 10px; margin-right: 10px; display: block"
											id="originaDiv">
											<table id="userOriginaList"></table>
										</div>
									</td>
									<td width="250" height="300" style="border: 1px solid #a6c9e2;"
										valign="top">
										<div style="margin-left: 10px; display: block" id="selectDiv">
											<table id="selectDivUser">
												<tr>
													<td><select id="choicedAccMan" size="20"
														style="width: 155px;" multiple="multiple"></select></td>
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
		<div id="organizationSelect" title="组织机构选择" style="display: none;">
			<div style="float: left; margin-left: 20px;">
				<br />
				<table cellspacing="0" cellpadding="0" align="center"
					style="border: 1px solid #a6c9e2;">
					<tr>
						<td align="left">
							<table width="350" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td width="350" height="300"
										style="border: 0.3px solid #a6c9e2;">
										<div style="overflow: auto; height: 100%">
											<div id="sysOrganizationTree"></div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>