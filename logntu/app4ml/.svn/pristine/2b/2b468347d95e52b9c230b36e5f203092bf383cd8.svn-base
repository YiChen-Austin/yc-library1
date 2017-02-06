$(document).ready(function() {
	$("#passwordchange").validationEngine({
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
	$("#update").click(
			function() {
				if (!checkname()) {
					return false;
				}
				$.ajax({
					url : $("#path",parent.document).val()+"/admin/"+'persionchange?'
							+ encodeURI($("#passwordchange").ajaxForm()
									.formSerialize()),
					type : 'POST',
					dataType : 'json',
					timeout : 1000,
					success : function(data) {
						if (data.result) {
							alert("修改 个人信息成功!");
							parent.location = "../index.jsp";
						} else {
							alert("修改个人信息失败!");
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("修改个人信息失败!");
					}

				});
			});

});
function checkname() {
	var realName = $("#realName").val();
	if (realName == null) {
		return false;
	}else{
		return true ;
	}
}