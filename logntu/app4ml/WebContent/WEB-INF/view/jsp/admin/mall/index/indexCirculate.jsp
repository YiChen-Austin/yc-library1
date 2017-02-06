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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width" />
<title><%=projectTitle%></title>
<link href="<%=path%>/css/cssreset.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/jquery-easyui-1.4.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/jquery-easyui-1.4.5/themes/icon.css" />
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/easyloader.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/commons/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/ux/mall/index/indexCirculate.js"></script>
</head>
<body>
	<div data-options="region:'center',title:'详细项',split:true"
		id="center-info">
		<div id="tb_header" style="margin-bottom: 2px;">
			省份：<input id="provinceSel"/>&nbsp;城市：<input
				 id="citySel"/>
		</div>
		<div id="tb_body">
			<div id="tb" style="padding: 5px; height: auto">
				<div style="margin-bottom: 5px">
					<rp:ResourcePrivilege resouceType="a"
						resourceId="saveIndexCirculateButton" resouceName="新增"
						resouceClass="easyui-linkbutton"
						dataOption="iconCls=\"icon-add\" plain=\"true\"" />
					<rp:ResourcePrivilege resouceType="a"
						resourceId="updateIndexCirculateButton" resouceName="修改"
						resouceClass="easyui-linkbutton"
						dataOption="iconCls=\"icon-pencil\" plain=\"true\"" />
					<rp:ResourcePrivilege resouceType="a"
						resourceId="deleteIndexCirculateButton" resouceName="删除"
						resouceClass="easyui-linkbutton"
						dataOption="iconCls=\"icon-delete\" plain=\"true\"" />
				</div>
			</div>
			<table id="image_table"></table>
		</div>
		<!-- 首页轮播图表单 -->
		<style type="text/css">
		.form_div {
			margin: 5px 0;
		}
		</style>
		<div id="image_win" class="easyui-window" title="新增"
			data-options="iconCls:'icon-database',modal:true,closed:true,width:500,height:240">
			<div id="image_form">
				<input type="hidden" name="indexImageId">
				<input type="hidden" name="cityId">
				<input type="hidden" name="oldImageFile">
				<div class="form_div">
					<label for="cityName">城市：</label> <span id="cityName"></span><!-- <input class="easyui-textbox"
						type="text" name="cityName" data-options="readonly:true"> -->
				</div>
				<div class="form_div">
					<label for="imageFile">图片：</label> <input type="file"
						id="imageFile" name="imageFile">
				</div>
				<div class="form_div">
					<label for="imageOrder">轮播顺序：</label> <input id="imageOrder"
						class="easyui-combobox"
						data-options="width:50,panelHeight:'auto',valueField:'id',textFiled:'text',data:[{id:1,text:'1',selected:true},{id:2,text:'2'},{id:3,text:'3'},{id:4,text:'4'},{id:5,text:'5'},{id:6,text:'6'}]">
				</div>
				<div class="form_div">
					<label for="enabled">是否有效：</label> <input id="enabled"
						class="easyui-combobox"
						data-options="width:50,panelHeight:'auto',valueField:'id',textFiled:'text',data:[{id:0,text:'否',selected:true},{id:1,text:'是'}]">
				</div>
			</div>
			<a id="image_add_ok" class="easyui-linkbutton"
				data-options="iconCls:'icon-accept'">确定</a> <a id="image_add_cancel"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</div>
</body>
</html>