package com.mall.web.mall.common.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.PicStringUtil;
import com.mall.web.mall.common.utils.SearchConditionUtil;

/**
 * jstl推展
 * 
 * 辅助url等计算
 */
public class MallJstlFunction {
	/**
	 * 替换相同参数值，如果值不存在，则增加该参数及值
	 * 
	 * @param url
	 *            基础url
	 * @param params
	 *            参数对
	 * @param key
	 *            需要替换的参数名
	 * @param value
	 *            替换后的参数值
	 */
	public static String urlReplaceValue(String url, String params, String key,
			String value) {
		if (params == null || params.length() == 0)
			return url
					+ (url.indexOf("?") < 0 ? "?" + key + "=" + value : "&"
							+ key + "=" + value);
		if (params.indexOf(key) < 0)
			return url
					+ (url.indexOf("?") < 0 ? "?" + params + "&" + key + "="
							+ value : "&" + params + "&" + key + "=" + value);
		String[] paras = params.split("&");
		String tmepPara = "";
		for (String p : paras) {
			if (p.indexOf(key) >= 0) {
				p = key + "=" + value;
			}
			if (tmepPara.length() > 0)
				tmepPara = tmepPara + "&" + p;
			else
				tmepPara = tmepPara + p;
		}
		return url + (url.indexOf("?") < 0 ? "?" + tmepPara : tmepPara);
	}

	/**
	 * 替换相同参数值，如果值不存在，则增加该参数及值
	 * 
	 * 其中分页参数默认为第一页
	 * 
	 * @param url
	 *            基础url
	 * @param params
	 *            参数对
	 * @param key
	 *            需要替换的参数名
	 * @param value
	 *            替换后的参数值
	 */
	public static String urlReplaceValueDefaultPage(String url, String params,
			String key, String value) {
		return urlReplaceValues(url, params, new String[] { key, "curPage" },
				new String[] { value, "1" });
	}

	/**
	 * 替换相同参数值，如果值不存在，则增加该参数及值
	 * 
	 * @param url
	 *            基础url
	 * @param params
	 *            参数对
	 * @param keys
	 *            需要替换的参数名
	 * @param values
	 *            替换后的参数值
	 */
	public static String urlReplaceValues(String url, String params,
			String[] keys, String[] values) {
		if (keys == null || values == null || keys.length != values.length) {
			if (params == null || params.length() == 0)
				return url;
			else
				return url + "?" + params;
		}
		String tmepPara = "";
		if (params == null || params.length() == 0) {
			tmepPara = "";
			for (int i = 0; i < keys.length; i++) {
				if (tmepPara.length() > 0)
					tmepPara = tmepPara + "&" + keys[i] + "=" + values[i];
				else
					tmepPara = tmepPara + keys[i] + "=" + values[i];
			}
			return url + (url.indexOf("?") < 0 ? "?" + tmepPara : tmepPara);
		}

		String[] paras = params.split("&");
		tmepPara = "";
		for (String p : paras) {
			for (int i = 0; i < keys.length; i++) {
				if (p.indexOf(keys[i]) >= 0) {
					p = keys[i] + "=" + values[i];
					break;
				}
			}
			if (tmepPara.length() > 0)
				tmepPara = tmepPara + "&" + p;
			else
				tmepPara = tmepPara + p;
		}
		for (int i = 0; i < keys.length; i++) {
			if (tmepPara.indexOf(keys[i]) >= 0) {
				continue;
			}
			if (tmepPara.length() > 0)
				tmepPara = tmepPara + "&" + keys[i] + "=" + values[i];
			else
				tmepPara = tmepPara + keys[i] + "=" + values[i];
		}
		return url + (url.indexOf("?") < 0 ? "?" + tmepPara : tmepPara);
	}

	/**
	 * 去除指定参数
	 */
	public static String urlRemoveKey(String url, String params, String key) {
		if (params == null || params.length() == 0)
			return url;
		if (params.indexOf(key) < 0)
			return url + "?" + params;
		String[] paras = params.split("&");
		String tmepPara = "";
		for (String p : paras) {
			if (p.indexOf(key) >= 0) {
				continue;
			}
			if (tmepPara.length() > 0)
				tmepPara = tmepPara + "&" + p;
			else
				tmepPara = tmepPara + p;
		}
		return url + (url.indexOf("?") < 0 ? "?" + tmepPara : tmepPara);
	}

	/**
	 * 去除指定参数及分页参数
	 */
	public static String urlRemoveKeyPage(String url, String params, String key) {
		return urlRemoveKeys(url, params, new String[] { key, "curPage" });
	}

	/**
	 * 去除指定参数
	 */
	public static String urlRemoveKeystr(String url, String params, String keys) {
		return urlRemoveKeys(url, params, keys.split(","));
	}

	/**
	 * 去除指定参数
	 */
	public static String urlRemoveKeys(String url, String params, String[] keys) {
		if (keys == null || keys.length <= 0) {
			if (params == null || params.length() == 0)
				return url;
			else
				return url + "?" + params;
		}
		if (params == null || params.length() == 0)
			return url;

		String[] paras = params.split("&");
		String tmepPara = "";
		for (String p : paras) {
			for (int i = 0; i < keys.length; i++) {
				if (p.indexOf(keys[i]) >= 0) {
					p = "";
					break;
				}
			}
			if (p.length() <= 0) {
				continue;
			}
			if (tmepPara.length() > 0)
				tmepPara = tmepPara + "&" + p;
			else
				tmepPara = tmepPara + p;
		}
		return url + (url.indexOf("?") < 0 ? "?" + tmepPara : tmepPara);
	}

	/**
	 * @author caokw
	 * @param path
	 *            图片路径，格式：2014/10/15/store_000.jpg
	 * @param method
	 *            功能，1表示缩放
	 * @return
	 */
	public static String PictureServer(String path, Integer method) {
		return PictureServerChange(path, method, null, null);

	}

