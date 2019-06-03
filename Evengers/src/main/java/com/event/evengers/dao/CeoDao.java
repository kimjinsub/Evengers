package com.event.evengers.dao;

import javax.servlet.http.HttpServletRequest;

import com.event.evengers.bean.Ceo;

public interface CeoDao {
	
	boolean ceoInsert(Ceo cb);

	public int ceoDoubleChk(String m_id);

	public int ceoCheckNumber(String c_rn);
}
