package com.intitude_manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intitude_manage.model.User;
import com.intitude_manage.repository.User_Repository;

@Service
public class User_Service {
	
	@Autowired
	private User_Repository user_repo;
	
	
	// Get All Users
	public List<User> getAllUser()
	{
		return user_repo.findAll();
	}
	
	
	
	// Get Users By Id
	public Optional<User> getUserBuId(int id)
	{
		return Optional.ofNullable(user_repo.findById(id).orElseThrow());
	}
	
	
	// Save User record
	public User saveUser(User user)
	{
		return user_repo.save(user);
	}
	
	
	//Delete User By Id
	public void deleteUserById(int id)
	{
		user_repo.deleteById(id);
	}

}
