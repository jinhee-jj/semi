<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키오스크</title>
<style>
	body{
		background: #F57C9B;
		text-align: center;
		font-size: 2em;
	}
	.buttons {
		width:600px;
		height:300px;
		background: white;
		border:0px;
		border-radius: 50px;
		margin-bottom: 0.5em;
		font-size: 3em;
		font-weight: bold;
		
	}
	.outer {
		margin-top:10%;
		width:100%;
		height:100%;
		
	}
	
</style>
</head>
<body>
	
	
	
	<!-- <button onclick="openFullScreenMode()">풀스크린</button> -->
	
<div class="outer">
	
	<h1 style="color:white;">어디서 드시겠습니까?</h1>
	
	<h5 style="color:#DED9D9">환경보호에 동참해 주세요.<br>주문 후에는 컵 변경이 불가한 점 양해 부탁드립니다.</h5>
	
	<table align="center" class="tableArea">
		<tr>
			<td>
				<button type="button" class="buttons" onclick="location.href='${pageContext.request.contextPath}/select.kk?eat=room&menu=all'"><img src="../../resources/images/room.png" width="150px" height="150px" >&nbsp;매장</button>
			</td>
		</tr>
		<tr>
			<td>
				<button type="button" class="buttons" onclick="location.href='${pageContext.request.contextPath}/select.kk?eat=pack&menu=all'"><img src="../../resources/images/packing.png" width="150px" height="150px">&nbsp;포장</button>
			</td>
		</tr>
	</table>
</div>	
	
	

	
</body>
</html>