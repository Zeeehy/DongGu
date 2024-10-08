<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import ="java.io.*"%>
<jsp:useBean id="dto" class="com.DongGu.board.FreeBoardDTO"></jsp:useBean>
<jsp:useBean id="dao" class="com.DongGu.board.FreeBoardDAO"></jsp:useBean>

<%
request.setCharacterEncoding("utf-8");

//사진이 있다면 사진 처리
String uploadPath = request.getRealPath("/") + "img/free/temp"; // 임시 저장 경로
String savepath = request.getRealPath("/")+"img/free/";

//System.out.println(savepath);
MultipartRequest mr = new MultipartRequest(request,uploadPath,10*1024*1024,"utf-8");
String originalFileName = mr.getFilesystemName("f_img");
String newFileName="";
//사진이 첨부됐으면
if (originalFileName != null) {
	String jpgType = "";//확장자 추출
	int lastDotIndex = originalFileName.lastIndexOf(".");
	if (lastDotIndex > 0) {
		jpgType = originalFileName.substring(lastDotIndex); // 예: ".jpg"
	}

	//새 파일 이름 생성 (확장자 유지)
	newFileName = "free"+dao.getSequnceNumForImg() + jpgType; // 예: "free1.jpg"

	// 파일 경로 객체 생성
	File tempFile = new File(uploadPath, originalFileName);
	File finalFile = new File(savepath, newFileName);

	// 파일이 존재하면 삭제
    if (finalFile.exists()) {
        if (!finalFile.delete()) {
            throw new IOException("기존 파일 삭제에 실패했습니다: " + finalFile.getName());
        }
        //System.out.println("기존 파일 삭제 완료: " + finalFile.getName());
    }

    // 파일 이름 변경 (이동)
    if (tempFile.renameTo(finalFile)) {
        //System.out.println("파일이 성공적으로 이동되었습니다: " + newFileName);
    } else {
        //System.out.println("파일 이동에 실패했습니다: " + originalFileName);
    }
}





dto.setF_content(mr.getParameter("f_content"));
dto.setF_title(mr.getParameter("f_title"));
dto.setF_img(newFileName);

dto.setF_id((String)session.getAttribute("sid"));
dto.setF_nickname((String)session.getAttribute("snickname"));


int result = dao.WriteFreeBoard(dto);

//오류
if(result==0){
	%>
	<script>
	window.alert('[오류] 작성실패');
	window.location.href="/DongGu/free/FreeBoard.jsp";
	</script>
<%
}else if(result>=1){
	%>
	<script>
	window.alert('작성완료!');
	window.location.href="/DongGu/free/FreeBoard.jsp";
	</script>
<%
}
%>