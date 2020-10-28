<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FaQ 목록</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ applicationScope.contextPath }/resources/css/boardCSS/FaQMain.css">

</head>

<!-- ${sessionScope.loginMember.deptCode} -->
<body>
	<jsp:include page="/views/common/menubar.jsp"/>
	<div class="boardWrap">

		<h2>FAQ</h2>

			<div id="searchForm">
			<input type="radio" name="list" value="date" id="date1" onclick="newList();"><label for="date1">최신순</label>
			<input type="radio" name="list" value="count" id="count1" onclick="seeList();"><label for="count1">조회순</label>
				
		<form action="${ applicationScope.contextPath }/search.faq" method="POST">
				<select id="searchType" name="searchType">
					<option value="title">제목</option>
					<option value="contents">내용</option>
				</select> 
				<input type="text" id="textType" placeholder="검색어 입력" name="textType"> 
				<input type="submit" value="검색" id="searchBtn" style="border-radius:5px;">
				</form>
			</div>
			<br>
			<table class="boardSt" id="FaqList">
			<thead>
				<tr class="mainTr">
				
					<th>글번호</th>
					<th colspan="3">제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
					<%-- <c:if test="${sessionScope.loginMember.deptCode eq 'HOF01'}">
					<th>수정/삭제</th>
					</c:if> --%>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="i" items="${ requestScope.list }">
				<tr class="bg">
				<td style="display:none"><c:out value="${ i.faqCode }"/></td>
					<td><c:out value="${ i.fId }"></c:out></td>
					<td colspan="3"><c:out value="${ i.faqTitle }"></c:out></td>
					<td><c:out value="${ i.memberId }"></c:out></td>
					<td><c:out value="${ i.faqDate }"></c:out></td>
					<td><c:out value="${ i.seeNum }"></c:out></td>
					<%-- <c:if test="${sessionScope.loginMember.deptCode eq 'HOF01'}">
					<td align="center">
						<input type="button" id="miniBtn" value="수정" name="updateB" value="${ i.faqCode }" onclick="update();">
						<input type="button" id="miniBtn" value="삭제" name="delB" value="${ i.faqCode }">
					</td>
					</c:if> --%>
				</tr>
				</c:forEach>
				</tbody>

			</table>
		<%-- <div class="pagingArea" align="center">
		<button onclick="location.href='${ applicationScope.contextPath }/selectList.faq?currentPage=1'"><<</button>
		
		
		<c:if test="${ requestScope.pi.currentPage <= 1 }">
			<button disabled><</button>
		</c:if>
		
		<c:if test="${ requestScope.pi.currentPage > 1 }">
			<button onclick="location.href='${ applicationScope.contextPath }/selectList.faq?currentPage=<c:out value="${ requestScope.pi.currentPage - 1 }" />'"><</button>
		</c:if>
		
		<c:forEach var="p" begin="${ requestScope.pi.startPage }" end="${ requestScope.pi.endPage }" step="1">
			<c:if test="${ requestScope.pi.currentPage eq p }">
				<button disabled><c:out value="${ p }"/></button>
			</c:if>
			<c:if test="${ requestScope.pi.currentPage ne p }">
				<button onclick="location.href='${ applicationScope.contextPath }/selectList.faq?currentPage=<c:out value="${ p }" />'"><c:out value="${ p }" /></button>
			</c:if>
		</c:forEach>
		
		<c:if test="${ requestScope.pi.currentPage >= requestScope.pi.maxPage }">
			<button disabled>></button>
		</c:if>
		
		<c:if test="${ requestScope.pi.currentPage < requestScope.pi.maxPage }">
			<button onclick="location.href='${ applicationScope.contextPath }/selectList.faq?currentPage=<c:out value="${ requestScope.pi.currentPage + 1 }" />'">></button>
		</c:if>
		
		<button onclick="location.href='${ applicationScope.contextPath }/selectList.faq?currentPage=<c:out value="${ requestScope.pi.maxPage }" />'">>></button>
	 	</div> --%>
		<c:if test="${sessionScope.loginMember.deptCode eq 'HOF01'}">
		<button id="inputBoard"
			onclick="location.href='/st4/views/board/FaQ/FaQInsertForm.jsp'">글쓰기</button>
			</c:if>

 <%-- <input type="button" class="Colorbtn0" onClick="location.href = '${ applicationScope.contextPath }/faqList.faq'" value="새로고침" >  --%>
	</div>
	<script>
	$(function(){
		$("#FaqList td").mouseenter(function(){
			$(this).parent().css({"background":"white", "cursor":"pointer"});
		}).mouseout(function(){
			$(this).parent().css({"background":"#EBEBEB"});
		}).click(function(){
			var num = $(this).parent().children().eq(0).text();
			
			location.href = "${ applicationScope.contextPath }/selectOne.faq?num=" + num;
		})
	});
	
	function newList(){
		location.href = "${ applicationScope.contextPath }/faqList.faq";
	};
	
	function seeList(){
		location.href = "${ applicationScope.contextPath }/faqSeeList.faq";
	};
	
/* 	function update(){
		$("input[name=updateB]").click(function(){
			var num = $(this).parent().children().eq(0).text();
			//console.log(num);
			
			location.href = "${ applicationScope.contextPath }/faqUpdate.faq?num=" + num;
		})
	}; */
	</script>
	
</body>
</html>