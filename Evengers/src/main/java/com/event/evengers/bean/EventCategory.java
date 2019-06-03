package com.event.evengers.bean;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("eventcategory")
@Data
public class EventCategory {

	private String ec_name;
}

