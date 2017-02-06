$('.ljzf').attr('disabled', "true");
$('.ljzf').addClass('bgh');
var src = $('#src').text();
//打开页面就判断账号是否存在
window.onload = function(){
	$('#payidlab').css('display', 'none');
    var _data = {
        "userName": $("#payid").val(),
    };
    if($("#payid").val()!=''){
    	$.ajax({
            url: src + '/pay/validUn',
            data: _data,
            type: 'post',
            dataType: 'json',
            success: function (msg) {
                if (msg.serviceCode == '0') {
                    $('#payidlab').css('display', 'none');
                    $('.ljzf').removeAttr("disabled");
                    $('.ljzf').removeClass('bgh');
                } else {
                    $('#payidlab').css('display', 'inline-block');
                    $('#payidlab span').text('没有此账号');
                    $('.ljzf').attr('disabled', "true");
                    $('.ljzf').addClass('bgh');
                }
            }
        })
    }else{
    	payID();
    }
}

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
                $('.ljzf').removeAttr("disabled");
                $('.ljzf').removeClass('bgh');
            } else {
                $('#payidlab').css('display', 'inline-block');
                $('#payidlab span').text('没有此账号');
                $('.ljzf').attr('disabled', "true");
                $('.ljzf').addClass('bgh');
            }
        }
    })
}

//支付宝支付
function alipay() {
    $('#input_num').attr("num", $('#input_num').val());
    var userName = $("#payid").val();
    var num = $('.jediv .active').attr("num");
    var yf = $("#yf").text();
    $('#userName').val(userName);
    $('#goodsAmount').val(num);
    $('#orderAmount').val(yf);
    if($("#tyiphone").is(":checked")){	
		if(yf<10){
			layer.msg('请选择充值数量');
		}else{
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
			
			$("#pay_action").attr("target","_blank");
			$("#pay_action").attr("action",$("#src").text()+"/pay/alipay");
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
            var roleId = $(this).attr('roleId');
            $(this).parents('[name="nice-select"]').find('input').val(val);
            $(this).parents('[name="nice-select"]').find('input').attr('data_id',roleId);
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
$('.zffs-li:gt(0)').hide(); //隐藏大于0
var stb3 = $('.zffs label');
stb3.click(function () {
    $(this).addClass('active').siblings().removeClass('active');
    var stb_index3 = stb3.index(this); //index(this)获取对象参数 获取ct的第几个
    $('.zffs-li').eq(stb_index3).show() //如果ct的和li相等就显示
        .siblings().hide(); //隐藏掉上一个执行
});
var stb4 = $('.lex label');
stb4.click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});
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
$('.z').click(function () {
    $('.form-amount').html($(this).html() / 1)
});
$('.je').blur(function () {
        var jg = $(".je").val();
        if (jg != null) {
            $('.form-amount').html($('.je').val() / 1);
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
