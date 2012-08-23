package com.cigteam.framework.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.cigteam.framework.core.dao.support.DaoSupport;
import com.cigteam.framework.security.dao.IPubResourcesDAO;
import com.cigteam.framework.security.entity.PubResources;




@Repository
public class PubResourcesDAOImpl extends DaoSupport<PubResources, Long> implements IPubResourcesDAO
{

}
