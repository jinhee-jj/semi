<jsp:directive.page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>자유게시판 등록</title>
<link rel="stylesheet" type="text/css"
	href="${ applicationScope.contextPath }/resources/css/freeBoard/freeInsertForm.css">

</head>
<body>
	<jsp:include
		page="/views/common/menubar.jsp" />
	<c:if test="${ !empty sessionScope.loginMember }">
		<div class="outer">
			<div class="wrap">
				<br>
				<h2 align="left" id="title">자유게시판 작성</h2>
				<br>
				<form action="${ pageContext.request.contextPath }/insert.fb?community=2" method="post">
					<table align="center" class="table-area">
						<tr>
							<td><label>제목</label></td>
							<td><input class="inputArea" type="text" name="title"></td>
						</tr>
	
						<tr>
							<td><label>내용</label></td>
							<td><textarea name="content" rows="25%" class="freeTextarea"></textarea>
							</td>
						</tr>
						<tr>
							<td><label>첨부파일</label></td>
							<td><input class="fileAttach" type="text" readonly> <input
								class="fileAttach" type="file"></td>
						</tr>
					</table>
					<br>
					<div class="buttons" align="center">
						<button type="reset" class="Btn" onclick="cancel()">취소</button>
						<button type="submit" class="Btn">등록</button>
					</div>
				</form>
			</div>
		</div>
		<script>
			function cancel() {
				location.href = "${pageContext.request.contextPath}/selectList.fb?community=2";
			}
		</script>
	</c:if>
	<c:if test="${ empty sessionScope.loginMember }">
		<c:set var="message" value="잘못된 경로로 접근하셨습니다." scope="request" />
		<jsp:forward page="../../common/errorPage.jsp" />
	</c:if>
</body>
</html>