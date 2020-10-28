<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/resources/css/notice/noticeUpdateForm.css" rel="stylesheet" type="text/css">

</head>
<body>
	<jsp:include page="../../common/menubar.jsp"/>
	<div id="wrapper">
			<br>
			<h2 align="left">공지 사항 수정</h2>
			<br>
			<div class="table-area">
				<form action="${ applicationScope.contextPath }/update.no" method="post">
					<table id="writeTable" align="center">
						<tr>
							<td>제목</td>
							<td><input type="text" class="writeText" size="108" name="title" value="<c:out value='${ requestScope.notice.nTitle }'/>">
								<input type="hidden" name="nno" value="<c:out value="${ requestScope.notice.nno }"/>">
							</td>
								
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="content" rows="30%" style="resize:none;"><c:out value='${ requestScope.notice.nContent }'/></textarea></td>		
						</tr>
						<!-- 수정... -->
						<%-- <c:forEach var="at" items="${ requestScope.atList }">
							<tr align="left">
								<td>첨부</td>
								<td>
									<c:out value="${ at.originName}"/>
									<button type="button" onclick="location.href='${ applicationScope.contextPath}/deleteAttach.no?num=<c:out value="${ at.attNo }"/>'">삭제</button>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td></td>
							<td>
								<input type=text name="attachedFileName" readonly>
								<input type="file" name="noticeFile">				
							</td>
						</tr> --%>
					</table>
				
					<br>
					<div class="buttons" align="center" align="right">
						<button type="reset" class="btn" onclick="location.href='${applicationScope.contextPath }/selectOne.no?nno=<c:out value="${ requestScope.notice.nno }"/>'">취소</button>
						<button type="submit" class="btn">수정</button>
					</div>
				</form>
			</div>
	</div>
</body>
</html>