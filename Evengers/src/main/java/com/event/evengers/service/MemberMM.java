package com.event.evengers.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Member;
import com.event.evengers.dao.MemberDao;

@Service
public class MemberMM {
	private ModelAndView mav;
	@Autowired
	private MemberDao mDao;

	@Autowired
	private HttpSession session;

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
	
	public ModelAndView ceoInsert(Member mb) {
		mav = new ModelAndView();
		String view = null;
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		mb.setC_pw(pwEncoder.encode(mb.getC_pw()));
		mb.setC_email(mb.getC_email()+mb.getC_email1());
		
		if (mDao.ceoInsert(mb)) {
			view = "index";
			mav.addObject("check", 1); //회원가입 성공
		} else {
			view = "joinFrm";
		}
		mav.setViewName(view);
		return mav;
	}


	public int memberDoubleChk(String m_id) {
		int mbChk = mDao.memberDoubleChk(m_id);
		int ceoChk = mDao.ceoDoubleChk(m_id);
		int chkNum = 0;
		//System.out.println(mbChk);
		//System.out.println(ceoChk);
		
		if(mbChk>0 || ceoChk>0) {
			chkNum = 1;
		}
		
		return chkNum;
	}

	public int ceoCheckNumber(String c_rn) {
		int numCheck = mDao.ceoCheckNumber(c_rn);
		System.out.println("존재?"+numCheck);
		int chkNum = 0;
		
		if(numCheck>0) {
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

	public ModelAndView access(String id, String pw) {
		mav = new ModelAndView();
		String view = null;

		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String mPwdEncode = mDao.mAccess(id);
		String cPwdEncode = mDao.cAccess(id);
		System.out.println(mPwdEncode);
		System.out.println(cPwdEncode);
		if (mPwdEncode != null) {
			if (pwdEncoder.matches(pw, mPwdEncode)) {
				session.setAttribute("id", id);
				System.out.println("멤버");
				view = "home";
				mav.setViewName(view);
				return mav;
			} else {
				view = "loginFrm";
			}
		} else {
			view = "loginFrm";
			System.out.println("안됨1");
		}
		if (cPwdEncode != null) {
			if (pwdEncoder.matches(pw, cPwdEncode)) {
				session.setAttribute("id", id);
				System.out.println("회사");
				view = "home";
				mav.setViewName(view);
				return mav;
			} else {
				view = "loginFrm";
			}
		} else {
			view = "loginFrm";
			System.out.println("안됨2");
		}
		mav.setViewName(view);
		return mav;
	}

	public String sendId(String email) {
		System.out.println(email);
		String id = mDao.sendMId(email);
		if (id != null) {
			return id;

		} else {
			id = mDao.sendCId(email);
		}
		return id;
	}

	public String sendNumber(String id, String email) {
		String pw=null;
		String me=mDao.findEmail(id);
		String ce=mDao.findCeoEmail(id);
		System.out.println("email"+email);
		System.out.println("me" + me);
		System.out.println("ce" + ce);
		if (me != null) {
			if (me.equals(email)) {
				pw = mDao.sendNumber(id);
				System.out.println("me");
			}
		}
		if (ce != null) {
			if (ce.equals(email)) {
				pw = mDao.sendCeoNumber(id);
				System.out.println("ce");
			}
		}
		return pw;

	}

	public String pwChange(String id, String pwMo1, String pwMo2) {
		String str = "";
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		
		if (pwMo1.equals(pwMo2)) {
			pwMo1 = pwdEncoder.encode(pwMo1);
			if (mDao.pwChange(id, pwMo1) || mDao.pwCeoChange(id, pwMo1)) {
				str = "비밀번호가 변경되었습니다";
			} 
		}else {
			str = "비밀번호가 일치 하지않습니다.";
		}
		return str;
	}
}
