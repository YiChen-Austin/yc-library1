var funcs = {
	image : {
		//新增
		popAddWindow : function() {
			$("#image_win").window({title: "新增"});
			$('#image_form input[name="indexImageId"]').val("");
			$("#image_form input[name='cityId']").val($("#citySel").combobox("getValue"));
			$('#image_form input[name="oldImageFile"]').val("");
			$("#cityName").html($("#citySel").combobox("getText"));
			$("#image_add_ok").unbind("click").bind("click", function() {
				funcs.image.submitBtn();
			});
			$("#image_win").window("open");
		},
		//修改
		popEditWindow : function(data) {
			$("#image_win").window({title: "修改"});
			$('#image_form input[name="indexImageId"]').val(data.indexImageId);
			$('#image_form input[name="cityId"]').val($("#citySel").combobox("getValue"));
			$('#image_form input[name="oldImageFile"]').val(data.image);
			$("#cityName").html($("#citySel").combobox("getText"));
			$("#imageOrder").combobox("setValue", data.imageOrder);
			$("#enabled").combobox("setValue", data.enabled);
			$("#image_add_ok").unbind("click").bind("click", function() {
				funcs.image.submitBtn();
			});
			$("#image_win").window("open");
		},
		submitBtn : function() {
			//修改
			if($('#image_form input[name="indexImageId"]').val()) {
				//图片改变，要上传文件
				if($("#imageFile").val()) {
					$.messager.progress();
					$.ajaxFileUpload({
						type: "post",
						fileElementId: "imageFile",
						url: $("#path",parent.document).val() + "/admin/mall/editIndexImageChange",
						dataType: "json",
						data: {
							indexImageId : $('#image_form input[name="indexImageId"]').val(),
							imageOrder : $("#imageOrder").combobox("getValue"),
							enabled : $("#enabled").combobox("getValue")
						},
						success: function(a, b) {
							if (a.code == 1) {
								$.messager.progress("close");
								$("#image_win").window("close");
								$("#image_table").datagrid("clearChecked");
								$("#image_table").datagrid("reload");
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
				else if($('#image_form input[name="oldImageFile"]').val()){
					$.messager.progress();
					$.ajax({
						url: $("#path",parent.document).val() + "/admin/mall/editIndexImage",
						type: "post",
						dataType: "json",
						data: {
							indexImageId : $('#image_form input[name="indexImageId"]').val(),
							imageOrder : $("#imageOrder").combobox("getValue"),
							enabled : $("#enabled").combobox("getValue")
						},
						success: function(a) {
							if (a.code == 1) {
								$.messager.progress("close");
								$("#image_win").window("close");
								$("#image_table").datagrid("clearChecked");
								$("#image_table").datagrid("reload");
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
				if($("#imageFile").val()) {
					$.messager.progress();
					$.ajaxFileUpload({
						type: "post",
						fileElementId: "imageFile",
						url: $("#path",parent.document).val() + "/admin/mall/saveIndexImage",
						dataType: "json",
						data: {
							cityId : $("#image_form input[name='cityId']").val(),
							cityName : $("#cityName").html(),
							imageOrder : $("#imageOrder").combobox("getValue"),
							enabled : $("#enabled").combobox("getValue")
						},
						success : function(a, b) {
							if (a.code == 1) {
								$.messager.progress("close");
								$("#image_win").window("close");
								$("#image_table").datagrid("clearChecked");
								$("#image_table").datagrid("reload");
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
		delIndexImage : function() {
			var d = $("#image_table").datagrid("getChecked");
			if (d.length > 0) {
				$.messager.confirm("确认信息", "确认删除选中的" + d.length + "项",
						function(r) {
							if (r) {
								var ids = "";
								for (var i = 0; i < d.length; i++) {
									if (i < d.length - 1) {
										ids += d[i]["indexImageId"] + ",";
									} else {
										ids += d[i]["indexImageId"];
									}
								}
								$.post($("#path",parent.document).val() + "/admin/mall/deleteIndexImage", {
									"ids" : ids
								}, function(data) {
									if (data.code == 1) {
										$.messager.alert("提示信息", "删除成功");
										$("#image_table").datagrid("clearChecked");
										$("#image_table").datagrid("reload");
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
					if($("#image_table").hasClass("datagrid-f")) {
						$("#image_table").datagrid({
							queryParams: {cityId : $("#citySel").combobox("getValue")}
						});
					} 
					//datagrid第一次渲染
					else {
						// 轮播图表格
						$("#image_table").datagrid({
							title : "首页轮播图列表",
							url: $("#path",parent.document).val() + "/admin/mall/findIndexImages",
							toolbar : "#tb",
							columns : [ [ {
								field : "indexImageId",
								checkbox : true
							}, {
								field : "cityId",
								hidden : true
							}, {
								field : "cityName",
								title : "城市"
							}, {
								field : "image",
								title : "图片"
							}, {
								field : "imageOrder",
								title : "轮播顺序"
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
							} ] ],
							idField : "indexImageId",
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
	$("#saveIndexCirculateButton").bind("click",function(){
		funcs.image.popAddWindow();
	});
	$("#updateIndexCirculateButton").bind("click",function(){
		//未选择
		if($("#image_table").datagrid("getChecked").length == 0) {
			$.messager.alert("提示", "请选择需要修改的记录", "warning");
		}
		//选择多条
		else if($("#image_table").datagrid("getChecked").length > 1) {
			$.messager.alert("提示", "最多只能选择一条记录", "warning");
		} else {
			funcs.image.popEditWindow($("#image_table").datagrid("getChecked")[0]);
		}
	});
	$("#deleteIndexCirculateButton").bind("click",function(){
		funcs.image.delIndexImage();
	});
	$("#image_add_cancel").bind("click", function() {
		$("#image_win").window("close");
	});
	
});
