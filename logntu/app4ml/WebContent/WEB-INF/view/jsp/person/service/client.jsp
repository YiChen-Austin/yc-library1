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
		<jsp:param name="tag" value="client" />
	</jsp:include>
	<div class="w1100 mtb20 color999">
		您当前的位置：首页 > 个人中心 > 联系客服
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="client" />
		</jsp:include>
		<div class="main-con fl">
		
			<ul class="kf-con">
				<li><img src="<%=path%>/images/service-email.png">
						<div>
							<span>15053476@qq.com</span>
							<p>快速回复，为您解决遇见的问题</p>
							<p>周一至周日 全天服务</p>
						</div></li>
				<li><img src="<%=path%>/images/service-qq.png">
						<div>
							<span>15053476</span>
							<p>在线对话，为您解决遇见的问题</p>
							<p>周一至周日 全天服务</p>
							<a href="tencent://message/?uin=123456789&在线咨询&Menu=yes" class="ljzx">立即咨询</a>
						</div></li>
				<li><img src="<%=path%>/images/service-cellph.png">
						<div>
							<span>400-512-1450</span>
							<p>周一至周日 8:00至18:00</p>
							<p>仅收市话费</p>
						</div></li>
			</ul>
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
					content : '<ul class="form-horizontal register-con sj active"> <li class="form-group1"><span id="spantext"></span> <div id="upfileResult"></div></li>  <li class="form-group"><input type="file" name="file" id="uploadImage"class="fileinput fileinputs" style="top:249px; height: 42px"/><label class="feedback-sca feedback-scas">选择上传图片</label> <button class="btn tj fr dl qr" onclick="qr()" id="ss">确认</button> </li> </ul>'
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
		var dengdai2 = layer.load(0, {
			shade : [ 0.1, '#fff' ]
		//0.1透明度的白色背景
		});
		var user = {
			img : $("#uploadImage").val(),
		};
		$
				.ajax({
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
		$
				.ajax({
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
</script>
</html>

