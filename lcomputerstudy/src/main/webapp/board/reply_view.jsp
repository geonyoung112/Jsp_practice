<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reply_view</title>
</head>
<body>
<h1>답글 쓰기</h1>
	<form action="reply.do" name="reply"  method="post">
		<table style="width:500px;">
			
			<%-- ReplyViewCommand --%>
			<input type="hidden" name="b_idx" value="${reply.b_idx}">
			<input type="hidden" name="b_ref" value="${reply.b_ref}">
			<input type="hidden" name="b_restep" value="${reply.b_restep}">
			<input type="hidden" name="b_relevel" value="${reply.b_relevel}">
		
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
				<td><input type="text" name="edit_title" value="${reply.b_title}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="edit_content">${reply.b_content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="답변"> <a href="write-list.do">목록</a>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>