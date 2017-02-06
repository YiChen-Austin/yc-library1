<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title> 武器</title>
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
        <li class="fl libiary-ico1 active">
            <a href="<%=project%>/weapoin" title = "武器"></a>
        </li>
        <li class="fl libiary-ico2">
            <a href="<%=project%>/jewelry" title = "饰品"></a>
        </li>
        <li class="fl libiary-ico3 ">
            <a href="<%=project%>/armor" title = "防具"></a>
        </li>
        <li class="fl libiary-ico4">
            <a href="" title = "套装"></a>
        </li>
    </ul>
    <div class="libiary-title">
        <span>
            武器种类
        </span>
        <p>
             Weapon type
        </p>
    </div>
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
                                <img src="<%=path%>/img/Weapon-img1.png">
                            </td>
                           <td>新手木剑</td>
                            <td>1级</td>
                            <td>攻击2-5</td>
                            <td class="wz">普通木剑，新手适用。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img2.png">
                            </td>
                            <td>铜剑</td>
                            <td>5级</td>
                            <td>攻击2-7</td>
                            <td class="wz">青铜长剑，非常锋利。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img3.png">
                            </td>
                            <td>铁剑</td>
                            <td>10级</td>
                            <td>物攻5-9</td>
                            <td class="wz">传统铁剑，沉重锋利。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img4.png">
                            </td>
                            <td>鹤嘴锄</td>
                            <td>11级</td>
                            <td>物攻0-9</td>
                            <td class="wz">挖矿必备工具，也有人用来做武器。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img5.png">
                            </td>
                            <td>海魂叉</td>
                            <td>15级</td>
                            <td>物攻3-11,魔攻1-2</td>
                            <td class="wz">取沧月海水凝练而成的法杖。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img6.png">
                            </td>
                            <td>八荒砍刀</td>
                            <td>15级</td>
                            <td>物攻4-12</td>
                            <td class="wz">又名“八方”，意在斩八方妖邪。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img7.png">
                            </td>
                            <td>半月弯刀</td>
                            <td>15级</td>
                            <td>物攻5-10，魔攻0-1，道攻1-1</td>
                            <td class="wz">型似半月的锋利弯刀。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img8.png">
                            </td>
                            <td>凌风</td>
                            <td>19级</td>
                            <td>物攻6-12</td>
                            <td class="wz">剑身寒光四射，传言这把利剑沾过虹魔教主的血。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img9.png">
                            </td>
                            <td>偃月长刀</td>
                            <td>20级</td>
                            <td>物攻4-10，魔攻1-3</td>
                            <td class="wz">既能挥刀斩仇敌，又能助法灭魔怪</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img10.png">
                            </td>
                            <td>降魔剑</td>
                            <td>20级</td>
                            <td>物攻6-11,道攻1-2,准确+1</td>
                            <td class="wz">这是一把能克制心魔的剑，不过用于杀人好像更合适。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img11.png">
                            </td>
                            <td>斩马刀</td>
                            <td>20级</td>
                            <td>物攻5-15</td>
                            <td class="wz">刀身巨大，斩马杀人不在话下。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img12.png">
                            </td>
                            <td>修罗战斧</td>
                            <td>22级</td>
                            <td>物攻0-20</td>
                            <td class="wz">寒光斧刃，透着嗜血杀气，仿佛修罗临世。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img13.png">
                            </td>
                            <td>凝霜</td>
                            <td>25级</td>
                            <td>物攻10-13</td>
                            <td class="wz">寒如冰霜，出鞘见血。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img14.png">
                            </td>
                            <td>炼狱战斧</td>
                            <td>26级</td>
                            <td>物攻0-25</td>
                            <td class="wz">举斧之处，恍如炼狱。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img15.png">
                            </td>
                            <td>魔杖</td>
                            <td>26级</td>
                            <td>物攻5-10,魔攻2-5</td>
                            <td class="wz">取死亡山脉之灵木，聚万灵之灵，成灵力魔杖。</td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img16.png">
                            </td>
                            <td>银蛇</td>
                            <td>26级</td>
                            <td>物攻7-14,道攻1-3,准确+1</td>
                            <td class="wz">蛇形利剑，传说出自炼器大师锤合之手。</td>
                        </tr>
                         <tr>
                            <td>
                                <img src="<%=path%>/img/Weapon-img17.png">
                            </td>
                            <td>井中月</td>
                            <td>28级</td>
                            <td>物攻7-22</td>
                            <td class="wz">水中花，井中月，如梦似幻，屠杀亦然</td>
                        </tr>
                    </tbody>
                </table>
              </div>
        </div>
    </div>
</div>
<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.10.1.min.js"></script>
<script src="<%=path%>/js/base.js"></script>
</html>

