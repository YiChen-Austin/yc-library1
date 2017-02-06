//更换皮肤

$(function(){
	var peelings='';
	$.ajax({
		url:$("#path",parent.document).val()+"/admin/"+'findSysPeelings',
		method:'POST',
		success:function(data){
		$.each($(data.list),function(i,item){
			$("#themeSwitch").append('<option value="'+item.value+'">'+item.name+'</option>').width('130px');
		});
		peelings=$('#peeling').text();
		$("#themeSwitch").val($('#peeling').text());
	}
	});
	 $("#themeSwitch").change(function(){	 
        var value = $(this).val();
        peelings=value;
        $("link:first").attr("href", $("#root").text()+"/css/theme/" + value + "/jquery-ui-1.8.1.custom.css");
        $(window.parent.document).find("link:first").attr("href", $("#root").text()+"/css/theme/" + value + "/jquery-ui-1.8.1.custom.css");
	 });
	 $("#save").click(function(){
		 $.ajax({
	    		url:$("#path",parent.document).val()+"/admin/"+'peelingChange',
	    		method:'POST',
	    		data:{'value':peelings},
	    		success:function(data){
	    			if(data.result){
	    				jAlert('设置成功！');
	    			}
	    		}
	        });
	 });
	 $("#root").hide();
	 $("#peeling").hide();
});