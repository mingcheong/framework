package com.cigteam.framework.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cigteam.framework.core.entity.IdEntity;
import com.cigteam.framework.core.page.QueryResult;
import com.cigteam.framework.exception.DaoException;




public interface DAO<E extends IdEntity, PK extends Serializable>
{

	/**
	 * 获取所有实体集合
	 * 
	 * @return 集合
	 * @throws DaoException
	 */
	public List<E> findAll() throws DaoException;


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
	public List<E> find(String jpql, Map<String, ?> parameters) throws DaoException;


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
	public List<E> find(String jpql, Object... values) throws DaoException;


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
	public E findUnique(String jpql, Object... values) throws DaoException;


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
	public List<E> find(String propertyName, Object value) throws DaoException;


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
	public E findUniqueProperty(String propertyName, Object value) throws DaoException;


	/**
	 * 查询指定ID实体类对象
	 * 
	 * @param id
	 *            实体ID
	 * 
	 * @return 实体对象
	 * @throws DaoException
	 */
	public E find(PK id) throws DaoException;


	/**
	 * 执行jpql语句
	 * 
	 * @param jpql
	 *            jpql语句
	 * 
	 * @return 成功标识
	 * @throws DaoException
	 */
	public int execute(String jpql) throws DaoException;


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
	public int execute(String jpql, Map<String, ?> parameters) throws DaoException;


	/**
	 * 不需确定新建实体是否已经存在
	 * 
	 * @param entity
	 *            实体
	 * @throws DaoException
	 */
	public E save(E entity) throws DaoException;


	/**
	 * 不需确定新建实体是否已经存在 传入对象集合
	 * 
	 * @param entitys
	 *            实体集合
	 * @throws DaoException
	 */
	public Collection<E> save(Collection<E> entitys) throws DaoException;


	/**
	 * 可确定为新建实体
	 * 
	 * @param entity
	 *            实体
	 * @throws DaoException
	 */
	public E persist(E entity) throws DaoException;


	/**
	 * 可确定为新建实体
	 * 
	 * @param entitys
	 *            实体集合
	 * @throws DaoException
	 */
	public void persist(Collection<E> entitys) throws DaoException;


	/**
	 * 若数据库中已有此记录，置为托管状态并刷新实体信息
	 * 
	 * @throws DaoException
	 * @param entity
	 *            实体
	 */
	public E merge(E entity) throws DaoException;


	/**
	 * 若数据库中已有此记录，置为托管状态
	 * 
	 * @param entitys
	 *            实体集合
	 * @throws DaoException
	 */
	public Collection<E> merge(Collection<E> entitys) throws DaoException;


	/**
	 * 根据实体删除
	 * 
	 * @param entity
	 *            实体
	 * @throws DaoException
	 */
	public void remove(E entity) throws DaoException;


	/**
	 * 根据ID删除
	 * 
	 * @param id
	 *            主键
	 * @throws DaoException
	 */
	public void remove(PK id) throws DaoException;


	/**
	 * 根据ID批量删除
	 * 
	 * @param ids
	 *            主键集合
	 * @throws DaoException
	 */
	public void remove(Collection<PK> ids) throws DaoException;


	/**
	 * 清除一级缓存
	 */
	public void clear();


	/**
	 * 将对象置为游离状态
	 */
	public void detach(E entity);


	/**
	 * 判断实体是否处于托管状态
	 * 
	 * @param entity
	 *            实体
	 */
	public boolean isManaged(E entity);


	/**
	 * 同步jpa容器和数据库
	 */
	public void flush();


	/**
	 * 获取集合记录条数
	 * 
	 * @return 记录数
	 */
	public PK getCount();


	/**
	 * 获取分页数据
	 * 
	 * @param entityClass
	 *            实体类
	 * @param firstindex
	 *            开始索引
	 * @param maxresult
	 *            需要获取的记录数
	 * @return
	 */
	public QueryResult<E> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);


	public QueryResult<E> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);


	public QueryResult<E> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);


	public QueryResult<E> getScrollData(int firstindex, int maxresult);


	public QueryResult<E> getScrollData();
}
