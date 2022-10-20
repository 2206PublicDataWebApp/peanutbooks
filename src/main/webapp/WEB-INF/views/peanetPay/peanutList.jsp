<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>땅콩사용리스트</title>
<link rel="stylesheet" href="../resources/css/pay/peanetList.css" ></link>
<script scr="../resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>땅콩 이용 내역</h1>
    <div id="spanDIV">        
        <div class="totalpeanet">남아 있는 땅콩 : </div>
        <div class="totalpeanet">96</div>        
    </div>
    <div id="table">
        <table >            
            <tr>
                <td class="contexnts">땅콩충전</td>
                <td rowspan="2"></td>
                <td rowspan="2" class="peanet">100</td>
            </tr>
            <tr>               
                <td class="date">2022.10.12 12:22:15</td>                
            </tr>
            <tr class="line"></tr>
        </table>
        <table >            
            <tr>
                <td class="contexnts">a도서</td>
                <td rowspan="2"></td>
                <td rowspan="2" class="peanet">-1</td>
            </tr>
            <tr>               
                <td class="date">2022.10.12 12:30:15</td>                
            </tr>
            <tr class="line"></tr>
        </table>
        <table >            
            <tr>
                <td class="contexnts">a도서</td>
                <td rowspan="2"></td>
                <td rowspan="2" class="peanet">-1</td>
            </tr>
            <tr>               
                <td class="date">2022.10.12 12:30:15</td>                
            </tr>
            <tr class="line"></tr>
        </table>
        <table >            
            <tr>
                <td class="contexnts">b도서</td>
                <td rowspan="2"></td>
                <td rowspan="2" class="peanet">-1</td>
            </tr>
            <tr>               
                <td class="date">2022.10.13 13:30:15</td>                
            </tr>
            <tr class="line"></tr>
        </table>
        <table >            
            <tr>
                <td class="contexnts">c도서</td>
                <td rowspan="2"></td>
                <td rowspan="2" class="peanet">-1</td>
            </tr>
            <tr>               
                <td class="date">2022.10.17 06:30:15</td>                
            </tr>
            <tr class="line"></tr>
        </table>
    </div>

    <div class="page_wrap">
        <div class="page_nation">
           <a class="arrow pprev" href="#"><<</a>
           <a class="arrow prev" href="#"><</a>
           <a href="#" class="active">1</a>
           <a href="#">2</a>
           <a href="#">3</a>
           <a href="#">4</a>
           <a href="#">5</a>           
           <a class="arrow next" href="#">></a>
           <a class="arrow nnext" href="#">>></a>
        </div>
     </div>
</body>
</html>