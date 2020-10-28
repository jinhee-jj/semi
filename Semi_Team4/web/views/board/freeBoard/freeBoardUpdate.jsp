<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 보기</title>
<link rel="stylesheet" type="text/css" href="${ applicationScope.contextPath }/resources/css/freeBoard/freeBoardOne.css">

</head>
<body>
	<jsp:include page="/views/common/menubar.jsp" />
	
	<c:if test="${ !empty sessionScope.loginMember }">
		<div class="outer">
			<br>
			<h2 align="left" id = "title">자유게시판 수정</h2>
			<div class="table-area">
				<form action="${ applicationScope.contextPath }/update.fb?num=${ requestScope.board.boardNo }" method="post">
					<table align="center">
						<tr>
							<td>제목</td>
							     <td colspan="4"><input type="text" size="99%" name="title" value="${ requestScope.board.boardTitle }"></td>
							<%-- <td colspan="4"><span><c:out value="${ requestScope.board.boardTitle }"/></span></td> --%>						
						</tr>
						
						<tr>
							<td>내용</td>
							<td colspan="4">
								<textarea cols="100%" rows="25%" class="freeTextarea" name="content" >${ requestScope.board.boardContent }</textarea>
								
							</td>
						</tr>
					</table>
					<br>
					<div align="right" class="bottm">
						<button type="button" class="Btn" onclick="goList()">목록</button>
					</div>
					<br>
					<div class="buttons" align="center">
						<button type="reset" class="Btn" onclick="cancel()">취소</button>
						<button type="submit" class="Btn">수정</button>
					</div>
				</form>
			</div>
					<script>
						function goList() {
							location.href = "${pageContext.request.contextPath}/selectList.fb?community=2";
						}
						$(function() {
							$("#searchCondition3").click(function(){
								
								var radio = $('input[name="radioSearch"]:checked').val();
								var select = $("#searchCondition option:selected").val();
								var search = $("#searchCondition2").val();
								
								location.href = "${ pageContext.request.contextPath }/search.fb?radio="+radio+"&select="+select+"&search="+search;
							})
						});
						
					</script>
		</div>
	</c:if>
	<c:if test="${ empty sessionScope.loginMember }">
		<c:set var="message" value="잘못된 경로로 접근하셨습니다." scope="request"/>
		<jsp:forward page="../../common/errorPage.jsp"/>
	</c:if>
</body>
</html>