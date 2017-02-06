<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv=content-type />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/body.css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/css/theme/<%=peeling%>/jquery-ui-1.8.1.custom.css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/util/v_1_0/error.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/news/bbs_edit.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>

<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script>
<title>论坛帖子</title>
</head>
<body>
	<div style="float: left; margin-left: 50px">
		<br />
		<form method="post" id="commonArticle" action="saveCommonArticle">
			<br />
			<div id="errorMessage">
				<s:fielderror />
				<s:actionerror />
				<s:actionmessage />
			</div>
			<table width="650" border="0" cellspacing="0" cellpadding="0"
				align="center" style="border-collapse: collapse"
				style="border: 0.2px solid #a6c9e2;">
				<tr>
					<td align="center">
						<table cellspacing="0" cellpadding="0" class="tableStyle"
							id="tableObject" style="padding-left: 3" border="1"
							align="center" style="background-color:#ecf0f4" width="100%">
							<tr>
								<td width="100" align="center" height="30"
									style="border: 0.2px solid #a6c9e2;"><label for="title">标题：</label></td>
								<td align="left" style="border: 0.2px solid #a6c9e2;"><input
									type="text" name="commonArticleBean.title"
									class="validate[required,custom[chineseEnglishLine],length[0,40]]"
									id="title" size="55" maxlength="100" /><font color="red">*</font>
									&nbsp;<label for="publishAuthorName">匿名发表：</label><input
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
									value="${commonArticleBean.id }" /> <input type="hidden"
									name="commonArticleBean.commonCategoryId" id="commonCategoryId"
									value="${commonArticleBean.commonCategoryId }" />

									<div id="IEStyle" style="display: none;">
										<textarea class="ckeditor" cols="80" id="editorcontent"
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
						</table>

					</td>
				</tr>
			</table>
			<table>
				<tr>

					<td><input type="submit" value="保存" id="saveSubmit"
						style="margin-right: 5px" /><input type="button" value="重置"
						id="resetButton" style="margin-right: 5px" /><input type="button"
						value="返回" id="backButton" style="margin-right: 5px" /></td>

				</tr>
			</table>
		</form>
	</div>
</body>
</html>