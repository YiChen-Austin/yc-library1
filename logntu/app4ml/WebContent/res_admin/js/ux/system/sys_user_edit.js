var orgId = '';
var orgName = '';
$(document).ready(
		function() {
			$("#sysUser").validationEngine( {
				validationEventTriggers : "keyup", // 触发的事件
				// validationEventTriggers:"keyup
				// blur",
				inlineValidation : true,// 是否即时验证，false为提交表单时验证,默认true
				success : false,// 为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
				promptPosition : "centerRight",// 提示所在的位置，topLeft,
				// topRight,
				// bottomLeft, centerRight, bottomRight
				failure : function() {
				},// 验证失败时调用的函数
				success : function() {
					//callSuccessFunction();
				}// 验证通过时调用的函数
			});
			$("#sysRoleSelect").dialog(
					{
						modal : true,
						width : 450,
						height : 430,
						minHeight : 430,
						minWidth : 450,
						maxHeight : 430,
						maxWidth : 450,
						buttons : {
							"确定" : function() {
								var sys_role_ids = '';
								var sys_role_names = '';

								var gr = jQuery("#sysRoleList").jqGrid(
										"getGridParam", "selarrrow");
								if (gr == '') {
									jAlert("请选择角色!");
									return false;
								} else {
									var len = gr.length;
									for ( var i = 0; i < len; i++) {
										var id = gr[i];
										var name = jQuery("#sysRoleList")
												.getRowData(id).name;
										if (sys_role_ids == '')
											sys_role_ids = id;
										else
											sys_role_ids += "," + id;
										if (sys_role_names == '')
											sys_role_names = name;
										else
											sys_role_names += "," + name;

									}
									$('#sysRoleIDs').val(sys_role_ids);
									$('#roleName').val(sys_role_names);
									$(this).dialog("close");

								}

							},
							"取消" : function() {
								$(this).dialog("close");
							}
						}
					}).dialog("close");
			$("#organizationSelect").dialog( {
				modal : true,
				width : 450,
				height : 430,
				minHeight : 430,
				minWidth : 450,
				maxHeight : 430,
				maxWidth : 450,
				buttons : {
					"确定" : function() {
						if (orgId == '') {
							jAlert("请选择组织机构!");
							return false;
						}
						$("#sysOrganizationIDs").val(orgId);
						$("#orgNames").val(orgName);
						$(this).dialog("close");
					},
					"取消" : function() {
						$(this).dialog("close");
					}
				}
			}).dialog("close");
			var operator = $("#isOperatorY").attr("checked");
			if (!operator) {
				$("#userName").attr("disabled", "disabled");
				$("#password").attr("disabled", "disabled");
				$("#effectStartDate").attr("disabled", "disabled");
				$("#effectEndDate").attr("disabled", "disabled");
			} else {
				$("#userName").removeAttr("disabled");
				$("#password").removeAttr("disabled");
				$("#effectStartDate").removeAttr("disabled");
				$("#effectEndDate").removeAttr("disabled");
			}
		});
