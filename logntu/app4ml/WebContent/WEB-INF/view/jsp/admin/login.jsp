<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./head/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title><%=projectTitle%></title>

<link href="<%=path%>/css/cssreset.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/jquery-easyui-1.4.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/jquery-easyui-1.4.5/themes/icon.css">
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-easyui-1.4.5/easyloader.js"></script>

<link href="<%=path%>/css/login.css" rel="stylesheet">
<link href="/themes/css/images/favicon.ico" rel="SHORTCUT ICON">
<link href="/themes/css/images/favicon.ico" rel="apple-touch-icon">


<script type="text/javascript" src="<%=path%>/js/ux/login.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
	<div id="box">
		<div class="boxBody">
			<div id="posts">
				<form id="loginForms" class="easyui-form" method="post" onkeydown="enterLogin();">
					<table width="227" height="100" border="0" cellpadding="0"
						cellspacing="0" class="loginbox">
						<tr>
							<td width="53" align="center" style="color: #363737;">用户名：</td>
							<td colspan="2" align="left"><input id="user_name"
								type="text" name="userName"
								class="loginipt easyui-validatebox"
								data-options="required:true,validType:'length[3,50]',missingMessage:'请输入用户名',invalidMessage:'请输入3-50位的用户名'" />
								<input type="hidden" id="path" value="<%=path_ex%>" /></td>
						</tr>
						<tr>
							<td width="53" align="center" style="color: #363737;">密&nbsp;&nbsp;码：</td>
							<td colspan="2" align="left"><input id="user_password"
								type="password" name="password"
								class="loginipt easyui-validatebox"
								data-options="required:true,validType:'length[1,50]',missingMessage:'请输入密码',invalidMessage:'密码错误'" /></td>
						</tr>
						<tr>
							<td width="53" align="center" style="color: #363737;">验证码：</td>
							<td align="left"><input type="text"
								name="verification" id="verificationField"
								class="loginipt1 easyui-validatebox"/></td>
							<td align="left"><a id="chngVerification" title="看不清吗？换一张吧!">
									<img src="<%=path_ex%>/admin/vcode" id="verification" border="0"
									style="height: 20px;" />
							</a></td>
						</tr>
					</table>
					<table width="227" border="0" cellspacing="0" cellpadding="0"
						class="btnbar">
						<tr>
							<td align="center"><input name="" type="button"
								class="loginbtn" id="loginButton" /></td>
							<td align="center"><input name="" type="button"
								class="logincancel" id="resetButton" /></td>
						</tr>
					</table>
				</form>
			</div>
			<marquee direction=left scrollamount=6 onmouseover="this.stop()"
				onmouseout="this.start()">
				<font color="red"><strong>系统提醒：用户密码是登陆系统的唯一凭证，请妥善保存。</strong></font>
			</marquee>
		</div>
	</div>
</body>
</html>