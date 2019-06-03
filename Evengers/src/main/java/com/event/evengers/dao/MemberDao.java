package com.event.evengers.dao;

import com.event.evengers.bean.Member;

public interface MemberDao {

	boolean memberInsert(Member mb);

	String testDao(String testcode);

}
