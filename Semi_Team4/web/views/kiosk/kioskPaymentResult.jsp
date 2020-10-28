<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link href="<%= request.getContextPath() %>/resources/css/kiosk/kioskPaymentResult.css" rel="stylesheet" type="text/css">
<title>주문확인서</title>
</head>
<body>
	<div class="outer"> 
		<div class="top">   
			<h1> 주문확인서</h1>
		<br>
		</div> 
		<br><br>
		<div class="center">
			<h2><br>주문해주셔서 감사합니다.<br></h2>
			<h1><br>주문번호<br></h1>
			<span class="orderNo"><c:out value="${ requestScope.OrderNUM.orderNo }"/></span>
		</div> 
		<div class="serve">
			<div class="left Btop">
				<h4>총주문금액</h4>
			</div> 
			
			<div class="right Btop">
				<h4><c:out value="${ requestScope.allP }"/>원</h4> 
			</div>
		
			<div class="left BCenter">
				<h4> 할인금액</h4> 
			</div> 
			
			<div class="right BCenter">
				<h4>-<c:out value="${ requestScope.disPrice }"/>원</h4> 
			</div>
			
			<div class="left Bbottom">
				<h3>총 결제금액</h3> 	
			</div> 
			<div class="right Bbottom">
				<h3><c:out value="${ requestScope.resultPrice }"/>원</h3> 
		</div> 
	</div>
		<span id="timeString" hidden></span>
	</div>
	
	<script type="text/javascript">

 var MOVE = function(param){
    var self=this;
    this.object = document.getElementById(param.id);
    this.time = param.time||6;     
    this.url = param.url||'';
    this.timer = null;
    this.run = function(){
     timeString.innerHTML = this.time + '초 후 자동으로 이동 합니다.';
     this.time--;
     if(this.time < 0){
     if(this.url!=''){
     location.href = this.url;
     window.clearTimeout(this.timer);
     }
     }else{
     this.timer = window.setTimeout(
     function(){self.run();}
     ,1000
     )
     }
  };
  this.run();
  }
  window.onload = function(){
  new MOVE({id:'prt',url:'${applicationScope.contextPath}/views/kiosk/kioskFirst.jsp'}); //이동할 URL
 
  
  }
  
  </script>
	
</body>
</html>