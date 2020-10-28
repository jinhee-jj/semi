<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>자유게시판</title>
<!--  <link rel="stylesheet" type="text/css" href="/resources/css/freeBoard/freeBoardList.css"> -->
<link rel="stylesheet" type="text/css" href="${ applicationScope.contextPath }/resources/css/freeBoard/freeBoardList.css">
</head>
<body>
	<jsp:include page="/views/common/menubar.jsp" />

	<div class="outer">
		<br>
		<h2 align="left" id="title">자유게시판</h2>
		<br>
		<div class="search-area" align="left">
		
			<div class="search123">
				<input type="radio" name="radioSearch" value="news" class="RBtn" checked="checked">최신순
				<input type="radio" name="radioSearch" value="count" class="RBtn">조회순
				<select id="searchCondition" name="searchCondition">
					<option value="title">제목</option>
					<option value="writer">작성자</option>
					<option value="content">내용</option>
				</select>
				<input type="search" id="searchCondition2" name="searchArea" placeholder="검색어를 입력하세요">
				<button type="submit" id="searchCondition3" style="border-radius:5px;" >검색</button>
			</div>
		</div>
		<br>
		<div class="table-area">
			<table align="center" id="listArea">
				<tr>
					<th>글번호</th>
					<th colspan="3">글제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
					<th>수정/삭제</th>
				</tr>

 				<c:forEach var="n" items="${ requestScope.list }">
					<tr>
						<input type="hidden" value="<c:out value="${ n.boardNo }"/>">
						<td><c:out value="${ n.boardNum }"/></td>
						<td colspan="3"><c:out value="${ n.boardTitle }"/></td>
						<td><c:out value="${ n.boardWriterId }"/></td>
						<td><c:out value="${ n.boardWriteDate }"/></td>
						<td><c:out value="${ n.boardCount }"/></td>
						
						<c:if test="${ sessionScope.loginMember.memberId eq n.boardWriterId }">
							<td onclick="event.cancelBubble=true"><button class="editBtn">수정</button>
							<button class="deleteBtn">삭제</button></td>
						</c:if>

					</tr>
					
				</c:forEach>
		
			</table>
			<div class="pager">
   				<button onclick="location.href='${applicationScope.contextPath}/selectList.fb?community=2&currentPage=1'"><<</button>
   				
    			<c:if test="${ requestScope.pi.currentPage <= 1 }">
    				<button disabled><</button>
    			</c:if>
    			<c:if test="${ requestScope.pi.currentPage > 1 }">
    				<button onclick="location.href='${applicationScope.contextPath}/selectList.fb?community=2&currentPage=<c:out value="${ requestScope.pi.currentPage - 1 }"/>'"><</button>
    			</c:if>
    			
    			<c:forEach var="p" begin="${ requestScope.pi.startPage }" end="${ requestScope.pi.endPage }" step="1">
    				<c:if test="${ requestScope.pi.currentPage eq p }">
    					<button disabled><c:out value="${ p }"/></button>
    				</c:if>
    				<c:if test="${ requestScope.pi.currentPage ne p }">
    					<button onclick="location.href='${applicationScope.contextPath}/selectList.fb?community=2&currentPage=<c:out value="${ p }"/>'"><c:out value="${ p }"/></button>
    				</c:if>
    			</c:forEach>
    			
    			<c:if test="${ requestScope.pi.currentPage >= requestScope.pi.maxPage }">
    				<button disabled>></button>
    			</c:if>
    			<c:if test="${ requestScope.pi.currentPage < requestScope.pi.maxPage }">
    				<button onclick="location.href='${applicationScope.contextPath}/selectList.fb?community=2&currentPage=<c:out value="${ requestScope.pi.currentPage + 1 }"/>'">></button>
    			</c:if>
    			
    			<button onclick="location.href='${applicationScope.contextPath}/selectList.fb?community=2&currentPage=<c:out value="${ requestScope.pi.maxPage }"/>'">>></button>
			</div>  

 <button id="insert" onclick="location.href='${applicationScope.contextPath}/views/board/freeBoard/freeBoardInsertForm.jsp'" >글쓰기</button>   
		</div>
	</div>
	<script>
	/* lt(5) */
		$(function() {
			$("#listArea tr").mouseenter(function(){
				$(this).parent().css({/* "background":"lightgray", "cursor":"pointer" */});
			}).mouseout(function(){
				$(this).parent().css(/* {"background":"white"} */);
			}).click(function(){
				var num = $(this).children("input").val();
				
				
				
				
				location.href = "${ pageContext.request.contextPath }/selectOne.fb?community=2&num=" + num;
				/* pageContext.request.contextPath */
			})
		});
		
 		$(function() {
			$(".editBtn").mouseenter(function(){
				$(this).parent().css({ /* "background":"lightgray", "cursor":"pointer" */ });
			}).mouseout(function(){
				$(this).parent().css( {/* "background":"white" */} );
			}).click(function(){
				var num = $(this).parent().parent().children("input").val();
				
				
				
				location.href = "${ pageContext.request.contextPath }/updateOne.fb?community=2&num="+num;
			})
		});
		
 		$(function() {
			$(".deleteBtn").mouseenter(function(){
				$(this).parent().css({ /* "background":"yellow", "cursor":"pointer" */});
			}).mouseout(function(){
				$(this).parent().css( {/* "background":"white" */} );
			}).click(function(){
				var num = $(this).parent().parent().children("input").val();
				
				
				
				
				location.href = "${ pageContext.request.contextPath }/delete.fb?community=2&num=" + num;
			})
		});
		
		$(function() {
			$("#searchCondition3").click(function(){
				
				var radio = $('input[name="radioSearch"]:checked').val();
				var select = $("#searchCondition option:selected").val();
				var search = $("#searchCondition2").val();
				
				location.href = "${ pageContext.request.contextPath }/search.fb?radio="+radio+"&select="+select+"&search="+search;
			})
		});
	</script>
</body>
</html>