/**
插件名：jstree的强化复选框

新增方法  ：
alone_check()		仅仅选中指定的节点
alone_uncheck()	仅仅取消选中指定节点
all_check()		全选
all_uncheck()		全不选
back_check()		反选

three_state : BOOLEAN	当为 false 的候，取消选中的父节点同样取消子节点

同时更改了 onchange 事件所对应的方法
作者：炫e小锋     QQ：251708339
time 5.21.2010
*/

var back_undetermined;
var back_checked;
var back_unchecked;

(function ($) {
	$.extend($.tree.plugins, {
		"checkbox" : {
			defaults : {
			three_state : true
			},
			get_checked : function (t) {
				if(!t) t = $.tree.focused();
				return t.container.find("a.checked").parent();
			},
			get_undeterminded : function (t) { 
				if(!t) t = $.tree.focused();
				return t.container.find("a.undetermined").parent();
			},
			get_unchecked : function (t) {
				if(!t) t = $.tree.focused();
				return t.container.find("a:not(.checked, .undetermined)").parent();
			},
			all_check : function(t){
				if(!t) t = $.tree.focused();
				var nodes =  t.container.find("a:not(.checked, .undetermined)").parent();
				nodes.each(function () {	
				$(this).children("a").removeClass("unchecked").addClass("checked");				
				});
				return true;
			},
			all_uncheck : function (t){
			if(!t) t = $.tree.focused();
			var nodes = t.container.find("a:not(.unchecked, .undetermined)").parent();
				nodes.each(function () {	
				$(this).children("a").removeClass("checked").addClass("unchecked");
				});
				return true;
			},
			back_check:function (t){
			if(!t) t = $.tree.focused();
			var checknodes = t.container.find("a.checked").parent();
			var unchecknodes = t.container.find("a:not(.checked, .undetermined)").parent();
			checknodes.each(function (){
			$(this).children("a").removeClass("checked").addClass("unchecked");
			});
			unchecknodes.each(function (){
			$(this).children("a").removeClass("unchecked").addClass("checked");
			});
			return true;
			},
			alone_check:function (n){
			if(!n) return false;
				var t = $.tree.reference(n);
				n = t.get_node(n);
				if(n.children("a").hasClass("checked")) return true;
				n.children("a").removeClass("unchecked").addClass("checked");
				return true;			
			},
			alone_uncheck:function (n){
				if(!n) return false;
				var t = $.tree.reference(n);
				n = t.get_node(n);
				if(n.children("a").hasClass("unchecked")) return true; 
				 n.children("a").removeClass("checked").addClass("unchecked");
				return true;
			},
			alone_undetermine : function (n) {
				if(!n) return false;
				var t = $.tree.reference(n);
				if(!t) return false;
				n = t.get_node(n);
				if(!n) return false;
				if(n.children("a").hasClass("undetermined")) return true; 
				if(n.children("a").hasClass("unchecked")) {n.children("a").removeClass("unchecked").addClass("undetermined"); }
				else if(n.children("a").hasClass("checked")) {n.children("a").removeClass("checked").addClass("undetermined");}
				else{
				n.children("a").addClass("undetermined");
				} 
				
				return true;
			},
			check : function (n) {
				if(!n) return false;
				var t = $.tree.reference(n);
				n = t.get_node(n);
				if(n.children("a").hasClass("checked")) return true;

				var opts = $.extend(true, {}, $.tree.plugins.checkbox.defaults, t.settings.plugins.checkbox);
				
					n.find("li").andSelf().children("a").removeClass("unchecked undetermined").addClass("checked");

					n.parents("li").each(function () { 
						if($(this).children("ul").find("a:not(.checked):eq(0)").size() > 0) {
							$(this).parents("li").andSelf().children("a").removeClass("unchecked checked").addClass("checked");
							return false;
						}
						else $(this).children("a").removeClass("unchecked undetermined").addClass("checked");
					});
				
				return true;
			},
			uncheck : function (n) {
				if(!n) return false;
				var t = $.tree.reference(n);
				n = t.get_node(n);
				if(n.children("a").hasClass("unchecked")) return true;

				var opts = $.extend(true, {}, $.tree.plugins.checkbox.defaults, t.settings.plugins.checkbox);

				if(!opts.three_state) {				
				n.find("li").andSelf().children("a").removeClass("checked").addClass("unchecked");				
				} 
				 n.children("a").removeClass("checked").addClass("unchecked");
				 
				return true;
			},
			toggle : function (n) {
				if(!n) return false;
				var t = $.tree.reference(n);
				n = t.get_node(n);
				if(n.children("a").hasClass("checked")) $.tree.plugins.checkbox.uncheck(n);
				else $.tree.plugins.checkbox.check(n);
			},

			callbacks : {
				onchange : function(n, t) {
					$.tree.plugins.checkbox.toggle(n);
				},
				beforeclose	: function(NODE,TREE_OBJ) { 
				back_undetermined = $.tree.plugins.checkbox.get_undeterminded();
				back_checked = $.tree.plugins.checkbox.get_checked();
				back_unchecked = $.tree.plugins.checkbox.get_unchecked();	
				return true ;
				},
				onclose : function(NODE,TREE_OBJ) {
				for(var i = 0 ;i < back_checked.size(); i++){
				$.tree.plugins.checkbox.alone_check(back_checked[i]);
				}
				for(var i = 0 ;i < back_unchecked.size(); i++){
				$.tree.plugins.checkbox.alone_uncheck(back_unchecked[i]);
				}
				for(var i = 0 ; i < back_undetermined.size(); i++){
				$.tree.plugins.checkbox.alone_undetermine(back_undetermined[i]);
				} }	
			}
		}
	});
})(jQuery);