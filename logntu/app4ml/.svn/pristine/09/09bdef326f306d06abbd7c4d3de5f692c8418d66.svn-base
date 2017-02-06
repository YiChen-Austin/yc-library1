$(document).ready(function() {
	$("#passwordchange").validationEngine( {
		validationEventTriggers : "keyup", // 触发的事件
											// validationEventTriggers:"keyup
											// blur",
		inlineValidation : true,// 是否即时验证，false为提交表单时验证,默认true
		success : false,// 为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
		promptPosition : "centerRight",// 提示所在的位置，topLeft, topRight,
										// bottomLeft, centerRight, bottomRight
		failure : function() {
		},// 验证失败时调用的函数
		success : function() {
			callSuccessFunction();
		}// 验证通过时调用的函数
	});
});
$(function() {
	$("#save").click(function() {
		var path=$("#path",parent.document).val()+"/admin";
		if (!passwordCompare())
			return false;
		$.ajax( {
			async : false,
			url : path+'/userPasswordChange?password='+ $('#initPassword').val() + '&newpassword=' + $('#newpassword').val(),
			type : 'POST',
			dataType : 'json',
			timeout : 1000,
			success : function(data) {
				if (data.result) {
					alert("修改密码成功!");
					
				} else {
					alert("修改密码失败!");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("修改密码失败!");
			}

		});		
	});

});
function passwordCompare() {
	var newpassword1 = $("#newpassword").val();
	var newpassword2 = $("#confirmPassword").val();
	if (newpassword1 != newpassword2) {
		$("#passwordCompare").attr("innerHTML",
				"<li><span><font color='red'>两次密码不一致!</font></span></li>");
		$("#passwordCompare").show();
		return false;
	}
	return true;

}
function testoldpassword() {
	$.ajax( {
		async : false,
		url : $("#path",parent.document).val()+"/admin/"+'findUserLoginInfo',
		type : 'POST',
		dataType : 'json',
		timeout : 1000,
		success : function(data) {
			if (data) {
				var oldPassword = data.oldPassword;
				var initPassword = $('#initPassword').val();
				if (oldPassword != $("#initPassword").val()) {
					$("#passwordCompare").attr("innerHTML",
							"<li><span><font color='red'>请输入正确的原始密码</font></span></li>");
					$("#passwordCompare").show();
					return false;
				}else{
					$('#newpassword').removeAttr('disabled');
					$('#confirmPassword').removeAttr('disabled');
					$("#passwordCompare").hide();
				}
				return true;
				
			} else {
				jAlert('加载密码失败!');
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			jAlert('加载密码失败!');
		}

	});
}