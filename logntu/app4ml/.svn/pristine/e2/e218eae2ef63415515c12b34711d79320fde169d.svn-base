//列表显示
$(function() {
	// jqGrid参数配置
	jQuery("#list").jqGrid({ // 获取数据的地址
		url : "",
		datatype : "local",
		// 列显示名称，是一个数组对象
		colNames : [ 'ID', '项目名称 ', '标签', '申请时间', '项目区域' ],
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
			name : 'proName',
			index : 'proName',
			align : 'center',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'tagStr',
			index : 'tagStr',
			align : 'center',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'publishTime',
			index : 'publishTime',
			align : 'center',
			width : 40,
			editable : true,
			edittype : "text",
			sortable : false,
			formatter : "date",
			formatoptions : {
				newformat : 'Y-m-d'
			}
		}, {
			name : 'areaStr',
			index : 'areaStr',
			align : 'center',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false
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
		caption : "项目平台标注"
	});

	jQuery("#list").navGrid('#pager', {
		edit : false,
		add : false,
		del : false,
		refresh : false,
		search : false
	}).navButtonAdd('#pager', {
		id : "cancel_id",
		caption : "取消",
		buttonicon : "ui-icon-plus",
		title : "取消关注投资等",
		position : "last",
		onClickButton : fun_0
	}).navButtonAdd('#pager', {
		id : "concern_id",
		caption : "关注",
		buttonicon : "ui-icon-plus",
		title : "批量关注",
		position : "last",
		onClickButton : fun_1
	}).navButtonAdd('#pager', {
		id : "plan_id",
		caption : "拟投",
		buttonicon : "ui-icon-pencil",
		title : "批量拟投",
		position : "last",
		onClickButton : fun_2
	}).navButtonAdd('#pager', {
		id : "lead_id",
		caption : "领投",
		buttonicon : "ui-icon-pencil",
		title : "批量拟投",
		position : "last",
		onClickButton : fun_3
	}).navButtonAdd('#pager', {
		id : "masses_id",
		caption : "参与众筹",
		buttonicon : "ui-icon-pencil",
		title : "批量拟投",
		position : "last",
		onClickButton : fun_4
	}).navButtonAdd('#pager', {
		id : "already_id",
		caption : "已投",
		buttonicon : "ui-icon-pencil",
		title : "批量拟投",
		position : "last",
		onClickButton : fun_5
	});
	// 列表宽度随浏览器变化成比例变化
	$(window).resize(function() {
		$('#list').jqGrid().setGridWidth($(window).width() * 0.96 - 10);
	});
	// 执行查询
	// toQuery();
	// 绑定tab的点击事情，点击的时候执行查询
	$("#P_All").bind('click', alterTag);
	$("#P_Un_Concern").bind('click', alterTag);
	$("#P_Concern").bind('click', alterTag);
	$("#P_Plan").bind('click', alterTag);
	$("#P_Lead").bind('click', alterTag);
	$("#P_Masses").bind('click', alterTag);
	$("#P_Already").bind('click', alterTag);
	if ($('#p_status').val() == '0')
		$("#P_Un_Concern").click();
	if ($('#p_status').val() == '1')
		$("#P_Concern").click();
	if ($('#p_status').val() == '2')
		$("#P_Plan").click();
	if ($('#p_status').val() == '3')
		$("#P_Lead").click();
	if ($('#p_status').val() == '4')
		$("#P_Masses").click();
	if ($('#p_status').val() == '5')
		$("#P_Already").click();
});
/**
 * 选项卡间的切换
 */
