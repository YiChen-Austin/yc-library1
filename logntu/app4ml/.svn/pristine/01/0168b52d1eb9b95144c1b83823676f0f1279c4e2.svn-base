$(document).ready(function(){
    $("#modifyBusinessDictionary").validationEngine({
        validationEventTriggers: "keyup", //触发的事件  validationEventTriggers:"keyup blur",
        inlineValidation: true,//是否即时验证，false为提交表单时验证,默认true
        success: false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
        promptPosition: "centerRight",//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
        failure: function(){
        },//验证失败时调用的函数
        success: function(){
        }//验证通过时调用的函数
    });
});

// 返回的url
var backUrl = '/page/system/business_dictionary_list';
// 新增的idString,供保存时使用
var newIdsStr = '';
// 新增的idString,供撤消时使用
var _newIdsStr = '';
// 已经存在的名字
var useNamesStr = '';
// 已经存在的idString,供删除时使用
var oriIdsStr = '';
// 新增的标志
var add_key = '+';
// 修改的标志
var edit_key = '√';
// 新增时的统计次数
var _count = 1;
var msg = '字典明细的名称或值必填且不重复!';
var msg2 = '字典明细必填记录!';
var bool = false;
// 总的行数
var rowNum;
// 需要新增的值
var insertVal = new Array();
// 需要保存的值
var updataVal = new Array();;
// 需要删除的值
var delVal = new Array();;
// 获得grid里的值
var gridVals;

