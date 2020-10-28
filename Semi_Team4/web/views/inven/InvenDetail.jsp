<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고현황 상세보기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ applicationScope.contextPath }/resources/css/boardCSS/FaQboard.css">

</head>
<body>
		<jsp:include page="/views/common/menubar.jsp"/>
		
		
	<div class="invenWrap">
	<input type="button" value="돌아가기" class="TopsearchBtn" onclick="history.back()">
	<div class="miniInput">
		<label>원재료명</label> <input type="text" name="pCode" size="10" value="${ requestScope.list[0].igretName }" readonly>
	</div>
		<div class="miniInput">
			<label>원재료 코드</label> <input type="text" name="pCode" size="10" value="${ requestScope.list[0].igretCode }" 
				readonly>
		</div>
		<br><br><br>
		<div class="invenScroll">
		<table class="invenTable" id="detailTable">
			<thead>
				<tr class="invenTr">
					<th>순번</th>
					<th>유통기한</th>
					<th>입고일자</th>
					<th>상태</th>
					<th>수량</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="i" items="${ requestScope.list }" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><c:out value="${ i.exDate }"></c:out></td>
					<td><c:out value="${ i.insertDate }"></c:out></td>
					<td><c:out value="${ i.invenType }"></c:out></td>
					<td id="numQ"><c:out value="${ i.igretQuantity }"></c:out></td>
				</tr>
			</c:forEach>

			</tbody>
		</table>
		</div>
		
</body>
</html>