package com.whl.dao;

import java.util.List;

import com.whl.domain.User;

public interface UserDAO {
	
	void saveUser(User user);
	
	List<User> listUser();

	void deleteUser(long id);
	
}
