package com.cigteam.framework.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.core.dao.DAO;
import com.cigteam.framework.core.service.support.CrudServiceSupport;
import com.cigteam.framework.security.dao.IPubAuthoritiesResourcesDAO;
import com.cigteam.framework.security.entity.PubAuthoritiesResources;
import com.cigteam.framework.security.service.IPubAuthoritiesResourcesService;




@Service("pubAuthoritiesResourcesService")
@Transactional(propagation = Propagation.REQUIRED)
public class PubAuthoritiesResourcesServiceImpl extends CrudServiceSupport<PubAuthoritiesResources, Long> implements IPubAuthoritiesResourcesService
{

	@Resource
	protected IPubAuthoritiesResourcesDAO pubAuthoritiesResourcesDAO;



	@Override
	protected DAO<PubAuthoritiesResources, Long> getEntityDao()
	{
		return pubAuthoritiesResourcesDAO;
	}

}
