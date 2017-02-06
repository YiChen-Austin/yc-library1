//列表显示
$(function() {
	// 查询
	$("#searchLogInfoButton").click(function() {
		var beginTime = document.getElementById("beginTime");
		var endTime = document.getElementById("endTime");
		var vbeginTime = beginTime.value;
		var vendTime = endTime.value;
		if (vbeginTime == '' || vendTime == '') {
			alert("请选择时间");
			return false;
		}
		load_data();
	});
	load_data();
});
function load_data() {
	var beginTime = document.getElementById("beginTime");
	var endTime = document.getElementById("endTime");
	var vbeginTime = beginTime.value;
	var vendTime = endTime.value;
	$
			.ajax( {
				type : "POST",
				datatype : "json",
				async : false,
				url : "logInfoSumReportCount",
				data : {
					"logInfoBean.beginTime" : vbeginTime,
					"logInfoBean.endTime" : vendTime
				},
				beforeSend : function() {
					$("#divProcessing").css("display", "block");
				},
				success : function(data) {
					$('#title').nextAll('tr').remove();
					var $_html = '';
					$
							.each(
									data.logInfoSumBeans,
									function(i, item) {
										var belongsCompany = item.belongsCompany;
										if (belongsCompany != '分公司') {
											$_html += '<tr>';
											$_html += '<td class="td_border" width="150px;" align="center" rowspan="'
													+ item.recordCount
													+ '">'
													+ item.belongsCompany
													+ '</td>';
											$_html += '<td class="td_border" align="center"  width="180px;"><font color="red">' + item.operatorUser + '</font></td>';
											$_html += '<td class="td_border" align="center"><font color="red">' + item.addCount + '</font></td>';
											$_html += '<td class="td_border" align="center"><font color="red">' + item.modifyCount + '</font></td>';
											$_html += '<td class="td_border" align="center"><font color="red">' + item.deleteCount + '</font></td>';
											$_html += '<td class="td_border" align="center"><font color="red">' + item.viewCount + '</font></td>';
											$_html += '<td class="td_border" align="center"><font color="red">' + item.loginCount + '</font></td>';
											$_html += '<td class="td_border" align="center"><font color="red">' + item.logoutCount + '</font></td>';
											$_html += '</tr>';
										} else {
											$_html += '<tr>';
											$_html += '<td class="td_border" width="150px;" align="center" rowspan="'
													+ (item.recordCount)
													+ '">'
													+ item.belongsCompany
													+ '</td>';
											$_html += '</tr>';
										}
										$
												.ajax( {
													type : "POST",
													datatype : "json",
													async : false,
													url : "logInfoReportCount",
													data : {
														"logInfoBean.beginTime" : vbeginTime,
														"logInfoBean.endTime" : vendTime,
														"logInfoBean.belongsCompany" : belongsCompany
													},
													success : function(data) {
														$
																.each(
																		data.logInfoBeans,
																		function(
																				i,
																				obj) {
																			$_html += '<tr>';
																			$_html += '<td class="td_border" align="center">' + obj.operatorUser + '</td>';
																			$_html += '<td class="td_border" align="center">' + obj.addCount + '</td>';
																			$_html += '<td class="td_border" align="center">' + obj.modifyCount + '</td>';
																			$_html += '<td class="td_border" align="center">' + obj.deleteCount + '</td>';
																			$_html += '<td class="td_border" align="center">' + obj.viewCount + '</td>';
																			$_html += '<td class="td_border" align="center">' + obj.loginCount + '</td>';
																			$_html += '<td class="td_border" align="center">' + obj.logoutCount + '</td>';
																			$_html += '</tr>';
																		});
													}
												});

									});
					$('#title').after($_html);
					$("#divProcessing").css("display", "none");
				}
			});
}