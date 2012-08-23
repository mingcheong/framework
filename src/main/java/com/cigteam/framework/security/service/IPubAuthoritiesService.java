package com.cigteam.framework.security.service;

import java.util.List;

import com.cigteam.framework.core.service.CrudService;
import com.cigteam.framework.exception.ServicesException;
import com.cigteam.framework.security.entity.PubAuthorities;




public interface IPubAuthoritiesService extends CrudService<PubAuthorities, Long>
{

	/**
	 * 根据用户主键查询用户所拥有的权限
	 * 
	 * @param userId
	 *            用户主键
	 * @return
	 * @throws ServicesException
	 */
	public List<PubAuthorities> findAuthByUser(Long userId) throws ServicesException;
}
