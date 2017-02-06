(function() {
	/**
	 * 所有类的基类，提供继承机制
	 */
	var initializing = false, fnTest = /xyz/.test(function() {xyz;}) ? /\b_super\b/ : /.*/;
	this.Class = function() {};
	Class.extend = function(prop) {
		var _super = this.prototype;
		initializing = true;
		var prototype = new this();
		initializing = false;
		for ( var name in prop) {
			prototype[name] = typeof prop[name] == "function" 
					&& typeof _super[name] == "function" && fnTest.test(prop[name]) ? 
				(function(name, fn) {
					return function() {
						var tmp = this._super;

						this._super = _super[name];

						var ret = fn.apply(this, arguments);
						this._super = tmp;

						return ret;
					};
				})(name, prop[name]) : prop[name];
		}
		function Class() {
			if (!initializing && this.init)
				this.init.apply(this, arguments);
		}
		Class.prototype = prototype;
		Class.prototype.constructor = Class;
		Class.extend = arguments.callee;
		return Class;
	};
})();
var validate = Class
		.extend({
			defaultCfg:{
				rules:{},
				submitFun:function(){},
				errorLabel:'<label style="color:#d85101;position: absolute;bottom: -23px;left: 5px;"></label>',
				errorFun:function(){}
			},
			init:function(cfg){				
				this.cfg = $.extend({},this.defaultCfg,cfg);
				this.flag=0;
				this.toAction(this);
				if(this.flag==0){
					for(var i in this.cfg.rules){
						$("#"+i).unbind("keyup");
					}
					this.cfg.submitFun();
				}
			},
			toAction:function(that){				
				for(var i in that.cfg.rules){
					this.toVal("#"+i,that.cfg.rules[i]);
				}
			},
			toVal:function(ele,constant){
				validateConstant[constant].test($(ele).val())?
					this.toRemoveError(ele):this.toShowError(ele,errorMsg[constant]);

			},
			toRemoveError:function(ele){
				var that = this;
				if($(ele).closest(".form-group").attr("not-allow")){
					$(ele).removeAttr("style").closest(".form-group").removeAttr("style")
							.removeAttr("not-allow");
					$(ele).next().remove();		
					$(ele).keyup(function(){
						ele = ele.replace("#","");
						that.toVal("#"+ele,that.cfg.rules[ele]);
					});							
				}				
			},
			toShowError:function(ele,message){
				var error = $(this.cfg.errorLabel).text(message);
				if(!$(ele).closest(".form-group").attr("not-allow")){
					$(ele).after(error);
					$(ele).css("border","1px solid #d85101").closest(".form-group")
							.css("color","").attr("not-allow","true");
					$(ele).keyup(function(){
						ele = ele.replace("#","");
						that.toVal("#"+ele,that.cfg.rules[ele]);
					});
				}	
				this.flag++;	
				var that = this;			
				
			}
		})

var validateConstant = {
	"sfz":/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$|^\s*$/,// 身份证
	"sfz1":/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/,// 身份证
	"numberEnglish": /^[A-Za-z0-9]+$/,// 英文和数字
	"name": /[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$|^\s*$/,// 姓名
	"name1": /[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$/,// 姓名
	"money": /(^[1-9]\d{0,9}(\.\d{1,2})?$)/,
	"mobile": /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/,// 手机号
	"tel": /^(\d{3,4}-?)?\d{7,9}$/g,// 电话
	"password": /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/,// 密码
	"password1": /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/,// 密码
	"zipCode": /^[0-9]{6}$/,// 邮政编码
	"email": /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,// 邮箱
	"null":/\S/,//空
	"number":/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+|^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/,//手机号或者邮箱号
}
var errorMsg = {
	"notEmpty" : "不能为空",
	"sfz" : "请输入身份证",
	"sfz1" : "请输入身份证",
	"rightfulString" : "请输入合法字符",// 合法字符
	"endlish" : "只能输入英文",// 纯英文
	"numberEnglish" : "只能输入英文或数字",// 英文和数字
	"name" : "请输入真实姓名",// 浮点型
	"name1" : "请输入真实姓名",// 浮点型
	"money" : "请输入合法价格",
	"chinese" : "只能输入汉字",// 纯中文
	"mobile" : "请输入正确的手机号",// 手机号
	"tel" : "请输入正确的电话号码",// 电话
	"password" : "请输入8~16位，数字、字母、字符至少包含两种。",
	"password1" : "请输入正确的密码。",
	"zipCode" : "请输入正确的QQ号",
	"email" : "请输入正确的邮箱",// 邮箱
	"positive":"请输入大于0的数字",//大于0的数字
	"id" : "请输入正确的身份证号",// 校验身份证
	"null":"不能为空",
	"number":"请输入正确手机号或者邮箱号",
}