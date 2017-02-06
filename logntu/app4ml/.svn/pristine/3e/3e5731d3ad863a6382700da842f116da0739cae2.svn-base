package com.mall.web.mobile.index.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.common.vo.DataJsonBean;

@Controller
@RequestMapping("/mobile/*")
public class MobIndexController {
	private static Logger logger = Logger.getLogger(MobIndexController.class);

	@RequestMapping("activity/ms")
	public ModelAndView ms(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/activity/ms");
		return mav;
	}

	@RequestMapping("activity/zk")
	public ModelAndView zk(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/activity/zk");
		return mav;
	}

	@RequestMapping("activity/zc")
	public ModelAndView zc(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/activity/zc");
		return mav;
	}

	@RequestMapping("raise")
	public ModelAndView raise(HttpServletRequest request) {
		// 未登录，进入登录界面，通过登录后，进行返回跳转
		// if (UserUtil.isUserLogin(request) == false)
		// return UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF
		// + "/raise");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/activity/raise");
		return mav;
	}

	@RequestMapping("qruser/*")
	public ModelAndView qruser(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/common/login"); // jsp目录
		return mav;
	}

//	/**
//	 * @Description(功能描述) : 安卓首页数据(老数据过期)
//	 * @author(作者) : wangliyou
//	 * @date (开发日期) : 2015年12月23日 下午3:00:19
//	 */
//	@RequestMapping("indexData")
//	public Object index(HttpServletRequest request,
//			HttpServletResponse response, PageBean page) {
//		response.setContentType("text/html;charset=UTF-8");
//		try {
//			response.getOutputStream().write(
//					MobileJsonBean.indexJson.getBytes("UTF-8"));
//		} catch (Exception e) {
//			logger.warn(e);
//		}
//		return null;
//	}
//
//	/**
//	 * @Description(功能描述) : 发现数据接口type( 0：文章，1：商品，2：活动)
//	 * @author(作者) : wangliyou
//	 * @date (开发日期) : 2015年12月18日 下午5:20:17
//	 */
//	@ResponseBody
//	@RequestMapping("found")
//	public Object found(HttpServletResponse response) {
//		try {
//			response.setContentType("text/html;charset=UTF-8");
//			response.getOutputStream().write(
//					MobileJsonBean.indexJson_found_1218.getBytes("UTF-8"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * @Description(功能描述) : 活动所需数据
//	 * @author(作者) : wangliyou
//	 * @date (开发日期) : 2015年12月23日 下午3:09:19
//	 */
//	@ResponseBody
//	@RequestMapping("activityJson")
//	public Object activityJson(HttpServletResponse response, String activityId) {
//		try {
//			response.setContentType("text/html;charset=UTF-8");
//			if ("01".equalsIgnoreCase(activityId)) {
//				response.getOutputStream().write(
//						MobileJsonBean.activity_1223_01.getBytes("UTF-8"));
//			} else if ("02".equalsIgnoreCase(activityId)) {
//				response.getOutputStream().write(
//						MobileJsonBean.activity_1223_02.getBytes("UTF-8"));
//			} else if ("03".equalsIgnoreCase(activityId)) {
//				response.getOutputStream().write(
//						MobileJsonBean.activity_1223_03.getBytes("UTF-8"));
//			} else if ("04".equalsIgnoreCase(activityId)) {
//				response.getOutputStream().write(
//						MobileJsonBean.activity_1223_04.getBytes("UTF-8"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * @Description(功能描述) : 发现-好玩
//	 * @author(作者) : wangliyou
//	 * @date (开发日期) : 2015年12月23日 下午3:40:54
//	 */
//	@RequestMapping("findvideoview")
//	public Object findvideoview(HttpServletRequest request,
//			HttpServletResponse response, PageBean page) {
//		response.setContentType("text/html;charset=UTF-8");
//		try {
//			response.getOutputStream().write(
//					MobileJsonBean.mobile_find.getBytes("UTF-8"));
//		} catch (Exception e) {
//			logger.warn(e);
//		}
//		return null;
//	}
//
//	/**
//	 * @Description(功能描述) :发现-好赚
//	 * @author(作者) : ventrue
//	 * @date (开发日期) : 2016年07月22日 下午3:40:54
//	 */
//	@RequestMapping("findMoney4Hz")
//	public Object findMoney4Hz(HttpServletRequest request,
//			HttpServletResponse response, PageBean page) {
//		response.setContentType("text/html;charset=UTF-8");
//		try {
//			response.getOutputStream().write(
//					MobileJsonBean.mobile_find_hz.getBytes("UTF-8"));
//		} catch (Exception e) {
//			logger.warn(e);
//		}
//		return null;
//	}
//
//	/**
//	 * @Description(功能描述) : 安卓首页数据(现在已用数据)
//	 * @author(作者) : wangliyou
//	 * @date (开发日期) : 2015年12月23日 下午3:40:54
//	 */
//	@RequestMapping("index1201Data")
//	public Object index1201(HttpServletRequest request,
//			HttpServletResponse response, PageBean page) {
//		response.setContentType("text/html;charset=UTF-8");
//		try {
//			response.getOutputStream().write(
//					MobileJsonBean.indexJson_1201.getBytes("UTF-8"));
//		} catch (Exception e) {
//			logger.warn(e);
//		}
//		return null;
//	}
//
//	@RequestMapping("index1201Sub")
//	public Object index1201Sub(HttpServletRequest request,
//			HttpServletResponse response, PageBean page, String sub) {
//		response.setContentType("text/html;charset=UTF-8");
//		try {
//			if ("01".equalsIgnoreCase(sub)) {
//				response.getOutputStream().write(
//						MobileJsonBean.indexJson_1201_01shouji
//								.getBytes("UTF-8"));
//			} else if ("02".equalsIgnoreCase(sub)) {
//				response.getOutputStream().write(
//						MobileJsonBean.indexJson_1201_02diannao
//								.getBytes("UTF-8"));
//			} else if ("03".equalsIgnoreCase(sub)) {
//				response.getOutputStream().write(
//						MobileJsonBean.indexJson_1201_03peijian
//								.getBytes("UTF-8"));
//			} else if ("04".equalsIgnoreCase(sub)) {
//				response.getOutputStream().write(
//						MobileJsonBean.indexJson_1201_04yunyingshang
//								.getBytes("UTF-8"));
//			} else if ("05".equalsIgnoreCase(sub)) {
//				response.getOutputStream().write(
//						MobileJsonBean.indexJson_1201_05fenqigou
//								.getBytes("UTF-8"));
//			} else if ("06".equalsIgnoreCase(sub)) {
//				response.getOutputStream().write(
//						MobileJsonBean.indexJson_1201_06shenghuoguan
//								.getBytes("UTF-8"));
//			}
//		} catch (Exception e) {
//			logger.warn(e);
//		}
//		return null;
//	}
//
//	@RequestMapping("index")
//	public ModelAndView index() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("mobile/index");
//		return mav;
//	}
//
//	
//
//	
//
//	@RequestMapping("index1201")
//	public ModelAndView index1201(HttpServletRequest request) {
//
//		/*
//		 * if (UserUtil.isUserLogin(request) == false) return
//		 * UserUtil.toUserLogin(request, UserUtil.Login_URL_PRF + "/index1201");
//		 * // 设置登陆后自动跳转的，的页面
//		 */ModelAndView mav = new ModelAndView();
//		mav.setViewName("mobile/index1201");// jsp目录
//		return mav;
//
//	}
//
//	@RequestMapping("classification")
//	public ModelAndView classification(HttpServletRequest request) {
//		// ModelAndView mav = new ModelAndView();
//		// mav.setViewName("mobile/classification");// jsp目录
//		// return mav;
//		ModelAndView mav = new ModelAndView();
//		try {
//			mav.setViewName("mobile/classification");
//		} catch (Exception e) {
//			logger.warn(e);
//		}
//		return mav;
//
//	}
//
//
//	/**
//	 * @Description(功能描述) : 活动所需数据
//	 * @author(作者) : chenghongxu
//	 * @date (开发日期) : 2016年3月24日 下午3:09:19
//	 */
//	@RequestMapping("activityMs")
//	public Object activityMs(HttpServletResponse response, String activityTime) {
//		JSONObject json = new JSONObject();
//		try {
//			response.setContentType("text/html;charset=UTF-8");
//			String by = MobileJsonBean.activity_2016_01;
//			JSONObject jsonObject = JSONObject.fromObject(by);
//			long countdown = 0;
//
//			JSONArray dataList = null;
//			JSONArray jArray = jsonObject.getJSONArray("timeRange");
//			for (int i = 0; i < jArray.size(); i++) {
//				int st;
//				int et;
//				String sst = jArray.getJSONObject(i - 1).getString("st");
//				String set = jArray.getJSONObject(i - 1).getString("et");
//				String first = sst.substring(0, sst.indexOf(":"));
//				String last = set.substring(0, set.indexOf(":"));
//				st = Integer.parseInt(first);
//				et = Integer.parseInt(last);
//
//				int s = DateUtil.getHour();
//				if ("0".equals(activityTime) || activityTime == null) {
//					if (s >= et) {
//						JSONObject jsonOt = jArray.getJSONObject(i - 1);
//						jsonOt.put("Type", "已结束");
//					} else {
//						if (s >= st && s < et) {
//							JSONObject jsonOt = jArray.getJSONObject(i);
//							jsonOt.put("Type", "正在进行中");
//
//							Calendar c = Calendar.getInstance();
//							c.set(Calendar.HOUR_OF_DAY, et); // 小时
//							c.set(Calendar.MINUTE, 0); // 分钟
//							c.set(Calendar.SECOND, 0); // 秒
//							long specified = c.getTime().getTime();
//							countdown = specified - new Date().getTime();
//
//							dataList = jsonObject.getJSONArray(i + "");
//							JSONArray Noparameter = jsonObject.getJSONArray(i
//									+ "");
//							for (int j = 0; j < Noparameter.size(); j++) {
//								JSONObject temp = Noparameter.getJSONObject(j);
//								temp.put("fType", "1");
//							}
//						} else {
//							JSONObject jsonOt = jArray.getJSONObject(i);
//							jsonOt.put("Type", "即将开始");
//						}
//					}
//
//				} else {
//					if (s >= et) {
//						JSONObject jsonOt = jArray.getJSONObject(i);
//						jsonOt.put("Type", "已结束");
//						if ((i + "").equals(activityTime)) {
//							JSONArray Noparameter = jsonObject.getJSONArray(i
//									+ "");
//							for (int j = 0; j < Noparameter.size(); j++) {
//								JSONObject temp = Noparameter.getJSONObject(j);
//								temp.put("fType", "0");
//							}
//						}
//					} else {
//						if (s >= st && s < et) {
//							JSONObject jsonOt = jArray.getJSONObject(i);
//							jsonOt.put("Type", "正在进行中");
//							if ((i + "").equals(activityTime)) {
//								JSONArray Noparameter = jsonObject
//										.getJSONArray(i + "");
//								for (int j = 0; j < Noparameter.size(); j++) {
//									JSONObject temp = Noparameter
//											.getJSONObject(j);
//									temp.put("fType", "1");
//								}
//							}
//							if (Integer.parseInt(activityTime) == (i)) {
//								Calendar c = Calendar.getInstance();
//								c.set(Calendar.HOUR_OF_DAY, et); // 小时
//								c.set(Calendar.MINUTE, 0); // 分钟
//								c.set(Calendar.SECOND, 0); // 秒
//								long specified = c.getTime().getTime();
//								countdown = specified - new Date().getTime();
//							}
//						} else {
//							JSONObject jsonOt = jArray.getJSONObject(i);
//							jsonOt.put("Type", "即将开始");
//							if ((i + "").equals(activityTime)) {
//								JSONArray Noparameter = jsonObject
//										.getJSONArray(i + "");
//								for (int j = 0; j < Noparameter.size(); j++) {
//									JSONObject temp = Noparameter
//											.getJSONObject(j);
//									temp.put("fType", "2");
//								}
//							}
//							if (Integer.parseInt(activityTime) == (i)) {
//								Calendar c = Calendar.getInstance();
//								c.set(Calendar.HOUR_OF_DAY, st);
//								c.set(Calendar.MINUTE, 0);
//								c.set(Calendar.SECOND, 0);
//								long specified = c.getTime().getTime();
//								countdown = specified - new Date().getTime();
//							}
//						}
//					}
//				}
//			}
//
//			if (!("0".equals(activityTime) || activityTime == null)) {
//				dataList = jsonObject.getJSONArray(activityTime);
//			}
//			json.element("timeRange", jArray);
//			json.element("nowList", dataList);
//			json.element("countdown", countdown);
//			json.element("serviceCode", "0");
//
//			System.out.println(json.toString());
//			response.getOutputStream().write(json.toString().getBytes("UTF-8"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
