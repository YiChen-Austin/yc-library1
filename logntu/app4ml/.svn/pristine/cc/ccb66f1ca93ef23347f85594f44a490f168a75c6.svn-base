/**
 * 
 */
package com.mall.web.admin.system.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MethodInvoker;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.admin.common.dao.SysJobDetailsDao;
import com.mall.web.admin.common.domain.SysJobDetails;
import com.mall.web.admin.common.scheduler.MethodInvokingJobBean;
import com.mall.web.admin.security.vo.JobDetailsBean;
import com.mall.web.admin.security.vo.TriggerBean;
import com.mall.web.common.dynamicds.DataSource;

/**
 * 功能说明：定时任务调度业务层
 * 
 * @作者： cnoot 创建日期： 2010-5-24
 */
@Service("sysSchedulerService")
public class SysSchedulerService {
	@Resource(name = "quartzScheduler")
	private Scheduler quartzScheduler;
	@Resource(name = "sysJobDetailDao")
	private SysJobDetailsDao sysJobDetailDao;
	// 触发器执行状态 1 为停止 0为正常
	private static String PASUED = "停止";
	private static String NORMAL = "正常";
	private static final Logger logger = LoggerFactory
			.getLogger(SysSchedulerService.class);

	/**
	 * 查询所有定时任务
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws SchedulerException
	 */
	@DataSource(value = "admin")
	public List<JobDetailsBean> findJobDetails() throws FrameworkException,
			SchedulerException {
		logger.debug("findJobDetails()..");
		List<JobDetailsBean> jdb = new ArrayList<JobDetailsBean>();
		for (SysJobDetails jd : sysJobDetailDao.findAll()) {
			JobDetailsBean jobDetails = new JobDetailsBean();
			jobDetails.setId(jd.getId());
			jobDetails.setBeanId(jd.getBeanId());
			jobDetails.setDescription(jd.getDescription());
			jobDetails.setJobName(jd.getJobName());
			jobDetails.setExecuteMethod(jd.getExecuteMethod());
			jobDetails.setTriggerSum(this.findTriggerByJobName(jd.getId())
					.size());
			jdb.add(jobDetails);
		}
		return jdb;
	}

