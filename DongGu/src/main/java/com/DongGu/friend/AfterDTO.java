package com.DongGu.friend;

import java.io.File;
import java.sql.Date;

public class AfterDTO {
	
   /* --- 수아언니 --- */
   private int r_num;   //후기번호
   private String r_write_id;   //저장아이디
   private int r_id_check; //저장아이디 구직자0 vs 고용자1 
   private String r_receive_id;    //받는 아이디(저장아이디가 누구한테쓰는지)
   private int r_star;    //평점
   private String r_content;   //내용
   private File r_img;   //사진   //올릴때 파일명과 달라짐
   private String r_img_name;   //사진명   //올릴때 파일명과 달라짐	//db에 없음

   private int r_sun_i;   //매칭상태 업데이트를 위해서 필요 (고용자인경우 초대장(invitation)의 키값, 구직자인경우 지원할래요(application)의 키값
   
   public AfterDTO() {
	   System.out.println("AfterDTO 생성자 호출됨!");
   }
   
   public AfterDTO(int r_num, String r_write_id, int r_id_check, String r_receive_id, int r_star, String r_content, File r_img) {
      super();
      this.r_num = r_num;
      this.r_write_id = r_write_id;
      this.r_id_check = r_id_check;
      this.r_receive_id = r_receive_id;
      this.r_star = r_star;
      this.r_content = r_content;
      this.r_img = r_img;
   }

   public int getR_num() {
      return r_num;
   }

   public void setR_num(int r_num) {
      this.r_num = r_num;
   }

   public String getR_write_id() {
      return r_write_id;
   }

   public void setR_write_id(String r_write_id) {
      this.r_write_id = r_write_id;
   }

   public int getR_id_check() {
      return r_id_check;
   }

   public void setR_id_check(int r_id_check) {
      this.r_id_check = r_id_check;
   }
   
   public String getR_receive_id() {
		return r_receive_id;
	}
	
	public void setR_receive_id(String r_receive_id) {
		this.r_receive_id = r_receive_id;
	}
	
	public int getR_star() {
	      return r_star;
   }

   public void setR_star(int r_star) {
      this.r_star = r_star;
   }

   public String getR_content() {
      return r_content;
   }

   public void setR_content(String r_content) {
      this.r_content = r_content;
   }

   public File getR_img() {
      return r_img;
   }

   public void setR_img(File r_img) {
      this.r_img = r_img;
   }

   public String getR_img_name() {
      return r_img_name;
   }

   public void setR_img_name(String r_img_name) {
      this.r_img_name = r_img_name;
   }

	public int getR_sun_i() {
		return r_sun_i;
	}
	
	public void setR_sun_i(int r_sun_i) {
		this.r_sun_i = r_sun_i;
	}
   
   


}