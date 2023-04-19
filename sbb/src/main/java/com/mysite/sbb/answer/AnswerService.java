package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public void create(Question question, String content) { // 답변생성
		Answer answer = new Answer();
		answer.setContent(content); //입력으로 받은 content
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question); // 입력으로 받은 question을 사용하여 객체 생성
		this.answerRepository.save(answer);
	}

}
