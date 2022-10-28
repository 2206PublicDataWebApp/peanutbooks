<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서승인</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
 
  
</head>
 <style>
	.container {
		width: 70%;
		max-width: 1000px;
		margin: 0 auto;
	}

	.btn {
	  appearance: none;
	    -webkit-appearance: none;
	  font-family: sans-serif;
	  cursor: pointer;
	  padding: 12px;
	  min-width: 150px;
	  border: none;
	    -webkit-transition: background-color 100ms linear;
	    -ms-transition: background-color 100ms linear;
	     transition: background-color 100ms linear;
	}
	label:hover{
		cursor:pointer;
	}
	.navi>li{
         float: left;        
         /* 가로로 */
         padding:0px;
         list-style-type: none;
         width: 50%;
         height: 50px;
         text-align: center;            
     }
     .navi>li>a{            
         color: white;
         text-decoration: none;
         display: block;      
         /* li 사이즈만큼 이벤트 범위 지정 */
         line-height: 50px;
         width: 100%;
         height: 100%;
     }
     .navi>li>a:hover{
         /* .navi>li>:hover{  이렇게해도 된다. */
         font-size: 1em;
         font-weight: bold;
     }
</style> 


<body>
<c:if test="${adminCheck == 'Y' }">
<!-- header start -->
<jsp:include page="../header/adminheader.jsp"></jsp:include>
<!-- header End -->

<!-- main contents start -->
<main>
<br><br>	
<div class="container">

	<br><br>
${sessionScope.loginMember.memberId}님 환영합니다!

	<div class="container text-center">
		<div class="row row-cols-1">
		   <div class="col" style="background-color: #5e5e5e; height:45px" vertical-align: middle;>도서리스트</div>        
		</div>
	<br>
		<div class="row row-cols-7" style="background-color:#e0e0e0; padding:20px">
		    <div class="col-2" style="text-align:right;">
		    	총도서<br>1&nbsp;&nbsp;&nbsp;
		    </div>
		    <div class="col"><img src="/resources/img/sidebar.png"></div>
		    <div class="col">심의중<br>1</div>
		    <div class="col"><img src="/resources/img/sidebar.png"></div>
		    <div class="col">승인<br>1</div>
		    <div class="col"><img src="/resources/img/sidebar.png"></div>
		    <div class="col-2" style="text-align:left;">&nbsp;&nbsp;보류<br>&nbsp;&nbsp;&nbsp;&nbsp;1</div>
		</div>
		
	</div>
	<br><br>
	
	<div class="container">

	<!-- 검색 -->
	<table align="center" class="table col-10" border="0px">
		<tr>
			<td style="border:none;">
				<div style="display: inline-block; margin: 0 5px;  float: left;">
				<h4 align="center">전체 도서 리스트</h4>
				</div>
				<!-- div 오른쪽 정렬 -->
				<div style="display: inline-block; margin: 0 5px;  float: right;">
				<form action="/admin/bookApprove/search.kh" method="get" >
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
		<c:if test="${!empty bList }">
			<c:forEach items="${bList }" var="notice" varStatus="i">
				<tr>
					<th>NO</th>
					<th>작가</th>
					<th>분류</th>
					<th>제목</th>
					<th>상태</th>
					<th>작성일</th>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty bList }">
			<tr>
				<td colspan="4" align="center"><b>데이터가 존재하지 않습니다.</b></td>
			</tr>
		</c:if>
			<tr align="center" height="20">
	            <td colspan="4" style="border:none;">
	                <c:if test="${currentPage != 1}">
	                    <a href="/admin/bookApprove/${urlVal }.kh?page=${currentPage - 1 }">[이전]</a>
	                </c:if>
	                <c:forEach var="p" begin = "${startNavi }" end="${endNavi }">
	                    <c:if test="${currentPage eq p }">
	                        <b>${p}</b> 
	                    </c:if>
	                    <c:if test="${currentPage ne p }">
	                        <a href = "/admin/bookApprove/${urlVal }.kh?page=${p }&searchCondition=${searchCondition }&searchValue=${searchValue }">${p}</a>
	                    </c:if>
	                </c:forEach>
	            <c:if test = "${currentPage < maxPage }">
	                <a href = "/admin/bookApprove/${urlVal}.kh?page=${currentPage + 1}">[다음]</a>
	            </c:if>
	            </td>
	        </tr>
		</table>

</div>
<br><br>
	
</div>
<br><br>

</main>
<!-- main contents End -->


<!-- 파일 업로드 스크립트 -->
<script>
//파일이름 출력용
function filename(obj) {
	var name = obj.files[0].name;
	var text = obj.previousElementSibling.previousElementSibling;
	text.value=name;
	
	imgCheck(obj);
	
	
}


///// 이미지파일 체크 시작 ////
var imgFile = document.querySelectorAll('.isFile');
var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/i;

function imgCheck(obj) {
for (var i = 0; i < imgFile.length; i++) {
	if (imgFile[i].value != "") {

		if (!imgFile[i].value.match(fileForm)) {
			alert("이미지 파일만 업로드 가능");
			imgFile[i].value = "";
			var text = obj.previousElementSibling.previousElementSibling
			text.value="";
			

		}
	}
}

};

</script>

<!-- Footer -->
<jsp:include page="../footer/footer.jsp"></jsp:include>
<!-- Footer -->
</c:if>
</body>
</html>