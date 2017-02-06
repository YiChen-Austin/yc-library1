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
	<div class="w1100 mtb20 color999">
		您当前的位置：首页 > 个人中心 > 账户安全
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="passwd" />
		</jsp:include>
		<div class="main-con fl">
			
			<div class="acc_fuc">
				<ul>
					<li class="bind_ph">
						<div class="info_box">
							<i class="icon_0"></i>
							<div class="info">
								<h3>修改密码</h3>
								<!-- <span class="suc"> <i class="icon_suc fl"></i> -->
								</span>
								<p>可用于加固账号安全，并获得快速找回密码等特有功能服务</p>
							</div>
						</div>
						<div class="act_box">
							<a href="javascript:void(0)"
								class="link flow_change_account_phone" onclick="xgPassword()">修改密码</a>
						</div>
					</li>
					<li class="bind_ph">
						<div class="info_box">
							<i class="icon_1 "></i>
							<div class="info">
								<h3>实名认证</h3>
								<span class="alerts"> <!-- <i class="icon_suc fl"> --></i>
								</span>
								<p>可用于加固账号安全，并获得快速找回密码等特有功能服务</p>
							</div>
						</div>
						<div class="act_box">
							<a href="javascript:void(0)"
								class="link flow_change_account_phone" onclick="smrz()">实名认证</a>
						</div>
					</li>
					<li class="bind_ph">
						<div class="info_box">
							<i class="icon_2"></i>
							<div class="info">
								<h3>绑定手机号</h3>
								<span class="suc"> <!-- <i class="icon_suc fl"> --></i>
								</span>
								<p>可用于加固账号安全，并获得快速找回密码等特有功能服务</p>
							</div>
						</div>
						<div class="act_box">
							<a href="<%=project%>/member/update_phone"
								class="link flow_change_account_phone" >绑定手机</a>
						</div>
					</li>
					<li class="bind_ph">
						<div class="info_box">
							<i class="icon_3"></i>
							<div class="info">
								<h3>绑定邮箱</h3>
								<span class="alerts"> <!-- <i class="icon_suc fl"> --></i>
								</span>
								<p>可用于加固账号安全，并获得快速找回密码等特有功能服务</p>
							</div>
						</div>
						<div class="act_box">
							<a href="<%=project%>/member/update_email" class="link flow_change_account_phone" >绑定邮箱</a>
						</div>
					</li>
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
//修改密码
function xgPassword() {
	layer
		.open({
			type : 1,
			skin : 'a-gg', //样式类名
			closeBtn : 1, //不显示关闭按钮
			shift : 2,
			title : '修改密码',
			shadeClose : true, //开启遮罩关闭
			content : '<ul class="form-horizontal register-con sj active"> <li class="form-group"> <input type="password"  placeholder="输入旧密码" class="form-control" id="oldpassword"> </li> <li class="form-group"> <input type="password"  placeholder="输入新密码" class="form-control" id="newpassword"></li><li> <input type="password"  placeholder="确认新密码" class="form-control" id="qrpassword"> </li>  <li class="form-group"> <button class="btn tj fr dl" onclick="toPassword()">确认</button> </li> </ul>'
		});
	$("#oldpassword").blur(function() {
		var val = new validate({
			rules : {
				oldpassword : "password",
			},
			submitFun : function() {
				var user = {
					OldPassword : $("#oldpassword").val(),
				};
			}
		})
	})
	$("#newpassword").blur(function() {
		var val = new validate({
			rules : {
				newpassword : "password"
			},
			submitFun : function() {
				var user = {
					NewPassword : $("#newpassword").val(),
				};
			}
		})
	})
	$("#qrpassword").blur(
		function() {
			var password = $('#newpassword').val();
			var password1 = $('#qrpassword').val();
			if (password != password1) {
				$("#qrpassword")
						.after(
								'<label class="s" style="color:#b91c21;width:70%;">密码不一致</label>');
				$(".s:gt(0)").remove();
				return false;
			} else {
				$(".s").remove();
			}
		})
}

function toPassword() {
	var newpassword = $('#newpassword').val();
	var qrpassword = $('#qrpassword').val();
	if (newpassword != qrpassword) {
		$("#qrpassword").after(
				'<label class="s" style="color:#b91c21;width:70%;">密码不一致</label>');
		$(".s:gt(0)").remove();
		return false;
	} else {
		$(".s").remove();
	}
	var val = new validate({
		rules : {
			oldpassword : "password",
			newpassword : "password"
		},
		submitFun : function() {
			toPassword1();
		}
	})
}

function toPassword1() {
	var dengdai1 = layer.load(0, {
		shade : [ 0.1, '#fff' ]
	//0.1透明度的白色背景
	});
	var user = {
			oldPw : $("#oldpassword").val(),
		newPw : $("#newpassword").val(),
	};
	$
			.ajax({
				url : 'updatePw',
				data : user,
				type : 'post',
				dataType : 'json',
				success : function(msg) {
					if (msg.serviceCode==0) {
						layer.close(dengdai1);
						layer.closeAll();
						layer.msg('修改成功');
					} else {
						layer.close(dengdai1);
						layer.closeAll();
						layer.msg('修改失败');
					}
				}
			})
}

