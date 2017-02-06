$(document)
		.ready(
				function() {
					$("#commonArticle").validationEngine({
						validationEventTriggers : "keyup blur", // 触发的事件
						// validationEventTriggers:"keyup
						// blur",
						inlineValidation : true,// 是否即时验证，false为提交表单时验证,默认true
						success : false,// 为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
						promptPosition : "centerRight",// 提示所在的位置，topLeft,
						// topRight, bottomLeft,
						// centerRight,
						// bottomRight
						failure : function() {
						},// 验证失败时调用的函数
						success : function() {
							callSuccessFunction();
						}// 验证通过时调用的函数
					});
					$("#organizationUserSelect")
							.dialog(
									{
										modal : false,
										width : 800,
										height : 450,
										minHeight : 450,
										minWidth : 800,
										maxHeight : 450,
										maxWidth : 800,
										buttons : {
											"确定" : function() {
												var _choicedAccMan = document
														.getElementById("choicedAccMan");
												$("#receiveEmpIDS")
														.val(
																getUserIdFromSelectedUsers(_choicedAccMan));
												$("#receiveEmps")
														.val(
																getUserNameFromSelectedUsers(_choicedAccMan));
												$(this).dialog("close");
											},
											"删除" : function() {
												var _choicedAccMan = document
														.getElementById("choicedAccMan");
												removeUserFromSelectedUsers(_choicedAccMan);

											},
											"取消" : function() {
												$(this).dialog("close");
											}
										}
									}).dialog("close");
					$("#organizationSelect").dialog({
						modal : true,
						width : 450,
						height : 430,
						minHeight : 430,
						minWidth : 450,
						maxHeight : 430,
						maxWidth : 450,
						buttons : {
							"确定" : function() {
								$("#receiveDepartIDS").val(getCheckedNode());
								$("#receiveDeparts").val(getCheckedName());
								$(this).dialog("close");
							},
							"取消" : function() {
								$(this).dialog("close");
							}
						}
					}).dialog("close");
				});
