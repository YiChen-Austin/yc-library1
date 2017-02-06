package com.mall.common.geo.coordinate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.mall.common.util.EtlSimulatUtil;


/**
 * @category 百度地图api
 * 
 *           百度apiv2版
 */
public class BaiduGeoUtil {
	private final static String aks[] = new String[] {
			"xxlg1y0uG7yVFvCLgO2nBucG", "rbsVYpfTHb0TlKb5BQYlI6PM",
			"b7cm067axhZ4OZPGHVNIC6U4", "aAMmfY4jeF0LE8FMOtGvLYBm",
			"DGzT9GscphOFhfYPdDxMAGCX" };
	private final static String sks[] = new String[] {
			"la1gyopR1On4U7ZURM7bFXHEIDAaThhS",
			"WCaLNdZTmeFVNldNrZkpnoGk4aGF01Ak",
			"USagCynhzN9KEodUpLFMZoRVBWpZMtGk",
			"BG5vWIBBugLQhFfkcVz9gGQc7TKxpVCr",
			"9QbmHmetyzX1zeC4etG7NL57wZ99qkp1" };
	private final static String ak = "KZCI5e2zTNf6abf1CF0GNd1i";
	private final static String host = "http://api.map.baidu.com";
	private final static String addr2LlUrl = "/geocoder/v2/?output=json";
	private final static String ll2AddrUrl = "/geocoder/v2/?callback=render&output=json";
	private final static String poiQueryUrl = "/place/search?output=json&coord_type=wgs84";

	/**
	 * 百度地图api(地址转百度经纬度)
	 * 
	 * @param String
	 *            address 地址信息
	 * @return Double[] 经纬度信息
	 */
	public static Double[] addr2lnglat(String address) {
		GetMethod getMethod = null;
		try {
			String url = host + addr2LlUrl + "&ak=" + ak + "&address="
					+ URLEncoder.encode(address, "utf-8");
			HttpClient httpClient = new HttpClient();
			getMethod = EtlSimulatUtil.getMethodPic(
					(int) System.currentTimeMillis() % 5,
					System.currentTimeMillis(),
					(int) System.currentTimeMillis() % 100, url);
			HttpMethodParams params = new HttpMethodParams();
			params.setContentCharset("utf8");
			getMethod.setParams(params);

			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String gzip = null;
				if (getMethod.getResponseHeaders("Content-Encoding").length > 0) {
					gzip = (getMethod.getResponseHeaders("Content-Encoding")[0])
							.getValue();
				}
				InputStream input = null;
				if ("gzip".equalsIgnoreCase(gzip)) {
					input = new GZIPInputStream(
							getMethod.getResponseBodyAsStream());
				} else {
					input = getMethod.getResponseBodyAsStream();
				}
				byte[] buffer = new byte[256];
				int len;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				while ((len = input.read(buffer)) >= 0) {
					out.write(buffer, 0, len);
				}
				String result = new String(out.toByteArray());
				JSONObject jsonobject = JSONObject.fromObject(result);
				if (jsonobject == null)
					return null;
				if (!"0".equalsIgnoreCase(jsonobject.getString("status")))
					return null;
				JSONObject resObj = JSONObject.fromObject(jsonobject
						.get("result"));
				if (resObj == null)
					return null;
				JSONObject location = JSONObject.fromObject(resObj
						.get("location"));
				if (location == null)
					return null;
				Double[] ll = new Double[2];
				ll[0] = Double.parseDouble(location.getString("lng"));
				ll[1] = Double.parseDouble(location.getString("lat"));
				return ll;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放连接
			if (getMethod != null)
				getMethod.releaseConnection();
		}
		return null;
	}

