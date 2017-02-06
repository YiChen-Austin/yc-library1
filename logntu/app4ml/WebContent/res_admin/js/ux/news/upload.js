//检查是否为正确格式
function checkPic() {
	var file = $("#pic").val();
	if (!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(file)) {
		alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
		file = $("#pic");
		file.after(file.clone());
		file.remove();

	}

}
// 上传
function uploadPic() {
	var file = $("#pic").val();
	if (file.length == 0) {
		alert("未选择图片！");
		return false;
	}
	$("#picform").ajaxSubmit({
		type : "POST",
		datatype : "json",
		// url : "uploadPicture?" +encodeURI($("#picform").ajaxForm()
		// .formSerialize()),
		url : "uploadPicture",
		success : function(data) {
			 window.returnValue = eval(data);
			 window.close();
		}

	});
}
//function getPath(obj) {
//	if (obj) {
//		if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
//			obj.select();
//			return document.selection.createRange().text;
//		} else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
//			if (obj.files) {
//				return obj.files.item(0).getAsDataURL();
//			}
//			return obj.value;
//		}
//		return obj.value;
//	}
//}