var isUpload = false;
$(function() {
	$.ajaxSetup({
		cache : false
	});
	if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
		$("#IEStyle").remove();
	} else {
		document.getElementById('IEStyle').style.display = "block";
		$("#IE6").remove();
	}
	;

	$("#sysOrganizationTree").tree({// menu_tree 为 由Jquery选择器选中的DIV容器的ID
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
				url : "findAllSysOrganizations"// 实际提交的Action
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
	$("#sysOrganization_tree").jstree({// menu_tree 为 由Jquery选择器选中的DIV容器的ID
		"themes" : {
			"theme" : "classic",
			"dots" : true,
			"icons" : true
		},
		"json_data" : {
			"ajax" : {
				"url" : "findAllSysOrganizations",
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
	// $("#sysOrganization_tree")
	// .tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
	// types : {
	// "default" : {
	// draggable : false
	// }
	// // 默认设置关闭拖放
	// },
	// data : {
	// type : "json",
	// async : true,
	// opts : {
	// async : true,
	// method : "POST",
	// url : "findAllSysOrganizations"// 实际提交的Action
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
	// error:function(xhr, status, error) {
	// isNotLoginError(xhr);
	// },
	// onselect : function(NODE, TREE_OBJ) {
	// jQuery("#userOriginaList")
	// .jqGrid(
	// 'setGridParam',
	// {
	// url : "findSysUsersBySysOrganizationId?sysOrganizationBean.id="
	// + NODE.id,
	// page : 1
	// }).trigger("reloadGrid")
	// }
	// },
	// // 插件使用右键菜单支持自定义右键菜单
	// plugins : {}
	//
	// });
	$("#sysOrganization_tree")
			.bind(
					"select_node.jstree",
					function(e, data) {
						var node = data.rslt.obj;
						jQuery("#userOriginaList")
								.jqGrid(
										'setGridParam',
										{
											url : "findSysUsersBySysOrganizationId?sysOrganizationBean.id="
													+ node.attr("id"),
											page : 1
										}).trigger("reloadGrid");

					});
	jQuery("#userOriginaList")
			.jqGrid(
					{
						datatype : "json",
						colNames : [ '真实姓名', '出生日期', '性别' ],
						colModel : [ {
							name : 'realName',
							index : 'realName',
							width : 80,
							sortable : false
						}, {
							name : 'birthDay',
							index : 'birthDay',
							width : 80,
							sortable : false
						}, {
							name : 'gender',
							index : 'gender',
							width : 30,
							sortable : false
						} ],
						onSelectRow : function(id) {
							userId = jQuery("#userOriginaList").jqGrid(
									'getGridParam', 'selrow');
							realName = jQuery("#userOriginaList").getRowData(
									userId).realName;
							var _choicedAccMan = document
									.getElementById("choicedAccMan");
							if (!isUserFromSelectedUsers(_choicedAccMan, userId))
								$(
										"<option value='" + userId + "'>"
												+ realName + "</option>")
										.appendTo($(_choicedAccMan));
						},
						loadui : 'disable',
						loadError : function(xhr, status, error) {
							isNotLoginError(xhr);
						},
						multiselect : false,
						height : 280,
						width : 300,
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
	$("#receiveEmps").click(function() {
		$("#organizationUserSelect").dialog("open");

	});
	$("#receiveDeparts").click(function() {
		$("#organizationSelect").dialog("open");

	});
	$("#saveSubmit").click(function() {
		$("#content").attr("value", editorcontent.getHTML());
		if (isUpload == true)
			$('#divProcessing').css('display', 'block');
		else
			$('#divProcessing').css('display', 'none');
		$('#saveSubmit').css('display', 'none');
		$('#resetButton').css('display', 'none');
		$('#backButton').css('display', 'none');
	});
	$("#backButton")
			.click(
					function() {
						var commonCategoryId = $("#commonCategoryId").val();
						window.location = 'newsIndex?commonArticleBean.commonCategoryId='
								+ commonCategoryId;
					});
	$("#resetButton").click(function() {
		editorcontent.setHTML("");
		document.forms[0].reset();
	});
	$("#replayButton")
			.click(
					function() {
						var commonCategoryId = $("#commonCategoryId").val();
						var id = $("#id").val();
						window.location = "createCommonArticle?commonArticleBean.commonCategoryId="
								+ commonCategoryId
								+ "&commonArticleBean.id="
								+ id;
					});
	// 删除功能
	$("#deleteButton")
			.click(
					function() {
						var commonCategoryId = $("#commonCategoryId").val();
						var id = $("#id").val();
						window.location = "deleteCommonArticleById?commonArticleBean.commonCategoryId="
								+ commonCategoryId
								+ "&commonArticleBean.id="
								+ id;
					});
});
function checkScope() {
	var checkScope = document.forms[0].scope.checked;
	if (checkScope) {
		$("#depertTr").css("display", "block");
		$("#empTr").css("display", "block");
		$("#scope").val("yes");
	} else {
		$("#depertTr").css("display", "none");
		$("#empTr").css("display", "none");
		$("#scope").val("no");
	}
}
function uploadNew() {
	var uploadNew = document.forms[0].upload.checked;
	if (uploadNew) {
		$("#myFile").css("display", "block");
		isUpload = true;
	} else {
		$("#myFile").css("display", "none");
		isUpload = false;
	}
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
function removeUserFromSelectedUsers(_choicedAccMan) {
	for ( var i = 0; i < _choicedAccMan.options.length; i++) {
		if (_choicedAccMan.options[i].selected == true) {
			_choicedAccMan.options[i] = null;
			removeUserFromSelectedUsers(_choicedAccMan);
		}
	}
}
function getUserIdFromSelectedUsers(_choicedAccMan) {
	var userId = "";
	for ( var i = 0; i < _choicedAccMan.options.length; i++) {
		userId += _choicedAccMan.options[i].value + ",";
	}
	if (userId != "")
		userId = userId.substring(0, userId.length - 1);
	return userId;

}
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
 * 获取选中节点及其直接和间接父节点的方法
 */
function getCheckedNode() {
	var ids = "";
	// 选中节点父节点集合
	var objct = jQuery.tree.plugins.checkbox.get_undeterminded();
	for (i = 0; i < objct.size(); i++) {
		ids += objct[i].id + ",";
	}
	// 选中节点集合
	objct = jQuery.tree.plugins.checkbox.get_checked();
	for (i = 0; i < objct.size(); i++) {
		ids += objct[i].id + ",";
	}
	if (ids != "")
		ids = ids.substring(0, ids.length - 1);
	return ids;
}
function getCheckedName() {
	var names = "";
	// 选中节点父节点集合
	var objct = jQuery.tree.plugins.checkbox.get_undeterminded();
	for (i = 0; i < objct.size(); i++) {
		names += $.tree.focused().get_text($(objct[i])) + ",";
	}
	// 选中节点集合
	objct = jQuery.tree.plugins.checkbox.get_checked();
	for (i = 0; i < objct.size(); i++) {
		names += $(objct[i]).children("a").text() + ",";
	}
	if (names != "")
		names = names.substring(0, names.length - 1);
	return names;
}