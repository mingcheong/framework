/*
 * Copyright 2006-2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cigteam.framework.core.dao.impl;

import java.awt.print.Book;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.cigteam.framework.core.dao.GenericDAO;
import com.cigteam.framework.exception.DaoException;

/**
 * 
 * @author by cigteam
 * 
 * 泛型DAO实现
 */
@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<E, PK extends Serializable> implements GenericDAO<E, PK> {

	protected Class<? extends E> entityClass;

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public GenericDAOImpl() {
		Type localType = getClass().getGenericSuperclass();
		if (localType instanceof ParameterizedType) {
			this.entityClass = ((Class<E>) ((ParameterizedType) localType).getActualTypeArguments()[0]);
		}
	}

	protected Class<? extends E> getEntityClass() {
		return this.entityClass;
	}

	/*
	 * public void setClazz(Class<T> clazz) { this.clazz = clazz; } public
	 * Class<T> getClazz() { return clazz; }
	 */
	public E get(PK id) throws DaoException {
		if (id == null)
			return null;
		return entityManager.find(this.entityClass, id);
	}

	public List<E> find(String queryStr, Object[] params, int begin, int max) throws DaoException {

		StringBuffer sb = new StringBuffer("select a from ");
		sb.append(this.entityClass.getSimpleName()).append(" a where ").append(queryStr);
		Query query = entityManager.createQuery(sb.toString());
		int parameterIndex = 1;
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		if (begin >= 0 && max > 0) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		if (begin >= 0 && max > 0) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		return query.getResultList();
	}

	public List<E> query(String queryStr, Object[] params, int begin, int max) throws DaoException {
		Query query = entityManager.createQuery(queryStr);
		int parameterIndex = 1;
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		if (begin >= 0 && max > 0) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		return query.getResultList();
	}

	public List<E> query(String jpql, Object[] params) throws DaoException {
		return query(jpql, params, 0, -1);
	}

	public List<E> query(String jpql) throws DaoException {
		return query(jpql, null);
	}

	public void remove(PK id) throws DaoException {
		if (id == null)
			return;
		E entity = this.get(id);
		if (entity != null) {
			entityManager.remove(entity);
		}
	}

	public E save(E entity) throws DaoException {
		entityManager.persist(entity);
		return entity;
	}

	public void save(List<E> entitys) throws DaoException {
		for (E entity : entitys) {
			entityManager.persist(entity);
		}
	}

	public E getBy(String propertyName, Object value) throws DaoException {
		if (propertyName == null || "".equals(propertyName) || value == null)
			return null;
		StringBuffer sb = new StringBuffer("select a from ");
		sb.append(this.entityClass.getSimpleName()).append(" a");
		Query query = null;
		if (propertyName != null && value != null) {
			sb.append(" where a.").append(propertyName).append(" = :value");
			query = entityManager.createQuery(sb.toString()).setParameter("value", value);
		} else {
			query = entityManager.createQuery(sb.toString());
		}
		List<E> result = query.getResultList();
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	public List<E> executeNamedQuery(String queryName, Object[] params, int begin, int max) throws DaoException {
		Query query = entityManager.createNamedQuery(queryName);
		int parameterIndex = 1;
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		if (begin >= 0 && max > 0) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		return query.getResultList();
	}

	public void update(E entity) throws DaoException {
		entityManager.merge(entity);
	}

	public List<E> executeNativeNamedQuery(String nnq) throws DaoException {
		Query query = entityManager.createNamedQuery(nnq);
		return query.getResultList();
	}

	public List<E> executeNativeNamedQuery(String nnq, Object[] params) throws DaoException {
		Query query = entityManager.createNamedQuery(nnq);
		int parameterIndex = 1;
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		return query.getResultList();
	}

	public List<E> executeNativeQuery(String nnq, Object[] params, int begin, int max) throws DaoException {
		Query query = entityManager.createNativeQuery(nnq);
		int parameterIndex = 1;
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		if (begin >= 0 && max > 0) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		return query.getResultList();
	}

	public int executeNativeSQL(String nnq) throws DaoException {
		Query query = entityManager.createNativeQuery(nnq);
		return query.executeUpdate();
	}

	public int batchUpdate(String jpql, Object[] params) throws DaoException {
		Query query = entityManager.createQuery(jpql);
		int parameterIndex = 1;
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		return query.executeUpdate();
	}

	public void flush() throws DaoException {
		Session session = (Session) entityManager.getDelegate();
		session.flush();
	}

	public E getSingleResult(String jpql, Object[] params) throws DaoException {
		List<E> list = this.query(jpql, params, 0, 1);
		if (list != null && !list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	public E findByUnique(String field, String value) throws DaoException {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> c = (CriteriaQuery<E>) cb.createQuery(getEntityClass());

		Root<E> entity = (Root<E>) c.from(getEntityClass());
		c.select(entity);
		ParameterExpression<String> p = cb.parameter(String.class, field);

		c.where(cb.equal(entity.get(field), p));

		TypedQuery<Book> q = (TypedQuery<Book>) entityManager.createQuery(c);
		if (value != null)
			q.setParameter(field, value);
		return null;
	}

	public void delete(PK id) {
		StringBuffer jpql = new StringBuffer("delete from ");
		jpql.append(getEntityClass().getSimpleName()).append(" where id = ?1");
		Query q = entityManager.createQuery(jpql.toString());
		q.setParameter(1, id);
		q.executeUpdate();
	}

	public List<E> findByProperty() throws DaoException {
		//		QueryBuilder qb = entityManager.get
		return null;
	}
}