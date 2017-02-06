package com.mall.common.mail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.BaseUtil;

/**
 * @功能说明：发送邮件工具类
 * @作者： 印鲜刚
 * @创建日期： 2010-9-13
 */
public class SendMail
{
	protected static Log log = LogFactory.getLog(SendMail.class);

	private MimeMessage mimeMsg;
	private Session session;
	private Properties props;
	private String username;
	private String password;
	private Multipart mp;

	public SendMail(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}

	public void setSmtpHost(String hostName) {
		if (props == null) {
			props = System.getProperties();
		}
		props.put("mail.smtp.host", hostName);
		props.put("mail.smtp.auth", "true");
	}

	public boolean createMimeMessage() {
		try {
			session = Session.getDefaultInstance(props, null);
			session.setDebug(false);
		} catch (Exception exception) {
			log.info("获取邮件会话对象时发生错误!");
			return false;
		}
		try {
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();
			return true;
		} catch (Exception exception1) {
			log.info("创建MIME邮件对象失败!");
		}
		return false;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNamePass(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * 设置邮件主题。
	 * @param mailSubject	邮件主题
	 * @return	操作是否成功
	 */
	public boolean setSubject(String mailSubject) {
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception exception) {
			log.info("设置邮件主题发生错误!");
		}
		return false;
	}

	/**
	 * 设置邮件正文。接受 HTML 内容，并且文本编码固定为GBK。
	 * @param mailBody
	 * @return	操作是否成功
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent(mailBody, "text/html;charset=GBK");
			mp.addBodyPart(bp);
			return true;
		} catch (Exception exception) {
			log.info("设置邮件内容出错！");
			return false;
		}
	}

	/**
	 * 添加邮件附件。
	 * @param file
	 * @param actualFileName
	 * @return
	 */
	public boolean addFileAffix(File file, String actualFileName) {
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(file);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(MimeUtility.encodeText(actualFileName, "GBK", "B"));
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			log.info("增加邮件附件发生错误！");
		}
		return false;
	}

	/**
	 * 添加邮件附件。
	 * @param file
	 * @return
	 */
	public boolean addFileAffix(File file) {
		return this.addFileAffix(file, file.getName());
	}

	/**
	 * 设置发件人。
	 * @param from
	 * @param person
	 * @return
	 */
	public boolean setFrom(String from, String person) {
		try {
			if (!BaseUtil.isEmpty(person))
				mimeMsg.setFrom(new InternetAddress(from, person));
			else
				mimeMsg.setFrom(new InternetAddress(from));
			return true;
		} catch (Exception exception) {
			log.info("发送人邮件地址:" + from + "发生错误！");
			return false;
		}
	}

	/**
	 * 设置发件人。
	 * @param from
	 * @return
	 */
	public boolean setFrom(String from) {
		return setFrom(from, null);
	}

	/**
	 * 设置收件人。
	 * @param to
	 * @return
	 */
	public boolean setTo(String to) {
		if (to == null)
			return false;
		try {
			mimeMsg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception exception) {
			log.info("接收人邮件地址:" + to + "发生错误！");
			return false;
		}
	}

	/**
	 * 设置抄送人邮件地址。
	 * @param copyto
	 * @return
	 */
	public boolean setCC(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(javax.mail.Message.RecipientType.CC, (javax.mail.Address[]) InternetAddress
					.parse(copyto));
			return true;
		} catch (Exception exception) {
			log.info("抄送人邮件地址:" + copyto + "发生错误！");
			return false;
		}
	}

	/**
	 * 设置密送人邮件地址。
	 * @param bcc
	 * @return
	 */
	public boolean setBCC(String bcc) {
		if (bcc == null)
			return false;
		try {
			mimeMsg.setRecipients(javax.mail.Message.RecipientType.BCC, (javax.mail.Address[]) InternetAddress
					.parse(bcc));
			return true;
		} catch (Exception exception) {
			log.info("秘送人邮件地址:" + bcc + "发生错误！");
			return false;
		}
	}

	/**
	 * 执行发送。
	 * @return
	 * @throws Exception
	 */
	public boolean sendout() throws Exception {
		Transport transport = null;
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			Session mailSession = Session.getInstance(props, null);
			transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), this.username, this.password);
			try {
				if (!BaseUtil.isEmpty(mimeMsg.getRecipients(javax.mail.Message.RecipientType.TO))) {
					transport.sendMessage(mimeMsg, mimeMsg.getRecipients(javax.mail.Message.RecipientType.TO));
				}
			} catch (Exception to) {
				throw new Exception(CommonConstant.MAIL_EXCEPTION_TO);
			}
			try {
				if (!BaseUtil.isEmpty(mimeMsg.getRecipients(javax.mail.Message.RecipientType.CC))) {
					transport.sendMessage(mimeMsg, mimeMsg.getRecipients(javax.mail.Message.RecipientType.CC));
				}
			} catch (Exception cc) {
				throw new Exception(CommonConstant.MAIL_EXCEPTION_CC);
			}
			try {
				if (!BaseUtil.isEmpty(mimeMsg.getRecipients(javax.mail.Message.RecipientType.BCC))) {
					transport.sendMessage(mimeMsg, mimeMsg.getRecipients(javax.mail.Message.RecipientType.BCC));
				}
			} catch (Exception bcc) {
				throw new Exception(CommonConstant.MAIL_EXCEPTION_BCC);
			}
			transport.close();
			log.info("发送邮件成功!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("请检查邮件相关参数设置是否正确，发送发生异常:" + e.getMessage());
			transport.close();
			throw new Exception(e.getMessage());
		}
	}
}