function loadData(id){
    $("#errorMsg").hide();
    jQuery("#childList").jqGrid({
        url: $("#path",parent.document).val()+"/admin/"+"findBusinessDictionaryDetails?id=" + id,
        datatype: "json",
        height: 200,
        width: 608,
        colNames: ['ID', '名称', '值', '备注', ''],
        colModel: [{
            name: 'id',
            index: 'id',
            fixed: true,
            sortable: false,
            editable: true,
            hidden: true
        }, {
            name: 'name',
            index: 'name',
            fixed: true,
            editable: true
        }, {
            name: 'value',
            index: 'value',
            fixed: true,
            editable: true
        }, {
            name: 'remark',
            index: 'remark',
            width: 200,
            fixed: true,
            editable: true
        }, {
            name: 'action',
            index: 'action',
            width: 38,
            fixed: true,
            editable: true
        }],
        multiselect: true,
        caption: "业务字典明细",
        pager: '#pageList',
        rowNum: 10,
        pgbuttons: false,
        pginput: false,
        viewrecords: true,
        sortorder: "desc",
        //加载完后触发
        gridComplete: function(){
            var ids = jQuery("#childList").jqGrid('getDataIDs');
            $.each(ids, function(i){
                var _userName = $.trim(jQuery("#childList").jqGrid('getCell', ids[i], 'name'));
                if (null == useNamesStr.match(_userName)) {
                    useNamesStr = useNamesStr.concat(';' + _userName + ',');
                }
                var _userId = $.trim(jQuery("#childList").jqGrid('getCell', ids[i], 'id'));
                if (null == oriIdsStr.match(_userId)) {
                    oriIdsStr = oriIdsStr.concat(';' + _userId + ',');
                }
            });
        },
        jsonReader: {
            root: "businessDictionaryDetailBeans",
            repeatitems: false
        }
    });
    
    jQuery("#childList").navGrid('#pageList', {
        edit: false,
        add: false,
        del: false,
        refresh: false,
        search: false
    }).navButtonAdd('#pageList', {
        caption: "新增",
        buttonicon: "ui-icon-plus",
        title: "新增一条记录",
        position: "last",
        onClickButton: function(){
            rowNum = jQuery('#childList').jqGrid('getGridParam', 'rowNum') +
            _count++;
            var rowId = rowNum + 1;
            // 加入新增的rowId
            newIdsStr = newIdsStr.concat(';' + rowId.toString() + ',');
            _newIdsStr = _newIdsStr.concat(';' +
            rowId.toString() +
            ',');
            jQuery('#childList').jqGrid('addRowData', rowId, {
                name: '',
                value: '',
                remark: ''
            });
            // 选中
            jQuery("#childList").jqGrid().setSelection(rowId, false);
            jQuery('#childList').jqGrid('editRow', rowId, false);
            var saveBtn = "<input class='ui-state-default ui-corner-all' style='vertical-align:middle; text-align:center; height:18px; width:18px;' type='button' value='√' onclick=\"save('" + rowId + "', '" + msg + "' + '');\" >";
            var cancelBtn = "<input class='ui-state-default ui-corner-all' style='vertical-align:middle; text-align:center; height:18px; width:18px;' type='button' value='X' onclick=\"cancelSave('" + rowId + "');\" >";
            jQuery('#childList').jqGrid('setRowData', rowId, {
                action: saveBtn + cancelBtn
            });
        }
    }).navButtonAdd('#pageList', {
        caption: "修改",
        buttonicon: "ui-icon-pencil",
        title: "修改一条或多条记录",
        position: "last",
        onClickButton: function(){
            rowNum = jQuery('#childList').jqGrid('getGridParam', 'rowNum');
            var ids = jQuery("#childList").jqGrid('getGridParam', 'selarrrow').toString();
            // 修改一条
            if (ids.match(',') == null && '' != ids) {
                var userCell = jQuery("#childList").jqGrid('getCell', ids, 'name');
                if (null == userCell.match(/<input/i)) {
                    var _var_useName = jQuery("#childList").jqGrid('getCell', ids, 'name');
                    jQuery('#childList').jqGrid('editRow', ids, false);
                    var saveBtn = "<input class='ui-state-default ui-corner-all' style='vertical-align:middle; text-align:center; height:18px; width:18px;' type='button' value='√' onclick=\"save('" + ids + "', '" + msg + "', '" + _var_useName + "');\" >";
                    var cancelBtn = "<input class='ui-state-default ui-corner-all' style='vertical-align:middle; text-align:center; height:18px; width:18px;' type='button' value='X' onclick=\"cancelSave('" + ids + "');\" >";
                    jQuery('#childList').jqGrid('setRowData', ids, {
                        action: saveBtn + cancelBtn
                    });
                }
            }
            //修改多条
            else {
                if ('' == ids) 
                	jAlert('请选择记录!');
                if (ids.match(',') != null) {
                    var _id = ids.split(',');
                    for (i = 0; i < _id.length; i++) {
                        var userCell = jQuery("#childList").jqGrid('getCell', _id[i], 'name');
                        if (null == userCell.match(/<input/i)) {
                            var _var_useName = jQuery("#childList").jqGrid('getCell', _id[i], 'name');
                            jQuery('#childList').jqGrid('editRow', _id[i], false);
                            var saveBtn = "<input class='ui-state-default ui-corner-all' style='vertical-align:middle; text-align:center; height:18px; width:18px;' type='button' value='√' onclick=\"save('" + _id[i] + "', '" + msg + "', '" + _var_useName + "');\" >";
                            var cancelBtn = "<input class='ui-state-default ui-corner-all' style='vertical-align:middle; text-align:center; height:18px; width:18px;' type='button' value='X' onclick=\"cancelSave('" + _id[i] + "');\" >";
                            jQuery('#childList').jqGrid('setRowData', _id[i], {
                                action: saveBtn + cancelBtn
                            });
                        }
                    }
                }
            }
        }
    }).navButtonAdd('#pageList', {
        caption: "删除",
        buttonicon: "ui-icon-trash",
        title: "删除一条或多条记录",
        position: "last",
        onClickButton: function(){
            var ids = jQuery("#childList").jqGrid('getGridParam', 'selarrrow').toString();
            if ('' == ids) {
            	jAlert('请选择记录!');
            }
            else {
                if (!confirm('真的要删除吗?删除后将无法恢复!')) {
                    return;
                }
                //为单个id
                if (ids.match(',') == null) {
                    var _var_useName = $.trim(jQuery("#childList").jqGrid('getCell', ids, 'name'));
                    useNamesStr = useNamesStr.replace(';' + _var_useName + ',', '');
                    
                    var _var_useId = $.trim(jQuery("#childList").jqGrid('getCell', ids, 'id'));
                    if (null != oriIdsStr.match(';' + _var_useId + ',')) {
                        oriIdsStr = oriIdsStr.replace(';' + _var_useId + ',', '');
                        delVal.push(_var_useId);
                    }
                    jQuery("#childList").jqGrid('delRowData', ids);
                }
                //多个id
                if (ids.match(',') != null) {
                    var _id = ids.split(',');
                    for (i = 0; i < _id.length; i++) {
                        var _var_useName = $.trim(jQuery("#childList").jqGrid('getCell', _id[i], 'name'));
                        useNamesStr = useNamesStr.replace(';' + _var_useName + ',', '');
                        
                        var _var_useId = $.trim(jQuery("#childList").jqGrid('getCell', _id[i], 'id'));
                        if (null != oriIdsStr.match(';' + _var_useId + ',')) {
                            oriIdsStr = oriIdsStr.replace(';' + _var_useId + ',', '');
                            delVal.push(_var_useId);
                        }
                        jQuery("#childList").jqGrid('delRowData', _id[i]);
                    }
                }
            }
        }
    });
    
    /**
     * 提交按钮
     */
    $("#saveSubmit").click(function(){
        if ($.validationEngine.loadValidation("#cnName") || $.validationEngine.loadValidation("#enName")) 
            return false;
        // gird里的全部id
        var ids = jQuery("#childList").jqGrid('getDataIDs');
        bool = true;
        var bool2 = true;
        $.each(ids, function(i){
            var userCell = jQuery("#childList").jqGrid('getCell', ids[i], 'action');
            // 检查是否含有未取消编辑状态的记录
            if (userCell.match(/<input/i) != null) {
            	jAlert('还有处于编辑状态的,请您保存或取消后再提交!');
                bool2 = false;
                return false;
            }
        });
        if (bool2) {
            submitEditAjax(ids);
        }
    });
	
    /**
     * 返回按钮
     */
	$("#backButton").click(function(){
		window.location.href = $("#path",parent.document).val()+backUrl;
	});
    
    /**
     * 重置按钮
     */
	$("#resetButton").click(function(){
		window.location.href = $("#path",parent.document).val()+"/admin/"+"findBusinessDictionary?id=" + id;
	});
    
};

