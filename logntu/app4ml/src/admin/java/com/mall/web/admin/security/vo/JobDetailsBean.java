/**
 * 
 */
package com.mall.web.admin.security.vo;

/**
 * 功能说明：
 * @作者： cnoot
 * 创建日期： 2010-5-26
 */
public class JobDetailsBean {
	private String id;
	private String jobName;
	private String description;
	private String beanId;
	//要执行的Bean的方法
	private String executeMethod;
	private int triggerSum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTriggerSum() {
		return triggerSum;
	}

	public void setTriggerSum(int triggerSum) {
		this.triggerSum = triggerSum;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public String getExecuteMethod() {
		return executeMethod;
	}

	public void setExecuteMethod(String executeMethod) {
		this.executeMethod = executeMethod;
	}

}
