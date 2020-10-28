<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%= request.getContextPath() %>/resources/css/management/deptAuthorization.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>SEMI-DEPTAUTHORIZATION</title>
</head>
<body>
<jsp:include page="../common/menubar.jsp"/>
<div class="deptWrap" id="deptWrap">

		<div class="search">
			 <label><input type="radio" name="deptRadioSearch" onchange="radioChang(this);" value="findAll" class="SBtn">전체</label>
			<input type="radio" name="deptRadioSearch" onchange="radioChang(this);" value="findId" class="SBtn">아이디순
			<input type="radio" name="deptRadioSearch" onchange="radioChang(this);" value="findDeptCode" class="SBtn">부서코드순
			<select id="searchCondition" name="deptSearch">
				<option value="memberName">이름</option>
				<option value="memberDeptCode">구분</option>
			</select>
			<input type="search" id="deptSearch2" name="searchArea" placeholder="검색어를 입력하세요">
			<button id="deptSearchBtn">검색</button>	
		</div>

	<form id="updateForm" action="${applicationScope.contextPath }/memberUpdate.me" method="post">
		
		
         <table class="deptTop" id="memberL">
        <thead>
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>연락처</th>
            <th>이메일</th>
            <th>부서코드</th>
            <th>구분</th>
            <th><input type="checkbox" name="deptAuthorizationChck" id="checkA" onclick="checkA();"></th>
        </tr>
        </thead>
        <tbody>
           <c:forEach var="memberL" items="${list }">
             <tr>
                  <td><input class="dA" readonly type="text" name="memberId" id="memberId" value="${memberL.memberId }"></td>
                  <td><input class="dA" readonly type="text" name="memberName"  id="memberName" value="${memberL.memberName }"></td>
                  <td><input class="dA" readonly type="text" name="memberPhone" id="memberPhone" value="${memberL.memberPhone }"></td>
                  <td><input class="dA" readonly type="text" name="memberEmail" id="memberEmail" value="${memberL.memberEmail }"></td>
                  <td><input class="dB" readonly type="text" name="deptCode" id="deptCode" value="${memberL.deptCode }"></td>
                  <td><input class="dB" readonly type="text" name="memberDeptCode" id="memberDeptCode" value="${memberL.memberDeptCode }"></td>
                  
                  
                  <td><input type="checkbox" class="checkDept" id="checkboxL" onchange="able(this);"></td>
              </tr>
           </c:forEach>   
              
        </tbody>     
    </table>

        <div class="tableTopBtn">
           <button class="submit" onclick="memberUpdate();">저장</button> 
            <!-- <button class="reset" id="deleteMember">삭제</button> --> 
            <div class="reset" id="deleteMember">삭제</div> 
         </div>
 </form>       
<form id="joinForm" action="${applicationScope.contextPath }/memberInsert.me" method="post">      
      <table class="deptBottom">
        <thead>
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>비밀번호</th>
            <th>연락처</th>
            <th>이메일</th>
            <th>부서코드</th>
            <th>구분</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input class="dC" type="text" id="memberId2" name="memberId" maxlength="13"></td>
            <td><input class="dC" type="text" id="memberName2" name="memberName" maxlength="13"></td>
            <td><input class="dC" type="password" id="memberPwd2" name="memberPwd" maxlength="13"></td>
            <td><input class="dC" type="text" id="memberPhone2" name="memberPhone" maxlength="15"></td>
            <td><input class="dC" type="email" id="memberEmail2" name="memberEmail"></td>
            <td><input class="dC" type="text" id="deptCode2" name="deptCode"></td>
            <td>          
                <input type="radio" id="HO" name="memberDeptCode" value="본사">본사
                <input type="radio" id="BR" name="memberDeptCode" value="지점">지점
                <input type="radio" id="KIOSK" name="memberDeptCode" value="키오스크">키오스크
             </td>
        </tbody>   
        
     
    </table>
        <div class="tableBottomBtn">
           <button class="submit" onclick="memberInsert();">생성</button> 
         </div>
 </form>