	/**
	 * 百度地图api(地址转百度经纬度)
	 * 
	 * @param String
	 *            address 地址信息
	 * @param int rand 随机数
	 * @return Double[] 经纬度信息
	 */
	public static Double[] addr2lnglat(String address, int rand) {
		GetMethod getMethod = null;
		try {
			rand = rand % aks.length;
			String url = addr2LlUrl + "&address="
					+ URLEncoder.encode(address, "utf-8");
			String wholeUrl = host + url + "&ak=" + ak + "&sn="
					+ BaiduSnCal.signature(url, aks[rand], sks[rand]);

			HttpClient httpClient = new HttpClient();
			getMethod = EtlSimulatUtil.getMethodPic(
					(int) System.currentTimeMillis() % 5,
					System.currentTimeMillis(),
					(int) System.currentTimeMillis() % 100, wholeUrl);
			HttpMethodParams params = new HttpMethodParams();
			params.setContentCharset("utf8");
			getMethod.setParams(params);

			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String gzip = null;
				if (getMethod.getResponseHeaders("Content-Encoding").length > 0) {
					gzip = (getMethod.getResponseHeaders("Content-Encoding")[0])
							.getValue();
				}
				InputStream input = null;
				if ("gzip".equalsIgnoreCase(gzip)) {
					input = new GZIPInputStream(
							getMethod.getResponseBodyAsStream());
				} else {
					input = getMethod.getResponseBodyAsStream();
				}
				byte[] buffer = new byte[256];
				int len;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				while ((len = input.read(buffer)) >= 0) {
					out.write(buffer, 0, len);
				}
				String result = new String(out.toByteArray());
				JSONObject jsonobject = JSONObject.fromObject(result);
				if (jsonobject == null)
					return null;
				if (!"0".equalsIgnoreCase(jsonobject.getString("status")))
					return null;
				JSONObject resObj = JSONObject.fromObject(jsonobject
						.get("result"));
				if (resObj == null)
					return null;
				JSONObject location = JSONObject.fromObject(resObj
						.get("location"));
				if (location == null)
					return null;
				Double[] ll = new Double[2];
				ll[0] = Double.parseDouble(location.getString("lng"));
				ll[1] = Double.parseDouble(location.getString("lat"));
				return ll;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放连接
			if (getMethod != null)
				getMethod.releaseConnection();
		}
		return null;
	}

	/**
	 * 百度地图api(经纬度转地址)
	 * 
	 * @param String
	 *            lng 经度
	 * @param String
	 *            lat 纬度
	 * @return String 地址信息
	 */
	public static String lnglat2addr(String lng, String lat) {
		GetMethod getMethod = null;
		try {
			String url = host + ll2AddrUrl + "&ak=" + ak + "&location=" + lat
					+ "," + lng;

			HttpClient httpClient = new HttpClient();
			getMethod = EtlSimulatUtil.getMethodPic(
					(int) System.currentTimeMillis() % 5,
					System.currentTimeMillis(),
					(int) System.currentTimeMillis() % 100, url);
			HttpMethodParams params = new HttpMethodParams();
			params.setContentCharset("utf8");
			getMethod.setParams(params);

			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String gzip = null;
				if (getMethod.getResponseHeaders("Content-Encoding").length > 0) {
					gzip = (getMethod.getResponseHeaders("Content-Encoding")[0])
							.getValue();
				}
				InputStream input = null;
				if ("gzip".equalsIgnoreCase(gzip)) {
					input = new GZIPInputStream(
							getMethod.getResponseBodyAsStream());
				} else {
					input = getMethod.getResponseBodyAsStream();
				}
				byte[] buffer = new byte[256];
				int len;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				while ((len = input.read(buffer)) >= 0) {
					out.write(buffer, 0, len);
				}
				String result = new String(out.toByteArray(),
						getMethod.getResponseCharSet());
				JSONObject jsonobject = JSONObject.fromObject(result.substring(
						15, result.length() - 1));
				if (jsonobject == null)
					return null;
				if (!"0".equalsIgnoreCase(jsonobject.getString("status")))
					return null;
				JSONObject resObj = JSONObject.fromObject(jsonobject
						.get("result"));
				if (resObj == null)
					return null;
				String address = resObj.getString("formatted_address");
				return address;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放连接
			if (getMethod != null)
				getMethod.releaseConnection();
		}
		return null;
	}

