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

	public String memberTest(String testcode) {
		//mav=new ModelAndView();
		 System.out.println("testcode="+testcode);
		String msg="";
		String result=mDao.testDao(testcode);
		if(result!=null) {
			msg="일반회원";
		}
		System.out.println("result="+result);
		/*
		 * if(result==testcode) { mav.addObject("check",1); }else {
		 * mav.addObject("check",0); }
		 */
		return msg;
	}

}
