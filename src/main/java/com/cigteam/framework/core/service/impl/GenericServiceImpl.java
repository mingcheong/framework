package com.cigteam.framework.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cigteam.framework.core.dao.GenericDAO;
import com.cigteam.framework.core.service.GenericService;
import com.cigteam.framework.exception.DaoException;
import com.cigteam.framework.exception.ServicesException;

/**
 * 基础服务层实现方法
 * 
 * @author mingcheong
 * @param <E>
 * @param <PK>
 */
@Transactional(propagation = Propagation.REQUIRED)
public class GenericServiceImpl<E, PK extends Serializable> implements GenericService<E, PK> {

	Logger logger = Logger.getLogger(this.getClass());

	protected final GenericDAO<E, PK> genericDAO;

	public GenericServiceImpl(GenericDAO<E, PK> genericDAO) {
		this.genericDAO = genericDAO;
	}

	@Override
	public E get(PK id) throws ServicesException {
		try {
			return genericDAO.get(id);
		} catch (DaoException e) {
			throw new ServicesException("查询失败：", e);
		}
	}

	@Override
	public E save(E entity) throws ServicesException {
		try {
			return genericDAO.save(entity);
		} catch (DaoException e) {
			throw new ServicesException("保存失败：", e);
		}
	}

	@Override
	public void save(List<E> entitys) throws ServicesException {
		try {
			genericDAO.save(entitys);
		} catch (DaoException e) {
			throw new ServicesException("保存失败：", e);
		}
	}

	@Override
	public void remove(PK id) throws ServicesException {
		try {
			genericDAO.remove(id);
		} catch (DaoException e) {
			throw new ServicesException("删除失败：", e);
		}
	}

	@Override
	public void update(E entity) throws ServicesException {
		try {
			genericDAO.update(entity);
		} catch (DaoException e) {
			throw new ServicesException("更新失败：", e);
		}
	}

	@Override
	public E getBy(String propertyName, Object value) throws ServicesException {
		try {
			return genericDAO.getBy(propertyName, value);
		} catch (DaoException e) {
			throw new ServicesException("查询失败：", e);
		}
	}

	@Override
	public List<E> find(String query, Object[] params, int begin, int max) throws ServicesException {
		try {
			return genericDAO.find(query, params, begin, max);
		} catch (DaoException e) {
			throw new ServicesException("查询失败：", e);
		}
	}

	@Override
	public List<E> query(String jpql, Object[] params, int begin, int max) throws ServicesException {
		try {
			return genericDAO.query(jpql, params, begin, max);
		} catch (DaoException e) {
			throw new ServicesException("查询失败：", e);
		}
	}

}
