package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.database.DBConnection2;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User2;

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
	
	public ArrayList<User2> getUsers(Pagination pagination){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User2> list = null;
		int pageNum = pagination.getPageNum();
		
		
		try {
			conn = DBConnection2.getConnection();
			//String query = "select * from user limit ?,3";
			String query = new StringBuilder()
					.append("SELECT 		ta.*\n")
					.append("FROM 			user ta\n")
					.append("LIMIT			?, ?\n")
					.toString();
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, pageNum);
	       	pstmt.setInt(2, Pagination.perPage);
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
	
	public int getUsersCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnection2.getConnection();
			String query = "SELECT COUNT(*) count FROM user ";
	       	pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){     
	        	count = rs.getInt("count");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public void insertUser(User2 user4) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection2.getConnection();
			String sql = "insert into user(u_id,u_pw,u_name,u_tel,u_age) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user4.getU_id());
			pstmt.setString(2, user4.getU_pw());
			pstmt.setString(3, user4.getU_name());
			pstmt.setString(4, user4.getU_tel());
			pstmt.setString(5, user4.getU_age());
			pstmt.executeUpdate();
		} catch( Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public User2 detailUser(int u_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User2 user2 = null;

		try {
			conn = DBConnection2.getConnection();
			String sql = "select * from user where u_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_idx);
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				user2 = new User2();
				user2.setU_idx(rs.getInt("u_idx"));
		   	    user2.setU_id(rs.getString("u_id"));
		   	    user2.setU_pw(rs.getString("u_pw"));
		   	    user2.setU_name(rs.getString("u_name"));
		        user2.setU_tel(rs.getString("u_tel"));
       	       	user2.setU_tels(user2.getU_tel().split("-"));
		   	    user2.setU_age(rs.getString("u_age"));
			}
		} catch(Exception e) {
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
				
			return user2;
		}
	
	
	
    public User2 editUser(int u_idx2){
    	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User2 user2 = null;

        try {
			conn = DBConnection2.getConnection();
            String sql = "select * from user where u_idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, u_idx2);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	user2 = new User2(); 
                user2.setU_idx(rs.getInt("u_idx"));
                user2.setU_id(rs.getString("u_id"));
                user2.setU_pw(rs.getString("u_pw"));
                user2.setU_name(rs.getString("u_name"));
                user2.setU_tel(rs.getString("u_tel"));
       	       	user2.setU_tels(user2.getU_tel().split("-"));
                user2.setU_age(rs.getString("u_age"));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }  finally {

			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user2;
    }
    
    
    
    public void editProcess(User2 user4) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection2.getConnection();
            String sql = "UPDATE user SET u_id = ?,u_pw = ?,u_name = ?,u_tel = ?,u_age = ? WHERE u_idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user4.getU_id());
            pstmt.setString(2, user4.getU_pw());
            pstmt.setString(3, user4.getU_name());
            pstmt.setString(4, user4.getU_tel());
            pstmt.setString(5, user4.getU_age());
            pstmt.setInt(6, user4.getU_idx());
            pstmt.executeUpdate();

        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
        		 if (pstmt != null) pstmt.close();
        		 if (conn != null)  conn.close();
        	}  catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

    public void deleteUser(int u_idx3) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection2.getConnection();
            String query = "DELETE FROM user WHERE u_idx = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, u_idx3);
            pstmt.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
        		if (pstmt != null) pstmt.close();
    			if (conn != null)  conn.close();
	        } catch (SQLException e) {
				e.printStackTrace();
	        }
        }
    }
    
    public User2 loginUser(String idx, String pw) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	User2 user2 = null;
    	
    	try {
			conn = DBConnection2.getConnection();
			String sql = "SELECT * FROM user WHERE u_id = ? AND u_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()){     
				user2 = new User2();
				user2.setU_idx(rs.getInt("u_idx"));
	        	user2.setU_pw(rs.getString("u_pw"));
	        	user2.setU_id(rs.getString("u_id"));
	        	user2.setU_name(rs.getString("u_name"));
		   }
		} catch( Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user2;
	} 

}
	
