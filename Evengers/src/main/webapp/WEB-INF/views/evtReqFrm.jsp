<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<h1>의뢰신청서</h1>
	<form>
		<table border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="req_title"></td>	
			</tr>
			
			<tr>
				<td>이벤트 카테고리</td>
				<td><div id="selectZone" name="ec_name"></div></td>
			</tr>
			
			<tr>
        		<td>사진첨부</td>
         		<td><input type="file" name="reqi_orifilename"></td>
      		</tr>
      		
			<tr>
				<td>희망지역</td>
				<td><input type="text" name="req_hopearea"></td>
				<td><button>지역검색</button></td>
			</tr>
			
			<tr>
				<td>상세주소</td>
				<td><input type="text" name="req_hopeaddr"></td>
			</tr>
			
			<tr>
				<td>의뢰 날짜 및 시간</td>
				<td><input type="text" name="req_hopedate"></td>
			</tr>
			
			
			<tr>
				<td>글내용</td>
				<td><textarea name="req_contents" rows="20"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="의뢰신청하기">
				<input type="button" onclick="location.href='./'" value="홈으로"></td>
			</tr>
		</table>
	</form>

</body>

<script>

$(document).ready(function(){
	   selectCategory();
	});
	
	
function selectCategory(){
    $.ajax({
       url:"selectCategory",
       dataType:"json",
       success:function(result){
          console.log(result);
          var str="";
          str+="<select name='e_category'><option selected='selected'>선택하세요</option>";
          for(var i in result){
             str+="<option value='"+result[i].ec_name+"'>"+result[i].ec_name+"</option>";
          }
          str+="</select>";
          $("#selectZone").html(str);
       },
       error:function(error){
          console.log(error);
       }
    })
 };
</script>
</html>