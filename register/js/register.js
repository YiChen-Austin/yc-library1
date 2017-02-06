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
            var pwdField = $("input[type=password]");
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

        }
    });

}

function hqyzm(){
    var tcsmrz = layer.open({
        type: 1,
        skin: 'a-gg', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        title: '输入验证码',
        shadeClose: true, //开启遮罩关闭
        content: '<div class="tcdiv "><div class="form-group sjsryzm"><label class="">验证码：</label><input type="text" class="" name="zyzm"id="syzms"/> <a href="" class=""><img src="img/yzm.png" class=""></a></div><div> <div class="inputcenter"><input type="button"  class="call-yzm" value="发送验证码"  onclick="totime()"></div></div></div>'
    });
    $("#syzms").blur(function(){
        var val = new validate({
            rules:{
                syzms:"null",

            },
            submitFun:function(){
                var user = {
                    syzms:$("#syzms").val(),

                };
            }
        })
    });
}
//判断是否是机器人，验证码

function totime(){
    var val = new validate({
        rules:{
            syzms:"null",
        },
        submitFun:function(){
            sendMessage();
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
});
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


function toSubmit(){
    var user = {
        Tel:$("#phone").val(),
        Password:$("#password").val(),
        Iphoneyzm:$("#iphoneyzm").val(),
        Iphonename:$("#iphonename").val(),
        Iphonecard:$("#iphonecard").val(),
    };
    $.ajax({
        url:'xxxx',
        data:user,
        type:'post',
        dataType:'json',
        success:function(msg){
            if(msg=='1'){
                layer.open({
                    type: 1,
                    area: ['250px', '150px'],
                    shadeClose: false, //点击遮罩关闭
                    content: '\<\div style="padding:20px;">验证通过，3秒后自动跳转\<\/div>'
                });
                function jump(count) {
                    console.log=Result;
                    window.setTimeout(function(){
                        count--;
                        if(count > 0) {

                            jump(count);
                        } else {
                            location.href="/";
                        }
                    }, 1000);
                }
                jump(3);
            }else{
                layer.open({
                    type: 1,
                    area: ['200px', '150px'],
                    shadeClose: true, //点击遮罩关闭
                    content: '\<\div style="padding:20px;">验证失败 ！！！\<\/div>'
                });
            }
        }
    })
}



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
function toyoux1(){
    var user = {
        Email:$("#email").val(),
        Password:$("#emailpassword").val(),
        Emailyzm:$("#emailyzm").val(),
        Emailname:$("#emailname").val(),
        Emailcard:$("#emailcard").val(),
    };
    $.ajax({
        url:'xxxx',
        data:user,
        type:'post',
        dataType:'json',
        success:function(msg){
            if(msg=='1'){
                layer.open({
                    type: 1,
                    area: ['250px', '150px'],
                    shadeClose: false, //点击遮罩关闭
                    content: '\<\div style="padding:20px;">验证通过，3秒后自动跳转\<\/div>'
                });
                function jump(count) {
                    console.log=Result;
                    window.setTimeout(function(){
                        count--;
                        if(count > 0) {
                            jump(count);
                        } else {
                            location.href="index.html";
                        }
                    }, 1000);
                }
                jump(3);
            }else{
                layer.open({
                    type: 1,
                    area: ['200px', '150px'],
                    shadeClose: true, //点击遮罩关闭
                    content: '\<\div style="padding:20px;">验证失败 ！！！\<\/div>'
                });
            }
        }

    })
}