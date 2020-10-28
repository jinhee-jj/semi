<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>본사-지점발주현황</title>
<link href="<%= request.getContextPath() %>/resources/css/purchase/purchaseHistoryListBRforHO.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../../common/menubar.jsp"/>
	<div id="wrapper">
		<h2>본사-지점발주현황</h2>
		<div id="historyListSection" style="overflow:scroll;">
			<div id="searchArea" align="center">
				<table align="left">
				<tr>
				<td>지점선택</td>
				<td>
					<select id="selectBranch" style="height:27px;">
							<option selected>전체지점</option>
						<c:forEach var="b" items="${ requestScope.bList }">
							<option id="branchName"><c:out value="${b.branchName}점"/></option>
						</c:forEach>
					</select>						
				</table>
			</div>
			<div>
			<br><br><br>
			<table align="center" id="listArea" class="table-bottom">
				<thead>
					<tr>
						<th width="20%">발주일자</th>
						<th width="15%">지점코드</th>
						<th width="20%">지점명</th>
						<th width="40%">발주내역</th>
						<th width="20%">상세보기</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="l" items="${ requestScope.list }">
						<tr>
							<td align="center"><c:out value="${ l.purchaseDate }"/></td>
							<td align="center"><c:out value="${ l.branchCode }"/></td>
							<td align="center"><c:out value="${ l.branchName }"/></td>
							<td align="center"><c:out value="${ l.iNameRep } 외  ${ l.cnt } 건  "/></td>
							<td align="center"><button class="detailBtn">상세보기</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
	</div>
	
	<!-- 상세보기 누르면 열리는 모달창 -->
<div id="detailContent" align="center">
<table class="detailTable" class="table-bottom">
     <thead> 
	    <tr>
	        <th width="15%">분류</th>
	        <th width="15%">제품코드</th>
	        <th width="40%">제품명</th>
	        <th width="20%">단위</th>
	        <th width="25%">수량</th>
	    </tr>
     </thead>
     <tbody>
        <tr>
            <td></td>
            <td>TOTAL</td>
            <td></td>
            <td></td>
            <td>3</td>
        </tr>
     </tbody>
    
</table>
<br>
<button id="detailCloseBtn">확인</button>
</div>
<div id="modalBackground"></div>

<script>
	$(function(){
		$(document).on("click", ".detailBtn, #modalBackground, #detailCloseBtn", function(){
			
	        $("#detailContent, #modalBackground").toggle();
	
	    });
	});

    
    $(function(){
    	$(document).on("click", ".detailBtn", function(event){
				//상세보기 누른 행의 날짜 가져오기
				var pDate = $(this).parent().parent().children().eq(0)
						.text();
				/* console.log(pDate); */

				
				var bCode = $(this).parent().parent().children().eq(1).text();
				/* console.log(bCode); */
				var arr = pDate.split(/:| /);
				console.log(arr);
				console.log(arr[0] + ", " + arr[1] + ", " + arr[2]);

				var date = arr[0];
				var hour = arr[1];
				var min = arr[2];
				
				$.ajax({
					url : "selectOneHistoryfromBR.pu",
					type : "post",
					data : {
						date : date,
						hour : hour,
						min : min,
						bCode : bCode
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
						console.log("지점 총 발주내역 상세조회 실패");
					}
				});
    		
    	});
    	
    });

    
    //지점 변경 시
    $("#selectBranch").change(function(){
    	var branch = $("#selectBranch option:selected").val();
    	console.log(branch);
    	
    	var size = branch.length;
   		//console.log(size);
    	
    	//지점이름 + 점 에서 점을 제외시킴
    	var bName = branch.substring(0, size-1);
    	
    	//console.log(bName);
    	
    	$.ajax({
    		url:"selectHistoryWithbName.pu",
    		type:"get",
    		data:{
    			bName : bName
    		},
    		success:function(data){
    			var $listAreaTbody = $("#listArea tbody");
					$listAreaTbody.html('');
					
					for(var index in data) {
						var $tr = $("<tr>");
						var $purchaseDateTd = $("<td align='center'>").text(data[index].purchaseDate);   
						var $branchCodeTd = $("<td align='center'>").text(data[index].branchCode);   					
						var $branchNameTd = $("<td align='center'>").text(data[index].branchName);
						var $iNameRepTd = $("<td align='center'>").text(data[index].iNameRep + ' 외 '+ data[index].cnt + ' 건');
						var $detailTd = $("<td align='center'>").html("<button class='detailBtn'>상세보기</button>");
					
						$tr.append($purchaseDateTd);
						$tr.append($branchCodeTd);
	    				$tr.append($branchNameTd);
	    				$tr.append($iNameRepTd);
	    				$tr.append($detailTd);
	    				$listAreaTbody.append($tr);	

	    				
				 	}
    			
    			
    			console.log("서버 전송 성공");
    		},
    		error:function(err){
    			console.log("지점별 주문내역 로드 실패");
    		}
    		
    	});
    }); 
</script>
</body>
</html>