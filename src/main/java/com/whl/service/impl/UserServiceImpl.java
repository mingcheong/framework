package com.whl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.whl.dao.UserDAO;
import com.whl.domain.User;
import com.whl.service.UserService;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
//@Transactional这是一种加事务的方式，还有一种是在spring配置文件中配置aop管理事务
public class UserServiceImpl implements UserService {

	@Resource(name = "userDAO")
	protected UserDAO userDAO;

	public void addUser(User user) {
		userDAO.saveUser(user);
	}

	public List<User> getUserList() {
		return userDAO.listUser();
	}

	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}

}
