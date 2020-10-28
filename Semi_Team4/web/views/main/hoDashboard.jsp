<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 차트용 라이브러리 -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<link href="<%= request.getContextPath() %>/resources/css/dashboard/hoDashboardCss.css" rel="stylesheet" type="text/css">

<title>메인</title>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:if test="${ !empty sessionScope.loginMember }">
		<div class="innerWrap">
			<div class="innerLeft">
				<div class="noticeView dashviews">
					<h2>공지사항</h2><a id="moreView" href="${ applicationScope.contextPath }/selectList.no" style="text-decoration:none">+</a>
					<table id="noticeTable">
						<tbody></tbody>
					</table>		
				</div>
				<div class="revenueView dashviews">
					<h2>매출통계</h2>
					<div id="revenueGraph">
					</div>	
				</div>
			</div>
			<div class="innerRight">
				<c:if test="${sessionScope.loginMember.memberDeptCode eq '본사'}">
					<div class="revenueNowView dashviews">
						<h2>현재 매출현황</h2>
						<table id="revenueNowTable">
							<tbody></tbody>
						</table>
					</div>
				</c:if>	
			
				<c:if test="${sessionScope.loginMember.memberDeptCode ne '본사'}">
					<div class="orderNowView dashviews">
						<h2>현재 주문현황</h2>
						<table id="orderNowTable">
							<tbody>
								<tr><td>예시</td><td>값</td></tr>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
	</c:if>
	
	<c:if test="${empty sessionScope.loginMember }">
		<c:set var="message" value="잘못된 경로로 접근하셨습니다."/>
		<jsp:forward page="../../common/errorPage.jsp"/>
	</c:if>	
	
	<script>
		$(function(){
			var row = 5;		// 표시할 공지사항 개수
			
			//공지사항 불러오기
			$.ajax({
				url: "${applicationScope.contextPath}/overviewNotice.db",
				type: "get",
				data: {row: row},
				success: function(data){
					var $table = $("#noticeTable tbody");
					$table.html("");
					
					for(var i=0; i<row; i++){
						var $tr = $("<tr>");

						if(!isEmpty(data[i])){
							var $titleTd = $("<td>").text(data[i].boardTitle);						
							var $dateTd = $("<td>").text(data[i].boardWriteDate);
							
							$tr.append($titleTd);
							$tr.append($dateTd);
						} else{
							var $titleTd = $("<td>").text("");
							var $dateTd = $("<td>").text("");
							
							$tr.append($titleTd);
							$tr.append($dateTd);
						}
						
						$table.append($tr);
					}
				}, error: function(err){
					console.log(err);
				}
			});
			
			//매출현황 불러오기
			$.ajax({
				url: "${ applicationScope.contextPath }/revenueNow.db",
				data: {today: getToday()},
				type: "post",
				success: function(data){
					// 표시할 행 개수
					var row = 15;
					
					if(data.length > row){
						row = data.length;
					}
					
					var $table = $("#revenueNowTable tbody");
					$table.html("");

					var $thTr = $("<tr>");

					$th1 = $("<th>").text("지점");
					$th2 = $("<th>").text("매출(원)");

					$thTr.append($th1);
					$thTr.append($th2);

					$table.append($thTr);
					
					for(var i=0; i<row; i++){
						var $tr = $("<tr>");

						if(!isEmpty(data[i])){
							var $branchTd = $("<td>").text(data[i].branchName);						
							var $dateTd = $("<td>").text(data[i].grossIncome);
							
							$tr.append($branchTd);
							$tr.append($dateTd);
						} else{
							var $branchTd = $("<td>").text("");
							var $dateTd = $("<td>").text("");
							
							$tr.append($branchTd);
							$tr.append($dateTd);
						}
						
						$table.append($tr);
					}
					
					
				}, error: function(err){
					console.log("revenueNow Fail");
				}
			});
			
			//주문내역 불러오기
			var branchCode = "${sessionScope.loginMember.deptCode}";

			var test = "${sessionScope.loginMember}";
			$.ajax({
				url: "${applicationScope.contextPath}/orderNow.db",
				data: {today: getToday(), branchCode: branchCode},
				type: "post",
				success: function(data){
					// 표시할 행 개수
					var row = 15;
					
					if(data.length > row){
						row = data.length;
					}
					
					var $table = $("#orderNowTable tbody");
					$table.html("");
	
					var $thTr = $("<tr>");
	
					$th1 = $("<th>").text("제품명");
					$th2 = $("<th>").text("수량");
					$th3 = $("<th>").text("포장(Y/N)");
					$th4 = $("<th>").text("상태");
	
					$thTr.append($th1);
					$thTr.append($th2);
					$thTr.append($th3);
					$thTr.append($th4);
	
					$table.append($thTr);
					
					for(var i=0; i<row; i++){
						var $tr = $("<tr>");
	
						if(!isEmpty(data[i])){
							
							var $menuNameTd = $("<td>").text(data[i].menuName);
							var $orderQuantityTd = $("<td>").text(data[i].orderQuantity);
							var $toGoTd = $("<td>").text(data[i].toGo);
							var $orderStatusTd = $("<td>").text(data[i].orderStatus);
							
							$tr.append($menuNameTd);
							$tr.append($orderQuantityTd);
							$tr.append($toGoTd);
							$tr.append($orderStatusTd);
	
						} else{
							var $menuNameTd = $("<td>").text("");
							var $orderQuantityTd = $("<td>").text("");
							var $toGoTd = $("<td>").text("");
							var $orderStatusTd = $("<td>").text("");
							
							$tr.append($menuNameTd);
							$tr.append($orderQuantityTd);
							$tr.append($toGoTd);
							$tr.append($orderStatusTd);
						}
						$table.append($tr);
					}
				}, error: function(err){
					console.log("orderNow Fail");
				}
			});

			inquiry(getToday());
			
			//매출통계 그래프
			function inquiry(defaultDate) {
				var deptCode = "${sessionScope.loginMember.deptCode}";
				var dCode = "${sessionScope.loginMember.deptCode}";
				dCode = dCode.substring(0, 3);

				var deptName = "";
				
				//지점명 불러오기
				$.ajax({
					url: "${applicationScope.contextPath}/getBranchName.db",
					type: "post",
					data: {deptCode: deptCode},
					success: function(data){
						if(isEmpty(data)){
							deptName = "전체";						
						} else{
							deptName = data + "지점";	
						}
					}, error: function(err){
						console.log(err);
					}
				});
				
				var selDate = "";
				var selBranch = "";

				selDate = defaultDate;
				selBranch = deptCode;

				//selBranch값이 '본사'인 경우
				if (selBranch == "HOF01") {
					$.ajax({
						url : "${applicationScope.contextPath}/hoIncomeListDay.rv",
						type : "post",
						data : {selDate : selDate},
						success : function(data) {
							//일간 매출현황 그래프 그리기
							//데이터 ArrayList, 최대 개수, 그래프 출력 위치의 id
							var maxNum = 5;
							
							if(maxNum > data.length){
								maxNum = data.length;
							}
							makeChart(data, maxNum, "revenueGraph", deptName);
						},
						error : function(err) {
							console.log("실패 ho매출현황 그래프 err: " + err);
						}
					});

				//selBranch 값이 '지점' 또는 '키오스크'인 경우
				} else {
					//일간 매출현황 테이블
					$.ajax({
						url : "${applicationScope.contextPath}/brIncomeListDay.rv",
						type : "post",
						data : {selDate : selDate, selBranch : selBranch},
						success : function(data) {
							//일간 매출현황 그래프 그리기
							//데이터 ArrayList, 최대 개수, 그래프 출력 위치의 id
							var maxNum = 5;
							
							if(maxNum > data.length){
								maxNum = data.length;
							}
							makeChart(data, maxNum, "revenueGraph", deptName);
						},
						error : function(err) {
							console.log("실패 월간매출 err: " + err);
						}
					});
				}
			}
		});

		//차트 그리는 함수
		function makeChart(data, maxNum, divId, deptName){
			google.charts.load('current', {'packages':['bar']});
			google.charts.setOnLoadCallback(drawChart);
		
			var count = 0;
			var list;
			var chart;
			var options;
			
			function drawChart() {
		  	  list = new google.visualization.DataTable();
		  	  list.addColumn('string', '일자');
		  	  list.addColumn('number', '매출(원)');
		  	  list.addColumn('number', '순이익(원)');

		  	  for(var i=0; i<maxNum; i++){
		  	  	list.addRow([data[i].date, data[i].grossIncome, data[i].margin]);
		  	  }
		  	  
	          options = {
				chart: {title: deptName},
	          };
	
	          chart = new google.charts.Bar(document.getElementById(divId));
	
	          chart.draw(list, google.charts.Bar.convertOptions(options));
	        }
	        
	        function addData(){
		  	    count++;
		  		list.addRow([data.date, data.grossIncome, data.margin], data.marginRate);
		  		chart.draw(list, options)
	        }
	        function removeData(){
		  		list.removeRow(0);
		  		chart.draw(list, opstions)
	        }
			
		}
		
		
		//비어있는 변수인지 확인
		function isEmpty(str){
			if(typeof(str) === "undefined" || str == null || str == ""){
				return true;
			} else{
				return false;
			}
		}
		
		//오늘 날짜 반환
		function getToday() {
			var now = new Date();
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			var date = now.getDate();

			if (month < 10) {
				month = "0" + month;
			}
			return year + "-" + month + "-" + date;
		}
		
	</script>
	

</body>
</html>