$(function() {
	$.ajaxSetup( {
		cache : false
	});
	$("#saveSubmit_a").click(function() {
		if (!dateCheck())
			return false;
		document.forms[0].action=$("#path",parent.document).val()+"/admin/"+"saveSysUser";
		document.forms[0].submit();
	});
	$("#saveSubmit_m").click(function() {
		if (!dateCheck())
			return false;
		document.forms[0].action=$("#path",parent.document).val()+"/admin/"+"modifySysUser";
		document.forms[0].submit();
	});
	$("#backButton").click(function() {
		window.location = $("#path",parent.document).val()+"/page/system/sys_user_list";
	});
	$("#orgNames").click(function() {
		$("#organizationSelect").dialog("open");
	});
	$("#roleName").click(function() {
		$("#sysRoleSelect").dialog("open");
	});
	jQuery("#sysRoleList").jqGrid( {
		// 获取数据的地址
		url : $("#path",parent.document).val()+"/admin/"+"findBranchSysRoles",
		mtype : "post",
		// 从服务器端返回的数据类型,配置为json
		datatype : "json",
		// 列显示名称，是一个数组对象
		colNames : [ 'ID', '角色代码', '角色名称', '备注', '标志位' ],
		// {name:表格列的名称,index:当排序时定义排序字段名称的索引,width:宽度,editable:单元格是否可编辑,edittype:可以编辑的类型}
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false,
			hidden : true

		}, {
			name : 'code',
			index : 'code',
			width : 60,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'name',
			index : 'name',
			width : 80,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'remark',
			index : 'remark',
			width : 100,
			editable : true,
			edittype : "text",
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
		altRows : true,
		// 定义是否可以多选
		multiselect : true,
		// 在grid上显示记录条数，这个参数是要被传递到后台
		rowNum : 100,
		// 一个数组用来调整表格显示的记录数，此参数值会替代rowNum参数值传给服务器端。如果为空则不显示，设置格式：[10,20,30]
		rowList : [ 100, 200 ],
		// 定义翻页用的导航栏，必须是有效的html元素
		pager : '#pager',
		// 默认的排序列。可以是列名称或者是一个数字，这个参数会被提交到后台
		sortname : 'id',
		// 是否要显示总记录数
		viewrecords : true,
		// 启用或者禁用单元格编辑功能
		cellEdit : false,
		// 是否可排序
		sortorder : "asc",
		// 表格高度，可以是数字，像素值或者百分比
		height : 430,
		// 如果设置则按此设置为主，如果没有设置则按colModel中定义的宽度计算
		width : 350,
		// jsonReader的属性
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
		},

		// 定义表格名称
		caption : "角色列表"
	});
	$("#sysOrganizationTree").tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
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
				onload : function(t) {
					t.settings.data.opts.static = false;
				},
				onselect : function(NODE, TREE_OBJ) {
					orgName = TREE_OBJ.get_text(NODE);
					orgId = $(NODE).attr("id");
				}
			},
			// 插件使用右键菜单支持自定义右键菜单
				plugins : {}
			});
});
function operatorCheck() {
	var operator = $("#isOperatorY").attr("checked");
	if (!operator) {
		$("#userName").attr("disabled", "disabled");
		$("#password").attr("disabled", "disabled");
		$("#effectStartDate").attr("disabled", "disabled");
		$("#effectEndDate").attr("disabled", "disabled");
		$("#dateCheck").hide();
	} else {
		$("#userName").removeAttr("disabled");
		$("#password").removeAttr("disabled");
		$("#effectStartDate").removeAttr("disabled");
		$("#effectEndDate").removeAttr("disabled");
	}

}
function resetSysUser() {
	document.forms[0].action = "findSysUser";
	document.forms[0].submit();
}
function dateCheck() {
	$("#dateCheck").hide();
	var operator = $("#isOperatorY").attr("checked");
	if (operator) {
		var userName = $("#userName").attr("value");
		var password = $("#password").attr("value");
		if (userName == '') {
			$("#dateCheck").show();
			$("#dateCheck").attr("innerHTML",
					"<ul><li><span>用户名不能为空!</span></li></ul>");
			return false;
		}
		if (password == '') {
			$("#dateCheck").show();
			$("#dateCheck").attr("innerHTML",
					"<ul><li><span>用户密码不能为空!</span></li></ul>");
			return false;
		}
		var effectStartDate = $("#effectStartDate").attr("value");
		var effectEndDate = $("#effectEndDate").attr("value");
		var birthDay = $("#birthDay").attr("value");
		if (birthDay == '') {
			if (effectStartDate != '' && effectEndDate != '') {
				if (effectStartDate >= effectEndDate) {
					$("#dateCheck").show();
					$("#dateCheck").attr("innerHTML",
							"<ul><li><span>生效日期不能大于失效日期!</span></li></ul>");
					return false;
				}
			} else {
				$("#dateCheck").hide();
			}
		} else {
			var nowDate = (new Date()).pattern("yyyy-MM-dd");
			if (birthDay >= nowDate) {
				$("#dateCheck").show();
				$("#dateCheck").attr("innerHTML",
						"<ul><li><span>出生日期不能大于当前日期!</span></li></ul>");
				return false;
			}
			if (effectStartDate != '' && effectEndDate != '') {
				if (effectStartDate >= effectEndDate) {
					$("#dateCheck").show();
					$("#dateCheck").attr("innerHTML",
							"<ul><li><span>生效日期不能大于失效日期!</span></li></ul>");
					return false;
				} else if (birthDay >= effectStartDate) {
					$("#dateCheck").show();
					$("#dateCheck").attr("innerHTML",
							"<ul><li><span>出生日期不能大于生效日期!</span></li></ul>");
					return false;
				} else {
					$("#dateCheck").hide();
				}
			} else if (effectStartDate == '' && effectEndDate != '') {
				if (birthDay >= effectEndDate) {
					$("#dateCheck").show();
					$("#dateCheck").attr("innerHTML",
							"<ul><li><span>出生日期不能大于失效日期!</span></li></ul>");
					return false;
				} else {
					$("#dateCheck").hide();
				}
			} else if (effectStartDate != '' && effectEndDate == '') {
				if (birthDay >= effectEndDate) {
					$("#dateCheck").show();
					$("#dateCheck").attr("innerHTML",
							"<ul><li><span>出生日期不能大于生效日期!</span></li></ul>");
					return false;
				} else {
					$("#dateCheck").hide();
				}
			} else {
				$("#dateCheck").hide();
			}
		}
	}
	return true;

}
Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "\u65e5",
		"1" : "\u4e00",
		"2" : "\u4e8c",
		"3" : "\u4e09",
		"4" : "\u56db",
		"5" : "\u4e94",
		"6" : "\u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f"
								: "\u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
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