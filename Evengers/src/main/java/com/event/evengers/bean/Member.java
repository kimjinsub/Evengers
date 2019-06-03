package com.event.evengers.bean;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Alias("member")
@Data
@Getter @Setter
public class Member {
   //회원
   private String m_id;
   private String m_pw;
   private String m_name;
   private String m_tel;
   private String m_rrn;
   private String m_email;
   private String m_email1;   //이메일 뒷자리
   private String m_area;
   //기업 
   private String c_id;
   private String c_pw;
   private String c_name;
   private String c_tel;
   private String c_email;
   private String c_email1;      //이메일 뒷자리
   private String c_rn;
   
}