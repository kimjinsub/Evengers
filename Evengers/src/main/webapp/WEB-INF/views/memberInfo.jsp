<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/all.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" /></script>

<title>memberInfo.jsp</title>
<style>
/* .info {
	width: 200px;
	height: 50px;
	line-height: 50px;
	margin: 10px;
	text-align: center;
} */
</style>

</head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<body>
 <div id="mHeader">
	<jsp:include page="mInfoHeader.jsp"></jsp:include>
</div> 


<!-- <div id="mInfo"name="mInfo"class="info">개인정보</div>
<div id="mInfoModify"class="info">개인정보 수정</div>
<div id="payList"class="info">구매내역</div>
<div id="receivedList"class="info">의뢰내역</div>
<div id="choiceList"class="info">찜목록보기</div>
 -->

  <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top" id="sideNav">
   <a class="navbar-brand js-scroll-trigger" href="#page-top">
      <span class="d-block d-lg-none">Clarence Taylor</span>
      <span class="d-none d-lg-block">
        <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="img/profile.jpg" alt="">
      </span>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" id="mInfo">개인정보</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" id="mInfoModify">개인정보 수정</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" id="payList">구매내역</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" id="receivedList">의뢰내역</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" id="choiceList">찜목록보기</a>
        </li>
      </ul>
    </div>
	</nav>
	
	<div id="mMain"></div>
  <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="/js/resume.min.js"></script>
</body>
<script>
$("#mMain").load("mInfo");
$('#mInfo').click(function(){
	$.ajax({
		url:"mInfo",
		dataType:"html",
		success:function(result){
			$("#mMain").html(result);
		},
		error:function(result){
			console.log(error);
		}
	})
});
$('#mInfoModify').click(function(){
	$.ajax({
		url:"mInfoModify",
		dataType:"html",
		success:function(result){
			$("#mMain").html(result);
		},
		error:function(result){
			console.log(error);
		}
	})
});
$('#payList').click(function(){
	$.ajax({
		url:"payList",
		dataType:"html",
		success:function(result){
			$("#mMain").html(result);
		},
		error:function(result){
			console.log(error);
		}
	})
});
$('#receivedList').click(function(){
	$.ajax({
		url:"receivedList",
		dataType:"html",
		success:function(result){
			$("#mMain").html(result);
		},
		error:function(result){
			console.log(error);
		}
	})
});
$('#choiceList').click(function(){
	$.ajax({
		url:"choiceList",
		dataType:"html",
		success:function(result){
			$("#mMain").html(result);
		},
		error:function(result){
			console.log(error);
		}
	})
});

</script>
</html>