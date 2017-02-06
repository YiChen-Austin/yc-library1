<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title> 法师</title>
<link rel="icon" href="<%=path%>/img/lt_ico.ico"type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta name="Description" content="龙途" />
<meta name="Keywords" content="漫浪游戏,通行证,密宝,SDO,SNDA,网络游戏,游戏充值，龙途，传奇" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/Characteristic.css" />
<script src="<%=path%>/js/jquery-1.10.1.min.js"></script>
</head>
<body>

<body style="overflow-x:hidden;">
<jsp:include page="../include/top.jsp" >
	<jsp:param name="tag" value="data" />
</jsp:include>
    <!--视频弹出内容 start-->

    <div id="dialog-overlay"></div>

    <div class="alertBox" id="alertBox">

        <div class="alertCon">

            <div id="ajaxCon">

                <!-- ajax cont -->

            </div>

            <p style="margin:0" class="close" title="关闭"></p>

        </div>

    </div>

    <!--视频弹出内容 end-->

   

    <div class="wrap">

        <div class="lunbo">

            <!--开始-->

            <div class="imgscroll">

                <div style="display:none;" class="pre" id="prev"></div>

                <div style="display:none;" class="next" id="next"></div>

                <div id="picBox" class="imglist">

                    <ul>

                        <li class="imgurl">

                            <div class="pic">

                                <span><img src="<%=path%>/img/fs_bg2.jpg" /></span>

                            </div>

                        </li>

                        <li class="imgurl">



                            <div class="pic">



                                <span><img src="<%=path%>/img/fs_bg1.jpg" /></span>



                            </div>



                        </li>



                    </ul>



                </div>



                <div id="listBox">



                    <ul class="roll_l">



                        <li><a href="javascript:void(0)" class="roll_t1" title=""></a></li>



                        <li><a href="javascript:void(0)" class="roll_t2" title=""></a></li>



                    </ul>



                </div>



            </div>

            <!--结束-->

        </div>



        <div class="wrapper info">



            <div class="w_main">






                <div class="main">



                    <a class="videoB popcl" href="video1.html"><img src="<%=path%>/img/zs_vBtn.jpg" /></a>



                    <div class="h_25"></div>



                    <div class="m_tit"></div>

					<ul class="frnav">
						<li class="one ">
							<a href="<%=project%>/occupation_zs"  title="战士"></a>
						</li>
						<i></i>
						<li class="two ">
							<a href="<%=project%>/occupation_ds"  title="道士"></a>
						</li>
						<i></i>
						<li class="three active">
							<a href="<%=project%>/occupation_fs"  title="法师"></a>
						</li>
					</ul>
                    <div class="m_txt2">
                        <div class="h_10"></div>
                        <p>山有魔灵，性平和，静心修炼，不受魔怪侵染终成圣道，得水、火、雷电之法，不受轮回，见魔怪为祸人间，不忍，与“战”合力封印魔龙，人称灵法神，后世人追随法神潜心修炼，
