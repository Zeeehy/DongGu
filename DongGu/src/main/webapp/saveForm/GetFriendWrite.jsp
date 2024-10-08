<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.DongGu.friend.FriendDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/DongGu/css/DongGu.css">
<jsp:useBean id="fdao" class="com.DongGu.friend.FriendDAO"></jsp:useBean>

<%
String s_id = (String) session.getAttribute("sid");
if (s_id == null || s_id.equals("")) {
%>
<script>
		window.alert('로그인 후 접속가능합니다.');
		window.location.href="/DongGu/member/login.jsp";
		</script>
<%
}
String animalName = "";

//동물정보 기본 셋팅(로그인정보로)
if (s_id != null && !s_id.equals("")) {
animalName = fdao.animalName(s_id, 0);
}
%>
<script>
	function animalChange(myani_num, updateMode){

	    /*
		// 타입 확인
	    if (typeof myani_num === 'string') {
	    	alert('aaaa는 문자열입니다.');
	    } else if (typeof myani_num === 'number') {
	    	alert('aaaa는 숫자입니다.');
	    } else {
	    	alert('aaaa는 문자열도 숫자도 아닙니다.');
	    }
	    */

        var selectElement = document.getElementById('ai_num');
        var options = selectElement.options;
        var ai_num, val_img, val_birth, val_link, val_aler, val_dise, val_caut;

        //select box option수만큼 돌리기
        for (var i = 0; i < options.length; i++) {

        	//option의 value가 myani_num와 동일 한 것 찾기
            if (options[i].value == myani_num) {

            	//option의 각 설정해준 값을 변수에 담기
            	ai_num = options[i].getAttribute('value');		//animalinfo테이블의 기본키
            	
            	//alert(ai_num);
            	val_name = options[i].getAttribute('val_name');	//animalinfo테이블의 동물이름
            	val_img = options[i].getAttribute('val_img');
                val_birth = options[i].getAttribute('val_birth');
                
                val_link = options[i].getAttribute('val_link');
                val_an_nature = options[i].getAttribute('val_an_nature');
                
                // 콤마로 문자열 나누기
                let nature_parts = val_an_nature.split(",");
                
                var val_an_nat = "";
                for(let i=0; i<nature_parts.length; i++){
	                val_an_nat += '<span class="animal_nature">'+nature_parts[i].trim()+"</span>"; // 공백 제거
				}
                
                val_aler = options[i].getAttribute('val_aler');
                val_dise = options[i].getAttribute('val_dise');
                val_caut = options[i].getAttribute('val_caut');
                
                val_anitype_num = options[i].getAttribute('val_anitype_num');	//animalinfo테이블, animaltype테이블의 at_num
                val_anitype_name = options[i].getAttribute('val_anitype_name');	//animaltype테이블의 at_name	(시츄, 푸들등)
                val_ani_num = options[i].getAttribute('val_ani_num');	//animaltype테이블, animal테이블의 a_num
                val_ani_name = options[i].getAttribute('val_ani_name');	//animal테이블의 a_name (강아지, 고양이 등)
            }
        }

        //해당 페이지의 td id 값 변수로 설정해주기
        var data_ani_name = document.getElementById('data_ani_name');
        var data_anitype_name = document.getElementById('data_anitype_name');
        
        var data_ani_img = document.getElementById('data_ani_img');
        var data_ani_birth = document.getElementById('data_ani_birth');
        var data_ani_alerg = document.getElementById('data_ani_alerg');
        var data_ani_disea = document.getElementById('data_ani_disea');
        var data_ani_caut = document.getElementById('data_ani_caut');

        //변수로 설정한 td id의 textContent를 
        //select box option을 통해 넘어온 값으로 바꿔주기
        data_ani_name.textContent = val_ani_name;
        data_anitype_name.textContent = "("+val_anitype_name+")";

        data_ani_img.innerHTML = "<img src='/DongGu/img/animal/"+val_img+"' class='writeImgSize'>";        data_ani_birth.textContent = val_birth;

        data_ani_alerg.textContent = val_aler;
        if(val_aler == "null" || val_aler == "" ){
            data_ani_alerg.textContent = "알레르기 없음";
		}
        
        data_ani_disea.textContent = val_dise;
        if(val_dise == "null" || val_dise == "" ){
        	data_ani_disea.textContent = "병력사항 없음";
		}
        
        data_ani_caut.textContent = val_caut;
        if(val_caut == "null" || val_caut == "" ){
        	data_ani_caut.textContent = "주의사항 없음";
		}
   	
        data_ani_nature.innerHTML = val_an_nat;
	}
	
	// 현재 날짜를 YYYY-MM-DD 형식으로 가져오기
	function getCurrentDate() {
	    const today = new Date();
	    const year = today.getFullYear();
	    const month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
	    const day = String(today.getDate()).padStart(2, '0');
	    return `${year}-${month}-${day}`;
	}
	
	// 현재 날짜를 min 속성으로 설정
	document.getElementById('i_start').setAttribute('min', getCurrentDate());
	document.getElementById('i_end').setAttribute('min', getCurrentDate());
	
	//저장전 데이터 존재여부파악
	function datacheck(){
		if(document.getElementById('i_title').value.trim() === ""){
			alert("초대제목을 입력해주세요.");
			document.getElementById('i_title').focus();
			return false; // 폼 제출을 막습니다.
			
		}else if(document.getElementById('i_start').value.trim() === ""){
			alert("시작일을 입력해주세요.");
			document.getElementById('i_start').focus();
			return false;
			
		}else if(document.getElementById('i_end').value.trim() === ""){
			alert("종료일을 입력해주세요.");
			document.getElementById('i_end').focus();
			return false;
			
		}else if(document.getElementById('ai_num').value.trim() === ""){
			alert("동물명을 선택해주세요.");
			document.getElementById('ai_num').focus();
			return false;
			
		}else if(document.getElementById('i_content').value.trim() === ""){
			alert("추가내용을 입력해주세요.");
			document.getElementById('i_content').focus();
			return false;
		}
	}
