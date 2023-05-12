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

import com.lcomputerstudy.testmvc.service.BoardService;
import com.lcomputerstudy.testmvc.service.UserService2;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User2;

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
		User2 user2 = null;

		

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
				user2.setU_id(request.getParameter("id"));
				user2.setU_pw(request.getParameter("password"));
				user2.setU_name(request.getParameter("name"));
				user2.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user2.setU_age(request.getParameter("age"));
				
				userService = UserService2.getInstance();
				userService.insertUser(user2);
						
				view = "test2/join_process";
				break;
				
			case "/detail.do":
			    int u_idx = Integer.parseInt(request.getParameter("u_idx"));
			    userService = UserService2.getInstance();
			    user2 = userService.detailUser(u_idx);
			    request.setAttribute("user", user2);
			    view = "test2/userDetail";
			    break;
			    
			case "/edit.do":
				int u_idx2 = Integer.parseInt(request.getParameter("u_idx"));
				userService = UserService2.getInstance();
				user2 = userService.editUser(u_idx2);
			    request.setAttribute("user", user2);
			    view = "test2/userEdit";
			    break;
			    
			case "/editprocess.do":
				user2.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
			    user2.setU_id(request.getParameter("edit_id"));
			    user2.setU_pw(request.getParameter("edit_pw"));
			    user2.setU_name(request.getParameter("edit_name"));
				user2.setU_tel(request.getParameter("edit_tel1") + "-" + request.getParameter("edit_tel2") + "-" + request.getParameter("edit_tel3"));
				user2.setU_age(request.getParameter("edit_age"));
				userService = UserService2.getInstance();
				userService.editprocess(user2);
				request.setAttribute("user", user2);
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
				user2 = userService.loginUser(u_idx1, pw1);
							
				if(user2 != null) {
					session = request.getSession();
//					session.setAttribute("u_idx", user.getU_idx());
//					session.setAttribute("u_id", user.getU_id());
//					session.setAttribute("u_pw", user.getU_pw());
//					session.setAttribute("u_name", user.getU_name());
					session.setAttribute("user", user2);

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
			case "/write-form.do":
				HttpSession session2 = request.getSession();
				if (session2 == null || session2.getAttribute("user") == null) {
				    view = "test2/login";
				} else {
					view = "board/write_form";
				}
				break;
				
			case "/write-action.do":
				session = request.getSession();
				//getAttribute는 기본 object를 가져오기 때문에 우리가 사용하고자 하는 class를 다운 캐스팅하기
				user2 = (User2)session.getAttribute("user");
				Board board  = new Board();
				board.setB_title(request.getParameter("b_title"));
				board.setB_content(request.getParameter("b_content"));
				board.setU_idx(user2.getU_idx());
				BoardService boardService = BoardService.getInstance();
				boardService.writeaction(board);
				view = "board/write_action";
				break;
				
			case "/write-list.do":
				String reqPage2 = request.getParameter("page");
				if(reqPage2 != null) {
					page = Integer.parseInt(reqPage2);
					
				}
				boardService = BoardService.getInstance();
				count = boardService.getBoardsCount();
				Pagination pagination2  = new Pagination();
				pagination2.setPage(page);
				pagination2.setCount(count);
				pagination2.init();
				
				ArrayList<Board> boardlist = boardService.boardlist(pagination2);
				request.setAttribute("listAll", boardlist);
				request.setAttribute("pagination", pagination2);
				view = "board/write_list";
				break;
				
			case "/content-view.do":
				int b_idx = Integer.parseInt(request.getParameter("b_idx"));
				boardService = BoardService.getInstance();
			    board = boardService.contentView(b_idx);
			    request.setAttribute("content", board);
				view = "board/content_view";
				break;

			case "/modify.do":
				Board board2 = new Board();
				board2.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
       	       //	board3.getUser().setU_id(request.getParameter("u_id")); // 수정된 부분
				board2.setB_title(request.getParameter("edit_title"));
				board2.setB_content(request.getParameter("edit_content"));
				boardService = BoardService.getInstance();
				boardService.modify(board2);
				request.setAttribute("content", board2);
				view = "board/modify";
				break;
				
			case "/write-delete.do":
				int b_idx2 = Integer.parseInt(request.getParameter("b_idx"));
				boardService = BoardService.getInstance();
			    boardService.delete(b_idx2);
				view = "board/delete";
				break;
				
//------------------답글 상세기능 ----------------------			
			case "/reply_view.do":
				Board board3 = new Board();
				board3.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
				boardService.replyView(board3);
				request.setAttribute("reply", board3);
				view = "board/reply_view";
				break;
				
			case "/reply_action.do":
				session = request.getSession();
				//getAttribute는 기본 object를 가져오기 때문에 우리가 사용하고자 하는 class를 다운 캐스팅하기
				user2 = (User2)session.getAttribute("user");
				
				Board board4 = new Board();
				board4.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
       	       //	board3.getUser().setU_id(request.getParameter("u_id")); // 수정된 부분
				board4.setB_title(request.getParameter("re_title"));
				board4.setB_content(request.getParameter("re_content"));
				boardService = BoardService.getInstance();
				boardService.replyAction(board4);
				request.setAttribute("reply", board4);
				view = "board/reply_action";
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


	
