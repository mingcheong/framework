package com.cigteam.framework.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.cigteam.framework.core.dao.support.DaoSupport;
import com.cigteam.framework.security.dao.IPubUsersDAO;
import com.cigteam.framework.security.entity.PubUsers;




@Repository
public class PubUsersDAOImpl extends DaoSupport<PubUsers, Long> implements IPubUsersDAO
{
}
