//placeholder 兼容性问题
function isPlaceholder(){
    var input = document.createElement('input');
    return 'placeholder' in input;
}

if (!isPlaceholder()) {//不支持placeholder 用jquery来完成
    $(document).ready(function() {
        if(!isPlaceholder()){
            $("input").not("input[type='password']").each(//把input绑定事件 排除password框
                function(){
                    if($(this).val()=="" && $(this).attr("placeholder")!=""){
                        $(this).val($(this).attr("placeholder"));
                        $(this).focus(function(){
                            if($(this).val()==$(this).attr("placeholder")) $(this).val("");
                        });
                        $(this).blur(function(){
                            if($(this).val()=="") $(this).val($(this).attr("placeholder"));
                        });
                    }
                });
            //对password框的特殊处理1.创建一个text框 2获取焦点和失去焦点的时候切换
            var pwdField = $("#password");
            var pwdVal  = pwdField.attr('placeholder');
            pwdField.after('<input name="password" id="pwdPlaceholder" type="text" value='+pwdVal+' autocomplete="off" />');
            var pwdPlaceholder = $('#pwdPlaceholder');
            pwdPlaceholder.show();
            pwdField.hide();

            pwdPlaceholder.focus(function(){
                pwdPlaceholder.hide();
                pwdField.show();
                pwdField.focus();
            });

            pwdField.blur(function(){
                if(pwdField.val() == '') {
                    pwdPlaceholder.show();
                    pwdField.hide();
                }
            });
            //对password框的特殊处理1.创建一个text框 2获取焦点和失去焦点的时候切换
            var pwdField1 = $("#emailpassword");
            var pwdVal1  = pwdField.attr('placeholder');
            pwdField1.after('<input name="password" id="pwdPlaceholder1" type="text" value='+pwdVal+' autocomplete="off" />');
            var pwdPlaceholder1 = $('#pwdPlaceholder1');
            pwdPlaceholder1.show();
            pwdField1.hide();

            pwdPlaceholder1.focus(function(){
                pwdPlaceholder1.hide();
                pwdField1.show();
                pwdField1.focus();
            });

            pwdField1.blur(function(){
                if(pwdField1.val() == '') {
                    pwdPlaceholder1.show();
                    pwdField1.hide();
                }
            });

        }
    });

}

//判断是否是机器人，验证码
function totime(){
	var user = {
			phone:$("#phone").val(),
			code:$('#syzms').val(),
	    };
    var val = new validate({
        rules:{
            syzms:"null",
        },
        submitFun:function(){
        	$.ajax({
                url:'smsSend',
                data:user,
                type:'post',
                dataType:'json',
                success:function(msg){
                	sendMessage();
                }
            })
            
        }
    })
}
//获得时间js开始
var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
function sendMessage() {
    layer.closeAll();
    curCount = count;
    //设置button效果，开始计时
    $("#btnSendCode").attr("disabled", "true");
    $("#btnSendCode").val("输入验证码" + curCount + "秒内");
    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    //向后台发送处理数据
}
//timer处理函数
function SetRemainTime() {
    layer.closeAll();
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $("#btnSendCode").removeAttr("disabled");//启用按钮
        $("#btnSendCode").val("重新发送验证码");
    }
    else {
        curCount--;
        $("#btnSendCode").val("输入验证码" + curCount + "秒内");
    }
}


