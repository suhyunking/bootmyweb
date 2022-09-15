package com.myweb.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.myweb.basic.util.interceptor.MenuHandler;
import com.myweb.basic.util.interceptor.UserAuthHandler;

@Configuration //스프링부트의 빈 설정파일
public class WebConfig implements WebMvcConfigurer {

	//인터셉터등록
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}

	//메뉴인터셉터등록
	@Bean
	public MenuHandler menuHandler() {
		return new MenuHandler();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor( userAuthHandler() )
				.addPathPatterns("/product/**")
				//.addPathPatterns("/topic/**")
				.addPathPatterns("/user/**")
				.addPathPatterns("/notice/**")
				.addPathPatterns("/main")
				.excludePathPatterns("/user/login");
		
		//혹시 다른 인터셉터가 있다면 추가하면 됩니다.
		//registry.addInterceptor(인터셉터)
		
		registry.addInterceptor(menuHandler())
				.addPathPatterns("/product/**")
				.addPathPatterns("/user/**")
				.addPathPatterns("/notice/**");
		
		
	}
	
	
	
	
	
}
