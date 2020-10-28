<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="<%= request.getContextPath() %>/resources/css/orderManagement/menuManagementDetailCss.css" rel="stylesheet" type="text/css">


<title>메뉴관리</title>

<!-- 본사/지점 화면 구분은 loginUer에 따라 '메뉴등록' 버튼의 유무로 결정한다. -->

</head>
<body>
 	<jsp:include page="../../views/common/menubar.jsp"/>
	
	<div class="outer">
	
		<div class="head">
			<h2>제품명</h2>
			<input type="text" name="menuName" readonly value="<c:out value="${requestScope.menuOne[0].menuName }"/>">
		</div>
	
		<div class="status">
			<label>상태: <c:out value="${requestScope.menuOne[0].status }"/></label>
		</div>
		<br>
		<div class="tableArea">
			<table>
				<!-- 예시데이터 시작 -->
				<tr>
					<th>메뉴사진</th>
					<th>원재료코드</th>
					<th>원재료명</th>
					<th>용량</th>
					<th>단위</th>
				</tr>

				<tr>
					<td rowspan="10"><img width="100px" height="100px" src="${ applicationScope.contextPath }/resources/images/menuImage/<c:out value="${ requestScope.menuOne[0].menuImage }"/>"class="img" ></td>
				</tr>
				
			<c:forEach var="o" items="${ requestScope.menuOne }">
				
				<tr>
					<td><c:out value="${ o.idenCode }"/></td>
					<td><c:out value="${ o.idenName }"/></td>
					<td><c:out value="${ o.idenCapacity }"/></td>
					<td><c:out value="${ o.idenUnit }"/></td>
				</tr>
				
			</c:forEach>
				
				<!-- requestScope 이용 시작-->
				<!-- c:forEach/> 또는 jQuery 이용하여 원재료 반복출력 -->
				
				<tr>
					<td rowspan="10"><c:out value="${requestScope.menuImage }"/></td>
				</tr>
					<c:forEach var="menu" items="${requestScope.menu }">
						
					</c:forEach>
					
				<!-- requestScope 이용 끝 -->
			</table>		
		</div>
		<br>		
		<div class="controllArea branch">
			<!-- <button value="goBack">뒤로가기</button> -->
		</div>

		<!-- sessionScope.loginUser.memberDeptCode를 통해 본사여부 확인 한 뒤
			 본사계정인 경우에만 노출되도록 처리 -->
		<div class="controllArea headOffice">
			<button value="goBack" onclick="location.href='${applicationScope.contextPath}/selectList.mm'">뒤로가기</button>
			<!-- 수정하기 클릭 시 ajax 이용하여 "용량"을 input[type:text]로 변환 후 수정 -->
			<button id="modifyBtn" value="modifyRecipe">수정</button>
			<button id="deleteBtn" value="delete">삭제</button>
			<button id="complateBtn" value="complate">완료</button>
		</div>
	
	</div>

	<!-- 모달창 배경 -->
	<div class="modalBackground">
	<!-- 삭제여부 확인용 모달창 -->
		<div id="confirmDelete">
			<br>
			<label>삭제하시겠습니까?</label>
			<br><br>
			<button id="complateDelete" value="complateDelete">확인</button>
			<button id="cancelDelete" value="cancelDelete">취소</button>
		</div>
	</div>

	<script>
		$(function(){
			$("#complateBtn").hide();
			
			$(".modalBackground").hide();
			
			$("#modifyBtn").click(function(){
				$("#complateBtn").show();
				
				$("#complateBtn").click(function(){
					$("#complateBtn").hide();
				});
			});
			
			$("#deleteBtn").click(function(){
				$(".modalBackground").show();
			});
			
			$("#complateDelete").click(function(){
				$(".modalBackground").hide();
			});
			
			$("#cancelDelete").click(function(){
				$(".modalBackground").hide();
			});
			
			$(".modalBackground").click(function(){
				$(".modalBackground").hide();
			});
			
		});
		
		//삭제 확인용 alert -> 모달창으로 구현
		function deleteMenu(){
			$("#confirmDelete").show();
		};
	
		$("#complateDelete").click(function(){
			var what = '${ requestScope.code }';
			
			location.href="${applicationScope.contextPath}/delete.mm?what="+what;
		});
		
	</script>
</body>
</html>