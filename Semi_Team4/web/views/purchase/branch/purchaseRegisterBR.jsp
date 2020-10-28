<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.cool.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%Member loginMember = (Member) session.getAttribute("loginMember");%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>지점-발주등록</title>
<link
	href="<%=request.getContextPath()%>/resources/css/purchase/purchaseRegisterBR.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../../common/menubar.jsp" />
	<div class="branchPurchaseWrap">

		<div class="date" align="right">
			<table>
				<tr>
					<td><label name="todayDate">일자 : </label></td>
					<td><input type="date" id="todayDate" readonly></td>
				</tr>
			</table>
		</div>
		<div id="ingrediantArea">
			<br>
			<div id="ingrediantList-back">
				<div id="searchArea" >
					<table id="searchAreaTable">
						<tr>
							<td><select id="searchCondition" style="height:27px;">
									<option name="searchCondition" id="findName">재료명</option>
									<option name="searchCondition" id="findCode">재료코드</option>
							</select></td>
							<td><input type="search" id="searchValue"
								placeholder="원재료 검색" style="height:27px;"></td>
							<td><button id="searchBtn">검색</button></td>
						</tr>
					</table>
							<button id="allListBtn">전체 리스트 보기</button>
				
				</div>
				<div id="ingrediantTableArea" >
					<br>
					<table align="center" id="ingrediantListTable" style="overflow:scroll;" >
						<thead>
							<tr>
								<th width="15%">분류</th>
								<th width="25%">원재료코드</th>
								<th width="25%">재료명</th>
								<th width="10%">수량</th>
								<th width="15%">단위</th>
								<th width="15%">매장재고</th>
								<th>수량</th>
								<th width="15%"><input type="checkbox" id="checkAll"
									onclick="checkAll();"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" items="${requestScope.list}">
								<tr>
									<td><c:out value="${i.iClassName }" /></td>
									<td><c:out value="${i.iCode }" /></td>
									<td><c:out value="${i.iName }" /></td>
									<td><c:out value="${i.iCapacity }" /></td>
									<td><c:out value="${i.iUnit }" /></td>
									<td><c:out value="${i.branchInven }" /></td>
									<td><input type="number" class="iNumBox" value="1" min="1"
										style="width: 50px;"></td>
									<td><input type="checkbox" class="checkIngredient"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<button id="cartBtn" style="height:40px";>
					장바구니로<br>이동
				</button>
			</div>
		</div>
		<div id="cartArea" align="center">
			<br>
			<div id="cartList-back">
				<div id="cartText">
					<table align="left">
						<tr>
							<td><label style="height: 30px;"><strong>장바구니</strong></label></td>
						</tr>
					</table>
				</div>
				<br>
				<table id="cartTable" style="overflow:scroll;">
					<thead>
						<tr>
							<th width="20%">원재료코드</th>
							<th width="25%">원재료명</th>
							<th width="20%">용량</th>
							<th width="15%">단위</th>
							<th>수량</th>
							<th width="20%">삭제</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
		<br style="clear: both">
		<div id="totalArea">
			<div id="totalTableArea" align="center">
				<table id="totalTable">
					<tr>
						<td width="40%">TOTAL</td>
						<td width="20%" align="right">품목 수 : <label id="kindNum"></label>
						</td>
						<td width="15%"></td>
						<td width="10%" id="totalNum" align="right">총 수량 : <label
							id="totalNum1"></label>
						</td>
						<td width="15%"></td>
					</tr>
				</table>
			</div>
			<button id="registerBtn" class="orderBtn" onclick="order();">발주하기</button>
		</div>
	</div>


	<!-- 발주하기 버튼 누르면 뜨는 창 -->
	<div id="detailContent" align="center">
		<table class="registerCheckTable">
			<tr>
				<th colspan="2"></th>
			</tr>
			<tr>
				<td>발주일 :</td>
				<td align="left"><label id="dateLb">오늘 날짜</label></td>
			</tr>
			<tr>
				<td>총 수량 :</td>
				<td align="left"><label id="numLb"></label> 개</td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan="2">발주가 완료되었습니다.</td>
			</tr>
		</table>
		<button id="detailCloseBtn">확인</button>
	</div>
	<div id="modalBackground"></div>

	<script>
		/* 모달창 스크립트 */
		$(function(){
			$(".orderBtn, #modalBackground, #detailCloseBtn").click(function(){
				$("#detailContent, #modalBackground").toggle();
			});		
		});
		
		/* th의 체크박스 선택시 전체 선택 및 해제 */
		function checkAll() {
			if($("#checkAll").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);	
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}	
		}
		
		/*검색버튼 클릭 시 검색*/
	 	$("#searchBtn").click(function(){
			var searchValue = $("#searchValue").val();
			var searchCondition = $("#searchCondition option:selected").val();
			 
			console.log(searchValue);
			console.log(searchCondition);
			
			/*검색 옵션이 재료명일 때 */
			if(searchCondition === "재료명") {
				
			$.ajax({
				url: "selectWithiName.pu",
				type: "get",
				data: {searchValue: searchValue},
				success: function(data) {

					var $ingrediantListTableBody = $("#ingrediantListTable tbody");
					
					$ingrediantListTableBody.html('');
					
 					for(var index in data){
						var $tr = $("<tr>");
						var $classNameTd = $("<td>").text(data[index].iClassName);
						var $codeTd = $("<td>").text(data[index].iCode);
						var $nameTd = $("<td>").text(data[index].iName);
						var $capacityTd = $("<td>").text(data[index].iCapacity);
						var $unitTd = $("<td>").text(data[index].iUnit);
						var $inven = $("<td>").text(data[index].branchInven);
						var $numTd = $("<td>")
                        .html(
                              "<input type='number' min='1' value='1' style='width:50px;'>");
			 			var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkIngredient'>");
						
						
						console.log($("<td>").text(data[index].iClassName));
						console.log(data[index].iClassName);
						console.log($classNameTd);
						
						$tr.append($classNameTd);
						$tr.append($codeTd);
						$tr.append($nameTd);
						$tr.append($capacityTd);
						$tr.append($unitTd);
						$tr.append($inven);
						$tr.append($numTd);
						$tr.append($checkBoxTd);
						
						$ingrediantListTableBody.append($tr);
 					}
					
				},
				error: function(err) {
					console.log("실패");
				}
				
			});
			/*검색 옵션이 재료코드일 때 */
			}else {
				
				$.ajax({
					url: "selectWithiCode.pu",
					type: "get",
					data: {searchValue: searchValue},
					success: function(data) {

						
						var $ingrediantListTableBody = $("#ingrediantListTable tbody");
						
						$ingrediantListTableBody.html('');
						
	 					for(var index in data){
							var $tr = $("<tr>");
							var $classNameTd = $("<td>").text(data[index].iClassName);
							var $codeTd = $("<td>").text(data[index].iCode);
							var $nameTd = $("<td>").text(data[index].iName);
							var $capacityTd = $("<td>").text(data[index].iCapacity);
							var $unitTd = $("<td>").text(data[index].iUnit);
							var $inven = $("<td>").text(data[index].branchInven);
							var $numTd = $("<td>")
                            .html(
                                  "<input type='number' min='1' value='1' style='width:50px;'>");
				 			var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkIngredient'>");
							
							
							console.log($("<td>").text(data[index].iClassName));
							console.log(data[index].iClassName);
							console.log($classNameTd);
							
							$tr.append($classNameTd);
							$tr.append($codeTd);
							$tr.append($nameTd);
							$tr.append($capacityTd);
							$tr.append($unitTd);
							$tr.append($inven);
							$tr.append($numTd);
							$tr.append($checkBoxTd);
							
							$ingrediantListTableBody.append($tr);
	 					}
						
					},
					error: function(err) {
						console.log("실패");
					}
					
				});
			}
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

		
		/* 재료목록에서 체크 후  장바구니로 이동 누르면 장바구니 목록에 추가*/
		var kindNum = 0;/*품목 수 넣을 변수 선언 및 초기화*/
		var totalNum1 = 0; /*수량 수 넣을 변수 선언 및 초기화*/
		var $cartTable = $("#cartTable tbody");
		
		$("#cartBtn").click(function() {
			var checkbox = $(".checkIngredient:checked");
			/*체크된 항목 수*/
			var size = checkbox.length;
			/* console.log("size 확인: " + size); */
			
			if(size === 0) {
				alert("품목을 하나 이상 선택하세요");
			}		
			var tdArr = new Array();


				checkbox.each(function(i) {
					var tr = checkbox.parent().parent().eq(i);
					var td = tr.children();
	
					var code = td.eq(1).text();
					var name = td.eq(2).text();
					var capacity = td.eq(3).text();
					var unit = td.eq(4).text();
					var branchInven = td.eq(5).text();
					var num = td.eq(6).children().val();
	
/* 					console.log("code: " + code);
					console.log("name: " + name);
					console.log("capacity: " + capacity);
					console.log("unit: " + unit);
					console.log("branchInven: " + branchInven);
					console.log("num: " + num); */
					
					
					var checkAmount = $cartTable.children().length;
					
					//console.log(checkAmount);					
					//장바구니에 아무 품목이 없을 때만 조건없이 들어감
					if(checkAmount === 0){
	
							tdArr.push(code);
							tdArr.push(name);
							tdArr.push(capacity);
							tdArr.push(unit);
							tdArr.push(num);
					
					//이미 장바구니에 있는 경우 중복된 상품은 넣지 않음		
					} else {
						
						var arr = new Array();
						
						//이미 장바구니에 들어가있는 품목의 코드를 배열에 담음
						for(var j = 0; j < checkAmount; j++) {

						    var checkiCode = $cartTable.children().eq(j).children().eq(0).text()
						   // console.log(checkiCode);
						    arr.push(checkiCode);
						}
						
						   //넣으려고 하는 품목의 코드가 배열에 있으면 alert
							if(arr.includes(code)) {
								alert('장바구니에 이미 있는 상품은 제외되었습니다.')
								//품목 수에 반영 x
								kindNum -= 1;
								 $("#kindNum").text(kindNum);
							} else {
								tdArr.push(code);
								tdArr.push(name);
								tdArr.push(capacity);
								tdArr.push(unit);
								tdArr.push(num);
							}
					
					}

				});
				
				jQuery.ajaxSettings.traditional = true;

				$.ajax({
					url : "addCart.pu",
					type : "get",
					data : {
						tdArr : tdArr
					},
					success : function(data) {
					/* 	console.log(data); */
						
							var $button = $("<button class='xBtn'>").text("x");
							
							for(var index in data) {
								var $tr = $("<tr>");
								var $td = $("<td>");
								var $codeTd = $("<td>").text(data[index].iCode);
								var $nameTd = $("<td>").text(data[index].iName);
								var $capacityTd = $("<td>").text(data[index].iCapacity);
								var $unitTd = $("<td>").text(data[index].iUnit);
		                        var $numTd = $("<td>")
		                                 .html(
		                                       "<input type='text' min='1' value='"
		                                       + data[index].iNum + "' style='width:25px;' class='addNum' readonly><button class='plusBtn'>+</button><button class='minusBtn'>-</button>");
                     
		             /*총 수량에 각 품목 수량 더해주기*/
		                          totalNum1 += data[index].iNum;
		                        var $buttonTd = $("<td>").append(
		                                             "<button id='xBtn' class='xBtn'>x</button>"); 

		                       	   $tr.append($codeTd);
		                           $tr.append($nameTd);
		                           $tr.append($capacityTd);
		                           $tr.append($unitTd);
		                           $tr.append($numTd);
		                           $tr.append($buttonTd);
		                           $cartTable.append($tr);
		                        
							}
						/*console.log("totalNum1 확인 : " + totalNum1); */
						
						/*총 수량 칸에 값 넣어주기*/
		                $("#totalNum1").text(totalNum1);
					},
					error : function(err) {
						console.log("에러");
					}
				});				
				 /*체크박스 초기화*/
                $("input[type=checkbox]").prop("checked", false);
                $(".iNumBox").val(1);
				 
                /* 
                console.log($cartTable.children().children().length); */
                /*품목 수 누적 추가*/
                kindNum += size;
                $("#kindNum").text(kindNum);
				 
		});

		

	    /*장바구니에서 x버튼 클릭시 행 삭제(했음) , 수량 차감*/
		$(function(){
			$(document).on("click", ".addNum", function(event){
				$(this).val();
				//console.log($(this).val());				
			});
			
			$(document).on("click", ".xBtn", function(event){
				var deleteNum = $(this).parent().prev().children().val();
				
				/* console.log($(this).parent().prev().children().val()); */
				$(this).parent().parent().remove();
						
				 kindNum -= 1;
                $("#kindNum").text(kindNum);
				 totalNum1 -= deleteNum;
				$("#totalNum1").text(totalNum1);
				 
			});
		});
		
	    // +, - 버튼 클릭되면 +1, -1 되도록
		var num;
		$(function(){
			$(document).on("click", ".plusBtn", function(event){
			 num = $(this).parent().children("input[type=text]").val();
			 
		     $(this).parent().children("input[type=text]").val(Number(num)+1);
			 console.log(num);
			 
			 totalNum1 += 1;
			 $("#totalNum1").text(totalNum1);
				
			});
			
			$(document).on("click", ".minusBtn", function(event){
				num = $(this).parent().children("input[type=text]").val();
				
				//1 이상일 때만 -버튼 클릭 가능
				if(Number(num) === 1){
					$(this).parent().children("input[type=text]").prop("disablaed", true);
					$("#kindNum").text(kindNum);
				} else {
					$(this).parent().children("input[type=text]").prop("disablaed", false);
					$(this).parent().children("input[type=text]").val(Number(num)-1);
					totalNum1 -= 1;
					$("#totalNum1").text(totalNum1);
				
				}
			
				
			});
		});
   
	  //발주하기 버튼 클릭시	    
		function order() {
			console.log("주문");
			$("#dateLb").text(today);
			$("#numLb").text(totalNum1);
			kindNum = 0;/*품목 수 넣을 변수 선언 및 초기화*/
			totalNum1 = 0; /*수량 수 넣을 변수 선언 및 초기화*/
			
			
			//주문할 정보 담긴 배열
			var cartArr = new Array();
			//재료코드만 남은 배열(거래처 코드 찾는데 쓰임)
			var ingreArr = new Array();
			
			var $cartTable = $("#cartTable tbody");
				$cartTable.children().each(function(i){
					var tr = $cartTable.children().eq(i);
					var td = tr.children();
					
					var code = td.eq(0).text();
					var name = td.eq(1).text();
					var unit = td.eq(3).text();
					var num = td.eq(4).children().val();
					var branchCode = "${loginMember.deptCode}";
					console.log("code: " + code);
					console.log("name: " + name);
					console.log("num: " + num);
					console.log("branchCode : " + branchCode);
					
					cartArr.push(code);
					cartArr.push(name);
					cartArr.push(unit);
					cartArr.push(num);
					cartArr.push(branchCode);
					
					ingreArr.push(code);
				});
		/* 	var size = $cartTableTr.length;
			console.log("장바구니리스트 size 확인 : " + size);
			 */
				jQuery.ajaxSettings.traditional = true;


				$.ajax({
					url : "insertList.pu",
					type : "post",
					data : {
						cartArr : cartArr, ingreArr : ingreArr
					},
					success : function(data) {
							console.log("서버 전송 성공")
					},
					error : function(err) {
						console.log("에러");
					}
				});	

				/*장바구니창 초기화*/
				$("#cartTable tbody").children().remove();
				 $("#kindNum").html('');
				 $("#totalNum1").html('');
		
			}
  
	  //전체리스트로 보기 눌렀을 때
	  $("#allListBtn").click(function(){
	  
		 $.ajax({
			 url: " selectListAjax.pu",
			 type: "get",
			 success: function(data){
				 var $ingrediantListTableBody = $("#ingrediantListTable tbody");
					
					$ingrediantListTableBody.html('');
					
					for(var index in data){
						var $tr = $("<tr>");
						var $classNameTd = $("<td>").text(data[index].iClassName);
						var $codeTd = $("<td>").text(data[index].iCode);
						var $nameTd = $("<td>").text(data[index].iName);
						var $capacityTd = $("<td>").text(data[index].iCapacity);
						var $unitTd = $("<td>").text(data[index].iUnit);
						var $inven = $("<td>").text(data[index].branchInven);
						var $numTd = $("<td>")
                     .html(
                           "<input type='number' min='1' value='1' style='width:50px;'>");
			 			var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkIngredient'>");
						
						
						console.log($("<td>").text(data[index].iClassName));
						console.log(data[index].iClassName);
						console.log($classNameTd);
						
						$tr.append($classNameTd);
						$tr.append($codeTd);
						$tr.append($nameTd);
						$tr.append($capacityTd);
						$tr.append($unitTd);
						$tr.append($inven);
						$tr.append($numTd);
						$tr.append($checkBoxTd);
						
						$ingrediantListTableBody.append($tr);
			 	}
			 },
			 error: function(err){
				 console.log(err);
			 }
			 
			 
		 });
		  
	  });
		
	

	</script>


</body>
</html>