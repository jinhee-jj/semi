<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="<%= request.getContextPath() %>/resources/css/orderManagement/menuManagementCss.css" rel="stylesheet" type="text/css">

<title>메뉴관리</title>

<!-- 본사/지점 화면 구분은 loginUer에 따라 '메뉴등록' 버튼의 유무로 결정한다. -->

</head>
<body>
 	<jsp:include page="../../views/common/menubar.jsp"/>
	
	<c:if test="${ !empty sessionScope.loginMember }">
	<input type="hidden" id="whatThe" value="${ sessionScope.loginMember.memberDeptCode }">
	<div class="outer">
		<br>
		<div class="search-area" align="left">
			<div class="searchMenu">
				<select id="searchCondition" name="searchCondition">
					<option value="menuName"  >메뉴명</option>
					<option value="type" >분류</option>
					<option value="state" >상태</option>
				</select>
				<input type="search" id="searchCondition2" placeholder="검색어를 입력해주세요" width="300">
				<button type="submit" id="searchCondition3">검색하기</button>
			</div>
		</div>
		<c:if test="${ sessionScope.loginMember.memberDeptCode.equals('본사') }">
		<div class="selectBranchArea">
			<label>지점선택</label>
			<select name="selectBranch" id="selectBranch">	<!-- 추후 value 값으로 지점코드 불러와 사용 예정 -->
				<option value="all">전체(본사)</option>
				
				<c:forEach var="b" items="${ requestScope.branch }">
					<option value="<c:out value="${ b.branchCode }"/>"><c:out value="${ b.branchName }"/>점</option>
				</c:forEach>
				
			</select>
		</div>
		</c:if>
		<div class="table-area">
			<table align="center" id="listArea">
				<tr>
					<th>분류</th>
					<th>메뉴사진</th>
					<th>메뉴코드</th>
					<th>메뉴명</th>
					<th>판매가</th>
					<th>상태</th>
					<th>레시피</th>
					<th><input type="checkbox" id="allCheck"/></th>
				</tr>
				<tbody></tbody>
				<c:forEach var="n" items="${ requestScope.menu }">
					<tr>
						<td><c:out value="${ n.menuClass }"/></td>
						<td><img width="100px" height="100px" src="${ applicationScope.contextPath }/resources/images/menuImage/<c:out value="${ n.menuImage }"/>"> </td>
						<td><c:out value="${ n.menuCode }"/></td>
						<td><c:out value="${ n.menuName }"/></td>
						<td><c:out value="${ n.menuPrice }"/></td>
						<td><c:out value="${ n.menuStatus }"/></td>
						<td><button onclick="menuDetail(this)">상세보기</button>
						<!-- 추후 request 통해 제품정보 인자로 전달하여 처리 -->
						<input type="hidden" id="menuCode" value="${ n.menuCode }">
						<td><input type="checkbox" name="selectToModify" id="chk" class="ckke"></td>
					</tr>
				</c:forEach>
				

			</table>
		</div>
		
		<br>
		<div id="buttons">
			<table>
				<tr>
					<td>
						<div id="leftbox">
							<button id="danjong">단종처리</button>
							<button id="maejin">매진</button>
							<button id="fanmae">판매중</button>
						</div>
					</td>
					
					<td>
					
						<div id="rightbox">
			 			<c:if test="${ sessionScope.loginMember.memberDeptCode.equals('본사') }">
			 				<button id="menuInsert" onclick="menuCreate()" style="right:50px">메뉴등록</button> <!-- 추후 아래와 같이 계정확인 후 보이도록 수정  -->
							</c:if>
						</div>
						
					</td>
			</table>
		</div>
	</div>
	</c:if>
				
	<script>
		$(function() {
			$("#listArea td").mouseenter(function(){
				$(this).parent().css({"background":"darkgray", "cursor":"pointer"});
			}).mouseout(function(){
				$(this).parent().css({"background":"white"});
			});
		});
		
		$("#searchCondition3").click(function(){
			var search = $("#searchCondition").val();
			var value = $("#searchCondition2").val();
			
			if( $("#whatThe").val() == "본사" ){
				location.href="${applicationScope.contextPath}/searchHOMenu.mm?search="+search+"&value="+value+"&branch="+branch;
			} else {
				location.href="${applicationScope.contextPath}/searchMenu.mm?search="+search+"&value="+value;	
			}
			
		});
		
		$("#allCheck").change(function(){
			
			if( $("#allCheck").is(":checked") ){
				$("input[name=selectToModify]").prop("checked", true);
			} else {
				$("input[name=selectToModify]").prop("checked", false);
			}
			
		});
		var branch = "";
		$("#danjong").click(function(){
			
 			if( $(".ckke").is(":checked") ){
 				var state = $(".ckke:checked").parent().parent().find("#menuCode");
 				
				var output = '';
				$.each(state, function(index, value){
					if(index == 0) {
						output += value.value;
					} else {
						output += "," + value.value;
					}
					
				});
 				
				if( '${ sessionScope.loginMember.memberDeptCode.equals("본사") }' ){
					
					location.href="${applicationScope.contextPath}/updateMenu.mm?menu="+output+"&state=0&branch="+branch;
				} else {
					location.href="${applicationScope.contextPath}/updateMenu.mm?menu="+output+"&state=0";
				}
				
				
 			} else {
 				
 			}
			
 			
			
		});
		
		$("#maejin").click(function(){
 			if( $(".ckke").is(":checked") ){
 				var state = $(".ckke:checked").parent().parent().find("#menuCode");
 				
				var output = '';
				$.each(state, function(index, value){
					if(index == 0) {
						output += value.value;
					} else {
						output += "," + value.value;
					}
					
				});
 				
				if( '${ sessionScope.loginMember.memberDeptCode.equals('본사') }' ){
					
					location.href="${applicationScope.contextPath}/updateMenu.mm?menu="+output+"&state=1&branch="+branch;
				} else {
					location.href="${applicationScope.contextPath}/updateMenu.mm?menu="+output+"&state=1";	
				}
				
				
 			} else {
 				
 			}
		});
		
		$("#fanmae").click(function(){
 			if( $(".ckke").is(":checked") ){
 				var state = $(".ckke:checked").parent().parent().find("#menuCode");
 				
				var output = '';
				$.each(state, function(index, value){
					if(index == 0) {
						output += value.value;
					} else {
						output += "," + value.value;
					}
					
				});
 				
				if( '${ sessionScope.loginMember.memberDeptCode.equals('본사') }' ){
					
					location.href="${applicationScope.contextPath}/updateMenu.mm?menu="+output+"&state=2&branch="+branch;
				} else {
					location.href="${applicationScope.contextPath}/updateMenu.mm?menu="+output+"&state=2";	
				}
				
				
 			} else {
 				
 			}
		});
		
		function menuDetail(e){
			var code = $(e).parent().parent().find("#menuCode").val();
			
			if( '${ sessionScope.loginMember.memberDeptCode.equals('본사') }' ){
				
				location.href="${applicationScope.contextPath}/selectOneMenu.mm?code="+code+"&branch="+branch;
			} else {
				location.href="${applicationScope.contextPath}/selectOneMenu.mm?code="+code;	
			}

		}
		
		$("#selectBranch").change(function(){
			zizum = $("#selectBranch option:selected").val();
			branch = $("#selectBranch option:selected").text();
			
			$.ajax({
				url: "${applicationScope.contextPath}/selectHOmenu.mm",
				data: {zizum: zizum
						},
				type: "post",
				success: function(data) {
					
					var $replySelectTable = $("#listArea > tbody:last");
					$replySelectTable.html('');

					for(var key in data) {
						
						var $tr = $("<tr>");
						var $orderNo = $("<td>").text(data[key].menuClass);
						var $Image = $("<td><img width=100px height=100px src=${ applicationScope.contextPath }/resources/images/menuImage/"+data[key].menuImage+">");
						var $togo = $("<td>").text(data[key].menuCode);
						var $grossIncome = $("<td>").text(data[key].menuName);
						var $discountIncome = $("<td>").text(data[key].menuPrice);
						var $totalIncome = $("<td>").text(data[key].menuStatus);
						var $codeNum = $("<td><input type=hidden id=menuCode value="+data[key].menuCode+">");
						var $recipe = $("<td><button onclick=menuDetail(this)>상세보기</button>");
						var $check = $("<td><input type=checkbox name=selectToModify id=chk class=ckke>");
						
						$tr.append($orderNo);
						$tr.append($Image);
						$tr.append($togo);
						$tr.append($grossIncome);
						$tr.append($discountIncome);
						$tr.append($totalIncome);
						$tr.append($codeNum);
						$tr.append($recipe);
						$tr.append($check);
						
						$replySelectTable.append($tr);
						
						
					}
	 				
					
				},error: function(err) {
					

				}

						
			});
			

			
		});
		
		
		
		
		function menuCreate(){
			
		}
		
		
	</script>
</body>
</html>