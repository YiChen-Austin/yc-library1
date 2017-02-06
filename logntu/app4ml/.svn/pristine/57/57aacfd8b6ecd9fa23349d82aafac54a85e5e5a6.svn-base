/**
 * 
 */
package com.mall.web.admin.system.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.common.dao.SysDesignatedDateDao;
import com.mall.web.admin.common.domain.SysDesignatedDate;
import com.mall.web.admin.security.vo.SysDesignatedDateBean;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：节假日维护
 * @作者： 郭明军
 * @创建日期：2010年6月7日
 * 
 */
@Service("sysDesignateddateservice")
public class SysDesignatedDateService {
	@Resource(name = "sysDesignatedDateDao")
	private SysDesignatedDateDao sysDesignatedDateDao;

	/**
	 * 获取总记录数
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int findAllRows(String time4, String time5, String identifer)
			throws FrameworkException {
		// Date date = new Date();
		// SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		// String str = f.format(date);
		// if ("".equals(time4) || time4 == null) {
		// time4 = str;
		// }
		// if ("".equals(time5) || time5 == null) {
		// time5 = du.dateToString(du.getDateAfterDays(du.stringToDate(str),
		// 30), "yyyy-MM-dd");
		// }
		return this.sysDesignatedDateDao.getTotalRows(time4, time5, identifer);
	}

	@DataSource(value = "admin")
	public SysDesignatedDateBean getDesignatedDateBeanById(String id)
			throws FrameworkException {
		SysDesignatedDate sysDesignatedDate = sysDesignatedDateDao.get(id);
		if (sysDesignatedDate == null)
			return null;
		SysDesignatedDateBean sysDesignatedDateBean = new SysDesignatedDateBean();
		sysDesignatedDateBean.setId(sysDesignatedDate.getId());
		sysDesignatedDateBean.setIdentifer(sysDesignatedDate.getIdentifer());
		sysDesignatedDateBean.setName(sysDesignatedDate.getName());
		sysDesignatedDateBean.setRemark(sysDesignatedDate.getRemark());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateName = simpleDateFormat.format(sysDesignatedDate.getEdate());
		sysDesignatedDateBean.setDate(dateName);
		return sysDesignatedDateBean;
	}

	/**
	 * 获取总页数
	 * 
	 * @param pagevo
	 * @return
	 */
	@DataSource(value = "admin")
	public int findAllPage(PageBean pagevo) throws FrameworkException {
		if (pagevo.getPageSize() == 0) {
			pagevo.setPageSize(5);
		}
		int pa = (pagevo.getTotalRecords() - 1) / pagevo.getPageSize() + 1;
		return pa;
	}

