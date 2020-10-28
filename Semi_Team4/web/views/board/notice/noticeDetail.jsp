<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/resources/css/notice/noticeDetail.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../../common/menubar.jsp"/>
	
	<!-- 본사계정에만 보이는 페이지 -->
	<div id="wrapper">
			<div class="boardWrap">
			<h2 align="left">공지 사항</h2>
			<div class="table-area">
				<br>
					<table id="writeTable" align="center">
						<tr>
							<td>제목</td>
							<td colspan="3"><input type="text" size="111" name="title" value="<c:out value='${ requestScope.notice.nTitle }'/>" readonly></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td colspan="3"><input type="text" size="10" name="writer"  value="<c:out value='${ requestScope.notice.nWriter }'/>" readonly></td>
						</tr>
						<tr>
							<td>내용</td>	
						</tr>
						<tr>
							<td colspan="4">
								<textarea readonly name="content" cols="120%" rows="25%" style="resize:none;"><c:out value='${ requestScope.notice.nContent }'/></textarea>
							</td>
						</tr>
						<c:forEach var="at" items="${ requestScope.atList }">
							<tr>
								<td>첨부</td>
								<td><c:out value="${ at.originName}"/>
								<button class="downloadBtn" onclick="location.href='${ applicationScope.contextPath }/download.no?num=<c:out value="${ at.attNo }"/>'">다운로드</button>
								</td>
							</tr>
						</c:forEach>
					</table>
					<br>
					
					<!-- 본사 계정일 경우 보이게 하기 -->
					<div align="center" align="right">
						<c:if test="${ sessionScope.loginMember.memberDeptCode eq '본사'}">
							<c:set var="nno" scope="request" value="${ requestScope.notice.nno }"/>
							<button class="editBtn" onclick="location.href='${applicationScope.contextPath}/selectUpdate.no?nno=${ nno }'">수정하기</button>
						</c:if>
						<button class="listBtn" onclick="location.href='${applicationScope.contextPath}/selectList.no'">목록으로</button>
					</div>
			</div>
			</div>
	</div>

</body>
</html>