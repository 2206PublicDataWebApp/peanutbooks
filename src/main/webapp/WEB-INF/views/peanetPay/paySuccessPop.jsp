<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제성공</title>
 <style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap');
        body{
           width: 380px;     
                                              
        }
        #box{
            width: 100%;
            height: 100%;
            margin-top: 1.5em;
            margin-left: 1.5em;
         }

         #inbox{            
            width: 100%;
            height: 100%;
            padding-top: 20%;
            padding-bottom: 20%;                                      
            border: 10px solid #ffca1a;
            border-radius: 0.5em;
            text-align: center;
            align-items: center;
            font-family: 'Noto Sans KR', sans-serif;
            font-size: large;            
         }
        
    </style>
</head>
<body>
	<div id="box">
    <div id="inbox">
    	<div>${sessionScope.loginMember.mNickname}님</div>
        <div>구독권 <strong style="color: red;font-size: 20px;">결제완료</strong>입니다.</div>
        <br>
        <div>만료기간은</div>
        <div><strong style="color: red; font-size: 30px;"> ${sessionScope.lastDate }</strong><br>까지 입니다</div>
        <br>
        <div>감사합니다.</div>
    </div>
    </div> 
</body>
</html>