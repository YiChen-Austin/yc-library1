package com.mall.web.mobile.third.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.util.QRcodeUtil;
import com.mall.web.mall.common.tag.MallJstlFunction;
import com.mall.web.mall.common.utils.NarrowImage;
import com.mall.web.mall.common.vo.DataJsonBean;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.third.qq.service.WeichatSubscribeService;
import com.mall.web.mall.third.wechat.config.WechatConfig;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.common.util.WeixinSHA1;
import com.mall.web.mobile.third.util.Message;
import com.tencent.common.Configure;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @Description(描述) : 微信登录
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年11月3日 上午9:48:35
 */
@Controller
@RequestMapping("/mobile/wechat")
public class MobWeixinCbMsgController {
	private static Logger logger = Logger
			.getLogger(MobWeixinCbMsgController.class);
	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;
	@Resource(name = "weichatSubscribeService")
	private WeichatSubscribeService weichatSubscribeService;

	// 自定义 token
	private static final String TOKEN = "2q98ruewsoifjmlkdsjfqehr0p09";
	/**
	 * 请求消息类型：事件
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(关注)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消关注)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	/**
	 * @Description(功能描述) : 微信回调
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年10月20日 下午5:37:23
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/wxmsg", method = RequestMethod.GET)
	public String wxmsgGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");

		String[] str = { TOKEN, BaseUtil.isEmpty(timestamp)?"":timestamp, BaseUtil.isEmpty(nonce)?"":nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = new WeixinSHA1().getDigestOfString(bigStr.getBytes())
				.toLowerCase();

		// 确认请求来至微信
		if (digest.equals(signature)) {
			response.getWriter().print(echostr);
		}
		String wxMsgXml = IOUtils.toString(request.getInputStream(), "utf-8");
		System.out.println(">>>>>>>get wxMsgXml:" + wxMsgXml);
		return null;
	}

	/**
	 * @Description(功能描述) : 微信回调
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年10月20日 下午5:37:23
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/wxmsg", method = RequestMethod.POST)
	public String wxmsgPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		String wxMsgXml = IOUtils.toString(request.getInputStream(), "utf-8");

		System.out.println(">>>>>>>post wxMsgXml:" + wxMsgXml);

		Message textMsg = null;
		try {
			textMsg = getMessage(wxMsgXml);
			if (textMsg != null) {
				// 事件
				if ("event".equalsIgnoreCase(textMsg.getMessageType())) {
					doEventRsp(request, textMsg, pw);
				}// 普通消息
				else {
					doMessageRsp(textMsg, pw);
				}
			} else {
				pw.write("");
				pw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Message getMessage(String xml) {
		try {
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("xml", Message.class);
			if (xml.indexOf("ToUserName") >= 0)
				xstream.aliasField("ToUserName", Message.class, "toUserName");
			if (xml.indexOf("FromUserName") >= 0)
				xstream.aliasField("FromUserName", Message.class,
						"fromUserName");
			if (xml.indexOf("CreateTime") >= 0)
				xstream.aliasField("CreateTime", Message.class, "createTime");
			if (xml.indexOf("MsgType") >= 0)
				xstream.aliasField("MsgType", Message.class, "messageType");
			if (xml.indexOf("Content") >= 0)
				xstream.aliasField("Content", Message.class, "content");
			if (xml.indexOf("MsgId") >= 0)
				xstream.aliasField("MsgId", Message.class, "msgId");
			if (xml.indexOf("Event") >= 0)
				xstream.aliasField("Event", Message.class, "event");
			if (xml.indexOf("EventKey") >= 0)
				xstream.aliasField("EventKey", Message.class, "eventKey");
			if (xml.indexOf("Ticket") >= 0)
				xstream.aliasField("Ticket", Message.class, "ticket");
			if (xml.indexOf("Latitude") >= 0)
				xstream.aliasField("Latitude", Message.class, "latitude");
			if (xml.indexOf("Longitude") >= 0)
				xstream.aliasField("Longitude", Message.class, "longitude");
			if (xml.indexOf("Precision") >= 0)
				xstream.aliasField("Precision", Message.class, "precision");
			if (xml.indexOf("MenuId") >= 0)
				xstream.aliasField("MenuId", Message.class, "menuId");
			Message Message = (Message) xstream.fromXML(xml);
			return Message;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return null;
	}

	private void doEventRsp(HttpServletRequest request, Message msg,
			PrintWriter pw) {
		switch (msg.getEvent()) {
		case "subscribe":// 订阅
			if (BaseUtil.isNotEmpty(msg.getEventKey())
					&& BaseUtil.isNotEmpty(msg.getTicket())) {
				pw.write(getXfsAutoMsg(request, msg));
			} else {
				pw.write(getNormalAutoMsg(msg));
				weichatSubscribeService.doWeichatSubscribe(
						msg.getToUserName(), msg.getFromUserName(),
						msg.getCreateTime(), msg.getEvent(), 0);
			}
			pw.flush();
			break;
		case "SCAN":// 扫描
			if (BaseUtil.isNotEmpty(msg.getEventKey())
					&& BaseUtil.isNotEmpty(msg.getTicket())) {
				pw.write(getXfsAutoMsg(request, msg));
			} else {
				pw.write(getNormalAutoMsg(msg));
			}
			pw.flush();
			break;
		case "unsubscribe":// 取消订阅
			pw.write("");
			pw.flush();
			weichatSubscribeService.doWeichatSubscribe(msg.getToUserName(),
					msg.getFromUserName(), msg.getCreateTime(), msg.getEvent(),
					0);
			break;
		// case "CLICK":// 事件类型
		// // 我的二维码
		// if ("MY_CODE".equalsIgnoreCase(msg.getEventKey())) {
		//
		// } else {
		//
		// }
		// pw.write("");
		// pw.flush();
		// break;
		default:
			pw.write("");
			pw.flush();
			break;
		}

	}

	private void doMessageRsp4PicTxt(Message msg, PrintWriter pw) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + msg.getFromUserName()
				+ "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[" + msg.getToUserName()
				+ "]]></FromUserName>");
		sb.append("<CreateTime>"
				+ Integer.toString((int) (System.currentTimeMillis() / 1000))
				+ "</CreateTime>");
		sb.append("<MsgType><![CDATA[news]]></MsgType>");
		sb.append("<ArticleCount>1</ArticleCount>");
		sb.append("<Articles>");
		sb.append("<item>");
		sb.append("<Title><![CDATA[亲，下载全球狗app，有更优惠、更多惊喜，更多机会等着您哟!]]></Title>");
		sb.append("<Description><![CDATA[亲，下载全球狗app，有更优惠、更多惊喜，更多机会等着您哟!]]></Description>");
		sb.append("<PicUrl><![CDATA[]]></PicUrl>");
		sb.append("<Url><![CDATA[http://a.app.qq.com/o/simple.jsp?pkgname=com.wzmall.shopping.main.controller]]></Url>");
		sb.append("</item>");
		sb.append("</Articles>");
		sb.append("</xml>");
		pw.write(sb.toString());
		pw.flush();
		System.out.println(sb.toString());
	}

	private void doMessageRsp(Message msg, PrintWriter pw) {
		StringBuffer sb = new StringBuffer(DataJsonBean.autoMsgJson4n);
		String xml = sb.toString();
		xml = xml.replace("{toUserName}", msg.getFromUserName());
		xml = xml.replace("{fromUserName}", msg.getToUserName());
		xml = xml.replace("{time}",
				Integer.toString((int) (System.currentTimeMillis() / 1000)));
		pw.write(xml);
		pw.flush();
		System.out.println(xml);
	}

	/**
	 * 自动回复（含消费商）
	 */
	private String getXfsAutoMsg(HttpServletRequest request, Message msg) {
		String scene_id = msg.getEventKey().indexOf("qrscene_") >= 0 ? msg
				.getEventKey().substring("qrscene_".length()) : msg
				.getEventKey();
		int userId = 0;
		Member userInfo = null;
		// 10000以内这无需变换userId
		if (Integer.parseInt(scene_id) < WechatConfig.WxLimtPos) {
			userId = Integer.parseInt(scene_id);
			userInfo = webMemberService.getMember(userId);
		} else {
			userId = Integer.parseInt(scene_id) + WechatConfig.WxStartPos;
			userInfo = webMemberService.getMember(userId);
		}
		if (userInfo == null) {
			weichatSubscribeService.doWeichatSubscribe(msg.getToUserName(),
					msg.getFromUserName(), msg.getCreateTime(), msg.getEvent(),
					0);
			return "";
		}
		try {
			request.getSession().setAttribute(UserUtil.WX_QR_GZH, userId);/*
			if (BaseUtil.isEmpty(userInfo.getWxQrCode())) {
				Date date = DateUtil.getGMTDate();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy/MM/dd/HH/mm/ss");
				String dateStr = sdf.format(date);
				String uploadPath = "/" + dateStr;
				if (!new File(uploadPath).isDirectory()) {
					new File(uploadPath).mkdirs();
				}
				String fileName = Integer.toString(userInfo.getUserId())
						+ System.currentTimeMillis() + ".jpg";

				// 微信二维码
				String accessToken = WechatConfig.getWxPlatAccessToken(
						Configure.getAppID4mp(), Configure.getAppSecret4mp());
				String ticket = WechatConfig.wxQrTicket(accessToken, scene_id);
				WechatConfig.saveQrCodeImg(URLEncoder.encode(ticket, "UTF-8"),
						fileName, uploadPath);

				String newFileName = uploadPath + "/to" + fileName;
				// 合成需要
				NarrowImage narrowImage = new NarrowImage();
				OutputStream os = new FileOutputStream(newFileName);
				QRcodeUtil.encodeSpread(narrowImage.zoomImage(uploadPath + "/"
						+ fileName, 0.512f), userInfo.getRealName(), os);

				//userInfo.setWxQrCode(dateStr + "/to" + fileName);
				//userInfo.setWxQrCode(userInfo.getWxQrCode());
				webMemberService.updateMember(userInfo);// 修改用户信息
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		StringBuffer sb = new StringBuffer(DataJsonBean.autoMsgJson4p);
		String xml = sb.toString();
		xml = xml.replace("{toUserName}", msg.getFromUserName());
		xml = xml.replace("{fromUserName}", msg.getToUserName());
		xml = xml.replace("{time}",
				Integer.toString((int) (System.currentTimeMillis() / 1000)));
		xml = xml.replace("{userName}", userInfo.getRealName());
		xml = xml.replace(
				"{userPic}",
				"http://pic.qqgo.cc/"
						+ MallJstlFunction.PictureServerChange(
								userInfo.getPortrait(), 0, null, null));
		System.out.println(xml);
		weichatSubscribeService.doWeichatSubscribe(msg.getToUserName(),
				msg.getFromUserName(), msg.getCreateTime(), msg.getEvent(),
				userId);
		return xml;
	}

	/**
	 * 自动恢复（普通回复）
	 */
	private String getNormalAutoMsg(Message msg) {
		StringBuffer sb = new StringBuffer(DataJsonBean.autoMsgJson);
		String xml = sb.toString();
		xml = xml.replace("{toUserName}", msg.getFromUserName());
		xml = xml.replace("{fromUserName}", msg.getToUserName());
		xml = xml.replace("{time}",
				Integer.toString((int) (System.currentTimeMillis() / 1000)));
		return xml.toString();
	}
}
