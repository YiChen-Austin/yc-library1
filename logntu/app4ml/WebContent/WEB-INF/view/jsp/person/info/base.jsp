<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=projectTitle%></title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"
	type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
<link href="<%=path%>/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="../include/top.jsp" >
		<jsp:param name="tag" value="center" />
	</jsp:include>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="base" />
		</jsp:include>
		
		<div class="main-con fl">
			
			<div class="tx fl">
			<img src="<%=project%>/${session_mem_user.portrait}" id="touxiang">
					<p>
						<a href="javascript:void(0)" onclick="xgtx()">修改头像</a>
					</p>
			</div>
			<div class="zl fl">
				<div class="zl-tit">
					<h1 class="fl">基础资料</h1>
					<a class="fr" href="javascript:void(0)" onclick="bj()">编辑</a>
				</div>
				<ul>
					<li>账户：<span>${session_mem_user.userName}</span></li>
					<li>性别：<span id="gender">${session_mem_user.gender}</span></li>
					<li>生日：<span id="sf">${session_mem_user.birthday}</span></li>
					
				</ul>
				<div class="zl-tit">
					<h1 class="fl">高级资料</h1>
				</div>
				<ul>
					<li>真实姓名：<span id="realName">${session_mem_user.realNameEX}</span></li>
					<li>身份证号：<span id="">${session_mem_user.idCardEx}</span></li>
				</ul>
			</div>
		</div>
	</div>
	
		
	
	<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script> 
<script src="<%=path%>/js/validate.js"></script>
<script src="<%=path%>/js/layer.js"></script>
<script>
	/*修改头像*/
	function xgtx() {
		layer
			.open({
				type : 1,
				skin : 'a-gg', //样式类名
				closeBtn : 1, //不显示关闭按钮
				shift : 2,
				title : '修改头像',
				shadeClose : true, //开启遮罩关闭
				content : $('#formImg')
			});
		if (typeof FileReader === 'undefined') {
			$('#upfileResult').css('display', 'none')
			$("#uploadImage").on("change", function() {
				var s = $("#uploadImage").val();
				$("#spantext").text(s);
			})
		} else {
			$("#uploadImage").on(
					"change",
					function() {
						var files = !!this.files ? this.files : [];
						if (!files.length || !window.FileReader)
							return;
						if (/^image/.test(files[0].type)) {
							var reader = new FileReader();
							reader.readAsDataURL(files[0]);
							reader.onloadend = function() {
								$("#upfileResult").css("background-image",
										"url(" + this.result + ")");
							}
						}
					});
		}
	}
	function qr() {
		$("#uploadForm").ajaxSubmit({
			type : "POST",
			datatype : "json",
			async: false,  
            cache: false,  
            contentType: false,  
            processData: false, 
			url : '<%=project%>/member/uUportrait',
			success : function(returndata) {
				layer.closeAll();
				var a = jQuery.parseJSON(returndata);
				console.log(a);
				$('#touxiang').attr('src','<%=project%>'+a.url);
			},
		 	error: function (returndata) {  
		 		layer.closeAll();
                alert(returndata);  
            }
		});
		
		
		
		
	}
	/* 修改资料*/
	function bj() {
		layer
			.open({
				type : 1,
				skin : 'a-gg', //样式类名
				closeBtn : 1, //不显示关闭按钮
				shift : 2,
				title : '修改资料',
				shadeClose : true, //开启遮罩关闭
				content : '<ul class="form-horizontal register-con sj active"> <li class="form-group"> <input type="text"  placeholder="输入昵称" class="form-control" id="name"> </li><li> <label><input type="radio" value="0" name="sex" class="sex" checked>男</label><label><input type="radio" value="1" class="sex" name="sex" >女</label></li>  <li class="form-group"> <button class="btn tj fr dl" onclick="toggyx()">确认</button> </li> </ul>'
			});
		$("#name").blur(function() {
			var val = new validate({
				rules : {
					name : "name",
				},
				submitFun : function() {
					var user = {
						Name : $("#name").val(),
					};
				}
			})
		})
	}
	function toggyx() {
		var val = new validate({
			rules : {
				name : "name",
			},
			submitFun : function() {
				toggyx1();
			}
		})
	}
	function toggyx1() {
		var dengdai2 = layer.load(0, {
			shade : [ 0.1, '#fff' ]
		//0.1透明度的白色背景
		});
		var user = {
			Name : $("#name").val(),
			Sex : $(".sex:checked").val(),
		};
		$.ajax({
			url : 'xxxx',
			data : user,
			type : 'post',
			dataType : 'json',
			success : function(msg) {
				if (msg == '1') {
					layer.close(dengdai2);
					layer.closeAll();
					var tcc = layer
							.open({
								type : 1,
								time : 3000,
								area : [ '250px', '150px' ],
								shadeClose : false, //点击遮罩关闭
								content : '\<\div style="padding:20px;">修改成功,3秒后自动关闭\<\/div>'
							});
				} else {
					layer.close(dengdai2);
					layer.closeAll();
					layer
					.open({
						type : 1,
						area : [ '200px', '150px' ],
						time : 3000,
						shadeClose : true, //点击遮罩关闭
						content : '\<\div style="padding:20px;">修改失败 ！！！\<\/div>'
					});
				}
			}
		})
	}
	
	
	//性别
	var gender = $('#gender').text();
	if(gender==1){
		$("#gender").html('女');
	}else{
		$("#gender").html('男');
	}
</script>
</html>

