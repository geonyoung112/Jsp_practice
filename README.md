# Jsp_practice
과정: MVC 형태로 코드 분리_백, 프론트, DB

## 오류1. 파일명.jsp 에서 .do로 변경(연동되는 모든 파일)
```
예시) <a href = "/lcomputerstudy/list.do">돌아가기</a>
    	<form action="joinresult.do" name="user" method="post">
```
## 오류2. printstacktrace로 작성
```
} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
```
## 오류3. $list
```
list.jsp

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${list}" var="user">
			<tr>
		       <td><a href="detail.do?u_idx=${user.u_idx}">${user.u_idx}</a></td>
				<td>${user.u_id}</td>
				<td>${user.u_name}</td>
		     </tr>
		</c:forEach>
// 리스트를 가져오는 경우 list로 작성함
```       
## 오류 4. u_idx 히든, u_tel 배열 담고 분리
```
edit.jsp

<form action="editprocess.do" name="user" method="post">
		    <input type="hidden" name="u_idx" value="${user.u_idx}"> //히든으로 작성
		    <p> 아이디 : <input type="text" name="edit_id" value="${user.u_id}"></p>
		    <p> 비밀번호 : <input type="password" name="edit_pw" value="${user.u_pw}"></p>
		    <p> 이름 : <input type="text" name="edit_name" value="${user.u_name}"></p>
		    <p> 연락처 : <input type="text" maxlength="4" size="4" name="edit_tel1" value="${user.u_tels[0]}"> -
		               <input type="text" maxlength="4" size="4" name="edit_tel2" value="${user.u_tels[1]}"> -
		               <input type="text" maxlength="4" size="4" name="edit_tel3" value="${user.u_tels[2]}">  //배열에 담고 split을 이용하기_안하면 제대로 입력값이 뜨지 않음
		    </p>
		    <p> 나이 : <input type="text" name="edit_age" value="${user.u_age}"></p>
		    <p> <input type="submit" value="수정완료"></p>
	</form>
  ```
  ```
  user2.java
  
  private String[] u_tels;

	public String[] getU_tels() {
		return u_tels;
	}
	public void setU_tels(String[] u_tels) {
		this.u_tels = u_tels;
	}
  ```
  ## 오류 5. SQLException : (conn=301) Parameter at position 6 is not set 
  ```
  DAO.java
  
   public void editProcess(User2 user4) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection2.getConnection();
            String sql = "UPDATE user SET u_id = ?,u_pw = ?,u_name = ?,u_tel = ?,u_age = ? WHERE u_idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user4.getU_id());
            pstmt.setString(2, user4.getU_pw());
            pstmt.setString(3, user4.getU_name());
            pstmt.setString(4, user4.getU_tel());
            pstmt.setString(5, user4.getU_age());
            pstmt.setInt(6, user4.getU_idx());          //6 값인 u_idx값을 작성
            pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        	try {
        		 if (pstmt != null) pstmt.close();
        		 if (conn != null)  conn.close();
        	}  catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
  ```
  
  ## 오류 6. 
  ```
  controller.java
  
  case "/edit.do":
				int u_idx2 = Integer.parseInt(request.getParameter("u_idx"));
				userService = UserService2.getInstance();
				User2 user3 = userService.editUser(u_idx2); //u_idx2 생성 후 user3 담고 service 파일에 연
			    request.setAttribute("user", user3);
			    view = "test2/userEdit";
			    break;
          
  ```
  ```
  service.java
  
  public User2 editUser(int u_idx2) {
		return dao.detailUser(u_idx2);
	}
  ```
  
  
