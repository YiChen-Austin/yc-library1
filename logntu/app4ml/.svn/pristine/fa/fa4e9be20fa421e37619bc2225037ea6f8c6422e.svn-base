package com.mall.web.mall.third.wechat.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ResourceUtils;

import net.sf.json.JSONObject;

import com.mall.web.mobile.third.util.WechatOpenInfo;
import com.mall.web.mobile.third.util.WechatUserInfo;
import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;

/**
 * @Description(描述) : 微信网站登录配置
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年11月9日 上午11:02:13
 */
public class WechatConfig {

	public static final int WxLimtPos = 10000;
	public static final int WxStartPos = 3253022 - WxLimtPos;

	public static String GetCodeUrl = "https://open.weixin.qq.com/connect/qrconnect?"; // 微信获取地址
	public static String GetAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?"; // 微信获取地址
	// public static String AppID = "wxa0f66b3b35160592"; //
	// 应用唯一标识，在微信开放平台提交应用审核通过后获得
	// public static String AppSecret = "d4624c36b6795d1d99dcf0547af5443d"; //
	// 应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
	public static String GrantTypeCode = "authorization_code"; // 授予类型
	public static String GrantTypeToken = "access_token"; // 授予类型
	public static String RedirectUri = "http://www.qqgo.cc/wechat/afterlogin"; // 回调地址
	public static String ScopeLogin = "snsapi_login"; // 用户授权的作用域(用于登录)
	public static String ResponseType = "code"; // 填code(请求code标识)

	public static String RedirectUri4Wxpay = "http://m.qqgo.cc/pay/wxpay/"; // 支付回调
	public static String GetAuthorizeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize"; // 微信获取地址（验证）
	public static String SnsapiBase = "snsapi_base"; // 用户授权的作用域(用于登录)
	public static String SnsapiUserinfo = "snsapi_userinfo"; // 用户授权的作用域(用于登录，获取用户信息)
	public static String WechatRedirect = "wechat_redirect"; // 微信规格调整

	public static String RedirectUri4Login = "http://m.qqgo.cc/wechat/afterlogin"; // 微信窗口回调

	public static String GetUserinfoUrl = "https://api.weixin.qq.com/sns/userinfo"; // 微信获取用户信息（验证）

	public static JsapiTicket jsapiTicket = null;

	/****************/
	// public static String MobileAppID = "wxf5ca1a0e4a201bac"; // 移动端appid
	// public static String MobileAppSecret =
	// "d4624c36b6795d1d99dcf0547af5443d"; // 移动端应用密钥AppSecret

	/**
	 * @Description(功能描述) : 发送get请求
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月17日 下午1:46:43
	 * @param url
	 *            : 请求地址
	 * @return : 返回json字符串
	 */
	public static String sendGet(String url) {
		String result = "";
		// BufferedReader in = null;
		InputStream input = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			// in = new BufferedReader(new InputStreamReader(
			// connection.getInputStream()));
			// String line;
			// while ((line = in.readLine()) != null) {
			// result += line;
			// }
			int length = 0;
			input = connection.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] content = new byte[512];
			while ((length = input.read(content)) > 0) {
				output.write(content, 0, length);
			}
			result = new String(output.toByteArray(), "UTF8");
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	// 获取微信openId
	public static WechatOpenInfo getUserOpenId(HttpServletRequest request,
			String appid, String key) throws Exception {
		String code = request.getParameter("code");
		String url = WechatConfig.GetAccessTokenUrl + "appid=" + appid
				+ "&secret=" + key + "&code=" + code + "&grant_type="
				+ WechatConfig.GrantTypeCode;
		String result = WechatConfig.sendGet(url.toString());
		JSONObject obj = JSONObject.fromObject(result);
		if (!obj.containsKey("errcode")) { // 验证成功
			// String openID = obj.getString("openid");
			return new WechatOpenInfo(obj);
		}
		return null;
	}

	// 获取微信openId
	public static WechatUserInfo getUserInfo(String openId, String accessToken)
			throws Exception {
		String url = WechatConfig.GetUserinfoUrl + "?access_token="
				+ accessToken + "&openid=" + openId + "&lang=zh_CN";
		String result = WechatConfig.sendGet(url.toString());
		JSONObject obj = JSONObject.fromObject(result);
		if (!obj.containsKey("errcode")) { // 验证成功
			// String openID = obj.getString("openid");
			return new WechatUserInfo(obj);
		}
		return null;
	}

