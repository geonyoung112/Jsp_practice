package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Reply;

public class BoardService {

	private static BoardService service = null;
	private static BoardDAO dao = null;
	
	private BoardService() {
		
	}

	public void writeaction(Board board) {
		dao.writeaction(board);
	}
	
	
	// ---return 타입으로 수정---//
		public Board contentView(int b_idx) {
			return dao.contentView(b_idx);
		}

	public void modify(Board board2) {
		dao.modify(board2);
	}
	
	public void delete(int b_idx2) {
		dao.delete(b_idx2);
	}
	
	/**public void replyView(Board board3) {
		dao.replyView(board3);
	}**/
	
	public int getBoardsCount() {
		return dao.getBoardsCount();
	}

	
	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}
	
	public ArrayList<Board> boardlist(Pagination pagination2) {
		return dao.boardlist(pagination2);
	}

	public void replyAction(Board board4) {
		dao.replyAction(board4);
		
	}

	public Reply rereplyView(Reply reply) {
		// TODO Auto-generated method stub
		return dao.rereplyview(reply);
	}


}
