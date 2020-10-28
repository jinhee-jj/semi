<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String selectSearch = request.getParameter("selectSearch");
	System.out.println("jsp에서 확인 " + selectSearch);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
<link href="<%= request.getContextPath() %>/resources/css/notice/noticeList.css" rel="stylesheet" type="text/css">

</head>
<body>
	<jsp:include page="../../common/menubar.jsp"/>
	<div id="wrapper">
		<div id="noticeListSection">
			<h2>공지사항</h2>
			<form action="${ applicationScope.contextPath }/selectSearchList.no" method="get">
			<c:choose>
				<c:when test="${ requestScope.selectSearch eq 'mostViewed' }">
					<input type="radio" name="selectSearch" id="latest" value="latest" ><label for="latest">최신순</label>
					<input type="radio" name="selectSearch" id="mostViewed" value="mostViewed" checked><label for="mostViewed">조회순</label>
				</c:when> 
			    <c:when test="${(empty requesetScope.selectSearch) || requestScope.selectSearch eq 'latest' }" > 
					<input type="radio" name="selectSearch" id="latest" value="latest" checked><label for="latest">최신순</label>
					<input type="radio" name="selectSearch" id="mostViewed" value="mostViewed"><label for="mostViewed">조회순</label>
				</c:when>			
			</c:choose>		
				
				<select name="searchCondition">
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
				
				<input type="search" name="searchValue" placeholder="검색어 입력">
				<input style="border-radius:5px;" type="submit" name="search" value="검색">
			</form>
			<br>
			<table align="center" id="listArea" class="table-bottom">
				<tr>
					<th>글번호</th>
					<th colspan="3">제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
					
					<c:if test="${ sessionScope.loginMember.memberDeptCode eq '본사'}">
						<th>수정/삭제</th>
					</c:if>
				</tr>
				<c:choose>
					<c:when test="${ not empty requestScope.list}">
						<c:forEach var="l" items="${ requestScope.list }">
							<tr>
								<td><c:out value="${ l.nno }"/></td>
								<td colspan="3" class="nTitle"><c:out value="${ l.nTitle }"/></td>
								<td><c:out value="${ l.nWriter }"/></td>
								<td><c:out value="${ l.nDate }"/></td>
								<td><c:out value="${ l.nCount }"/></td>
								<c:if test="${ sessionScope.loginMember.memberDeptCode eq '본사'}">
									<td align="center">
									<button id="editBtn"  onclick="location.href='${applicationScope.contextPath}/selectUpdate.no?nno=${ l.nno }'">수정</button>
									<button id="deleteBtn" onclick="location.href='${applicationScope.contextPath}/delete.no?nno=<c:out value="${ l.nno }"/>'">삭제</button>		
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>					
						<c:forEach var="l" items="${ requestScope.listS }">
							<tr>
								<td><c:out value="${ l.nno }"/></td>
								<td colspan="3" class="nTitle"><c:out value="${ l.nTitle }"/></td>
								<td><c:out value="${ l.nWriter }"/></td>
								<td><c:out value="${ l.nDate }"/></td>
								<td><c:out value="${ l.nCount }"/></td>
								<c:if test="${ sessionScope.loginMember.memberDeptCode eq '본사'}">
									<td align="center">
									<button id="editBtn"  onclick="location.href='${applicationScope.contextPath}/selectUpdate.no?nno=${ l.nno }'">수정</button>
									<button id="deleteBtn" onclick="location.href='${applicationScope.contextPath}/delete.no?nno=<c:out value="${ l.nno }"/>'">삭제</button>		
									</td>
								</c:if>
							</tr>
						</c:forEach>	
					</c:otherwise>
				</c:choose>
		</table>
		
		<c:if test="${ sessionScope.loginMember.memberDeptCode eq '본사'}">
			<div class="btn" align="center">
				<button onclick="location.href='${applicationScope.contextPath }/views/board/notice/noticeInsertForm.jsp'" id="writeBtn">글쓰기</button>
			</div>
		</c:if>
		<br>
		
		<div class="paging-area" align="center">
		
		<c:choose>
			<c:when test="${ not empty requestScope.list}">
			
				<button onclick="location.href='${ applicationScope.contextPath }/selectList.no?currentPage=1'"><<</button>
			
				<c:if test="${ requestScope.pi.currentPage <= 1 }">
					<button disabled><</button>
				</c:if>
				
				<c:if test="${ requestScope.pi.currentPage > 1 }" >
					<button onclick="location.href='${applicationScope.contextPath}/selectList.no?currentPage=<c:out value="${ requestScope.pi.currentPage - 1 }"/>'"><</button>
				</c:if>
				
				<c:forEach var="p" begin="${ requestScope.pi.startPage }" end="${ requestScope.pi.endPage }" step="1">
					<c:if test="${ requestScope.pi.currentPage eq p }">
						<button disabled><c:out value="${ p }"/></button>
					</c:if>
					
					<c:if test="${ requestScope.pi.currentPage ne p }">
						<button onclick="location.href='${applicationScope.contextPath}/selectList.no?currentPage=<c:out value="${ p }"/>'"><c:out value="${ p }"/></button>
					</c:if>
				</c:forEach>
			
			
				<c:if test="${ requestScope.pi.currentPage >= requestScope.pi.maxPage }">
					<button disabled>></button>
				</c:if>
				
				<c:if test="${ requestScope.pi.currentPage < requestScope.pi.maxPage }">
					<button onclick="location.href='${applicationScope.contextPath}/selectList.no?currentPage=<c:out value="${requestScope.pi.currentPage + 1 }"/>'">></button>
				</c:if>
				<button onclick="location.href='${applicationScope.contextPath}/selectList.no?currentPage=<c:out value="${ requestScope.pi.maxPage}"/>'">>></button>
		
			</c:when>
			<c:otherwise>
				<button onclick="location.href='${ applicationScope.contextPath }/selectSearchList.no?currentPage=1'"><<</button>
			
				<c:if test="${ requestScope.piS.currentPage <= 1 }">
					<button disabled><</button>
				</c:if>
				
				<c:if test="${ requestScope.piS.currentPage > 1 }" >
					<button onclick="location.href='${applicationScope.contextPath}/selectSearchList.no?currentPage=<c:out value="${ requestScope.pi.currentPage - 1 }"/>'"><</button>
				</c:if>
				
				<c:forEach var="p" begin="${ requestScope.piS.startPage }" end="${ requestScope.piS.endPage }" step="1">
					<c:if test="${ requestScope.pi.currentPage eq p }">
						<button disabled><c:out value="${ p }"/></button>
					</c:if>
					
					<c:if test="${ requestScope.piS.currentPage ne p }">
						<button onclick="location.href='${applicationScope.contextPath}/selectSearchList.no?currentPage=<c:out value="${ p }"/>'"><c:out value="${ p }"/></button>
					</c:if>
				</c:forEach>
			
			
				<c:if test="${ requestScope.piS.currentPage >= requestScope.piS.maxPage }">
					<button disabled>></button>
				</c:if>
				
				<c:if test="${ requestScope.piS.currentPage < requestScope.piS.maxPage }">
					<button onclick="location.href='${applicationScope.contextPath}/selectSearchList.no?currentPage=<c:out value="${requestScope.piS.currentPage + 1 }"/>'">></button>
				</c:if>
				<button onclick="location.href='${applicationScope.contextPath}/selectSearchList.no?currentPage=<c:out value="${ requestScope.piS.maxPage}"/>'">>></button>
			
			</c:otherwise>
		</c:choose>
		</div>	
		</div>	
		
	</div>
	
	<script>
		$(".nTitle").click(function(){
			var nno = $(this).parent().children().eq(0).text();
			console.log(nno);
			
			location.href="${applicationScope.contextPath}/selectOne.no?nno=" + nno;		
			
		});
		
	
		
		$("input[type=radio]").click(function(){
			
			console.log("라디오 클릭");
			var selectSearch =  $(this).val();
			//console.log(selectSearch);
			
			location.href="${applicationScope.contextPath}/selectList.no?selectSearch=" + selectSearch;
			
			
		});
	</script>
</body>
</html>