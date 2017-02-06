package com.mall.web.common.util;

import javax.servlet.http.HttpServletRequest;

import com.mall.common.util.BaseUtil;

public class SpiderCheckUtil {

	// 各大主流搜索引擎爬虫蜘蛛
	private static String[] spiders = { "Baiduspider", "JikeSpider",
			"Googlebot", "iaskspider", "Sogou", "Sosospider", "msnbot",
			"Yahoo", "YodaoBot", "360Spider" };

	/**
	 * 判断请求是否为爬虫
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isCrawler(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		try {
			if (!BaseUtil.isEmpty(userAgent)) {
				userAgent = userAgent.toLowerCase();
				for (String str : spiders) {
					if (userAgent.indexOf(str.toLowerCase()) >= 0) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
