<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>본사-거래처 관리</title>
<link href="<%= request.getContextPath() %>/resources/css/purchase/supplierManagementListHO.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../../common/menubar.jsp"/>
		<h2>본사-거래처관리</h2>
		<div id="supplierRegisterSection" align="center" style="overflow:scroll;">
		<br>
			<div id="searchArea" align="center">
				<table align="left">
				<tr>
					<td>
						<select id="searchCondition" style="height:27px;">
							<option name="searchCondition">거래처명</option>
							<option name="searchCondition">거래처코드</option>
						</select>
					</td>
					<td><input type="search" id="searchInput" style="height:27px;" name="searchValue" placeholder="거래처 검색" size=25></td>
					<td><button id="searchBtn">검색</button></td>	
					<td><button id="allListBtn">전체리스트 보기</button></td>
				</tr>
				</table>
			</div>
			<table align="center" id="supplierTable" class="table-bottom">
				<thead>
					<tr>
						<th width="20%" align="center">거래처코드</th>
						<th width="20%" align="center">거래처명</th>
						<th width="20%" align="center">대표자</th>
						<th width="30%" align="center">연락처</th>
						<th width="50%" align="center">주소</th>
						<th width="20%" align="center">사업자번호</th>
						<th width="30%" align="center">이메일</th>
						<th width="20%" align="center">은행명</th>
						<th width="30%" align="center">계좌번호</th>
						<th width="20%" align="center">팩스번호</th>
						<!-- <th width="20%" align="center">등록일</th> -->
						<th width="20%" align="center"><input type="checkbox" id="checkAll" onclick="checkAll();"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="s" items="${ requestScope.list }">
						<tr>
							<td id="sCode1"><c:out value="${ s.sCode }"/></td>
							<td id="cName1"><c:out value="${ s.cName }"/></td>
							<td id="rName1"><c:out value="${ s.rName }"/></td>
							<td id="sPhone1"><c:out value="${ s.sPhone }"/></td>
							<td id="sAddress1"><c:out value="${ s.sAddress }"/></td>
							<td id="sNum1"><c:out value="${ s.sNum }"/></td>
							<td id="sEmail1"><c:out value="${ s.sEmail }"/></td>
							<td id="sBank1"><c:out value="${ s.sBank }"/></td>
							<td id="sAccount1"><c:out value="${ s.sAccount }"/></td>
							<td id="sFax1"><c:out value="${ s.sFax }"/></td>
							<%-- <td><c:out value="${ s.enrollDate }"/></td> --%>
							<td><input type="checkbox" class="checkSupplier" ></td>					
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br><br><br>
		<div id="supplierRegisterArea">
			<div id="supplierRegisterTableArea">
				<form id="insertForm" action="${ applicationScope.contextPath }/insertSupplier.pu" method="post" >
					<table id="supplierRegisterTable" align="center">
						<thead>
							<tr>
								<th align="center">거래처코드</th>
								<th align="center">거래처명</th>
								<th align="center">대표자</th>
								<th align="center">연락처</th>
								<th align="center">사업장주소</th>
								<th align="center">사업자번호</th>
								<th align="center">이메일</th>
								<th align="center">은행명</th>
								<th align="center">계좌번호</th>
								<th align="center">팩스번호</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="text" id="sCode" name="supplierCode" size=7 maxlength=8></td>				 
								<td><input type="text" id="cName" name="supplierName" size=5></td>
								<td><input type="text" id="rName" name="supplierRep" size=5></td>
								<td><input type="phone" id="sPhone" name="supplierPhone" size=7></td>
								<td><input type="text" id="sAddress" name="supplierAddress" size=7></td>
								<td><input type="text" id="sNum" name="supplierNumber" size=7></td>
								<td><input type="email" id="sEmail" name="supplierEmail" size=7></td>
								<td><input type="text" id="sBank" name="supplierBank" size=7></td>
								<td><input type="text" id="sAccount" name="supplierBankNum" size=7></td>
								<td><input type="text" id="sFax" name="supplierFax" size=7></td>
							</tr>
						</tbody>			
					</table>
				<div id="editBtn">수정</div>
				<div id="storeBtn" hidden>저장</div>
				<div id="deleteBtn">삭제</div>
				<div id="registerBtn" onclick="insertSupplier();">등록</div>
				</form>
			</div>
		</div>
		</div>
		
	<script>
		/* th의 체크박스 선택시 전체 선택 및 해제 */
		function checkAll() {
			if($("#checkAll").prop("checked")) {
				$("input[type=checkbox]").prop("checked", true);	
			} else {
				$("input[type=checkbox]").prop("checked", false);
			}
	
		}
		
		function insertSupplier(){
			$("#insertForm").submit();
		}
		
		
		//삭제하기 버튼 누름
		$("#deleteBtn").click(function(){
			
			var checkbox =  $(".checkSupplier:checked");
			var size = checkbox.length;
			var sCodes = new Array();
			
			if(size < 1) {
				alert("하나 이상의 거래처를 선택하세요.");
			}else if (window.confirm("정말 삭제하시겠습니까?")) { 
				
				console.log("삭제!");
				var tr = $(".checkSupplier:checked").parent().parent();
				
				
				for(var i = 0; i < size; i++){
					sCode = tr.eq(i).children().eq(0).text();
					console.log(sCode);
					sCodes.push(sCode);
				}
					
				console.log(sCodes);
				jQuery.ajaxSettings.traditional = true; 
				$.ajax({
					url:"deleteSup.pu",
					type:"post",
					data: {
						sCodes : sCodes
					},
					success:function(data){
					//	console.log("서버전송 성공");
					//	console.log(data);
						checkbox.parent().parent().remove();
					},
					error:function(err){
						console.log("거래처 삭제 실패");
					}					
				});	 				 
					 
			}

		});
		
		
		//수정하기 버튼 누르면
		$("#editBtn").click(function(){
			
			var checkbox =  $(".checkSupplier:checked");
			var size = checkbox.length;
			
			if(size !== 1) {
				alert("하나의 거래처를 선택하세요.");
			}else {
				$("#editBtn").hide();
				$("#storeBtn").show();
				
				$("#sCode").prop("readonly",true);
				var tr = checkbox.parent().parent();
				var td = tr.children();
				
				var sCode = td.eq(0).text();
				var cName = td.eq(1).text();
				var rName = td.eq(2).text();
				var sPhone = td.eq(3).text();
				var sAddress = td.eq(4).text();
				var sNum = td.eq(5).text();
				var sEmail = td.eq(6).text();
				var sBank = td.eq(7).text();
				var sAccount = td.eq(8).text();
				var sFax = td.eq(9).text();
				 
				var sCodeNew = $("#sCode").val(sCode);
				var cNameNew = $("#cName").val(cName);
				var rNameNew = $("#rName").val(rName);
				var sPhoneNew = $("#sPhone").val(sPhone);
				var sAddressNew = $("#sAddress").val(sAddress);
				var sNumNew = $("#sNum").val(sNum);
				var sEmailNew = $("#sEmail").val(sEmail);
				var sBankNew = $("#sBank").val(sBank);
				var sAccountNew = $("#sAccount").val(sAccount);
				var sFaxNew = $("#sFax").val(sFax);
				
				
				console.log();
			
				
				$("#storeBtn").click(function(){
					$("#storeBtn").hide();
					$("#editBtn").show(); 
					
					$("#sCode").prop("readonly",false);
					
					var checkbox =  $(".checkSupplier:checked");
					
					checkbox.prop("checked", false);
					
					var sCode = $("#sCode").val();
					var cName = $("#cName").val();
					var rName = $("#rName").val();
					var sPhone = $("#sPhone").val();
					var sAddress = $("#sAddress").val();
					var sNum = $("#sNum").val();
					var sEmail = $("#sEmail").val();
					var sBank = $("#sBank").val();
					var sAccount = $("#sAccount").val();
					var sFax = $("#sFax").val();
					
					
				var supplier = { sCodeNew: sCode, cNameNew: cName, rNameNew: rName, sPhoneNew: sPhone
								, sAddressNew: sAddress, sNumNew: sNum, sEmailNew: sEmail, sBankNew: sBank
								, sAccountNew: sAccount, sFaxNew: sFax };
					 
				 	$.ajax({
						url:"updateSup.pu",
						data: supplier,
						type:"post",
						success:function(data){
							
							td.eq(0).text(data.sCode);
							td.eq(1).text(data.cName);
							td.eq(2).text(data.rName);
							td.eq(3).text(data.sPhone);
							td.eq(4).text(data.sAddress);
							td.eq(5).text(data.sNum);
							td.eq(6).text(data.sEmail);
							td.eq(7).text(data.sBank);
							td.eq(8).text(data.sAccount);
							td.eq(9).text(data.sFax);
							
							var sCode = $("#sCode").val('');
							var cName = $("#cName").val('');
							var rName = $("#rName").val('');
							var sPhone = $("#sPhone").val('');
							var sAddress = $("#sAddress").val('');
							var sNum = $("#sNum").val('');
							var sEmail = $("#sEmail").val('');
							var sBank = $("#sBank").val('');
							var sAccount = $("#sAccount").val('');
							var sFax = $("#sFax").val('');
							
						},
						error:function(err){
							console.log("거래처 저장 실패");
						}
						
						
					}); 
				 	
				 	
					
				});
			}
			
				
		});			
		
		//검색 버튼 클릭시
	   	$("#searchBtn").click(function(){
	   		//검색창에 적혀질 글
	   		var searchValue = $("#searchInput").val();
	   		//검색 조건
	   		var searchCondition = $("#searchCondition option:selected").val();
	   		
	   		//console.log(searchValue);
	   		//console.log(searchCondition);
	   		
	   			$.ajax({
	   				url: "searchSupinManage.pu",
	   				type: "get",
	   				data: {
	   					searchCondition : searchCondition, searchValue : searchValue
	   				},
	   				success: function(data) {
	   					
	   					var $supplierTableTbody = $("#supplierTable tbody");
	   					$supplierTableTbody.html('');
	   					
	   					for(var index in data) {
	   						var $tr = $("<tr>");
	   						var $sCodeTd = $("<td align='center'>").text(data[index].sCode);   
	   						var $cNameTd = $("<td align='center'>").text(data[index].cName);
	   						var $rNameTd = $("<td align='center'>").text(data[index].rName);
	   						var $sAddressTd = $("<td align='center'>").text(data[index].sAddress);
	   						var $sPhoneTd = $("<td align='center'>").text(data[index].sPhone);
	   						var $sNumTd = $("<td align='center'>").text(data[index].sNum);
	   						var $enrollDateTd = $("<td align='center'>").text(data[index].enrollDate);
	   						var $sEmailTd = $("<td align='center'>").text(data[index].sEmail);
	   						var $sBankTd = $("<td align='center'>").text(data[index].sBank);
	   						var $sAccountTd = $("<td align='center'>").text(data[index].sAccount);
	   						var $sFaxTd = $("<td align='center'>").text(data[index].sFax);
	   						var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkSupplier' >");
	   					
	   						$tr.append($sCodeTd);
	   						$tr.append($cNameTd);
	   	    				$tr.append($rNameTd);
	   	    				$tr.append($sPhoneTd);
	   	    				$tr.append($sAddressTd);
	   	    				$tr.append($sNumTd);
	   	    				$tr.append($sEmailTd);
	   	    				$tr.append($sBankTd);
	   	    				$tr.append($sAccountTd);
	   	    				$tr.append($sFaxTd);
	   	    				$tr.append($checkBoxTd);
	   	    				$supplierTableTbody.append($tr);	
					
	   					}
	   				},
	   				error: function(err) {
	   					console.log("거래처명으로 검색 실패");
	   				}
	    				
	   				
	   			});
	   			
	   	});
	   		 //전체리스트로 보기 눌렀을 때
	   		  $("#allListBtn").click(function(){
	   			 console.log("클릭");
	   		  	 var ajax = 'ajax';
	   			  
	   			 $.ajax({
	   				 url: " selectSupList.pu",
	   				 type: "get",
	   				 data: {
	   					ajax : ajax
	   				 },
	   				 success: function(data){

		   					var $supplierTableTbody = $("#supplierTable tbody");
		   					$supplierTableTbody.html('');
		   					
		   					for(var index in data) {
		   						var $tr = $("<tr>");
		   						var $sCodeTd = $("<td align='center'>").text(data[index].sCode);   
		   						var $cNameTd = $("<td align='center'>").text(data[index].cName);
		   						var $rNameTd = $("<td align='center'>").text(data[index].rName);
		   						var $sAddressTd = $("<td align='center'>").text(data[index].sAddress);
		   						var $sPhoneTd = $("<td align='center'>").text(data[index].sPhone);
		   						var $sNumTd = $("<td align='center'>").text(data[index].sNum);
		   						var $enrollDateTd = $("<td align='center'>").text(data[index].enrollDate);
		   						var $sEmailTd = $("<td align='center'>").text(data[index].sEmail);
		   						var $sBankTd = $("<td align='center'>").text(data[index].sBank);
		   						var $sAccountTd = $("<td align='center'>").text(data[index].sAccount);
		   						var $sFaxTd = $("<td align='center'>").text(data[index].sFax);
		   						var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkSupplier' >");
		   					
		   						$tr.append($sCodeTd);
		   						$tr.append($cNameTd);
		   	    				$tr.append($rNameTd);
		   	    				$tr.append($sPhoneTd);
		   	    				$tr.append($sAddressTd);
		   	    				$tr.append($sNumTd);
		   	    				$tr.append($sEmailTd);
		   	    				$tr.append($sBankTd);
		   	    				$tr.append($sAccountTd);
		   	    				$tr.append($sFaxTd);
		   	    				$tr.append($checkBoxTd);
		   	    				$supplierTableTbody.append($tr);	
	   						
	   						 }
	   				 }
	   				 ,
	   				 error: function(err){
	   					 console.log(err);
	   				 }	   				 
	   			 });	   			  
	   		  });
	   		

	</script>
</body>
</html>