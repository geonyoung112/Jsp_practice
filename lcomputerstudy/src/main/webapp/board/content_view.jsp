<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 보기</title>
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
	.btn-group {
		margin-top: 20px;
		display: flex;
		justify-content: center;
	}
	.btn-group a, input[type="submit"] {
		display: inline-block;
		padding: 10px 20px;
		margin: 0 10px;
		border: 1px solid #ccc;
		border-radius: 5px;
		text-decoration: none;
		color: #333;
		cursor: pointer;
	}
	.btn-group a:hover, input[type="submit"]:hover {
		background-color: #f5f5f5;
	}
</style>
</head>

<body>
<h1>게시글 조회</h1>
	<form action="modify.do" name= "content" method="post">
		<table style="width:500px;">

			<%-- bIdx hidden --%>
			<tr>
				<td>번호</td>
				<td><input type="hidden" name="b_idx" value="${content.b_idx}">${content.b_idx}</td>
			</tr>
			<tr>
			
			
			
				<td>조회수</td>
				<td>${content.b_readcount}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="u_idx" value="${content.user.u_id}" readonly></td>	
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="edit_title" value="${content.b_title}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="edit_content">${content.b_content}</textarea></td>
			</tr>
			
		</table>
		
		<div>
					<input type="submit" value="수정">&nbsp;&nbsp;
					<a href="write-list.do">목록보기</a>&nbsp;&nbsp;
					<a href="write-delete.do?b_idx=${content.b_idx}">삭제</a>&nbsp;&nbsp;
					<a href="reply_view.do?b_group=${content.b_group}&b_order=${content.b_order}&b_depth=${content.b_depth}">답변</a>
			</div>
	</form>
<!-- 댓글 기능을 하는 테이블 따로 만들기 -->

	<h1>댓글 작성</h1>
		<form action="rereply_view.do" name= "reply" method="post">
		<table style ="width:800px;">
	        <tr>
	            <td>댓글 작성자</td>
	            <td><input type="text" name="u_idx" value ="${reply.user.u_id}" readonly></td>
	        </tr>
	        <tr>
	        	<td>댓글 내용</td>
	            <td><textarea rows="5" cols="50" name="content">${reply.re_content}</textarea></td>
	        </tr>       
	      </table>
	      
	       <div>
	        	<input type="hidden" name="bno" value="${reply.b_idx}"> &nbsp;&nbsp;
	            <a href="rereply_view.do">댓글 등록</a>
	        </div>
        
	<h1>댓글 목록</h1> 
			<div>
					<p><input type="hidden" name="rno" value="${reply.re_idx}"></p>
					<p>${reply.user.u_id}/<fmt:formatDate value="${reply.re_date}" pattern="yyyy-MM-dd" /></p>
					<p>${reply.re_content}</p>
			</div>
    </form>







</body>
</html>