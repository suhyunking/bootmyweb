package com.myweb.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //엔티티리스너를 활성화 시키기 위한 선언
@SpringBootApplication
public class BootmywebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootmywebApplication.class, args);
	}

}
