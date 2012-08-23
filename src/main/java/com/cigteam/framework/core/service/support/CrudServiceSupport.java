package com.cigteam.framework.core.service.support;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.core.dao.DAO;
import com.cigteam.framework.core.entity.IdEntity;
import com.cigteam.framework.core.service.CrudService;
import com.cigteam.framework.exception.DaoException;
import com.cigteam.framework.exception.ServicesException;




@Transactional(propagation = Propagation.REQUIRED)
public abstract class CrudServiceSupport<E extends IdEntity, PK extends Serializable> implements CrudService<E, PK>
{
	protected abstract DAO<E, PK> getEntityDao();


	@Override
	public List<E> findAll() throws ServicesException
	{
		try
		{
			return getEntityDao().findAll();
		}
		catch (DaoException e)
		{
			throw new ServicesException("查询记录出错：", e);
		}
	}


	@Override
	public List<E> find(String propertyName, Object value) throws ServicesException
	{
		try
		{
			return getEntityDao().find(propertyName, value);
		}
		catch (DaoException e)
		{
			throw new ServicesException("查询记录出错：", e);
		}
	}


	@Override
	public E findUnique(String propertyName, Object value) throws ServicesException
	{
		try
		{
			return getEntityDao().findUniqueProperty(propertyName, value);
		}
		catch (DaoException e)
		{
			throw new ServicesException("查询记录出错：", e);
		}
	}


	@Override
	public E find(PK id) throws ServicesException
	{
		try
		{
			return getEntityDao().find(id);
		}
		catch (DaoException e)
		{
			throw new ServicesException("查询失败：", e);
		}
	}


	@Override
	public E save(E entity) throws ServicesException
	{
		try
		{
			if (entity.getId() > 0)
			{
				return getEntityDao().merge(entity);
			}
			else
			{
				return getEntityDao().persist(entity);
			}
		}
		catch (DaoException e)
		{
			throw new ServicesException("保存失败：", e);
		}
	}


	@Override
	public void save(List<E> entitys) throws ServicesException
	{
		for (E entity : entitys)
		{
			save(entity);
		}
	}


	@Override
	public void remove(E entity) throws ServicesException
	{
		try
		{
			getEntityDao().remove(entity);
		}
		catch (DaoException e)
		{
			throw new ServicesException("删除失败：", e);
		}

	}


	@Override
	public void remove(PK id) throws ServicesException
	{
		try
		{
			getEntityDao().remove(id);
		}
		catch (DaoException e)
		{
			throw new ServicesException("删除失败：", e);
		}

	}


	@Override
	public void remove(List<PK> ids) throws ServicesException
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void remove(PK[] ids) throws ServicesException
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void remove(String ids) throws ServicesException
	{
		// TODO Auto-generated method stub

	}

}
