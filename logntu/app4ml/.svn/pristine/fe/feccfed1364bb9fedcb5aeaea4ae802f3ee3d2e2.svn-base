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
<title>推广店铺</title>
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
<script src="<%=path%>/js/ux/mall/spread/index.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'推广店铺查询',border:false"
		style="height: 60px; line-height: 30px; padding-left: 5px; background: #E8F1FF;">
		<form id="searchform">
			<label>店铺名称：</label><input type="text" class="easyui-textbox"
				name="storeName" /> <label>处 理 人：</label><input type="text"
				class="easyui-textbox" name="operName" style="width: 120px;" /> <label>处理时间：</label><input
				type="text" class="easyui-datebox" name="beginTime"
				style="width: 120px;" />—<input type="text" class="easyui-datebox"
				name="endTime" style="width: 120px;" /> <a href="javascript:;"
				class="easyui-linkbutton" data-options="iconCls:'icon-zoom'"
				id="btnSearch">查询</a>
		</form>
	</div>
	<div data-options="region:'center',title:'推广店铺信息',border:false">
	  <div class="easyui-layout" fit="true">
	  <div data-options="region:'north',border:false" style="padding: 0px; margin: 0px;height:29px;">
	  <div id="toolbar">
			<table style="padding: 0px; margin: 0px;">
				<tr>
					<td><rp:ResourcePrivilege resouceType="a"
							resourceId="savespreadIndexButton" resouceName="新增"
							resouceClass="easyui-linkbutton"
							dataOption="iconCls=\"icon-add\" plain=\"true\"" /></td>
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					<td><rp:ResourcePrivilege resouceType="a"
							resourceId="updatespreadIndexButton" resouceName="修改"
							resouceClass="easyui-linkbutton"
							dataOption="iconCls=\"icon-pencil\" plain=\"true\"" /></td>
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					<td><rp:ResourcePrivilege resouceType="a"
							resourceId="deletespreadIndexButton" resouceName="删除"
							resouceClass="easyui-linkbutton"
							dataOption="iconCls=\"icon-delete\" plain=\"true\"" /></td>
				</tr>
			</table>
		</div>
		<div id="tabs_info" data-options="border:false">
			<div title="进行中"></div>
			<div title="一周内结束"></div>
			<div title="已结束"></div>
		</div>
		</div>
		<div data-options="region:'center',border:false">
		<table id="J_spreadContent" toolbar="#toolbar"
			data-options="border:false" fit="true"></table>
		</div>
		</div>
	</div>

	<!-- 对话框 -->
	<div id="d_add">
		<form id="fmAdd">
			<div class="easyui-tabs" data-options="border:false">
				<div title="店铺信息" style="padding-top: 5px;">
					<label style="margin-left: 5px;">店铺编号：</label><input name="storeId"
						id="storeId" style="width: 100px;" type="text"
						class="easyui-textbox" data-options="required:true"
						missingMessage="必填" /> <a id="btnStoreSearch" href="javascript:;"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-bulletmagnify'">查找</a>
					<fieldset style="margin: 5px; border-color: #E8F1FF;">
						<legend>店铺相关信息</legend>
						<table>
							<tr>
								<td align="right"><label>店铺名称：</label></td>
								<td><input style="border: none; width: 480px;" readonly
									name="storeName" type="text" /></td>
							</tr>
							<tr>
								<td align="right"><label>店铺主营：</label></td>
								<td><input style="border: none; width: 480px" readonly
									name="descriptionEx" type="text" /></td>
							</tr>
							<tr>
								<td align="right"><label>店铺地址：</label></td>
								<td><input style="border: none; width: 480px" readonly
									name="address" type="text" /></td>
							</tr>
						</table>
					</fieldset>

				</div>
				<div title="推广信息">
					<table style="margin: 5px;">

						<tr>
							<td align="right"><label>推广省份：</label></td>
							<td><input name="province" id="provinceContorl"
								style="width: 180px;" type="text" data-options="required:true"
								missingMessage="必选" /></td>
							<td align="right"><label>开始日期：</label></td>
							<td><input name="openTime" type="text"
								data-options="required:true" missingMessage="必选"
								class="easyui-datebox" /></td>


						</tr>

						<tr>
							<td align="right"><label>推广城市：</label></td>
							<td><input name="cityId" id="cityIdContorl"
								style="width: 180px;" type="text" data-options="required:true"
								missingMessage="必选" /></td>
							<td align="right"><label>结束日期：</label></td>
							<td><input name="endTime" type="text" class="easyui-datebox"
								data-options="required:true" missingMessage="必选" /></td>
						</tr>
						<tr>
							<td align="right"><label>推广区域：</label></td>
							<td><input name="zoneId" type="text" id="zoneIdControl"
								style="width: 180px;" class="easyui-combobox" /></td>
							<td align="right"><label>推广大类：</label></td>
							<td><input name="cateMId" style="width: 180px;" type="text"
								id="cateMIdControl" /></td>
						</tr>
						<tr>
							<td align="right"><label>推广商圈：</label></td>
							<td><input name="regionId" type="text" id="regionIdControl"
								style="width: 180px;" class="easyui-combobox" /></td>
							<td align="right"><label>推广小类：</label></td>
							<td><input name="cateSIds" type="text" style="width: 180px;"
								id="cateSIdsControl" /></td>

						</tr>
						<tr>
							<td align="right"><label>推广距离：</label></td>
							<td><input name="distance" type="text" style="width: 80px"
								class="easyui-numberbox" /><label>米</label></td>
							<td align="right"><label>费用：</label></td>
							<td><input name="price" style="width: 80px" type="text"
								class="easyui-numberbox" data-options="required:true"
								missingMessage="必填" /><label>元</label></td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>

	<div id="d_edit" style="visibility:hidden;">
		<form id="fmEdit">
			<div class="easyui-tabs" data-options="border:false">
				<div title="店铺信息" style="padding: 5px;">
					<table>
						<tr>
							<td><label align="right">店铺编号：</label></td>
							<td><input name="storeId"  style="border: none; width: 480px;" readonly
								type="text" /></td>
						</tr>
						<tr>
							<td align="right"><label>店铺名称：</label></td>
							<td><input style="border: none; width: 480px;" readonly
								name="storeName" type="text" /></td>
						</tr>
						<tr>
							<td align="right"><label>店铺主营：</label></td>
							<td><input style="border: none; width: 480px" readonly
								name="descriptionEx" type="text" /></td>
						</tr>
						<tr>
							<td align="right"><label>店铺地址：</label></td>
							<td><input style="border: none; width: 480px" readonly
								name="address" type="text" /></td>
						</tr>
					</table>
				</div>
				<div title="推广信息">
					<table style="margin: 5px;">
						<tr>
							<td align="right"><label>推广城市：</label></td>
							<td>
							<input name="cityId" type="hidden" />
							<input name="spreadId" type="hidden" />
							<input style="border: none;" name="cityInfo" type="text" readonly/>
							</td>
							<td align="right"><label>开始日期：</label></td>
							<td><input name="openTime" type="text"
								data-options="required:true" missingMessage="必选"
								class="easyui-datebox" /></td>
						</tr>
						<tr>
							<td align="right"><label>推广区域：</label></td>
							<td><input name="zoneId" type="text" id="zoneControlEdit"
								style="width: 180px;"  /></td>
							<td align="right"><label>结束日期：</label></td>
							<td><input name="endTime" type="text" class="easyui-datebox"
								data-options="required:true" missingMessage="必选" /></td>
						</tr>
						<tr>
							<td align="right"><label>推广商圈：</label></td>
							<td><input name="regionId" type="text" id="regionControlEdit"
								style="width: 180px;" /></td>
							<td align="right"><label>推广大类：</label></td>
							<td><input name="cateMId" style="width: 180px;" type="text"
								id="cateMIdControlEdit" /></td>
						</tr>
						<tr>
							<td align="right"><label>推广距离：</label></td>
							<td><input name="distance" type="text" style="width: 80px"
								class="easyui-numberbox" /><label>米</label></td>
							<td align="right"><label>推广小类：</label></td>
							<td><input name="cateSIds" type="text" style="width: 180px;"
								id="cateSIdsControlSEdit" /></td>

						</tr>
						<tr>

							<td align="right"><label>费用：</label></td>
							<td colspan="3"><input name="price" style="width: 80px"
								type="text" class="easyui-numberbox"
								data-options="required:true" missingMessage="必填" /><label>元</label></td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>

</body>
</html>