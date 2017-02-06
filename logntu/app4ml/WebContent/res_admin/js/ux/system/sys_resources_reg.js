$("html").ready(function() {

	// 操作表示位置，参数分别为 add updata del
		var operatingFlag = "add";

		/**
		 * 获取菜单信息
		 */
		var node;
		$(function() {
			// ajax调用不使用缓存
			$.ajaxSetup( {
				cache : false
			});
			$("#menu_tree").tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
						types : {
							"default" : {
								draggable : false
							}
						},
						data : {
							type : "json",
							async : false,
							opts : {
								async : true,
								method : "POST",
								url : $("#path",parent.document).val()+"/admin/"+"findAllSysMenus"// 实际提交的Action
							}
						},
						lang : {
							loading : "菜单加载中……" // 在用户等待数据渲染的时候给出的提示内容，默认为loading
					},
					ui : {
						theme_name : 'classic' // 更换皮肤，设置样式
					},
					callback : {
						onload : function(t) {
							t.settings.data.opts.static = false;
						},
						onselect : function(NODE, TREE_OBJ) {

							// 初始化部分信息
						showDIV("");
						clearAlladd();
						$("#components_hidden_id").attr("value", "");
						$("#actionUrl_hidden_id").attr("value", "");
						$("#after_actionUrl_hidden_id").attr("value", "");

						// 清空组建列表
						jQuery("#components_table_id").jqGrid('clearGridData');
						// 清空Action列表
						jQuery("#actionUrl_table_id").jqGrid('clearGridData');

						$("#jsp_hidden_id").attr("value", $(NODE).attr("id"));
						showJSP($("#jsp_hidden_id").attr("value"));

						// jAlert(TREE_OBJ.get_text(NODE));
					}
					},
					// 插件使用右键菜单支持自定义右键菜单
						plugins : {}
					});
		});

		/**
		 * 获取菜单对应的JSP页面页面
		 */
		jQuery("#jsp_table_id").jqGrid( {
			async : false,
			// 获取数据的地址
			// url : "getTargetJSPAction",
			mtype : "get",
			// 从服务器端返回的数据类型,配置为json
			datatype : "json",
			// 列显示名称，是一个数组对象
			colNames : [ 'ID', 'JSP页面地址', '描述', '备注' ],
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
				name : 'name',
				index : 'name',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			}, {
				name : 'description',
				index : 'description',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			}, {
				name : 'remark',
				index : 'remark',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			} ],
			altRows : true,
			// 定义是否可以多选
			multiselect : false,
			onSelectRow : function(id) {

				// 初始化部分信息
			showDIV("");
			clearAlladd();
			$("#actionUrl_hidden_id").attr("value", "");
			$("#after_actionUrl_hidden_id").attr("value", "");
			// 清空Action列表
			jQuery("#actionUrl_table_id").jqGrid('clearGridData');

			showComponents(id);
			$("#components_hidden_id").attr("value", id);
		},

		height : 160,
		// 如果设置则按此设置为主，如果没有设置则按colModel中定义的宽度计算
			width : 490,
			// 定义表格名称
			caption : "JSP页面设置",
			jsonReader : {
				// 这个元素指明表格所需要的数据从哪里开始
				root : "sysResourcesRegBeans",
				repeatitems : false
			}
		});

		/**
		 * 获取JSP页面对应的组件
		 */
		jQuery("#components_table_id").jqGrid( {
			async : false,
			// 获取数据的地址
			mtype : "get",
			// 从服务器端返回的数据类型,配置为json
			datatype : "json",
			// 列显示名称，是一个数组对象
			colNames : [ 'ID', '组件名称', '组件ID', '描述', '备注' ],
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
				name : 'name',
				index : 'name',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			}, {
				name : 'name_id',
				index : 'name_id',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			}, {
				name : 'description',
				index : 'description',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			}, {
				name : 'remark',
				index : 'remark',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			} ],
			altRows : true,
			// 定义是否可以多选
			multiselect : false,
			onSelectRow : function(id) {

				// 放置组件ID到隐藏的位置
			$("#actionUrl_hidden_id").attr("value", id);
			$("#after_actionUrl_hidden_id").attr("value", "");
			showActionUrl(id);
			showDIV("apDiv6");
		},
		height : 70,
		// 如果设置则按此设置为主，如果没有设置则按colModel中定义的宽度计算
			width : 895,
			// 定义表格名称
			caption : "组件页面设置",
			jsonReader : {
				// 这个元素指明表格所需要的数据从哪里开始
				root : "sysResourcesRegBeans",
				repeatitems : false
			}
		});

		/**
		 * 获取页面组件上对应的Action
		 */
		jQuery("#actionUrl_table_id").jqGrid( {
			async : false,
			// 获取数据的地址
			// url : "getTargetJSPAction",
			mtype : "get",
			// 从服务器端返回的数据类型,配置为json
			datatype : "json",
			// 列显示名称，是一个数组对象
			colNames : [ 'ID', 'Action地址', '描述', '备注' ],
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
				name : 'name',
				index : 'name',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			}, {
				name : 'description',
				index : 'description',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			}, {
				name : 'remark',
				index : 'remark',
				width : 5,
				editable : true,
				edittype : "text",
				sortable : false
			} ],
			altRows : true,
			// 定义是否可以多选
			multiselect : false,
			onSelectRow : function(id) {

				$("#after_actionUrl_hidden_id").attr("value", id);

			},

			height : 100,
			// 如果设置则按此设置为主，如果没有设置则按colModel中定义的宽度计算
			width : 895,
			// 定义表格名称
			caption : "组件Action地址",
			jsonReader : {
				// 这个元素指明表格所需要的数据从哪里开始
				root : "sysResourcesRegBeans",
				repeatitems : false
			}
		});

		/**
		 * 修改JSP地址
		 */
		$("#jsp_updata_id").click(function() {


				var targeID = $("#components_hidden_id").attr("value");
				if (targeID == "") {
					jAlert("请先选中将要修改的JSP页面");
					return;
				}
				// 操作标识
				operatingFlag = "updata";
				
				// 传值给框框
				var tempName = jQuery("#jsp_table_id").jqGrid('getCell',
						targeID, "name");
				$("#jspPage_url_id").attr("value", tempName);
				tempName = jQuery("#jsp_table_id").jqGrid('getCell', targeID,
						"description");
				$("#jspPage_description_id").attr("value", tempName);
				tempName = jQuery("#jsp_table_id").jqGrid('getCell', targeID,
						"remark");
				$("#jspPage_remark_id").attr("value", tempName);
				showDIV("apDiv4");

			});

		/**
		 * 修改组件按钮
		 */
		$("#components_updata_id").click(function() {


				var targeID = $("#actionUrl_hidden_id").attr("value");
				if (targeID == "") {
					jAlert("请先选中将要修改的组件");
					return;
				}
				
				// 操作标识
				operatingFlag = "updata";
				
				
				// 传值给框框
				var tempName = jQuery("#components_table_id").jqGrid('getCell',
						targeID, "name");
				$("#component_name_id").attr("value", tempName);
				
				var tempName = jQuery("#components_table_id").jqGrid('getCell',
						targeID, "name_id");
				$("#component_name_id_id").attr("value", tempName);
				
				tempName = jQuery("#components_table_id").jqGrid('getCell', targeID,
						"description");
				$("#component_description_id").attr("value", tempName);
				
				
				tempName = jQuery("#components_table_id").jqGrid('getCell', targeID,
						"remark");
				$("#component_remark_id").attr("value", tempName);
				showDIV("apDiv5");

				
				

			});

		
		
		/**
		 * 新增指定的菜单对应的jsp页面
		 */
		$("#save_jsppage_id").click(
				function() {
					if ($("#jspPage_url_id").attr("value") == "") {
						jAlert("JSP地址不能为空");
						return;
					}
					var temp = $("#jspPage_url_id").attr("value");

					if (temp.substring(0, 1) != "/") {
						jAlert("JSP地址必须以\"/\"开头");
						return;
					}
					if (temp.substring(temp.length - 4, temp.length)
							.toLowerCase() != ".jsp") {
						jAlert("必须为JSP页面的地址");
						return;
					}

					if(operatingFlag=="add"){
						var targeID = $("#jsp_hidden_id").attr("value");
						if(targeID==""){
							jAlert("请先选中菜单");
							return ;
						}
						var sendData = "targeID=" + targeID
								+ "&name="
								+ $("#jspPage_url_id").attr("value") + "&"
								+ "&description="
								+ $("#jspPage_description_id").attr("value") + "&"
								+ "&remark="
								+ $("#jspPage_remark_id").attr("value");
						$.ajax( {
							async : false,
							url : $("#path",parent.document).val()+"/admin/"+"addTargetJSPAction",
							type : 'POST',
							dataType : 'json',
							data : sendData,
							timeout : 1000,
							success : function(data) {
								jAlert(data.flag);
								showJSP($("#jsp_hidden_id").attr("value"));
							}
						});
						
					}
					
				//修改指定的JSP地址
					if (operatingFlag=="updata"){
						var targeID = $("#components_hidden_id").attr("value");
						if(targeID==""){
							jAlert("请先选中组件在点击修改按钮");
						}
						var sendData = "targeID=" + targeID
								+ "&name="
								+ $("#jspPage_url_id").attr("value") + "&"
								+ "&description="
								+ $("#jspPage_description_id").attr("value") + "&"
								+ "&remark="
								+ $("#jspPage_remark_id").attr("value");
						$.ajax( {
							async : false,
							url : $("#path",parent.document).val()+"/admin/"+"updataTargetJSPAction",
							type : 'POST',
							dataType : 'json',
							data : sendData,
							timeout : 1000,
							success : function(data) {
								jAlert(data.flag);
								showJSP($("#jsp_hidden_id").attr("value"));
							}
						});
						
						
						
						
						
						
					}
					
					
					
					

				});

		/**
		 * 新增指定的JSP页面对应的组件
		 */
		$("#save_componente_id").click(
				function() {
					if ($("#component_name_id").attr("value") == "") {
						jAlert("组件名不能为空");
						return;
					}

					if ($("#component_name_id_id").attr("value") == "") {
						jAlert("组件ID不能为空");
						return;
					}

					if(operatingFlag=="add"){
					var targeID = $("#components_hidden_id").attr("value");
					if(targeID==""){
						jAlert("请先选中JSP地址");
					}
					var sendData = "targeID=" + targeID
							+ "&name="
							+ $("#component_name_id").attr("value") + "&"
							+ "&description="
							+ $("#component_description_id").attr("value")
							+ "&" + "&remark="
							+ $("#component_remark_id").attr("value")
							+ "&name_id="
							+ $("#component_name_id_id").attr("value");
					$.ajax( {
						async : false,
						url : $("#path",parent.document).val()+"/admin/"+"addTargetcomponentAction",
						type : 'POST',
						dataType : 'json',
						data : sendData,
						timeout : 1000,
						success : function(data) {
							jAlert(data.flag);

							showComponents($("#components_hidden_id").attr(
									"value"));

						}
					});
					}
					
					
					//修改
					if(operatingFlag=="updata"){
						var targeID = $("#actionUrl_hidden_id").attr("value");
						if(targeID==""){
							jAlert("请先选中组件在点击修改按钮");
						}
						var sendData = "targeID=" + targeID
								+ "&name="
								+ $("#component_name_id").attr("value") + "&"
								+ "&description="
								+ $("#component_description_id").attr("value")
								+ "&" + "&remark="
								+ $("#component_remark_id").attr("value")
								+ "&name_id="
								+ $("#component_name_id_id").attr("value");
						$.ajax( {
							async : false,
							url : $("#path",parent.document).val()+"/admin/"+"updataTargetcomponentAction",
							type : 'POST',
							dataType : 'json',
							data : sendData,
							timeout : 1000,
							success : function(data) {
								jAlert(data.flag);

								showComponents($("#components_hidden_id").attr(
										"value"));

							}
						});
					}
					
					
				});

		/**
		 * 新增指定的组件添加Action地址
		 */
		$("#save_actionUrl_id").click(function() {
			if ($("#actionUrl_url_id").attr("value") == "") {
				jAlert("Action的地址不能为空");
				return;
			}

			// 操作标识
				operatingFlag = "add";
				var targeID = $("#actionUrl_hidden_id").attr("value");
				var sendData = "targeID=" + targeID
						+ "&name="
						+ $("#actionUrl_url_id").attr("value") + "&"
						+ "&description="
						+ $("#actionUrl_description_id").attr("value") + "&"
						+ "&remark="
						+ $("#actionUrl_remark_id").attr("value");

				$.ajax( {
					async : false,
					url : $("#path",parent.document).val()+"/admin/"+"addTargetActionURLAction",
					type : 'POST',
					dataType : 'json',
					data : sendData,
					timeout : 1000,
					success : function(data) {

						jAlert(data.flag);

						showActionUrl($("#actionUrl_hidden_id").attr("value"));

					}
				});
			});

		$("#jsp_add_id").click(function() {

			if ($("#jsp_hidden_id").attr("value") == "") {
				jAlert("请首先选中菜单");
				return;
			}

			// 操作标识
				operatingFlag = "add";
				showDIV("apDiv4");
				clearAlladd();
			});

		$("#components_add_id").click(function() {

			if ($("#components_hidden_id").attr("value") == "") {
				jAlert("请首先选中JSP页面");
				return;
			}
			// 操作标识
				operatingFlag = "add";
				showDIV("apDiv5");
				clearAlladd();
			});

		/**
		 * 删除Action地址按钮
		 */
		$("#del_actionUrl_id").click(function() {

			var targeID = $("#after_actionUrl_hidden_id").attr("value");

			if (targeID == "") {
				jAlert("请先选中将要删除的Action地址");
				return;
			}
			if (confirm("是否要删除?") == false) {
				return;
			}

			// 操作标识
				operatingFlag = "del";
				$.ajax( {
					async : false,
					url : $("#path",parent.document).val()+"/admin/"+"delActionUrlAction",
					type : 'POST',
					dataType : 'json',
					data : "targeID=" + targeID,
					timeout : 1000,
					success : function(data) {
						jAlert(data.flag);
						showActionUrl($("#actionUrl_hidden_id").attr("value"));
						$("#after_actionUrl_hidden_id").attr("value", "");

					}
				});

			});

		/**
		 * 删除指定的组件
		 */
		$("#components_del_id").click(function() {
			var targeID = $("#actionUrl_hidden_id").attr("value");
			if (targeID == "") {
				jAlert("请先选中将要删除的组件");
				return;
			}
			if (confirm("是否要删除?") == false) {
				return;
			}

			// 操作标识
				operatingFlag = "del";

				$
						.ajax( {
							async : false,
							url : $("#path",parent.document).val()+"/admin/"+"delComponentAction",
							type : 'POST',
							dataType : 'json',
							data : "targeID=" + targeID,
							timeout : 1000,
							success : function(data) {
								jAlert(data.flag);
								showComponents($("#components_hidden_id").attr(
										"value"));
								showDIV("");
								$("#actionUrl_hidden_id").attr("value", "");
								// 清空Action列表
								jQuery("#actionUrl_table_id").jqGrid(
										'clearGridData');

							}
						});
			});

		/**
		 * 删除指定的JSP页面
		 */
		$("#jsp_del_id").click(function() {
			var targeID = $("#components_hidden_id").attr("value");
			if (targeID == "") {
				jAlert("请先选中将要删除的JSP页面");
				return;
			}
			if (confirm("是否要删除?") == false) {
				return;
			}

			// 操作标识
				operatingFlag = "del";

				$.ajax( {
					async : false,
					url : $("#path",parent.document).val()+"/admin/"+"delJspUrlAction",
					type : 'POST',
					dataType : 'json',
					data : "targeID=" + targeID,
					timeout : 1000,
					success : function(data) {
						jAlert(data.flag);
						showJSP($("#jsp_hidden_id").attr("value"));
						showDIV("");
						$("#components_hidden_id").attr("value", "");
						// 清空组建列表
					jQuery("#components_table_id").jqGrid('clearGridData');
					// 清空Action列表
					jQuery("#actionUrl_table_id").jqGrid('clearGridData');
				}
				});
			});

		/**
		 * ------------------------------常用方法调用
		 */

		/**
		 * 显示隐藏DIV
		 */
		function showDIV(id) {
			if (id == "") {
				$("#apDiv4").hide();
				$("#apDiv5").hide();
				$("#apDiv6").hide();
				return;
			}
			$("#apDiv4").hide();
			$("#apDiv5").hide();
			$("#apDiv6").hide();
			$("#" + id).show();

		}
		/**
		 * 清空新增信息
		 */
		function clearAlladd() {
			var inputs = $("*[type=text]");
			for ( var i = 0; i < inputs.size(); i++) {
				$(inputs[i]).attr("value", "");
			}

		}

		// 刷新JSP页面
		function showJSP(id) {

			jQuery("#jsp_table_id").jqGrid('setGridParam', {
				url : $("#path",parent.document).val()+"/admin/"+"findTargetJSPAction?" + encodeURI("targeID=" + id),
				page : 1
			}).trigger("reloadGrid");
		}

		// 刷新组件页面
		function showComponents(id) {

			jQuery("#components_table_id").jqGrid('setGridParam', {
				async : false,
				url : $("#path",parent.document).val()+"/admin/"+"findComponentsAction?" + encodeURI("targeID=" + id)
			}).trigger("reloadGrid");

		}

		// 刷新Action地址
		function showActionUrl(id) {

			jQuery("#actionUrl_table_id").jqGrid('setGridParam', {
				async : false,
				url : $("#path",parent.document).val()+"/admin/"+"findAcrionUrlsAction?" + encodeURI("targeID=" + id)
			}).trigger("reloadGrid");

		}

	});
