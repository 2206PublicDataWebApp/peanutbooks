<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
 <style>
	.container {
		width: 70%;
		max-width: 1000px;
		margin: 0 auto;
	}
	a {
		text-decoration:none !important
	}
	a:hover { 
		text-decoration:none !important
	}
</style>  
<body>
<!-- header start -->
<jsp:include page="../header/header.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
<br><br>	
<div class="container">

	<!-- 검색 -->
	<table align="center" class="table col-10" border="0px">
		<tr>
			<td style="border:none;">
				<div style="display: inline-block; margin: 0 5px;  float: left;">
				<h4 align="center">공지사항</h4>
				</div>
				<!-- div 오른쪽 정렬 -->
				<div style="display: inline-block; margin: 0 5px;  float: right;">
				<form action="/notice/search.kh" method="get" >
					<div style= "display: inline-block">
						<select class="form-select" name="searchCondition" >
							<option <c:if test="${searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
							<option <c:if test="${searchCondition eq 'title'}">selected</c:if> value="title">제목</option>
							<option <c:if test="${searchCondition eq 'contents'}">selected</c:if> value="contents">내용</option>
						</select>
					</div>
					<div style= "display: inline-block">
					<input type="text" name="searchValue" placeholder="검색"  value="${searchValue }">
					</div>
					<div style= "display: inline-block">
					<input type="submit" value="검색" class="btn btn-dark">
					</div>
				</form>
				</div>
			</td>
		</tr>
		
	</table>
	<!-- 검색 -->


	<!-- 리스트 출력 -->
	<table align="center" class="table col-10">
		<tr>
			<td style="border:none;" td colspan="4" align="right">
				<a href="/notice/list.kh" style="color: black"> 전체 | </a>
				<a href="/notice/categoryCount.kh?noticeCategory=0&page=${currentPage }" style="color: black"> 공지 |</a>
				<a href="/notice/categoryCount.kh?noticeCategory=1&page=${currentPage }" style="color: black"> 업데이트 |</a>
				<a href="/notice/categoryCount.kh?noticeCategory=2&page=${currentPage }" style="color: black"> 이벤트 |</a>
				<a href="/notice/categoryCount.kh?noticeCategory=3&page=${currentPage }" style="color: black"> 안내 </a>
			</td>
		</tr>
		<c:if test="${!empty nList }">
			<c:forEach items="${nList }" var="notice" varStatus="i">
				<tr>
					<td>${i.count }</td>
					<td>
						<c:if test="${notice.noticeCategory == '0' }">
							공지
						</c:if>
						<c:if test="${notice.noticeCategory == '1' }">
							업데이트
						</c:if>
						<c:if test="${notice.noticeCategory == '2' }">
							이벤트
						</c:if>
						<c:if test="${notice.noticeCategory == '3' }">
							안내
						</c:if>
					</td>
					<td><a href="/notice/noticeDetailView.kh?noticeNo=${notice.noticeNo }&page=${currentPage }" style="color: black">${notice.noticeTitle }</a></td>
					<td>${notice.nCreateDate }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty nList }">
			<tr>
				<td colspan="4" align="center"><b>데이터가 존재하지 않습니다.</b></td>
			</tr>
		</c:if>
			<tr align="center" height="20">
	            <td colspan="4" style="border:none;">
	                <c:if test="${currentPage != 1}">
	                    <a href="/notice/${urlVal }.kh?page=${currentPage - 1 }">[이전]</a>
	                </c:if>
	                <c:forEach var="p" begin = "${startNavi }" end="${endNavi }">
	                    <c:if test="${currentPage eq p }">
	                        <b>${p}</b> 
	                    </c:if>
	                    <c:if test="${currentPage ne p }">
	                        <a href = "/notice/${urlVal }.kh?page=${p }&searchCondition=${searchCondition }&searchValue=${searchValue }">${p}</a>
	                    </c:if>
	                </c:forEach>
	            <c:if test = "${currentPage < maxPage }">
	                <a href = "/notice/${urlVal}.kh?page=${currentPage + 1}">[다음]</a>
	            </c:if>
	            </td>
	        </tr>
		</table>

</div>
<br><br>
</main>
<!-- main contents End -->


<script>
	$('.summernote').summernote({
		height : 300,
		lang : "ko-KR",
	});
	function titleLengthCk(thisInput){
	 	console.log(thisInput.value.length);
	 	if(thisInput.value.length>30){
	 		thisInput.value = thisInput.value.substr(0,30);
	 	}	
	}
	function noticeCheck() {
		if(noticeForm.noticeTitle.value=="") { // document 를 생략해도 됨
	        alert("제목을 입력하세요!");
	        noticeForm.noticeTitle.focus();
	    	return false;
	    }else if(noticeForm.noticeContents.value==""){
	        alert("내용을 입력하세요");
	        noticeForm.noticeContents.focus();
	        return false;
	    }
		return noticeForm.submit();
	 }
	
	
</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
</html>