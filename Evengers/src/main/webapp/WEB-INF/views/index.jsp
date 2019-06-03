<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
#evt{
 width:100%;
 height:400px;
 background-color:aqua; }
 .evtList{
  width:150px;
  height: 100px;
  margin:40px;
  background-color: gray; 
  display: inline-block;
  float:left;
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
</div>
<div id="evt">

</div>
</body>
<script>
evt_category("getEvtCategory","#category");
//evt_getEvtList("getEvtList","#evt",$("#ec_name").val());
function evt_category(url,position){
	$.ajax({
		url:url,
		dataType:"html",
		success:function(result){
			//console.log(result);
			$(position).html(result);
		},
		error:function(error){
			console.log(error);
		}
	})
}
function evt_getEvtList(category){
	
	$.ajax({
			url : "getEvtList",
			data : {
				ec_name : category
			},
			dataType : "json",
			success : function(result) {
				var str = "";
				for ( var i in result) {
					console.log(result)
					str+="<div class='evtList' id='"+result[i].e_code+"'>"
					+"이벤트이름:"+result[i].e_name+"사진이름:"+result[i].e_sysfilename
					+"</div>"
					$("#evt").html(str);
				}
			},
			error : function(error) {
				console.log(error);
			}
		});
	}
</script>
</html>