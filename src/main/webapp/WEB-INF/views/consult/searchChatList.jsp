<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 채팅 상담 리스트</title>
<link rel="stylesheet" href="../resources/css/chat/consultingList.css" ></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
</head>
<body>
<div id="header">
<jsp:include page="../header/adminheader.jsp"></jsp:include>
</div>
<!--관리자일 경우 들어갈수 있도록 다시한번 체크  -->
<br>
<main>
<div>	
   <select>
   	<option name="memberId">고객ID 조회</option>
   	<option name="date">날짜별 조회</option>   	
   </select>  
   <input type="text"/ pleasholder="">   

</div>
<div>
    <div>                             
       <div id="pagename" align="center">채팅상담리스트</div>
       <div class="table-responsive">
            <table class="table table-striped table-hover" border="1"  id="togglePart" style="display:none;">
                <thead class="table-light">
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">고객ID</th>
                        <th scope="col">문의주제</th>
                        <th scope="col">신청시간</th>
                        <th scope="col">상담결과</th>
                        <th scope="col"></th>                
                    </tr>
                </thead>
                <tbody></tbody>
               </table>
		</div>
	</div>

</main>
	<script>
		
	</script>
</body>
</html>
