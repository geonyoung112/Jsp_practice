package com.lcomputerstudy.testmvc3.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc3.vo.Board;
import com.lcomputerstudy.testmvc3.DAO.BoardDAO;

public class BoardService {

	private static BoardService service = null;
	private static BoardDAO dao = null;
	
	private BoardService() {
		
	}
	


	public void write(String b_writer, String b_title, String b_content) {
		dao.write(b_writer, b_title, b_content);
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


// ---return 타입으로 수정---//
	public void contentView(String b_idx) {
		dao.contentView(b_idx);
	}
}
