/**
 * 
 */
package com.mall.web.admin.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @功能说明：操作日志实体
 * @作者： xgyin
 * @创建日期： 2011-3-22
 */

@Entity
@Table(name = "sys_log_info")
public class SysLogInfo implements java.io.Serializable {
	private static final long serialVersionUID = -6982213456454919708L;

	/** id */
	private String id;

	/** 操作类型 */
	private String optype;

	/** 操作内容 */
	private String objectname;

	/** 数据id */
	private String dataid;

	/** ip地址 */
	private String ip;

	/** 操作用户 */
	private String operatorUser;

	/** 操作人员所属机构 */
	private String operatorOrg;

	/** 操作时间 */
	private Date dtime;

	/** 状态 */
	private String state;

	/** 备注 */
	private String remark;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "optype", length = 80)
	public String getOptype() {
		return optype;
	}

	public void setOptype(String optype) {
		this.optype = optype;
	}

	@Column(name = "objectname", length = 80)
	public String getObjectname() {
		return objectname;
	}

	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}

	@Column(name = "dataid", length = 255)
	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	@Column(name = "ip", length = 20)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "operatorUser", length = 80)
	public String getOperatorUser() {
		return operatorUser;
	}

	public void setOperatorUser(String operatorUser) {
		this.operatorUser = operatorUser;
	}

	@Column(name = "operatorOrg", length = 80)
	public String getOperatorOrg() {
		return operatorOrg;
	}

	public void setOperatorOrg(String operatorOrg) {
		this.operatorOrg = operatorOrg;
	}

	@Column(name = "dtime", length = 23)
	public Date getDtime() {
		return dtime;
	}

	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}

	@Column(name = "state", length = 2)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "remark", length = 4000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}