//当前选中节点
var node;
var node1
// 操作状态 [none:默认无操作,same:同级,secondary:次级,modify:修改,delete:删除]
var operatorType = 'none';
// 选中类型[administrator:管理人员;publisher:发布人员]
var selectType = "administrator";
var userId;
var realName;
var instNode;
var orgId;
$(document).ready(function() {
	$("#menuForm").validationEngine({
		validationEventTriggers : "keyup", // 触发的事件
		// validationEventTriggers:"keyup
		// blur",
		inlineValidation : false,// 是否即时验证，false为提交表单时验证,默认true
		success : false,// 为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
		promptPosition : "centerRight",// 提示所在的位置，topLeft, topRight,
		// bottomLeft, centerRight, bottomRight
		failure : function() {
		},// 验证失败时调用的函数
		success : function() {
			callSuccessFunction();
		}// 验证通过时调用的函数
	});
	$("#organizationUserSelect").dialog({
		modal : false,
		width : 800,
		height : 370,
		minHeight : 370,
		minWidth : 800,
		maxHeight : 370,
		maxWidth : 800
	}).dialog("close");
});
$(function() {
	// ajax调用不使用缓存
	$.ajaxSetup({
		cache : false
	});
	/**
	 * 删除选中按钮
	 */
	$('#remove_btn')
			.click(
					function() {
						if (null == $('#choicedAccMan option:selected').get(0))
							$('#choicedAccMan option:first').attr('selected',
									true);
						$('#choicedAccMan option:selected').remove();
						$('#userOriginaList')
								.jqGrid(
										'setGridParam',
										{
											url : 'findSysUsersBySysOrganizationId?sysOrganizationBean.id='
													+ orgId,
											page : 1
										}).trigger("reloadGrid");
					});

	/**
	 * 删除全部按钮
	 */
	$('#remove_all_btn')
			.click(
					function() {
						$('#choicedAccMan').empty();
						$('#userOriginaList')
								.jqGrid(
										'setGridParam',
										{
											url : 'findSysUsersBySysOrganizationId?sysOrganizationBean.id='
													+ orgId,
											page : 1
										}).trigger("reloadGrid");
					});
	$("#sysOrganization_tree").jstree({// menu_tree 为 由Jquery选择器选中的DIV容器的ID
		"themes" : {
			"theme" : "classic",
			"dots" : true,
			"icons" : true
		},
		"json_data" : {
			"ajax" : {
				"url" : "findSelectSysOrganizations",
				"data" : function(n) {
					return {
						id : n.attr ? n.attr("id") : 0
					};
				}
			}
		},
		"core" : {
			"strings" : {
				"loading" : "菜单加载中……"
			}
		},
		// 插件使用右键菜单支持自定义右键菜单
		"plugins" : [ "themes", "json_data", "ui", "crrm" ]
	});

	$("#sysOrganization_tree").bind("select_node.jstree", function(e, data) {
		node1 = data.rslt.obj;
		orgId = node1.attr("id");
		clearUserOrganizationInfo();
		jQuery("#userOriginaList")
		.jqGrid(
				'setGridParam',
				{
					url : "findSysUsersBySysOrganizationId?sysOrganizationBean.id="
							+ orgId ,
					page : 1
				}).trigger("reloadGrid");
	});
	jQuery("#userOriginaList").jqGrid(
			{
				datatype : "json",
				colNames : [ '真实', '性别' ],
				colModel : [ {
					name : 'realName',
					index : 'realName',
					width : 90,
					sortable : false
				}, {
					name : 'gender',
					index : 'gender',
					width : 60,
					sortable : false
				} ],
				onSelectAll : function() {
					var gr = jQuery("#userOriginaList").jqGrid("getGridParam",
							"selarrrow");
					$.each(gr, function(index, obj) {
						var realName = jQuery("#userOriginaList").getRowData(
								obj).realName;
						var _choicedAccMan = document
								.getElementById("choicedAccMan");
						// 组织机构ID-用户ID
						var userId = obj;
						if (!isUserFromSelectedUsers(_choicedAccMan, userId))
							$(
									"<option value='" + userId + "'>"
											+ realName + "</option>").appendTo(
									$(_choicedAccMan));
						$("#selectBtn").removeAttr("disabled");
					});
				},
				onSelectRow : function(id) {
					var userId = jQuery("#userOriginaList").jqGrid(
							'getGridParam', 'selrow');
					var realName = jQuery("#userOriginaList")
							.getRowData(userId).realName;
					userId = userId;
					var _choicedAccMan = document
							.getElementById("choicedAccMan");
					if (!isUserFromSelectedUsers(_choicedAccMan, userId))
						$(
								"<option value='" + userId + "'>" + realName
										+ "</option>").appendTo(
								$(_choicedAccMan));
					$("#selectBtn").removeAttr("disabled");

				},
				/* modify */
				afterInsertRow : function(row_id) {
					// 循环已选人员
					$('#choicedAccMan').children().each(
							function() {
								var option_val = $(this).val();
								if (row_id == option_val)
									// 选中已选的
									$('#userOriginaList').jqGrid()
											.setSelection(row_id, true);
							});
				},
				loadui : 'disable',
				loadError : function(xhr, status, error) {
					isNotLoginError(xhr);
				},
				multiselect : true,
				height : 250,
				width : 300,
				caption : "组织机构人员信息",
				jsonReader : {
					// 这个元素指明表格所需要的数据从哪里开始
					root : "sysUserBeans",
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
	$("#commonCategory_tree").jstree({// menu_tree 为 由Jquery选择器选中的DIV容器的ID
		"themes" : {
			"theme" : "classic",
			"dots" : true,
			"icons" : true
		},
		"json_data" : {
			"ajax" : {
				"url" : "findAllNewsCategories",
				"data" : function(n) {
					return {
						id : n.attr ? n.attr("id") : 0
					};
				}
			}
		},
		"core" : {
			"strings" : {
				"loading" : "菜单加载中……"
			}
		},
		// 插件使用右键菜单支持自定义右键菜单
		"plugins" : [ "themes", "json_data", "ui", "crrm" ]
	});
	// $("#commonCategory_tree")
	// .tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
	// types : {
	// // all node types inherit the "default" node type
	// "default" : {
	// draggable : false
	// }
	// },
	// data : {
	// type : "json",
	// async : true,
	// opts : {
	// async : true,
	// method : "POST",
	// url : "findAllNewsCategories"// 实际提交的Action
	// }
	// },
	// lang : {
	// loading : "菜单加载中……" // 在用户等待数据渲染的时候给出的提示内容，默认为loading
	// },
	// ui : {
	// theme_name : 'classic' // 更换皮肤，设置样式
	// },
	// callback : {
	//
	// onload : function(t) {
	// t.settings.data.opts.static = false;
	// },
	// onselect : function(NODE, TREE_OBJ) {
	// $("#repeatName").hide();
	// operatorType = 'modify';
	// node = NODE;
	// var name = TREE_OBJ.get_text(node);
	// $("#name").attr("value", name);
	// $("#id").attr("value", $(NODE).attr("id"));
	// $("#parentId").attr("value", $(NODE).attr("parentId"));
	// $("#remark").attr("value", $(NODE).attr("remark"));
	// $("#flag").attr("value", $(NODE).attr("flag"));
	// $("#administratorName").attr("value", $(NODE).attr("administratorName"));
	// $("#administratorId").attr("value", $(NODE).attr("administratorId"));
	// $("#publisherName").attr("value", $(NODE).attr("publisherName"));
	// $("#publisherId").attr("value", $(NODE).attr("publisherId"));
	// if ($(NODE).attr("parentId") != '') {
	// $("#menuTable").css('display', 'block');
	// $('#sameLevelCreateButton').removeAttr(
	// 'disabled');
	// $('#secondaryLevelCreateButton')
	// .removeAttr('disabled');
	// $('#deleteButton').removeAttr(
	// 'disabled');
	// $('#saveButton').removeAttr('disabled');
	// } else {
	// $("#menuTable").css('display', 'block');
	// $('#sameLevelCreateButton').attr(
	// 'disabled', 'disabled');
	// $('#secondaryLevelCreateButton')
	// .removeAttr('disabled');
	// $('#deleteButton').attr('disabled',
	// 'disabled');
	// $('#saveButton').removeAttr('disabled');
	// }
	// }
	// },
	// // 插件使用右键菜单支持自定义右键菜单
	// plugins : {}
	//
	// });
	$("#commonCategory_tree").bind("select_node.jstree", function(e, data) {
		$("#repeatName").hide();
		operatorType = 'modify';
		node = data.rslt.obj;
		instNode = data.inst;
		var name = $.trim(instNode.get_text());
		$("#name").attr("value", name);
		$("#id").attr("value", node.attr("id"));
		$("#parentId").attr("value", node.attr("parentId"));
		$("#remark").attr("value", node.attr("remark"));
		$("#flag").attr("value", node.attr("flag"));
		$("#administratorName").attr("value", node.attr("administratorName"));
		$("#administratorId").attr("value", node.attr("administratorId"));
		$("#publisherName").attr("value", node.attr("publisherName"));
		$("#publisherId").attr("value", node.attr("publisherId"));
		if (node.attr("parentId") != '') {
			$("#menuTable").css('display', 'block');
			$('#sameLevelCreateButton').removeAttr('disabled');
			$('#secondaryLevelCreateButton').removeAttr('disabled');
			$('#deleteButton').removeAttr('disabled');
			$('#saveButton').removeAttr('disabled');
		} else {
			$("#menuTable").css('display', 'block');
			$('#sameLevelCreateButton').attr('disabled', 'disabled');
			$('#secondaryLevelCreateButton').removeAttr('disabled');
			$('#deleteButton').attr('disabled', 'disabled');
			$('#saveButton').removeAttr('disabled');
		}

	});
	// 同级新增功能
	$("#sameLevelCreateButton").click(function() {
		operatorType = 'same';
		$("#name").attr("value", "");
		$("#id").attr("value", "");
		$("#parentId").attr("value", node.attr("parentId"));
		$("#remark").attr("value", "");
		$("#flag").attr("value", "news");
		$("#selectId").attr("value", node.attr("id"));
		$("#administratorName").attr("value", "");
		$("#administratorId").attr("value", "");
		$("#publisherName").attr("value", "");
		$("#publisherId").attr("value", "");
	});
	// 次级新增功能
	$("#secondaryLevelCreateButton").click(function() {
		operatorType = 'secondary';
		$("#name").attr("value", "");
		$("#id").attr("value", "");
		$("#parentId").attr("value", node.attr("id"));
		$("#remark").attr("value", "");
		$("#flag").attr("value", "news");
		$("#selectId").attr("value", node.attr("id"));
		$("#administratorName").attr("value", "");
		$("#administratorId").attr("value", "");
		$("#publisherName").attr("value", "");
		$("#publisherId").attr("value", "");
	});
	// 同级新增、次级新增和修改的保存功能
	$("#saveButton")
			.click(
					function() {
						var actionMethod = 'modifyNewsCategory';
						if (operatorType == 'same')
							actionMethod = 'sameSaveNewsCategory';
						else if (operatorType == 'secondary')
							actionMethod = 'secondarySaveNewsCategory';
						else
							actionMethod = 'modifyNewsCategory';
						$
								.ajax({
									async : false,
									url : actionMethod,
									type : 'POST',
									dataType : 'json',
									data : jQuery("input, textarea",
											$("#menuForm")).serialize(),
									timeout : 1000,
									success : function(data) {
										if (data.result) {
											$("#repeatName").hide();
											if (operatorType == 'secondary') {
												jAlert("操作成功!");
												var newNode = addNode(node,
														'secondary', data.newId);
												instNode.open_node(node, null,
														true);
												instNode.select_node(newNode,
														true, null);
											} else if (operatorType == 'same') {
												jAlert("操作成功!");
												var newNode = addNode(node,
														'same', data.newId);
												instNode.select_node(newNode,
														true, null);
											} else if (operatorType == 'modify') {
												jAlert("操作成功!");
												instNode.set_text(node, $(
												"#name").val());
												$(node).attr(
														"remark",
														$("#remark").attr(
																"value"));
												$(node).attr(
														"administratorName",
														$("#administratorName")
																.val());
												$(node).attr(
														"administratorId",
														$("#administratorId")
																.val());
												$(node).attr(
														"publisherName",
														$("#publisherName")
																.val());
												$(node).attr(
														"publisherId",
														$("#publisherId").val());
											}

										} else {
											if (data.newId == 'none') {
												$("#repeatName")
														.attr("innerHTML",
																"<li><span>已有该信息名!</span></li>");
												$("#repeatName").show();
											} else {
												$("#repeatName").hide();
												jAlert("操作失败!");
											}
										}
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
										$("#repeatName").hide();
										jAlert("操作失败!");
									},
									beforeSend : function() {
										if ($.validationEngine
												.loadValidation("#name")
												|| $.validationEngine
														.loadValidation("#administratorName")
												|| $.validationEngine
														.loadValidation("#publisherName"))
											return false;
									}

								});
					});
	// 删除功能
	$("#deleteButton").click(
			function() {
				operatorType = 'delete';
				if (!confirm('真的要删除吗?删除后将无法恢复!')) {
					return;
				}
				$.ajax({
					async : false,
					url : 'deleteDiscussionCategory?commonCategoryBean.id='
							+ node.attr("id"),
					type : 'POST',
					dataType : 'json',
					timeout : 1000,
					success : function(data) {
						if (data) {
							$("#repeatName").hide();
							changeOrderNo(node);
							instNode.delete_node(node);
						} else {
							jAlert('删除失败!');
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						jAlert('删除失败!');
					}

				});
			});
	$("#publisherName").click(function() {
		selectType = "publisher";
		removeAllUsers();
		$("#organizationUserSelect").dialog("open");

	});
	$("#administratorName").click(function() {
		selectType = "administrator";
		removeAllUsers();
		$("#organizationUserSelect").dialog("open");
	});
	$("#selectBtn").click(function() {
		var _choicedAccMan = document.getElementById("choicedAccMan");
		var userId = getUserIdFromSelectedUsers(_choicedAccMan);
		var userName = getUserNameFromSelectedUsers(_choicedAccMan);
		if (userId == null || userId == "") {
			alert("请选择用户!");
			return;
		}
		if (selectType == 'administrator') {
			$("#administratorId").attr("value", userId);
			$("#administratorName").attr("value", userName);
		} else {
			$("#publisherId").attr("value", userId);
			$("#publisherName").attr("value", userName);
		}
		$("#organizationUserSelect").dialog("close");
		jQuery("#userOriginaList").jqGrid().clearGridData();
		$("#sysOrganization_tree a").removeClass();
		$('#selectBtn').attr('disabled', 'disabled');
	});
});
/**
 * 删除节点改变其orderNo值
 * 
 * @param node
 *            -节点对象
 * @return
 */
function changeOrderNo(node) {
	// 选中节点位置
	var orderNo = Number(node.orderNo);
	parentNode = $(node).parent();
	var childrenNodes = $(parentNode).children();
	var size = childrenNodes.size();
	for ( var i = 0; i < size; i++) {
		if (Number(childrenNodes[i].orderNo) > orderNo) {
			$(childrenNodes[i]).attr("orderNo", i);
		}
	}
}
/**
 * node -节点对象 method -增加方式[same:同级新增;secondary:次级新增] newId; -增加节点成功返回的ID值
 */
function addNode(node, method, newId) {
	// 选中节点位置
	 orderNo = getNodeOrderNo(node, method);
	// 父节点
	var parentNode = null;
	if (method == 'same') {
		parentNode = instNode._get_parent(node);
		var childrenNodes = instNode._get_children(parentNode);
		var size = childrenNodes.size();
		for ( var i = 0; i < size; i++) {
			var childNode = instNode._get_node(childrenNodes[i]);
			if (childNode.attr("orderNo") > orderNo) {
				childNode.attr("orderNo", i + 2);
			}
		}
		// 插入新的节点
		var newNode = instNode.create_node(node, "after", {
			data : $("#name").val(),
			attributes : {
				"id" : newId,
				"orderNo" : (orderNo + 1),
				"parentId" : parentNode.attr("id"),
				"flag" : $("#flag").val(),
				"remark" : $("#remark").val(),
				"administratorId" : $("#administratorId").val(),
				"publisherId" : $("#publisherId").val()
			}
		}, null, true);
		return newNode;
	} else {
		parentNode = node;
		var childrenNodes = instNode._get_children(parentNode);
		orderNo = childrenNodes.size();
		// 插入新的节点
		var newNode = instNode.create_node(node, "last", {
			data : $("#name").val(),
			attributes : {
				"id" : newId,
				"orderNo" : (orderNo),
				"parentId" :parentNode.attr("id"),
				"flag" : $("#flag").val(),
				"remark" : $("#remark").val(),
				"administratorId" : $("#administratorId").val(),
				"publisherId" : $("#publisherId").val()
			}
		}, null,true);
		return newNode;
	}
}
/**
 * 用于获取树节点所在树的父节点里的子节点位置[其实位置为1] 返回-1 表示失败 node -节点对象 operatorType -增加方式
 * 
 * @return
 */
function getNodeOrderNo(node, operatorType) {
	// 获取该父节点下的所有节点数
	if (operatorType == 'same') {
		var parentNode = instNode._get_parent(node);
		var childrenNodes = instNode._get_children(parentNode);
		var size = childrenNodes.size();
		for ( var i = 0; i < size; i++) {
			var childNode = instNode._get_node(childrenNodes[i]);
			if (childNode.attr("id") == node.attr("id")) {
				return i + 1;
			}
		}
	} else {
		var childrenNodes = instNode._get_children(node);
		return childrenNodes.size();
	}
	return -1;

}
// 清空组织机构用户列表
function clearUserOrganizationInfo() {
	jQuery("#userOriginaList").jqGrid().clearGridData();
}

// 判断用户列表中是否存在给定用户ID的用户
function isUserFromSelectedUsers(_choicedAccMan, selectId) {
	var isExit = false;
	for ( var i = 0; i < _choicedAccMan.options.length; i++) {
		if (_choicedAccMan.options[i].value == selectId) {
			isExit = true;
			break;
		}
	}
	return isExit;
}

// 从用户列表中删除已选中的而用户
function removeUserFromSelectedUsers(_choicedAccMan) {
	for ( var i = 0; i < _choicedAccMan.options.length; i++) {
		if (_choicedAccMan.options[i].selected == true) {
			_choicedAccMan.options[i] = null;
			removeUserFromSelectedUsers(_choicedAccMan);
		}
	}
}

// 从用户列表中删除已选中的而用户
function removeAllUsers(_choicedAccMan) {
	var _choicedAccMan = document.getElementById("choicedAccMan");
	$(_choicedAccMan).empty();
	clearUserOrganizationInfo();
}

// 得到用户ID从用户列表中
function getUserIdFromSelectedUsers(_choicedAccMan) {
	var userId = "";
	for ( var i = 0; i < _choicedAccMan.options.length; i++) {
		userId += _choicedAccMan.options[i].value + ",";
	}
	if (userId != "")
		userId = userId.substring(0, userId.length - 1);
	return userId;

}

// 得到用户名从用户列表中
function getUserNameFromSelectedUsers(_choicedAccMan) {
	var userName = "";
	for ( var i = 0; i < _choicedAccMan.options.length; i++) {
		userName += _choicedAccMan.options[i].text + ",";
	}
	if (userName != "")
		userName = userName.substring(0, userName.length - 1);
	return userName;

}

/**
 * 展开节点
 */
function opennode() {
	$.tree.focused().open_branch(getnodeid());
}
/**
 * 关闭节点
 */
function closenode() {
	$.tree.focused().close_branch(getnodeid());
}
function isNotLoginError(xhr) {
	if (xhr.status == '401') {
		var errMsg = window["eval"]("(" + xhr.getResponseHeader("Error-Json")
				+ ")");
		if (errMsg.reason == 'notLogin') {
			jAlert("你还未登录,请登录!");
			window.parent.location.href = errMsg.content + "/page/login.jsp";
		}
	} else if (xhr.status == '500') {
		var errMsg = window["eval"]("(" + xhr.getResponseHeader("Error-Json")
				+ ")");
		if (errMsg.reason == 'insideError') {
			window.location.href = errMsg.content + "/page/error.jsp";
		}
	} else if (xhr.status == '402') {
		var errMsg = window["eval"]("(" + xhr.getResponseHeader("Error-Json")
				+ ")");
		if (errMsg.reason == 'kickRepeatLogin') {
			jAlert("对不起该账号已经在其他地方登录,你已被踢出!");
			window.location.href = errMsg.content + "/page/login.jsp";
		}
	}
}
