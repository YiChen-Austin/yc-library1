/**
 * 
 */
package com.mall.web.admin.system.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.security.vo.JobDetailsBean;
import com.mall.web.admin.security.vo.TriggerBean;
import com.mall.web.admin.system.service.SysSchedulerService;

/**
 * 功能说明：
 * 
 * @作者： cnoot 创建日期： 2010-5-24
 */
@Controller
@RequestMapping("/admin/")
public class SchedulerAction extends BaseAction {
	private static Logger logger = Logger.getLogger(SchedulerAction.class);
	@Autowired
	private SysSchedulerService sysSchedulerService;

	/**
	 * 查询所有定时任务
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws SchedulerException
	 */
	@RequestMapping("findAllJobs")
	@ResponseBody
	public Map<String, Object> findAllJobs() throws SchedulerException,
			FrameworkException {
		logger.debug("findAllJobs()..........");
		List<JobDetailsBean> jobDetailBeans = sysSchedulerService
				.findJobDetails();
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("jobDetailBeans", jobDetailBeans);
		return context;
	}

	/**
	 * 给指定的定时任务添加触发器
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws ParseException
	 */
	@RequestMapping("addTrigger")
	@ResponseBody
	public Map<String, Object> addTriggerToJobDetail(TriggerBean triggerBean,
			JobDetailsBean jobDetailsBean) throws FrameworkException,
			SchedulerException, ClassNotFoundException, NoSuchMethodException,
			ParseException {
		String executeResult = null;
		logger.debug("addTriggerToJobDetail()..........");
		if (sysSchedulerService.judgeTriggerName(triggerBean.getTriggerName())) {
			sysSchedulerService.addTriggerToJobDetail(jobDetailsBean.getId(),
					triggerBean);
			executeResult = "true";
		} else {
			executeResult = "sameNameError";
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("executeResult", executeResult);
		return context;
	}

	/**
	 * 根据定时任务名称查询其关联的所有触发器
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 */
	@RequestMapping("findTriggerByJobName")
	@ResponseBody
	public Map<String, Object> findTriggerByJobName(
			JobDetailsBean jobDetailsBean) throws FrameworkException,
			SchedulerException, ClassNotFoundException, NoSuchMethodException {
		logger.debug("findTriggerByJobName()..........");
		List<TriggerBean> triggerBeans = sysSchedulerService
				.findTriggerByJobName(jobDetailsBean.getId());
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("triggerBeans", triggerBeans);
		return context;
	}

	/**
	 * 停止触发器
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	@RequestMapping("pauseTriggers")
	@ResponseBody
	public Map<String, Object> pauseTriggers(String triggers)
			throws SchedulerException {
		sysSchedulerService.pauseTriggers(triggers.split(","));
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("executeResult", true);
		return context;
	}

	/**
	 * 重启触发器
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	@RequestMapping("resumeTrigger")
	@ResponseBody
	public Map<String, Object> resumeTrigger(String triggers)
			throws SchedulerException {
		sysSchedulerService.resumeTrigger(triggers.split(","));
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("executeResult", true);
		return context;
	}

	/**
	 * 删除触发器
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	@RequestMapping("removeTriggers")
	@ResponseBody
	public Map<String, Object> removeTriggers(String triggers)
			throws SchedulerException {
		sysSchedulerService.removeTriggers(triggers.split(","));
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("executeResult", true);
		return context;
	}

	/**
	 * 新增定时任务(
	 * 
	 * @return
	 * @throws SchedulerException
	 * @throws FrameworkException
	 */
	@RequestMapping("addJobDetail")
	@ResponseBody
	public Map<String, Object> addJobDetail(JobDetailsBean jobDetailsBean)
			throws SchedulerException, FrameworkException {
		String executeResult = null;
		try {
			if (sysSchedulerService.judgeBeanAndMethod(jobDetailsBean)) {
				if (sysSchedulerService.judgeJobName(jobDetailsBean
						.getJobName())) {
					sysSchedulerService.addJobDetail(jobDetailsBean);
					executeResult = "true";
				} else {
					executeResult = "sameNameError";
				}
			} else {
				executeResult = "errBeanOrMethod";
			}
		} catch (SchedulerException e) {
			executeResult = "false";
			throw e;
		} catch (FrameworkException e) {
			executeResult = "false";
			throw e;
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("executeResult", executeResult);
		return context;
	}

	/**
	 * 删除定时任务(
	 * 
	 * @return
	 * @throws SchedulerException
	 * @throws SchedulerException
	 * @throws FrameworkException
	 * @throws FrameworkException
	 */
	@RequestMapping("deleteJobDetail")
	@ResponseBody
	public Map<String, Object> deleteJobDetail(JobDetailsBean jobDetailsBean)
			throws SchedulerException, FrameworkException {
		String executeResult = null;
		try {
			sysSchedulerService.deleteJob(jobDetailsBean.getId());
			executeResult = "true";
		} catch (SchedulerException e) {
			executeResult = "false";
			throw e;
		} catch (FrameworkException e) {
			executeResult = "false";
			throw e;
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("executeResult", executeResult);
		return context;
	}

	/**
	 * 修改定时任务
	 * 
	 * @return
	 * @throws SchedulerException
	 * @throws FrameworkException
	 */
	@RequestMapping("updateJobDetail")
	@ResponseBody
	public Map<String, Object> updateJobDetail(JobDetailsBean jobDetailsBean)
			throws SchedulerException, FrameworkException {
		String executeResult = null;
		logger.debug("updateJobDetail()........jobDetailsBean.."
				+ jobDetailsBean);
		logger.debug("updateJobDetail()........jobDetailsBean. is null."
				+ (jobDetailsBean == null));
		if (sysSchedulerService.judgeBeanAndMethod(jobDetailsBean)) {
			if (sysSchedulerService.judgeJobName(jobDetailsBean.getId(),
					jobDetailsBean.getJobName())) {
				sysSchedulerService.updateJobDetail(jobDetailsBean);
				executeResult = "true";
			} else {
				executeResult = "sameNameError";
			}

		} else {
			executeResult = "errBeanOrMethod";
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("executeResult", executeResult);
		return context;
	}
}
