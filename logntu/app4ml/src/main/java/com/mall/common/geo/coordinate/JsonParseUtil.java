package com.mall.common.geo.coordinate;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonParseUtil<T> {
	@SuppressWarnings("unchecked")
	public List<T> getList(String jsonArr, Class<T> _class) {
		String key = "QAZXSWEDCVFRTGBNHYUJM";
		JSONObject jsonobject = JSONObject.fromObject("{" + key + ":" + jsonArr
				+ "}");
		// 获取一个json数组
		JSONArray array = jsonobject.getJSONArray(key);
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject object = (JSONObject) array.get(i);
			T bean = (T) JSONObject.toBean(object, _class);
			if (bean != null) {
				list.add(bean);
			}
		}
		return list;
	}
}
