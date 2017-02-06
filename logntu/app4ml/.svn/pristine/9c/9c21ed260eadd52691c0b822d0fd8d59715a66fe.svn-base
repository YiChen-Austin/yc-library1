//列表显示
$(function() {
	// jqGrid参数配置
	jQuery("#list").jqGrid({ // 获取数据的地址
		url : "",
		datatype : "local",
		// 列显示名称，是一个数组对象
		colNames : [ 'ID', '会员姓名 ', '会员用户名 ', '申请类型', '申请理由', '状态', '申请时间' ],
		// {name:表格列的名称,index:当排序时定义排序字段名称的索引,width:宽度,editable:单元格是否可编辑,edittype:可以编辑的类型}
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 5,
			editable : true,
			edittype : "text",
			sortable : false,
			hidden : true

		}, {
			name : 'realName',
			index : 'realName',
			align : 'center',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'menName',
			index : 'menName',
			align : 'center',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'appTypeStr',
			index : 'appTypeStr',
			align : 'center',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'appReason',
			index : 'appReason',
			align : 'center',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'statusStr',
			index : 'statusStr',
			align : 'center',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'createDate',
			index : 'createDate',
			align : 'center',
			width : 40,
			editable : true,
			edittype : "text",
			sortable : false,
			formatter : "date",
			formatoptions : {
				newformat : 'Y-m-d'
			}
		} ],
		altRows : true,
		// 定义是否可以多选
		multiselect : true,
		// 在grid上显示记录条数，这个参数是要被传递到后台
		rowNum : 15,
		// 一个数组用来调整表格显示的记录数，此参数值会替代rowNum参数值传给服务器端。如果为空则不显示，设置格式：[10,20,30]
		rowList : [ 10, 15, 20, 30 ],
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
		autowidth : true,
		// jsonReader的属性
		jsonReader : {
			// 这个元素指明表格所需要的数据从哪里开始
			root : "applyBeans",
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
		caption : "审批信息"
	});

	jQuery("#list").navGrid('#pager', {
		edit : false,
		add : false,
		del : false,
		refresh : false,
		search : false
	}).navButtonAdd('#pager', {
		id : "view_id",
		caption : "查看",
		buttonicon : "ui-icon-plus",
		title : "查看用户详情",
		position : "last",
		onClickButton : new_open_view
	}).navButtonAdd('#pager', {
		id : "agree_id",
		caption : "同意",
		buttonicon : "ui-icon-plus",
		title : "批量同意",
		position : "last",
		onClickButton : batch_agree
	}).navButtonAdd('#pager', {
		id : "disagree_id",
		caption : "不同意",
		buttonicon : "ui-icon-pencil",
		title : "批量不同意",
		position : "last",
		onClickButton : batch_disagree
	});
	// 列表宽度随浏览器变化成比例变化
	$(window).resize(function() {
		$('#list').jqGrid().setGridWidth($(window).width() * 0.96 - 10);
	});
	// 执行查询
	// toQuery();
	// 绑定tab的点击事情，点击的时候执行查询
	$("#apply_uncheck").bind('click', alterTag);
	$("#apply_agree").bind('click', alterTag);
	$("#apply_disagree").bind('click', alterTag);
	if ($('#app_status').val() == '0')
		$("#apply_uncheck").click();
	if ($('#app_status').val() == '1')
		$("#apply_agree").click();
	if ($('#app_status').val() == '2')
		$("#apply_disagree").click();
});
/**
 * 选项卡间的切换
 */
function alterTag() {
	var tag_no = $(this).attr("name");
	$("li").attr("class", "");
	$(this).parent().attr("class", "selectTag");
	if (tag_no == 'apply_uncheck') {
		$('#app_status').val('0'); // 
		var $td = $('#pager_left').find('table tbody tr td');
		$
				.each(
						$td,
						function(i, item) {
							if ('view_id' != $(this).attr("id")) {
								$(this).attr('disabled', 'disabled');
								document.getElementById($(this).attr("id")).style.display = "none";
								$(this).unbind("click");
							}
							if ('agree_id' == $(this).attr("id")) {
								document.getElementById($(this).attr("id")).style.display = "inline";
								$(this).removeAttr('disabled');
								$(this).bind("click", batch_agree);
							}
							if ('disagree_id' == $(this).attr("id")) {
								document.getElementById($(this).attr("id")).style.display = "inline";
								$(this).removeAttr('disabled');
								$(this).bind("click", batch_disagree);
							}
						});
	} else if (tag_no == 'apply_agree') {
		$('#app_status').val('1'); // 待达标确认
		var $td = $('#pager_left').find('table tbody tr td');
		$.each($td, function(i, item) {
			if ('view_id' != $(this).attr("id")) {
				$(this).attr('disabled', 'disabled');
				document.getElementById($(this).attr("id")).style.display = "none";
				$(this).unbind("click");
			}
		});
	} else if (tag_no == 'apply_disagree') {
		$('#app_status').val('2'); // 待运行开通
		var $td = $('#pager_left').find('table tbody tr td');
		$.each(
						$td,
						function(i, item) {
							if ('view_id' != $(this).attr("id")) {
								$(this).attr('disabled', 'disabled');
								document.getElementById($(this).attr("id")).style.display = "none";
								$(this).unbind("click");
							}
						});
	}
	// 执行查询
	toQuery();
}
/**
 * 执行查询
 */
function toQuery() {
	// 清空表格数据，清空tab记录条数
	$("#list").clearGridData();
	jQuery("#list").jqGrid('setGridParam', {
		type : "post",
		datatype : "json",
		url : "/applyAdmin/list?status=" + $("#app_status").val()+"&appType="+$("#app_type").val()
	}).trigger("reloadGrid");
}
// 同意
function batch_agree() {
	var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
	if (gr == '') {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/applyAdmin/agree?ids=" + gr,
			type : 'POST',
			success : function(data) {
				if (data.result == true) {
					alert("处理成功!");
				}
				toQuery();
			}
		});
	}
}

// 不同意
function batch_disagree() {
	var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
	if (gr == '') {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/applyAdmin/disagree?ids=" + gr,
			type : 'POST',
			success : function(data) {
				if (data.result == true) {
					alert("处理成功!");
				}
				toQuery();
			}
		});
	}
}
function new_open_view() {
	var rowId = jQuery("#list").jqGrid('getGridParam', 'selarrrow');
	if (rowId == '' || rowId == null || rowId.length > 1) {
		alert('请选择单条信息进行查看!');
	} else { // 选中
		var url = "/applyAdmin/view/" + rowId;
		$("#open_member_view").attr("href", url);
		if ($.browser.msie) {
			$("#open_member_view").get(0).click();
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", false, false);
			$("#open_member_view").get(0).dispatchEvent(evt);
		}
	}
}