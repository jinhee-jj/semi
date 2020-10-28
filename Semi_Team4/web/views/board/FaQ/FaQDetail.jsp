<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FaQ</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%-- <link rel="stylesheet" type="text/css"
	href="${ applicationScope.contextPath }/resources/css/boardCSS/FaQboard.css">
 --%>
 <link rel="stylesheet" type="text/css" href="${ applicationScope.contextPath }/resources/css/boardCSS/FaQInsertFormCss.css">
</head>

<body>

	<jsp:include page="/views/common/menubar.jsp" />
	<div class="outer">
	<div class="boardWrap">

		<h2>FAQ</h2>
		<div class="insertBody">

			<table class="tableForm">
				<tr>
					<td><p>제목</p></td>
					<td><input type="text" name="FaQTitle" class="inputText noneIn"
						value="<c:out value='${ requestScope.faq.faqTitle }' />" readonly></td>
				</tr>

				<tr>
					<td><p>작성자</p></td>
					<td><input type="text" name="FaQWriter" class="inputText noneIn"
						value="<c:out value='${ requestScope.faq.memberId }'/>" readonly></td>
				</tr>

				<tr>
					<td>질문</td>

					<td><textarea cols="100" rows="13%" name="FaQ1"
							class="textArea noneIn" readonly><c:out value="${ requestScope.faq.fContents }" />
						</textarea></td>
				</tr>
				<tr>
					<td>대답</td>

					<td><textarea cols="100" rows="13%" name="FaQ2"
							class="textArea noneIn" readonly><c:out value='${ requestScope.faq.aContents }' />
						</textarea></td>
				</tr>
			</table>
				<input type="hidden" value="<c:out value='${ requestScope.faq.fId }' />" id="fnum" name="fnum">
			<br>
		</div>
		<br>
		<button class="inputBoard2"
			onclick="location.href = '${ applicationScope.contextPath }/faqList.faq'">목록으로</button>
			
		<c:if test="${sessionScope.loginMember.deptCode eq 'HOF01'}">
		<div class="buttons" align="right">
			<input type="button" class="inputBoard2" value="수정" name="updateB"
				value="${ i.faqCode }" onclick="update();">
				
			<input type="button" class="inputBoard2" value="삭제" name="delB"
				value="${ i.faqCode }" onclick="delfaq();">
				</div>
		</c:if>
		<br>
	</div>
	</div>
	<script>
	
		function update() {
			var num = $("#fnum").val();
			//console.log(num);
			location.href = "${ applicationScope.contextPath }/faqUpdate.faq?num=" + num;
		};


		function delfaq(){
			var num = $("#fnum").val();

			location.href = "${ applicationScope.contextPath }/faqDel.faq?num=" + num;
		};
		
	</script>
	
	
</body>
</html>