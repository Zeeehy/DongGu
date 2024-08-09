package com.DongGu.friend;

import java.sql.Date;

public class FriendDTO2 {

	private int i_id;	//초대장번호
	private String ap_num;	//지원번호
	private int m_num; 	//매칭상태번호
	
	private String p_id;	//구직자아이디
	private String ap_content;	//추가내용

	
	public FriendDTO2() {}
	
	public FriendDTO2(int i_id, String ap_num, int m_num, String p_id, String ap_content) {
		super();
		this.i_id = i_id;
		this.ap_num = ap_num;
		this.m_num = m_num;
		
		this.p_id = p_id;
		this.ap_content = ap_content;
	}

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public String getAp_num() {
		return ap_num;
	}

	public void setAp_num(String ap_num) {
		this.ap_num = ap_num;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getAp_content() {
		return ap_content;
	}

	public void setAp_content(String ap_content) {
		this.ap_content = ap_content;
	}
	
	
}
