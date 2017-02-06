package com.mall.web.mall.common.utils;

/**
 * @category 查询条件拼接和解析 *
 * @author ventrue
 * @version 0.5
 * 
 *          适应seo对url链接要求（伪静态、目录层次短），主要为了浅层次要求，对条件进行了拼接和解析 ,主要适用于查询列表页，首页、店铺页
 */
public class SearchConditionUtil {
	/**
	 * @param cityId
	 *            城市id
	 * @param catagoryId
	 *            分类id
	 * @param zoneId
	 *            地区id
	 * @param pageNum
	 *            分页数
	 * @param keyword
	 *            关键字
	 * @param subRegion
	 *            子区域关键字
	 * @param lnt
	 *            经度
	 * @param lat
	 *            纬度
	 * @param okey
	 *            排序名称
	 * @param ovalue
	 *            排序关键字
	 * @return 条件字符串
	 */
	public static String connectCondi(String cityId, String catagoryId,
			String zoneId, String pageNum, String keyword, String subRegion,
			String lng, String lat, String okey, String ovalue, String distance) {
		StringBuffer sb = new StringBuffer();
		if (cityId != null && cityId.length() > 0) {
			sb.append("c" + cityId);
		}
		if (catagoryId != null && catagoryId.length() > 0) {
			sb.append("g" + catagoryId);
		}
		if (zoneId != null && zoneId.length() > 0) {
			sb.append("z" + zoneId);
		}
		if (pageNum != null && pageNum.length() > 0) {
			sb.append("p" + pageNum);
		}
		if (keyword != null && keyword.length() > 0) {
			sb.append("k" + keyword);
		}
		if (subRegion != null && subRegion.length() > 0) {
			sb.append("sub" + subRegion);
		}
		if (lng != null && lng.length() > 0) {
			sb.append("lng" + lng);
		}
		if (lat != null && lat.length() > 0) {
			sb.append("lat" + lat);
		}
		if (okey != null && okey.length() > 0) {
			sb.append("ok" + okey);
		}
		if (ovalue != null && ovalue.length() > 0) {
			sb.append("ov" + ovalue);
		}
		if (distance != null && distance.length() > 0) {
			sb.append("dt" + distance);
		}
		return sb.toString();
	}

	/**
	 * @param cityId
	 *            城市id
	 * @param catagoryId
	 *            分类id
	 * @param zoneId
	 *            地区id
	 * @param pageNum
	 *            分页数
	 * @param keyword
	 *            关键字
	 * @param subRegion
	 *            子区域关键字
	 * @param lnt
	 *            经度
	 * @param lat
	 *            纬度
	 * @return 条件字符串
	 */
	public static String connectCondi(String cityId, String catagoryId,
			String zoneId, String pageNum, String keyword, String subRegion,
			String lng, String lat) {
		StringBuffer sb = new StringBuffer();
		if (cityId != null && cityId.length() > 0) {
			sb.append("c" + cityId);
		}
		if (catagoryId != null && catagoryId.length() > 0) {
			sb.append("g" + catagoryId);
		}
		if (zoneId != null && zoneId.length() > 0) {
			sb.append("z" + zoneId);
		}
		if (pageNum != null && pageNum.length() > 0) {
			sb.append("p" + pageNum);
		}
		if (keyword != null && keyword.length() > 0) {
			sb.append("k" + keyword);
		}
		if (subRegion != null && subRegion.length() > 0) {
			sb.append("sub" + subRegion);
		}
		if (lng != null && lng.length() > 0) {
			sb.append("lng" + lng);
		}
		if (lat != null && lat.length() > 0) {
			sb.append("lat" + lat);
		}
		return sb.toString();
	}

	/**
	 * @param cityId
	 *            城市id
	 * @param catagoryId
	 *            分类id
	 * @param zoneId
	 *            地区id
	 * @param pageNum
	 *            分页数
	 * @param keyword
	 *            关键字
	 * @param subRegion
	 *            子区域关键字
	 * @param lnt
	 *            经度
	 * @param lat
	 *            纬度
	 * @return 以分页符及其值为界，前面部分url
	 */
	public static String connect2PrefixUrl(String cityId, String catagoryId,
			String zoneId, String pageNum, String keyword, String subRegion,
			String lng, String lat, String okey, String ovalue, String distance) {
		StringBuffer sb = new StringBuffer();
		if (cityId != null && cityId.length() > 0) {
			sb.append("c" + cityId);
		}
		if (catagoryId != null && catagoryId.length() > 0) {
			sb.append("g" + catagoryId);
		}
		if (zoneId != null && zoneId.length() > 0) {
			sb.append("z" + zoneId);
		}
		return sb.toString();
	}

