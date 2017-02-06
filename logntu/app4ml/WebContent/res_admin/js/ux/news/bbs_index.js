$(function() {
	// ajax调用不使用缓存
	$.ajaxSetup({
		cache : false
	});
	$("#commonCategory_tree").jstree({// menu_tree 为 由Jquery选择器选中的DIV容器的ID
		"themes" : {
			"theme" : "classic",
			"dots" : true,
			"icons" : true
		},
		"json_data" : {
			"ajax" : {
				"url" : "findAllDiscussionCategories",
				"data" : function(n) {
					return {
						id : n.attr ? n.attr("id") : 0
					};
				}
			}
		},
		"core" : {
			"strings" : {
				"loading" : "菜单加载中……"
			}
		},
		// 插件使用右键菜单支持自定义右键菜单
		"plugins" : [ "themes", "json_data", "ui", "crrm" ]
	});
	// $("#commonCategory_tree")
	// .tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
	// types : {
	// // all node types inherit the "default" node type
	// "default" : {
	// draggable : false
	// }
	// },
	// data : {
	// type : "json",
	// async : true,
	// opts : {
	// async : true,
	// method : "POST",
	// url : "findAllDiscussionCategories"// 实际提交的Action
	// }
	// },
	// selected : $("#commonCategoryId").val(),
	// lang : {
	// loading : "菜单加载中……" // 在用户等待数据渲染的时候给出的提示内容，默认为loading
	// },
	// ui : {
	// theme_name : 'classic' // 更换皮肤，设置样式
	// },
	// callback : {
	//
	// onload : function(t) {
	// t.settings.data.opts.static = false;
	// },
	// onselect : function(NODE, TREE_OBJ) {
	// $("#commonCategoryId").attr("value", NODE.id);
	// jQuery("#bbs_list").jqGrid().clearGridData();
	// jQuery("#bbs_list")
	// .jqGrid(
	// 'setGridParam',
	// {
	// url :
	// "findAllCommonArticlesByCommonCategory?commonArticleBean.commonCategoryId="+NODE.id,
	// page : 1
	// }).trigger(
	// "reloadGrid")
	//						
	// }
	// },
	// // 插件使用右键菜单支持自定义右键菜单
	// plugins : {}
	//
	// });
	$("#commonCategory_tree")
			.bind(
					"select_node.jstree",
					function(e, data) {
						var node = data.rslt.obj;
						$("#commonCategoryId").attr("value",node.attr("id"));
						jQuery("#bbs_list").jqGrid().clearGridData();
						jQuery("#bbs_list")
								.jqGrid(
										'setGridParam',
										{
											url : "findAllCommonArticlesByCommonCategory?commonArticleBean.commonCategoryId="
													+node.attr("id"),
											page : 1
										}).trigger("reloadGrid");
					});
	// 公共论坛列表
	jQuery("#bbs_list")
			.jqGrid(
					{
						// 获取数据的地址
						url : "findAllCommonArticlesByCommonCategory",
						datatype : "json",
						colNames : [ '表情', '主题', '作者', '人气', '发表时间',
								'最新回复时间|回复人', '操作' ],
						colModel : [ {
							name : 'emotion',
							index : 'emotion',
							align : 'left',
							width : 30,
							sortable : false
						}, {
							name : 'title',
							index : 'title',
							align : 'left',
							width : 100,
							sortable : false
						}, {
							name : 'publishAuthorName',
							index : 'publishAuthorName',
							align : 'left',
							width : 60,
							sortable : false
						}, {
							name : 'readCount',
							index : 'readCount',
							align : 'left',
							width : 40,
							sortable : false
						}, {
							name : 'publishTime',
							index : 'publishTime',
							align : 'left',
							width : 80,
							sortable : false
						}, {
							name : 'lastTimeAuthorName',
							index : 'lastTimeAuthorName',
							align : 'left',
							width : 100,
							sortable : false
						}, {
							name : 'deleteButton',
							index : 'deleteButton',
							align : 'left',
							width : 140,
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
						height : 258,
						width : 630,
						caption : "公共论坛列表",
						jsonReader : {
							// 这个元素指明表格所需要的数据从哪里开始
							root : "commonArticleBeans",
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
						loadComplete : function() {
							// grid button添加事件
							$('#bbs_list :button[name="deleteButton"]').bind(
									'click', deleteArticle);
							$('#bbs_list :button[name="lockedButton"]').bind(
									'click', lockedArticle);
							$('#bbs_list :button[name="unlockedButton"]').bind(
									'click', unlockedArticle);

						}
					});
	// 查询
	$("#searchButton").click(
			function() {
				jQuery("#bbs_list").jqGrid(
						'setGridParam',
						{
							type : "post",
							datatype : "json",
							url : "findAllCommonArticlesByCommonCategory?"
									+ encodeURI($("#searchCondition")
											.ajaxForm().formSerialize()),
							page : 1
						}).trigger("reloadGrid");
			});
	// 删除帖子
	function deleteArticle() {
		var commonCategoryId = $("#commonCategoryId").val();
		var deleteid = $(this).parents('tr').attr('id');
		if (!confirm('真的要删除吗?删除后将无法恢复!')) {
			return;
		}
		jQuery("#bbs_list")
				.jqGrid(
						'setGridParam',
						{
							type : "post",
							datatype : "json",
							url : "deleteCommonArticles?commonArticleBean.commonCategoryId="
									+ commonCategoryId
									+ "&commonArticleBean.deleteIDs="
									+ deleteid,
							page : 1
						}).trigger("reloadGrid");

	}
	// 锁定帖子
	function lockedArticle() {
		var commonCategoryId = $("#commonCategoryId").val();
		var lockedid = $(this).parents('tr').attr('id');
		if (!confirm('真的要锁定帖子吗?锁定后将无法回复帖子!')) {
			return;
		}
		jQuery("#bbs_list")
				.jqGrid(
						'setGridParam',
						{
							type : "post",
							datatype : "json",
							url : "lockedCommonArticles?commonArticleBean.commonCategoryId="
									+ commonCategoryId
									+ "&commonArticleBean.id=" + lockedid,
							page : 1
						}).trigger("reloadGrid");

	}
	// 恢复帖子
	function unlockedArticle() {
		var commonCategoryId = $("#commonCategoryId").val();
		var lockedid = $(this).parents('tr').attr('id');
		if (!confirm('真的要恢复帖子吗?恢复后将可以继续回复帖子!')) {
			return;
		}
		jQuery("#bbs_list")
				.jqGrid(
						'setGridParam',
						{
							type : "post",
							datatype : "json",
							url : "unlockedCommonArticles?commonArticleBean.commonCategoryId="
									+ commonCategoryId
									+ "&commonArticleBean.id=" + lockedid,
							page : 1
						}).trigger("reloadGrid");

	}
	// 发送新帖
	$("#sendButton")
			.click(
					function() {
						var commonCategoryId = $("#commonCategoryId").val();
						if (commonCategoryId == "")
							jAlert("请选择论坛类别!");
						else
							window.location = "createCommonArticle?commonArticleBean.commonCategoryId="
									+ commonCategoryId;
					});

});
function isNotLoginError(xhr) {
	if (xhr.status == '401') {
		var errMsg = window["eval"]("(" + xhr.getResponseHeader("Error-Json")
				+ ")");
		if (errMsg.reason == 'notLogin') {
			jAlert("你还未登录,请登录!");
			window.parent.location.href = errMsg.content + "/page/login.jsp";
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
			window.location.href = errMsg.content + "/page/login.jsp";
		}
	}
}
