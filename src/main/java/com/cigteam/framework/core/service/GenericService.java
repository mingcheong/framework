package com.cigteam.framework.core.service;

import java.io.Serializable;
import java.util.List;

import com.cigteam.framework.exception.DaoException;
import com.cigteam.framework.exception.ServicesException;

public interface GenericService<E, PK extends Serializable> {
	/**
	 * 根据Id查找一个类型为T的对象。
	 * 
	 * @param id
	 *            传入的ID的值
	 * @return 一个类型为T的对象
	 */
	E get(PK id) throws ServicesException;

	/**
	 * 持久化一个对象，该对象类型为T。
	 * 
	 * @param entity
	 *            需要持久化的对象，使用JPA标注。
	 */
	E save(E entity) throws ServicesException;

	/**
	 * 批量添加
	 * 
	 * @param entitys
	 * @throws DaoException
	 */
	public void save(List<E> entitys) throws ServicesException;

	/**
	 * 根据对象id删除一个对象，该对象类型为T
	 * 
	 * @param id
	 *            需要删除的对象的id。
	 */
	void remove(PK id) throws ServicesException;

	/**
	 * 更新一个对象，主要用于更新一个在persistenceContext之外的一个对象。
	 * 
	 * @param transientObject
	 *            需要更新的对象，该对象不需要在persistenceContext中。
	 */
	void update(E entity) throws ServicesException;

	/**
	 * 根据对象的一个属性名和该属性名对应的值来查找一个对象。
	 * 
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            属性名对应的值
	 * @return 一个对象，如果在该属性名和值的条件下找到多个对象，则抛出一个IllegalStateException异常
	 */
	E getBy(String propertyName, Object value) throws ServicesException;

	/**
	 * 根据一个查询条件及其参数，还有开始查找的位置和查找的个数来查找类型为T的对象。
	 * 
	 * @param query
	 *            查询的条件，使用位置参数，对象名统一为obj，查询条件从where后开始。比如：obj.name =
	 *            ?1 and obj.properties = ?2
	 * @param params
	 *            查询条件中的参数的值。使用Object数组，要求顺序和查询条件中的参数位置一致。
	 * @param begin
	 *            开始查询的位置
	 * @param max
	 *            需要查询的对象的个数
	 * @return 一个该类型对象的List对象，如果没有查到任何数据，返回一个空的List对象。
	 */
	List<E> find(String query, Object[] params, int begin, int max) throws ServicesException;

	/**
	 * 根据一个查询条件及其参数，还有开始查找的位置和查找的个数来查找任意类型的对象。
	 * 
	 * @param jpql
	 *            完整的查询语句，使用位置参数。比如：select user from User
	 *            user where user.name = ?1 and
	 *            user.properties = ?2
	 * @param params
	 *            查询条件中的参数的值。使用Object数组，要求顺序和查询条件中的参数位置一致。
	 * @param begin
	 *            开始查询的位置
	 * @param max
	 *            需要查询的对象的个数
	 * @return 一个任意对象的List对象，如果没有查到任何数据，返回一个空的List对象。
	 */
	List<E> query(String jpql, Object[] params, int begin, int max) throws ServicesException;

}