	/**
	 * 百度地图api(经纬度转地址)
	 * 
	 * @param String
	 *            lng 经度
	 * @param String
	 *            lat 纬度
	 * @param int rand 随机数
	 * @return String 地址信息
	 */
	public static String lnglat2addr(String lng, String lat, int rand) {
		GetMethod getMethod = null;
		try {
			rand = rand % aks.length;
			String url = ll2AddrUrl + "&location=" + lat + "," + lng;
			String wholeUrl = host + url + "&ak=" + ak + "&sn="
					+ BaiduSnCal.signature(url, aks[rand], sks[rand]);

			HttpClient httpClient = new HttpClient();
			getMethod = EtlSimulatUtil.getMethodPic(
					(int) System.currentTimeMillis() % 5,
					System.currentTimeMillis(),
					(int) System.currentTimeMillis() % 100, wholeUrl);
			HttpMethodParams params = new HttpMethodParams();
			params.setContentCharset("utf8");
			getMethod.setParams(params);

			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String gzip = null;
				if (getMethod.getResponseHeaders("Content-Encoding").length > 0) {
					gzip = (getMethod.getResponseHeaders("Content-Encoding")[0])
							.getValue();
				}
				InputStream input = null;
				if ("gzip".equalsIgnoreCase(gzip)) {
					input = new GZIPInputStream(
							getMethod.getResponseBodyAsStream());
				} else {
					input = getMethod.getResponseBodyAsStream();
				}
				byte[] buffer = new byte[256];
				int len;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				while ((len = input.read(buffer)) >= 0) {
					out.write(buffer, 0, len);
				}
				String result = new String(out.toByteArray(),
						getMethod.getResponseCharSet());
				JSONObject jsonobject = JSONObject.fromObject(result.substring(
						15, result.length() - 1));
				if (jsonobject == null)
					return null;
				if (!"0".equalsIgnoreCase(jsonobject.getString("status")))
					return null;
				JSONObject resObj = JSONObject.fromObject(jsonobject
						.get("result"));
				if (resObj == null)
					return null;
				String address = resObj.getString("formatted_address");
				return address;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放连接
			if (getMethod != null)
				getMethod.releaseConnection();
		}
		return null;
	}

