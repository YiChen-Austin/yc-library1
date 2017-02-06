
//判断账号是否存在
function payID() {
    $('#payidlab').css('display', 'none');
    var _data = {
        "userName": $("#payid").val(),
    };
    $.ajax({
        url: src + '/pay/validUn',
        data: _data,
        type: 'post',
        dataType: 'json',
        success: function (msg) {
            if (msg.serviceCode == '0') {
                $('#payidlab').css('display', 'none');
            } else {
                $('#payidlab').css('display', 'inline-block');
                $('#payidlab span').text('没有此账号，请注册！！！');
            }
        }
    })
}
var src = $('#src').text();
(function () {
    load_server_data();
    load_balance_data();
})();
//加载服务器数据
function load_server_data() {
        var serverId = '',
            prClass = '';
        if ($(this).attr("server_id")) {
            serverId = $(this).attr("server_id");
            prClass = $(this).attr("pre_class");
        }
        //删除所有子元素
        if (serverId) {
            $("#select_zone .s_" + serverId).each(function (i, item) {
                $(item).remove();
            });
            $(this).prevAll().each(function (i, item) {
                $("#select_zone .s_" + $(item).attr("server_id")).each(function (i, xitem) {
                    $(xitem).remove();
                });
            });
            $(this).nextAll().each(function (i, item) {
                $("#select_zone .s_" + $(item).attr("server_id")).each(function (i, xitem) {
                    $(xitem).remove();
                });
            });
        }
        _data = {
            "serverId": serverId,
            "userName": $("#payid").val(),
        };
        $.ajax({
            url: src + '/pay/serverZone',
            data: _data,
            type: 'post',
            dataType: 'json',
            success: function (json) {
                if (json) {
                    if (!serverId) {
                        serverId = "00";
                    }
                    var isNext = false;
                    var _html = '';
                    _html += '<div class="nice-select fl s_' + serverId + ' ' + prClass + '" id="u_' + serverId + '" name="nice-select">';
                    var v_html = '<input type="text" value="请选择" readonly>';
                    var ul_html = '<ul>';
                    $.each(json, function (i, item) {
                    	ul_html += '<li server_id="' + item.serverId + '" pre_class="' + prClass + ' s_' + serverId + '" data-id="' + item.serverId + '" data-value=' + i + '>' + item.serverName + '</li>';
                        if (parseInt(item.subLayer) > 0) {
                            isNext = true;
                        }
                    });
                    ul_html += '</ul>';
                    var i_html = '';
                    if (isNext) {
                    	i_html += '<input type="text" value="请选择" readonly>';
                    }else{
                    	i_html += '<input type="text" value="请选择" id="server_code" data_id="" readonly>';
                    }
                    _html+=i_html;
                    _html+=ul_html;
                    _html += '</div>';
                    $("#select_zone").append(_html);
                    selectVal();
                    $('.ljzf').attr('disabled', "true");
                    $('.ljzf').addClass('bgh');
                    if (isNext) {
                        $("#u_" + serverId + " li").bind("click", load_server_data);
                    } else {
                        $("#u_" + serverId + " li").bind("click", load_role_data);
                    }
                }
            }
        })
    }
//加载余额支付
function load_balance_data() {
        $.ajax({
            url: src + '/pay/checkBalance',
            type: 'post',
            dataType: 'json',
            success: function (json) {
            	
                if (json&&json.serviceCode=='0'&&json.desposit) {
                	$("#lb_label_nl").attr("data-b",json.desposit.balance);
                	$("#lb_label_nl").css("display","inline-block");
                }
            }
        })
    }
