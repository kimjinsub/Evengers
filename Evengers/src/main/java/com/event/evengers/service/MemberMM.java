package com.event.evengers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Member;
import com.event.evengers.dao.CeoDao;
import com.event.evengers.dao.MemberDao;

@Service
public class MemberMM {
	private ModelAndView mav;
	@Autowired
	private MemberDao mDao;
	@Autowired
	private CeoDao cDao;

	public ModelAndView memberInsert(Member mb) {
		mav = new ModelAndView();
		String view = null; // 포워딩 주소
		// 비번을 암호화(Encoding)할 수 있지만 복호화(Decoding)는 불가능
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		mb.setM_pw(pwEncoder.encode(mb.getM_pw()));
		mb.setM_email(mb.getM_email()+mb.getM_email1());
		
		if (mDao.memberInsert(mb)) {
			view = "index";
			mav.addObject("check", 1); //회원가입 성공
		} else {
			view = "index";
		}
		mav.setViewName(view);
		return mav;
	}
	
	/*
	 * public ModelAndView ceoInsert(Member mb) { mav = new ModelAndView(); String
	 * view = null; BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
	 * mb.setC_pw(pwEncoder.encode(mb.getC_pw()));
	 * mb.setC_email(mb.getC_email()+mb.getC_email1());
	 * 
	 * if (cDao.ceoInsert(mb)) { view = "index"; mav.addObject("check", 1); //회원가입
	 * 성공 } else { view = "joinFrm"; } mav.setViewName(view); return mav; }
	 */


	public int memberDoubleChk(String m_id) {
		int mbChk = mDao.memberDoubleChk(m_id);
		int ceoChk = cDao.ceoDoubleChk(m_id);
		int chkNum = 0;
		//System.out.println(mbChk);
		//System.out.println(ceoChk);
		
		if(mbChk>0 || ceoChk>0) {
			chkNum = 1;
		}
		
		return chkNum;
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
