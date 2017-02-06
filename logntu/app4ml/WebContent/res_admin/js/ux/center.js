$(function(){  
	var contextPath = $("#contextPath").val();
  $.fn.EasyWidgets({

    behaviour : {
      useCookies : false
    },
    i18n : {
      editText : '编辑',
      closeText : '<img src="' + contextPath+'/res_admin/images/desktop/close.gif" border= "0" alt="关闭"/>',
      closeTitle : '关闭',
      extendText : '<img src="' + contextPath+'/res_admin/images/desktop/shrink.gif" border= "0" alt="展开"/>',
      extendTitle : '展开',
      collapseText : '<img src="' + contextPath+'/res_admin/images/desktop/expansion.gif" border= "0" alt="收缩"/>',
      collapseTitle : '收缩',
      cancelEditText : '取消'
    },


    callbacks : {

      onClose : function(){
      },

      onEnable : function(){
      },

      onExtend : function(){
      },

      onDisable : function(){
      },

      onDragStop : function(){
      },

      onCollapse : function(){
      },

      onAddQuery : function(){
        return true;
      },

      onEditQuery : function(){
        return true;
      },

      onShowQuery : function(){
        return true;
      },

      onHideQuery : function(){
        return true;
      },

      onCloseQuery : function(xlt){
    	  if(confirm("您确定要关闭桌面的"+xlt.parent().parent().children().attr("innerHTML") +"模块区域?","确定","删除")){
    		  var selfDesktopDenfendId = xlt.parent().parent().parent().attr('id');
        	  $.post($("#path",parent.document).val()+"/admin/"+"deleteSelfDesktopDefendById?selfDesktopDenfendId=" + selfDesktopDenfendId);
        	  return true;
    	  }
    	  return false;
      },

      onCancelEdit : function(){
      },

      onEnableQuery : function(){
        return true;
      },

      onExtendQuery : function(){
        return true;
      },

      onDisableQuery : function(){
        return true;
      },

      onCollapseQuery : function(){
        return true;
      },

      onCancelEditQuery : function(){
        return true;
      },

      onChangePositions : function(str){
    	$.ajax({
  	        type: "post",
  	        dataType: "json",
  	        url: $("#path",parent.document).val()+"/admin/"+"setDesktopPositionds?placeId=" + str
  	    });
    	return true;
      },
      onRefreshPositions : function(str){
      }
    }

  });

  // Some Ajax progress stuff for Widgets on demand
  
  $('#ajax-progress').ajaxStart(function(){
    $(this).show();
  }).ajaxStop(function(){
    $(this).hide();
  });
  
  //Google搜索
  $("#googleLinkId").parent().attr("innerHTML", "<iframe id='mygoogle' src='" + contextPath + "/page/desktop/google' frameborder='0' style='width:410px;height:190px;align:center;'/>");
  //天气预报
  $("#weatherId").parent().attr("innerHTML", "<iframe id='myweather' src='" + contextPath + "/page/desktop/weather' frameborder='0' style='width:410px;height:210px;align:center;'/>");
  //桌面代办事项
  jQuery("#announceId").jqGrid(
		{
			url : $("#path",parent.document).val()+"/page/"+'findDesktopMessages',
			datatype : "json",
			colNames : [ 'ID', 'href','发送人', '发送时间', '内容' ],
			colModel : [ {
				name : 'id',
				index : 'id',
				width : 30,
				editable : true,
				edittype : "text",
				sortable : false,
				hidden : true

			},			{
				name : 'href',
				index : 'href',
				width : 30,
				editable : true,
				edittype : "text",
				sortable : false,
				hidden : true

			},{
				name : 'sendName',
				index : 'sendName',
				width : 80,
				sortable : false
			}, {
				name : 'edate',
				index : 'edate',
				width : 80,
				sortable : false
			}, {name : 'content',
				index : 'content',
				width : 200,
				sortable : false
			} ],
			loadComplete : function(xhr) {
			  	var viewGrid = $("#gview_announceId").children("div")[2];
			    var contentDiv = $(viewGrid).children("div");
			    var newContent ='<marquee height="170" scrollamount="3" onmouseover="stop();" onmouseout="start();" direction="down" >' 
			    	+ contentDiv.attr("innerHTML") + "</marquee>";
			    contentDiv.attr("innerHTML",newContent);
				return xhr;
			},
			height : 180,
			width : 410,
			afterInsertRow: function(row_id){
				var href = $('#announceId').jqGrid().getCell(row_id, 'href');
				var content = $('#announceId').jqGrid().getCell(row_id, 'content');
				var element_btn = "<a href='#' onclick=myAnnounce('" + href +"','" +row_id +"')>" + content + "</a>";
				$('#announceId').jqGrid().setCell(row_id, 'content', element_btn);
			},
			jsonReader: {
		        root: "messageBeans",
		        repeatitems: false
	    	}
		});  
     //桌面公告列表
  	jQuery("#informationId").jqGrid( {
  		url : $("#path",parent.document).val()+"/page/"+'findDesktopNews',
		datatype : "json",
		colNames : [ 'ID', '信息标题', '发布人', '发布部门','发布日期','阅读'],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 30,
			editable : true,
			edittype : "text",
			sortable : false,
			hidden : true

		}, {
			name : 'title',
			index : 'title',
			align : 'left',
			width : 100,
			sortable : false
		}, {
			name : 'publishAuthorName',
			index : 'publishAuthorName',
			align : 'left',
			width : 100,
			sortable : false
		},{
			name : 'publishDepartmentName',
			index : 'publishDepartmentName',
			align : 'left',
			width : 100,
			sortable : false
		}, {
			name : 'publishTime',
			index : 'publishTime',
			align : 'left',
			width : 100,
			sortable : false
		}, {
			name : 'readCount',
			index : 'readCount',
			align : 'left',
			width : 100,
			sortable : false
		} ],
//		loadComplete : function(xhr) {
//		  	var viewGrid = $("#gview_informationId").children("div")[2];
//		    var contentDiv = $(viewGrid).children("div");
//		    var newContent ='<marquee height="170" scrollamount="3" onmouseover="stop();" onmouseout="start();" direction="up" >' 
//		    	+ contentDiv.attr("innerHTML") + "</marquee>";
//		    contentDiv.attr("innerHTML",newContent);
//			return xhr;
//		},
		height : 180,
		width : 410,
		jsonReader: {
	        root: "commonArticleBeans",
	        repeatitems: false
    	}
		
	});
});
function myAnnounce(href,messageId){
	 $.ajax({
	        type: "POST",
	        dataType: "json",
	        url: "readMsg?messageBean.id=" + messageId,
	        success: function(data){
		 	jQuery("#announceId")
			.jqGrid(
					'setGridParam',
					{
						url : $("#path",parent.document).val()+"/admin/"+"findDesktopMessages",
						page : 1
					}).trigger(
					"reloadGrid");
		 	if (data.result == true && href!="null" && href!=''){
//		        	$("#iframeContent").attr('src',contextPath+"/" +href);
		        	window.location=contextPath+"/" +href ;
		        }
		    }
	    });
}
/**
 * 快速入门
 * 
 * @return
 */
function doqurick(url) {
	//var url = contextPath + url_; // 转向网页的地址;
	var name = '快速入门'; // 网页名称，可为空;
	var iWidth = '600'; // 弹出窗口的宽度;
	var iHeight = '500'; // 弹出窗口的高度;
	// 获得窗口的垂直位置
	var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
	// 获得窗口的水平位置
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	window
	.open(
			url,
			name,
			'height='
			+ iHeight
			+ ',innerHeight='
			+ iHeight
			+ ',width='
			+ iWidth
			+ ',innerWidth='
			+ iWidth
			+ ',top='
			+ iTop
			+ ',left='
			+ iLeft
			+ ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no,z-look=yes');
}
