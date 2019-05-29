package com.event.evengers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Member;
import com.event.evengers.service.MemberMM;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	ModelAndView mav;
	@Autowired
	MemberMM mm;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
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
	
	@RequestMapping(value = "/memberInsert", method = RequestMethod.GET)
	public ModelAndView memberInsert(Member mb) {
		mav=mm.memberInsert(mb);
		System.out.println("test1");
		System.out.println("test2");
		return mav;
	}
}