	/**
	 * @param cityId
	 *            城市id
	 * @param catagoryId
	 *            分类id
	 * @param zoneId
	 *            地区id
	 * @param pageNum
	 *            分页数
	 * @param keyword
	 *            关键字
	 * @param subRegion
	 *            子区域关键字
	 * @param lnt
	 *            经度
	 * @param lat
	 *            纬度
	 * @return 以分页符及其值为界，后面部分url
	 */
	public static String connect2SuffixUrl(String cityId, String catagoryId,
			String zoneId, String pageNum, String keyword, String subRegion,
			String lng, String lat, String okey, String ovalue, String distance) {
		StringBuffer sb = new StringBuffer();
		if (keyword != null && keyword.length() > 0) {
			sb.append("k" + keyword);
		}
		if (subRegion != null && subRegion.length() > 0) {
			sb.append("sub" + subRegion);
		}
		if (lng != null && lng.length() > 0) {
			sb.append("lng" + lng);
		}
		if (lat != null && lat.length() > 0) {
			sb.append("lat" + lat);
		}
		if (okey != null && okey.length() > 0) {
			sb.append("ok" + okey);
		}
		if (ovalue != null && ovalue.length() > 0) {
			sb.append("ov" + ovalue);
		}
		if (distance != null && distance.length() > 0) {
			sb.append("dt" + distance);
		}
		return sb.toString();
	}

	/**
	 * @param condition
	 *            条件字符串
	 * @return 差分项值（数组）
	 */
	public static String[] parseCondi(String condition) {
		if (condition == null || condition.length() <= 0)
			return null;
		String[] condi = new String[11];
		int pos = -1;
		// 处理距离
		pos = condition.lastIndexOf("dt");
		if (pos >= 0)
			condi[10] = condition.substring(pos + 2);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 处理排序值
		pos = condition.lastIndexOf("ov");
		if (pos >= 0)
			condi[9] = condition.substring(pos + 2);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 处理排序关键字
		pos = condition.lastIndexOf("ok");
		if (pos >= 0)
			condi[8] = condition.substring(pos + 2);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 处理纬度
		pos = condition.indexOf("lat");
		if (pos >= 0)
			condi[7] = condition.substring(pos + 3);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 处理经度
		pos = condition.indexOf("lng");
		if (pos >= 0)
			condi[6] = condition.substring(pos + 3);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 子区域关键字
		pos = condition.indexOf("sub");
		if (pos >= 0)
			condi[5] = condition.substring(pos + 3);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 处理关键字
		pos = condition.indexOf("k");
		if (pos >= 0)
			condi[4] = condition.substring(pos + 1);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 处理分页
		pos = condition.indexOf("p");
		if (pos >= 0)
			condi[3] = condition.substring(pos + 1);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 处理地区id
		pos = condition.indexOf("z");
		if (pos >= 0)
			condi[2] = condition.substring(pos + 1);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 处理分类id
		pos = condition.indexOf("g");
		if (pos >= 0)
			condi[1] = condition.substring(pos + 1);
		if (pos > 1)
			condition = condition.substring(0, pos);
		// 城市id
		pos = condition.indexOf("c");
		if (pos >= 0)
			condi[0] = condition.substring(pos + 1);
		return condi;
	}

	public static void main(String[] arges) {
		System.out.println(connectCondi("13308", "1", "2", "3", "中国", "大融城旁",
				"162.1111", "23.3455", "disc", "asc", "50"));
		String[] values = parseCondi("c13308g1z2p3k中国sub大融城旁lng162.1111lat23.3455okdiscovascdt50");
		if (values != null) {
			for (String v : values) {
				System.out.println(v);
			}
		}
	}
}
