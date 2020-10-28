<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/resources/css/notice/noticeInsertForm.css" rel="stylesheet" type="text/css">

</head>
<body>
	<jsp:include page="../../common/menubar.jsp"/>
	<div class="outer">
		<div id="wrapper">
				<div class="boardWrap">
				<br>
				<h2 align="left">공지 사항 작성</h2>
				<br>
				<div class="table-area">
					<form action="${ applicationScope.contextPath }/insertNotice.no" method="post" encType="multipart/form-data">
						<table id="writeTable" align="center">
							<tr>
								<td>제목</td>
								<td><input type="text" class="writeText" size="108" name="title"></td>
							</tr>
							<tr>
								<td>내용</td>
								<td><textarea name="content" rows="25%" style="resize:none;"></textarea></td>
								
							</tr>
							<tr>
								<td>첨부</td>
								<td>
									<input type="file" name="noticeFile">
								</td>
							</tr>
						</table>
						<br>
						<div class="buttons" align="center" align="right">
							<button type="reset" class="btn">취소</button>
							<button type="submit" class="btn">등록</button>
						</div>
					</form>
				</div>
				</div>
		</div>
	</div>
</body>
</html>