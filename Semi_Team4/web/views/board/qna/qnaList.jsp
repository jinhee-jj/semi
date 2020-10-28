<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import=" java.util.ArrayList, com.kh.cool.qna.model.vo.QnaBoard, com.kh.cool.qna.model.vo.PageInfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() %>/resources/css/qna/qnaCss.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<title>커뮤니티 > 질의응답</title>
</head>
<body>
	<jsp:include page="/views/common/menubar.jsp"></jsp:include>

	<div class="outer">
		<div class="boardWrap">
			<h2>질의응답</h2>
			<div class="top">
				<input hidden name="searched" value="${requestScope.isSearched }">
				<form id="conditions" action="${applicationScope.contextPath }/search.qa" method="post">
				
					<!-- currentPage parameter 전달을 위한 임시변수 -->
					<input hidden name="currentPage" value="1">
					<input hidden name="qnaList" value="${requestScope.qnaList }">
				
					<c:if test="${ empty requestScope.sortCondition }">
						<label><input type="radio" name="sortCondition" value="lastest" checked>최신순</label>
						<label><input type="radio" name="sortCondition" value="popular">조회순</label>
					</c:if>
					<c:if test="${ requestScope.sortCondition eq 'lastest'}">
						<label><input type="radio" name="sortCondition" value="lastest" checked>최신순</label>
						<label><input type="radio" name="sortCondition" value="popular">조회순</label>
					</c:if>
					<c:if test="${ requestScope.sortCondition eq 'popular' }">
						<label><input type="radio" name="sortCondition" value="lastest">최신순</label>
						<label><input type="radio" name="sortCondition" value="popular" checked>조회순</label>
					</c:if>
					
					<select name="searchCondition">
						<c:if test="${ empty requestScope.sortCondition }">
							<option value="title" selected>제목</option>
							<option value="writer">작성자</option>
							<option value="content">내용</option>
						</c:if>
					
						<c:if test="${ !empty requestScope.sortCondition }">
							<c:if test="${requestScope.searchCondition eq title}">
								<option value="title" selected>제목</option>
							</c:if>
							<c:if test="${requestScope.searchCondition ne title}">
								<option value="title">제목</option>
							</c:if>
							<c:if test="${requestScope.searchCondition eq title}">
								<option value="writer" selected>작성자</option>
							</c:if>
							<c:if test="${requestScope.searchCondition ne title}">
								<option value="writer">작성자</option>
							</c:if>
							<c:if test="${requestScope.searchCondition eq content}">
								<option value="content" selected>내용</option>
							</c:if>											
							<c:if test="${requestScope.searchCondition ne content}">
								<option value="content">내용</option>
							</c:if>											
						</c:if>
								
					
					</select>
					<c:if test="${ !empty requestScope.searchText }">
						<input type="search" name="searchText" value="${requestScope.searchText}">
					</c:if>
					
					<c:if test="${ empty requestScope.searchText }">
						<input type="search" name="searchText" placeholder="검색어 입력">
					</c:if>					
					
					<input style="border-radius:5px;" type="submit" name="search" value="검색">
				</form>
			</div>
			<br>
			<table id="listTable">
				<tr id="titleLine">
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
				
				<c:forEach var="item" items="${ requestScope.qnaList }" end="10">
					<tr>
	 					<input type="hidden" value="<c:out value="${ item.boardNo }"/>">		<!-- Board_NO: 전체 게시물 번호 -->
						<td><c:out value="${ item.boardNum }"/></td>
						<td><c:out value="${ item.boardTitle }"/></td>
						<td><c:out value="${ item.boardWriterId }"/></td>
						<td><c:out value="${ item.boardWriteDate }"/></td>
						<td><c:out value="${ item.boardCount }"/></td>
					</tr>
				</c:forEach>
			</table>
			
			<br>
			<div class="viewPages">
				<!-- 페이징 처리 시작 -->
				
				<div class="pagingArea" align="center">
					<!-- 처음으로 -->
					<button onclick="goTo(1)"> << </button>
					
					<!-- 이전페이지 -->
					<c:if test="${requestScope.pi.currentPage <= 1 }">
						<button disabled> < </button>
					</c:if>
					
					<c:if test="${requestScope.pi.currentPage > 1 }">
						<button onclick="goTo(<c:out value="${ requestScope.pi.currentPage - 1 }"/>)"> < </button>
					</c:if>
					
					<!-- 묶음 페이지 시작 -->
					<c:forEach var="page" begin="${requestScope.pi.startPage }"
								end="${requestScope.pi.endPage }" step="1">
						
						<!-- 현제 페이지에 해당하는 버튼 비활성화 -->
						<c:if test="${requestScope.pi.currentPage eq page }">
							<button disabled>
								<c:out value="${ page }"/>
							</button>
						</c:if>
						
						<c:if test="${requestScope.pi.currentPage ne page }">
							<button onclick="goTo(<c:out value="${ page }"/>)">
								<c:out value="${ page }"/>
							</button>
						</c:if>
					</c:forEach>
					<!-- 묶음 페이지 끝 -->
					
					<!-- 다음 페이지 -->
					<c:if test="${requestScope.pi.currentPage >= requestScope.pi.maxPage }">
						<button disabled> > </button>
					</c:if>
					
					<c:if test="${requestScope.pi.currentPage < requestScope.pi.maxPage }">
						<button onclick="goTo(<c:out value="${ requestScope.pi.currentPage + 1 }"/>)"> > </button>
					</c:if>								
					
					<!-- 마지막 페이지로 -->
 					<button onclick="goTo(<c:out value="${ requestScope.pi.maxPage }"/>);"> >> </button>
				</div>	
				<!-- 페이징 처리 끝 -->
					
				<div id="newQna">
					<button value="newQna" onclick="location.href='${applicationScope.contextPath}/views/board/qna/qnaInsertForm.jsp'">글쓰기</button>
				</div>
			</div>
		</div>
	</div>

	<script>
	$("#listTable td").mouseenter(function(){
		$(this).parent().css({"background":"lightgrey", "cursor":"pointer"});
		
	}).mouseout(function(){
		$(this).parent().css({"background":"#EBEBEB"});
		
	}).click(function(){
		var bNo = $(this).parent().children("input").val();
		
		location.href="${applicationScope.contextPath}/selectOne.qa?bNo=" + bNo;
	});
	
	function goTo(page){
		
		$("input[name=currentPage]").val(page);
		
		$("form").submit();
	};
	
	</script>

</body>
</html>

