<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
    crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/book/bookstep-style.css">
    <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
    <title>땅콩북스</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>



    
    <main>

    
        <section class="container">
            <article id="info-area" class="row">
                <div id="cover-area" class="col-md-6">
                <div id="book-title"> ${nBook.bookTitle } </div>
                </div>
                <div id="writer-area" class="col-md-6" >
                    <div id="header-area" class="d-md-block d-none">
                        
                    </div>
                    <div id="writer">
                        
                        <div id="name-area">
                            ${nBook.writer }
                        </div>
                        <div id="writer-info">
                            ${nBook.writerInfo }
                        </div>
                        <hr class="d-md-none d-md-inline"> 
                    </div>
                    <div id="wirter-history">
                        <ul>
                        <!-- 모든 작품 제목 반복문 -->
                        	<c:forEach items="${nbList}" var="nbList">
                            	<li>${nbList.bookTitle }</li> 
                            </c:forEach>
                        <!-- 작품제목 반복 종료 -->
                        </ul>
                    </div>
                </div>
                
                <hr class="d-md-none d-md-inline"> 
            </article>
        

       


    <article id="step-area" class="container">
    <!-- 시리즈 반복 시작 -->
    	<c:forEach items="${nsList }" var="nsList">
		        <div class="one-step row">
		            <div class="step col-md-2">
		                ${nsList.seriesNo }
		            </div>
		            <div class="step-main col-md-8">
		                <div class="step-title" onclick="readDetailSeries(${nsList.seriesNo },${nBook.bookNo })">
		                    ${nsList.title }
		                </div>
		                <div class="step-text" onclick="readDetailSeries(${nsList.seriesNo },${nBook.bookNo })">
		                    ${nsList.contents }
		                </div>
		                <div class="step-date" onclick="readDetailSeries(${nsList.seriesNo },${nBook.bookNo })">
		                    ${nsList.insertDate }
		                </div>
		            </div>
		            <div class="step-img col-md-2" onclick="readDetailSeries(${nsList.seriesNo },${nBook.bookNo })">
		                <img src="/resources/bookImg/${nsList.subpicRename }" alt="">
		            </div>

		        </div>
	        </c:forEach>
	        <!-- 시리즈 반복 종료 --> 
            
    </article>
</section>
    </main>
    
</body>

<script type="text/javascript">
document.querySelector('#cover-area').style.backgroundImage=" url('/resources/bookImg/${nBook.coverRename}')"
document.querySelector('#header-area').style.backgroundImage=" url('/resources/bookImg/${oneWriter.headPicRename}')"

</script>

<script src="/resources/js/book/bookStepNor.js"></script>
</html>