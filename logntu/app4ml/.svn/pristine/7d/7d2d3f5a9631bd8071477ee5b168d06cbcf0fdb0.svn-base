package com.mall.common.mail;

import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import com.mall.common.dns.DNSServer;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;

/**
 * 发送验证邮件。
 * 
 * @author sol
 */
public class VerificationMailSender implements ConnectionListener, TransportListener
{
	/** 邮件主题 */
	private static final String SUBJECT = "全球购：请完成邮箱验证";
	/** 发件人 */
	private static final String FROM = "qqgo.cc<noreply@qqgo.cc>";
	/** 验证链接路径 */
	private static final String VERIFY_URI_PATH = "http://www.qqgo.cc/member/security/resetPaymentPasswordVerifyEmail";
	
	// 域名mx对应表
	public static Hashtable<String, String> dnsMxTable = new Hashtable<String, String>();

	public static String getDns(String hostname)
	{
		String dns = dnsMxTable.get(hostname);
		if (dns == null) {
			Vector<String> v = DNSServer.findMXServers(hostname);
			if (v == null || v.size() <= 0)
				return "";
			else {
				String ip = DNSServer.findAServer(v.elementAt(0));
				if (!BaseUtil.isEmpty(ip)) {
					dnsMxTable.put(hostname, ip);
					return ip;
				}
			}
		}
		return dns;
	}

	/**
	 * 发送邮件。
	 * @param to				收件人地址
	 * @param userId			收件人用户编号
	 * @param verificationCode	收件人邮箱激活码
	 */
	public void sendMail(String to, int userId, String verificationCode)
	{
		Properties props = System.getProperties();
		String hostname = to.substring(to.indexOf("@") + 1);
		props.put("mail.smtp.host", getDns(hostname));
		// create some properties and get a Session
		Session session = Session.getInstance(props, null);
		sendMail(session, FROM, to, htmlContent(userId, verificationCode));
	}

	/*!
	 * 发送邮件。
	 * @param session		当前session
	 * @param from			发件人称谓
	 * @param to			收件人地址
	 * @param htmlContent	邮件正文
	 */
	private void sendMail(Session session, String from, String to, StringBuffer htmlContent)
	{
		Transport trans = null;
		try {
			InternetAddress[] toAddr = InternetAddress.parse(to, false);
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, toAddr);
			msg.setSentDate(DateUtil.getGMTDate());
			msg.setSubject(SUBJECT);
			msg.setDataHandler(new DataHandler(new ByteArrayDataSource(htmlContent.toString(), "text/html")));
			msg.saveChanges();
			
			// get the smtp transport for the address
			trans = session.getTransport(toAddr[0]);

			// connect the transport
			trans.connect();

			// send the message
			trans.sendMessage(msg, toAddr);
		}
		catch (Exception mex) {
			// ignore
		}
		finally {
			try {
				// close the transport
				if (trans != null) {
					trans.close();
				}
			}
			catch (MessagingException mex) { /* ignore */ }
		}
	}

	/**
	 * 生成邮件正文。
	 * @param userId			收件人用户编号
	 * @param verificationCode	收件人邮箱激活码
	 * @return		邮件正文
	 */
	public StringBuffer htmlContent(int userId, String verificationCode)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<html>\n");
		sb.append("<head><meta http-equiv=Content-Type content=\"text/html; charset=gb2312\"></head>\n");
		sb.append("<body>\n");
		
		sb.append("<h3>尊敬的全球购用户：</h3>");
		sb.append("<p>您申请重置支付密码，请在72小时内完成设置。如果未作任何操作，系统将保留原始密码。</p>");
		// 验证链接：通过邮箱校验的方式重置支付密码
		String href = VERIFY_URI_PATH + "?id=" + String.valueOf(userId) + "&vc=" + verificationCode;
		sb.append("<p><a href=\"" + href + "\">立即重置支付密码</a></p>");
		sb.append("<p>如果链接点击无效，请复制到下方网页地址到浏览器地址栏中打开：</p>");
		sb.append("<p>" + href + "</p>");
		sb.append("<p>此为系统邮件，请勿回复。</p>");
		
		sb.append("</body>\n");
		sb.append("<html>\n");
		return sb;
	}

	// implements ConnectionListener interface
	public void opened(ConnectionEvent e)
	{
		System.out.println(">>> ConnectionListener.opened()");
	}

	public void disconnected(ConnectionEvent e)
	{
		System.out.println(">>> ConnectionListener.disconnected()");
	}

	public void closed(ConnectionEvent e)
	{
		System.out.println(">>> ConnectionListener.closed()");
	}

	// implements TransportListener interface
	public void messageDelivered(TransportEvent e)
	{
		System.out.println(">>> TransportListener.messageDelivered().");
		System.out.println(" Valid Addresses:");
		Address[] valid = e.getValidSentAddresses();
		if (valid != null) {
			for (int i = 0; i < valid.length; i++)
				System.out.println("    " + valid[i]);
		}
	}

	public void messageNotDelivered(TransportEvent e)
	{
		System.out.println(">>> TransportListener.messageNotDelivered().");
		System.out.println(" Invalid Addresses:");
		Address[] invalid = e.getInvalidAddresses();
		if (invalid != null) {
			for (int i = 0; i < invalid.length; i++)
				System.out.println("    " + invalid[i]);
		}
	}

	public void messagePartiallyDelivered(TransportEvent e)
	{
		System.out.println(">>> TransportListener.messagePartiallyDelivered().");
		System.out.println(" Valid Addresses:");
		Address[] valid = e.getValidSentAddresses();
		if (valid != null) {
			for (int i = 0; i < valid.length; i++)
				System.out.println("    " + valid[i]);
		}
		System.out.println(" Valid Unsent Addresses:");
		Address[] unsent = e.getValidUnsentAddresses();
		if (unsent != null) {
			for (int i = 0; i < unsent.length; i++)
				System.out.println("    " + unsent[i]);
		}
		System.out.println(" Invalid Addresses:");
		Address[] invalid = e.getInvalidAddresses();
		if (invalid != null) {
			for (int i = 0; i < invalid.length; i++)
				System.out.println("    " + invalid[i]);
		}
	}

	public static void main(String[] args)
	{
		VerificationMailSender sender = new VerificationMailSender();
		sender.sendMail("user@qqgo.cc", 1, "abcde");
	}
}
