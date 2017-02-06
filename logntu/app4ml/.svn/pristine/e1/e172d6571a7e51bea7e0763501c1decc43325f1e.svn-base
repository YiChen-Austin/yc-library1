/**
 * 自定义节假日维护编辑jsp页面js
 */
$(document).ready(function() {
	//需要高亮数组
	var colorArray = new Array();
	
	/**
	 * 保存修改
	 */
	$("#submit_id").click(function() {
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

						$.ajax( {
									async : false,
									type : "post",
									dataType : "json",
									url : $("#path",parent.document).val()+"/admin/"+"sysDesignatedDateUpdate",
									data : "id="
											+ $("#id_id").attr("value")
											+ "&name="
											+ $("#name_id").attr("value")
											+ "&edate="
											+ $("#date_id").attr("value")
											+ "&identifer="
											+ radio
											+ "&remark="
											+ $("#remark_id").attr("value"),
									success : function(json) {
									alert(json);
										// window.location.href =
										// "sys_designated_date_edit.jsp";
									}
								});
					});
	
	
	
	
	var nowDate = new Date();
	//setTimeColor(nowDate.getFullYear()+"-"+(nowDate.getMonth()+1)+"-"+nowDate.getDate());
//	WdatePicker( {
//		//specialDates:colorArray,//高亮特殊节假日
//		eCont : 'timeDiv',
//		position:{left:100,top:20},
//		skin:'plane',
//		onpicked : function(dp) {
//			findTime(dp.cal.getDateStr());
//		},
//		//dchanged:function(dp){setTimeColor(dp.cal.getDateStr());}, 
//		Mchanged:function(dp){setTimeColor(dp.cal.getDateStr());},
//		ychanged:function(dp){setTimeColor(dp.cal.getDateStr());}
//	});
	
	
	
	/**
	 *通过时间获取许并设置高亮与不可用
	 */
	function setTimeColor (dataText){
		var myDate = new Date();
	    var year = myDate.getFullYear();   //获取完整的年份(4位,1970-????)
	    var month = myDate.getMonth()+1;      //获取当前月份(0-11,0代表1月
	    if(Number(month)<10)
	    	month = "0" + month;
	    var date = myDate.getDate();       //获取当前日(1-31)
		findTime(year + "-" + month + "-" + date);
		$.ajax( {
			async : false,
			url : $("#path",parent.document).val()+"/admin/"+"dateColorAction",
			type : 'POST',
			dataType : 'json',
			data : "timeStr=" + dataText,
			success : function(json) {
			var colorList = json.colorList; 
			for(var i = 0 ;i<colorList.length;i++){
			colorArray[i] = colorList[i];
			}
			//var startDate =  colorArray[0];
			
		if(colorArray.length==0){
			WdatePicker( {
				//specialDates:colorArray,//高亮特殊节假日
				eCont : 'timeDiv',
				position:{left:100,top:20},
				skin:'plane',
				onpicked : function(dp) {
					findTime(dp.cal.getDateStr());
				},
				startDate:dataText,
				//dchanged:function(dp){setTimeColor(dp.cal.getDateStr());}, 
				Mchanged:function(dp){setTimeColor(dp.cal.getDateStr());},
				ychanged:function(dp){setTimeColor(dp.cal.getDateStr());}
			});
		}else{
			
			WdatePicker( {
				specialDates:colorArray,//高亮特殊节假日
				eCont : 'timeDiv',
				position:{left:100,top:20},
				skin:'plane',
				onpicked : function(dp) {
					findTime(dp.cal.getDateStr());
				},
				startDate:dataText,
				//dchanged:function(dp){setTimeColor(dp.cal.getDateStr());}, 
				Mchanged:function(dp){setTimeColor(dp.cal.getDateStr());},
				ychanged:function(dp){setTimeColor(dp.cal.getDateStr());}
			});	
		}
			//alert("success");
		}}
		);
	}
/**
 * 在编辑页面显示内容
 */
function findTime(timeStr) {
	$.ajax( {
		async : false,
		url : $("#path",parent.document).val()+"/admin/"+"sysDesignatedDateQuery",
		type : 'POST',
		dataType : 'json',
		data : "timeStr=" + timeStr,
		timeout : 1000,
		success : function(data) {

			var radio = data.sysdesignteddatebean.identifer;
			// 删除首位空，并转换为小写
		radio = radio.replace(/^\s*/, "").replace(/\s*$/, "").toLowerCase();

		if (radio != "ph" && radio != "pw" && radio != "w" && radio != "h") {
			alert("发生未知的错误");
			return;
		}

		if (radio == "ph") {
			showChecked("radio_ph_id");
		}
		if (radio == "pw") {
			showChecked("radio_pw_id");
		}
		if (radio == "w") {
			showChecked("radio_w_id");
		}
		if (radio == "h") {
			showChecked("radio_h_id");
		}

		$("#id_id").attr("value", data.sysdesignteddatebean.id);
		$("#name_id").attr("value", data.sysdesignteddatebean.name==null?"":data.sysdesignteddatebean.name);
		$("#date_id").attr("value", data.sysdesignteddatebean.date);
		$("#remark_id").attr("value", data.sysdesignteddatebean.remark==null?"":data.sysdesignteddatebean.remark);
	}
	});

	function showChecked(id) {
		$("#radio_ph_id").attr("checked", "");
		$("#radio_pw_id").attr("checked", "");
		$("#radio_w_id").attr("checked", "");
		$("#radio_h_id").attr("checked", "");
		$("#" + id).attr("checked", "checked");
	}
}}


);

