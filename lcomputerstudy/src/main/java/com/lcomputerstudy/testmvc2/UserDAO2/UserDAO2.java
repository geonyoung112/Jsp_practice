package com.lcomputerstudy.testmvc2.UserDAO2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc2.database2.DBConnection2;
import com.lcomputerstudy.testmvc2.vo2.User2;

public class UserDAO2 {
	private static UserDAO2 dao = null;
	
	private UserDAO2() {
		
	}
	
	public static UserDAO2 getInstance() {
		if(dao == null) {
			dao = new UserDAO2();
		}
		
		return dao;
	}
	
	public ArrayList<User2> getUsers(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User2> list = null;
		
		
		try {
			conn = DBConnection2.getConnection();
			String query = "select * from user";
	       	pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        list = new ArrayList<User2>();

	        while(rs.next()){     
	        	User2 user = new User2();
       	       	user.setU_idx(rs.getInt("u_idx"));
       	       	user.setU_id(rs.getString("u_id"));
       	       	user.setU_name(rs.getString("u_name"));
       	       	user.setU_tel(rs.getString("u_tel"));
       	       	user.setU_age(rs.getString("u_age"));
       	       	list.add(user);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	} 
	
	
	public void insertUser(User2 user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection2.getConnection();
			String sql = "insert into user(u_id,u_pw,u_name,u_tel,u_age) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getU_id());
			pstmt.setString(2, user.getU_pw());
			pstmt.setString(3, user.getU_name());
			pstmt.setString(4, user.getU_tel());
			pstmt.setString(5, user.getU_age());
			pstmt.executeUpdate();
		} catch( Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteUser(User2 user) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    try {
	        conn = DBConnection2.getConnection();
	        String sql = "DELETE FROM user WHERE u_idx=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, user.getU_idx());
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
	}
	
	public User2 detailUser(int u_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User2 user = null;

			try {
				conn = DBConnection2.getConnection();
				String query = "select * from user where u_idx=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, u_idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					user = new User2();
					user.setU_idx(rs.getInt("u_idx"));
		   	       	user.setU_id(rs.getString("u_id"));
		   	       	user.setU_name(rs.getString("u_name"));
		   	       	user.setU_tel(rs.getString("u_tel"));
		   	       	user.setU_age(rs.getString("u_age"));
				}
			} catch(Exception e) {
				e.printStackTrace();
			} 
			return user;
		}
}