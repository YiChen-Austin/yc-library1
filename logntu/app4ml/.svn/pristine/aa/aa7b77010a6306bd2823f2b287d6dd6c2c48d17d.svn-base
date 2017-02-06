$(function() {
	var path=$("#path",parent.document).val();
	// 用户信息grid
	var userId = '';
	//登录用户ID
	var loginUserId = $("#loginUserId").val();
	var userFlag = '1';
	var roleId;
	jQuery("#userInfoList").jqGrid( {
		url : $("#path",parent.document).val()+"/admin/"+'findAllSysUsers',
		datatype : "json",
		mtype : 'POST',
		colNames : [ '姓名', '用户名', '性别', '出生日期', '身份证号','标志位' ],
		colModel : [ {
			name : 'realName',
			index : 'realName',
			width : 90,
			sortable : false
		}, {
			name : 'userName',
			index : 'userName',
			width : 80,
			sortable : false
		}, {
			name : 'gender',
			index : 'gender',
			width : 60,
			sortable : false
		}, {
			name : 'birthDay',
			index : 'birthDay',
			width : 100,
			sortable : false
		}, {
			name : 'identityCard',
			index : 'identityCard',
			width : 170,
			sortable : false
		}, {
			name : 'flag',
			index : 'flag',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false,
			hidden : true
		} ],
		multiselect : false,
		onSelectRow : function(id) {
			clearUserRoleInfo();
			jQuery("#userRoleInfo").jqGrid('setGridParam', {
				url : $("#path",parent.document).val()+"/admin/"+"findAllSysRoles",
				page : 1
			}).trigger("reloadGrid");
			userId = jQuery("#userInfoList").jqGrid('getGridParam', 'selrow');
			userFlag = jQuery("#userInfoList").getRowData(userId).flag;
			if(userFlag=="1"&&userId==loginUserId){
				$("#submitBtn").attr("disabled", "disabled");
			}else
			{
				$("#submitBtn").removeAttr("disabled");
			}
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
		caption : "用户信息",
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
	// 角色信息grid
	jQuery("#userRoleInfo").jqGrid(
			{
				datatype : "json",
				mtype : 'get',
				colNames : [ '角色名', '备注', '标志位' ],

				colModel : [ {
					name : 'name',
					index : 'name',
					width : 100,
					sortable : false
				}, {
					name : 'remark',
					index : 'remark',
					width : 160,
					sortable : false

				}, {
					name : 'flag',
					index : 'flag',
					width : 20,
					editable : true,
					edittype : "text",
					sortable : false,
					hidden : true
				} ],
				loadComplete : function(xhr) {
					$.ajax( {
						type : "POST",
						url : $("#path",parent.document).val()+"/admin/"+"findSysRolesBySysUserId",
						data : "id=" + userId,
						success : function(data) {
							$.each(data.sysRoleBeans, function(i, item) {
								jQuery("#userRoleInfo").jqGrid().setSelection(
										item.id, false);
								if(item.flag=='0')
									roleId = item.id;
								
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
				width : 260,
				caption : "角色列表",
				jsonReader : {
					root : "sysRoleBeans",
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
				clearUserRoleInfo();
				jQuery("#userInfoList").jqGrid(
						'setGridParam',
						{
							url : $("#path",parent.document).val()+"/admin/"+"findAllSysUsers?"
									+ encodeURI($("#searchCondition")
											.ajaxForm().formSerialize()),
							page : 1
						}).trigger("reloadGrid");
			});
	$("#submitBtn").click(
			function() {
				var ids = jQuery("#userRoleInfo").jqGrid('getGridParam',
						'selarrrow');
				if(userFlag=='0'){
					var len = ids.length;
					var isSet = false;
					for(var i=0;i<len;i++){
						var id = ids[i];
						var flag = jQuery("#userRoleInfo")
						.getRowData(id).flag;
						if(flag=='0'){
							if(id==roleId)
								isSet = true;
							break;
						}
					}
					if(!isSet){
						jAlert("禁止取消系统管理员的默认角色,请重新设置!");
						return;
					}
				}
//				else{
//					if(ids.length>1){
//						jAlert("非管理用户最多只能设置一个角色,请重新设置!");
//						return;
//					}
//				}
				$.ajax( {
					type : "POST",
					url : $("#path",parent.document).val()+"/admin/"+"setSysRole",
					data : "id=" + userId
							+ "&sysRoleIDs=" + ids,
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
function clearUserRoleInfo() {
	jQuery("#userRoleInfo").jqGrid().clearGridData();
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
			window.location.href = errMsg.content+ "/admin/index";
		}
	}
}