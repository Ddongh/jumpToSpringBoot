package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration // 스프링의 환경설정 파일임을 나타내는 애너테이션
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
				   // 내부적으로 SpringSecurityFilterChain이 동작하여 URL 필터가 적용
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // 스프링 시큐리티의 세부 설정은 SecurityFilterChain 빈을 생성하여 설정
		http.authorizeHttpRequests().requestMatchers( // 모든 인증되지 않은 요청을 허락한다(밑에 줄 까지)
			new AntPathRequestMatcher("/**")).permitAll()
		.and() // http 객체의 설정을 이어서 할 수 있게
			.csrf().ignoringRequestMatchers(
				new AntPathRequestMatcher("/h2-console/**")) //스프링 시큐리티가 CSRF 처리시 H2 콘솔은 예외로 처리
		.and()
			.headers()
			.addHeaderWriter(new XFrameOptionsHeaderWriter(
				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)) //URL 요청시 X-Frame-Options 헤더값을 sameorigin으로 설정
																		 // frame에 포함된 페이지가 페이지를 제공하는 사이트와 동일한 경우에는 계속 사용할 수 있다.
			;

		return http.build();
	}
}
