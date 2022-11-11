<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제성공</title>
<link rel="shortcut icon" href="/resources/img/icons8-book-32.png">
 <style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap');
body{
   width: 380px; 
   height: 250px;    
                                      
}
#box{
    width: 100%;
    height: 100%;
    margin-top: 1.5em;
    margin-left: 1.5em;
    border: 2px solid #ffca1a;
    border-radius: 0.5em;
 }
#line{
	width: 200px;
    margin: auto;
    text-align: center;
    border-bottom: 1px solid #ffca1a;
    margin-bottom: 1rem;
}
#inbox{            
   width: 100%;
   height: 60%;
   padding-top:1.2em;                                            
   text-align: center;
   align-items: center;
   font-family: 'Noto Sans KR', sans-serif;
   font-size: large;                         
}
  
#date{
	width:99%;
    height: 30%;
    background-color : #ffca1a; 
    border: 2px solid #ffca1a;
    border-radius: 0 0 2.2em 2.2em;
    display: flex;
	justify-content: center;   
	align-items: center;
	font-size: 30px;
	font-weight: 600;
	color: #706c6c;

}
   
</style>
</head>
<body>
	<div id="box">
    <div id="inbox">
    	<div>${sessionScope.loginMember.mNickname}님</div>
        <div id="line">감사합니다.</div>
        <div style="margin-bottom:1rem;">구독권 <strong style="color: red;font-size: 20px;">결제완료</strong>입니다.</div>
        
        <div>만료기간은</div>
    </div>
    <div id="date">${sessionScope.lastDate }</strong></div>       
    </div> 
</body>
</html>