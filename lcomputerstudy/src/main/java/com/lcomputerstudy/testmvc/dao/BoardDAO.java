package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.database.DBConnection2;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;
public class BoardDAO {
	
	private static BoardDAO dao = null;
	
	
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}

	public void writeaction(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection2.getConnection();
			String query="INSERT INTO board(b_title, u_idx, b_content, b_date, b_group, b_order, b_depth) VALUES (?, ?, ?, NOW(), ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getB_title());
			pstmt.setInt(2, board.getU_idx());
			pstmt.setString(3, board.getB_content());
			pstmt.setInt(4, board.getB_group());
			pstmt.setInt(5, board.getB_order());
			pstmt.setInt(6, board.getB_depth());
			pstmt.executeUpdate();
			pstmt.close();
			//새글 작성해보기
			query = "update board set b_group = last_insert_id() where b_idx = last_insert_id()";
			pstmt = conn.prepareStatement(query);
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
	

	public ArrayList<Board> boardlist(Pagination pagination2) {
		ArrayList<Board> boardlist = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pageNum = pagination2.getPageNum();


		try {
			conn = DBConnection2.getConnection();
			String query = new StringBuilder()
					.append("SELECT 		b.*, u.*\n")
					.append("FROM 			board b\n")
					.append("LEFT JOIN user u ON b.u_idx = u.u_idx\n")
					.append("LIMIT			?, ?\n")
					//.append("ORDER BY 		b.b_group DESC, b.b_order DESC, b.b_depth DESC\n")
					.toString();
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, pageNum);
	       	pstmt.setInt(2, Pagination.perPage);
	        rs = pstmt.executeQuery();
	        boardlist = new ArrayList<Board>();

	        while(rs.next()){     
       	       	Board board = new Board();
       	       	User user = new User();
       	       	user.setU_idx(rs.getInt("u_idx"));
       	       	user.setU_id(rs.getString("u_id"));
       	       	user.setU_name(rs.getString("u_name"));
       	       	user.setU_pw(rs.getString("u_pw"));
       	       	user.setU_age(rs.getString("u_age"));
       	       	user.setU_tel(rs.getString("u_tel"));
       	       	board.setUser(user);
       	        //board.getUser().setU_id(rs.getString("u_id")); // 수정된 부분
       	       	
    	       	board.setB_idx(rs.getInt("b_idx"));
       	       	board.setB_title(rs.getString("b_title"));
       	       	board.setB_content(rs.getString("b_content"));
       	       	board.setB_date(rs.getTimestamp("b_date"));
       	       	board.setB_readcount(rs.getInt("b_readcount"));
       	       	board.setB_group(rs.getInt("b_group"));
       	       	board.setB_order(rs.getInt("b_order"));
       	       	board.setB_depth(rs.getInt("b_depth"));
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
	public int getBoardsCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnection2.getConnection();
			String query = "SELECT COUNT(*) AS count FROM board ";
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

	

	public void readCount(int b_idx) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn = DBConnection2.getConnection();
			
			// bReadCount = bReadCount + 1 --> 기본 값 0
			String query="UPDATE board SET b_readcount = b_readcount+1 WHERE b_idx=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, b_idx);
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

	public Board contentView(int b_idx) {
		readCount(b_idx);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		
		try {
			conn = DBConnection2.getConnection();
			String query = "SELECT * FROM board b LEFT JOIN user u ON b.u_idx = u.u_idx WHERE b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b_idx);
			rs = pstmt.executeQuery();
				
			if(rs.next()) {
				board = new Board();
				board.setB_idx(rs.getInt("b_idx"));
				
				User user = new User();
       	       	user.setU_id(rs.getString("u_id"));
       	       	board.setUser(user);
       	       	board.getUser().setU_id(rs.getString("u_id")); // 수정된 부분
       	       	
       	       	board.setB_title(rs.getString("b_title"));
       	       	board.setB_content(rs.getString("b_content"));
       	       	board.setB_date(rs.getTimestamp("b_date"));
       	       	board.setB_readcount(rs.getInt("b_readcount"));
       	       	board.setB_group(rs.getInt("b_group"));
       	       	board.setB_order(rs.getInt("b_order"));
       	       	board.setB_depth(rs.getInt("b_depth"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
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
			String query="UPDATE board SET b_title=?, b_content=? WHERE b_idx=?";
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
	
	public void delete(int b_idx2) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection2.getConnection();
			String query="DELETE FROM board WHERE b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b_idx2);
			pstmt.executeUpdate();
	        
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//------------------답글 상세기능 ----------------------
	public void replyStep(Board board3) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection2.getConnection();
			String query = "UPDATE board SET b_order=b_order+1 WHERE b_group=? and b_step>?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board3.getB_group());
			pstmt.setInt(2, board3.getB_order());
			pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void replyView(Board board3) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = DBConnection2.getConnection();
		    replyStep(board3);
		    String query = "INSERT INTO board(b_title, u_idx, b_content, b_date, b_group, b_order, b_depth) VALUES (?, ?, ?, NOW(), ?, ?, ?)";
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1, board3.getB_title());
		    pstmt.setInt(2, board3.getUser().getU_idx());
		    pstmt.setString(3, board3.getB_content());
		    pstmt.setInt(4, board3.getB_group());
		    pstmt.setInt(5, board3.getB_order() + 1);
		    pstmt.setInt(6, board3.getB_depth() + 1);
		    pstmt.executeUpdate();
			
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}

	public void replyAction(Board board4) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection2.getConnection();
			String query="UPDATE board SET b_title=?, b_content=? WHERE b_idx=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, board4.getB_title());
			pstmt.setString(2, board4.getB_content());
			pstmt.setInt(3, board4.getB_idx());
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
