/**
 * 
 */
package com.mall.web.admin.security.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * @功能说明：角色用户对应
 * @作者： 印鲜刚
 * @创建日期： 2010-5-4 @
 */
@Entity
@Table(name = "sys_role_user")
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = -2830652976117676035L;
	@EmbeddedId
	private SysUserRolePk id;

	public SysUserRolePk getId() {
		return id;
	}

	public void setId(SysUserRolePk id) {
		this.id = id;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysUserRole))
			return false;
		SysUserRole castOther = (SysUserRole) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
