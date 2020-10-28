<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고내역</title>

<link rel="stylesheet" type="text/css"
	href="${ applicationScope.contextPath }/resources/css/boardCSS/FaQboard.css">

</head>
<body>
	<jsp:include page="/views/common/menubar.jsp"/>
	<div class="invenWrap">
		<form>
			<input type="button" value="조회하기" class="TopsearchBtn" onclick="submitList();">
			<div class="miniInput">
				<label>마지막일자</label> <input type="date" name="endDate" id="endDate">
			</div>
			<div class="miniInput">
				<label>시작일자</label> <input type="date" name="startDate" id="startDate">
			</div>
			<c:if test="${sessionScope.loginMember.deptCode eq 'HOF01'}">
			<br><br><br>
			<div class="miniInput">
				<label>지점선택</label> <select name="invenInput" id="selectListBranch">
				
					<option>선택</option>
					<option value="BNH01">강남점</option>
					<option value="BNH02">역삼점</option>
					<option value="BNH03">서초점</option>
					<option value="BNH04">청담점</option>
					<option value="BNH05">서울역점</option>
				</select>
			</div>
			</c:if>
			<div class="miniInput">
			<label>항목선택</label>
			<select id="igList">
			<option></option>
			</select>
			</div>
			<div class="miniInput">
			<label>이력 형태 선택</label>
			<select id="invenType">
			<option value="입고">입고</option>
			<option value="출고">출고</option>
			<option value="제조실수">제조실수</option>
			<option value="폐기">폐기</option>
			<option value="기타">기타</option>
			
			</select>
			</div>
		</form>
		<br><br><br>
		

		<div class="invenScroll">
			<table class="invenTable" id="invenListTable">
				<thead>
					<tr class="invenTr">
						<th>원재료 코드</th>
						<th>원재료명</th>
						<th>분류</th>
						<th>입고수량</th>
						<th>총수량</th>
						<th>단위</th>
						<th>처리일자</th>
						<th>이력분류</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			
			</table>
		</div>

	</div>
	<script>
	$(function(){
		$.ajax({
			url : "${ applicationScope.contextPath }/igSelectList.in",
			type : "get",
			success : function(data){
				
				var $select = $("#igList");
				$select.find("option").remove();
				
				var $option1 = $("<option>");
				$option1.val("all");
				$option1.text("전체")
				$select.append($option1);
				
				for(var index in data){
					var $option = $("<option>");
					$option.val(data[index].igretCode);
					$option.text(data[index].igretName);
					
					$select.append($option);
				}
				
			},
			error : function(err){
				alert("리스트 조회 실패!");
			}
		});
	});
	
	
	function submitList(){
		var igCode = $("#igList option:selected").val();
		var branchCode = $("#selectListBranch option:selected").val();
		var startDay = $("#startDate").val();
		var endDay = $("#endDate").val();
		var invenType = $("#invenType").val();
		
		var branch = '${sessionScope.loginMember.deptCode}';
		
		if(branch != 'HOF01'){
			branchCode = '${sessionScope.loginMember.deptCode}';
		}
		
		
		
		$.ajax({
			url : "${ applicationScope.contextPath }/invenListDate.in" ,
			type : "post",
			data : {igCode:igCode, branchCode:branchCode, startDay:startDay, endDay:endDay, invenType:invenType} ,
			 success : function(data){
				 var $tr = $("<tr>");
				 
				 //console.log(data);
				 var $tbody = $("#invenListTable tbody");
					$tbody.html('');

				 
				 for(var i in data){
					 
				 //code name class addq allq 중량 updateday 입고
				 var $tr = $("<tr>");
				 
				 var $codetd = $("<td>").text(data[i].igretCode);
				 var $nametd = $("<td>").text(data[i].igretName);
				 var $classtd = $("<td>").text(data[i].igretClass);
				 var $addqtd = $("<td>").text(data[i].addQuantity);
				 var $allqtd = $("<td>").text(data[i].allQuantity);
				 var $capatd = $("<td>").text(data[i].igretQuantity + data[i].invenType);
				 //var $unittd = $("<td>").text(data[i].invenType);
				 var $updatetd = $("<td>").text(data[i].insertDate);
				 var $typetd = $("<td>").text(data[i].InvenSelectType);
				 
				 $tr.append($codetd);
				 $tr.append($nametd);
				 $tr.append($classtd);
				 $tr.append($addqtd);
				 $tr.append($allqtd);
				 $tr.append($capatd);
				 $tr.append($updatetd);
				 $tr.append($typetd);
				 
				 $tbody.append($tr);
				 
				 }
			 } ,
			error : function(err){
				alert("입고 내역 조회 실패!");
			}
		});
		
	};
	
	
	
	
	
	
	</script>

</body>
</html>