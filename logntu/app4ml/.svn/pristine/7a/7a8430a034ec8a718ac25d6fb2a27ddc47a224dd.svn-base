var jobId = '';
var isEdit=false;
$(function() {
	var editId='';
	var isSave=true;
	jQuery("#jobDetails").jqGrid( {
		url : $("#path",parent.document).val()+"/admin/"+'findAllJobs',
		datatype : "json",
		mtype:'POST',
		colNames : [ '计划名称', '计划执行类', '执行方法','触发器数量', '说明' ],
		colModel : [ {name : 'jobName',index : 'jobName',width : 90,sortable : false}, 
		             {name : 'beanId',index : 'beanId',width : 120,sortable : false},
		             {name : 'executeMethod',index : 'executeMethod',width : 120,sortable : false}, 
		             {name : 'triggerSum',index : 'triggerSum',width : 50,sortable : false,align : 'center'}, 
		             {name : 'description',index : 'description',width : 100,sortable : false} ],
		editable : true,
		onSelectRow : function(id) {
			jobId = id;
			if(isSave){
				editId=id;
			}
		},
		ondblClickRow:function(rowid,iRow,iCol,e){
			manageTriggers();
		},
		rowNum : 10,
		rowList : [ 10, 20, 30, 50 ],
		sortname : 'id',
		viewrecords : true,
		sortorder : "desc",
		height : 400,
		width : 700,
		caption : "计划任务信息",
		jsonReader : {
			root : "jobDetailBeans",
			repeatitems : false
		},
		loadError : function(xhr, status, error) {
			isNotLoginError(xhr);
		}
	});
	jQuery("#triggersList").jqGrid({
		datatype : "json",
		colNames : [ '触发器名称','开始时间','上次执行时间','下次执行时间','状态','说明'],
		colModel : [ { name : 'triggerName', index : 'triggerName', width : 90,sortable : false}, 		        
		             { name : 'startTime', index : 'startTime', width : 130,sortable : false}, 
		             { name : 'previousFireTime', index : 'previousFireTime', width : 130,sortable : false}, 
		             { name : 'nextFireTime', index : 'nextFireTime', width : 130,sortable : false},		        
		             { name : 'state', index : 'state', width : 40,align : 'center',sortable : false},
		             { name : 'description', index : 'description', width : 130,sortable : false}],
		loadError : function(xhr, status, error) {
					isNotLoginError(xhr);
			},
		multiselect : true,
		sortname : 'id',
		viewrecords : true,
		sortorder : "desc",
		height : 270,
		width : 730,
		jsonReader : {
			root : "triggerBeans",
			repeatitems : false
		},rowNum:1000
		
	});
	//新增触发器dialog
	$("#triggerDetail").dialog({
		modal:true,
		close:function(){
			$(".formError").remove();
		},
		width:480,
		height:370,
		minHeight:370,
		minWidth:480,
		maxHeight:370,
		maxWidth:480
		}).dialog("close");
	//新增触发器保存
	$("#saveTrigger").click(function(){
		if(!validationForm("#form1")){
			return false;
		};
		if(($("#repeatCount").val()==''&&$("#repeatInterval").val()!='')||($("#repeatCount").val()!=''&&$("#repeatInterval").val()=='')){
			jAlert('执行次数和执行次数间隔必须同时填写！');
			return;
		}
			$.ajax( {
				type : "POST",
				url : $("#path",parent.document).val()+"/admin/"+"addTrigger",
				data : $("#form1").ajaxForm().formSerialize()+"&id="+jobId,
				success : function(data) {
					if(data.executeResult=="sameNameError"){
						jAlert("触发器名字已经存在，请重新输入!");
					}
					if(data.executeResult=="true"){
						$("#form1").ajaxForm().clearForm();
						jAlert('设置成功！');
						reloadTriggersList();
						jQuery("#jobDetails").jqGrid().trigger("reloadGrid");
					}
					if(data.executeResult=="false"){
						jAlert('设置失败！');
					}
				},error:function(xhr, textStatus, errorThrown){
					if(xhr.status='401'){
						isNotLoginError(xhr);
						return;
					}
					jAlert('设置失败！');
				}
			});
			$(":radio:first").click();
	});
	//查看计划任务对应的多元触发器Dialog
	$("#triggerDialog").dialog({
		modal:true,
		width:760,
		height:418,
		minHeight:418,
		minWidth:760,
		maxHeight:418,
		maxWidth : 760
	}).dialog("close");
	//新增触发器
	$("#addNewTrigger").click(function() {
		$("#triggerDetail").dialog("open");
	});
	//删除触发器
	$("#deleteTrigger").click(function() {
		if(!confirm("确定要删除该触发器？一旦删除不可恢复！")){
			return;
		} 
		exeAjax("removeTriggers");
	});
	//恢复触发器
	$("#resumeTrigger").click(function() {
		
		exeAjax("resumeTrigger");
	});
	//停止触发器
	$("#pauseTrigger").click(function() {
		exeAjax("pauseTriggers");
	});
	
	//新增/修改计划任务dialog
	$("#jobDetail").dialog({
		modal:true,
		close:function(){
			$(".formError").remove();
		},
		width:480,
		height:340,
		minHeight:340,
		minWidth:480,
		maxHeight:340,
		maxWidth:480
		}).dialog("close");
	//新增定时任务
	$("#addNewJob").click(function(){
		isEdit=false;
		$("#jobDetail").dialog("open");
	});
	//保存定时任务
	$("#saveJob").click(function(){
		if(!validationForm("#jobForm")){
			return false;
		};
		
		$.ajax( {
			type : "POST",
			url : isEdit==true?$("#path",parent.document).val()+"/admin/"+"updateJobDetail":$("#path",parent.document).val()+"/admin/"+"addJobDetail",
			data : isEdit==true?$("#jobForm").ajaxForm().formSerialize()+"&id="+jobId:$("#jobForm").ajaxForm().formSerialize(),
			success : function(data) {
				if (data.executeResult == "errBeanOrMethod") {
					jAlert('计划执行类  或者 方法有错！');
					return;
				}
				if (data.executeResult == "sameNameError") {
					jAlert("定时任务已经存在，请重新输入!");
					return;
				}
				if (data.executeResult=="true") {
					jQuery("#jobDetails").jqGrid().trigger("reloadGrid");
					jAlert('设置成功！');
					$("#jobForm").ajaxForm().clearForm();
					return;
				} 
				if (data.executeResult=="false") {
					jAlert('设置失败！');
					return;
				}
				$(":radio:first").click();
			},error:function(xhr, textStatus, errorThrown){
				if(xhr.status='401'){
					isNotLoginError(xhr);
					return;
				}
				jAlert('设置失败！');
			}
		});
	});
	//修改定时任务
	$("#updateJob").click(function(){
		if(jobId==''){
			jAlert('请选择一条记录！');
			return;
		}
		isEdit=true;
		var job=jQuery("#jobDetails").jqGrid('getRowData',jobId);
		$("#jobDetail").dialog("open");
		$("#jobName").val(job.jobName);
		$("#beanId").val(job.beanId);
		$("#executeMethod").val(job.executeMethod);
		$("#descriptionBean").val(job.description);
	});
	//删除定时任务
	$("#deleteJob").click(function(){
		if(jobId==''){
			jAlert('请选择一条记录！');
			return;
		}
		if(!confirm("确定要删除该定时任务？一旦删除不可恢复！")){
			return;
		} 
		$.ajax( {
			type : "POST",
			url : $("#path",parent.document).val()+"/admin/"+"deleteJobDetail",
			data :{id:jobId},
			success : function(data) {
				if (data.executeResult=="true"){
					jAlert('设置成功！');
					jQuery("#jobDetails").jqGrid().trigger("reloadGrid");
				}
				if (data.executeResult=="false"){
					jAlert('设置失败！');
				}
			},error:function(xhr, textStatus, errorThrown){
				if(xhr.status='401'){
					isNotLoginError(xhr);
					return;
				};
				jAlert('设置失败！');
			}
		});
	});
	
	//管理触发器
	$("#manageTriggers").click(function(){
		if(jobId==''){
			jAlert('请选择一条记录！');
			return;
		}
		manageTriggers();
	});
	
	/*
	 * 新增Trigger Dialog中的单选控制
	 */
	//定义input框
	var mothInput='<input type="text" size="4" name="dayOfMonth" id="dayOfMonth" class="validate[required,custom[onlyNumber]]" />';
	var hourInput='<input type="text" size="4" name="hour" id="hour" class="validate[required,custom[numberFloat]]"/>';
	var minuteInput='<input type="text" size="4" name="minute" id="minute" class="validate[required,custom[numberFloat]]"/>';
	var weekInput='<input type="text" size="4" name="dayOfWeek" id="minute" class="validate[required,custom[numberFloat]]"/>';
	var repeatCountInput='<input type="text" size="4" name="repeatCount" id="repeatCount" class="validate[custom[numberFloat]]"/>';
	var repeatIntervalInput='<input type="text" size="4" name="repeatInterval" id="repeatInterval" class="validate[custom[numberFloat]]"/>';
	var cronExpressionInput='<input type="text" name="cronExpression" id="cronExpression" class="validate[required]"/>';
	
	var exeTimeTr='<td>执行时间：</td>';
	
	var remarkTD='<td>备注：</td>';
	var mothly='<td>每月&nbsp;'+mothInput+'&nbsp;号&nbsp;'+hourInput+'&nbsp;:&nbsp;'+minuteInput+'&nbsp;分<font color="blue">* 月末请填写 -1</font></td>';
	var weekly='<td>每周第&nbsp;'+weekInput+'&nbsp;天&nbsp;'+hourInput+'&nbsp;:&nbsp;'+minuteInput+'&nbsp;分 <font color="blue">* 每周第1天为星期天</font></td>';
	var daily='<td>每天&nbsp;'+hourInput+'&nbsp;:&nbsp;'+minuteInput+'分</td>';
	var cronExpression='<td>'+cronExpressionInput+'</td>';
	
	var startTimeTr='<tr class="custmerOpt"><td>开始时间：</td><td><input type="text" id="startTime" name="startTime" onfocus="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true})" readonly="readonly" /> <font color="blue">* 不选表示当前时间</font></td></tr>';
	var repeatCountTr='<tr  class="custmerOpt"><td>执行次数：</td><td>'+repeatCountInput+' <font color="blue">* 不填写表示一直执行</font></td></tr>';
	var repeatInterval='<tr class="custmerOpt"><td>执行间隔：</td><td>'+repeatIntervalInput+' <font color="blue">* 不填写表示默认每<font color="red">{0}</font>执行</font></td></tr>';
	
	
	$(":radio").click(function(){
		$(".formError").remove();
		clearTr();
		validation();
		switch ($(this).val()) {
			case "1":
				$("#opt").append(exeTimeTr + mothly); 
				break;
			case "2":
				$("#opt").append(exeTimeTr + weekly);
				break;
			case "3":
				$("#opt").append(exeTimeTr + daily);
				break;
			case "4":
				$("#remark").after(startTimeTr).after(repeatCountTr).after(repeatInterval.toString().replace("{0}", "小时"));
				break;
			case "5":
				$("#remark").after(startTimeTr).after(repeatCountTr).after(repeatInterval.toString().replace("{0}", "分钟"));
				break;
			case "6":
				$("#remark").after(startTimeTr).after(repeatCountTr).after(repeatInterval.toString().replace("{0}", "秒钟"));
				break;
			case "7":
				$("#opt").append(exeTimeTr + cronExpression);
				break;
			default:
				break;
		}
	});
	$("#jobForm").validationEngine({
		validationEventTriggers:"keyup",  //触发的事件  validationEventTriggers:"keyup blur",
		inlineValidation: true,//是否即时验证，false为提交表单时验证,默认true
		success :  false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
		promptPosition: "topRight",//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		failure : function() { },//验证失败时调用的函数
		success : function() {  }//验证通过时调用的函数
		});;
	$(":radio:first").click();
});
function validation(){
	/*
	 * 验证
	 * 
	 */
	$("#form1").validationEngine({
		validationEventTriggers:"keyup",  //触发的事件  validationEventTriggers:"keyup blur",
		inlineValidation: true,//是否即时验证，false为提交表单时验证,默认true
		success :  false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
		promptPosition: "topRight",//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		failure : function() { },//验证失败时调用的函数
		success : function() {  }//验证通过时调用的函数
		});;
}
function validationForm(formid){
	var success=true;
	$(formid+" :text").each(function(i,input){
		if($.validationEngine.loadValidation("#"+$(input).attr('id'))){
			success=false;
		}
	});
	return success;
}
function clearUserRoleInfo(){
	jQuery("#triggersList").jqGrid().clearGridData();
}
function clearTr(){
	$("#opt td").remove();
	$("#remark td").remove();
	$(".custmerOpt").remove();
}
function isNotLoginError(xhr){
	if (xhr.status=='401') {
		var errMsg = window["eval"]("("+ xhr.getResponseHeader("Error-Json") + ")");
		if (errMsg.reason == 'notLogin') {
			jAlert("你还未登录,请登录！");
			window.parent.location.href = errMsg.content+ "/admin/index";
		}
	}
}
function exeAjax(action){
	$.ajax( {
		type : "POST",
		url : $("#path",parent.document).val()+"/admin/"+action,
		data : {triggers:getSelectedTriggerName()},
		success : function(data) {
			if(data.executeResult=="true"){
				jAlert('设置成功！');
				reloadTriggersList();
				jQuery("#jobDetails").jqGrid().trigger("reloadGrid");
			}
			if(data.executeResult=="false"){
				jAlert('设置失败！');
			}
		},error:function(xhr, textStatus, errorThrown){
			if(xhr.status='401'){
				isNotLoginError(xhr);
				return;
			}
			jAlert('设置失败！');
		}
	});
}
function manageTriggers(){
	clearUserRoleInfo();
	$("#triggerDialog").dialog("open");
	reloadTriggersList();
}
function getSelectedTriggerName(){
	var names='';
	var ids=jQuery("#triggersList").jqGrid('getGridParam','selarrrow');
	$(ids.toString().split(",")).each(function(i,id){
		names+=jQuery("#triggersList").jqGrid('getRowData',id).triggerName+",";
	});
	return names.substring(0, names.length-1);
}
function reloadTriggersList(){
	jQuery("#triggersList").jqGrid('setGridParam', {
		url : $("#path",parent.document).val()+"/admin/"+"findTriggerByJobName?id="+ jobId,
		page : 1
	}).trigger("reloadGrid");
}