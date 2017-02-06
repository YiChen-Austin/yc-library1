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
public class SysUserOrganizationPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2967615140791737101L;
	@Column(name = "SYSUSERS_ID")
	private String sysUserId;
	@Column(name = "SYSORGANIZATIONS_ID")
	private String sysOrganizationId;


	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysOrganizationId() {
		return sysOrganizationId;
	}

	public void setSysOrganizationId(String sysOrganizationId) {
		this.sysOrganizationId = sysOrganizationId;
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysUserOrganizationPk))
			return false;
		SysUserOrganizationPk castOther = (SysUserOrganizationPk) other;
		return new EqualsBuilder().append(
				this.getSysUserId() + this.getSysOrganizationId(),
				castOther.getSysUserId()
						+ castOther.getSysOrganizationId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(
				this.getSysUserId() + this.getSysOrganizationId())
				.toHashCode();
	}

}
