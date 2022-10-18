<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작가 정산 요청</title>
<link rel="stylesheet" href="../resources/css/pay/writerPay.css">
<script
  src="https://code.jquery.com/jquery-3.6.1.js"
  integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
  crossorigin="anonymous"></script>
</head>
<body>
<section>
<h1>작가 정산 요청</h1>
<div>
	 <table>
            <tr>
                <td>도서번호 :</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>정산조회수 :</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>차감될 땅콩 :</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>지급금액 :</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>지급요청은행 :</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>계좌번호 :</td>
                <td><input type="text"></td>
            </tr>
        </table>
    </div>
    <div class="btn"><button>지급요청</button></div>   
</section>

</body>
</html>