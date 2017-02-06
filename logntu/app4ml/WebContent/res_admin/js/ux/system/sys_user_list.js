//列表显示
$(function() {
	// jqGrid参数配置
	jQuery("#list").jqGrid(
			{
				// 获取数据的地址
				url : $("#path",parent.document).val()+"/admin/"+"findAllSysUsers",
				mtype : "post",
				// 从服务器端返回的数据类型,配置为json
				datatype : "json",
				// 列显示名称，是一个数组对象
				colNames : [ 'ID', '姓名', '性别', '身份证号', '用户名', '出生日期',
						'生效日期', '失效日期', '状态', '操作员','标志位','所在组织机构' ],
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
					width : 30,
					editable : true,
					edittype : "text",
					sortable : false
				}, {
					name : 'gender',
					index : 'gender',
					width : 20,
					editable : true,
					edittype : "text",
					sortable : false
				}, {
					name : 'identityCard',
					index : 'identityCard',
					width : 50,
					editable : true,
					edittype : "text",
					sortable : false
				}, {
					name : 'userName',
					index : 'userName',
					width : 30,
					editable : true,
					edittype : "text",
					sortable : false
				}, {
					name : 'birthDay',
					index : 'birthDay',
					width : 40,
					editable : true,
					edittype : "text",
					sortable : false
				}, {
					name : 'effectStartDate',
					index : 'effectStartDate',
					width : 40,
					editable : true,
					edittype : "text",
					sortable : false
				}, {
					name : 'effectEndDate',
					index : 'effectEndDate',
					width : 40,
					editable : true,
					edittype : "text",
					sortable : false
				}, {
					name : 'disabledFlag',
					index : 'disabledFlag',
					width : 20,
					editable : true,
					edittype : "text",
					sortable : false
				}, {
					name : 'operatorFlag',
					index : 'operatorFlag',
					width : 20,
					editable : true,
					edittype : "text",
					sortable : false
				} , {
					name : 'flag',
					index : 'flag',
					width : 20,
					editable : true,
					edittype : "text",
					sortable : false,
					hidden : true
				}, {
					name : 'orgNames',
					index : 'orgNames',
					width : 50,
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
				height : 300,
				// 如果设置则按此设置为主，如果没有设置则按colModel中定义的宽度计算
				width : 800,
				// jsonReader的属性
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
							url : $("#path",parent.document).val()+"/admin/"+"findAllSysUsers?"
									+ encodeURI($("#searchForm").ajaxForm()
											.formSerialize()),
							page : 1
						}).trigger("reloadGrid");
			});

	// 新增功能
	$("#createButton").click(function() {
		window.location = $("#path",parent.document).val()+"/admin/"+"createSysUser";
		});
	// 修改功能
	$("#updateButton").click(function() {
		var gr;
		var rs;
		var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
		if (gr == '' || gr.length>1) {
			jAlert("请选择要修改的记录!");
			return false;
		}else{
			gr = jQuery("#list").jqGrid('getGridParam', 'selrow');
			if (gr) {
				rs = jQuery("#list").jqGrid('getRowData', gr);
				if(rs.flag=='0')
					jAlert("禁止修改系统管理人员,请去掉此人员再进行修改操作!");
				else
					window.location = $("#path",parent.document).val()+"/admin/"+"findSysUser?id=" + rs.id;
			}			
		}
	});
	//恢复初始密码密码
	$("#initButton").click(function() {
		var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
		if (gr == '') {
			jAlert("请选择记录!");
		} else {
			if(!confirm('真的要恢复初始密码吗?')){      
			       return;      
			 }   
			jQuery("#list").jqGrid('setGridParam', {
				type : "post",
				datatype : "json",
				url : $("#path",parent.document).val()+"/admin/"+"initPassword?deleteIDs=" + gr,
				page : 1
			}).trigger("reloadGrid");
		}
	});
	// 删除功能
	$("#deleteButton").click(function() {
		var currentUserId = $("#currentUserId").val();
		var gr = jQuery("#list").jqGrid("getGridParam", "selarrrow");
		if (gr == '') {
			jAlert("请选择记录!");
		} else {
			var len = gr.length;
			for(var i=0;i<len;i++){
				var id = gr[i];
				if(id==currentUserId){
					jAlert("禁止删除当前登录用户!");
					return;
				}
				var flag = jQuery("#list")
				.getRowData(id).flag;
				if(flag=='0'){
					jAlert("禁止删除系统管理人员,请去掉此人员再进行删除操作!");
					return;
				}
			}
			if(!confirm('真的要删除吗?删除后将无法恢复!')){      
			       return;      
			 }   
			jQuery("#list").jqGrid('setGridParam', {
				type : "post",
				datatype : "json",
				url : $("#path",parent.document).val()+"/admin/"+"deleteSysUsers?deleteIDs=" + gr,
				page : 1
			}).trigger("reloadGrid");
		}
	});
});
