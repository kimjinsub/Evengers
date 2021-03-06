package com.event.evengers.service;

import java.util.ArrayList;
import java.util.Locale.Category;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.event.evengers.bean.Event;
import com.event.evengers.bean.EventImage;
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
	   @Autowired
	   private HttpSession session;
	
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
		System.out.println("gggg");
		ArrayList<Category> categoryList=eDao.getCategories();
		System.out.println("categoryList="+categoryList);
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
	public ModelAndView evtInsert(MultipartHttpServletRequest multi) {
		mav = new ModelAndView();
		
		String e_name = multi.getParameter("e_name");
		String c_id = session.getAttribute("id").toString();
		int e_price = Integer.parseInt(multi.getParameter("e_price"));
		String e_category = multi.getParameter("e_category");
		int e_reservedate = Integer.parseInt(multi.getParameter("e_reservedate"));
		int e_refunddate = Integer.parseInt(multi.getParameter("e_refunddate"));
		String e_contents = multi.getParameter("e_contents");
		String eo_name = multi.getParameter("eo_name");
		String eo_price = multi.getParameter("eo_price");
		
		System.out.println("e_name="+e_name);
		System.out.println("c_id="+c_id);
		System.out.println("e_price="+e_price);
		System.out.println("e_category="+e_category);
		System.out.println("e_reservedate="+e_reservedate);
		System.out.println("e_refunddate="+e_refunddate);
		System.out.println("e_contents="+e_contents);
		System.out.println("eo_name="+eo_name);
		System.out.println("eo_price="+eo_price);
		
		
		
		Map<String, String> fileMap = file.singleFileUp(multi, 1);
		Event eb = new Event();
		eb.setE_name(e_name);
		eb.setC_id(c_id);
		eb.setE_price(e_price);
		eb.setE_category(e_category);
		eb.setE_reservedate(e_reservedate);
		eb.setE_refunddate(e_refunddate);
		eb.setE_contents(e_contents);
		eb.setE_orifilename(fileMap.get("e_orifilename"));
		eb.setE_sysfilename(fileMap.get("e_sysfilename"));
		
		String view = null;
		if (eDao.evtInsert(eb)) {
			
			String e_code = eDao.getEvtCode(eb.getC_id()); //이벤트 코드 값 가져오기
			
			String[] eo_names = eo_name.split(",");
			System.out.println(eo_names);
			String[] eo_prices = eo_price.split(",");
			System.out.println(eo_prices[0]);
			int cnt1=0;
			for(int i=0;i<eo_names.length;i++) {
				EventOption eob = new EventOption();
				eob.setEo_name(eo_names[i]);
				eob.setEo_price(Integer.parseInt(eo_prices[i]));
				eob.setE_code(e_code);
				if(eDao.evtOptionInsert(eob)) {
					cnt1++;
				}
			}
			ArrayList<String[]> fileList=file.multiFileUp(multi, 1);
			int cnt2=0;
			for(int i=0;i<fileList.size();i++) {
				EventImage ei = new EventImage();
				ei.setEi_orifilename(fileList.get(i)[0]);
				ei.setEi_sysfilename(fileList.get(i)[1]);
				System.out.println("ei_ori="+ei.getEi_orifilename());
				System.out.println("ei_sys="+ei.getEi_sysfilename());
				ei.setE_code(e_code);
				if(eDao.evtImageInsert(ei)) {
					cnt2++;
				}
			}
			if(cnt1==eo_names.length && cnt2==fileList.size()) {
				view = "home";
			}
		} else {
			view = "evtInsertFrm";
		}
		mav.setViewName(view);
		return mav;
	}

	public String getEvtList(String ec_name) {

        String json_evtList="";
        ArrayList<Event> evtList=eDao.getEvtList(ec_name);
        Gson gson=new Gson();
        json_evtList=gson.toJson(evtList);
      return json_evtList;
   }

}
