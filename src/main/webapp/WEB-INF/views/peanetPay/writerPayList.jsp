<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
 <%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
 <%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>작가정산요청서</title>
<link rel='stylesheet' href="/resources/css/pay/writerpayList.css">
	<script src='../resources/js/jquery-3.6.0.min.js' ></script>
</head>
<body>
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<h1>정산 접수 내역</h1>
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
            <c:if test="${count eq 'no'}">				
				<tr>
					<td>접수 이력이 없습니다.</td>
				</tr>				
			</c:if>
			<c:if test="${!empty wrList  }">
				<tr>
					<c:forEach items="${wrList }" var="WriterPay" varStatus="i">
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
				<a
					href="/writer/list.kh?page=${1}&memberId=${loginMember.memberId}"
					title="first page"><svg fill="currentColor">
						<path
							d="M17.59 18L19 16.59 14.42 12 19 7.41 17.59 6l-6 6zM11 18l1.41-1.41L7.83 12l4.58-4.59L11 6l-6 6z" /></svg>
					First</a>
				<a
					href="/writer/list.kh?page=${pm.startNavi -1}&memberId=${loginMember.memberId}"
					title="previous page"><svg fill="currentColor">
							<path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" /></svg> </a>
			</c:if>
			<c:forEach begin="${pm.startNavi}" end="${pm.endNavi }" var="p">

				<a class="page-active"
					" href="/writer/list.kh?page=${p }&memberId=${loginMember.memberId}">
					${p} </a>

			</c:forEach>
			<c:if test="${pm.maxPage > pm.currentPage }">
				<a title="next page"
					href="/writer/list.kh?page=${pm.endNavi+1}&memberId=${loginMember.memberId}"><svg
						fill="currentColor">
							<path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z" /></svg></a>
			</c:if>
			<c:if test="${(pm.endNavi %5)==0 && pm.maxPage != pm.endNavi }">
				<a
					href=/writer/list.kh?page=${pm.maxPage}&memberId=${loginMember.memberId}"
					title="last page">Last<svg fill="currentColor">
						<path
							d="M6.41 6L5 7.41 9.58 12 5 16.59 6.41 18l6-6zM13 6l-1.41 1.41L16.17 12l-4.58 4.59L13 18l6-6z" /></svg></a>
			</c:if>
		</div>
	</div>
</main>

<jsp:include page="../footer/footer.jsp"></jsp:include>
<script>
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
					loction.href='/writer/list.kh';
				} else {
					alert('실패');
				}
			}
		});
	}
	
</script>
</body>
</html>