package com.cigteam.framework.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.core.dao.DAO;
import com.cigteam.framework.core.service.support.CrudServiceSupport;
import com.cigteam.framework.security.dao.IPubRolesAuthoritiesDAO;
import com.cigteam.framework.security.entity.PubRolesAuthorities;
import com.cigteam.framework.security.service.IPubRolesAuthoritiesService;




@Service("pubRolesAuthoritiesService")
@Transactional(propagation = Propagation.REQUIRED)
public class PubRolesAuthoritiesServiceImpl extends CrudServiceSupport<PubRolesAuthorities, Long> implements IPubRolesAuthoritiesService
{

	@Resource
	protected IPubRolesAuthoritiesDAO pubRolesAuthoritiesDAO;



	@Override
	protected DAO<PubRolesAuthorities, Long> getEntityDao()
	{
		return pubRolesAuthoritiesDAO;
	}

}
