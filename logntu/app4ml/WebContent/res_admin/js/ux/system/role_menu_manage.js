$(function() {
	// 角色信息grid
	var roleId = '';
	//角色默认标志位
	var roleFlag = '1';
	//默认系统角色包含的菜单节点ID数组
	var defaultMenuIDs = new Array();
	jQuery("#roleInfoList").jqGrid(
			{
				url : $("#path",parent.document).val()+"/admin/"+'findAllSysRoles',
				datatype : "json",
				colNames : [ '角色名', '备注','标志位' ],
				colModel : [ {
					name : 'name',
					index : 'name',
					width : 120,
					sortable : false
				}, {
					name : 'remark',
					index : 'remark',
					width : 240,
					sortable : false
				}, {
					name : 'flag',
					index : 'flag',
					width : 20,
					editable : true,
					edittype : "text",
					sortable : false,
					hidden : true
				} ],
				multiselect : false,
				onSelectRow : function(id) {
					roleId = jQuery("#roleInfoList").jqGrid('getGridParam',
							'selrow');
					roleFlag = jQuery("#roleInfoList").getRowData(roleId).flag;
					$("#menuTreeInfo a").removeClass();
					$.ajax( {
						type : "POST",
						url : $("#path",parent.document).val()+"/admin/"+"findSysMenusBySysRoleId",
						data : "id=" + roleId,
						success : function(value) {
							var data = eval('(' + value + ')'); 
						    var tempMenuIDs = new Array();
							$.each(data.sysMenuBeans, function(i, item) {
								$.tree.focused().open_branch(getNodeId(item.id));
								$.tree.reference("#" + item.id).get_node(
										"#" + item.id).children("a")
										.removeClass("unchecked").addClass(
												"checked");
								if(item.flag=='0')
									tempMenuIDs[i] = item.id;
							});
							defaultMenuIDs = tempMenuIDs;
						}
					});
				},

				rowNum : 1000,
				height : 428,
				width : 400,
				caption : "角色列表",
				jsonReader : {
					// 这个元素指明表格所需要的数据从哪里开始
					root : "sysRoleBeans",
					page : "pageBean.curPage", // 当前页
					total : "pageBean.totalPages", // 总页数
					records : "pageBean.totalRecords", // 总记录数
					rows : "pageBean.pageSize",// 每页显示的行数
					sort : "pageBean.sort",// 排序字段
					order : "pageBean.order",// 排序方式
					// 指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素,这个名字就是colModel中的名字
					repeatitems : false
				},

				prmNames : {
					rows : "pageSize",
					page : "curPage",
					total : "totalPages",
					records : "totalRecords",
					sort : "orderBy",
					order : "order"
				}
			});
	$.ajaxSetup( {
		cache : false
	});// ajax调用不使用缓存
	$("#menuTreeInfo").tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
				types : {
					"default" : {
						draggable : false
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
				theme_name : 'checkbox' // 更换皮肤，设置样式
			},
			callback : {
				onload : function(t) {
					t.settings.data.opts.static = false;
				}
			},
			// 插件使用右键菜单支持自定义右键菜单
				plugins : {
					checkbox : {
						// 是否选择子节点保证父节点也被选中
				three_state : false
			}
		}

			});

	$("#submitBtn").click(
			function() {
				if (roleFlag == '0') {
					var unchecked = jQuery.tree.plugins.checkbox
							.get_unchecked();
					for ( var i = 0; i < unchecked.size(); i++) {
						if ($(unchecked[i]).attr("flag") + "\n" == 0
								|| $(unchecked[i]).attr("flag") + "\n" == "0") {
							jAlert("禁止取消系统角色的默认菜单,请重新设置!\n" + "相关菜单为："
									+ $(unchecked[i]).text());
							return;
						}

					}

				}
				$.ajax( {
					type : "POST",
					url : $("#path",parent.document).val()+"/admin/"+"setSysMenu",
					data : "id=" + roleId
							+ "&sysMenuIDs=" + getCheckedNode(),
					success : function(data) {
						if (data) {
							jAlert('设置成功！');
						} else {
							jAlert('设置失败！');
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						jAlert('设置失败！');
					}
				});
			});
});
/**
 * 获取选中节点及其直接和间接父节点的方法
 */
function getCheckedNode() {
	var ids = "";
	//选中节点父节点集合
	var objct = jQuery.tree.plugins.checkbox.get_undeterminded();
	for (i = 0; i < objct.size(); i++) {
		ids += objct[i].id + ",";
	}
	//选中节点集合
	objct = jQuery.tree.plugins.checkbox.get_checked();
	for (i = 0; i < objct.size(); i++) {
		ids += objct[i].id + ",";
	}
	return ids.substring(0,ids.length-1);
}
//获取选中节点中包含系统权限的菜单节点数
function getSelectedSysRoleNodeLen(){
	var sum= 0;
	//选中节点父节点集合
	var objct = jQuery.tree.plugins.checkbox.get_undeterminded();
	for (i = 0; i < objct.size(); i++) {
		if(objct[i].flag=='0')
		 sum=sum + 1;
	}
	//选中节点集合
	objct = jQuery.tree.plugins.checkbox.get_checked();
	for (i = 0; i < objct.size(); i++) {
		if(objct[i].flag=='0')
			sum = sum + 1;
	}
	return sum;
	
}
function getNodeId(nodeId) {
	return "#" + nodeId + "";
}