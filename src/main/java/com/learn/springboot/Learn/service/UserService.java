package com.learn.springboot.Learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.springboot.Learn.entity.User;
import com.learn.springboot.Learn.exceptions.UserNotFound;
import com.learn.springboot.Learn.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public User getUserById(Long id) {
		return userRepo.findById(id).orElseThrow(()->new UserNotFound("User Not Found"));
	}
	
	public User createUser(User user) {
		String email = user.getEmail();
		Optional<User> isExists = userRepo.findByEmail(email);
		
		if(isExists.isPresent()) {
			throw new IllegalArgumentException("User Already Exists with given email");
		}
		
		return userRepo.save(user);
	}
	
	public User updateUser(Long id,User user) {
		User validUser = userRepo.findById(id).orElseThrow(()->new UserNotFound("User Not Found"));
		if (user.getFullName() != null) {
		        validUser.setFullName(user.getFullName());
		}
	    if (user.getEmail() != null) {
	        validUser.setEmail(user.getEmail());
	    }
	    if (user.getPhoneNumber() != null) {
	        validUser.setPhoneNumber(user.getPhoneNumber());
	    }
		
		return userRepo.save(validUser);
	}
	
	public String deleteUser(Long id) {
		User user = userRepo.findById(id).orElseThrow(()->new UserNotFound("User Not Found"));
		userRepo.delete(user);
		return "Deleted Successfull";
	}
}
