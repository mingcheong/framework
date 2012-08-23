package com.cigteam.framework.security.service;

import com.cigteam.framework.core.service.CrudService;
import com.cigteam.framework.exception.ServicesException;
import com.cigteam.framework.security.entity.PubUsers;




public interface IPubUsersService extends CrudService<PubUsers, Long>
{

	/**
	 * 根据用户账号查询用户实体
	 * 
	 * @param account
	 *            用户账号
	 * @return
	 * @throws ServicesException
	 */
	public PubUsers findByAccount(String account) throws ServicesException;
}