/**
 * grid里的保存按钮
 */
function save(id, str, oriVal){
    //为新增时
    var _id = id;
    // 校验
    if (!validation(_id, oriVal)) {
        //提示信息
    	jAlert(str);
        bool = false;
        return bool;
    }
    if (_newIdsStr.match(';' + id + ',') != null) {
        jQuery('#childList').jqGrid('setRowData', _id, {
            action: add_key
        });
    }
    //不为新增时
    else {
        jQuery('#childList').jqGrid('setRowData', _id, {
            action: edit_key
        });
    }
    //保存数据
    jQuery('#childList').jqGrid('saveRow', _id, null, 'clientArray');
    // 不是新增时，加入新name
    if ('' != oriVal) {
        useNamesStr = useNamesStr.replace(';' + oriVal + ',', '');
    }
    newIdsStr = newIdsStr.replace(';' + id + ',', '');
}

/**
 * 取消按钮
 */
function cancelSave(id){
    var _id = id;
    if (newIdsStr.match(';' + id + ',') != null) {
        jQuery("#childList").jqGrid('delRowData', _id);
    }
    else {
        jQuery('#childList').jqGrid('restoreRow', _id);
    }
    newIdsStr = newIdsStr.replace(';' + id + ',', '');
}

/**
 * 提交的ajax
 */