	// 获取微信openId
	public static WechatOpenInfo getUserOpenId4M(String code, String appId,
			String key) throws Exception {
		String url = WechatConfig.GetAccessTokenUrl + "appid=" + appId
				+ "&secret=" + key + "&code=" + code + "&grant_type="
				+ WechatConfig.GrantTypeCode;
		String result = WechatConfig.sendGet(url.toString());
		JSONObject obj = JSONObject.fromObject(result);
		if (!obj.containsKey("errcode")) { // 验证成功
			// String openID = obj.getString("openid");
			return new WechatOpenInfo(obj);
		}
		return null;
	}

	/**
	 * 后台控制微信平台（自定义菜单），所需 Access_Token
	 * 
	 * @param appId
	 * @param appsecret
	 *            密钥
	 */
	public static String getWxPlatAccessToken(String appId, String appsecret)
			throws Exception {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ appId + "&secret=" + appsecret;
		String result = WechatConfig.sendGet(url.toString());
		JSONObject obj = JSONObject.fromObject(result);
		if (!obj.containsKey("errcode")) { // 验证成功
			return obj.getString("access_token");
		}
		return null;
	}

	/**
	 * 根据 Access_Token，获取jsapi_ticket
	 * 
	 * @param appId
	 * @param appsecret
	 *            密钥
	 */
	public static String getWxPlatJsapiTicket() throws Exception {
		if (jsapiTicket == null || jsapiTicket.isValid() == false) {
			String accessToken = getWxPlatAccessToken(Configure.getAppID4mp(),
					Configure.getAppSecret4mp());
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
					+ accessToken + "&type=jsapi";
			String result = WechatConfig.sendGet(url.toString());
			JSONObject obj = JSONObject.fromObject(result);
			if (obj.containsKey("ticket")) { // 验证成功
				jsapiTicket = new JsapiTicket();
				jsapiTicket.setTicket(obj.getString("ticket"));
			}
		}
		return jsapiTicket == null ? null : jsapiTicket.getTicket();
	}

