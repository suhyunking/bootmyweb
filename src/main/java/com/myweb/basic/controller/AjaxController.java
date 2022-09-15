package com.myweb.basic.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.basic.command.CategoryVO;
import com.myweb.basic.command.UploadVO;
import com.myweb.basic.product.ProductService;

@RestController
public class AjaxController {
	@Autowired
	ProductService productService;
	
	
	@Value("${project.upload.path}")
	String uploadPath;
	
	//매게변수 없고 반환만 있는 모형 (첫번째 카테고리)
	@GetMapping("/getCategory")
	public List<CategoryVO> getCategory(){
		List<CategoryVO> list= productService.getCategory();
		return list;
	}
	
	//필요한 값 post방식으로 전달, 반환 있는 모형 (2, 3번째 카테고리)
	@PostMapping("/getCategoryChild")
	public List<CategoryVO> getCategoryChild(@RequestBody CategoryVO vo){
		
		List<CategoryVO> list= productService.getCategoryChild(vo);
		
		
		return list;
		
	}
	
	//이미지 처리
	@GetMapping("/display")
	public byte[] display(@RequestParam("filename") String filename, @RequestParam("filepath") String filepath, 
							@RequestParam("uuid") String uuid) {
		
		/*
		 * System.out.println(filename); System.out.println(filepath);
		 * System.out.println(uuid);
		 */
		//업로드경로
		String saveName = uploadPath + "\\"+ filepath  +"\\"+ uuid + "_" + filename;
		//썸네일경로
		String thumbnailName = uploadPath + "\\"+ filepath  +"\\thumb_"+ uuid + "_" + filename;
		byte[] result = null;
		try {
			File file= new File(thumbnailName);
			result= FileCopyUtils.copyToByteArray(file);
		} catch (Exception e) {
			System.out.println("이미지 경로 참조 에러");
		}
		return result;
	}
	
	@PostMapping("/getAjaxImg")
	public List<UploadVO> getAjaxImg(@RequestBody UploadVO vo){
		List<UploadVO> list= productService.getDetailImg(vo.getProd_id());
		return list;
	}
}
