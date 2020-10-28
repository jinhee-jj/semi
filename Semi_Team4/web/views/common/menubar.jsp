<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" import="com.kh.cool.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${ pageContext.request.contextPath }" scope="application"/>
<%Member loginMember = (Member) session.getAttribute("loginMember");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" charset="UTF-8"
			src="https://widgets.booked.net/weather/info?action=get_weather_info&ver=6&cityID=18406&type=13&scode=2&ltid=3458&domid=593&anc_id=82449&cmetric=1&wlangID=24&color=fff5d9&wwidth=158&header_color=fff5d9&text_color=333333&link_color=08488D&border_form=3&footer_color=fff5d9&footer_text_color=333333&transparent=1"></script>
    <link href="<%= request.getContextPath() %>/resources/css/common/menubar.css" rel="stylesheet" type="text/css">
</head>
<body>

	<c:if test = "${sessionScope.loginMember != null }">

	           <% Member deptCode = (Member)session.getAttribute("loginMember");
	           		
	           //.out.println("menubar deptCode 확인용"+ deptCode);
	           		String dep = deptCode.getDeptCode();
	            	String loginCode = dep.substring(0,3);
	            	//System.out.println("menubar loginCode(hof/bnh/kok) 확인용 " + loginCode);
	            %>
        <div class="header">
            <div class="article article1">             
	            
		       <c:set var ="dCode" value="<%=loginCode %>"/>   
	           <c:if test ="${dCode eq 'HOF' }">
		         <span>			
			         <div class="weatherView dashviews">
						<!-- weather widget start -->
							<div id="m-booked-small-t3-71686">
								<div class="booked-weather-160x36 w160x36-06"
									style="color: #333333; border-radius: 4px; -moz-border-radius: 4px; border: none">
									
									<!-- 지역코드 포함 -->
									<a target="_blank" style="color: white; font-size:13px;"
										href="https://booked.kr/weather/seoul-18406"
										class="booked-weather-160x36-city">서울특별시</a> 
									<a target="_blank" class="booked-weather-160x36-right" href="https://www.booked.net/">
									<img src="//s.bookcdn.com/images/letter/s5.gif" alt="Hotel reservations online - booked.net" /></a>
									<div class="booked-weather-160x36-degree" style="color:white;">
										<span class="plus">+</span>26&deg;<span style="color:white;">C</span>
									</div>
								</div>
							</div>
							
							
						<!-- weather widget end -->
					</div>
				</span>
			
			<span>  |  </span><span><%=loginMember.getMemberName() %>님</span><span>  |  </span><span>본사</span><span>  |  </span>       
		            <button onclick="logoutBtn();">로그아웃</button>   
	           </c:if>
           
              
              <c:if test="${dCode eq 'BNH'}">
			
			         <div class="weatherView dashviews">
						<!-- weather widget start -->
							<div id="m-booked-small-t3-71686">
								<div class="booked-weather-160x36 w160x36-06"
									style="color: #333333; border-radius: 4px; -moz-border-radius: 4px; border: none">
									
									<!-- 지역코드 포함 -->
									<a target="_blank" style="color: white; font-size:13px;"
										href="https://booked.kr/weather/seoul-18406"
										class="booked-weather-160x36-city">서울특별시</a> 
									<a target="_blank" class="booked-weather-160x36-right" href="https://www.booked.net/">
									<img src="//s.bookcdn.com/images/letter/s5.gif" alt="Hotel reservations online - booked.net" /></a>
									<div class="booked-weather-160x36-degree" style="color:white;">
										<span class="plus">+</span>26&deg;<span style="color:white;">C</span>
									</div>
								</div>
							</div>
							
						<!-- weather widget end -->
					</div>

			
			<span>  |  </span><span><%=loginMember.getMemberName() %>님</span><span>  |  </span><span>지점</span><span>  |  </span>       
	              <button onclick="logoutBtn();">로그아웃</button>
              </c:if>
             
              <c:if test="${dCode eq 'KOK'}">
	               <span>			
			         <div class="weatherView dashviews">
						<!-- weather widget start -->
							<div id="m-booked-small-t3-71686">
								<div class="booked-weather-160x36 w160x36-06"
									style="color: #333333; border-radius: 4px; -moz-border-radius: 4px; border: none">
									
									<!-- 지역코드 포함 -->
									<a target="_blank" style="color: white; font-size:13px;"
										href="https://booked.kr/weather/seoul-18406"
										class="booked-weather-160x36-city">서울특별시</a> 
									<a target="_blank" class="booked-weather-160x36-right" href="https://www.booked.net/">
									<img src="//s.bookcdn.com/images/letter/s5.gif" alt="Hotel reservations online - booked.net" /></a>
									<div class="booked-weather-160x36-degree" style="color:white;">
										<span class="plus">+</span>26&deg;<span style="color:white;">C</span>
									</div>
								</div>
							</div>
							
							
						<!-- weather widget end -->
					</div>
				</span>
			
			<span>  |  </span><span><%=loginMember.getMemberName() %>님</span><span>  |  </span><span>키오스크</span><span>  |  </span>       
	              <button onclick="logoutBtn();">로그아웃</button>
              </c:if>
             
              
                <!--상세메뉴클릭  -->
                <div>
                   <span id="s1"></span>
                   <span id="s2"></span>
                </div>
            </div>  
        </div>
    <div class="wrapServe">    

        <div class="sidenav">
            <div class="logo">           	  
            <!-- div에 링크 걸었으나 이미지가 가린 부분은 클릭이 안되는 현상발생.....
            <div class="logo" onclick="goMain()">  -->      	  
           		<a href="${ applicationScope.contextPath  }/views/main/hoDashboard.jsp"><img src="${ applicationScope.contextPath }/resources/images/logo.png"></a>  	
            	<!-- </a> -->
            </div> 
            <button class="dropdown-btn">재고관리</button>
            <div class="dropdown-container">
                <a href="${ applicationScope.contextPath }/views/inven/InvenInsert.jsp"><span>입고등록</span></a>
                <a href="${ applicationScope.contextPath }/views/inven/InvenList.jsp"><span>입고내역</span></a>
                <a href="${ applicationScope.contextPath }/views/inven/InvenStatus.jsp"><span>재고현황</span></a>
                 <c:if test="${dCode eq 'HOF'}">
                <a href="${ applicationScope.contextPath }/igManageList.in"><span>제품관리</span></a>
                </c:if>
            </div>

            <button class="dropdown-btn">발주관리</button>
            <div class="dropdown-container">
            	<c:if test="${dCode eq 'BNH'}">
	            	<a href="${ applicationScope.contextPath }/selectList.pu"><span>발주등록</span></a>
	                <a href="${ applicationScope.contextPath }/selectpListBR.pu"><span>발주내역</span></a>
                </c:if>
                <c:if test="${dCode eq 'HOF' }">
	                <a href="${ applicationScope.contextPath }/selectBRpuListforHo.pu"><span>발주등록</span></a>
	                <a href="${ applicationScope.contextPath }/selectHOpuList.pu"><span>발주내역</span></a>
	                <a href="${ applicationScope.contextPath }/selectAllBRpList.pu"><span>지점발주내역</span></a>
	                <a href="${ applicationScope.contextPath }/selectSupList.pu"><span>거래처관리</span></a>
                </c:if>
            </div>

            <button class="dropdown-btn">주문관리</button>
            <div class="dropdown-container">
                <a href="${ applicationScope.contextPath }/views/kiosk/kioskFirst.jsp"><span>키오스크</span></a>
                <a href="${ applicationScope.contextPath }/selectBreak.om"><span>주문내역</span></a>
                <a href="${ applicationScope.contextPath }/selectList.mm"><span>메뉴관리</span></a>
            </div>

            <button class="dropdown-btn">정산관리</button>
            <div class="dropdown-container">
                <a href="${ applicationScope.contextPath }/views/revenue/revenueSummary.jsp"><span>매출현황</span></a>
            </div>

            <button class="dropdown-btn">운영관리</button>
            <div class="dropdown-container">
            <c:if test="${dCode eq 'BNH'}"> <!--지점  -->
                <a href="${ applicationScope.contextPath }/views/management/memberInfo.jsp" id="mInfo"><span>내계정관리</span></a>
            </c:if>
             <c:if test="${dCode eq 'HOF'}"> <!--본사  -->
               <a href="${ applicationScope.contextPath }/views/management/memberInfo.jsp" id="mInfo"><span>내계정관리</span></a>
               <a href="${ applicationScope.contextPath }/memberSelectAll.me"><span>계정권한관리</span></a>
               <a href="${ applicationScope.contextPath }/PaperHOFAll.me"><span>문서관리</span></a>
            </c:if>
            <c:if test="${dCode eq 'KOK'}"> <!--키오스크 -->
                <a href="${ applicationScope.contextPath }/views/management/memberInfo.jsp" id="mInfo"><span>내계정관리</span></a>
            </c:if>
            </div>

            <button class="dropdown-btn">커뮤니티</button>
            <div class="dropdown-container">
                <a href="${ applicationScope.contextPath }/selectList.no"><span>공지사항</span></a>
                <a href="${ applicationScope.contextPath }/selectList.fb?community=2"><span>자유게시판</span></a>
                <a href="${ applicationScope.contextPath }/faqList.faq"><span>FAQ</span></a>
                <a href="${ applicationScope.contextPath }/selectList.qa"><span>질의응답</span></a>
            </div>
        </div>


    </div>

