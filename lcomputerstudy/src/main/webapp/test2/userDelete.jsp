<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 삭제</title>
</head>
<body>
	<h1>회원 삭제</h1>
	<form action="delete.do" name="user" method="post">
		<label>삭제할 회원 번호:</label>
		<input type="text" name="u_idx"><br>
		<input type="submit" value="삭제">
	</form>
	<a href="/lcomputerstudy/list.do">회원 목록으로 돌아가기</a>
</body>
</body>
</html>