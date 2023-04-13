package com.lcomputerstudy.testmvc2.service2;

import java.util.ArrayList;
import com.lcomputerstudy.testmvc2.UserDAO2.UserDAO2;
import com.lcomputerstudy.testmvc2.vo2.User2;

public class UserService2 {

	private static UserService2 service = null;
	private static UserDAO2 dao = null;
	
	private UserService2() {
		
	}
	
	public void insertUser(User2 user) {
		dao.insertUser(user);
	}                    
	
	public void detailUser(int u_idx) {
		dao.detailUser(u_idx);
	}

	public static UserService2 getInstance() {
		if(service == null) {
			service = new UserService2();
			dao = UserDAO2.getInstance();
			
		}
		return service;
	}
	
	public ArrayList<User2> getUsers() {
		return dao.getUsers();
	}
}