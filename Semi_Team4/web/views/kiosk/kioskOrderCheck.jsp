<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/resources/css/kiosk/kioskOrderCheckCss.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<title>주문확인서</title>
</head>
<body>
	<div class="outer">
		<div class="header">
			<a href="#">
				<img width="10%" src="<%= request.getContextPath() %>/resources/images/cursor.png" onclick="location.href='${ applicationScope.contextPath }/cartList.kk?eat=${ requestScope.eat }&menu=${ requestScope.menu }'">
			</a>
			<h2 id="title">주문확인</h2>
			<br>
		</div>
		
		<div class="selectedItemsBasket">
			<!-- c:forEach 또는 jQuery 반복문 사용 -->

			<!-- 예시데이터 -->
			<table>
				
				 <c:forEach var="c" items="${ requestScope.cart }">
									<tr>
					<td rowspan="2"><img width="100px" height="100px" src="<c:out value="${ c.image }"/>"></td>
					<c:set var="ct" value="${ c.count }"></c:set>
					<input type="hidden" class="menuList" value="<c:out value="${ c.menuName }"/>">
					<input type="hidden" class="menuCT" value="<c:out value="${ c.count }"/>">
					<td><label style="font-size: 1.3rem;" id="menuName" ><c:out value="${ c.menuName }"/> <c:out value="${ c.count }"/>개</label></td>
				</tr>
				
				<tr>
					<td><label id="menuPrice"><c:out value="${ c.menuClass }"/>원</label></td>
				</tr>

				</c:forEach>
				
				
				
			</table>
		</div>
		
		<div class="summary">
			<div style="font-size: 2rem;" class="inform">텀블러를 가지고 와서 할인을 받으세요!</div>
			<div class="discountCheck">
				<label>텀블러에 받을게요</label>
				<input type="checkbox" class="selectTumbler" id="selectTumbler" hidden>
				<label for="selectTumbler">
					<img class="check" src="<%= request.getContextPath() %>/resources/images/check.png">
					<img hidden class="pinkCheck" src="<%= request.getContextPath() %>/resources/images/pinkCheck.png">
				</label>
			</div>
			<table class="informTable">
				<tr>
					<td>총 주문금액</td>
					<td id="priceG">${ requestScope.allP }원</td>
				</tr>
				
				<tr>
					<td>할인금액</td>
					<td id="disNum">- 0원</td>
				</tr>

				<tr>
					<td><br>최종 결제금액</td>
					<input type="hidden" id="HOHOHO" value="${ requestScope.allP }">
					<td id="allResult">${ requestScope.allP }원</td>
				</tr>
			</table>
		</div>

		<div class="orderBtn" id="orderComplte" >주문하기</div>
	</div>
	
	<script>
	var disPrice = 0;
	//체크박스 선택 시 이미지 변경
	$(function(){
		$(".selectTumbler").click(function(){
			
			if($(this).is(":checked")) {
				$(this).siblings("label").children().attr("src", "<%= request.getContextPath() %>/resources/images/pinkCheck.png");
				
				
				var oror = $(".menuList");
				oooo = $(".menuCT");

 				var nowput = '';
				$.each(oooo, function(index, value){
					
					if(index == 0) {
						nowput += value.value;
					} else {
						nowput += "," + value.value;
					}
					
					
				});
				popo = nowput.split(',');
				
				var output = '';
				$.each(oror, function(index, value){
					
					if(index == 0) {
						output += value.value;
					} else {
						output += "," + value.value;
					}
					
					
				});
				var sc = output.split(',');
				
				var outsl = '';
				$.each(sc, function(index, item){
					
					outsl = sc[index].slice(-4,-1);
					smud = sc[index].slice(-3,-1);
					
					if(outsl == 'HOT'){
						disPrice += 100 * popo[index];
					} else if(outsl == 'ICE'){
						disPrice += 100 * popo[index];
					} else if(smud == '스무') {
						disPrice += 100 * popo[index];
					} else {
						
					}
					

				});
				
				priceG = $("#HOHOHO").val();
 				$("#disNum").text("- " + disPrice +"원");
				resultPrice = Number(priceG) - disPrice;
 				
 				$("#allResult").text(resultPrice+"원");
				
			} else {
				$(this).siblings("label").children().attr("src", "<%= request.getContextPath() %>/resources/images/check.png");
			
				disPrice = 0;
				$("#disNum").text("- " + disPrice +"원");
				$("#allResult").text( '${ requestScope.allP }' + "원");
			
			
			}
			
		});
	});			
		

	
	$(function(){
		$("#orderComplte").click(function(){

				sessionStorage.clear();
				location.href='${applicationScope.contextPath}/orderResult.kk?eat=${ requestScope.eat }&menu=${ requestScope.menu }&disPrice='+disPrice;
				
				

				
		
		});
	});
	
	</script>


</body>
</html>



