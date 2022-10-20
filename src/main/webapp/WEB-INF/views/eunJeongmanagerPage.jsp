<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매니저 페이지</title>
	<script src="../resources/js/jquery-3.6.0.min.js" ></script>
<style>
    #manaPage{
        font-family: 'Spoqa Han Sans Neo', 'sans-serif';
        padding-top:7rem;
        margin:0 auto;
        width:100%;       
        
        display: block;       
    }
    #manaPage nav{
        margin:0;      
        width:100%;        
        height: 100%;
        padding-left:30%;
        text-align: left;  
        border-bottom: 1px solid rgb(201, 196, 196);                    
    }  
    nav ul{
        width: 100%;
        height: 100%;
        display: inline;     

    }
    nav ul li{
    display: inline;
    list-style: none;   
    padding-left: 3rem;
    font-size: 1rem;
    color: darkgray;
    padding-bottom: 0.5rem;
    font-weight: 600;
    text-align: center;
}  
nav ul li a{
    text-align: center;
    text-decoration: none;
    color: inherit;
}
article{
    margin: 0%;
    margin-top: 3rem;    
}

    </style>
</head>
<body>

<header>
<jsp:include page="./header/header.jsp"></jsp:include>
</header>
    
    <main id="manaPage">
        <nav>	
            <ul >                
                <li id="pay"><a href="javascript:void(0);" onclick="pay();">결제조회</a></li>
                <li><a href="javascript:void(0);" onclick="peanet();">땅콩확인</a></li>
                <li><a href="javascript:void(0);" onclick="writerList();">작가정산</a></li>               
                <li><a href="javascript:void(0);" onclick="chatManager();">채팅확인</a></li>			
            </ul>
        </nav>	
        <div id="contain">
        </div>
    
    </main>
    
<jsp:include page="./footer/footer.jsp"></jsp:include>
<script>
//결제 리스트
function pay(){  
	$("#contain").load("/pay/start.kh");
}
//땅콩구매리스트
function peanet(){ 
	$("#contain").load("/peanet/list.kh");
}
//작가 정산리스트
function writerList(){ 
	$("#contain").load("/writer/list.kh");
}
//채팅 상담리스트
function chatManager(){ 
	$("#contain").load("/chat/move.kh");
}

</script>


 

</body>
</html>