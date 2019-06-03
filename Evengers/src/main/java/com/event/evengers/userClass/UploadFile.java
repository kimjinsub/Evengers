package com.event.evengers.userClass;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.event.evengers.dao.EventDao;
@Component
public class UploadFile {
   //파일 업로드 메소드   
   //String fullPath="D:/Work/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVC-Board/resources/upload";
   @Autowired
   private EventDao eDao;
   
   public String fileUp(MultipartHttpServletRequest multi,int param){
      String e_sysfilename = null;
      switch(param) {
      case 1: //썸네일 사진
         String root=multi.getSession().getServletContext().getRealPath("/");
         System.out.println("root="+root);
         String path=root+"resources/upload/thumbnail/";   //클린하면 폴더가 사라질수도 있어서 2번 실행
         File dir=new File(path);
         if(!dir.isDirectory()){  //upload폴더 없다면
            dir.mkdirs();  //upload폴더 생성
         }
         MultipartFile file = multi.getFile("file");
         String e_orifilename = file.getOriginalFilename();
         e_sysfilename=System.currentTimeMillis()+"."//현재 시간
               +e_orifilename.substring(e_orifilename.lastIndexOf(".")+1);
         
         try {
            file.transferTo(new File(path+e_sysfilename));
         } catch (Exception e) {
         }
         
      }
      return e_sysfilename;
   } //sysfilename 넘어감!!!!
      
   
   //파일 다운로드
      public void download(String fullPath, 
            String oriFileName, HttpServletResponse resp) throws Exception {
         
         //한글파일 깨짐 방지
         String downFile = URLEncoder.encode(oriFileName, "UTF-8");
         //파일 객체 생성
         File file = new File(fullPath);
         //다운로드 경로 파일을 읽어 들임
         InputStream is = new FileInputStream(file);
         //반환객체설정
         resp.setContentType("application/octet-stream");
         resp.setHeader("content-Disposition", 
               "attachment; filename=\""+downFile+"\"");
         //반환객체에 스트림 연결
         OutputStream os = resp.getOutputStream();
         
         //파일쓰기
         byte[] buffer = new byte[1024];
         int length;
         while((length = is.read(buffer)) != -1){
            os.write(buffer,0,length);
         }
         os.flush();
         os.close();
         is.close();
      }
}
