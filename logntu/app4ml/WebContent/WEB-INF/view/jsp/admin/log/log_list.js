//列表显示
$(function() {
	var belongs = $('#belongs').val();
	if(belongs=='通信管理局'||belongs=='共建共享办公室'){
		$('#supplyNameId').css('display', 'block');
		$('#supplyContentId').css('display', 'block');
	}
	else{
		$('#supplyNameId').css('display', 'none');
		$('#supplyContentId').css('display', 'none');
	}
	// jqGrid参数配置
	jQuery("#list").jqGrid(
			{
				// 获取数据的地址
				url : "findAllLogInfos",
				mtype : "post",
				// 从服务器端返回的数据类型,配置为json
				datatype : "json",
				// 列显示名称，是一个数组对象
				colNames : [ 'ID', '操作对象', '业务ID', '操作类型', '操作时间', 'ip地址',
						'操作用户', '操作人员所属机构 ', '所属运营商' ],
				// {name:表格列的名称,index:当排序时定义排序字段名称的索引,width:宽度,editable:单元格是否可编辑,edittype:可以编辑的类型}
				colModel : [ {
					name : 'id',
					index : 'id',
					width : 5,
					editable : true,
					edittype : "text",
					sortable : true,
					hidden : true

				}, {
					name : 'objectname',
					index : 'objectname',
					width : 100,
					editable : true,
					edittype : "text",
					sortable : true,
					align : "center"
				}, {
					name : 'dataid',
					index : 'dataid',
					width : 100,
					editable : true,
					edittype : "text",
					sortable : true,
					align : "center"
				}, {
					name : 'optype',
					index : 'optype',
					width : 20,
					editable : true,
					edittype : "text",
					sortable : true,
					align : "center"
				}, {
					name : 'dtime',
					index : 'dtime',
					width : 50,
					editable : true,
					edittype : "text",
					sortable : true,
					align : "center"
				}, {
					name : 'ip',
					index : 'ip',
					width : 50,
					editable : true,
					edittype : "text",
					sortable : true,
					align : "center"
				}, {
					name : 'operatorUser',
					index : 'operatorUser',
					width : 30,
					editable : true,
					edittype : "text",
					sortable : true,
					align : "center"
				}, {
					name : 'operatorOrg',
					index : 'operatorOrg',
					width : 40,
					editable : true,
					edittype : "text",
					sortable : true,
					align : "center"
				}, {
					name : 'belongsCompany',
					index : 'belongsCompany',
					width : 40,
					editable : true,
					edittype : "text",
					sortable : true,
					align : "center"
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
				height : 300,
				// 如果设置则按此设置为主，如果没有设置则按colModel中定义的宽度计算
				width : 1000,
				// jsonReader的属性
				jsonReader : {
					// 这个元素指明表格所需要的数据从哪里开始
					root : "logInfoBeans",
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
					rows : "pageBean.pageSize",
					page : "pageBean.curPage",
					total : "pageBean.totalPages",
					records : "pageBean.totalRecords",
					sort : "pageBean.orderBy",
					order : "pageBean.order"
				},

				// 定义表格名称
				caption : "人员列表"
			});

	// 查询
	$("#searchButton").click(
			function() {
				jQuery("#list").jqGrid(
						'setGridParam',
						{
							type : "post",
							datatype : "json",
							url : "findAllLogInfos?"
									+ encodeURI($("#searchForm").ajaxForm()
											.formSerialize()),
							page : 1
						}).trigger("reloadGrid");
			});

	// 删除功能
	$("#deleteButton").click(function() {
		var currentUserId = $("#currentUserId").val();
		var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
		if (gr == '') {
			jAlert("请选择记录!");
		} else {
			if (!confirm('真的要删除吗?删除后将无法恢复!')) {
				return;
			}
			jQuery("#list").jqGrid('setGridParam', {
				type : "post",
				datatype : "json",
				url : "deleteLogInfos?logInfoBean.deleteIDS=" + gr,
				page : 1
			}).trigger("reloadGrid");
		}
	});
});
