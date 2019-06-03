package com.event.evengers;

import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;
import com.event.evengers.bean.Email;
import com.event.evengers.bean.EmailSender;
import com.event.evengers.bean.Member;
import com.event.evengers.bean.TempKey;
import com.event.evengers.service.MemberMM;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	ModelAndView mav;
	@Autowired
	MemberMM mm;
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/joinFrm", method = RequestMethod.GET)
	public ModelAndView joinFrm() {
		mav = new ModelAndView();
		mav.setViewName("joinFrm");
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

	@RequestMapping(value = "/memberInsert", method = RequestMethod.GET)
	public ModelAndView memberInsert(Member mb) {
		mav = mm.memberInsert(mb);
		System.out.println("test1");
		System.out.println("test2");
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

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ModelAndView email() {
		mav = new ModelAndView();
		mav.setViewName("email");
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

}

