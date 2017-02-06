/**
 * 常量js
 * 
 * @author 潘瑞峥
 * @date 2010-10-21
 */
(function(){
	
	/** 行合并标志 */
	var COLS_SIGN = '--';
	
	/** 列合并标志 */
	var ROWS_SIGN = '..';
	
	/** grid宽度 */
	var widthDefault = document.documentElement.clientWidth*0.84;
	
	/** grid高度 */
	var heightDefault = document.documentElement.clientHeight*0.18;
	/**每页记录数*/
	var rowNum_default = 20;
	
	/** div里grid宽度 */
	var div_widthCustom = 628;
	
	/** 百叶窗时间 */
	var blind_time = 200;
	
	/** 默认使用的css名字 */
	var defaultCss = 'buttom';
	
	/** 不可编辑时使用的css */
	var disableCss = 'buttom_disable';
	
	/** 选项卡被选中时候的class */
	var selectTagClassName = 'selectTag';
	
	/** 选项卡的name */
	var tagsName = 'tags';
	
	/** 属于选项卡内容的name */
	var tagsBoxName = 'tab_box';
	
	/** 伸缩按钮a的class */
	var titDivAClass = 'tab_tit_show';
	
	/** 伸缩div被收缩时候的class */
	var tabTitHide = 'tab_tit_hide';
	
	/** 需要隐藏的div的兄弟节点divclass */
	var hideHeadClassName = '.tab_tit_bg';
	
	/** jqGrid显示的最大列数 */
	var rowNum_max = 99999999;
	
	/** 屏幕高度需要减去的像素 */
	var cut_height = 43;
	
	/** 每个页面在body下div的主样式名称 */
	var css_div_main = 'T_mian';
	
	window["constant"] = {};
	
	window["constant"]["widthDefault"] = widthDefault;
	
	window["constant"]["heightDefault"] = heightDefault;
	
	window["constant"]["div_widthCustom"] = div_widthCustom;
	
	window["constant"]["blind_time"] = blind_time;
	
	window["constant"]["defaultCss"] = defaultCss;
	
	window["constant"]["disableCss"] = disableCss;
	
	window["constant"]["selectTagClassName"] = selectTagClassName;
	
	window["constant"]["tagsName"] = tagsName;
	
	window["constant"]["tagsBoxName"] = tagsBoxName;
	
	window["constant"]["titDivAClass"] = titDivAClass;
	
	window["constant"]["tabTitHide"] = tabTitHide;
	
	window["constant"]["hideHeadClassName"] = hideHeadClassName;
	
	window["constant"]["rowNum_max"] = rowNum_max;
	
	window["constant"]["rowNum_default"] = rowNum_default;
	
	window["constant"]["cut_height"] = cut_height;
	
	window["constant"]["css_div_main"] = css_div_main;
	
	window['constant']['COLS_SIGN'] = COLS_SIGN;
	
	window['constant']['ROWS_SIGN'] = ROWS_SIGN;
	
})();
function daysBetween(DateOne,DateTwo) 
{   
   var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-')); 
   var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1); 
   var OneYear = DateOne.substring(0,DateOne.indexOf ('-')); 
   var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-')); 
   var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1); 
   var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));    
   var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
  return Math.abs(cha); 
} 
function isNum(s){
	var regNum;
	regNum=/\d*/;
	var regFloat=new RegExp("^(\\d+)(\\.\\d+)?$");
	if(regNum.exec(s)!=null&&regNum.exec(s)!='')
	   return true;
	else if(regFloat.exec(s)!=null&&regFloat.exec(s)!='')
	   return true;
	else
	   return false;
}
