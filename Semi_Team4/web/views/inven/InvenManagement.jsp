<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.cool.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="contextPath" value="${ pageContext.request.contextPath }"
	scope="application" />
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품관리</title>
<link rel="stylesheet" type="text/css"
	href="${ applicationScope.contextPath }/resources/css/boardCSS/FaQboard.css">
</head>

<body>
<c:if test="${sessionScope.loginMember.deptCode eq 'HOF01'}">
			<jsp:include page="/views/common/menubar.jsp" />
			<div class="invenWrap">
					<div class="invenScroll im">
						<table class="invenTable" id="ingredientListTable">
							<thead>
								<tr class="invenTr">
									<th>선택</th>
									<th>원재료 코드</th>
									<th>원재료명</th>
									<th>분류</th>
									<th colspan="2">구매용량</th>
									<th>보관방식</th>
									<th>이미지</th>
								</tr>
							</thead>
							<tbody>
							 <c:forEach var="i" items="${ requestScope.list }">
							<tr>
							<td><input type="checkbox" value="<c:out value='${ i.igCode }'></c:out>" name="ckManage" id="ckManage"></td>
							<td><c:out value="${ i.igCode }"></c:out></td>
							<td><c:out value="${ i.igName }"></c:out></td>
							<td><c:out value="${ i.igClass }"></c:out></td>
							<td><c:out value="${ i.igCapacity }"></c:out></td>
							<td><c:out value="${ i.igUnit }"></c:out></td>
							<td><c:out value="${ i.igSType }"></c:out></td>
							<td><img src="${applicationScope.contextPath}/resources/images/ingredientImg/<c:out value='${ i.saveName }'></c:out>" width="100" height="100" id="pImg" name="pImg"></td>
							</tr>
							</c:forEach> 
							

							</tbody>
						</table>
						
					</div>
					<input type="button" class="TopsearchBtn" value="삭제" id="delIg" name="delIg" onclick="function_click();">

				<form action="${ applicationScope.contextPath }/iginsert.in"
					method="post" enctype="multipart/form-data">
					<table class="invenTable">
						<thead>
							<tr class="invenTr">
								<th>원재료 코드</th>
								<th>원재료명</th>
								<th>분류</th>
								<th>중량</th>
								<th>단위</th>
								<th>보관방식</th>
								<th>이미지</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="text" name="pCode" id="pCode" ></td>
								<td><input type="text" id="pName" name="pName"></td>
								<td><select id="classCode" name="classCode">
										<option value="CF">원두</option>
										<option value="CI">시럽</option>
										<option value="PD">파우더</option>
										<option value="TA">티</option>
										<option value="BE">베이스</option>
										<option value="TP">토핑</option>
										<option value="CS">소모품</option>
										<option value="UG">유제품</option>
										<option value="BP">비품</option>
										<option value="SD">사이드</option>
										<option value="PV">기본</option>


								</select></td>
								<td><input type="text" id="pQ" name="pQ"></td>
								<td><select id="pUnit" name="pUnit">
										<option value="G">g</option>
										<option value="EA">개</option>
										<option value="ML">ml</option>
								</select></td>
								<td><select id="pStorage" name="pStorage">
										<option value="실온">실온</option>
										<option value="냉장">냉장</option>
										<option value="냉동">냉동</option>
								</select></td>
								<td id="imgArea"><input type="file" name="igImg" id="igImg"></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" class="TopsearchBtn" value="등록"
					id="pinsert" name="pinsert"> 
					<input type="submit" class="TopsearchBtn"
						value="수정" id="pupdate" name="pupdate">
					<input type="reset" class="TopsearchBtn" value="취소" id="nono" name="nono">
					<!-- <input type="button" class="Colorbtn0" onClick="window.location.reload()" value="새로고침" > -->
				</form>
					 <input type="button" class="Colorbtn0" onClick="location.href = '${ applicationScope.contextPath }/igManageList.in'" value="새로고침" > 

</div>
	</c:if>
	<c:if test="${sessionScope.loginMember.deptCode ne 'HOF01'}">
	<br><br><br><br><br><br><br>
	<h3 align="center">들어오지 마시용</h3>
	</c:if>
	<script>
	 
	 function restart(){
		 location.href = "${ applicationScope.contextPath }/igManageList.in";
			 
	 };
	 
	 $(document).ready(function() {
		 $("input:checkbox").on('click', function() { 
			 if ( $(this).prop('checked') ) { 
				var code = $(this).val(); 
				$("#pCode").val(code);	
				$("#pCode").prop("readonly",true);
				$("#pCode").css("background-color: #C4C4C4;");
				
				var name = $(this).parent().parent().children().eq(2).text();
				$("#pName").val(name);
				
				var pclass = $(this).parent().parent().children().eq(3).text();
				//console.log(pclass);
				switch(pclass){
				 case "원두" : pclass = "CF"; break;
				 case "시럽" : pclass = "CI"; break;
				 case "파우더" : pclass = "PD"; break;
				 case "티" : pclass = "TA"; break;
				 case "베이스" : pclass = "BE"; break;
				 case "토핑" : pclass = "TP"; break;
				 case "소모품" : pclass = "CS"; break;
				 case "유제품" : pclass = "UG"; break;
				 case "비품" : pclass = "BP"; break;
				 case "사이드" : pclass = "SD"; break;
				 case "기본" : pclass = "PV"; break;
				    default : alert("에러발생");
				}
				
				$("#classCode").val(pclass).prop("selected", true);
				
				var capa = $(this).parent().parent().children().eq(4).text();
				$("#pQ").val(capa);
				
				var punit = $(this).parent().parent().children().eq(5).text();
				$("#pUnit").val(punit).prop("selected", true);
				
				var pStype = $(this).parent().parent().children().eq(6).text();
				$("#pStorage").val(pStype).prop("selected", true);
				
				/* var pImage = $(this).parent().parent().children().eq(7).val();
				$("#pImg").val(pImage); */
			 
			 }
					
			 }); 
		 });
	 
	 $("#pinsert").click(function () {
		 if(($("#pCode").val().length == 0) && ($("#pCode").val().length > 3)){
			 alert("코드를 확인하세요.");
			 return false;
			 }
		 if($("#pName").val().length == 0){
			 alert("이름를 입력하세요.");
			 return false;
			 }
		 if($("#pQ").val().length == 0){
			 alert("중량을 입력하세요.");
			 return false;
			 }
		 if($("#igImg").val().length == 0){
			 alert("파일을 넣으세요.");
			 return false;
			 }
		 if($("input:checkbox[name=ckManage]:checked").length > 0){
			 alert("제품 등록을 위해서 체크박스를 전부 해제해주세요.");
			 return false;
		 }
	       $("form").attr("action", "${ applicationScope.contextPath }/iginsert.in");
	});
	 $("#pupdate").click(function () {
	       $("form").attr("action", "${ applicationScope.contextPath }/igUpdate.in");
	});
	$("#nono").click(function(){
		$("#pCode").prop("readonly",false);
	});
	

	function function_click() {  
	    var str = "";  
	    $("input:checkbox:checked").each(function (index) {  
	        str += $(this).val() + ",";  
	    });  
	     
	    location.href = "${applicationScope.contextPath}/ingredientDel.in?data="
			+ str; 
	}  


	</script> 
	
	
</body>
</html>