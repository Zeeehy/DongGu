package com.DongGu.review;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class ReviewDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ReviewDAO() {
        System.out.println("ReviewDAO 객체 생성됨!");
    }

    // 후기 뿌리기
    public ArrayList<ReviewDTO> AfterList(int rIdCheck) {
        ArrayList<ReviewDTO> arr = new ArrayList<>();

        String sql = "SELECT r.r_num, r.r_write_id, r.r_id_check, r.r_star, r.r_content, r.r_img, r.r_date, " +
                     "       CASE " +
                     "           WHEN r.r_id_check = 0 THEN o.o_nickname " +
                     "           WHEN r.r_id_check = 1 THEN p.p_nickname " +
                     "           ELSE 'Unknown' " +
                     "       END AS nickname " +
                     "FROM review r " +
                     "LEFT JOIN owner o ON r.r_write_id = o.o_id AND r.r_id_check = 0 " +
                     "LEFT JOIN petsitter p ON r.r_write_id = p.p_id AND r.r_id_check = 1 " +
                     "WHERE r.r_id_check = ? " +
                     "ORDER BY r.r_num DESC";

        String uploadPath = "/DongGu/";  // 이 부분은 실제 프로젝트에서 설정된 경로로 바꾸어야 합니다.
        String savepath = uploadPath + "img/webFolder/upload/";

        try (Connection conn = com.DongGu.db.DongGuDB.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, rIdCheck); // r_id_check 값을 설정

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int r_num = rs.getInt("r_num");
                    String r_write_id = rs.getString("r_write_id");
                    int r_id_check = rs.getInt("r_id_check");
                    int r_star = rs.getInt("r_star");
                    String r_content = rs.getString("r_content");
                    String r_img_name = rs.getString("r_img");  // 파일 이름을 가져옴
                    Date r_date = rs.getDate("r_date");  // r_date 값을 가져옴
                    String nickname = rs.getString("nickname");  // 닉네임 가져오기

                    String r_img_path = savepath + r_img_name;  // 이미지 경로 설정

                    ReviewDTO dto = new ReviewDTO(r_num, r_write_id, r_id_check, r_star, r_content, r_img_path, r_img_name, r_date, nickname);
                    arr.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // -1 : 에러코드
        }

        return arr;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    ////////////////////////지현//////////////////////////////////
    public ArrayList<ReviewDTO> AfterListPerson(int rIdCheck,String id,int cp,int listSize) {
        ArrayList<ReviewDTO> arr = new ArrayList<>();

        String sql = "select *  "
        		+ "from  "
        		+ "(select rownum as rnum, a.* from "
        		+ "    (  "
        		+ "SELECT r.r_num, r.r_write_id, r.r_id_check, r.r_star, r.r_content, r.r_img, r.r_date, "
        		+ "    CASE  "
        		+ "    WHEN r.r_id_check = 0 THEN o.o_nickname "
        		+ "    WHEN r.r_id_check = 1 THEN p.p_nickname  "
        		+ "    ELSE 'Unknown'  "
        		+ "    END AS nickname  "
        		+ "FROM review r  "
        		+ "LEFT JOIN owner o ON r.r_write_id = o.o_id AND r.r_id_check = 0  "
        		+ "LEFT JOIN petsitter p ON r.r_write_id = p.p_id AND r.r_id_check = 1  "
        		+ "WHERE r.r_id_check = ? and r.r_receive_id = ? "
        		+ "ORDER BY r.r_num DESC)a  "
        		+ "    )b "
        		+ "where rnum >= ? and rnum <=?";


        try (Connection conn = com.DongGu.db.DongGuDB.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, rIdCheck); // r_id_check 값을 설정
            ps.setString(2, id); // id값
            ps.setInt(3, cp*listSize-(listSize-1));
			ps.setInt(4, cp*listSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int r_num = rs.getInt("r_num");
                    String r_write_id = rs.getString("r_write_id");
                    int r_id_check = rs.getInt("r_id_check");
                    int r_star = rs.getInt("r_star");
                    String r_content = rs.getString("r_content");
                    String r_img_name = rs.getString("r_img");  // 파일 이름을 가져옴
                    Date r_date = rs.getDate("r_date");  // r_date 값을 가져옴
                    String nickname = rs.getString("nickname");  // 닉네임 가져오기


                    ReviewDTO dto = new ReviewDTO(r_num, r_write_id, r_id_check, r_star, r_content, r_img_name, r_date, nickname);
                    arr.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // -1 : 에러코드
        }

        return arr;
    }
    
    /* 총 리뷰 갯수 가져오기*/
	public int getReviewCnt(int rIdCheck,String id) {
		try {
			conn = com.DongGu.db.DongGuDB.getConn();
			String sql="select count(*) from review WHERE r_id_check = ? and r_receive_id = ?";
			ps =conn.prepareStatement(sql);
			ps.setInt(1, rIdCheck); // r_id_check 값을 설정
            ps.setString(2, id); // id값
            
			rs = ps.executeQuery();
			int result=0;
			
			if(rs.next()) { 
				result=rs.getInt(1);
			}
			
			
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}

