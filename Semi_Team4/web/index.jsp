
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%-- <%Member loginMember = (Member) session.getAttribute("loginMember");%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%= request.getContextPath() %>/resources/css/common/login.css" rel="stylesheet" type="text/css">
<title>SEMI-LOGIN</title>

</head>
<body>

<div id="main">
<br><hr>
<br><br><br>
<div class="logowrap">
    <div class="logo" id="logo">
    	<img src="resources/images/logo.png"><br> 
    </div>  

	    <form action="<%=request.getContextPath()%>/loginMember.me" method="post">
	        <div class="right">
	            <h2 align="center">LOGIN</h2>
	            
	            <div class="radio-DEPTCODE">
		           	<input type="radio" id="HO" name="deptCode" value="HOF" checked>본사
		           	<input type="radio" id="BR" name="deptCode" value="BNH">지점
		           	<input type="radio" id="KIOSK" name="deptCode" value="KOK">키오스크
	            </div>
	                    
	            <div class="fields"> 
	                 <div class="id">ID<input type="text" class="memberId" name="memberId" autofocus  placeholder="ID를 입력하세요."></div>                                                             
	                 <div class="password">Password<input type="password" class="memberPwd" name="memberPwd" autofocus  placeholder="비밀번호를 입력하세요."></div>  
	            </div>
	            <button type="submit" class="signInButton">확인</button>
	            
	            <div class="link">
	            	<a href="views/member/passwordSearch.jsp">비밀번호 찾기</a>
	            </div>  
				<br><br>
	        </div>           
	     </form>

</div>  
		 <br><br><br>
	     <p align="center" class="loginpclear"> 로그인 오류 시 02-123-1234 로 전화주세요</p> 
		 <br><hr>
		 <p align="center" class="footer">COOL</p>      
</div>
</body>
</html>