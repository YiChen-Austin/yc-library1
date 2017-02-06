//当前选中节点
var node;

$(function() {
	// 登录人员所在组织机构ID
	var organizationId = $("#organizationId").val();
	// ajax调用不使用缓存
	$.ajaxSetup( {
		cache : false
	});
	$("#sysOrganization_tree").tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
				types : {

					"default" : {
						draggable : true
					}
				// 默认设置关闭拖放

				},
				data : {
					type : "json",
					async : true,
					opts : {
						async : true,
						method : "POST",
						url : $("#path",parent.document).val()+"/admin/"+"findAllSysOrganizations"// 实际提交的Action
					}
				},
				lang : {
					loading : "菜单加载中……" // 在用户等待数据渲染的时候给出的提示内容，默认为loading
			},
			ui : {
				theme_name : 'classic' // 更换皮肤，设置样式
			},
			callback : {
				beforemove : function(NODE, REF_NODE, TYPE, TREE_OBJ) {
					if ($(REF_NODE).attr("parentId") == ""||$(REF_NODE).attr("id") == organizationId) {
						jAlert("不允许拖拽到根节点");
						return false;
					}
					return true;
				},
				onmove : function(NODE, REF_NODE, TYPE, TREE_OBJ, RB) {
					// TYPE 通过type获取移动到的节点的相对位置，
				// 分别为： "before", "after" ，"inside"
				// REF_NODE 移动到的某个节点
				// NODE 原移动的节点

				if (confirm("确定要调整位置？") == false) {
					$.tree.rollback(RB);
					return;
				}
				// 操作失败就进行回滚
				if (moveNode(NODE, REF_NODE, TYPE) == false) {
					$.tree.rollback(RB);
					jAlert("菜单位置调整失败");
				} else {
					// jAlert("菜单位置调整成功");
				}
			},
			onload : function(t) {
				t.settings.data.opts.static = false;
			}
			},
			// 插件使用右键菜单支持自定义右键菜单
				plugins : {}

			});
	
	
	function moveNode(oldNODE, newNODE, TYPE) {
		var flag = false;
		$.ajax( {
			type : "POST",
			cache : false,
			async : false,// 设置异步为false,重要！
			dataType : "json",
			url : $("#path",parent.document).val()+"/admin/"+"moveSysOrganization",
			data : "oldNodeId=" + oldNODE.id + "&newNodeId=" + newNODE.id
					+ "&changeType=" + TYPE,
			success : function(json) {
				flag = json.result;
			}
		});
		return flag;

	}

});
