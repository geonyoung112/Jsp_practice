package com.lcomputerstudy.testmvc2.service2;

import java.util.ArrayList;
import com.lcomputerstudy.testmvc2.UserDAO2.UserDAO2;
import com.lcomputerstudy.testmvc2.vo2.Pagination;
import com.lcomputerstudy.testmvc2.vo2.User2;

public class UserService2 {

	private static UserService2 service = null;
	private static UserDAO2 dao = null;
	
	private UserService2() {
		
	}
	
	public void insertUser(User2 user) {
		dao.insertUser(user);
	}                    
	
	public User2 detailUser(int u_idx) {
		return dao.detailUser(u_idx);
	}
	
	public User2 editUser(int u_idx2) {
		return dao.detailUser(u_idx2);
	}
	
	public void editprocess(User2 user4) {
		dao.editProcess(user4);
	}
	
	public void deleteUser(int u_idx3) {
		dao.deleteUser(u_idx3);
	}
	
	public int getUsersCount() {
		return dao.getUsersCount();
	}
	
	public static UserService2 getInstance() {
		if(service == null) {
			service = new UserService2();
			dao = UserDAO2.getInstance();
			
		}
		return service;
	}
	
	public ArrayList<User2> getUsers(Pagination pagination) {
		return dao.getUsers(pagination);
	}




}