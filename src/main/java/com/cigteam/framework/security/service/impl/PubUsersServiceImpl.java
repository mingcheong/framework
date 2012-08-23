package com.cigteam.framework.security.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.core.dao.DAO;
import com.cigteam.framework.core.service.support.CrudServiceSupport;
import com.cigteam.framework.exception.ServicesException;
import com.cigteam.framework.security.dao.IPubUsersDAO;
import com.cigteam.framework.security.entity.PubUsers;
import com.cigteam.framework.security.service.IPubUsersService;




@Service("pubUsersService")
@Transactional(propagation = Propagation.REQUIRED)
public class PubUsersServiceImpl extends CrudServiceSupport<PubUsers, Long> implements IPubUsersService
{

	@Resource
	protected IPubUsersDAO pubUsersDAO;



	@Override
	protected DAO<PubUsers, Long> getEntityDao()
	{
		return pubUsersDAO;
	}


	@Override
	public PubUsers findByAccount(String account) throws ServicesException
	{
		if (StringUtils.isEmpty(account))
			return null;
		return findUnique("account", account);
	}
}
