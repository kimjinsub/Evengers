package com.event.evengers.bean;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("member")
@Data
public class Member {
	private String id;
	private String pw;
	private String name;
	private String email;
	/*private String m_id;
	private String m_pw;
	private String m_name;
	private String m_tel;
	private String m_rrn;
	private String m_email;
	private String m_area;*/
}
