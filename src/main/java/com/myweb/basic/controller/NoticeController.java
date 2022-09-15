package com.myweb.basic.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.basic.entity.Notice;
import com.myweb.basic.notice.NoticeService;
import com.myweb.basic.util.Criteria;
import com.myweb.basic.util.PageDTO;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	//전체목록
	@GetMapping("/noticeListAll")
	public String noticeListAll(@ModelAttribute("cri") Criteria cri, Model model) {
		//기본
//		List<Notice> list = noticeService.getList();
//		model.addAttribute("list", list);
		
		//페이지
//		PageDTO<Notice> list = noticeService.getList(cri);
//		model.addAttribute("pageDTO", list);
		
		//검색페이지
		PageDTO<Notice> list = noticeService.getList(cri);
		model.addAttribute("pageDTO", list);
		//사용자클래스를 선언하면, 스프링이 자동으로 model에 사용자클래스의 변수를 담아서 가지고 나가게됩니다.
		//명시적 선언은 @ModelAttribute("cri") Criteria cri
		return "notice/noticeListAll";
	}
	
	//등록
	@GetMapping("/noticeReg")
	public String noticeReg() {
		return "notice/noticeReg";
	}
	
	//디테일
	@GetMapping("/noticeDetail")
	public String noticeDetail(@RequestParam("nno") Long nno, Model model) {
		Notice notice= noticeService.getDetail(nno);
		model.addAttribute("vo", notice);
		return "notice/noticeDetail";
	}
	
	//수정
	@GetMapping("/noticeModify")
	public String noticeModify(@RequestParam("nno") long nno,
								@RequestParam("writer") String writer,
								HttpSession session,
								Model model) {
		//권한검사
				String userId = (String)session.getAttribute("userId");
				if(userId.equals(writer) == false) {
					return "redirect:/notice/noticeListAll";
				}
				
				
				Notice notice = noticeService.getDetail(nno);
				//조건~~~
				model.addAttribute("vo", notice);
				return "notice/noticeModify";
	}
	
	//나의 목록화면
	@GetMapping("/noticeListMe")
	public String noticeListMe(HttpSession session, Model model) {
		/*
		 * 1. session에 저장된 값을 얻습니다.(세션에는 userId이름으로 값이 저장돼 있습니다.)
		 * 2. 쿼리메서드 or JPQL을 이용해서 repository에 writer기반으로 조회하는 구문을 작성하세요.
		 * 3. 반환은 List<Notice> 타입입니다. 
		 * 4. 화면에서 내가 작성한 글을 출력해주세요.
		 * 
		 */
		String userId=(String)session.getAttribute("userId");
		
		List<Notice> list= noticeService.getListMe(userId);
		model.addAttribute("list", list);
		return "notice/noticeListMe";
	}
	
	//폼등록
	@PostMapping("/noticeForm")
	public String noticeForm(Notice notice) {
		Notice n= noticeService.noticeReg(notice);
		
		return "redirect:/notice/noticeListAll";
	}
	
	//나의 글 수정
	@PostMapping("/noticeUpdate")
	public String noticeUpdate(Notice notice) {
		
		noticeService.noticeUpdate(notice);
		
		return "redirect:/notice/noticeListMe";
				
	}
}