	/**
	 * 根据搜索关键字和城市名进行poi查询
	 * 
	 * @param query
	 *            查询关键字
	 * @param region
	 *            区域即城市名
	 * @return
	 */
	public static List<Map<String, Object>> poiQuery(String query, String region) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		GetMethod getMethod = null;
		try {
			String url = host + poiQueryUrl + "&ak=" + ak + "&query="
					+ URLEncoder.encode(query, "utf-8") + "&region="
					+ URLEncoder.encode(region, "utf-8");

			HttpClient httpClient = new HttpClient();
			getMethod = EtlSimulatUtil.getMethodPic(
					(int) System.currentTimeMillis() % 5,
					System.currentTimeMillis(),
					(int) System.currentTimeMillis() % 100, url);
			HttpMethodParams params = new HttpMethodParams();
			params.setContentCharset("utf8");
			getMethod.setParams(params);

			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String gzip = null;
				if (getMethod.getResponseHeaders("Content-Encoding").length > 0) {
					gzip = (getMethod.getResponseHeaders("Content-Encoding")[0])
							.getValue();
				}
				InputStream input = null;
				if ("gzip".equalsIgnoreCase(gzip)) {
					input = new GZIPInputStream(
							getMethod.getResponseBodyAsStream());
				} else {
					input = getMethod.getResponseBodyAsStream();
				}
				byte[] buffer = new byte[256];
				int len;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				while ((len = input.read(buffer)) >= 0) {
					out.write(buffer, 0, len);
				}
				String result = new String(out.toByteArray(),
						getMethod.getResponseCharSet());
				JSONObject jsonobject = JSONObject.fromObject(result);
				if (jsonobject == null)
					return null;
				if (!"OK".equalsIgnoreCase(jsonobject.getString("status")))
					return null;
				JSONArray resArr = JSONArray.fromObject(jsonobject
						.get("results"));
				if (resArr == null)
					return null;
				for (Object o : resArr.toArray()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name",
							(String) JSONObject.fromObject(o).get("name"));
					map.put("address",
							(String) JSONObject.fromObject(o).get("address"));
					map.put("location", JSONObject.fromObject(o)
							.get("location"));
					list.add(map);
				}
				return list;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			if (getMethod != null)
				getMethod.releaseConnection();
		}
		return null;
	}

	/**
	 * 根据搜索关键字和城市名进行poi查询
	 * 
	 * @param query
	 *            查询关键字
	 * @param region
	 *            区域即城市名
	 * @param int rand 随机数
	 * @return
	 */
	public static List<Map<String, Object>> poiQuery(String query,
			String region, int rand) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		GetMethod getMethod = null;
		try {
			rand = rand % aks.length;
			String url = poiQueryUrl + "&query="
					+ URLEncoder.encode(query, "utf-8") + "&region="
					+ URLEncoder.encode(region, "utf-8");
			String wholeUrl = host + url + "&ak=" + ak + "&sn="
					+ BaiduSnCal.signature(url, aks[rand], sks[rand]);

			HttpClient httpClient = new HttpClient();
			getMethod = EtlSimulatUtil.getMethodPic(
					(int) System.currentTimeMillis() % 5,
					System.currentTimeMillis(),
					(int) System.currentTimeMillis() % 100, wholeUrl);
			HttpMethodParams params = new HttpMethodParams();
			params.setContentCharset("utf8");
			getMethod.setParams(params);

			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String gzip = null;
				if (getMethod.getResponseHeaders("Content-Encoding").length > 0) {
					gzip = (getMethod.getResponseHeaders("Content-Encoding")[0])
							.getValue();
				}
				InputStream input = null;
				if ("gzip".equalsIgnoreCase(gzip)) {
					input = new GZIPInputStream(
							getMethod.getResponseBodyAsStream());
				} else {
					input = getMethod.getResponseBodyAsStream();
				}
				byte[] buffer = new byte[256];
				int len;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				while ((len = input.read(buffer)) >= 0) {
					out.write(buffer, 0, len);
				}
				String result = new String(out.toByteArray(),
						getMethod.getResponseCharSet());
				JSONObject jsonobject = JSONObject.fromObject(result);
				if (jsonobject == null)
					return null;
				if (!"OK".equalsIgnoreCase(jsonobject.getString("status")))
					return null;
				JSONArray resArr = JSONArray.fromObject(jsonobject
						.get("results"));
				if (resArr == null)
					return null;
				for (Object o : resArr.toArray()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name",
							(String) JSONObject.fromObject(o).get("name"));
					map.put("address",
							(String) JSONObject.fromObject(o).get("address"));
					map.put("location", JSONObject.fromObject(o)
							.get("location"));
					list.add(map);
				}
				return list;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			if (getMethod != null)
				getMethod.releaseConnection();
		}
		return null;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		Double[] ll = addr2lnglat("重庆卓威儿童乐园");
		if (ll != null)
			System.out.println(ll[0] + "," + ll[1]);
		ll = addr2lnglat("重庆卓威儿童乐园", 3);
		if (ll != null)
			System.out.println(ll[0] + "," + ll[1]);

		System.out.println(lnglat2addr("106.53726063389", "29.589214527577"));
		System.out
				.println(lnglat2addr("106.53726063389", "29.589214527577", 6));

		List<Map<String, Object>> list = poiQuery("洋河", "重庆");
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		list = poiQuery("洋河", "重庆", 7);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

}