</div>
 
  <script>
  
     function memberInsert(){
        $("#joinForm").submit();
     }
 
     function memberUpdate(){
        $("#updateForm").submit();
     }
     
  </script>   
  
  <!-- 선택 시작 -->
 <script>
    function checkA() {
      if($("#checkA").prop("checked")) {
         $("input[type=checkbox]").prop("checked", true);   
      } else {
         $("input[type=checkbox]").prop("checked", false);
      }

   }
 </script>
  
  <script>
     function able(checkboxL){
        console.log("readonly 해제");
        $(checkboxL).parents("tr").children().eq(4).children().prop("readonly", false);
        //$(checkboxL).parents("tr").children().eq(5).children().prop("readonly", false);
     
        /* 계획변경 
        	초기 : 부서코드, 구분 수정 가능
        	변경 : 부서코드만 수정 가능
        */
     
     }
  </script>
  <!-- 선택 끝 -->  
  
  
  
  <!--정렬 시작  -->
  <script type="text/javascript">
	function radioChang(val){
		var radioF = $(val).val();
		console.log("$(val).val(); : " + $(val).val());
		
		//아이디로 정렬
		if(radioF === "findId"){
			
			$.ajax({
					url: "deptId.me",
					type: "get",
					data: {radioF:radioF},
					success: function(data){
						//console.log("1번 : " + data);
						var $memberLBody = $("#memberL tbody");
						//console.log($memberLBody);
						
						$memberLBody.html('');
						
						
						for(var index in data){
							var $tr = $("<tr>");
							var $memberIdTd = $("<td>").html("<input type='text' id='memberId' readonly value='"+data[index].memberId+"'>");
							var $memberNameTd = $("<td>").html("<input type='text' id='memberName'readonly value='"+data[index].memberName+"'>");
							var $memberPhoneTd = $("<td>").html("<input type='text' id='memberPhone' readonly value='"+data[index].memberPhone+"'>");
							var $memberEmailTd = $("<td>").html("<input type='text' id='memberEmail' readonly value='"+data[index].memberEmail+"'>");
							var $deptCodeTd = $("<td>").html("<input type='text' id='deptCode' value='"+data[index].deptCode+"'>");
							var $memberDeptCodeTd = $("<td>").html("<input type='text' id='memberDeptCode' readonly value='"+data[index].memberDeptCode+"'>");
							var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkDept'>");
							
							
								/* console.log($("<td>").text(data[index].memberId));
								console.log($("<td>").text(data[index].memberName));
								console.log($("<td>").text(data[index].memberPhone));
								console.log($("<td>").text(data[index].memberEmail));
								console.log($("<td>").text(data[index].deptCode));
								console.log($("<td>").text(data[index].memberDeptCode)); */
								
								$tr.append($memberIdTd);
								$tr.append($memberNameTd);
								$tr.append($memberPhoneTd);
								$tr.append($memberEmailTd);
								$tr.append($deptCodeTd);
								$tr.append($memberDeptCodeTd);
								$tr.append($checkBoxTd);
								
								$memberLBody.append($tr);
								
						}
						
					},
					error:function(err){
						console.log("계정권한 관리 : 검색실패");
					}
				});
			
			
			/* 부서코드로 정렬*/
			
			}else if(radioF === "findDeptCode"){
				$.ajax({
					url: "deptDeptCode.me",
					type: "get",
					data: {radioF:radioF},
					success: function(data){
							console.log("2번 : " +data);
						var $memberLBody = $("#memberL tbody");
							//console.log($memberLBody);
						
						$memberLBody.html('');
						
						
						for(var index in data){
							var $tr = $("<tr>");
							var $memberIdTd = $("<td>").html("<input type='text' id='memberId' readonly value='"+data[index].memberId+"'>");
							var $memberNameTd = $("<td>").html("<input type='text' id='memberName' readonly value='"+data[index].memberName+"'>");
							var $memberPhoneTd = $("<td>").html("<input type='text' id='memberPhone' readonly value='"+data[index].memberPhone+"'>");
							var $memberEmailTd = $("<td>").html("<input type='text' id='memberEmail' readonly value='"+data[index].memberEmail+"'>");
							var $deptCodeTd = $("<td>").html("<input type='text' id='deptCode' value='"+data[index].deptCode+"'>");
							var $memberDeptCodeTd = $("<td>").html("<input type='text' id='memberDeptCode' readonly value='"+data[index].memberDeptCode+"'>");
							var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkDept'>");
							
							
								/* //확인용
								console.log($("<td>").text(data[index].memberId));
								console.log($("<td>").text(data[index].memberName));
								console.log($("<td>").text(data[index].memberPhone));
								console.log($("<td>").text(data[index].memberEmail));
								console.log($("<td>").text(data[index].deptCode));
								console.log($("<td>").text(data[index].memberDeptCode)); */
								
								$tr.append($memberIdTd);
								$tr.append($memberNameTd);
								$tr.append($memberPhoneTd);
								$tr.append($memberEmailTd);
								$tr.append($deptCodeTd);
								$tr.append($memberDeptCodeTd);
								$tr.append($checkBoxTd);
								
								$memberLBody.append($tr);
								
						}
						
					},
					error:function(err){
						console.log("계정권한 관리 : 검색실패");
					}
				});
			
			/*전체 정렬  */
			}else{
				$.ajax({
					url: "deptAll.me",
					type: "get",
					data: {radioF:radioF},
					success: function(data){
							console.log("2번 : " +data);
						var $memberLBody = $("#memberL tbody");
							//console.log($memberLBody);
						
						$memberLBody.html('');
						
						
						for(var index in data){
							var $tr = $("<tr>");
							var $memberIdTd = $("<td>").html("<input type='text' id='memberId' readonly value='"+data[index].memberId+"'>");
							var $memberNameTd = $("<td>").html("<input type='text' id='memberName' readonly value='"+data[index].memberName+"'>");
							var $memberPhoneTd = $("<td>").html("<input type='text' id='memberPhone' readonly value='"+data[index].memberPhone+"'>");
							var $memberEmailTd = $("<td>").html("<input type='text' id='memberEmail' readonly value='"+data[index].memberEmail+"'>");
							var $deptCodeTd = $("<td>").html("<input type='text' id='deptCode' value='"+data[index].deptCode+"'>");
							var $memberDeptCodeTd = $("<td>").html("<input type='text' id='memberDeptCode' readonly value='"+data[index].memberDeptCode+"'>");
							var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkDept'>");
							
							
								/* //확인용
								console.log($("<td>").text(data[index].memberId));
								console.log($("<td>").text(data[index].memberName));
								console.log($("<td>").text(data[index].memberPhone));
								console.log($("<td>").text(data[index].memberEmail));
								console.log($("<td>").text(data[index].deptCode));
								console.log($("<td>").text(data[index].memberDeptCode)); */
								
								$tr.append($memberIdTd);
								$tr.append($memberNameTd);
								$tr.append($memberPhoneTd);
								$tr.append($memberEmailTd);
								$tr.append($deptCodeTd);
								$tr.append($memberDeptCodeTd);
								$tr.append($checkBoxTd);
								
								$memberLBody.append($tr);
								
						}
						
					},
					error:function(err){
						console.log("계정권한 관리 : 검색실패");
					}
				});
			}
	}
	</script>
  <!--정렬 끝  -->
  
  
  
  <!-- 검색 시작 -->
  	<script>
  		$("#deptSearchBtn").click(function(){
  			var deptSearch2 = $("#deptSearch2").val();
  			var searchCondition = $("#searchCondition option:selected").text(); 
  			//var searchCondition = $("#searchCondition").find(":selected").text();

  				//확인용
	  			//console.log(deptSearch2);
	  			//console.log(searchCondition);
  			
  			/*검색조건 : 이름 */
  			if(searchCondition==="이름"){
  				$.ajax({
  					url: "deptNameSearch.me",
  					type: "get",
  					data: {deptSearch2:deptSearch2},
  					success: function(data){
  						//console.log(data);
  						var $memberLBody = $("#memberL tbody");
  						
  						$memberLBody.html('');
  						
  						
  						for(var index in data){
							var $tr = $("<tr>");
							var $memberIdTd = $("<td>").html("<input type='text' id='memberId' readonly value='"+data[index].memberId+"'>");
							var $memberNameTd = $("<td>").html("<input type='text' id='memberName' readonly value='"+data[index].memberName+"'>");
							var $memberPhoneTd = $("<td>").html("<input type='text' id='memberPhone' readonly value='"+data[index].memberPhone+"'>");
							var $memberEmailTd = $("<td>").html("<input type='text' id='memberEmail' readonly value='"+data[index].memberEmail+"'>");
							var $deptCodeTd = $("<td>").html("<input type='text' id='deptCode' value='"+data[index].deptCode+"'>");
							var $memberDeptCodeTd = $("<td>").html("<input type='text' id='memberDeptCode' readonly value='"+data[index].memberDeptCode+"'>");
							var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkDept'>");
  							
  							
  								console.log($("<td>").text(data[index].memberId));
  								console.log($("<td>").text(data[index].memberName));
  								console.log($("<td>").text(data[index].memberPhone));
  								console.log($("<td>").text(data[index].memberEmail));
  								console.log($("<td>").text(data[index].deptCode));
  								console.log($("<td>").text(data[index].memberDeptCode));
  								
  								$tr.append($memberIdTd);
  								$tr.append($memberNameTd);
  								$tr.append($memberPhoneTd);
  								$tr.append($memberEmailTd);
  								$tr.append($deptCodeTd);
  								$tr.append($memberDeptCodeTd);
  								$tr.append($checkBoxTd);
  								
  								$memberLBody.append($tr);
  								
  						}
  						
  					},
  					error:function(err){
  						console.log("계정권한 관리 : 검색실패");
  					}
  				});
  			
  			
  			/* 검색조건:구분(본사/지점) */
  			
  			}else{
  				$.ajax({
  					url: "deptSortSearch.me",
  					type: "get",
  					data: {deptSearch2:deptSearch2},
  					success: function(data){
  							//console.log(data);
  						var $memberLBody = $("#memberL tbody");
  							//console.log($memberLBody);
  						
  						$memberLBody.html('');
  						
  						
  						for(var index in data){
							var $tr = $("<tr>");
							var $memberIdTd = $("<td>").html("<input type='text' id='memberId' readonly value='"+data[index].memberId+"'>");
							var $memberNameTd = $("<td>").html("<input type='text' id='memberName' readonly value='"+data[index].memberName+"'>");
							var $memberPhoneTd = $("<td>").html("<input type='text' id='memberPhone' readonly value='"+data[index].memberPhone+"'>");
							var $memberEmailTd = $("<td>").html("<input type='text' id='memberEmail' readonly value='"+data[index].memberEmail+"'>");
							var $deptCodeTd = $("<td>").html("<input type='text' id='deptCode' value='"+data[index].deptCode+"'>");
							var $memberDeptCodeTd = $("<td>").html("<input type='text' id='memberDeptCode' readonly value='"+data[index].memberDeptCode+"'>");
							var $checkBoxTd = $("<td>").html("<input type='checkbox' class='checkDept'>");
  							
  							
  								/* //확인용
  								console.log($("<td>").text(data[index].memberId));
  								console.log($("<td>").text(data[index].memberName));
  								console.log($("<td>").text(data[index].memberPhone));
  								console.log($("<td>").text(data[index].memberEmail));
  								console.log($("<td>").text(data[index].deptCode));
  								console.log($("<td>").text(data[index].memberDeptCode)); */
  								
  								$tr.append($memberIdTd);
  								$tr.append($memberNameTd);
  								$tr.append($memberPhoneTd);
  								$tr.append($memberEmailTd);
  								$tr.append($deptCodeTd);
  								$tr.append($memberDeptCodeTd);
  								$tr.append($checkBoxTd);
  								
  								$memberLBody.append($tr);
  								
  						}
  						
  					},
  					error:function(err){
  						console.log("계정권한 관리 : 검색실패");
  					}
  				});
  			}
  		});
  	</script>
  
  <!-- 검색 끝 -->
  
  
 <!-- 삭제 시작 --> 
<script type="text/javascript">
   $("#deleteMember").click(function(){
      
      var ch = $(".checkDept:checked").parent().parent().find("#memberId");
      var add = "";
         //console.log(ch);
         //console.log(add);

      $.each(ch, function(index, value){
         if(index == 0) {
            add += value.value;
         }else {
            add += ", " + value.value;
         }
      });
         //console.log(add);

      var sp = add.split(", "); 
         //console.log(sp);

      $.ajax({
         url:"${applicationScope.contextPath}/memberDelete.me",
            type:"post",
            data:{
                add:add         
            },
            success:function(data){
               //console.log(data);
               $(".checkDept:checked").parent().parent().remove();
               //console.log("삭제됨")
            },
            error:function(err){
               console.log("삭제 실패")
            }
         
      });
   });
</script>
 <!-- 삭제 끝 -->

  
<!--  <script>
    $(function(){
       
       $.ajax({
          url:"${applicationScope.contextPath}/memberSelectAll.me",
          type:"get", 
          success:function(data){
             
             console.log("성공");
             
          },
          error:function(err){
             console.log("조회실패");
          }
       })
    });
 </script> -->
 
 

     
</body>
</html>