<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>order double</title>
<style>
.order {
	position: fixed;
	bottom: 0;
	height: 4rem;
	color: white;
	margin-bottom: 0;
	width: 100%;
	z-index: 25;
}

.select {
	left: 0;
	background: black;
	padding-right: 3rem;
	width: 40%;
	float: left;
}

.delete {
	right: 0;
	background: #F57C98;
	padding-left: 3rem;
	width: 40%;
	float: right;
}
</style>
</head>
<body>
	<div class="order">
		<div class="select" onclick="">
			<h1 align="center">전체 선택하기</h1>
		</div>
		<div class="delete" onclick="">
			<h1 align="center">삭제하기</h1>
		</div>
	</div>
</body>
</html>