package com.mall.common.interceptor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mall.common.util.BaseUtil;

public class UnFilterUtil {
	public final static String DefaultId = "default";
	public static UnFilterUtil unFilterUtil = null;
	private Map<String, UnFilterBean> unFilterBeans;
	private String xmlFile;

	public UnFilterUtil(String xmlFile) throws DocumentException {
		this.xmlFile = xmlFile;
		load();
	}

	@SuppressWarnings("unchecked")
	private void load() throws DocumentException {
		unFilterBeans = new TreeMap<String, UnFilterBean>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(xmlFile);
		Element rootElm = document.getRootElement();
		List<Element> list = rootElm.elements();
		for (Element e : list) {
			UnFilterBean bean = new UnFilterBean(e);
			unFilterBeans.put(bean.getId(), bean);
		}
	}

	/**
	 * 判断jsp链接是否包含在无需过滤的jsp中 ，是則返回0，无jsp配置则返回-1，否则返回对应指定的jsp
	 * 
	 */
	public String noFilterBackPath(String path) {
		path = path.replace("//", "/");
		String id = path.indexOf("/", 2) < 1 ? "" : path.substring(1,
				path.indexOf("/", 2));
		UnFilterBean bean = unFilterBeans.get(id);
		if (bean == null) {
			bean = unFilterBeans.get(DefaultId);
		}
		String[] temp = bean.getNoFilters();
		if (BaseUtil.isEmpty(temp))
			return "-1";
		for (String t : temp) {
			if (t.equalsIgnoreCase(path))
				return "0";
		}
		return bean.back;
	}

	/**
	 * 判断jsp链接是否包含在无需过滤的jsp中 ，是則返回0，无jsp配置则返回-1，否则返回对应指定的jsp
	 * 
	 */
	public String noFilterBackPathSim(String path) {
		String id = path.substring(1, path.indexOf("/", 2));
		UnFilterBean bean = unFilterBeans.get(id);
		if (bean == null) {
			bean = unFilterBeans.get(DefaultId);
		}
		return bean.back;
	}

	public class UnFilterBean {
		private String id;
		private String back;
		private String[] noFilters;

		public UnFilterBean(Element element) {
			this.id = element.attributeValue("id");
			this.back = element.attributeValue("back");
			String unfilter = element.attributeValue("unfilter");
			if (!BaseUtil.isEmpty(unfilter)) {
				this.noFilters = unfilter.split(",");
			}
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getBack() {
			return back;
		}

		public void setBack(String back) {
			this.back = back;
		}

		public String[] getNoFilters() {
			return noFilters;
		}

		public void setNoFilters(String[] noFilters) {
			this.noFilters = noFilters;
		}

	}
}
