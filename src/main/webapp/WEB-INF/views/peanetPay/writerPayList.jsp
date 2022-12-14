<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
 <%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
 <%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>작가정산요청서</title>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel='stylesheet' href="/resources/css/pay/writerpayList.css">
	<script src='../resources/js/jquery-3.6.0.min.js' ></script>
</head>
<body>
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<h1 id="h"></h1>
<div style="width:100%;display: flex;justify-content: center;">
	<div id ="title">정산 접수 내역</div>
</div>
<div id="searchbtn">			
	<select id="selectWP" >
		<option id="all" checked>전체</option>
		<option id="id">아이디</option>
		<option id="date">접수일</option>
		<option id="end">지급건</option>
	</select> 		
	
	<input class="inputzone" type="text" style="display:none" >						
	<input class="inputzone" type="date" style="display:none">				
	
	<button id="wpBtn" class="btnbox" onclick="wpSearch();">검 색</button>
</div>
			
<main id='putMain'>
    <div id='table'>
        <table>
            <thead>
                <tr>
                    <th>접수번호</th>
                    <th>고객ID</th>
                    <th class='768moni'>시리즈번호</th>
                    <th>책번호</th>
                    <th>정산땅콩</th>
                    <th>정산금액</th>                
                    <th>접수일</th>
                    <th>지급은행</th>
                    <th>지급계좌</th>
                    <th>승인여부</th>
                </tr>
            </thead>
            <tbody id='tb'>
            <c:if test="${empty wrList || count eq 'no'}">				
				<tr>
					<td>접수 이력이 없습니다.</td>
				</tr>				
			</c:if>
			<c:if test="${!empty wrList  }">
				<c:forEach items="${wrList }" var="WriterPay" varStatus="i">
				<tr>
						<td>${WriterPay.wrpayNo}</td>
						<td>${WriterPay.memberId}</td>
						<td>${WriterPay.seriesNo}</td>
						<td>${WriterPay.ori_bookNo}</td>
						<td>${WriterPay.changeP}</td>
						<td>${WriterPay.payment}</td>
						<td><fmt:formatDate value="${WriterPay.putDate }"
								pattern="yyyy.MM.dd. HH:mm:ss" /></td>
						<td>${ WriterPay.bankName}</td>
						<td>${ WriterPay.bankNo}</td>
						<c:if test="${ WriterPay.bankStatus=='N'}">
							<td id="wrStratus${i.count }"><input type="button" class="okbtn"
								onclick="putWRpay('${WriterPay.wrpayNo}');" value="승인"></td>
						</c:if>
						<c:if test="${ WriterPay.bankStatus!='N' }">
							<td>승인완료</td>
						</c:if>
				</tr>

				</c:forEach>
				
			</c:if>
			</tbody>
        </table>       
    
    </div>
	<div class="page_wrap">
		<div class="pagination">

			<c:if test="${pm.startNavi !=1}">
					<a href="javascript:void(0);" 
								onclick="pageWPSearch(${1});"
					title="first page"><svg fill="currentColor">
						<path
							d="M17.59 18L19 16.59 14.42 12 19 7.41 17.59 6l-6 6zM11 18l1.41-1.41L7.83 12l4.58-4.59L11 6l-6 6z" /></svg>
					First</a>				
					<a href="javascript:void(0);" 
								onclick="pageWPSearch(${pm.startNavi -1});"
					title="previous page"><svg fill="currentColor">
							<path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" /></svg> </a>
			</c:if>
			<c:forEach begin="${pm.startNavi}" end="${pm.endNavi }" var="p">
					<c:if test="${pm.currentPage eq p}">
						<a class="page-active"
							href="javascript:void(0);" onclick="pageWPSearch(${p });"
							style="background-color: grey;"> ${p} </a>
					</c:if>
					<c:if test="${pm.currentPage ne p}">
						<a class="page-active"
							href="javascript:void(0);" onclick="pageWPSearch(${p });">
							${p} </a>
					</c:if>
				</c:forEach>
			<c:if test="${pm.maxPage > pm.currentPage && pm.maxPage != pm.endNavi}">
				<a title="next page"
					href="javascript:void(0);" onclick="pageWPSearch(${pm.endNavi+1});"><svg
						fill="currentColor">
							<path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z" /></svg></a>
			</c:if>
			<c:if test="${(pm.endNavi %5)==0 && pm.maxPage != pm.endNavi }">
				<a
					href="javascript:void(0);" onclick="pageWPSearch(${pm.maxPage});"
					title="last page">Last<svg fill="currentColor">
						<path
							d="M6.41 6L5 7.41 9.58 12 5 16.59 6.41 18l6-6zM13 6l-1.41 1.41L16.17 12l-4.58 4.59L13 18l6-6z" /></svg></a>
			</c:if>
		</div>
	</div>
</main>

<jsp:include page="../footer/footer.jsp"></jsp:include>
<script>


	/* 페이징  */
	function pageWPSearch(page){ 		
		var putDate="${putDate }";			
		var memberId="${memberId }";
		var bankStatus="${bankStatus }";
		location.href="/writer/admin_list.kh?page="+page+"&memberId="+memberId+"&putDate="+putDate+"&bankStatus="+bankStatus;
	}
	
	/* 검색창 display  */
	$('#selectWP').change(function(){
		if($('#selectWP option').eq(1).is(':checked')){
			$('input[type="text"]').show();
			$('input[type="date"]').hide();
		}else if($('#selectWP option').eq(2).is(':checked')){
			$('input[type="date"]').show();
			$('input[type="text"]').hide();
		}else{
			$('input[type="text"]').hide();
			$('input[type="date"]').hide();
		}
	});
	
	/* 검색버튼  */
	function wpSearch(){
		var memberId = $('input[type="text"]').val();
		var putDate = $('input[type="date"]').val();		
		
		if($('#selectWP option').eq(1).is(':checked')){	
			location.href="/writer/admin_list.kh?memberId="+memberId;
		}else if($('#selectWP option').eq(2).is(':checked')){
			location.href="/writer/admin_list.kh?putDate="+putDate;
		}else if ($('#selectWP option').eq(3).is(':checked')){
			location.href="/writer/admin_list.kh?bankStatus=Y";			
		}else {
			location.href="/writer/admin_list.kh";
		};
			
	}
	
	/*지급승인버튼  */
	function putWRpay(wrpayNo) {
		$.ajax({
			url  : '/writer/payStatus.kh',
			type : 'post',
			data : {
				'wrpayNo' : wrpayNo
			},
			success : function(result) {
				if (result == 'success') {
					alert('접수 완료');
					location.href="/writer/admin_list.kh";					
				} else {
					alert('실패');
				}
			}
		});
	}
	
</script>
</body>
</html>