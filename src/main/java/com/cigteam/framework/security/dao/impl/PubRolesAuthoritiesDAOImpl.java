package com.cigteam.framework.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.cigteam.framework.core.dao.support.DaoSupport;
import com.cigteam.framework.security.dao.IPubRolesAuthoritiesDAO;
import com.cigteam.framework.security.entity.PubRolesAuthorities;




@Repository
public class PubRolesAuthoritiesDAOImpl extends DaoSupport<PubRolesAuthorities, Long> implements IPubRolesAuthoritiesDAO
{

}
