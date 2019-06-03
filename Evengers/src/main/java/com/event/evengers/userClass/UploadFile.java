package com.event.evengers.userClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.event.evengers.dao.EventDao;
import com.google.gson.Gson;

@Component
public class UploadFile {
	private String root;
	private String path;
	private File dir;

	public Map<String, String> singleFileUp(MultipartHttpServletRequest multi, int param) {
		Map<String,String> fileMap = new HashMap<String, String>();
		String e_orifilename = null;
		String e_sysfilename = null;
		switch (param) {
		case 1: // 썸네일 사진
			root = multi.getSession().getServletContext().getRealPath("/");
			System.out.println("root=" + root);
			path = root + "resources/upload/thumbnail/"; // 클린하면 폴더가 사라질수도 있어서 2번 실행
			dir = new File(path);
			if (!dir.isDirectory()) { // upload폴더 없다면
				dir.mkdirs(); // upload폴더 생성
			}
			List<MultipartFile> files=multi.getFiles("e_orifilename");//비동기 2개이상 파일전송
			for(MultipartFile file : files) {
				String multiRepName=file.getOriginalFilename();
				e_orifilename=multiRepName;
				System.out.println("multiRepName="+multiRepName);
				e_sysfilename = System.currentTimeMillis() + "."// 현재 시간
						+ e_orifilename.substring(e_orifilename.lastIndexOf(".") + 1);
				try {
					file.transferTo(new File(path + e_sysfilename));
				} catch (Exception e) {
				}
			}
			fileMap.put("e_orifilename", e_orifilename);
			fileMap.put("e_sysfilename", e_sysfilename);
			System.out.println("orifilename="+e_orifilename);
			System.out.println("sysfilename="+e_sysfilename);
		}
		return fileMap;
	}

	public ArrayList<String[]> multiFileUp(MultipartHttpServletRequest multi,int param) {
		ArrayList<String[]> eiList=new ArrayList<>();
		ArrayList<String[]> fileList = new ArrayList<String[]>();
		String ei_orifilename=null;
		String ei_sysfilename=null;
		switch(param) {
		case 1:
			root = multi.getSession().getServletContext().getRealPath("/");
			System.out.println("root=" + root);
			path = root + "resources/upload/eventImage/"; // 클린하면 폴더가 사라질수도 있어서 2번 실행
			dir = new File(path);
			if (!dir.isDirectory()) { // upload폴더 없다면
				dir.mkdirs(); // upload폴더 생성
			}
			List<MultipartFile> files2=multi.getFiles("ei_files");//비동기 2개이상 파일전송
			for(MultipartFile file2 : files2) {
				String multiRepName=file2.getOriginalFilename();
				ei_orifilename=multiRepName;
				System.out.println("multiRepName="+multiRepName);
				ei_sysfilename = System.currentTimeMillis() + "."// 현재 시간
						+ ei_orifilename.substring(ei_orifilename.lastIndexOf(".") + 1);
				try {
					file2.transferTo(new File(path + ei_sysfilename));
				} catch (Exception e) {
				}
				System.out.println("orifilename="+ei_orifilename);
				System.out.println("sysfilename="+ei_sysfilename);
				String[] names= {ei_orifilename,ei_sysfilename};
				fileList.add(names);
			}
			return fileList;
			
			/*
 			Iterator<String> getFile=multi.getFileNames(); //#1파일업로드칸 2개이상일때
			String s1=getFile.next();
			String s2=getFile.next();
	         System.out.println("s2="+s2);
	         List<MultipartFile> fList=multi.getFiles(s2);
	         //List<MultipartFile> fList = multi.getFiles("ei_files");
			
			
			for (int i = 0; i < fList.size(); i++) {
				String ei_orifilename=fList.get(i).getOriginalFilename();
				String ei_sysfilename = System.currentTimeMillis() + "."// 현재 시간
						+ ei_orifilename.substring(ei_orifilename.lastIndexOf(".") + 1);
				
				try {
					fList.get(i).transferTo(new File(path + ei_sysfilename));
					String[] ei_file = {ei_orifilename,ei_sysfilename};
					System.out.println(ei_file);
					eiList.add(ei_file);
				} catch (Exception e) {
				}
			}*/
		}
		return eiList;
	}
}