function submitEditAjax(ids){
	if('' == ids){
		jAlert(msg2);
		return false;
	}
	insertVal = new Array();
	updataVal = new Array();
    var id = $.trim($("#hiddenId").val());
    var cnName = encodeURI($.trim($("#cnName").val()));
    var enName = encodeURI($.trim($("#enName").val()));
    var flag = $("#flag").val();
    var remark = encodeURI($.trim($("#remark").val()));
    var data = "id=" + id + "&cnName=" + cnName + "&enName=" + enName + "&flag=" + flag + "&remark=" + remark;
    $.each(ids, function(i){
        var userCell = jQuery("#childList").jqGrid('getCell', ids[i], 'action');
        if (add_key == userCell) {
            var _val = jQuery('#childList').jqGrid('getRowData', ids[i]);
            insertVal.push($.trim(_val.name) + ';' + $.trim(_val.value) + ';' + $.trim(_val.remark));
        }
        if (edit_key == userCell) {
            var _val = jQuery('#childList').jqGrid('getRowData', ids[i]);
            updataVal.push($.trim(_val.id) + ';' + $.trim(_val.name) + ';' + $.trim(_val.value) + ';' + $.trim(_val.remark));
        }
    });
	var values = '';
    if (0 == insertVal.length && 0 == updataVal.length && 0 == delVal.length) {
	}
	else {
		values = insertVal + '||' + updataVal + '||' + delVal;
	}
	$.ajax({
		type: "POST",
		cache: false,
 		async: false,
		dataType: "json",
		type: "POST",
		url: $("#path",parent.document).val()+"/admin/"+"modifyBusinessDictionary",
		data: data + "&values=" + values,
		success: function(json){
		if (json.result) {
			window.location.href = $("#path",parent.document).val()+backUrl;
			$("#errorMsg").hide();
		}
		else {
			$("#errorMsg").show();
		}
	}
});
}

/**
 * 校验明细的合法性
 */
function validation(id, oriVal){
    var rowData = jQuery("#childList").jqGrid('getRowData', id);
    var _key;
    var rowData_name = rowData.name;
    var rowData_value = rowData.value;
    var newName_val;
    var newValue_val;
    
    // 非ie
    if (null == rowData_name.match(/value/i)) {
        _key = "_name\"";
        var newName = rowData_name.slice((rowData_name.indexOf('id="') + 4), (rowData_name.lastIndexOf(_key) + _key.length - 1));
        newName_val = $('#' + newName).val();
    }
    // ie
    else {
        if (null != rowData_name.match(/value="/i)) {
            var _var_newName_val = rowData_name.slice(rowData_name.indexOf('value="') + 7);
            newName_val = $.trim(_var_newName_val.slice(0, _var_newName_val.indexOf('"')));
        }
        else {
            var _var_newName_val = rowData_name.slice(rowData_name.indexOf('value=') + 6);
            if (null != _var_newName_val.match(/>/i)) {
                newName_val = $.trim(_var_newName_val.split('>')[0]);
            }
            if (null != _var_newName_val.match(/ /i)) {
                newName_val = $.trim(_var_newName_val.split(' ')[0]);
            }
        }
    }
    
    // 非ie
    if (null == rowData_value.match(/value=/i)) {
        _key = "_value\"";
        var newValue = rowData_value.slice((rowData_value.indexOf('id="') + 4), (rowData_value.lastIndexOf(_key) + _key.length - 1));
        newValue_val = $('#' + newValue).val();
    }
    // ie
    else {
        if (null != rowData_value.match(/value="/i)) {
            var _var_newValue_val = rowData_value.slice(rowData_value.indexOf('value="') + 7);
            newValue_val = $.trim(_var_newValue_val.slice(0, _var_newValue_val.indexOf('"')));
        }
        else {
            var _var_newValue_val = rowData_value.slice(rowData_value.indexOf('value=') + 6);
            if (null != _var_newValue_val.match(/>/i)) {
                newValue_val = $.trim(_var_newValue_val.split('>')[0]);
            }
            if (null != _var_newValue_val.match(/ /i)) {
                newValue_val = $.trim(_var_newValue_val.split(' ')[0]);
            }
        }
    }
    
    // 合法
    if ('' != $.trim(newName_val) && '' != $.trim(newValue_val) && undefined != newName_val && undefined != newValue_val && ((null == useNamesStr.match(';' + $.trim(newName_val) + ',')) || (oriVal == $.trim(newName_val)))) {
        useNamesStr = useNamesStr.concat(';' + $.trim(newName_val) + ',');
        return true;
    }
    else {
        return false;
    }
}

/**
 * 加载grid值
 */
$(document).ready(function(){
	loadData($.trim($("#hiddenId").val()));
});
