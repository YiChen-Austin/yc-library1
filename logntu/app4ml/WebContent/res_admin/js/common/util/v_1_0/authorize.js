var jspUrl = "" ;
var jspPath ="";

$("html").ready(function(){
	
	checkAuthorize();
	
});

/**
 * 传递需要自动控制的JSP页面地址
 */
function autoAuthorize(URL,allPath) {
	jspUrl= URL;
	jspPath = allPath;
}

/**
 * 进行权限认真
 * @return
 */
function checkAuthorize(){

	
	if(jspUrl.substring(0, jspPath.length)==jspPath){
		
		jspUrl=jspUrl.substring(jspPath.length+1, jspUrl.length);	
		
	}
	
	var authorizes;
	//找到授权组件
	var allObject = $("*[authorize=true]");
	//隐藏所有组件
	$(allObject).hide();
	
	
	$.ajax({
		  type: "GET",
		  cache: false,
		  async: false,
		  url: "checkAuthorize",
		  dataType: "json",
		  data:"jspUrl="+jspUrl,
		  success: function(JSON){
		  authorizes=JSON.authorizeList;
	   	}
		});
	
	
	
	
	for ( var i = 0; i < allObject.size(); i++) {
		
		for(var j=0;j<authorizes.length;j++){
			
			if($(allObject[i]).attr("id")==authorizes[j])
			{
				$(allObject[i]).show();
				
			}
			
		}


	}

	
	
}