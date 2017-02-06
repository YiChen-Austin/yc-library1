//列表显示
$(function() {
	// jqGrid参数配置
	jQuery("#articleList").jqGrid({
		// 获取数据的地址
		url : "",
		datatype : "local",
		// 列显示名称，是一个数组对象
		colNames : [ 'ID' ,'封面','文章标题','分类', '文章摘要', '时间', '发布机构'],
		// {name:表格列的名称,index:当排序时定义排序字段名称的索引,width:宽度,editable:单元格是否可编辑,edittype:可以编辑的类型}
		colModel : [ {
			name : 'id',
			index : 'id',
			align : 'center',
			width : 5,
			editable : true,
			edittype : "text",
			sortable : false,
			hidden : true

		}, {
			name : 'imgUrl',
			index : 'imgUrl',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'title',
			index : 'title',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'categoryName',
			index : 'categoryName',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'artAbstract',
			index : 'artAbstract',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'publishTime',
			index : 'publishTime',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false,
			formatter : "date",
			formatoptions : {
				newformat : 'Y-m-d'
			}
		},{
			name : 'publishOrgName',
			index : 'publishOrgName',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}],
		altRows : true,
		// 定义是否可以多选
		multiselect : true,
		// 在grid上显示记录条数，这个参数是要被传递到后台
		rowNum : 30,
		// 一个数组用来调整表格显示的记录数，此参数值会替代rowNum参数值传给服务器端。如果为空则不显示，设置格式：[10,20,30]
		rowList : [ 10, 15, 20, 30 ],
		// 定义翻页用的导航栏，必须是有效的html元素
		pager : '#articlePager',
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
		width : 1400,
		// jsonReader的属性
		jsonReader : {
			// 这个元素指明表格所需要的数据从哪里开始
			root : "articleBeans",
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
		caption : "文章基本信息"
	});
	jQuery("#articleList").closest(".ui-jqgrid-bdiv").css({'overflow-x':'hidden'});
	toQuery();
	/**
	 * 执行查询
	 */
	function toQuery() {
		// 清空表格数据，清空tab记录条数
		$("#articleList").clearGridData();
		jQuery("#articleList").jqGrid('setGridParam', {
			type : "post",
			datatype : "json",
			url : $("#path",parent.document).val()+"/articleAdmin/findArticleAll"
		}).trigger("reloadGrid");
	}
	// 查询
	$("#searchArticleButton").click(
			function() {
				jQuery("#articleList").jqGrid(
						'setGridParam',
						{
							type : "post",
							datatype : "json",
							url : $("#path",parent.document).val()+"/articleAdmin/findArticleAll?"
									+ encodeURI($("#searchArticleForm").ajaxForm()
											.formSerialize()),
							page : 1
						}).trigger("reloadGrid");
			});
	// 查看
	$("#singleArticleButton").click(function() {
		var rowId = jQuery("#articleList").jqGrid('getGridParam', 'selarrrow');
		if (rowId == '' || rowId == null || rowId.length > 1) {
			alert('请选择单条信息数据修改!');
		} else { // 选中
			window.location = $("#path",parent.document).val()+"/articleAdmin/viewArticle?id=" + rowId;
		}
	});
	// 添加
	$("#saveArticleButton").click(function() {
		window.location = $("#path",parent.document).val()+"/articleAdmin/createArticle";
	});
	// 修改
	$("#updateArticleButton").click(function() {
		var rowId = jQuery("#articleList").jqGrid('getGridParam', 'selarrrow');
		if (rowId == '' || rowId == null || rowId.length > 1) {
			alert('请选择单条信息数据修改!');
		} else { // 选中
			window.location = $("#path",parent.document).val()+"/articleAdmin/updateArticle?id=" + rowId;
		}
	});
	// 删除
	$("#deleteArticleButton").click(function() {
		var gr = jQuery("#articleList").jqGrid("getGridParam", "selarrrow");
		if (gr == '') {
			alert("请选择信息数据删除!");
		} else {
			$.ajax({
				url : $("#path",parent.document).val()+"/articleAdmin/deleteArticle?deleteIds=" + gr,
				type : 'POST',
				success : function(data) {
				var $temp=eval('(' + data + ')');
					if ($temp.result == true) {
						alert("删除成功!");
						toQuery();
					}
				}
			});
		}
	});
	// 导入
	$("#importArticleButton").click(function() {
		$("#filediv").dialog("open");
	});
	// 导出
	$("#exportArticleButton").click(function() {
		window.location = "expArticleAll.action?"
			+ encodeURI($("#searchArticleForm")
					.ajaxForm().formSerialize());
	});

	$("#articleFileBn").click(function() {
		if (checkFile()) {
			$("#loading").ajaxStart(function() {
				$(this).show();
			}).ajaxComplete(function() {
				$(this).hide();
			});
			$.ajaxFileUpload({
				url : $("#path",parent.document).val()+'/articleAdmin/impArticleAll', // 需要链接到服务器地址
				secureuri : false,
				fileElementId : 'articleFile', // 文件选择框的id属性
				dataType : 'json', // 服务器返回的格式，可以是json
				success : function(data, status) { // 相当于java中try语句块的用法
					$("#filediv").dialog("close");
					alert("上传成功！");
					toQuery();
				},
				error : function(data, status, e) {
					$("#filediv").dialog("close");
					alert("上传失败！");
					toQuery();
				}
			});
		}
	});

	$("#filediv").dialog({
		modal : true,
		width : 600,
		height : 200,
		minHeight : 200,
		minWidth : 600,
		maxHeight : 200,
		maxWidth : 600
	}).dialog("close");
	load_article_category();
});
function checkFile() {
	var myFile = $("#articleFile").val();
	if (myFile == '') {
		$("#errorDescription").show();
		$("#errorDescription").attr("innerHTML",
				"<ul><li><span>请选择要上传文件!</span></li></ul>");
		return false;
	} else if (myFile.substring(myFile.length - 4) != '.xls') {
		$("#errorDescription").show();
		$("#errorDescription").attr("innerHTML",
				"<ul><li><span>请选择后缀名为.xls的上传文件!</span></li></ul>");
		return false;
	} else {
		$("#errorDescription").hide();
		return true;

	}
}
function load_article_category() {
	$.post($("#path",parent.document).val()+"/articleAdmin/findArticleCategorys", function(json) {
		if (json.result == true) {
			$.each(json.baseCategoryBeans, function(i, item) {
				$("<option value='" + item.id + "'>" + item.name + "</option>")
						.appendTo("#categoryId");
			});
		}
	});
}