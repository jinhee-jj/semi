<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>본사-발주등록</title>
<link href="<%= request.getContextPath() %>/resources/css/purchase/purchaseRegisterHO.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../../common/menubar.jsp"/>
		<h2>본사-발주등록</h2>
		<div id="purchaseRegisterSection" style="overflow:scroll;">
			<div id="branchArea" align="center">
				<table align="right">
				<tr>
				<td>일자 : </td>
				<td><input type="date" readonly></td>	
				</tr>
				</table>
			</div>
			<table align="center" id="listArea" class="table-bottom">
				<tr>
					<th width="10%">순번</th>
					<th width="20%">거래처코드</th>
					<th width="20%">거래처명</th>
					<th width="20%">제품내역</th>
					<th width="20%">총 매입가</th>
					<th width="20%">상세보기</th>
					<th width="20%"><input type="checkbox" id="checkAll" onclick="checkAll();"></th>
				</tr>
				<c:forEach var="p" items="${ requestScope.puList }">
				<tr>
					<td align="center"><c:out value="${ p.rownum }"/></td>
					<td align="center"><c:out value="${ p.supCode }" /></td>
					<td align="center"><c:out value="${ p.supName }"/></td>
					<td align="center"><c:out value="${ p.iName } 외  ${ p.cnt - 1 } 건"/></td>
					<td align="center"><fmt:formatNumber type="number" maxFractionDigits="3" value="${p.price}" /><%-- <c:out value="${ p.price }"/> --%></td>
					<td align="center"><button class="detailBtn">상세보기</button></td>
					<td align="center"><input type="checkbox" class="checkSupCode"></td>
				</tr>
				</c:forEach>	
			</table>
			<br><br>
			<div id="totalArea">
				<div id="totalTableArea">
				<table id="totalTable" align="center">
					<tr>
					<td width="30px"></td>
					<td width="240px">  TOTAL</td>
					<td width="400px"></td>
					<td width="200px">총 매입가 : <label id="price"></label> </td>
					<td width="100px"></td>
					</tr>
				</table>
				</div>
				<br>
				<button id="registerBtn" class="orderBtn" onclick="order();">발주하기</button>
			</div>
		</div>

	

<!-- 발주하기 버튼 누르면 뜨는 창 -->

<div id="modal">
	<div id="detailContent" align="center">
		<table class="registerCheckTable">
			<tr>
				<th colspan="2"></th>
			</tr>
			<tr>
				<td>발주일 : </td>
				<td align="left"><label id="dateLb">오늘 날짜</label></td>
			</tr>
			<tr>
				<td>총 매입가 : </td>
				<td align="left"><label id="numLb"></label> 원</td>		
			</tr>
			<tr><td colspan="2">발주가 완료되었습니다.</td></tr>
		</table>
		<button id="detailCloseBtn">확인</button>
	</div>
	<div id="modalBackground"></div>
</div>

<script>
		/* 모달창  */
		$(function(){
			$(".orderBtn, #modalBackground, #detailCloseBtn").click(function(){
					$("#detailContent, #modalBackground").toggle();			
			});		
		
		});
		
	
		/* 일자에 현재 날짜 뜨게 하기 */
		var today;
		var dd;
		var mm;
		var yyyy;
	 	$(document).ready(function(){
	 		today = new Date();
	 		dd = today.getDate();
	 		mm = today.getMonth()+1; 
	 		yyyy = today.getFullYear();

	 		if(dd<10) {
	 		    dd='0'+dd
	 		} 

	 		if(mm<10) {
	 		    mm='0'+mm
	 		} 

	 		today = yyyy+'-'+mm+'-'+dd;
	 		$("input[type=date]").val(today);
	 		
	 	});	 	
	 	/* 상세보기 클릭 시 한 거래처 내의 주문 재료 목록  */
		$(".detailBtn").click(function(){
			console.log($(this).parent().parent().children().eq(1).text());
			var sCode = $(this).parent().parent().children().eq(1).text();
			location.href="${applicationScope.contextPath}/selectOneSup.pu?sCode=" + sCode;
			
		});
		
	 	//금액 콤마 없애기
	 	function noComma(price1){
	 		return (price1+"").replace(/\,/gi, '');
	 	}
	 	
	 	
	 	//아래 총합계 금액 나타낼 변수 선언
		var priceAll;		
		/* th의 체크박스 선택시 전체 선택 및 해제 */
		function checkAll() {
			if($("#checkAll").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);	
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}	
		}
		
		$(".checkSupCode, #checkAll").click(function(){
			var check = $(".checkSupCode:checked");
			var size = check.length;
			console.log(size);
			
			var tr = $(".checkSupCode:checked").parent().parent();
			
			priceAll = Number(0);
			for(var i = 0; i < size; i++) {
				//테이블에 표기된 가격
				price = tr.eq(i).children().eq(4).text();
				console.log(price);
				//콤마 없애주기
				noComma(price)
				//
				priceAll += Number(noComma(price));
			}
			//console.log(priceAll);
			var showPrice = priceAll.toLocaleString();
			$("#price").text(showPrice);
			$("#numLb").text(showPrice);
			$("#dateLb").text(today);
		
		});

		
		/* 발주하기 클릭 시  */
		function order(){

			var checkbox = $(".checkSupCode:checked");
			var size = checkbox.length;
			console.log(size);
			
			//거래처 코드 담아줄 배열
			var arr = new Array();
			
			checkbox.each(function(i) {
				
				var tr = checkbox.parent().parent().eq(i);
			
				var sCode = tr.children().eq(1).text();
				console.log(sCode);
			
				arr.push(sCode);
			});
			
			console.log(arr);
			
	 		jQuery.ajaxSettings.traditional = true;
			
	 		$.ajax({
				url:"insertRegisterHO.pu",
				type:"get",
				data:{
					arr:arr
				},
				success:function(data){
					checkbox.parent().parent().remove();
				
				},
				error:function(err){
					console.log("발주 실패");
				}
				
			});
			 
	 		$("#price").text('');

	
		}
		
</script>
	
	
</body>
</html>