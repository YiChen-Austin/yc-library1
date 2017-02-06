//当前选中节点
var node;
$(function() {
	var operatorType = "origina";
	// ajax调用不使用缓存
	$.ajaxSetup({
		cache : false
	});
	$("#sysOrganization_tree").tree(
			{// menu_tree 为 由Jquery选择器选中的DIV容器的ID
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
						url : $("#path", parent.document).val() + "/admin/"
								+ "findAllSysOrganizations"// 实际提交的Action
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
					error : function(xhr, status, error) {
						isNotLoginError(xhr);
					},
					onselect : function(NODE, TREE_OBJ) {
						operatorType = "origina";
						node = NODE;
						$('#addOrgAreaBtn').removeAttr('disabled');
						$('#deleteOrgAreaBtn').removeAttr('disabled');
						clearUserOrganizationInfo(operatorType);
						jQuery("#userOriginaList").jqGrid(
								'setGridParam',
								{
									url : $("#path", parent.document).val()
											+ "/areaAdmin/" + "list?orgId="
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
				colNames : [ 'id', '组织结构', '城市名称' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					width : 5,
					editable : true,
					edittype : "text",
					sortable : false,
					hidden : true
				}, {
					name : 'orgName',
					index : 'orgName',
					width : 90,
					sortable : false
				}, {
					name : 'areaName',
					index : 'areaName',
					width : 80,
					sortable : false
				} ],
				loadui : 'disable',
				loadError : function(xhr, status, error) {
					isNotLoginError(xhr);
				},
				multiselect : true,
				loadComplete : function(xhr) {
					$.ajax({
						type : "POST",
						url : $("#path", parent.document).val() + "/areaAdmin/"
								+ "list",
						data : "orgId=" + node.id,
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
				caption : "组织机构对应城市",
				jsonReader : {
					// 这个元素指明表格所需要的数据从哪里开始
					root : "orgAreas",
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
	// 新增地区
	jQuery("#areaAddedList").jqGrid({
		// 获取数据的地址
		url : "",
		datatype : "local",
		colNames : [ 'id', '城市首字母', '城市名字', '城市简拼' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 5,
			editable : true,
			edittype : "text",
			sortable : false,
			hidden : true
		}, {
			name : 'firstLetter',
			index : 'firstLetter',
			width : 30,
			sortable : false
		}, {
			name : 'regionName',
			index : 'regionName',
			width : 80,
			sortable : false
		}, {
			name : 'pinyin',
			index : 'pinyin',
			width : 80,
			sortable : false
		} ],
		altRows : true,
		// 定义是否可以多选
		multiselect : true,
		// 在grid上显示记录条数，这个参数是要被传递到后台
		rowNum : 20,
		// 一个数组用来调整表格显示的记录数，此参数值会替代rowNum参数值传给服务器端。如果为空则不显示，设置格式：[10,20,30]
		rowList : [ 50, 100, 200, 300 ],
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
		height : 400,
		// 如果设置则按此设置为主，如果没有设置则按colModel中定义的宽度计算
		width : 700,
		caption : "城市大全",
		jsonReader : {
			// 这个元素指明表格所需要的数据从哪里开始
			root : "cities",
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
	$("#all_cities_view").dialog({
		modal : true,
		width : 800,
		height : 600,
		minHeight : 600,
		minWidth : 800,
		maxHeight : 600,
		maxWidth : 800
	}).dialog("close");
	loadProvinces();
	$("#deleteOrgAreaBtn").bind('click', delOrgArea);
	$("#addOrgAreaBtn").click(function() {
		$("#provinces_sele").val("-1");
		$("#areaAddedList").clearGridData();
		$("#all_cities_view").dialog("open");
	});
	$("#provinces_sele").bind('change', provinces);
	$("#addedCityBtn").bind('click', saveOrgArea);
	$("#closeCityBtn").bind('click', closeOrgArea);
});
function delOrgArea() {
	var gr = jQuery("#userOriginaList").jqGrid("getGridParam", "selarrrow");
	if (gr == '') {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/areaAdmin/del?ids=" + gr,
			type : 'POST',
			success : function(data) {
				var $temp = eval('(' + data + ')');
				if ($temp.result == true) {
					alert("处理成功!");
				}
				toQuery();
			}
		});
	}
}
function closeOrgArea() {
	$("#all_cities_view").dialog("close");
}
function saveOrgArea() {
	var gr = jQuery("#areaAddedList").jqGrid("getGridParam", "selarrrow");
	if (gr == '' || gr == 'null' || gr == null) {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/areaAdmin/save?orgId=" + node.id + "&areaIds=" + gr,
			type : 'POST',
			success : function(data) {
				var $temp = eval('(' + data + ')');
				if ($temp.result == true) {
					$("#all_cities_view").dialog("close");
					alert("处理成功!");
				}
				toQuery();
			}
		});
	}
}
function loadProvinces() {
	$.ajax({
		url : "/areaAdmin/provinces",
		type : 'POST',
		success : function(data) {
			var $temp = eval('(' + data + ')');
			if ($temp.provinces) {
				var _option = '<option value="-1">请选择省份</option>';
				for (var i = 0; i < $temp.provinces.length; i++) {
					var province = $temp.provinces[i];
					_option += '<option value="' + province.regionId + '">'
							+ province.firstLetter + '--' + province.regionName
							+ '</option>';
				}
				$("#provinces_sele").html(_option);
			}
		}
	});
}
function provinces() {
	var regionId = $("#provinces_sele").val();
	cities(regionId);
}
function cities(regionId) {
	// 清空表格数据，清空tab记录条数
	$("#areaAddedList").clearGridData();
	jQuery("#areaAddedList").jqGrid('setGridParam', {
		type : "post",
		datatype : "json",
		url : "/areaAdmin/cities?regionId=" + regionId
	}).trigger("reloadGrid");
}
function toQuery() {
	// 清空表格数据，清空tab记录条数
	$("#userOriginaList").clearGridData();
	jQuery("#userOriginaList").jqGrid('setGridParam', {
		type : "post",
		datatype : "json",
		url : "/areaAdmin/list?orgId=" + node.id
	}).trigger("reloadGrid");
}
function clearUserOrganizationInfo(operatorType) {
	if (operatorType == 'origina')
		jQuery("#userOriginaList").jqGrid().clearGridData();
	else
		jQuery("#userAddedList").jqGrid().clearGridData();
}
function isNotLoginError(xhr) {
	if (xhr.status == '401') {
		var errMsg = window["eval"]("(" + xhr.getResponseHeader("Error-Json")
				+ ")");
		if (errMsg.reason == 'notLogin') {
			jAlert("你还未登录,请登录!");
			window.parent.location.href = errMsg.content + "/admin/index";
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
			window.location.href = errMsg.content + "/admin/index";
		}
	}
}
