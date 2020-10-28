<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kiosk manubar</title>
<link href="<%= request.getContextPath() %>/resources/css/kiosk/kioskMenuBar.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="bar">
	<div id="back">
		<button type="button" onclick="location.href='${applicationScope.contextPath}/views/kiosk/kioskFirst.jsp'">
			<img src="<%= request.getContextPath() %>/resources/images/cursor.png" width="70%" height="70%">
		</button>
		<h1>주문하기</h1>
	</div>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="select.kk?eat=${ requestScope.eat }&menu=all">ALL</a></li>
			<li class="nav-item"><a class="nav-link" href="select.kk?eat=${ requestScope.eat }&menu=setMenu">SET MENU</a></li>
			<li class="nav-item"><a class="nav-link" href="select.kk?eat=${ requestScope.eat }&menu=coffee">COFFEE</a></li>
			<li class="nav-item"><a class="nav-link" href="select.kk?eat=${ requestScope.eat }&menu=juice">JUICE</a></li>
			<li class="nav-item"><a class="nav-link" href="select.kk?eat=${ requestScope.eat }&menu=bread">BREAD</a></li>
			<li class="nav-item"><a class="nav-link" href="select.kk?eat=${ requestScope.eat }&menu=tea">TEA</a></li>
		</ul>
	</nav>
</div>
 				
</body>
</html>