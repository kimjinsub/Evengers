<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.all {
	width: 100%;
	border: 1px solid #000000;
	position: relative;
}

.searchimage {
	width: 100%;
}

.searchtext {
	padding: 10px 10px;
	text-align: center;
		position: absolute;
	top: 50%;
	left: 50%;
}
</style>
</head>
<body>
	<div class="all">
		<div class="searchimage">
			<img src="img/event.jpg" width="100%" height="350">
		</div>
		<div class="searchtext">
			<input type="text" width="600px" height="200px">
			<button>검색</button>
		</div>
	</div>
</body>
</html>