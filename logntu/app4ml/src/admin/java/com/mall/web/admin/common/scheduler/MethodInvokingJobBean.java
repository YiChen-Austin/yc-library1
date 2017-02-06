/**
 * 
 */
package com.mall.web.admin.common.scheduler;

import java.lang.reflect.InvocationTargetException;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.MethodInvoker;

import com.mall.web.admin.common.domain.SysJobDetails;


/**
 * 功能说明：执行计划中指定的方法
 * @作者： cnoot
 * 创建日期： 2010-5-25
 */
public class MethodInvokingJobBean extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(MethodInvokingJobBean.class);

	/**
	 * 执行指定的方法
	 */
	protected void executeInternal(JobExecutionContext context) {
		try {
			ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext().get(
					"applicationContextKey");
			SysJobDetails jobDetails = (SysJobDetails) context.getJobDetail().getJobDataMap().get("jobDetails");
			logger.debug("executeInternal()....执行计划名称.." + jobDetails.getJobName());
			logger.debug("executeInternal()....执行的BeanId.." + jobDetails.getBeanId());
			logger.debug("executeInternal()....执行的方法.." + jobDetails.getExecuteMethod());
			MethodInvoker methodInvoker = new MethodInvoker();
			methodInvoker.setTargetObject(applicationContext.getBean(jobDetails.getBeanId()));
			methodInvoker.setTargetClass(applicationContext.getType(jobDetails.getBeanId()));
			methodInvoker.setTargetMethod(jobDetails.getExecuteMethod());
			methodInvoker.prepare();
			methodInvoker.invoke();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
