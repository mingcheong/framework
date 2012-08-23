package com.cigteam.framework.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.core.dao.DAO;
import com.cigteam.framework.core.service.support.CrudServiceSupport;
import com.cigteam.framework.security.dao.IPubResourcesDAO;
import com.cigteam.framework.security.entity.PubResources;
import com.cigteam.framework.security.service.IPubResourcesService;




@Service("pubResourcesService")
@Transactional(propagation = Propagation.REQUIRED)
public class PubResourcesServiceImpl extends CrudServiceSupport<PubResources, Long> implements IPubResourcesService
{

	@Resource
	protected IPubResourcesDAO pubResourcesDAO;



	@Override
	protected DAO<PubResources, Long> getEntityDao()
	{
		return pubResourcesDAO;
	}
}
