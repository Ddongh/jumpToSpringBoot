package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //HelloController 클래스가 컨트롤러의 기능을 수행

public class HelloController {
	@GetMapping("/hello") //http://localhost:8080/hello URL 요청이 발생하면 hello 메서드가 실행됨
	@ResponseBody //hello 메서드의 응답 결과가 문자열 그 자체임을 나타낸다.
	public String hello() {
		return "Hello Spring Boot Board";
	}
}
