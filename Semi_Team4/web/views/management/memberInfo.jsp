<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="com.kh.cool.member.model.vo.Member"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="<%= request.getContextPath() %>/resources/css/management/memberInfo.css" rel="stylesheet" type="text/css">
<title>SEMI-MEMBERINFO</title>
</head>
<body>
   	<jsp:include page="../common/menubar.jsp"/>
    <form id="myUpdate" action="${applicationScope.contextPath }/myUpdate.me" method="post">     
    
            <div class="fields"> 
                <h2 align="center"><br><br>사원정보</h2>
                	<div class="fieldSer">
                            <div class="memberId"><span>아이디</span><input type="text" readonly id="memberId" name="memberId" value="${sessionScope.loginMember.memberId }"></div>                                                             
                            <div class="memberName"><span>이름</span><input type="text" readonly  id="memberName" name="memberName" value="${sessionScope.loginMember.memberName }"></div>    
                            <span class="infoSpan">구분</span>
                            <div class="radio-DEPTCODE" id="radio-DEPTCODE">
                                <input type="radio" id="HO" readonly name="DEPTCODE" value="HOF" onclick="return false;">본사
                                <input type="radio" id="BR" readonly name="DEPTCODE" value="BNH" onclick="return false;">지점
                                <input type="radio" id="KIOSK" readonly name="DEPTCODE" value="KOK" onclick="return false;">키오스크
                            </div>
                            <div class="passwordInfo"> 
                                <div class="password"><span>비밀번호</span><input  type="password" class="memberPwd" id="memberPwdChange" name="memberPwdChange" autofocus  placeholder="변경하실 비밀번호를 입력하세요."></div>
                                <div class="password"><span>비밀번호재확인</span><input  type="password" class="memberPwd" id="memberPwdChangeCheck" name="memberPwd" autofocus  placeholder="변경하실 비밀번호를 다시 입력하세요."></div>    
                            	<div id = pwdCheckA></div>
                            </div>
								
								


                            <div class="memberEmail"><span>이메일</span>
                                <input type="email" class="memberEmail" id="memberEmail" readonly name="email" autocomplete="off" value="${sessionScope.loginMember.memberEmail }">
                                 <input type="email" id="memberEmailChange" name="emailCh" autocomplete="off">                       
                            </div>
                            
                            <div class="infoSpan"><span class="infoSpan">연락처</span>
                                <input type="text" class="memberPhone" id="memberPhone" name="memberPhone">
                            </div>
                            
                            <div class="footerBtn">
                            	 <button type="submit" class="submit" id="pwdBtnA">저장</button> 
	                           <!--  <button class="submit" onclick="myUpdate();">저장</button>  -->
	                            <button class="reset" onclick="cancle();">취소</button> 
                            </div>
            		</div>
            	</div>          
     </form> 
     
     <!--계정정보 회원유형(본사/지점/키오스크)  -->
     <script>
     	$(function(){
     		<%-- <%Member longinMember = (Member)session.getAttribute("loginMember");
     			loginMember
     		%> --%>
     		var mInfoDCode = "${loginMember.deptCode}";
     		var code = mInfoDCode.substr(0,3);
     			//console.log(mInfoDCode);
     			//console.log(typeof(mInfoDCode));
     		$("input[name=DEPTCODE]").each(function(){
     			if($(this).val() == code){
     				$(this).attr("checked", true);
     			}
     		})
     	})
     </script>
     
     <!-- 비밀번호 일치여부 -->
     <script>
      	$(function(){
     		$('#memberPwd').keyup(function(){
     			if($('#memberPwdChange').val() != $('#memberPwd').val()){
     				$('#pwdCheckA').text('비밀번호가 일치하지 않습니다.').css({'color':'red'});
     				$("#pwdBtnA").prop('disabled',true);
     			}else{
     				$('#pwdCheckA').text('비밀번호가 일치합니다.').css({'color':'blue'});
     				$("#pwdBtnA").prop('disabled',false);
     			}
     		});
     	}); 
     </script>
     
    
     <script>
     	function cancle(){
     		location.href="${applicationScope.contextPath}/memberInfo.jsp";
     	}
     </script>
     
</body>
</html>







