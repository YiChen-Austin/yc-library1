/**
 * 
 */
package com.mall.web.admin.security.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class SysUserRolePk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2967615140791737101L;
	@Column(name = "SYSUSERS_ID")
	private String sysUserId;
	@Column(name = "SYSROLES_ID")
	private String sysRoleId;

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysUserRolePk))
			return false;
		SysUserRolePk castOther = (SysUserRolePk) other;
		return new EqualsBuilder().append(
				this.getSysUserId() + this.getSysRoleId(),
				castOther.getSysUserId() + castOther.getSysRoleId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(
				this.getSysUserId() + this.getSysRoleId()).toHashCode();
	}

}
