package com.cigteam.framework.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.core.dao.DAO;
import com.cigteam.framework.core.service.support.CrudServiceSupport;
import com.cigteam.framework.exception.DaoException;
import com.cigteam.framework.exception.ServicesException;
import com.cigteam.framework.security.dao.IPubAuthoritiesDAO;
import com.cigteam.framework.security.entity.PubAuthorities;
import com.cigteam.framework.security.service.IPubAuthoritiesService;




@Service("pubAuthoritiesService")
@Transactional(propagation = Propagation.REQUIRED)
public class PubAuthoritiesServiceImpl extends CrudServiceSupport<PubAuthorities, Long> implements IPubAuthoritiesService
{

	@Resource
	protected IPubAuthoritiesDAO pubAuthoritiesDAO;



	@Override
	protected DAO<PubAuthorities, Long> getEntityDao()
	{
		return pubAuthoritiesDAO;
	}


	@Override
	public List<PubAuthorities> findAuthByUser(Long userId) throws ServicesException
	{
		if (userId == null || userId < 1)
			return null;

		String jpql = "select a.authoritie from PubRolesAuthorities a where a.role.id in (select b.role.id from PubUsersRoles b where b.user.id = ?)";

		try
		{
			return getEntityDao().find(jpql, userId);
		}
		catch (DaoException e)
		{
			throw new ServicesException("查询权限记录出错：", e);
		}
	}
}
