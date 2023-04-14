package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lcomputerstudy.testmvc2.service2.UserService2;
import com.lcomputerstudy.testmvc2.vo2.User2;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		
		
		
		switch (command) {
			case "/list.do":
				UserService2 userService = UserService2.getInstance();
				ArrayList<User2> list= userService.getUsers();
				view = "test2/userlist";
				request.setAttribute("list", list);
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
			    user4.setU_id(request.getParameter("edit_id"));
			    user4.setU_pw(request.getParameter("edit_password"));
			    user4.setU_name(request.getParameter("edit_name"));
				user4.setU_tel(request.getParameter("edit_tel1") + "-" + request.getParameter("edit_tel2") + "-" + request.getParameter("edit_tel3"));
				user4.setU_age(request.getParameter("edit_age"));

				userService = UserService2.getInstance();
				userService.editprocess(user4);
				request.setAttribute("user", user4);
				 view = "test2/editProcess";
			    break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}

}


	
