package com.mall.web.mall.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.mall.security.vo.CustomUserDetails;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Member member = webMemberService.getMember(username);
		if (member != null) {
			WebMemberVo memberVo = WebMemberVo.bean2Vo(member);
			return new CustomUserDetails(member.getUserId(), username,
					member.getPassword(), memberVo, true, true, true, true,
					findUserAuthorities(username));
		}
		return null;
	}

	public Collection<GrantedAuthority> findUserAuthorities(String username) {
		List<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();
		autthorities.add(new SimpleGrantedAuthority("ROLE_LOGIN"));
		// autthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		// autthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return autthorities;
	}
}
