<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/DongGu.css">
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css'>
<link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
</head>
<body>
<%
String sid = (String)session.getAttribute("sid");
String sname = (String)session.getAttribute("sname");
String snickname = (String)session.getAttribute("snickname");
%>
<div class="wrapper"> <!-- wrapper 시작 -->
<header class="dg_hd">
	<h1>

		<a href="/DongGu/Index.jsp"><img src="/DongGu/img/logo.png" alt="logo"></a>

	</h1>
	<nav>
		<ul>
			<li>
				<a href="#"><span>초대장</span></a>
			</li>
			<li>
				<a id="board" href="/DongGu/free/FreeBoard.jsp"><span>게시판  <i style="margin-left: 5px;" class="fa fa-caret-down" aria-hidden="true"></i></span></a>
				<div id="s-d" class="dropdown">
					<p><a href="/DongGu/notice/Notice.jsp">공지사항<img src="/DongGu/img/icon_dog.png"></a></p>
					<p><a href="/DongGu/free/FreeBoard.jsp">자유게시판<img src="/DongGu/img/icon_dog.png"></a></p>
					<p><a href="/DongGu/qna/QnABoard.jsp">QnA<img src="/DongGu/img/icon_dog.png"></a></p>
				</div>
			</li>
			<li>
				<a href="/DongGu/DongGuPlace.jsp"><span>동구의 추천</span></a>
			</li>
			</li>
			<li>
				<a href="/DongGu/AfterList.jsp"><span>이용후기</span></a>
			</li>
			<li>
				<a href="#"><span>고객센터</span></a>
			</li>
		</ul>
	</nav>
	<div class="util">
	<%if(sname == null){
	%>
	<a href="/DongGu/member/login.jsp">
		<button type="button" class="login"></button>
	</a>
	<%
	}else{
		%>
		<div>
		 <%= snickname %>님 로그인중 | <a href="/DongGu/member/logout.jsp">로그아웃</a>
		</div>
		<%
	} %>
		
	</div>
</header>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script>
$(" #board ").click(function () {
	$(" #s-d ").toggleClass('show');
})
</script>
</body>
</html>
			<li>
				<a href="#"><span>초대장</span></a>
			</li>
			<li>
				<a id="board" href="/DongGu/free/FreeBoard.jsp"><span>게시판  <i style="margin-left: 5px;" class="fa fa-caret-down" aria-hidden="true"></i></span></a>
				<div id="s-d" class="dropdown">
					<p><a href="/DongGu/notice/Notice.jsp">공지사항<img src="/DongGu/img/icon_dog.png"></a></p>
					<p><a href="/DongGu/free/FreeBoard.jsp">자유게시판<img src="/DongGu/img/icon_dog.png"></a></p>
					<p><a href="/DongGu/qna/QnABoard.jsp">QnA<img src="/DongGu/img/icon_dog.png"></a></p>
				</div>
			</li>
			<li>
				<a href="/DongGu/DongGuPlace.jsp"><span>동구의 추천</span></a>
			</li>
			</li>
			<li>
				<a href="/DongGu/AfterList.jsp"><span>이용후기</span></a>
			</li>
			<li>
				<a href="#"><span>고객센터</span></a>
			</li>
		</ul>
	</nav>
	<div class="util">
	<%if(sname == null){
	%>
	<a href="/DongGu/member/login.jsp">
		<button type="button" class="login"></button>
	</a>
	<%
	}else{
		%>
		<div>
		 <%= snickname %>님 로그인중 | <a href="/DongGu/member/logout.jsp">로그아웃</a>
		</div>
		<%
	} %>
		
	</div>
</header>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script>
$(" #board ").click(function () {
	$(" #s-d ").toggleClass('show');
})
</script>
</body>
</html>