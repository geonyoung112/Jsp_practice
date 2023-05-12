<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reply_view</title>
<style>
	h1 {
		margin: 20px 0;
	}
	table {
		width: 100%;
		margin: 20px 0;
		border-collapse: collapse;
	}
	td {
		padding: 10px;
		border: 1px solid #ccc;
	}
	textarea, input[type="text"] {
		width: 100%;
		padding: 10px;
		border: 1px solid #ccc;
		border-radius: 5px;
		resize: none;
		box-sizing: border-box;
	}

	input[type="submit"], input[type="reset"]{
		display: inline-block;
		padding: 10px 20px;
		margin: 0 10px;
		border: 1px solid #ccc;
		border-radius: 5px;
		text-decoration: none;
		color: #333;
		cursor: pointer;
	}
	input[type="submit"]:hover,  input[type="reset"]:hover {
		background-color: #f5f5f5;
	}
	
	
</style>
</head>
<body>
<h1>답글 쓰기</h1>
	<form action="reply_action.do" name="reply"  method="post">
		<table style="width:500px;">
			
			<%-- ReplyViewCommand --%>
			<input type="hidden" name="b_idx" value="${reply.b_idx}">
			<input type="hidden" name="b_group" value="${reply.b_group}">
			<input type="hidden" name="b_order" value="${reply.b_order}">
			<input type="hidden" name="b_depth" value="${reply.b_depth}">
		
			<tr>
				<%-- 고유번호/수정불가 --%>
				<td>번호</td>
				<td>${reply.b_idx}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${reply.b_readcount}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="u_idx" value="${reply.user.u_id}" readonly></td>	
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="re_title" value="${reply.b_title}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="re_content">${reply.b_content}</textarea></td>
			</tr>
			
		</table>
			<div>
				<input type="submit" value="답변"> 
				<input type="reset" value="재입력">
				<a href="write-list.do">목록</a>
				
			</div>
	</form>

</body>
</html>