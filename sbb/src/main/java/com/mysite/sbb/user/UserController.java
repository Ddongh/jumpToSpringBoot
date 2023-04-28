package com.mysite.sbb.user;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signup") ///user/signup URL이 GET으로 요청되면 회원 가입을 위한 템플릿을 렌더링
	public String singup(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	
	@PostMapping("/signup") //POST로 요청되면 회원가입을 진행
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) { //비밀번호1과 비밀번호2가 동일한지를 검증
			bindingResult.rejectValue("passwod2", "passwordInCorrect",
					"2개의 패스워트가 일치하지 않습니다.");
			return "signup_form";
		}
		userService.create(userCreateForm.getUsername(),
				userCreateForm.getEmail(), userCreateForm.getPassword1());
		
		return "redirect:/";
	}
}
