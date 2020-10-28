<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.cool.purchase.model.vo.SupplierOrder "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <% 
 ArrayList<SupplierOrder> list = (ArrayList<SupplierOrder>)request.getAttribute("list");
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>본사-발주등록</title>
<link href="<%= request.getContextPath() %>/resources/css/purchase/purchaseHistoryOneSup.css" rel="stylesheet" type="text/css">

</head>
<body>
	<jsp:include page="../../common/menubar.jsp"/>
		<h2>본사-발주등록</h2>
		<div id="purchaseRegisterSection" style="overflow:scroll;">
			<div id="branchArea" align="center">
				<table align="left">
				<tr>
				<td>거래처명 : </td>
				<td><input type="text" class="sName" value="${ requestScope.list.get(0).supName }" style="width:65px">
					<input type="text" class="sCode" value="${ requestScope.list.get(0).supCode }" style="width:65px" hidden></td>
				</tr>
				</table>
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
					<th width="15%">제품코드</th>
					<th width="15%">제품명</th>
					<th width="10%">수량</th>
					<th width="10%">용량</th>
					<th width="10%">단위</th>
					<th width="15%">매입단가</th>
					<th width="15%">합계</th>
					<th width="20%">지점별주문량</th>
				</tr>
				<!-- 스크롤 보기용 행 추가 시작 -->
				<c:forEach var="l" items="${ requestScope.list }">
				<tr>
					<td align="center"><c:out value="${ l.rownum }"/></td>
					<td align="center"><c:out value="${ l.iCode }" /></td>
					<td align="center"><c:out value="${ l.iName }"/></td>
					<td align="center"><c:out value="${ l.cnt }"/></td>
					<td align="center"><c:out value="${ l.iCapacity }"/></td>
					<td align="center"><c:out value="${ l.iUnit }"/></td>
					<td align="center"><fmt:formatNumber type="number" maxFractionDigits="3" value="${l.price}" /></td>
					<td align="center"><fmt:formatNumber type="number" maxFractionDigits="3" value="${l.sumPrice}" /></td>
					<td align="center"><button class="detailBtn">보기<input type="text" value="${ l.supName }" hidden></button></td>
				</tr>
				</c:forEach>
				
				<!-- 스크롤 보기용 행 추가 끝 -->
			</table>
			<br>
			<button id="backBtn">뒤로</button>
		</div>
	
	
<!-- 상세보기 버튼 누르면 나오는 창 -->
	<div class="detailContent1" align="center">
		<table class="detailTable" class="table-bottom">
			<thead>
			    <tr>
			        <th width="20%">지점코드</th>
			        <th width="20%">지점명</th>
			        <th width="15%">수량</th>
			    </tr>
		    </thead>
		    <tbody>
		   
		    </tbody>
	    </table>
		<br>
		<button class="detailCloseBtn1">확인</button>
		</div>
	<div class="modalBackground1"></div>

<script>
		/* 모달창 */
	    $(function(){
	        $(".detailBtn, .modalBackground1, .detailCloseBtn1").click(function () {
	            $(".detailContent1, .modalBackground1").toggle();
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
		
	 	/* 상세보기 클릭 시  */
		$(".detailBtn").click(function(){
			console.log($(this).parent().parent().children().eq(1).text());
			
			
			var sCode = $(".sCode").val();
			console.log(sCode);
			var iCode = $(this).parent().parent().children().eq(1).text();
			
			
			$.ajax ({
				url : "selectOneIng.pu",
				type : "get",
				data : {
					iCode : iCode, sCode : sCode
				},
				success : function(data){
										
					var $detailTableBody =  $(".detailTable tbody");
					$detailTableBody.html('');
					
					for(var index in data) {
						var $tr = $("<tr>");
						var $bCode = $("<td>").text(data[index].branchCode);
						var $bName = $("<td>").text(data[index].branchName);
						var $num = $("<td>").text(data[index].purchseQuantity);
						
/* 						console.log($bCode);
						console.log($bName);
						console.log($num); */
						
						$tr.append($bCode);
						$tr.append($bName);
						$tr.append($num);
						
						$detailTableBody.append($tr);
					}
				},
				error : function(err){
					console.log("지점별 발주 수량 조회 실패 ");
				}
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
	 	
		$("#backBtn").click(function(){
			location.href="${ applicationScope.contextPath }/selectBRpuListforHo.pu";
			
		});
	</script>
	
	
</body>
</html>