/*实名认证*/
function smrz(){
	layer.msg('已经认证了');
}
/* function smrz(){
    var tcsmrz = layer.open({
        type: 1,
        skin: 'a-gg', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        title: '实名认证',
        shadeClose: true, //开启遮罩关闭
        content: '<ul class="form-horizontal register-con sj active"> <li class="form-group"> <input type="text"  placeholder="输入真实姓名" class="form-control" id="name1"> </li> <li class="form-group"> <input type="text"  placeholder="输入身份证号" class="form-control"  id="sfs1"> </li> <li class="form-group"> <button class="btn tj fr dl" onclick="toValidate()">确认</button> </li> </ul>'
    });
    $("#name1").blur(function(){
        var val = new validate({
            rules:{
                name1: "name1",
            },
            submitFun:function(){
                var user = {
                    RealName:$("#name1").val(),
                };
            }
        })
    })
    $("#sfs1").blur(function(){
        var val = new validate({
            rules:{
                sfs1: "sfz1",
            },
            submitFun:function(){
                var user = {
                    IDCard:$("#sfs1").val()
                };
            }
        })
    })
}
function toValidate() {
    var val = new validate({
        rules: {
            name1: "name1",
            sfs1: "sfz1",
        },
        submitFun: function () {
            tosmrz();
        }
    })
}
function tosmrz(){
    var dengdai = layer.load(0, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    var user = {
        RealName:$("#name1").val(),
        IDCard:$("#sfs1").val()
    };
    $.ajax({
        url:'xxxx',
        data:user,
        type:'post',
        dataType:'json',
        success:function(msg){
            if(msg=='1'){
                layer.close(dengdai);
                layer.closeAll();
                var tcc = layer.open({
                    type: 1,
                    area: ['250px', '150px'],
                    time:3000,
                    shadeClose: false, //点击遮罩关闭
                    content: '\<\div style="padding:20px;">修改成功,3秒后自动关闭\<\/div>'
                });

            }else{
                layer.close(dengdai);
                layer.closeAll();
                layer.open({
                    type: 1,
                    area: ['200px', '150px'],
                    time:3000,
                    shadeClose: true, //点击遮罩关闭
                    content: '\<\div style="padding:20px;">修改失败 ！！！\<\/div>'
                });
            }
        }
    })
} */
/*更改手机号*/
function ggsj(){
    layer.open({
        type: 1,
        skin: 'a-gg', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        title: '更改手机号',
        shadeClose: true, //开启遮罩关闭
        content: '<ul class="form-horizontal register-con sj active"> <li class="form-group"> <input type="text"  placeholder="输入手机号" class="form-control" id="phone"> </li>  <li class="form-group"> <button class="btn tj fr dl" onclick="toggsj()">确认</button> </li> </ul>'
    });
    $("#phone").blur(function(){
        var val = new validate({
            rules:{
                phone:"mobile",
            },
            submitFun:function(){
                var user = {
                	phone:$("#phone").val(),
                };
            }
        })
    })
}

function toggsj() {
    var val = new validate({
        rules: {
            phone:"mobile",
        },
        submitFun: function () {
            tosmrz1();
        }
    })
}
function tosmrz1(){
    var dengdai1 = layer.load(0, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    var user = {
    		phone:$("#phone").val(),
    };
    $.ajax({
        url:'updatePhone',
        data:user,
        type:'post',
        dataType:'json',
        success:function(msg){
        	if(msg.serviceCode==0){
        		layer.closeAll();
        		layer.msg('修改成功');
        	}else{
        		layer.closeAll();
        		layer.msg('修改失败');
        	} 
        	
        }
    })
}
/* 更改邮箱*/
function ggyx(){
    layer.open({
        type: 1,
        skin: 'a-gg', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        title: '更改邮箱',
        shadeClose: true, //开启遮罩关闭
        content: '<ul class="form-horizontal register-con sj active"> <li class="form-group"> <input type="text"  placeholder="输入邮箱" class="form-control" id="email"> </li>  <li class="form-group"> <button class="btn tj fr dl" onclick="toggyx()">确认</button> </li> </ul>'
    });
    $("#email").blur(function(){
        var val = new validate({
            rules:{
                email:"email",
            },
            submitFun:function(){
                var user = {
                    Email:$("#email").val(),
                };
            }
        })
    })
}
function toggyx() {
    var val = new validate({
        rules: {
            email:"email",
        },
        submitFun: function () {
            toggyx1();
        }
    })
}
function toggyx1(){

    var user = {
    		email:$("#email").val(),
    };
    $.ajax({
        url:'updateEmail',
        data:user,
        type:'post',
        dataType:'json',
        success:function(msg){
            
        }
    })
}
</script>
</html>

