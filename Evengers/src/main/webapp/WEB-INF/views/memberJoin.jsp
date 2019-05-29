<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="memberJoin" action="memberInsert" method="post"
		onsubmit="return check()">
		<table>
			<tr>
				<td colspan="3">일반 회원가입 페이지</td>
			</tr>
			<tr>
				<td width="100">이름</td>
				<td><input type="text" name="mname"></td>
			</tr>
			<tr>
				<td width="100">아이디</td>
				<td><input type="text" name="mid"></td>
				<td><button>중복확인</button></td>
			</tr>
			<tr>
				<td width="100">비밀번호</td>
				<td><input type="password" name="mpwd"></td>
			</tr>

			<tr>
				<td width="100">휴대폰번호</td>
				<td><input type="text" name="mphone"></td>
			</tr>
			<tr>
				<td width="100">주민등록번호</td>
				<td><input type="text" name="mrrn"></td>
			</tr>
			<tr>
				<td width="100">이메일주소</td>
				<td><input type="text" name="memail"></td>
				<td><select><option value="네이버">@naver.com</option>
						<option value="야후">@yahoo.co.kr</option></select></td>
			</tr>
			<tr>
				<td width="100">지역</td>
				<td><select><option value="서울">서울</option>
						<option value="인천">인천</option></select></td>
			</tr>

			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="회원가입"> 
					<input type="reset" value="다시작성"> 
					<input type="button" onclick="location.href='joinFrm'" value="홈으로"></td>
			</tr>
		</table>
	</form>

</body>
</html>