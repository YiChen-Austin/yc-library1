var contextPath = '';
var WINDOW_WIDTH = document.documentElement.clientWidth * 1.02;
var WINDOW_HEIGHT = document.documentElement.clientHeight * 1.2;
$(function() {
	/**
	 * 用户非正常退出时开始 $(window).unload(function() { if ($.browser.msie) { if
	 * (window.event.clientY < 0 || window.event.altKey) { $.ajax( { url :
	 * "exitLogin" }); } } else { var clHeight = document.body.clientHeight; if
	 * (clHeight == 0) { $.ajax( { url : "exitLogin" }); } } }); 用户非正常退出时结束 *
	 */
	/**
	 * 消息提醒开始 
	 */
	//GetOrderCount(); 
	//window.setInterval("GetOrderCount()",30000);
	 /* 消息提醒结束
	 */
//	$("#wrapper").width(WINDOW_WIDTH * 0.98);
//	$("#main").width(WINDOW_WIDTH * 0.85);
//	$("#left").width(WINDOW_WIDTH * 0.128);
	$("#divFrame").height(WINDOW_HEIGHT * 0.68);
	$("#leftContent").height(WINDOW_HEIGHT * 0.68);
//	var mainWidth = $("#main").width();
//	var leftWidth = $("#left").width();
	var mainWidth=WINDOW_WIDTH * 0.85;
	var leftWidth =WINDOW_WIDTH * 0.128;
//	$("#leftContent").width(WINDOW_WIDTH * 0.126);
	$.ajax( {
		dataType : 'json',
		url : "initTopMenu",
		type : 'GET',
		cache : false,
		ansync : false,
		success : function(data, t) {
			$.each(data.topMenu, function(i, item) {
				$("#topMenu").append(
						$("<span><a href='#'>" + item.menuName + "</a></span>").attr( {
									id : item.menuId
								}).click(function() {
											if (i == 0) {
												var src = $("#optBar").attr('src');
												//$("#main").width(mainWidth + leftWidth);
												$("#main").removeClass("mainTemp1").addClass("mainTemp2");
												$("#optBar").attr('src',src.replace("openBar","closeBar"));
												$("#optBar").hide();
											} else {
												//$("#main").width(mainWidth);
												$("#main").removeClass("mainTemp2").addClass("mainTemp1");
												$("#left").show();
												$("#leftHeader").text(item.menuName.replace("&nbsp;", ""));
												$("#optBar").show();
												chiceMenu(item.menuId,item.menuName);
											}
										}));
			});			
			/** 一级菜单选中与非选中样式开始 */
			$("#topMenu").width((mainWidth + leftWidth) * 0.87);
			$("#topMenu span:first").addClass("selected");
			$("#topMenu span").click(function() {
				$("#topMenu span").removeClass("selected");
				$(this).addClass("selected");
			}).blur(function() {
				$(this).addClass("selected");
			});
			$("#topMenu").append('<p class="address">'+data.subWeb+'</p>');
			/** 一级菜单选中与非选中样式结束 */
			/** 首页按钮事件开始 */
			$("#topMenu A:first").click(
					function() {
						var path=$('#path').val();
						$("#iframeContent").attr('src',path + "/page/desktopCenter");
						$("#mainHeader").text("当前操作：首页");
						$("#left").hide();
						//$("#main").width(mainWidth + leftWidth);
						$("#main").removeClass("mainTemp1").addClass("mainTemp2");
					});
			/** 首页按钮事件结束 */
			/** 首页默认处理开始 */
			$("#left").toggle(false);
			//$("#main").width(mainWidth + leftWidth);
			$("#main").removeClass("mainTemp1").addClass("mainTemp2");
			/** 首页默认处理结束 */
		}
	});
	/** 隐藏或显示左边菜单树开始 */
	var flip = 1;
	$("#optBar").click(function(val) {
		var src = $("#optBar").attr('src');
		$("#left").toggle(flip++ % 2 == 0);
		if (flip % 2 == 0) {
			$("#optBar").attr('src', src.replace("closeBar", "openBar"));
			//$("#main").width(mainWidth + leftWidth);
			$("#main").removeClass("mainTemp1").addClass("mainTemp2");
		} else {
			$("#optBar").attr('src', src.replace("openBar", "closeBar"));
			//$("#main").width(mainWidth);
			$("#main").removeClass("mainTemp2").addClass("mainTemp1");
		}
	});
	/** 隐藏或显示左边菜单树结束 */
});
/** 根据选择的导航条取得左边的树 */
function chiceMenu(sid, name) {
	var path=$('#path').val();
	$("#leftMenu").tree({
		url:path+'/admin/findLeftMenu/'+sid,
		method: 'get',
		animate: true,
		lines:true,
		onClick: function(node){
			if(node.attr.pageUrl!=''){
			   $("#mainHeader").text("当前操作：" + $.trim(node.text));
			   $("#iframeContent").attr('src',path +node.attr.pageUrl);
			}
		},
		onDblClick: function(node){
			if(node.state=="closed"){
				$("#leftMenu").tree('expand',node.target);
			}else if(node.state=="open"){
				$("#leftMenu").tree('collapse',node.target);
			}
		},
		onLoadSuccess:function(node,data){			
			var obj=$("#leftMenu .tree-file:first");
			var tg=obj.parent();
			if(tg!=null){
				tg.addClass("tree-node-selected");
				tg.click();
			}
		}
	});
}

/**
 * 消息提醒
 */
function GetOrderCount() {
	$
			.ajax( {
				type : "POST",
				dataType : "json",
				url : "getPersonNotReadMsg",
				success : function(data) {
					if (data.result == true) {
						var taskHtml = "代办事项【<font color=red>"
								+ data.messageBeans.length + "</font>】条:";
						$.messager.lays(180,260);
						$.messager.anim('slide', 2000);
						$
								.each(
										data.messageBeans,
										function(i, item) {
											taskHtml += "<br/>●&nbsp;&nbsp;<a href='#' onclick=doBusiness('"
													+ item.href
													+ "','"
													+ item.id
													+ "') style='font-size:14px;font-weight:400;font-family:黑体;'>"
													+ item.content
													+ "</a>";
										});
						$.messager.show('<font color=red>消息提醒</font>',
								taskHtml, 10000);
					}
				}
			});
}
function doBusiness(href, messageId) {
	$.ajax( {
		type : "POST",
		dataType : "json",
		url : "readMsg?messageBean.id=" + messageId,
		success : function(data) {
			$("#iframeContent")
					.attr('src', contextPath + "/page/desktopCenter");
			if (data.result == true && href != "null" && href != '') {
				$("#iframeContent").attr('src', contextPath + "/" + href);
			}
		}
	});
}
