//当前选中节点
var node;
// 操作状态 [none:默认无操作,same:同级,secondary:次级,modify:修改,delete:删除]
var operatorType = 'none';
var instNode;
$(document).ready(function() {
	$("#menuForm").validationEngine({
		validationEventTriggers : "keyup", // 触发的事件
		// validationEventTriggers:"keyup
		// blur",
		inlineValidation : true,// 是否即时验证，false为提交表单时验证,默认true
		success : false,// 为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
		promptPosition : "centerRight",// 提示所在的位置，topLeft, topRight,
		// bottomLeft, centerRight, bottomRight
		failure : function() {
		},// 验证失败时调用的函数
		success : function() {
			callSuccessFunction();
		}// 验证通过时调用的函数
	});
});
$(function() {
	// ajax调用不使用缓存
	$.ajaxSetup({
		cache : false
	});
	$("#commonCategory_tree").jstree({// menu_tree 为 由Jquery选择器选中的DIV容器的ID
		"themes" : {
			"theme" : "classic",
			"dots" : true,
			"icons" : true
		},
		"json_data" : {
			"ajax" : {
				"url" : "findAllDiscussionCategories",
				"data" : function(n) {
					return {
						id : n.attr ? n.attr("id") : 0
					};
				}
			}
		},
		"core" : {
			"strings" : {
				"loading" : "组织机构加载中……"
			}
		},
		// 插件使用右键菜单支持自定义右键菜单
		"plugins" : [ "themes", "json_data", "ui", "crrm" ]
	});
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
		$("#flag").attr("value", "discussion");
		$("#selectId").attr("value", node.attr("id"));
	});
	// 次级新增功能
	$("#secondaryLevelCreateButton").click(function() {
		operatorType = 'secondary';
		$("#name").attr("value", "");
		$("#id").attr("value", "");
		$("#parentId").attr("value", node.attr("id"));
		$("#remark").attr("value", "");
		$("#flag").attr("value", "discussion");
		$("#selectId").attr("value", node.attr("id"));
	});
	// 同级新增、次级新增和修改的保存功能
	$("#saveButton")
			.click(
					function() {
						var actionMethod = 'modifyDiscussionCategory';
						if (operatorType == 'same')
							actionMethod = 'sameSaveDiscussionCategory';
						else if (operatorType == 'secondary')
							actionMethod = 'secondarySaveDiscussionCategory';
						else
							actionMethod = 'modifyDiscussionCategory';
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
												$(node).attr("remark",
														$("#remark").val());
											}

										} else {
											if (data.newId == 'none') {
												$("#repeatName")
														.attr("innerHTML",
																"<li><span>已有该讨论名!</span></li>");
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
												.loadValidation("#name"))
											return false;
									}

								});
					});
	// 删除功能
	$("#deleteButton").click(
			function() {
				operatorType = 'delete';
				if (!confirm('真的要删除吗？删除后将无法恢复!')) {
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
	var orderNo = getNodeOrderNo(node, method);
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
				"remark" : $("#remark").val()
			}
		}, null, true);
		return newNode;
	} else {
		// parentNode = $(node);
		// orderNo = $(parentNode).children().children().size();
		// 插入新的节点
		parentNode = node;
		var childrenNodes = instNode._get_children(parentNode);
		orderNo = childrenNodes.size();
		// 插入新的节点
		var newNode = instNode.create_node(node, "last", {
			data : $("#name").val(),
			attributes : {
				"id" : newId,
				"orderNo" : (orderNo),
				"parentId" : parentNode.attr("id"),
				"flag" : $("#flag").val(),
				"remark" : $("#remark").val()
			}
		}, null, true);
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
