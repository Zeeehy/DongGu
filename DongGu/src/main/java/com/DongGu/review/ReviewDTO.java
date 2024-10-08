package com.DongGu.review;

import java.io.File;
import java.sql.Date;

public class ReviewDTO {
   
   /* --- 유리 --- */
   private int r_num;   //후기번호
   private String r_write_id;   //저장아이디
   private int r_id_check; //저장아이디 구직자 vs 고용자 
   private int r_star;    //평점
   private String r_content;   //내용
   private String r_img;   //사진   //올릴때 파일명과 달라짐
   private String r_img_name;   //사진명   //올릴때 파일명과 달라짐
   private java.sql.Date r_date; // 등록날짜
   private String nickname;

   public ReviewDTO(int r_num, String r_write_id, int r_id_check, int r_star, String r_content, String r_img, String r_img_name,
      Date r_date, String nickname) {
      super();
      this.r_num = r_num;
      this.r_write_id = r_write_id;
      this.r_id_check = r_id_check;
      this.r_star = r_star;
      this.r_content = r_content;
      this.r_img = r_img;
      this.r_img_name = r_img_name;
      this.r_date = r_date;
      this.nickname = nickname;  // 초기화
   }
   
   
   public ReviewDTO(int r_num, String r_write_id, int r_id_check, int r_star, String r_content, String r_img, Date r_date,
		String nickname) {
	super();
	this.r_num = r_num;
	this.r_write_id = r_write_id;
	this.r_id_check = r_id_check;
	this.r_star = r_star;
	this.r_content = r_content;
	this.r_img = r_img;
	this.r_date = r_date;
	this.nickname = nickname;
}



public java.sql.Date getR_date() {
      return r_date;
   }
   
   public void setR_date(java.sql.Date r_date) {
      this.r_date = r_date;
   }
   
   public String getNickname() {
      return nickname;
   }
   
   public void setNickname(String nickname) {
      this.nickname = nickname;
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
	
	public String getR_img() {
		return r_img;
	}
	
	public void setR_img(String r_img) {
		this.r_img = r_img;
	}
	
	public String getR_img_name() {
		return r_img_name;
	}
	
	public void setR_img_name(String r_img_name) {
		this.r_img_name = r_img_name;
	}
	      
	   

}