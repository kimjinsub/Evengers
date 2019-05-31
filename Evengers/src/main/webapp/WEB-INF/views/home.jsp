<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>
	<script>
	window.onload=function(){
		var chk=${check};
		if(chk==1){
			alert("회원가입 성공");
		}
		if(chk==2){
			alert("로그인 실패");
		}
	}
</script>
</head>
<body>
<h1>
	Evengers!
</h1>

<a href="joinFrm">회원가입</a>
</body>
</html>
