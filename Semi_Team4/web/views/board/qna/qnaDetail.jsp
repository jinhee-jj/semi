<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() %>/resources/css/qna/qnaInsertFormCss.css" rel="stylesheet" type="text/css">
<!-- qnaInsertForm의 css과 동일한 css파일 사용 --> 

<title>커뮤니티 > 질의응답</title>
</head>
<body>
	<jsp:include page="/views/common/menubar.jsp"></jsp:include>
	<div class="outer">
		<div class="boardWrap">
			<c:if test="${ !empty sessionScope.loginMember }">
				<br>
				<h2>질의응답 내용</h2>
				<br>
				<form action="${applicationScope.contextPath }/modify.qa" method="post">
					<table class="boardTable">
						<tr>
							<input hidden name="bNo" value="<c:out value="${requestScope.qnaBoard.boardNo}"/>">
							<td><label>제목</label></td>
							<td class="inputArea"><input type="text" name="title" id="title" readonly
								value="<c:out value='${requestScope.qnaBoard.boardTitle }'/>"></td>
						</tr>
						
						<tr>
							<td><label>내용</label></td>
							<td class="inputArea">
								<textarea name="content" id="content" rows="15%" readonly><c:out value="${requestScope.qnaBoard.boardContent }"/></textarea>
							</td>
						</tr>
						
						<tr>
							<td><label>첨부</label></td>
							<td id="attached">
								<c:if test="${requestScope.fileList[0].attFileOriginName ne ' ' || empty requestScope.fileList[0].attFileOriginName }">
									<button onclick="location.href='${applicationScope.contextPath}/downloadAttachment.qa?num=<c:out value="${requestScope.fileList[0].attNo }"/> '">
									<c:out value="${requestScope.fileList[0].attFileOriginName }"/></button>
								</c:if>
								<c:if test="${requestScope.fileList[1].attFileOriginName ne ' ' || empty requestScope.fileList[1].attFileOriginName}">
									<button onclick="location.href='${applicationScope.contextPath}/downloadAttachment.qa?num=<c:out value="${requestScope.fileList[1].attNo }"/> '">
									<c:out value="${requestScope.fileList[1].attFileOriginName }"/></button>
								</c:if>
							</td>
						</tr>
					</table>
					<br>
					<div class="buttons">
						
						<button type="submit" class="btn modify" onclick="submit();">수정완료</button>
						<button class="btn modify" onclick="location.href='${applicationScope.contextPath}/selectOne.qa?bNo=<c:out value="${requestScope.qnaBoard.boardNo }"/>'">돌아가기</button>
						<c:if test="${ sessionScope.loginMember.memberId eq requestScope.qnaBoard.boardWriterId || sessionScope.loginMember.memberDeptCode eq '본사' }">
							<button class="btn detail" onclick="modifyQna();">수정</button>
							<button class="btn detail" onclick="location.href='${applicationScope.contextPath}/delete.qa?bNo=<c:out value="${requestScope.qnaBoard.boardNo }"/>'">삭제</button>
						</c:if>
						<button class="btn detail" onclick="location.href='${ applicationScope.contextPath }/selectList.qa'">목록으로</button>
					</div>
					<br>
					<div id="commentView">
					<table class="boardTable" id="commentTable" >
						<thead>
							<th><label><c:out value="${sessionScope.loginMember.memberId }"/></label></th>
							<th colspan="3" class="inputArea">
								<textarea name="commentContent" id="commentContent" rows="3%" placeholder="댓글입력"></textarea>
							</th>
						</thead>
						<thead>
							<th></th>
							<th colspan="3" class="inputArea" id="commentBtn">
								<div id="commentBtnInput">댓글쓰기</div>
								<br>
							</th>
						</thead>
						<thead>
							<th> </th>
							<th>id</th>
							<th id="commentContentView">내용</th>
							<th>날짜</th>
						</thead>
							<tr></tr>
					</table>
					</div>
					<br><br>
				</form>
				
				<script>
					$(function() {
						//수정버튼 숨기기
						$(".modify").hide();
						
						//댓글출력 ajax
						var commentWriterId = '<c:out value="${sessionScope.loginMember.memberId}"/>';
						var boardNo = '<c:out value="${requestScope.qnaBoard.boardNo}"/>';
						var commentContent = $("#commentContent").val();
						var communityCode = '<c:out value="${requestScope.qnaBoard.communityCode}"/>'
						
						$.ajax({
							url: "${applicationScope.contextPath}/selectComment.qa",
							data: {commentWriterId : commentWriterId, boardNo : boardNo,
									commentContent : commentContent, communityCode: communityCode},
							type: "post",
							success: function(data){
								
								var $commentTable = $("#commentTable tbody");
								$commentTable.html('');
								
								for(var key in data){
									var $tr = $("<tr>");
									var $Td = $("<td>").text(" ");
									var $cWriterTd = $("<td>").text(data[key].commentWriterId);
									var $cContentTd = $("<td>").text(data[key].commentContent);
									var $cWriteDateTd = $("<td>").text(data[key].commentWriteDate);
									
									$tr.append($Td);
									$tr.append($cWriterTd);
									$tr.append($cContentTd);
									$tr.append($cWriteDateTd);
									
									$commentTable.append($tr);
								}
							}, eerror: function(err){
								console.log("댓글 불러오기 실패!");
							}
						});
					});
				
					function modifyQna() {
						$(".detail").hide();
						$(".modify").show();
						
						$("h2").text('질의응답 수정');
						$(".inputArea input[type=text]").prop('readonly', false)
						$(".inputArea input[type=text]").prop('autofocus', true);
						$(".inputArea textArea").prop('readonly', false);
					};
					
					//버튼 교체 시 자동 submit을 방지
					var submitAction = function(e) {
						e.preventDefault();
					    e.stopPropagation();
					};
					$('form').bind('submit', submitAction);
					
					//댓글처리 영역
					$("#commentBtnInput").mouseenter(function(){
						$(this).css({"cursor":"pointer", "outline":"inset"});
						
					}).mouseout(function(){
						$(this).css({ "outline":"none"});
						
					}).click(function(){
						var commentWriterId = '<c:out value="${sessionScope.loginMember.memberId}"/>';
						var boardNo = '<c:out value="${requestScope.qnaBoard.boardNo}"/>';
						var commentContent = $("#commentContent").val();
						var communityCode = '<c:out value="${requestScope.qnaBoard.communityCode}"/>'
						
						$.ajax({
							url: "${applicationScope.contextPath}/insertComment.qa",
							data: {commentWriterId : commentWriterId, boardNo : boardNo,
									commentContent : commentContent, communityCode: communityCode},
							type: "post",
							success: function(data){
								
								var $commentTable = $("#commentTable tbody");
								$commentTable.html('');
								
								for(var key in data){
									var $tr = $("<tr>");
									var $Td = $("<td>").text(" ");
									var $cWriterTd = $("<td>").text(data[key].commentWriterId);
									var $cContentTd = $("<td>").text(data[key].commentContent);
									var $cWriteDateTd = $("<td>").text(data[key].commentWriteDate);
									
									$tr.append($Td);
									$tr.append($cWriterTd);
									$tr.append($cContentTd);
									$tr.append($cWriteDateTd);
									
									$commentTable.append($tr);
								}
							}, eerror: function(err){
								console.log("댓글 작성 실패!");
							}
						});
					});
					
				</script>
			</c:if>
		</div>
	</div>

		
	<c:if test="${empty sessionScope.loginMember }">
		<c:set var="message" value="잘못된 경로로 접근하셨습니다."/>
		<jsp:forward page="../../common/errorPage.jsp"/>
	</c:if>
	
	<c:if test="${ sessionScope.loginMember.memberId ne requestScope.board.boardWriterId }}">
		<c:set var="message" value="해당 게시물에 접근 권한이 없습니다."/>
		<jsp:forward page="../../common/errorPage.jsp"/>
	</c:if>
	
</body>
</html>
