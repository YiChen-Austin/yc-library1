package com.mall.common.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.mall.common.constant.CommonConstant;

public class Mail4RegUtil {
	protected final static Logger logger = Logger.getLogger(Mail4RegUtil.class);

	public static boolean send(Mail4Reg mail) {
		// 发送email
		HtmlEmail email = new HtmlEmail();
		try {
			// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
			email.setHostName(mail.getHost());
			// 字符编码集的设置
			email.setCharset(CommonConstant.UTF8);
			// 收件人的邮箱
			email.addTo(mail.getReceiver());
			// 发送人的邮箱
			email.setFrom(mail.getSender(), mail.getName());
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			email.setAuthentication(mail.getUsername(), mail.getPassword());
			// 要发送的邮件主题
			email.setSubject(mail.getSubject());
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			//email.setMsg(mail.getMessage());
			email.setHtmlMsg(mail.getMessage());
			// 发送
			email.send();
			if (logger.isDebugEnabled()) {
				logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
			}
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver() + " 失败");
			return false;
		}
	}
	public static void main(String[] args){
		Mail4Reg mail=new Mail4Reg();
		mail.setReceiver("xxxxx@qq.com");
		//mail.setSubject("邮箱找回密码验证码");
		mail.setMessage4Reg("http://localhost:8080/app4ml/register/confirmEmail?contextId=RSC5370B1C285EA10438FA9C23531807AAE&checkCode=428053");
		Mail4RegUtil.send(mail);
		//mail.setSubject("邮箱找回密码验证码");
		//mail.setMessage4Find("658741");
		//Mail4RegUtil.send(mail);
	}
}
