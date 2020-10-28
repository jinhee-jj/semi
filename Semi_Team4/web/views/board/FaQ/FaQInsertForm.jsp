<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FaQ 작성하기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="${ applicationScope.contextPath }/resources/css/boardCSS/FaQInsertFormCss.css">

</head>


<body>
<c:if test="${sessionScope.loginMember.deptCode eq 'HOF01'}">
		<jsp:include page="/views/common/menubar.jsp"/>
	<div class="outer">
		<div class="boardWrap">
			<br>
			<h2>FAQ 작성하기</h2>
			<br>
			<div class="insertBody">
					<table class="tableForm">
						<tr>
							<td>제목</td>
							<td><input type="text" name="FaQTitle" class="inputText" id="FaQTitle"></td>
						</tr>
	
						<tr>
							<td>작성자</td>
							<td><input type="text" name="FaQWriter" id="FaQWriter"
								value="<c:out value="${ sessionScope.loginMember.memberId }"/> "class="inputText"
								readonly></td>
						</tr>
	
						<tr>
							<td>질문</td>
							<td><textarea cols="100" rows="13%" name="FaQ1" class="textArea" id="FaQ1"></textarea></td>
						</tr>
						<tr>
							<td>답변</td>
							<td><textarea cols="100" rows="13%" name="FaQ2" class="textArea" id="FaQ2"></textarea></td>
						</tr>
					</table>
				</div>
					<br>
					<div class="buttons" align="right">
						<button class="inputBoard2" type="button" id="resetB" name="resetB" onclick="history.back()">뒤로가기</button>
						<button class="inputBoard2" type="button" id="inputB" name="inputB" onclick="inputfaq();">저장하기</button>
					</div>
				</div>
	</div>
	</c:if>
	<c:if test="${sessionScope.loginMember.deptCode ne 'HOF01'}">
	<br><br><br><br><br><br><br>
	<h2 align="center">권한이 없습니다.</h2>
	</c:if>
	
	<script>
	function inputfaq(){
		var fTitle = $("#FaQTitle").val();
		var fWriter = $("#FaQWriter").val();
		var inputF = $("#FaQ1").val();
		var inputQ = $("#FaQ2").val();

		
		$.ajax({
				 type : "POST",
					url : "${ applicationScope.contextPath }/fAqInsert.faq",
					data : {
						fTitle : fTitle
						,fWriter : fWriter
						,inputF : inputF
						,inputQ : inputQ
					},
					success : function(){
						location.href = '${ applicationScope.contextPath }/faqList.faq';
						
					},
					error : function(err) {
						alert("faq 등록 실패!");
					}
		
		});
	};
	
	</script>
	
</body>
</html>