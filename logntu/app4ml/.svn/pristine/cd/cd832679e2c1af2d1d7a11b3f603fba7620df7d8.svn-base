var funcs = {
	store : {
		//新增
		popAddWindow : function() {
			$("#store_win").window({title: "新增"});
			$("#indexStoreId").val("");
			$("#oldImageFile").val("");
			$("#cityName").html($("#citySel").combobox("getText"));
			$("#cityId").val($("#citySel").combobox("getValue"));
			$("#storeId").numberbox("enable");
			$("#storeId").numberbox("setValue", "");
			$("#search_store").show();
			$("#storeName").textbox("setValue", "");
			$("#imageFile").val("");
			$("#address").textbox("setValue", "");
			$("#tel").textbox("setValue", "");
			$("#store_add_ok").unbind("click").bind("click", function() {
				funcs.store.submitBtn();
			});
			$("#store_win").window("open");
		},
		//修改
		popEditWindow : function(data) {
			$("#store_win").window({title: "修改"});
			$("#indexStoreId").val(data.indexStoreId);
			$("#cityId").val($("#citySel").combobox("getValue"));
			$("#oldImageFile").val(data.storeImage);
			$("#cityName").html($("#citySel").combobox("getText"));
			$("#storeId").numberbox("setValue", data.storeId);
			$("#storeId").numberbox("disable");
			$("#search_store").hide();
			$("#storeId").numberbox("setValue", data.storeId);
			$("#storeName").textbox("setValue", data.storeName);
			$("#cateId").combobox("setValue", data.mcateId);
			$("#storeOrder").combobox("setValue", data.storeOrder);
			$("#enabled").combobox("setValue", data.enabled);
			$("#address").textbox("setValue", data.address);
			$("#tel").textbox("setValue", data.tel);
			$("#store_add_ok").unbind("click").bind("click", function() {
				funcs.store.submitBtn();
			});
			$("#store_win").window("open");
		},
		submitBtn : function() {
			//修改
			if($('#indexStoreId').val()) {
				//图片改变，要上传文件
				if($("#imageFile").val()) {
					$.messager.progress();
					$.ajaxFileUpload({
						type: "post",
						fileElementId: "imageFile",
						url: $("#path",parent.document).val() + "/admin/mall/editIndexStoreChange",
						dataType: "json",
						data: {
							indexStoreId : $("#indexStoreId").val(),
							storeName : $("#storeName").textbox("getValue"),
							mcateId : $("#cateId").combobox("getValue"),
							cateNames : $("#cateId").combobox("getText"),
							storeOrder : $("#storeOrder").combobox("getValue"),
							enabled : $("#enabled").combobox("getValue"),
							address : $("#address").textbox("getValue"),
							tel : $("#tel").textbox("getValue")
						},
						success: function(a, b) {
							if (a.code == 1) {
								$.messager.progress("close");
								$("#store_win").window("close");
								$("#store_table").datagrid("clearChecked");
								$("#store_table").datagrid("reload");
							} else {
								$.messager.progress("close");
								$.messager.alert("错误", "上传失败");
							}
						},
						error: function(a, b, c) {
							$.messager.progress("close");
							$.messager.alert("错误", "服务器无响应");
						}
					});
				}
				//图片不改变，不上传文件
				else if($('#oldImageFile').val()){
					$.messager.progress();
					$.ajax({
						url: $("#path",parent.document).val() + "/admin/mall/editIndexStore",
						type: "post",
						dataType: "json",
						data: {
							indexStoreId : $("#indexStoreId").val(),
							storeName : $("#storeName").textbox("getValue"),
							mcateId : $("#cateId").combobox("getValue"),
							cateNames : $("#cateId").combobox("getText"),
							storeOrder : $("#storeOrder").combobox("getValue"),
							enabled : $("#enabled").combobox("getValue"),
							address : $("#address").textbox("getValue"),
							tel : $("#tel").textbox("getValue")
						},
						success: function(a) {
							if (a.code == 1) {
								$.messager.progress("close");
								$("#store_win").window("close");
								$("#store_table").datagrid("clearChecked");
								$("#store_table").datagrid("reload");
							} else {
								$.messager.progress("close");
								$.messager.alert("错误", "修改失败");
							}
						},
						error: function() {
							$.messager.progress("close");
							$.messager.alert("错误", "服务器无响应");
						}
					});
				}
			} 
			//新增
			else {
				if($("#storeId").val() && $("#storeName").val() && $("#imageFile").val() && $("#address").val()) {
					$.messager.progress();
					$.ajaxFileUpload({
						type: "post",
						fileElementId: "imageFile",
						url: $("#path",parent.document).val() + "/admin/mall/saveIndexStore",
						dataType: "json",
						data: {
							cityId : $("#cityId").val(),
							cityName : $("#cityName").html(),
							storeId : $("#storeId").numberbox("getValue"),
							storeName : $("#storeName").textbox("getValue"),
							mcateId : $("#cateId").combobox("getValue"),
							cateNames : $("#cateId").combobox("getText"),
							storeOrder : $("#storeOrder").combobox("getValue"),
							enabled : $("#enabled").combobox("getValue"),
							address : $("#address").textbox("getValue"),
							tel : $("#tel").textbox("getValue")
						},
						success : function(a, b) {
							if (a.code == 1) {
								$.messager.progress("close");
								$("#store_win").window("close");
								$("#store_table").datagrid("clearChecked");
								$("#store_table").datagrid("reload");
							} else {
								$.messager.progress("close");
								$.messager.alert("错误", "上传失败");
							}
						},
						error: function(a, b, c) {
							$.messager.progress("close");
							$.messager.alert("错误", "服务器无响应");
						}
					});
				} 
				//未上传图片
				else {
					$.messager.alert("提示", "请选择上传图片", "warning");
				}
			}
		},
		delIndexStore : function() {
			var d = $("#store_table").datagrid("getChecked");
			if (d.length > 0) {
				$.messager.confirm("确认信息", "确认删除选中的" + d.length + "项",
						function(r) {
							if (r) {
								var ids = "";
								for (var i = 0; i < d.length; i++) {
									if (i < d.length - 1) {
										ids += d[i]["indexStoreId"] + ",";
									} else {
										ids += d[i]["indexStoreId"];
									}
								}
								$.post($("#path",parent.document).val() + "/admin/mall/deleteIndexStore", {
									"ids" : ids
								}, function(data) {
									if (data.code == 1) {
										$.messager.alert("提示信息", "删除成功");
										$("#store_table").datagrid("clearChecked");
										$("#store_table").datagrid("reload");
									} else {
										$.messager.alert("提示信息", "服务器无响应");
									}
								}, "json");
							}
						});
			} else {
				$.messager.alert("提示信息", "请至少选择一项");
			}
		}
	}
};
$(function() {
	$("#provinceSel").combobox({
		url :  $("#path",parent.document).val() + "/admin/mall/findProvince",
		width : 120,
		panelHeight : "auto",
		valueField : "regionId",
		textField : "regionName",
		editable : false,
		loadFilter : function(d) {
			if (d.code == 1) {
				return d.rows;
			} else {
				$.messager.alert("提示信息", "服务器无响应");
				return null;
			}
		},
		onLoadSuccess : function(d) {
			$(this).combobox("setValue", d[0]["regionId"]);
			$("#citySel").combobox({
				url :  $("#path",parent.document).val() + "/admin/mall/findCityByProvince",
				width : 120,
				panelHeight : "auto",
				valueField : "regionId",
				textField : "regionName",
				editable : false,
				loadFilter : function(d) {
					if (d.code == 1) {
						return d.rows;
					} else {
						$.messager.alert("提示信息", "服务器无响应");
					}
				},
				onBeforeLoad : function(p) {
					p.provinceId = $("#provinceSel").combobox('getValue');
				},
				onLoadSuccess : function(f) {
					$(this).combobox("setValue", f[0]["regionId"]);
				},
				onChange : function() {
					//datagrid已经渲染过了
					if($("#store_table").hasClass("datagrid-f")) {
						$("#store_table").datagrid({
							queryParams: {cityId : $("#citySel").combobox("getValue")}
						});
					} 
					//datagrid第一次渲染
					else {
						// 轮播图表格
						$("#store_table").datagrid({
							title : "首页轮播图列表",
							url: $("#path",parent.document).val() + "/admin/mall/findIndexStores",
							toolbar : "#tb",
							columns : [ [ 
					           {
								title : "主要信息",
								colspan : 8
							}, {
								title : "其余信息",
								colspan : 4
							}], [{
								field : "indexStoreId",
								checkbox : true
							}, {
								field : "cityName",
								title : "城市"
							}, {
								field : "storeName",
								title : "店铺名"
							}, {
								field : "storeImage",
								title : "图片"
							}, {
								field : "cateNames",
								title : "分类"
							}, {
								field : "cateOrder",
								title : "分类排序"
							}, {
								field : "storeOrder",
								title : "店铺排序"
							}, {
								field : "enabled",
								title : "是否有效",
								formatter : function(v, r, i) {
									if (v == 1) {
										return "是";
									} else {
										return "否";
									}
								}
							},{
								field : "address",
								title : "地址"
							}, {
								field : "tel",
								title : "电话"
							}, {
								field : "thumbUp",
								title : "鲜花"
							}, {
								field : "thumbDown",
								title : "鸡蛋"
							}] ],
							idField : "indexStoreId",
							loadMsg : "加载中，请稍后.....",
							pagination : true,
							rowStyler : function(i, d) {
								if (d.enabled == 1) {
									return 'background-color:#6293BB;color:#fff;';
								}
							},
							loadFilter : function(data) {
								if (data.code == 1) {
									return data;
								} else {
									$.messager.alert("提示", "服务器无响应");
								}
							},
							queryParams : {
								cityId : $("#citySel").combobox("getValue")
							}
						});
					}
				}
			});
		},
		onSelect : function() {
			$("#citySel").combobox("reload");
		}
	});
	//店铺分类下拉框
	$("#cateId").combobox({
		url : $("#path",parent.document).val() + "/admin/mall/findFirstScate",
		valueField : "cateId",
		textField : "cateName",
		width : 120,
		panelHeight : "auto",
		editable : false,
		loadFilter : function(d) {
			if (d.code == 1) {
				return d.rows;
			} else {
				$.messager.alert("提示信息", "服务器无响应");
			}
		},
		onLoadSuccess : function(f) {
			$(this).combobox("setValue", f[0]["cateId"]);
		}
	});
	$("#saveIndexShopButton").bind("click",function(){
		funcs.store.popAddWindow();
	});
	$("#updateIndexShopButton").bind("click",function(){
		//未选择
		if($("#store_table").datagrid("getChecked").length == 0) {
			$.messager.alert("提示", "请选择需要修改的记录", "warning");
		}
		//选择多条
		else if($("#store_table").datagrid("getChecked").length > 1) {
			$.messager.alert("提示", "最多只能选择一条记录", "warning");
		} else {
			funcs.store.popEditWindow($("#store_table").datagrid("getChecked")[0]);
		}
	});
	$("#deleteIndexShopButton").bind("click",function(){
		funcs.store.delIndexStore();
	});
	$("#store_add_cancel").bind("click", function() {
		$("#store_win").window("close");
	});
	$("#search_store").bind("click", function() {
		$.post($("#path",parent.document).val() + "/admin/mall/getStoreById", {storeId : $("#storeId").numberbox("getValue")}, function(data) {
			//填充表单信息
			if(data && data.code == "1") {
				$("#storeName").textbox("setValue", data.row.storeName);
				$("#cateId").combobox("setValue", data.row.cateMId);
				$("#address").textbox("setValue", data.row.address);
				$("#tel").textbox("setValue", data.row.tel);
			} else {
				$.messager.alert("提示", "店铺不存在!", "warning");
			}
		}, "json");
	});
});
