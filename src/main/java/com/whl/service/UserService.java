package com.whl.service;

import java.util.List;

import com.whl.domain.User;

public interface UserService {
	
	void addUser(User user);
	
	List<User> getUserList();
	
	void deleteUser(int id);
}
