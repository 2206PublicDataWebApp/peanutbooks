<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>땅콩북스</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
<link rel="stylesheet" href="/resources/css/book/writerprofile.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>

<body>
	<jsp:include page="../header/header.jsp" />

	<main>
		<form action="/book/profileModify.do" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="removeImg">
			<section class="container">

				<article id="header-area">

					<div class="align-items-center">
						<div class="align-items-center">
							<img class="headerimg"
								src="/resources/bookImg/${writer.headPicRename }"> <label
								class="input-file-button float-md-start"> <img
								src="/resources/img/book/icons8-camera-96.png" alt=""
								id="camera1"> <input type="file" name="headerPicture"
								class="isFile" style="display: none"
								accept="image/jpeg, image/png, image/jpg"
								onchange="imgView(this);">
							</label>

						</div>

					</div>
					</div>


				</article>

				<article id="profile" class="row">
					<div class="profile-pic-area col-md-6">
						<div class="align-items-center">
							<div class="align-items-center" id="picParent">
								<img class="detailImg mx-auto"
									src="/resources/bookImg/${writer.mainPicRename }" id="proPic"> 
									<img
									src="/resources/img/book/icons8-camera-96.png" alt=""
									id="camera" onclick="picModifyMenu();"> 
									<!--  여기부터 사진 삭제/교체메뉴 -->
										<div id="profilePicMenu">
											<div id="menu1" onclick="removeProPic()">사진삭제</div>
											<label
											class="input-file-button float-md-start"> 
											
												<div id="menu2">사진수정</div>
											
												<input type="file" name="profilePicture"
												class="isFile" style="display: none"
												accept="image/jpeg, image/png, image/jpg"
												onchange="imgView1(this);">
										</label>
										</div><!-- 사진교체 종료 -->

							</div>

						</div>
					</div>


					<div class="col-md-6">
						<div id="wirter-name">
							<div id="name-info">작가명</div>
							<div id="name">${loginMember.mNickname }</div>
						</div>
						<div id="wirter-text">
							소개
							<textarea name="info" id="" rows="6" placeholder="소개를 입력하세요"
								required maxlength="500">${writer.info }</textarea>
							<button>수정</button>
						</div>
					</div>
				</article>
			</section>
		</form>

	</main>



	<jsp:include page="../footer/footer.jsp" />
	
</body>

<script src="/resources/js/book/writerprofile.js"></script>
<script type="text/javascript">
document.querySelector('#profilePicMenu').style.display='none';
</script>
<c:if test="${msg !=null }">
	<script>	
		alert('프로필등록완료');
	</script>
</c:if>



</html>