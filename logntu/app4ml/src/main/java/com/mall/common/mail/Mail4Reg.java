package com.mall.common.mail;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.DateUtil;

public class Mail4Reg {
	private String host="smtp.ym.163.com"; // 服务器地址

	private String sender="lt@cqlongtu.com"; // 发件人的邮箱

	private String receiver; // 收件人的邮箱

	private String name="龙途游戏"; // 发件人昵称

	private String username="lt@cqlongtu.com"; // 账号

	private String password="lt2cq0Lo9ngtu"; // 密码

	private String subject="请完成龙途游戏注册"; // 主题

	private String message; // 信息(支持HTML)

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 *用户注册 
	 */
	public void setMessage4Reg(String url) {
		this.message ="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		this.message+="\r\n";
		this.message+="<html xmlns=\"http://www.w3.org/1999/xhtml\">";
		this.message+="\r\n";
		this.message+="<head>";
		this.message+="\r\n";
		this.message+="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />";
		this.message+="\r\n";
		this.message+="<title>龙途通行证注册确认信</title>";
		this.message+="\r\n";
		this.message+="</head>";
		this.message+="\r\n";
		this.message+="<style type=\"text/css\">";
		this.message+="\r\n";
		this.message+="*{margin:0;padding:0}";
		this.message+="\r\n";
		this.message+="</style>";
		this.message+="\r\n";
		this.message+="<body style=\"font-size:12px;font-family:'宋体';margin:0;padding:0;\">";
		this.message+="\r\n";
		this.message+="<div class=\"wrap\" style=\"width:700px;height:491px;margin:0 auto;\">";
		this.message+="\r\n";
		this.message+="<div class=\"email_top\" style=\"text-align:center;padding-top: 20px;\">";
		this.message+="\r\n";
		this.message+="<p class=\"txt_4\" style=\"margin-bottom: 20px;line-height:20px;\"><b class=\"emailt_sty\" style=\"color:#000000;font-size:14px;\">亲爱的"+receiver+"，您好！</b></p>";
		this.message+="\r\n";
		this.message+="<p>龙途已收到您的注册信息，请点击以下确认链接完成注册。</p>";
		this.message+="\r\n";
		this.message+="<p class=\"txt_1\" style=\"color:#0084ff;font-size:16px;margin:22px 0;line-height:20px;\"><a target=\"_blank\" hidefocus=\"true\" class=\"btn btnMail\" href=\""+url+"\" style=\"text-decoration:none;color:#0084ff;cursor:pointer;\">完成注册，立即体验龙途娱乐之旅！</a></p>";
		this.message+="\r\n";
		this.message+="<p class=\"txt_3\" style=\"margin-bottom:0px;line-height:20px;\">如果不能点击该链接，请复制以下地址并粘贴到浏览器输入框</p>";
		this.message+="\r\n";
		this.message+="<p class=\"txt_2\" style=\"width:511px;margin:0 auto;margin-bottom:32px;line-height:20px;\"> <a href=\"http://register.sdo.com/register/confirmEmail?contextId=RSC5370B1C285EA10438FA9C23531807AAE&checkCode=428053\" target=\"_blank\" hidefocus=\"true\" style=\"cursor:pointer;\">"+url+"</a></p>";
		this.message+="\r\n";
		this.message+="</div>";
		this.message+="\r\n";
		this.message+="<div style=\"text-align:center;padding-top:20px;font-size:14px;color:#666;\">";
		this.message+="\r\n";
		this.message+="<p style=\"font-size:14px;color:#666;margin:0;\">龙途游戏</p>";
		this.message+="\r\n";
		this.message+="<p style=\"margin:0;padding-top:8px;font-size:14px;color:#666;\">"+DateUtil.dateToString(DateUtil.getGMTDate(),CommonConstant.DATE_SHORT_FORMAT)+"</p>";
		this.message+="\r\n";
		this.message+="</div>";
		this.message+="\r\n";
		this.message+="</div>";
		this.message+="\r\n";
		this.message+="</body>";
		this.message+="\r\n";
		this.message+="</html>";
		//System.out.println(this.message);
	}
	/**
	 *找回密码 
	 */
	public void setMessage4Find(String code) {
		this.message="尊敬的用户：<br>";
		this.message+="\r\n";
		this.message+="您的通过邮箱找回密码验证码为<b>"+code+"</b>，请在网页中填写，完成验证。";
		this.message+="\r\n";
		this.message+="<br><br>";
		this.message+="\r\n";
		this.message+=name;
		//System.out.println(this.message);
	}
}