package com.event.evengers.dao;

import java.util.ArrayList;
import java.util.Locale.Category;

import com.event.evengers.bean.Event;
import com.event.evengers.bean.EventOption;

public interface EventDao {

	ArrayList<Category> getCategories();
	
	   public boolean evtInsert(Event eb);

	   public boolean evtOptionInsert(EventOption eob);

	ArrayList<Event> getEvtList(String ec_name);
}
