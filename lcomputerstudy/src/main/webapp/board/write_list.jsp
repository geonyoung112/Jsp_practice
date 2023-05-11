<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write_list</title>
<style>
    h1 {
        text-align: center;
        font-size: 2rem;
          margin: 3rem 0;
    }

    table {
        border-collapse: collapse;
        margin: 1rem auto;
        width: 80%;
    }

    th {
        background-color: lightgreen;
        font-weight: bold;
        text-align: center;
    }

    td, th {
        border: 1px solid #818181;
        padding: 0.5rem;
    }

    a {
   		text-decoration: none;
  		color: black;
        font-weight: bold;
        
    }
    
    a:hover {
  		color: blue;
	}
	
	ul {
		width:600px;
		height:50px;
		margin:10px auto;
	}
	li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
	}

	.button-style {
    	font-size: 16px;
   	 	padding: 8px 16px;
   	 	margin: 2rem auto;
  	}
  	
	table tr:first-child {
	  background-color: #e6f3ff;
	  color: black;
	}
	
	div.pagination {
	  text-align: center;
	}

    
</style>
</head>
<body>
<h1>자유 게시판</h1>
<table>
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
		<td>${brd.b_idx}</td> 
		<td>${brd.user.u_id}</td> 
		<td>
			<%-- 제목이 출력되는 위치 --%>
			<%-- 1부터 글의 레벨까지 --%>
			<a href="content-view.do?b_idx=${brd.b_idx}">${brd.b_title}</a>
		</td> 
		<td><fmt:formatDate value="${brd.b_date}" pattern="yyyy-MM-dd" /></td> 
		<td>${brd.b_readcount}</td> 
	</tr>
	</c:forEach>
	</table>
		
<div align='center'>
  <a href="write-form.do">
    <button class="button-style">글 작성</button>
  </a>
  <a href="logout.do">
    <button class="button-style">로그아웃</button>
  </a>
</div>
<!-- 아래부터 pagination -->
	<div class ="pagination">
	
		<ul>
			 <c:choose>
				<c:when test="${ pagination.prevPage ge 1}"> 
					<li>
						<a href="write-list.do?page=${pagination.prevPage}">
							◀
						</a>
					</li>
				</c:when>
			</c:choose> 
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				<c:choose>
						<c:when test="${pagination.page eq i }">
							
							<li style="background-color:#ededed;">
								<span>${i}</span>
							</li>
						</c:when>
						<c:when test="${ pagination.page ne i }">
							<li>
								<a href="write-list.do?page=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${ pagination.nextPage le pagination.lastPage }">
					<!-- 버튼이 보이지 않는 오류 해결_ge를 le로 변경(>=을 <=으로)-->
					<li style="">
						<a href="write-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
</body>
</html>   