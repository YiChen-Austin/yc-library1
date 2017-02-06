<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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

<link href="<%=path%>/css/main.css" rel="stylesheet" />
<link href="/themes/css/images/favicon.ico" rel="SHORTCUT ICON">
<link href="/themes/css/images/favicon.ico" rel="apple-touch-icon">

<script type="text/javascript" src="<%=path%>/js/ux/main.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge" />
</head>
<body class="top" style="overflow: hidden">
	<div id="topMenu" class="topbg"><input type="hidden" id="path" value="<%=path_ex%>" /></div>
	<div id="wrapper">
		<div id="left" class="left">
			<div id="leftHeader" class="leftHeader">应用菜单管理</div>
			<div id="leftContent" class="leftContent"
				style="width: 166px; overflow: auto;">
				<div id="leftMenu">
				</div>
			</div>
		</div>
		<div id="main" class="main">
			<table width="100%" cellspacing="0px" cellpadding="0px">
				<tr>
					<td><img id="optBar" height="29" width="8" align="middle"
						src="<%=path%>/images/main/closeBar.jpg"
						style="display: none" /></td>
					<td width="100%">
						<div class="rightHeader">
							&nbsp;&nbsp;<span id="mainHeader" class="text">当前操作：首页</span><a><span
								class="loginMessage">&nbsp;&nbsp;&nbsp;&nbsp;欢迎您，${loginUser.realName }&nbsp;所在机构：${loginUser.organizationName }&nbsp;&nbsp;</span></a>
							<a href='<%=path_ex%>/admin/exit'><span class="exitLogin">[退出系统]</span></a>
						</div>
						<div id="divFrame" class="ui-widget-content" style="border: 1px solid #a6c9e2;">
							<iframe scrolling="auto"  id="iframeContent" height="100%" src="<%=path_ex%>/page/desktopCenter" frameborder="0" width="100%"></iframe>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
	</div>
	<script type="text/javascript" language="javascript"
		src="<%=path%>/js/ux/footer.js" charset="utf-8">		
	</script>
</body>
</html>
