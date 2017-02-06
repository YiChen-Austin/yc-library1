<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<div class="lt-header">
    <div class="w1200">
        <a href="<%=project%>/index" class="logo fl">
            <img src="<%=path%>/img/logo.png">
        </a>
        <ul class="nav fl">
            <li class="fl">
                <a href="<%=project%>/index" class="item ${param.tag=='main'?'cur':''}" target="_blank">
                    <span>官方首页</span>
                    <p>INDEX</p>
                </a>
            </li>
            <li class="fl">
                <a href="<%=project%>/announ_list/402881f9589a81ba01589a86ae9e0001" class="item ${param.tag=='announ'?'cur':''}" target="_blank">
                    <span>活动公告</span>
                    <p>Announ Cement</p>
                </a>
            </li>
            <li class="fl">
                <a href="<%=project%>/data" class="item ${param.tag=='data'?'cur':''}" target="_blank">
                    <span>游戏资料 </span>
                    <p>GAME DATA </p>
                </a>
            </li>
            <li class="fl">
                <a href="<%=project%>/pay/pay4l" class="item " target="_blank">
                    <span>充值中心</span>
                    <p> Pay Center</p>
                </a>
            </li>
            <li class="fl">
                <a href="" class="item" target="_blank">
                    <span>游戏论坛</span>
                    <p>Game Forum</p>
                </a>
            </li>
            <li class="fl">
                <a href="<%=project%>/member/client" class="item" target="_blank">
                    <span>客服中心</span>
                    <p> Call Center</p>
                </a>
            </li>
        </ul>
        <div class="fr user_info">
            <a href="<%=project%>/member/login">登录</a>
            <span class="line">|</span>
            <a href="<%=project%>/member/reg" target="_blank">注册</a>
        </div>
    </div>
</div>