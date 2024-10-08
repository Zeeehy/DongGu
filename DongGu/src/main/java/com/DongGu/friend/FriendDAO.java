package com.DongGu.friend;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

import com.DongGu.board.NoticeDTO;
import com.DongGu.cafe.CafeDTO;

public class FriendDAO {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public static final int NOT_ID = 1;
	public static final int NOT_PWD = 2;
	public static final int LOGIN_OK = 3;
	public static final int ERROR = -1;

	public FriendDAO() {
		System.out.println("FriendDAO 객체 생성됨! ");
	}
	
	public int FriendOwnerWrite(FriendDTO fto) {
		try {
			conn = com.DongGu.db.DongGuDB.getConn();
			String sql = "insert into invitation values(seq_invitation_i_num.nextval, ?,?,?, ?,?,?, sysdate)";

			ps = conn.prepareStatement(sql);

			ps.setString(1, fto.getI_title());
			ps.setInt(2, fto.getAi_num());
			ps.setInt(3, 1); // matchingstate 테이블의 1(매칭대기)로 자동설정

			ps.setString(4, fto.getI_content());
			ps.setString(5, fto.getI_start());
			ps.setString(6, fto.getI_end());
			
			System.out.println("insert into invitation values(PK_invitation.nextval, ?,?,?, ?,?,?, sysdate)");
			System.out.println(fto.getI_title());
			System.out.println(fto.getAi_num());
			System.out.println(1);
			System.out.println(fto.getI_content());
			System.out.println(fto.getI_start());
			System.out.println(fto.getI_end());
			

			String dd = "insert into invitation values(seq_invitation_i_num.nextval, ?,?,?, ?,?,?, sysdate)";
			dd += fto.getI_title();
			dd += fto.getAi_num();
			dd += 1;
			dd += fto.getI_content();
			dd += fto.getI_start();
			dd += fto.getI_end();
			
			int count = ps.executeUpdate();
			return count;
			//return dd;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
			//return "nono";
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}
		}
	}

	/** 초대장(고용자글쓰기) - 고용자주소, 고용자자택주소 데이터 불러오기 */
	public String ownerData(String s_id) {

		try {
			conn = com.DongGu.db.DongGuDB.getConn();
			String sql = "select * from owner where o_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, s_id);
			rs = ps.executeQuery();

			String ownerAddr = "";
			String ownerHouse = "";

			if (rs.next()) {
				ownerAddr = rs.getString("o_addr");
				ownerHouse = rs.getString("o_house");
			}

			return ownerAddr + "//" + ownerHouse;

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {

			}
		}
	}

	/** 초대장(고용자글쓰기) > 동물정보 select box > 동물정보 select box > option 에 담기 */
	public String animalName(String s_id, int update_val) {
		try {

			conn = com.DongGu.db.DongGuDB.getConn();
			String sql = "select animalinfo.*, animaltype.at_name, animaltype.a_num, "
					+ " (SELECT a_name FROM animal WHERE animal.a_num = animaltype.a_num) AS a_name "
					+ " from animalinfo " + " left join animaltype on animaltype.at_num = animalinfo.at_num "
					+ " where o_id = ? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, s_id);
			rs = ps.executeQuery();

			String animal_select = "";

			while (rs.next()) {
				int ai_num = rs.getInt("ai_num");
				String ai_name = rs.getString("ai_name");
				String ai_img = rs.getString("ai_img");
				Date ai_birth = rs.getDate("ai_birth");

				String an_num_link = rs.getString("an_num_link");
				String link_text = "";
				String[] link_arr = an_num_link.split("\\|");	//올바른 구분자를 사용하여 문자열을 분리하려면 "\\|"를 사용해야돼
				String an_word = "";
				
				for (int i = 0; i < link_arr.length; i++) {
					String secondQuery = "SELECT an_word FROM animalnature WHERE an_num = ?";

					PreparedStatement ps2;
					ResultSet rs2;

					ps2 = conn.prepareStatement(secondQuery);

					link_text = link_arr[i];
					ps2.setString(1, link_text);
					rs2 = ps2.executeQuery();

					while (rs2.next()) {
						an_word += rs2.getString("an_word") + ",";
					}

					rs2.close();
					ps2.close();
				}

				//an_word가 콤마로 끝날 때 실행
				if (an_word.endsWith(",")) {
				    // 이 경우 an_word.endsWith(",")는 true를 반환하므로 이 블록이 실행됩니다.
					
				    an_word = an_word.substring(0, an_word.length() - 1); // 마지막 콤마를 제거합니다.
				}

				String ai_alergy = rs.getString("ai_alergy");
				String ai_disease = rs.getString("ai_disease");
				String ai_caution = rs.getString("ai_caution");

				int at_num = rs.getInt("at_num");
				String at_name = rs.getString("at_name");
				int a_num = rs.getInt("a_num");
				String a_name = rs.getString("a_name");
				
				//마이페이지에서 넘어와서 수정모드에서 열었을 경우 update_val은 ai_num				
				//등록모드는 update_val은 0
				
				//넘어온 ai_num(=update_val)과 
				//반복문으로 돌리고 있는 데이터의 ai_num이 같은경우
				//selected 시켜라
				String selected = "";
				if(update_val == ai_num) {
					selected = "selected";
				}

				animal_select += "<option value=" + ai_num + " val_name='" + ai_name + "' val_img='" + ai_img
						+ "' val_birth='" + ai_birth + "' val_link='" + an_num_link + "' val_aler='" + ai_alergy
						+ "' val_dise='" + ai_disease + "' val_caut='" + ai_caution + "' val_anitype_num=" + at_num
						+ " val_anitype_name='" + at_name + "' val_ani_num=" + a_num + " val_ani_name='" + a_name
						+ "'  val_an_nature='" + an_word + "' "+selected+" >" + ai_name + "</option>";
			}
			return animal_select;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
				return null;
			}
		}
	}

	/** 초대장(고용자글쓰기) > 동물정보 selectbox > 선택에 따른 동물데이터(성격) 불러오기 */
	public String animalInfo(int at_num_val) {
		try {

			conn = com.DongGu.db.DongGuDB.getConn();

			String sql = "SELECT animaltype.*, "
					+ " (SELECT a_name FROM animal WHERE animal.a_num = animaltype.a_num) AS a_name "
					+ " FROM animaltype " + "WHERE at_num = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, at_num_val);
			rs = ps.executeQuery();

			int a_num = 0;
			int at_num = 0;
			String at_name = "";
			String a_name = "";

			if (rs.next()) {
				a_num = rs.getInt("a_num");
				a_name = rs.getString("a_name");
				at_num = rs.getInt("at_num");
				at_name = rs.getString("at_name");
			}

			return a_num + "//" + a_name + "//" + at_num + "//" + at_name;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
				return null;
			}
		}
	}

	/* 초대장 리스트 */
	/* 초대장 상세 */

	/** 초대장>구직자 글쓰기 */
	public int FriendSitterWrite(FriendDTO2 fto2) {
		try {
			conn = com.DongGu.db.DongGuDB.getConn();
			String sql = "insert into application values(seq_application_ap_num.nextval, ?,?,?, ?,?)";

			ps = conn.prepareStatement(sql);

			// ps.setInt(1, fto2.getI_num());
			ps.setInt(1, fto2.getI_num()); // 초대장번호
			ps.setString(2, fto2.getAp_title()); // 지원제목
			ps.setInt(3, 1); // 매칭상태번호 matchingstate 테이블의 1(매칭대기)로 자동설정

			ps.setString(4, fto2.getP_id()); // 구직자아이디
			ps.setString(5, fto2.getAp_content()); // 추가내용

			int count = ps.executeUpdate();
			return count;
			// return "insert into application values(PK_application.nextval,
			// 1,"+fto2.getAp_title()+",1, "+fto2.getP_id()+","+fto2.getAp_content()+")";

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
			// return "nono";
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}
		}
	}

	/** 초대장>구직자 글쓰기 - 구직자 성별, 흡연여부, 사진, 주소, 반려경험, 동구경험, 기타경험 데이터 불러오기 */
	public String sitterData(String s_id) {

		try {
			conn = com.DongGu.db.DongGuDB.getConn();
			String sql = "select p_gender, p_smoke, p_img, p_addr, "
			           + "NVL(p_ex_my, '없음') as p_ex_my, "
			           + "NVL(p_ex_other, '없음') as p_ex_other, "
			           + "NVL(p_ex_etc, '없음') as p_ex_etc "
			           + "from petsitter where p_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, s_id);
			rs = ps.executeQuery();

			int sitterGender = 0;
			int sitterSmoke = 0;
			String sitterImg = "";
			String sitterAddr = "";
			String sitterMy = "";
			String sitterOther = "";
			String sitterEtc = "";

			if (rs.next()) {
				sitterGender = rs.getInt("p_gender");
				sitterSmoke = rs.getInt("p_smoke");
				sitterImg = rs.getString("p_img");
				sitterAddr = rs.getString("p_addr");
				sitterMy = rs.getString("p_ex_my").replaceAll("\n", "<br>");
				sitterOther = rs.getString("p_ex_other").replaceAll("\n", "<br>");
				sitterEtc = rs.getString("p_ex_etc").replaceAll("\n", "<br>");
			}
			return sitterGender + "//" + sitterSmoke + "//" + sitterImg + "//" + sitterAddr + "//" + sitterMy + "//"
					+ sitterOther + "//" + sitterEtc;

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {

			}
		}
	}
	




	/** 마이페이지>초대장 수정하기(데이터 불러오기) */
	public ArrayList<FriendDTO> getFriendData(int i_nums) {

		try {
			conn = com.DongGu.db.DongGuDB.getConn();
			String sql = "select * from invitation where i_num = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i_nums);
			rs = ps.executeQuery();
			
			ArrayList<FriendDTO> arr = new ArrayList<FriendDTO>();
			
			int i_num = 0;
			int ai_num = 0;
			int m_num = 0;
			String i_title = "";
			String i_content = "";
			String i_start = "";
			String i_end = "";
			Date i_date = null;

			if (rs.next()) {
				i_num = rs.getInt("i_num");
				ai_num = rs.getInt("ai_num");
				m_num = rs.getInt("m_num");
				i_title = rs.getString("i_title");
				i_content = rs.getString("i_content");
				i_start = rs.getString("i_start");
				i_end = rs.getString("i_end");
				i_date = rs.getDate("i_date");
				
				FriendDTO dto = new FriendDTO(i_num, ai_num, m_num, i_title, i_content, i_start, i_end, i_date);
				arr.add(dto);
			}

			return arr;

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {

			}
		}
	}
	


	/** 마이페이지>초대장(고용자수정하기) */
	public int FriendOwnerUpdate(int idx, FriendDTO fto) {
		try {
			conn = com.DongGu.db.DongGuDB.getConn();
			String sql = "update invitation set i_title=?, ai_num=?, i_content=?, i_start=?, i_end=? where i_num=?";
			

			ps = conn.prepareStatement(sql);

			ps.setString(1, fto.getI_title());
			ps.setInt(2, fto.getAi_num());
			ps.setString(3, fto.getI_content());
			ps.setString(4, fto.getI_start());
			ps.setString(5, fto.getI_end());
			
			ps.setInt(6, idx);
			int count = ps.executeUpdate();
			
			return count;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
			//return "nono";
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}
		}
	}
	
	
	


	/** 마이페이지>지원할래요 수정하기(데이터 불러오기) */
	public ArrayList<FriendDTO2> doFriendData(int s_ap_num) {

		try {
			conn = com.DongGu.db.DongGuDB.getConn();
			String sql = "select * from application where ap_num = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_ap_num);
			rs = ps.executeQuery();
			
			ArrayList<FriendDTO2> arr = new ArrayList<FriendDTO2>();

			
			int i_num = 0;
			int ap_num = 0;
			String ap_title = "";
			
			int m_num = 0;
			String p_id = "";
			String ap_content = "";

			if (rs.next()) {
				i_num = rs.getInt("i_num");
				ap_num = rs.getInt("ap_num");
				m_num = rs.getInt("m_num");
				ap_title = rs.getString("ap_title");
				p_id = rs.getString("p_id");
				ap_content = rs.getString("ap_content");
				
				FriendDTO2 dto2 = new FriendDTO2(i_num, ap_num, ap_title, m_num, p_id, ap_content);
				arr.add(dto2);
			}

			return arr;

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {

			}
		}
	}
	


	/** 마이페이지>지원할래요(구직자수정하기) */
	public int FriendSitterUpdate(int idx, FriendDTO2 fto2) {
		try {
			conn = com.DongGu.db.DongGuDB.getConn();
			String sql = "update application set ap_title=?, ap_content=? where ap_num=?";

			ps = conn.prepareStatement(sql);
			
			ps.setString(1, fto2.getAp_title());
			ps.setString(2, fto2.getAp_content());
			ps.setInt(3, idx);
			int count = ps.executeUpdate();
			
			return count;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
			//return "nono";
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}
		}
	}
}
	
	

