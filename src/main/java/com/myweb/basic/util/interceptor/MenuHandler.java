package com.myweb.basic.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MenuHandler implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//URI값을 얻어서 모델에 저장
		System.out.println("포스트핸들러MENU:" + request.getRequestURI() );
		request.setAttribute("menu", request.getRequestURI());
		
		
		
	}

	
}
