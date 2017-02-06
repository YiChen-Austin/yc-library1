package com.mall.web.mall.security.vo;

import java.util.Collection;

import org.springframework.security.core.userdetails.User;

import com.mall.web.mall.member.vo.WebMemberVo;

public class CustomUserDetails extends User {
	public CustomUserDetails(
			int userId,
			String username,
			String password,
			WebMemberVo memberVo,
			boolean enabled,
			boolean accountNonExpired,
			boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends org.springframework.security.core.GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
		this.memberVo=memberVo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2896188530894026320L;
	private int userId;
	private WebMemberVo memberVo;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public WebMemberVo getMemberVo() {
		return memberVo;
	}

	public void setMemberVo(WebMemberVo memberVo) {
		this.memberVo = memberVo;
	}

}
