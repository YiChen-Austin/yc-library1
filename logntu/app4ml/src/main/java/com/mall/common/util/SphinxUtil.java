package com.mall.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sphx.api.SphinxClient;
import org.sphx.api.SphinxException;
import org.sphx.api.SphinxMatch;
import org.sphx.api.SphinxResult;
import org.sphx.api.SphinxWordInfo;

import com.mall.common.vo.PageBean;

public class SphinxUtil {
	
	private static String HOST = "192.168.1.114";
	private static int PORT = 9346;
	private static int MODE = SphinxClient.SPH_MATCH_EXTENDED;
	private static int SORTMODE = SphinxClient.SPH_SORT_ATTR_DESC;
	private static int GROUPMODE = SphinxClient.SPH_GROUPBY_ATTR;
	
	
	/**
	 * 
	 * @param keyword 关键字
	 * @param index 索引
	 * @param pageNumber 当前页
	 * @param pageSize 每页数量
	 * @param latLng 
	 * @return
	 * @throws SphinxException
	 */
	public static List<Map<String,Object>> sphinx(String keyword, String index, double[] latLng,PageBean pageBean) throws SphinxException{
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		String indexTemp = "*";
		if(!BaseUtil.isEmpty(index)){
			indexTemp = index;
		}
		SphinxClient client = new SphinxClient();
		client.SetServer(HOST, PORT);
		client.SetMatchMode(MODE);
		client.SetLimits((pageBean.getCurPage()-1)*pageBean.getPageSize(), pageBean.getPageSize());
		//float a1 = (float)latLng[1];
		float a1 = 29.588903f;
		///float a2 = (float)latLng[3];
		float a2 = 106.531902f;
		client.SetGeoAnchor("latitude", "longitude", 29.588903f, 106.531902f);
		//client.SetFilterRange("@geodist", (long)0, (long)500, true);
		//client.SetFilterFloatRange("@geodist", 0.00f, 2000.11f, false);
		//client.SetFilterFloatRange("longitude", a1, a2, false);
		//client.SetFilterFloatRange("latitude", (float)latLng[0], (float)latLng[2], true);
		//过滤评价
		//client.SetFilter("recommended", 1, true);
		//client.SetFilterRange("recommended", 0, 1, true);
		/*暂时不在sphinx内部实现排序和分组，经纬度待添加
		client.SetSortMode(SORTMODE, "recommended");
		client.SetGroupBy("owner_type", GROUPMODE, "recommended");*/
		//client.SetWeights ( new int[] { 100, 1 } );
		SphinxResult res = client.Query(keyword, indexTemp);
		if(res != null){
			pageBean.init(res.total);
			Map<String, Object> opt = new HashMap<String, Object>();
			//opt.put("before_match", "<b>");
			//opt.put("after_match", "</b>");
			//opt.put("chunk_separator", "...");
			//opt.put("limit", 10);
			opt.put("around", 3);
			if(res != null){
				String[] docs = new String[res.matches.length];
				for(int i=0;i<res.matches.length;i++){
					Map<String, Object> map = new HashMap<String, Object>();
					SphinxMatch match = res.matches[i];
					//docs[i] = String.valueOf(match.docId);
					docs[i] = match.toString();
					map.put("id", match.docId);
					//map.put("weight", match.weight);
					resultList.add(map);
				}
				//String[] highLight = client.BuildExcerpts(res.attrNames, indexTemp, keyword, opt);
				//System.out.println(highLight);
			}
		}
		return resultList;
	}
}