	//http://pic.qqgo.cc /2015/11/21/1448073332791.jpeg
	public static void main(String[] args) {//  2015/10/01/1443685472567.jpg
		System.out.println(PictureServerChange2("2015/01/08/lunbo_go_banner_0120160111.jpg", 1, null, null));
		System.out.println(PictureServerChange2("2015/01/08/lunbo_6plus20160111.jpg", 1, null, null));
		System.out.println(PictureServerChange2("2015/01/08/lunbo_Vivo-x6plus20160111.jpg", 1, null, null));
		System.out.println(PictureServerChange2("2015/01/08/lunbo_VIVO-y35a20160111.jpg", 1, null, null));
		System.out.println(PictureServerChange2("2015/01/08/lunbo_p8banner20160111.jpg", 1, null, null));
		System.out.println(PictureServerChange2("2015/01/08/lunbo_OPPOr7sm_banner20160111.jpg", 1, null, null));
	}
	/**
	 * @author caokw
	 * @param path
	 *            图片路径，格式：2014/10/15/store_000.jpg
	 * @param method
	 *            功能，1表示缩放
	 * @param height
	 *            图片高度
	 * @param width
	 *            图片宽度
	 * @return
	 */
	public static String PictureServerChange(String path, Integer method,
			Integer width, Integer height) {
		if (path == null || path.length() <= 0)
			return "";
		StringBuilder sb = new StringBuilder("A/");
		String[] pSplit=path.split("[//\\.]");
		if (pSplit.length==8)// 秒下面的图片
		{
			String DateInfo = String.format("%s/%s/%s %s:%s:%s", pSplit[0],
					pSplit[1], pSplit[2], pSplit[3], pSplit[4],
					pSplit[5]);
			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			Calendar c = Calendar.getInstance();
			c.setTimeZone(TimeZone.getDefault());
			c.setTime(new Date(DateInfo));
			long day = c.getTimeInMillis() / 1000;// 十进制秒数
			sb.append(method).append(Long.toHexString(day)).append("!")
					.append(pSplit[6]+"."+pSplit[7]);
			if (height != null && width != null) {
				sb.append("_").append(width).append("x").append(height)
						.append("."+pSplit[7]);
			}
		} else if (pSplit.length==5){
			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			Calendar c = Calendar.getInstance();
			c.setTimeZone(TimeZone.getDefault());
			c.setTime(new Date(pSplit[0]+"/"+pSplit[1]+"/"+pSplit[2]));
			long day = c.getTimeInMillis() / 86400000;// 十进制天数
			sb.append(method).append(Long.toHexString(day)).append("!")
					.append(pSplit[3]+"."+pSplit[4]);
			if (height != null && width != null) {
				sb.append("_").append(width).append("x").append(height)
						.append("."+pSplit[4]);
			}
		}

		return sb.toString();
	}
	/**
	 * @author caokw
	 * @param path
	 *            图片路径，格式：2014/10/15/store_000.jpg
	 * @param method
	 *            功能，1表示缩放
	 * @param height
	 *            图片高度
	 * @param width
	 *            图片宽度
	 * @return
	 */
	public static String PictureServerChange2(String path, Integer method,
			Integer width, Integer height) {
		if (path == null || path.length() <= 0)
			return "";
		StringBuilder sb = new StringBuilder("A/");
		List<String> list = new ArrayList<String>();//2015/11/21/1448073332791.jpeg
		Pattern pattern_hand = Pattern
				.compile("^(\\d{4})/(\\d{2})/(\\d{2})/(\\d{2})/(\\d{2})/(\\d{2})/(.*(.{6}))$");
		Matcher matcher_hand = pattern_hand.matcher(path);
		if (matcher_hand.find())// 秒下面的图片
		{
			int gc = matcher_hand.groupCount();
			for (int i = 0; i <= gc; i++) {
				list.add(matcher_hand.group(i));
			}
			String DateInfo = String.format("%s/%s/%s %s:%s:%s", list.get(1),
					list.get(2), list.get(3), list.get(4), list.get(5),
					list.get(6));
			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			Calendar c = Calendar.getInstance();
			c.setTimeZone(TimeZone.getDefault());
			c.setTime(new Date(DateInfo));
			long day = c.getTimeInMillis() / 1000;// 十进制秒数
			sb.append(method).append(Long.toHexString(day)).append("!")
					.append(list.get(7));
			if (height != null && width != null) {
				sb.append("_").append(width).append("x").append(height)
						.append(list.get(8));
			}
		} else {
			pattern_hand = Pattern
					.compile("^(\\d{4}/\\d{2}/\\d{2})/(.*(.{6}))$");
			matcher_hand = pattern_hand.matcher(path);
			if (matcher_hand.find()) {
				int gc = matcher_hand.groupCount();
				for (int i = 0; i <= gc; i++) {
					list.add(matcher_hand.group(i));
				}
			} else {
				return "";
			}

			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			Calendar c = Calendar.getInstance();
			c.setTimeZone(TimeZone.getDefault());
			c.setTime(new Date(list.get(1)));
			long day = c.getTimeInMillis() / 86400000;// 十进制天数
			sb.append(method).append(Long.toHexString(day)).append("!")
					.append(list.get(2));
			if (height != null && width != null) {
				sb.append("_").append(width).append("x").append(height)
						.append(list.get(3));
			}
		}

		return sb.toString();
	}
	/**
	 * 反解析加密路径为真实物理路径
	 * 
	 * @param encodePicPath
	 *            加密路径
	 * @return
	 */
	public static String decodePicPath(String encodePicPath) {
		// 检查请求参数格式
		String picPattern = "^([0-9])(\\w+)!(.+.([a-zA-Z]{3,4}))_(\\d+)x(\\d+).\\4$";
		String picSourcePattern = "^([0-9])(\\w+)!(.+.([a-zA-Z]{3,4}))$";
		if (PicStringUtil.isMatch(encodePicPath, picPattern)) {
			List<String> list = PicStringUtil.patternMatch(encodePicPath,
					picPattern);
			// 处理参数
			String path = (String) list.get(2);
			String imageName = (String) list.get(3);
			// 获取服务器文件路径
			String pathPhysical = null;
			if (path.length() == 4) {// 年月日
				pathPhysical = PicStringUtil.dateFormatPathByDay(path);
			} else {// 年月日时分秒
				pathPhysical = PicStringUtil.dateFormatPathBySecond(path);
			}
			if (pathPhysical != null) {
				StringBuffer serverPath = new StringBuffer();
				serverPath.append(pathPhysical);
				serverPath.append(imageName);
				return serverPath.toString().substring(1);
			}
		} else if (PicStringUtil.isMatch(encodePicPath, picSourcePattern)) {
			List<String> list = PicStringUtil.patternMatch(encodePicPath,
					picSourcePattern);
			// 处理参数
			String path = (String) list.get(2);
			String imageName = (String) list.get(3);

			// 获取服务器文件路径
			String pathPhysical = null;
			if (path.length() == 4) {// 年月日
				pathPhysical = PicStringUtil.dateFormatPathByDay(path);
			} else {// 年月日时分秒
				pathPhysical = PicStringUtil.dateFormatPathBySecond(path);
			}
			if (pathPhysical != null) {
				StringBuffer serverPath = new StringBuffer();
				serverPath.append(pathPhysical);
				serverPath.append(imageName);
				return serverPath.toString().substring(1);
			}
		}
		return "";
	}

