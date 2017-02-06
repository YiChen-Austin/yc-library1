
$(function() {
	$.ajax( {
		dataType : 'json',
		url : $("#path",parent.document).val()+"/admin/"+"shortcutMenu",
		cache : false,
		ansync : false,
		success : function(data) {

			var ids = [];
			$.each(data.mainMenuList, function(i, item) {
				ids.push(item.menuID);

			});
			initree(ids);
			
		}
	
	});
	$("#subBtn").click(function(){
		var obj = jQuery.tree.plugins.checkbox.get_checked();
//		if(obj.length>10){
//			jAlert('最多可设置10个!');
//			return;
//		}
		//jAlert($("div li").size());
		$.ajax({
			url:$("#path",parent.document).val()+"/admin/"+'addmenu?ids='+getnode(),
			success:function(){
			jAlert('设置成功');
			}
		});
	});
});
function initree(ids) {
	$.ajaxSetup( {
		cache : false
	});// ajax调用不使用缓存`
	$("#checkboxtree_id").tree( {// async_json_1 为 由Jquery选择器选中的DIV容器的ID
				data : {
					type : "json",
					async : false,
					opts : {
						async : false,
						method : "POST",
						url : $("#path",parent.document).val()+"/admin/"+"shortcutMenu"// 实际提交的Action
					}
				},
				lang : {
					loading : "目录加载中……" // 在用户等待数据渲染的时候给出的提示内容，默认为loading
			},
			ui : {
				theme_name : 'checkbox' // 更换皮肤，设置样式
			},
			callback : {
				onselect : function(NODE, TREE_OBJ) {
					if($(NODE).attr('pageurl')==''){
						jAlert('非功能性菜单，不能设为快捷菜单');
							$(NODE).children("a").removeClass();
						}
					},onchange : function(NODE, TREE_OBJ) {
						if($(NODE).attr('pageurl')==''){
							$(NODE).children("a").removeClass();
						}
					}
				},
				
			// 插件使用
				plugins : {
					checkbox : {
						// 是否选择子节点保证父节点也被选中
				three_state : false

			}
		},
		selected : ids
			});
	
};

/**
 * 获取选中节点的方法
 */
function getnode() {
	var objct = jQuery.tree.plugins.checkbox.get_checked();
	var ids = "";
		for (i = 0; i < objct.size(); i++) {
			ids += objct[i].id + ";";
		}
		if (ids.substring(ids.length - 1, ids.length) == ";") {
			ids = ids.substring(0, ids.length - 1);
		}
	return ids;
}
/**
 * 首页显示快捷菜单
 */
var imagePath="";
$(function() {
	$.ajax( {
		dataType : 'json',
		url : $("#path",parent.document).val()+"/admin/"+"selecshortcutMenu",
		cache : false,
		ansync : false,
		success : function(data) {
		imagePath=data.imagePath;

			$.each(data.mainMenuList, function(i, item) {
				$("#list").append(
						$("<a >"+""+item.menuName+""+"</a>").attr({href: $("#path",parent.document).val()+"/"+item.menuUrl})).append("<br/>");
							//$("<a >"+"<img src='"+imagePath+"'>"+item.menuName+"</img>"+"</a>").attr({href:contextPath+"/"+item.menuUrl})).append("<br/>");
				
			});
		}
	});
});


