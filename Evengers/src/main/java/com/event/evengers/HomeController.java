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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Member;
import com.event.evengers.service.EventMM;
import com.event.evengers.service.MemberMM;
import com.google.gson.Gson;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	ModelAndView mav;
	@Autowired
	MemberMM mm;
	@Autowired
	EventMM em;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
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
	
	@RequestMapping(value = "/sessionTest", method = RequestMethod.GET,produces = "application/json; charset=utf8")
	public @ResponseBody String sessionTest(String testcode) {
		String msg=mm.memberTest(testcode);
		return msg;
	}
	
	   @RequestMapping(value = "/getCategoryList", produces = "application/json; charset=utf-8")
	   public @ResponseBody String getCategoryList() {
	      String json_categories=em.getCategoryList();
	      return json_categories;
	   }
	   @RequestMapping(value = "/evtInsertFrm", method = RequestMethod.GET)
	   public ModelAndView evtInsertFrm() {
	      mav = new ModelAndView();
	      mav.setViewName("evtInsertFrm");
	      return mav;
	   }
		@RequestMapping(value = "/getEvtCategory", method = RequestMethod.GET)
		public ModelAndView getEvtCategory() {
			mav=new ModelAndView();
			mav.setViewName("category");
			return mav;
		}
		@RequestMapping(value = "/getEvtList", produces = "application/json; charset=utf-8")
		   public @ResponseBody String getEvtList(String ec_name) {
			System.out.println("ec_name="+ec_name);
		      String json_evtList=em.getEvtList(ec_name);
		      System.out.println(json_evtList);
		      return json_evtList;
		   }
}

