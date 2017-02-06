(function($){
    var ajax=$.ajax;
    $.ajax=function(s){
        var old=s.error;
        var errHeader=s.errorHeader||"Error-Json";
        s.error=function(xhr,status,err){
            var errMsg = window["eval"]("(" + xhr.getResponseHeader(errHeader) + ")");
            if(errMsg.reason=='notLogin'){
				alert("你还未登录,请登录!");
				window.parent.location.href=errMsg.content+"/page/login.jsp";
			}else if(errMsg.reason=='insideError'){
				window.location.href = errMsg.content+"/page/exitLogin.action";
			}else if(errMsg.reason=='kickRepeatLogin'){
				alert("对不起该账号已经在其他地方登录,你已被踢出!");
				window.parent.location.href=errMsg.content+"/page/exitLogin.action";
			}
           old(xhr,status,err||errMsg);
        };
       ajax(s);
    };
})(jQuery); 