终得魔法奥妙，承法师一脉，行走世间，惩恶扬善。</p>
                    </div>
                    <div class="h_40"></div>





                </div>



                <div class="titList">法师技能</div>



                <div class="list">



                    <ul>



                        <li>



                            <p><img src="<%=path%>/img/fs_jn1.jpg" /></p>



                            <em>大火球</em>



                            <i>修炼等级：7</i>



                            <span>技能说明：凝聚魔力打出一个火球攻击目标。</span>



                        </li>



                        <li>



                            <p><img src="<%=path%>/img/fs_jn2.jpg" /></p>



                            <em>抗拒光环</em>



                            <i>修炼等级：12</i>

                            <span>技能说明：向周围瞬间释放魔力弹开低于自己等级的人或怪物。</span>

                        </li>

                        <li>

                            <p><img src="<%=path%>/img/fs_jn3.jpg" /></p>

                            <em>诱惑之光</em>

                            <i>修炼等级：13</i>

                            <span>技能说明：将诱惑之力与魔力结合释放出强大光芒迷晕或迷惑怪物成为自己的随从，诱惑的怪物等级不可超过自己等级3级以上。</span>

                        </li>

                        <li>

                            <p><img src="<%=path%>/img/fs_jn4.jpg" /></p>

                            <em>雷电术</em>

                            <i>修炼等级：17</i>

                            <span>技能说明：注入魔力向天空引出一道闪电攻击敌人。</span>

                        </li>

                        <li>

                            <p><img src="<%=path%>/img/fs_jn5.jpg" /></p>

                            <em>瞬息移动</em>

                            <i>修炼等级：19</i>

                            <span>技能说明：运用强大魔力打破空间束缚，从而实现随机传送。</span>

                        </li>

                        <li>

                            <p><img src="<%=path%>/img/fs_jn6.jpg" /></p>

                            <em>火墙</em>

                            <i>修炼等级：24</i>

                            <span>技能说明：放出用魔力炼造的地狱烈火焚烧敌人或怪物造成持续伤害。</span>

                        </li>

                        

                    </ul>

                </div>

                <div class="h_40"></div>

            </div>

            <div class="clearit"></div>

        </div>

        <div class="clearit"></div>

    </div>

    <!--弹窗-->

    <div id="overlay"></div>

    <div class="overlay" id="showGift_1">

        <a class="close2" href="javascript:;"></a>

        <div class="overlayCon">

            <div class="role_tan">

                <div class="tPic">

                    <ul>

                        <li class="on5"><img src="<%=path%>/img/zs_p2.jpg" /></li>

                        <li><img src="<%=path%>/img/zs_p3.jpg" /></li>

                        <li><img src="<%=path%>/img/zs_pic2.jpg" /></li>

                        <li><img src="<%=path%>/img/zs_pic3.jpg" /></li>

                    </ul>

                </div>

                <div class="tTit">

                    <!--<span>天魔神甲（本次不投放）</span>-->

                    <span class="on5">中型盔甲</span>

                    <span>轻型盔甲</span>

                    <!--<span>圣战宝甲（本次不投放）</span>-->

                    <span>战神盔甲</span>

                    <span>重盔甲</span>

                </div>

                <div class="qieBtn">

                    <div class="on5"><a href="javascript:void(0);">壹</a></div>

                    <div><a href="javascript:void(0);">贰</a></div>

                    <div><a href="javascript:void(0);">叁</a></div>

                    <div><a href="javascript:void(0);">肆</a></div>

                </div>

            </div>

        </div>

    </div>
