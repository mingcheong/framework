package com.cigteam.framework.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.cigteam.framework.core.dao.support.DaoSupport;
import com.cigteam.framework.security.dao.IPubAuthoritiesDAO;
import com.cigteam.framework.security.entity.PubAuthorities;




@Repository
public class PubAuthoritiesDAOImpl extends DaoSupport<PubAuthorities, Long> implements IPubAuthoritiesDAO
{

}