	/**
	 * @author caokw
	 * @param 日期格式转换
	 * @param format
	 *            日期格式
	 * @param Date
	 *            日期对象
	 * @return
	 */
	public static String DateTimeFormat(String format, Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * @author caokw
	 * @param 过滤html标签
	 * @param inputString
	 *            html字符串
	 */
	public static String FilterHtmlTags(String inputString) {
		String htmlStr = inputString;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			// 不能引错包
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }

			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }

			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr;
		} catch (Exception e) {
			return "";
		}
		return textStr;// 返回文本字符串
	}

	/**
	 * @author caokw
	 * @param 截取字符串
	 * @param name
	 *            字符串
	 * @param length
	 *            截取的长度 append 追加的字符
	 * @return
	 */
	public static String subString(String name, Integer length, String append) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			if (name.length() > length) {
				result.append(name.substring(0, length - 1));
				if (append != null && append.length() > 0) {
					result.append(append);
				}
			} else {
				result.append(name);
			}
		}
		return result.toString();
	}

	/*************************/
	// rest
	/**
	 * @param catagoryId
	 *            分类id
	 * @param zoneId
	 *            地区id
	 * @param pageNum
	 *            分页数
	 * @param keyword
	 *            关键字
	 * @return 条件字符串
	 */
	public static String connectCondi(String cityId, String catagoryId,
			String zoneId, String pageNum, String keyword, String subRegion,
			String lng, String lat) {
		return SearchConditionUtil.connectCondi(cityId, catagoryId, zoneId,
				pageNum, keyword, subRegion, lng, lat);
	}

	/**
	 * @param catagoryId
	 *            分类id
	 * @param zoneId
	 *            地区id
	 * @param pageNum
	 *            分页数
	 * @param keyword
	 *            关键字
	 * @return 条件字符串
	 */
	public static String connectCondiEx(String cityId, String catagoryId,
			String zoneId, String pageNum, String keyword, String subRegion,
			String lng, String lat, String okey, String ovalue, String distance) {
		return SearchConditionUtil.connectCondi(cityId, catagoryId, zoneId,
				pageNum, keyword, subRegion, lng, lat, okey, ovalue, distance);
	}

	/**
	 * @param prefixUrl
	 *            前缀链接
	 * @param suffixUrl
	 *            后缀链接
	 * @param pageNum
	 *            分页数
	 * @param queryString
	 *            其他查询链接
	 * @return 条件字符串
	 */
	public static String rest2pageUrl(String prefixUrl, String suffixUrl,
			String pageNume, String queryString) {
		StringBuffer sb = new StringBuffer();
		if (!BaseUtil.isEmpty(prefixUrl)) {
			sb.append(prefixUrl);
		}
		if (!BaseUtil.isEmpty(pageNume)) {
			sb.append("p" + pageNume);
		}
		if (!BaseUtil.isEmpty(suffixUrl)) {
			sb.append(suffixUrl);
		}
		if (!BaseUtil.isEmpty(queryString)) {
			sb.append("?" + queryString);
		}
		return sb.toString();
	}

	/*************************/
	/**
	 * @author caokw
	 * @param 将换行符替换为
	 * <br>
	 *            标签
	 * @param content
	 *            内容
	 * @return
	 */
	public static String BrChar(String content) {
		if (content != null && content.length() > 0) {
			return content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		}
		return content;
	}

	/*************************/
	/**
	 * @author ventrue
	 * @param 解析和处理产品属性
	 * 
	 * @param attrId
	 *            属性Id
	 * @param attrName
	 *            属性名称
	 * @param inputMode
	 *            属性类型
	 * @param defValue
	 *            可选值
	 * 
	 * @param vIds
	 *            选中值id串
	 * @param vNames
	 *            选中值name串
	 * 
	 * @return
	 */
	public static String pulishGoodPro(String attrId, String attrName,
			String inputMode, String defValue, String vIds, String vNames,
			String gcheck, String isRequired) {
		// 类型为空，则不处理
		if (inputMode == null || inputMode.length() <= 0)
			return "";
		// 属性id为空，则不处理
		if (attrId == null || attrId.length() <= 0)
			return "";
		// 属性id为空，则不处理
		if (attrName == null || attrName.length() <= 0)
			return "";
		String tIds = null;
		if (vIds != null && vIds.length() > 0) {
			tIds = "," + vIds + ",";
		}
		String tNames = null;
		if (vNames != null && vNames.length() > 0) {
			tNames = "," + vNames + ",";
		}
		try {
			// 特别处理品牌类可编辑
			if ("select_edit".equalsIgnoreCase(inputMode)) {
				ObjectMapper mapper = new ObjectMapper();
				LinkedHashMap<String, Object> map = mapper.readValue(defValue,
						new TypeReference<Object>() {
						});
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> data = (List<LinkedHashMap<String, Object>>) map
						.get("data");
				if (data == null || data.size() <= 0)
					return "";
				StringBuffer sb = new StringBuffer();
				sb.append("<li class=\"J_spu-property\" id=\"spu_" + attrId
						+ "\" inputMode=\"" + inputMode + "\" name=\"spus\">");
				sb.append("<label class=\"label-title\">" + attrName
						+ ":</label>");
				sb.append("<span>"
						+ ("required".equalsIgnoreCase(isRequired) ? "<em>*</em>"
								: "") + "");
				sb.append("<ul class=\"J_ul-single J_is-mainprop ul-select\"><li>");
				sb.append("<div class=\"kui-combobox\" role=\"combobox\">");
				sb.append("<div class=\"kui-dropdown-trigger\">");
				sb.append("<label class=\"kui-placeholder\" style=\""
						+ (vNames == null ? "" : "display:none;")
						+ "\">可直接输入内容</label>");
				sb.append("<input class=\"kui-combobox-caption\" type=\"text\" style=\"width: 150px;\" role=\"textbox\" aria-autocomplete=\"list\" aria-haspopup=\"true\" id=\"simulate-prop_"
						+ attrId
						+ "\" aria-owns=\"kui-list-"
						+ attrId
						+ "\" value=\"" + vNames + "\" data-db-json=\"json\" data-psid=\""+attrId+"\">");
				sb.append("<div class=\"kui-icon-dropdown\"></div>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("<select name=\"cp_"
						+ attrId
						+ "\" id=\"prop_"
						+ attrId
						+ "\" class=\"keyPropClass\" data-transtype=\"dropbox\" style=\"display: none; visibility: hidden;\">");
				sb.append("<option class=\"J_empty\" value=\"\"></option>");
				for (LinkedHashMap<String, Object> value : data) {
					sb.append("<option value=\"" + value.get("key"));
					// 判断是否选中
					if (tIds != null
							&& tIds.indexOf("," + value.get("key") + ",") >= 0) {
						sb.append("\" selected=\"selected\">");
					} else
						sb.append("\">");
					sb.append(value.get("value"));
					sb.append("</option>");
				}
				sb.append("</select>");
				sb.append("</li></ul></span>");
				sb.append("<input type=\"hidden\" id=\"nav_cp_" + attrId
						+ "\" data-feed=\"err_NotNull_" + attrId + "\">");
				sb.append("<div id=\"err_NotNull_" + attrId
						+ "\" style=\"display: none;\"></div>");
				sb.append("</li>");
				return sb.toString();
			} else // 1、 处理select单选则类型
			if ("select".equalsIgnoreCase(inputMode)) {
				ObjectMapper mapper = new ObjectMapper();
				LinkedHashMap<String, Object> map = mapper.readValue(defValue,
						new TypeReference<Object>() {
						});
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> data = (List<LinkedHashMap<String, Object>>) map
						.get("data");
				if (data == null /* || data.size() <= 0 */)
					return "";
				StringBuffer sb = new StringBuffer();
				sb.append("<li class=\"J_spu-property\" id=\"spu_" + attrId
						+ "\" inputMode=\"" + inputMode + "\" name=\"spus\">");
				sb.append("<label class=\"label-title\">" + attrName
						+ ":</label>");
				sb.append("<span>"
						+ ("required".equalsIgnoreCase(isRequired) ? "<em>*</em>"
								: "") + "");
				sb.append("<ul class=\"J_ul-single ul-select\"><li>");
				sb.append("<div class=\"kui-combobox\" role=\"combobox\">");
				sb.append("<div class=\"kui-dropdown-trigger\">");
				sb.append("<input class=\"kui-combobox-caption\" style=\"width: 150px;\" readonly=\"true\" role=\"textbox\" aria-autocomplete=\"list\" aria-haspopup=\"true\" id=\"simulate-prop_"
						+ attrId
						+ "\" aria-expanded=\"false\" aria-owns=\"kui-list-"
						+ attrId + "\" value=\"" + vNames + "\" data-db-json=\"json\" data-psid=\""+attrId+"\">");
				sb.append("<div class=\"kui-icon-dropdown\"></div>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("<select name=\"cp_"
						+ attrId
						+ "\" id=\"prop_"
						+ attrId
						+ "\" data-transtype=\"dropbox\" style=\"display: none; visibility: hidden;\">");
				sb.append("<option value=\"\"></option>");
				for (LinkedHashMap<String, Object> value : data) {
					sb.append("<option value=\"" + value.get("key"));
					// 判断是否选中
					if (tIds != null
							&& tIds.indexOf("," + value.get("key") + ",") >= 0) {
						sb.append("\" selected=\"selected\">");
					} else
						sb.append("\">");
					sb.append(value.get("value"));
					sb.append("</option>");
				}
				sb.append("</select>");
				sb.append("</li></ul></span>");
				sb.append("<input type=\"hidden\" id=\"nav_cp_" + attrId
						+ "\" data-feed=\"err_NotNull_" + attrId + "\">");
				sb.append("<div id=\"err_NotNull_" + attrId
						+ "\" style=\"display: none;\"></div>");
				sb.append("</li>");
				return sb.toString();
			}// 2、 处理checkbox多选框
			else if ("checkbox".equalsIgnoreCase(inputMode)) {
				ObjectMapper mapper = new ObjectMapper();
				LinkedHashMap<String, Object> map = mapper.readValue(defValue,
						new TypeReference<Object>() {
						});
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> data = (List<LinkedHashMap<String, Object>>) map
						.get("data");
				if (data == null || data.size() <= 0)
					return "";
				StringBuffer sb = new StringBuffer();
				sb.append("<li class=\"J_spu-property\" id=\"spu_" + attrId
						+ "\" inputMode=\"" + inputMode + "\" name=\"spus\">");
				sb.append("<label class=\"label-title\">" + attrName
						+ ":</label>");
				sb.append("<span>"
						+ ("required".equalsIgnoreCase(isRequired) ? "<em>*</em>"
								: "") + "");
				sb.append("<ul class=\"J_ul-multi ul-checkbox\">");
				for (LinkedHashMap<String, Object> value : data) {
					sb.append("<li><input type=\"checkbox\" name=\"cp_"
							+ attrId + "\" value=\""
							+ (String) value.get("key") + "\" id=\"prop_"
							+ attrId + "\" class=\"checkbox\"");
					// 判断是否选中
					if (tIds != null
							&& tIds.indexOf((String) value.get("key")) >= 0) {
						sb.append(" checked=\"checked\">");
					} else
						sb.append(">");
					sb.append("<label>" + value.get("value") + "</label>");
					sb.append("</li>");
				}
				sb.append("</ul></span>");
				sb.append("<input type=\"hidden\" id=\"nav_cp_" + attrId
						+ "\" data-feed=\"err_NotNull_" + attrId + "\">");
				sb.append("<div id=\"err_NotNull_" + attrId
						+ "\" style=\"display: none;\"></div>");
				sb.append("</li>");
				return sb.toString();
			}// 3、 处理input单输入框
			else if ("input".equalsIgnoreCase(inputMode)) {
				StringBuffer sb = new StringBuffer();
				sb.append("<li class=\"J_spu-property\" name=\"keySpus\" id=\"spu_"
						+ attrId + "\" inputMode=\"" + inputMode + "\">");
				sb.append("<label class=\"label-title\">" + attrName
						+ ":</label>");
				sb.append("<span>"
						+ ("required".equalsIgnoreCase(isRequired) ? "<em>*</em>"
								: "") + "");
				sb.append("<input type=\"text\" id=\"prop_" + attrId
						+ "\" name=\"cp_" + attrId
						+ "\" class=\"text text-long\"");
				if (tNames != null && tNames.length() > 0)
					sb.append("value=\"" + vNames + "\">");
				else {
					sb.append("value=\"\">");
				}
				sb.append("</span>");
				sb.append("<input type=\"hidden\" id=\"nav_cp_" + attrId
						+ "\" data-feed=\"err_NotNull_" + attrId + "\">");
				sb.append("<div id=\"err_NotNull_" + attrId
						+ "\" style=\"display: none;\"></div>");
				sb.append("</li>");
				return sb.toString();
			}// 4、 处理mutiinput多个输入框组
			else if ("mutiinput".equalsIgnoreCase(inputMode)) {
				ObjectMapper mapper = new ObjectMapper();
				LinkedHashMap<String, Object> map = mapper.readValue(defValue,
						new TypeReference<Object>() {
						});
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> data = (List<LinkedHashMap<String, Object>>) map
						.get("data");
				if (data == null || data.size() <= 0)
					return "";
				String[] values = null;
				StringBuffer sb = new StringBuffer();
				sb.append("<li class=\"measure J_spu-property\" id=\"spu_"
						+ attrId + "\" inputMode=\"" + inputMode
						+ "\" name=\"spus\">");
				sb.append("<label class=\"label-title\">" + attrName
						+ ":</label>");
				sb.append("<span class=\"wrap-mt\">"
						+ ("required".equalsIgnoreCase(isRequired) ? "<em>*</em>"
								: "") + "");
				if (vNames != null && data.size() == vNames.split("\t").length) {
					values = vNames.split("\t");
				}
				for (LinkedHashMap<String, Object> value : data) {
					String type = (String) value.get("type");
					if ("lable".equalsIgnoreCase(type)) {
						sb.append("<lable class=\"title\" for=\"" + attrId
								+ "_" + value.get("key") + "\">"
								+ value.get("value")
								+ "<input type=\"hidden\" name=\"cp_" + attrId
								+ "_" + value.get("key") + "\" value=\""
								+ value.get("value") + "\"></lable>");
					} else if ("code".equalsIgnoreCase(type)) {
						sb.append("<code class=\"operator\">"
								+ value.get("value")
								+ "<input type=\"hidden\" name=\"cp_" + attrId
								+ "_" + value.get("key") + "\" value=\""
								+ value.get("value") + "\"></code>");
					} else if ("unit".equalsIgnoreCase(type)) {
						sb.append("<span class=\"unit\">" + value.get("value")
								+ "<input type=\"hidden\" name=\"cp_" + attrId
								+ "_" + value.get("key") + "\" value=\""
								+ value.get("value") + "\"></span>");
					} else {
						sb.append("<input name=\"cp_"
								+ attrId
								+ "_"
								+ value.get("key")
								+ "\" class=\"text text-min\" value=\""
								+ (values == null || values.length <= 0 ? ""
										: values[Integer
												.parseInt((String) value
														.get("key")) - 1])
								+ "\" type=\"text\">");
					}
				}
				sb.append("</span>");
				sb.append("<input type=\"hidden\" id=\"nav_cp_" + attrId
						+ "\" data-feed=\"err_NotNull_" + attrId + "\">");
				sb.append("<div id=\"err_NotNull_" + attrId
						+ "\" style=\"display: none;\"></div>");
				sb.append("</li>");
				return sb.toString();
			}
			/**************************/
			// 5、 处理sku-checkbox多选框(sku中普通checkbox)
			else if ("sku-checkbox".equalsIgnoreCase(inputMode)) {
				ObjectMapper mapper = new ObjectMapper();
				LinkedHashMap<String, Object> map = mapper.readValue(defValue,
						new TypeReference<Object>() {
						});
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> data = (List<LinkedHashMap<String, Object>>) map
						.get("data");
				if (data == null || data.size() <= 0)
					return "";
				StringBuffer sb = new StringBuffer();
				sb.append("<div data-caption=\"" + attrName + "\" data-pid=\""
						+ attrId + "\" inputMode=\"" + inputMode
						+ "\" class=\"sku-group\" data-features=\"\">");
				sb.append("<label class=\"sku-label\">" + attrName
						+ ":</label>");
				sb.append("<div class=\"sku-box\">");
				sb.append("<ul class=\"sku-list\">");
				for (LinkedHashMap<String, Object> value : data) {
					sb.append("<li class=\"sku-item\"><input type=\"checkbox\" name=\"cp_"
							+ attrId
							+ "\" value=\""
							+ (String) value.get("key")
							+ "\" id=\"prop_"
							+ attrId
							+ "-"
							+ (String) value.get("key")
							+ "\" class=\"J_Checkbox\"");
					// 判断是否选中
					if (tIds != null
							&& tIds.indexOf((String) value.get("key")) >= 0) {
						sb.append(" checked=\"checked\">");
					} else
						sb.append(">");
					sb.append("<label class=\"labelname\" for=\"prop_" + attrId
							+ "-" + (String) value.get("key") + "\" title=\""
							+ value.get("value") + "\">" + value.get("value")
							+ "</label>");
					sb.append("</li>");
				}
				sb.append("</ul></div>");
				sb.append("</div>");
				return sb.toString();
			}// 6、 处理sku-c-checkbox多选框(sku中普通checkbox，在标题中显示颜色)
			else if ("sku-c-checkbox".equalsIgnoreCase(inputMode)) {
				ObjectMapper mapper = new ObjectMapper();
				LinkedHashMap<String, Object> map = mapper.readValue(defValue,
						new TypeReference<Object>() {
						});
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> data = (List<LinkedHashMap<String, Object>>) map
						.get("data");
				if (data == null || data.size() <= 0)
					return "";
				StringBuffer sb = new StringBuffer();
				sb.append("<div data-caption=\""
						+ attrName
						+ "\" data-pid=\""
						+ attrId
						+ "\" inputMode=\""
						+ inputMode
						+ "\" class=\"sku-group\" data-features=\"image edit\">");
				sb.append("<label class=\"sku-label\">" + attrName
						+ ":</label>");
				sb.append("<div class=\"sku-box sku-color\">");
				sb.append("<div style=\"background-color: #fff;border: 1px solid #D7D7D7;\">");
				sb.append("<ul class=\"sku-list\" style=\"padding: 10px 10px 0;\">");
				for (LinkedHashMap<String, Object> value : data) {
					// 判断是否选中
					if (tIds != null
							&& tIds.indexOf("," + (String) value.get("key")
									+ ",") >= 0) {
						sb.append("<li class=\"sku-item edit\"><input type=\"checkbox\" name=\"cp_"
								+ attrId
								+ "\" value=\""
								+ (String) value.get("key")
								+ "\" id=\"prop_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\" class=\"J_Checkbox\" data-color=\""
								+ (String) value.get("remark") + "\"");
						sb.append(" checked=\"checked\">");
					} else {
						sb.append("<li class=\"sku-item\"><input type=\"checkbox\" name=\"cp_"
								+ attrId
								+ "\" value=\""
								+ (String) value.get("key")
								+ "\" id=\"prop_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\" class=\"J_Checkbox\" data-color=\""
								+ (String) value.get("remark") + "\"");
						sb.append(">");
					}
					// 花色
					if ("130164".equalsIgnoreCase((String) value.get("key"))) {
						sb.append(" <label class=\"color-lump color-assortment\" for=\"prop_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\"></label>");
					}// 透明
					else if ("107121".equalsIgnoreCase((String) value
							.get("key"))) {
						sb.append(" <label class=\"color-lump color-transparent\" for=\"prop_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\"></label>");
					} else {
						sb.append(" <label class=\"color-lump\" style=\"background-color:#"
								+ value.get("remark")
								+ ";\" for=\"prop_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\"></label>");
					}
					sb.append(" <label class=\"labelname\" for=\"prop_"
							+ attrId + "_" + (String) value.get("key")
							+ "\" title=\"" + value.get("value") + "\">"
							+ value.get("value") + "</label>");
					// 判断是否选中
					if (tIds != null
							&& tIds.indexOf("," + (String) value.get("key")
									+ ",") >= 0) {
						String vv = (String) value.get("value");
						if (vNames != null) {
							String[] ids = vIds.split(",");
							String[] names = vNames.split(",");
							for (int i = 0; i < ids.length
									&& ids.length == names.length; i++) {
								if (ids[i].equalsIgnoreCase((String) value
										.get("key"))) {
									vv = names[i];
								}
							}
						}
						sb.append(" <input id=\"J_Alias_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\" class=\"editbox text\" maxlength=\"15\" type=\"text\" value=\""
								+ vv + "\" name=\"cpva_" + attrId + ":"
								+ (String) value.get("key") + "\">");
					} else {
						sb.append(" <input id=\"J_Alias_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\" class=\"editbox text\" maxlength=\"15\" type=\"text\" value=\""
								+ value.get("value") + "\" name=\"cpva_"
								+ attrId + ":" + (String) value.get("key")
								+ "\">");
					}

					sb.append("</li>");
				}
				sb.append("</ul></div>");

				sb.append("<ul class=\"clearfix\" id=\"sku-color-custom\">");

				String[] ids = (vIds == null ? new String[] {} : vIds
						.split(","));
				String[] names = (vNames == null ? new String[] {} : vNames
						.split(","));

				for (int ind = 0; ind < ids.length
						&& ids.length == names.length; ind++) {
					if(ids[ind].length()<=0)
						continue;
					int v = Integer.parseInt(ids[ind]);
					if (v < 0) {
						sb.append("<li class=\"custom-list edit\">");
						sb.append("<input class=\"J_Checkbox\" type=\"checkbox\" data-color=\"\" checked=\"checked\" name=\"cp_"
								+ attrId
								+ "\" value=\""
								+ attrId
								+ ":"
								+ v
								+ "\" data-path=\"\" data-thumb=\"\" id=\"prop_"
								+ attrId
								+ "-"
								+ v
								+ "\"><input class=\"text color-text color-textbox\" type=\"text\" name=\"cp_intput_cpv_"
								+ attrId
								+ "_"
								+ v
								+ "\" value=\""
								+ names[ind]
								+ "\"  placeholder=\""
								+ names[ind]
								+ "\" maxlength=\"16\" id=\"J_note_"
								+ attrId
								+ "-" + v + "\"><i class=\"icon\"></i>");
					}
				}
				boolean isfirst = true;
				for (int i = -6; i < 0; i++) {
					if (vIds.indexOf("," + i + ",") >= 0)
						continue;
					sb.append("<li class=\"custom-list\""
							+ (isfirst == true ? ""
									: " style=\"display: none;\"") + ">");
					sb.append("<input class=\"J_Checkbox\" type=\"checkbox\" data-color=\"\" name=\"cp_"
							+ attrId
							+ "\" value=\""
							+ attrId
							+ ":"
							+ i
							+ "\" data-path=\"\" data-thumb=\"\" id=\"prop_"
							+ attrId
							+ "-"
							+ i
							+ "\"><input class=\"text color-text color-textbox\" type=\"text\" name=\"cp_intput_cpv_"
							+ attrId
							+ "_"
							+ i
							+ "\"  placeholder=\"其它颜色\" maxlength=\"16\" id=\"J_note_"
							+ attrId + "-" + i + "\"><i class=\"icon\"></i>");
					sb.append("</li>");
					isfirst = false;
				}
				sb.append("</ul>");
				sb.append("</div></div>");
				return sb.toString();
			}// 6、 处理sku-g-c-checkbox多选框(sku中普通checkbox，颜色分组)
			else if ("sku-g-c-checkbox".equalsIgnoreCase(inputMode)) {
				ObjectMapper mapper = new ObjectMapper();
				LinkedHashMap<String, Object> map = mapper.readValue(defValue,
						new TypeReference<Object>() {
						});
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) map
						.get("data");
				if (data == null || data.size() <= 0)
					return "";
				StringBuffer sb = new StringBuffer();
				sb.append("<div data-caption=\""
						+ attrName
						+ "\" data-pid=\""
						+ attrId
						+ "\" inputMode=\""
						+ inputMode
						+ "\" class=\"sku-group\" data-features=\"image edit\">");
				sb.append("<label class=\"sku-label\">" + attrName
						+ ":</label>");
				sb.append("<div class=\"sku-box sku-color\" id=\"sku-color-wrap\">");
				sb.append("<div id=\"sku-color-tab\">");

				// 处理分组tab
				sb.append("<ul class=\"clearfix\" id=\"sku-color-tab-header\">");
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> group = (List<LinkedHashMap<String, Object>>) data
						.get("group");
				int seq = 0;
				for (LinkedHashMap<String, Object> g : group) {
					sb.append("<li" + (seq == 0 ? " class=\"selected\"" : "")
							+ " index=\"" + g.get("key") + "\">");
					sb.append("<div class=\"rgb-box\" style=\"background: "
							+ g.get("remark") + ";\"></div>");
					sb.append("<div class=\"color-text\">" + g.get("value")
							+ "</div>");
					sb.append("</li>");
					seq++;
				}
				sb.append("</ul>");

				// 处理内容
				sb.append("<div id=\"sku-color-tab-contents\">");
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> gdata = (List<LinkedHashMap<String, Object>>) data
						.get("gdata");
				seq = 0;
				for (LinkedHashMap<String, Object> vdate : gdata) {
					sb.append("<ul class=\"color-list clearfix\" id=\"" + seq
							+ "\" "
							+ (seq == 0 ? "" : "style=\"display: none;\"")
							+ ">");
					@SuppressWarnings("unchecked")
					List<LinkedHashMap<String, Object>> ldata = (List<LinkedHashMap<String, Object>>) vdate
							.get("data");
					for (LinkedHashMap<String, Object> value : ldata) {
						sb.append("<li>");
						sb.append("<label data-text=\"" + value.get("value")
								+ "\">");
						sb.append("<input class=\"J_Checkbox\" type=\"checkbox\" data-text=\""
								+ value.get("value")
								+ "\" name=\"cp_"
								+ attrId
								+ "\" id=\"prop_"
								+ attrId
								+ "-"
								+ value.get("key")
								+ "\" value=\""
								+ value.get("key")
								+ "\" data-path=\"\" data-thumb=\"\">");
						sb.append("<i class=\"color-box\" style=\"background:"
								+ value.get("remark") + "\"></i>");
						sb.append(value.get("value"));
						sb.append("</label>");
						sb.append("</li>");
					}
					sb.append("</ul>");
					seq++;
				}
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
				return sb.toString();
			}// 7、 处理sku-s-checkbox多选框(尺寸)
			else if ("sku-s-checkbox".equalsIgnoreCase(inputMode)) {
				ObjectMapper mapper = new ObjectMapper();
				LinkedHashMap<String, Object> map = mapper.readValue(defValue,
						new TypeReference<Object>() {
						});
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> data = (List<LinkedHashMap<String, Object>>) map
						.get("data");
				if (data == null || data.size() <= 0)
					return "";
				StringBuffer sb = new StringBuffer();
				sb.append("<div data-caption=\"" + attrName + "\" data-pid=\""
						+ attrId + "\" inputMode=\"" + inputMode
						+ "\" class=\"sku-group sku-size\" data-features=\"\">");
				sb.append("<label class=\"sku-label\">" + attrName
						+ ":</label>");
				sb.append("<div class=\"sku-box sku-size-wrap\">");
				sb.append("<div style=\"padding: 10px 0 0 10px;\">");
				sb.append("<ul class=\"sku-list\">");
				for (LinkedHashMap<String, Object> value : data) {
					// 判断是否选中
					if (tIds != null
							&& tIds.indexOf("," + (String) value.get("key")
									+ ",") >= 0) {
						sb.append("<li class=\"sku-item edit\"><input type=\"checkbox\" name=\"cp_"
								+ attrId
								+ "\" value=\""
								+ (String) value.get("key")
								+ "\" id=\"prop_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\" class=\"J_Checkbox\"");
						sb.append(" checked=\"checked\">");
					} else {
						sb.append("<li class=\"sku-item\"><input type=\"checkbox\" name=\"cp_"
								+ attrId
								+ "\" value=\""
								+ (String) value.get("key")
								+ "\" id=\"prop_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\" class=\"J_Checkbox\"");
						sb.append(">");
					}
					sb.append("<label class=\"labelname\" for=\"prop_" + attrId
							+ "-" + (String) value.get("key") + "\" title=\""
							+ value.get("value") + "\">" + value.get("value")
							+ "</label>");
					if (tIds != null
							&& tIds.indexOf("," + (String) value.get("key")
									+ ",") >= 0) {
						String vv = (String) value.get("value");
						if (vNames != null) {
							String[] ids = vIds.split(",");
							String[] names = vNames.split(",");
							for (int i = 0; i < ids.length
									&& ids.length == names.length; i++) {
								if (ids[i].equalsIgnoreCase((String) value
										.get("key"))) {
									vv = names[i];
								}
							}
						}
						sb.append(" <input id=\"J_Alias_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\" class=\"editbox text\" maxlength=\"15\" type=\"text\" value=\""
								+ vv + "\" name=\"cpva_" + attrId + ":"
								+ (String) value.get("key") + "\">");
					} else {
						sb.append(" <input id=\"J_Alias_"
								+ attrId
								+ "-"
								+ (String) value.get("key")
								+ "\" class=\"editbox text\" maxlength=\"15\" type=\"text\" value=\""
								+ value.get("value") + "\" name=\"cpva_"
								+ attrId + ":" + (String) value.get("key")
								+ "\">");
					}
					sb.append("</li>");
				}
				sb.append("</ul></div>");

				sb.append("<ul class=\"size-diy\" id=\"sku-size-custom\">");
				String[] ids = (vIds == null ? new String[] {} : vIds
						.split(","));
				String[] names = (vNames == null ? new String[] {} : vNames
						.split(","));

				for (int ind = 0; ind < ids.length
						&& ids.length == names.length; ind++) {
					if(ids[ind].length()<=0)
						continue;
					int v = Integer.parseInt(ids[ind]);
					if (v < 0) {
						sb.append("<li class=\"custom-list eidt\">");
						sb.append("<input type=\"checkbox\" class=\"J_Checkbox other-check\" checked=\"checked\" name=\"cp_"
								+ attrId
								+ "\" value=\""
								+ attrId
								+ ":"
								+ v
								+ "\" id=\"prop_"
								+ attrId
								+ "-"
								+ v
								+ "\"><label class=\"labelname hidden\">其它尺码</label><input type=\"text\" class=\"other-input text\" placeholder=\""
								+ names[ind]
								+ "\" value=\""
								+ names[ind]
								+ "\" maxlength=\"16\" name=\"cp_intput_cpv_"
								+ attrId + "_" + v + "\">");
					}
				}

				boolean isfirst = true;
				for (int i = -6; i < 0; i++) {
					if (vIds.indexOf("," + i + ",") >= 0)
						continue;
					sb.append("<li class=\"custom-list\""
							+ (isfirst == true ? ""
									: " style=\"display: none;\"") + ">");
					sb.append("<input type=\"checkbox\" class=\"J_Checkbox other-check\" name=\"cp_"
							+ attrId
							+ "\" value=\""
							+ attrId
							+ ":"
							+ i
							+ "\" id=\"prop_"
							+ attrId
							+ "-"
							+ i
							+ "\"><label class=\"labelname hidden\">其它尺码</label><input type=\"text\" class=\"other-input text\" placeholder=\"其它尺码\" value=\"其它尺码\" maxlength=\"16\" name=\"cp_intput_cpv_"
							+ attrId + "_" + i + "\">");
					sb.append("</li>");

					isfirst = false;
				}
				sb.append("</ul>");
				sb.append("</div></div>");
				return sb.toString();
			}// 8、 处理sku-g-s-checkbox多选框(尺寸分组)
			else if ("sku-g-s-checkbox".equalsIgnoreCase(inputMode)) {
				ObjectMapper mapper = new ObjectMapper();
				LinkedHashMap<String, Object> map = mapper.readValue(defValue,
						new TypeReference<Object>() {
						});
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) map
						.get("data");
				if (data == null || data.size() <= 0)
					return "";
				StringBuffer sb = new StringBuffer();
				sb.append("<div data-caption=\""
						+ attrName
						+ "\" data-pid=\""
						+ attrId
						+ "\" inputMode=\""
						+ inputMode
						+ "\" class=\"sku-switchable sku-group sku-size\" data-features=\"edit\">");
				sb.append("<label class=\"sku-label\">" + attrName
						+ ":</label>");
				sb.append("<div class=\"sku-box\"");
				sb.append("<div class=\"sku-size-wrap\">");
				sb.append("<div class=\"size-select\">");
				// 处理分组tab
				sb.append("<ul class=\"size-type\">");
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> group = (List<LinkedHashMap<String, Object>>) data
						.get("group");
				int seq = 0;
				for (LinkedHashMap<String, Object> g : group) {
					sb.append("<li>");
					sb.append("<label>");
					sb.append("<input class=\"type-radio\" type=\"radio\" name=\"sizeGroupType\" value=\""
							+ g.get("key")
							+ "-men_tops\" checked=\""
							+ (((String) g.get("key")).equalsIgnoreCase(gcheck)
									|| (gcheck == null && seq == 0) ? "checked"
									: "") + "\">");
					sb.append(g.get("value"));
					sb.append("</label>");
					sb.append("</li>");
					seq++;
				}
				sb.append("</ul>");

				// 处理内容
				sb.append("<div class=\"size-content\">");
				@SuppressWarnings("unchecked")
				List<LinkedHashMap<String, Object>> gdata = (List<LinkedHashMap<String, Object>>) data
						.get("gdata");
				seq = 0;
				for (LinkedHashMap<String, Object> vdate : gdata) {
					sb.append("<ul class=\"size-pannel\" id=\"J_SizePannel_"
							+ group.get(seq).get("key")
							+ "-men_tops\" style=\""
							+ (((String) group.get(seq).get("key"))
									.equalsIgnoreCase(gcheck)
									|| (gcheck == null && seq == 0) ? ""
									: "display:none;") + "\">");
					@SuppressWarnings("unchecked")
					List<LinkedHashMap<String, Object>> ldata = (List<LinkedHashMap<String, Object>>) vdate
							.get("data");
					for (LinkedHashMap<String, Object> value : ldata) {
						sb.append("<li class=\"sku-item\">");
						sb.append("<input class=\"J_Checkbox\" type=\"checkbox\" name=\"cp_"
								+ attrId
								+ "\" id=\"prop_"
								+ attrId
								+ "-"
								+ value.get("key")
								+ "\" value=\""
								+ value.get("key")
								+ "\" data-path=\"\" data-thumb=\"\">");
						sb.append("<label class=\"labelname\" for=\"prop_"
								+ attrId + "-" + value.get("key")
								+ "\" title=\"" + value.get("value") + "\">");
						sb.append(value.get("value"));
						sb.append("</label>");
						sb.append("</li>");
					}
					sb.append("</ul>");
					seq++;
				}
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static void main2(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		String defValue = "";
		// 普通属性select
		defValue += "{\"data\" :";
		defValue += "[";
		defValue += "{\"key\":\"908772475\",\"value\":\"1me/1米\"},";
		defValue += "{\"key\":\"670370619\",\"value\":\"78点\"},";
		defValue += "{\"key\":\"568142695\",\"value\":\"ABLonG/爱宝隆\"}";
		defValue += "]";
		defValue += "}";
		// select
		// System.out.println(pulishGoodPro("2676886", "冰箱冷柜机型", "select_edit",
		// defValue, "670370619", null, null));
		/*************************/
		// 普通属性input
		// System.out.println(pulishGoodPro("2676886", "冰箱冷柜机型", "input", null,
		// null, null, null));
		/*************************/
		// 普通属性多输入 mutiinput
		defValue = "";
		defValue += "{\"data\" :";
		defValue += "[";
		defValue += "{\"key\":\"1\",\"value\":\"宽\",\"type\":\"lable\"},";
		defValue += "{\"key\":\"2\",\"value\":\"\",\"type\":\"input\"},";
		defValue += "{\"key\":\"3\",\"value\":\"x\",\"type\":\"code\"},";
		defValue += "{\"key\":\"4\",\"value\":\"深(厚)\",\"type\":\"lable\"},";
		defValue += "{\"key\":\"5\",\"value\":\"\",\"type\":\"input\"},";
		defValue += "{\"key\":\"6\",\"value\":\"x\",\"type\":\"code\"},";
		defValue += "{\"key\":\"7\",\"value\":\"高\",\"type\":\"lable\"},";
		defValue += "{\"key\":\"8\",\"value\":\"\",\"type\":\"input\"},";
		defValue += "{\"key\":\"9\",\"value\":\"mm\",\"type\":\"unit\"}";
		defValue += "]";
		defValue += "}";
		// mutiinput
		// System.out
		// .println(pulishGoodPro("2676886", "冰箱冷柜机型", "mutiinput",
		// defValue, "1,2,3,4,5,6,7,8,9",
		// "宽,23,x,深(厚),44,x,高,55,mm", null));
		/*************************/
		// 普通属性多选 checkbox(普通)
		defValue = "";
		defValue += "{\"data\" :";
		defValue += "[";
		defValue += "{\"key\":\"373362439\",\"value\":\"288MB\"},";
		defValue += "{\"key\":\"13357\",\"value\":\"384MB\"},";
		defValue += "{\"key\":\"353886287\",\"value\":\"80MB\"}";
		defValue += "]";
		defValue += "}";
		// checkbox
		// System.out.println(pulishGoodPro("2676886", "冰箱冷柜机型", "checkbox",
		// defValue, "13357,353886287", null, null));
		/*************************/
		// sku属性多选 checkbox(普通)
		defValue = "";
		defValue += "{\"data\" :";
		defValue += "[";
		defValue += "{\"key\":\"373362439\",\"value\":\"288MB\"},";
		defValue += "{\"key\":\"13357\",\"value\":\"384MB\"},";
		defValue += "{\"key\":\"353886287\",\"value\":\"80MB\"}";
		defValue += "]";
		defValue += "}";
		// checkbox
		// System.out.println(pulishGoodPro("12304035", "机身内存", "sku-checkbox",
		// defValue, null, null, null));
		/*************************/
		// sku属性多选 checkbox(普通)
		defValue = "";
		defValue += "{\"data\" :";
		defValue += "[";
		defValue += "{\"key\":\"3232483\",\"value\":\"军绿色\",\"remark\":\"5d762a\"},";
		defValue += "{\"key\":\"3232484\",\"value\":\"天蓝色\",\"remark\":\"1eddff\"},";
		defValue += "{\"key\":\"3232481\",\"value\":\"巧克力色\",\"remark\":\"d2691e\"}";
		defValue += "]";
		defValue += "}";
		System.out.println(pulishGoodPro("1627207", "颜色分类", "sku-c-checkbox",
				defValue, null, null, null, null));
		/*************************/
		// sku属性多选 checkbox(颜色分组)
		defValue = "";
		defValue += "{\"data\" :";
		defValue += "{\"group\": [";
		defValue += "{\"key\": \"0\",\"value\": \"乳白色\",\"remark\": \"rgb(255, 255, 255)\"},";
		defValue += "{\"key\": \"1\",\"value\": \"灰色系\",\"remark\": \"rgb(128, 128, 128)\"}],";
		defValue += "\"gdata\": [";
		defValue += "{\"data\": [";
		defValue += "{\"key\": \"28321\",\"value\": \"乳白色\",\"remark\": \"rgb(255, 251, 240)\"},";
		defValue += "{\"key\": \"4266701\",\"value\": \"米白色\",\"remark\": \"rgb(238, 222, 176)\"},";
		defValue += "{\"key\": \"21966\",\"value\": \"白\",\"remark\": \"rgb(255, 255, 255)\"},";
		defValue += "{\"key\": \"28320\",\"value\": \"白色\",\"remark\": \"rgb(255, 255, 255)\"}";
		defValue += "]},";
		defValue += "{\"data\": [";
		defValue += "{\"key\": \"28334\",\"value\": \"灰色\",\"remark\": \"rgb(128, 128, 128)\"},";
		defValue += "{\"key\": \"28330\",\"value\": \"银色\",\"remark\": \"rgb(192, 192, 192)\"},";
		defValue += "{\"key\": \"28332\",\"value\": \"浅灰色\",\"remark\": \"rgb(228, 228, 228)\"},";
		defValue += "{\"key\": \"3232478\",\"value\": \"深灰色\",\"remark\": \"rgb(102, 102, 102)\"}";
		defValue += "]}";
		defValue += "]";
		defValue += "}";
		defValue += "}";
		// System.out.println(pulishGoodPro("1627207", "颜色", "sku-g-c-checkbox",
		// defValue, null, null, null));
		/*************************/
		// sku属性多选 checkbox(尺寸分组)
		defValue = "";
		/*defValue += "{\"data\" :";
		defValue += "{\"group\": [";
		defValue += "{\"key\": \"136553091\",\"value\": \"通用\"},";
		defValue += "{\"key\": \"27013\",\"value\": \"中国码\"}],";
		defValue += "\"gdata\": [";
		defValue += "{\"data\": [";
		defValue += "{\"key\": \"7190522\",\"value\": \"165/80A\"},";
		defValue += "{\"key\": \"179282386\",\"value\": \"175/88A\"},";
		defValue += "{\"key\": \"386962125\",\"value\": \"180/92A\"},";
		defValue += "{\"key\": \"386962126\",\"value\": \"185/96A\"},";
		defValue += "{\"key\": \"381746150\",\"value\": \"190/100A\"}";
		defValue += "]},";*/
		defValue += "{\"data\": [";
		defValue += "{\"key\": \"28314\",\"value\": \"S\"},";
		defValue += "{\"key\": \"28315\",\"value\": \"M\"},";
		defValue += "{\"key\": \"28316\",\"value\": \"L\"},";
		defValue += "{\"key\": \"28317\",\"value\": \"XL\"},";
		defValue += "{\"key\": \"6145171\",\"value\": \"2XL\"},";
		defValue += "{\"key\": \"115781\",\"value\": \"3XL\"}";
		defValue += "]}";
		/*defValue += "]";
		defValue += "}";
		defValue += "}";*/
		System.out.println(defValue);
		// System.out.println(pulishGoodPro("20509", "尺码", "sku-s-checkbox",
		// defValue, null, null, null));
	}
}
