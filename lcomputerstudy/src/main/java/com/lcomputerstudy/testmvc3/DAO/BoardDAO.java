package com.lcomputerstudy.testmvc3.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc2.database2.DBConnection2;
import com.lcomputerstudy.testmvc3.vo.Board;
public class BoardDAO {
	
	private static BoardDAO dao = null;
	
	private BoardDAO() {
		
	}
	
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}

	public void write(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection2.getConnection();
			String query="INSERT INTO bbs(b_writer, b_title, b_content, b_date) VALUES (?, ?, ?, NOW())";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getB_writer());
			pstmt.setString(2, board.getB_title());
			pstmt.setString(3, board.getB_content());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public ArrayList<Board> boardlist() {
		ArrayList<Board> boardlist = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection2.getConnection();
			String query="SELECT b_idx, b_writer, b_title, b_content, b_date, b_readcount, b_ref, b_restep, b_relevel "
					+ "FROM bbs ORDER BY b_ref desc, b_restep asc";
	       	pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        boardlist = new ArrayList<Board>();

	        while(rs.next()){     
	        	Board board = new Board();
       	       	board.setB_idx(rs.getInt("b_idx"));
       	       	board.setB_writer(rs.getString("b_writer"));
       	       	board.setB_title(rs.getString("b_title"));
       	       	board.setB_content(rs.getString("b_content"));
       	       	board.setB_date(rs.getTimestamp("b_date"));
       	       	board.setB_readcount(rs.getInt("b_readcount"));
       	       	board.setB_ref(rs.getInt("b_ref"));
       	       	board.setB_restep(rs.getInt("b_restep"));
       	       	board.setB_relevel(rs.getInt("b_relevel"));
       	       	boardlist.add(board);
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
		
		return boardlist;
	
	}

	
// ------ 목록까지 완 -----

	public void readCount(Board board2) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn = DBConnection2.getConnection();
			
			// bReadCount = bReadCount + 1 --> 기본 값 0
			String query="UPDATE bbs SET b_readcount = b_readcount+1 WHERE b_idx=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,board2.getB_idx());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
// ---- return 타입으로 다시 메소드 작성,  controller도 같이 슈정 -- //

	public Board contentView(Board board2) {
		readCount(board2);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		
		try {
			conn = DBConnection2.getConnection();
			String query = "SELECT * FROM bbs WHERE b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board2.getB_idx());
			rs = pstmt.executeQuery();
				
			if(rs.next()) {
				board = new Board();
				board.setB_idx(rs.getInt("b_idx"));
       	       	board.setB_writer(rs.getString("b_writer"));
       	       	board.setB_title(rs.getString("b_title"));
       	       	board.setB_content(rs.getString("b_content"));
       	       	board.setB_date(rs.getTimestamp("b_date"));
       	       	board.setB_readcount(rs.getInt("b_readcount"));
       	       	board.setB_ref(rs.getInt("b_ref"));
       	       	board.setB_restep(rs.getInt("b_restep"));
       	       	board.setB_relevel(rs.getInt("b_relevel"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
					
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return board; 
	}

	public void modify(Board board2) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection2.getConnection();
			String query="UPDATE bbs SET b_title=?, b_content=? WHERE b_idx=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, board2.getB_title());
			pstmt.setString(2, board2.getB_content());
			pstmt.setInt(3, board2.getB_idx());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
}
