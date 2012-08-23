package com.cigteam.framework.core.service;

import java.io.Serializable;
import java.util.List;

import com.cigteam.framework.core.entity.IdEntity;
import com.cigteam.framework.exception.ServicesException;




public interface CrudService<E extends IdEntity, PK extends Serializable>
{

	/**
	 * 获取所有实体集合
	 * 
	 * @return 集合
	 * @throws ServicesException
	 */
	public List<E> findAll() throws ServicesException;


	/**
	 * 查找指定属性的实体集合
	 * 
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            条件
	 * @return 实体集合
	 * @throws ServicesException
	 */
	public List<E> find(String propertyName, Object value) throws ServicesException;


	/**
	 * 查找指定属性的实体集合
	 * 
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            条件
	 * @return 实体
	 * @throws ServicesException
	 */
	public E findUnique(String propertyName, Object value) throws ServicesException;


	/**
	 * 查询指定ID实体类对象
	 * 
	 * @param id
	 *            实体ID
	 * 
	 * @return 实体对象
	 * @throws ServicesException
	 */
	public E find(PK id) throws ServicesException;


	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体
	 * @throws ServicesException
	 */
	public E save(E entity) throws ServicesException;


	/**
	 * 保存实体集合
	 * 
	 * @param entitys
	 *            实体集合
	 * @throws ServicesException
	 */
	public void save(List<E> entitys) throws ServicesException;


	/**
	 * 根据实体删除
	 * 
	 * @param entity
	 *            实体
	 * @throws ServicesException
	 */
	public void remove(E entity) throws ServicesException;


	/**
	 * 根据ID删除
	 * 
	 * @param id
	 *            主键
	 * @throws ServicesException
	 */
	public void remove(PK id) throws ServicesException;


	/**
	 * 根据ID批量删除
	 * 
	 * @param ids
	 *            主键集合
	 * @throws ServicesException
	 */
	public void remove(List<PK> ids) throws ServicesException;


	/**
	 * 批量删除
	 * 
	 * @param ids
	 *            主键集合
	 * @throws ServicesException
	 */
	public void remove(PK[] ids) throws ServicesException;


	/**
	 * 批量删除（避免内存溢出异常(OutOfMemoryException))
	 * 
	 * @param ids
	 * @throws ServicesException
	 */
	public void remove(String ids) throws ServicesException;

}
