package com.event.evengers;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Member;
import com.event.evengers.dao.MemberDao;
import com.event.evengers.service.MemberMM;

@Controller
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	ModelAndView mav;
	@Autowired
	MemberMM mm;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping(value = "/joinFrm", method = RequestMethod.GET)
	public ModelAndView joinFrm() {
		mav=new ModelAndView();
		mav.setViewName("joinFrm");
		return mav;
	}
	
	@RequestMapping(value = "/memberJoin", method = RequestMethod.GET)
	public ModelAndView memberJoin() {
		mav=new ModelAndView();
		mav.setViewName("memberJoin");
		return mav;
	}
	
	@RequestMapping(value = "/ceoJoin", method = RequestMethod.GET)
	public ModelAndView ceoJoin() {
		mav=new ModelAndView();
		mav.setViewName("ceoJoin");
		return mav;
	}
	
	@RequestMapping(value = "/memberInsert", method = RequestMethod.POST)
	public ModelAndView memberInsert(Member mb) {
		mav = new ModelAndView();
		mav=mm.memberInsert(mb);
		return mav;
	}
	
	@RequestMapping(value = "/ceoInsert", method = RequestMethod.POST)
	public ModelAndView ceoInsert(Member mb) {
		mav = new ModelAndView();
		mav=mm.ceoInsert(mb);
		return mav;
	}
	
	
	@RequestMapping(value = "/memberDoubleChk", method = RequestMethod.POST)
	@ResponseBody
	public int memberDoubleChk(HttpServletRequest req){
		String m_id = req.getParameter("m_id");
		int idCheck = mm.memberDoubleChk(m_id);
		int result = 0;
		
		if(idCheck >0) {
			result = 1;
		}
		return result;
	}
	
	@RequestMapping(value = "/ceoDoubleChk", method = RequestMethod.POST)
	@ResponseBody
	public int ceoDoubleChk(HttpServletRequest req){
		String c_id = req.getParameter("c_id");
		int idCheck = mm.memberDoubleChk(c_id);
		int result = 0;
		
		if(idCheck >0) {
			result = 1;
		}
		return result;
	}
	
	@RequestMapping(value = "/ceoCheckNumber", method = RequestMethod.POST)
	@ResponseBody
	public int ceoCheckNumber(HttpServletRequest req){
		String c_rn = req.getParameter("c_rn");
		int numCheck = mm.ceoCheckNumber(c_rn);
		int result = 0;
		
		if(numCheck >0) {
			result = 1;
		}
		return result;
	}



	
}

