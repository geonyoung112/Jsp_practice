<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>

<Style>
	table {
		borde-collapse:collapse;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border: 1px solid #818181;
		width:200px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#0000;
		font-weight:700;
	}
</Style>
</head>

<body>
	<h1>회원 목록</h1>
	<table>
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
		</tr>
		<c:forEach items="${list2}" var = "item">
				<td>${item.u_idx}</td>
				<td>${item.u_id}</td>
				<td>${item.u_name}</td>
		     <tr>
		</c:forEach>
	</table>
</body>
</html>