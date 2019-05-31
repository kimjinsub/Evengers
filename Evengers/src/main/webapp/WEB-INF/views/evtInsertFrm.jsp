<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 상품 등록</title>
<style type="text/css">
   input.option-add {
    background-image:url("img/plus.png") ;
    width: 25px;
    height: 25px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
   <form action="evtInsert"  method="post" enctype="multipart/form-data">
      <table border="1" width="400" align="center">
      <tr>
         <td colspan="2" align="center">이벤트 상품 등록</td>
      </tr>
      <tr>
         <td>상품명</td>
         <td><input type="text" name="e_name"></td>
      </tr>
      <tr>
         <td>가격</td>
         <td><input type="number" name="e_price"></td>
      </tr>
      <tr>
         <td>이벤트 카테고리</td>
         <td><div id="selectZone"></div></td>
      </tr>   
      <tr>
         <td>옵션</td>
         <td><div id="add"><input type="text" class="option_name" placeholder="내용">
         <input type="number" class="option_price" placeholder="가격"></div>
         <input type="button" onclick='addOption()' class="option-add" id="e_option"></td>
      </tr>
      
      <tr>
         <td>예약가능일</td>
         <td><input type="number" name="e_reservedate"></td>
      </tr>      
      <tr>
         <td>환불가능일</td>
         <td><input type="number" name="e_refunddate"></td>
      </tr>   
      <tr>
         <td>썸네일사진</td>
         <td><input type="file" name="e_orifilename" ></td>
      </tr>
      <!-- <tr>
         <td>이벤트 첨부 사진</td>
         <td><input type="file" name="ei_files" id="ei_files" onchange="fileChk(this)" multiple/>
         <input type="hidden" id="fileCheck" value="0" name="fileCheck"></td>
      </tr>    -->       
      <tr>
         <td>글 내용</td>
         <td><textarea name="e_contents" id="e_contents" rows="20"></textarea></td>
      </tr>   
      <tr>
         <td colspan="2" align="center">
         <input type="submit" value="작성완료">
         <input type="button" onclick="location.href='/evengers/'" value="홈으로">
         <input type="reset" id="rs" value="다시작성"> </td>
      </tr>
      </table>
   </form>
</body>
<script>

$(document).ready(function(){
   selectCategory();
});
$('#rs').on('click', function() {
   console.log("empty file");
   $("#fileCheck").val(0);
});

/* function fileChk(elem) { //파일태그가 넘어오는지 확인.
   console.dir(elem);
   if (elem.value == "") {
      console.log("empty file");
      $("#fileCheck").val(0); //파일첨부x
   } else {
      console.log("not empty");
      $("#fileCheck").val(1); //파일첨부o
   }

} //End fileChk */
var i=1;
function addOption(){
   var str="";
   str+="<div><input type='text' class='option_name' placeholder='내용'>"
      +"<input type='number' class='option_price' placeholder='가격'></div>";
   $('#add').append(str);
};

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

/* function confirm(){ //옵션 보내기
    var str="";
     var num="";
     $(".option_name").each(function(){
         str+=$(this).val()+",";
      })
      $(".option_price").each(function(){
         num+=$(this).val()+",";
      })
      $("#add").append('<input type="hidden" name="eo_name"/>'
            +'<input type="hidden" name="eo_price"/>');
      $("input[name=eo_name]").val(str);
      $("input[name=eo_price]").val(num);
      console.log($("input[name=eo_price]").val());
      if($("input[name=eo_name]").val()==null || $("input[name=eo_name]").val() == null){
         return false;
      }
      return true;
} */ 

</script>
</html>