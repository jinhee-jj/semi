<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() %>/resources/css/qna/qnaInsertFormCss.css" rel="stylesheet" type="text/css">

<title>커뮤니티 > 질의응답</title>
</head>
<body>
	<jsp:include page="/views/common/menubar.jsp"></jsp:include>
	
	<c:if test="${ !empty sessionScope.loginMember }">
		<div class="outer">
			<div class="boardWrap">
				<br>
				<h2>질의응답 작성하기</h2>
				<br>
				<form action="${applicationScope.contextPath }/insert.qa" method="post" encType="multipart/form-data">
					<table class="boardTable">
						<tr>
							<td><label>제목</label><input hidden name="writer" value="${sessionScope.loginMember.memberId }"></td>
							<td class="inputArea"><input type="text" name="title" id="title" autofocus></td>
						</tr>
						
						<tr>
							<td><label>내용</label></td>
							<td class="inputArea"><textarea name="content" id="content" rows="25%"></textarea></td>
						</tr>
						
						<tr>
							<td><label>첨부</label></td>
							<td id="attached">
								<input type="file" name="attachment1" id="attachment1">
								<input type="file" name="attachment2" id="attachment2">
							</td>
						</tr>
					</table>
					<br>
					<div class="buttons">
						<input type="reset" class="btn" value="지우기">
						<input type="submit" class="btn" value="등록" onclick="submit();">
						<button class="btn detail" onclick="location.href='${ applicationScope.contextPath }/selectList.qa'">목록으로</button>
					</div>
		
				</form>
			</div>
		</div>
		
		<script>
			//버튼 교체 시 자동 submit을 방지
			var submitAction = function(e) {
				e.preventDefault();
			    e.stopPropagation();
			};
			$('form').bind('submit', submitAction);
		</script>
		
	</c:if>
	
	<c:if test="${ empty sessionScope.loginMember }">
		<c:set var="message" value="잘못된 경로로 접근하셨습니다." scope="request"/>
		<jsp:forward page="../../common/erropPage.jsp"/>
	</c:if>
</body>
</html>
