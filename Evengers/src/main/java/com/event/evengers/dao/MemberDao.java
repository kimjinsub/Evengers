package com.event.evengers.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

import com.event.evengers.bean.Member;

public interface MemberDao {

	boolean memberInsert(Member mb);
	
	//boolean ceoInsert(Member mb);

	public int memberDoubleChk(String m_id);
	
	//public int ceoDoubleChk(String m_id);

	//public int ceoCheckNumber(String c_rn);

	String testDao(String testcode);

	String mAccess(@Param("m_id")String id);
	
	String cAccess(@Param("c_id")String id);
	
	String sendCId(@Param("c_email")String email);
	
	String sendMId(@Param("m_email")String email);
	
	String sendNumber(@Param("m_id")String id);
	
	String sendCeoNumber(@Param("c_id")String id);
	
	String getPw(String id);
	
	boolean pwChange(@Param("m_id")String id, @Param("m_pw")String pwMo1);

	boolean pwCeoChange(@Param("c_id")String id, @Param("c_pw")String pwMo1);

	String findEmail(@Param("m_id")String id);

	String findCeoEmail(@Param("c_id")String id);

}