</script>
<%
// 현재 날짜를 가져와서 "yyyy-MM-dd" 형식으로 바꿔줌
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String today = sdf.format(new Date());

int a_num = 0;
int ai_num = 0;
String w_title = "";
String w_start = "";
String w_end = "";
String w_content = "";

String iNumStr = request.getParameter("i_num");
String btnValue = "초대하기";
int btnValue_int = 0;

int i_nums = 0;
//마이페이지에서 넘어온 경우 iNumStr(  request.getParameter("i_num")  ) 값 존재
//수정모드
if (iNumStr != null && !iNumStr.isEmpty()) {

	btnValue = "초대장 수정하기";
	btnValue_int = 1;

	i_nums = Integer.parseInt(iNumStr);
	//int i_nums = Integer.parseInt(request.getParameter("i_num"));

	//마이페이지에서 수정하기 클릭시
	ArrayList<FriendDTO> arr = fdao.getFriendData(i_nums);

	if (arr == null || arr.size() == 0) {
%>
<script>
			window.alert('데이터가 존재하지 않습니다. 관리자에게 문의하세요.');
	        history.back(); // 이전 페이지로 돌아가기
			</script>
<%
} else {
//System.out.println(arr.size());
for (int i = 0; i < arr.size(); i++) {

	a_num = arr.get(i).getI_num();
	ai_num = arr.get(i).getAi_num();
	w_title = arr.get(i).getI_title();

	String startDateTime = arr.get(i).getI_start();
	String endDateTime = arr.get(i).getI_end();

	//시, 분, 초 자르고 날짜만 변수에 저장
	w_start = startDateTime.substring(0, 10);
	w_end = endDateTime.substring(0, 10);

	w_content = arr.get(i).getI_content();
}

//동물정보 셋팅 변경(로그인정보 +마이페이지에서 넘어온 초대장키를 기준으로 동물정보 select box selected 시키기
animalName = fdao.animalName(s_id, ai_num);
%>
<script>
				//animalChange(<%=ai_num%>);
				//updateModeAnimalInfo(<%=ai_num%>);
			</script>
<%
}
//초대장 처음 등록시
} else {

a_num = 0;
ai_num = 0;
w_title = "";
w_start = today;
w_end = today;
w_content = "";
}
%>
<script>
function validateDates(startend) {
    // 날짜 입력 요소 가져오기
    const startDate = document.getElementById('i_start').value;
    const endDate = document.getElementById('i_end').value;

    // 날짜 입력 값이 모두 입력되었는지 확인
    if (!startDate || !endDate) {
        alert('시작 날짜와 종료 날짜를 모두 입력하세요.');
        return;
    }

    // 날짜를 Date 객체로 변환
    const start = new Date(startDate);
    const end = new Date(endDate);

    // 종료 날짜가 시작 날짜보다 늦은지 확인
    if (end < start) {
        alert('종료 날짜는 시작 날짜보다 늦어야 합니다. 날짜를 다시 입력해주세요. 오늘날짜로 다시 셋팅됩니다.');
        
        if(startend == 'start'){
        	document.getElementById('i_start').value = '<%=today%>';
        	document.getElementById('i_start').focus();
        }else {
        	document.getElementById('i_end').value = '<%=today%>';
        	document.getElementById('i_end').focus();
        }
        
    }
}

