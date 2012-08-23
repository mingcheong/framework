package com.cigteam.framework.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.core.dao.DAO;
import com.cigteam.framework.core.service.support.CrudServiceSupport;
import com.cigteam.framework.security.dao.IPubRolesDAO;
import com.cigteam.framework.security.entity.PubRoles;
import com.cigteam.framework.security.service.IPubRolesService;




@Service("pubRolesService")
@Transactional(propagation = Propagation.REQUIRED)
public class PubRolesServiceImpl extends CrudServiceSupport<PubRoles, Long> implements IPubRolesService
{

	@Resource
	protected IPubRolesDAO pubRolesDAO;



	@Override
	protected DAO<PubRoles, Long> getEntityDao()
	{
		return pubRolesDAO;
	}

}
