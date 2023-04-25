package com.lcomputerstudy.testmvc3.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc3.vo.Board;
import com.lcomputerstudy.testmvc3.DAO.BoardDAO;

public class BoardService {

	private static BoardService service = null;
	private static BoardDAO dao = null;
	
	private BoardService() {
		
	}
	


	public void write(Board board) {
		dao.write(board);
	}
	
	
	// ---return 타입으로 수정---//
		public Board contentView(Board board2) {
			return dao.contentView(board2);
		}

	public void modify(Board board2) {
		// TODO Auto-generated method stub
		dao.modify(board2);
	}
	
	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}
	
	public ArrayList<Board> boardlist() {
		return dao.boardlist();
	}


}
