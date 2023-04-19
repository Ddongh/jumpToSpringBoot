package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@RequestMapping("/answer") //답변 컨트롤러의 URL 프리픽스를 /answer로 고정
@RequiredArgsConstructor
@Controller
public class AnswerController {
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	@PostMapping("/create/{id}") //answer/create/{id}와 같은 URL 요청시 createAnswer 메서드가 호출되도록 @PostMapping으로 매핑
								 //@GetMapping과 동일하게 매핑을 담당하는 역할을 하지만 POST요청만 받아들일 경우에 사용
								 //value는 생략
//	public String createAnwer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
	public String createAnwer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult) { //@Valid와 BindingResult를 사용하여 검증
		//@RequestParam String content : 템플릿에서 답변으로 입력한 내용(content)을 얻기 위해 추가
		//content 대신 다른 이름으로 사용하면 오류가 발생
		Question question = this.questionService.getQuestion(id);
		if (bindingResult.hasErrors()) { //검증에 실패할 경우에는 다시 답변을 등록할 수 있는 question_detail 템플릿을 렌더링
			model.addAttribute("question", question); //question_detail 템플릿은 Question 객체가 필요하므로 model 객체에 Question 객체를 저장한 후에 question_detail 템플릿을 렌더링
			return "question_detail";
		}
		// 답변저장
		//this.answerService.create(question, content);
		this.answerService.create(question, answerForm.getContent());

		return String.format("redirect:/question/detail/%s", id);
	}
}
