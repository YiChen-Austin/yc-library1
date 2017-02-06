<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/body.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/theme/<%=peeling %>/jquery-ui-1.8.1.custom.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/js/vendor/jqgrid/v_3_6_5/css/ui.jqgrid.css"  media="screen"/>
<link type="text/css" rel="stylesheet" href="<%=path%>/js/vendor/formValidator/1_6_2/css/validationEngine.jquery.css" />
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jqgrid/v_3_6_5/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/jquery/v_1_4_2/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/my97/v_4_6/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/vendor/formValidator/1_6_2/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=path%>/js/ux/system/sys_scheduler_manage.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/ui/v_1_0/style.js"></script>


<link type="text/css" rel="stylesheet" href="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.css" />
<script type="text/javascript" src="<%=path %>/js/vendor/jquery/jqueryalert/jquery.alerts.js"></script>
</head>
<body>

<div style="margin-left: 10px;margin-top: 10px">
<input type="button" value="新增" id="addNewJob"/>
<input type="button" value="修改" id="updateJob" />
<input type="button" value="删除" id="deleteJob" />
<input type="button" value="管理触发器" id="manageTriggers" />
	<table id="jobDetails"></table>
</div>

<div id="triggerDialog" title="触发器列表" style="font-size: 12px">
	<table id="triggersList"></table>
	<input type="button" value="增加" id="addNewTrigger" />
	<input type="button" value="删除" id="deleteTrigger" />
	<input type="button" value="恢复" id="resumeTrigger"/>
	<input type="button" value="停止" id="pauseTrigger" />
</div>
<div id="jobDetail" title="定时任务" style="display: none;">
	<form id="jobForm">
		<table>
			<tr>
				<td>名称：</td>
				<td><input type="text" id="jobName" name="jobName"  class="validate[required,custom[chineseEnglishNumber]]" />（必填）</td>
			</tr>
			<tr>
				<td>计划执行类：</td>
				<td><input type="text" id="beanId" name="beanId"   class="validate[required,custom[englishNumberLine]]" />（必填）</td>
			</tr>
			<tr>
				<td>计划执行方法：</td>
				<td><input type="text" id="executeMethod" name="executeMethod" class="validate[required,custom[englishNumberLine]]" /> （必填）</td>
			</tr>
			<tr>
				<td>描述：</td>
				<td>
				<textarea rows="6" name="description" id="descriptionBean" cols="40"></textarea>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 10px;margin-top: 20px" colspan="2"><input type="button" id="saveJob" value="保存"/></td>
			</tr>
		</table>
	</form>
</div>
<div id="triggerDetail" title="触发器信息" style="display: none;">
<form id="form1">
		<table>
			<tr>
				<td>名称：</td>
				<td><input type="text" id="triggerName" name=""triggerName" class="validate[required,custom[chineseEnglishNumber]]" />（必填）</td>
			</tr>
			<tr>
				<td  rowspan="2">执行频率：</td>
				<td>
					<input type="radio" value="1" name=""exeType"/>月
					<input type="radio" value="2" name=""exeType"/>周
					<input type="radio" value="3" name=""exeType"/>日
					
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" value="4" name=""exeType"/>时
					<input type="radio" value="5" name=""exeType"/>分
					<input type="radio" value="6" name=""exeType"/>秒
					<input type="radio" value="7"  name=""exeType"/>表达式
				</td>
			</tr>
			<tr id="opt">
			
			</tr>
			<tr id="remark">
				
			</tr>
			
			<tr>
				<td>说明：</td>
				<td><textarea rows="6" name=""description"  cols="40"></textarea></td>
			</tr>
			<tr>
				<td style="padding-left: 10px" colspan="2"><input type="button" id="saveTrigger" value="保存"/></td>
			</tr>
		</table>
</form>
</div>
</body>
</html>