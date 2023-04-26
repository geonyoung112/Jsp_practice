<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write_list</title>
<style>
	table{border-collapse: collapse;}
	table, td, th {border: 1px solid black;}
	span.brelevel{font-size:7px;color:red;}
</style>
</head>
<body>
<table style="width:500px;">
	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>제목</td>
		<td>날짜</td>
		<td>조회수</td>
	</tr>
	
	<%-- request.setAttribute("listAll",list); --%>
	<%-- forEach 반복문/글목록 --%>
	<c:forEach var="brd" items="${listAll}"> 
	<tr>
		<%-- 변수가 아닌 getter method 호출 --%>
		<td>${brd.b_idx}</td> 
		<td>${brd.b_writer}</td> 
		<td>
			<%-- 제목이 출력되는 위치 --%>
			<%-- 1부터 글의 레벨까지 --%>
			<c:forEach begin="1" end="${brd.b_relevel}"><span class="b_relevel">[re]</span></c:forEach>
			<a href="content-view.do?b_idx=${brd.b_idx}">${brd.b_title}</a>
		</td> 
		<td><fmt:formatDate value="${brd.b_date}" pattern="yyyy-MM-dd" /></td> 
		<td>${brd.b_readcount}</td> 
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5"><a href="write-form.do">글 작성</a></td>
	</tr>
</table>
</body>
</html>