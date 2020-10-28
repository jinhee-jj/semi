<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link href="<%= request.getContextPath() %>/resources/css/kiosk/ElectronicBoard.css" rel="stylesheet" type="text/css">
<title>전광판</title>
<style>
	.orderNumber{
		color: white;
		font-size: 1.5rem;
		padding: 1rem;
		
	}
	
	.topLeft{
		color: yellow;
		font-size: 2rem;
		
	}
	
	.topRight{
		color: white;
		font-size: 2rem;
		
	}
	
	.leftttopp{
		float: left; width: 49.1%; border-right:7px solid #fff; text-align: center; border-bottom: 7px solid #fff;
	}
	
	.rightttopp{
		float: left; width: 50%; text-align: center; border-bottom: 7px solid #fff;
	}
	
	.lefttbottomm{
		float: left; width: 49.1%; height: 98%; border-right:7px solid #fff; text-align: center; border-bottom: 7px solid #fff;
	}
	
	.righttbottomm{
		float: left; width: 49.1%; height: 98%; text-align: center; border-bottom: 7px solid #fff;
	}
</style>
</head>
<body>
	<div class="outer"> 	
		
 		<div style="height: 1024px;">
			
			<div class="leftttopp" >
				<h1 class="topLeft">주문하신 제품이 나왔습니다.</h1>
				
			</div>
			
			<div class="rightttopp" >
				<h1 class="topRight">제품을 준비중입니다.</h1>
				
			</div>
			
			<div class="lefttbottomm" >
			
			
				<c:forEach var="k" items="${ requestScope.orderNUm2 }">
					
					<span class="orderNumber" ><c:out value="${ k.orderNo }"/></span>
		
				</c:forEach>
					
				</div>
			
			<div class="righttbottomm" >
			
				<c:forEach var="n" items="${ requestScope.orderNum }">
					
					<span class="orderNumber"><c:out value="${ n.orderNo }"/></span>
		
				</c:forEach>
			
				</div>
			
		</div>
		
	</div>
	
	<span id="timeString" hidden></span>
	
<script type="text/javascript">

 var MOVE = function(param){
    var self=this;
    this.object = document.getElementById(param.id);
    this.time = param.time||5;
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
	  
	  	var what = Math.floor(Math.random() * 10) + 1;
		$.ajax({
			url: "${applicationScope.contextPath}/selectETB.kk",
			data: {what: what},
			type: "post",
			success: function(data) {
				 
				}

		})
	  
  new MOVE({id:'prt',url:'${applicationScope.contextPath}/selectETB.kk?what='+what}); //이동할 URL
  
  }
  
  /* <meta http-equiv="refresh" content="200; url='${applicationScope.contextPath}/selectETB.kk'/" /> */
  
  </script>
	
	
</body>
</html>