//加载角色名字
function load_role_data() {
    $('#xzdq').css('display', 'none');
    var serverId = '',
        prClass = '';
    if ($(this).attr("server_id")) {
        serverId = $(this).attr("server_id");
        prClass = $(this).attr("pre_class");
    }
    //删除所有子元素
    if (serverId) {
        $("#select_zone .s_" + serverId).each(function (i, item) {
            $(item).remove();
        });
        $(this).prevAll().each(function (i, item) {
            $("#select_zone .s_" + $(item).attr("server_id")).each(function (i, xitem) {
                $(xitem).remove();
            });
        });
        $(this).nextAll().each(function (i, item) {
            $("#select_zone .s_" + $(item).attr("server_id")).each(function (i, xitem) {
                $(xitem).remove();
            });
        });
    }
    _data = {
        "serverId": serverId
    };
    $('#xzdq').css('display', 'none');
    $.ajax({
        url: src + '/pay/serverRole',
        data: _data,
        type: 'post',
        dataType: 'json',
        success: function (json) {
            var payIdval = $("#payid").val();
            if (payIdval == '') {
                layer.msg('请输入账号');

            } else {
                if (json) {
                    var _html = '';
                    _html += '<div class="nice-select fl" name="nice-select">';
                    _html += '<input type="text" value="请选择角色" readonly id="pay_Name" data_id="">';
                    _html += '<ul>';
                    $.each(json, function (i, item) {
                        _html += '<li onclick="_pay_Name()" data-id="' + item.roleId + '" pre_class="' + prClass + ' s_' + serverId + '"  value="' + item.serverId + '" data-value=' + i + '>' + item.roleName + '</li>';
                        
                    });
                    _html += '</ul></div>';
                    $("#select_zone").append(_html);
                    $('#xzdq').css('display', 'none');
                    selectVal();
                } else {
                    $('#xzdq').css('display', 'inline-block');
                    $('#xzdq span').text('没有此角色');
                }
            }
        }
    })
}
//什么时候能点击
function _pay_Name() {
	$('.ljzf').removeAttr("disabled");
    $('.ljzf').removeClass('bgh');
}
//支付宝支付
function alipay() {
    $('#input_num').attr("num", $('#input_num').val());
    var userName = $("#payid").val();
    var serverCode = $("#server_code").attr("data_id");
    var roleId = $('#pay_Name').attr("data_id");
    var num = $('.jediv .active').attr("num");
    var yf = $("#yf").text();
    $('#userName').val(userName);
    $('#serverCode').val(serverCode);
    $('#roleCode').val(roleId);
    $('#goodsAmount').val(num);
    $('#orderAmount').val(yf);
    if($("#tyiphone").is(":checked")){	
		if(yf<10){
			layer.msg('请选择充值数量');
		}else{
			$("#pay_action").attr("target","_blank");
			$("#pay_action").attr("action",$("#src").text()+"/pay/alipay");
			layer.open({
		        type: 1,
		        skin: 'a-gg', //样式类名
		        closeBtn: 1, //不显示关闭按钮
		        move: false,
		        cancel: function(index){window.location.reload()} ,
		        title: '登陆支付宝支付',
		        shadeClose: true, //开启遮罩关闭
		        content: '<div>请您在新打开的支付宝页面完成支付，支付完成前请不要关闭该窗口</div><div><a href="http://member.cqlongtu.com">已完成支付</a><a href="">支付时遇见问题</a></div>'
		    });
			$("#pay_action").submit();
		}
	}else {
    	layer.msg('请同意协议');
    }
}
//龙币支付
function lbpay(){
	$('#input_num').attr("num", $('#input_num').val());
    var userName = $("#payid").val();
    var serverCode = $("#server_code").attr("data_id");
    var roleId = $('#pay_Name').attr("data_id");
    var num = $('.jediv .active').attr("num");
    var yf = $("#yf").text();
    $('#userName').val(userName);
    $('#serverCode').val(serverCode);
    $('#roleCode').val(roleId);
    $('#goodsAmount').val(num);
    $('#orderAmount').val(yf);
    if($("#tyiphone").is(":checked")){	
		if(yf<10){
			layer.msg('请选择充值数量');
		}else{
			$("#pay_action").attr("action",$("#src").text()+"/pay/balancePay");
			$("#pay_action").attr("target","_self");
			$("#pay_action").submit();
		}
	}else {
    	layer.msg('请同意协议');
    }
}
//微信支付
function wxpay() {
    $('#input_num').attr("num", $('#input_num').val());
    var userName = $("#payid").val();
    var serverCode = $("#server_code").attr("data_id");
    var roleId = $('#pay_Name').attr("data_id");
    var num = $('.jediv .active').attr("num");
    var yf = $("#yf").text();
    $('#userName').val(userName);
    $('#serverCode').val(serverCode);
    $('#roleCode').val(roleId);
    $('#goodsAmount').val(num);
    $('#orderAmount').val(yf);
    if($("#tyiphone").is(":checked")){	
		if(yf<10){
			layer.msg('请选择充值数量');
		}else{
			$("#pay_action").attr("action",$("#src").text()+"/pay/wxpay");
			$("#pay_action").attr("target","_self");
			$("#pay_action").submit();
		}
	}else {
    	layer.msg('请同意协议');
    }
}
//银联支付
function unionpay() {
    $('#input_num').attr("num", $('#input_num').val());
    var userName = $("#payid").val();
    var serverCode = $("#server_code").attr("data_id");
    var roleId = $('#pay_Name').attr("data_id");
    var num = $('.jediv .active').attr("num");
    var yf = $("#yf").text();
    $('#userName').val(userName);
    $('#serverCode').val(serverCode);
    $('#roleCode').val(roleId);
    $('#goodsAmount').val(num);
    $('#orderAmount').val(yf);
    if($("#tyiphone").is(":checked")){	
		if(yf<10){
			layer.msg('请选择充值数量');
		}else{
			$("#pay_action").attr("target","_blank");
			$("#pay_action").attr("action",$("#src").text()+"/pay/unionpay");
			layer.open({
		        type: 1,
		        skin: 'a-gg', //样式类名
		        closeBtn: 1, //不显示关闭按钮
		        move: false,
		        cancel: function(index){window.location.reload()} ,
		        title: '登陆银联支付',
		        shadeClose: true, //开启遮罩关闭
		        content: '<div>请您在新打开的银联支付页面完成支付，支付完成前请不要关闭该窗口</div><div><a href="http://member.cqlongtu.com">已完成支付</a><a href="">支付时遇见问题</a></div>'
		    });
			$("#pay_action").submit();
		}
	}else {
    	layer.msg('请同意协议');
    }
}
//下拉框
function selectVal() {
        $('[name="nice-select"]').click(function (e) {
            $('[name="nice-select"]').find('ul').hide();
            $(this).find('ul').show();
            e.stopPropagation();
        });
        $('[name="nice-select"]').eq(2).addClass('ss');

        $('[name="nice-select"] li').hover(function (e) {
            $(this).toggleClass('on');
            e.stopPropagation();
        });
        $('[name="nice-select"] li').click(function (e) {
            var val = $(this).text();
            var dataId = $(this).attr('data-id');
            $(this).parents('[name="nice-select"]').find('input').val(val);
            $(this).parents('[name="nice-select"]').find('input').attr('data_id',dataId);
            $('[name="nice-select"] ul').hide();
            e.stopPropagation();
        });
        $(document).click(function () {
            $('[name="nice-select"] ul').hide();
        });
    }
    //选项卡
