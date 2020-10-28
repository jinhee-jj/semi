<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고등록</title>
<link rel="stylesheet" type="text/css"
	href="${ applicationScope.contextPath }/resources/css/boardCSS/FaQboard.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/views/common/menubar.jsp" />
	<div class="invenWrap">
		<div class="miniInput">
			<label>등록일</label> <input type="text" name="insertDate" size="10"
				class="noneLine" id="DDay" readonly>
		</div>
				<c:if test="${sessionScope.loginMember.deptCode eq 'HOF01'}">
		<div class="miniInput">
			<form name="branchSelect" method="post" action="">
			
				<label>지점선택</label> 
				<select name="invenInsert" id="invenInsert"
					onchange="change(this.value);">
					<option>선택</option>
					<option value="BNH01">강남점</option>
					<option value="BNH02">역삼점</option>
					<option value="BNH03">서초점</option>
					<option value="BNH04">청담점</option>
					<option value="BNH05">서울역점</option>
				</select>
			</form>
		</div>
				</c:if>

		<br> <br> <br>
		<div class="invenScroll">
			<table class="invenTable" id="invenList">
				<thead>
					<tr class="invenTr">
						<th>원재료 코드</th>
						<th>원재료명</th>
						<th>분류</th>
						<th>수량</th>
						<th>중량</th>
						<th>유통기한</th>
					</tr>
				</thead>
				<tbody id="tdoby">
				</tbody>
			</table>
		</div>
		<br>
		<div class="all" align="left">
			<label>총 목록</label> <input type="text" name="insetNum" size="13"
			value="" class="noneLine" id="allsum" readonly>
		</div>
		
		
		
		
		
		