	/**
	 * 查询所有信息
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysDesignatedDate> sysDesignatedDateFind(String time4,
			String time5, String identifer, PageBean pageBean)
			throws FrameworkException {
		Date startDate = null;
		Date endDate = null;
		if (!BaseUtil.isEmpty(time4)) {
			startDate = DateUtil.stringToDate(time4);
		}
		if (!BaseUtil.isEmpty(time5)) {
			endDate = DateUtil.stringToDate(time5);
		}

		return this.sysDesignatedDateDao.sysDesignatedDateFind(startDate,
				endDate, identifer, pageBean);
	}

	/**
	 * 编辑日期
	 * 
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void sysDesignatedDateUpdate(
			SysDesignatedDateBean sysdesignteddatebean)
			throws FrameworkException {
		SysDesignatedDate sysDesignatedDate = sysDesignatedDateDao
				.get(sysdesignteddatebean.getId());
		if (sysDesignatedDate == null)
			return;
		sysDesignatedDate.setIdentifer(sysDesignatedDate.getIdentifer());
		sysDesignatedDate.setName(sysDesignatedDate.getName());
		sysDesignatedDate.setRemark(sysDesignatedDate.getRemark());
		this.sysDesignatedDateDao.update(sysDesignatedDate);
	}

	/**
	 * 编辑查询记录
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public SysDesignatedDateBean sysDesignatedDateQuery(String date)
			throws ParseException, FrameworkException {
		// 注意参数的大小写格式
		Date newDate = DateUtil.stringToDate(date);

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("edate", newDate);
		List<SysDesignatedDate> list = sysDesignatedDateDao
				.findAllEntitiesByCondition(condition);
		if (list.size() <= 0)
			return null;
		SysDesignatedDate sysDesignatedDate = list.get(0);
		// 转换为Bean
		SysDesignatedDateBean sysDesignatedDateBean = new SysDesignatedDateBean();
		sysDesignatedDateBean.setId(sysDesignatedDate.getId());
		sysDesignatedDateBean.setIdentifer(sysDesignatedDate.getIdentifer());
		sysDesignatedDateBean.setName(sysDesignatedDate.getName());
		sysDesignatedDateBean.setRemark(sysDesignatedDate.getRemark());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateName = simpleDateFormat.format(sysDesignatedDate.getEdate());
		sysDesignatedDateBean.setDate(dateName);
		return sysDesignatedDateBean;
	}

	/**
	 * 统计给定时间区间[time1, time2]的工作天数
	 * 
	 * @param time1
	 *            :yyyy-MM-dd
	 * @param time2
	 *            :yyyy-MM-dd
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public int coutnWorkDays(String time1, String time2)
			throws FrameworkException, Exception {
		// 临时参数
		int sum = 0;
		sum = this.sysDesignatedDateDao.countWork(time1, time2)
				+ this.sysDesignatedDateDao.countPwork(time1, time2);
		return sum;
	}

	/**
	 * 获取给定时间延迟n天的日期值
	 * 
	 * @param time3
	 *            ：yyyy-MM-dd
	 * @param days
	 *            ：n
	 * @return
	 * @throws Exception
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public Date getWorkDate(String time3, int days) throws Exception,
			FrameworkException {
		SysDesignatedDate sdd = this.sysDesignatedDateDao.sysDesignatedDateDay(
				time3, days);
		if (BaseUtil.isEmpty(sdd))
			return null;
		return sdd.getEdate();
	}

	/**
	 * 获取节假日对象
	 * 
	 * @param time3
	 * @param days
	 * @return
	 * @throws Exception
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysDesignatedDate sysDesignatedDateDay(String time3, int days)
			throws Exception, FrameworkException {
		SysDesignatedDate sdd = this.sysDesignatedDateDao.sysDesignatedDateDay(
				time3, days);
		return sdd;
	}

	/**
	 * 给出任意一个年月日得到该天是星期几
	 * 
	 * @param date
	 *            参数格式 2009-6-10 返回值：0 代表星期日，1代表星期1，2代表星期2，以此类推
	 * @throws ParseException
	 * @return int 星期一：1 星期二：2 星期三：3 星期四：4 星期五：5 星期六：6 星期日：7
	 * @throws ParseException
	 */
	@DataSource(value = "admin")
	public static int getWeek(String date) throws ParseException {
		// 注意参数的大小写格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date d = dateFormat.parse(date);
		c.setTime(d);
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获取指定时间段的特殊节假日高粱列表
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public List<String> getColorDay(Date beforDate, Date afterDate)
			throws FrameworkException {

		List<String> reulest = new ArrayList<String>();
		List<SysDesignatedDate> sysDesignatedDates = sysDesignatedDateDao
				.findTergetDate(beforDate, afterDate);
		for (SysDesignatedDate sysDesignatedDate : sysDesignatedDates) {
			if ("h".equals(sysDesignatedDate.getIdentifer())) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				String string = simpleDateFormat.format(sysDesignatedDate
						.getEdate());
				reulest.add(string);

			}
		}

		return reulest;
	}

	/**
	 * 调整节假日
	 * 
	 * @param time1
	 * @param time2
	 * @param identifer
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void adjustSysDesignatedDate(String time1, String time2,
			String identifer) throws FrameworkException {
		this.sysDesignatedDateDao.adjustSysDesignatedDate(time1, time2,
				identifer);

	}
}
