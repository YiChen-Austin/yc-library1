$(function() {
	// 片区信息grid
	var regionId = '';
	jQuery("#regionList").jqGrid( {
		url : $("#path",parent.document).val()+"/admin/"+'findAllRegions',
		datatype : "json",
		mtype : 'POST',
		colNames : ['片区名称', '片区代码', '备注' ],
		colModel : [ {
			name : 'cnName',
			index : 'cnName',
			width : 90,
			sortable : false
		}, {
			name : 'enName',
			index : 'enName',
			width : 80,
			sortable : false
		}, {
			name : 'remark',
			index : 'remark',
			width : 60,
			sortable : false
		}],
		multiselect : false,
		onSelectRow : function(id) {
			clearBranchInfo();
			jQuery("#branchInfo").jqGrid('setGridParam', {
				url : $("#path",parent.document).val()+"/admin/"+"findAllBranchs",
				page : 1
			}).trigger("reloadGrid");
			regionId = jQuery("#regionList").jqGrid('getGridParam', 'selrow');
		},

		rowNum : 15,
		// 一个数组用来调整表格显示的记录数，此参数值会替代rowNum参数值传给服务器端。如果为空则不显示，设置格式：[10,20,30]
		rowList : [ 10, 15, 20, 30 ],
		pager : '#pager',
		sortname : 'id',
		viewrecords : true,
		sortorder : "desc",
		height : 370,
		width : 500,
		caption : "片区信息列表",
		jsonReader : {
			// 这个元素指明表格所需要的数据从哪里开始
			root : "regionBeans",
			page : "curPage", // 当前页
			total : "pageBean.totalPages", // 总页数
			records : "pageBean.totalRecords", // 总记录数
			rows : "pageBean.pageSize",// 每页显示的行数
			sort : "pageBean.sort",// 排序字段
			order : "pageBean.order",// 排序方式
			// 指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素,这个名字就是colModel中的名字
			repeatitems : false
		},
		loadError : function(xhr, status, error) {
			isNotLoginError(xhr);
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
	// 分公司grid
	jQuery("#branchInfo").jqGrid(
			{
				datatype : "json",
				mtype : 'get',
				colNames : [ '分公司名称', '简称', '备注' ],

				colModel : [ {
					name : 'name',
					index : 'name',
					width : 100,
					sortable : false
				}, {
					name : 'shortName',
					index : 'shortName',
					width : 60,
					sortable : false

				}, {
					name : 'remark',
					index : 'remark',
					width : 140,
					editable : true,
					edittype : "text",
					sortable : false,
					hidden : false
				} ],
				loadComplete : function(xhr) {
					$.ajax( {
						type : "POST",
						url : $("#path",parent.document).val()+"/admin/"+"findBranchesByRegionId",
						data : "regionBean.id=" + regionId,
						success : function(data) {
							$.each(data.sysOrganizationBeans, function(i, item) {
								jQuery("#branchInfo").jqGrid().setSelection(
										item.id, false);							
							});
						}
					});
					return xhr;
				},
				loadError : function(xhr, status, error) {
					isNotLoginError(xhr);
				},
				multiselect : true,
				sortname : 'id',
				viewrecords : true,
				sortorder : "desc",
				height : 360,
				width : 360,
				caption : "分公司列表",
				jsonReader : {
					root : "sysOrganizationBeans",
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
				rowNum : 1000

			});
	// 查询按钮
	$("#searchButton").click(
			function() {
				clearBranchInfo();
				jQuery("#regionList").jqGrid(
						'setGridParam',
						{
							url : $("#path",parent.document).val()+"/admin/"+"findAllRegions?"
									+ encodeURI($("#searchCondition")
											.ajaxForm().formSerialize()),
							page : 1
						}).trigger("reloadGrid");
			});
	$("#submitBtn").click(
			function() {
				var ids = jQuery("#branchInfo").jqGrid('getGridParam',
						'selarrrow');
				$.ajax( {
					type : "POST",
					url : $("#path",parent.document).val()+"/admin/"+"setBranches",
					data : "regionBean.id=" + regionId
							+ "&regionBean.branchIDs=" + ids,
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
function clearBranchInfo() {
	jQuery("#branchInfo").jqGrid().clearGridData();
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