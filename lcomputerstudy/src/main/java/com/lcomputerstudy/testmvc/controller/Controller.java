package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc2.service2.UserService2;
import com.lcomputerstudy.testmvc2.vo2.Pagination;
import com.lcomputerstudy.testmvc2.vo2.User2;
import com.lcomputerstudy.testmvc3.service.BoardService;
import com.lcomputerstudy.testmvc3.vo.Board;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int count = 0;
		String pw = null;
		HttpSession session = null;
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		command = checkSession(request, response, command);
		String view = null;

		

		switch (command) {
			case "/list.do":
				String reqPage = request.getParameter("page");
				if(reqPage != null) {
					page = Integer.parseInt(reqPage);
					
				}
				UserService2 userService = UserService2.getInstance();
				count = userService.getUsersCount();
				Pagination pagination = new Pagination();
				pagination.setPage(page);
				pagination.setCount(count);
				pagination.init();
				
				ArrayList<User2> list= userService.getUsers(pagination);
				
				request.setAttribute("list", list);
				request.setAttribute("pagination", pagination);
				view = "test2/userlist";
				break;
				
			case "/newjoin.do":
				view = "test2/Newjoin";
				break;
				
			case "/joinresult.do":
				User2 user = new User2();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService2.getInstance();
				userService.insertUser(user);
						
				view = "test2/join_process";
				break;
				
			case "/detail.do":
			    int u_idx = Integer.parseInt(request.getParameter("u_idx"));
			    userService = UserService2.getInstance();
			    User2 user2 = userService.detailUser(u_idx);
			    request.setAttribute("user", user2);
			    view = "test2/userDetail";
			    break;
			    
			case "/edit.do":
				int u_idx2 = Integer.parseInt(request.getParameter("u_idx"));
				userService = UserService2.getInstance();
				User2 user3 = userService.editUser(u_idx2);
			    request.setAttribute("user", user3);
			    view = "test2/userEdit";
			    break;
			    
			case "/editprocess.do":
				User2 user4 = new User2();
				user4.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
			    user4.setU_id(request.getParameter("edit_id"));
			    user4.setU_pw(request.getParameter("edit_pw"));
			    user4.setU_name(request.getParameter("edit_name"));
				user4.setU_tel(request.getParameter("edit_tel1") + "-" + request.getParameter("edit_tel2") + "-" + request.getParameter("edit_tel3"));
				user4.setU_age(request.getParameter("edit_age"));

				
				userService = UserService2.getInstance();
				userService.editprocess(user4);
				request.setAttribute("user", user4);
				 view = "test2/editProcess";
			    break;
			    
			    
			case "/delete.do":
			    int u_idx3 = Integer.parseInt(request.getParameter("u_idx"));
			    userService = UserService2.getInstance();
			    userService.deleteUser(u_idx3);
			    request.setAttribute("u_idx", u_idx3);
				view = "test2/userDelete";
			    break;
			    
			case "/user-login.do":
				view = "test2/login";
				break;
			
			case "/user-login-process.do":
				String u_idx1 = request.getParameter("login_id");
				String pw1 = request.getParameter("login_password");
				
				userService = UserService2.getInstance();
				user = userService.loginUser(u_idx1, pw1);
							
				if(user != null) {
					session = request.getSession();
//					session.setAttribute("u_idx", user.getU_idx());
//					session.setAttribute("u_id", user.getU_id());
//					session.setAttribute("u_pw", user.getU_pw());
//					session.setAttribute("u_name", user.getU_name());
					session.setAttribute("user", user);

					view = "test2/login-result";
				} else {
					view = "test2/login-fail";
				}			
				break;
				
			case "/logout.do":
				session = request.getSession();
				session.invalidate();
				view = "test2/login";
				break;
			
			case "/access-denied.do":
				view = "test2/access-denied";
				break;
				
				
				
				
//     -------    게시판 controller	-------- 			
			case "/write.do":
				Board board = new Board();
				board.setB_writer(request.getParameter("b_writer"));
				board.setB_title(request.getParameter("b_title"));
				board.setB_content(request.getParameter("b_content"));
				BoardService boardService = BoardService.getInstance();
				boardService.write(board);
				view = "board/write_view";
				break;
				
				
			case "/write-list.do":
				boardService = BoardService.getInstance();
				ArrayList<Board> boardlist = boardService.boardlist();
				request.setAttribute("listAll", boardlist);
				view = "board/write_list";
				break;
				
// ------ 오류: 글작성시 안적고 목록으로 가도 글이 2개 생성, 등록하고 난 후에도 2개 생성 (하나는 공백) -----
				
			case "/content-view.do":
				int b_idx = Integer.parseInt(request.getParameter("b_idx"));
				boardService = BoardService.getInstance();
			    Board board2 = boardService.contentView(b_idx);
			    request.setAttribute("content", board2);
				view = "board/content_view";
				break;

			case "/modify.do":
				Board board3 = new Board();
				board3.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board3.setB_writer(request.getParameter("edit_writer"));
				board3.setB_title(request.getParameter("edit_title"));
				board3.setB_content(request.getParameter("edit_content"));
				boardService = BoardService.getInstance();
				boardService.modify(board3);
				request.setAttribute("content", board3);
				view = "board/modify";
				break;
				
			case "/write-delete.do":
				int b_idx2 = Integer.parseInt(request.getParameter("b_idx"));
				boardService = BoardService.getInstance();
			    boardService.delete(b_idx2);
				view = "board/delete";
				break;
				
				

		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
		
		String checkSession (HttpServletRequest request, HttpServletResponse response, String command) {
			HttpSession session = request.getSession();
			
			String[] authList = {
					"/list.do"
					,"/newjoin.do"
					,"/joinresult.do"
					,"/detail.do"
					,"/edit.do"
					,"/editprocess.do"
					,"/logout.do"
				};
			
			for(String item : authList) {
				if(item.equals(command)) {
					if(session.getAttribute("user") == null) {
						command = "/access-denied.do";
					}
				}
			}
			return command;
		}
		
		
}


	
