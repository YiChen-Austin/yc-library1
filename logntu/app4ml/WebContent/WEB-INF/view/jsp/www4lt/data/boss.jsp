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
<div class="libiary-banner"></div>
<div class="w1200">
    <ul class="libiary-nav npc-nav">
        <li class="fl libiary-ico5 ">
            <a href="<%=project%>/npc" title = "NPC"></a>
        </li>
        <li class="fl libiary-ico6 ">
            <a href="<%=project%>/monster" title = "怪物"></a>
        </li>
        <li class="fl libiary-ico7 active">
            <a href="<%=project%>/boss" title = "BOSS"></a>
        </li>
    </ul>
    <div class="libiary-title">
        <span>
            Boos介绍
        </span>
        <p>
            Boos Introduce
        </p>
    </div>
    <div class="libiary-con">
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/boss-img1.png" class="fl">
            <div>
                <span>
                 骷髅洞boss
                </span>
                <p>
                   由天然洞穴发展而来，兽人相信这个洞穴能让灵魂得到安眠。这里也是兽人国王的陵寝。
骷髅王：黄泉教主的得力助手，受教主命令隐藏在古墓之中，一面唤醒墓中沉睡的骷髅，一面制造更多的怨灵——击杀进入古墓的一切生命。
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/boss-img2.png" class="fl">
            <div>
                <span>
                 僵尸洞boss
                </span>
                <p>
                    一场诡异的地震掩埋了矿区，正在采矿的矿工或死或伤，更可怕的是，地底升起的魔气让死去的矿工变成了可怕的僵尸，据逃出来的矿工说：矿区地底的深处，回荡着恐怖的吼声，那是僵尸王在咆哮。重现的尸王，成为进入矿区采矿和寻宝之人的最大威胁。
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/boss-img3.png" class="fl">
            <div>
                <span>
               蜈蚣洞boss
                </span>
                <p>
                  本体是黑暗洞穴里一条巨大的蜈蚣，因受魔气侵蚀得到邪恶力量，成为洞穴里众虫怪的首领，自封“触龙神”。它盘踞在暗处，随时准备袭击前来洞中寻找神器和历练的人类。
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/boss-img4.png" class="fl">
            <div>
                <span>
                  沃玛教主
                </span>
                <p>
                   创世之初三大魔教沃玛教教主，魔龙座下护法之一，好食人血，不满魔龙束缚，魔龙遭封印后，挣脱束缚，欲用法师一脉纯净之血成为大陆主宰。后被团结起来的法师、战士和道士击杀并将灵魂封印在沃玛寺庙中。但沃玛教主妄图以万人血肉重塑肉身，进入沃玛之人九死一生。
                </p>
            </div>
        </div>
        
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/boss-img5.png" class="fl">
            <div>
                <span>
                    祖玛教主
                </span>
                <p>
                 兽人心中最崇高的存在，也是人类心中最残忍的存在。曾经的祖玛教主不屑同沃玛教主那样食人血，一心想通过冥思悟得天机，对沃玛狂热的杀戮嗤之以鼻。然而沃玛教主以天机为诱饵，骗其进入魔窟，在黑暗之力的侵蚀下，祖玛教主沦为杀人机器。
                </p>
            </div>
        </div>
        
        
    </div>
</div>
<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.10.1.min.js"></script>
<script src="<%=path%>/js/base.js"></script>
</html>

