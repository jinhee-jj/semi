<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" scope="application"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="<%= request.getContextPath() %>/resources/css/management/paperManagement.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="../common/menubar.jsp"/>
    
    <div class="paperWrap">
            <div class="paperOne"> 
                    <span>지점검색 : </span><input type="search" id="deptSearch" name="searchArea" placeholder="지점명을 입력하세요">
					<button id="deptSearchBtn">검색</button>
					<label><input type="button" id="branchNameAllBtn" value="전체"></label>		    
            </div>
            
        
            <table class="paperTop" id="paperTopList">
                <thead>
                    <tr>
                        <th>순번</th>
                        <th>발주일자</th>
                        <th>지점코드</th>
                        <th>지점명</th>
                        <th>상세보기</th> 
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="deptNameList" items="${requestScope.list}">
                    <tr>
                        <td><input class="PA" type="text" id="rowNum" name="rowNum" value="${deptNameList.rNum }"></td>
                        <td><input class="PA" type="text" id="branchDate" name="branchDate" value="${deptNameList.branchDate }"></td>
                        <td><input class="PA" type="text" id="branchCode" name="branchCode" value="${deptNameList.branchCode }"></td>
                        <td><input class="PA" type="text" id="branchName" name="branchName" value="${deptNameList.branchName }"></td>
          	            <!-- <td><button class="paperBtn" id="modalViewDetailOpen">상세보기</button></td> -->
          	             <td><input class="paperBtn" id="modalViewDetailOpen" value="상세보기"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
      </div>
    
        <div id="modal">
        
            <div class="modal_content">
                <div class="modalWrap"> 
                    <h1 align="center"><br><br>지점발주서</h1>
                    <div class="modalLeft">
                    		<table class="modalLeftTitle">
                    			<thead>
	                    			<tr>
	                    				<th>NO</th>
	                    			</tr>
	                    			<tr>
	                    				<th>발주일자</th>
	                    			</tr>
	                    			<tr>
	                    				<th>지점명</th>
	                    			</tr>
	                    			<tr>
	                    				<th>지점주소</th>
	                    			</tr>
	                    		<thead>
	                    		<tbody></tbody>
         
                    		</table>
                    </div>           
            
                    <div class="modalRight">
                        <table class="modalRightTitle">
                            <thead>
	                    			<tr>
	                    				<th>등록번호</th>
	                    			</tr>
	                    			<tr>
	                    				<th>회사명</th>
	                    			</tr>
	                    			<tr>
	                    				<th>대표자명</th>
	                    			</tr>
	                    			<tr>
	                    				<th>본사주소</th>
	                    			</tr>
	                    			<tr>
	                    				<th>본사연락처</th>
	                    			</tr>
	                    	<thead>
	                    	<tbody></tbody>	
                              
                        </table>
                    </div> 
            
                    <div class="orderGRBottom">
                        <table class="orderGRBottomT">
                            <thead>
                                <tr>
                                    <th>NO</th>
                                    <th>분류</th>
                                    <th>제품코드</th>
                                    <th>제품명</th>
                                    <th>수량</th>
                                </tr>
                            </thead>
    
                            <tbody></tbody>
    
                            <!-- <tfoot>
                                <tr>
                                <th colspan="4">총수량</th>
                                <td></td>
                                </tr>
                            </tfoot> -->
    
                        </table>
                        <span id="modalSpan">위와 같이 발주합니다.</span>			 
                    </div>	
            </div>
            
                <button type="button" id="modalViewDetailClose" class="paperCloseBtn">닫기</button>
            
        </div>
     </div>

 <!--모달창 누르기 시작  -->
<script type="text/javascript">
           /*  document.getElementsByClassName("paperBtn").onclick = function() {
                document.getElementById("modal").style.display="block";
            }
           
            document.getElementById("paperCloseBtn").onclick = function() {
                document.getElementById("modal").style.display="none";
            }    */
        
        
        $(function(){
        	$(".paperBtn, .paperCloseBtn, #modalViewDetailOpen").click(function(){
        		$("#modal").toggle();
        	});
        });
        
