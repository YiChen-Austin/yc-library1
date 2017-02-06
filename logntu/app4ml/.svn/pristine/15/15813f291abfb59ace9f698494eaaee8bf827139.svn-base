/**
 * 自定义配置节假日维护jsp页面的js
 */
$(function() {
	$("#date2").hide();
	$("#date3").hide();
	// jqGrid参数配置
	jQuery("#sysdesignateddate").jqGrid(
			{
				url : $("#path", parent.document).val() + "/admin/"
						+ "sysDesignatedDateFind",
				datatype : "json",
				colNames : [ 'id', '日期', '名称', '状态', '备注' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					hidden : true
				}, {
					name : 'date',
					index : 'date',
					width : 60
				}, {
					name : 'name',
					index : 'name',
					width : 55
				}, {
					name : 'identifer',
					index : 'identifer',
					width : 90
				}, {
					name : 'remark',
					index : 'remark',
					width : 150
				} ],
				altRows : false,
				rowNum : 15,
				rowList : [ 10, 15, 20, 30 ],
				pager : '#pager',
				sortname : 'id',
				viewrecords : true,
				cellEdit : false,
				sortorder : "desc",
				height : 330,
				width : 600,
				jsonReader : {
					root : 'list',
					repeatitems : false
				},
				caption : '特定日期处理'
			});

	/**
	 * 条件查询
	 */
	$("#jumpSelect").click(
			function() {

				jQuery("#sysdesignateddate").jqGrid(
						'setGridParam',
						{
							url : $("#path", parent.document).val() + "/admin/"
									+ "sysDesignatedDateFind?time4="
									+ $("#time4").attr("value") + "&time5="
									+ $("#time5").attr("value") + "&identifer="
									+ $("#identifer").attr("value"),
							page : 1
						}).trigger("reloadGrid");
			});

	/**
	 * 编辑日期
	 */
	$("#update").click(
			function() {
				var id = jQuery("#sysdesignateddate").jqGrid('getGridParam',
						'selrow');
				if (id == '' || id == null)
					jAlert("请选择需要编辑的日期");
				else
					window.location.href = $("#path", parent.document).val()
							+ "/admin/sys_designated_date_edit?id=" + id;
			});

	/**
	 * 统计日期
	 */
	$("#statistics").click(
			function() {
				if ($("#time1").attr("value") == "") {
					jAlert("开始日期不能为空");
					return;
				}
				if ($("#time2").attr("value") == "") {
					jAlert("结束日期不能为空");
					return;
				}

				$.ajax({
					async : false,
					type : "post",
					url : $("#path", parent.document).val() + "/admin/"
							+ "sysDesignatedDateSum",
					dataType : "json",
					data : encodeURI($("#From3").ajaxForm().formSerialize()),
					success : function(json) {
						jAlert("统计结果：" + json.time2);
					}
				});
			});
	$("#hwsave").click(
			function() {
				var time6 = $("#time6").attr("value");
				var time7 = $("#time7").attr("value");
				if (time6 == "" || time6 == null) {
					jAlert("开始日期不能为空!");
					return;
				}
				if (time7 == "" || time7 == null) {
					jAlert("结束日期不能为空!");
					return;
				}
				if (time6 >= time7) {
					jAlert("开始日期不能大于结束日期!");
					return;
				}
				var radio;
				if ($("#radio_ph_id").attr("checked") == true) {
					radio = "ph";
				} else if ($("#radio_pw_id").attr("checked") == true) {
					radio = "pw";
				} else if ($("#radio_w_id").attr("checked") == true) {
					radio = "w";
				} else if ($("#radio_h_id").attr("checked") == true) {
					radio = "h";
				}

				$.ajax({
					async : false,
					type : "post",
					dataType : "json",
					url : $("#path", parent.document).val() + "/admin/"
							+ "adjustSysDesignatedDate",
					data : "time1=" + time6 + "&time2=" + time7 + "&identifer="
							+ radio,
					success : function(json) {
						jAlert("编辑成功！");
					}
				});

			});

	/**
	 * 计算日期
	 */
	$("#computation").click(
			function() {
				if ($("#time3").attr("value") == "") {
					jAlert("日期不能为空");
					return;
				}
				if ($("#days").attr("value") == "") {
					jAlert("请输入正确的天数");
					return;
				}

				if (isNaN(parseInt(parseInt($("#days").attr("value"))))) {
					jAlert("请输入整天数");
					return;
				}

				if (parseInt($("#days").attr("value")) < 1) {
					jAlert("请输入正确的天数");
					return;
				}
				$.ajax({
					async : false,
					type : "post",
					dataType : "json",
					url : $("#path", parent.document).val() + "/admin/"
							+ "sysDesignatedDateDay",
					data : encodeURI($("#From4").ajaxForm().formSerialize()),
					success : function(json) {
						jAlert("计算日期为：" + json.days);
					}
				});
			});

	/**
	 * 统计日期显示
	 */
	jQuery("#da1").click(function() {
		// $("#main").hide();
		$("#date2").hide();
		$("#date3").hide();
		$("#date1").show();
	});

	/**
	 * 计算日期显示
	 */
	jQuery("#da2").click(function() {
		// $("#main").hide();
		$("#date1").hide();
		$("#date3").hide();
		$("#date2").show();
	});
	/**
	 * 节假日调整显示
	 */
	jQuery("#da3").click(function() {
		// $("#main").hide();
		$("#date1").hide();
		$("#date2").hide();
		$("#date3").show();
	});
	/**
	 * 统计日期返回
	 */
	$("#return1").click(function() {
		$("#date1").hide();
		$("#date2").hide();
		$("#main").show();
	});

	/**
	 * 计算日期返回
	 */
	$("#return2").click(function() {
		$("#date1").hide();
		$("#date2").hide();
		$("#main").show();
	});
});
