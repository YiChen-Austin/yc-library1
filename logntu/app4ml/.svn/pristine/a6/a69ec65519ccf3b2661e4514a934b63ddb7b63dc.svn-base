/**
 * 
 */
package com.mall.web.admin.security.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 功能说明：
 * @作者： cnoot
 * 创建日期： 2010-5-25
 */
public class TriggerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7125633888954859427L;
	private String triggerName;
	private String triggerGroup;
	private Date startTime = null;

	private Date endTime = null;

	private Date nextFireTime = null;

	private Date previousFireTime = null;

	private String repeatCount;

	private String repeatInterval;

	private int timesTriggered = 0;
	private String description;
	private boolean complete = false;
	private String state;
	private String cronExpression;
	private int dayOfMonth;
	private int dayOfWeek;
	private int hour;
	private int minute;
	private int exeType;

	public int getExeType() {
		return exeType;
	}

	public void setExeType(int exeType) {
		this.exeType = exeType;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getPreviousFireTime() {
		return previousFireTime;
	}

	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

	public String getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(String repeatCount) {
		this.repeatCount = repeatCount;
	}

	public String getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(String repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public int getTimesTriggered() {
		return timesTriggered;
	}

	public void setTimesTriggered(int timesTriggered) {
		this.timesTriggered = timesTriggered;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

}
