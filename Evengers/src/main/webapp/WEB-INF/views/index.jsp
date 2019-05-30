<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#top{
 width:100%;
 height:10%;
 background-color:#F2F2F2;
}
#search{

 width:100%;
 height:30%;
}
#category{
width:100%;
 height:10%;
 background-color:#F2F2F2;
 text-align: center;
}
</style>
</head>

<body>
<div id="top">
 <jsp:include page="top.jsp"></jsp:include>
</div>
<div id="search">
<jsp:include page="search.jsp"></jsp:include>
</div>
<div id="category">
<jsp:include page="category.jsp"></jsp:include>
</div>
</body>

</html>