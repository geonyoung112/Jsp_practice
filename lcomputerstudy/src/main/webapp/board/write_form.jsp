<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 등록</title>

</head>
<body>
<h1>글쓰기</h1>
<!-- 제출시 등록 글로 넘어가기: write-action.do  -->
<form action = "write-action.do" method="post">
	<table style = "width: 500px;">
		<tr>
			<td >제목</td>
			<td><input type="text" name="b_title" size="50"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="b_content" rows="10" cols="50"></textarea></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="입력">&nbsp;&nbsp;
			</td>
			<td>
				<a href="write-list.do">목록</a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>