<script>

    function picroll() {

        function G(s) {

            return document.getElementById(s)

        }

        function getStyle(obj, attr) {

            if (obj.currentStyle) {

                return obj.currentStyle[attr]

            } else {

                return getComputedStyle(obj, false)[attr]

            }

        }

        var iBase = {

            Id: function (name) {

                return document.getElementById(name);

            },

            //设置元素透明度,透明度值按IE规则计,即0~100

            SetOpacity: function (ev, v) {

                ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')' : ev.style.opacity = v / 100;

            }

        }

        //淡入效果(含淡入到指定透明度)

        function fadeIn(elem, speed, opacity) {

            /*

            * 参数说明

            * elem==>需要淡入的元素

            * speed==>淡入速度,正整数(可选)

            * opacity==>淡入到指定的透明度,0~100(可选)

            */

            speed = speed || 20;

            opacity = opacity || 100;

            //显示元素,并将元素值为0透明度(不可见)

            elem.style.display = 'block';

            iBase.SetOpacity(elem, 0);

            //初始化透明度变化值为0

            var val = 0;

            //循环将透明值以5递增,即淡入效果

            (function () {

                iBase.SetOpacity(elem, val);

                val += 5;

                if (val <= opacity) {

                    setTimeout(arguments.callee, speed)

                }

            })();

        }

        //淡出效果(含淡出到指定透明度)

        function fadeOut(elem, speed, opacity) {

            /*

            * 参数说明

            * elem==>需要淡入的元素

            * speed==>淡入速度,正整数(可选)

            * opacity==>淡入到指定的透明度,0~100(可选)

            */

            speed = speed || 20;

            opacity = opacity || 0;

            //初始化透明度变化值为0

            var val = 100;

            //循环将透明值以5递减,即淡出效果

            (function () {

                iBase.SetOpacity(elem, val);

                val -= 5;

                if (val >= opacity) {

                    setTimeout(arguments.callee, speed);

                } else if (val < 0) {

                    //元素透明度为0后隐藏元素

                    elem.style.display = 'none';

                }

            })();

        }

        var oPic = G("picBox"); //大图列表ID

        var oList = G("listBox"); //小图列表ID

        var oPrev = G("prev"); //大图片上一页按钮，可修改ID名

        var oNext = G("next"); //大图片下一页按钮，可修改ID名

        var oPicLi = oPic.getElementsByTagName("li");

        var oListLi = oList.getElementsByTagName("li");

        var len1 = oPicLi.length;

        var len2 = oListLi.length;

        var oPicUl = oPic.getElementsByTagName("ul")[0];

        var oListUl = oList.getElementsByTagName("ul")[0];

        var w1 = oPicLi[0].offsetWidth;

        var w2 = oListLi[0].offsetWidth;

        oPicUl.style.width = w1 * len1 + "px";

        oListUl.style.width = w2 * len2 + "px";

        var index = 0;

        var num = 7; //小图列表显示小图数量，需按设计稿小图列表数量做相应修改

        var num2 = Math.ceil(num / 2);

        function Change() {

            for (var i = 0; i < len1; i++) {

                oPicLi[i].style.display = "none";

            }

            fadeIn(oPicLi[index]);

            for (var i = 0; i < len2; i++) {

                oListLi[i].className = "";

                if (i == index) {

                    oListLi[i].className = "on"

                }

            }

        }

        //执行下一页

        function nextPic() {

            index++;

            index = index == len2 ? 0 : index;

            Change()

        }

        //执行上一页

        function prevPic() {

            index--;

            index = index == -1 ? len2 - 1 : index;

            Change()

        }

        //点击id为next的按钮时执行下一页事件

        oNext.onclick = function () { nextPic(); }

        //点击id为prev的按钮时执行下一页事件

        oPrev.onclick = function () { prevPic() };

        for (var i = 0; i < len2; i++) {

            oListLi[i].index = i;

            oListLi[i].onclick = function () {

                index = this.index;

                Change()

            }

        }

        oListLi[0].onclick();

        function tt() {

            setInterval(function () { nextPic(); }, 8000);

        }

        tt();

    }

    picroll();

    </script>
    <script type="text/javascript">
        // 弹窗

        function popup(message) {

            var maskHeight = $(document).height();

            var maskWidth = $(window).width();

            var dialogTop = ($(document).scrollTop() + (($(window).height() - $('#showGift_' + message).height()) / 2)) >> 0;

            var dialogLeft = (maskWidth / 2) - ($('#showGift_' + message).width() / 2);

            $('#overlay').css({ height: maskHeight, width: maskWidth }).fadeTo("fast", 0.9);

            $('#showGift_' + message).css('top', dialogTop).fadeTo("slow", 1);

        }

        $('a.close2').click(function () {

            $('#overlay, #showGift_1, #showGift_2, #showGift_3, #showGift_4, #showGift_5, #showGift_6, #showGift_7, #showGift_8').fadeOut(300);

            return false;

        });

        $('#overlay').click(function () {

            $('#overlay, .overlay').fadeOut(300);

            return false;

        });

    </script>


  
    <!--右浮动-->
<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/occupation.js"></script>
<script type="text/javascript" src="<%=path%>/js/tan.js"></script>
<script type="text/javascript" src="<%=path%>/js/LAB.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=path%>/js/common.js"></script>
</html>
