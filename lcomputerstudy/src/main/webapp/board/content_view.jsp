<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 보기</title>
</head>
<body>
<h2>게시글 수정</h2>
	<form action="modify.do" name= "content" method="post">
		<table style="width:500px;">
			<%-- bIdx hidden --%>
			<input type="hidden" name="b_idx" value="${content.b_idx}">
			<tr>
				<td>번호</td>
				<td>${content.b_idx}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${content.b_readcount}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="b_writer" value="${content.b_writer}"></td>	
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="b_title" value="${content.b_title}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="b_content">${content.b_content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정">&nbsp;&nbsp;
					<a href="write-list.do">목록보기</a>&nbsp;&nbsp;
					<!--  <a href="delete.do?b_idx=${content.b_idx}">삭제</a>&nbsp;&nbsp;-->
					<!--  <a href="reply_view.do?b_idx=${content.b_idx}">답변</a>-->
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>