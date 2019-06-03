package com.event.evengers.dao;

import java.util.ArrayList;
import java.util.Locale.Category;

import com.event.evengers.bean.Event;
import com.event.evengers.bean.EventOption;

public interface EventDao {
	public boolean evtInsert(Event eb);
	
	boolean addCategory(String ec_name);

	ArrayList<Category> getCategories();

	boolean deleteCategory(String ec_name);
	
	public boolean evtOptionInsert(EventOption eob);
	
	public String getEvtCode(String c_id);

    ArrayList<Event> getEvtList(String ec_name);

}
