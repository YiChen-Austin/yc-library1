//当前选中节点
var node;

$(function() {
	// ajax调用不使用缓存
	$.ajaxSetup( {
		cache : false
	});
	$("#menu_tree").tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
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
						url : $("#path",parent.document).val()+"/admin/"+"findAllSysMenus"// 实际提交的Action
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
					if ($(REF_NODE).attr("parentId") == "") {
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
//					jAlert(("菜单位置调整成功");
				}
			},
			onload : function(t) {
				t.settings.data.opts.static = false;
			}
			},
			// 插件使用右键菜单支持自定义右键菜单
				plugins : {}

			});

	/**
	 * 用于获取树节点所在树的父节点里的子节点位置[其实位置为1] 返回-1 表示失败 node -节点对象 operatorType -增加方式
	 * 
	 * @return
	 */
	function getNodeOrderNo(node, operatorType) {
		// 获取该父节点下的所有节点数
		if (operatorType == 'same') {
			var childrenNodes = $(node).parent().children();
			var size = childrenNodes.size();
			for ( var i = 0; i < size; i++) {
				if (childrenNodes[i] == node) {
					return i + 1;
				}
			}
		} else {
			return $(node).children().children().size();
		}
		return -1;

	}

	/**
	 * 移动节点的方法
	 * 
	 * @param oldNODE
	 *            原节点
	 * @param newNODE
	 *            目标节点
	 * @param TYPE
	 *            移动的类型
	 * @return
	 */
	function moveNode(oldNODE, newNODE, TYPE) {
		var flag = false;
		$.ajax( {
			type : "POST",
			cache : false,
			async : false,// 设置异步为false,重要！
			dataType : "json",
			url : $("#path",parent.document).val()+"/admin/"+"moveSysMenu",
			data : "oldNodeId=" + oldNODE.id + "&newNodeId=" + newNODE.id
					+ "&changeType=" + TYPE,
			success : function(json) {
			flag = json.result;
			}
		});
		return flag;

	}

});
