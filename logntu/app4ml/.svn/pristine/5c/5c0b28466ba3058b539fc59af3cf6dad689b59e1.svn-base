<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../head/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="rp" uri="/WEB-INF/tld/resourcepri.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>推广受理</title>
<link href="<%=path%>/js/jquery-easyui-1.4.5/themes/default/easyui.css"
	rel="stylesheet" type="text/css">
<link href="<%=path%>/js/jquery-easyui-1.4.5/themes/icon.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/jquery.min.js" charset="utf-8"></script>
<script src="<%=path%>/js/jquery-easyui-1.4.5/jquery.easyui.min.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=path%>/js/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/ux/mall/spread/accept.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'推广受理查询',border:false"
		style="height: 60px; line-height: 30px; padding-left: 5px; background: #E8F1FF;">
		<form id="searchform">
			<label>联系人：</label><input type="text" class="easyui-textbox"
				name="userName" style="width: 120px;" /> <label>联系电话：</label><input
				type="text" class="easyui-textbox" name="phone"
				style="width: 120px;" /> <label>处 理 人：</label><input type="text"
				class="easyui-textbox" name="operName" style="width: 120px;" /> <label>店铺名称：</label><input
				type="text" class="easyui-textbox" name="storeName" /> <label>申请时间：</label><input
				type="text" class="easyui-datebox" name="beginTime"
				style="width: 120px;" />—<input type="text" class="easyui-datebox"
				name="endTime" style="width: 120px;" /> <a href="javascript:;"
				class="easyui-linkbutton" data-options="iconCls:'icon-zoom'"
				id="btnSearch">查询</a>
		</form>
	</div>
	<div data-options="region:'center',title:'推广受理信息',border:false">
	  <div class="easyui-layout" fit="true">
	  <div data-options="region:'north',border:false" style="padding: 0px; margin: 0px;height:29px;">
		<div id="tabs_info"
			data-options="border:false">
			<div title="待受理"></div>
			<div title="已受理"></div>
			<div title="已删除"></div>
		</div>

		<div id="toolbar">
			<table style="padding: 0px; margin: 0px;height:34px;">
				<tr>
					<td><rp:ResourcePrivilege resouceType="a"
							resourceId="savespreadAcceptButton" resouceName="受理"
							resouceClass="easyui-linkbutton"
							dataOption="iconCls=\"icon-user\" plain=\"true\"" />
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					<td><rp:ResourcePrivilege resouceType="a"
							resourceId="deletespreadAcceptButton" resouceName="删除"
							resouceClass="easyui-linkbutton"
							dataOption="iconCls=\"icon-delete\" plain=\"true\"" /></td>
				</tr>
			</table>


		</div>
		</div>
		<div data-options="region:'center',border:false">
		<table id="J_spreadContent" toolbar="#toolbar"
			data-options="border:false" fit="true"></table>
		</div>
		</div>
	</div>

	<!-- 对话框 -->
	<div id="dialog_accept">
		<form id="fm" style="padding: 5px;">
			<table>
				<tr>
					<td align="right"><label>店铺名称：</label></td>
					<td><input style="border: none;" readonly name="storeName"
						type="text" /></td>
				</tr>
				<tr>
					<td align="right"><label>联系人：</label></td>
					<td><input style="border: none;" readonly name="userName"
						type="text" /></td>
				</tr>
				<tr>
					<td align="right"><label>联系电话：</label></td>
					<td><input style="border: none;" readonly name="phone"
						type="text" /></td>
				</tr>
				<tr>
					<td align="right"><label>备用电话：</label></td>
					<td><input style="border: none;" readonly name="tel"
						type="text" /></td>
				</tr>
				<tr>
					<td align="right" valign="top"><label>申请内容：</label></td>
					<td><textarea
							style="border: none; outline: none; padding: 0px; margin: 0px; resize: none; width: 280px;"
							readonly name="content" readonly></textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top"><label>受理回复：</label></td>
					<td><input type="hidden" name="applyId" value="0" /> 
					<textarea name="operContent" id="operContent" style="width: 280px; height: 50px;"
							class="easyui-validatebox" data-options="required:true"
							missingMessage="必填">	
					
					</textarea></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>