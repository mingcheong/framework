package com.cigteam.framework.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.core.dao.DAO;
import com.cigteam.framework.core.service.support.CrudServiceSupport;
import com.cigteam.framework.security.dao.IPubUsersRolesDAO;
import com.cigteam.framework.security.entity.PubUsersRoles;
import com.cigteam.framework.security.service.IPubUsersRolesService;




@Service("pubUsersRolesService")
@Transactional(propagation = Propagation.REQUIRED)
public class PubUsersRolesServiceImpl extends CrudServiceSupport<PubUsersRoles, Long> implements IPubUsersRolesService
{

	@Resource
	protected IPubUsersRolesDAO pubUsersRolesDAO;



	@Override
	protected DAO<PubUsersRoles, Long> getEntityDao()
	{
		return pubUsersRolesDAO;
	}

}
