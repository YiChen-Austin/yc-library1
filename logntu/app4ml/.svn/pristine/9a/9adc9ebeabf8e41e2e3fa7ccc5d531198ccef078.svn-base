<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/tempCss.css" />
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
	src="<%=path%>/js/ux/member/member_lr_list.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/common/ui/v_1_0/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/vendor/jquery/ajaxfileupload/ajaxfileupload.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/vendor/jquery/ajaxfileupload/ajaxfileupload.css" />
<script type="text/javascript"
	src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>

<title>${typeValue}</title>
</head>
<body>
	<div style="margin-left: 20px;">
		<br />
		<form id="searchMemberForm" style="margin-left: 0px;">
			<table>
				<tr>
					<td><label for="member_userName">用户名：</label> <input
						type="text" class="inputbox" name="userName" id="member_userName" /></td>
					<td><label for="openId">openId：</label> <input type="text"
						class="inputbox" name="openId" id="openId" /></td>
					<td><label for="openId">姓名：</label> <input type="text"
						class="inputbox" name="realName" id="realName" /></td>
					<c:if test="${loginUser.organizationName=='总站'}">
						<td><label for="openId">区域：</label> <select id="areaId" name="areaId">
								<option value="0">请选择</option>
								<option value="13308">重庆</option>
								<option value="11895">成都</option>
								<option value="19580">遂宁</option>
						</select></td>
					</c:if>
					<td><input class="BtnAction" type="submit" value="查询"
						id="searchMemberButton" /><input class="BtnAction" type="button"
						value="重置" id="resetButton"
						onclick="document.forms['searchMemberForm'].reset();" /> <input
						type="hidden" value="${typeValue}" name="typeValue" id="typeValue"></input><input
						type="hidden" value="${typeKey}" name="type" id="typeKey"></input></td>
				</tr>
			</table>
		</form>
		<table id="memberList"></table>
		<div id="memberPager"></div>
		<table>
			<tr>
				<td><input type="button" id="singleMemberButton" value="查看"
					style="margin-right: 5px" /> <input type="button"
					id="saveMemberButton" value="新增" style="margin-right: 5px" /> <input
					type="button" id="initPwButton" value="初始化密码"
					style="margin-right: 5px" /><input type="button" id="freezeButton"
					value="冻结" style="margin-right: 5px" /><input type="button"
					id="unfreezeButton" value="解冻" style="margin-right: 5px" /></td>
			</tr>
		</table>
		<div class="errbox">
			<ul>
				<li>后台新增用户，默认密码为:ck360</li>
			</ul>
		</div>
	</div>
	<div id="member_add" title="新增${typeValue}" style="display: none">
		<form id="memberAdddForm" style="margin-left: 0px;">
			<table class="info" style="margin-top: 10px;">
				<tr style="margin-top: 10px; height: 40px;">
					<td class="ftt2"><label for="title">用户名：<font
							color="red">*</font></label></td>
					<td colspan="3"><input type="text" name="userName"
						id="new_userName"
						class="inputboxlen validate[required,length[0,255]]" /></td>
				</tr>
				<tr style="margin-top: 10px; height: 40px;">
					<td class="ftt2"><label for="title">姓名：<font
							color="red">*</font></label></td>
					<td colspan="3"><input type="text" name="realName"
						id="new_realName"
						class="inputboxlen validate[required,length[0,255]]" /></td>
				</tr>
				<tr style="margin-top: 10px; height: 40px;">
					<td class="ftt2"><label for="title">单位：<font
							color="red">*</font></label></td>
					<td colspan="3"><input type="text" name="orgName"
						id="new_orgName"
						class="inputboxlen validate[required,length[0,255]]" /></td>
				</tr>
				<tr style="margin-top: 10px; height: 40px;">
					<td class="ftt2"></td>
					<td><input type="button" value="保存" id="saveNewMemberSubmit"
						style="margin-right: 5px" /><input type="reset" value="重置"
						onclick="document.forms['memberAdddForm'].reset();"
						id="resetNewMemberSubmit" style="margin-right: 5px" /> <input
						type="button" value="关闭" id="closeNewMemberButton"
						style="margin-right: 5px" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="member_view" title="${typeValue}详情" style="display: none">
		<table class="info" id="member_view">
		</table>
		<table class="info" id="member_att">
		</table>
	</div>
</body>
</html>