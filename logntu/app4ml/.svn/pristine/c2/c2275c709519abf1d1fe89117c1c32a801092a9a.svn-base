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
<script type="text/javascript" src="<%=path%>/js/ux/news/bbs_edit.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript">
	//替换特殊字符
	function HTMLEncode(text) {
		text = text.replace(/&/g, "&amp;");
		text = text.replace(/\"/g, "&quot;");
		text = text.replace(/</g, "&lt;");
		text = text.replace(/>/g, "&gt;");
		text = text.replace(/\'/g, "&#146;");
		text = text.replace(/\ /g, "&nbsp;");
		text = text.replace(/\n/g, "<br>");
		text = text.replace(/\t/g, "&nbsp;&nbsp;&nbsp;&nbsp;");
		return text;
	}
</script>
<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script>

<title>公共论坛</title>
</head>
<body>
	<div style="float: left; margin-left: 50px">
		<br />
		<table width="680" cellspacing="0" cellpadding="0" bgcolor="#69A8BC"
			style="border: 0.2px solid #a6c9e2;" align="center">
			<tr>
				<td align="center">

					<table style="border: 0.2px solid #a6c9e2;" width="100%"
						cellpadding="1" cellspacing="1" id="tableObject">
						<tr bgcolor="#D9E9EE">
							<td height="20" colspan="2" align="left"><img
								src="<%=path%>/images/bbs/note.gif" /> <b>标题：${commonArticleBeans[0].title}</b></td>
						</tr>
						<tr bgcolor="#F3F9FA">
							<td width="130" align="center" height="150" valign="top"><br />
								<img src="<%=path%>/images/bbs/dep.gif" align="middle" /><br />
								${commonArticleBeans[0].publishDepartmentName}<br></br>${commonArticleBeans[0].publishAuthorName}[1楼]
								<br /> <br /> <font color=#336699>
									${commonArticleBeans[0].publicTimeStr}</font> <input type="hidden"
								name="commonArticleBean.commonCategoryId" id="commonCategoryId"
								value="${commonCategoryId }" /> <input type="hidden"
								name="commonArticleBean.id" id="id"
								value="${commonArticleBean.id }" /> <input type="hidden"
								name="commonArticleBean.lockedButton" id="lockedButton"
								value="${commonArticleBeans[0].lockedButton }" /></td>
							<td align="left" height="150" valign="top"><br /> <img
								src="<%=path%>/images/bbs/${commonArticleBeans[0].emotion}.gif" />
								<font color=#336699><b>标题：</b>
									${commonArticleBeans[0].title}</font><br /> <br />
								<center>
									<s:set var="affixName" value="commonArticleBeans[0].affixName"></s:set>
									<s:set var="image" value="commonArticleBeans[0].image"></s:set>
									<s:if test="#affixName!=null">
										<s:if test="#image=='yes'">
											<img
												src="downImageOrAffix?commonArticleBean.id=${commonArticleBeans[0].id }"
												border="0" alt="图片" />
										</s:if>
										<s:else>
											<a
												href="downImageOrAffix?commonArticleBean.id=${commonArticleBeans[0].id }"
												target="_blank"><img
												src="<%=path%>/images/bbs/view_acces.gif" border="0" /></a>
										</s:else>
									</s:if>
								</center>
								<table width="100%" cellspacing="0" cellpadding="0">
									<tr>
										<td>${commonArticleBeans[0].content}</td>
									</tr>
								</table></td>
						</tr>
						<s:iterator value="commonArticleBeans" status="index">
							<s:if test="#index.index>0">
								<tr bgcolor="#ffffff">
									<td width="130" align="center" height="130" valign="top">
										<br /> <img src="<%=path%>/images/bbs/dep.gif" align="middle" />
										<br /> <s:property value="publishDepartmentName" /><br></br>
										<s:property value="publishAuthorName" />[<s:property
											value="#index.index+1" />#] <br /> <br /> <font
										color=#336699><s:property value="publicTimeStr" /> </font>
									</td>
									<td align="left" style="padding: 2px" valign="top" height="130">
										<br /> <img
										src="<%=path%>/images/bbs/<s:property value='emotion'/>.gif" />
										<font color=#336699> 回复标题：<s:property value='title' /><br /></font>
										<br />
										<center>
											<s:set var="affixName" value="affixName"></s:set>
											<s:set var="image" value="image"></s:set>
											<s:if test="#affixName!=null">
												<s:if test="#image=='yes'">
													<img
														src="downImageOrAffix?commonArticleBean.id=<s:property value='id'/>"
														border="0" alt="图片" />
												</s:if>
												<s:else>
													<a
														href="downImageOrAffix?commonArticleBean.id=<s:property value='id'/>"
														target="_blank"><img
														src="<%=path%>/images/bbs/view_acces.gif" border="0" /></a>
												</s:else>
											</s:if>
										</center>
										<table width="100%" cellspacing="0" cellpadding="0">
											<tr>
												<%
													String content = (String) request.getAttribute("content");
												%>
												<td><%=content%></td>
											</tr>
										</table>
									</td>
								</tr>
							</s:if>
						</s:iterator>
					</table>
				</td>
			</tr>
		</table>
		<form method="post" id="commonArticle"
			action="saveCommonArticleForReply">
			<br />
			<div id="errorMessage">
				<s:fielderror />
				<s:actionerror />
				<s:actionmessage />
			</div>
			<table width="680" border="0" cellspacing="0" cellpadding="0"
				align="center" style="border-collapse: collapse"
				style="border: 0.2px solid #a6c9e2;">
				<tr>
					<td align="center">
						<table cellspacing="0" cellpadding="0" class="tableStyle"
							id="tableObject" style="padding-left: 3" border="1"
							align="center" style="background-color:#ecf0f4" width="100%">
							<tr>
								<td width="100" align="center" height="30"
									style="border: 0.2px solid #a6c9e2;"><label for="title">回复标题：</label></td>
								<td align="left" style="border: 0.2px solid #a6c9e2;"><input
									type="text" name="commonArticleBean.title" id="title" size="55"
									maxlength="100" /><label for="publishAuthorName">匿名发表：</label><input
									type="checkbox" name="commonArticleBean.publishAuthorName"
									value="匿名" id="publishAuthorName" /></td>
							</tr>
							<tr>
								<td width="100" align="center" height="30" rowspan="2"
									style="border: 0.2px solid #a6c9e2;"><label for="emotion">心情：</label></td>
								<td align="left" style="border: 0.2px solid #a6c9e2;"><input
									type="radio" name="commonArticleBean.emotion" checked="checked"
									value="note" align="middle" /> <img
									src="<%=path%>/images/bbs/note.gif" align="middle" id="emotion" />随便说说
									<input type="radio" name="commonArticleBean.emotion"
									value="question" align="middle" id="emotion" /> <img
									src="<%=path%>/images/bbs/question.gif" align="middle" />好奇怪哟
									<input type="radio" name="commonArticleBean.emotion"
									value="warning" align="middle" id="emotion" /> <img
									src="<%=path%>/images/bbs/warning.gif" align="middle" />大家注意 <input
									type="radio" name="commonArticleBean.emotion" value="more"
									align="middle" id="emotion" /> <img
									src="<%=path%>/images/bbs/more.gif" align="middle" />大家过来</td>
							</tr>
							<tr>
								<td align="left" style="border: 0.2px solid #a6c9e2;"><input
									type="radio" name="commonArticleBean.emotion" value="agree"
									align="middle" /> <img src="<%=path%>/images/bbs/agree.gif"
									align="middle" />坚决同意 <input type="radio"
									name="commonArticleBean.emotion" value="disagree"
									align="middle" /> <img src="<%=path%>/images/bbs/disagree.gif"
									align="middle" />坚决反对 <input type="radio"
									name="commonArticleBean.emotion" value="smile" align="middle" />
									<img src="<%=path%>/images/bbs/smile.gif" align="middle" />我得意笑
									<input type="radio" name="commonArticleBean.emotion"
									value="sad" align="middle" /> <img
									src="<%=path%>/images/bbs/sad.gif" align="middle" />气死我了</td>
							</tr>
							<tr>
								<td colspan="2" align="center"
									style="border: 0.2px solid #a6c9e2;"><input type="hidden"
									name="commonArticleBean.id" id="id"
									value="${commonArticleBeans[0].id }" /> <input type="hidden"
									name="commonArticleBean.commonCategoryId" id="commonCategoryId"
									value="${commonArticleBeans[0].commonCategoryId }" />

									<div id="IE6">
										<input type="hidden" name="commonArticleBean.content"
											id="content" />
										<iframe id='editorcontent'
											src='<%=path%>/eWebEditor/eWebEditor.jsp' frameborder=0
											scrolling=no width='100%' height='350'></iframe>
									</div>
									<div id="IEStyle" style="display: none;">
										<textarea class="ckeditor" cols="80" id="editorcontent"
											name="commonArticleBean.content" rows="10"></textarea>
									</div></td>
							</tr>
						</table>

					</td>
				</tr>
			</table>
			<table>
				<tr>

					<td><input type="submit" value="回复该贴" id="replayButton"
						style="margin-right: 5px" disabled="disabled" /><input
						type="button" value="返回" id="backButton" style="margin-right: 5px" /></td>

				</tr>
			</table>
		</form>
	</div>
</body>
</html>