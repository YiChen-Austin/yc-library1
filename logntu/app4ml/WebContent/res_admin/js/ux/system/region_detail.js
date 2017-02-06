$(document).ready(function() {
	$("#region").validationEngine({
	validationEventTriggers:"keyup",  //触发的事件  validationEventTriggers:"keyup blur",
	inlineValidation: true,//是否即时验证，false为提交表单时验证,默认true
	success :  false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
	promptPosition: "centerRight",//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
	failure : function() {},//验证失败时调用的函数
	success : function() {callSuccessFunction();  }//验证通过时调用的函数
	});
});	
$(function() {
	var id= $("#id").val();
	$("#saveButton").click(
		function() {
			if(id==''){
				$("#region").attr("action", $("#path",parent.document).val()+"/admin/"+"saveRegion");
				
			}else{
				$("#region").attr("action", $("#path",parent.document).val()+"/admin/"+"modifyRegion");
			}
			$("#region").attr("method", "post");
			$("#region").submit();
		}
	);
	$("#backButton").click(
			function() {
				window.location = $("#path",parent.document).val()+"/page/system/region_list";
			}
			);	
});
