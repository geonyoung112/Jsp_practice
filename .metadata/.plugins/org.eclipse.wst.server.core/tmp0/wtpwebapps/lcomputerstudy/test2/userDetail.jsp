<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 정보</title>
</head>
<body>
	<h1>회원 상세 정보</h1>
	<table>
		<%
			String u_idx = request.getParameter("u_idx");

			User user = UserService2.getInstance().getUser(Integer.parseInt(u_idx));
		%>
		<tr>
			<td>회원 번호</td>
			<td><%= user.getU_idx() %></td>		
		</tr>
		<tr>
			<td>회원 ID</td>
			<td><%= user.getU_id() %></td>		
		</tr>
		<tr>
			<td>회원 이름</td>
			<td><%= user.getU_name() %></td>		
		</tr>
		<tr>
			<td>회원 전화번호</td>
			<td><%= user.getU_tel() %></td>		
		</tr>
		<tr>
			<td>회원 나이</td>
			<td><%= user.getU_age() %></td>		
		</tr>
		<tr>
			<td colspan="2">
				<a href="list.do" style="font-weight:700;background-color:#818181;color:#fff;padding:10px;display:inline-block;">목록</a>
				<a href="edit.do?u_idx=<%= user.getU_idx() %>" style="font-weight:700;background-color:#818181;color:#fff;padding:10px;display:inline-block;">수정</a>
				<a href="delete.do?u_idx=<%= user.getU_idx() %>" style="font-weight:700;background-color:red;color:#fff;padding:10px;display:inline-block;">삭제</a>
			</td>
		</tr>
	</table>
</body>

</html>