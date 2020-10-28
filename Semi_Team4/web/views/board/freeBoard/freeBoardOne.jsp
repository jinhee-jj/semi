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
			<div class="wrap">
			<br>
			<h2 align="left" id = "title">자유게시판 보기</h2>
			<div class="table-area">
				<form action="${ applicationScope.contextPath }/insert.no" method="post">
					<table align="center">
						<tr>
							<td>제목</td>
							     <td colspan="4"><input type="text" size="99%" name="title" value="${ requestScope.board.boardTitle } " readonly></td>
							<%-- <td colspan="4"><span><c:out value="${ requestScope.board.boardTitle }"/></span></td> --%>						
						</tr>
						
						<tr>
							<td>내용</td>
							<td colspan="4">
								<textarea cols="100%" rows="25%" class="freeTextarea" readonly>${ requestScope.board.boardContent }</textarea>
								
							</td>
						</tr>
					</table>
					<br>
					<div align="right" class="bottm">
						<button type="button" class="Btn" onclick="goList()">목록</button>
					</div>
					
				</form>
			</div>
			<div class="comment">
			<br>
					<table class="review">
					<thead>
						<tr>
						<td>
							<div>
								${ sessionScope.loginMember.memberId }
							</div>
						</td>
						<td align="center">
							<div>
								<textarea name="comment_content" rows="4%" cols="100%" class="commentInput"></textarea>
							</div>
						</td>
						<td>
							<!-- <div id="btn" onclick="writeCmt()">
								<p>[댓글등록]</p>
							</div> -->
							<button onclick="writeCmt()" class="Btn bt">[댓글등록]</button>
						</td>
						</tr>
						</thead>
						<tbody id="replySelectArea">
							<tr class="repleArea">
						<!-- <td class="nick_name">작성자</td>
						<td align="center">
								<textarea name="comment_content" rows="4%" cols="100%" class="commentInput none" readonly>내용내용</textarea>
						</td>
						<td>작성일자</td> -->
							</tr>
						</tbody>
					</table>
					<br><br>
					<script>
						function goList() {
							location.href = "${pageContext.request.contextPath}/selectList.fb?community=2";
						}
						
						function writeCmt() {
							var writer = '<c:out value="${sessionScope.loginMember.memberId}"/>';
							var bno = '<c:out value="${requestScope.board.boardNo}"/>';
							var content = $(".commentInput").val();
							
							console.log(writer);
							console.log(bno);
							console.log(content);
							
							$.ajax({
								url: "${applicationScope.contextPath}/insertReply.fb",
								data : {writer: writer, content: content, bno: bno},
								type: "post",
								success: function(data) {
									
									var $replySelectTable = $("#replySelectArea");
									$replySelectTable.html('');
									
									for(var key in data) {
										var $tr = $("<tr>");
										var $writerIdTd = $("<td>").text(data[key].commentWriterId);
										var $contentTd = $("<td>").text(data[key].commentContent);
										var $dateTd = $("<td>").text(data[key].commentWriteDate);
										
										$tr.append($writerIdTd);
										$tr.append($contentTd);
										$tr.append($dateTd);
										
										$replySelectTable.append($tr);
									}
								},
								error : function(err) {
									alert("댓글 작성 실패!!!");
								}
							})
						}
						
						$("#replySelectArea").ready(function(){
							var writer = '<c:out value="${sessionScope.loginMember.memberId}"/>';
							var bno = '<c:out value="${requestScope.board.boardNo}"/>';
							var content = $(".commentInput").val();
							
							$.ajax({
								url: "${applicationScope.contextPath}/replyList.fb",
								data: {writer: writer, content: content, bno: bno},
								type: "post",
								success: function(data) {
									
									var $replySelectTable = $("#replySelectArea");
									$replySelectTable.html('');
									
									for(var key in data) {
										var $tr = $("<tr>");
										var $writerTd = $("<td>").text(data[key].commentWriterId);
										var $contentTd = $("<td>").text(data[key].commentContent);
										var $dateTd = $("<td>").text(data[key].commentWriteDate);
										
										$tr.append($writerTd);
										$tr.append($contentTd);
										$tr.append($dateTd);
										
										$replySelectTable.append($tr);
									}
								},
								error: function(err) {
									console.log("댓글보기 실패!!!");
								}
							})
						});
					</script>
			</div>
			</div>
		</div>
	</c:if>
	<c:if test="${ empty sessionScope.loginMember }">
		<c:set var="message" value="잘못된 경로로 접근하셨습니다." scope="request"/>
		<jsp:forward page="../../common/errorPage.jsp"/>
	</c:if>
</body>
</html>