window.onload = function() {
    // 페이지 로드 후 종료 날짜 input에 이벤트 리스너 추가
    document.getElementById('endDate').addEventListener('change', validateEndDate);
    
};
</script>

</head>
<body>
	<%@include file="../SubHeader.jsp"%>

	<form name="getWrite" action="GetWrite_ok.jsp"
		onsubmit="return datacheck();">
		<div class="saveTableOne">
			<h3>초 대 합 니 다</h3>

			<!-- ok파일에서 필요 -->
			<input type="hidden" name="update_yn" id="update_yn"
				value="<%=btnValue_int%>">
			<!-- 수정모드인지 등록모드인지 파악하기 위함 -->
			<input type="hidden" name="i_nums" id="i_nums" value="<%=i_nums%>">
			<!-- 초대장의 기본키값 -->
			<table>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" class="tac">
						<input type="text" name="i_title"
						id="i_title" value="<%=w_title%>" class="saveWS50p tac f16 fw500"
						placeholder="초대 제목을 입력해주세요.">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="tac">
						<input type="date" name="i_start"
						id="i_start" value="<%=w_start%>" class="saveWS20p tac"
						min="<%=today%>" onchange="validateDates('start')"> ~ <input type="date" name="i_end"
						id="i_end" value="<%=w_end%>" class="saveWS20p tac"
						min="<%=today%>" onchange="validateDates('end')">
					</td>
					

					
					
				</tr>
				<tr>
					<td></td>
				</tr>


				<!-- 
				<tr>
					<th colspan="4" class="saveMtitle">동물정보</th>
				</tr>
				 -->
			</table>
			<table class="animalBox">

				<tr>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" class="tac" >
						<select name="ai_num" id="ai_num"
						class="saveWS50p tac f16 fw500" onchange="animalChange(this.value);">
							<option value="">동물이름을 선택해주세요. 아래에 동물정보가 보여집니다.</option>
							<%=animalName%>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tar">
						<span id="data_ani_name">동물종류</span>
						<span id="data_anitype_name">(품종)</span>
					</td>
					<td id="data_ani_img" rowspan="6">사진</td>
				</tr>
				<tr>
					<td id="data_ani_birth" class="tar">생일</td>
				</tr>
				<tr>
					<td class="tar">
						<div id="data_ani_nature">성격</div>
					</td>
				</tr>
				<tr>
					<td id="data_ani_alerg" class="tar">알레르기</td>
				</tr>
				<tr>
					<td id="data_ani_disea" class="tar">병력사항</td>
				</tr>
				<tr>
					<td class="tar">
						<textarea class="saveWS80p saveHS100 read_ta" id="data_ani_caut"
							disabled>주의사항</textarea>
					</td>
				</tr>
				<%
				if (s_id != null && !s_id.equals("")) {

					String ownerData2 = fdao.ownerData(s_id);
					String[] owner_arr = ownerData2.split("//");

					String ownerAddr = owner_arr[0];
					String ownerHouse = owner_arr[1];
				%>
				<tr>
					<td colspan="2" class="tac">
						<%=ownerAddr%>
						(<%=ownerHouse%>)
					</td>
				</tr>
				<%
				}
				%>

			</table>
			<table>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>
						<textarea class="saveWS95p saveHS100 write_ta"
							placeholder="추가할 내용을 적어주세요." name="i_content" id="i_content"><%=w_content%></textarea>
					</td>
				</tr>
			</table>
			<div class="saveWS100p tac">
				<input type="submit" name="" id="" value="<%=btnValue%>" class="saveBtnOne">
			</div>
		</div>

	</form>

	<!-- 
	<form name="getWriteAnimal" action="GetWriteAnimal_ok.jsp">
		<input type="text" name="my_animal_key" id="my_animal_key" value="">
		
		<input type="text" name="write_title" id="write_title" value="">
		<input type="text" name="write_start" id="write_start" value="">
		<input type="text" name="write_end" id="write_end" value="">
		<input type="text" name="write_content" id="write_content" value="">
		
	</form>
	 -->

	<script>
	//0.1초 뒤에 select box 선택된 동물이름 기준으로 데이터 뿌려주기
	setTimeout(function() {
	    var aiNum = "<%=ai_num%>
		";
			if (aiNum) {
				animalChange(aiNum);
			}
		}, 100); // 100ms 대기

		/*
		function updateModeAnimalInfo(){

		    var update_ai_num = document.getElementById('update_ai_num').value;
		    alert(update_ai_num);

			animalChange(update_ai_num);
		}
		 */
	</script>

	<%@ include file="../Footer.jsp"%>

</body>
</html>