package com.whl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.whl.dao.UserDAO;
import com.whl.domain.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	/**
	 * 实体管理
	 */
	@PersistenceContext
	protected EntityManager entityManager;

	public void saveUser(User user) {
		entityManager.persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> listUser() {
		Query query =  entityManager.createQuery("from User");
		return query.getResultList();
	}

	public void deleteUser(long id) {
		User user = new User();
		user.setId(id);
		entityManager.remove(user);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
