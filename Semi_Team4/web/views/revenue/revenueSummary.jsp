<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 차트용 라이브러리 -->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<link
	href="<%=request.getContextPath()%>/resources/css/revenue/revenueSummaryCss.css"
	rel="stylesheet" type="text/css">
<title>정산관리 > 매출현황</title>
</head>
<body>
	<c:if test="${ !empty sessionScope.loginMember }">
		<jsp:include page="/views/common/menubar.jsp"></jsp:include>
		<div class="outer">
			<div class="inner-wrapper">
				<div class="top">
					<div id="inquiryDiv">
						<!-- 조회기준일 선택 -->
						<!-- 본사 지점 선택 시작 -->
						<div id="selectBranchOption">
							<label>조회기준</label> <select id="viewBranch">
								<option></option>
							</select>
						</div>
						&nbsp;
						<!-- 본사 지점 선택 끝 -->
						<label>조회기준일</label> <input type="date" id="searchDate"
							name="searchDate">
						<button onclick="inquiry();">조회</button>
					</div>
				</div>
				<div class="overview-values">
					<!-- 지점명 불러오기 -->
					<label id="selectedBranch"></label> <br> <br>
					<!-- 총매출 출력 시작 -->
					<table id="grossIncomeTable">
						<tr>
							<!-- 예시 숫자 value 값에 넣어놓음 -> 읽어올 예정 -->
							<td class="tag">지난해 총매출 (원)</td>
							<td id="lastYearGrossIncome">0</td>

							<td class="tag">지난달 총매출 (원)</td>
							<td id="lastMonthGrossIncome">0</td>

							<td class="tag">어제 총매출 (원)</td>
							<td id="lastDayGrossIncome">0</td>
						</tr>
					</table>
					<!-- 총매출 출력 끝 -->

					<!-- 순이익 출력 시작 -->
					<table id="netIncome">
						<tr>
							<!-- 예시 숫자 value 값에 넣어놓음 -> 읽어올 예정 -->
							<td class="tag">지난해 순이익 (원)</td>
							<td id="lastYearNetIncome">0</td>

							<td class="tag">지난달 순이익 (원)</td>
							<td id="lastMonthNetIncome">0</td>

							<td class="tag">어제 순이익 (원)</td>
							<td id="lastDayNetIncome">0</td>
						</tr>
					</table>
					<!-- 순이익 출력 끝 -->
				</div>
				<!-- 예시데이터 사용 -->
				<div class="overview-table">
					<div class="monthOverview-table">
						<h3>월간 매출현황</h3>
						<table id="moTable">
							<tbody></tbody>
						</table>

					</div>

					<div class="dayOverview-table">
						<h3>일간 매출현황</h3>
						<table id="doTable">
							<tbody></tbody>
						</table>

					</div>
				</div>
				<br>
				<div class="overview-graph">
					<div class="monthOverview-graph">
						<div class="monthGraph" id="monthGraph"></div>
					</div>
					<div class="dayOverview-graph">
						<div class="dayGraph" id="dayGraph"></div>
					</div>
				</div>
			</div>
		</div>

	</c:if>

	<c:if test="${empty sessionScope.loginMember }">
		<c:set var="message" value="잘못된 경로로 접근하셨습니다." />
		<jsp:forward page="../../common/errorPage.jsp" />
	</c:if>

	<script>
		$(function() {
			$("#selectBranchOption").hide();

			$("#netIncome").hide();
			
			//조회 초기값 = 오늘날짜
			$("#searchDate").val(getToday());

			//조회 가능일 제한
			$("#searchDate").attr("max", getToday());
			
			var deptCode = "${sessionScope.loginMember.deptCode}";
			var dCode = "${sessionScope.loginMember.deptCode}";
			dCode = dCode.substring(0, 3);

			//지점 계정인 경우-> 지점명 불러오기
			$.ajax({
				url : "${applicationScope.contextPath}/branchCheck.rv",
				data : {
					deptCode : deptCode
				},
				type : "post",
				success : function(data) {
					//본사가 아닌 경우
					if (!(dCode === "HOF")) {
						$("#selectedBranch").text(data + "점 매출현황");
					} else {
						$("#selectBranchOption").show();
						$("#netIncome").show();
					}
				}
			});

			//본사 계정인 경우-> 지점 리스트 불러오기
			$.ajax({
				url : "${applicationScope.contextPath}/selectBranchAll.rv",
				type : "post",
				success : function(data) {

					var $select = $("#viewBranch");
					$select.find("option").remove();

					//본사 선택 option
					var $topOption = $("<option>");
					$topOption.val("HOF01");
					$topOption.text('전체 ( 본사 )');

					$select.append($topOption);

					//지점 선택 option
					for ( var i in data) {
						var $option = $("<option>");
						$option.val(data[i].branchCode);
						$option.text(data[i].branchName + "지점 ( "
								+ data[i].branchCode + " )");

						$select.append($option);
					}
				},
				error : function(err) {
					console.log("err: " + err);
				}
			});
		});

		//첫 화면 로딩 시 오늘 날짜 기준으로 조회 초기화
		inquiry(getToday());

		//조회버튼 클릭 시 조회되는 내용
		function inquiry(defaultDate) {
			var deptCode = "${sessionScope.loginMember.deptCode}";
			var dCode = "${sessionScope.loginMember.deptCode}";
			dCode = dCode.substring(0, 3);

			var selDate = "";
			var selBranch = "";
			if (defaultDate != null) {
				selDate = defaultDate;
				selBranch = "HOF01";
			} else {
				selDate = $("#searchDate").val();
				selBranch = $("#viewBranch :selected").val()
			}

			if (!(dCode === "HOF")) {
				selBranch = deptCode;
			}

			if (selBranch == "HOF01") {
				//선택된 option값이 '본사'인 경우

				//총매출(일)
				$.ajax({
					url : "${applicationScope.contextPath}/hoIncomeYesterday.rv",
					type : "post",
					data : {selDate : selDate},
					success : function(data) {
						var grossIncomeHoYesterday = addCurrencyComma(data.grossIncome);
						$("#lastDayGrossIncome").text(grossIncomeHoYesterday);
					},
					error : function(err) {
						console.log(" 실패 총매출(일)" + err);
					}
				});

				//총매출 (월)
				$.ajax({
					url : "${applicationScope.contextPath}/hoIncomeMonth.rv",
					type : "post",
					data : {selDate : selDate},
					success : function(data) {
						var grossIncomeHoMonth = addCurrencyComma(data.grossIncome);
						$("#lastMonthGrossIncome").text(grossIncomeHoMonth);
					},
					error : function(err) {
						console.log(" 실패 총매출(월)" + err);
					}
				});

				//총매출 (연)
				$.ajax({
					url : "${applicationScope.contextPath}/hoIncomeYear.rv",
					type : "post",
					data : {selDate : selDate},
					success : function(data) {
						var grossIncomeHoYear = addCurrencyComma(data.grossIncome);
						$("#lastYearGrossIncome").text(grossIncomeHoYear);
					},
					error : function(err) {
						console.log(" 실패 총매출(년)" + err);
					}
				});

				//순이익(일)
				$.ajax({
					url : "${applicationScope.contextPath}/hoMarginYesterday.rv",
					type : "post",
					data : {selDate : selDate},
					success : function(data) {
						var marginHoMonth = addCurrencyComma(data.margin);
						$("#lastDayNetIncome").text(marginHoMonth);
					},
					error : function(err) {
						console.log * ("실패 순이익(일) err: " + err)
					}
				});

				//순이익(월)
				$.ajax({
					url : "${applicationScope.contextPath}/hoMarginMonth.rv",
					type : "post",
					data : {selDate : selDate},
					success : function(data) {var marginHoYesterday = addCurrencyComma(data.margin);
						$("#lastMonthNetIncome").text(marginHoYesterday);
					},
					error : function(err) {
						console.log * ("실패 순이익(일) err: " + err)
					}
				});

				//순이익(연)
				$.ajax({
					url : "${applicationScope.contextPath}/hoMarginYear.rv",
					type : "post",
					data : {selDate : selDate},
					success : function(data) {var marginHoYear = addCurrencyComma(data.margin);
						$("#lastYearNetIncome").text(marginHoYear);
					},
					error : function(err) {
						console.log * ("실패 순이익(일) err: " + err)
					}
				});

				//일간 매출현황 테이블
				$.ajax({
					url : "${applicationScope.contextPath}/hoIncomeListDay.rv",
					type : "post",
					data : {selDate : selDate
					},
					success : function(data) {
						// 표시할 행 개수
						var row = 5;

						$table = $("#doTable tbody");
						$table.html("");

						//컬럼명 삽입
						var $thTr = $("<tr>");

						$th1 = $("<th>").text("일자");
						$th2 = $("<th>").text("매출(원)");
						$th3 = $("<th>").text("제조원가(원)");
						$th4 = $("<th>").text("순이익(원)");
						$th5 = $("<th>").text("마진율");

						$thTr.append($th1);
						$thTr.append($th2);
						$thTr.append($th3);
						$thTr.append($th4);
						$thTr.append($th5);

						$table.append($thTr);

						//행 삽입
						for (var i = 0; i < row; i++) {
							var $tr = $("<tr>");

							if (!isEmpty(data[i])) {
								var $dateTd = $("<td>").text(
										data[i].date);
								var $grossIncomeTd = $("<td>")
										.text(
												addCurrencyComma(data[i].grossIncome));
								var $manufactureCostTd = $("<td>")
										.text(
												addCurrencyComma(data[i].manufactureCost));
								var $marginTd = $("<td>")
										.text(
												addCurrencyComma(data[i].margin));
								var $marginRateTd = $("<td>").text(
										data[i].marginRate + "%");

								$tr.append($dateTd);
								$tr.append($grossIncomeTd);
								$tr.append($manufactureCostTd);
								$tr.append($marginTd);
								$tr.append($marginRateTd);

							} else {
								var $dateTd = $("<td>").text("-");
								var $grossIncomeTd = $("<td>")
										.text("-");
								var $manufactureCostTd = $("<td>")
										.text("-");
								var $marginTd = $("<td>").text("-");
								var $marginRateTd = $("<td>").text("-");

								$tr.append($dateTd);
								$tr.append($grossIncomeTd);
								$tr.append($manufactureCostTd);
								$tr.append($marginTd);
								$tr.append($marginRateTd);
							}

							$table.append($tr);
						}

						//일간 매출현황 그래프 그리기
						//데이터 ArrayList, 최대 개수, 그래프 출력 위치의 id
						var maxNum = 5;

						if (maxNum > data.length) {
							maxNum = data.length;
						}

						makeChart(data, maxNum, "dayGraph");

					},
					error : function(err) {
						console.log("실패 월간매출 err: " + err);
					}
				});

				//월간 매출현황 테이블
				$.ajax({
					url : "${applicationScope.contextPath}/hoIncomeListMonth.rv",
					type : "post",
					data : {
						selDate : selDate
					},
					success : function(data) {
						// 표시할 행 개수
						var row = 5;

						$table = $("#moTable tbody");
						$table.html("");

						//컬럼명 삽입
						var $thTr = $("<tr>");

						$th1 = $("<th>").text("일자");
						$th2 = $("<th>").text("매출(원)");
						$th3 = $("<th>").text("제조원가(원)");
						$th4 = $("<th>").text("순이익(원)");
						$th5 = $("<th>").text("마진율");

						$thTr.append($th1);
						$thTr.append($th2);
						$thTr.append($th3);
						$thTr.append($th4);
						$thTr.append($th5);

						$table.append($thTr);

						//행 삽입
						for (var i = 0; i < row; i++) {
							var $tr = $("<tr>");

							if (!isEmpty(data[i])) {
								var $dateTd = $("<td>").text(
										data[i].date);
								var $grossIncomeTd = $("<td>")
										.text(
												addCurrencyComma(data[i].grossIncome));
								var $manufactureCostTd = $("<td>")
										.text(
												addCurrencyComma(data[i].manufactureCost));
								var $marginTd = $("<td>")
										.text(
												addCurrencyComma(data[i].margin));
								var $marginRateTd = $("<td>").text(
										data[i].marginRate + "%");

								$tr.append($dateTd);
								$tr.append($grossIncomeTd);
								$tr.append($manufactureCostTd);
								$tr.append($marginTd);
								$tr.append($marginRateTd);

							} else {
								var $dateTd = $("<td>").text("-");
								var $grossIncomeTd = $("<td>")
										.text("-");
								var $manufactureCostTd = $("<td>")
										.text("-");
								var $marginTd = $("<td>").text("-");
								var $marginRateTd = $("<td>").text("-");

								$tr.append($dateTd);
								$tr.append($grossIncomeTd);
								$tr.append($manufactureCostTd);
								$tr.append($marginTd);
								$tr.append($marginRateTd);
							}

							$table.append($tr);
						}

						//월간 매출현황 그래프 그리기
						//데이터 ArrayList, 최대 개수, 그래프 출력 위치의 id
						var maxNum = 5;

						if (maxNum > data.length) {
							maxNum = data.length;
						}

						makeChart(data, maxNum, "monthGraph");

					},
					error : function(err) {
						console.log("실패 월간매출 err: " + err);
					}
				});

				//선택된 option 값이 '지점'인 경우
			} else {
				//일매출
				$.ajax({
					url : "${applicationScope.contextPath }/brIncomeYesterday.rv",
					type : "post",
					data : {
						selDate : selDate,
						selBranch : selBranch
					},
					success : function(data) {
						var grossIncomeBranchYesterday = addCurrencyComma(data.grossIncome);
						$("#lastDayGrossIncome").text(
								grossIncomeBranchYesterday);

					},
					error : function(err) {
						console.log("BranchDay err: " + err);
					}
				});

				//월매출
				$.ajax({
					url : "${applicationScope.contextPath }/brIncomeMonth.rv",
					type : "post",
					data : {
						selDate : selDate,
						selBranch : selBranch
					},
					success : function(data) {

						var grossIncomeBranchMonth = addCurrencyComma(data.grossIncome);
						$("#lastMonthGrossIncome").text(
								grossIncomeBranchMonth);

					},
					error : function(err) {
						console.log("BranchMonth err: " + err);
					}
				});

				//연매출
				$.ajax({
					url : "${applicationScope.contextPath }/brIncomeYear.rv",
					type : "post",
					data : {
						selDate : selDate,
						selBranch : selBranch
					},
					success : function(data) {
						var grossIncomeBranchYear = addCurrencyComma(data.grossIncome);
						$("#lastYearGrossIncome").text(
								grossIncomeBranchYear);

					},
					error : function(err) {
						console.log("BranchMonth err: " + err);
					}
				});

				//일간 매출현황 테이블
				$.ajax({
					url : "${applicationScope.contextPath}/brIncomeListDay.rv",
					type : "post",
					data : {
						selDate : selDate,
						selBranch : selBranch
					},
					success : function(data) {
						// 표시할 행 개수
						var row = 5;

						$table = $("#doTable tbody");
						$table.html("");

						//컬럼명 삽입
						var $thTr = $("<tr>");

						$th1 = $("<th>").text("일자");
						$th2 = $("<th>").text("매출(원)");
						$th3 = $("<th>").text("제조원가(원)");
						$th4 = $("<th>").text("순이익(원)");
						$th5 = $("<th>").text("마진율");

						$thTr.append($th1);
						$thTr.append($th2);
						$thTr.append($th3);
						$thTr.append($th4);
						$thTr.append($th5);

						$table.append($thTr);

						//행 삽입
						for (var i = 0; i < row; i++) {
							var $tr = $("<tr>");

							if (!isEmpty(data[i])) {
								var $dateTd = $("<td>").text(
										data[i].date);
								var $grossIncomeTd = $("<td>")
										.text(addCurrencyComma(data[i].grossIncome));
								var $manufactureCostTd = $("<td>")
										.text(addCurrencyComma(data[i].manufactureCost));
								var $marginTd = $("<td>")
										.text(addCurrencyComma(data[i].margin));
								var $marginRateTd = $("<td>").text(
										data[i].marginRate + "%");

								$tr.append($dateTd);
								$tr.append($grossIncomeTd);
								$tr.append($manufactureCostTd);
								$tr.append($marginTd);
								$tr.append($marginRateTd);

							} else {
								var $dateTd = $("<td>").text("-");
								var $grossIncomeTd = $("<td>")
										.text("-");
								var $manufactureCostTd = $("<td>")
										.text("-");
								var $marginTd = $("<td>").text("-");
								var $marginRateTd = $("<td>").text("-");

								$tr.append($dateTd);
								$tr.append($grossIncomeTd);
								$tr.append($manufactureCostTd);
								$tr.append($marginTd);
								$tr.append($marginRateTd);
							}

							$table.append($tr);

							//일간 매출현황 그래프 그리기
							//데이터 ArrayList, 최대 개수, 그래프 출력 위치의 id
							var maxNum = 5;

							if (maxNum > data.length) {
								maxNum = data.length;
							}

							makeChart(data, maxNum, "dayGraph");
						}
					},
					error : function(err) {
						console.log("실패 월간매출 err: " + err);
					}
				})

				//월간 매출현황 테이블
				$.ajax({
					url : "${applicationScope.contextPath}/brIncomeListMonth.rv",
					type : "post",
					data : {
						selDate : selDate,
						selBranch : selBranch
					},
					success : function(data) {
						// 표시할 행 개수
						var row = 5;

						$table = $("#moTable tbody");
						$table.html("");

						//컬럼명 삽입
						var $thTr = $("<tr>");

						$th1 = $("<th>").text("일자");
						$th2 = $("<th>").text("매출(원)");
						$th3 = $("<th>").text("제조원가(원)");
						$th4 = $("<th>").text("순이익(원)");
						$th5 = $("<th>").text("마진율");

						$thTr.append($th1);
						$thTr.append($th2);
						$thTr.append($th3);
						$thTr.append($th4);
						$thTr.append($th5);

						$table.append($thTr);

						//행 삽입
						for (var i = 0; i < row; i++) {
							var $tr = $("<tr>");

							if (!isEmpty(data[i])) {
								var $dateTd = $("<td>").text(
										data[i].date);
								var $grossIncomeTd = $("<td>")
										.text(
												addCurrencyComma(data[i].grossIncome));
								var $manufactureCostTd = $("<td>")
										.text(
												addCurrencyComma(data[i].manufactureCost));
								var $marginTd = $("<td>")
										.text(
												addCurrencyComma(data[i].margin));
								var $marginRateTd = $("<td>").text(
										data[i].marginRate + "%");

								$tr.append($dateTd);
								$tr.append($grossIncomeTd);
								$tr.append($manufactureCostTd);
								$tr.append($marginTd);
								$tr.append($marginRateTd);

							} else {
								var $dateTd = $("<td>").text("-");
								var $grossIncomeTd = $("<td>")
										.text("-");
								var $manufactureCostTd = $("<td>")
										.text("-");
								var $marginTd = $("<td>").text("-");
								var $marginRateTd = $("<td>").text("-");

								$tr.append($dateTd);
								$tr.append($grossIncomeTd);
								$tr.append($manufactureCostTd);
								$tr.append($marginTd);
								$tr.append($marginRateTd);
							}
							$table.append($tr);

							//월간 매출현황 그래프 그리기
							//데이터 ArrayList, 최대 개수, 그래프 출력 위치의 id
							var maxNum = 5;

							if (maxNum > data.length) {
								maxNum = data.length;
							}
							makeChart(data, maxNum, "monthGraph");
						}
					},
					error : function(err) {
						console.log("실패 월간매출 err: " + err);
					}
				})
			}
		}

		//1,000 자릿수에 콤마 추가
		function addCurrencyComma(num) {
			return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
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

		//비어있는 변수인지 확인
		function isEmpty(str) {
			if (typeof (str) === "undefined" || str == null || str == "") {
				return true;
			} else {
				return false;
			}
		}

		//차트 그리는 함수
		function makeChart(data, maxNum, divId) {
			google.charts.load('current', {
				'packages' : [ 'bar' ]
			});
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

				for (var i = 0; i < maxNum; i++) {
					list.addRow([ data[i].date, data[i].grossIncome,data[i].margin ]);
				}

				options = {
					chart : {title : "매출현황 그래프"}
				};

				chart = new google.charts.Bar(document.getElementById(divId));

				chart.draw(list, google.charts.Bar.convertOptions(options));
			}

			function addData() {
				count++;
				list.addRow([ data.date, data.grossIncome, data.margin ],
						data.marginRate);
				chart.draw(list, options)
			}
			function removeData() {
				list.removeRow(0);
				chart.draw(list, opstions)
			}
		}
	</script>

</body>
</html>
