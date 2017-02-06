
$(function() {
	var combogridOption={
			panelWidth:180, 
    	    idField:'regionId',
    	    multiple:false,
    	    textField:'regionName',   
    	    columns:[[   
    	        {field:'regionId',title:'编号',width:60},   
    	        {field:'regionName',title:'名称',width:100}
    	    ]]
	};
	var typeOption={
			panelWidth:180, 
    	    idField:'cateId',
    	    multiple:false,
    	    textField:'cateName',   
    	    columns:[[   
    	        {field:'cateId',title:'编号',width:60},   
    	        {field:'cateName',title:'类别名称',width:100}
    	    ]]
	};
	var option={
			url : $("#path",parent.document).val()+"/admin/mall/spreadIndexSearch",
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
			idField:"spreadId",
			columns : [[{
				field : "spreadId",
				checkbox:true,
				hidden:true
			},{
				field : "storeName",
				title : "店铺名称",
				width :"120px",
				align : 'left'
			}, {
				field : "distance",
				title : "推广距离",
				width :"80px",
				formatter:function(value,row,index){  
	                 if(value>0){
	                	 return value+"米";
	                 }
	                 return "无";
	            },
				align : 'left'
			},  {
				field : "cateMId",
				title : "推广大类",
				align : 'left'
			}  , {
				field : "cateSIds",
				title : "推广小类",
				width :"120px",
				align : 'left'
			} , {
				field : "city",
				title : "推广城市",
				width :"80px",
				align : 'left'
			}, {
				field : "zone",
				title : "推广区域",
				width :"80px",
				align : 'left'
			}, {
				field : "region",
				title : "推广商圈",
				width :"80px",
				align : 'left'
			}, {
				field : "openTime",
				title : "开始时间",
				width :"145px",
				formatter:function(value,row,index){  
	                 var unixTimestamp = new Date(value);  
	                 return unixTimestamp.toLocaleString();  
	            },
				align : 'left'
			}, {
				field : "endTime",
				title : "结束时间",
				width :"145px",
				formatter:function(value,row,index){  
	                 var unixTimestamp = new Date(value);  
	                 return unixTimestamp.toLocaleString();  
	            },
				align : 'left'
			},{
				field : "price",
				title : "推广费用",
				width :"80px",
				formatter:function(value,row,index){  
	                 if(value>0){
	                	 return value+"元";
	                 }
	                 return "无";
	            },
				align : 'left'
			} , {
				field : "applyName",
				title : "处理人",
				width :"80px",
				align : 'left'
			}, {
				field : "createTime",
				title : "处理时间",
				width :"145px",
				formatter:function(value,row,index){  
	                 var unixTimestamp = new Date(value);  
	                 return unixTimestamp.toLocaleString();  
	            },
				align : 'left'
			}]]
	};
	//选项卡
	$('#tabs_info').tabs({
		onSelect:function(title,index){
			$('#searchform').form('clear');
			$("#J_spreadContent").datagrid($.extend(option,{
				queryParams:formToJson("searchform",{name:"status",value:index})
			}));
			   $('div.datagrid-toolbar td').eq(0).css({display:index==0?"":"none"});
			   $('div.datagrid-toolbar td').eq(1).css({display:index==0?"":"none"});
		}
		
	});	
	//搜索
	$("#btnSearch").bind("click",function(){
		var index = $('#tabs_info').tabs('getTabIndex',$('#tabs_info').tabs('getSelected'));
		$('#J_spreadContent').datagrid({ queryParams:formToJson("searchform",{name:"status",value:index}) });
	});
	//添加
	$("#savespreadIndexButton").bind("click",function(){
			$("#d_add").dialog({   
			    title: '新增推广店铺', 
			    width:"600px",
			    modal: true,
			    buttons:[{
			    	text:'保存',
			    	iconCls:"icon-disk",
					handler:function(){
						if($("#fmAdd").form('validate')){
							var url= $("#path",parent.document).val()+"/admin/mall/spreadIndexAdd";
							$.post(url,$("#fmAdd").serialize(),function(rs){
								if(rs.code=1){
									$('#d_add').dialog('close');
									//更新数据
									var index = $('#tabs_info').tabs('getTabIndex',$('#tabs_info').tabs('getSelected'));
									$('#J_spreadContent').datagrid("load",{ queryParams:formToJson("searchform",{name:"status",value:index}) });
								}else{
									$.messager.alert('赢载网-友情提示：','删除失败请重试!','error');
								}
						   },"json");
						}
					}
			    }]
			}); 
	});
	//修改
	$("#cateSIdsControlSEdit").combogrid($.extend(typeOption,{ multiple:true}));
	 $("#regionControlEdit").combogrid(combogridOption);
	$("#updatespreadIndexButton").bind("click",function(){
		var rows = $('#J_spreadContent').datagrid('getSelections');
		if(rows.length>0){
			if(rows.length>1){
				$.messager.alert('赢载网-友情提示：','请选择一条数据!','info');
				return;
			}
			var row = $('#J_spreadContent').datagrid('getSelected');
			  $.ajax({
		            type: "post",
		            dataType: "json",
		            url: $("#path",parent.document).val()+"/admin/mall/spreadIndexInfo",
		            data:{"spreadId":row.spreadId},
		            success: function(rs){
						   if(rs.serverCode==1){
							    //加载区域
								$("#zoneControlEdit").combogrid($.extend(combogridOption,{ 
									value:rs.data.zoneId==0?"":rs.data.zoneId,
									url:$("#path",parent.document).val()+"/admin/mall/findCityByProvince",
									onBeforeLoad : function(p) {
											p.provinceId =rs.data.cityId;
									},
									onSelect : function(newValue,oldValue) {
										$("#regionControlEdit").combogrid({ 
											 value:"",
								    		 url:$("#path",parent.document).val()+"/admin/mall/findCityByProvince",
								    		 onBeforeLoad : function(p) {
													p.provinceId =$("#zoneControlEdit").combogrid('getValue');
												}
								    	});
									}
								}));
								if(rs.data.zoneId>0){
								     //加载商圈
										$("#regionControlEdit").combogrid({ 
											value:(rs.data.regionId==null||rs.data.regionId==0)?"":rs.data.regionId,
											url:$("#path",parent.document).val()+"/admin/mall/findCityByProvince",
											 onBeforeLoad : function(p) {
													p.provinceId =rs.data.zoneId;
											}
										});
							       }else{
							    	   $("#regionControlEdit").combogrid('setValue',"");
							       }
								//大类
								$("#cateMIdControlEdit").combogrid($.extend(typeOption,{ 
									 multiple:false,
									 value:rs.data.cateMId==0?"":rs.data.cateMId,
									url:$("#path",parent.document).val()+"/admin/mall/findTypeParent",
									onSelect : function(newValue,oldValue) {
								    	$("#cateSIdsControlSEdit").combogrid({ 
								    		  url:$("#path",parent.document).val()+"/admin/mall/findTypeByParent",
								    		  onBeforeLoad : function(p) {
													p.pId =$("#cateMIdControlEdit").combogrid('getValue');
											}
								    	});
								   }
								}));
								//小类
								if(rs.data.cateMId>0){
									$("#cateSIdsControlSEdit").combogrid({ 
							    		  url:$("#path",parent.document).val()+"/admin/mall/findTypeByParent",
							    		  onBeforeLoad : function(p) {
												p.pId =rs.data.cateMId;
										}
							    	});
								}
								if(rs.data.cateSIds!=null&&rs.data.cateSIds!=""){
									 $('#cateSIdsControlSEdit').combogrid('setValues',rs.data.cateSIds.replace(/(^,*)|(,*$)/g, "").split(","));
								}else{
										 $('#cateSIdsControlSEdit').combogrid('setValue',"");
								}
								//初始化数据
								$('#fmEdit').form('load',{
									 storeId:rs.data.storeId,
				                     storeName:rs.data.storeName,
				                     descriptionEx:rs.data.description,
				                     address:rs.data.address,
				                     openTime:rs.data.openTime,
				                     cityInfo:row.city,
				                     spreadId:row.spreadId,
				                     cityId:rs.data.cityId,
				                     endTime:rs.data.endTime,
				                     distance:rs.data.distance,
				                     price:rs.data.price
			                     }); 
								
								$("#d_edit").css("visibility","visible");
								$("#d_edit").dialog({   
								    title: '修改推广店铺',  
								    modal: true,
								    width:"600px",
								    buttons:[{
								    	text:'保存',
								    	iconCls:"icon-disk",
										handler:function(){
											if($("#fmEdit").form('validate')){
												var url= $("#path",parent.document).val()+"/admin/mall/spreadIndexEdit";
												$.post(url,$("#fmEdit").serialize(),function(rs){
													if(rs.code=1){
														$('#d_edit').dialog('close');
														var index = $('#tabs_info').tabs('getTabIndex',$('#tabs_info').tabs('getSelected'));
														$('#J_spreadContent').datagrid("load",{ queryParams:formToJson("searchform",{name:"status",value:index}) });
													}else{
														$.messager.alert('赢载网-友情提示：','删除失败请重试!','error');
													}
											   });
											}
										}
								    }]
								}); 
							   
						   }else{
							   $.messager.alert('赢载网-友情提示：','该店铺不存在!','info'); 
						   }
		            }
			   });
		}else{
			$.messager.alert('赢载网-友情提示：','请选择你要修改的数据!','info');
		}
	});
	//删除
	$("#deletespreadIndexButton").bind("click",function(){
		var rows = $('#J_spreadContent').datagrid('getSelections');
		if(rows.length>0){
			var ids = [];
			for(var i=0; i<rows.length; i++){
			    ids.push(rows[i].spreadId);
			}
			$.messager.confirm('赢载网-友情提示：','你确定要删除吗?',function(r){   
			    if (r){  
					 var url= $("#path",parent.document).val()+"/admin/mall/spreadIndexDel";
					 var data={idsInt:ids.join(',')};
					 $.post(url,data,function(rs){
						if(rs.code=1){
							var index = $('#tabs_info').tabs('getTabIndex',$('#tabs_info').tabs('getSelected'));
							$('#J_spreadContent').datagrid({ queryParams:formToJson("searchform",{name:"status",value:index}) });
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
	
	$("#btnStoreSearch").bind("click",function(){
		   var storeId=$("#storeId").val();
		   if(storeId!=""&&storeId>0){
			   $.ajax({
		            type: "post",
		            dataType: "json",
		            url: $("#path",parent.document).val()+"/admin/mall/spreadIndexStore",
		            data:{"storeId":storeId},
		            success: function(rs){
						   if(rs.serverCode==1){
							   $('#fmAdd').form('load',{storeName:rs.data.storeName,descriptionEx:rs.data.descriptionEx,address:rs.data.address});
						   }else{
							   $.messager.alert('赢载网-友情提示：','该店铺不存在!','info'); 
						   }
		            }
			   });
		   }else{
			   $.messager.alert('赢载网-友情提示：','请输入店铺编号!','info');
		   }
	});
	
	//城市切换
	$("#cityIdContorl").combogrid(combogridOption);
	$("#zoneIdControl").combogrid(combogridOption);
	$("#regionIdControl").combogrid(combogridOption);
	$("#provinceContorl").combogrid($.extend(combogridOption,{//省份
		    url:$("#path",parent.document).val()+"/admin/mall/findProvince",
		    onSelect : function(newValue,oldValue) {
			    	$("#cityIdContorl").combogrid({ 
			    		 url:$("#path",parent.document).val()+"/admin/mall/findCityByProvince",
			    		 onBeforeLoad : function(p) {
								p.provinceId =$("#provinceContorl").combogrid('getValue');
							}
			    	});
			}
	}));  
	$("#cityIdContorl").combogrid({//城市
	    onSelect : function(newValue,oldValue) {
		    	$("#zoneIdControl").combogrid({ 
		    		 url:$("#path",parent.document).val()+"/admin/mall/findCityByProvince",
		    		 onBeforeLoad : function(p) {
							p.provinceId =$("#cityIdContorl").combogrid('getValue');
						}
		    	});
		}
	}); 
	$("#zoneIdControl").combogrid({//区域
	    onSelect : function(newValue,oldValue) {
		    	$("#regionIdControl").combogrid({ 
		    		 url:$("#path",parent.document).val()+"/admin/mall/findCityByProvince",
		    		 onBeforeLoad : function(p) {
							p.provinceId =$("#zoneIdControl").combogrid('getValue');
						}
		    	});
		}
	});  
	
    //大类小类
	$("#cateSIdsControl").combogrid(typeOption);
	$("#cateMIdControl").combogrid($.extend(typeOption,{
		url:$("#path",parent.document).val()+"/admin/mall/findTypeParent",
		onSelect : function(newValue,oldValue) {
	    	$("#cateSIdsControl").combogrid({ 
	    		  multiple:true,
	    		  url:$("#path",parent.document).val()+"/admin/mall/findTypeByParent",
	    		  onBeforeLoad : function(p) {
						p.pId =$("#cateMIdControl").combogrid('getValue');
				}
	    	});
	   }
    }));
	
  
	
	
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
