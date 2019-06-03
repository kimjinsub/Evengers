package com.event.evengers;

import java.text.DateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Email;
import com.event.evengers.bean.EmailSender;

import com.event.evengers.bean.Ceo;
import com.event.evengers.bean.Member;

import com.event.evengers.bean.TempKey;

import com.event.evengers.service.CeoMM;
import com.event.evengers.service.EventMM;
import com.event.evengers.dao.MemberDao;

import com.event.evengers.service.MemberMM;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	ModelAndView mav;
	@Autowired
	MemberMM mm;
	@Autowired

	private HttpSession session;
	@Autowired
	CeoMM cm;

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
		mav = new ModelAndView();
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
		mav = new ModelAndView();
		mav.setViewName("memberJoin");
		return mav;
	}

	@RequestMapping(value = "/ceoJoin", method = RequestMethod.GET)
	public ModelAndView ceoJoin() {
		mav = new ModelAndView();
		mav.setViewName("ceoJoin");
		return mav;
	}
	
	@RequestMapping(value = "/memberInsert", method = RequestMethod.POST)

	public ModelAndView memberInsert(Member mb) {

		mav = new ModelAndView();
		mav=mm.memberInsert(mb);

		return mav;
	}

	@RequestMapping(value = "/loginFrm", method = RequestMethod.GET)
	public ModelAndView loginFrm() {
		mav = new ModelAndView();
		mav.setViewName("loginFrm");
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find() {
		mav = new ModelAndView();
		mav.setViewName("find");
		return mav;
	}

	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public ModelAndView access(String id, String pw) {
		mav = mm.access(id, pw);
		return mav;
	}

	@RequestMapping(value = "/idFind", method = RequestMethod.GET)
	public ModelAndView idFind() {
		mav = new ModelAndView();
		mav.setViewName("idFind");
		return mav;
	}

	@RequestMapping(value = "/pwFind", method = RequestMethod.GET)
	public ModelAndView pwFind() {
		mav = new ModelAndView();
		mav.setViewName("pwFind");
		return mav;
	}

	@RequestMapping(value = "/sendId", method = RequestMethod.GET)
	public @ResponseBody String sendId(String email) {
		String id = mm.sendId(email);
		System.out.println(email);
		return id;
	}

	@RequestMapping(value = "/sendNumber", method = RequestMethod.GET)
	public @ResponseBody String sendNumber(String id, String email) {
		String sendEmail = mm.sendNumber(id, email);
		System.out.println("sendEmail" + sendEmail);
		return sendEmail;
	}

	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email email;

	@RequestMapping("/sendpw.do")
	public @ResponseBody boolean sendEmailAction(@RequestParam Map<String, Object> paramMap, ModelMap model)
			throws Exception {

		String id = (String) paramMap.get("id");
		String e_mail = (String) paramMap.get("email");
		System.out.println(id);
		System.out.println(e_mail);

		String pw = mm.sendNumber(id, e_mail);
		System.out.println("pw"+pw);
		if (pw != null) {
			int rankey = new TempKey().getKey(50, false);
			email.setContent("인증번호는 " + rankey + " 입니다.");
			email.setReceiver(e_mail);
			email.setSubject(id + "님 비밀번호 찾기 메일입니다.");
			session.setAttribute("rankey", rankey);
			emailSender.SendEmail(email);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/matchNum", method = RequestMethod.GET)
	public @ResponseBody boolean matchNum(String matchNum) {

		String rankey = String.valueOf(session.getAttribute("rankey"));
		if (rankey.equals(matchNum)) {
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping(value = "/pwChange", method = RequestMethod.GET
			,produces = "application/json;charset=utf-8")
	public @ResponseBody String pwChange(String id,String pwMo1,String pwMo2) {
		System.out.println("id"+id);
		System.out.println("pwMo1"+pwMo1);
		System.out.println("pwMo2"+pwMo2);
		String msg= mm.pwChange(id,pwMo1,pwMo2);
			 
		return msg;
	}

	@RequestMapping(value = "/sessionTest", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public @ResponseBody String sessionTest(String testcode) {
		String msg = mm.memberTest(testcode);
		return msg;
	}

	@RequestMapping(value = "/getEvtCategory", method = RequestMethod.GET)
	public ModelAndView getEvtCategory() {
		mav = new ModelAndView();
		mav.setViewName("category");
		return mav;
	}

	@RequestMapping(value = "/getEvtList", produces = "application/json; charset=utf-8")
	public @ResponseBody String getEvtList(String ec_name) {
		System.out.println("ec_name=" + ec_name);
		String json_evtList = em.getEvtList(ec_name);
		System.out.println(json_evtList);
		return json_evtList;
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
		String msg = em.addCategory(ec_name);
		return msg;
	}

	@RequestMapping(value = "/getCategoryList", produces = "application/json; charset=utf-8")
	public @ResponseBody String getCategoryList() {
		String json_categories = em.getCategoryList();
		return json_categories;
	}

	@RequestMapping(value = "/deleteCategory", produces = "application/json; charset=utf-8")
	public @ResponseBody String deleteCategory(String ec_name) {
		String msg = em.deleteCategory(ec_name);
		return msg;
	}

	@RequestMapping(value = "/selectCategory", produces = "application/json; charset=utf-8")
	public @ResponseBody String selectCategory() {
		String json_categories = em.getCategoryList();
		return json_categories;
	}

	@RequestMapping(value = "/evtInsert")
	public ModelAndView evtInsert(MultipartHttpServletRequest multi) {
		mav = em.evtInsert(multi);
		return mav;
	}

	@RequestMapping(value = "/ceoInsert", method = RequestMethod.POST)
	public ModelAndView ceoInsert(Ceo cb) {
		mav = new ModelAndView();
		mav=cm.ceoInsert(cb);
		return mav;
	}

	@RequestMapping(value = "/memberDoubleChk", method = RequestMethod.POST)
	@ResponseBody
	public int memberDoubleChk(HttpServletRequest req) {
		String m_id = req.getParameter("m_id");
		int idCheck = mm.memberDoubleChk(m_id);
		int result = 0;

		if (idCheck > 0) {
			result = 1;
		}
		return result;
	}

	@RequestMapping(value = "/ceoDoubleChk", method = RequestMethod.POST)
	@ResponseBody
	public int ceoDoubleChk(HttpServletRequest req) {
		String c_id = req.getParameter("c_id");
		int idCheck = mm.memberDoubleChk(c_id);
		int result = 0;

		if (idCheck > 0) {
			result = 1;
		}
		return result;
	}

	@RequestMapping(value = "/ceoCheckNumber", method = RequestMethod.POST)
	@ResponseBody
	public int ceoCheckNumber(HttpServletRequest req) {
		String c_rn = req.getParameter("c_rn");
		int numCheck = cm.ceoCheckNumber(c_rn);
		int result = 0;

		if (numCheck > 0) {
			result = 1;
		}
		return result;
	}
	@RequestMapping(value = "/evtReqFrm", method = RequestMethod.GET)
	public ModelAndView evtReqFrm() {
		mav=new ModelAndView();
		mav.setViewName("evtReqFrm");
		return mav;
	}
	@RequestMapping(value = "/mInfo", method = RequestMethod.GET)
	public ModelAndView mInfo() {
		mav = new ModelAndView();
		mav.setViewName("mInfo");
		return mav;
	}
	@RequestMapping(value = "/memberInfo", method = RequestMethod.GET)
	public ModelAndView memberInfo() {
		mav = new ModelAndView();
		mav.setViewName("memberInfo");
		return mav;
	}
	@RequestMapping(value = "/mInfoModify", method = RequestMethod.GET)
	public ModelAndView mInfoModify() {
		mav = new ModelAndView();
		mav.setViewName("mInfoModify");
		return mav;
	}
	@RequestMapping(value = "/payList", method = RequestMethod.GET)
	public ModelAndView payList() {
		mav = new ModelAndView();
		mav.setViewName("payList");
		return mav;
	}
	@RequestMapping(value = "/choiceList", method = RequestMethod.GET)
	public ModelAndView choiceList() {
		mav = new ModelAndView();
		mav.setViewName("choiceList");
		return mav;
	}
	@RequestMapping(value = "/receivedList", method = RequestMethod.GET)
	public ModelAndView receivedList() {
		mav = new ModelAndView();
		mav.setViewName("receivedList");
		return mav;
	}
	@RequestMapping(value = "/receivedEstList", method = RequestMethod.GET)
	public ModelAndView receivedEstList() {
		mav = new ModelAndView();
		mav.setViewName("receivedEstList");
		return mav;
	}
	@RequestMapping(value = "/myReqList", method = RequestMethod.GET)
	public ModelAndView myReqList() {
		mav = new ModelAndView();
		mav.setViewName("myReqList");
		return mav;
	}
	@RequestMapping(value = "/mInfo", method = RequestMethod.GET)
	public ModelAndView mInfo() {
		mav = new ModelAndView();
		mav.setViewName("mInfo");
		return mav;
	}
	@RequestMapping(value = "/memberInfo", method = RequestMethod.GET)
	public ModelAndView memberInfo() {
		mav = new ModelAndView();
		mav.setViewName("memberInfo");
		return mav;
	}
	@RequestMapping(value = "/mInfoModify", method = RequestMethod.GET)
	public ModelAndView mInfoModify() {
		mav = new ModelAndView();
		mav.setViewName("mInfoModify");
		return mav;
	}
	@RequestMapping(value = "/payList", method = RequestMethod.GET)
	public ModelAndView payList() {
		mav = new ModelAndView();
		mav.setViewName("payList");
		return mav;
	}
	@RequestMapping(value = "/choiceList", method = RequestMethod.GET)
	public ModelAndView choiceList() {
		mav = new ModelAndView();
		mav.setViewName("choiceList");
		return mav;
	}
	@RequestMapping(value = "/receivedList", method = RequestMethod.GET)
	public ModelAndView receivedList() {
		mav = new ModelAndView();
		mav.setViewName("receivedList");
		return mav;
	}
	@RequestMapping(value = "/receivedEstList", method = RequestMethod.GET)
	public ModelAndView receivedEstList() {
		mav = new ModelAndView();
		mav.setViewName("receivedEstList");
		return mav;
	}
	@RequestMapping(value = "/myReqList", method = RequestMethod.GET)
	public ModelAndView myReqList() {
		mav = new ModelAndView();
		mav.setViewName("myReqList");
		return mav;
	}
}

