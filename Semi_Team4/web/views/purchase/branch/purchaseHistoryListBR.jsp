<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>지점-발주내역</title>
<link
	href="<%=request.getContextPath()%>/resources/css/purchase/purchaseHistoryListBR.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../../common/menubar.jsp" />
		<div id="historyListSection" style="overflow:scroll;">
			<div id="branchArea" align="center">
				<table>
					<tr>
						<td>지점명 :</td>
						<td><c:out value="${requestScope.branchName }점"/></td>
					</tr>
				</table>
			</div>
				<form action="${ applicationScope.contextPath }/selectpListBR.pu" method="get">
					<table align="right">
						<td><input type="date" id="startDay" name="startDay"></td><td>~</td><td><input type="date" id="endDay" name="endDay"></td><td><input type="button" id="dateBtn" value="조회"></td>
					</table>
				</form>
			<table align="center" id="purchaseHistoryTable" class="tableBottom" >
				<thead>
					<tr>
						<th width="10%">순번</th>
						<th width="20%">발주일자</th>
						<th width="30%">발주내역</th>
						<th width="10%">상세보기</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="repList" items="${ requestScope.repList }">
						<tr align="center">
							<td class="pCode"><c:out value="${ repList.rowNum }" /></td>
							<td class="pDate"><c:out value="${ repList.purchaseDate }" /></td>
							<td><c:out
									value="${ repList.iNameRep } 외  ${ repList.cnt }건 " /></td>
							<td align="center"><button class="detailBtn">상세보기</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	<!-- 상세보기 누르면 열리는 모달창 -->
	<div id="detailContent" align="center" class="tableBottom">
		<table class="detailTable">
			<thead>
				<tr>
					<th width="20%">분류</th>
					<th width="40%">제품코드</th>
					<th width="30%">제품명</th>
					<th width="30%">수량</th>
					<th width="15%"></th>
				</tr>
			</thead>
			<tbody>

			</tbody>

		</table>
		<br>
		<button id="detailCloseBtn">확인</button>
	</div>
	<div id="modalBackground"></div>

	<script>
		/* 모달창  */
		$(function() {
			$(".detailBtn, #modalBackground, #detailCloseBtn").click(
					function() {
						$("#detailContent, #modalBackground").toggle();
					});
		});

		/* 상세버튼 클릭 시  */
		$(".detailBtn").click(
				function() {
					//상세보기 누른 행의 순번 가져오기
					var pDate = $(this).parent().parent().children().eq(1)
							.text();

					console.log(pDate);
					var arr = pDate.split(/:| /);
					console.log(arr);
					console.log(arr[0] + ", " + arr[1] + ", " + arr[2]+", " + arr[3]);

					var date = arr[0];
					var hour = arr[1];
					var min = arr[2];
					var sec = arr[3];
					
					$.ajax({
						url : "selectOneHistoryBR.pu",
						type : "get",
						data : {
							date : date,
							hour : hour,
							min : min,
							sec : sec
						},
						success : function(data) {
							var $detailTableBody = $(".detailTable tbody");
							$detailTableBody.html('');

							for ( var index in data) {
								var $tr = $("<tr>");
								var $td = $("<td>");
								var $cNameTd = $("<td>").text(
										data[index].iClassName);
								var $codeTd = $("<td>").text(
										data[index].ingredientCode);
								var $nameTd = $("<td>").text(
										data[index].ingredientName);
								var $quanTd = $("<td>").text(
										data[index].purchseQuantity);
								var $lastTd = $("<td>").text("개");

								$tr.append($cNameTd);
								$tr.append($codeTd);
								$tr.append($nameTd);
								$tr.append($quanTd);
								$tr.append($lastTd);
								$detailTableBody.append($tr);
								
					}
						},
						error : function() {
							console.log("지점 발주내역 상세조회 실패");
						}
					})
				});
		
		$("#dateBtn").click(function(){
			var startDay = $("#startDay").val();
			var endDay = $("#endDay").val();
			console.log(startDay);
			console.log(endDay);
			
			if($("#startDay").val() != '' && $("#endDay").val() != ''){
				
				$("#dateBtn").attr("type", "submit");
				
			} else {
				alert("조회할 날짜를 입력하세요");
			}
		});
		
		
		$(document).ready(function() {
		    var date = new Date();

		    var day = date.getDate();
		    var month = date.getMonth() + 1;
		    var year = date.getFullYear();

		    if (month < 10) month = "0" + month;
		    if (day < 10) day = "0" + day;

		    var today = year + "-" + month + "-" + day;   
		    $("#startDay").attr("max", today);
		    $("#endDay").attr("max", today);
		});
		
		

	</script>
</body>
</html>