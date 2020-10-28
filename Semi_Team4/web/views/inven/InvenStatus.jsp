<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고현황</title>
<link rel="stylesheet" type="text/css"
	href="${ applicationScope.contextPath }/resources/css/boardCSS/FaQboard.css">
</head>
<body>
	<jsp:include page="/views/common/menubar.jsp"/>

	<div class="invenWrap">
		<form action="${ applicationScope.contextPath }/status.in" method="post">
			<div class="miniInput">
				<input type="submit" value="조회" class="LookupBtn">
			</div>
			<!-- <div class="miniInput">
				<label>조회기준일</label> <input type="date" name="StatusDate" id="StatusDate" size="10"
					class="noneLine">
			</div> -->
			<div class="miniInput">
			<label>오늘날짜</label> <input type="text" name="insertDate" size="10"
				class="noneLine" id="DDay" readonly>
		</div>
			<c:if test="${sessionScope.loginMember.deptCode eq 'HOF01'}">
			<div class="miniInput">
				<label>지점선택</label> 
				<select name="invenStatus" id="invenStatus">
					<option value="BNH01">강남점</option>
					<option value="BNH02">역삼점</option>
					<option value="BNH03">서초점</option>
					<option value="BNH04">청담점</option>
					<option value="BNH05">서울역점</option>
				</select>
			</div>
			</c:if>
			<c:if test="${sessionScope.loginMember.deptCode ne 'HOF01'}">
			<div class="miniInput">
			<label>지점코드</label>
			<input type="text" value="${sessionScope.loginMember.deptCode}" name="invenStatus" id="invenStatus" readonly="readonly">
			</div>
			</c:if>
		</form>
		<br>
		<br><br>

		<%-- <form action="${ applicationScope.contextPath }statusDetail.in" method="post"> --%>
		<div class="invenScroll">
			<table class="invenTable" id="branchInvenList">
				<thead class="invenTr">
					<tr>
						<th>원재료 코드</th>
						<th>원재료명</th>
						<th>분류</th>
						<th>수량</th>
						<th>중량</th>
						<th>상세보기</th>
						<th style="display:none"></th>
						<th style="display:none"></th>
						
					</tr>
				</thead>
				<tbody>
				<c:forEach var="i" items="${ requestScope.list }">
					<tr>
						<td><c:out value="${ i.igretCode }"></c:out></td>
						<td><c:out value="${ i.igretName }"></c:out></td>
						<td><c:out value="${ i.igretClass }"></c:out></td>
						<td><c:out value="${ i.igretQuantity }"></c:out></td>
						<td><c:out value="${ i.igretWeight }"></c:out><c:out value="${ i.buyType }"></c:out></td>
						<td><button class="LookupBtn" id="detailBtn">상세보기</button></td>
					<td style="display:none"><c:out value="${ i.insertDate }"/></td>
					<td style="display:none"><c:out value="${ i.branchCode }"/></td>
					</tr>
				</c:forEach>

				</tbody>
			</table>
			</div>
		<!-- </form> -->
		<div style="border: 1px solid;" align="center">
		<label>총 목록 : </label><input type="text" size="5" class="noneLine1" value="${ requestScope.list.size() }" disabled>
		</div>
	</div>
<script>
$(function(){
	$("#branchInvenList td #detailBtn").mouseenter(function(){
		$(this).css({"background":"#E7E7E7", "cursor":"pointer"});
	}).mouseout(function(){
		$(this).css({"background":"#FFFFFF"});
	}).click(function(){
		var date = $(this).parent().parent().children().eq(6).text();
		var brCode = $(this).parent().parent().children().eq(7).text();
		var igCode = $(this).parent().parent().children().eq(0).text();
		
		var branch = '${sessionScope.loginMember.deptCode}';
		
		if(branch != 'HOF01'){
			brCode = '${sessionScope.loginMember.deptCode}';
		}
		
		location.href="${applicationScope.contextPath}/listDetail.in?date=" + date + "?" + brCode +"?" + igCode;

	});
	
});

//현재날짜	 
$(function() {
	var day = new Date();

	var Year = day.getFullYear();
	var Month = day.getMonth() + 1;
	var Day = day.getDate();

	if (Month < 10)
		Month = "0" + Month;
	if (Day < 10)
		Day = "0" + Day;

	$("#DDay").val(Year + "-" + Month + "-" + Day);
});

</script>
</body>
</html>