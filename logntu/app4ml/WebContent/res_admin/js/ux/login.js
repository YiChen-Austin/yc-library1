var path;
$(function() {
	path = $('#path').val();
	$("#loginButton").click(loginSubmit);
	$("#resetButton").click(function() {
		$('#loginForms').form('clear');
	});
	$("#chngVerification").click(
			function() {
				$("#verification").attr(
						'src',
						path + "/admin/vcode?_d="
								+ new Date().getMilliseconds());
			});
});

function loginSubmit() {
	// 表单提交
	$('#loginForms').form('submit', {
		url : path + '/admin/login',
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(value) {
			var data = eval('(' + value + ')'); 
			//var data = JSON.parse(value); //由JSON字符串转换为JSON对象
			if (data&& data.success == false) {
				$.messager.alert('提示', data.msg, 'error');
				$("#verification").attr(
						'src',
						path + "/admin/vcode?_d="
								+ new Date().getMilliseconds());
			} else if (data&& data.success == true) {
				window.location.href = path + "/admin/main";
			} else {
				$.messager.alert('提示', '登录失败!', 'info');
				$("#verification").attr(
						'src',
						path + "/admin/vcode?_d="
								+ new Date().getMilliseconds());
			}
		}
	});
}
/**
 * 按回车键登录
 * 
 */
function enterLogin() {
	var event = arguments.callee.caller.arguments[0] || window.event;// 消除浏览器差异
	if (event.keyCode == 13) {
		loginSubmit();
	}
}
