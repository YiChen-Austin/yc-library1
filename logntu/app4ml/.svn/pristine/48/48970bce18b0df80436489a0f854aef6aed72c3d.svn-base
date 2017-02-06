$(function() {
	$("#backNewsButton").click(function() {
		window.location =  $("#path",parent.document).val()+"/page/activityAdmin/activity_report_list";
	});
	$("#saveNewsSubmit").click(function() {
		var info='';
		if ($("#title").val()== '' || $("#title").val() == null){
			info="请选择标题";
		}
		if ($("#artAbstract").val()== '' || $("#artAbstract").val() == null){
			if(info=='')
				info="请选择摘要";
			else
				info+="、摘要";
		}
		if (info==''){
			$("#newsDetail").attr('action',  $("#path",parent.document).val()+'/activityAdmin/saveNews');
			$("#artContent").val(UM.getEditor('myEditor').getContent());
			document.forms["newsDetail"].submit();
		}else {
			alert(info);
		}
	});
});