	/**
	 * 新增定时任务
	 * 
	 * @param jobDetailsBean
	 * @throws FrameworkException
	 * @throws SchedulerException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void addJobDetail(JobDetailsBean jobDetailsBean)
			throws FrameworkException, SchedulerException {
		logger.debug("addJobDetail()..jobDetailsBean:" + jobDetailsBean);
		logger.debug("getBeanId:" + jobDetailsBean.getBeanId());
		logger.debug("getExecuteMethod:" + jobDetailsBean.getExecuteMethod());
		logger.debug("getJobName:" + jobDetailsBean.getJobName());
		SysJobDetails jd = conversionJobDetailsBean(jobDetailsBean);
		this.sysJobDetailDao.save(jd);
		JobDetail jobDetail = this.conversionJobDetails(jd);
		this.quartzScheduler.addJob(jobDetail, true);
	}

	/**
	 * 修改定时任务
	 * 
	 * @param jobDetails
	 * @throws FrameworkException
	 * @throws SchedulerException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void updateJobDetail(JobDetailsBean jobDetailsBean)
			throws FrameworkException, SchedulerException {
		logger.debug("updateJobDetail(),jobDetailsBean:" + jobDetailsBean);
		SysJobDetails jd = this.findJobDetailsById(jobDetailsBean.getId());
		jd.setDescription(jobDetailsBean.getDescription());
		jd.setBeanId(jobDetailsBean.getBeanId());
		jd.setExecuteMethod(jobDetailsBean.getExecuteMethod());
		jd.setJobName(jobDetailsBean.getJobName());
		this.sysJobDetailDao.update(jd);
	}

	/**
	 * 根据定时任务ID取得其详细信息
	 * 
	 * @param id
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysJobDetails findJobDetailsById(String id)
			throws FrameworkException {
		logger.debug("findJobDetailsById(),id:" + id);
		return sysJobDetailDao.get(id);
	}

	/**
	 * 停止触发器
	 * 
	 * @param triggerNames
	 * @throws SchedulerException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void pauseTriggers(String[] triggerNames) throws SchedulerException {
		logger.debug("pauseTriggers(),triggerNames:" + triggerNames);
		for (String triggerName : triggerNames) {
			this.quartzScheduler.pauseTrigger(TriggerKey.triggerKey(
					triggerName, Scheduler.DEFAULT_GROUP));
		}
	}

	/**
	 * 重启触发器
	 * 
	 * @param triggerNames
	 * @throws SchedulerException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void resumeTrigger(String[] triggerNames) throws SchedulerException {
		logger.debug("resumeTrigger(),triggerNames:" + triggerNames);
		for (String triggerName : triggerNames) {
			this.quartzScheduler.resumeTrigger(TriggerKey.triggerKey(
					triggerName, Scheduler.DEFAULT_GROUP));
		}
	}

	/**
	 * 删除触发器
	 * 
	 * @param triggerNames
	 * @throws SchedulerException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void removeTriggers(String[] triggerNames) throws SchedulerException {
		logger.debug("removeTriggers(),triggerNames:" + triggerNames);
		for (String triggerName : triggerNames) {
			this.quartzScheduler.pauseTrigger(TriggerKey.triggerKey(
					triggerName, Scheduler.DEFAULT_GROUP));
			this.quartzScheduler.unscheduleJob(TriggerKey.triggerKey(
					triggerName, Scheduler.DEFAULT_GROUP));
		}
	}

	/**
	 * 删除定时任务
	 * 
	 * @param jobNames
	 * @throws SchedulerException
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public boolean deleteJob(String id) throws SchedulerException,
			FrameworkException {
		logger.debug("deleteJob()..jobDetailsBean:" + id);
		this.sysJobDetailDao.deleteById(id);
		this.quartzScheduler.pauseJob(new JobKey(id, Scheduler.DEFAULT_GROUP));
		return this.quartzScheduler.deleteJob(new JobKey(id,
				Scheduler.DEFAULT_GROUP));
	}

	/**
	 * 给指定的计划任务新增触发器(Trigger)
	 * 
	 * @param jobId
	 * @param tb
	 * @throws SchedulerException
	 * @throws FrameworkException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws ParseException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void addTriggerToJobDetail(String jobName, TriggerBean tb)
			throws SchedulerException, FrameworkException,
			ClassNotFoundException, NoSuchMethodException, ParseException {
		logger.debug("addTriggerToJobDetail()");
		logger.debug("addTriggerToJobDetail()...getExeType:" + tb.getExeType());
		Trigger trigger = null;
		Calendar c = Calendar.getInstance();
		switch (tb.getExeType()) {
		// 每月执行
		case 1:
			// trigger = TriggerUtils.makeMonthlyTrigger(tb.getDayOfMonth(),
			// tb.getHour(), tb.getMinute());

			// 当天小于执行日，则放入下月执行
			if (c.get(Calendar.DAY_OF_MONTH) < tb.getDayOfMonth()) {
				c.set(Calendar.MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
				c.set(Calendar.DAY_OF_MONTH, tb.getDayOfMonth());
			} else {
				c.set(Calendar.DAY_OF_MONTH, tb.getDayOfMonth());
			}
			c.set(Calendar.HOUR_OF_DAY, tb.getHour());
			c.set(Calendar.MINUTE, tb.getMinute());

			trigger = newTrigger()
					.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
					.withSchedule(
							calendarIntervalSchedule().withIntervalInMonths(1))
					.startAt(c.getTime()).build();
			break;
		// 每周执行
		case 2:
			// trigger = TriggerUtils.makeWeeklyTrigger(tb.getDayOfWeek(),
			// tb.getHour(), tb.getMinute());

			// 当天小于执行日，则放入下月执行
			if (c.get(Calendar.DAY_OF_WEEK) < tb.getDayOfWeek()) {
				c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) + 1);
			} else {
				c.set(Calendar.DAY_OF_WEEK, tb.getDayOfWeek());
			}
			c.set(Calendar.HOUR_OF_DAY, tb.getHour());
			c.set(Calendar.MINUTE, tb.getMinute());

			trigger = newTrigger()
					.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
					.withSchedule(
							calendarIntervalSchedule().withIntervalInWeeks(1))
					.startAt(c.getTime()).build();
			break;
		// 每天执行
		case 3:
			// trigger = TriggerUtils.makeDailyTrigger(tb.getHour(),
			// tb.getMinute());
			// 当天小于执行日，则放入下月执行
			if (c.get(Calendar.HOUR_OF_DAY) < tb.getHour()) {
				c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
			} else {
				c.set(Calendar.DAY_OF_MONTH, tb.getHour());
			}
			c.set(Calendar.MINUTE, tb.getMinute());

			trigger = newTrigger()
					.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
					.withSchedule(
							calendarIntervalSchedule().withIntervalInDays(1))
					.startAt(
							dateOf(tb.getHour(), tb.getMinute(), 1, tb
									.getDayOfMonth(), Integer.parseInt(DateUtil
									.getCurrentMonth()))).build();
			break;
		// 按小时
		case 4:
			if (stringToInt(tb.getRepeatCount()) != 0
					&& stringToInt(tb.getRepeatInterval()) != 0) {
				// trigger = TriggerUtils.makeHourlyTrigger(
				// stringToInt(tb.getRepeatInterval()),
				// stringToInt(tb.getRepeatCount()));
				trigger = newTrigger()
						.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
						.withSchedule(
								simpleSchedule()
										.withIntervalInHours(
												stringToInt(tb
														.getRepeatInterval()))
										.withRepeatCount(
												stringToInt(tb.getRepeatCount())))
						.startNow().build();

			} else if (stringToInt(tb.getRepeatInterval()) != 0) {
				// trigger = TriggerUtils
				// .makeHourlyTrigger(tb.getRepeatInterval());
				trigger = newTrigger()
						.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
						.withSchedule(
								simpleSchedule().withIntervalInHours(
										stringToInt(tb.getRepeatInterval())))
						.startNow().build();
			} else {
				// trigger = TriggerUtils.makeHourlyTrigger();
				trigger = newTrigger()
						.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
						.withSchedule(simpleSchedule().withIntervalInHours(1))
						.startNow().build();
			}
			break;
		// 按分钟
		case 5:
			if (stringToInt(tb.getRepeatCount()) != 0
					&& stringToInt(tb.getRepeatInterval()) != 0) {
				// trigger = TriggerUtils.makeMinutelyTrigger(
				// stringToInt(tb.getRepeatInterval()),
				// stringToInt(tb.getRepeatCount()));
				trigger = newTrigger()
						.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
						.withSchedule(
								simpleSchedule()
										.withIntervalInMinutes(
												stringToInt(tb
														.getRepeatInterval()))
										.withRepeatCount(
												stringToInt(tb.getRepeatCount())))
						.startNow().build();
			} else if (stringToInt(tb.getRepeatInterval()) != 0) {
				// trigger = TriggerUtils.makeMinutelyTrigger(tb
				// .getRepeatInterval());
				trigger = newTrigger()
						.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
						.withSchedule(
								simpleSchedule().withIntervalInMinutes(
										stringToInt(tb.getRepeatInterval())))
						.startNow().build();
			} else {
				// trigger = TriggerUtils.makeMinutelyTrigger();
				trigger = newTrigger()
						.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
						.withSchedule(simpleSchedule().withIntervalInMinutes(1))
						.startNow().build();
			}

			break;
		// 按秒
		case 6:
			if (stringToInt(tb.getRepeatCount()) != 0
					&& stringToInt(tb.getRepeatInterval()) != 0) {
				// trigger = TriggerUtils.makeSecondlyTrigger(
				// stringToInt(tb.getRepeatInterval()),
				// stringToInt(tb.getRepeatCount()));
				trigger = newTrigger()
						.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
						.withSchedule(
								simpleSchedule()
										.withIntervalInSeconds(
												stringToInt(tb
														.getRepeatInterval()))
										.withRepeatCount(
												stringToInt(tb.getRepeatCount())))
						.startNow().build();
			} else if (stringToInt(tb.getRepeatInterval()) != 0) {
				// trigger = TriggerUtils.makeSecondlyTrigger(tb
				// .getRepeatInterval());
				trigger = newTrigger()
						.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
						.withSchedule(
								simpleSchedule().withIntervalInSeconds(
										stringToInt(tb.getRepeatInterval())))
						.startNow().build();
			} else {
				// trigger = TriggerUtils.makeSecondlyTrigger();
				trigger = newTrigger()
						.withIdentity(jobName, Scheduler.DEFAULT_GROUP)
						.withSchedule(simpleSchedule().withIntervalInSeconds(1))
						.startNow().build();
			}
			break;
		// 表达式
		case 7:
			logger.debug("CronTrigger()...");
			CronTriggerImpl cronTrigger = new CronTriggerImpl();
			cronTrigger.setName(tb.getTriggerName());
			cronTrigger.setGroup(Scheduler.DEFAULT_GROUP);
			cronTrigger.setJobName(jobName);
			cronTrigger.setJobGroup(Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(tb.getCronExpression());
			cronTrigger.setDescription(tb.getDescription());
			this.quartzScheduler.scheduleJob(cronTrigger);
			break;

		default:
			break;
		}
		if (trigger == null) {
			return;
		}
		if (tb.getStartTime() != null) {
			// trigger.setStartTime(tb.getStartTime());
		}
		// trigger.setName(tb.getTriggerName());
		// trigger.setJobName(jobName);
		// trigger.setGroup(Scheduler.DEFAULT_GROUP);
		// trigger.setDescription(tb.getDescription());
		quartzScheduler.scheduleJob(trigger);
	}

	/**
	 * 判断定时任务名称是否重复
	 * 
	 * @param id
	 * @param name
	 * @return 存在返回 false 不存在返回 true
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public boolean judgeJobName(String id, String name)
			throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("jobName", name);
		if (!BaseUtil.isEmpty(id))
			params.put("AND__id__ne", id);
		List<SysJobDetails> jobDetails = this.sysJobDetailDao
				.findAllEntitiesByCondition(params);
		if (jobDetails == null || jobDetails.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断定时任务名称是否重复
	 * 
	 * @param name
	 * @return 存在返回 false 不存在返回 true
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public boolean judgeJobName(String name) throws FrameworkException {
		return this.judgeJobName(null, name);
	}

	/**
	 * 判断触发器是否存在，
	 * 
	 * @param triggerName
	 * @return 存在返回 false 不存在返回 true
	 * @throws SchedulerException
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public boolean judgeTriggerName(String triggerName)
			throws SchedulerException {
		Trigger trigger = this.quartzScheduler.getTrigger(TriggerKey
				.triggerKey(triggerName, Scheduler.DEFAULT_GROUP));
		if (trigger == null) {
			return true;
		}
		return false;
	}

	/**
	 * 根据jobName取得其所有的触发器
	 * 
	 * @param jobName
	 * @return
	 * @throws SchedulerException
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<TriggerBean> findTriggerByJobName(String jobName)
			throws SchedulerException, FrameworkException {
		logger.debug("findTriggerByJobName()");
		List<TriggerBean> triggerBeans = new ArrayList<TriggerBean>();
		// Trigger[] triggers = null;
		List<? extends Trigger> triggers = quartzScheduler
				.getTriggersOfJob(JobKey.jobKey(jobName,
						Scheduler.DEFAULT_GROUP));
		for (Trigger trigger : triggers) {
			triggerBeans.add(conversionTrigger(trigger));
		}
		return triggerBeans;
	}

	/**
	 * 判断新增计划的 beanid 和 method 是否有效
	 * 
	 * @param jobDetailsBean
	 * @return
	 * @throws SchedulerException
	 */
	@DataSource(value = "admin")
	public boolean judgeBeanAndMethod(JobDetailsBean jobDetailsBean)
			throws SchedulerException {
		logger.debug("judgeBeanAndMethod()....");
		ApplicationContext applicationContext = (ApplicationContext) this.quartzScheduler
				.getContext().get("applicationContextKey");
		try {
			Object obj = applicationContext.getBean(jobDetailsBean.getBeanId());
			MethodInvoker mi = new MethodInvoker();
			mi.setTargetObject(obj);
			mi.setTargetMethod(jobDetailsBean.getExecuteMethod());
			mi.prepare();
			return true;
		} catch (Exception e) {
			logger.debug("不存在填写的beanId 或者 方法");
			// e.printStackTrace();
			return false;
		}
	}