	/**
	 * 获取微信js配置信息
	 * 
	 * @param appId
	 * @param appsecret
	 *            密钥
	 */
	public static Map<String, Object> getWxConfig(String url) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noncestr", RandomStringGenerator.getRandomStringByLength(32));
		map.put("timestamp", Long.toString(System.currentTimeMillis() / 1000));
		map.put("url", url);
		map.put("jsapi_ticket", getWxPlatJsapiTicket());
		// map.put("noncestr", "Wm3WZYTPz0wzccnW");
		// map.put("jsapi_ticket",
		// "sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg");
		// map.put("timestamp", "1414587457");
		// map.put("url", "http://mp.weixin.qq.com?params=value");
		String signature = Signature.getSign4Sha1(map);
		map.remove("url");
		map.remove("jsapi_ticket");
		map.put("signature", signature);
		map.put("appId", Configure.getAppID4mp());
		return map;
	}

	public static void main4menu(String[] args) throws Exception {
		try {
			File file = ResourceUtils
					.getFile("classpath:mall/weixin/weixin_menu.json");
			String menuJson = FileUtils.readFileToString(file, "UTF-8");
			// 获取accessToken -参数appid，secret
			String accessToken = getWxPlatAccessToken(Configure.getAppID4mp(),
					Configure.getAppSecret4mp());
			System.out.println(accessToken);
			// 创建菜单
			// String s =
			// "{\"button\":[{\"name\":\"休闲娱乐\",\"sub_button\":[{\"type\":\"click\",\"name\":\"笑话大全\",\"key\":\"m_joke\"},{\"type\":\"click\",\"name\":\"内涵段子\",\"key\":\"m_duanzi\"},{\"type\":\"click\",\"name\":\"爆笑图片\",\"key\":\"m_laughImg\"}]},{\"name\":\"实用工具\",\"sub_button\":[{\"type\":\"click\",\"name\":\"天气查询\",\"key\":\"m_weather\"},{\"type\":\"click\",\"name\":\"公交查询\",\"key\":\"m_bus\"}]},{\"type\":\"click\",\"name\":\"关于企特\",\"key\":\"m_about\"}]}";
			String res = deleteMenu(accessToken);
			System.out.println("deleteMenu:" + res);
			res = createMenu(menuJson, accessToken);
			System.out.println("createMenu:" + res);
			// String res = wxQrTicket(accessToken, "0");
			// saveQrCodeImg(URLEncoder.encode(res, "UTF-8"), "qrcode.jpg",
			// "d:/xxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			// System.out.println("getWxPlatJsapiTicket:" + jsapiTicket + ","
			// + getWxConfig("http://m.qqgo.cc/share/wxshare"));

			/*String accessToken = WechatConfig.getWxPlatAccessToken(
					Configure.getAppID4mp(), Configure.getAppSecret4mp());
			String ticket = WechatConfig.wxQrTicket(accessToken, "3");
			System.out.println(ticket);
			ticket = WechatConfig.wxQrTicket(accessToken, "4");
			System.out.println(ticket);
			ticket = WechatConfig.wxQrTicket(accessToken, "5");
			System.out.println(ticket);
			ticket = WechatConfig.wxQrTicket(accessToken, "6");
			System.out.println(ticket);
			ticket = WechatConfig.wxQrTicket(accessToken, "7");
			System.out.println(ticket);
			ticket = WechatConfig.wxQrTicket(accessToken, "8");
			System.out.println(ticket);
			ticket = WechatConfig.wxQrTicket(accessToken, "9");
			System.out.println(ticket);
			ticket = WechatConfig.wxQrTicket(accessToken, "10");
			System.out.println(ticket);
			ticket = WechatConfig.wxQrTicket(accessToken, "11");
			System.out.println(ticket);
			ticket = WechatConfig.wxQrTicket(accessToken, "12");
			System.out.println(ticket);*/
			main4menu(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HttpClient httpclient;
	static {
		httpclient = new DefaultHttpClient();
		// 接受任何证书的浏览器客户端
	}

	/**
	 * 创建菜单
	 */
	public static String createMenu(String params, String accessToken)
			throws Exception {
		HttpPost httpost = getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ accessToken);
		httpost.setEntity(new StringEntity(params, "UTF-8"));
		HttpResponse response = httpclient.execute(httpost);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject obj = JSONObject.fromObject(jsonStr);
		return obj.getString("errmsg");
	}

	/**
	 * 查询菜单
	 */
	public static String getMenuInfo(String accessToken) throws Exception {
		HttpGet get = getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token="
				+ accessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		return jsonStr;
	}

	/**
	 * 删除自定义菜单
	 */
	public static String deleteMenu(String accessToken) throws Exception {
		HttpGet get = getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="
				+ accessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject obj = JSONObject.fromObject(jsonStr);
		return obj.getString("errmsg");
	}

	/**
	 * 永久二维码请求说明ticket
	 */
	public static String wxQrTicket(String accessToken, String scene_id)
			throws Exception {
		HttpPost httpost = getPostMethod("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
				+ accessToken);
		httpost.setEntity(new StringEntity(
				"{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\":"
						+ scene_id + "}}}", "UTF-8"));
		HttpResponse response = httpclient.execute(httpost);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject obj = JSONObject.fromObject(jsonStr);
		return obj.getString("ticket");
	}

	/**
	 * 永久二维码请求说明图片
	 */
	public static void saveQrCodeImg(String accessToken, String filename,
			String savePath) throws Exception {
		// 构造URL
		URL url = new URL("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="
				+ accessToken);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

	/**
	 * 模拟浏览器post提交
	 * 
	 * @param url
	 * @return
	 */
	public static HttpPost getPostMethod(String url) {
		HttpPost pmethod = new HttpPost(url); // 设置响应头信息
		pmethod.addHeader("Connection", "keep-alive");
		pmethod.addHeader("Accept", "*/*");
		pmethod.addHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		pmethod.addHeader("Host", "mp.weixin.qq.com");
		pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
		pmethod.addHeader("Cache-Control", "max-age=0");
		pmethod.addHeader("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		return pmethod;
	}

	/**
	 * 模拟浏览器GET提交
	 * 
	 * @param url
	 * @return
	 */
	public static HttpGet getGetMethod(String url) {
		HttpGet pmethod = new HttpGet(url);
		// 设置响应头信息
		pmethod.addHeader("Connection", "keep-alive");
		pmethod.addHeader("Cache-Control", "max-age=0");
		pmethod.addHeader("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		pmethod.addHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/;q=0.8");
		return pmethod;
	}
}
