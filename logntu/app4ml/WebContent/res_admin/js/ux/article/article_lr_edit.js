$(function() {
	$("#backArticleButton").click(function() {
		window.location =  $("#path",parent.document).val()+"/page/articleAdmin/article_lr_list";
	});
	$("#saveArticleSubmit").click(function() {
		var info='';
		if ($("#title").val()== '' || $("#title").val() == null){
			info="请选择标题";
		}
		if ($("#categoryId").val()== '' || $("#categoryId").val() == '-1'){
			if(info=='')
				info="请选择分类";
			else
				info+="、分类";
		}
		if ($("#artAbstract").val()== '' || $("#artAbstract").val() == null){
			if(info=='')
				info="请选择摘要";
			else
				info+="、摘要";
		}
		if (info==''){
			$("#articleDetail").attr('action',  $("#path",parent.document).val()+'/articleAdmin/saveArticle');
			$("#artContent").val(UM.getEditor('myEditor').getContent());
			document.forms["articleDetail"].submit();
		}else {
			alert(info);
		}
	});
});