</c:if>
<c:if test="${sessionScope.loginMember ==null }">
<c:set var ="message" value="접근실패" scope="request"/>
	<jsp:forward page="errorPage.jsp"/>	
</c:if>
    <script>
       var dropdown = document.getElementsByClassName("dropdown-btn");
   		
       var i;
    
	   for (i = 0; i < dropdown.length; i++) {
	
		   dropdown[i].addEventListener("click", function() {
		      	this.classList.toggle("active");
		      	var dropdownContent = this.nextElementSibling;
		      	
		      	if (dropdownContent.style.display === "block") {
		      		dropdownContent.style.display = "none";
		      	}else {
		      			dropdownContent.style.display = "block";
		      	}
	      });
	    }
	</script>      
<!-- 메뉴바 END-->

<!-- 메뉴클릭시 header영역 표시(상세메뉴) -->
   <script type="text/javascript">
            $(function(){
                $("a").on('click', function(){
                    var a =$(this).children();
                    for(var i = 0; i < 2; i++){
                        $("#s" + (i+1)).text(a[i].innerHTML);    
                    }
                });
            });
      </script>
<!-- 메뉴클릭시 header영역 표시 END -->

<!-- 로그아웃 버튼 클릭 -->
	<script>
		function logoutBtn(){
			location.replace("${ applicationScope.contextPath }/memberLogout.me");
			/* location.href="${applicationScope.contextPath}/memberLogout.me"; */
		};
		
		function goMain(){
			location.href="${ applicationScope.contextPath }/views/main/hoDashboard.jsp";
		};
	</script>
	
<!--날씨 위젯 시작  -->	
	<script type="text/javascript">
								var css_file = document.createElement("link");
								css_file.setAttribute("rel", "stylesheet");
								css_file.setAttribute("type", "text/css");
								css_file
										.setAttribute("href",
												'https://s.bookcdn.com/css/w/bw-160-36.css?v=0.0.1');
								document.getElementsByTagName("head")[0]
										.appendChild(css_file);
								function setWidgetData(data) {
									if (typeof (data) != 'undefined'
											&& data.results.length > 0) {
										for (var i = 0; i < data.results.length; ++i) {
											var objMainBlock = document
													.getElementById('m-booked-small-t3-71686');
											if (objMainBlock !== null) {
												var copyBlock = document
														.getElementById('m-bookew-weather-copy-'
																+ data.results[i].widget_type);
												objMainBlock.innerHTML = data.results[i].html_code;
												if (copyBlock !== null)
													objMainBlock.appendChild(copyBlock);
											}
										}
									} else {
										alert('data=undefined||data.results is empty');
									}
								}
							</script>
<!--날씨 위젯 끝  -->

</body>
</html>
