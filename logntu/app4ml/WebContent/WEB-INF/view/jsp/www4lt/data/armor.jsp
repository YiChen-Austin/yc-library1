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
<div class="w1100 libiary">
    <ul class="libiary-nav">
        <li class="fl libiary-ico1">
            <a href="<%=project%>/weapoin" title = "武器"></a>
        </li>
        <li class="fl libiary-ico2">
            <a href="<%=project%>/jewelry" title = "饰品"></a>
        </li>
        <li class="fl libiary-ico3 active">
            <a href="<%=project%>/armor" title = "防具"></a>
        </li>
        <li class="fl libiary-ico4">
            <a href="" title = "套装"></a>
        </li>
    </ul>
    <div class="libiary-title">
        <span>
            防护种类
        </span>
        <p>
            Types of protection
        </p>
    </div>
    <ul class="protective-tab1">
        <li class="fl active">
            服饰
        </li>
        <li class="fl">
            头盔
        </li>
        <!-- <li class="fl">
            腰带
        </li> -->
    </ul>
    <div>
        <div class="tab1">
            <div>
                <table class="protective-table tab2-1">
                    <thead>
                        <tr>
                            <th>装备图标</th>
                            <th>装备名称</th>
                            <th>穿戴要求</th>
                            <th>属性</th>
                            <th>说明装备</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img1.jpg">
                            </td>
                            <td>布衣（男）</td>
                            <td>1级</td>
                            <td>物防0-2,魔防0-1</td>
                            <td class="wz">粗布做的衣服，能抵御严寒。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img2.jpg">
                            </td>
                            <td>布衣（女）</td>
                            <td>1级</td>
                            <td>物防0-2,魔防0-1</td>
                            <td class="wz">粗布做的衣服，能抵御严寒。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img3.jpg">
                            </td>
                            <td>轻型盔甲（男）</td>
                            <td>11级</td>
                            <td>物防3-3，魔防1-2</td>
                            <td class="wz">当年，将士们就是穿着这种衣服与怪物搏斗。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img4.jpg">
                            </td>
                            <td>轻型盔甲（女）</td>
                            <td>11级</td>
                            <td>物防3-3，魔防1-2</td>
                            <td class="wz">当年，将士们就是穿着这种衣服与怪物搏斗。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img5.jpg">
                            </td>
                            <td>中型盔甲（男）</td>
                            <td>16级</td>
                            <td>物防3-5，魔防1-2</td>
                            <td class="wz">曾是龙源大陆上最流行的服饰，连尊贵的城主也曾痴迷。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img6.jpg">
                            </td>
                            <td>中型盔甲（女）</td>
                            <td>16级</td>
                            <td>物防3-5，魔防1-2</td>
                            <td class="wz">曾是龙源大陆上最流行的服饰，连尊贵的城主也曾痴迷。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img7.jpg">
                            </td>
                            <td>重盔甲（男）</td>
                            <td>22级</td>
                            <td>物防4-7，魔防2-3</td>
                            <td class="wz">融入重铁板甲服装，能提供强大的防御。</td>
                        </tr>
                       <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img8.jpg">
                            </td>
                            <td>重盔甲（男）</td>
                            <td>22级</td>
                            <td>物防4-7，魔防2-3</td>
                            <td class="wz">融入重铁板甲服装，能提供强大的防御。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img9.jpg">
                            </td>
                            <td>魔法长袍（男）</td>
                            <td>22级</td>
                            <td>物防3-5，魔防3-4，魔攻0-2</td>
                            <td class="wz">中阶法师的流行法袍。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img10.jpg">
                            </td>
                            <td>魔法长袍（女）</td>
                            <td>22级</td>
                            <td>物防3-5，魔防3-4，魔攻0-2</td>
                            <td class="wz">中阶法师的流行法袍。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img11.jpg">
                            </td>
                            <td>灵魂战衣（男）</td>
                            <td>22级</td>
                            <td>物防3-6，魔防3-3，道攻0-2</td>
                            <td class="wz">中阶道士的流行道袍。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/fs-img12.jpg">
                            </td>
                            <td>灵魂战衣（女）</td>
                            <td>22级</td>
                            <td>物防3-6，魔防3-3，道攻0-2</td>
                            <td class="wz">中阶道士的流行道袍。</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="tab1">
            <div>
                <table class="protective-table tab3-1">
                    <thead>
                    <tr>
                        <th>装备图标</th>
                        <th>装备名称</th>
                        <th>穿戴要求</th>
                        <th>属性</th>
                        <th>说明装备</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/tk-img1.jpg">
                            </td>
                            <td>青铜头盔</td>
                            <td>10级</td>
                            <td>物防0-1</td>
                            <td class="wz">坚固的头盔，可保护勇士头部安全。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/tk-img2.jpg">
                            </td>
                            <td>魔法头盔</td>
                            <td>14级</td>
                            <td>物防0-1，魔防1-1</td>
                            <td class="wz">轻便的头盔，好用又实惠。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/tk-img3.jpg">
                            </td>
                            <td>道士头盔</td>
                            <td>24级</td>
                            <td>物防1-2，魔防2-3</td>
                            <td class="wz">又称“狗头”，适合所有人。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/tk-img4.jpg">
                            </td>
                            <td>骷髅头盔</td>
                            <td>攻击30</td>
                            <td>物防2-3</td>
                            <td class="wz">邪恶怪物的完整头骨制成。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/tk-img5.jpg">
                            </td>
                            <td>黑铁头盔</td>
                            <td>攻击46</td>
                            <td>物防4-5,魔防2-3</td>
                            <td class="wz">黑铁打造的坚硬头盔，防御能力十分强大。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/tk-img6.jpg">
                            </td>
                            <td>圣战头盔</td>
                            <td>攻击46</td>
                            <td>物防4-5，魔防2-3，物攻0-1</td>
                            <td class="wz">为应对魔龙大战炼器大师锤合专门为战士造出的头盔。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/tk-img7.jpg">
                            </td>
                            <td>法神头盔</td>
                            <td>魔法28</td>
                            <td>物防4-4,魔防1-2,魔攻0-1</td>
                            <td class="wz">为应对魔龙大战炼器大师锤合专门为法师造出的头盔。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/tk-img8.jpg">
                            </td>
                            <td>天尊头盔</td>
                            <td>精神力25</td>
                            <td>物防4-4,魔防1-2,魔攻0-1</td>
                            <td class="wz">为应对魔龙大战炼器大师锤合专门为道士造出的头盔。</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <%-- <div class="tab1">
            <div>
                <table class="protective-table tab4-1">
                    <thead>
                    <tr>
                        <th>装备图标</th>
                        <th>装备名称</th>
                        <th>穿戴要求</th>
                        <th>属性</th>
                        <th>说明装备</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <img src="<%=path%>/img/wq-img.jpg">
                        </td>
                        <td>蛇纹手镯</td>
                        <td>7</td>
                        <td>+16</td>
                        <td>+10</td>
                        <td class="wz">传说中这条手镯曾被最黑暗的喻言所诅咒，来自纳班德尔的纯净力量却让它重获新生，成为一种非常实用的魔法装备。</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div> --%>
    </div>
</div>
<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.10.1.min.js"></script>
<script src="<%=path%>/js/base.js"></script>
</html>

