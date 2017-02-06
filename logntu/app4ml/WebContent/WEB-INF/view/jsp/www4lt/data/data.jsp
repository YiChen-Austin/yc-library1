<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title> 游戏资料</title>
<link rel="icon" href="<%=path%>/img/lt_ico.ico"type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta name="Description" content="龙途" />
<meta name="Keywords" content="漫浪游戏,通行证,密宝,SDO,SNDA,网络游戏,游戏充值，龙途，传奇" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="../include/top.jsp" >
	<jsp:param name="tag" value="data" />
</jsp:include>
<div class="data-banner"></div>
<div class="w1000 data">
    <div class="lf-nav fl">
        <a id="fb1"  href="javascript:void(0);" class="active">
            <div>
                新手入门
            </div>
            <p>
                newbie guide
            </p>
            <img src="<%=path%>/img/bottom.png">
        </a>
        <a id="fb2" href="javascript:void(0);" class="">
            <div>
                游戏操作
            </div>
            <p>
                operation
            </p>
            <img src="<%=path%>/img/bottom.png">
        </a>
        <a id="fb3" href="javascript:void(0);" class="">
            <div>
                职业介绍
            </div>
            <p>
                occupation
            </p>
            <img src="<%=path%>/img/bottom.png">
        </a>
        <a id="fb4" href="javascript:void(0);" class="">
            <div>
                人物角色
            </div>
            <p>
                person
            </p>
            <img src="<%=path%>/img/bottom.png">
        </a>
        <a id="fb5" href="javascript:void(0);" class="">
            <div>
                游戏资料
            </div>
            <p>
                game data
            </p>
            <img src="<%=path%>/img/bottom.png">
        </a>
        <a id="fb6" href="javascript:void(0);" class="">
            <div>
                PVP对战
            </div>
            <p>
                PVP fighting
            </p>
            <img src="<%=path%>/img/bottom.png">
        </a>
        <a id="fb7" href="javascript:void(0);" class="">
            <div>
                游戏攻略
            </div>
            <p>
                walkthrough
            </p>
        </a>
    </div>
    <ul class="fl data-con">
        <li class="fb1" id="section1">
            <div class="titCon">
                <span>01</span>
                <em>新手入门</em>
            </div>
            <div class="fl titLfet">
                <i>进入游戏</i>
                <i>了解游戏</i>
                <i>服务中心</i>
            </div>
            <div class="conCon">
                <p>
                    <a href="<%=project%>/member/reg"  >账号注册</a>
                    <a href="<%=project%>/download"  >下载安装</a>
                    <a href="<%=project%>/download"  >游戏配置</a>
                    <a href="<%=project%>/pay/pay4l"  >游戏充值</a>
                </p>
                <p>
                    <a href="<%=project%>/operation"  >新手指南</a>
                </p>
                <p>
                    <a href="<%=project%>/member/agreement"  >用户协议</a>
                    <a href="<%=project%>/member/passwd"  >账号安全</a>
                    <a href="<%=project%>/member/passwd"  >密码找回</a>
                </p>
            </div>
        </li>
        <li id="section2">
            <div class="titCon">
                <span>02</span>
                <em>游戏操作</em>
            </div>
            <div class="conCon">
                <p>
                    <a href="<%=project%>/game_operation"  >创建角色 </a>
                    <a href="<%=project%>/game_operation"  >游戏界面 </a>
                    <a href="<%=project%>/game_operation"  >基本操作  </a>
                </p>
            </div>
        </li>
        <li id="section3">
            <div class="titCon">
                <span>03</span>
                <em>职业介绍</em>
            </div>
            <div class="conCon">
                <p>
                    <a href="<%=project%>/occupation_zs"  >战士</a>
                    <a href="<%=project%>/occupation_fs"  >法师</a>
                    <a href="<%=project%>/occupation_ds"  >道士</a>
                </p>
            </div>
        </li>
        <li id="section4">
            <div class="titCon">
                <span>04</span>
                <em>人物角色</em>
            </div>
            <div class="conCon">
                <p>
                   <!--  <a href=""  >角色属性系统</a>
                    <a href=""  >基本系统</a>
                    <a href=""  >社交系统</a> -->
                    <a href="<%=project%>/game_operation"  >创建角色 </a>
                    <a href="<%=project%>/game_operation"  >游戏界面 </a>
                    <a href="<%=project%>/game_operation"  >基本操作  </a>
                </p>
            </div>
        </li>
        <li id="section5">
            <div class="titCon">
                <span>05</span>
                <em>游戏资料</em>
            </div>
            <div class="fl titLfet">
                <i>物品</i>
                <i>怪物</i>
            </div>
            <div class="conCon">
                <p>
                    <a href="<%=project%>/weapoin"  >武器 </a>
                    <a href="<%=project%>/jewelry"  >饰品  </a>
                    <a href="<%=project%>/armor"  >防具  </a>
                </p>
                <p>
                    <a href="<%=project%>/npc"  >NPC  </a>
                    <a href="<%=project%>/monster"  >怪物  </a>
                    <a href="<%=project%>/boss"  >Boss  </a>
                </p>
            </div>
        </li>
        <li id="section6">
            <div class="titCon">
                <span>06</span>
                <em>PVP对战</em>
            </div>
            <div class="conCon">
                <p>
                    <a href="<%=project%>/game_operation"  >创建角色 </a>
                    <a href="<%=project%>/game_operation"  >游戏界面 </a>
                    <a href="<%=project%>/game_operation"  >基本操作  </a>
                </p>
            </div>
        </li>
        <li id="section7">
            <div class="titCon">
                <span>07</span>
                <em>游戏攻略</em>
            </div>
            <div class="conCon">
                <p>
                    <a href="<%=project%>/game_operation"  >创建角色 </a>
                    <a href="<%=project%>/game_operation"  >游戏界面 </a>
                    <a href="<%=project%>/game_operation"  >基本操作  </a>
                </p>
            </div>
        </li>
    </ul>
</div>
<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.10.1.min.js"></script>
<script src="<%=path%>/js/base.js"></script>
</html>
