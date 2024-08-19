<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.sql.Date" %>  
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/DongGu/css/DongGu.css">
<%
//로그인 안했을 시 전 페이지로 돌아가게
String s_id =(String)session.getAttribute("sid");
if(s_id==null || s_id.equals("")){
	%>
	<script>
	window.alert('로그인이 필요한 작업입니다.');
	window.location.href="/DongGu/member/login.jsp";
	</script>
<%
}
%>

</head>
<body>
<%@include file="../SubHeader.jsp" %>
<form name="WriteQnABoard" action="/DongGu/qna/WriteQnABoard_ok.jsp">
	<div id="FreeBoardDivTitle2">
	    <div style="width:90%; left: 50%; text-align:center; "><span>QnA  작성 </span></div>
	    <div style="width:10%;">
	    	<span style="text-align: right;">
	        <input type="submit" value="등록" id="WriteFreeBoardSubmitButton">
	    </span>
	    </div>
	</div>

	<div id="WriteFreeBoardMainDiv">
		<input name="q_title" id="WriteFreeBoardTitleInput"  autofocus type="text" placeholder="제목을 입력해 주세요." required>
		<div id="WriteFreeBoardContentDiv">
			<textarea name="q_content" id="WriteFreeBoardContent" placeholder="내용을 입력하세요." required></textarea>
		</div>
	</div>
	
</form>
<%@include file="../Footer.jsp" %>
</body>
</html>