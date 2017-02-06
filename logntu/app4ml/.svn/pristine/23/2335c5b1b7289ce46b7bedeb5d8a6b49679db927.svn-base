/**
 * 
 */
package com.mall.web.admin.system.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.common.domain.SysDesignatedDate;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.security.vo.SysDesignatedDateBean;
import com.mall.web.admin.system.service.SysDesignatedDateService;

/**
 * @功能说明：节假日维护
 * @作者： 郭明军
 * @创建日期：2010年6月7日
 * 
 */
@Controller
@RequestMapping("/admin/")
public class SysDesignatedDateAction extends BaseAction {

	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SysDesignatedDateService sysdesignateddateservice;

	@RequestMapping("sys_designated_date_edit")
	public ModelAndView sys_designated_date_edit(SysDesignatedDateBean sysdesignteddatebean) throws FrameworkException {
		SysDesignatedDateBean temp=sysdesignateddateservice.getDesignatedDateBeanById(sysdesignteddatebean.getId());
		if(temp==null)
			return forword("system/sys_designated_date", null);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysdesignteddatebean", temp);
		return forword("system/sys_designated_date_edit", context);
	}

	/**
	 * 节假日调整
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("adjustSysDesignatedDate")
	@ResponseBody
	public String adjustSysDesignatedDate(String time1, String time2,
			SysDesignatedDateBean sysdesignteddatebean)
			throws FrameworkException {
		this.sysdesignateddateservice.adjustSysDesignatedDate(time1, time2,
				sysdesignteddatebean.getIdentifer());
		return SUCCESS;
	}

	/**
	 * 节假日维护查询
	 * 
	 * @return
	 * @throws ParseException
	 * @throws FrameworkException
	 */
	@RequestMapping("sysDesignatedDateFind")
	@ResponseBody
	public Map<String, Object> sysDesignatedDateFind(
			SysDesignatedDateBean sysdesignteddatebean, PageBean pagevo,
			String time4, String time5) throws ParseException,
			FrameworkException {
		logger.debug("特定日期处理！");
		String identifer = new SysDesignatedDate().getIdentifer();

		logger.debug("identifer:" + identifer);

		List<SysDesignatedDate> list2 = this.sysdesignateddateservice
				.sysDesignatedDateFind(time4, time5, identifer, pagevo);
		List<SysDesignatedDateBean> list = new ArrayList<SysDesignatedDateBean>();
		// 封装 list
		for (SysDesignatedDate list1 : list2) {
			SysDesignatedDateBean sysDes = new SysDesignatedDateBean();
			sysDes.setId(list1.getId());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			String str = simpleDateFormat.format(list1.getEdate());
			sysDes.setDate(str);
			sysDes.setName(list1.getName());
			// 判断为工作日还是节假日
			if ("w".equals(list1.getIdentifer())) {
				sysDes.setIdentifer("特定工作日");
			} else if ("h".equals(list1.getIdentifer())) {
				sysDes.setIdentifer("特定节假日");
			} else if ("pw".equals(list1.getIdentifer())) {
				sysDes.setIdentifer("普通工作日");
			} else if ("ph".equals(list1.getIdentifer())) {
				sysDes.setIdentifer("普通节假日");
			}
			sysDes.setRemark(list1.getRemark());
			list.add(sysDes);
		}

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("list", list);
		context.put("records", pagevo.getTotalRecords());
		context.put("total", pagevo.getTotalPages());
		context.put("page", pagevo.getCurPage());
		context.put("rows", pagevo.getPageSize());
		return context;
	}

	/**
	 * 编辑日期查询
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws ParseException
	 */
	@RequestMapping("sysDesignatedDateQuery")
	@ResponseBody
	public Map<String, Object> sysDesignatedDateQuery(String timeStr)
			throws FrameworkException, ParseException {
		logger.debug("编辑日期查询");
		SysDesignatedDateBean sysdesignteddatebean = sysdesignateddateservice
				.sysDesignatedDateQuery(timeStr);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("sysdesignteddatebean", sysdesignteddatebean);
		return context;
	}

	/**
	 * 编辑日期
	 * 
	 * @throws FrameworkException
	 */
	@RequestMapping("sysDesignatedDateUpdate")
	@ResponseBody
	public String sysDesignatedDateUpdate(SysDesignatedDateBean sysdesignteddatebean)
			throws FrameworkException {
		logger.debug("编辑日期！");
		this.sysdesignateddateservice.sysDesignatedDateUpdate(sysdesignteddatebean);
		return SUCCESS;
	}

	/**
	 * 统计工作日 校验
	 * 
	 * @throws ParseException
	 * @throws FrameworkException
	 */
	@RequestMapping("sysDesignatedDateSum")
	@ResponseBody
	public String sysDesignatedDateSum(String time1, String time2)
			throws FrameworkException, Exception {
		logger.debug("统计工作日");

		if ("".equals(time1) || time1 == null) {
			return SUCCESS;
		}
		if ("".equals(time2) || time2 == null) {
			return SUCCESS;
		}
		time2 = this.sysdesignateddateservice.coutnWorkDays(time1, time2) + "";
		return SUCCESS;
	}

	/**
	 * 计算日期 校验
	 * 
	 * @throws FrameworkException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@RequestMapping("sysDesignatedDateDay")
	@ResponseBody
	public String sysDesignatedDateDay(String time3, String days)
			throws FrameworkException, Exception {
		logger.debug("计算日期");
		if ("".equals(time3) || time3 == null) {
			return SUCCESS;
		}
		if ("".equals(days) || days == null) {
			return SUCCESS;
		}
		SysDesignatedDate sdd = this.sysdesignateddateservice
				.sysDesignatedDateDay(time3, Integer.parseInt(days));
		days = DateUtil.dateToString(sdd.getEdate(),
				CommonConstant.DATE_SHORT_FORMAT)
				+ " "
				+ DateUtil.getWeek(DateUtil.dateToString(sdd.getEdate()))
				+ " "
				+ (BaseUtil.isEmpty(sdd.getName()) ? "" : sdd.getName());
		return SUCCESS;
	}

	/**
	 * 特殊假日时间高亮
	 */
	@RequestMapping("dateColorAction")
	@ResponseBody
	public Map<String, Object> dateColorAction(String timeStr)
			throws NumberFormatException, ParseException, FrameworkException {
		logger.debug("timeStr:" + timeStr);
		Date beforDate;
		Date afterDate;
		// 分别转成本月第一天和最后一天
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.stringToDate(timeStr));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		beforDate = calendar.getTime();
		// 当前月的第一天加上一个月减去一天
		calendar.setTime(beforDate);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		afterDate = calendar.getTime();

		List<String> colorList = sysdesignateddateservice.getColorDay(
				beforDate, afterDate);

		Map<String, Object> context = new HashMap<String, Object>();
		context.put("colorList", colorList);
		return context;
	}

}
