package com.event.evengers.dao;

import javax.servlet.http.HttpServletRequest;

import com.event.evengers.bean.Member;

public interface MemberDao {

	boolean memberInsert(Member mb);
	
	//boolean ceoInsert(Member mb);

	public int memberDoubleChk(String m_id);
	
	//public int ceoDoubleChk(String m_id);

	//public int ceoCheckNumber(String c_rn);

	String testDao(String testcode);

}
