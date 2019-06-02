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
import com.event.evengers.service.EventMM;
import com.event.evengers.dao.MemberDao;
import com.event.evengers.service.MemberMM;

@Controller
public class HomeController {
	ModelAndView mav;
	@Autowired
	MemberMM mm;
	@Autowired
	EventMM em;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)

	public String home() {
		return "index";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String bootStrap() {
		return "home";
	}
	@RequestMapping(value = "/boot", method = RequestMethod.GET)
	public String boot() {
		return "boot";
	}
	@RequestMapping(value = "/joinFrm", method = RequestMethod.GET)
	public ModelAndView joinFrm() {
		mav=new ModelAndView();
		mav.setViewName("joinFrm");
		return mav;
	}
	@RequestMapping(value = "/evtInsertFrm", method = RequestMethod.GET)
	public ModelAndView evtInsertFrm() {
		mav=new ModelAndView();
		mav.setViewName("evtInsertFrm");
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
	
	@RequestMapping(value = "/categoryFrm", method = RequestMethod.GET)
	public String categoryFrm() {
		return "categoryFrm";
	}
	@RequestMapping(value = "/carryOption")
	public ModelAndView carryOption(HttpServletRequest multi) {
		mav = new ModelAndView();
		System.out.println("hi");
		System.out.println(multi.getParameter("eo_name").split(",")[0]);
		System.out.println(multi.getParameter("eo_price").split(",")[1]);
		System.out.println(multi.getParameter("eo_price").split(",").length);
		mav.setViewName("home");
		return mav;
	}
	@RequestMapping(value = "/addCategory", produces = "application/json; charset=utf-8")
	public @ResponseBody String addCategory(String ec_name) {
		String msg=em.addCategory(ec_name);
		return msg;
	}
	@RequestMapping(value = "/getCategoryList", produces = "application/json; charset=utf-8")
	public @ResponseBody String getCategoryList() {
		String json_categories=em.getCategoryList();
		return json_categories;
	}
	@RequestMapping(value = "/deleteCategory", produces = "application/json; charset=utf-8")
	public @ResponseBody String deleteCategory(String ec_name) {
		String msg=em.deleteCategory(ec_name);
		return msg;
	}
	@RequestMapping(value = "/selectCategory", produces = "application/json; charset=utf-8")
	public @ResponseBody String selectCategory() {
		String json_categories=em.getCategoryList();
		return json_categories;
	}
	@RequestMapping(value = "/evtInsert")
	   public ModelAndView evtInsert(HttpServletRequest multi) {
	      System.out.println("ff"+multi.getParameter("e_price"));
	      System.out.println("ff"+multi.getParameter("e_name"));
	      mav = em.evtInsert(multi);
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

