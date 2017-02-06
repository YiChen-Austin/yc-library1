<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/body.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/jquery-easyui-1.4.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="<%=path%>/js/jquery-easyui-1.4.5/themes/icon.css">
		<script type="text/javascript"
			src="<%=path%>/js/jquery-easyui-1.4.5/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
		<title>订单列表</title>
</head>
<body>
	<h2>已退款处理成功文件导入</h2>
	<p>1、导出退款订单列表文件，在第三方进行退款处理.</p>
	<p>2、将处理成功结果填入退款订单导出文件对位位置.</p>
	<p>3、导入已经填写了处理信息的退款文件.</p>
	<div style="margin: 20px 0;"></div>
	<form id="search_form" class="easyui-form" method="post"
		data-options="novalidate:true" enctype="multipart/form-data">
		选择文件： <input id="refund" name="refund" class="easyui-filebox"
			style="width: 200px" data-options="prompt:'请选择文件...'"> <a
			href="#" class="easyui-linkbutton" style="width: 122px"
			onclick="uploadRefund()">导入退款处理信息</a>
	</form>

	<script type="text/javascript">
		function uploadRefund() {
			//得到上传文件的全路径  
			var fileName = $('#refund').filebox('getValue');
			//获取题型  
			//进行基本校验  
			if (fileName == "") {
				$.messager.alert('提示', '请选择上传文件！', 'info');
			} else {
				//对文件格式进行校验  
				var d1 = /\.[^\.]+$/.exec(fileName);
				if (d1 == ".xlsx") {
					//提交表单  
					document.getElementById("search_form").action = "/admin/order/impRefund";
					document.getElementById("search_form").submit();
					$.messager.alert('提示', '操作成功！', 'info');
				} else {
					$.messager.alert('提示', '请选择xls格式文件！', 'info');
					$('#refund').filebox('setValue', '');
				}
			}
		}
	</script>
</body>
</html>