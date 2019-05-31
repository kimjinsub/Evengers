package com.event.evengers.service;

import java.util.ArrayList;
import java.util.Locale.Category;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Event;
import com.event.evengers.bean.EventOption;
import com.event.evengers.dao.EventDao;
import com.event.evengers.userClass.UploadFile;
import com.google.gson.Gson;

@Service
public class EventMM {
	ModelAndView mav;
	@Autowired
	EventDao eDao;
	@Autowired
	UploadFile file;
	
	
	public String addCategory(String ec_name) {
		boolean result=eDao.addCategory(ec_name);
		String msg="";
		if(result) {
			msg="성공";
		}
		return msg;
	}
	
	public String getCategoryList() {
		String json_categories="";
		ArrayList<Category> categoryList=eDao.getCategories();
		Gson gson = new Gson();
		json_categories=gson.toJson(categoryList);
		return json_categories;
	}

	public String deleteCategory(String ec_name) {
		boolean result=eDao.deleteCategory(ec_name);
		String msg="";
		if(result) {
			msg="성공";
		}
		return msg;
	}
	public ModelAndView evtInsert(HttpServletRequest multi) {
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
	      
	      String view = null;
	      if (eDao.evtInsert(eb)) {
	         
	         String e_code = eDao.getEvtCode(eb.getC_id());
	         
	         String[] eo_names = multi.getParameter("eo_name").split(",");
	         String[] eo_prices = multi.getParameter("eo_price").split(",");
	         int cnt=0;
	         for(int i=0;i<eo_names.length;i++) {
	            EventOption eob = new EventOption();
	            eob.setEo_name(eo_names[i]);
	            eob.setEo_price(Integer.parseInt(eo_prices[i]));
	            eob.setE_code(e_code);
	            if(eDao.evtOptionInsert(eob)) {
	               cnt++;
	            }
	         }
	         if(cnt==eo_names.length) {
	            view = "home";
	         }
	      } else {
	         view = "evtInsertFrm";
	      }
	      mav.setViewName(view);
	      return mav;
	   }
}