</script>
<!--상세보기에 들어갈 내용  시작-->
<script>
$(function(){
	
	$(document).on("click", ".paperBtn", function(event){
		var rnum = $(this).parents("tr").children().eq(0).children().val();
		var branchDate = $(this).parents("tr").children().eq(1).children().val();
		var branchcode = $(this).parents("tr").children().eq(2).children().val();
		var branchName = $(this).parents("tr").children().eq(3).children().val();
		
		//console.log("지점순번 : " + rnum);
		//console.log("지점발주일자 : " + branchDate)
		//console.log("지점코드 : " + branchcode);
		//console.log(" 지점이름 : "+branchName); //역삼 
		

		
		
		//지점정보
		$.ajax({
			url: "PaperSelectBNH.me", 
			type:"get",
			data:{rnum:rnum, branchDate:branchDate, branchcode:branchcode, branchName:branchName},
			success : function(data){
				var $modalLeftTitleBody = $(".modalLeftTitle tbody");
					$modalLeftTitleBody.html('');
				
				//console.log(data);
				for(var index in data){

					var $tr = $("<tr>").append($("<td>"));
					var $purchaseCodetd=$("<tr>").append($("<td>").text(data[index].rNum));//발주번호 대신 rnum
					var $branchDatetd=$("<tr>").append($("<td>").text(data[index].branchDate));//발주일자
					var $branchNametd=$("<tr>").append($("<td>").text(data[index].branchName));//지점명
					var $branchAddresstd=$("<tr>").append($("<td>").text(data[index].branchAddress));//지점주소
					
					$modalLeftTitleBody.append($purchaseCodetd);
					$modalLeftTitleBody.append($branchDatetd);
					$modalLeftTitleBody.append($branchNametd);
					$modalLeftTitleBody.append($branchAddresstd);
					
					//$modalLeftTitleBody.append($tr);
					
				}
				
			},
			error:function(err){
				console.log("문서관리 : 지점발주서 지점정보 실패");
			}
		});
		
		//본사정보
		$.ajax({
			url: "PaperSelectHOF.me", 
			type:"get",
			success : function(data){
				var $modalRightTitleBody = $(".modalRightTitle tbody");
				
					$modalRightTitleBody.html('');
				
					//console.log(data);
					
				for(var index in data){
					var $tr = $("<tr>").append($("<td>"));
					
					var $hoNotd=$("<tr>").append($("<td>").text(data[index].hoNo));//사업자번호
					var $hoNametd=$("<tr>").append($("<td>").text(data[index].hoName));//상호
					var $hoRepNametd=$("<tr>").append($("<td>").text(data[index].hoRepName));//대표자명
					var $hoAddresstd=$("<tr>").append($("<td>").text(data[index].hoAddress));//본사주소
					var $hoPhonetd=$("<tr>").append($("<td>").text(data[index].hoPhone));//본사연락처
					
					
					$modalRightTitleBody.append($hoNotd);
					$modalRightTitleBody.append($hoNametd);
					$modalRightTitleBody.append($hoRepNametd);
					$modalRightTitleBody.append($hoAddresstd);
					$modalRightTitleBody.append($hoPhonetd);
					
					//$modalRightTitleBody.append($tr);
					
					
				}
				
			},
			error:function(err){
				console.log("문서관리 : 지점발주서 본사정보 조회실패");
			}
		});
		$.ajax({
			url:"${applicationScope.contextPath }/PaperSelectOrder.me", 
			type:"get",
			data:{branchDate:branchDate, branchcode:branchcode},
			success:function(data){
				var $orderGRBottomTBody = $(".orderGRBottomT tbody");
				$orderGRBottomTBody.html('');
				
				for(var index in data){    // <th>제품코드</th><th>제품명</th>
					var $tr = $("<tr>");
					var $numtd=$("<td>").text(Number(index)+1);//넘버링
					var $ingredientClassNametd =$("<td>").text(data[index].ingredientClassName);//분류
					var $ingredientCodetd=$("<td>").text(data[index].ingredientCode);//원재료코드
					var $ingredientNametd=$("<td>").text(data[index].ingredientName);//원재료명
					var $purchaseQuantitytd=$("<td>").text(data[index].purchaseQuantity);//수량
					
			
					$tr.append($numtd);
					$tr.append($ingredientClassNametd);
					$tr.append($ingredientCodetd);
					$tr.append($ingredientNametd);
					$tr.append($purchaseQuantitytd);
					
					$orderGRBottomTBody.append($tr);
				}
			},
			error:function(err){
				console.log("문서관리 : 지점발주서 제품리스트 조회실패")
			}
		});
	});
});	
</script>
<!--상세보기에 들어갈 내용 끝-->


			
<!-- 지점명 검색하기 시작 -->
<script>
	$("#deptSearchBtn").click(function(){
		var deptSearch = $("#deptSearch").val();
			//console.log("deptSearch : " +deptSearch);
		
			
			$.ajax({
				url:"${applicationScope.contextPath }/paperManagementSearch.me",	
				type:"get",
				data:{deptSearch:deptSearch},
				success : function(data){
					var $paperTopListBody = $("#paperTopList tbody");
						
					$paperTopListBody.html('');
					
					//console.log(data);
					for(var index in data){  
						var $tr = $("<tr>");        //=$("<td>").text(data[index].ingredientClassName);
						var $rowNumtd=$("<td>").text(data[index].rNum);//순번
						var $branchDatetd=$("<td>").text(data[index].branchDate);//발주일자
						var $branchCodetd=$("<td>").text(data[index].branchCode);//지점코드
						var $branchNametd=$("<td>").text(data[index].branchName);//지점명
						var $modalViewDetailOpenTd = $("<td>").html("<button class='paperBtn' id='modalViewDetailOpen'>상세보기</button>");//상세보기
						
						$tr.append($rowNumtd);
						$tr.append($branchDatetd);
						$tr.append($branchCodetd);
						$tr.append($branchNametd);
						$tr.append($modalViewDetailOpenTd);
						
						$paperTopListBody.append($tr);
						
					}
					
				},
				
			})
			
	});
	