var stb2 = $('.czje');
stb2.click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});
$('.zffs-li:gt(1)').hide(); //隐藏大于0
var stb3 = $('.zffs label');
stb3.click(function () {
    $(this).addClass('active').siblings().removeClass('active');
    var stb_index3 = stb3.index(this); //index(this)获取对象参数 获取ct的第几个
    $('.zffs-li').eq(stb_index3).show() //如果ct的和li相等就显示
        .siblings().hide(); //隐藏掉上一个执行
    
    if($("#lb_label_nl").hasClass("active")&&(parseFloat($("#lb_label_nl").attr("data-b"))<parseFloat($('.form-amount').html()))){
    	$("#lb-error").css("display","block");
    }else{
    	$("#lb-error").css("display","none");
    }
});
var stb4 = $('.lex label');
stb4.click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});
//银行选择
var stb5 = $('.xzyh label');
stb5.click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});
var stb6 = $('.jl label');
stb6.click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});
$('.bd:gt(0)').hide(); //隐藏大于0
var stb6 = $('.jl label');
stb6.click(function () {
    $(this).addClass('active').siblings().removeClass('active');
    var stb_index3 = stb6.index(this); //index(this)获取对象参数 获取ct的第几个
    $('.bd').eq(stb_index3).show() //如果ct的和li相等就显示
        .siblings().hide(); //隐藏掉上一个执行
});
//点击充值金额
$('.z').click(function () {
    $('.form-amount').html($(this).html() / 10);
    
    if($("#lb_label_nl").hasClass("active")&&(parseFloat($("#lb_label_nl").attr("data-b"))<parseFloat($('.form-amount').html()))){
    	$("#lb-error").css("display","block");
    }else{
    	$("#lb-error").css("display","none");
    }
});
//获取充值金额
$('.je').blur(function () {
        var jg = $(".je").val();
        if (jg != null) {
            $('.form-amount').html($('.je').val() / 10);
            
            if($("#lb_label_nl").hasClass("active")&&(parseFloat($("#lb_label_nl").attr("data-b"))<parseFloat($('.form-amount').html()))){
            	$("#lb-error").css("display","block");
            }else{
            	$("#lb-error").css("display","none");
            }
        }
    })
    //判断只能输入数字
$('.num').keyup(function () {
    var tmptxt = $(this).val();
    $(this).val(tmptxt.replace(/\D|^0/g, ''));
}).bind("paste", function () {
    var tmptxt = $(this).val();
    $(this).val(tmptxt.replace(/\D|^0/g, ''));
}).css("ime-mode", "disabled");
$('.je').change(function () {
    var number = $(this).val();
    if (number < 100) {
        $('.je').val("100");
    }
})