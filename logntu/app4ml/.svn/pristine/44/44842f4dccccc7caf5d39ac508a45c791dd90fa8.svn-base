//列表显示
$(function() {
	// jqGrid参数配置
	jQuery("#memberList").jqGrid({
		// 获取数据的地址
		url : "",
		datatype : "local",
		// 列显示名称，是一个数组对象
		colNames : [ 'ID', '姓名', '用户名', 'openid', '昵称', '角色', '状态', '注册时间', '登录时间', '区域'],
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
			name : 'realName',
			index : 'realName',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'userName',
			index : 'userName',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'openId',
			index : 'openId',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'nickName',
			index : 'nickName',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'role',
			index : 'role',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'statusStr',
			index : 'statusStr',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'createTime',
			index : 'createTime',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false,
			formatter : "date",
			formatoptions : {
				newformat : 'Y-m-d'
			}
		}, {
			name : 'curLoginTimeStr',
			index : 'curLoginTimeStr',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		}, {
			name : 'areaStr',
			index : 'areaStr',
			align : 'center',
			width : 20,
			editable : true,
			edittype : "text",
			sortable : false
		} ],
		altRows : true,
		// 定义是否可以多选
		multiselect : true,
		// 在grid上显示记录条数，这个参数是要被传递到后台
		rowNum : 30,
		// 一个数组用来调整表格显示的记录数，此参数值会替代rowNum参数值传给服务器端。如果为空则不显示，设置格式：[10,20,30]
		rowList : [ 10, 15, 20, 30 ],
		// 定义翻页用的导航栏，必须是有效的html元素
		pager : '#memberPager',
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
			root : "memberBeans",
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
		caption : $("#typeValue").val()+'列表'
	});
	jQuery("#memberList").closest(".ui-jqgrid-bdiv").css({'overflow-x':'hidden'});
	toQuery();
	/**
	 * 执行查询
	 */
	function toQuery() {
		// 清空表格数据，清空tab记录条数
		$("#memberList").clearGridData();
		jQuery("#memberList").jqGrid('setGridParam', {
			type : "post",
			datatype : "json",
			url : $("#path",parent.document).val()+"/memberAdmin/list?type="+$("#typeKey").val()
		}).trigger("reloadGrid");
	}
	// 查询
	$("#searchMemberButton").click(
			function() {
				jQuery("#memberList").jqGrid(
						'setGridParam',
						{
							type : "post",
							datatype : "json",
							url : $("#path",parent.document).val()+"/memberAdmin/list?"
									+ encodeURI($("#searchMemberForm").ajaxForm()
											.formSerialize()),
							page : 1
						}).trigger("reloadGrid");
			});
	// 查看
	$("#singleMemberButton").click(function() {
		var rowId = jQuery("#memberList").jqGrid('getGridParam', 'selarrrow');
		if (rowId == '' || rowId == null || rowId.length > 1) {
			alert('请选择单条信息进行查看!');
		} else { // 选中
			$.ajax({
				url : "/memberAdmin/detail?id="+rowId+"&type="+$("#typeKey").val(),
				type : 'POST',
				success : function(data) {
					var $temp = eval('(' + data + ')');
					if ($temp && $temp.member) {
						var _html='';
						_html+='<tr>';
						_html+='<td  class="ftt2" colspan="2" ><img src="'+$temp.member.portrait+'" style="width:100px;height:100px;" title="个人头像"></td>';
						_html+='<td colspan="2" ><img src="'+$temp.member.handIdentification+'" style="width:100px;height:100px;" title="手持身份证"></td>';
						_html+='</tr>';
						_html+='<tr>';
						_html+='<td class="ftt2"><lable>用户名:</lable></td>';
						_html+='<td colspan="3">'+$temp.member.userName+'</td>';
						_html+='</tr>';
						_html+='<tr>';
						_html+='<td class="ftt2"><lable>openId:</lable></td>';
						_html+='<td colspan="3">'+$temp.member.openId+'</td>';
						_html+='</tr>';
						_html+='<tr>';
						_html+='<td class="ftt2"><lable>姓名:</lable></td>';
						_html+='<td colspan="3">'+$temp.member.realName+'</td>';
						_html+='</tr>';
						_html+='<tr>';						
						_html+='<td class="ftt2"><lable>联系方式:</lable></td>';
						_html+='<td colspan="3">'+$temp.member.mobile+'</td>';
						_html+='</tr>';
						_html+='<tr>';
						_html+='<td class="ftt2"><lable>昵称:</lable></td>';
						_html+='<td colspan="3">'+$temp.member.nickName+'</td>';
						_html+='</tr>';
						_html+='<tr>';
						_html+='<td class="ftt2"><lable>单位:</lable></td>';
						_html+='<td colspan="3">'+$temp.member.orgName+'</td>';
						_html+='</tr>';
						_html+='<tr>';
						_html+='<td class="ftt2"><lable>身份证:</lable></td>';
						_html+='<td colspan="3">'+$temp.member.identification+'</td>';
						_html+='</tr>';
						$("#member_view").html(_html);
						
						if ($temp && $temp.memberTs) {
							var _att='';
							_att+='<tr>';
							_att+='<td class="ftt2" colspan="3">天使</td>';
							_att+='</tr>';
							_att+='<tr>';
							_att+='<td class="ftt2">单位：</td>';
							_att+='<td colspan="3">'+$temp.memberTs.orgName+'</td>';
							_att+='</tr>';
							_att+='<tr>';
							_att+='<td class="ftt2">电话：</td>';
							_att+='<td colspan="3">'+$temp.memberTs.tel+'</td>';
							_att+='</tr>';
							$("#member_att").html(_att);
						}
						if ($temp && $temp.memberDs) {
							var _att='';
							_att+='<tr>';
							_att+='<td class="ftt2" colspan="3">导师</td>';
							_att+='</tr>';
							_att+='<tr>';
							_att+='<td class="ftt2">单位：</td>';
							_att+='<td colspan="3">'+$temp.memberDs.orgName+'</td>';
							_att+='</tr>';
							_att+='<tr>';
							_att+='<td class="ftt2">电话：</td>';
							_att+='<td colspan="3">'+$temp.memberDs.tel+'</td>';
							_att+='</tr>';
							$("#member_att").html(_att);
						}
						if ($temp && $temp.memberJg) {
							var _att='';
							_att+='<tr>';
							_att+='<td class="ftt2" colspan="3">创业服务机构</td>';
							_att+='</tr>';
							_att+='<tr>';
							_att+='<td class="ftt2">单位：</td>';
							_att+='<td colspan="3">'+$temp.memberJg.orgName+'</td>';
							_att+='</tr>';
							_att+='<tr>';
							_att+='<td class="ftt2">电话：</td>';
							_att+='<td colspan="3">'+$temp.memberJg.tel+'</td>';
							_att+='</tr>';
							$("#member_att").html(_att);
						}
						$("#member_view").dialog("open");
					}
				}
			});		
		}
	});
	// 打开添加窗口
	$("#saveMemberButton").click(function() {
		$("#new_userName").val('');
		$("#new_realName").val('');
		$("#new_orgName").val('');
		$("#member_add").dialog("open");
	});
	//关闭
	$("#closeNewMemberButton").click(function() {
		$("#member_add").dialog("close");
	});
	//保存新增
	$("#saveNewMemberSubmit").click(function() {
		//保存数据
		var cdata = encodeURI($("#memberAdddForm").ajaxForm().formSerialize());
		$.ajax({
			url : "/memberAdmin/save?type="+$("#typeKey").val()+"&"+cdata,
			type : 'POST',
			success : function(data) {
				var $temp = eval('(' + data + ')');
				if ($temp && $temp.result==true) {
					$("#member_add").dialog("close");	
					alert("添加成功");
					toQuery();
				}else{
					alert("添加失败，已有相同用户名存在");
				}
			}
		});				
	});
	//初始化密码
	$("#initPwButton").click(function() {
		var rowId = jQuery("#memberList").jqGrid('getGridParam', 'selarrrow');
		if (rowId == '' || rowId == null || rowId.length > 1) {
			alert('请选择单条信息进行处理!');
		} else { // 选中
			$.ajax({
				url : "/memberAdmin/initpw?id="+rowId,
				type : 'POST',
				success : function(data) {
					var $temp = eval('(' + data + ')');
					if ($temp && $temp.result==true) {
						alert("初始化成功！");
						toQuery();
					}else{
						alert("初始化失败！");
						toQuery();
					}
				}
			});		
		}				
	});
	//冻结
	$("#freezeButton").click(function() {
		var rowId = jQuery("#memberList").jqGrid('getGridParam', 'selarrrow');
		if (rowId == '' || rowId == null || rowId.length > 1) {
			alert('请选择单条信息进行解冻!');
		} else { // 选中
			$.ajax({
				url : "/memberAdmin/freeze?id="+rowId,
				type : 'POST',
				success : function(data) {
					var $temp = eval('(' + data + ')');
					if ($temp && $temp.result==true) {
						alert("冻结成功！");
						toQuery();
					}else{
						alert("冻结失败！");
						toQuery();
					}
				}
			});		
		}				
	});
	//解冻
	$("#unfreezeButton").click(function() {
		var rowId = jQuery("#memberList").jqGrid('getGridParam', 'selarrrow');
		if (rowId == '' || rowId == null || rowId.length > 1) {
			alert('请选择单条信息进行冻结!');
		} else { // 选中
			$.ajax({
				url : "/memberAdmin/unfreeze?id="+rowId,
				type : 'POST',
				success : function(data) {
					var $temp = eval('(' + data + ')');
					if ($temp && $temp.result==true) {
						alert("解冻成功！");
						toQuery();
					}else{
						alert("解冻失败！");
						toQuery();
					}
				}
			});		
		}				
	});
	$("#member_add").dialog({
		modal : true,
		width : 400,
		height : 240,
		minHeight : 240,
		minWidth : 400,
		maxHeight : 240,
		maxWidth : 400
	}).dialog("close");
	
	$("#member_view").dialog({
		modal : true,
		width : 800,
		height : 600,
		minHeight : 600,
		minWidth : 800,
		maxHeight : 600,
		maxWidth : 800
	}).dialog("close");
});