<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>키오스크 장바구니</title>
<link href="<%= request.getContextPath() %>/resources/css/kiosk/kioskCart.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="wrap">
		<div class="header">
				<table id="headerTable">
					<tr>
						<td id="cartImg" width="7%" onclick="location.href='${applicationScope.contextPath}/select.kk?eat=${ requestScope.eat }&menu=${ requestScope.menu }'"><img src="${ applicationScope.contextPath }/resources/images/cartImg.png"></td>
						<td id="damgi"width="30%"><label style="font-size:1.5rem;">담기</label><br>
													<p>총 <label><c:out value="${ requestScope.allCount }"/></label>개의 메뉴가 담겨있습니다<p></td>
						<td id="trashImg" width="55" align="right"><img src="${ applicationScope.contextPath }/resources/images/trashImg.png" onclick="hide();"></td>
						<td id="checkImg" width="7%" align="right"><img src="${ applicationScope.contextPath }/resources/images/checkImg.png" onclick="location.href='${applicationScope.contextPath}/select.kk?eat=${ requestScope.eat }&menu=${ requestScope.menu }'"></td>
					</tr>
				</table>
		</div>	
		<hr>
		<div id="cartList">
		<br>
			<table id="cartTable">
			
 				<c:forEach var="c" items="${ requestScope.cartList }">
					<tr>
						
						<td width="10%" style="padding: 1rem"><img width="100px" height="100px" src="<c:out value="${ c.image }"/>"></td>
						
						<td width="65%"align="left" id="menuName"><c:out value="${ c.menuName }"/>&nbsp;&nbsp;<c:out value="${ c.count }개"/><br><label id="menuPrice"><c:out value="${ c.menuPrice }"/></label></td>
						
						<td width="10%" align="right">
						<input id="bsNum" type="hidden" value="${ c.basketNum }">				
						<img class="deleteImg" onclick="deleteChk(this)" src="${ applicationScope.contextPath }/resources/images/deleteImg.png">
						<label id="ALALAL" class="TOTOTO" onclick="checkDelete(this)">
							<img class="check" src="${ applicationScope.contextPath }/resources/images/check.png" hidden>
							<img class="pinkCheck" src="${ applicationScope.contextPath }/resources/images/pinkCheck.png" hidden>
							<input  id="menuCheck" name="menuCk" class="checkCheck" type="checkbox" hidden>
						</label>
						</td>
						<!-- <input  id="menuCheck" name="menuCk" class="checkCheck" type="checkbox" ></td> -->
					
					</tr>
				</c:forEach>
			
			</table>
		</div>
		<hr>
		<div id="totalList">
		<br>
			<table id="totalTable">
				<tr>
					<td width="75%" align="left" colspan="2">총 <c:out value="${ requestScope.allCount }"/>개</td>
					<td width="10%" align="right"><label id="totalPrice"><c:out value="${ requestScope.allPrice }"/>원</label></td>
				</tr>
			</table>
		</div>
		<br>
		<button id="orderArea" align="center" onclick="location.href='${ applicationScope.contextPath }/order.kk?eat=${ requestScope.eat }&menu=${ requestScope.menu }'">주문하기</button>
		<button id="chooseAll" onclick="selectAll();" hidden>전체 선택하기</button>
		<button id="delete" onclick="deleteGOGO()" hidden>삭제하기</button>
	</div>
	<script>
		/* 위에 쓰레기통 누르면  완료 버튼으로 바뀜. 전체 해제하기, 삭제하기 버튼 나오고, 메뉴 오른쪽의 x표시가 체크표시로 바뀜 ,*/
		/* 완료버튼 누르면 다시 키오스크 첫화면으로 */
		function hide(){
			if($("#trashImg").children().attr("src") === "${ applicationScope.contextPath }/resources/images/trashImg.png") {
				$("#orderArea").hide();
				$(".deleteImg").hide();
				$("#chooseAll").show();
				$("#delete").show();
				$(".check").show();
				$("#trashImg").children().attr("src", "${ applicationScope.contextPath }/resources/images/complete.png");
			} else {
				$("#orderArea").show();
				$(".deleteImg").show();
				$("#chooseAll").hide();
				$("#delete").hide();
				$(".check").hide();
				
				$("#trashImg").children().attr("src", "${ applicationScope.contextPath }/resources/images/trashImg.png");
			}
		};
		
		/* 전체 선택하기, 전체 해제하기 버튼 클릭시 텍스트 변경 */
		var cnt = 1;
		function selectAll() {
			if(cnt%2==1){
			$("input[type=checkbox]").prop("checked", true);
			$("#chooseAll").text("전체 해제하기");
			}else {
				$("input[type=checkbox]").prop("checked", false);
				$("#chooseAll").text("전체 선택하기");
			}
			cnt++;
		}
		
		function deleteChk(e){
			
			var goqk = $(e).parent().find("#bsNum").val();
			
			location.href='${applicationScope.contextPath}/deleteCart.kk?num=' + goqk +'&eat=${ requestScope.eat}' + '&menu=${ requestScope.menu}';
		}
		
		
 		function checkDelete(e){
 			
 		if($(e).children().is(":checked") === true) {
			
			$(e).children().attr("src", "${ applicationScope.contextPath }/resources/images/pinkCheck.png");
			
			
		} else {
			
			$(e).children().attr("src", "${ applicationScope.contextPath }/resources/images/check.png")
			
			
		}
			
		}
 		
 		function deleteGOGO(){
 			
			if($(".checkCheck").is(":checked") === true){
				
				var goqk = $(".checkCheck:checked").parent().parent().find("#bsNum");
				
				var output = '';
				$.each(goqk, function(index, value){
					if(index == 0) {
						output += value.value;
					} else {
						output += "," + value.value;
					}
					
				});
				
				var eat = '${ requestScope.eat }';
				var menu = '${ requestScope.menu}';
				var num = '0';
				
				$.ajax({
					url: "${applicationScope.contextPath}/deleteCart.kk",
					data: {output: output, eat: eat, menu: menu, num: num},
					type: "post",
					success: function(data) {
						
						
						$(".checkCheck:checked").parent().parent().parent().remove();
						 
						}

				})
				
				
			} else {
				
			}
 			
			
 		}
		
		/* 전체선택하기, 전체 해제하기 선택 시 체크이미지 모두 분홍색 체크 이미지, 삭제하기 버튼 분홍색으로 변경, 해제 */
		$(function(){
			$("#chooseAll").click(function(){
				/* console.log($(this).text()); */
				if($(this).text() === "전체 해제하기") {
					$("input[type=checkbox]").siblings("label").children().attr("src", "${ applicationScope.contextPath }/resources/images/pinkCheck.png");
					$("#delete").css({"background-color":"#F57C9B",
						"color":"white"});
				} else {
					$("input[type=checkbox]").siblings("label").children().attr("src", "${ applicationScope.contextPath }/resources/images/check.png")
					$("#delete").css({"background-color":"#CBC8C8",
						"color":"#BDB1B1"});
				} 
			});
 		}); 
		
		/* 체크박스 갯수(메뉴 수)만큼 선택시 전체 선택하기가 -> 전체 해제하기로 바뀌고 체크박스 선택된 게 있는 경우 삭제하기 버튼 활성화 */
		$(function(){
			$(".checkCheck").change(function(){
				/* console.log($('.checkCheck:checked').length); */
				if($('.checkCheck:checked').length === $(".checkCheck").length) {
					$("#delete").css({"background-color":"#F57C9B",
						"color":"white"});
					$("#chooseAll").text("전체 해제하기");
					cnt++;
				} else if ($('.checkCheck:checked').length >= 1) {
					$("#delete").css({"background-color":"#F57C9B",
						"color":"white"});
				} else {
					$("#delete").css({"background-color":"#CBC8C8",
						"color":"#BDB1B1"});
				}
			});
		});
		
		
	</script>
</body>
</html>