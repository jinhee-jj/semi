<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<% 	String message = (String)request.getAttribute("message"); //키값만 뽑으면 벨류는 알아서 담김    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1 align="center">에러 발생!!</h1>
	<h3 align="center"><%=message %></h3>

</body>
</html>