</script>			
<!-- 지점명 검색하기 시작 -->

<script>
	//전체지점보기
	$(document).on("click", "#branchNameAllBtn", function(event){
			//console.log("branchNameAllBtn : 클릭됨 " )
		
		var branchName = 'branchName';
		//console.log(branchName);
			
		$.ajax({
			url:"paperDeptFindAll.me",
			type:"get",
			data:{branchName:branchName},
			
			
			success : function(data){
				var $paperTopListTBody = $("#paperTopList tbody");
					
				
					$paperTopListTBody.html('');
				
				//console.log(data);
				for(var index in data){  
					var $tr = $("<tr>");       
					var $rowNumtd=$("<td>").text(data[index].rNum);//순번
					var $branchDatetd=$("<td>").text(data[index].branchDate);//발주일자
					var $branchCodetd=$("<td>").text(data[index].branchCode);//지점코드
					var $branchNametd=$("<td>").text(data[index].branchName);//지점명
					var $modalViewDetailOpenTd = $("<td>").html("<input class='paperBtn' id='modalViewDetailOpen' value='상세보기'>");//상세보기
					
					$tr.append($rowNumtd);
					$tr.append($branchDatetd);
					$tr.append($branchCodetd);
					$tr.append($branchNametd);
					$tr.append($modalViewDetailOpenTd);
					
					$paperTopListTBody.append($tr);
					
				}
				
			},
			error: function(err){
				console.log("문서관리 : 지점명 정렬 실패" )
			}
		});
		
		
	});
</script>

		
    </body>
</html>