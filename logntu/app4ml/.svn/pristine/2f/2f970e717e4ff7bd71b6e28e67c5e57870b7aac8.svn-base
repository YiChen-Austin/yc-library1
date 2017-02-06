$(function() {
	$("#backActivityButton").click(function() {
		window.location =  $("#path",parent.document).val()+"/page/activityAdmin/activity_lr_list";
	});
	$("#saveActivitySubmit").click(function() {
		var info='';
		if ($("#eventTitle").val()== '' || $("#eventTitle").val() == null){
			info="请填写活动主题";
		}
		if ($("#endTime").val()== '' || $("#endTime").val() == null){
			if(info=='')
				info="请选择结束时间";
			else
				info+="、结束时间";
		}
		if (info==''){
			$("#activityDetail").attr('action',  $("#path",parent.document).val()+'/activityAdmin/saveActivity');
			document.forms["activityDetail"].submit();
		}else {
			alert(info);
		}
	});
	//获取经纬度
	$("#mapid").bind('click', function() {
		map = art.dialog.open('/activityAdmin/map', {
			width : '630px',
			height : '530px',
			title : '通过详细地址查找经纬度'
		});
	});
});
function setLanLng(value, lng, lat) {
	map.close();
	if (value != null && value != '') {
		//cleanRegion();
		$("#lngx").val(lng);
		$("#latx").val(lat);
		$("#lanlng").html("("+value+")");
	}		
}