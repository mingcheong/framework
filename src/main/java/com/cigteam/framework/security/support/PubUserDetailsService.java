package com.cigteam.framework.security.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.exception.ServicesException;
import com.cigteam.framework.security.entity.PubAuthorities;
import com.cigteam.framework.security.entity.PubUsers;
import com.cigteam.framework.security.service.IPubAuthoritiesService;
import com.cigteam.framework.security.service.IPubUsersService;




/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函�数.
 * 
 * @author 张敏明
 */
@Service
@Transactional(readOnly = true)
public class PubUserDetailsService implements UserDetailsService
{

	private IPubUsersService pubUsersService;

	private IPubAuthoritiesService pubAuthoritiesService;



	@Autowired
	public PubUserDetailsService(@Qualifier("pubUsersService")
	IPubUsersService pubUsersService, @Qualifier("pubAuthoritiesService")
	IPubAuthoritiesService pubAuthoritiesService)
	{
		this.pubUsersService = pubUsersService;
		this.pubAuthoritiesService = pubAuthoritiesService;
		System.out.println("dd");
	}


	/**
	 * 获取用户Detail信息的回调函�数.
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException
	{
		User userDetail = null;
		try
		{

			// 取得用户的实体
			PubUsers user = pubUsersService.findByAccount(userName);
			if (user == null)
				throw new UsernameNotFoundException("用户" + userName + " 不存在");

			// 取得用户的权限
			List<PubAuthorities> auths = pubAuthoritiesService.findAuthByUser(user.getId());

			// 无以下属性,暂时全部设为true.
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			userDetail = new User(user.getAccount(), user.getPwd(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, auths);
		}
		catch (ServicesException e)
		{
			throw new UsernameNotFoundException("用户" + userName + " 查询出错！");
		}

		return userDetail;
	}
}
