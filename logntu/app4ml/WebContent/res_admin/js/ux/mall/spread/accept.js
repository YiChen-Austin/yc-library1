
$(function() {
	var option={
			loadMsg : "加载中，请稍后.....",
			singleSelect : true,
			pagination : true,
			pageSize : 15,
			pageList : [ 5, 10, 15, 20, 30, 50, 80, 200 ],
			loadFilter : function(data) {
				if (data.code == 1) {
					return data;
				} else {
					$.messager.alert("提示", "服务器无响应");
				}
			},
			idField:"applyId",
			columns : [[{
				field : "applyId",
				checkbox:true
			},{
				field : "storeName",
				title : "店铺名称",
				width :"120px",
				align : 'left'
			},{
				field : "userName",
				title : "联系人",
				width :"80px",
				align : 'left'
			}, {
				field : "phone",
				title : "联系电话",
				width :"100px",
				align : 'left'
			}, {
				field : "tel",
				title : "备用电话",
				width :"100px",
				align : 'left'
			} , {
				field : "address",
				title : "店铺地址",
				align : 'left'
			}  , {
				field : "operName",
				title : "受理人",
				width :"80px",
				align : 'left'
			} , {
				field : "createTime",
				title : "申请时间",
				width :"120px",
				formatter:function(value,row,index){  
	                 var unixTimestamp = new Date(value);  
	                 return unixTimestamp.toLocaleString();  
	            },
				align : 'left'
			}, {
				field : "content",
				title : "申请内容",
				width :"200px",
				align : 'left'
			} , {
				field : "operContent",
				title : "受理内容",
				align : 'left'
			}]]
	};
	//选项卡
	$('#tabs_info').tabs({
		onSelect:function(title,index){
			$('#searchform').form('clear');
		    $("#J_spreadContent").datagrid($.extend(option,{
		    	url : $("#path",parent.document).val()+"/admin/mall/spreadAcceptSearch",
				queryParams:formToJson("searchform",{name:"stateInfo",value:index==2?9:index}),
				singleSelect:index==0?true:false
			}));
		    $('div.datagrid-toolbar td').eq(0).css({display:index==0?"":"none"});
		     $('div.datagrid-toolbar td').eq(1).css({display:index==0?"":"none"});
		     $('div.datagrid-toolbar td').eq(2).css({display:index==2?"none":""});
		}
	});	
	//搜索
	$("#btnSearch").bind("click",function(){
		var index = $('#tabs_info').tabs('getTabIndex',$('#tabs_info').tabs('getSelected'));
		$('#J_spreadContent').datagrid({ queryParams:formToJson("searchform",{name:"stateInfo",value:index==2?9:index}) });
	});
	//受理
	$("#savespreadAcceptButton").bind("click",function(){
		var rows = $('#J_spreadContent').datagrid('getSelections');
		if(rows.length>0){
			if(rows.length>1){
				$.messager.alert('赢载网-友情提示：','请选择一条数据!','info');
				return;
			}
			var row = $('#J_spreadContent').datagrid('getSelected');
			$('#fm').form('load',row);  
			$("#operContent").val("亲，我们会在24小时内与您联系，请保持电话畅通，谢谢！");
			$("#dialog_accept").dialog({   
			    title: '推广受理',  
			    modal: true,
			    buttons:[{
			    	text:'回复',
					handler:function(){
						if($("#fm").form('validate')){
							var url= $("#path",parent.document).val()+"/admin/mall/spreadAcceptOper";
							$.post(url,$("#fm").serialize(),function(rs){
								if(rs.code=1){
									$('#dialog_accept').dialog('close');
									var index = $('#tabs_info').tabs('getTabIndex',$('#tabs_info').tabs('getSelected'));
									$('#J_spreadContent').datagrid({ queryParams:formToJson("searchform",{name:"stateInfo",value:index==2?9:index}) });
									
								}else{
									$.messager.alert('赢载网-友情提示：','删除失败请重试!','error');
								}
						   });
						}
					}
			    }]
			});   
		}else{
			$.messager.alert('赢载网-友情提示：','请选择你要受理的数据!','info');
		}
	});
	//删除
	$("#deletespreadAcceptButton").bind("click",function(){
		var rows = $('#J_spreadContent').datagrid('getSelections');
		if(rows.length>0){
			var ids = [];
			for(var i=0; i<rows.length; i++){
			    ids.push(rows[i].applyId);
			}
			$.messager.confirm('赢载网-友情提示：','你确定要删除吗?',function(r){   
			    if (r){  
					 var url= $("#path",parent.document).val()+"/admin/mall/spreadAcceptDel";
					 var data={idsInt:ids.join(',')};
					 $.post(url,data,function(rs){
						if(rs.code=1){
							var index = $('#tabs_info').tabs('getTabIndex',$('#tabs_info').tabs('getSelected'));
							$('#J_spreadContent').datagrid({ queryParams:formToJson("searchform",{name:"stateInfo",value:index==2?9:index}) });
						}else{
							$.messager.alert('赢载网-友情提示：','删除失败请重试!','error');
						}
				    });
			    }   
			});  
			
		}else{
			$.messager.alert('赢载网-友情提示：','请选择你要删除的数据!','info');
		}
		 
	});
});


//jsonObj格式：{name:"status",value:"9"}
function formToJson(id,jsonObj) {
	var arr = $("#" + id).serializeArray();
	if(typeof(jsonObj) == "object" ){
		arr.push(jsonObj);
	}
	var jsonStr = "";
	jsonStr += '{';
	for (var i = 0; i < arr.length; i++) {
		jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",';
	}
	jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
	jsonStr += '}';

	var json = JSON.parse(jsonStr);
	return json;
}