$('.lt-logindiv:gt(0)').hide();//隐藏大于0
var stb=$('.tab div');
stb.click(function(){
    $(this).addClass('active')//变成空白
        .siblings().removeClass('active');//实现后删除节点兄弟姐弟
    var stb_index=stb.index(this);//index(this)获取对象参数 获取ct的第几个
    $('.lt-logindiv').eq(stb_index).slideDown(500)//如果ct的和li相等就显示
        .siblings().slideUp(500);//隐藏掉上一个执行
});
//手机注册手机号验证
$("#phone").blur(function(){
    var val = new validate({
        rules:{
            phone:"mobile",

        },
        submitFun:function(){
            var user = {
                Tel:$("#phone").val(),

            };
        }
    })
})
//手机注册验证码验证
$("#iphoneyzm").blur(function(){
    var val = new validate({
        rules:{
            iphoneyzm:"null",

        },
        submitFun:function(){
            var user = {
                Tel:$("#iphoneyzm").val(),

            };
        }
    })
});
//手机注册密码验证
$("#password").blur(function(){
    var val = new validate({
        rules:{
            password:"password",
        },
        submitFun:function(){
            var user = {

                Password:$("#password").val(),

            };
        }
    })
});
//手机注册真实密码验证
$("#iphonename").blur(function(){
    var val = new validate({
        rules:{
            iphonename:"name1",
        },
        submitFun:function(){
            var user = {

                Password:$("#iphonename").val(),

            };
        }
    })
});
//手机注册身份证验证
$("#iphonecard").blur(function(){
    var val = new validate({
        rules:{
            iphonecard:"sfz1",
        },
        submitFun:function(){
            var user = {

                Password:$("#iphonecard").val(),

            };
        }
    })
});
//邮箱注册邮箱验证
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
});
//邮箱注册密码验证
$("#emailpassword").blur(function(){
    var val = new validate({
        rules:{
            emailpassword:"password",

        },
        submitFun:function(){
            var user = {

                Password:$("#emailpassword").val(),

            };
        }
    })
});
//邮箱注册验证码验证
$("#emailyzm").blur(function(){
    var val = new validate({
        rules:{
            emailyzm:"null",

        },
        submitFun:function(){
            var user = {
                Tel:$("#emailyzm").val(),

            };
        }
    })
});
//邮箱注册真实真实姓名验证
$("#emailname").blur(function(){
    var val = new validate({
        rules:{
            emailname:"name1",

        },
        submitFun:function(){
            var user = {
                Tel:$("#emailname").val(),

            };
        }
    })
});
//邮箱注册身份证验证
$("#emailcard").blur(function(){
    var val = new validate({
        rules:{
            emailcard:"sfz1",

        },
        submitFun:function(){
            var user = {
                Tel:$("#emailcard").val(),

            };
        }
    })
});
//验证码点击切换
var yzmimgsrc = $('.emailyzmimg').attr("src");
var yzmimgsrcnew = yzmimgsrc+"?"
function qiehaun(){
	$("#emailyzmimg").attr("src",yzmimgsrcnew+Math.random());
}
function iphoneyzm(){
	$("#iphoneyzmimg").attr("src",yzmimgsrcnew+Math.random());
}

//判断协议
var $ty = $("#tyiphone");
function toValidate(){
    if($ty.is(":checked")){
        var val = new validate({
            rules:{
                phone:"mobile",
                password:"password",
                iphoneyzm:"null",
                iphonename:"name1",
                iphonecard:"sfz1",
            },
            submitFun:function(){
                toSubmit();
            }
        })
    }else{
        layer.open({
            type: 1,
            area: ['250px', '150px'],
            shadeClose: false, //点击遮罩关闭
            content: '\<\div style="padding:20px; text-align:center;line-height: 63px;">请同意协议\<\/div>'
        });
    }
}

//手机注册
function toSubmit(){
    $("#iphonebut").submit();
}



//判断协议
var $tyemali = $("#tyemail");
function toyoux(){
    if($tyemali.is(":checked")) {
        var val = new validate({
            rules: {
                email: "email",
                emailpassword: "password",
                emailyzm: "null",
                emailname:"name1",
                emailcard:"sfz1",
            },
            submitFun: function () {
                toyoux1();
            }
        })
    }else{
        layer.open({
            type: 1,
            area: ['250px', '150px'],
            shadeClose: false, //点击遮罩关闭
            content: '\<\div style="padding:20px;text-align:center;line-height: 63px;">请同意协议\<\/div>'
        });
    }
}
//邮箱注册
function toyoux1(){
	$("#emlibut").submit();
}