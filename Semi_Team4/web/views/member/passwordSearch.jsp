<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" scope="application"/>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link href="<%= request.getContextPath() %>/resources/css/member/passwordSearch.css" rel="stylesheet" type="text/css">
<title>SEMI-PASSWORD</title>
</head>
<body>

<div id="main">
<br><hr>
<br><br><br>
<div class="passwrap">
    <div class="logo" id="logo">
    	<img src="/st4/resources/images/logo.png">   
    </div>           
    <form id="passwordSearch" action="${applicationScope.contextPath }/passwordSearch.me" method="post">
        <div class="right">
                    <h2 align="center">비밀번호 : 임시비밀번호 발급</h2>
                    
                    <div class="fields"> 
                            <div class="id">아이디<input type="text" class="memberId" name="memberId" autofocus  placeholder="ID를 입력하세요."></div>                                                             
                            <span>구분</span>       
                            <select name="deptCode" class="form-control" id="deptCodeSelect">
                                <option value="" selected="selected">구분</option>
                            </select>          
                            <div class="memberName">이름<input type="text" class="memberName" name="memberName" autofocus placeholder="이름을 입력하세요." ></div>  
                            <div class="memberEmail">이메일<br>
                                <input type="text" class="memberEmail1" id="memberEmail" name="memberEmail" autocomplete="off" placeholder="등록하신 이메일 주소를 입력하세요.">          
                                 <button type="submit" class="emailButton" id="passwordSearch">임시비밀번호발급</button>
                                 <!-- <button type="submit" class="emailButton" id="modalViewDetailOpen">임시비밀번호발급</button> -->
                            </div>                        
                    </div>                 
                    <div onclick="history.back();"class="backPwdBtn">뒤로가기</div> 
					<br><br><br> 
         </div>           
     </form>
</div>
     <br><br><br>
     <p align="center" class="loginpclear"> 로그인 오류 시 02-123-1234 로 전화주세요</p> 
	<br><hr>
	<p align="center" class="footer">COOL</p>      
</div>


<!-- 
<div id="modal">
  <div class="modal_content">
      <h2>확인</h2>
      <p>입력하신 메일주소로 임시비밀번호를 전송하였습니다.<br> 
         확인 후 로그인하셔서 비밀번호 변경해주시기 바랍니다.</p>
  
      <button type="button" id="modalViewDetailClose">닫기</button>
  
  </div>
</div> 
-->

<!-- select선택 시작 : 클릭할떄 text로 가져오기 -->
	
<script>
 	$("#deptCodeSelect").mouseenter(function(){
		
	/* $("#deptCodeSelect").on("change", function(){ */
	
	
		
		console.log($("#deptCodeSelect").find("option[value='"+$(this).val()+"']"));	
		
		$.ajax({
			url:"${applicationScope.contextPath }/PasswordDeptCode.me",
			type:"get",
			success:function(data){
				console.log("Data : " + data);
				
				var $select = $("#deptCodeSelect");
				
				$select.find("option").remove();
				
				console.log("2 : " + $select.find("option"));
				
				$select.append($("<option>").text("구분"));
				for(var index in data){
					
					var $option=$("<option>");
		
					$option.val(data[index].deptCode); //val에 deptCode 추가
					$option.text(data[index].deptCode); // text에 deptCode 추가
				
					$select.append($option); //select option에 붙이기
				
				}
				
			},
			error: function(err){
				console.log("db list 실패")
			}			
		})	
	});
	
</script>
	
	
<!-- select 선택 끝 -->


<script>
function passwordSearch(){
	$("#passwordSearch").submit();
}

</script> 


</body>
</html>