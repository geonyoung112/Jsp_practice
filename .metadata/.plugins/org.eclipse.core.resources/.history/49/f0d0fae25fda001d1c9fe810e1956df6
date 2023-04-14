<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
</head>
<body>
	<h2>회원 정보 수정</h2>
	<c:forEach items="${list}" var = "item2">
		<form action="edit.do" name="user" method="post">
		
		    <p> 아이디 : <input type="text" name="edit_id" value="${item2.u_id}"></p>
		    <p> 비밀번호 : <input type="password" name="edit_password" value="${item2.u_pw}"></p>
		    <p> 이름 : <input type="text" name="edit_name" value="${item2.u_name}">
		    <p> 연락처 : <input type="text" maxlength="4" size="4" name="edit_tel1" value="${item2.tel[0]}"> -
		               <input type="text" maxlength="4" size="4" name="edit_tel2" value="${item2.tel[1]}"> -
		               <input type="text" maxlength="4" size="4" name="edit_tel3" value="${item2.tel[2]}"> 
		    </p>
		    <p> 나이 : <input type="text" name="edit_age" value="${item2.u_age}"></p>
		    <p> <input type="submit" value="수정완료"></p>
		</form>
	</c:forEach>
</body>
</html>