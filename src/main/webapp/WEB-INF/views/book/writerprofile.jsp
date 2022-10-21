<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>땅콩북스</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
    <link rel="stylesheet" href="/resources/css/book/writerprofile.css">
</head>

<body>
	<jsp:include page="../header/header.jsp" />

<main>
    <section class="container">

        <article id="header-area">

            <div class="align-items-center">
                <div class="align-items-center">
                    <img class="headerimg">
                    <label class="input-file-button float-md-start"> 
                        <img src="/resources/img/book/icons8-camera-96.png" alt="" id="camera1">
                         <input type="file" name="recipePicture" class="isFile" style="display: none"
                            accept="image/jpeg, image/png, image/jpg" onchange="imgView(this);">
                    </label>
    
                </div>
    
            </div>
        </div>


        </article>

        <article id="profile" class="row">
            <div class="profile-pic-area col-md-6">
                <div class="align-items-center">
                    <div class="align-items-center">
                        <img class="detailImg mx-auto">
                        <label class="input-file-button float-md-start"> 
                            <img src="/resources/img/book/icons8-camera-96.png" alt="" id="camera">
                             <input type="file" name="recipePicture" class="isFile" style="display: none"
                                accept="image/jpeg, image/png, image/jpg" onchange="imgView(this);">
                        </label>
        
                    </div>
        
                </div>
            </div>

            <div class="col-md-6">
                <div id="wirter-name">
                    <div id="name-info">
                        작가명
                    </div>
                    <div id="name">
                        이용자
                    </div>
                </div>
                <div id="wirter-text">
                    소개
                    <textarea name="" id=""rows="6" placeholder="소개를 입력하세요" required></textarea>
                    <button>등록</button>
                </div>
            </div>
        </article>
    </section>

</main>



	<jsp:include page="../footer/footer.jsp" />
    
</body>
<script src="/resources/js/book/writerprofile.js"></script>

</html>