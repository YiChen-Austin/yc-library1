$(function() {
	jQuery("#desktopDefendList").jqGrid(
			{
				url : $("#path",parent.document).val()+"/page/"+'findAlldesktopDefends',
				datatype : "json",
				colNames : [ 'ID', '区域业务名称', 'DIV标签ID', '备注' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					width : 30,
					editable : true,
					edittype : "text",
					sortable : false,
					hidden : true

				}, {
					name : 'title',
					index : 'title',
					width : 80,
					sortable : false
				}, {
					name : 'divId',
					index : 'divId',
					width : 80,
					sortable : false
				}, {
					name : 'remark',
					index : 'remark',
					width : 500,
					sortable : false
				} ],
				loadComplete : function(xhr) {
					$.ajax( {
						type : "POST",
						url : $("#path",parent.document).val()+"/page/"+"findSelfDesktopDefends",
						success : function(data) {
							$.each(data.desktopDefendBeans, function(i, item) {
								jQuery("#desktopDefendList").jqGrid().setSelection(
										item.id, false);								
							});
						}
					});
					return xhr;
				},
				loadError : function(xhr, status, error) {
					isNotLoginError(xhr);
				},
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
				height : 330,
				width : 780,
				caption : "桌面管理列表",
				jsonReader : {
					// 这个元素指明表格所需要的数据从哪里开始
					root : "desktopDefendBeans",
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

	// 删除功能
	$("#setButton").click(function() {

		var ids = jQuery("#desktopDefendList").jqGrid('getGridParam',
				'selarrrow');
		$.ajax( {
			type : "POST",
			url : $("#path",parent.document).val()+"/page/"+"setSelfDesktopDefends",
			data : "deleteIDs=" + ids,
			success : function(data) {
				if (data) {
					jAlert('设置成功!');
				} else {
					jAlert('设置失败!');
				}
			},
			error : function(xhr, textStatus, errorThrown) {
				if (xhr.status == '401'||xhr.status == '500') {
					isNotLoginError(xhr);
					return;
				}
				jAlert('设置失败!');
			}
		});
	
	});

});
function clearDesktopDefendInfo() {
	jQuery("#desktopDefendList").jqGrid().clearGridData();
}
function isNotLoginError(xhr) {
	if (xhr.status == '401') {
		var errMsg = window["eval"]("(" + xhr.getResponseHeader("Error-Json")
				+ ")");
		if (errMsg.reason == 'notLogin') {
			jAlert("你还未登录,请登录!");
			window.parent.location.href = errMsg.content + "/admin/index";
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
			window.location.href = errMsg.content+"/admin/index";
		}
	}
}