<!-- 셀렉트 성공이후에 구조 변경하기.  -->
		<div class="bacode" align="right">
			
				<label>바코드</label> 
				<input type="text" id="insertCode"
					name="insertCode" size="24" class="noneLine hUp"
					style="background-color: #C4C4C4;" autofocus="autofocus"> 
				<label>입고 수량</label> 
				<input type="number" id="insertInt" name="insertInt" size="3"
					class="noneLine hUp buttUp"> 
				<input type="button" value="등록" onclick="insetInvenVal();">
		</div>
		
		
		
	</div>

	 <div class="modalPop">
		<h3>제품을 등록하시겠습니까?</h3>
		<p>
			<c:out value="${ barcode }"></c:out>
		</p>
	<div align="center">
		<form action="${ applicationScope.contextPath }/insertInvenLast.in" method="post">
		<br>
		<label>원재료명 : </label><input class="noneLine1" type="text" name="igName" readonly>
		<br>
		<label>수량  : </label><input class="noneLine1" type="text" name="igQ" id="igQ" readonly>
		<br>
		<input type="hidden" name="insertBarcode" readonly>
		<input type="hidden" name="insertIgCode" readonly>
		<input type="hidden" name="insertBranchCode" readonly>
		<input type="hidden" name="insertSupCode" readonly>
		<br>
			<input type="submit" value="확인" class="MBtn" id="submitAdd">
			<input type="reset" value="취소" class="MBtn" id="noSubmit">
			<!-- <button class="MBtn" id="noSubmit">취소</button> -->
		</form>

	</div>
	</div>


	<div class="modalBg"></div> 


	<script>
	$(function(){
		
		var branch = '${sessionScope.loginMember.deptCode}';
		//console.log(branch);
		$.ajax({
			type : "POST",
			url : "${applicationScope.contextPath}/DayListSelect.in",
			data : {
				branch : branch
			},
			success : function(branch) {

				var $tbody = $("#invenList tbody");
				$tbody.html('');

				for ( var i in branch) {
					var $tr = $("<tr>");

					var $codetd = $("<td>").text(branch[i].igretCode);
					var $nametd = $("<td>").text(branch[i].igretName);
					var $classtd = $("<td>").text(branch[i].igretClass);
					var $addtd = $("<td>").text(branch[i].addQuantity);
					var $weighttd = $("<td>").text(branch[i].igretWeight);
					var $datetd = $("<td>").text(branch[i].exDate);

					$tr.append($codetd);
					$tr.append($nametd);
					$tr.append($classtd);
					$tr.append($addtd);
					$tr.append($weighttd);
					$tr.append($datetd);

					$tbody.append($tr);
					
					
				}
					var allsize = branch.length;
					//console.log(allsize);
					$("#allsum").attr('value', allsize);
					

			},
			error : function(err) {
				alert("재고 조회 실패!");
			}
		});
	});
		function change(branch) {

			$.ajax({
				type : "POST",
				url : "${applicationScope.contextPath}/DayListSelect.in",
				data : {
					branch : branch
				},
				success : function(branch) {

					var $tbody = $("#invenList tbody");
					$tbody.html('');

					for ( var i in branch) {
						var $tr = $("<tr>");

						var $codetd = $("<td>").text(branch[i].igretCode);
						var $nametd = $("<td>").text(branch[i].igretName);
						var $classtd = $("<td>").text(branch[i].igretClass);
						var $addtd = $("<td>").text(branch[i].addQuantity);
						var $weighttd = $("<td>").text(branch[i].igretWeight);
						var $datetd = $("<td>").text(branch[i].exDate);

						$tr.append($codetd);
						$tr.append($nametd);
						$tr.append($classtd);
						$tr.append($addtd);
						$tr.append($weighttd);
						$tr.append($datetd);

						$tbody.append($tr);
						
						
					}
						var allsize = branch.length;
						//console.log(allsize);
						$("#allsum").attr('value', allsize);
						

				},
				error : function(err) {
					alert("재고 조회 실패!");
				}
			});
		};
		

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

		
		//글자수 제한
		var limit = 22;
		
		$(document).ready(function(){
			$("#insertCode").keyup(function(){
				var Tlength = $(this).val().length;
				//console.log(Tlength);
				
				if(Tlength > limit){
					$(this).prop('readonly', true);
				}
			});
		});
		
			 $(".MBtn, .close").on('click', function() {
			 $(".modalPop").fadeOut(300);
			 $(".modalBg").fadeOut(300);
			 $("#insertCode").val('');
			 });  
	

			 function insetInvenVal(){
				 var selectBranchInven = $("#invenInsert").val();
				 console.log(selectBranchInven);
				 var insertCode = $("#insertCode").val();
				 console.log(insertCode);
				 var insertInt = $("#insertInt").val();
				 console.log(insertInt);
				 
				 $.ajax({
					 type : "POST",
						url : "${ applicationScope.contextPath }/insertSelect.in",
						data : {
							selectBranchInven : selectBranchInven
							,insertCode : insertCode
							,insertInt : insertInt
						},
						success : function(list){
							 document.get
							 $(".modalPop").fadeIn(300);
							 $(".modalBg").fadeIn(300);
							 
							 var name = list[0].igretName;
							 var number = list[0].addQuantity;
							 
							 $('input[name=igName]').attr('value', name);
							 $('input[name=igQ]').attr('value', number);
							 
							 
							 var insertBarcode = list[0].Barcode;
							 var insertIgCode = list[0].igretCode;
							 var insertBranchCode = list[0].branchCode;
							 var insertSupCode = list[0].sup_code;
							 
							 $('input[name=insertBarcode]').attr('value', insertBarcode);
							 $('input[name=insertIgCode]').attr('value', insertIgCode);
							 $('input[name=insertBranchCode]').attr('value', insertBranchCode);
							 $('input[name=insertSupCode]').attr('value', insertSupCode);
							 
							
						},
						error : function(err) {
							alert("재고 등록 실패!");
						}
					 
				 });
			 };
			 
			 
			 
 	</script>
</body>
</html>