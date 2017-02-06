$(function() {
	var businessDicId;
	jQuery("#businessDictionaryList").jqGrid({
				url : $("#path",parent.document).val()+"/admin/"+'findAllBusinessDictionaries',
				datatype : "json",
				colNames : [ 'ID', '中文名称', '英文名称', '编辑可否', '备注' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					width : 30,
					editable : true,
					edittype : "text",
					sortable : false,
					hidden : true

				}, {
					name : 'cnName',
					index : 'cnName',
					width : 80,
					sortable : false
				}, {
					name : 'enName',
					index : 'enName',
					width : 80,
					sortable : false
				}, {
					name : 'flag',
					index : 'flag',
					width : 50,
					sortable : false
				}, {
					name : 'remark',
					index : 'remark',
					width : 500,
					sortable : false
				} ],
				onSelectRow : function(id) {
					businessDicId = jQuery("#businessDictionaryList").jqGrid(
							'getGridParam', 'selrow');
					var flag = jQuery("#businessDictionaryList").getRowData(
							businessDicId).flag;
					var _buttonTextColor = $("#createButton").css("color");
					if (flag == '否') {
						$("#updateButton").css("color", "#ACA899");
						$("#deleteButton").css("color", "#ACA899");
						$("#updateButton").attr("disabled", "disabled");
						$("#deleteButton").attr("disabled", "disabled");
					} else if (flag == '是') {
						$("#updateButton").css("color", _buttonTextColor);
						$("#deleteButton").css("color", _buttonTextColor);
						$("#updateButton").removeAttr("disabled");
						$("#deleteButton").removeAttr("disabled");
					}
				},
				altRows : true,
				// 定义是否可以多选
				multiselect : false,
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
				caption : "业务字典列表",
				jsonReader : {
					// 这个元素指明表格所需要的数据从哪里开始
					root : "businessDictionaryBeans",
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
	// 查询按钮
	$("#searchButton").click(
			function() {

				jQuery("#businessDictionaryList").jqGrid(
						'setGridParam',
						{
							url : $("#path",parent.document).val()+"/admin/"+"findAllBusinessDictionaries?"
									+ encodeURI($("#searchForm").ajaxForm()
											.formSerialize()),
							page : 1
						}).trigger("reloadGrid");
			});
	// 新增功能
	$("#createButton").click(function() {
		window.location = $("#path",parent.document).val()+"/admin/"+"createBusinessDictionary";
	});
	// 修改功能
	$("#updateButton")
			.click(
					function() {
						if (null == businessDicId){
							jAlert("请选择您要修改的记录！");
						}
						else {
							window.location = $("#path",parent.document).val()+"/admin/"+"findBusinessDictionary?id="
								+ businessDicId;
						}
	});
	
	// 删除功能
	$("#deleteButton")
			.click(
					
					function() {
						if (null == businessDicId){
							jAlert("请选择您要删除的记录！");
						}
						else {
							if (!confirm('真的要删除吗？删除后将无法恢复!')) {
								return;
							}
							jQuery("#businessDictionaryList")
									.jqGrid(
											'setGridParam',
											{
												type : "post",
												datatype : "json",
												url : $("#path",parent.document).val()+"/admin/"+"deleteBusinessDictionary?deleteIDs="
														+ businessDicId,
												page : 1
											}).trigger("reloadGrid");
						}
					});

});