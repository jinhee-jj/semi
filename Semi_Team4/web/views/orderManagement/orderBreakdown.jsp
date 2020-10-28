<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>주문내역</title>
<link href="<%= request.getContextPath() %>/resources/css/orderManagement/orderBreakdown.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../../views/common/menubar.jsp"/>
	
	<c:if test="${ !empty sessionScope.loginMember }">
	<div id="wrapper">
			<div class="contentArea">
				<br>
				<div id="orderHistoryArea">
					<h2 id="title">주문내역</h2><input type="hidden" id="whatThe" value="${ sessionScope.loginMember.memberDeptCode }">
					
					<!-- 지점 선택은 본사 계정에서만 보이도록 설정하기 -->
							<input type="date" name="date" id="date">
						<c:if test="${ sessionScope.loginMember.memberDeptCode.equals('본사') }">
							<select id="zizum" name="zizum" style="height:25px;">
								<option value="select">지점선택</option>
								
								<c:forEach var="b" items="${ requestScope.branch }">
									<option value="<c:out value="${ b.branchCode }"/>"><c:out value="${ b.branchName }"/>점</option>
								</c:forEach>
								
								
								<!-- <option value="gangnam">강남점</option>
								<option value="yeogsam">역삼점</option> -->
							</select>
						 </c:if>
							<br><br>
					<table align="center" id="orderHistoryTable" class="table-bottom">
						<tr>
							<th width="10%" align="center">주문번호</th>
							<th width="25%" align="center">주문일자</th>
							<th width="10%" align="center">포장여부(Y/N)</th>
							<th width="20%" align="center">판매금액</th>
							<th width="20%" align="center">할인금액</th>
							<th width="20%" align="center">결제금액</th>
						</tr>
						<tbody></tbody>
		 			 	<c:forEach var="n" items="${ requestScope.history }">
							<tr>
								<td><c:out value="${ n.orderNo }"/></td>
								<td><c:out value="${ n.orderDate }"/></td>
								<td><c:out value="${ n.togo }"/></td>
								<td><c:out value="${ n.grossIncome }"/></td>
								<td><c:out value="${ n.discountIncome }"/></td>
								<td><c:out value="${ n.totalIncome }"/></td>
							</tr>
						</c:forEach>
						
						
					</table>	
				</div>
				<div id="historyDetailArea">
					<h2 id="title">상세내역</h2>
					<table align="center" id="historyDetailTable" class="table-bottom">
						<tr>
							<th width="10%" align="center">주문번호</th>
							<th width="30%" align="center">메뉴명</th>
							<th width="5%" align="center">수량</th>
							<th width="15%" align="center">메뉴가격</th>
							<!-- <th width="15%" align="center">메뉴총가격</th> -->
							<th width="15%" align="center">상태</th>
						</tr>
						
						<tbody></tbody>
						
						<c:forEach var="n" items="${ requestScope.detail }">
							<tr>
								<td><c:out value="${ n.orderNumber }"/></td>
								<td><c:out value="${ n.menuName }"/></td>
								<td><c:out value="${ n.orderQuantity }"/></td>
								<td><c:out value="${ n.orderPrice }"/></td>
								<%-- <td><c:out value="${ n.orderMenuPrice }"/></td> --%>
								<td><c:out value="${ n.orderStatus }"/></td>
							</tr>
						</c:forEach>
						
					</table>
				</div>
			</div>
	</div>
	</c:if>
	<script>
		
		$(function() {
			$("#date").change(function(){
				date = $("#date").val();
				
				if( $("#whatThe").val() == "본사" ){
					
					$.ajax({
						url: "${applicationScope.contextPath}/search.om",
						data: {date: date, zizum: zizum
								},
						type: "post",
						success: function(data) {
							
							var $replySelectTable = $("#orderHistoryTable > tbody:last");
							$replySelectTable.html('');

							for(var key in data) {
								
								var $tr = $("<tr>");
								var $orderNo = $("<td>").text(data[key].orderNo);
								var $orderDate = $("<td>").text(data[key].orderDate);
								var $togo = $("<td>").text(data[key].togo);
								var $grossIncome = $("<td>").text(data[key].grossIncome);
								var $discountIncome = $("<td>").text(data[key].discountIncome);
								var $totalIncome = $("<td>").text(data[key].totalIncome);
								
								$tr.append($orderNo);
								$tr.append($orderDate);
								$tr.append($togo);
								$tr.append($grossIncome);
								$tr.append($discountIncome);
								$tr.append($totalIncome);
								
								$replySelectTable.append($tr);
								
								
							}

					},error: function(err) {
								
					}

					});
					
					$.ajax({
						url: "${applicationScope.contextPath}/search2.om",
						data: {date: date, zizum: zizum
								},
						type: "post",
						success: function(data) {
							
							var $detailTable = $("#historyDetailTable > tbody:last");
							$detailTable.html('');

							for(var key in data) {
								
								var $tr = $("<tr>");
								var $orderNo = $("<td>").text(data[key].orderNumber);
								var $orderDate = $("<td>").text(data[key].menuName);
								var $togo = $("<td>").text(data[key].orderQuantity);
								var $grossIncome = $("<td>").text(data[key].orderPrice);
								/* var $discountIncome = $("<td>").text(data[key].orderMenuPrice); */
								var $totalIncome = $("<td>").text(data[key].orderStatus);
								
								$tr.append($orderNo);
								$tr.append($orderDate);
								$tr.append($togo);
								$tr.append($grossIncome);
								/* $tr.append($discountIncome); */
								$tr.append($totalIncome);
								
								$detailTable.append($tr);
								
							}
								
								
							},error: function(err) {
								

					}

					});
					
					
				} else {
					
					
					$.ajax({
						url: "${applicationScope.contextPath}/search.om",
						data: {date: date
								},
						type: "post",
						success: function(data) {
							
							var $replySelectTable = $("#orderHistoryTable > tbody:last");
							$replySelectTable.html('');

							for(var key in data) {
								
								var $tr = $("<tr>");
								var $orderNo = $("<td>").text(data[key].orderNo);
								var $orderDate = $("<td>").text(data[key].orderDate);
								var $togo = $("<td>").text(data[key].togo);
								var $grossIncome = $("<td>").text(data[key].grossIncome);
								var $discountIncome = $("<td>").text(data[key].discountIncome);
								var $totalIncome = $("<td>").text(data[key].totalIncome);
								
								$tr.append($orderNo);
								$tr.append($orderDate);
								$tr.append($togo);
								$tr.append($grossIncome);
								$tr.append($discountIncome);
								$tr.append($totalIncome);
								
								$replySelectTable.append($tr);
								
								
							}

					},error: function(err) {
								
					}

					});
					
					$.ajax({
						url: "${applicationScope.contextPath}/search2.om",
						data: {date: date
								},
						type: "post",
						success: function(data) {
							
							var $detailTable = $("#historyDetailTable > tbody:last");
							$detailTable.html('');

							for(var key in data) {
								
								var $tr = $("<tr>");
								var $orderNo = $("<td>").text(data[key].orderNumber);
								var $orderDate = $("<td>").text(data[key].menuName);
								var $togo = $("<td>").text(data[key].orderQuantity);
								var $grossIncome = $("<td>").text(data[key].orderPrice);
								/* var $discountIncome = $("<td>").text(data[key].orderMenuPrice); */
								var $totalIncome = $("<td>").text(data[key].orderStatus);
								
								$tr.append($orderNo);
								$tr.append($orderDate);
								$tr.append($togo);
								$tr.append($grossIncome);
								/* $tr.append($discountIncome); */
								$tr.append($totalIncome);
								
								$detailTable.append($tr);
								
							}
								
								
							},error: function(err) {
								

					}

					});
					
					
				}
				
				
				
				
			});
			
			$("#zizum").change(function(){
				zizum = $("#zizum option:selected").val();
				
				$.ajax({
					url: "${applicationScope.contextPath}/selectHO.om",
					data: {zizum: zizum
							},
					type: "post",
					success: function(data) {
						
						var $replySelectTable = $("#orderHistoryTable > tbody:last");
						$replySelectTable.html('');

						for(var key in data) {
							
							var $tr = $("<tr>");
							var $orderNo = $("<td>").text(data[key].orderNo);
							var $orderDate = $("<td>").text(data[key].orderDate);
							var $togo = $("<td>").text(data[key].togo);
							var $grossIncome = $("<td>").text(data[key].grossIncome);
							var $discountIncome = $("<td>").text(data[key].discountIncome);
							var $totalIncome = $("<td>").text(data[key].totalIncome);
							
							$tr.append($orderNo);
							$tr.append($orderDate);
							$tr.append($togo);
							$tr.append($grossIncome);
							$tr.append($discountIncome);
							$tr.append($totalIncome);
							
							$replySelectTable.append($tr);
							
							
						}
		 				
						
					},error: function(err) {
						

					}

							
				});
				
				
				
				
				$.ajax({
					url: "${applicationScope.contextPath}/selectHO2.om",
					data: {zizum: zizum
							},
					type: "post",
					success: function(data) {
						
						var $detailTable = $("#historyDetailTable > tbody:last");
						$detailTable.html('');

						for(var key in data) {
							
							var $tr = $("<tr>");
							var $orderNo = $("<td>").text(data[key].orderNumber);
							var $orderDate = $("<td>").text(data[key].menuName);
							var $togo = $("<td>").text(data[key].orderQuantity);
							var $grossIncome = $("<td>").text(data[key].orderPrice);
							/* var $discountIncome = $("<td>").text(data[key].orderMenuPrice); */
							var $totalIncome = $("<td>").text(data[key].orderStatus);
							
							$tr.append($orderNo);
							$tr.append($orderDate);
							$tr.append($togo);
							$tr.append($grossIncome);
							/* $tr.append($discountIncome); */
							$tr.append($totalIncome);
							
							$detailTable.append($tr);
							
						}
							
							
						},error: function(err) {
							

				}

				});
				

				
			});
			
		});
		
		
	</script>
</body>
</html>