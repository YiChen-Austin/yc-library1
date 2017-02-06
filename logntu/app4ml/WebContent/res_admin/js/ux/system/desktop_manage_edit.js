$(document).ready(function() {
	$("#desktopDefend").validationEngine({
	validationEventTriggers:"keyup",  //触发的事件  validationEventTriggers:"keyup blur",
	inlineValidation: true,//是否即时验证，false为提交表单时验证,默认true
	success :  false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
	promptPosition: "centerRight",//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
	failure : function() {},//验证失败时调用的函数
	success : function() {callSuccessFunction();  }//验证通过时调用的函数
	});
});	
$(function() {
	$("#backButton").click(
			function() {
				window.location = $("#path",parent.document).val()+"/page/system/desktop_manage_list";
			}
	);
	$("#saveSubmit").click(
			function() {
				var id = $("#id").val();
				if(id==''){
					$("#desktopDefend").attr('action',$("#path",parent.document).val()+"/page/"+'saveDesktopDefend');
					$("#desktopDefend").attr('method','POST');
				    $("#desktopDefend").submit();
					
				}else{
					$("#desktopDefend").attr('action',$("#path",parent.document).val()+"/page/"+'modifyDesktopDefend');
					$("#desktopDefend").attr('method','POST');
				    $("#desktopDefend").submit();
				}
			}
	);
});