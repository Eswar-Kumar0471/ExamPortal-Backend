package com.examportal.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.repo.RoleRepository;
import com.examportal.repo.UserRepository;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	//creating User
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		// TODO Auto-generated method stub
		User local = this.userRepo.findByUserName(user.getUserName());
		if(local!=null) {
			System.out.println("User is already there !!");
			throw new Exception("User already present");
		}else {
			//create user
			for(UserRole ur: userRoles) {
				roleRepo.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local = this.userRepo.save(user);
		}
		return local;
	}

}