function alterTag() {
	var tag_no = $(this).attr("name");
	$("li").attr("class", "");
	$(this).parent().attr("class", "selectTag");
	if (tag_no == 'P_All') {
		$('#p_status').val('-1'); // 
		var $td = $('#pager_left').find('table tbody tr td');
		$.each($td,function(i, item) {
			$(this).attr('disabled', 'disabled');
			document.getElementById($(this).attr("id")).style.display = "none";
			$(this).unbind("click");
			
			document.getElementById($(this).attr("id")).style.display = "inline";
			$(this).removeAttr('disabled');
			$(this).bind("click", eval("(fun_" + i + ")"));
		});
	}else if (tag_no == 'P_Un_Concern') {
		$('#p_status').val('0'); // 
		var $td = $('#pager_left').find('table tbody tr td');
		$.each($td,function(i, item) {
			$(this).attr('disabled', 'disabled');
			document.getElementById($(this).attr("id")).style.display = "none";
			$(this).unbind("click");
			if (i!=0) {
				document.getElementById($(this).attr("id")).style.display = "inline";
				$(this).removeAttr('disabled');
				$(this).bind("click", eval("(fun_" + i + ")"));
			}
		});
	} else if (tag_no == 'P_Concern') {
		$('#p_status').val('1'); 
		var $td = $('#pager_left').find('table tbody tr td');
		$.each($td, function(i, item) {
			$(this).attr('disabled', 'disabled');
			document.getElementById($(this).attr("id")).style.display = "none";
			$(this).unbind("click");
			if (i!=1) {
				document.getElementById($(this).attr("id")).style.display = "inline";
				$(this).removeAttr('disabled');
				$(this).bind("click", eval("(fun_" + i + ")"));
			}
		});
	} else if (tag_no == 'P_Plan') {
		$('#p_status').val('2'); 
		var $td = $('#pager_left').find('table tbody tr td');
		$.each($td, function(i, item) {
			$(this).attr('disabled', 'disabled');
			document.getElementById($(this).attr("id")).style.display = "none";
			$(this).unbind("click");
			if (i!=2) {
				document.getElementById($(this).attr("id")).style.display = "inline";
				$(this).removeAttr('disabled');
				$(this).bind("click", eval("(fun_" + i + ")"));
			}
		});
	}else if (tag_no == 'P_Lead') {
		$('#p_status').val('3'); 
		var $td = $('#pager_left').find('table tbody tr td');
		$.each($td, function(i, item) {
			$(this).attr('disabled', 'disabled');
			document.getElementById($(this).attr("id")).style.display = "none";
			$(this).unbind("click");
			if (i!=3) {
				document.getElementById($(this).attr("id")).style.display = "inline";
				$(this).removeAttr('disabled');
				$(this).bind("click", eval("(fun_" + i + ")"));
			}
		});
	}else if (tag_no == 'P_Masses') {
		$('#p_status').val('4'); 
		var $td = $('#pager_left').find('table tbody tr td');
		$.each($td, function(i, item) {
			$(this).attr('disabled', 'disabled');
			document.getElementById($(this).attr("id")).style.display = "none";
			$(this).unbind("click");
			if (i!=4) {
				document.getElementById($(this).attr("id")).style.display = "inline";
				$(this).removeAttr('disabled');
				$(this).bind("click", eval("(fun_" + i + ")"));
			}
		});
	}else if (tag_no == 'P_Already') {
		$('#p_status').val('5'); 
		var $td = $('#pager_left').find('table tbody tr td');
		$.each($td, function(i, item) {
			$(this).attr('disabled', 'disabled');
			document.getElementById($(this).attr("id")).style.display = "none";
			$(this).unbind("click");
			if (i!=5) {
				document.getElementById($(this).attr("id")).style.display = "inline";
				$(this).removeAttr('disabled');
				$(this).bind("click", eval("(fun_" + i + ")"));
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
		url : "/projectAdmin/listTag?tag=" + $("#p_status").val()
	}).trigger("reloadGrid");
}
// 取消
function fun_0() {
	var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
	if (gr == '') {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/projectAdmin/utag?tag=0&ids=" + gr,
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
//取消
function fun_1() {
	var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
	if (gr == '') {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/projectAdmin/utag?tag=1&ids=" + gr,
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
//取消
function fun_2() {
	var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
	if (gr == '') {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/projectAdmin/utag?tag=2&ids=" + gr,
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
//取消
function fun_3() {
	var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
	if (gr == '') {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/projectAdmin/utag?tag=3&ids=" + gr,
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
//取消
function fun_4() {
	var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
	if (gr == '') {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/projectAdmin/utag?tag=4&ids=" + gr,
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
//取消
function fun_5() {
	var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
	if (gr == '') {
		alert("请选择需要处理的数据!");
	} else {
		$.ajax({
			url : "/projectAdmin/utag?tag=5&ids=" + gr,
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
function new_open_view() {
	var rowId = jQuery("#list").jqGrid('getGridParam', 'selarrrow');
	if (rowId == '' || rowId == null || rowId.length > 1) {
		alert('请选择单条信息进行查看!');
	} else { // 选中
		var url = "/projectAdmin/view/" + rowId;
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