//当前选中节点
var node;
// 操作状态 [none:默认无操作,same:同级,secondary:次级,modify:修改,delete:删除]
var operatorType = 'none';
$(document).ready(function() {
	$("#menuForm").validationEngine({
	validationEventTriggers:"keyup",  //触发的事件  validationEventTriggers:"keyup blur",
	inlineValidation: true,//是否即时验证，false为提交表单时验证,默认true
	success :  false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
	promptPosition: "centerRight",//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
	failure : function() { },//验证失败时调用的函数
	success : function() {callSuccessFunction();  }//验证通过时调用的函数
	});
});
$(function() {
	$("#pageUrl").width(220);
	$("#remark").width(220);
	// ajax调用不使用缓存
	$.ajaxSetup( {
		cache : false
	});
	$("#menu_tree")
			.tree( {// menu_tree 为 由Jquery选择器选中的DIV容器的ID
				
					types : {
						// all node types inherit the "default" node type
						"default" : {
							draggable : false
						}
					},
						data : {
							type : "json",
							async : true,
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
								$("#repeatName").hide();
								operatorType = 'modify';
								node = NODE;
								var name = TREE_OBJ.get_text(node);
								$("#name").attr("value", name);
								$("#id").attr("value", $(NODE).attr("id"));
								$("#orderNo").attr("value", $(NODE).attr("orderNo"));
								$("#parentId").attr("value", $(NODE).attr("parentId"));
								$("#pageUrl").attr("value", $(NODE).attr("pageUrl"));
								$("#remark").attr("value", $(NODE).attr("remark"));
								$("#flag").attr("value", $(NODE).attr("flag"));
								$("#nodeImage").attr("value", $(NODE).attr("rel"));
								
								if( $(NODE).attr("internalCommand") == "1" || $(NODE).attr("internalCommand")  == 1){
									$("#internalCommand").attr("checked","checked");
								}else{
									$("#internalCommand").attr("checked","");
								}
								
								if ($(NODE).attr("parentId")!= '') {
									$("#menuTable").css('display', 'block');
									$('#sameLevelCreateButton').removeAttr(
											'disabled');
									$('#secondaryLevelCreateButton')
											.removeAttr('disabled');
									if($(NODE).attr("flag")=='0'){
										$('#deleteButton').attr(
												'disabled', 'disabled');
										$('#saveButton').attr(
												'disabled', 'disabled');
									}else{
										$('#deleteButton').removeAttr(
										'disabled');
										$('#saveButton').removeAttr('disabled');
									}
								} else {
									$("#menuTable").css('display', 'block');
									$('#sameLevelCreateButton').attr(
											'disabled', 'disabled');
									$('#secondaryLevelCreateButton')
											.removeAttr('disabled');
									$('#deleteButton').attr('disabled',
											'disabled');
									$('#saveButton').attr(
											'disabled', 'disabled');
								}
							}
						},
						// 插件使用右键菜单支持自定义右键菜单
						plugins : {}

					});
	// 同级新增功能
	$("#sameLevelCreateButton").click(function() {
		operatorType = 'same';
		$("#name").attr("value", "");
		$("#id").attr("value", "");
		$("#parentId").attr("value", node.parentId);
		$("#pageUrl").attr("value", "");
		$("#remark").attr("value", "");
		$("#nodeImage").attr("value", "");
		$("#selectId").attr("value", node.id);
		$("#flag").attr("value", "1");
		$('#saveButton').removeAttr('disabled');
		$("#internalCommand").attr("checked","");
	});
	// 次级新增功能
	$("#secondaryLevelCreateButton").click(function() {
		operatorType = 'secondary';
		$("#name").attr("value", "");
		$("#id").attr("value", "");
		$("#parentId").attr("value", node.id);
		$("#pageUrl").attr("value", "");
		$("#remark").attr("value", "");
		$("#nodeImage").attr("value","");
		$("#selectId").attr("value", node.id);
		$("#flag").attr("value", "1");
		$('#saveButton').removeAttr('disabled');
		$("#internalCommand").attr("checked","");
	});
	// 同级新增、次级新增和修改的保存功能
	$("#saveButton").click(
			function() {
				//校验JSP的地址是否满足条件
				var pageUrl  = $("#pageUrl").attr("value");
				if(pageUrl!="" && $("#internalCommand").attr("checked")== false){
//					if(pageUrl.substring(0,1)!="/" ){
//						$("#repeatName").attr("innerHTML", "<li><span>链接地址必须以\"/\"开头</span></li>");
//						$("#repeatName").show();
//						return ;
//					}
//					if(pageUrl.substring(pageUrl.length-4,pageUrl.length).toLowerCase()!=".jsp"){
//						$("#repeatName").attr("innerHTML", "<li><span>链接地址必须是正确的JSP地址</span></li>");
//						$("#repeatName").show();	
//						return ;
//					}
						
				}
				var actionMethod = 'modifySysMenu';
				if(operatorType=='same')
					actionMethod = 'sameSaveSysMenu';
				else if(operatorType=='secondary')
					actionMethod = 'secondarySaveSysMenu';
				else 
					actionMethod = 'modifySysMenu';
				$.ajax( {
					async : false,
					url : $("#path",parent.document).val()+"/admin/"+actionMethod,
					type : 'POST',
					dataType : 'json',
					data : jQuery("input, textarea", $("#menuForm"))
							.serialize(),
					timeout : 1000,
					success : function(data) {
						if (data.result) {
							$("#repeatName").hide();
							if (operatorType == 'secondary') {
								jAlert("操作成功!");
								var newNode = addNode(node, 'secondary', data.newId);
								$.tree.focused().select_branch($(newNode));
							} else if (operatorType == 'same') {
								jAlert("操作成功!");
								var newNode = addNode(node, 'same', data.newId);
								$.tree.focused().select_branch($(newNode));
							} else if (operatorType == 'modify') {
								jAlert("操作成功!");
								$.tree.focused().rename($(node),
										$("#name").attr("value"));
								$(node).attr("pageUrl", $("#pageUrl").attr("value"));
								$(node).attr("remark", $("#remark").attr("value"));
								$(node).attr("rel", $("#nodeImage").attr("value"));
								if($("#internalCommand").attr("checked")==true){
									$(node).attr("internalCommand","1");
								}else{
									node.internalCommand="0";
								}
								
							}

						} else {
							if(data.newId=='none'){
								$("#repeatName").attr("innerHTML", "<li><span>已有该菜单名!</span></li>");
								$("#repeatName").show();
							}
							else{
								$("#repeatName").hide();
								jAlert("操作失败!");
							}
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$("#repeatName").hide();
						jAlert("操作失败!");
					},
					beforeSend: function(){
						if($.validationEngine.loadValidation("#name")||$.validationEngine.loadValidation("#nodeImage"))
							return false;
					}

				});
			});
	// 删除功能
	$("#deleteButton").click(function() {
		operatorType = 'delete';
		if(!confirm('真的要删除吗？删除后将无法恢复!')){      
		       return;      
		 };  
		$.ajax( {
			async : false,
			url : $("#path",parent.document).val()+"/admin/"+'deleteSysMenu?id=' + node.id,
			type : 'POST',
			dataType : 'json',
			timeout : 1000,
			success : function(data) {
				if (data) {
					$("#repeatName").hide();
					changeOrderNo(node);
					$.tree.focused().remove($(node));
				} else {
					jAlert('删除失败!');
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				jAlert('删除失败!');
			}

		});
	});

});
/**
 * 删除节点改变其orderNo值
 * 
 * @param node
 *            -节点对象
 * @return
 */
function changeOrderNo(node){
	// 选中节点位置
	var orderNo = Number(node.orderNo);
	parentNode = $(node).parent();
	var childrenNodes = $(parentNode).children();
	var size = childrenNodes.size();
	for ( var i = 0; i < size; i++) {
		if(Number(childrenNodes[i].orderNo)>orderNo){
			$(childrenNodes[i]).attr("orderNo", i);
		}
	}
}
/**
 * node -节点对象 method -增加方式[same:同级新增;secondary:次级新增] newId; -增加节点成功返回的ID值
 */
function addNode(node, method, newId) {
	// 选中节点位置
	var orderNo =getNodeOrderNo(node, method);
	// 父节点
	var parentNode = null;
	// 是否多页面
	var internalCommand=0;
	if($("#internalCommand").attr("checked")==true){
		internalCommand="1";
	}else{
		internalCommand="0";
	}
	if (method == 'same'){
		parentNode = $(node).parent();
		var childrenNodes = $(parentNode).children();
		var size = childrenNodes.size();
		for ( var i = 0; i < size; i++) {
			if(Number(childrenNodes[i].orderNo)>orderNo){
				$(childrenNodes[i]).attr("orderNo", i+2);
			}
		}
		// 插入新的节点
		var newNode = $.tree.focused().create( {
			data : $("#name").attr("value"),
			attributes : {
				"id" : newId,
				"orderNo" : (orderNo+1),
				"parentId" : parentNode.id,
				"pageUrl" : $("#pageUrl").attr("value"),
				"remark" : $("#remark").attr("value"),
				"rel" : $("#nodeImage").attr("value"),
				"flag" : "1",
				"internalCommand":internalCommand
			}
		}, parentNode, orderNo);
		return newNode;
	}
	else {
		parentNode = $(node);
		orderNo = $(parentNode).children().children().size();
		// 插入新的节点
		var newNode = $.tree.focused().create( {
			data : $("#name").attr("value"),
			attributes : {
				"id" : newId,
				"orderNo" : (orderNo),
				"parentId" : node.id,
				"pageUrl" : $("#pageUrl").attr("value"),
				"remark" : $("#remark").attr("value"),
				"rel" : $("#nodeImage").attr("value"),
				"flag" : "1",
				"internalCommand":internalCommand
			}
		}, parentNode, orderNo);
		return newNode;
	}
}
 /**
	 * 用于获取树节点所在树的父节点里的子节点位置[其实位置为1] 返回-1 表示失败 node -节点对象 operatorType -增加方式
	 * 
	 * @return
	 */
 function getNodeOrderNo(node, operatorType) {
	 // 获取该父节点下的所有节点数
	 if(operatorType=='same'){
		 var childrenNodes = $(node).parent().children();
		 var size = childrenNodes.size();
		 for ( var i = 0; i < size; i++) {
			 if (childrenNodes[i] == node) {
			 return i+1;
		 }
	 }
	 }else{
		 return  $(node).children().children().size();
	 }
	 return -1;

 }
/**
 * 展开节点
 */
function opennode() {
	$.tree.focused().open_branch(getnodeid());
}
/**
 * 关闭节点
 */
function closenode() {
	$.tree.focused().close_branch(getnodeid());
}
