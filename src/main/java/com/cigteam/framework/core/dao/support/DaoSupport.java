package com.cigteam.framework.core.dao.support;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cigteam.framework.core.dao.DAO;
import com.cigteam.framework.core.entity.IdEntity;
import com.cigteam.framework.core.page.QueryResult;
import com.cigteam.framework.exception.DaoException;




@SuppressWarnings("unchecked")
public abstract class DaoSupport<E extends IdEntity, PK extends Serializable> implements DAO<E, PK>
{

	@PersistenceContext
	protected EntityManager em;

	protected Class<E> entityClass;



	protected Class<E> getEntityClass()
	{
		if (entityClass == null)
		{
			entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}


	protected void setQueryParams(Query query, Object... values)
	{
		if (values != null && values.length > 0)
		{
			for (int i = 0; i < values.length; i++)
			{
				query.setParameter(i + 1, values[i]);
			}
		}
	}


	/**
	 * 组装order by语句
	 * 
	 * @param orderby
	 * @return
	 */
	protected String buildOrderby(LinkedHashMap<String, String> orderby)
	{
		StringBuffer orderbyql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0)
		{
			orderbyql.append(" order by ");
			for (String key : orderby.keySet())
			{
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}


	protected String getCountField(Class<E> clazz)
	{
		String out = "o";
		try
		{
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor propertydesc : propertyDescriptors)
			{
				Method method = propertydesc.getReadMethod();
				if (method != null && method.isAnnotationPresent(EmbeddedId.class))
				{
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o." + propertydesc.getName() + "." + (!ps[1].getName().equals("class") ? ps[1].getName() : ps[0].getName());
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return out;
	}


	/**
	 * 若数据库中存在，返回最新状态；否则，返回空
	 * 
	 * @param entity
	 *            实体
	 * @throws DaoException
	 * @return
	 */
	protected E refresh(E entity) throws DaoException
	{
		PK id = (PK) em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
		if (null == entity) { throw new DaoException("请求刷新的实体为空！"); }
		if (id == null) { return null; }
		if (em.contains(entity))
		{
			em.refresh(entity);
			return entity;
		}
		else
		{
			return find(id);
		}
	}


	/**
	 * 获取所有实体集合
	 * 
	 * @return 集合
	 * @throws DaoException
	 */
	public List<E> findAll() throws DaoException
	{
		String jpql = "SELECT o FROM " + getEntityClass().getName() + " AS o";
		TypedQuery<E> query = em.createQuery(jpql, getEntityClass());
		return query.getResultList();
	}


	/**
	 * 根据命名参数查询
	 * 
	 * @param jpqlName
	 *            命名参数的名字
	 * @param parameters
	 *            参数集合
	 * @return 集合
	 * @throws DaoException
	 */
	public List<E> findByNamedQuery(String jpqlName, Map<String, ?> parameters) throws DaoException
	{
		TypedQuery<E> query = em.createNamedQuery(jpqlName, getEntityClass());
		for (Parameter<?> sqlParam : query.getParameters())
		{
			query.setParameter(sqlParam.getName(), parameters.get(sqlParam.getName()));
		}
		return query.getResultList();
	}


	/**
	 * 查询指定jpql，并返回集合
	 * 
	 * @param jpql
	 *            jpql语句
	 * @param parameters
	 *            参数集合
	 * @return 集合
	 * @throws DaoException
	 */
	public List<E> find(String jpql, Map<String, ?> parameters) throws DaoException
	{
		TypedQuery<E> query = em.createQuery(jpql, getEntityClass());
		for (Parameter<?> sqlParam : query.getParameters())
		{
			query.setParameter(sqlParam.getName(), parameters.get(sqlParam.getName()));
		}
		return query.getResultList();
	}


	/**
	 * 查询指定jpql，并返回集合
	 * 
	 * @param jpql
	 *            jpql语句
	 * @param values
	 *            可变的参数列表
	 * @return 集合
	 * @throws DaoException
	 */
	public List<E> find(String jpql, Object... values) throws DaoException
	{
		Query query = em.createQuery(jpql);
		setQueryParams(query, values);
		return query.getResultList();
	}


	/**
	 * 按照jpql语句查询唯一对象.
	 * 
	 * @param jpql
	 *            jpql语句
	 * @param values
	 *            可变参数集合
	 * @return OBJECT对象
	 * @throws DaoException
	 */
	public E findUnique(String jpql, Object... values) throws DaoException
	{
		Query query = em.createQuery(jpql);
		setQueryParams(query, values);
		E entity = null;
		try
		{
			entity = (E) query.getSingleResult();
		}
		catch (NoResultException ex)
		{
			return null;
		}
		catch (NonUniqueResultException ex)
		{
			List<E> result = query.getResultList();
			entity = result.get(0);
		}
		return entity;
	}


	/**
	 * 查找指定属性的实体集合
	 * 
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            条件
	 * @return 实体集合
	 * @throws DaoException
	 */
	public List<E> find(String propertyName, Object value) throws DaoException
	{
		String jpql = "from " + getEntityClass().getName() + " as a where a." + propertyName + "=?";
		return find(jpql, value);
	}


	/**
	 * 查找指定属性的实体集合
	 * 
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            条件
	 * @return 实体
	 * @throws DaoException
	 */
	public E findUniqueProperty(String propertyName, Object value) throws DaoException
	{
		String jpql = "from " + getEntityClass().getSimpleName() + " where " + propertyName + "=?";
		return findUnique(jpql, value);
	}


	/**
	 * 查询指定ID实体类对象
	 * 
	 * @param id
	 *            实体ID
	 * 
	 * @return 实体对象
	 * @throws DaoException
	 */
	public E find(PK id) throws DaoException
	{
		return em.find(getEntityClass(), id);
	}


	/**
	 * 执行jpql语句
	 * 
	 * @param jpql
	 *            jpql语句
	 * 
	 * @return 成功标识
	 * @throws DaoException
	 */
	public int execute(String jpql) throws DaoException
	{
		return execute(jpql, null);
	}


	/**
	 * 执行jpql语句
	 * 
	 * @param jpql
	 *            jpql语句
	 * @param parameters
	 *            参数集合
	 * @return 成功标识
	 * @throws DaoException
	 */
	public int execute(String jpql, Map<String, ?> parameters) throws DaoException
	{
		Query query = em.createQuery(jpql);
		if (parameters != null && !parameters.isEmpty())
		{
			for (Parameter<?> sqlParam : query.getParameters())
			{
				query.setParameter(sqlParam.getName(), parameters.get(sqlParam.getName()));
			}
		}
		return query.executeUpdate();
	}


	/**
	 * 不需确定新建实体是否已经存在
	 * 
	 * @param entity
	 *            实体
	 * @throws DaoException
	 */
	public E save(E entity) throws DaoException
	{
		try
		{
			entity = em.contains(entity) ? entity : em.merge(entity);
		}
		catch (Exception e)
		{
			throw new DaoException("保存失败，请联系管理员！");
		}
		return entity;
	}


	/**
	 * 不需确定新建实体是否已经存在 传入对象集合
	 * 
	 * @param entitys
	 *            实体集合
	 * @throws DaoException
	 */
	public Collection<E> save(Collection<E> entitys) throws DaoException
	{
		Collection<E> collection = new HashSet<E>();
		for (E entity : entitys)
		{
			collection.add(save(entity));
		}
		return collection;
	}


	/**
	 * 可确定为新建实体
	 * 
	 * @param entity
	 *            实体
	 * @throws DaoException
	 */
	public E persist(E entity) throws DaoException
	{
		try
		{
			em.persist(entity);
			return entity;
		}
		catch (Exception e)
		{
			throw new DaoException("错误：保存新建实例时，发生异常：" + e.getMessage());
		}
	}


	/**
	 * 可确定为新建实体
	 * 
	 * @param entitys
	 *            实体集合
	 * @throws DaoException
	 */
	public void persist(Collection<E> entitys) throws DaoException
	{
		for (E entity : entitys)
		{
			em.persist(entity);
		}
	}


	/**
	 * 若数据库中已有此记录，置为托管状态并刷新实体信息
	 * 
	 * @throws DaoException
	 * @param entity
	 *            实体
	 */
	public E merge(E entity) throws DaoException
	{
		try
		{
			entity = em.contains(entity) ? entity : em.merge(entity);
		}
		catch (Exception e)
		{
			throw new DaoException("更新失败，请联系管理员！");
		}
		return entity;
	}


	/**
	 * 若数据库中已有此记录，置为托管状态
	 * 
	 * @param entitys
	 *            实体集合
	 * @throws DaoException
	 */
	public Collection<E> merge(Collection<E> entitys) throws DaoException
	{
		Collection<E> collection = new HashSet<E>();
		for (E entity : entitys)
		{
			collection.add(merge(entity));
		}
		return collection;
	}


	/**
	 * 根据实体删除
	 * 
	 * @param entity
	 *            实体
	 * @throws DaoException
	 */
	public void remove(E entity) throws DaoException
	{
		if (entity != null) { throw new DaoException("请求删除的对象为空!"); }
		try
		{
			if (em.contains(entity))
			{
				em.remove(entity);
			}
			else
			{
				PK id = (PK) em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
				em.remove(find(id));
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException("删除对象时出错，请联系管理员!");
		}
	}


	/**
	 * 根据ID删除
	 * 
	 * @param id
	 *            主键
	 * @throws DaoException
	 */
	public void remove(PK id) throws DaoException
	{
		if (id == null) { throw new DaoException("请求删除的对象ID为空!"); }
		remove(find(id));
	}


	/**
	 * 根据ID批量删除
	 * 
	 * @param ids
	 *            主键集合
	 * @throws DaoException
	 */
	public void remove(Collection<PK> ids) throws DaoException
	{
		for (PK id : ids)
		{
			remove(id);
		}
	}


	/**
	 * 清除一级缓存
	 */
	public void clear()
	{
		em.clear();
	}


	/**
	 * 将对象置为游离状态
	 * 
	 * @param entity
	 *            实体
	 */
	public void detach(E entity)
	{
		em.detach(entity);
	}


	/**
	 * 将对象置为游离状态
	 * 
	 * @param entitys
	 *            实体集合
	 */
	public void detach(Collection<E> entitys)
	{
		for (E entity : entitys)
		{
			detach(entity);
		}
	}


	/**
	 * 判断实体是否处于托管状态
	 * 
	 * @param entity
	 *            实体
	 */
	public boolean isManaged(E entity)
	{
		return em.contains(entity);
	}


	/**
	 * 同步jpa容器和数据库
	 */
	public void flush()
	{
		em.flush();
	}


	/**
	 * 获取集合记录条数
	 * 
	 * @return 记录数
	 */
	public PK getCount()
	{
		return (PK) em.createQuery("select count(" + getCountField(getEntityClass()) + ") from " + getEntityClass().getName() + " o").getSingleResult();
	}


	public QueryResult<E> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby)
	{
		return getScrollData(firstindex, maxresult, null, null, orderby);
	}


	public QueryResult<E> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams)
	{
		return getScrollData(firstindex, maxresult, wherejpql, queryParams, null);
	}


	public QueryResult<E> getScrollData(int firstindex, int maxresult)
	{
		return getScrollData(firstindex, maxresult, null, null, null);
	}


	public QueryResult<E> getScrollData()
	{
		return getScrollData(-1, -1);
	}


	public QueryResult<E> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby)
	{
		QueryResult<E> qr = new QueryResult<E>();
		Query query = em.createQuery("select o from " + getEntityClass().getName() + " o " + (wherejpql == null ? "" : "where " + wherejpql) + buildOrderby(orderby));
		setQueryParams(query, queryParams);
		if (firstindex != -1 && maxresult != -1)
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setResultList(query.getResultList());
		query = em.createQuery("select count(" + getCountField(getEntityClass()) + ") from " + getEntityClass().getName() + " o " + (wherejpql == null ? "" : "where " + wherejpql));
		setQueryParams(query, queryParams);
		qr.setTotalRecord((Long) query.getSingleResult());
		return qr;
	}

}
