<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 결제리스트</title>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/pay/payList.css" ></link>
<script src="/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="../header/adminheader.jsp"></jsp:include>

<div id="body">
<div style="width:100%;display: flex;justify-content: center;">
	<div id ="title">결제내역리스트</div>
</div>
	<div id="searchZone"> 
		<select id="selectPay">
			<option value="all" checked>전체</option>
			<option value="id">아이디</option>
			<option value="date">날짜</option>
		</select>
			<input type="text" class="input" style="display:none" >
			<input type="hidden" value=${printId }>	
		 	<div id="dateSet" style="display:none">
		 		<div>
			 		<button class="dateBtn" onclick="month(1);">1개월</button>
			 		<button class="dateBtn" onclick="month(3);">3개월</button>
			 		<button class="dateBtn" onclick="monthSet();">기간설정</button>
				</div>
				<div >
					<input type="date" id="start"  >						
					<input type="date" id="end" >
					<input type="hidden" value=${sta_date }>									
					<input type="hidden" value=${end_date }>
				</div>						
		 	</div>		
		<button id="searchBtn" onclick="paySearch();">검 색</button>
	</div>
	
	<table class="responsive-table">
		<caption></caption>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">주문번호</th>
				<th scope="col">ID</th>
				<th scope="col">결제금액</th>
				<th scope="col">결제내용</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${empty payList }">
			
				<tr>
					<td>결제 내역이 없습니다.</td>
				</tr>
			</tbody>
		</table>
		</c:if>
		<c:if test="${!empty payList  }">
			<c:forEach items="${payList }" var="Pay" varStatus="i">
				<tr>
					<th scope="번호">${i.count +((pm.currentPage-1)*10) }</th>
					<td data-title="주문번호">${Pay.orderNo }</td>
					<td data-title="ID">${Pay.memberId }</td>
					<td data-title="금액">${Pay.pay }</td>
					<td data-title="결제내용" data-type="currency">${Pay.orderContents }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="page_wrap">
			<div class="pagination">

				<c:if test="${pm.startNavi !=1}">
					<a href="javascript:void(0);"
						onclick="pagePaySearch(${1 });"
						title="firstpage"><svg fill="currentColor">
					<path
						d="M17.59 18L19 16.59 14.42 12 19 7.41 17.59 6l-6 6zM11 18l1.41-1.41L7.83 12l4.58-4.59L11 6l-6 6z" /></svg>
						First</a>
					<a href="javascript:void(0);"
						onclick="pagePaySearch(${pm.startNavi -1});" 
						title="previouspage"><svg fill="currentColor">
					<path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" /></svg> </a>
				</c:if>
				<c:forEach begin="${pm.startNavi}" end="${pm.endNavi }" var="p">

					<a class="page-active" href="javascript:void(0);"
						onclick="pagePaySearch(${p });"> ${p} </a>

				</c:forEach>
				<c:if test="${pm.maxPage > pm.currentPage }">
					<a title="next page" href="javascript:void(0);"
						onclick="pagePaySearch(${pm.endNavi+1});"><svg fill="currentColor">
					<path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z" /></svg></a>
				</c:if>
				<c:if test="${(pm.endNavi %5)==0 && pm.maxPage != pm.endNavi }">
					<a href="javascript:void(0);"
						onclick="pagePaySearch(${pm.maxPage});" title="last page">Last<svg fill="currentColor">
					<path
						d="M6.41 6L5 7.41 9.58 12 5 16.59 6.41 18l6-6zM13 6l-1.41 1.41L16.17 12l-4.58 4.59L13 18l6-6z" /></svg></a>
				</c:if>
			</div>
		</div>
		</c:if>

</div>
	<jsp:include page="../footer/footer.jsp"></jsp:include>
	
<script>

/* 검색창 display  */
$('#selectPay').on('change',function(){
	if($('#selectPay option').eq(1).is(':checked')){
		$('input[type="text"]').show();
		$('#dateSet').hide();
		$('#start').hide();
		$('#end').hide();
	}else if($('#selectPay option').eq(2).is(':checked')){
		$('#dateSet').show();
		$('#start').show();
		$('#end').show();
		$('input[type="text"]').hide();
	}else{
		$('input[type="text"]').hide();
		$('#dateSet').hide();
	}
	
	
});




/* 페이징  */
function pagePaySearch(page){
	
	var memberId=$('input[type=hidden]').eq(1).val();		
	var startDate=$('input[type=hidden]').eq(2).val();
	var endDate=$('input[type=hidden]').eq(3).val();		
	location.href="/pay/admin_list.kh?page="+page+"&memberId="+memberId+"&startDate="+ startDate +"&endDate="+ endDate ;
}

/* 검색버튼  */
function paySearch(){
	var memberId=$('input[type=text]').val();
	var startDate=$('#start').val();
	var endDate=$('#end').val();
	var selectPay = $('#selectPay option');
	
	if(selectPay.eq(1).is(':checked')){	
		location.href="/pay/admin_list.kh?memberId="+memberId;
	}else if(selectPay.eq(2).is(':checked')){
		location.href="/pay/admin_list.kh?startDate="+ startDate +"&endDate="+ endDate;
	}else{
		location.href="/pay/admin_list.kh?" ;
	};
		
}

function month(num){
	var today = new Date();
 	var year = today.getFullYear();
	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	var day = ('0' + today.getDate()).slice(-2);
	var startDate = year + '-' + month  + '-' + day;
	$('#end').val(startDate);

	var afterMonth = new Date(today.setMonth(today.getMonth() - num));
	 year = today.getFullYear();
	 month = ('0' + (today.getMonth() + 1)).slice(-2);
	 day = ('0' + today.getDate()).slice(-2);
	var endDate = year + '-' + month  + '-' + day;
	$('#start').val(endDate);
}

function monthSet(){
	$('#start').val('');
	$('#end').val('');
}
</script>
</body>
</html>