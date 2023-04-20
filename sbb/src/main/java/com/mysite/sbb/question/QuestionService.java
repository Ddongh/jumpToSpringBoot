package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import java.time.LocalDateTime;
import com.mysite.sbb.DataNotFoundException;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //@Service 애너테이션이 붙은 클래스는 서비스로 인식한다.
public class QuestionService {
	private final QuestionRepository questionRepository;
	
//	public List<Question> getList() {
//		return this.questionRepository.findAll();
//	}
	public Page<Question> getList(int page) { //정수 타입의 페이지번호를 입력받아 해당 페이지의 질문 목록을 리턴
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate")); //Sort.Order 객체로 구성된 리스트에 Sort.Order 객체를 추가하고 Sort.by(소트리스트)로 소트 객체를 생성
												  //작성일시(createDate)를 역순(Desc)으로 조회
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); //게시물을 역순으로 조회하기 위해서는 위와 같이 PageRequest.of 메서드의 세번째 파라미터로 Sort 객체를 전달
//		Pageable pageable = PageRequest.of(page, 10); //page는 조회할 페이지의 번호이고 10은 한 페이지에 보여줄 게시물의 갯수
		return this.questionRepository.findAll(pageable);
	}
	
	
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	public void create(String subject, String content) { //질문 데이터를 저장
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q);
	}
}
