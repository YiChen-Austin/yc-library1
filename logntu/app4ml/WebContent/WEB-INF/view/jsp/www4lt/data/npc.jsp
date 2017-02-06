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
        <li class="fl libiary-ico5 active">
            <a href="<%=project%>/npc" title = "NPC"></a>
        </li>
        <li class="fl libiary-ico6 ">
            <a href="<%=project%>/monster" title = "怪物"></a>
        </li>
        <li class="fl libiary-ico7 ">
            <a href="<%=project%>/boss" title = "BOSS"></a>
        </li>
    </ul>
    <div class="libiary-title">
        <span>
            NPC介绍
        </span>
        <p>
            NPC Introduce
        </p>
    </div>
    <div class="libiary-con">
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img1.jpg" class="fl">
            <div>
                <span>
                   裁缝
                </span>
                <p>
                    脑袋不好使，但绣花针用得炉火纯青
“你好，你是来找我缝东西的吗？”
“那癫老头说用针把自己扎个遍看起来就要大很多！我真是太聪明了”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img2.jpg" class="fl">
            <div>
                <span>
                    淬炼师
                </span>
                <p>
                    武器的升级
“我叫有才，是一个有才华的人，没错，我只做升级！”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img3.jpg" class="fl">
            <div>
                <span>
                   彩票员
                </span>
                <p>
                  彩票这东西，买中了，可就是大富翁呢~”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img4.jpg" class="fl">
            <div>
                <span>
                   传送员
                </span>
                <p>
                    曾经是从城家喻户晓的风水大师，对龙源大陆最为了解的人，也是首批进入沙克的帮会成员。
“上可入天宫，下能达地府，不要说我吹牛，你想去我都能送你！不过，若是太过凶险的地方，你长得再像花，我都是要收费的！”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img5.jpg" class="fl">
            <div>
                <span>
                   店小二
                </span>
                <p>
                    物品的保管与寄存
尽职尽责的店小二
“过来看过来瞧，好仓库这里找，英雄放心，我一定拼命保护好你的物品。”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img6.jpg" class="fl">
            <div>
                <span>
                    服装店掌柜
                </span>
                <p>
                   金丝银线织华服，退敌降魔弹指间
				“寻找神器甚是危险，我虽然不懂技法，但也希望早日加固封印，这些都是我织的新衣，你随意挑选，如果衣服头盔破了，我还能帮你修理。”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img7.jpg" class="fl">
            <div>
                <span>
                    马倌
                </span>
                <p>
                    买卖马匹，口粮等
我始终相信千里马常有，伯乐亦常有。
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img8.jpg" class="fl">
            <div>
                <span>
                    赏金猎人
                </span>
                <p>
                   “杀掉怪物保卫人类是作为猎人的职责，你准备好了吗？”
“你还太稚嫩，等到XX后再来吧！”
                </p>
            </div>
        </div>
         <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img9.jpg" class="fl">
            <div>
                <span>
                   典籍铺掌柜
                </span>
                <p>
                   博览群书，通晓技法，书生百用，指点迷津
“书籍能很好的丰富我们的技能，我收集书籍，也出售书籍，你需要什么呢？
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img10.jpg" class="fl">
            <div>
                <span>
                    首饰店掌柜
                </span>
                <p>
                   首饰买卖维修、特殊处理等
爱美又自恋的女掌柜，手中拿着玉吊坠
“你来啦！是不是来看我有没有更漂亮？要买卖首饰吗？”
“把需要出售的饰品放上去吧，我马上给你估价”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img11.jpg" class="fl">
            <div>
                <span>
                   屠夫
                </span>
                <p>
                   “这个小鲜肉盛行的世代，屠夫越来越不好做了，你有什么肉都可以卖给我！”
“我年轻的时候在猪洞中杀过几头白野猪，它们身上真的藏有宝贝，我想，真正的宝贝应该在怪物首领的手中！”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img12.jpg" class="fl">
            <div>
                <span>
                   武器铺老铁匠
                </span>
                <p>
                   疯癫的顶级武器打造师，不过谁知道他是真疯还是装疯呢？
“知道我为什么这么聪明吗？因为我最爱吃的东西是油炸小蚂蚁！唉~你找我啥事呢？”
“沙克城地底的魔龙睡醒了呢，你说，它出来后会不会把我们撕成碎片？”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img13.jpg" class="fl">
            <div>
                <span>
                   小货郎
                </span>
                <p>
                 小生意人，酷爱买彩票花光积蓄，十足的彩票狂人。
“我的东西很紧俏，要买要卖赶紧的，不要耽误我中五百万。”
                </p>
            </div>
        </div>
        <div class="libiary-condiv fl">
            <img src="<%=path%>/img/npc-img14.jpg" class="fl">
            <div>
                <span>
                  药铺掌柜
                </span>
                <p>
                满口救死扶伤，实则爱财如命
“活死人，医白骨，把你治好是我的职责，不用忙着谢我，医者父母心！不过，钱带够了吗？”
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

