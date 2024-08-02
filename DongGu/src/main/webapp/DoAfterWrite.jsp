<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
<link rel="stylesheet" type="text/css" href="/DongGu/css/DongGu.css">
</head>
<body>
<%@include file="Header.jsp" %>

	<form name="">
	
		<div class="saveTableOne">
			<h3>동구체험후기</h3>
			<table>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="" id="" value="" class="saveWS250">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea class="saveWS250"></textarea>
					</td>
				</tr>
				<tr>
					<th>평점</th>
					<td>
						<select name="" class="saveWS250">
							<option value="">0</option>
							<option value="">0.5</option>
							<option value="">1</option>
							<option value="">1.5</option>
							<option value="">2</option>
							<option value="">2.5</option>
							<option value="">3</option>
							<option value="">3.5</option>
							<option value="">4</option>
							<option value="">4.5</option>
							<option value="">5</option>
						</select>
					</td>
				</tr>
			</table>
			<input type="submit" name="" id="" value="체험후기올리기" class="saveBtnOne">
		</div>
		
	</form>	
	
<%@include file="Footer.jsp" %>

</body>
</html>