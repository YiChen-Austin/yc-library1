//当前选中节点
var node;
$(function() {
	var operatorType = "origina";
	// ajax调用不使用缓存
	$.ajaxSetup( {
		cache : false
	});
	$("#sysOrganization_tree")
			.tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
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
							error:function(xhr, status, error) {
								isNotLoginError(xhr);
							},
							onselect : function(NODE, TREE_OBJ) {
								operatorType = "origina";
								node = NODE;
								$("#originaDiv").show();
								$("#searchCondition").hide();
								$("#addedDiv").hide();
								$('#addUserButton').removeAttr('disabled');
								$('#originaBtn').removeAttr('disabled');
								clearUserOrganizationInfo(operatorType);
								jQuery("#userOriginaList")
										.jqGrid(
												'setGridParam',
												{
													url : $("#path",parent.document).val()+"/admin/"+"findSysUsersBySysOrganizationId?id="
															+ node.id,
													page : 1
												}).trigger("reloadGrid")
							}
						},
						// 插件使用右键菜单支持自定义右键菜单
						plugins : {}

					});
	// 组织机构原有人员
	jQuery("#userOriginaList").jqGrid(
			{
				datatype : "json",
				colNames : [ '真实姓名', '出生日期', '性别' ],
				colModel : [ { name : 'realName', index : 'realName', width : 90, sortable : false
				}, {
					name : 'birthDay',
					index : 'birthDay',
					width : 80,
					sortable : false
				}, {
					name : 'gender',
					index : 'gender',
					width : 60,
					sortable : false
				} ],
				loadui:'disable',
				loadError : function(xhr, status, error) {
					isNotLoginError(xhr);
				},
				multiselect : true,
				loadComplete : function(xhr) {
					$.ajax( {
						type : "POST",
						url : $("#path",parent.document).val()+"/admin/"+"findSysUsersBySysOrganizationId",
						data : "id=" + node.id,
						success : function(data) {
							$.each(data.sysUserBeans, function(i, item) {
								jQuery("#userOriginaList").jqGrid()
										.setSelection(item.id, false);
							});
						}
					});
					return xhr;
				},
				height : 388,
				width : 400,
				caption : "修改组织机构原有人员",
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
	// 组织机构新增人员
	jQuery("#userAddedList").jqGrid( {
		datatype : "json",
		colNames : [ '真实姓名', '出生日期', '性别' ],
		colModel : [ {
			name : 'realName',
			index : 'realName',
			width : 90,
			sortable : false
		}, {
			name : 'birthDay',
			index : 'birthDay',
			width : 80,
			sortable : false
		}, {
			name : 'gender',
			index : 'gender',
			width : 60,
			sortable : false
		} ],
		loadError : function(xhr, status, error) {
			isNotLoginError(xhr);
		},
		multiselect : true,
		rowNum : 10,
		pager : '#pager',
		rowList : [ 10, 20, 30, 50 ],
		sortname : 'id',
		viewrecords : true,
		sortorder : "desc",
		height : 308,
		width : 470,
		caption : "组织机构新增人员",
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
	// 查询人员
	$("#searchButton")
			.click(
					function() {
						jQuery("#userAddedList")
								.jqGrid(
										'setGridParam',
										{
											url : $("#path",parent.document).val()+"/admin/"+"findNotSelectedSysUsers?sysOrganizationIDs="
													+ node.id
													+ "&"
													+ encodeURI($("#searchCondition")
															.ajaxForm()
															.formSerialize()),
											page : 1
										}).trigger("reloadGrid");
					});
	// 添加人员
	$("#addUserButton")
			.click(
					function() {
						operatorType = "added";
						$('#addedBtn').removeAttr('disabled');
						$("#originaDiv").hide();
						$("#searchCondition").show();
						$("#addedDiv").show();
						jQuery("#userAddedList")
								.jqGrid(
										'setGridParam',
										{
											url : $("#path",parent.document).val()+"/admin/"+"findNotSelectedSysUsers?sysOrganizationIDs="
													+ node.id,
											page : 1
										}).trigger("reloadGrid");
					});
	// 修改组织机构原有人员
	$("#originaBtn")
			.click(
					function() {
						var ids = jQuery("#userOriginaList").jqGrid(
								'getGridParam', 'selarrrow');
						$
								.ajax( {
									type : "POST",
									url : $("#path",parent.document).val()+"/admin/"+"setSysUserBySysOrganizationId",
									data : "id="
											+ node.id
											+ "&sysUserIDs="
											+ ids,
									success : function(data) {
										if (data) {
											clearUserOrganizationInfo(operatorType);
											jQuery("#userOriginaList")
													.jqGrid(
															'setGridParam',
															{
																url : $("#path",parent.document).val()+"/admin/"+"findSysUsersBySysOrganizationId?id="
																		+ node.id,
																page : 1
															}).trigger(
															"reloadGrid")
											jAlert('设置成功！');
										} else {
											jAlert('设置失败！');
										}
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
										jAlert('设置失败！');
									}
								});
					});
	// 新增组织机构人员
	$("#addedBtn")
			.click(
					function() {
						var ids = jQuery("#userAddedList").jqGrid(
								'getGridParam', 'selarrrow');
						$
								.ajax( {
									type : "POST",
									url : $("#path",parent.document).val()+"/admin/"+"setNewSysUserBySysOrganizationId",
									data : "id="
											+ node.id
											+ "&sysUserIDs="
											+ ids,
									success : function(data) {
										if (data) {
											clearUserOrganizationInfo(operatorType);
											jQuery("#userAddedList")
													.jqGrid(
															'setGridParam',
															{
																url : $("#path",parent.document).val()+"/admin/"+"findNotSelectedSysUsers?sysOrganizationIDs="
																	+ node.id,
																page : 1
															}).trigger(
															"reloadGrid")
											jAlert('设置成功！');
										} else {
											jAlert('设置失败！');
										}
									},
									error : function(xhr,
											textStatus, errorThrown) {
										if(xhr.status=='401'||xhr.status=='500'){
											isNotLoginError(xhr);
											return;
										}
										jAlert('设置失败！');
									}
								});
					});
});
function clearUserOrganizationInfo(operatorType) {
	if (operatorType == 'origina')
		jQuery("#userOriginaList").jqGrid().clearGridData();
	else
		jQuery("#userAddedList").jqGrid().clearGridData();
}
function isNotLoginError(xhr){
	if (xhr.status=='401') {
		var errMsg = window["eval"]("("+ xhr.getResponseHeader("Error-Json") + ")");
		if (errMsg.reason == 'notLogin') {
			jAlert("你还未登录,请登录!");
			window.parent.location.href = errMsg.content+ "/admin/index";
		}
	}else if(xhr.status=='500'){
		var errMsg = window["eval"]("("+ xhr.getResponseHeader("Error-Json") + ")");
		if (errMsg.reason == 'insideError') {
			window.location.href = errMsg.content+"/page/error.jsp";
		}
	}else if(xhr.status=='402'){
		var errMsg = window["eval"]("("+ xhr.getResponseHeader("Error-Json") + ")");
		if (errMsg.reason == 'kickRepeatLogin') {
			jAlert("对不起该账号已经在其他地方登录,你已被踢出!");
			window.location.href = errMsg.content+ "/admin/index";
		}
	}
}
