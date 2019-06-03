package com.event.evengers.service;

import java.util.ArrayList;
import java.util.Locale.Category;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Event;
import com.event.evengers.bean.EventOption;
import com.event.evengers.dao.EventDao;
import com.event.evengers.userClass.UploadFile;
import com.google.gson.Gson;
@Service
public class EventMM {
	   private ModelAndView mav;
	@Autowired
	private EventDao eDao;
	
	   @Autowired
	   private HttpSession session;
	   //@Autowired
	   //private Event eb;
	   
	   //@Autowired
	   //private EventOption eob;
	   
	   
	   @Autowired
	   private UploadFile file;
	   
	   public ModelAndView evtInsert(MultipartHttpServletRequest multi) {
	      mav = new ModelAndView();
	      
	      String e_name = multi.getParameter("e_name");
	      /* String c_id = session.getAttribute("c_id").toString(); */
	      int e_price = Integer.parseInt(multi.getParameter("e_price"));
	      String e_category = multi.getParameter("e_category");
	      int e_reservedate = Integer.parseInt(multi.getParameter("e_reservedate"));
	      int e_refunddate = Integer.parseInt(multi.getParameter("e_refunddate"));
	      String e_contents = multi.getParameter("e_contents");
	      String e_orifilename = multi.getParameter("e_orifilename");
	      
	      String e_sysfilename = file.fileUp(multi, 1); //1이면 이벤트 썸네일 사진
	      Event eb = new Event(); 
	      eb.setE_name(e_name);
	      /* eb.setC_id(c_id); */
	      eb.setC_id("aaa");
	      eb.setE_price(e_price);
	      eb.setE_category(e_category);
	      eb.setE_reservedate(e_reservedate);
	      eb.setE_refunddate(e_refunddate);
	      eb.setE_contents(e_contents);
	      eb.setE_orifilename(e_orifilename);
	      eb.setE_sysfilename(e_sysfilename);
	      
	      EventOption eob = new EventOption();
	      
	      String view = null;
	      if (eDao.evtInsert(eb)) {
	         if(eDao.evtOptionInsert(eob)) {
	            view = "home";
	         }
	      } else
	         view = "evtInsertFrm";

	      mav.setViewName(view);
	      return mav;
	   }

	
	  public String getCategoryList() {
	      String json_categories="";
	      ArrayList<Category> categoryList=eDao.getCategories();
	      Gson gson = new Gson();
	      json_categories=gson.toJson(categoryList);
	      return json_categories;
	   }


	public String getEvtList(String ec_name) {
		  String json_evtList="";
		  ArrayList<Event> evtList=eDao.getEvtList(ec_name);
		  Gson gson=new Gson();
		  json_evtList=gson.toJson(evtList);
		return json_evtList;
	}
	


}