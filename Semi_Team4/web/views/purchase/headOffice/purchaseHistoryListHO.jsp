<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>본사-발주내역</title>
<link href="<%= request.getContextPath() %>/resources/css/purchase/purchaseHistoryLIstHO.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../../common/menubar.jsp"/>
		<h2>본사-거래처발주내역</h2>
		<div id="historyListSection" style="overflow:scroll;">
			<br>
			<form action="${ applicationScope.contextPath }/selectHOpuList.pu" method="get">
			<div id="searchArea" align="center">
				<table align="left">
				<tr>
					<td>
						<select id="searchCondition" name="searchCondition" style="height:27px;">
							<option name="searchCondition">거래처명</option>
							<option name="searchCondition">거래처코드</option>
						</select>
					</td>
					<td><input type="search" style="height:27px;" id="searchInput" name="searchValue" placeholder="거래처 검색" size=25></td>
					<td><input type="date" id="startDay" name="startDay"></td><td>~</td><td><input type="date" id="endDay" name="endDay"></td>
					<td><button id="searchBtn">검색</button></td>	
					<td><button id="allListBtn">전체리스트 보기</button></td>
				</tr>
				</table>
			</div>
					<table align="right">
					</table>
			</form>
			<br>
			
			<table align="center" id="listArea" class="table-bottom">
				<thead>
					<tr>
						<th width="10%">순번</th>
						<th width="25%">발주일자</th>
						<th width="15%">거래처코드</th>
						<th width="15%">거래처명</th>
						<th width="20%">상세보기</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="l" items="${ requestScope.list }">
					<tr>
						<td align="center"><c:out value="${ l.rownum }"/></td>
						<td align="center" hidden class="pCode"><input type="hidden" value="${ l.hoPurchaseCode }"></td>					
						<td align="center"><c:out value="${ l.hoPurchaseDate }"/></td>
						<td align="center"><c:out value="${ l.supCode }"/></td>
						<td align="center"><c:out value="${ l.supName }"/></td>
						<td align="center"><button class="detailBtn">상세보기</button></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

<div id="detailContent" align="center">
<table class="detailTable" class="table-bottom">
	<thead>
	    <tr>
	        <th width="20%">지점코드</th>
	        <th width="30%">지점명</th>
	        <th width="30%">재료명</th>
	        <th width="20%">수량</th>
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
	//모달창
    $(function(){
    	$(document).on("click", ".detailBtn, #modalBackground, #detailCloseBtn", function(){
    		
            $("#detailContent, #modalBackground").toggle();
    
        });
    });
    
    
	
	//상세보기 클릭시
	$(function(){
		$(document).on("click", ".detailBtn", function(event){
			//클릭한 상세보기 행의 발주번호
	    	var pCode = $(this).parent().parent().children().find("input[type=hidden]").val();
	    	console.log("발주번호 확인 " + $(this).parent().parent().children().find("input[type=hidden]").val());
	    	
	    	$.ajax({
	    		url:"selectHOpuListDetail.pu",
	    		type:"get",
	    		data:{
	    			pCode:pCode
	    		},
	    		success:function(data){
	    			console.log("성공");
	    			
	    			var $detailTableTbody = $(".detailTable tbody");
	    			$detailTableTbody.html('');
	    			
	    			for(var index in data) {
	    				var $tr = $("<tr>");
	    				var $bCodeTd = $("<td>").text(data[index].branchCode);
	    				var $bNameTd = $("<td>").text(data[index].branchName);
	    				var $iNameTd = $("<td class='iName'>").text(data[index].ingredientName);
	    				var $quanTd = $("<td>").text(data[index].purchseQuantity);
	    				
	    				$tr.append($bCodeTd);
	    				$tr.append($bNameTd);
	    				$tr.append($iNameTd);
	    				$tr.append($quanTd);
	    				$detailTableTbody.append($tr);				
	    				if($(".iName:empty")) {
	    					$(".iName:empty").parent().css('background-color','rgba(170,207,208,0.5)');
	    				} 
	    				
	    			}
	    		},
	    		error:function(err){
	    			console.log("본사 발주내역 상세조회 실패");
	    		}
	    	
	    	});
	    	
		});
	});

    
   	//검색 버튼 클릭시
   	$("#searchBtn").click(function(){
   		//검색창에 적혀질 글
   		var searchValue = $("#searchInput").val();
   		//검색 조건
   		var searchCondition = $("#searchCondition option:selected").val();
   		
   		//console.log(searchValue);
   		//console.log(searchCondition);
   		 		
   			$.ajax({
   				url: "searchSupplier.pu",
   				type: "get",
   				data: {
   					searchCondition : searchCondition, searchValue : searchValue
   				},
   				success: function(data) {
   					
   					var $listAreaTbody = $("#listArea tbody");
   					$listAreaTbody.html('');
   					
   					for(var index in data) {
   						var $tr = $("<tr>");
   						var $rowNumTd = $("<td align='center'>").text(data[index].rownum);   
   						var pCode = data[index].hoPurchaseCode;
   						var $pCodeTd = $("<td align='center'  class='pCode' hidden >").html("<input type='hidden' value='" + data[index].hoPurchaseCode + "'>")
   						//console.log(data[index].hoPurchaseCode);
   						
   						var $dateTd = $("<td align='center'>").text(data[index].hoPurchaseDate);
   						var $supCodeTd = $("<td align='center'>").text(data[index].supCode);
   						var $supNameTd = $("<td align='center'>").text(data[index].supName);
   						var $detailTd = $("<td align='center'>").html("<button class='detailBtn'>상세보기</button>");
   					
   						$tr.append($rowNumTd);
   						$tr.append($pCodeTd);
   	    				$tr.append($dateTd);
   	    				$tr.append($supCodeTd);
   	    				$tr.append($supNameTd);
   	    				$tr.append($detailTd);
   	    				$listAreaTbody.append($tr);	
   					   					
   					}
   				},
   				error: function(err) {
   					console.log("거래처명으로 검색 실패");
   				} 				
   				
   			});
   			
   		 //전체리스트로 보기 눌렀을 때
   		  $("#allListBtn").click(function(){
   		  
   			 $.ajax({
   				 url: " selectHOpuListAjax.pu",
   				 type: "get",
   				 success: function(data){
   					var $listAreaTbody = $("#listArea tbody");
   					$listAreaTbody.html('');
   					
   					for(var index in data) {
   						var $tr = $("<tr>");
   						var $rowNumTd = $("<td align='center'>").text(data[index].rownum);   
   						var pCode = data[index].hoPurchaseCode;
   						var $pCodeTd = $("<td align='center'  class='pCode' hidden >").html("<input type='hidden' value='" + data[index].hoPurchaseCode + "'>")
   						//console.log(data[index].hoPurchaseCode);
   						
   						var $dateTd = $("<td align='center'>").text(data[index].hoPurchaseDate);
   						var $supCodeTd = $("<td align='center'>").text(data[index].supCode);
   						var $supNameTd = $("<td align='center'>").text(data[index].supName);
   						var $detailTd = $("<td align='center'>").html("<button class='detailBtn'>상세보기</button>");
   					
   						$tr.append($rowNumTd);
   						$tr.append($pCodeTd);
   	    				$tr.append($dateTd);
   	    				$tr.append($supCodeTd);
   	    				$tr.append($supNameTd);
   	    				$tr.append($detailTd);
   	    				$listAreaTbody.append($tr);	
   					
   				 	}
   				 },
   				 error: function(err){
   					 console.log(err);
   				 }
   				 
   				 
   			 });
   			  
   		  });		
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