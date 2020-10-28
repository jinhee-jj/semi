<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키오스크 메인</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/css/kiosk/kioskMain.css"
	rel="stylesheet" type="text/css">
<style>
	.whatIs {
		text-align:center;
	}
	.item2In {
		text-align:center;
	}
	.item2In h3 {
		display:inline-block;zoom:1;display:inline;
		line-height: 4em;
	}
	.item1 h2 {
		display:inline-block;zoom:1;display:inline;
		line-height: 2.5em;
	}

</style>
</head>
<body>
	<jsp:include page="./kioskMenuBar.jsp" />
	<div class="MenuMain">
		<!-- 추후에 foreach추가바람 시작-->
		<c:forEach var="n" items="${ requestScope.menuList }">
		<div class="menuList" align="center" onclick="modal(this)">
		
			<div>
				<img src="${ applicationScope.contextPath }/resources/images/menuImage/<c:out value="${ n.menuImage }"/>"class="img">
			</div>
			<div align="center">
				<p class="menuName"><c:out value="${ n.menuName }"></c:out></p>
			</div>
			<div align="center">
				<p class="menuPrice"><c:out value="${ n.menuPrice }"></c:out></p>
			</div>
			
			<input type="hidden" value="${ n.menuCode }"/>
			
		<div style="margin-top:50px">
		</div>	

		</div>
		</c:forEach>
		<!-- 추후에 foreach추가바람 여기까지-->
		<!-- 아래 동일 div는 페이지 확인용 -->



	</div>

	<!-- 계산하기 인클루드에서 일반으로 변경-->
	<div class="cart" align="center" >
	
	<div onclick="carGoIn();">
	<div class="item1">
	
	
	<c:forEach var="f" items="${ requestScope.cart }" varStatus="i">
				<c:if test="${ i.last }">
					<c:set var="YOYO" value="${ i.last }"/>
				</c:if>
	</c:forEach>
	
	
	
	<img alt="" src="${ applicationScope.contextPath }/resources/images/pinkShopping.png" align="left" class="IconCart">
		<h2>총수량&nbsp;</h2><h2 id="allAmount"> <input id="hallAmount" type="hidden" value="${ requestScope.allC }"> <c:out value="${ requestScope.allC }"/></h2><h2>개</h2>
	</div>
	<div class="item2">
		<div class="item2In">
			<h3 class="whatIs" id="item2MenuName"><c:out value="${ requestScope.cart[0].menuName }"/> / </h3><h3 class="whatIs" id="item2MenuAmount"><c:out value="${ requestScope.cart[0].count }"/> / </h3><h3 class="whatIs" id="item2MenuPrice"><c:out value="${ requestScope.cart[0].menuPrice }"/></h3>
		</div>
	</div>
	</div>
	<div class="order" onclick="location.href='${applicationScope.contextPath}/order.kk?eat=${ requestScope.eat }&menu=${ requestScope.menu }'">
		<h1 align="center">주문하기</h1>

	</div>
	</div>




	<!-- 모달창내용 -->
	<div class="optionModal">
		<button class="MBtn">X</button>
		<br> <br> <br> <br>
		<table class="modalTable" align="center">
			<tr>
				<td width="20%"></td>
				<td width="30%">
					<h2 id="imAmYo" align="right">
						<c:out value="아메리카노"></c:out>
					</h2>
				</td>
				<td>
				<!-- class="textPink" -->
					<h2 id="YoGoodYe" class="textPink" align="left">
						<c:out value="1.500원"></c:out>
					</h2>
				</td>
				<td width="20%"></td>
			<tr>
				<td colspan="4"><h4 id="YeeYoYee"></h4>
				</td>
			</tr>
			<tr>
				<th align="right"><img id="YoYesMan"
					src="/st4/resources/images/menuImage/MENU_CC004.PNG" class="img"></th>
				<th>
					<h3 class="textRed" id="HotOrIce">
						<c:out value="HOT"></c:out>
					</h3>
					<h5 class="textRed" id="drink">
						<c:out value="따뜻한 음료"></c:out>
					</h5>
				</th>
				<th width="35%">
					<button type="button" class="numBtn" id="numMinus">-</button>
						<input type="text" name="menuCount" class="numText" id="count" value="&nbsp;1" readonly>
					<button type="button" class="numBtn" id="numPlus">+</button>
				</th>
			</tr>
		</table>
		
		<br>
		<table class="dropButton" align="center">
			<tr class="dropBtn">
				<th align="left">
					<!-- <div class="optionSelect"> --> <!-- <div class="dropBtn"> -->
					<c:out value="아메리카노"></c:out> <c:out value="첫 번째"></c:out> <c:out
						value="메뉴의 옵션"></c:out>
				</th>
				<th align="right">
					<p>▼&nbsp</p>
				</th>
			</tr>
			<tr class="optionDetail">
				<td><img
					src="${ applicationScope.contextPath }/resources/images/cup.png">
					<span>샷양 </span></td>
				<td>
					<div align="right">
						<select>
							<option>기본</option>
							<option>샷추가 +300원</option>
							<option>연한 샷</option>
						</select>
					</div>
				</td>
			</tr>
			<tr class="optionDetail">
				<td><img
					src="${ applicationScope.contextPath }/resources/images/water.png">
					<span>물양</span>
				</td>
				<td>
					<div align="right">
							<select>
								<option>기본</option>
								<option>물 적게</option>
								<option>물 많게</option>
							</select>
					</div>
				</td>
			</tr>	
			<tr class="optionDetail">
				<td colspan="1"><span>&nbsp;덜뜨겁게</span></td>
				<td align="right"><input type="checkbox" value="lowHot" style="width:2em; height:2em;">
				</td>
			</tr>
			<tr class="optionDetail">
				<td colspan="1"><span>&nbsp;헤이즐넛 시럽추가</span> <br> <span class="info">&nbsp;&nbsp;헤이즐넛
						시럽추가(는) <span class="textPink">500원</span>이 추가됩니다.
				</span></td>
				<td align="right"><input type="checkbox" value="hazelnut" style="width:2em; height:2em;" >
				</td>
			</tr>
		</table>
  		<div class="ok" align="center">
			<h2 align="center" style="line-height: 2.5em;">확인</h2>
		</div>
		
	</div>

	<div class="modalBack"></div>

	<!-- 모달창 끝 -->
	<!-- append 요소 -->


	<script>
		function modal(e) {
			 menuName = $(e).children().eq(1).text();
			 menuPrice = $(e).children().eq(2).text();
			 menuImage = $(e).children().children().attr('src');
			 menuCode = $(e).children().eq(3).val();
			var slice = menuName.slice(-8,-5);
			var slice2 = menuName.slice(-7, -4);
			
			if(slice == "HOT"){
				$("#HotOrIce").text(slice);
				$("#drink").text("따듯한 음료");
			} else if(slice == "ICE"){
				$("#HotOrIce").text(slice);
				$("#drink").text("차가운 음료");
			} else if(slice2 == "스무디") {
				$("#HotOrIce").text("ICE");
				$("#drink").text("차가운 음료");
			} else {
				$("#HotOrIce").text("사이드");
				$("#drink").text("간식");
			}
			
			var slice3 = menuName.slice(5,7);
			
			
			if(slice3 == "아메"){
				$("#YeeYoYee").text("4가지 원두(브라질, 탄자니아, 만델링)을 블랜딩하여 고소한 견과류의 풍미를 살린 아메리칸 스타일의 커피");
			} else if(slice3 == "카페"){
				$("#YeeYoYee").text("고소한 에스프레소에 우유를 더해 부드러운 맛을 즐길 수 있는 커피");
			} else if(slice3 == "바닐"){
				$("#YeeYoYee").text("라떼에 바닐라의 감미로운 향을 더해 더욱 풍부한 맛을 내는 커피");
			} else if(slice3 == "모카"){
				$("#YeeYoYee").text("초콜릿 파우더와 우유를 더한 에스프레소에 휘핑크림을 올린 달콤한 커피");
			} else if(slice3 == "딸기"){
				$("#YeeYoYee").text("새콤달콤한 딸기 맛과 향에 우유가 가미된 부드러운 딸기스무디");
			} else if(slice3 == "초코"){
				$("#YeeYoYee").text("진한 초콜릿 맛과 향에 우유가 가미된 부드러운 초코스무디");
			} else if(slice3 == "얼그"){
				$("#YeeYoYee").text("고급스러운 베르가못 향이 특징인 홍차");
			} else if(slice3 == "그린"){
				$("#YeeYoYee").text("고급스러운 녹차의 향과 맛을 가진 그린티");
			} else if(slice3 == "마카"){
				$("#YeeYoYee").text("상큼한 산딸기를 사용한 크림과 이탈리안머랭으로 만든 쫀득한 산딸기 마카롱");
			} else if(slice3 == "쿠키"){
				$("#YeeYoYee").text("초콜릿 청크가 박힌 정통 아메리칸 쿠키.");
			} else if(slice3 == "머핀"){
				$("#YeeYoYee").text("깊은 초콜릿 풍미를 가진 맛있는 머핀");
			}
			
			$("#YoYesMan").attr('src', menuImage);
			$("#imAmYo").text(menuName);
			$("#YoGoodYe").text(menuPrice +"원");
			
			
			$(".optionModal").fadeIn(300);
			$(".modalBack").fadeIn(300);
		}
		$(".MBtn, .close").on('click', function() {
			$(".optionModal").fadeOut(300);
			$(".modalBack").fadeOut(300);
		});
		$(".ok, .close").on('click', function() {
			$(".optionModal").fadeOut(300);
			$(".modalBack").fadeOut(300);
		});

		$('.optionDetail').hide();

		var count = 1;
		$(".dropBtn").click(function() {
			$(".optionDetail").slideToggle("100");
		});
		$(".ok").click(function() {
			$(".item1").clone().before(".order");
			$(".item2").clone().before(".item1");
			$('.item1').css("display","block");
			$('.item2').css("display","block");
			
			var result = $("#allAmount").text();
			
			allCount = Number(result) + count;
			
 			$("#item2MenuName").text(menuName + "/");
			$("#item2MenuAmount").text(count + " /");
			$("#item2MenuPrice").text(menuPrice);
 			$("#allAmount").text(allCount);
			
 			console.log(result);
 			
			eat = '${requestScope.eat}';
			menu = '${requestScope.menu}';
			
			$.ajax({
				url: "${applicationScope.contextPath}/cartInsert.kk",
				data: {eat: eat, menu:menu, menuName: menuName, count: count, menuPrice: menuPrice, allCount: allCount, menuImage: menuImage, menuCode: menuCode
						},
				type: "post",
				success: function(data) {
					
					
	 				
					
					}

			})
			
			count = 1;
			$("#count").val(" "+ count);
			
		});
		
		
		$("#numMinus").on('click',function(){
			if(count != 1) {
				
				count -= 1;
				
				$("#count").val(" "+ count);
			}
			
		});
		$("#numPlus").on('click',function(){
			count += 1;
			
			$("#count").val(" "+ count);
		});
		
		function carGoIn() {
			sessionStorage.setItem("auto" , "1");
			
			var eat = '${ requestScope.eat }';
			var menu = '${ requestScope.menu }';
			
			 location.href='${applicationScope.contextPath}/cartList.kk?eat=' + eat + '&menu=' + menu;
		}
  		
		$(function(){
			
			if(1 == sessionStorage.getItem("auto")) {
				
				$(".item1").clone().before(".order");
				$(".item2").clone().before(".item1");
				$('.item1').css("display","block");
				$('.item2').css("display","block");
				
				
			}
			
		});
		
		
		
/* 		<c:out
		value="4가지 원두(브라질, 탄자니아, 만델링)을 블랜딩하여 고소한 견과류의풍미를 살린 아메리칸 스타일의 커피"></c:out> */
	</script>
</body>
</html>