	@DataSource(value = "admin")
	private JobDetail conversionJobDetails(SysJobDetails jds) {
		logger.debug("conversionJobDetails()");
		logger.debug("getJobName()" + jds.getJobName());
		JobDetail jobDetail = newJob(MethodInvokingJobBean.class).withIdentity(
				jds.getId(), Scheduler.DEFAULT_GROUP).build();
		// jobDetail.setName(jds.getId());
		// jobDetail.setDescription(jds.getDescription());
		// jobDetail.setGroup(Scheduler.DEFAULT_GROUP);
		// jobDetail.setDurability(true);
		// jobDetail.setJobClass(MethodInvokingJobBean.class);
		logger.debug("JobDetail..jds" + jds);
		jobDetail.getJobDataMap().put("name", jds.getId());
		jobDetail.getJobDataMap().put("description", jds.getDescription());
		jobDetail.getJobDataMap().put("durability", true);
		return jobDetail;
	}

	@DataSource(value = "admin")
	@SuppressWarnings("static-access")
	private TriggerBean conversionTrigger(Trigger trigger)
			throws SchedulerException, FrameworkException {
		logger.debug("conversionTrigger()");
		TriggerBean triggerBean = new TriggerBean();
		triggerBean.setTriggerName(trigger.getKey().getName());
		triggerBean.setTriggerGroup(trigger.getKey().getGroup());
		triggerBean.setStartTime(trigger.getStartTime());
		triggerBean.setEndTime(trigger.getEndTime());
		triggerBean.setPreviousFireTime(trigger.getPreviousFireTime());
		triggerBean.setNextFireTime(trigger.getNextFireTime());
		triggerBean.setDescription(trigger.getDescription());
		// 触发器执行状态 1 为停止 0为正常
		triggerBean
				.setState(quartzScheduler.getTriggerState(trigger.getKey()) == TriggerState.PAUSED ? this.PASUED
						: this.NORMAL);
		return triggerBean;
	}

	@DataSource(value = "admin")
	private int stringToInt(String val) {
		if (val == null || val.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(val);
	}

	/**
	 * @param jobDetailsBean
	 * @return
	 */
	@DataSource(value = "admin")
	private SysJobDetails conversionJobDetailsBean(JobDetailsBean jobDetailsBean) {
		SysJobDetails jd = new SysJobDetails();
		jd.setBeanId(jobDetailsBean.getBeanId());
		jd.setDescription(jobDetailsBean.getDescription());
		jd.setExecuteMethod(jobDetailsBean.getExecuteMethod());
		jd.setJobName(jobDetailsBean.getJobName());
		return jd;
	}
}
