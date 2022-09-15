package com.myweb.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.basic.command.TopicVO;
import com.myweb.basic.topic.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	@Qualifier("topicService")
	TopicService topicSerivce;
	
	@GetMapping("/topicReg")
	public String topicReg() {
		return "topic/topicReg";
	}
	
	@PostMapping("/topicForm")
	public String productForm(@Valid TopicVO vo, Errors errors, Model model) {
		if(errors.hasErrors()) {
			List<FieldError> list= errors.getFieldErrors();
			for(FieldError err:list) {
				if(err.isBindingFailure()) {
					model.addAttribute("valid_"+err.getField());
			}else {
				model.addAttribute("valid_"+err.getField(),err.getDefaultMessage());
				}
			}
			model.addAttribute("vo",vo);
			return "topic/topicReg";
		}
		boolean result= topicSerivce.topicReg(vo);
		return "redirect:/topic/topicList";
	}
}
