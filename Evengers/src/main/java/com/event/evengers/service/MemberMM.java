package com.event.evengers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Member;
import com.event.evengers.dao.MemberDao;

@Service
public class MemberMM {
	private ModelAndView mav;
	@Autowired
	private MemberDao mDao;
	
	public ModelAndView memberInsert(Member mb) {
		mav=new ModelAndView();
		String view=null;
		boolean result=mDao.memberInsert(mb);
		if(result) {
			view="home";
		}else {
			view="home";
		}
		mav.setViewName(view);
		return mav;
	}

}
